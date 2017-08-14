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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.kisti.osp.icecap.NoSuchDataTypeException;
import com.kisti.osp.icecap.NoSuchDataTypeStructureException;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeAnalyzer;
import com.kisti.osp.icecap.model.DataTypeEditor;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.icecap.service.base.DataTypeLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the data type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icecap.service.DataTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataTypeLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataTypeLocalServiceUtil
 */
public class DataTypeLocalServiceImpl extends DataTypeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.osp.icecap.service.DataTypeLocalServiceUtil} to access the data type local service.
	 */
	public boolean addFavoriteToDataTypeObject(
			String dtName, 
			String dtVersion,
			boolean isFavorite,
			ServiceContext sc) throws SystemException{
		// get current time
		Date now = new Date();
		
		DataType dataType = null;
		dataType = super.dataTypePersistence.fetchByNameVersion(dtName, dtVersion);
		if(dataType == null){
			throw new SystemException("No matching data type object");
		}else{
			// set modified date 
			dataType.setModifiedDate(sc.getModifiedDate(now));
		}
		
		dataType.setIsFavorite(isFavorite);
		
		// set data type structure
//		DataTypeStructureServiceUtil.createDataTypeStructureObject(dataType.getTypeId(), dtStructure, sc);
		
		/* Let's update this data type row. */
		super.dataTypePersistence.update(dataType);
		return true;
	}
	
	
	public boolean addOwnerIdToDataTypeObject(
			String dtName, 
			String dtVersion,
			Long ownerId,
			ServiceContext sc) throws SystemException{
		// get current time
		Date now = new Date();
		
		DataType dataType = null;
		dataType = super.dataTypePersistence.fetchByNameVersion(dtName, dtVersion);
		if(dataType == null){
			throw new SystemException("No matching data type object");
		}else{
			// set modified date 
			dataType.setModifiedDate(sc.getModifiedDate(now));
		}
		
		dataType.setOwnerId(ownerId);
		
		// set data type structure
//		DataTypeStructureServiceUtil.createDataTypeStructureObject(dataType.getTypeId(), dtStructure, sc);
		
		/* Let's update this data type row. */
		super.dataTypePersistence.update(dataType);
		return true;
	}
	
	/****
	 * Create a new data type object
	 * @param dtName
	 * @param dtVersion
	 * @param dtDescription
	 * @param dtSampleFilePath
	 * @param dtStatus
	 * @param dtStructure
	 * @param ownerId
	 * @param sc Service Context
	 * @return
	 * @throws SystemException
	 */
	public boolean createDataTypeObject(
			String dtName, 
			String dtVersion,
			Map<Locale,String> dtDescription,
			String dtSampleFilePath,
			String dtStatus,
			String dtStructure,
			Long ownerId,
			boolean isFavorite,
//			String dtUserId,
//			String dtCompanyId,
//			String dtCreateDate,
//			String dtModifiedDate,
			ServiceContext sc) throws SystemException{
		// get current time
		Date now = new Date();
		
		Long typeId = null;
		DataType dataType = null;
		dataType = super.dataTypePersistence.fetchByNameVersion(dtName, dtVersion);
		if(dataType == null){
			typeId = super.counterLocalService.increment();
			// since this object hasn't been created before,
			// set createdate, companyid, userid, ...
			dataType = super.dataTypePersistence.create(typeId);
			dataType.setCreateDate(sc.getCreateDate(now));
			dataType.setUserId(sc.getUserId());
			dataType.setCompanyId(sc.getCompanyId());
		}else{
			// set modified date 
			dataType.setModifiedDate(sc.getModifiedDate(now));
		}
		
		int status = 0;
		if(dtStatus.equalsIgnoreCase("private")){
			status = 1;
		}else{
			status = 0; // public
		}
		
		// set status 
		dataType.setStatus(status);
		// set data type name
		dataType.setName(dtName);
		// set data type version
		dataType.setVersion(dtVersion);
		// set data type description
		dataType.setDescriptionMap(dtDescription);
		// set sample path
		dataType.setSamplePath(dtSampleFilePath);
		
//		copySampleDataTypeToServer(dtSampleFilePath);
		
		// set owner id
		dataType.setOwnerId(ownerId);
		System.out.println("owner id: " + ownerId);
//		addOwnerIdToDataTypeObject(dtName, dtVersion, ownerId, sc);
		
		// set favorite
		dataType.setIsFavorite(isFavorite);
//		addFavoriteToDataTypeObject(dtName, dtVersion, isFavorite, sc);
System.out.println("is favorite: " + isFavorite);
				
		// set data type structure
		DataTypeStructureLocalServiceUtil.createDataTypeStructureObject(dataType.getTypeId(), dtStructure);
		
		/* Let's update this data type row. */
		super.dataTypePersistence.update(dataType);
		return true;
	}
	
//	/***
//	 * Copy sample data type file to our server
//	 * @param dtSampleFilePath
//	 */
//	private void copySampleDataTypeToServer(String dtSampleFilePath) {
//		// TODO Auto-generated method stub
//		
//	}


	/****
	 * Find a data type object matching name+version
	 * @param dtName data type name
	 * @param dtVersion data type version
	 * @return data type object 
	 * @throws SystemException
	 */
	public DataType findDataTypeObject(
			String dtName, 
			String dtVersion) throws SystemException{
		/* Check an existing entry matching an empty record. */
		DataType existingDT = dataTypePersistence.fetchByNameVersion(dtName, dtVersion);
		if(existingDT == null){
			return null;
		}
		
		Long typeId = new Long(existingDT.getTypeId());
System.out.println("typeId:" + typeId);
System.out.println("data type name:" + dtName);
System.out.println("data type version:" + dtVersion);
		return existingDT;
	}
	
	/***
	 * 
	 */
	public void addDataTypeAnalyzer(
			String dtName, 
			String dtVersion,
			Long dataTypeAnalyzerId) throws SystemException{
		/* Check an existing entry matching an empty record. */
		DataType existingDT = dataTypePersistence.fetchByNameVersion(dtName, dtVersion);
		if(existingDT == null){
			return;
		}
		
		Long typeId = new Long(existingDT.getTypeId());
System.out.println("typeId:" + typeId);
		
//		DataTypeAnalyzer dataTypeAnalyzer = DataTypeAnalyzerServiceUtil.
	}
	
	/****
	 * Find a data type object matching name+version
	 * @param dataTypeId data type Id
	 * @return data type object 
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public DataType findDataTypeObject(
			Long dataTypeId) throws SystemException, NoSuchDataTypeException{
		/* Check an existing entry matching an empty record. */
		DataType existingDT = dataTypePersistence.findByTypeId(dataTypeId);
		if(existingDT == null){
			return null;
		}
		
		Long typeId = new Long(existingDT.getTypeId());
System.out.println("typeId:" + typeId);
		return existingDT;
	}
	
	/****
	 * Find a data type object associated with dataTypeId
	 * @param dataTypeId data type Id
	 * @return data type object 
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public Map<String, Object> retrieveDataTypeObject(
			Long dataTypeId) throws SystemException, NoSuchDataTypeException{
		/* Check an existing entry matching an empty record. */
		DataType existingDT = dataTypePersistence.findByTypeId(dataTypeId);
		if(existingDT == null){
			return null;
		}
		
		Long typeId = new Long(existingDT.getTypeId());
System.out.println("typeId:" + typeId);
		return existingDT.getModelAttributes();
	}
	
	/****
	 * Find a data type object associated with favorite value
	 * @param dtName data type name
	 * @param dtVersion data type version
	 * @return data type object 
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public List<DataType> findDataTypeObjectByFavorite(
			long groupId,
			boolean isFavorite) throws SystemException, NoSuchDataTypeException{
		/* Check an existing entry matching an empty record. */
		List<DataType> existingDTList = dataTypePersistence.findByG_Favorite(groupId, isFavorite);
		if(existingDTList == null){
			return null;
		}else{
			System.out.println("returned size:" + existingDTList.size());
		}
		
//		Long typeId = new Long(existingDT.getTypeId());
//System.out.println("typeId:" + typeId);
		return existingDTList;
	}
	
	/****
	 * 
	 * @param groupId
	 * @param ownerId
	 * @return
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public List<DataType> findDataTypeObjectByOwner(
			long groupId,
			long ownerId) throws SystemException, NoSuchDataTypeException{
		/* Check an existing entry matching an empty record. */
		List<DataType> existingDTList = dataTypePersistence.findByG_OwnerId(groupId, ownerId);
		if(existingDTList == null){
			return null;
		}else{
			System.out.println("returned size:" + existingDTList.size());
		}
		
//		Long typeId = new Long(existingDT.getTypeId());
//System.out.println("typeId:" + typeId);
		return existingDTList;
	}
	
	/****
	 * Perform data type validation - GPLUS
	 * @param typeId 
	 * @param version
	 * @return true or false
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public boolean checkDataTypeObjectValiation(String name,String version) throws SystemException{
		boolean res = false;
		try{
			DataType dataType = dataTypePersistence.findByNameVersion(name, version);
			res = true;
		}catch(NoSuchDataTypeException e){
			
		}finally {
			return res;
		}
	}
	
	/****
	 * 
	 * @param typeId
	 * @param description
	 * @param name
	 * @param version
	 * @param ownerId
	 * @param isFavorite
	 * @param samplePath
	 * @param status
	 * @param sc
	 * @return
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public Map<String,Object> modifyDataTypeObject(
			// a field on which a DataType object is fetch
			long typeId,
			// a list of fields that is modifable
			String name, // DataType name
			String version, // DataType version
			Map<Locale,String> description, // DataType description
			String samplePath, // DataType sample path
			Integer status, // DataType (public/private) status 
			String structure, //DataTypeStructure
			Long ownerId, // DataType ownerId
			Boolean isFavorite, // DataType favorite
			ServiceContext sc // ServiceContext
		) throws SystemException, NoSuchDataTypeException{
		// a map to be returned with filled pairs of attribute/value
		Map<String, Object> resDataTypeMap = null;
		// fetch a DataType object by typeId
		DataType targetDataTypeObject = dataTypePersistence.fetchByTypeId(typeId);
		if(targetDataTypeObject == null){
			throw new NoSuchDataTypeException("No matching DataType object");
		}
		
		if(name != null) // modify name
			targetDataTypeObject.setName(name);
		if(version != null)	// modify version
			targetDataTypeObject.setVersion(version);
		if(samplePath != null) // modify sample path
			targetDataTypeObject.setSamplePath(samplePath);
		if(status != null) // modify status
			targetDataTypeObject.setStatus(status);

		targetDataTypeObject.setDescriptionMap(description);
		
		// if DataTypeStructure needs updating
		if(structure != null){
			DataTypeStructure dtsObj = null;
			try {
				// get DataTypeStructure
				dtsObj = DataTypeStructureLocalServiceUtil.getDataTypeStructure(typeId);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				throw new SystemException(e.getMessage());
			}
			// let's update DataTypeStructure
			dtsObj.setStructure(structure);
		}
		
		// if ownerId is assigned
		if(ownerId != null)
			targetDataTypeObject.setOwnerId(ownerId);
		// if favoriteField is set 
		if(isFavorite != null)
			targetDataTypeObject.setIsFavorite(isFavorite);
		
		// get current time
		Date now = new Date();
		targetDataTypeObject.setModifiedDate(sc.getModifiedDate(now));

		/* Let's update this modified data type row. */
		DataType resDataTypeObj = super.dataTypePersistence.update(targetDataTypeObject);
		/* Get Map<String, Object> contents */
		resDataTypeMap = resDataTypeObj.getModelAttributes();
		return resDataTypeMap;
	}
	
	/*****
	 * Modify a DataType object to add or remove DataTypeEditor and/or DataTypeAnalyzer
	 * @param dtName DataType name
	 * @param dtVersion DataType version
	 * @param editorList a list of DataTypeEditor objects
	 * @param analyzerList a list of DataTypeAnalyzer objects
	 * @param sc ServiceContext
	 * @return typeId
	 * @throws SystemException
	 */
	public Long modifyDataTypeObjectForEditorAnalyzer(
			String dtName, 
			String dtVersion,
			Map<Locale,String> description,
			List<Map<String,Object>> editorList,
			List<Map<String,Object>> analyzerList,
			ServiceContext sc) throws SystemException{
		
		//must Variable Check
		if(GetterUtil.getString(dtName).equals("")||GetterUtil.getString(dtVersion).equals("")){
			throw new SystemException("checke Must Variable");	
		}
		
		// DataType object
		DataType dataTypeObject = null;
		long resTypeId = 0;
		
		String modifyMode = Constants.ADD;
		try {
			dataTypeObject = dataTypePersistence.findByNameVersion(dtName, dtVersion);
			resTypeId = dataTypeObject.getTypeId();
			modifyMode = Constants.UPDATE;
			dataTypeObject.setModifiedDate(new Date());
		} catch (NoSuchDataTypeException e) {
			resTypeId = super.counterLocalService.increment();
			// since this object hasn't been created before,
			// set createdate, companyid, userid, ...
			dataTypeObject = super.dataTypePersistence.create(resTypeId);
			dataTypeObject.setName(dtName);
			dataTypeObject.setVersion(dtVersion);
			dataTypeObject.setCompanyId(sc.getCompanyId());
			dataTypeObject.setUserId(sc.getUserId());
			dataTypeObject.setCreateDate(new Date());
			dataTypeObject.setSamplePath("0");
			dataTypeObject.setStatus(0);
			dataTypeObject.setGroupId(sc.getScopeGroupId());
			dataTypeObject.setIsFavorite(false);
			dataTypeObject.setOwnerId(sc.getUserId());
		}
		dataTypeObject.setDescriptionMap(description);
		dataTypePersistence.update(dataTypeObject);
		
		if(modifyMode.equals(Constants.UPDATE)){
			//delete DataTypeEditor
			dataTypeEditorPersistence.removeByTypeID(resTypeId);
			
			//delete DataTypeAnalyzer
			dataTypeAnalyzerPersistence.removeByTypeID(resTypeId);
		}
		
		for(int i=0;i<editorList.size();i++){
			Map<String, Object> editorContents = (Map<String, Object>)editorList.get(i);
			Long editorId = GetterUtil.getLong(editorContents.get("editorId"),0);
			Boolean isDefault = GetterUtil.getBoolean(editorContents.get("isDefault"),false);
			DataTypeEditorLocalServiceUtil.createDataTypeEditorObject(resTypeId, isDefault, editorId);
		}
		
		for(int i=0;i<analyzerList.size();i++){
			Map<String, Object> analyzerContents = (Map<String, Object>)analyzerList.get(i);
			Long analyzerId = GetterUtil.getLong(analyzerContents.get("analyzerId"),0);
			Boolean isDefault = GetterUtil.getBoolean(analyzerContents.get("isDefault"),false);
			DataTypeAnalyzerLocalServiceUtil.createDataTypeAnalyzerObject(resTypeId, isDefault, analyzerId);
		}
		
		return resTypeId;
	}
				
	/****
	 * Retrieve matching a list of DataType objects - GPLUS
	 * @param name data type name
	 * @return a list of Map<String, Object>
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public List<Map<String, Object>> retrieveDataTypeObjects(
			String name, Object isFavorite, long ownerId,int begin, int end) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataType.class);
		
		if(!GetterUtil.getString(name).equals("")){
			query.add(RestrictionsFactoryUtil.like("name", "%" + name + "%"));
		}
		if(isFavorite != null){
			query.add(RestrictionsFactoryUtil.eq("isFavorite", (GetterUtil.getBoolean(isFavorite,false))));
		}
		long checkOwnerId = GetterUtil.getLong(ownerId,0);
		if(checkOwnerId!=0){
			query.add(RestrictionsFactoryUtil.eq("ownerId", ownerId));
		}
		
		query.addOrder(OrderFactoryUtil.asc("name"));
		
		// a map to be returned with filled pairs of attribute - value
		List<Map<String, Object>> resDataTypeMapList = new ArrayList<Map<String, Object>>();
		
		// fetch a DataType object by typeId
		List<DataType> dataTypeObjList = dataTypeLocalService.dynamicQuery(query, begin, end);
		for(DataType dataType:dataTypeObjList){
			resDataTypeMapList.add(dataType.getModelAttributes());
		}
		return resDataTypeMapList;
	}
	
	/****
	 * Retrieve matching a count of DataType objects - GPLUS
	 * @param name data type name
	 * @return Integer
	 * @throws SystemException
	 */
	public int retrieveDataTypeCount(String name, Object isFavorite, long ownerId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataType.class);
		
		if(!GetterUtil.getString(name).equals("")){
			query.add(RestrictionsFactoryUtil.like("name", "%" + name + "%"));
		}
		if(isFavorite != null){
			query.add(RestrictionsFactoryUtil.eq("isFavorite", (GetterUtil.getBoolean(isFavorite,false))));
		}
		long checkOwnerId = GetterUtil.getLong(ownerId,0);
		if(checkOwnerId!=0){
			query.add(RestrictionsFactoryUtil.eq("ownerId", ownerId));
		}
		
		int dataTypeCount = GetterUtil.getInteger(dataTypePersistence.countWithDynamicQuery(query),0); 
		return dataTypeCount;
	}
	
	
	
	/***
	 * Remove a DataType object matching typeId
	 * @param typeId
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public void removeDataTypeObject(long typeId) throws SystemException,PortalException{
		// implementation of cascading deletion
		DataType dataType = dataTypePersistence.findByTypeId(typeId);
		
		//delete DataTypeEditor
		dataTypeEditorPersistence.removeByTypeID(typeId);
		
		//delete DataTypeAnalyzer
		dataTypeAnalyzerPersistence.removeByTypeID(typeId);
		
		//delete DataTypeStructure
		try {
			dataTypeStructurePersistence.remove(typeId);
		} catch (NoSuchDataTypeStructureException e) {
			
		}
		
		//delete SampleFile
		long fileEntryId = GetterUtil.getLong(dataType.getSamplePath(),0);
		if(fileEntryId!=0){
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
			long groupId = dataType.getGroupId()==0?dlFileEntry.getGroupId():dataType.getGroupId();
			String folderSeq = groupId + "_" + typeId;
			EdisonFileUtil.deleteGroupEdisonFile(groupId, EdisonFileConstants.PORT_TYPE, folderSeq);
		}
		//delete DataType
		dataTypePersistence.remove(dataType.getTypeId());
	}
	
	/****
	 * Retrieve view counts associated with this DataType object
	 * @param typeId a given typeId
	 * @return
	 * @throws SystemException
	 * @throws NoSuchDataTypeException
	 */
	public Map<String, Object> retrieveViewCount(long typeId
		) throws SystemException, NoSuchDataTypeException{
		Map<String, Object> dataTypeObjMap = new HashMap<String, Object>();
		
		String numEditorStr = "numEditors";
//		int editorCnt = 0;
		
		String numAnalyzerStr = "numAnalyzers";
//		int analyzerCnt = 0;
		
		String structureExistenceStr = "isStructurePresent";
		boolean structurePresence = false;
		
		// fetch a DataType object by typeId
//		DataType targetDataTypeObject = dataTypePersistence.fetchByTypeId(typeId);
//		if(targetDataTypeObject == null){
//			throw new NoSuchDataTypeException("No matching DataType object");
//		}
			
		// get editor counts 
//		List<DataTypeEditor> dteList = dataTypeEditorPersistence.findByTypeID(typeId);
//		if(dteList != null)
//			editorCnt = dteList.size();
		dataTypeObjMap.put(numEditorStr, dataTypeEditorPersistence.countByTypeID(typeId));
		
		// get analyzer counts 
//		List<DataTypeAnalyzer> dtaList = dataTypeAnalyzerPersistence.findByTypeID(typeId);
//		if(dtaList != null)
//			editorCnt = dtaList.size();
		dataTypeObjMap.put(numAnalyzerStr, dataTypeAnalyzerPersistence.countByTypeID(typeId));
		
		// get the existence of a DataTypeStructure object
		DataTypeStructure dts = dataTypeStructurePersistence.fetchByTypeID(typeId);
		if(dts != null){
			String structure = dts.getStructure();
			if(structure != null && !structure.equalsIgnoreCase("")){
				structurePresence = true;
			}
		}
		dataTypeObjMap.put(structureExistenceStr, new Boolean(structurePresence));
		
		return dataTypeObjMap;
	}
	
	/**
	 *  Copy DataTypeEntry  - GPLUS
	 * @param typeId
	 * @param userId
	 * @param sc
	 * @return new DataTypeEntry Object
	 * @throws SystemException
	 * @throws PortalException
	 * @throws IOException
	 */
	public Map<String,Object> copyDataType(long typeId,long userId,ServiceContext sc) throws SystemException, PortalException{
		//Search DataType
		DataType dataType = dataTypePersistence.fetchByPrimaryKey(typeId);
		long newTypeId = CounterLocalServiceUtil.increment(DataType.class.getName());
		
		DataType newDataType = (DataType) dataType.clone();
		
		
		
		// Create New DataType Version
		String preVersion = dataType.getVersion();
		String[] versions = preVersion.split("\\.");
		int lastVersions = (Integer.parseInt(versions[0])) + 1;
		String newVersion = lastVersions + "." + versions[1] + "." + versions[2];

		while1: while(true){
			boolean isDuplicated = checkDataTypeObjectValiation(dataType.getName(), newVersion);
			if(!isDuplicated){
				newDataType.setVersion(newVersion);
				break while1;
			}else{
				lastVersions += 1;
				newVersion = lastVersions + "." + versions[1] + "." + versions[2];
			}
		}
		
		newDataType.setUuid(PortalUUIDUtil.generate());
		newDataType.setTypeId(newTypeId);
		newDataType.setUserId(userId);
		newDataType.setOwnerId(userId);
		newDataType.setCreateDate(new Date());
		newDataType.setIsFavorite(false);
		newDataType.setGroupId(dataType.getGroupId()==0?sc.getScopeGroupId():dataType.getGroupId());
		
		//copy samplefile
		if(!newDataType.getSamplePath().equals("0")){
			int sampeFileId = GetterUtil.getInteger(newDataType.getSamplePath());
			long fileEntryId;
			try {
				fileEntryId = EdisonFileUtil.copyDlFile(sc.getLiferayPortletRequest(), sampeFileId, "", String.valueOf(newTypeId), EdisonFileConstants.PORT_TYPE);
				newDataType.setSamplePath(String.valueOf(fileEntryId));
			} catch (IOException e) {
				new PortalException(e);
			}
		}else{
			newDataType.setSamplePath("0");
		}
		
		//DataType
		dataTypePersistence.update(newDataType);
		
		Map<String, Object> viewMap = retrieveViewCount(typeId);
		
		//copy DataTypeEditor
		if(GetterUtil.getInteger(viewMap.get("numEditors"),0)!=0){
			List<DataTypeEditor> editorList = dataTypeEditorPersistence.findByTypeID(typeId);
			for(DataTypeEditor dataTypeEditor:editorList){
				dataTypeEditor.setTypeId(newTypeId);
				dataTypeEditorPersistence.update(dataTypeEditor);
			}
		}
		
		//copy DataTypeAnalyzer
		if(GetterUtil.getInteger(viewMap.get("numAnalyzers"),0)!=0){
			List<DataTypeAnalyzer> analyzerList = dataTypeAnalyzerPersistence.findByTypeID(typeId);
			for(DataTypeAnalyzer dataTypeAnalyzer:analyzerList){
				dataTypeAnalyzer.setTypeId(newTypeId);
				dataTypeAnalyzerPersistence.update(dataTypeAnalyzer);
			}
		}
		
		//copy DataTypeStructure
		if(GetterUtil.getBoolean(viewMap.get("isStructurePresent"),false)){
			DataTypeStructure dataTypeStructure = DataTypeStructureLocalServiceUtil.getDataTypeStructure(typeId);
			dataTypeStructure.setTypeId(newTypeId);
			dataTypeStructurePersistence.update(dataTypeStructure);
		}
		
		return newDataType.getModelAttributes();
	}
	
	/**
	 * SampleFile Upload & Update DataType - GPLUS
	 * @param typeId
	 * @param sc
	 * @param upload
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public Map<String,Object> addSampleFilePath(long typeId, ServiceContext sc,UploadPortletRequest upload) throws SystemException, PortalException{
		DataType dataType = dataTypePersistence.fetchByTypeId(typeId);
		String name = String.valueOf(typeId);
		FileEntry sampeFileEntry = null;
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			long fileEntryId = GetterUtil.getLong(dataType.getSamplePath(),0);
			
			if(fileEntryId!=0){
				//기존 파일 삭제
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
			}
			
			List<FileEntry> sampleFileList = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc.getUserId(), sc.getScopeGroupId(), "", name, "sampleFile", EdisonFileConstants.PORT_TYPE);
			sampeFileEntry = sampleFileList.get(0);
			returnMap.put("fileEntryId", sampeFileEntry.getFileEntryId());
			returnMap.put("title", sampeFileEntry.getTitle());
			
			
			dataType.setSamplePath(String.valueOf(sampeFileEntry.getFileEntryId()));
			dataType.setModifiedDate(new Date());
			dataTypePersistence.update(dataType);
		} catch (SQLException e) {
			e.printStackTrace();
			new PortalException(e);
		} catch (IOException e) {
			e.printStackTrace();
			new PortalException(e);
		}
		
		return returnMap;
	}
}