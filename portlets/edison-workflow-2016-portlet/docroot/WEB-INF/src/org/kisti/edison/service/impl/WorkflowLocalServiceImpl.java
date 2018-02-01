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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowInstance;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.PortTypeLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.service.WorkflowInstanceLocalServiceUtil;
import org.kisti.edison.service.WorkflowLocalServiceUtil;
import org.kisti.edison.service.base.WorkflowLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.wfapi.custom.Transformer;
import org.kisti.edison.wfapi.custom.model.MWorkflow;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Longs;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
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
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

/**
 * The implementation of the workflow local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.service.WorkflowLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.base.WorkflowLocalServiceBaseImpl
 * @see org.kisti.edison.service.WorkflowLocalServiceUtil
 */
public class WorkflowLocalServiceImpl extends WorkflowLocalServiceBaseImpl{
  /*
   * NOTE FOR DEVELOPERS:
   *
   * Never reference this interface directly. Always use {@link
   * org.kisti.edison.service.WorkflowLocalServiceUtil} to access the workflow
   * local service.
   */
	
  
  private static Log log = LogFactory.getLog(WorkflowLocalServiceImpl.class);

  // 개발서버 icebreaker file : http://150.183.247.210:8080/ldap/api/file/download?id=
  //실서버 WORKFLOW IP : 150.183.247.103:9000
  private final String ICEBREAKER_UPLOAD_CLUSTER = "EDISON-CFD";
//  private final String WORKFLOW_ENGINE_URL_PRIVATE = "http://150.183.247.210:8090";
//  private final String WORKFLOW_ENGINE_URL_PRIVATE = "http://10.183.100.103:8090/simflow";
  private final String WORKFLOW_ENGINE_URL_PRIVATE = PropsUtil.get(EdisonPropsUtil.WORKFLOW_ENGIN_URL);
  private final String WORKFLOW_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/WORKFLOW_TEMP";
  private final String WORKFLOW_INSTANCE_PATH = "/EDISON/LDAP/DATA/";
  private static final String DYNAMIC_CONVERTER = "DynamicConverter";
  private Ordering<String> scriptFirst = new Ordering<String>(){
    @Override
    public int compare(String left, String right){
      if(left.equals("$script")){
        return -1;
      }
      if(right.equals("$script")){
        return 1;
      }
      return 0;
    }
  };
  
  public Workflow createWorkflow(String screenLogic, String title, String descrption, HttpServletRequest request)
      throws SystemException, PortalException{
    Workflow workflow = WorkflowLocalServiceUtil.createWorkflow();
    User user = PortalUtil.getUser(request);
    workflow.setTitle(title);
    workflow.setTargetLanguage(LanguageUtil.getLanguageId(PortalUtil.getLocale(request)));
    workflow.setScreenLogic(screenLogic);
    workflow.setUserId(user.getUserId());
    workflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
    workflow.setCreateDate(new Date());
    workflow.setIsPublic(false);
    return super.workflowLocalService.addWorkflow(workflow);
  }

  public Workflow createWorkflow() throws SystemException{
    long workflowId = super.counterLocalService.increment();
    return super.workflowLocalService.createWorkflow(workflowId);
  }
  
    public Workflow copyWorkflow(
        long sourceWorkflowId, String newTitle, String descrption, HttpServletRequest request)
        throws SystemException, PortalException{
      User user = PortalUtil.getUser(request);
      //Locale locale = PortalUtil.getLocale(request);
      Workflow targetWorkflow = super.workflowLocalService.getWorkflow(sourceWorkflowId);
      targetWorkflow.setParentWorkflowId(sourceWorkflowId);
      targetWorkflow.setTitle(newTitle);
      targetWorkflow.setDescription(descrption);
      targetWorkflow.setWorkflowId(super.counterLocalService.increment());
      targetWorkflow.setCreateDate(new Date());
      targetWorkflow.setUserId(user.getUserId());
      targetWorkflow.setIsPublic(false);
      targetWorkflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
      return super.workflowLocalService.addWorkflow(targetWorkflow);
  }
  
  public Workflow copyWorkflow(long sourceWorkflowId, HttpServletRequest request) throws SystemException, PortalException{
    User user = PortalUtil.getUser(request);
    Locale locale = PortalUtil.getLocale(request);
    Workflow targetWorkflow = super.workflowLocalService.getWorkflow(sourceWorkflowId);
    targetWorkflow.setParentWorkflowId(sourceWorkflowId);
    targetWorkflow.setTitle("copied from " + targetWorkflow.getTitle(locale));
    targetWorkflow.setWorkflowId(super.counterLocalService.increment());
    targetWorkflow.setCreateDate(new Date());
    targetWorkflow.setUserId(user.getUserId());
    targetWorkflow.setIsPublic(false);
    targetWorkflow.setCompanyId(PortalUtil.getCompany(request).getCompanyId());
    return super.workflowLocalService.addWorkflow(targetWorkflow);
  }
  
  public Workflow updateWorkflow(long workflowId, Map<String, Object> workflowParam, Locale locale) 
      throws SystemException, PortalException{
    Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
    workflow.setWorkflowModelAttributes(workflowParam, locale);
    workflow.setModifiedDate(new Date());
    return super.workflowLocalService.updateWorkflow(workflow);
  }
  
  public Workflow updateWorkflowTutorial(long workflowId, long tutorialFileEntryId) 
      throws PortalException, SystemException{
    Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
    workflow.setTutorialFileEntryId(tutorialFileEntryId);
    return super.workflowLocalService.updateWorkflow(workflow);
  }
  
  public Workflow updateWorkflow(Map<String, Object> workflowParam) 
      throws SystemException, PortalException{
    long workflowId = GetterUtil.getLong(workflowParam.get("workflowId"));
    Workflow workflow = WorkflowLocalServiceUtil.getWorkflow(workflowId);
    boolean isPublic =  GetterUtil.getInteger(workflowParam.get("isPublic")) == 1 ? true : false;
    String serviceLang = "";
    String[] serviceLangArray = CustomUtil.paramToArray(workflowParam.get("select_languageId"));
    if(serviceLangArray != null && serviceLangArray.length > 0){
      for(int i=0 ; i< serviceLangArray.length; i++){
        serviceLang += CustomUtil.strNull(serviceLangArray[i]) + ",";
      }
    }
    workflow.setTitleMap(CustomUtil.getLocalizationMap(workflowParam, "title"));
    workflow.setDescriptionMap(CustomUtil.getLocalizationMap(workflowParam, "description"));
    workflow.setTargetLanguage(serviceLang);
    workflow.setIsPublic(isPublic);
    workflow.setModifiedDate(new Date());
    return super.workflowLocalService.updateWorkflow(workflow);
  }
  
  public List<Map<String, Object>> retrieveWorkflows(Map<String, Object> searchParam, Locale locale) throws SystemException{
    return workflowFinder.retrieveWorkflows(searchParam, locale);
  }
  
  public WorkflowInstance pauseWorkflowInstance(long workflowInstanceId) throws PortalException, SystemException, IOException{
    WorkflowInstance workflowInstance = WorkflowInstanceLocalServiceUtil.getWorkflowInstance(workflowInstanceId);
    URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/"+workflowInstance.getWorkflowUUID()+"/pause");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("PUT");
    
    if (conn.getResponseCode() == HttpStatus.OK.value()) {
    }else{      
      log.error("Failed WorkflowEngineService [ pauseWorkflowInstance ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
    }
    conn.disconnect();
    return getWorkflowStatus(workflowInstanceId);
  }
  
  public WorkflowInstance resumeWorkflowInstance(long workflowInstanceId) throws PortalException, SystemException, IOException{
    WorkflowInstance workflowInstance = WorkflowInstanceLocalServiceUtil.getWorkflowInstance(workflowInstanceId);
    URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/"+workflowInstance.getWorkflowUUID()+"/resume");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("PUT");
    
    if (conn.getResponseCode() == HttpStatus.OK.value()) {
    }else{      
      log.error("Failed WorkflowEngineService [ resumeWorkflowInstance ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
    }
    conn.disconnect();
    return getWorkflowStatus(workflowInstanceId);
  }
  
  public WorkflowInstance deleteWorkflowInstance(long workflowInstanceId) throws PortalException, SystemException, IOException{
    WorkflowInstance workflowInstance = WorkflowInstanceLocalServiceUtil.getWorkflowInstance(workflowInstanceId);
    URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/"+workflowInstance.getWorkflowUUID());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("DELETE");
    
    if (conn.getResponseCode() == HttpStatus.OK.value()) {
    }else{      
      log.error("Failed WorkflowEngineService [ deleteWorkflowInstance ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
    }
    conn.disconnect();
    return WorkflowInstanceLocalServiceUtil.deleteWorkflowInstance(workflowInstanceId);
  }

  public Workflow deleteWorkflowAndInstances(long workflowId) throws PortalException, SystemException{
    List<WorkflowInstance> instances = WorkflowInstanceLocalServiceUtil.getWorkflowWorkflowInstances(workflowId);
    for(WorkflowInstance instance : instances){
      try{
        deleteWorkflowInstance(instance.getWorkflowInstanceId());
      }catch(Exception e){
        log.error("delete WorkflowInstance Error - workflowInstanceId : " + instance.getWorkflowInstanceId());
        throw new SystemException(e);
      }
    }
    return WorkflowLocalServiceUtil.deleteWorkflow(workflowId);
  }
  
  public WorkflowInstance runWorkflow(
      long workflowId, 
      Map<String, Object> workflowParams,
      HttpServletRequest request) throws SystemException, PortalException, IOException{
    Workflow workflow = getWorkflow(workflowId);
    JSONObject screenLogic = JSONFactoryUtil.createJSONObject(workflow.getScreenLogic());
    boolean isLoopWorkflow = GetterUtil.getString(screenLogic.getString("loopStartElementId")).equals("") ? false : true;
    if(isLoopWorkflow){
      return runLoopWorkflow(workflowParams, request, workflow);
    }else{
      return runNormalWorkflow(workflowParams, request, workflow);
    }
    
  }

  private WorkflowInstance runLoopWorkflow(Map<String, Object> workflowParams,
      HttpServletRequest request, Workflow workflow) throws SystemException, PortalException, IOException{
    Map<String, MWorkflow> workflowPair =  createLoopWorkflow(workflow, workflowParams, request);
    MWorkflow loopWorkflow = workflowPair.get("loopWorkflow");
    MWorkflow loopTargetWorkflow = workflowPair.get("loopTargetWorkflow");
    
    if(loopWorkflow == null || loopTargetWorkflow == null){
      throw new SystemException("LoopWorkflow Empty Error");
    }
    WorkflowInstance workflowInstance = null;
    String loopTargetWorkflowUuid = askForCreateWorkflow(Transformer.pojo2Json(loopTargetWorkflow));
    loopWorkflow.setRefUuid(loopTargetWorkflowUuid);
    String loopWorkflowUuid = askForCreateWorkflow(Transformer.pojo2Json(loopWorkflow));
    if(StringUtils.hasLength(loopWorkflowUuid)){
      workflowInstance = createWorkflowInstance(loopWorkflowUuid, workflow, request);
      startWorkflowInstance(workflowInstance.getWorkflowInstanceId());
    }else{
      throw new SystemException("runNormalWorkflow Failed");
    }
    return workflowInstance;
  }

  private WorkflowInstance runNormalWorkflow(Map<String, Object> workflowParams,
      HttpServletRequest request, Workflow workflow) throws SystemException, PortalException, IOException {
    JsonNode workflowJson = createWorkflow(workflow, workflowParams, request);
    WorkflowInstance workflowInstance = null;
    String workflowUuid = askForCreateWorkflow(workflowJson);
    if(StringUtils.hasLength(workflowUuid)){
      workflowInstance = createWorkflowInstance(workflowUuid, workflow, request);
      startWorkflowInstance(workflowInstance.getWorkflowInstanceId());
    }else{
      throw new SystemException("runNormalWorkflow Failed");
    }
    return workflowInstance;
  }

  private String askForCreateWorkflow(JsonNode workflowJson) throws SystemException{
    String workflowUuid = null;
    HttpURLConnection conn = null;
    try{
      URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/create");
      conn = (HttpURLConnection) url.openConnection();
      
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Content-Type", "application/json");
      
      OutputStream os = conn.getOutputStream();
      os.write(workflowJson.toString().getBytes());
      os.flush();
      
      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
      
      String output = "";  
      StringBuffer responseBuffer = new StringBuffer();
      
      if(conn.getResponseCode() == HttpStatus.CREATED.value()) {
        while ((output = br.readLine()) != null) {
          if(!GetterUtil.getString(output).equals("null")){
            responseBuffer.append(GetterUtil.getString(output));
          }
        }       
        workflowUuid = responseBuffer.toString();
        log.error("workflow UUID : " + workflowUuid);
        
      } else if (conn.getResponseCode() == HttpStatus.BAD_REQUEST.value()) {
        log.info("Failed WorkflowEngineService [ runWorkflow ] : BAD REQUEST : wrong body content - HTTP error code : " + conn.getResponseCode());
      }else{      
        log.error("Failed WorkflowEngineService [ runWorkflow ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
      }
    }catch(Exception e){
      throw new SystemException(e);
    }finally {
      if(conn != null){
        conn.disconnect();
      }
      log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Create Workflow");
      log.info(workflowJson.toString());
      log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Create Workflow");
    }
    return workflowUuid;
  }
  
  public int startWorkflowInstance(long workflowInstanceId) throws PortalException, SystemException, IOException{
    WorkflowInstance workflowInstance = WorkflowInstanceLocalServiceUtil.getWorkflowInstance(workflowInstanceId);
    URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/"+workflowInstance.getWorkflowUUID()+"/start");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("GET");
    
    if (conn.getResponseCode() == HttpStatus.OK.value()) {
    }else{      
      log.error("Failed WorkflowEngineService [ startWorkflowInstance ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
    }
    conn.disconnect();
    return conn.getResponseCode();
  }
  
  public WorkflowInstance createWorkflowInstance(
      String workflowUUID, 
      Workflow workflow, 
      HttpServletRequest request) throws SystemException, IOException{
    WorkflowInstance instance = WorkflowInstanceLocalServiceUtil.createWorkflowInstance();
    JsonNode workflowStatusJson = askForWorkflowStatus(workflowUUID);
    MWorkflow workflowStatus = Transformer.json2Pojo(workflowStatusJson, MWorkflow.class);
    instance.setWorkflowId(workflow.getWorkflowId());
    instance.setCompanyId(workflow.getCompanyId());
    instance.setWorkflowUUID(workflowUUID);
    instance.setTitle(workflowStatus.getTitle());
    instance.setCreateDate(GetterUtil.getDate(workflowStatus.getCreatedTime(),
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), new Date()));
    instance.setUserId(workflow.getUserId()); 
    instance.setStartTime(GetterUtil.getDate(workflowStatus.getStartTime(),
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), new Date()));
    instance.setScreenLogic(workflow.getScreenLogic());
    instance.setStatus(workflowStatus.getStatus());
    instance.setStatusResponse(workflowStatusJson.toString());
    WorkflowInstance savedWorkflowInstance = WorkflowInstanceLocalServiceUtil.addWorkflowInstance(instance);
    WorkflowInstanceLocalServiceUtil.addWorkflowWorkflowInstance(workflow.getWorkflowId(), savedWorkflowInstance.getWorkflowInstanceId());
    return savedWorkflowInstance;
  }
  
  public WorkflowInstance getWorkflowStatus(long workflowInstanceId) throws PortalException, SystemException, IOException{
    WorkflowInstance instance = WorkflowInstanceLocalServiceUtil.getWorkflowInstance(workflowInstanceId);
    JsonNode workflowStatusJson = askForWorkflowStatus(instance.getWorkflowUUID());
    return updateWorkflowInstance(workflowStatusJson, instance);
  }
  
  public WorkflowInstance updateWorkflowInstance(
      JsonNode workflowStatusJson, 
      WorkflowInstance workflowInstance) throws SystemException{
    MWorkflow workflowStatus = Transformer.json2Pojo(workflowStatusJson, MWorkflow.class);
    workflowInstance.setStatus(workflowStatus.getStatus());
    workflowInstance.setStatusResponse(workflowStatusJson.toString());
    workflowInstance.setEndTime(GetterUtil.getDate(workflowStatus.getEndTime(),
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"), null));
    return workflowInstanceLocalService.updateWorkflowInstance(workflowInstance);
  }
  
  public JsonNode askForWorkflowStatus(String workflowUUID) throws IOException{
    HttpURLConnection conn = null;
    try{
      URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/workflow/"+workflowUUID+"/status");
      conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
      if(conn.getResponseCode() == HttpStatus.OK.value()){
        String  output = "";    
        StringBuffer responseBuffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        while ((output = br.readLine()) != null) {
          if(!GetterUtil.getString(output).equals("null")){
            responseBuffer.append(GetterUtil.getString(output));
          }
        }
        String wrappedResponse = wrapWorkflowRoot(responseBuffer.toString());
        return Transformer.string2Json(wrappedResponse);
      }else if(conn.getResponseCode() == HttpStatus.NOT_FOUND.value()){
        return Transformer.string2Json(
            "{\"workflow\" : "
            + "{"
            + "\"status\": \"NOT_FOUND\", "
            + "\"uuid\": \"" + workflowUUID 
            + "\"}"
            +"}"
            );
      }else{
        throw new RuntimeException(
            "Failed WorkflowEngineService [ getWorkflowStatus ] : HTTP error code : "
                + conn.getResponseCode() + ", workflow UUID : " + workflowUUID);
      }
    }finally{
      if(conn != null) conn.disconnect();
    }
  }
  
  private String wrapWorkflowRoot(String workflow){
    return "{ \"workflow\": " + workflow + "}";
  }

  public String getWorkflowSimulationLog(String workflowUUID, String simulationUUID, String ibAccessToken) throws IOException{
    return askForWorkflowSimulationLog(workflowUUID, simulationUUID, "output", ibAccessToken);
  }
  
  public String getWorkflowSimulationErrorLog(String workflowUUID, String simulationUUID, String ibAccessToken) throws IOException{
    return askForWorkflowSimulationLog(workflowUUID, simulationUUID, "error", ibAccessToken);
  }
  
  private String askForWorkflowSimulationLog(String workflowUUID, String simulationUUID, String logType, String ibAccessToken) throws IOException{
    HttpURLConnection conn = null;
    try{
      URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/simulation/"+ simulationUUID +"/log/" + logType);
      conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "text/plain");
      conn.setRequestProperty("Authorization", "Basic " + ibAccessToken);
      if(conn.getResponseCode() == HttpStatus.OK.value()){
        String  output = "";    
        StringBuffer responseBuffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        while ((output = br.readLine()) != null) {
          if(!GetterUtil.getString(output).equals("null")){
            responseBuffer.append(CustomUtil.strNull(output)+"<br/>");
          }
        }
        return responseBuffer.toString();
      }else{
        throw new RuntimeException(
            "Failed WorkflowEngineService [ askForWorkflowSimulationLog " + logType + " ] : HTTP error code : "
                + conn.getResponseCode() + ", workflow UUID : " + workflowUUID+ ", simulation UUID : " + simulationUUID);
      }
    }finally{
      if(conn != null) conn.disconnect();
    }
  }
  
  public String getWorkflowJobIntermediateResult(String jobUuid, String ibAccessToken) throws IOException{
    return askForWorkflowJobIntermediateResult(jobUuid, ibAccessToken);
  }
  
  private String askForWorkflowJobIntermediateResult(String jobUuid, String ibAccessToken) throws IOException{
    HttpURLConnection conn = null;
    try{
      URL url = new URL(WORKFLOW_ENGINE_URL_PRIVATE+"/job/" + jobUuid + "/log/error");
      conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "text/plain");
      conn.setRequestProperty("Authorization", "Basic " + ibAccessToken);
      if(conn.getResponseCode() == HttpStatus.OK.value()){
        String  output = "";    
        StringBuffer responseBuffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        while ((output = br.readLine()) != null) {
          if(!GetterUtil.getString(output).equals("null")){
            responseBuffer.append(CustomUtil.strNull(output)+"<br/>");
          }
        }
        return responseBuffer.toString();
      }else{
        throw new RuntimeException(
            "Failed WorkflowEngineService [ askForWorkflowJobIntermediateResult ] : HTTP error code : "
                + conn.getResponseCode() + ", job UUID : " + jobUuid);
      }
    }finally{
      if(conn != null) conn.disconnect();
    }
  }
  
  private Map<String, MWorkflow> createLoopWorkflow(
      Workflow workflow, 
      Map<String, Object> workflowParams,
      HttpServletRequest request) throws SystemException, PortalException, IOException{
    long companyId = PortalUtil.getCompanyId(request);
    Locale locale = PortalUtil.getLocale(request);
    String exec_path = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
    JSONObject screenLogic = JSONFactoryUtil.createJSONObject(workflow.getScreenLogic());
    JSONArray simulationJsonArray = screenLogic.getJSONArray("elements");
    User user = PortalUtil.getUser(request);
    String icebreakerVcToken = GetterUtil.getString(workflowParams.get("icebreakerVcToken"));
    MWorkflow loopWorkflow = makeMWorkflow(workflowParams, user, icebreakerVcToken);
    MWorkflow loopTargetWorkflow = makeMWorkflow(workflowParams, user, icebreakerVcToken);
    
    // loop workflow
    String loopStartElementId = GetterUtil.getString(screenLogic.getString("loopStartElementId"));
    Map<String, MWorkflow> loopWorkflowPair = new HashMap<String, MWorkflow>(); 
    JSONArray loopControllerSimulation = JSONFactoryUtil.createJSONArray();
    JSONArray loopTargetSimulations = JSONFactoryUtil.createJSONArray();
    for(int i=0; i<simulationJsonArray.length(); i++){
      JSONObject simulationLoopJson = simulationJsonArray.getJSONObject(i);
      if(simulationLoopJson.getString("id").equals(loopStartElementId)){
        loopControllerSimulation.put(simulationLoopJson);
      }else{
        loopTargetSimulations.put(simulationLoopJson);
      }
    }
    if(loopTargetSimulations.length() > 0){
      loopWorkflow.setSimulations(getSimulations(user, exec_path, locale, loopTargetSimulations, icebreakerVcToken));
      loopWorkflow.setRefUuid("-1");
      loopWorkflowPair.put("loopTargetWorkflow", loopWorkflow);
    }
    if(loopControllerSimulation.length() > 0){
      loopTargetWorkflow.setSimulations(getSimulations(user, exec_path, locale, loopControllerSimulation, icebreakerVcToken));
      loopWorkflowPair.put("loopWorkflow", loopTargetWorkflow);
    }
    return loopWorkflowPair;
  }

  private MWorkflow makeMWorkflow(Map<String, Object> workflowParams, User user,
      String icebreakerVcToken){
    MWorkflow workflow = new MWorkflow();
    workflow.setTitle(GetterUtil.getString(workflowParams.get("workflowInstanceTitle")));
    workflow.setAccessToken("Basic " + icebreakerVcToken);
    workflow.setUserId(user.getScreenName());
    return workflow;
  }
  
  private JsonNode createWorkflow(
      Workflow workflow, 
      Map<String, Object> workflowParams,
      HttpServletRequest request) throws SystemException, PortalException, IOException{
    long companyId = PortalUtil.getCompanyId(request);
    Locale locale = PortalUtil.getLocale(request);
    String exec_path = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
    JSONObject screenLogic = JSONFactoryUtil.createJSONObject(workflow.getScreenLogic());
    JSONArray simulationJsonArray = screenLogic.getJSONArray("elements");
    User user = PortalUtil.getUser(request);
    String icebreakerVcToken = GetterUtil.getString(workflowParams.get("icebreakerVcToken"));
    MWorkflow mWorkflow = makeMWorkflow(workflowParams, user, icebreakerVcToken);
  	mWorkflow.setSimulations(getSimulations(user, exec_path, locale, simulationJsonArray, icebreakerVcToken));
  	return Transformer.pojo2Json(mWorkflow);
  }

  private List<MWorkflow.Simulations.Simulation> getSimulations(User user, String exec_path, Locale locale, JSONArray simulationJsonArray, String icebreakerVcToken)
      throws PortalException, SystemException, IOException, JSONException{
    MWorkflow.Simulations simulations = new MWorkflow.Simulations();
    for(int i=0; i<simulationJsonArray.length(); i++){
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
      }else{
        scienceApp = ScienceAppLocalServiceUtil.getScienceApp(data.getLong("scienceAppId"));
        scienceApp.setTitle(scienceApp.getTitle(locale));
      }
      simulation.setSolverId(String.valueOf(scienceApp.getScienceAppId()));
      simulation.setTitle(scienceApp.getName());
      simulation.setDescription(scienceApp.getTitle());
      simulation.setClientId(clientId);
      simulation.getJobs().add(getJob(user, exec_path, data, scienceApp, icebreakerVcToken));
      if(data.has("parentNodes")){
        JSONArray parentsJson = data.getJSONArray("parentNodes");
        List<String> parents = new ArrayList<String>();
        for(int j=0; j<parentsJson.length(); j++){
          parents.add(parentsJson.getString(j));
        }
        if(parents.size() > 0){
          simulation.setParentNodes(parents);
        }
      }
      if(data.has("childNodes")){
        JSONArray childrenJson = data.getJSONArray("childNodes");
        List<String> children = new ArrayList<String>();
        for(int j=0; j<childrenJson.length(); j++){
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

  private MWorkflow.Simulations.Simulation.Jobs.Job getJob(User user, String exec_path, JSONObject data, ScienceApp scienceApp, String icebreakerVcToken)
      throws PortalException, SystemException, IOException, JSONException{
    MWorkflow.Simulations.Simulation.Jobs.Job job = new MWorkflow.Simulations.Simulation.Jobs.Job();
    JSONObject inputports = data.getJSONObject("inputports");
    Iterator<String> inputPortNames = inputports.keys();
    long appGroupId = scienceApp.getGroupId();
    job.setTitle(scienceApp.getName());
    job.setDescription(scienceApp.getTitle());
    
    if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
      job.setExecutable(exec_path + "DCExcution/1.0.0/bin/DCExcution.sh");
      job.setType(ScienceAppConstants.APP_RUNTYPE_SEQUENTIAL.toUpperCase());
    }else{
      job.setExecutable(exec_path + ScienceAppLocalServiceUtil.getScienceAppBinPath(scienceApp.getScienceAppId()));
      job.setType(scienceApp.getRunType().toUpperCase());
    }
    job.getDependencies().add(new MWorkflow.Simulations.Simulation.Jobs.Job.Dependency("dummyKey", "dummyValue"));
    if(ScienceAppConstants.APP_RUNTYPE_PARALLEL.equals(scienceApp.getRunType())){
      String cpuValue = data.has("cpuNumber") ? data.getString("cpuNumber") : data.getString("defaultCpus");
      String category = data.getString("parallelModule");
      MWorkflow.Simulations.Simulation.Jobs.Job.Attribute item 
        = new MWorkflow.Simulations.Simulation.Jobs.Job.Attribute("np", cpuValue);
      job.setCategory(category != null ? category.toUpperCase() : category);
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
      log.error(
          "Failed WorkflowEngineService [ getJob ] : PermissionChecker Error");
      throw new SystemException(e);
    }
    while(inputPortNames.hasNext()){
      String inputPortName = inputPortNames.next();
      JSONObject inputport = inputports.getJSONObject(inputPortName);
      MWorkflow.Simulations.Simulation.Jobs.Job.File item = new MWorkflow.Simulations.Simulation.Jobs.Job.File();
      // if parent node exist
      if(inputport.has("hasParent") && inputport.getBoolean("hasParent")){
        if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
          executions.add("$" + inputPortName);
          item.setKey(inputPortName);
        }else{
          executions.add(inputPortName + " $cmd" + inputPortName);
          item.setKey("cmd" + inputPortName);
        }
        item.setExpectedSource(inputport.getString("expectedSource"));
        item.setExpectedValue(inputport.getString("expectedValue"));
        files.add(item);
        continue;
      }
      String editorType = inputport.getString("editorType");
      if(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK.equals(editorType)){
        String fileId = uploadFileToIcebreaker(appGroupId, icebreakerVcToken, inputport.getString("input-value"));
        if(fileId != null){
          if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
            executions.add("$inputdeck");
          }else{
            executions.add(inputPortName + " $inputdeck");
          }
          item.setKey("inputdeck");
          item.setValue(fileId);
          files.add(item);
        }
      }else if(ScienceAppConstants.EDITOR_TYPE_FILE.equals(editorType)){ // text editor 추가
        String parentPath = inputport.getString("parentPath");
        String fileName = inputport.getString("fileName");
        Path uploadFilePath = Paths.get(WORKFLOW_INSTANCE_PATH, parentPath, fileName);
        String fileId = uploadFileToIcebreaker(appGroupId, icebreakerVcToken, uploadFilePath.toFile());
        if(fileId != null){
          if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
            executions.add("$" + inputPortName);
            item.setKey(inputPortName);
          }else{
            executions.add(inputPortName + " $cmd" + inputPortName);
            item.setKey("cmd" + inputPortName);
          }
          item.setValue(fileId);
          files.add(item);
        }
      }else if(ScienceAppConstants.EDITOR_TYPE_STRING.equals(editorType)){
        executions.add(inputPortName + " " + inputport.getString("input-value"));
      }else{
        String fileId = uploadFileToIcebreaker(appGroupId, icebreakerVcToken, inputport.getString("input-value"));
        if(fileId != null){
          if(ObjectUtils.nullSafeEquals(DYNAMIC_CONVERTER, scienceApp.getAppType())){
            executions.add("$" + inputPortName);
            item.setKey(inputPortName);
          }else{
            executions.add(inputPortName + " $cmd" + inputPortName);
            item.setKey("cmd" + inputPortName);
          }
          item.setValue(fileId);
          files.add(item);
        }
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
      log.error(
          "Failed WorkflowEngineService [ downloadFileApi ] : PermissionChecker Error");
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
      String fileApiURL = (String) appGroup.getExpandoBridge()
          .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
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
        }catch (IOException e){}
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
      }catch(Exception e){
        log.error("Failed WorkflowEngineService [ downloadFileApi ] : downloadFromDLFileEntry Error");
        throw new SystemException(e);
      }finally {
        if(inputStream != null){
          try{
            inputStream.close();
          }catch (IOException e){}
        }
      }
    }
    return downloadedFile;
  }
  
  @SuppressWarnings("rawtypes")
  public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, File uploadFile)
      throws PortalException, SystemException, MalformedURLException, IOException{
    Group appGroup = GroupLocalServiceUtil.getGroup(appGroupId);
    String fileApiURL = (String) appGroup.getExpandoBridge()
        .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
    String fileId = null;
    try{
      log.info("uploadFileToIcebreaker - upload target File : " + uploadFile.getAbsolutePath());
      Map returnFileMap = SimulationLocalServiceUtil.uploadFile(ICEBREAKER_UPLOAD_CLUSTER, fileApiURL,
          icebreakerVcToken, uploadFile);
      fileId = CustomUtil.strNull(returnFileMap.get("fileId"));
    }catch (InterruptedException e){
      throw new SystemException(e);
    }
    return fileId;
  }
  
  public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, InputStream fileContent) throws IOException, SystemException, PortalException{
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
    }catch(Exception e){
      log.error("Failed WorkflowEngineService [ downloadFileApi ] : downloadFromDLFileEntry Error");
      throw new SystemException(e);
    }finally {
      if(fileContent != null){
        try{
          fileContent.close();
        }catch (IOException e){}
      }
    }
    return uploadFileToIcebreaker(appGroupId, icebreakerVcToken, file);
  }
  
  public String uploadFileToIcebreaker(long appGroupId, String icebreakerVcToken, String fileContent) throws IOException, SystemException, PortalException{
    return uploadFileToIcebreaker(appGroupId, icebreakerVcToken, new ByteArrayInputStream(fileContent.getBytes()));
  }
  
  @SuppressWarnings("unchecked")
  public List<Workflow> getWorkflowsByLikeSearch(Map<String, Object> searchParam) throws SystemException{
    int begin = GetterUtil.getInteger(searchParam.get("begin"), -1);
    int end = GetterUtil.getInteger(searchParam.get("end"), -1);
    DynamicQuery query = makeWorkflowListWhereClause(searchParam);
    query.addOrder(OrderFactoryUtil.desc("createDate"));
    return (List<Workflow>) super.workflowLocalService.dynamicQuery(query, begin, end); 
  }

  public long getCountWorkflowsByLikeSearch(Map<String, Object> serachParam) throws SystemException{
    DynamicQuery query = makeWorkflowListWhereClause(serachParam);
    return workflowLocalService.dynamicQueryCount(query);
  }
  
  private DynamicQuery makeWorkflowListWhereClause(Map<String, Object> serachParam){
    DynamicQuery query = DynamicQueryFactoryUtil.forClass(Workflow.class);
    Iterator<String> keys = serachParam.keySet().iterator();
    while(keys.hasNext()){
      String key = keys.next();
      if("begin".equals(key) || "end".equals(key) ){
        continue;
      }
      if("targetLanguage".equals(key)){
        query.add(RestrictionsFactoryUtil.like(key, "%" + serachParam.get(key) + "%"));
      }
      if("title".equals(key)){
        query.add(RestrictionsFactoryUtil.like(key, "%" + serachParam.get(key) + "%"));
      }
      if("userId".equals(key)){
        query.add(RestrictionsFactoryUtil.eq(key, serachParam.get(key)));
      }
      if("isPublic".equals(key)){
        query.add(RestrictionsFactoryUtil.eq(key, serachParam.get(key)));
      }
    }
    return query;
  }
  
  public long getCountWorkflowInstanceByUserId(
      User user, 
      Map<String, Object> params) throws SystemException{
    long groupId = GetterUtil.getLong(params.get("groupId"));
    if(!EdisonUserUtil.isPowerUserThan(user) 
        && !EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
      params.put("userId", user.getUserId());
    }
    return workflowFinder.countWorkflowInstances(params);
  }
  
  public List<Map<String, Object>> getWorkflowInstanceByUserId(
      User user, 
      Map<String, Object> params,
      Locale locale
      ) throws SystemException{
    long groupId = GetterUtil.getLong(params.get("groupId"));
    if(!EdisonUserUtil.isPowerUserThan(user) 
        && !EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
      params.put("userId", user.getUserId());
    }
    return workflowFinder.retrieveWorkflowInstances(params, locale);
  }
  
  public List<Map<String, Object>> getDataTypeEditors(DataType dataType) throws SystemException, PortalException{
    List<Map<String, Object>> editorIds = DataTypeEditorLocalServiceUtil
        .retrieveDataTypeEditorList(dataType.getTypeId());
    List<Map<String, Object>> editors = Lists.newArrayList();
    for(Map<String, Object> editorIdMap :  editorIds){
      long editorId = GetterUtil.getLong(editorIdMap.get("editorId"), 0);
      ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(editorId);
      Map<String, Object> scienceAppMap = scienceApp.getModelAttributes();
      scienceAppMap.put("isDefault", editorIdMap.get("isDefault"));
      if(ObjectUtils.nullSafeEquals(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK,
          scienceApp.getEditorType())){
        DataTypeStructure structure  = DataTypeStructureLocalServiceUtil.getDataTypeStructure(dataType.getTypeId());
        scienceAppMap.put("structure", structure.getStructure());
      }
      editors.add(scienceAppMap);
    }
    return editors;
  }
  
  public Map<String, Object> getDataTypeDefaultEditor(DataType dataType)
      throws SystemException, PortalException{
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
  
  public Map<String, Object> getDataTypeDefaultAnalyzer(DataType dataType)
      throws SystemException, PortalException{
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
  
  public List<Map<String, Object>> getDataTypeAnalyzers(DataType dataType)
      throws SystemException, PortalException{
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
  
  public List<AssetCategory> getSiteAssetCategoriesByParentId(
      long companyGroupId, long groupId, final long parentCategoryId)
      throws PortalException, SystemException{
    final AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil
        .getGroupVocabulary(companyGroupId, EdisonAssetCategory.GLOBAL_DOMAIN);
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
  
  private List<AssetCategory> getSiteAssetCategories(
      long companyGroupId, long groupId, Predicate<AssetCategory> predication)
      throws PortalException, SystemException{
    AssetEntry aEntry = AssetEntryLocalServiceUtil
        .fetchEntry(Group.class.getName(), groupId); // 사이트의 entry 값
    List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil
        .getAssetEntryAssetCategories(aEntry.getEntryId()); // 사이트에서 사용중인 category
    return Lists.newArrayList(Iterables.filter(aCategoryList, predication));
  }

  public List<AssetCategory> getLv1Categories(long companyGroupId, long groupId, Locale locale)
      throws PortalException, SystemException{
    List<AssetCategory> lv1Categories = getRootSiteAssetCategries(companyGroupId, groupId);
    Collections.sort(lv1Categories, new Ordering<AssetCategory>(){
      @Override
      public int compare(AssetCategory left, AssetCategory right){
        return Longs.compare(left.getCategoryId(), right.getCategoryId());
      }});
    return lv1Categories;
  }
}