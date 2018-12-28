package org.kisti.edison.wfapi.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonNode;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.wfapi.custom.Transformer;
import org.kisti.edison.wfapi.custom.WorkflowPagingUtil;
import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("/services/simulation")
public class WorkflowSimulationController{
    private Log log = LogFactoryUtil.getLog(getClass());
    
    @RequestMapping(value = "/error")
    public ResponseEntity<?> error(HttpServletRequest request)
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
    
//////////////////////////////////////////////////////////////////simulation
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> list(
        @RequestParam Map<String, Object> searchParam,
        HttpServletRequest request)
        throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            String title = CustomUtil.strNull(searchParam.get("title"), null);
            
            int curPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("p_curPage"), "1"));
            int linePerPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("linePerPage"), "10"));
            int pagePerBlock = 5;
            int totalCnt = GetterUtil
                .getInteger(WorkflowSimulationLocalServiceUtil.getCountWorkflowSimulations(title, user.getUserId()));
            int totalPage = WorkflowPagingUtil.getTotalPage(totalCnt, curPage, linePerPage);
            int begin = (curPage - 1) * linePerPage;
            int end = linePerPage;
            
            Map<String, Object> listAndPagingMap = Maps.newHashMap();
            listAndPagingMap.put("workflows",
                WorkflowSimulationLocalServiceUtil.getWorkflowSimulations(title, user.getUserId(), begin, end));
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
    
    @RequestMapping(value = "/{simulationId}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> simulation(
        @PathVariable Long simulationId,
        HttpServletRequest request)
            throws Exception{
        try{
            return WorkflowSimulationLocalServiceUtil.getWorkflowSimulation(simulationId).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> createSimulation(
        @RequestParam Map<String, Object> params,
        HttpServletRequest request)
        throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            return WorkflowSimulationLocalServiceUtil.createWorkflowSimulation(params, user).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/{simulationId}/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> removeSimulation(
        @PathVariable Long simulationId,
        @RequestParam Map<String, Object> params,
        HttpServletRequest request)
            throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            return WorkflowSimulationLocalServiceUtil.updateWorkflowSimulation(simulationId, params, user)
                .getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/{simulationId}/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateSimulation(
        @PathVariable Long simulationId,
        @RequestParam Map<String, Object> params,
        HttpServletRequest request)
            throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.deleteSimulationAndJobs(simulationId).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    ////////////////////////////////////////////////////////////////// simulation job
    
    @RequestMapping(value = "/{simulationId}/list", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> jobList(
        @PathVariable Long simulationId,
        @RequestParam Map<String, Object> searchParam,
        HttpServletRequest request)
            throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            String title = CustomUtil.strNull(searchParam.get("title"), null);
            
            int curPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("p_curPage"), "1"));
            int linePerPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("linePerPage"), "10"));
            int pagePerBlock = 5;
            int totalCnt = GetterUtil.getInteger(WorkflowSimulationJobLocalServiceUtil
                .countWorkflowSimulationJobs(simulationId, title, user.getUserId()));
            int totalPage = WorkflowPagingUtil.getTotalPage(totalCnt, curPage, linePerPage);
            int begin = (curPage - 1) * linePerPage;
            int end = linePerPage;
            
            Map<String, Object> listAndPagingMap = Maps.newHashMap();
            listAndPagingMap.put("workflows", WorkflowSimulationJobLocalServiceUtil
                .getWorkflowSimulationJobs(simulationId, title, user.getUserId(), begin, end));
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
    
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}", 
        "/job/{simulationJobId}"}, method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> job(
        @PathVariable Long simulationJobId,
        HttpServletRequest request)
            throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(simulationJobId).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/{simulationId}/job/create", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> createJob(
        @PathVariable Long simulationId,
        @RequestParam Map<String, Object> params,
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.createWorkflowSimulationJob(simulationId, params, request)
                .getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}/update", 
        "/job/{simulationJobId}/update"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateWorkflowSimulationJob(
        @RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob(simulationJobId, params)
                .getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}/delete",
        "/job/{simulationJobId}/delete"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteWorkflowSimulationJob(
        @PathVariable("simulationJobId") long simulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationJobWitEngine(simulationJobId)
                .getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    ///////////////////////////////////////////////////////// ENGINE

    @RequestMapping(value = "/job/{simulationJobId}/run", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> runWorkflow(
        @PathVariable("simulationJobId") long simulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.startWorkflowSimulationJob(simulationJobId).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/job/{simulationJobId}/status", method = RequestMethod.GET)
    public @ResponseBody JsonNode workflowStatus(@RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil.getWorkflowStatus(simulationJobId).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }


//    @RequestMapping(value = "/instance/{workflowInstanceId}/reuse", method = RequestMethod.POST)
//    public @ResponseBody Map<String, Object> reuseWorkflowSimulationJob(
//        @RequestParam Map<String, Object> params,
//        @PathVariable("workflowInstanceId") long workflowInstanceId, 
//        HttpServletRequest request) throws Exception{
//        try{
//            return WorkflowSimulationJobLocalServiceUtil.reuseWorkflowSimulationJob(workflowInstanceId, params).getModelAttributes();
//        }catch (Exception e){
//            log.error("error", e);
//            throw e;
//        }
//    }
    
    @RequestMapping(value = "/job/{simulationJobId}/resume", method = RequestMethod.POST)
    public @ResponseBody JsonNode resumeWorkflowSimulationJob(@RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil.resumeWorkflowSimulationJob(simulationJobId).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/job/engine/{jobUUID}/intermediate", method = RequestMethod.GET)
    public @ResponseBody String intermediateLog(@RequestParam(value = "accessToken") String ibAccessToken,
        @PathVariable("jobUUID") String jobUUID, HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.getWorkflowJobIntermediateResult(jobUUID, ibAccessToken)
                .replaceAll("[\"\']", " ");
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/job/engine/{workflowUUID}/simulation/{simulationUUID}/log",
        method = RequestMethod.GET)
    public @ResponseBody String simulationLog(@RequestParam Map<String, Object> params,
        @PathVariable("workflowUUID") String workflowUUID, @PathVariable("simulationUUID") String simulationUUID,
        @RequestParam(value = "accessToken") String ibAccessToken, HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationLog(workflowUUID, simulationUUID, ibAccessToken)
                .replaceAll("[\"\']", " ");
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/job/engine/{workflowUUID}/simulation/{simulationUUID}/errorlog",
        method = RequestMethod.GET)
    public @ResponseBody String simulationErrorLog(@RequestParam Map<String, Object> params,
        @PathVariable("workflowUUID") String workflowUUID, @PathVariable("simulationUUID") String simulationUUID,
        @RequestParam(value = "accessToken") String ibAccessToken, HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationErrorLog(workflowUUID, simulationUUID, ibAccessToken)
                .replaceAll("[\"\']", " ");
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

    @RequestMapping(value = "/job/{simulationJobId}/pause", method = RequestMethod.POST)
    public @ResponseBody JsonNode pauseWorkflowSimulationJob(@RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil.pauseWorkflowSimulationJob(simulationJobId).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }

//    @SuppressWarnings("serial")
//    @RequestMapping(value = "/job/{simulationJobId}/siblings", method = RequestMethod.GET)
//    public @ResponseBody List<Map<String, Object>> workflowInstanceList(
//        @PathVariable("simulationJobId") long simulationJobId, HttpServletRequest request) throws Exception{
//        try{
//            Locale locale = PortalUtil.getLocale(request);
//            List<Map<String, Object>> jstreeList = new ArrayList<>();
//            Map<String, Object> rootCategory = new HashMap<String, Object>(){
//                {
//                    put("id", "currentWorkflowTop");
//                    put("parent", "#");
//                    put("text", "Current Workflow");
//                    put("type", "root");
//                }
//            };
//            WorkflowSimulationJob currInstance = WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(workflowInstanceId);
//            Workflow workflow = WorkflowSimulationJobLocalServiceUtil.getWorkflow(currInstance.getWorkflowId());
//            List<WorkflowSimulationJob> workflowInstances = WorkflowSimulationJobLocalServiceUtil
//                .getWorkflowWorkflowSimulationJobsByWorkflowId(workflow.getWorkflowId());
//            jstreeList.add(rootCategory);
//            jstreeList.add(WorkflowBeanUtil.workflowToJstreeModel(workflow, locale));
//            jstreeList.addAll(WorkflowBeanUtil.workflowInstanceToJstreeModel(workflowInstances, locale));
//            return jstreeList;
//        }catch (Exception e){
//            log.error("error", e);
//            throw e;
//        }
//    }
//
//    @RequestMapping(value = "/{simulationId}/instances", method = RequestMethod.POST)
//    public @ResponseBody List<Map<String, Object>> workflowInstances(
//        @PathVariable("simulationId") long simulationId,
//        HttpServletRequest request) throws Exception{
//        try{
//            Locale locale = PortalUtil.getLocale(request);
//            Workflow workflow = WorkflowSimulationJobLocalServiceUtil.getWorkflow(simulationId);
//            System.out.println(simulationId);
//            List<WorkflowSimulationJob> workflowInstances = WorkflowSimulationJobLocalServiceUtil
//                .getWorkflowWorkflowSimulationJobsByWorkflowId(simulationId);
//            List<Map<String, Object>> jstreeIntances = WorkflowBeanUtil
//                .workflowInstanceToJstreeModel(workflowInstances, locale);
//            jstreeIntances.add(WorkflowBeanUtil.workflowToJstreeModel(workflow, "#", locale));
//            return jstreeIntances;
//        }catch (Exception e){
//            log.error("error", e);
//            throw e;
//        }
//    }
//    
//    @RequestMapping(value = "/instance", method = RequestMethod.POST)
//    public @ResponseBody Map<String, Object> workflowInstanceListByUser(@RequestParam Map<String, Object> searchParam,
//        HttpServletRequest request) throws Exception{
//        try{
//            Locale locale = PortalUtil.getLocale(request);
//            User user = PortalUtil.getUser(request);
//            Map<String, Object> listAndPagingMap = new HashMap<>();
//            long companyId = PortalUtil.getCompany(request).getCompanyId();
//            searchParam.put("companyId", companyId);
//            String pagingClassName = GetterUtil.getString(searchParam.get("pagingClassName"));
//            int curPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("p_curPage"), "1"));
//            int linePerPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("linePerPage"), "10"));
//            int pagePerBlock = 5;
//            int totalCnt = GetterUtil
//                .getInteger(WorkflowSimulationJobLocalServiceUtil.getCountWorkflowSimulationJobByUserId(user, searchParam));
//            int totalPage = WorkflowPagingUtil.getTotalPage(totalCnt, curPage, linePerPage);
//
//            int begin = (curPage - 1) * linePerPage;
//            int end = linePerPage;
//            searchParam.put("begin", begin);
//            searchParam.put("end", end);
//
//            String pagingHtml = WorkflowPagingUtil.getPaging(request.getContextPath(), pagingClassName, totalCnt,
//                curPage, linePerPage, pagePerBlock);
//
//            listAndPagingMap.put("workflowInstances",
//                WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobByUserId(user, searchParam, locale));
//            listAndPagingMap.put("pagingHtml", pagingHtml);
//            listAndPagingMap.put("curPage", curPage);
//            listAndPagingMap.put("totalPage", totalPage);
//
//            return listAndPagingMap;
//        }catch (Exception e){
//            log.error("error", e);
//            throw e;
//        }
//    }

}
