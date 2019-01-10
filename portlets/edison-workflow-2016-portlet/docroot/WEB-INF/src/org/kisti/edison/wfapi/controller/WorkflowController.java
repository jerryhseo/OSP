package org.kisti.edison.wfapi.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.model.Workflow;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.service.WorkflowLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.wfapi.custom.WorkflowPagingUtil;
import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("/services/workflows")
public class WorkflowController{
    private Log log = LogFactoryUtil.getLog(getClass());
    
    @RequestMapping(value = "/error")
    public ResponseEntity<?> getWorkflows(HttpServletRequest request)
        throws Exception{
        String errorMessage = "";
        if(EdisonWorkflowError.REQ_LOGIN.equals(request.getAttribute("error"))){
            if(Locale.KOREAN.equals(PortalUtil.getLocale(request))){
                return new ResponseEntity<>("인증되지 않은 사용자입니다. 로그인 후 사용해주세요.", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>("Unauthenticated user. Available after sign in.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getWorkflows(
        @RequestParam Map<String, Object> searchParam,
        HttpServletRequest request)
        throws Exception{
        try{
            Locale locale = PortalUtil.getLocale(request);
            Map<String, Object> listAndPagingMap = new HashMap<>();
            User user = PortalUtil.getUser(request);
            long companyId = PortalUtil.getCompany(request).getCompanyId();
            searchParam.put("userId", user.getUserId());
            searchParam.put("companyId", companyId);

            int curPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("p_curPage"), "1"));
            int linePerPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("linePerPage"), "10"));
            int pagePerBlock = 5;
            int totalCnt = GetterUtil.getInteger(WorkflowLocalServiceUtil.getCountWorkflowsByLikeSearch(searchParam));
            int totalPage = WorkflowPagingUtil.getTotalPage(totalCnt, curPage, linePerPage);

            int begin = (curPage - 1) * linePerPage;
            int end = linePerPage;
            searchParam.put("begin", begin);
            searchParam.put("end", end);

            listAndPagingMap.put("workflows", WorkflowLocalServiceUtil.retrieveWorkflows(searchParam, locale));
            listAndPagingMap.put("curPage", curPage);
            listAndPagingMap.put("totalPage", totalPage);
            listAndPagingMap.put("pagination",
                WorkflowPagingUtil.getPaginationMap(totalCnt, curPage, linePerPage, pagePerBlock));

            return listAndPagingMap;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/{workflowId}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getWorkflow(
        @PathVariable("workflowId") long workflowId,
        HttpServletRequest request) throws Exception{
        try{
            Locale locale = PortalUtil.getLocale(request);
            Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
            int workflowScienceAppCnt = ScienceAppLocalServiceUtil.countScienceAppByWorkflowId(workflowId);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            
            if(0<workflowScienceAppCnt){
            	ScienceApp workflowScienceApp = ScienceAppLocalServiceUtil.getScienceAppByWorkflowId(workflowId);
            	workflowMap.put("isScienceApp", true);
            	workflowMap.put("scienceAppId", workflowScienceApp.getScienceAppId());
            	workflowMap.put("scienceAppName", workflowScienceApp.getName());
            	workflowMap.put("scienceAppVersion", workflowScienceApp.getVersion());
            } else {
            	workflowMap.put("isScienceApp", false);
            }
            
            return workflowMap;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(
        @RequestParam("screenLogic") String screenLogic, 
        @RequestParam("title") String title,
        @RequestParam(required = false, value = "description") String description, HttpServletRequest request){
        try{
            Workflow workflow = WorkflowLocalServiceUtil.createWorkflow(screenLogic, title, description, request);
            Locale locale = PortalUtil.getLocale(request);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            return new ResponseEntity<>(workflowMap, HttpStatus.OK);
        }catch (PortalException | SystemException e){
            String errorMessage = e.getMessage();
            log.error(errorMessage, e);
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{workflowId}/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable("workflowId") long workflowId,
        @RequestBody Map<String, Object> workflowParam, HttpServletRequest request){
        try{
            log.info(workflowParam != null ? workflowParam.toString() : "workflowParam is null");
            Locale locale = PortalUtil.getLocale(request);
            Workflow workflow = WorkflowLocalServiceUtil.updateWorkflow(workflowId, workflowParam, locale);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            return new ResponseEntity<>(workflowMap, HttpStatus.OK);
        }catch (Exception e){
            String errorMessage = e.getMessage();
            log.error(errorMessage, e);
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/{workflowId}/saveas", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveas(
        @PathVariable("workflowId") long workflowId,
        @RequestParam(required = false, value="title") String newTitle,
        @RequestParam(required = false, value="description") String newDescription,
        @RequestParam(required = false, value="screenLogic") String screenLogic,
        HttpServletRequest request) throws Exception{
        try{
            Workflow workflow = null;
            if(StringUtils.hasLength(newTitle)){
                workflow = WorkflowLocalServiceUtil.copyWorkflow(workflowId, newTitle, newDescription, screenLogic, request);
            }else{
                workflow = WorkflowLocalServiceUtil.copyWorkflow(workflowId, newTitle, request);
            }
            Locale locale = PortalUtil.getLocale(request);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            return workflowMap;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/{workflowId}/copy", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> copy(
        @PathVariable("workflowId") long workflowId,
        @RequestBody Map<String, Object> workflowParam,
        HttpServletRequest request) throws Exception{
        try{
            String newTitle = GetterUtil.getString(workflowParam.get("title"));
            Workflow workflow = WorkflowLocalServiceUtil.copyWorkflow(workflowId, newTitle, request);
            Locale locale = PortalUtil.getLocale(request);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            return workflowMap;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/{workflowId}/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> delete(
        @PathVariable("workflowId") long workflowId,
        HttpServletRequest request) throws Exception{
        try{
            Workflow workflow = WorkflowLocalServiceUtil.deleteWorkflow(workflowId);
            Locale locale = PortalUtil.getLocale(request);
            Map<String, Object> workflowMap = workflow.getModelAttributes();
            workflowMap.put("title", workflow.getTitle(locale));
            workflowMap.put("titleMap", workflow.getTitle());
            workflowMap.put("description", workflow.getDescription(locale));
            workflowMap.put("descriptionMap", workflow.getDescription());
            return workflowMap;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/{workflowId}/app/{scienceAppId}/delete", method = RequestMethod.POST)
    public  @ResponseBody Map<String, Object> removeWorkflowWithSciencApp(
	@PathVariable("workflowId") long workflowId,
	@PathVariable("scienceAppId") long scienceAppId,
    HttpServletRequest request) throws Exception{
    try{
    	ScienceAppLocalServiceUtil.deleteScienceAppRelation(scienceAppId);
    	Workflow workflow = WorkflowLocalServiceUtil.deleteWorkflow(workflowId);
        Locale locale = PortalUtil.getLocale(request);
        Map<String, Object> workflowMap = workflow.getModelAttributes();
        workflowMap.put("title", workflow.getTitle(locale));
        workflowMap.put("titleMap", workflow.getTitle());
        workflowMap.put("description", workflow.getDescription(locale));
        workflowMap.put("descriptionMap", workflow.getDescription());
        return workflowMap;
    }catch (Exception e){
        log.error("error", e);
        throw e;
    }
}
}
