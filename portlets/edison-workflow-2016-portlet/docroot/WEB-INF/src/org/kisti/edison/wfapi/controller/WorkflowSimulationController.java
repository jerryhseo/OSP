package org.kisti.edison.wfapi.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowSimulation;
import org.kisti.edison.service.WorkflowLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.wfapi.custom.Transformer;
import org.kisti.edison.wfapi.custom.WorkflowBeanUtil;
import org.kisti.edison.wfapi.custom.WorkflowPagingUtil;
import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowError;
import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.ImmutableMap;
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
    
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String edisonWorkflowException(Exception exception){
        exception.printStackTrace();
        return exception.getMessage();
    }
    
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
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> list(
        @RequestParam Map<String, Object> searchParam,
        HttpServletRequest request)
        throws Exception{
        try{
            Locale locale = PortalUtil.getLocale(request);
            User user = PortalUtil.getUser(request);
            String title = CustomUtil.strNull(searchParam.get("title"), null);
            long workflowId = GetterUtil.getLong(searchParam.get("workflowId"), 0);
            if(workflowId == 0){
                log.error("workflwId is mssing");
                throw new EdisonWorkflowException("need workflowId");
            }
            
            int curPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("p_curPage"), "1"));
            int linePerPage = Integer.parseInt(CustomUtil.strNull(searchParam.get("linePerPage"), "10"));
            int pagePerBlock = 5;
            int totalCnt = GetterUtil
                .getInteger(WorkflowSimulationLocalServiceUtil.getCountWorkflowSimulations(workflowId, title, user.getUserId()));
            int totalPage = WorkflowPagingUtil.getTotalPage(totalCnt, curPage, linePerPage);
            int begin = (curPage - 1) * linePerPage;
            int end = linePerPage;
            
            Map<String, Object> listAndPagingMap = Maps.newHashMap();
            listAndPagingMap.put("simulations",
                WorkflowSimulationLocalServiceUtil.getWorkflowSimulations(workflowId, title, user.getUserId(), begin, end));
            listAndPagingMap.put("userName", user.getScreenName());
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
            /*log.error("error", e);*/
        	log.error("error : Server Index Error");
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
            int end = begin + linePerPage;
            
            List<Map<String, Object>> jobs = WorkflowBeanUtil.simulationJobToJstreeModel(
                WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobs(
                    simulationId, title, user.getUserId(), begin, end));
            
            Map<String, Object> listAndPagingMap = Maps.newHashMap();
            listAndPagingMap.put("jobs", jobs);
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
    
    @RequestMapping(value = {"/{simulationId}/job/seq"}, method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> jobSeq(
        @PathVariable Long simulationId,
        HttpServletRequest request)
            throws Exception{
        try{
            return ImmutableMap.<String, Object> builder()
                .put("seq", WorkflowSimulationJobLocalServiceUtil.getSimulationJobSeq(simulationId))
                .build();
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
            WorkflowSimulation simulation = WorkflowSimulationLocalServiceUtil.getWorkflowSimulation(simulationId);
            Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(simulation.getWorkflowId());
            String simulationJobTitle = GetterUtil.getString(params.get("title"));
            return WorkflowSimulationJobLocalServiceUtil.createSimulationJob(simulation, workflow, simulationJobTitle)
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
        "/{simulationId}/job/{simulationJobId}/copy", 
    "/job/{simulationJobId}/copy"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> copyWorkflowSimulationJob(
        @RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long targetSimulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil.copyWorkflowSimulationJob(targetSimulationJobId, params)
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
    
    @RequestMapping(value = "/{simulationId}/job/{simulationJobId}/create/engine", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> createSimulationJobEngine(
        @PathVariable("simulationId") long simulationId, 
        @PathVariable("simulationJobId") long simulationJobId, 
        @RequestParam("strNodes") String strNodes,
        @RequestParam("icebreakerVcToken") String icebreakerVcToken,
        HttpServletRequest request) throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            return WorkflowSimulationJobLocalServiceUtil.createWorkflowEngineJson(
                simulationJobId, strNodes, user.getScreenName(), icebreakerVcToken, request).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}/rerun", 
        "/job/{simulationJobId}/rerun"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> reRunSimulationJob(
        @PathVariable("simulationId") long simulationId, 
        @PathVariable("simulationJobId") long simulationJobId, 
        @RequestParam("strNodes") String strNodes,
        @RequestParam("icebreakerVcToken") String icebreakerVcToken,
        HttpServletRequest request) throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            return WorkflowSimulationJobLocalServiceUtil.rerunWorkflowEngineJson(
                simulationJobId, strNodes, user.getScreenName(), icebreakerVcToken, request).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    /* 2019.02.25 _ Add reuse copy API */
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}/reuse/copy"}, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> reuseCopySimulationJob(
        @PathVariable("simulationId") long simulationId, 
        @PathVariable("simulationJobId") long simulationJobId, 
        @RequestParam("strNodes") String strNodes,
        @RequestParam("icebreakerVcToken") String icebreakerVcToken,
        @RequestParam("title") String title,
        HttpServletRequest request) throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            return WorkflowSimulationJobLocalServiceUtil.reuseCopyWorkflowEngineJson(
                simulationJobId, strNodes, user.getScreenName(), icebreakerVcToken, request, title).getModelAttributes();
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = {
        "/{simulationId}/job/{simulationJobId}/export", 
        "/job/{simulationJobId}/export"}, method = RequestMethod.POST)
    public void exportSimulationJob(
        @PathVariable("simulationId") long simulationId, 
        @PathVariable("simulationJobId") long simulationJobId, 
        @RequestParam("strNodes") String strNodes,
        @RequestParam("icebreakerVcToken") String icebreakerVcToken,
        HttpServletRequest request,HttpServletResponse response) throws Exception{
        try{
            User user = PortalUtil.getUser(request);
            String obj = WorkflowSimulationJobLocalServiceUtil.exportWorkflowEngineJson(
                  simulationJobId, strNodes, user.getScreenName(), icebreakerVcToken, request);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(obj.toString());
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
                .string2Json(WorkflowSimulationJobLocalServiceUtil
                    .getWorkflowStatus(simulationJobId).getStatusResponse());
            return status;
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
                .string2Json(WorkflowSimulationJobLocalServiceUtil
                    .pauseWorkflowSimulationJob(simulationJobId).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/job/{simulationJobId}/update-ib", method = RequestMethod.POST)
    public @ResponseBody JsonNode insertIbUuid(
        @RequestParam("jobUuid") String jobUuid,
        @RequestParam("ibSimUuid") String ibSimUuid,
        @RequestParam("ibJobUuid") String ibJobUuid,
        @PathVariable("simulationJobId") long simulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            return WorkflowSimulationJobLocalServiceUtil
                .updateWorflowSimulationJobIbUuid(simulationJobId, jobUuid, ibSimUuid, ibJobUuid);
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/job/{simulationJobId}/pause/{uuid}", method = RequestMethod.POST)
    public @ResponseBody JsonNode pauseWorkflowSimulation(
        @RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, 
        @PathVariable("uuid") String simUuid, 
        HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil
                    .pauseWorkflowSimulation(simulationJobId, simUuid).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/job/{simulationJobId}/resume/{uuid}", method = RequestMethod.POST)
    public @ResponseBody JsonNode resumeWorkflowSimulation(
        @RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, 
        @PathVariable("uuid") String simUuid, 
        HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil
                    .resumeWorkflowSimulation(simulationJobId, simUuid).getStatusResponse());
            return status;
        }catch (Exception e){
            log.error("error", e);
            throw e;
        }
    }
    
    @RequestMapping(value = "/job/{simulationJobId}/resume", method = RequestMethod.POST)
    public @ResponseBody JsonNode resumeWorkflowSimulationJob(@
        RequestParam Map<String, Object> params,
        @PathVariable("simulationJobId") long simulationJobId, 
        HttpServletRequest request) throws Exception{
        try{
            JsonNode status = Transformer
                .string2Json(WorkflowSimulationJobLocalServiceUtil
                    .resumeWorkflowSimulationJob(simulationJobId).getStatusResponse());
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

}
