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

package com.kisti.osp.icecap.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.DataEntryProvenance;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryProvenanceLocalServiceUtil;
import com.kisti.osp.icecap.service.base.DataEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the data entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icecap.service.DataEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataEntryLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataEntryLocalServiceUtil
 */
public class DataEntryLocalServiceImpl extends DataEntryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.osp.icecap.service.DataEntryLocalServiceUtil} to access the data entry local service.
	 */
	public DataEntry createDataEntryObject (
			Long dcId, // data collection id
			Long simulationSubjectId, 
			String inputData,
			String path,
//			String comments,
			Map<Locale, String> commentsMap,
			String productionTime,
//			Long fileEntryId,
			Long dlFolderId,
			String resultFileId, 
			String resultFileNm,
			ServiceContext sc) throws SystemException, PortalException, SQLException, IOException{
		
		DataEntry dataEntry = null;
		List<DataEntry> dataEntryList = super.dataEntryPersistence.findByCollectionID(dcId);
		 
		for(int i=0;i<dataEntryList.size();i++){
			DataEntry de = dataEntryList.get(i);
			
//			if(de.getSimulationSubjectId() == simulationSubjectId && de.getInputData().equals(inputData)){
//				System.out.println("Found a matching data entry!");
//				// hit!
//				dataEntry = de;
//				break;
//			}
			
			DataEntryProvenance dataEntryProvenance = dataEntryProvenancePersistence.findByPrimaryKey(de.getEntryId());
			String dataEntryInputData = dataEntryProvenance.getInputData();
			
			if(dataEntryInputData != null && !dataEntryInputData.equals("")){
				if(de.getSimulationSubjectId() == simulationSubjectId && dataEntryInputData.equals(inputData)){
					System.out.println("Found a matching data entry!");
					dataEntry = de;
					break;
				}
			}
//			else{
//				if(de.getSimulationSubjectId() == simulationSubjectId){
//					System.out.println("Found a matching data entry!");
//					dataEntry = de;
//					break;
//				}
//			}
		}
		
		//if(dataCollection == null) throw new SystemException("A matching data type does not exist...");
		Long entryId = null;
		if(dataEntry == null){
			System.out.println("Creating a new data entry object...");
			entryId = super.counterLocalService.increment();
			// since this object hasn't been created before,
			// set createdate, companyid, userid, ...
			dataEntry = super.dataEntryPersistence.create(entryId);
			dataEntry.setEntryId(entryId);
			dataEntry.setCollectionId(dcId);
			dataEntry.setCreateDate(sc.getCreateDate(new Date()));
			dataEntry.setUserId(sc.getUserId());
			dataEntry.setGroupId(sc.getScopeGroupId()); // correct?
			dataEntry.setCompanyId(sc.getCompanyId());

			// set initial numbers
			// set download count to be zero by default
			dataEntry.setDownloadCount(new Long(0));
			// set view count to be zero by default
			dataEntry.setViewCount(new Long(0));
			
			// dataEntryProvenance record creation
			DataEntryProvenance dataEntryProvenance = dataEntryProvenancePersistence.create(entryId);
			// set inputData to this object
			dataEntryProvenance.setInputData(inputData);
			super.dataEntryProvenancePersistence.update(dataEntryProvenance);
			

//		int status = 0;
//		if(dcStatus.equalsIgnoreCase("private")){
//			status = 1;
//		}else{
//			status = 0; // public
//		}
//		// set status 
//		dataEntry.setStatus(status);

  		// set science app id 
  		// Q: can we change science app id after it gets set?
  		dataEntry.setSimulationSubjectId(simulationSubjectId);
  		// set input data 
//  		dataEntry.setJobData(inputData);
  		// set collection
  //		dataEntry.setComments(comments);
  		dataEntry.setCommentsMap(commentsMap);
  		// set path 
  		dataEntry.setPath(path);
  		// set production time
  		dataEntry.setProductionTime(Long.parseLong(productionTime));
  		// set file entry Id
  //		dataEntry.setFileEntryId(fileEntryId);
  		
  		
  		long fileEntryId = 0;
  		if(!resultFileId.equals("")){
  			fileEntryId = icebreakerFileUpLoadDLfile(dataEntry, dlFolderId, resultFileId, resultFileNm, sc);
  		}else{
  			fileEntryId = localFileUploadDLfile(dataEntry, dlFolderId, sc);
  		}
  		dataEntry.setFileEntryId(fileEntryId);
		}else{
			// set last accessed time
			dataEntry.setLastAccessedDate(sc.getModifiedDate(new Date()));
			// increment view count 
			Long lastCnt = dataEntry.getViewCount();
			dataEntry.setViewCount(new Long(lastCnt+1));
		
		}
		/* Let's update this data collection row. */
		dataEntry = super.dataEntryPersistence.update(dataEntry);
		
		return dataEntry;
	}
	
	/**
	 * GPLUS
	 * upload dl file mehod 
	 * @param deId
	 * @param dcId
	 * @param simulationSubjectId
	 * @param inputData
	 * @param path
	 * @param commentsMap
	 * @param productionTime
	 * @param dlFolderId
	 * @param sc
	 * @return
	 * @throws SystemException 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws PortalException 
	 */
	public DataEntry updateDataEntryObject (
		Long deId, // data entryId
		Long dcId, // data collection id
		Map<Locale, String> commentsMap,
		Long dlFolderId,
		ServiceContext sc) throws SystemException, PortalException, SQLException, IOException{

		DataEntry dataEntry = dataEntryPersistence.findByPrimaryKey(deId);
		
		if(dataEntry != null){
			dataEntry.setLastAccessedDate(sc.getModifiedDate(new Date()));
			Long lastCnt = dataEntry.getViewCount();
			dataEntry.setViewCount(new Long(lastCnt+1));
			dataEntry.setCommentsMap(commentsMap);
			
			dataEntry = super.dataEntryPersistence.update(dataEntry);
		}
		
		return dataEntry;
	}
	
	/**GPLUS
	 * local file upload by dlFile 
	 * @param dataEntry
	 * @param dcId
	 * @param dlFolderId
	 * @param sc
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @throws SQLException
	 * @throws IOException
	 */
	protected long localFileUploadDLfile(DataEntry dataEntry, Long dlFolderId, ServiceContext sc) 
		throws PortalException, SystemException, SQLException, IOException{
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());

		long fileEntryId = 0;
		if(dlFolderId != 0 ){
			String entryFiles = GetterUtil.getString(upload.getFileName("entryFile"),"");
			
			if(!entryFiles.equals("")){
				List<FileEntry> dataEntryFile = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload,
					dataEntry.getUserId(), dataEntry.getGroupId(), "", String.valueOf(dataEntry.getCollectionId()),
					"entryFile", EdisonFileConstants.DATA_COLLECTION);
			
				if(dataEntryFile != null && dataEntryFile.size() > 0){
					FileEntry entryMap = dataEntryFile.get(0);
					fileEntryId = entryMap.getFileEntryId();
				}
			}			
		}
		
		return fileEntryId;
	}
	
	/**GPLUS
	 * icebreaker file upload by dlFile
	 * @param dataEntry
	 * @param dcFolderId
	 * @param resultFileId
	 * @param resultFileNm
	 * @param sc
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	protected long icebreakerFileUpLoadDLfile(DataEntry dataEntry, long dcFolderId, String resultFileId, String resultFileNm, ServiceContext sc)
		throws PortalException, SystemException, IOException{
		String publicIceBreakerUrl = (String) GroupLocalServiceUtil.getGroup(sc.getScopeGroupId())
			.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);
		
		publicIceBreakerUrl +="/api/file/download?id="+resultFileId;
		
		File tempFile = null;
		
		URL remoteFileURL = new URL(publicIceBreakerUrl);
		URLConnection uCon = remoteFileURL.openConnection();
		InputStream is = uCon.getInputStream();
		
		byte[] bytes = FileUtil.getBytes(is);
		
		if(ArrayUtil.isNotEmpty(bytes)){
			tempFile = FileUtil.createTempFile(bytes);
		}else{
			try{
				tempFile = FileUtil.createTempFile(is);
				is.close();
			}catch (IllegalArgumentException e){
				// 빈 값의 temp 파일 생성 메소드
				tempFile = FileUtil.createTempFile();
				// temp 파일 물리적 생성
				FileUtil.write(tempFile, "");
			}	
		}
		
		String contentType = MimeTypesUtil.getContentType(resultFileNm);
		FileEntry fileEntry = null;
		
		try{
			fileEntry = DLAppLocalServiceUtil.addFileEntry(dataEntry.getUserId(), sc.getScopeGroupId(), dcFolderId,
				null, contentType, resultFileNm, null, null, tempFile, sc);
		}catch (DuplicateFileException e){
			// 중복파일명 입력 시 파일명 변경처리
			Random rand = new Random();
			int index = resultFileNm.lastIndexOf(".");

			String onlyFileName = resultFileNm.substring(0, index);
			String ext = resultFileNm.substring(index);

			fileEntry = DLAppLocalServiceUtil.addFileEntry(dataEntry.getUserId(), sc.getScopeGroupId(), dcFolderId,
				null, contentType, onlyFileName + "_" + CustomUtil.getRandomString(3) + ext, null, null, tempFile,
				sc);
		}
		
		long fileEntryId = 0;
		if(fileEntry != null){
			fileEntryId = fileEntry.getFileEntryId();
		}
		
		return fileEntryId;
	}
	
	
	/**GPLUS
	 * Check Already Exist DataEntry with SimulationSubjectId, And InputData
	 * if exist this simulationSubjectId and inputData, return true 
	 * @param simulationSubjectId
	 * @param inputData
	 * @return
	 * @throws SystemException
	 */
	public Map<String, Object> checkExistSubjectIdAndJobData(Long simulationSubjectId, String inputData) throws SystemException, PortalException{
//		List<DataEntry> dataEntryList = dataEntryPersistence.findBySimulationSubjectIdAndInputData(simulationSubjectId, jobData);
		
		List<DataEntry> dataEntryList = new ArrayList<DataEntry>();
		List<DataEntry> dataEntryCandidateList = dataEntryPersistence.findBySimulationSubject(simulationSubjectId);
		if(dataEntryCandidateList != null){
			for(int i=0;i<dataEntryCandidateList.size();i++){
				DataEntry dataEntry = dataEntryCandidateList.get(i);
				DataEntryProvenance dataEntryProvenance = DataEntryProvenanceLocalServiceUtil.fetchDataEntryProvenance(dataEntry.getEntryId());
				String dataEntryInputData = dataEntryProvenance.getInputData();
				if(dataEntryInputData != null && dataEntryInputData.equals(inputData)){
					dataEntryList.add(dataEntry);
				}
			}
		}
		
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		boolean alreadyExistDataEntry = false;
		//collection 명, 파일명
		if(dataEntryList != null && dataEntryList.size() > 0){
			alreadyExistDataEntry = true;
			DataEntry dataEntry = dataEntryList.get(0);
			
			DataCollection dataCollection = DataCollectionLocalServiceUtil.getDataCollection(dataEntry.getCollectionId());
			messageMap.put("collectionName", dataCollection.getName());
			
			DLFileEntry dataEntryFile = DLFileEntryLocalServiceUtil.getFileEntry(dataEntry.getFileEntryId());
			messageMap.put("dataEntryFileName", dataEntryFile.getTitle());
		}
		messageMap.put("alreadyExistDataEntry", alreadyExistDataEntry);
		
		return messageMap;
	}

	/****
	 * Retrieve a list of DataEntry objects matching a given subjectId
	 * @param subjectId
	 * @return a list of data entries
	 * @throws SystemException
	 */
	public List<DataEntry> findDataEntryPerApp(
			Long subjectId) throws SystemException{
		List<DataEntry> resDataEntryList = null;
		if(subjectId != null){
			resDataEntryList = dataEntryPersistence.findBySimulationSubject(subjectId);
		}
		return resDataEntryList;
	}
	
	/*****
	 * Find a DataEntry object matching given info
	 * @param dtTypeName DataType name
	 * @param dtTypeVersion DataType version
	 * @param dcCollectionName DataCollection name
	 * @param dcCollectionVersion DataCollection version
	 * @param subjectId subject id: science app or workflow instance
	 * @param inputData input data
	 * @return a DataEntry object
	 * @throws SystemException
	 */
	public DataEntry findDataEntryObject(
			String dtTypeName,
			String dtTypeVersion,
			String dcCollectionName,
			String dcCollectionVersion,
			Long subjectId, 
			String inputData) throws SystemException{
		DataEntry de = null;
		DataType dt = null;
		DataCollection dc = null;
		
		DataType dtItem = dataTypePersistence.fetchByNameVersion(dtTypeName, dtTypeVersion);
		if(dtItem != null){
			dt = dtItem;
//			DataCollection dcItem = dataCollectionPersistence.fetchByTypeID_First(dt.getTypeId(), null);
//			if(dcItem != null){
			List<DataCollection> dcList = dataCollectionPersistence.findByTypeID(dt.getTypeId());
			for(int i=0;i<dcList.size();i++){
				DataCollection dcItem = dcList.get(i);
				if(dcItem.getName().equals(dcCollectionName) && dcItem.getVersion().equals(dcCollectionVersion)){
					dc = dcItem;
					break;
				}
			}
		}
		
		// if dataCollection object is found, 
		if(dc != null){
//			DataEntry deItem = dataEntryPersistence.fetchByCollectionID_First(dc.getCollectionId(), null);
			List<DataEntry> deList = dataEntryPersistence.findByCollectionID(dc.getCollectionId());
			for(int i=0;i<deList.size();i++){
				DataEntry deItem = deList.get(i);
				
				DataEntryProvenance deProv = dataEntryProvenancePersistence.fetchByPrimaryKey(deItem.getEntryId());
				if(deItem.getSimulationSubjectId() == subjectId && deProv != null && deProv.getInputData().equals(inputData)){
	  				de = deItem;
	  				break;
	  			}
			}
		}
		return de;
	}
	
	/***
	 * Retrieve DataEntry objects whose collectionId matches a given collectionId
	 * @param collectionId DataCollectionID
	 * @return a list of map objects
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveDataEntryOnlyUsingMapByCollectionId(long collectionId) throws SystemException{
		// a list of DataEntry objects belonging to a DataCollection object matching collectId
		List<Map<String, Object>> dataEntryList = null;
		
		// gather all DataEntry objects in this DataCollection object
		List<DataEntry> deList = dataEntryPersistence.findByCollectionID(collectionId);
		if(deList != null){
			for(int i=0;i<deList.size();i++){
				if(dataEntryList == null){
					dataEntryList = new ArrayList<Map<String, Object>>();
					dataEntryList.add((deList.get(i)).getModelAttributes());
				}
			}
		}
		return dataEntryList;
	}

	/***
	 * Retrieve DataEntry objects whose collectionId matches a given collectionId
	 * @param collectionId DataCollectionID
	 * @return a list of map objects
	 * @throws SystemException
	 */
	public List<DataEntry> retrieveDataEntryOnlyByCollectionId(long collectionId) throws SystemException{
		// a list of DataEntry objects belonging to a DataCollection object matching collectId
		List<DataEntry> dataEntryList = dataEntryPersistence.findByCollectionID(collectionId);
		return dataEntryList;
	}
	
	/*****
	 * Add PROV structure assoacited with this data entry
	 * @param entryId data entry Id
	 * @param strPROV PROV spec
	 * @throws SystemException
	 */
	public void addPROVStructure(
			long entryId,
			String simulationUuid,
			long groupId,
			long userId,
			long scienceAppId,
			String scienceAppName,
			long jobSeqNo,
			String jobUuid,
			long jobStatusVal,
			String startDateStr,
			String endDateStr,
			long jobCompletion,
			String jobExecPath,
			long jobUniversityField,
			long jobInputDeckYnVal,
			long jobInputDeckNameVal,
			String jobDataStr
			) throws SystemException{
		
//		String strPROV = get
//		// find a data entry with a given entryId
//		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
//		if(dataEntry != null){
//			dataEntry.setProvStructure(strPROV);
//		}
	}
	
	/*****
	 * Search for matching DataEntry objects on a given target 
	 * @param entryId data entry Id
	 * @param strPROV PROV spec
	 * @throws SystemException
	 */
	public List<Map<String, Object>> searchPROVStructure(
			String searchTerm
			) throws SystemException{
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		// find a data entry with a given entryId
//		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
//		if(dataEntry != null){
//			dataEntry.setProvStructure(strPROV);
//		}
		
		// first gather all existing data entries
		List<DataEntry> dataEntryList = dataEntryPersistence.findAll();
		// then inject data entries into ElasticSearch
		resList = injectDataEntryListForSearch(searchTerm, dataEntryList);
		return resList;
	}

	private List<Map<String, Object>> injectDataEntryListForSearch(String searchTerm, List<DataEntry> dataEntryList) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		// connect to ElasticSearch+MySQL
		// retrieve matching data entries
		return resList;
	}
	
	/****
	 * Retrieve DataEntry objects by CollectionId
	 * @param collectionId
	 * @return List<Map<String, Object>>
	 * @throws SystemException
	 * @throws PortalException
	 */
	public List<Map<String, Object>> retrieveDataEntryByCollectionId(long collectionId) throws SystemException, PortalException{
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		
		List<DataEntry> dataEntryList = dataEntryPersistence.findByCollectionID(collectionId);
		for(int i=0;i<dataEntryList.size();i++){
			DataEntry dataEntry = dataEntryList.get(i);
			
			// collect model attributes not only from DataEntry but from DLFileEntry
			Map<String, Object> tempMap = dataEntry.getModelAttributes();
			// get DLFileEntryId
			long fileEntryId = dataEntry.getFileEntryId();
			// get a DLFileEntry matching this id
			DLFileEntry dlFileEntryItem = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
			if(dlFileEntryItem != null){
				tempMap.putAll(dlFileEntryItem.getModelAttributes());
				
			}
			// now we add this temporary map to that return list
			resList.add(tempMap);
					
		}
		return resList;
	}
	
	/****
	 * Retrieve DataEntry objects by EntryId
	 * @param collectionId
	 * @return List<Map<String, Object>>
	 * @throws SystemException
	 * @throws PortalException
	 */
	public Map<String, Object> retrieveDataEntryByEntryId(long entryId) throws SystemException, PortalException{
		// a map for return
		Map<String, Object> resMap = null;
		
		// get a DataEntry object matching entryId
		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
		if(dataEntry != null){
//		for(int i=0;i<dataEntryList.size();i++){
//			DataEntry dataEntry = dataEntryList.get(i);
//			
//			// collect model attributes not only from DataEntry but from DLFileEntry
			Map<String, Object> tempMap = dataEntry.getModelAttributes();
			// get DLFileEntryId
			long fileEntryId = dataEntry.getFileEntryId();
			// get a DLFileEntry matching this id
			DLFileEntry dlFileEntryItem = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
			if(dlFileEntryItem != null){
				tempMap.putAll(dlFileEntryItem.getModelAttributes());
				
			}
			// now we update this temporary map to that return map
			resMap = tempMap;
		}
		return resMap;
	}
	
	/****
	 * Increment download count by 1
	 * @param entryId a given DataEntry 
	 * @throws SystemException
	 */
	public void incrementDownloadCnt(long entryId) throws SystemException{
		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
		if(dataEntry != null){
			dataEntry.setDownloadCount(dataEntry.getDownloadCount()+1);
		}
		dataEntryPersistence.update(dataEntry);
	}
	
	/****
	 * Increment view count by 1
	 * @param entryId a given DataEntry 
	 * @throws SystemException
	 */
	public void incrementViewCnt(long entryId) throws SystemException{
		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
		if(dataEntry != null){
			dataEntry.setViewCount(dataEntry.getViewCount()+1);
		}
		dataEntryPersistence.update(dataEntry);
	}
	
	/****
	 * Remove DataEntry
	 * @param entryId a given DataEntry 
	 * @throws SystemException
	 * @throws PortalException 
	 */
	public void removeDataEntryByEntryId(long entryId) throws SystemException, PortalException{
		DataEntry dataEntry = dataEntryPersistence.fetchByPrimaryKey(entryId);
		if(dataEntry != null){
//			dataEntry.setViewCount(dataEntry.getViewCount()+1);
			// remove DLFileEntry first
			long fileEntryId = dataEntry.getFileEntryId();
			// remove a DLFileEntry matching this FileEntryId
//			DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
			EdisonFileUtil.deleteSingleEdisonFile(fileEntryId);
			dataEntryPersistence.remove(entryId);
		}
	}
	
//	@Override
//	public boolean createDataEntryObject(Long dcId, String scienceAppId, String inputData, String path, String comments,
//			String productionTime, ServiceContext sc) throws SystemException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public DataEntry findDataEntryObject(String dtTypeName, String dtTypeVersion, String dcCollectionName,
//			String dcCollectionVersion, String scienceAppId, String inputData) throws SystemException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	/**
	 * using method in Unified Search - GPLUS make categoryid array in method
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	private long[] makeArrayAssetCategory(long companyGroupId, long groupId) throws PortalException, SystemException {
		
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
		String categoryStr = "";
		if(parentGroupId != 0){ // 포탈이 아닐때 사이트의 카테고리를 long배열로 생성
  		AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
  				EdisonAssetCategory.GLOBAL_DOMAIN);
  
  		AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
  		List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId()); // 사이트에서
  																	// 사용중인
  																	// category
  
  		for (AssetCategory aCategory : aCategoryList) {
  			// 글로벌 도메인이면서 parentCategory값
  			if (aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory.getParentCategoryId() != 0) {
  				// parent 카테고리 아이디를 만듬.
  				if (categoryStr.equals("")) {
  
  					categoryStr += aCategory.getCategoryId();
  				} else {
  					categoryStr += "," + aCategory.getCategoryId();
  				}
  			}
  		}
		}
		return StringUtil.split(categoryStr, 0l);
	}
	
	/** - GPLUS
	 * Retrieve list of dataEntry by users (+by categories in site)
	 * admin can retrieve dataEntry of all Users
	 * @param companyGroupId
	 * @param groupId
	 * @param userId
	 * @param start
	 * @param end
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListDataEntry(long companyGroupId, long groupId, String searchText, long userId, int start, int end, Locale locale) 
		throws PortalException, SystemException{
		Map<String, Object> searchParam = new HashMap<String, Object>();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		
		if (categoryIds != null) searchParam.put("categoryIds", categoryIds);
		if(!searchText.equals("")) searchParam.put("searchText", searchText);
		if(userId != 0) searchParam.put("userId", userId);
		
		searchParam.put("begin", start);
		searchParam.put("end", end);
		
		List<DataEntry> dataEntryList = dataEntryFinder.retrieveListDataEntry(searchParam);
		
		List<Map<String, Object>> deList = new ArrayList<Map<String, Object>>();
		Map<String, Object> deMap = null;
		
		for(DataEntry deObject : dataEntryList){
			deMap = new HashMap<String, Object>();
			
			long collectionId = deObject.getCollectionId();
			long fileEntryId = deObject.getFileEntryId();
			
			deMap.put("entryId", deObject.getEntryId());
			deMap.put("comment", deObject.getComments(locale));
			deMap.put("simulationSubjectId", deObject.getSimulationSubjectId());
			deMap.put("viewCount", deObject.getViewCount());
			deMap.put("downloadCount", deObject.getDownloadCount());
			deMap.put("lastAccessedDate", deObject.getLastAccessedDate());
			
			DataEntryProvenance dataEntryProvenance = dataEntryProvenancePersistence.fetchByPrimaryKey(deObject.getEntryId());
			deMap.put("inputData", dataEntryProvenance.getInputData());
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = simpleDateFormat.format(deObject.getCreateDate());
			deMap.put("createDate", createDate);
			
			deMap.put("userId", deObject.getUserId());
			if(deObject.getUserId() != 0){
				User entryUser = UserLocalServiceUtil.getUser(deObject.getUserId());
				deMap.put("screenName", entryUser.getScreenName());
			}
			
			if(collectionId != 0){
				DataCollection dataCollection = dataCollectionLocalService.getDataCollection(collectionId);
				deMap.put("dataCollection", dataCollection);
			}
			
			if(fileEntryId != 0){
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(deObject.getFileEntryId());
				deMap.put("fileEntry", fileEntry);
			}
			
			deList.add(deMap);
		}
		
		return deList;
	}
	
	/****
	 * Copy result files of a completed job (simulation) from portal to IceCap server
	 * @param entryId
	 * @return server path
	 */
	public String copyFilesFromPortal(String collectionName,
									  String collectionVersion,
									  long entryId,
									  Map<String, String> fileLocMap  
									  ){
		String path = "/EDISON/IceCap/"+collectionName+"/"+collectionVersion+"/"+Long.toString(entryId)+"/";
		// ...
		return path;
	}
	
	
	/** - GPLUS
	 * Retrieve count of dataEntry by users (+by categories in site)
	 * admin can retrieve dataEntry count of all Users
	 * @param companyGroupId
	 * @param groupId
	 * @param userId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public int retrieveCountDataCollection(long companyGroupId, long groupId, String searchText, long userId) 
		throws PortalException, SystemException{
		Map<String, Object> searchParam = new HashMap<String, Object>();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		
		if (categoryIds != null) searchParam.put("categoryIds", categoryIds);
		if(!searchText.equals("")) searchParam.put("searchText", searchText);
		if(userId != 0)	searchParam.put("userId", userId);
		
		return dataEntryFinder.retrieveCountDataEntry(searchParam);
	}
}