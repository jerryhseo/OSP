/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package org.kisti.edison.bestsimulation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException;
import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.base.SimulationJobLocalServiceBaseImpl;
import org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK;
import org.kisti.edison.bestsimulation.service.persistence.SimulationPK;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.MonitoringStatusConstatns;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * The implementation of the simulation job local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.SimulationJobLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author mhkang
 * @see org.kisti.edison.bestsimulation.service.base.SimulationJobLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil
 */
public class SimulationJobLocalServiceImpl
	extends SimulationJobLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil} to access the simulation job local service.
	 */
	private static Log log = LogFactoryUtil.getLog(SimulationJobLocalServiceImpl.class);
	
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	@SuppressWarnings("unchecked")
  public SimulationJob getSimulationJobWithJobUuid(String jobUuid) throws NoSuchSimulationJobException,SystemException{
	  DynamicQuery simulationJobQuery = DynamicQueryFactoryUtil.forClass(
        SimulationJob.class, "simJob", PortletClassLoaderUtil.getClassLoader());
	  simulationJobQuery.add(PropertyFactoryUtil.forName("simJob.jobUuid").eq(jobUuid));
	  simulationJobQuery.addOrder(OrderFactoryUtil.desc("simJob.jobSubmitDt"));
	  List<SimulationJob> jobs = simulationJobLocalService.dynamicQuery(simulationJobQuery, 0, 1);
	  if(jobs != null && !jobs.isEmpty()){
	    return jobs.get(0);
	  }else{
	    throw new NoSuchSimulationJobException("There are no SimulationJobs with jobUuid " + jobUuid);
	  }
	}

  @SuppressWarnings("unchecked")
  public List<SimulationJob> getJobsWithSimulationUuid(
      String simulationUuid, int start, int size) throws SystemException{
    DynamicQuery query = makeJobsQuery(simulationUuid);
    query.addOrder(OrderFactoryUtil.desc("simJob.primaryKey.jobSeqNo"));
    return simulationJobLocalService.dynamicQuery(query, start, start + size);
  }

  public long getJobsCountWithSimulationUuid(String simulationUuid) throws SystemException{
    DynamicQuery query = makeJobsQuery(simulationUuid);
    return simulationJobLocalService.dynamicQueryCount(query);
  }

  private DynamicQuery makeJobsQuery(String simulationUuid){
    DynamicQuery simulationJobQuery = DynamicQueryFactoryUtil.forClass(
        SimulationJob.class, "simJob", PortletClassLoaderUtil.getClassLoader());
    simulationJobQuery.add(PropertyFactoryUtil.forName("simJob.primaryKey.simulationUuid").eq(simulationUuid));
    return simulationJobQuery;
  }
  
	public SimulationJob createSimulationJob(String simulationUuid,long groupId,String jobTitle) throws SystemException{
		SimulationJobPK simulationJobPK = new SimulationJobPK();
		simulationJobPK.setJobSeqNo(getMaxJobSeqNoSimulationJob(simulationUuid));
		simulationJobPK.setSimulationUuid(simulationUuid);
		simulationJobPK.setGroupId(groupId);
		
		
		SimulationJob simulationJob = simulationJobPersistence.create(simulationJobPK);
		simulationJob.setJobUuid(UUID.randomUUID().toString());
		simulationJob.setJobTitle(jobTitle);
		simulationJob.setJobSubmit(false);
		simulationJob.setSimulationUuid(simulationUuid);
		return simulationJobPersistence.update(simulationJob);
	}
	
	
	public SimulationJob createSimulationJobWithJobData(String simulationUuid,long groupId,String jobTitle,String jobData) throws SystemException{
		SimulationJobPK simulationJobPK = new SimulationJobPK();
		simulationJobPK.setJobSeqNo(getMaxJobSeqNoSimulationJob(simulationUuid));
		simulationJobPK.setSimulationUuid(simulationUuid);
		simulationJobPK.setGroupId(groupId);
		
		SimulationJob simulationJob = simulationJobPersistence.create(simulationJobPK);
		String jobUuid = UUID.randomUUID().toString();
		simulationJob.setJobUuid(jobUuid);
		simulationJob.setJobTitle(jobTitle);
		simulationJob.setJobSubmit(false);
		
		SimulationJobDataLocalServiceUtil.modifySimulationJobData(jobUuid,jobData);
		
		return simulationJobPersistence.update(simulationJob);
	}
	
	public void deleteSimulationJob(String simulationUuid,long jobSeqNo, long groupId) throws NoSuchSimulationJobException, SystemException{
		SimulationJobPK simulationJobPK = new SimulationJobPK();
		simulationJobPK.setJobSeqNo(jobSeqNo);
		simulationJobPK.setSimulationUuid(simulationUuid);
		simulationJobPK.setGroupId(groupId);
		simulationJobPersistence.remove(simulationJobPK);
	}
	
	public void deleteSimulationJobByJobUuid(String simulationUuid, String jobUuid) throws SystemException{
		simulationJobPersistence.removeByjobUuid(simulationUuid, jobUuid);
	}
	
	
	public Map<String,Object> submitSimulationJob(String simulationUuid, String jobUuid,String syncCallBackURL) throws SystemException, PortalException{
		SimulationJob simulationJob = getSimulationJobWithJobUuid(jobUuid);
		Simulation simulation = super.simulationPersistence.findBySimulationUuid(simulationUuid);
		User user = UserLocalServiceUtil.getUser(simulation.getUserId());
		Group group = GroupLocalServiceUtil.getGroup(simulation.getGroupId());
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(GetterUtil.getLong(simulation.getScienceAppId()));
		SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
		
		IcebreakerVcToken tokenEntity;
		try {
			tokenEntity = SimulationLocalServiceUtil.getOrCreateToken(group.getGroupId(), user);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		
		String cluster = simulation.getCluster();
		String icebreakerUrl = GetterUtil.getString(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL),"");
		String icebreakerZone = GetterUtil.getString(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ZONE));
		String vcToken = tokenEntity.getVcToken();
		String exec_path = PrefsPropsUtil.getString(group.getCompanyId(), EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + ScienceAppLocalServiceUtil.getScienceAppBinPath(scienceApp.getScienceAppId());
		
		if(icebreakerUrl.equals("")||icebreakerZone.equals("")){
			throw new PortalException("CHECK THIS "+group.getName()+"GROUP "+EdisonExpando.SITE_ICEBREAKER_URL+" OR "+EdisonExpando.SITE_ICEBREAKER_ZONE);
		}
		
		
		// ============================= 작업 실행 라이브러리 받아오기 =================
		StringBuffer code_library = new StringBuffer();	// 실행 라이브러리
		code_library.append("<dependencies>");
		code_library.append("</dependencies>");
		//================================= 작업 실행 라이브러리 받아오기 끝 =================
		
		//디렉토리 생성 
		File icebreakerTempFolder = new File(ICEBREAKER_TEMP_PATH);
		//해당 디렉토리의 존재여부를 확인
		if(!icebreakerTempFolder.exists()){
			icebreakerTempFolder.mkdirs();//없다면 생성
		}
		
		StringBuffer fileItemsStr = new StringBuffer(); // 파일 데이터
		Map<String,String> executionMap = new HashMap<String,String>(); // 실행 데이터맵
		fileItemsStr.append("	<files>");
		
		String executionStr = "";
		
		// 명령어 정렬
		String executionStrOrder = "";
		boolean isInputDeck = false;
		String inputDeckName = "";
		String codeMpiNumber = "1";
		if(!GetterUtil.getString(simulationJobData.getJobData(),"").equals("")) {
			JSONObject jobDataJson = JSONObject.fromObject(simulationJobData.getJobData());
			Set<String> set = jobDataJson.keySet();
			for (String portNameStr : set) {
				if(portNameStr.equals("code_mpi_number")){
					codeMpiNumber = GetterUtil.getString(jobDataJson.getString("code_mpi_number"));
				}else{
					//================================= 포트 가져오기 start =============================
					JSONObject parameterData = jobDataJson.getJSONObject(portNameStr);
					
					String editorType = GetterUtil.getString(parameterData.getString("editor_type"),"");
					String portData = GetterUtil.getString(parameterData.getString("port_data"),"");
					
					if(editorType.equals(ScienceAppConstants.EDITOR_TYPE_STRING)) {
						executionMap.put(StringUtil.trim(portNameStr), portNameStr + " " + portData + " ");
					}else if(editorType.equals(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK)){
						isInputDeck = true;
						if(!inputDeckName.equals("")){
							inputDeckName+="/"+portNameStr;
						}else{
							inputDeckName = portNameStr;
						}
						
						String fileName = portNameStr.replaceAll("-", "")+UUID.randomUUID().toString().substring(0, 7);
						String folderName = "/" + user.getScreenName()+Calendar.getInstance().getTimeInMillis()+"ID";
						
						File inputDeckTempFile;
						try {
							inputDeckTempFile = EdisonFileUtil.createCustomFile(ICEBREAKER_TEMP_PATH + folderName, fileName, portData);
						} catch (Exception e) {
							throw new SystemException(e);
						}
						
						
						log.debug("##############################################################################################################");
						log.debug("ICEBREAKER_TEMP_PATH : "+ICEBREAKER_TEMP_PATH);
						log.debug("fileName : "+fileName);
						log.debug("fileBufferStr : "+portData);
						log.debug("##############################################################################################################");
						
						//api를 통한 InputDeck File UPLOAD
						Map<String, Object> fileUploadMap;
						try {
							fileUploadMap = SimulationLocalServiceUtil.uploadFile(cluster, 
																  icebreakerUrl,
																  vcToken,
																  inputDeckTempFile);
						} catch (Exception  e) {
							throw new SystemException(e);
						}
						
						
						log.debug("############################################RETURN_INPUT_DECK_FILE###########################################");
						log.debug("INPUTDECK_FILE_ID : "+CustomUtil.strNull(fileUploadMap.get("fileId")));
						log.debug("INPUTDECK_FILE_PATH : "+CustomUtil.strNull(fileUploadMap.get("filePath")));
						log.debug("INPUTDECK_FILE_SIZE : "+CustomUtil.strNull(fileUploadMap.get("fileSize")));
						log.debug("##############################################################################################################");
						
						if(inputDeckTempFile.exists()){
							inputDeckTempFile.delete();//Temp File Delete
						}
						
						if(!CustomUtil.strNull(fileUploadMap.get("fileId")).equals("")){
							executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $inputdeck ");
							fileItemsStr.append("		<item key=\"inputdeck\" value=\""+CustomUtil.strNull(fileUploadMap.get("fileId"))+"\"/>");
						}
						
					}else if(editorType.equals(ScienceAppConstants.EDITOR_TYPE_FILE)||editorType.equals(ScienceAppConstants.EDITOR_TYPE_TEXT)){
						String fileType = GetterUtil.getString(parameterData.getString("file_Type"),"");
						InputStream inputStream = null;
						String fileName = "";
						if(fileType.toUpperCase().equals("SAMPLE")){
							long fileEntryId = GetterUtil.getLong(parameterData.getString("file_id"),0);
							DLFileEntry dlFileEntry = DLFileEntryServiceUtil.getFileEntry(fileEntryId);
							inputStream = dlFileEntry.getContentStream();
							fileName = dlFileEntry.getTitle();
						}else if(fileType.toUpperCase().equals("FILE")){
							String filePath = GetterUtil.getString(parameterData.getString("file_path"),"");
							File file = new File(filePath);
							fileName = file.getName();
							
							try {
								inputStream = new FileInputStream(file);
							} catch (FileNotFoundException e) {
								throw new SystemException(e);
							}
						}else{
							throw new PortalException("NO FILE TYPE");
						}
						//유니크한 파일 명으로 변경
						File file = new File(ICEBREAKER_TEMP_PATH+File.separator+UUID.randomUUID().toString().substring(0, 7)+"_"+fileName);
						OutputStream outStream = null;
						try {
							outStream = new FileOutputStream(file);
							// 읽어들일 버퍼크기를 메모리에 생성
							byte[] buf = new byte[1024];
							int len = 0;
							// 끝까지 읽어들이면서 File 객체에 내용들을 쓴다
							while ((len = inputStream.read(buf)) > 0){
								outStream.write(buf, 0, len);
							}
							
							// Stream 객체를 모두 닫는다.
							outStream.close();
							inputStream.close();
							
							//icebreaker fileupload
							Map<String,Object> returnFileMap = SimulationLocalServiceUtil.uploadFile(cluster,icebreakerUrl, vcToken, file);

							String fileId = CustomUtil.strNull(returnFileMap.get("fileId"));

							executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $cmd"+ portNameStr +" ");
							fileItemsStr.append("	<item key=\"cmd"+portNameStr+"\" value=\""+fileId+"\"/>");
							
						}catch (FileNotFoundException e) {
							throw new SystemException(e);
						}catch (IOException e) {
							throw new PortalException(e);
						}catch (InterruptedException e) {
							throw new SystemException(e);
						}finally{
							if(file.exists()){file.delete();}
						}
					}else{
						executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $cmd"+ portNameStr +" ");
						fileItemsStr.append("	<item key=\"cmd"+portNameStr+"\" value=\""+portData+"\"/>");
					}
					executionStrOrder += executionMap.get(StringUtil.trim(portNameStr));
				}
			}
		}
		
		fileItemsStr.append("	</files>");
		//================================= 포트 가져오기 끝 =============================
		
		//================================= 실행 정보 받아오기 (MPI)===============================
		StringBuffer execution_mpi_attributes = new StringBuffer();
		execution_mpi_attributes.append(" <attributes>");
		execution_mpi_attributes.append(" 		<item key=\"np\" value=\""+codeMpiNumber+"\"/> ");
		execution_mpi_attributes.append(" </attributes>");
		
		//================================= 실행 정보 받아오기 끝 =================================
		
		Map<String,Object> jobExeCuteMap = new HashMap<String,Object>();
		jobExeCuteMap.put("code_library", code_library.toString() );
		jobExeCuteMap.put("filesItem", fileItemsStr.toString() );
		jobExeCuteMap.put("executionStr", executionStrOrder);
		jobExeCuteMap.put("executionType", scienceApp.getRunType());
		jobExeCuteMap.put("code_mpi_module", scienceApp.getParallelModule());
		jobExeCuteMap.put("execution_mpi_attributes",execution_mpi_attributes.toString() );
		jobExeCuteMap.put("simulationUuid", simulationUuid);
		jobExeCuteMap.put("scienceAppId", scienceApp.getScienceAppId());
		jobExeCuteMap.put("scienceAppName", scienceApp.getName());
		jobExeCuteMap.put("cyberLabId", " ");
		jobExeCuteMap.put("classId", " ");
		jobExeCuteMap.put("title", simulationJob.getJobTitle());
		jobExeCuteMap.put("description", "");
		jobExeCuteMap.put("exec_path", exec_path);
		jobExeCuteMap.put("cluster", cluster);
		jobExeCuteMap.put("Token", vcToken);
		jobExeCuteMap.put("syncCallBackURL", syncCallBackURL);
		
		try {
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map resultJobMap = SimulationLocalServiceUtil.executeJob(icebreakerUrl, jobExeCuteMap);
			
			if(resultJobMap==null){
				throw new SystemException("IB_ERROR");
			}
			
			String resultJobUuid = CustomUtil.strNull(resultJobMap.get("uuid"));
			Date submittedTime = transFormat.parse(CustomUtil.strNull(resultJobMap.get("submittedTime")));
			
			simulationJob.setJobStatus(1701005);
			simulationJob.setJobExecPath(exec_path);
			simulationJob.setJobUniversityField(GetterUtil.get(user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY), 0L));
			simulationJob.setJobPhase(1301003);
			simulationJob.setJobInputDeckYn(isInputDeck);
			simulationJob.setJobInputDeckName(inputDeckName);
			simulationJob.setJobUuid(resultJobUuid);
			simulationJob.setJobSubmitDt(submittedTime);
			simulationJob.setJobSubmit(true);
			
			Map<String,Object> updateSimulationJobMap = SimulationJobLocalServiceUtil.updateSimulationJob(simulationJob).getModelAttributes();
			
			simulationJobData.setJobUuid(resultJobUuid);
			SimulationJobDataLocalServiceUtil.updateSimulationJobData(simulationJobData);
			
			return updateSimulationJobMap;
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	
	
	public long getMaxJobSeqNoSimulationJob(String simulationUuid){
		
		long maxJobSeqNo = 0;			
		
		maxJobSeqNo = simulationJobFinder.getMaxJobSeqNoSimulationJob(simulationUuid);
		
		return maxJobSeqNo;
	}	
	
	
	public int deleteSimulationJob(long groupId, String simulationUuid, long jobPhase, long jobSeqNo){		
		return simulationJobFinder.deleteSimulationJob(groupId, simulationUuid, jobPhase, jobSeqNo);
	}	
	


	public int deleteSimulationParameter(long groupId, String simulationUuid, long jobSeqNo){		
		return simulationJobFinder.deleteSimulationParameter(groupId, simulationUuid, jobSeqNo);
	}
	
	
	public int deleteSimulationCommandOption(long groupId, String simulationUuid, long optionSeq){		
		return simulationJobFinder.deleteSimulationCommandOption(groupId, simulationUuid, optionSeq);
	}
	
	
	
	public List getListSimulationJob(long groupId, String simulationUuid, long jobPhase, long jobSeqNo, Locale locale){
				
		List<SimulationJob> listObject = simulationJobFinder.getListSimulationJob(groupId, simulationUuid, jobPhase, jobSeqNo);
				
		List resultList = new ArrayList();
		SimulationJob simulationJob = null;
		Map result = null;
		for (int i = 0; i < listObject.size(); i++) {
			
			simulationJob = listObject.get(i);
			
			if(simulationJob != null) {				
				result = new HashMap<String, Object>();				
				result.put("jobSeqNo", simulationJob.getJobSeqNo());
				result.put("simulationUuid", simulationJob.getSimulationUuid());
				result.put("groupId", simulationJob.getGroupId());
				result.put("jobUuid", simulationJob.getJobUuid());
				result.put("jobStatus", simulationJob.getJobStatus());
				result.put("jobStatusNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(simulationJob.getJobStatus()), EdisonExpando.CDNM + "_" + locale.toString()));
				result.put("jobStartDt", simulationJob.getJobStartDt());
				result.put("jobEndDt", simulationJob.getJobEndDt());
				result.put("jobTitle", simulationJob.getJobTitle());
				result.put("jobExecPath", simulationJob.getJobExecPath());
				result.put("jobPhase", simulationJob.getJobPhase());
				result.put("jobPhaseNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(simulationJob.getJobPhase()), EdisonExpando.CDNM + "_" + locale.toString()));				
				result.put("jobSubmitDt", simulationJob.getJobSubmitDt());
				result.put("jobUniversityField", simulationJob.getJobUniversityField());
				result.put("jobInputDeckYn", simulationJob.getJobInputDeckYn());
				result.put("jobInputDeckName", simulationJob.getJobInputDeckName());
				result.put("jobSubmit", simulationJob.getJobSubmit());
				
				
				resultList.add(result);
			}//if(simulationJob != null) {
			
		}//for
		
		return resultList;
	}	
	
	//화면 기본 조회
	public List<Map<String, Object>> getMonitoringList(long groupId, long userId, String searchValue, long jobStatus, long classId, long customId, int begin, int end) throws SystemException, PortalException, ParseException{
		return getMonitoringList(groupId, userId, searchValue, jobStatus, "", 0, classId, customId, begin, end);
	}
	
	//모니터링 하위 JOB 조회
	public List<Map<String, Object>> getMonitoringJobList(long groupId,String simulationUuid) throws SystemException, PortalException, ParseException{
		return getMonitoringList(groupId, 0,"", 0, simulationUuid, 0, 0, 0, 0, 0);
	}

	
	public Map<String,Object> getMonitoringJob(long groupId,String simulationUuid,long jobSeqNo) throws SystemException, PortalException, ParseException{
		List<Map<String, Object>> resultList = getMonitoringList(groupId, 0,"", 0, simulationUuid, jobSeqNo, 0, 0, 0, 0);
		return resultList.get(0);
	}
	
	protected List<Map<String, Object>> getMonitoringList(long groupId, long userId, String searchValue, long jobStatus, 
			String simulationUuid,long jobSeqNo, long classId, long customId, int begin, int end) throws SystemException, PortalException, ParseException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		//simulationUuid가 있을 경우에는 simulationUuid를 기준으로 simulationJob을 조회 한다.
		List<Object[]> monitoringList = null;
		if(!simulationUuid.equals("")){
			monitoringList = simulationJobFinder.getMonitoringJobList(groupId, simulationUuid,jobSeqNo);
		}else if(customId > 0){
			monitoringList = simulationJobFinder.getMonitoringList(groupId, userId, searchValue, jobStatus, classId, customId, begin, end);
		}else{
			monitoringList = simulationJobFinder.getMonitoringList(groupId, userId,  searchValue, jobStatus, 0, 0, begin, end);
		}
		
		log.info("monitoringList.size() : " + monitoringList.size());
		log.info("groupId : " + groupId + ", " + "userId : " + userId + ", " + "searchValue : " + searchValue + ", " + "jobStatus : " + jobStatus + ", " + "simulationUuid : " + simulationUuid);
		log.info("jobSeqNo : " + jobSeqNo + ", " + "classId : " + classId + ", " + "customId : " + customId + ", " + "begin : " + begin + ", " + "end : " + end);
		
		Map <String, Object> resultRow = null;
		for (int i = 0; i < monitoringList.size(); i++) {
			Object[] resultArray = monitoringList.get(i);
			if(resultArray != null) {
				Simulation simulation = (Simulation) resultArray[0];
				SimulationJob simulationJob = (SimulationJob) resultArray[1];
				String stayDt = CustomUtil.strNull(resultArray[2]);
				String executeDt = CustomUtil.strNull(resultArray[3]);
				String jobCnt = CustomUtil.strNull(resultArray[4]);
				
				resultRow = new HashMap<String, Object>();
				if (simulation != null) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					resultRow.put("simulationUuid",simulation.getSimulationUuid());
					resultRow.put("groupId",simulation.getGroupId());
					resultRow.put("userId",simulation.getUserId());
					//userNm
					User user = UserLocalServiceUtil.getUser(simulation.getUserId());
					resultRow.put("userNm",user.getScreenName());
					resultRow.put("simulationTitle",simulation.getSimulationTitle());
					resultRow.put("scienceAppId",simulation.getScienceAppId());
					resultRow.put("scienceAppName",simulation.getScienceAppName());
					resultRow.put("simulationCreateDt",simulation.getSimulationCreateDt());
					resultRow.put("cluster",simulation.getCluster());

					resultRow.put("jobSeqNo",simulationJob.getJobSeqNo());
					resultRow.put("jobUuid",simulationJob.getJobUuid());
					resultRow.put("jobStatus",simulationJob.getJobStatus());
					//상태에 대한 이미지 파일 
					String jobStatusImg = EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(simulationJob.getJobStatus()),EdisonExpando.OPTION1);
					resultRow.put("jobStatusImg",jobStatusImg);
					resultRow.put("jobStartDt",simulationJob.getJobStartDt());
					if(CustomUtil.strNull(simulationJob.getJobEndDt()).equals("")){
						resultRow.put("jobEndDt","");
					}else{
						resultRow.put("jobEndDt",simpleDateFormat.format(CustomUtil.StringToDateFormat(CustomUtil.strNull(simulationJob.getJobEndDt()), "yyyy-MM-dd HH:mm:ss")));
					}
					resultRow.put("jobTitle",simulationJob.getJobTitle());
					resultRow.put("jobExecPath",simulationJob.getJobExecPath());
					resultRow.put("jobPhase",simulationJob.getJobPhase());
					resultRow.put("jobSubmitDt",simpleDateFormat.format(CustomUtil.StringToDateFormat(CustomUtil.strNull(simulationJob.getJobSubmitDt()), "yyyy-MM-dd HH:mm:ss")));
					resultRow.put("jobPostProcessorYn","N");
					resultRow.put("jobMiddleFileProcessorYn","N");
					
					
					//후처리기 존재 여부 - 미사용, 2018.03.19
					/*if(scienceAppId > 0L) {
						String outputPort = "";
						
						ScienceAppOutputPorts scienceAppOutputPorts = ScienceAppOutputPortsLocalServiceUtil.fetchScienceAppOutputPorts(scienceAppId);
						if(scienceAppOutputPorts != null) {
							outputPort = scienceAppOutputPorts.getOutputPorts();
						}
						if(outputPort != null && !outputPort.equals("")) {
							JSONObject outputPortJson = JSONObject.fromObject(JSONSerializer.toJSON(outputPort));
							Iterator<String> itr = outputPortJson.keys();
							int analyzerCount = 0;
							int middleFileCount = 0;
							while (itr.hasNext()) {
								String names = itr.next();
								if(!StringUtil.toUpperCase(names).equals("TEMP")) {
									JSONObject jsonPort = outputPortJson.getJSONObject(names);
									String analyzerId = GetterUtil.get(jsonPort.get("defaultAnalyzer_"), names);
									if(analyzerId != null && !analyzerId.equals("")) {
										analyzerCount++;
									}
								} else {
									middleFileCount++;
								}
							}
							
							if(analyzerCount > 0) {
								resultRow.put("jobPostProcessorYn","Y");
							}
							if(middleFileCount > 0) {
								resultRow.put("jobMiddleFileProcessorYn","Y");
							}
						}
					}*/

					//로그포트 존재 여부
					long scienceAppId = GetterUtil.get(simulation.getScienceAppId(),0L);
					if(scienceAppId > 0L) {
						String logPort = "";
						ScienceAppLogPorts scienceAppLogPorts =  ScienceAppLogPortsLocalServiceUtil.fetchScienceAppLogPorts(scienceAppId);
						if(scienceAppLogPorts != null){
							logPort = scienceAppLogPorts.getLogPorts();
							
							if(logPort != null && !logPort.equals("") && !logPort.equals("false")){
							    try{
							        JSONObject logPortJson = JSONObject.fromObject(JSONSerializer.toJSON(logPort));
							        Iterator<String> itr = logPortJson.keys();
							        
							        int analyzerCount = 0;
							        while (itr.hasNext()) {
							            String names = itr.next();
							            JSONObject jsonPort = logPortJson.getJSONObject(names);
							            String analyzerId = GetterUtil.get(jsonPort.get("defaultAnalyzer_"), names);
							            String dataType_ = GetterUtil.get(jsonPort.get("dataType_"), names);
							            
							            JSONObject dataTypeJson = JSONObject.fromObject(JSONSerializer.toJSON(jsonPort.get("dataType_")));
							            String dataTypeName = GetterUtil.get(dataTypeJson.get("name"), names);
							            String dataTypeVersion = GetterUtil.get(dataTypeJson.get("version"), names);
							            
							            DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
							            if(analyzerId != null && !analyzerId.equals("")) {
							                analyzerCount++;
							            }
							        }
							        if(analyzerCount > 0) {
							            resultRow.put("jobLogFileProcessorYn","Y");
							        }
							    }catch(JSONException je){
							        log.error("logport json exception");
							        log.error("logport json \n" + logPort + "\n");
							        log.error("exception", je);
							        resultRow.put("jobLogFileProcessorYn","N");
							    }
							}
						} else {
							resultRow.put("jobLogFileProcessorYn","N");
						}
					}
					
					
/*					if(CustomUtil.strNull(simulationJob.getJobPostProcessor()).equals("")){
						resultRow.put("jobPostProcessorYn","N");
					}else{
						String[] postArr = StringUtil.upperCase(simulationJob.getJobPostProcessor()).split(";");
						if(postArr.length>0){
							for1:for(String postProcessor : postArr){
								if(!postProcessor.equals("DOWNLOAD")){
									resultRow.put("jobPostProcessorYn","Y");
									break for1;
								}
							}
						}else{
							resultRow.put("jobPostProcessorYn","N");
						}
					}
					
					if(CustomUtil.strNull(resultRow.get("jobPostProcessorYn")).equals("")){
						resultRow.put("jobPostProcessorYn","N");
					}*/
					
					resultRow.put("jobUniversityField",simulationJob.getJobUniversityField());
					resultRow.put("jobInputDeckYn",simulationJob.getJobInputDeckYn());
					resultRow.put("jobInputDeckName",simulationJob.getJobInputDeckName());
					
					resultRow.put("stayDt", stayDt);
					resultRow.put("executeDt", executeDt);
					resultRow.put("jobCnt", jobCnt);
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	//모니터링 카운트
	public int getMonitoringCount(long groupId, long userId, String searchValue,long jobStatus, long classId, long customId) throws SystemException{
		return simulationJobFinder.getMonitoringCount(groupId, userId, searchValue, jobStatus, classId, customId);
	}
	
	//가상클래스 삭제시 모니터링 데이터 삭제
	/*public boolean deleteMonitoringByJobClassId(long groupId, long jobClassId, User user) throws SystemException, PortalException{
		//제출 중인 데이터 조회
		boolean returnStatus = true;
		List<SimulationJob> simulationList = simulationJobPersistence.findByjobClassId(groupId, jobClassId, 1301003);
		root1:for(SimulationJob simulationJob:simulationList){
			String result = deleteMonitoring(simulationJob.getSimulationUuid(), groupId, simulationJob.getJobSeqNo(), user);
			if(!result.equals("SUCCESS")){returnStatus=false; break root1;}
		}
		return returnStatus; 
	}*/
	
	
	
	//simulation까지 삭제 할 경우에는 jobSeqNo = 0;
	//DELETE 모니터링
	public String deleteMonitoring(String simulationUuid, long groupId,long jobSeqNo,User user) throws PortalException, SystemException{
		String returnResult = "";
		if(jobSeqNo!=0){
			SimulationJobPK simulationJobPK = new SimulationJobPK(jobSeqNo, simulationUuid, groupId);
			SimulationJob simulationJob = SimulationJobLocalServiceUtil.getSimulationJob(simulationJobPK);
			try {
				if(!CustomUtil.strNull(simulationJob.getJobUuid()).equals("")){
					returnResult = icebreakerCancleJobAndDeleteData(simulationJob, user);
				}else{
					returnResult = "SUCCESS";
				}
				
			} catch (MalformedURLException e) {
				throw new PortalException(e);
			} catch (IOException e) {
				throw new PortalException(e);
			} catch (ParseException e) {
				throw new PortalException(e);
			}
			
			if(returnResult.equals("SUCCESS")){
				//삭제 하고자 하는 데이터의 jobSeqNo가 1일 경우
				//모니터링 목록에서 해당 job의 jobSeqNo가 1을 조회 하기 때문에 
				//해당 simulationUuid에 해당하는 jobSeqNo를 -1을 하여 update한다.
				if(jobSeqNo==1){
					List<Map<String,Object>> simulationJobList =  getListSimulationJob(groupId, simulationUuid, 1301003, 0, Locale.KOREA);
					if(simulationJobList.size()!=0){
						for(Map<String,Object> simulationJobMap : simulationJobList){
							long jobSeqNoModel = GetterUtil.getLong(simulationJobMap.get("jobSeqNo"),0);
							simulationJobFinder.updateSimulationJobSetJobSeqNo(jobSeqNoModel-1, jobSeqNoModel, simulationUuid, groupId);
						}
					}
				}
				
				//job을 제출 한 것에 대한 리스트를 조회
				List<SimulationJob> searchList = simulationJobFinder.getListSimulationJob(groupId, simulationUuid, 1301003, 0L); 
				if(searchList.size()==0){
					//simulation  삭제
					SimulationPK simulationPK = new SimulationPK(simulationUuid, groupId);
					SimulationLocalServiceUtil.deleteSimulation(simulationPK);
					
					//simulation을 삭제 할때 simulationjob에 아직 제출 되지 않은 Data가 있을 경우 해당 데이터를 삭제
					List<SimulationJob> deleteJobList = simulationJobPersistence.findBysimulationUuid(simulationUuid);
					if(deleteJobList.size()>0){
						for(SimulationJob deleteSimulation:deleteJobList){
							SimulationJobLocalServiceUtil.deleteSimulationJob(deleteSimulation);
						}
					}
				}
			}
		}else{
			List<SimulationJob> simulationList = simulationJobFinder.getListSimulationJob(groupId, simulationUuid, 1301003, 0L);
			for(SimulationJob simulationJob : simulationList){
				try {
					if(!CustomUtil.strNull(simulationJob.getJobUuid()).equals("")){
						returnResult = icebreakerCancleJobAndDeleteData(simulationJob, user);
					}else{
						returnResult = "SUCCESS";
					}
				} catch (MalformedURLException e) {
					throw new PortalException(e);
				} catch (IOException e) {
					throw new PortalException(e);
				} catch (ParseException e) {
					throw new PortalException(e);
				}
			}
			if(returnResult.equals("SUCCESS")){
				//simulation  삭제
				SimulationPK simulationPK = new SimulationPK(simulationUuid, groupId);
				SimulationLocalServiceUtil.deleteSimulation(simulationPK);
				
				//simulation을 삭제 할때 simulationjob에 아직 제출 되지 않은 Data가 있을 경우 해당 데이터를 삭제
				List<SimulationJob> deleteJobList = simulationJobPersistence.findBysimulationUuid(simulationUuid);
				if(deleteJobList.size()>0){
					for(SimulationJob deleteSimulation:deleteJobList){
						SimulationJobLocalServiceUtil.deleteSimulationJob(deleteSimulation);
					}
				}
			}
			
		}
		return returnResult;
	}
	
	protected String icebreakerCancleJobAndDeleteData(SimulationJob simulationJob,User user) throws PortalException, SystemException, MalformedURLException, IOException, ParseException{
		boolean cancleSuccess = true;
		String returnResult = "SUCCESS";
		SimulationJobPK simulationJobPK = new SimulationJobPK(simulationJob.getJobSeqNo(), simulationJob.getSimulationUuid(), simulationJob.getGroupId());
		
		if(simulationJob.getJobStatus()==MonitoringStatusConstatns.QUEUED||simulationJob.getJobStatus()==MonitoringStatusConstatns.RUNNING){
			String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(simulationJob.getGroupId()).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
			IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(simulationJob.getGroupId(), user);
			
			int cancleResult = SimulationLocalServiceUtil.cancleJob(icebreakerUrl, vcToken.getVcToken(), simulationJob.getSimulationUuid(), simulationJob.getJobUuid());
			if(cancleResult!=200){
				returnResult = "--simulation_uuid:"+simulationJob.getSimulationUuid();
				returnResult += ", job_uuid:"+simulationJob.getJobUuid();
				returnResult += ",canclejob error_code:"+String.valueOf(cancleResult);
				cancleSuccess = false;
			}
		}
		
		
		boolean deleteSuccess = true;
		if(cancleSuccess){
			String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(simulationJob.getGroupId()).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
			IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(simulationJob.getGroupId(), user);
			
			int deleteResult = SimulationLocalServiceUtil.deleteSimulationJob(icebreakerUrl, vcToken.getVcToken(), simulationJob.getSimulationUuid(), simulationJob.getJobUuid());
			if(deleteResult!=200){
				returnResult = "--simulation_uuid:"+simulationJob.getSimulationUuid();
				returnResult += ", job_uuid:"+simulationJob.getJobUuid();
				returnResult += ",deletejob error_code:"+String.valueOf(deleteResult);
				deleteSuccess = false;
			}
		}
/*		if(cancleSuccess&&deleteSuccess){
			SimulationJobLocalServiceUtil.deleteSimulationJob(simulationJobPK);
			//MyFile Data가 있을 경우 삭제
			int myFileCnt = MyFileMgntLocalServiceUtil.getMyfileMgntCountByjobUuid(user.getUserId(), 1917002,simulationJob.getJobUuid());
			
			if(myFileCnt!=0){
				List<MyFileMgnt> fileList = MyFileMgntLocalServiceUtil.getMyFileListByjobUuid(user.getUserId(), 1917002,simulationJob.getJobUuid());
				for(MyFileMgnt myFileMgnt:fileList){
					//MyFileMgnt  삭제
					MyFileMgntLocalServiceUtil.deleteMyFileMgnt(myFileMgnt);
					
					//MyFileMgntAPIInfo 삭제
					MyFileMgntAPIInfoPK myFileMgntAPIInfoPK = new MyFileMgntAPIInfoPK(myFileMgnt.getFileSeq(), "POST");
					MyFileMgntAPIInfoLocalServiceUtil.deleteMyFileMgntAPIInfo(myFileMgntAPIInfoPK);
				}
			}
		}
*/		
		return returnResult;
	}

	
	public long getMaxStatusSeqSimulationJobStatus(long groupId, String simulationUuid, String jobUuid){
		
		long maxStatusSeq = 0;			
		
		maxStatusSeq = simulationJobFinder.getMaxStatusSeqSimulationJobStatus(groupId, simulationUuid, jobUuid);
		
		return maxStatusSeq;
	}	

	
	public long getStatusSimulationJobStatus(long groupId, String simulationUuid, String jobUuid){
		
		long jobStatus = 0;			
		
		jobStatus = simulationJobFinder.getStatusSimulationJobStatus(groupId, simulationUuid, jobUuid);
		
		return jobStatus;
	}	

	//
	public List<SimulationJob> getMigrationSimulationJobStatus(long groupId, long jobStatus, boolean dateUpdate){
		return simulationJobFinder.getMigrationSimulationJobStatus(groupId, jobStatus, dateUpdate);
	}
	
	/**
	 * 시뮬레이션 프로젝트 모니터링 리스트
	 * @param classId - 시뮬레이션 프로젝트 모델 아이디
	 * @param customId - 시뮬레이션 프로젝트 아이디
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String, Object>> getSimulationMonitoringJobList(long classId, long customId, int begin, int end) throws SystemException, PortalException, ParseException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		List<Object[]> monitoringList = null;
		monitoringList = simulationJobFinder.getSimulationMonitoringJobList(classId, customId, begin, end);
		Map <String, Object> resultRow = null;
		for (int i = 0; i < monitoringList.size(); i++) {
			Object[] resultArray = monitoringList.get(i);
			if(resultArray != null) {
				Simulation simulation = (Simulation) resultArray[0];
				SimulationJob simulationJob = (SimulationJob) resultArray[1];
				String stayDt = CustomUtil.strNull(resultArray[2]);
				String executeDt = CustomUtil.strNull(resultArray[3]);
				String jobCnt = CustomUtil.strNull(resultArray[4]);
				
				resultRow = new HashMap<String, Object>();
				if (simulation != null) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					resultRow.put("simulationUuid",simulation.getSimulationUuid());
					resultRow.put("groupId",simulation.getGroupId());
					resultRow.put("userId",simulation.getUserId());
					//userNm
					User user = UserLocalServiceUtil.getUser(simulation.getUserId());
					resultRow.put("userNm",user.getScreenName());
					resultRow.put("simulationTitle",simulation.getSimulationTitle());
//					resultRow.put("simulationDescription",simulation.getSimulationDescription());
					resultRow.put("scienceAppId",simulation.getScienceAppId());
					resultRow.put("scienceAppName",simulation.getScienceAppName());
					resultRow.put("simulationCreateDt",simulation.getSimulationCreateDt());
					resultRow.put("cluster",simulation.getCluster());

					resultRow.put("jobSeqNo",simulationJob.getJobSeqNo());
//					resultRow.put("resultSize",simulationJob.getResultSize());
					resultRow.put("jobUuid",simulationJob.getJobUuid());
					resultRow.put("jobStatus",simulationJob.getJobStatus());
					//상태에 대한 이미지 파일 
//					String jobStatusImg = EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(simulationJob.getJobStatus()),EdisonExpando.OPTION1);
					
					String jobStatusImg = "";
					if(simulationJob.getJobStatus() == 1701005) jobStatusImg = "queued.png";
					else if(simulationJob.getJobStatus() == 1701006) jobStatusImg = "running.png";
					else if(simulationJob.getJobStatus() == 1701011) jobStatusImg = "success.png";
					else if(simulationJob.getJobStatus() == 1701012) jobStatusImg = "failed.png";
					
					resultRow.put("jobStatusImg",jobStatusImg);
					if(CustomUtil.strNull(simulationJob.getJobEndDt()).equals("")){
						resultRow.put("jobStartDt","");
					}else{
						resultRow.put("jobStartDt",simpleDateFormat.format(CustomUtil.StringToDateFormat(CustomUtil.strNull(simulationJob.getJobStartDt()), "yyyy-MM-dd HH:mm:ss")));
					}
					
					if(CustomUtil.strNull(simulationJob.getJobEndDt()).equals("")){
						resultRow.put("jobEndDt","");
					}else{
						resultRow.put("jobEndDt",simpleDateFormat.format(CustomUtil.StringToDateFormat(CustomUtil.strNull(simulationJob.getJobEndDt()), "yyyy-MM-dd HH:mm:ss")));
					}
					resultRow.put("jobTitle",simulationJob.getJobTitle());
					resultRow.put("jobExecPath",simulationJob.getJobExecPath());
					resultRow.put("jobPhase",simulationJob.getJobPhase());
					resultRow.put("jobSubmitDt",simpleDateFormat.format(CustomUtil.StringToDateFormat(CustomUtil.strNull(simulationJob.getJobSubmitDt()), "yyyy-MM-dd HH:mm:ss")));
//					resultRow.put("jobPostProcessor",simulationJob.getJobPostProcessor());
					resultRow.put("jobPostProcessorYn","N");
					resultRow.put("jobMiddleFileProcessorYn","N");
					
					resultRow.put("jobUniversityField",simulationJob.getJobUniversityField());
//					resultRow.put("jobVirtualLabId",simulationJob.getJobVirtualLabId());
//					resultRow.put("jobClassId",simulationJob.getJobClassId());
					resultRow.put("jobInputDeckYn",simulationJob.getJobInputDeckYn());
					resultRow.put("jobInputDeckName",simulationJob.getJobInputDeckName());
					
					resultRow.put("stayDt", stayDt);
					resultRow.put("executeDt", executeDt);
					resultRow.put("jobCnt", jobCnt);
				}
				returnList.add(resultRow);
			}
		}
		
		return returnList;
	}
	
	/**
	 * 시뮬레이션 프로젝트 모니터링 카운트
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getSimulationMonitoringJobCount(long classId, long customId) throws SystemException{
		
		return simulationJobFinder.getSimulationMonitoringJobCount(classId, customId);
	}
	
	/**
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ##### 통계 Service #########################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 */

	//기관 정보에 따른 실험실 목록
	public List<Map<String, Object>> getListVirtualLabSearchUni(long groupId, String languageId, long jobUniversityField) throws SystemException, PortalException, ParseException{
	List<Object[]> tempList = simulationJobFinder.getListVirtualLabSearchUni(groupId, languageId, jobUniversityField);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("virtualLabId",		objs[0]);
					map.put("virtualLabTitle",		objs[1]);
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	//실험실정보에 따른 수업목록
	public List<Map<String, Object>> getListVirtualClassSearchLab(long groupId, String languageId, long jobVirtualLabId) throws SystemException, PortalException, ParseException{
		List<Object[]> tempList = simulationJobFinder.getListVirtualClassSearchLab(groupId, languageId, jobVirtualLabId);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("classId",		objs[0]);
					map.put("classTitle",		objs[1]);
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	
	/**
	 * ##### 2. Sw START ###################################################################################################################################################
	 */
	public List<Long> getSiteCategoryIdList(long globalGroupId, long groupId) throws PortalException, SystemException{
		
		List<Long> siteCategoryIdList = new ArrayList<Long>();
		
		//커뮤니티 사이트 일경우 현재 사이트에 등록된 Category를 가지고 온다.
		AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
		AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId); //현재 그룹애 해당하는  AssetEntry를 가지고 온다.
		List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId()); //사이트 관리 > 구성 > 사이트 설정 > 분류에 설정된 카테고리를 모두 가지고 온다.
		
		//GLOBAL_DOMAIN에 등록된 카테고리를 가지고 온다.
		for(AssetCategory aCategory : aCategoryList){
			if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()){
				siteCategoryIdList.add(aCategory.getCategoryId());
			}
		}
		
		return siteCategoryIdList;
	}
	
	protected Map<String,Object> settingStatisticsParameter(long companyGroupId, long groupId, long columnId, 
			String startDt, String endDt, boolean categorySearch) 
					throws PortalException, SystemException {
		Map<String,Object> searchParam = new HashMap<String,Object>();

		if(categorySearch){
			List<Long> siteCategoryIdList = getSiteCategoryIdList(companyGroupId, groupId);
			String categoryIds = "";
	        for(Long siteCategoryId : siteCategoryIdList){
	        	if("".equals(categoryIds)){
	        		categoryIds += String.valueOf(siteCategoryId);
	        	}else{
	        		categoryIds += ", "+String.valueOf(siteCategoryId);
	        	}
	        }
	        searchParam.put("categoryIds", categoryIds);			
		}
		
		searchParam.put("columnId", columnId);
		searchParam.put("startDt", startDt);
		searchParam.put("endDt", endDt);
		
		return searchParam;
	}
	
	public List<Map<String, Object>> getStatisticsSwTableOrganigation(long companyGroupId, long groupId, Locale locale, 
			long columnId, String startDt, String endDt, boolean categorySearch
			) throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = settingStatisticsParameter(companyGroupId, groupId, columnId, startDt, endDt, categorySearch);
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class.getName());
        searchParam.put("classNameId", classNameId);
        
		List<Object[]> tempList = simulationJobFinder.getStatisticsSwTableOrganigation(searchParam);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("universityId",		objs[0]);

					String universityNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(objs[0]), EdisonExpando.CDNM, locale);
					map.put("universityNm",		universityNm);
					map.put("cnt",		objs[1]);
					map.put("appNames",		objs[2].toString().trim());
					resultList.add(map);
				}
				
				Comparator<HashMap> affiliation = new Comparator<HashMap>() {
					@Override
					public int compare(HashMap o1, HashMap o2) {
						String s1 = o1.get("universityNm").toString();
						String s2 = o2.get("universityNm").toString();
						
						int n1=s1.length(), n2=s2.length();
						for (int i1=0, i2=0; i1<n1 && i2<n2; i1++, i2++) {
							char c1 = s1.charAt(i1);
							char c2 = s2.charAt(i2);
							if (c1 != c2) {
								c1 = Character.toUpperCase(c1);
								c2 = Character.toUpperCase(c2);
								if (c1 != c2) {
									c1 = Character.toLowerCase(c1);
									c2 = Character.toLowerCase(c2);
									if (c1 != c2) {
										return c1 - c2;
									}
								}
							}
						}
						return n1 - n2;
					}
				};
				
				Collections.sort(resultList, affiliation);
			}
		}		
		return resultList;
	}	

	public List<Map<String, Object>> getStatisticsSwBarChartDate(long companyGroupId, long groupId, Locale locale, 
			long columnId, String startDt, String endDt, boolean categorySearch
			) throws SystemException, PortalException, ParseException{
		
		Map<String,Object> searchParam = settingStatisticsParameter(companyGroupId, groupId, columnId, startDt, endDt, categorySearch);
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class.getName());
        searchParam.put("classNameId", classNameId);
        
		List<Object[]> tempList = simulationJobFinder.getStatisticsSwBarChartDate(searchParam);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("month",		objs[0]);
					map.put("count",		objs[1]);
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	/**
	 * ##### 2. Sw END ###################################################################################################################################################
	 */	

	
	/**
	 * ##### 5. User START ###################################################################################################################################################
	 */	
	public List<Map<String, Object>> getStatisticsUserTableOrganigation(long groupId, Locale locale, long columnId, String startDt, String endDt) throws SystemException, PortalException, ParseException{		
		List<Object[]> tempList = simulationJobFinder.getStatisticsUserTableOrganigation(groupId, columnId, startDt, endDt);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;

		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(objs[0]), EdisonExpando.CDNM, locale);
					map.put("affiliation",	affiliation); 
					map.put("userCnt",		objs[1]);
					
					resultList.add(map);
				}
				Comparator<HashMap> affiliation = new Comparator<HashMap>() {
					@Override
					public int compare(HashMap o1, HashMap o2) {
						String s1 = o1.get("affiliation").toString();
						String s2 = o2.get("affiliation").toString();
						
						int n1=s1.length(), n2=s2.length();
						for (int i1=0, i2=0; i1<n1 && i2<n2; i1++, i2++) {
							char c1 = s1.charAt(i1);
							char c2 = s2.charAt(i2);
							if (c1 != c2) {
								c1 = Character.toUpperCase(c1);
								c2 = Character.toUpperCase(c2);
								if (c1 != c2) {
									c1 = Character.toLowerCase(c1);
									c2 = Character.toLowerCase(c2);
									if (c1 != c2) {
										return c1 - c2;
									}
								}
							}
						}
						return n1 - n2;
					}
				};
				
				Collections.sort(resultList, affiliation);
			}
		}		
		return resultList;
	}	
	public List<Map<String, Object>> getStatisticsUserBarChartDate(long groupId, long columnId, String startDt, String endDt) throws SystemException, PortalException, ParseException{
		List<Object[]> tempList = simulationJobFinder.getStatisticsUserBarChartDate(groupId, columnId, startDt, endDt);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("month",		objs[0]);
					map.put("count",		objs[1]);
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	/**
	 * ##### 5. User END ###################################################################################################################################################
	 */
	
	
	/**
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ##### EDISON 프로젝트 앱, 시뮬레이션 통계 Service #########################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 */
	
	public List<Map<String, Object>> getMajorAchievementsListForProjectStatistics(String projectYn, String key, long groupId) throws Exception{
		
		List<Object[]> majorAchievementList  = simulationJobFinder.getMajorAchievementsListForProjectStatistics(projectYn, key);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		

		long appDetailPlid = PortalUtil.getPlidFromPortletId(groupId, false, "edisonprojectapp_WAR_edisonproject2016portlet");
		long contentDetailPlid = PortalUtil.getPlidFromPortletId(groupId, false, "edisonprojectcontent_WAR_edisonproject2016portlet");
		
		for (int i = 0; i < majorAchievementList.size(); i++) {
			Object[] achievementObj = (Object[])majorAchievementList.get(i);
			resultRow = new HashMap<String, Object>();
			
			if(achievementObj != null ){
				resultRow.put("projectCategoryId", 	achievementObj[0]);    
				resultRow.put("projectCategoryNm", 	achievementObj[1]);    
				resultRow.put("propertyKey", 		achievementObj[2]);    
				resultRow.put("propertyValue",		achievementObj[3]);
				resultRow.put("conCnt",				achievementObj[4]);
				resultRow.put("appCnt",				achievementObj[5]);
				resultRow.put("appDetailPlid",		appDetailPlid);
				resultRow.put("contentDetailPlid",	contentDetailPlid);

				returnList.add(resultRow);
			}
			
		}
		return returnList;
	}

	public List<Map<String, Object>> getScienceAppCenterListForProjectStatistics(String propertyKey, Locale locale) throws Exception{
		
		List<Object[]> scienceAppCenterList  = simulationJobFinder.getScienceAppCenterListForProjectStatistics(propertyKey);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < scienceAppCenterList.size(); i++) {
			Object[] scienceAppCenterObj = (Object[])scienceAppCenterList.get(i);
			resultRow = new HashMap<String, Object>();
			
			if(scienceAppCenterObj != null){
				int projectCnt = 0;
				if(scienceAppCenterObj[4] != null){
					projectCnt = (Integer)scienceAppCenterObj[4];
				}
				
				if(projectCnt > 0){
					resultRow.put("projectCategoryId", 	scienceAppCenterObj[0]);
					resultRow.put("projectCategoryNm", 	scienceAppCenterObj[1]);    
					resultRow.put("prpertyKey", 		scienceAppCenterObj[2]);    
					resultRow.put("propertyValue",		LanguageUtil.get(locale, CustomUtil.strNull(scienceAppCenterObj[3])));    
					resultRow.put("projectCnt",		 	projectCnt);    
					
					
					returnList.add(resultRow);
				}
			}
			
		}
		return returnList;
	}
	
	
	public List<Map<String, Object>> getAppDetailListForProjectStatistics(long jobPhase, long columnId, long categoryId, String languageId, Locale locale) throws Exception{
		
		List<Object[]> appDetailList  = simulationJobFinder.getAppDetailListForProjectStatistics(jobPhase, columnId, categoryId, languageId);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < appDetailList.size(); i++) {
			Object[] appDetailObj = (Object[])appDetailList.get(i);
			resultRow = new HashMap<String, Object>();
			
			if(appDetailObj != null){
				resultRow.put("scienceAppId", 		appDetailObj[0]);    
				resultRow.put("groupId", 			appDetailObj[1]);    
				resultRow.put("projectCategoryId", 	appDetailObj[2]);    
				resultRow.put("projectCategoryNm", 	appDetailObj[3]);    
				resultRow.put("propertyKey", 		appDetailObj[4]);    
				resultRow.put("propertyValue",		appDetailObj[5]);    
				resultRow.put("appTitle", 			appDetailObj[6]);    
				
				String affiliation = "";
				if(!"".equals(String.valueOf(appDetailObj[7])) && !"0".equals(String.valueOf(appDetailObj[7]))){
					affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(appDetailObj[7]), EdisonExpando.CDNM, locale);
				}
				resultRow.put("affiliation", 		affiliation);    
				resultRow.put("authorId", 			appDetailObj[8]);    
				resultRow.put("firstName", 			appDetailObj[9]);    
				resultRow.put("screenName", 		appDetailObj[10]);    
				resultRow.put("appVersion", 		appDetailObj[11]);    
				resultRow.put("runtime", 			appDetailObj[12]);    
				resultRow.put("executeCount", 		appDetailObj[13]);    
				resultRow.put("averageRuntime", 	appDetailObj[14]);   
				resultRow.put("userCount", 			appDetailObj[15]);   
				resultRow.put("createDate", 		appDetailObj[16]);   
				
				returnList.add(resultRow);
			}
			
		}
		return returnList;
	}
	
	public Map<String, Object> getVirtualClassStatisticsSimulation(String scienceAppIdObj,String virtualLabUsersIdObj) {
		
		String[] scienceAppIds = StringUtil.split(scienceAppIdObj);
		String[] userIds = StringUtil.split(virtualLabUsersIdObj);
		
		boolean searchList = false;
		Map<String, Object> virtualClassStatisticsMap = new HashMap<String, Object>();
		
		if(scienceAppIds.length!=0&&userIds.length!=0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userIds", userIds);
			params.put("scienceAppIds", scienceAppIds);
			
			Object[] simulationMap = simulationJobFinder.getVirtualClassStatisticsSimulation(params);
			
			virtualClassStatisticsMap.put("executeUserCnt", simulationMap[0]);
			virtualClassStatisticsMap.put("executeCnt", simulationMap[1]);
			virtualClassStatisticsMap.put("cpuTime", simulationMap[2]);
		}else{
			virtualClassStatisticsMap.put("executeUserCnt", 0);
			virtualClassStatisticsMap.put("executeCnt", 0);
			virtualClassStatisticsMap.put("cpuTime", 0);
		}
		
		return virtualClassStatisticsMap;
	}
	
	public List<Map<String, Object>> getVirtualClassStatisticsList(Map<String, Object> params, Locale locale, boolean excelFile) {
		List<Object[]> virtualClassStatisticsList = simulationJobFinder.getVirtualClassStatisticsList(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		if(virtualClassStatisticsList != null && virtualClassStatisticsList.size() > 0) {
			for (int i = 0; i < virtualClassStatisticsList.size(); i++) {
				Object[] resultArray = virtualClassStatisticsList.get(i);
				
				if(resultArray != null) {
					int groupId = (Integer) resultArray[0];
					String university = (String) resultArray[1];
					Long virtualLabId = (Long) resultArray[2];
					String virtualLabTitle = (String) resultArray[3];
					String classTitle = (String) resultArray[4];
					String virtualLabPersonName = (String) resultArray[5];
					String classId = (String) resultArray[6];
					String scienceAppIdObj = CustomUtil.strNull(resultArray[7]);
					String virtualLabUsersIdObj = CustomUtil.strNull(resultArray[8]);
					String registerStudentCtn = CustomUtil.strNull(resultArray[9]);
					String classCreateDt = CustomUtil.strNull(resultArray[10]);
					
					Map<String, Object> virtualClassStatisticsMap = SimulationJobLocalServiceUtil.getVirtualClassStatisticsSimulation(scienceAppIdObj, virtualLabUsersIdObj);
					
					String executeCount = CustomUtil.strNull(virtualClassStatisticsMap.get("executeCnt"),"0");
					String executeStudentcount = CustomUtil.strNull(virtualClassStatisticsMap.get("executeUserCnt"),"0");
					String avgerageRuntime = CustomUtil.strNull(virtualClassStatisticsMap.get("cpuTime"),"0");
					
					resultRow = new HashMap<String, Object>();
					resultRow.put("groupId", groupId);
					
					String affiliation = "";
					if(!"".equals(university)){
						affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(university), EdisonExpando.CDNM, locale);
					}
					
					resultRow.put("universityId", university);
					resultRow.put("university", affiliation);
					resultRow.put("virtualLabId", virtualLabId);
					resultRow.put("virtualLabTitle", virtualLabTitle);
					resultRow.put("classTitle", classTitle);
					resultRow.put("virtualLabPersonName", virtualLabPersonName);
					resultRow.put("classId", classId);
					resultRow.put("executeCount", executeCount);
					resultRow.put("executeStudentcount",executeStudentcount);
//					resultRow.put("classPersonnel",classPersonnel);
					resultRow.put("classCreateDt",classCreateDt);
					resultRow.put("registerStudentCtn",registerStudentCtn);
					
					List<String> scienceAppTitleList = new ArrayList<String>();

					String scienceAppTitle = ""; 
					if(scienceAppIdObj != null && !scienceAppIdObj.equals("")){
						String scienceAppIdArray[] = scienceAppIdObj.split(",");
						List<String> scienceAppIdDistinctList = new ArrayList<String>(new HashSet<String>(new ArrayList<String>(Arrays.asList(scienceAppIdArray))));
						
						if(!scienceAppIdDistinctList.isEmpty() && scienceAppIdDistinctList.size() > 0){
							for(String scienceAppId : scienceAppIdDistinctList){
								if(scienceAppTitleList.size() < 5){
									ScienceApp sciecneApp = null;
									try {
										sciecneApp = ScienceAppLocalServiceUtil.getScienceApp(Long.parseLong(scienceAppId));
										scienceAppTitleList.add(sciecneApp.getTitle(locale));
										resultRow.put("scienceAppTitle", scienceAppTitleList);
										
										if(excelFile){
											scienceAppTitle += "● " + sciecneApp.getTitle(locale) +"\n ";					
											resultRow.put("scienceAppTitle", scienceAppTitle);
										}
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (PortalException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SystemException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}else{
									break;
								}
							}
						}
					}
					
					resultRow.put("avgerageRuntime", avgerageRuntime);
//					resultRow.put("classCnt", classCnt);
					
					returnList.add(resultRow);
				}
			}
		}
		return returnList;
	}
	
	public int getCountVirtualClassStatistics(Map<String, Object> params, Locale locale) {
		return simulationJobFinder.getCountVirtualClassStatistics(params, locale);
	}
	
	/* etrub */
	
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SimulationJob> getJobsBySimulationUuidWithAdditionalCondition(String simulationUuid)
        throws SystemException{
	    DynamicQuery query = DynamicQueryFactoryUtil.forClass(
	        SimulationJob.class, "job", PortletClassLoaderUtil.getClassLoader());
	    query.add(PropertyFactoryUtil.forName("primaryKey.simulationUuid").eq(simulationUuid));
	    query.add(RestrictionsFactoryUtil.gt("job.jobStatus", MonitoringStatusConstatns.SUBMISSION_FAILED))
	         .add(RestrictionsFactoryUtil.eq("job.jobSubmit", true));
	    List jobs = getSimulationJobLocalService().dynamicQuery(query);
	    return jobs == null ? null : (List<SimulationJob>) jobs;
	}
	
	/* etrub */
	
	
	/**
	 *  Added By Jerry H. Seo
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	
	public SimulationJob addJob( String simulationUuid, long groupId ) throws SystemException{
		SimulationJobPK jobPK = new SimulationJobPK( this.getMaxJobSeqNoSimulationJob(simulationUuid), simulationUuid, groupId);
		SimulationJob job = super.createSimulationJob(jobPK);
		
		job.setJobUuid(UUID.randomUUID().toString());
		job.setJobTitle("#"+String.format("%04d", this.getMaxJobSeqNoSimulationJob(simulationUuid)));
		job.setJobSubmit(false);
		
		return super.simulationJobPersistence.update(job);
	}
	
	public SimulationJob getJob( String jobUuid ) throws NoSuchSimulationJobException, SystemException{
		return super.simulationJobPersistence.findByUuid(jobUuid);
	}
	
	public List<SimulationJob> getJobsByAppUuid( String uuid ) throws SystemException{
		return super.simulationJobPersistence.findBysimulationUuid(uuid);
	}
	
	public List<SimulationJob> getJobsByAppUuid( String uuid, int start, int end ) throws SystemException{
		return super.simulationJobPersistence.findBysimulationUuid(uuid, start, end);
	}
	
	public int countJobsByAppUuid( String uuid ) throws SystemException{
		return super.simulationJobPersistence.countBysimulationUuid(uuid);
	}
	
	public List<SimulationJob> getJobsBySimulationUuid( String uuid ) throws SystemException{
		return super.simulationJobPersistence.findBysimulationUuid(uuid);
	}
	
	public List<SimulationJob> getJobsBySimulationUuid( String uuid, int start, int end ) throws SystemException{
		return super.simulationJobPersistence.findBysimulationUuid(uuid, start, end);
	}
	
	public int countJobsBySimulationUuid( String uuid ) throws SystemException{
		return super.simulationJobPersistence.countBysimulationUuid(uuid);
	}
	
	public String getJobInputData( String jobUuid ) throws NoSuchSimulationJobDataException, SystemException{
		SimulationJobData inputs = super.simulationJobDataPersistence.findByPrimaryKey(jobUuid);
		
		return inputs.getJobData();
	}
	
	public void deleteJobsBySimulationUuid( String simulationUuid ) throws SystemException{
		List<SimulationJob> jobs = super.simulationJobLocalService.getJobsBySimulationUuid(simulationUuid);
		for( SimulationJob job : jobs ){
			try {
				deleteJob(job.getJobUuid());
			} catch (NoSuchSimulationJobException e) {
				//no throw SystmeException 20180202
				throw new SystemException( e.getMessage());
			}
		}
	}
	
	public String getJobWorkingDirPath( String jobUuid ) throws NoSuchSimulationJobException, SystemException{
		SimulationJob job = super.simulationJobPersistence.findByUuid(jobUuid);
		if( job.getJobSubmit() )
			return job.getSimulationUuid()+File.separator+job.getJobUuid()+".job";
		else
			return StringPool.BLANK;
	}
	
	public void deleteJob( String jobUuid ) throws NoSuchSimulationJobException, SystemException{
		try {
			SimulationJob job = super.simulationJobPersistence.removeByUuid(jobUuid);
			cancleJob(job);
			deleteJobFormIB(job);
			super.simulationJobDataPersistence.remove(jobUuid);
		} catch (NoSuchSimulationJobDataException e) {
			log.info("Job has no input data: "+jobUuid);
		}
	}
	
	private void cancleJobFromJobUuid(String jobUuid) throws NoSuchSimulationJobException, SystemException{
		SimulationJob job = super.simulationJobPersistence.findByUuid(jobUuid);
		cancleJob(job);
		job.setJobStatus(1701010);
		super.simulationJobPersistence.update(job);
	}
	
	private void cancleJob(SimulationJob job) throws SystemException{
		if(job.getJobStatus()==1701006){
			try{
				long userId = SimulationLocalServiceUtil.getSimulationByUUID(job.getSimulationUuid()).getUserId();
				long groupId = job.getGroupId();
				User user = UserLocalServiceUtil.getUser(userId);
				String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
				IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
				SimulationLocalServiceUtil.cancleJob(icebreakerUrl, vcToken.getVcToken(), job.getSimulationUuid(), job.getJobUuid());
			}catch(Exception e){
				e.printStackTrace();
				throw new SystemException(e);
			}
		}
	}
	
	private void deleteJobFormIB(SimulationJob job) throws SystemException{
		if(job.getJobSubmit()){
			try{
				long userId = SimulationLocalServiceUtil.getSimulationByUUID(job.getSimulationUuid()).getUserId();
				long groupId = job.getGroupId();
				User user = UserLocalServiceUtil.getUser(userId);
				String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
				IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
				SimulationLocalServiceUtil.deleteSimulationJob(icebreakerUrl, vcToken.getVcToken(), job.getSimulationUuid(), job.getJobUuid());
			}catch(Exception e){
				e.printStackTrace();
				throw new SystemException(e);
			}
		}
	}
}