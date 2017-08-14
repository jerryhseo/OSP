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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;
import com.kisti.osp.icecap.service.base.DataCollectionLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the data collection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.kisti.osp.icecap.service.DataCollectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataCollectionLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil
 */
public class DataCollectionLocalServiceImpl extends DataCollectionLocalServiceBaseImpl {
	/* 
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil} to access
	 * the data collection local service.
	 */
	public boolean createDataCollectionObject(Long dtId, // data type id
			String dcName, String dcVersion, Map<Locale, String> titleMap/*String dcTitle*/, Map<Locale, String> descriptionMap /*String dcDescription*/, String dcAccessMethod,
			String dcStatus, String dcTargetLanguage, long[] categoryIds, long companyGroupId, ServiceContext sc) throws SystemException, PortalException{
		// get current time
		Date now = new Date();

		Long collectionId = null;
		DataCollection dataCollection = null;
		List<DataCollection> dataCollectionList = super.dataCollectionPersistence.findByName(dcName);
		for (int i = 0; i < dataCollectionList.size(); i++) {
			DataCollection dc = dataCollectionList.get(i);
			if (dc.getVersion().equals(dcVersion) && dc.getTypeId() == dtId.longValue()) {
				System.out.println("Found a matching data type!");
				// hit!
				dataCollection = dc;
				break;
			}
		}

		// if(dataCollection == null) throw new SystemException("A matching data
		// type does not exist...");
		if (dataCollection == null) {
			System.out.println("Creating a new data collection object...");
			collectionId = super.counterLocalService.increment();
			// since this object hasn't been created before,
			// set createdate, companyid, userid, ...
			dataCollection = super.dataCollectionPersistence.create(collectionId);
			dataCollection.setTypeId(dtId);
			dataCollection.setCreateDate(sc.getCreateDate(now));
			
			long userId = sc.getUserId();
			long groupId = sc.getScopeGroupId();
			dataCollection.setUserId(userId);
			dataCollection.setGroupId(groupId); // correct?
			dataCollection.setCompanyId(sc.getCompanyId());
			
			// create EDISON DLFolder
			DLFolder dlFolder = EdisonFileUtil.createEdisonDLFolder(sc.getLiferayPortletRequest(), 
					userId, 
					groupId, 
					Long.toString(collectionId), 
					EdisonFileConstants.DATA_COLLECTION);
			System.out.println(dlFolder);
			if(dlFolder != null)
				dataCollection.setFolderId(dlFolder.getFolderId());
			else
				throw new SystemException("A DLFolder cannot be created.");
			// Asset entry 등록
			createDataCollectionToAssetEntry(dataCollection, categoryIds);
//			updateDataCollectionAssetCategories(collectionId, categoryIds);
//			// asset AssetCategory 등록 
//			makeArrayAssetCategory(companyGroupId, groupId);
			
		} else {
			// set modified date
			dataCollection.setModifiedDate(sc.getModifiedDate(now));
			
			DataCollection dcItem = super.dataCollectionPersistence.findByT_NameVersion(dtId, dcName, dcVersion);
			collectionId = dcItem.getCollectionId();
			// update asset category
			updateDataCollectionAssetCateories(collectionId, categoryIds);
		}

		int status = 0;
		if (dcStatus.equalsIgnoreCase("private")) {
			status = 1;
		} else {
			status = 0; // public
		}

		// set status
		dataCollection.setStatus(status);
		// set data collection name
		dataCollection.setName(dcName);
		// set data collection version
		dataCollection.setVersion(dcVersion);
		// set data collection title
//		dataCollection.setTitle(dcTitle);
//		Map<Locale, String> titleMap = new HashMap<Locale, String>();
//		titleMap.put(, value)
		dataCollection.setTitleMap(titleMap);
		// set data collection description
//		dataCollection.setDescription(dcDescription);
		dataCollection.setDescriptionMap(descriptionMap);
		// set data access method
		dataCollection.setAccessMethod(dcAccessMethod);
		// set data target language
		dataCollection.setTargetLanguage(dcTargetLanguage);
		// add DLFoler Id
//		dataCollection.setFolderId(dcFolderId);


		/*dl file upload - icon, mainImage*/
		
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
		
		
		//create dlfileEntry of icons 
		String dcIconStr = CustomUtil.strNull(upload.getFileName("dc_icon"), "");
		if(!dcIconStr.equals("")){
			List iconList = EdisonFileUtil.getListEdisonFile(dataCollection.getGroupId(), "icon", String.valueOf(
				dataCollection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);

			if(iconList != null && iconList.size() > 0){
				for(int i = 0; i < iconList.size(); i++){
					Map fileMap = (Map) iconList.get(i);
					DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.parseLong(CustomUtil.strNull(fileMap.get(
						"fileEntryId"))));
				}
			}

			try {
				EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, dataCollection.getUserId(),
					dataCollection.getGroupId(), "icon", String.valueOf(dataCollection.getCollectionId()), "dc_icon",
					EdisonFileConstants.DATA_COLLECTION);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				throw new SystemException();
			}
		}

		// create dlfileEntry of main Images
		// String dcMainImgStr =
		// CustomUtil.strNull(upload.getFileName("dc_mainImg"), "");

		String[] dcMainImgStrArr = upload.getFileNames("dc_mainImg");
		if(dcMainImgStrArr != null){ // 이미지가 있는경우
			List mainImgList = EdisonFileUtil.getListEdisonFile(dataCollection.getGroupId(), "mainImg", String
				.valueOf(dataCollection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);

			/*if(mainImgList != null && mainImgList.size() > 0){
				for(int i = 0; i < mainImgList.size(); i++){
					Map fileMap = (Map) mainImgList.get(i);
					DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.parseLong(CustomUtil.strNull(fileMap.get(
						"fileEntryId"))));
				}
			}*/

			try {
				EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, dataCollection.getUserId(),
					dataCollection.getGroupId(), "mainImg", String.valueOf(dataCollection.getCollectionId()),
					"dc_mainImg", EdisonFileConstants.DATA_COLLECTION);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				throw new SystemException();
			}
		}
		
		/* Let's update this data collection row. */
		super.dataCollectionPersistence.update(dataCollection);
		return true;
	}

	/****
	 * Find an id of a DataType object matching name+version
	 * 
	 * @param dtId
	 *            data type id
	 * @param dcName
	 *            data collection name
	 * @param dcVersion
	 *            data collection version
	 * @return data collection object
	 * @throws SystemException
	 */
	public Map<String, Object> findDataCollectionObject(Long dtId, String dcName, String dcVersion)
			throws SystemException {
		/* Check an existing entry matching an empty record. */
		// DataType existingDT = dataTypePersistence.fetchByNameVersion(dtName,
		// dtVersion);
		// Long typeId = new Long(existingDT.getTypeId());
		// System.out.println("typeId:" + typeId);
		// System.out.println("data type name:" + dtName);
		// System.out.println("data type version:" + dtVersion);
		Map<String, Object> resMap = null;
		DataCollection dc = null;
		List<DataCollection> dcItemList = dataCollectionPersistence.findByTypeID(dtId);
		// List<DataCollection> dcList =
		// dataCollectionPersistence.fetchByTypeID_First(dtId, null);
		for (int i = 0; i < dcItemList.size(); i++) {
			DataCollection dcItem = dcItemList.get(i);
			if (dcItem.getName().equals(dcName) && dcItem.getVersion().equals(dcVersion)) {
				dc = dcItem;
				break;
			}
		}
		if (dc != null){
			resMap = dc.getModelAttributes();
		}
		return resMap;
	}

	/****
	 * Validate a DataCollection object matching a given name and a given
	 * version
	 * 
	 * @param name
	 *            a given DataCollection name
	 * @param version
	 *            a given DataCollection version
	 * @return true or false
	 * @throws SystemException
	 */
	public boolean checkDataCollectionValidity(String name, String version) throws SystemException {
		List<DataCollection> dcItemList = dataCollectionPersistence.findByNameVersion(name, version);
		for (int i = 0; i < dcItemList.size(); i++) {
			DataCollection dcItem = dcItemList.get(i);
			if (dcItem.getName().equals(name) && dcItem.getVersion().equals(version)) {
				return true;
			}
		}
		return false;
	}

	/****
	 * Retrieve a set of DataCollection objects whose title partially contains
	 * or fully matching searchText
	 * 
	 * @param searchText
	 *            search text
	 * @param start
	 *            start
	 * @param end
	 *            end
	 * @return a set of matching DataCollection objects
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveDataCollectionObjects(String searchText, Locale locale, int start, int end)
			throws SystemException {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		// List<DataCollection> dcItemList =
		// dataCollectionPersistence.findAll(start, end);
		// if(dcItemList != null){
		// for(int i=0;i<dcItemList.size();i++){
		// DataCollection dcItem = dcItemList.get(i);
		// if(dcItem.getTitle().contains(searchText)){
		// resList.add(dcItem.getModelAttributes());
		// }
		// }
		// }
		String attributeOnSearch = "name";
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);
		if (!GetterUtil.getString(searchText).equals("")) {
			query.add(RestrictionsFactoryUtil.like(attributeOnSearch, "%" + searchText + "%"));
		}
		
		/*add Conditions*/
		query.add(RestrictionsFactoryUtil.eq("status", 0)); //public status
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		/*add Conditions End*/

		
		
		query.addOrder(OrderFactoryUtil.asc("name"));
		
		// fetch a DataCollection object by searchText
		List<DataCollection> dcItemList = dataCollectionLocalService.dynamicQuery(query, start, end);
		if (dcItemList != null) {
			for (int i = 0; i < dcItemList.size(); i++) {
				DataCollection dcItem = dcItemList.get(i);
				// if(dcItem.getTitle().contains(searchText)){
				resList.add(dcItem.getModelAttributes());
				// }
			}
		}
		return resList;
	}

	/****
	 * Retrieve a set of DataCollection objects whose title partially contains
	 * or fully matching searchText
	 * 
	 * @param searchText
	 *            search text
	 * @param start
	 *            start
	 * @param end
	 *            end
	 * @return a set of matching DataCollection objects
	 * @throws SystemException
	 */
	public int countDataCollectionObjects(String searchText, Locale locale) throws SystemException {
		int resCnt = 0;
		String attributeOnSearch = "name";
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);

		if (!GetterUtil.getString(searchText).equals("")) {
			query.add(RestrictionsFactoryUtil.like(attributeOnSearch, "%" + searchText + "%"));
		}
		/*add Conditions*/
		query.add(RestrictionsFactoryUtil.eq("status", 0)); //public status
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		/*add Conditions End*/
		
		query.addOrder(OrderFactoryUtil.asc("name"));

		// fetch a DataCollection object by searchText
		resCnt = (int) dataCollectionLocalService.dynamicQueryCount(query);
		return resCnt;
	}

	/****
	 * Retrieve a set of DataCollection objects by a given array of
	 * CollectionIds
	 * 
	 * @param collectionIdArray
	 *            an array of CollectionId
	 * @param start
	 *            start
	 * @param end
	 *            end
	 * @return a set of matching DataCollection objects
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveDataCollectionObjectsByCollectId(Object[] collectionIdArray, int start,
			int end, Locale locale) throws SystemException {
		// a list to be returned with matching DataCollection objects
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);
		query.add(RestrictionsFactoryUtil.in("collectionId", collectionIdArray));
		query.add(RestrictionsFactoryUtil.eq("status", 0)); //public status
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		query.setLimit(start, start+end);
		
	// fetch a DataCollection object by searchText
		List<DataCollection> collectionList = dataCollectionLocalService.dynamicQuery(query);
		for(DataCollection collection : collectionList){
			resList.add(collection.getModelAttributes());
		}
		return resList;
	}

	/****
	 * Retrieve a set of DataCollection objects by a given array of
	 * CollectionIds
	 * 
	 * @param collectionIdArray
	 *            an array of CollectionId
	 * @return a set of matching DataCollection objects
	 * @throws SystemException
	 * @throws PortalException
	 */
	public List<Map<String, Object>> retrieveDataCollectionObjectsByCollectId(Object[] collectionIdArray, Locale locale)
			throws SystemException, PortalException {
		// a list to be returned with matching DataCollection objects
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);
		query.add(RestrictionsFactoryUtil.in("collectionId", collectionIdArray));
		query.add(RestrictionsFactoryUtil.eq("status", 0)); //public status
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		
	// fetch a DataCollection object by searchText
		List<DataCollection> collectionList = dataCollectionLocalService.dynamicQuery(query);
		for(DataCollection collection : collectionList){
			resList.add(collection.getModelAttributes());
		}
		return resList;
	}

	/****
	 * Count a set of DataCollection objects by a given array of CollectionIds
	 * 
	 * @param collectionIdArray
	 *            an array of CollectionId
	 * @return a set of matching DataCollection objects
	 * @throws SystemException
	 * @throws PortalException
	 */
	public int countDataCollectionObjectsByCollectId(Object[] collectionIdArray, Locale locale)
			throws SystemException, PortalException {
		// iterate a given CollectionId
		List<Map<String, Object>> resList = retrieveDataCollectionObjectsByCollectId(collectionIdArray, locale);
		if (resList != null)
			return resList.size();
		else
			return 0;
	}

	/***
	 * Remove DataCollection objects whose collection Id matches a given collectionId
	 */
	public void removeDataCollectionByCollectionId(long collectionId) throws SystemException, PortalException{
		DataCollection dataCollection = dataCollectionPersistence.findByPrimaryKey(collectionId);
		if(dataCollection != null){
			//List<DataEntry> dataEntryList = DataEntryLocalServiceUtil.retrieveDataEntryOnlyByCollectionId(collectionId);
			
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(), dataCollection.getCollectionId());
			if(assetEntry != null){
				long entryId = assetEntry.getEntryId(); //asset entry id
				if(entryId != 0){
  				AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId); //delete asset categories
  				AssetLinkLocalServiceUtil.deleteLinks(entryId);//Asset link table deletion
  				AssetEntryLocalServiceUtil.deleteAssetEntry(entryId);//delete asset entry
				}
			}
			
			List<Map<String,Object>> dataEntryUsedCollectionList = DataEntryLocalServiceUtil.retrieveDataEntryByCollectionId(collectionId);
			for(Map<String,Object> dataEntryMap : dataEntryUsedCollectionList){
				long dataEntryId = GetterUtil.getLong(dataEntryMap.get("entryId"));
				DataEntryLocalServiceUtil.removeDataEntryByEntryId(dataEntryId);// now delete this DataEntry object	
			}
			
			//delete dl folder/file 
			deleteDataCollectionDLFolder(dataCollection.getGroupId(), collectionId);
			
			dataCollectionPersistence.remove(collectionId);
			
		}
	}

	/**
	 * create Asset Entry of data collection (with AssetCategory) - GPLUS
	 * 
	 * @param dcObj
	 *            - to create Asset Entry
	 * @param categoryIds
	 *            - link with assetEntry of and assetCategories
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	private AssetEntry createDataCollectionToAssetEntry(DataCollection dcObj, long[] categoryIds)
			throws PortalException, SystemException {

		long classTypeId = 0;
		boolean visible = true;
		Date startDate = null;
		Date endDate = null;
		Date expirationDate = null;
		String mimeType = ContentTypes.TEXT_HTML;
		String description = null;
		String summary = null;
		String url = null;
		String layoutUuid = null;
		int height = 0;
		int width = 0;
		Integer priority = null;
		boolean sync = false;

		AssetEntry collectionAssetEntry = assetEntryLocalService.updateEntry(dcObj.getUserId(), dcObj.getGroupId(),
				dcObj.getCreateDate(), dcObj.getModifiedDate(), DataCollection.class.getName(), dcObj.getCollectionId(),
				dcObj.getUserUuid(), classTypeId, categoryIds, null, visible, startDate, endDate, expirationDate,
				mimeType, dcObj.getName(), description, summary, url, layoutUuid, height, width, priority, sync);

		return collectionAssetEntry;
	}

	/**
	 * link with assetEntry of data collection and assetCategories - GPLUS Used
	 * when updating categories
	 * 
	 * @param collectionId
	 *            - search for AssetEntry of collection
	 * @param categoryIds
	 * @throws PortalException
	 * @throws SystemException
	 */
	private void updateDataCollectionAssetCateories(long collectionId, long[] categoryIds)
			throws PortalException, SystemException {
		
		AssetEntry collectionAssetEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(),
				collectionId);

		if (collectionAssetEntry != null) {
			// linked categories clear
			AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(collectionAssetEntry.getEntryId());

			if (categoryIds != null) {
				AssetCategoryLocalServiceUtil.addAssetEntryAssetCategories(collectionAssetEntry.getEntryId(),
						categoryIds);
			}
		}
	}

	/**
	 * delete folder and files of selected collection - GPLUS
	 * 
	 * @param groupId
	 * @param collectionId
	 * @throws PortalException
	 * @throws SystemException
	 */
	private void deleteDataCollectionDLFolder(long groupId, long collectionId) throws PortalException, SystemException {
		EdisonFileUtil.deleteGroupEdisonFile(groupId, EdisonFileConstants.DATA_COLLECTION,
				groupId + "_" + collectionId);
	}

	/**
	 * 
	 * @param dcName
	 * @return
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListDataCollectionHistory(String dcName, String dcVersion, Locale locale) throws SystemException{
		
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);
		query.add(RestrictionsFactoryUtil.eq("name", dcName));
		query.add(RestrictionsFactoryUtil.eq("status", 0)); //public status
		query.add(RestrictionsFactoryUtil.like("targetLanguage", "%" + LocaleUtil.toLanguageId(locale) + "%"));
		query.addOrder(OrderFactoryUtil.asc("name"));
		
		// fetch a DataCollection object by searchText
		List<DataCollection> collectionList = dataCollectionLocalService.dynamicQuery(query);
		
		List<Map<String, Object>> historyList = new ArrayList<Map<String, Object>>();
		Map<String, Object> historyMap = null;
		if(!collectionList.isEmpty()){
			for(DataCollection collection : collectionList){
				
				if(!dcVersion.equals(collection.getVersion())){
    			historyMap = new HashMap<String, Object>();
    			historyMap.put("collectionId", collection.getCollectionId());
    			historyMap.put("name", collection.getName());
    			historyMap.put("title", collection.getTitle(locale));
    			historyMap.put("version", collection.getVersion());
    			
    			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    			String createDate = simpleDateFormat.format(collection.getCreateDate());
    			historyMap.put("createDate", createDate);
    			historyMap.put("groupId", collection.getGroupId());
    			historyMap.put("targetLanguage", collection.getTargetLanguage());
    			
    			historyList.add(historyMap);
				}
			}
		}
		return historyList;
	}
	
	/**
	 * using method in unified search of specific user- GPLUS Search by Text,
	 * Category
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 * @param start
	 * @param end
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListDataCollection(long companyGroupId, long groupId, long userId,
			String searchText, int start, int end, Locale locale) throws PortalException, SystemException {

		Map<String, Object> searchParam = new HashMap<String, Object>();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		if(locale != null){
			searchParam.put("languageId", locale.toString());
		}

		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		searchParam.put("categoryIds", categoryIds);

		if (!searchText.equals("")) {
			searchParam.put("searchText", searchText);
		}

		searchParam.put("userId", userId);
		searchParam.put("begin", start);
		searchParam.put("end", end);

		List<DataCollection> retriveListDataCollection = dataCollectionFinder.retrieveListDataCollection(searchParam);

		List<Map<String, Object>> dcList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dcMap = null;
		for (DataCollection dcObject : retriveListDataCollection) {
			dcMap = new HashMap<String, Object>();

			dcMap.put("collectionId", dcObject.getCollectionId());
			dcMap.put("dataTypeId", dcObject.getTypeId());
			dcMap.put("name", dcObject.getName());
			dcMap.put("version", dcObject.getVersion());
			dcMap.put("title", dcObject.getTitle(locale));
			dcMap.put("groupId", dcObject.getGroupId());
			dcMap.put("userId", dcObject.getUserId());
			dcMap.put("description", dcObject.getDescription(locale));

			String status = "public";
			if(dcObject.getStatus() == 1){ //public
				status = "private";
			}
			dcMap.put("status", status);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = simpleDateFormat.format(dcObject.getCreateDate());
			
			dcMap.put("createDate", createDate);
			
			dcList.add(dcMap);
		}

		return dcList;
	}

	/**
	 * using method in unified search - GPLUS Search by Text, Category
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 * @param start
	 * @param end
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListDataCollection(long companyGroupId, long groupId, String searchText,
			int start, int end, Locale locale) throws PortalException, SystemException {
		
		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		return retrieveListDataCollection(categoryIds, searchText, start, end, locale);
	}

	/**
	 * using method in Unified Search - GPLUS Search by Text, Category
	 * 
	 * @param categoryIds
	 * @param searchText
	 * @param start
	 * @param end
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListDataCollection(long[] categoryIds, String searchText, int start,
			int end, Locale locale) throws SystemException {
		Map<String, Object> searchParam = new HashMap<String, Object>();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		if(locale != null){
			searchParam.put("languageId", locale.toString());
		}

		if (categoryIds != null) {
			searchParam.put("categoryIds", categoryIds);
		}

		if (!searchText.equals("")) {
			searchParam.put("searchText", searchText);
		}

		searchParam.put("begin", start);
		searchParam.put("end", end);

		List<DataCollection> retriveListDataCollection = dataCollectionFinder.retrieveListDataCollection(searchParam);

		List<Map<String, Object>> dcList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dcMap = null;
		for (DataCollection dcObject : retriveListDataCollection) {
			dcMap = new HashMap<String, Object>();

			dcMap.put("collectionId", dcObject.getCollectionId());
			dcMap.put("dataTypeId", dcObject.getTypeId());
			dcMap.put("name", dcObject.getName());
			dcMap.put("version", dcObject.getVersion());
			dcMap.put("title", dcObject.getTitle(locale));
			dcMap.put("groupId", dcObject.getGroupId());
			dcMap.put("userId", dcObject.getUserId());
			dcMap.put("description", dcObject.getDescription(locale));

			String status = "public";
			if(dcObject.getStatus() == 1){ //public
				status = "private";
			}
			dcMap.put("status", status);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = simpleDateFormat.format(dcObject.getCreateDate());
			
			dcMap.put("createDate", createDate);

			dcList.add(dcMap);
		}

		return dcList;
	}

	/**
	 * using method in Unified Search of specific user - GPLUS Search by Text,
	 * Category
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public int retrieveCountDataCollection(long companyGroupId, long groupId, long userId, String searchText,
			Locale locale) throws PortalException, SystemException {

		Map<String, Object> searchParam = new HashMap<String, Object>();
		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		if(locale != null){
			searchParam.put("languageId", locale.toString());
		}
		searchParam.put("userId", userId);
		searchParam.put("categoryIds", categoryIds);

		if (!searchText.equals("")) {
			searchParam.put("searchText", searchText);
		}

		return dataCollectionFinder.retrieveCountDataCollection(searchParam);
	}

	/**
	 * using method in Unified Search - GPLUS Search by Text, Category
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 * @param locale
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public int retrieveCountDataCollection(long companyGroupId, long groupId, String searchText, Locale locale)
			throws PortalException, SystemException {

		long[] categoryIds = makeArrayAssetCategory(companyGroupId, groupId);
		return retrieveCountDataCollection(categoryIds, searchText, locale);
	}

	/**
	 * using method in Unified Search - GPLUS Search by Text, Category
	 * 
	 * @param categoryIds
	 * @param searchText
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public int retrieveCountDataCollection(long[] categoryIds, String searchText, Locale locale)
			throws SystemException {
		Map<String, Object> searchParam = new HashMap<String, Object>();

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(DataCollection.class.getName());
		searchParam.put("classNameId", classNameId);
		
		if(locale != null){
			searchParam.put("languageId", locale.toString());
		}

		if (categoryIds != null) {
			searchParam.put("categoryIds", categoryIds);
		}

		if (!searchText.equals("")) {
			searchParam.put("searchText", searchText);
		}

		return dataCollectionFinder.retrieveCountDataCollection(searchParam);
	}

	/**
	 * related asset count method
	 * @param classPKList
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public int retrieveCountRelatedAssetDataCollection(List<Long> classPKList, Locale locale)
		throws SystemException {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("languageId", locale.toString());
		
		String collectionIds = "";
    for(Long simProId : classPKList){
    	if("".equals(collectionIds)){
    		collectionIds += String.valueOf(simProId);
    	}else{
    		collectionIds += ", "+String.valueOf(simProId);
    	}
    }
    
  if(!"".equals(collectionIds) ){
  	params.put("modelSeqList", collectionIds);
  }
		
		return dataCollectionFinder.retrieveCountRelatedAssetDataCollection(params);
	}
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

	@Override
	public List<Map<String, Object>> retrieveDataCollectionObjects(String searchText, int start, int end)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countDataCollectionObjects(String searchText) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}
}
