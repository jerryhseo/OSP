/**
	 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.kisti.edison.WFEngine500Exception;
import org.kisti.edison.WFEngineForbiddenException;
import org.kisti.edison.WFEngineNotFoundException;
import org.kisti.edison.WFEngineOtherException;
import org.kisti.edison.WFEngineUnauthorizedException;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowSimulation;
import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.PortTypeLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;
import org.kisti.edison.service.base.WorkflowSimulationJobLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.wfapi.custom.IBUtil;
import org.kisti.edison.wfapi.custom.Transformer;
import org.kisti.edison.wfapi.custom.model.Item;
import org.kisti.edison.wfapi.custom.model.Job;
import org.kisti.edison.wfapi.custom.model.MWorkflow;
import org.kisti.edison.wfapi.custom.model.Simulation;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Longs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

/**
 * The implementation of the workflow simulation job local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.service.WorkflowSimulationJobLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.base.WorkflowSimulationJobLocalServiceBaseImpl
 * @see org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil
 */
public class WorkflowSimulationJobLocalServiceImpl extends WorkflowSimulationJobLocalServiceBaseImpl{
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link
     * org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil} to access
     * the workflow simulation job local service.
     */
    private static Log log = LogFactory.getLog(WorkflowSimulationJobLocalServiceImpl.class);

    // 개발서버 icebreaker file :
    // http://150.183.247.210:8080/ldap/api/file/download?id=
    // 실서버 WORKFLOW IP : 150.183.247.103:9000
    private final String ICEBREAKER_UPLOAD_CLUSTER = "EDISON-CFD";
    // private final String WORKFLOW_ENGINE_URL_PRIVATE =
    // "http://150.183.247.210:8090";
    // private final String WORKFLOW_ENGINE_URL_PRIVATE =
    // "http://10.183.100.103:8090/simflow";
    private final String WORKFLOW_ENGINE_URL_PRIVATE = PropsUtil.get(EdisonPropsUtil.WORKFLOW_ENGIN_URL);
    private final String WORKFLOW_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/WORKFLOW_TEMP";
    // private final String WORKFLOW_INSTANCE_PATH = "/EDISON/LDAP/DATA/";
    private static final String DYNAMIC_CONVERTER = "DynamicConverter";
    private static final String CONTROLLER = "Controller";
    private static final String _DEFAULT_CLUSTER = "EDISON-CFD";
    private static final String _CMD_PREFIX = "cmd";
    private static final String _TEMP_BASE_PATH = "/EDISON/SOLVERS/";
    private static final String _TOKEN_PREFIX = "Basic ";
    
    
    private Ordering<String> scriptFirst = new Ordering<String>(){
        @Override
        public int compare(String left, String right){
            if(left.equals("$cmdscript")){
                return -1;
            }
            if(right.equals("$cmdscript")){
                return 1;
            }
            return 0;
        }
    };
    
    public List<WorkflowSimulationJob> getWorkflowSimulationJobs(long simulationId, String title, long userId, int begin, int end) throws SystemException {
        if(StringUtils.hasText(title)) {
            return workflowSimulationJobPersistence.findBySimulationId_Title_UserId(simulationId, "%" + title + "%", userId, begin, end);    
        }
        return workflowSimulationJobPersistence.findBySimulationId_UserId(simulationId, userId, begin, end);
    }
    
    public int countWorkflowSimulationJobs(long simulationId, String title, long userId) throws SystemException {
        if(StringUtils.hasText(title)) {
            return workflowSimulationJobPersistence.countBySimulationId_Title_UserId(simulationId, "%" + title + "%", userId);    
        }
        return workflowSimulationJobPersistence.countBySimulationId_UserId(simulationId, userId);
    }
    
    // UPDATE META DATA
    public WorkflowSimulationJob updateWorkflowSimulationJob(long simulationJobId, Map<String, Object> params) 
        throws PortalException, SystemException {
        WorkflowSimulationJob simulationJob = this.getWorkflowSimulationJob(simulationJobId);
        if(StringUtils.hasText(CustomUtil.strNull(params.get("title"),null))){
            simulationJob.setTitle(CustomUtil.strNull(params.get("title"), "" ));
        }
        if(StringUtils.hasText(CustomUtil.strNull(params.get("screenLogic"),null))){
            simulationJob.setScreenLogic(CustomUtil.strNull(params.get("screenLogic"), "" ));
        }
        if(StringUtils.hasText(CustomUtil.strNull(params.get("workflowUUID"),null))){
            simulationJob.setWorkflowUUID(CustomUtil.strNull(params.get("workflowUUID"), "" ));
        }
        if(StringUtils.hasText(CustomUtil.strNull(params.get("reuseWorkflowUUID"),null))){
            simulationJob.setReuseWorkflowUUID(CustomUtil.strNull(params.get("reuseWorkflowUUID"), "" ));
        }
        simulationJob.setModifiedDate(new Date());
        return this.updateWorkflowSimulationJob(simulationJob);
    }

    // COPY
    @SuppressWarnings("unchecked")
    public WorkflowSimulationJob copyWorkflowSimulationJob(
        long sourceSimulationJobId, Map<String, Object> params)
        throws SystemException, PortalException{
        WorkflowSimulationJob sourceJob = this.getWorkflowSimulationJob(sourceSimulationJobId);
        String simulationJobTitle = GetterUtil.getString(params.get("title"));

        WorkflowSimulationJob targetJob = createWorkflowSimulationJob();
        targetJob.setWorkflowId(sourceJob.getWorkflowId());
        targetJob.setSimulationId(sourceJob.getSimulationId());
        if(StringUtils.hasText(simulationJobTitle)){
            targetJob.setTitle(simulationJobTitle);
        }else{
            targetJob.setTitle("copy " + sourceJob.getTitle());
        }
        targetJob.setUserId(sourceJob.getUserId());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String, Object> screenLogic = gson.fromJson(sourceJob.getScreenLogic(), Map.class);
        List<Map<String, Object>> nodes = (List<Map<String, Object>>) screenLogic.get("nodes");
        for(Map<String, Object> node : nodes) {
            Map<String, String> status = Maps.newHashMap();
            status.put("status", "WAITING");
            node.put("status", status);
            node.put("ibData", Maps.newHashMap());
        }
        targetJob.setScreenLogic(gson.toJson(screenLogic));
//        targetJob.setScreenLogic(sourceJob.getScreenLogic());
        targetJob = WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationJob(targetJob);
        WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(targetJob.getSimulationId(),
            targetJob.getSimulationJobId());
        return targetJob;
    }
    
    // CREATE
//    public WorkflowSimulationJob createWorkflowSimulationJob(
//        long simulationId, Map<String, Object> params, HttpServletRequest request) 
//            throws SystemException, PortalException, IOException{
//        WorkflowSimulation simulation = WorkflowSimulationLocalServiceUtil.getWorkflowSimulation(simulationId);
//        Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(simulation.getWorkflowId());
//        String icebreakerToken = GetterUtil.getString(params.get("icebreakerVcToken"));
//        String simulationJobTitle = GetterUtil.getString(params.get("title"));
//        
//        WorkflowSimulationJob simulationJob = createSimulationJob(simulation, workflow, simulationJobTitle);
//        
//        JsonNode workflowJson = createWorkflow(simulationJob, icebreakerToken, request);
//        simulationJob.setWorkflowUUID(askForCreateWorkflow(workflowJson));
//        return createWorkflowSimulationJob(simulationJob);
//    }
    
    
//  private JsonNode createWorkflow(WorkflowSimulationJob simulationJob, String icebreakerVcToken, HttpServletRequest request)
//      throws SystemException, PortalException, IOException{
//      long companyId = PortalUtil.getCompanyId(request);
//      User user = PortalUtil.getUser(request);
//      Locale locale = PortalUtil.getLocale(request);
//      String execPath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
//      JSONObject screenLogic = JSONFactoryUtil.createJSONObject(simulationJob.getScreenLogic());
//      JSONObject outPorts = screenLogic.getJSONObject("outPorts");
//      JSONArray simulationJsonArray = screenLogic.getJSONArray("elements");
//      MWorkflow mWorkflow = new MWorkflow();
//      mWorkflow.setTitle(simulationJob.getTitle());
//      mWorkflow.setAccessToken("Basic " + icebreakerVcToken);
//      mWorkflow.setUserId(user.getScreenName());
//      mWorkflow.setSimulations(getSimulations(user, execPath, locale, simulationJsonArray, outPorts, icebreakerVcToken));
//      return Transformer.pojo2Json(mWorkflow);
//  }
    
    @SuppressWarnings("unchecked")
    public WorkflowSimulationJob createWorkflowEngineJson(
        long simulationJobId, String strNodes, String userName, String ibToken, HttpServletRequest request) throws WFEngine500Exception{
        if(!StringUtils.hasText(strNodes)){
        }
        try{
            WorkflowSimulationJob job = WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(simulationJobId);
            WorkflowSimulation workflowSimulation = WorkflowSimulationLocalServiceUtil.getWorkflowSimulation(job.getSimulationId());
            String title = workflowSimulation.getTitle();
            PermissionChecker checker = PermissionCheckerFactoryUtil.create(PortalUtil.getUser(request));
            PermissionThreadLocal.setPermissionChecker(checker);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            org.kisti.edison.wfapi.custom.model.Workflow workflow = new org.kisti.edison.wfapi.custom.model.Workflow();
            workflow.setTitle(title);
            workflow.setUserId("edison".equals(userName) ? "edisonadm" : userName);
            workflow.setAccessToken(_TOKEN_PREFIX + ibToken);
            List<Simulation> simulations = Lists.newArrayList();
            List<Map<String, Object>> nodes = (List<Map<String, Object>>) gson.fromJson(strNodes, List.class);
            for(Map<String, Object> node : nodes){
                Simulation simulation = handleNode(node, userName, request);
                if(simulation != null){
                    simulations.add(simulation);
                }
            }
            workflow.setSimulations(simulations);
            JsonElement workflowJson = gson.toJsonTree(workflow);
            JsonObject root = new JsonObject();
            root.add("workflow", workflowJson);
            String workflowUUID = askForCreateWorkflow(gson.toJson(root));
            System.out.println(workflowUUID);
            if(StringUtils.hasText(workflowUUID)){
                job.setWorkflowUUID(workflowUUID);
                return startWorkflowSimulationJob(job);
            } else {
                return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(simulationJobId);
            }
        }catch (Exception e){
            throw new WFEngine500Exception(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private Simulation handleNode(Map<String, Object> node, String userScreenName, HttpServletRequest request) 
        throws PortalException, SystemException, IOException {
        
        String execPath = PrefsPropsUtil.getString(PortalUtil.getCompanyId(request), EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
        execPath = execPath != null ? execPath :_TEMP_BASE_PATH;
        Simulation simulation = new Simulation();
        List<Job> jobs = Lists.newArrayList();
        simulation.setBackend("torque");
        Job job = new Job();
        job.setCluster(_DEFAULT_CLUSTER);
        if(node.get("scienceAppData") != null){
            Map<String, Object> scienceAppData = (Map<String, Object>) node.get("scienceAppData");
            String runType = CustomUtil.strNull(scienceAppData.get("runType"));
            String appType = CustomUtil.strNull(scienceAppData.get("appType"));
            long scienceAppId = GetterUtil.getLong(scienceAppData.get("scienceAppId"));
            ScienceApp scienceApp = null;
            if(ObjectUtils.nullSafeEquals(appType, DYNAMIC_CONVERTER)){
                scienceApp = ScienceAppLocalServiceUtil.createScienceApp(-1);
                scienceApp.setTitle("Dynamic Converter");
                scienceApp.setName("Dynamic Converter");
                scienceApp.setAppType(appType);
//                scienceApp.setGroupId(data.getLong("groupId"));
                simulation.setBackend("docker");
            }else if(ObjectUtils.nullSafeEquals(appType, CONTROLLER)){
                scienceApp = ScienceAppLocalServiceUtil.createScienceApp(-1);
                scienceApp.setTitle("Switcher");
                scienceApp.setName("Switcher");
                scienceApp.setAppType(appType);
//                scienceApp.setGroupId(data.getLong("groupId"));
                simulation.setBackend("docker");
            }else{
                scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
            }
            
            simulation.setClientId(CustomUtil.strNull(node.get("id")));
            simulation.setTitle(CustomUtil.strNull(scienceAppData.get("name")));
            simulation.setDescription(CustomUtil.strNull(scienceAppData.get("text")));
            simulation.setSolverId(GetterUtil.getLong(scienceAppData.get("scienceAppId")));
            
            if(node.containsKey("childNodes")) {
                simulation.setChildNodes((List<String>) node.get("childNodes"));
            }
            if(node.containsKey("parentNodes")) {
                simulation.setParentNodes((List<String>) node.get("parentNodes"));
            }
            if(node.containsKey("outPort")) {
                simulation.setOutPort((Map<String, List<String>>) node.get("outPort"));
            }
            if(node.containsKey("outPortFile")) {
                simulation.setOutPortFile((Map<String, String>) node.get("outPortFile"));
            }
            
            job.setTitle(simulation.getTitle());
            job.setDescription(simulation.getDescription());
            job.setSolverId(simulation.getSolverId());
            job.setSolverName(CustomUtil.strNull(scienceAppData.get("name")));
            
            job.setDependencies(Lists.newArrayList(
                new Item("dummyKey", "dummyValue")));
            
            List<Item> attributes = Lists.newArrayList();
            if(ScienceAppConstants.APP_RUNTYPE_PARALLEL.equals(runType)){
                job.setType(ScienceAppConstants.APP_RUNTYPE_PARALLEL.toUpperCase());
                attributes.add(new Item("np", GetterUtil.getLong(scienceAppData.get("defaultCpus"))));
                job.setCategory(CustomUtil.strNull(scienceAppData.get("parallelModule")).toUpperCase());
            } else {
                job.setType(ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL.toUpperCase());
            }
            job.setAttributes(attributes);
            
            if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                job.setExecutable(execPath + "DCExcution/1.0.0/bin/DCExcution.sh");
            }else if(ObjectUtils.nullSafeEquals(CONTROLLER, scienceApp.getAppType())){
                job.setExecutable(execPath + "WFSwitcher/1.0.0/bin/WFSwitcher.sh");
            }else{
                job.setExecutable(
                    execPath + ScienceAppLocalServiceUtil.getScienceAppBinPath(scienceApp.getScienceAppId()));
            }
            List<Map<String, Object>> arrInputPorts = (List<Map<String, Object>>) node.get("arrInputPorts");
            List<String> connectedPorts = node.containsKey("connectedPorts") ? (List<String>) node.get("connectedPorts") : null;
            List<Item> files = Lists.newArrayList();
            List<String> executions = Lists.newArrayList();
            for(Map<String, Object> inputPort : arrInputPorts) {
                String portName = CustomUtil.strNull(inputPort.get("name_"));
                Item file = handleInputPort(request, inputPort, connectedPorts);
                if(file != null){
                    files.add(file);
                    executions.add(
                        file.getKey().replace("cmd", "") + " $" +file.getKey());                    
                }
                if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                    executions.add("$cmd" + portName);
                }
                // TODO : update ScreenLogic
            }
            if(executions.size() > 0){
                if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                    Collections.sort(executions, scriptFirst);
                }
                job.setExecution(Joiner.on(" ").join(executions));
            }
            if(files.size() > 0) {
                job.setFiles(files);
            }
        }
        jobs.add(job);
        simulation.setJobs(jobs);
        return simulation;
        
    }

    @SuppressWarnings("unchecked")
    private Item handleInputPort(HttpServletRequest request, Map<String, Object> inputPort, List<String> connectedPorts)
        throws PortalException, SystemException, IOException{
        String portName = CustomUtil.strNull(inputPort.get("name_"));
        String userName = PortalUtil.getUser(request) != null ? PortalUtil.getUser(request).getScreenName() : null;
        if(inputPort.containsKey("inputs_")){
            Long groupId = ParamUtil.getLong(request, "groupId");
            String token = ParamUtil.getString(request, "icebreakerVcToken");
            Map<String, Object> inputs = (Map<String, Object>) inputPort.get("inputs_");
            String pathType = CustomUtil.strNull(inputs.get("type_"));
//            System.out.println(portName);
//            System.out.println(pathType);
            
            String inputParent = CustomUtil.strNull(inputs.get("parent_"));
            String inputFileName = CustomUtil.strNull(inputs.get("name_"));
//            System.out.println(inputParent);
//            System.out.println(inputFileName);
            Item file = new Item(_CMD_PREFIX + portName);
            String ibFileId = null;
            
            if( pathType.equalsIgnoreCase("fileContent") ){
                Path parentPath = null;
                if( Validator.isNull(inputParent) ){
                    SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
                    parentPath = Paths.get(dateForm.format(new Date()));
                }
                else{
                    parentPath = Paths.get(inputParent);
                }
                
                if( Validator.isNull(inputFileName) ){
                    inputFileName = portName.replaceAll("-", "");
                }
                
                String content = CustomUtil.strNull(inputs.get("context_"));
                String target = Paths.get(parentPath.toString(), inputFileName).toString();
                
                String repositoryType = OSPRepositoryTypes.USER_HOME.toString();
                String targetPath = IBUtil.saveFileContent(userName, target, content, repositoryType);
                System.out.println(targetPath);
//                ibFileId = IBUtil.getFileId(targetPath);
                if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                    ibFileId = WorkflowSimulationJobLocalServiceUtil.getFileId(groupId, token, targetPath);
                }else {
                    ibFileId = uploadFileToIcebreaker(groupId, token, new File(targetPath));
                }
            }else if( pathType.equalsIgnoreCase("dlEntryId")){
                long fileEntryId = GetterUtil.getLong(inputs.get("content_"));
                Path parentPath = null;
                if( Validator.isNull(inputParent) ){
                    SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
                    parentPath = Paths.get(dateForm.format(new Date()));
                }
                else{
                    parentPath = Paths.get(inputParent);
                }
                if( Validator.isNull(inputFileName) ){
                    try {
                        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
                        inputFileName = fileEntry.getTitle();
                    } catch (PortalException | SystemException e) {
                        throw new WFEngineOtherException(e);
                    }
                }
                String repositoryType = OSPRepositoryTypes.USER_HOME.toString();
                String filePath = IBUtil.copyDLEntryFile(
                        fileEntryId, 
                        userName, 
                        parentPath.resolve(inputFileName).toString(),
                        repositoryType,
                        true);
                System.out.println(filePath);
                if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                    ibFileId = WorkflowSimulationJobLocalServiceUtil.getFileId(groupId, token, filePath);
                }else {
                    ibFileId = uploadFileToIcebreaker(groupId, token, new File(filePath));
                }
            }else if( pathType.equalsIgnoreCase("file")){
                Path target = Paths.get(inputParent).resolve(inputFileName);
                Path targetPath = OSPFileLocalServiceUtil.getRepositoryPath(
                    userName, target.toString(),
                    OSPRepositoryTypes.USER_HOME.toString());
                System.out.println(targetPath.toString());
                if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                    ibFileId = WorkflowSimulationJobLocalServiceUtil.getFileId(groupId, token, targetPath.toString());
                }else {
                    ibFileId = uploadFileToIcebreaker(groupId, token, new File(targetPath.toString()));
                }
            }else if( pathType.equalsIgnoreCase("contents") ){
//                        JSONObject argVal = JSONFactoryUtil.createJSONObject();
//                        argVal.put("type", "STRING");
//                        argVal.put("value", inputData.getString("context_"));
//                        progArgs.put(portName, argVal);
//                        files.put(portName, inputData.getString("context_"));
            }
            if(ibFileId != null) {
                System.out.println(file.getKey());
                System.out.println(ibFileId);
                file.setValue(ibFileId);
                return file;
            }
            return null;
        }else if(connectedPorts != null && connectedPorts.contains(portName)){
            return new Item(_CMD_PREFIX + portName);
        }
        return null;
    }
    
    public String getSimulationJobSeq(long simulationId) throws SystemException {
        return String.format("#%04d", 
            getWorkflowSimulationWorkflowSimulationJobsCount(simulationId) + 1);
    }

    public WorkflowSimulationJob createSimulationJob(
        WorkflowSimulation simulation, Workflow workflow, String simulationJobTitle) 
        throws SystemException, PortalException{
        WorkflowSimulationJob simulationJob = createWorkflowSimulationJob(); 
        simulationJob.setCreateDate(new Date());
        simulationJob.setWorkflowId(simulation.getWorkflowId());
        simulationJob.setSimulationId(simulation.getSimulationId());
        if(StringUtils.hasText(simulationJobTitle)) {
            simulationJob.setTitle(simulationJobTitle);            
        } else {
            simulationJob.setTitle(getSimulationJobSeq(simulation.getSimulationId()));    
        }
        simulationJob.setUserId(simulation.getUserId());
        simulationJob.setScreenLogic(workflow.getScreenLogic());
        WorkflowSimulationJob savedWorkflowSimulationJob = WorkflowSimulationJobLocalServiceUtil
            .addWorkflowSimulationJob(simulationJob);
        WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(simulation.getSimulationId(), simulationJob.getSimulationJobId());
        return savedWorkflowSimulationJob;
    }
    
    public WorkflowSimulationJob createWorkflowSimulationJob() throws SystemException{
        long simulationJobId = super.counterLocalService.increment();
        return super.workflowSimulationJobLocalService.createWorkflowSimulationJob(simulationJobId);
    }
    
    public WorkflowSimulationJob createWorkflowSimulationJob(WorkflowSimulationJob simulationJob) 
            throws SystemException, IOException, PortalException{
        JsonNode workflowStatusJson = askForWorkflowStatus(simulationJob.getWorkflowUUID());
        MWorkflow workflowStatus = Transformer.json2Pojo(workflowStatusJson, MWorkflow.class);
        simulationJob.setCreateDate(GetterUtil.getDate(workflowStatus.getCreatedTime(),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), new Date()));
        simulationJob.setStatus(workflowStatus.getStatus());
        simulationJob.setStatusResponse(workflowStatusJson.toString());
        return simulationJob;
    }
    
    private String askForCreateWorkflow(String workflowJson) throws PortalException{
        String workflowUuid = null;
        HttpURLConnection conn = null;
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Create Workflow");
        log.info(workflowJson);
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/create");
            conn = (HttpURLConnection) url.openConnection();
            
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            
            OutputStream os = conn.getOutputStream();
            os.write(workflowJson.getBytes());
            os.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            
            String output = "";
            StringBuffer responseBuffer = new StringBuffer();
            
            if(conn.getResponseCode() == HttpStatus.CREATED.value()){
                while((output = br.readLine()) != null){
                    if(!GetterUtil.getString(output).equals("null")){
                        responseBuffer.append(GetterUtil.getString(output));
                    }
                }
                workflowUuid = responseBuffer.toString();
                log.error("workflow UUID : " + workflowUuid);
                
            } else {
                throw passException(conn.getResponseCode(), "Failed WorkflowEngineService [ runWorkflow ]");
            }
        }catch (Exception e){
            if(e instanceof PortalException) {
                throw (PortalException) e;
            }else {
                log.error(e);
                throw passException(-1, "Failed WorkflowEngineService Portal Error [ runWorkflow ]");
            }
        }finally{
            if(conn != null){
                conn.disconnect();
            }
        }
        return workflowUuid;
    }
    
    // RUN(Start)
    public WorkflowSimulationJob startWorkflowSimulationJob(long simulationJobId) 
        throws PortalException, SystemException, IOException{
        return startWorkflowSimulationJob(
            WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(simulationJobId));
    }
    
    public WorkflowSimulationJob startWorkflowSimulationJob(WorkflowSimulationJob simulationJob)
        throws SystemException, PortalException, IOException{
        
        if(StringUtils.hasText(simulationJob.getWorkflowUUID())){
            JsonNode statusJson = askForWorkflowStart(simulationJob.getWorkflowUUID());
            MWorkflow workflowStatus = Transformer.json2Pojo(statusJson, MWorkflow.class);
            simulationJob.setStatus(workflowStatus.getStatus());
            simulationJob.setStatusResponse(statusJson.toString());
            simulationJob.setStartTime(GetterUtil.getDate(workflowStatus.getStartTime(),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), new Date()));
//            simulationJob.setReuseWorkflowUUID("");
        }
        simulationJob = WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob(simulationJob);
        // CacheRegistryUtil.clear();
        return simulationJob;
    }
    
    public JsonNode askForWorkflowStart(String workflowUUID) throws PortalException{
        HttpURLConnection conn = null;
        try{
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/" + workflowUUID + "/start");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            if(conn.getResponseCode() == HttpStatus.OK.value()){
                String output = "";
                StringBuffer responseBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while((output = br.readLine()) != null){
                    if(!GetterUtil.getString(output).equals("null")){
                        responseBuffer.append(GetterUtil.getString(output));
                    }
                }
                String wrappedResponse = wrapWorkflowRoot(responseBuffer.toString());
                return Transformer.string2Json(wrappedResponse);
            }else if(conn.getResponseCode() == HttpStatus.NOT_FOUND.value()){
                return Transformer.string2Json("{\"workflow\" : " + "{" + "\"status\": \"NOT_FOUND\", " + "\"uuid\": \""
                    + workflowUUID + "\"}" + "}");
            }else{
                throw passException(conn.getResponseCode(), "Failed WorkflowEngineService [ getWorkflowStatus ]");
            }
        }catch (Exception e){
            if(e instanceof PortalException) {
                throw (PortalException) e;
            }else {
                log.error(e);
                throw passException(-1, "Failed WorkflowEngineService Portal Error [ runWorkflow ]");
            }
        }finally{
            if(conn != null)
                conn.disconnect();
        }
    }

    // STATUS
    public WorkflowSimulationJob getWorkflowStatus(long simulationJobId)
        throws PortalException, SystemException, IOException{
        WorkflowSimulationJob job = WorkflowSimulationJobLocalServiceUtil
            .getWorkflowSimulationJob(simulationJobId);
        JsonNode workflowStatusJson = askForWorkflowStatus(job.getWorkflowUUID());
        return updateWorkflowSimulationJob(workflowStatusJson, job);
    }

    // DELETE
    public WorkflowSimulation deleteSimulationAndJobs(long simulationId) throws PortalException, SystemException{
        List<WorkflowSimulationJob> jobs = WorkflowSimulationJobLocalServiceUtil
            .getWorkflowSimulationWorkflowSimulationJobs(simulationId);
        for(WorkflowSimulationJob job : jobs){
            try{
                deleteWorkflowSimulationJobWitEngine(job);
            }catch (Exception e){
                log.error("delete WorkflowSimulationJob Error - simulationJobId : " + job.getSimulationJobId());
                throw new SystemException(e);
            }
        }
        return WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulation(simulationId);
    }
    
    public WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(long simulationJobId) 
        throws PortalException, SystemException, IOException{
        return deleteWorkflowSimulationJobWitEngine(this.getWorkflowSimulationJob(simulationJobId));
    }

    public WorkflowSimulationJob deleteWorkflowSimulationJobWitEngine(WorkflowSimulationJob job)
        throws PortalException, SystemException, IOException{
        if(StringUtils.hasText(job.getWorkflowUUID())) {
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/" + job.getWorkflowUUID());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            if(conn.getResponseCode() == HttpStatus.OK.value()){
            }else{
                throw passException(conn.getResponseCode(), 
                    "Failed WorkflowEngineService [ deleteWorkflowSimulationJobWitEngine ]");
            }
            conn.disconnect();
        }
        return this.deleteWorkflowSimulationJob(job);
    }
    
    public WorkflowSimulationJob pauseWorkflowSimulationJob(long simulationJobId)
        throws PortalException, SystemException, IOException{
        WorkflowSimulationJob simulationJob = WorkflowSimulationJobLocalServiceUtil
            .getWorkflowSimulationJob(simulationJobId);
        URL url = new URL(
            WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/" + simulationJob.getWorkflowUUID() + "/pause");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");

        if(conn.getResponseCode() == HttpStatus.OK.value()){
        }else{
            throw passException(conn.getResponseCode(), "Failed WorkflowEngineService [ pauseWorkflowSimulationJob ]");
        }
        conn.disconnect();
        return getWorkflowStatus(simulationJobId);
    }

    public WorkflowSimulationJob resumeWorkflowSimulationJob(long simulationJobId)
        throws PortalException, SystemException, IOException{
        WorkflowSimulationJob simulationJob = WorkflowSimulationJobLocalServiceUtil
            .getWorkflowSimulationJob(simulationJobId);
        URL url = new URL(
            WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/" + simulationJob.getWorkflowUUID() + "/resume");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");

        if(conn.getResponseCode() == HttpStatus.OK.value()){
        }else{
            throw passException(conn.getResponseCode(), "Failed WorkflowEngineService [ resumeWorkflowSimulationJob ]");
        }
        conn.disconnect();
        return getWorkflowStatus(simulationJobId);
    }

    public WorkflowSimulationJob updateWorkflowSimulationJob(
        JsonNode workflowStatusJson,
        WorkflowSimulationJob simulationJob) throws SystemException{
        MWorkflow workflowStatus = Transformer.json2Pojo(workflowStatusJson, MWorkflow.class);
        simulationJob.setStatus(workflowStatus.getStatus());
        simulationJob.setStatusResponse(workflowStatusJson.toString());
        simulationJob.setEndTime(
            GetterUtil.getDate(workflowStatus.getEndTime(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), null));
        return updateWorkflowSimulationJob(simulationJob);
    }

    public JsonNode askForWorkflowStatus(String workflowUUID) throws PortalException{
        HttpURLConnection conn = null;
        try{
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/workflow/" + workflowUUID + "/status");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            if(conn.getResponseCode() == HttpStatus.OK.value()){
                String output = "";
                StringBuffer responseBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while((output = br.readLine()) != null){
                    if(!GetterUtil.getString(output).equals("null")){
                        responseBuffer.append(GetterUtil.getString(output));
                    }
                }
                String wrappedResponse = wrapWorkflowRoot(responseBuffer.toString());
                return Transformer.string2Json(wrappedResponse);
            }else if(conn.getResponseCode() == HttpStatus.NOT_FOUND.value()){
                return Transformer.string2Json("{\"workflow\" : " + "{" + "\"status\": \"NOT_FOUND\", " + "\"uuid\": \""
                    + workflowUUID + "\"}" + "}");
            }else{
                throw passException(conn.getResponseCode(), "Failed WorkflowEngineService [ getWorkflowStatus ]");
            }
        }catch (Exception e){
            if(e instanceof PortalException) {
                throw (PortalException) e;
            }else {
                log.error(e);
                throw passException(-1, "Failed WorkflowEngineService Portal Error [ askForWorkflowStatus ]");
            }
        }finally{
            if(conn != null)
                conn.disconnect();
        }
    }

    private String wrapWorkflowRoot(String workflow){
        return "{ \"workflow\": " + workflow + "}";
    }

    public String getWorkflowSimulationLog(String workflowUUID, String simulationUUID, String ibAccessToken)
        throws IOException{
        return askForWorkflowSimulationLog(workflowUUID, simulationUUID, "output", ibAccessToken);
    }

    public String getWorkflowSimulationErrorLog(String workflowUUID, String simulationUUID, String ibAccessToken)
        throws IOException{
        return askForWorkflowSimulationLog(workflowUUID, simulationUUID, "error", ibAccessToken);
    }

    private String askForWorkflowSimulationLog(String workflowUUID, String simulationUUID, String logType,
        String ibAccessToken) throws IOException{
        HttpURLConnection conn = null;
        try{
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/simulation/" + simulationUUID + "/log/" + logType);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");
            conn.setRequestProperty("Authorization", "Basic " + ibAccessToken);
            if(conn.getResponseCode() == HttpStatus.OK.value()){
                String output = "";
                StringBuffer responseBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while((output = br.readLine()) != null){
                    if(!GetterUtil.getString(output).equals("null")){
                        responseBuffer.append(CustomUtil.strNull(output) + "<br/>");
                    }
                }
                return responseBuffer.toString();
            }else{
                throw new RuntimeException("Failed WorkflowEngineService [ askForWorkflowSimulationLog " + logType
                    + " ] : HTTP error code : " + conn.getResponseCode() + ", workflow UUID : " + workflowUUID
                    + ", simulation UUID : " + simulationUUID);
            }
        }finally{
            if(conn != null)
                conn.disconnect();
        }
    }

    public String getWorkflowJobIntermediateResult(String jobUuid, String ibAccessToken) throws IOException{
        return askForWorkflowJobIntermediateResult(jobUuid, ibAccessToken);
    }

    private String askForWorkflowJobIntermediateResult(String jobUuid, String ibAccessToken) throws IOException{
        HttpURLConnection conn = null;
        try{
            URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE + "/job/" + jobUuid + "/log/error");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");
            conn.setRequestProperty("Authorization", "Basic " + ibAccessToken);
            if(conn.getResponseCode() == HttpStatus.OK.value()){
                String output = "";
                StringBuffer responseBuffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while((output = br.readLine()) != null){
                    if(!GetterUtil.getString(output).equals("null")){
                        responseBuffer.append(CustomUtil.strNull(output) + "<br/>");
                    }
                }
                return responseBuffer.toString();
            }else{
                throw new RuntimeException(
                    "Failed WorkflowEngineService [ askForWorkflowJobIntermediateResult ] : HTTP error code : "
                        + conn.getResponseCode() + ", job UUID : " + jobUuid);
            }
        }finally{
            if(conn != null)
                conn.disconnect();
        }
    }

//    private JsonNode createWorkflowJson(WorkflowSimulationJob simulationJob, Map<String, Object> workflowParams,
//        HttpServletRequest request) throws SystemException, PortalException, IOException{
//        long companyId = PortalUtil.getCompanyId(request);
//        Locale locale = PortalUtil.getLocale(request);
//        String exec_path = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
//        JSONObject screenLogic = JSONFactoryUtil.createJSONObject(simulationJob.getScreenLogic());
//        JSONObject outPorts = screenLogic.getJSONObject("outPorts");
//        JSONArray simulationJsonArray = screenLogic.getJSONArray("elements");
//        User user = PortalUtil.getUser(request);
//        String icebreakerVcToken = GetterUtil.getString(workflowParams.get("icebreakerVcToken"));
//        MWorkflow mWorkflow = makeMWorkflow(workflowParams, user, icebreakerVcToken);
//        if(StringUtils.hasText(simulationJob.getReuseWorkflowUUID())){
//            mWorkflow.setReUseWorkflowUuid(simulationJob.getReuseWorkflowUUID());
//        }
//        mWorkflow
//            .setSimulations(getSimulations(user, exec_path, locale, simulationJsonArray, outPorts, icebreakerVcToken));
//        return Transformer.pojo2Json(mWorkflow);
//    }

    private List<MWorkflow.Simulations.Simulation> getSimulations(User user, String execPath, Locale locale,
        JSONArray simulationJsonArray, JSONObject simulationOutPorts, String icebreakerVcToken)
        throws PortalException, SystemException, IOException, JSONException{
        MWorkflow.Simulations simulations = new MWorkflow.Simulations();
        for(int i = 0; i < simulationJsonArray.length(); i++){
            String clientId = simulationJsonArray.getJSONObject(i).getString("id");
            JSONObject data = simulationJsonArray.getJSONObject(i).getJSONObject("data");
            ScienceApp scienceApp;
            MWorkflow.Simulations.Simulation simulation = new MWorkflow.Simulations.Simulation();
            if(ObjectUtils.nullSafeEquals(data.getString("appType"), DYNAMIC_CONVERTER)){
                scienceApp = ScienceAppLocalServiceUtil.createScienceApp(-1);
                scienceApp.setTitle("Dynamic Converter");
                scienceApp.setName("Dynamic Converter");
                scienceApp.setAppType(data.getString("appType"));
                scienceApp.setGroupId(data.getLong("groupId"));
                simulation.setBackend("docker");
            }else if(ObjectUtils.nullSafeEquals(data.getString("appType"), CONTROLLER)){
                scienceApp = ScienceAppLocalServiceUtil.createScienceApp(-1);
                scienceApp.setTitle("Switcher");
                scienceApp.setName("Switcher");
                scienceApp.setAppType(data.getString("appType"));
                scienceApp.setGroupId(data.getLong("groupId"));
                simulation.setBackend("docker");
            }else{
                scienceApp = ScienceAppLocalServiceUtil.getScienceApp(data.getLong("scienceAppId"));
                scienceApp.setTitle(scienceApp.getTitle(locale));
            }
            simulation.setSolverId(String.valueOf(scienceApp.getScienceAppId()));
            simulation.setTitle(scienceApp.getName());
            simulation.setDescription(scienceApp.getTitle());
            simulation.setClientId(clientId);
            simulation.getJobs().add(getJob(user, execPath, data, scienceApp, icebreakerVcToken));
            if(simulationOutPorts.has(clientId)){
                JSONObject outPorts = simulationOutPorts.getJSONObject(clientId);
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, Object>>(){
                }.getType();
                if(outPorts.has("outPort")){
                    Map<String, Object> myMap = gson.fromJson(outPorts.getJSONObject("outPort").toString(), type);
                    simulation.setOutPort(myMap);
                }
                if(outPorts.has("outPortFile")){
                    Map<String, Object> myMap = gson.fromJson(outPorts.getJSONObject("outPortFile").toString(), type);
                    simulation.setOutPortFile(myMap);
                }
            }
            if(ObjectUtils.nullSafeEquals(CONTROLLER, scienceApp.getAppType())){
                simulation.setSwitcher(true);
            }else{
                if(data.getBoolean("startPoint")){
                    simulation.setStartPoint(true);
                }
            }

            if(data.has("parentNodes")){
                JSONArray parentsJson = data.getJSONArray("parentNodes");
                List<String> parents = new ArrayList<String>();
                for(int j = 0; j < parentsJson.length(); j++){
                    parents.add(parentsJson.getString(j));
                }
                if(parents.size() > 0){
                    simulation.setParentNodes(parents);
                }
            }
            if(data.has("childNodes")){
                JSONArray childrenJson = data.getJSONArray("childNodes");
                List<String> children = new ArrayList<String>();
                for(int j = 0; j < childrenJson.length(); j++){
                    children.add(childrenJson.getString(j));
                }
                if(children.size() > 0){
                    simulation.setChildNodes(children);
                }
            }
            simulations.getSimulation().add(simulation);
        }
        return simulations.getSimulation();
    }

    private MWorkflow.Simulations.Simulation.Jobs.Job getJob(User user, String execPath, JSONObject data,
        ScienceApp scienceApp, String icebreakerVcToken)
        throws PortalException, SystemException, IOException, JSONException{
        MWorkflow.Simulations.Simulation.Jobs.Job job = new MWorkflow.Simulations.Simulation.Jobs.Job();
        JSONObject inputports = data.getJSONObject("inputports");
        Iterator<String> inputPortNames = inputports.keys();
        long appGroupId = scienceApp.getGroupId();
        job.setTitle(scienceApp.getName());
        job.setDescription(scienceApp.getTitle());
        job.setSolverName(scienceApp.getName());
        job.setSolverId(String.valueOf(scienceApp.getScienceAppId()));

        if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
            job.setExecutable(execPath + "DCExcution/1.0.0/bin/DCExcution.sh");
            job.setType(ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL.toUpperCase());
        }else if(ObjectUtils.nullSafeEquals(CONTROLLER, scienceApp.getAppType())){
            job.setExecutable(execPath + "WFSwitcher/1.0.0/bin/WFSwitcher.sh");
            job.setType(ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL.toUpperCase());
        }else{
            job.setExecutable(
                execPath + ScienceAppLocalServiceUtil.getScienceAppBinPath(scienceApp.getScienceAppId()));
            job.setType(scienceApp.getRunType().toUpperCase());
        }
        job.getDependencies().add(new MWorkflow.Simulations.Simulation.Jobs.Job.Dependency("dummyKey", "dummyValue"));
        if(ScienceAppConstants.APP_RUNTYPE_PARALLEL.equals(scienceApp.getRunType())){
            String cpuValue = data.has("cpuNumber") ? data.getString("cpuNumber") : data.getString("defaultCpus");
            String category = data.getString("parallelModule");
            MWorkflow.Simulations.Simulation.Jobs.Job.Attribute item = new MWorkflow.Simulations.Simulation.Jobs.Job.Attribute(
                "np", cpuValue);
            job.setCategory(StringUtils.hasText(category) ? category.toUpperCase() : null);
            job.getAttributes().add(item);
        }
        List<String> executions = new ArrayList<String>();
        List<MWorkflow.Simulations.Simulation.Jobs.Job.File> files = new ArrayList<MWorkflow.Simulations.Simulation.Jobs.Job.File>();
        try{
            /* ICEBREAKER_URL 접근을 위한 PermissionChecker - Custom Field */
            PermissionChecker checker;
            checker = PermissionCheckerFactoryUtil.create(user);
            PermissionThreadLocal.setPermissionChecker(checker);
        }catch (Exception e){
            log.error("Failed WorkflowEngineService [ getJob ] : PermissionChecker Error");
            throw new SystemException(e);
        }
        while(inputPortNames.hasNext()){
            String inputPortName = inputPortNames.next();
            JSONObject inputport = inputports.getJSONObject(inputPortName);
            MWorkflow.Simulations.Simulation.Jobs.Job.File item = new MWorkflow.Simulations.Simulation.Jobs.Job.File();

            String editorType = inputport.getString("editorType");
            JSONObject inputValue = inputport.getJSONObject("input-value");
            log.info("editorType : " + editorType);
            if(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK.equals(editorType)){
                String inputData = "";
                if(inputValue != null && inputValue.has("context_")){
                    inputData = inputValue.getString("context_");
                }else{
                    inputData = inputport.getString("input-value");
                }
                String fileId = uploadFileToIcebreaker(appGroupId, icebreakerVcToken, inputData);
                if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                    executions.add("$cmd" + inputPortName);
                    // executions.add("$inputdeck");
                }else{
                    executions.add(inputPortName + " $cmd" + inputPortName);
                    // executions.add(inputPortName + " $inputdeck");
                }
                item.setKey("cmd" + inputPortName);
                if(fileId != null){
                    item.setValue(fileId);
                }
                files.add(item);
            }else if(ScienceAppConstants.EDITOR_TYPE_FILE.equals(editorType)){
                String parentPath = inputport.getString("parentPath");
                String fileName = inputport.getString("fileName");
                Path targetFilePath = OSPFileLocalServiceUtil.getRepositoryPath(user.getScreenName(),
                    Paths.get(parentPath, fileName).toString(), OSPRepositoryTypes.USER_HOME.toString());
                String fileId = getFileId(appGroupId, icebreakerVcToken, targetFilePath.toString());
                if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                    executions.add("$cmd" + inputPortName);
                }else{
                    executions.add(inputPortName + " $cmd" + inputPortName);
                }
                item.setKey("cmd" + inputPortName);
                if(fileId != null){
                    item.setValue(fileId);
                }
                files.add(item);
            }else if(ScienceAppConstants.EDITOR_TYPE_STRING.equals(editorType)){
                if(inputValue != null && inputValue.has("context_")){
                    executions.add(inputPortName + " " + inputValue.getString("context_"));
                }
            }else{
                if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                    executions.add("$cmd" + inputPortName);
                }else{
                    executions.add(inputPortName + " $cmd" + inputPortName);
                }
                item.setKey("cmd" + inputPortName);
                if(inputValue != null && inputValue.has("context_")){
                    String fileId = uploadFileToIcebreaker(appGroupId, icebreakerVcToken,
                        inputValue.getString("context_"));
                    if(fileId != null){
                        item.setValue(fileId);
                    }
                }
                files.add(item);
            }
        }

        if(files.size() > 0){
            job.setFiles(files);
        }
        if(executions.size() > 0){
            if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
                Collections.sort(executions, scriptFirst);
            }
            job.setExecution(Joiner.on(" ").join(executions));
        }
        return job;
    }

    public String getFileId(long appGroupId, String vcToken, String path)
        throws IOException, SystemException, PortalException{
        String fileId = "";
        Group appGroup = GroupLocalServiceUtil.getGroup(appGroupId);
        String fileApiURL = (String) appGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);

        String apiUrl = fileApiURL + "/api/file/encode";

        HttpURLConnection connection = connect(apiUrl, "POST", "application/json", "application/json", vcToken);

        JSONObject jsonPath = JSONFactoryUtil.createJSONObject();
        jsonPath.put("path", path.toString());

        OutputStream outStream = connection.getOutputStream();
        outStream.write(jsonPath.toString().getBytes());
        outStream.flush();
        outStream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuffer responseBuffer = new StringBuffer();

        if(connection.getResponseCode() == 201){
            while((line = reader.readLine()) != null){
                responseBuffer.append(line);
            }
            reader.close();
            String responseFileId = responseBuffer.toString();
            JSONObject response = JSONFactoryUtil.createJSONObject(responseFileId);
            fileId = response.getString("id");
        }else if(connection.getResponseCode() == 400){
            connection.disconnect();
            throw new SystemException(
                "Failed IcebreakerService [ encode file id ] : BAD REQUEST : wrong body content - HTTP error code : "
                    + connection.getResponseCode());
        }
        connection.disconnect();
        return fileId;
    }

    public File downloadFileApi(User user, long appGroupId, JSONObject inputport)
        throws PortalException, SystemException, IOException{
        JSONObject inputValue = inputport.getJSONObject("input-value");
        if(inputValue == null){
            return null;
        }
        String fileId = inputValue.getString("fileId");
        String fileName = inputValue.getString("fileName");
        File downloadedFile = null;
        try{
            /* ICEBREAKER_URL 접근을 위한 PermissionChecker - Custom Field */
            PermissionChecker checker;
            checker = PermissionCheckerFactoryUtil.create(user);
            PermissionThreadLocal.setPermissionChecker(checker);
        }catch (Exception e){
            log.error("Failed WorkflowEngineService [ downloadFileApi ] : PermissionChecker Error");
            throw new SystemException(e);
        }

        if(ObjectUtils.nullSafeEquals(fileId, "SAMPLE")){
            downloadedFile = downloadFromDLFileEntry(inputport, user);
        }else{
            downloadedFile = downloadFromIceBreaker(appGroupId, fileId, fileName);
        }
        return downloadedFile;
    }

    private File downloadFromIceBreaker(long appGroupId, String fileId, String fileName) throws SystemException{
        File downloadedFile = null;
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        try{
            File tempFolder = new File(WORKFLOW_TEMP_PATH);
            if(!tempFolder.exists()){
                tempFolder.mkdirs();
            }
            Group appGroup = GroupLocalServiceUtil.getGroup(appGroupId);
            String fileApiURL = (String) appGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
            URL url = new URL(fileApiURL + "/api/file/download" + "?id=" + fileId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == HttpStatus.OK.value()){
                inputStream = conn.getInputStream();
            }else{
                throw new SystemException(
                    "Failed WorkflowEngineService [ downloadFileApi ] : ETC : etc error - HTTP error code : "
                        + conn.getResponseCode());
            }
            fileName = fileId + "_" + fileName;
            downloadedFile = new File(tempFolder + "/" + fileName);
            if(!downloadedFile.exists()){
                downloadedFile.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(downloadedFile);
            FileCopyUtils.copy(inputStream, os);
            os.flush();
        }catch (Exception e){
            log.error("Failed WorkflowEngineService [ downloadFileApi ] : downloadFromIceBreaker Error");
            throw new SystemException(e);
        }finally{
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }
        return downloadedFile;
    }

    private File downloadFromDLFileEntry(JSONObject inputport, User user)
        throws PortalException, SystemException, IOException{
        File tempFolder = new File(WORKFLOW_TEMP_PATH);
        if(!tempFolder.exists()){
            tempFolder.mkdirs();
        }
        File downloadedFile = null;
        InputStream inputStream = null;
        long portTypeId = inputport.getLong("port-type-id");
        PortType portType = PortTypeLocalServiceUtil.getPortType(portTypeId);
        long fileEntryId = GetterUtil.get(portType.getSampleFilePath(), 0L);
        if(fileEntryId > 0){
            try{
                DLFileEntry dlFileEntry = DLFileEntryServiceUtil.getFileEntry(fileEntryId);
                inputStream = dlFileEntry.getContentStream();
                String fileName = System.currentTimeMillis() + "_" + dlFileEntry.getTitle();
                downloadedFile = new File(tempFolder + "/" + fileName);
                if(!downloadedFile.exists()){
                    downloadedFile.createNewFile();
                }
                FileOutputStream os = new FileOutputStream(downloadedFile);
                FileCopyUtils.copy(inputStream, os);
                os.flush();
            }catch (Exception e){
                log.error("Failed WorkflowEngineService [ downloadFileApi ] : downloadFromDLFileEntry Error");
                throw new SystemException(e);
            }finally{
                if(inputStream != null){
                    try{
                        inputStream.close();
                    }catch (IOException e){
                    }
                }
            }
        }
        return downloadedFile;
    }

    @SuppressWarnings("rawtypes")
    public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, File uploadFile)
        throws PortalException, SystemException, MalformedURLException, IOException{
        Group appGroup = GroupLocalServiceUtil.getGroup(appGroupId);
        String fileApiURL = (String) appGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
        String fileId = null;
        try{
            log.info("uploadFileToIcebreaker - upload target File : " + uploadFile.getAbsolutePath());
            System.out.println(fileApiURL);
            Map returnFileMap = SimulationLocalServiceUtil.uploadFile(ICEBREAKER_UPLOAD_CLUSTER, fileApiURL,
                icebreakerVcToken, uploadFile);
            System.out.println(returnFileMap);
            fileId = CustomUtil.strNull(returnFileMap.get("fileId"));
        }catch (InterruptedException e){
            throw new SystemException(e);
        }
        return fileId;
    }

    public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, InputStream fileContent)
        throws IOException, SystemException, PortalException{
        File tempFolder = new File(WORKFLOW_TEMP_PATH);
        if(!tempFolder.exists()){
            tempFolder.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "_string_editor_input.txt";
        File file = new File(tempFolder + "/" + fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        try{
            FileOutputStream os = new FileOutputStream(file);
            FileCopyUtils.copy(fileContent, os);
        }catch (Exception e){
            log.error("Failed WorkflowEngineService [ downloadFileApi ] : downloadFromDLFileEntry Error");
            throw new SystemException(e);
        }finally{
            if(fileContent != null){
                try{
                    fileContent.close();
                }catch (IOException e){
                }
            }
        }
        return uploadFileToIcebreaker(appGroupId, icebreakerVcToken, file);
    }

    public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, String fileContent)
        throws IOException, SystemException, PortalException{
        if(StringUtils.hasLength(fileContent)){
            return uploadFileToIcebreaker(appGroupId, icebreakerVcToken,
                new ByteArrayInputStream(fileContent.getBytes()));
        }else{
            return null;
        }
    }

    public long getCountWorkflowSimulationJobByUserId(User user, Map<String, Object> params) throws SystemException{
        long groupId = GetterUtil.getLong(params.get("groupId"));
        if(!EdisonUserUtil.isPowerUserThan(user)
            && !EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
            params.put("userId", user.getUserId());
        }
        return workflowFinder.countWorkflowSimulationJobs(params);
    }

    public List<Map<String, Object>> getWorkflowSimulationJobByUserId(User user, Map<String, Object> params,
        Locale locale) throws SystemException{
        long groupId = GetterUtil.getLong(params.get("groupId"));
        if(!EdisonUserUtil.isPowerUserThan(user)
            && !EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
            params.put("userId", user.getUserId());
        }
        return workflowFinder.retrieveWorkflowSimulationJobs(params, locale);
    }

    public List<Map<String, Object>> getDataTypeEditors(DataType dataType) throws SystemException, PortalException{
        List<Map<String, Object>> editorIds = DataTypeEditorLocalServiceUtil
            .retrieveDataTypeEditorList(dataType.getTypeId());
        List<Map<String, Object>> editors = Lists.newArrayList();
        for(Map<String, Object> editorIdMap : editorIds){
            long editorId = GetterUtil.getLong(editorIdMap.get("editorId"), 0);
            ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(editorId);
            Map<String, Object> scienceAppMap = scienceApp.getModelAttributes();
            scienceAppMap.put("isDefault", editorIdMap.get("isDefault"));
            if(ObjectUtils.nullSafeEquals(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK, scienceApp.getEditorType())){
                DataTypeStructure structure = DataTypeStructureLocalServiceUtil
                    .getDataTypeStructure(dataType.getTypeId());
                scienceAppMap.put("structure", structure.getStructure());
            }
            editors.add(scienceAppMap);
        }
        return editors;
    }

    public Map<String, Object> getDataTypeDefaultEditor(DataType dataType) throws SystemException, PortalException{
        List<Map<String, Object>> editors = getDataTypeEditors(dataType);
        for(Map<String, Object> editor : editors){
            if(GetterUtil.getBoolean(editor.get("isDefault"), false)){
                return editor;
            }
        }
        if(editors == null || editors.isEmpty()){
            throw new SystemException("no available Editor.");
        }
        return editors.get(0);
    }

    public Map<String, Object> getDataTypeDefaultAnalyzer(DataType dataType) throws SystemException, PortalException{
        List<Map<String, Object>> analyzers = getDataTypeAnalyzers(dataType);
        for(Map<String, Object> analyzer : analyzers){
            if(GetterUtil.getBoolean(analyzer.get("isDefault"), false)){
                return analyzer;
            }
        }
        if(analyzers == null || analyzers.isEmpty()){
            throw new SystemException("no available Analyzer.");
        }
        return analyzers.get(0);
    }

    public List<Map<String, Object>> getDataTypeAnalyzers(DataType dataType) throws SystemException, PortalException{
        List<Map<String, Object>> analyzerIds = DataTypeAnalyzerLocalServiceUtil
            .retrieveDataTypeAnalyzerList(dataType.getTypeId());
        List<Map<String, Object>> analyzers = Lists.newArrayList();
        for(Map<String, Object> analyzerIdMap : analyzerIds){
            long analyzerId = GetterUtil.getLong(analyzerIdMap.get("analyzerId"), 0);
            ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(analyzerId);
            Map<String, Object> scienceAppMap = scienceApp.getModelAttributes();
            scienceAppMap.put("isDefault", analyzerIdMap.get("isDefault"));
            analyzers.add(scienceAppMap);
        }
        return analyzers;
    }

    public List<AssetCategory> getRootSiteAssetCategries(long companyGroupId, long groupId)
        throws PortalException, SystemException{
        return getSiteAssetCategoriesByParentId(companyGroupId, groupId, 0);
    }

    public List<AssetCategory> getSiteAssetCategoriesByParentId(long companyGroupId, long groupId,
        final long parentCategoryId) throws PortalException, SystemException{
        final AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
            EdisonAssetCategory.GLOBAL_DOMAIN);
        return getSiteAssetCategories(companyGroupId, groupId, new Predicate<AssetCategory>(){
            @Override
            public boolean apply(AssetCategory assetCategory){
                if(assetCategory.getVocabularyId() == aVocabulary.getVocabularyId()
                    && assetCategory.getParentCategoryId() == parentCategoryId){
                    return true;
                }
                return false;
            }
        });
    }

    private HttpURLConnection connect(String apiUrl, String method, String accept, String contentType,
        String icebreakerVcToken) throws IOException{
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(method);
        connection.setRequestProperty("Accept", accept);
        connection.setRequestProperty("Content-Type", contentType + ";charset=utf-8");

        connection.setRequestProperty("Authorization", "Basic " + icebreakerVcToken);
        return connection;
    }

    private List<AssetCategory> getSiteAssetCategories(long companyGroupId, long groupId,
        Predicate<AssetCategory> predication) throws PortalException, SystemException{
        AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId); // 사이트의
                                                                                                   // entry
                                                                                                   // 값
        List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil
            .getAssetEntryAssetCategories(aEntry.getEntryId()); // 사이트에서 사용중인
                                                                // category
        return Lists.newArrayList(Iterables.filter(aCategoryList, predication));
    }

    public List<AssetCategory> getLv1Categories(long companyGroupId, long groupId, Locale locale)
        throws PortalException, SystemException{
        List<AssetCategory> lv1Categories = getRootSiteAssetCategries(companyGroupId, groupId);
        Collections.sort(lv1Categories, new Ordering<AssetCategory>(){
            @Override
            public int compare(AssetCategory left, AssetCategory right){
                return Longs.compare(left.getCategoryId(), right.getCategoryId());
            }
        });
        return lv1Categories;
    }
    
    private PortalException passException(int httpStatusCode, String message) {
        if(httpStatusCode == HttpStatus.UNAUTHORIZED.value()) {
            return new WFEngineUnauthorizedException(message);
        }
        if(httpStatusCode == HttpStatus.FORBIDDEN.value()) {
            return new WFEngineForbiddenException(message);
        }
        if(httpStatusCode == HttpStatus.NOT_FOUND.value()) {
            return new WFEngineNotFoundException(message);
        }
        if(httpStatusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new WFEngine500Exception(message);
        }
        return new WFEngineOtherException(message);
    }
}