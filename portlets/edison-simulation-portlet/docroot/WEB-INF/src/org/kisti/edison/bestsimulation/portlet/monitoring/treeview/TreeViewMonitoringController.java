package org.kisti.edison.bestsimulation.portlet.monitoring.treeview;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.MonitoringStatusConstatns;
import org.kisti.edison.util.MyFileIcebreakerTokenUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.sdr.metadata.model.Dataset;
import com.sdr.metadata.service.DatasetLocalServiceUtil;
import com.sdr.metadata.service.DatasetServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class TreeViewMonitoringController{
    private static Log log = LogFactory.getLog(TreeViewMonitoringController.class);
    private static final String SIMULATION_TYPE = "simulation";
    private static final String JOB_TYPE = "job";
    private static final String OSP_PATH_TYPE_DL_ENTRY_ID = "dlEntryId";
    private static final String OSP_PATH_TYPE_FILE = "file";
    private static final String OSP_PATH_TYPE_FILE_CONTENT = "fileContent";
    private static final String OSP_PATH_TYPE_CONTEXT = "context";
    

    @RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model) throws SystemException{
        return "treeview/treeview";
    }
    
    @ResourceMapping(value = "getResultFiles")
    public void getResultFiles(ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        try{
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long groupId = themeDisplay.getSiteGroupId();
            String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge()
                .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
            User user = themeDisplay.getUser();
            IcebreakerVcToken vcToken = MyFileIcebreakerTokenUtil.getOrCreateToken(groupId, user);
            String jobUuid = ParamUtil.getString(request, "jobUuid", "");

            String result = SimulationLocalServiceUtil.retrievePostProcessor(
                icebreakerUrl, vcToken.getVcToken(), jobUuid);
            response.getWriter().write(result);
        }catch (IOException | ParseException e){
            String msg = TreeViewMonitoringController.class.getName() + ".getResultFiles error";
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), msg);
        }
    }
    
    @ResourceMapping(value = "fileDownload")
    public void fileDownload(ResourceRequest request, ResourceResponse response, ModelMap model) 
        throws PortalException, SystemException, IOException{
        Map<String, String[]> params = request.getParameterMap();
        for(String key : params.keySet()){
            for(String str : params.get(key)){
                log.info(key + " : " + str);
            }
        }
        String portName = ParamUtil.getString(request, "portName");
        String jobUuid = ParamUtil.getString(request, "jobUuid");
        SimulationJobData simulationJobData = 
            SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
        JobDataList jobData = 
            new GsonBuilder().create()
            .fromJson(wrapJobDataArray(simulationJobData), JobDataList.class);
        JobData portData = jobData.getPortData(portName);
        if(ObjectUtils.nullSafeEquals(portData.getType_(), OSP_PATH_TYPE_FILE)){
            OSPFileUtil.downloadFile(request, response,
                Paths.get(portData.getParent_(), portData.getName_()).toString(),
                OSPRepositoryTypes.USER_HOME.toString());
        }else if(ObjectUtils.nullSafeEquals(portData.getType_(), OSP_PATH_TYPE_DL_ENTRY_ID)){
            OSPFileUtil.downloadDLEntry(request, response, Long.valueOf(portData.getId_()));
        }else if(ObjectUtils.nullSafeEquals(portData.getType_(), OSP_PATH_TYPE_CONTEXT)
            || ObjectUtils.nullSafeEquals(portData.getType_(), OSP_PATH_TYPE_FILE_CONTENT)){
            OSPFileUtil.downloadFromText(request, response, portName + ".txt", portData.getContext_());
        }
    }
    
    private String wrapJobDataArray(SimulationJobData simulationJobData){
        return "{jobData : " + simulationJobData.getJobData() +"}";
    }
    
    @ResourceMapping(value = "getSimulations")
    public void getSimulations(
        ResourceRequest request, ResourceResponse response, ModelMap model) 
            throws PortalException, SystemException{
        final int LIST_SIZE = 25;
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = PortalUtil.getUser(request);
        long groupId = themeDisplay.getSiteGroupId();
        long parentGroupId = themeDisplay.getScopeGroup().getParentGroupId();
        
        int currentPage = ParamUtil.getInteger(request, "currentPage", 1);
        int start = (currentPage - 1) * LIST_SIZE;
        int end = start + LIST_SIZE;
        String searchKeyword = ParamUtil.getString(request, "searchKeyword", "");
        
        response.setContentType("application/json; charset=UTF-8");
        try{
            if(parentGroupId == 0){
                if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
                    || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
                    || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)){
                    long simulationsCount = SimulationLocalServiceUtil.countByTitle(groupId, searchKeyword);
                    if(simulationsCount > start){
                        List<Simulation> simulations = SimulationLocalServiceUtil.findByTitle(
                            groupId, searchKeyword, start, end);
                        response.getWriter().write(
                            serializeJSONArray(simulationsToJstreeModel(simulations, themeDisplay.getLocale())));
                    }else{
                        response.getWriter().write("[]");
                    }
                }else{
                    long userId = user.getUserId();
                    long simulationsCount = SimulationLocalServiceUtil
                        .countByUserIdAndTitle(groupId, userId, searchKeyword);
                    if(simulationsCount > start){
                        List<Simulation> simulations = SimulationLocalServiceUtil.findByUserIdAndTitle(
                            groupId, userId, searchKeyword, start, end);
                        response.getWriter().write(
                            serializeJSONArray(simulationsToJstreeModel(simulations, themeDisplay.getLocale())));
                    }else{
                        response.getWriter().write("[]");
                    }
                }
            }else{
                if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
                    || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
                    || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)){
                    long simulationsCount = SimulationLocalServiceUtil.countByGroupIdAndTitle(groupId, searchKeyword);
                    if(simulationsCount > start){
                        List<Simulation> simulations = SimulationLocalServiceUtil.findByGroupIdAndTitle(
                            groupId, searchKeyword, start, end);
                        response.getWriter().write(
                            serializeJSONArray(simulationsToJstreeModel(simulations, themeDisplay.getLocale())));
                    }else{
                        response.getWriter().write("[]");
                    }
                }else{
                    long userId = user.getUserId();
                    long simulationsCount = SimulationLocalServiceUtil
                        .countByUserIdAndGroupIdAndTitle(groupId, userId, searchKeyword);
                    if(simulationsCount > start){
                        List<Simulation> simulations = SimulationLocalServiceUtil.findByUserIdAndGroupIdAndTitle(
                            groupId, userId, searchKeyword, start, end);
                        response.getWriter().write(
                            serializeJSONArray(simulationsToJstreeModel(simulations, themeDisplay.getLocale())));
                    }else{
                        response.getWriter().write("[]");
                    }
                }
            }
        }catch(IOException e){
            String msg = TreeViewMonitoringController.class.getName() + ".getSimulations error";
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), msg);
        }
    }
    
    @ResourceMapping(value = "getJobs")
    public void getJobs(
        ResourceRequest request, ResourceResponse response, ModelMap model) 
            throws PortalException, SystemException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String simulationUuid = ParamUtil.getString(request, "simulationUuid", "");
        response.setContentType("application/json; charset=UTF-8");
        try{
            List<SimulationJob> jobs = SimulationJobLocalServiceUtil
                .getJobsBySimulationUuidWithAdditionalCondition(simulationUuid);
            response.getWriter().write(
                serializeJSONArray(jobsToJstreeModel(jobs, themeDisplay.getLocale())));
        }catch(IOException e){
            String msg = TreeViewMonitoringController.class.getName() + ".getJobs error";
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), msg);
        }
    }
    
    @ResourceMapping(value = "jobInfo")
    public String jobInfo(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "jobUuid", required = false)String jobUuid) 
        throws PortalException, SystemException, ParseException, IOException{
        log.info("jobInfo");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        SimulationJob job = SimulationJobLocalServiceUtil.getJob(jobUuid);
        SimulationJobData simulationJobData = 
            SimulationJobDataLocalServiceUtil.getSimulationJobData(job.getJobUuid());
        String publicIceBreakerUrl = (String) GroupLocalServiceUtil
            .getGroup(themeDisplay.getSiteGroupId()).getExpandoBridge()
            .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);
        
        List<Group> childGroups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(),
            themeDisplay.getScopeGroupId(), true);
        for(Group group : childGroups){
            if("sdr".equalsIgnoreCase(group.getName())){
                request.setAttribute("sdrGroupId", group.getGroupId());
            }
        }

        request.setAttribute("job", SimulationJobLocalServiceUtil.getMonitoringJob(
            job.getGroupId(), job.getSimulationUuid(), job.getJobSeqNo()));
        request.setAttribute("jobData", simulationJobData);
        request.setAttribute("myPagePlid", PortalUtil.getPlidFromPortletId(
            themeDisplay.getScopeGroupId(), "edisonmypage_WAR_edisondefault2016portlet"));
        request.setAttribute("workBenchPlid", PortalUtil.getPlidFromPortletId(
            themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet"));
        request.setAttribute("icebreakerUrl", publicIceBreakerUrl);
        
        if(job.getJobStatus() == MonitoringStatusConstatns.SUCCESS){
            Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(job.getSimulationUuid());
            long scienceAppId = Long.valueOf(simulation.getScienceAppId());
            String outputPortJson = ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
            request.setAttribute("outputPortJson", outputPortJson);
        }
        
        return "treeview/job-info";
    }
    
    //에러보기
    @ResourceMapping(value = "errorAPICall")
    public void errorView(ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException, IOException, ParseException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        long groupId = ParamUtil.getLong(request, "groupId", 0);
        String jobUuid = ParamUtil.getString(request, "jobUuid", ""); 
        User user = themeDisplay.getUser();
        
        String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge()
            .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
        IcebreakerVcToken vcToken = MyFileIcebreakerTokenUtil.getOrCreateToken(groupId, user);
        
        String result = SimulationLocalServiceUtil.errorView(icebreakerUrl, vcToken.getVcToken(), jobUuid);
        result = result.replaceAll("[\"\']", " ");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(result);
    }
    
    //모니터링 삭제
    @ResourceMapping(value = "deleteJob")
    public void deleteJob(ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException, IOException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        String simulationUuid = ParamUtil.getString(request, "simulationUuid", ""); 
        long groupId = ParamUtil.getLong(request, "groupId", 0);
        long jobSeqNo = ParamUtil.getLong(request, "jobSeqNo",0);
        User user = themeDisplay.getUser();
        
        String returnResult = SimulationJobLocalServiceUtil.deleteMonitoring(simulationUuid, groupId, jobSeqNo, user);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(returnResult);
    }
    
    //작업중지 API
    @ResourceMapping(value = "stopAPICall")
    public void stopAPICall(ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException, MalformedURLException, IOException, ParseException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        String simulationUuid = ParamUtil.getString(request, "simulationUuid", ""); 
        long groupId = ParamUtil.getLong(request, "groupId", 0);
        String jobUuid = ParamUtil.getString(request, "jobUuid", "");
        
        String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge()
            .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
        IcebreakerVcToken vcToken = MyFileIcebreakerTokenUtil.getOrCreateToken(groupId, user);
        
        int result = SimulationLocalServiceUtil.cancleJob(icebreakerUrl, vcToken.getVcToken(), simulationUuid, jobUuid);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(Integer.toString(result));
    }
    
    //시뮬레이션 조회
    @ResourceMapping(value = "searchJobList")
    public void searchJobList(ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException, IOException, ParseException{
        
        long jobSeqNo = ParamUtil.getLong(request, "jobSeqNo", 0);
        long groupId = ParamUtil.getLong(request, "groupId", 0);
        String simulationUuid = ParamUtil.getString(request, "simulationUuid", ""); 
        
        response.setContentType("application/json; charset=UTF-8");
        JSONObject jsonObj = null;
        
        if(jobSeqNo==0){
            List<Map<String, Object>> resultList = SimulationJobLocalServiceUtil.getMonitoringJobList(groupId, simulationUuid);
            jsonObj = new JSONObject();
            jsonObj.put("dataList", resultList);
        }else{
            Map<String, Object> resultMap = SimulationJobLocalServiceUtil.getMonitoringJob(groupId, simulationUuid, jobSeqNo);
            jsonObj = new JSONObject();
            jsonObj.put("data", resultMap);
        }
        
        PrintWriter out = response.getWriter();
        out.write(jsonObj.toString());
    }
    
    //모니터링 job status update
    @ResourceMapping(value = "updateJobStatus")
    public void updateJobStatus(ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException{
        long jobSeqNo = ParamUtil.getLong(request, "jobSeqNo", 0);
        String simulationUuid = ParamUtil.getString(request, "simulationUuid", ""); 
        long groupId = ParamUtil.getLong(request, "groupId", 0);
        
        long jobStatus = ParamUtil.getLong(request, "jobStatus",0);
        SimulationJobPK simulationJobPK = new SimulationJobPK(jobSeqNo, simulationUuid, groupId);
        SimulationJob simulationJob = SimulationJobLocalServiceUtil.getSimulationJob(simulationJobPK);
        simulationJob.setJobStatus(jobStatus);
        SimulationJobLocalServiceUtil.updateSimulationJob(simulationJob);
    }
    
    //시뮬레이션 job paramter 조회
    @ResourceMapping(value = "searchJobParam")
    public void searchJobParam(ResourceRequest request, ResourceResponse response)
        throws SystemException, IOException, PortalException, ParseException{
        Map param = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String simulationUuid = CustomUtil.strNull(param.get("simulationUuid"));
        long jobSeqNo = ParamUtil.getLong(request, "jobSeqNo",0);
        String jobUuid = ParamUtil.get(request, "jobUuid","");
        long groupId = themeDisplay.getSiteGroupId();
        
        response.setContentType("application/json; charset=UTF-8");
        
        com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
        //시뮬레이션 JOB 조회
        Map<String,Object> simulationJobMap = SimulationJobLocalServiceUtil
            .getMonitoringJob(groupId, simulationUuid, jobSeqNo);
        
        jsonObj.put("jobStatus", CustomUtil.strNull(simulationJobMap.get("jobStatus")));
        jsonObj.put("jobSubmitDt", CustomUtil.strNull(simulationJobMap.get("jobSubmitDt")));
        jsonObj.put("jobEndDt", CustomUtil.strNull(simulationJobMap.get("jobEndDt")));
        jsonObj.put("stayDt", CustomUtil.strNull(simulationJobMap.get("stayDt")));
        jsonObj.put("executeDt", CustomUtil.strNull(simulationJobMap.get("executeDt")));
        
        long scienceAppId = GetterUtil.get(simulationJobMap.get("scienceAppId"), 0L);

        if(scienceAppId > 0) {
            jsonObj.put("inputPorts", ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId));
        } else {
            jsonObj.put("inputPorts", "");
        }
        
        SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(jobUuid);
        
        if(simulationJobData != null) {
            jsonObj.put("simulationJobData", simulationJobData.getJobData());
        } else {
            jsonObj.put("simulationJobData", "");
        }
        
        PrintWriter out = response.getWriter();
        out.write(jsonObj.toString());
    }
    
    @ResourceMapping(value = "transferJobData")
    public void transferJobDataToSDR(
        @RequestParam(value = "collectionId", required=false) String collectionId,
        @RequestParam(value = "jobUuid", required=false) String jobUuid,
        @RequestParam(value = "scienceAppId", required=false) String scienceAppId,
        @RequestParam(value = "jobTitle", required=false) String jobTitle,
        ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
        //Dataset ds = DatasetServiceUtil.save (location = jobuuid, datatype = scienceAppname)
        //DatasetServiceUtil.curate(ds, sc);
        //DatasetServiceUtil.s
        // jobUuid
        //        - solver(App) Name()
        //        - collection ID
        //        - simulation title - job title (optional)
        //        - service context

        final int REPO_ID = 1;
        Gson result = new GsonBuilder().create();
        Map<String, Object> resultMap  = new HashMap<String, Object>();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        
        log.info("collectionId : " + collectionId);
        log.info("jobUuid : " + jobUuid);
        log.info("scienceAppId : " + scienceAppId);
        log.info("jobTitle : " + jobTitle);
        ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(GetterUtil.getLong(scienceAppId, 0));
        if(scienceApp != null){
            String scienceAppName = scienceApp.getName() + "_" + scienceApp.getVersion();
            log.info("scienceAppName : " + scienceAppName);
            try{
                Dataset ds = DatasetLocalServiceUtil.save(GetterUtil.getLong(collectionId), jobUuid, scienceAppName, jobTitle, REPO_ID, sc);
                DatasetLocalServiceUtil.curate(ds, sc);
                resultMap.put("isComplete", ds != null);
            }catch(Exception e){
                resultMap.put("isComplete", false);
                resultMap.put("msg", e.getMessage());
                log.error("transferJobDataToSDR", e);
            }
        }else{
            String msg = "there are no scienceApp - scienceAppId : " + scienceAppId;
            resultMap.put("isComplete", false);
            resultMap.put("msg", msg);
            log.info(msg);
        }
        
        out.write(result.toJson(resultMap));
        out.flush();
        out.close();
    }
    
    // scienceApp 중간 파일 조회
    @ResourceMapping(value = "scienceAppMiddleFile")
    public void scienceAppMiddleFile(ResourceRequest request, ResourceResponse response)
        throws NumberFormatException, SystemException, IOException, PortalException, ParseException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long plid = themeDisplay.getPlid();
        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);

        long scienceAppId = ParamUtil.getLong(request, "scienceAppId", 0);
        long groupId = ParamUtil.getLong(request, "groupId", 0);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("fileState", false);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        ScienceAppOutputPorts outputPorts = ScienceAppOutputPortsLocalServiceUtil
            .fetchScienceAppOutputPorts(scienceAppId);
        if(outputPorts != null){
            String outputPortsJson = outputPorts.getOutputPorts();
            if(outputPortsJson != null && !outputPortsJson.equals("")){
                JSONObject outputObj = JSONObject.fromObject(JSONSerializer.toJSON(outputPortsJson));
                Iterator<String> itr = outputObj.keys();
                while(itr.hasNext()){
                    String key = itr.next();
                    if(StringUtil.toUpperCase(key).equals("TEMP")){
                        JSONObject jsonPort = outputObj.getJSONObject(key);
                        long analyzer_id = GetterUtil.get(jsonPort.get("defaultAnalyzer_"), 0L);

                        if(analyzer_id > 0){
                            Map<String, Object> portMap = new HashMap<String, Object>();

                            String portName = GetterUtil.get(jsonPort.get("name_"), key);
                            portMap.put("portName", portName);

                            JSONObject outputDataJson = JSONObject
                                .fromObject(JSONSerializer.toJSON(jsonPort.get("outputData_")));
                            String fileType = GetterUtil.get(outputDataJson.get("type_"), "");
                            String fileName = GetterUtil.get(outputDataJson.get("name_"), "");

                            JSONObject dataTypeJson = JSONObject
                                .fromObject(JSONSerializer.toJSON(jsonPort.get("dataType_")));

                            // SEARCH DATATYPE
                            DataType dataType = DataTypeLocalServiceUtil
                                .findDataTypeObject(dataTypeJson.getString("name"), dataTypeJson.getString("version"));
                            if(dataType != null){
                                List<Map<String, Object>> searchList = DataTypeAnalyzerLocalServiceUtil
                                    .retrieveDataTypeAnalyzerList(dataType.getTypeId());

                                // app
                                for(Map<String, Object> searchMap : searchList){
                                    long analyzerAppId = GetterUtil.getLong(searchMap.get("analyzerId"));
                                    if(analyzerAppId != 0){
                                        ScienceApp analyzerApp = ScienceAppLocalServiceUtil
                                            .getScienceApp(analyzerAppId);
                                        if(analyzerApp.getStatus() == 1901004){
                                            portMap.put("token", AuthTokenUtil.getToken(httpRequest, plid,
                                                analyzerApp.getExeFileName()));
                                            portMap.put("scienceAppId", scienceAppId);
                                            portMap.put("editorType", analyzerApp.getEditorType());
                                            portMap.put("name", analyzerApp.getName());
                                            portMap.put("title", analyzerApp.getTitle(themeDisplay.getLocale()));
                                            portMap.put("exeFileName", analyzerApp.getExeFileName());
                                            portMap.put("fileListType", StringUtil.toUpperCase(fileType));
                                            portMap.put("fileListValue", fileName);
                                            portMap.put("plid", plid);
                                            portMap.put("groupId", groupId);
                                        }
                                    }
                                }
                            }

                            // data type id
                            long port_type_id = GetterUtil.getLong(dataType.getTypeId(), 0L);
                            portMap.put("port_type_id", port_type_id);

                            boolean mandatory = GetterUtil.get(jsonPort.get("mandatory"), false);
                            portMap.put("mandatory", mandatory);
                            jsonObj.put("portMap", portMap);
                            jsonObj.put("fileState", true);
                        }
                        break;
                    }
                }
            }
        }
        writer.write(jsonObj.toString());
    }

    private static void handleRuntimeException(Exception ex, HttpServletResponse response, String message)
        throws SystemException{
        try{
            log.error(message, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(message);
            response.flushBuffer();
        }catch (IOException e){
            throw new SystemException(e);
        }
    }

    private static String serializeJSONArray(List<Map<String, Object>> list){
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray != null ? jsonArray.toString() : "[]";
    }
    
    private List<Map<String, Object>> simulationsToJstreeModel(
        List<Simulation> simulations, Locale locale){
      List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
      for(Simulation simulation : simulations){
        result.add(simulationToJstreeModel(simulation, locale));
      }
      return result;
    }

    private Map<String, Object> simulationToJstreeModel(
        Simulation simulation, Locale locale){
      Map<String, Object> modelMap = new HashMap<String, Object>();
      Map<String, Object> aTagAttr = new HashMap<String, Object>();
      aTagAttr.put("title", simulation.getSimulationTitle(locale));
      modelMap.put("id", simulation.getSimulationUuid());
      modelMap.put("text", simulation.getSimulationTitle(locale));
      modelMap.put("type", SIMULATION_TYPE);
      modelMap.put("a_attr", aTagAttr);
      return modelMap;
    }
    
    private List<Map<String, Object>> jobsToJstreeModel(List<SimulationJob> jobs, Locale locale){
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for(SimulationJob job : jobs){
          result.add(jobToJstreeModel(job, locale));
        }
        return result;
    }

    private Map<String, Object> jobToJstreeModel(SimulationJob job, Locale locale){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> aTagAttr = new HashMap<String, Object>();
        aTagAttr.put("title", job.getJobTitle(locale));
        modelMap.put("id", job.getJobUuid());
        modelMap.put("text", job.getJobTitle(locale));
        modelMap.put("type", JOB_TYPE);
        modelMap.put("a_attr", aTagAttr);
        return modelMap;
    }
    
    class JobDataList{
        List<JobData> jobData;
        public List<JobData> getJobData(){
            return jobData;
        }
        public void setJobData(List<JobData> jobData){
            this.jobData = jobData;
        }
        public JobData getPortData(String portName){
            for(JobData portData : this.jobData){
                if(ObjectUtils.nullSafeEquals(portName, portData.getPortName_())){
                    return portData;
                }
            }
            return null;
        }
    }
    
    class JobData{
        private String type_;
        private boolean dirty_;
        private String id_;
        private String portName_;
        private int order_;
        private boolean relative;
        private String name_;
        private String parent_;
        private String context_;
        public String getContext_(){
            return context_;
        }
        public void setContext_(String context_){
            this.context_ = context_;
        }
        public String getParent_(){
            return parent_;
        }
        public void setParent_(String parent_){
            this.parent_ = parent_;
        }
        public String getType_(){
            return type_;
        }
        public void setType_(String type_){
            this.type_ = type_;
        }
        public boolean isDirty_(){
            return dirty_;
        }
        public void setDirty_(boolean dirty_){
            this.dirty_ = dirty_;
        }
        public String getId_(){
            return id_;
        }
        public void setId_(String id_){
            this.id_ = id_;
        }
        public String getPortName_(){
            return portName_;
        }
        public void setPortName_(String portName_){
            this.portName_ = portName_;
        }
        public int getOrder_(){
            return order_;
        }
        public void setOrder_(int order_){
            this.order_ = order_;
        }
        public boolean isRelative(){
            return relative;
        }
        public void setRelative(boolean relative){
            this.relative = relative;
        }
        public String getName_(){
            return name_;
        }
        public void setName_(String name_){
            this.name_ = name_;
        }     
    }
}
