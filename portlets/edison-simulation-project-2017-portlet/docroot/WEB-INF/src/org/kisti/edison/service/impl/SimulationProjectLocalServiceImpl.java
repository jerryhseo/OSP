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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.SimProScienceAppLink;
import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.service.base.SimulationProjectLocalServiceBaseImpl;
import org.kisti.edison.service.persistence.SimProScienceAppLinkPK;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

/**
 * The implementation of the simulation project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.SimulationProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.service.base.SimulationProjectLocalServiceBaseImpl
 * @see org.kisti.edison.service.SimulationProjectLocalServiceUtil
 */
public class SimulationProjectLocalServiceImpl
	extends SimulationProjectLocalServiceBaseImpl {
	
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

// ===================	Simulation Project  Start getlist  =================================	
	
	/**
	 * Simulation Project List Get (Portal - Admin)
	 * Use Simulation Project
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomMySimulationProjectList(int begin, int end, String searchValue, Locale locale) throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = new HashMap<String,Object>();
        searchParam.put("begin", begin);
        searchParam.put("end", end);
        if(!"".equals(CustomUtil.strNull(searchValue))){
        	searchParam.put("searchValue", searchValue);
        }
        long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
        searchParam.put("classNameId", classNameId);
        searchParam.put("languageId", locale.toString());
        
        List<Object[]> tempList = simulationProjectFinder.getCustomMySimulationProjectList(searchParam);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        SimulationProject objs = null;
        Map<String, Object> map = null;

        if(tempList != null){
	         if(tempList.size() > 0){
	         	for(int i=0;i < tempList.size();i++){
	         		Object[] temp = tempList.get(i);
	         		objs = (SimulationProject)temp[0];
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		map.put("insertDtStr",				(String)temp[1]);
	         		map.put("screenName",				(String)temp[2]);
	         		
	         		resultList.add(map);
	         	}
	         }
        }		
		 
		 return resultList;
	}
	
	/**
	 * Simulation Project List Get (Portal - User)
	 * Use Simulation Project
	 * @param ownerId - 프로젝트 소유자 아이디
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어 
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomMySimulationProjectList(long ownerId, int begin, int end, String searchValue, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
		 searchParam.put("ownerId", ownerId);
         searchParam.put("begin", begin);
         searchParam.put("end", end);
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("languageId", locale.toString());
         
         List<Object[]> tempList = simulationProjectFinder.getCustomMySimulationProjectList(searchParam);
         List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
         SimulationProject objs = null;
         Map<String, Object> map = null;

         if(tempList != null){
 	         if(tempList.size() > 0){
 	         	for(int i=0;i < tempList.size();i++){
 	         		Object[] temp = tempList.get(i);
 	         		objs = (SimulationProject)temp[0];
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		map.put("insertDtStr",				(String)temp[1]);
	         		map.put("screenName",				(String)temp[2]);
 	         		
 	         		resultList.add(map);
 	         	}
 	         }
         }	
		 
		 return resultList;
	}
	
	/**
	 * Simulation Project List Get (Site - Admin, Site Admin)
	 * Use Simulation Project
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomMySimulationProjectList(int begin, int end, String searchValue, Locale locale, List<Long> siteCategoryIdList) throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = new HashMap<String,Object>();
        searchParam.put("begin", begin);
        searchParam.put("end", end);
        if(!"".equals(CustomUtil.strNull(searchValue))){
        	searchParam.put("searchValue", searchValue);
        }
        long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
        searchParam.put("classNameId", classNameId);
        searchParam.put("languageId", locale.toString());
        
        String categoryIds = "";
        for(Long categoryId : siteCategoryIdList){
        	if("".equals(categoryIds)){
        		categoryIds += String.valueOf(categoryId);
        	}else{
        		categoryIds += ", "+String.valueOf(categoryId);
        	}
        }
        searchParam.put("categoryIds", categoryIds);
        
        List<Object[]> tempList = simulationProjectFinder.getCustomMySimulationProjectList(searchParam);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        SimulationProject objs = null;
        Map<String, Object> map = null;

        if(tempList != null){
	         if(tempList.size() > 0){
	         	for(int i=0;i < tempList.size();i++){
	         		Object[] temp = tempList.get(i);
	         		objs = (SimulationProject)temp[0];
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		map.put("insertDtStr",				(String)temp[1]);
	         		map.put("screenName",				(String)temp[2]);
	         		
	         		resultList.add(map);
	         	}
	         }
        }			
		 
		 return resultList;
	}	
	
	/**
	 * Simulation Project List Get (Site - User)
	 * Use Simulation Project
	 * @param ownerId - 프로젝트 소유자 아이디
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomMySimulationProjectList(long ownerId, int begin, int end, String searchValue, Locale locale, List<Long> siteCategoryIdList) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
		 searchParam.put("ownerId", ownerId);
         searchParam.put("begin", begin);
         searchParam.put("end", end);
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("languageId", locale.toString());
         
         String categoryIds = "";
         for(Long categoryId : siteCategoryIdList){
         	if("".equals(categoryIds)){
         		categoryIds += String.valueOf(categoryId);
         	}else{
         		categoryIds += ", "+String.valueOf(categoryId);
         	}
         }
         searchParam.put("categoryIds", categoryIds);
         
         List<Object[]> tempList = simulationProjectFinder.getCustomMySimulationProjectList(searchParam);
         List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
         SimulationProject objs = null;
         Map<String, Object> map = null;

         if(tempList != null){
 	         if(tempList.size() > 0){
 	         	for(int i=0;i < tempList.size();i++){
 	         		Object[] temp = tempList.get(i);
 	         		objs = (SimulationProject)temp[0];
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		map.put("insertDtStr",				(String)temp[1]);
	         		map.put("screenName",				(String)temp[2]);
 	         		
 	         		resultList.add(map);
 	         	}
 	         }
         }		
		 
		 return resultList;
	}

// ===================	Simulation Project  End getlist  =================================
	
// ===================	Simulation Project  Start Count  =================================	
	
	/**
	 * Simulation Project List Count (Portal - Admin)
	 * Use Simulation Project or Integrated Search
	 * @param searchValue - 검색어
	 * @return int - count
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomMySimulationProjectCount(String searchValue, Locale locale) throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = new HashMap<String,Object>();
        if(!"".equals(CustomUtil.strNull(searchValue))){
        	searchParam.put("searchValue", searchValue);
        }
        long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
        searchParam.put("classNameId", classNameId);
        searchParam.put("languageId", locale.toString());
        
        int resultCount = 0;
        resultCount = simulationProjectFinder.getCustomMySimulationProjectCount(searchParam);
		 
		 return resultCount;
	}
	
	/**
	 * Simulation Project List Count (Portal - User)
	 * Use Simulation Project
	 * @param ownerId - 프로젝트 소유자 아이디
	 * @param searchValue - 검색어
	 * @return int - count
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomMySimulationProjectCount(long ownerId, String searchValue, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
		 searchParam.put("ownerId", ownerId);
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("languageId", locale.toString());
         
         int resultCount = 0;
         resultCount = simulationProjectFinder.getCustomMySimulationProjectCount(searchParam);
		 
		 return resultCount;
	}

	/**
	 * Simulation Project List Count (Site - Admin, Site Admin)
	 * Use Simulation Project
 	 * @param searchValue - 검색어
 	 * @param siteCategoryIdList - 카테고리 아이디 리스트
	 * @return int - count
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomMySimulationProjectCount(String searchValue, List<Long> siteCategoryIdList, Locale locale) throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = new HashMap<String,Object>();
        if(!"".equals(CustomUtil.strNull(searchValue))){
        	searchParam.put("searchValue", searchValue);
        }
        long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
        searchParam.put("classNameId", classNameId);
        searchParam.put("languageId", locale.toString());
        
        String categoryIds = "";
        for(Long categoryId : siteCategoryIdList){
        	if("".equals(categoryIds)){
        		categoryIds += String.valueOf(categoryId);
        	}else{
        		categoryIds += ", "+String.valueOf(categoryId);
        	}
        }
        searchParam.put("categoryIds", categoryIds);
        
        int resultCount = 0;
        resultCount = simulationProjectFinder.getCustomMySimulationProjectCount(searchParam);
		 
		return resultCount;
	}	
	
	/**
	 * Simulation Project List Count (Site - User)
	 * Use Simulation Project
	 * @param ownerId - 프로젝트 소유자 아이디
	 * @param siteCategoryIdList - 카테고리 아이디 리스트
	 * @return int - count
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomMySimulationProjectCount(long ownerId, String searchValue, List<Long> siteCategoryIdList, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
		 searchParam.put("ownerId", ownerId);
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("languageId", locale.toString());
         
         String categoryIds = "";
         for(Long categoryId : siteCategoryIdList){
         	if("".equals(categoryIds)){
         		categoryIds += String.valueOf(categoryId);
         	}else{
         		categoryIds += ", "+String.valueOf(categoryId);
         	}
         }
         searchParam.put("categoryIds", categoryIds);
         
         int resultCount = 0;
         resultCount = simulationProjectFinder.getCustomMySimulationProjectCount(searchParam);
         
         return resultCount;
	}	

// ===================	Simulation Project End Count  =================================	
	
// ===================	Use Integrated Search Simulation Project Start getList  =======	
	

	/**
	 * Simulation Project List Get
	 * Use Integrated Search
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어
	 * @param locale 
	 * @param siteCategoryIdList - 카테고리 아이디 리스트
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomIntegratedSearchSimulationProjectList(int begin, int end, String searchValue, Locale locale, List<Long> siteCategoryIdList) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
         searchParam.put("begin", begin);
         searchParam.put("end", end);
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         
         String categoryIds = "";

         if(siteCategoryIdList != null){
	         for(Long categoryId : siteCategoryIdList){
	         	if("".equals(categoryIds)){
	         		categoryIds += String.valueOf(categoryId);
	         	}else{
	         		categoryIds += ", "+String.valueOf(categoryId);
	         	}
	         }
         }
         
         if(!"".equals(categoryIds)){
        	 searchParam.put("categoryIds", categoryIds);
         }
         
         searchParam.put("projectOpenYn", true);
         searchParam.put("languageId", locale.toString());
         
         List<SimulationProject> tempList = simulationProjectFinder.getCustomSimulationProjectList(searchParam);
         List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
         SimulationProject objs = null;
         Map<String, Object> map = null;

         if(tempList != null){
	         if(tempList.size() > 0){
	         	for(int i=0;i < tempList.size();i++){
	         		objs = (SimulationProject)tempList.get(i);
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		
	         		resultList.add(map);
	         	}
	         }
         }		
		 
		 return resultList;
	}	
	
	/**
	 * Simulation Project List Count
	 * Use Integrated Search
	 * @param ownerId - 프로젝트 소유자 아이디
	 * @param siteCategoryIdList - 카테고리 아이디 리스트
	 * @return int
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomIntegratedSearchSimulationProjectCount(String searchValue, List<Long> siteCategoryIdList, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         
         String categoryIds = "";
         
         if(siteCategoryIdList != null){
	         for(Long categoryId : siteCategoryIdList){
	         	if("".equals(categoryIds)){
	         		categoryIds += String.valueOf(categoryId);
	         	}else{
	         		categoryIds += ", "+String.valueOf(categoryId);
	         	}
	         }
         }
         
         if(!"".equals(categoryIds)){
        	 searchParam.put("categoryIds", categoryIds);
         }
         
         searchParam.put("projectOpenYn", true);
         searchParam.put("languageId", locale.toString());
         
         int resultCount = 0;
         resultCount = simulationProjectFinder.getCustomSimulationProjectCount(searchParam);
         
         return resultCount;
	}
	
	// ===================	Use Integrated Search Simulation Project End getList  =======
	
	// ===================  Use Link Service Start ======================
	
	/**
	 * Simulation Project List Get
	 * Use Link
	 * @param begin 
	 * @param end
	 * @param searchValue - 검색어 
	 * @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomLinkSimulationProjectList(int begin, int end, String searchValue, List<Long> simProIdList, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
         searchParam.put("begin", begin);
         searchParam.put("end", end);
         
         String simProIds = "";
         for(Long simProId : simProIdList){
         	if("".equals(simProIds)){
         		simProIds += String.valueOf(simProId);
         	}else{
         		simProIds += ", "+String.valueOf(simProId);
         	}
         }
         
		 if(!"".equals(simProIds) ){
			 searchParam.put("simProIds", simProIds);
		 }
		 
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("projectOpenYn", true);
         searchParam.put("languageId", locale.toString());
         
         List<SimulationProject> tempList = simulationProjectFinder.getCustomSimulationProjectList(searchParam);
         List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
         SimulationProject objs = null;
         Map<String, Object> map = null;

         if(tempList != null){
	         if(tempList.size() > 0){
	         	for(int i=0;i < tempList.size();i++){
	         		objs = (SimulationProject)tempList.get(i);
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		
	         		resultList.add(map);
	         	}
	         }
         }		
		 
		 return resultList;
	}	

	/**
	 * Simulation Project List Get
	 * Use Link
	 * @param searchValue - 검색어 
	 * @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	 * @param locale 
	 * @return List<Map<String,Object>>
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getCustomLinkSimulationProjectList(String searchValue, List<Long> simProIdList, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
         
         String simProIds = "";
         for(Long simProId : simProIdList){
         	if("".equals(simProIds)){
         		simProIds += String.valueOf(simProId);
         	}else{
         		simProIds += ", "+String.valueOf(simProId);
         	}
         }
         
		 if(!"".equals(simProIds) ){
			 searchParam.put("simProIds", simProIds);
		 }
		 
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("projectOpenYn", true);
         searchParam.put("languageId", locale.toString());
         
         List<SimulationProject> tempList = simulationProjectFinder.getCustomSimulationProjectList(searchParam);
         List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
         SimulationProject objs = null;
         Map<String, Object> map = null;

         if(tempList != null){
	         if(tempList.size() > 0){
	         	for(int i=0;i < tempList.size();i++){
	         		objs = (SimulationProject)tempList.get(i);
	         		map = new HashMap();
	         		map.put("simulationProjectId",		objs.getSimulationProjectId());
	         		map.put("title",					objs.getTitle(locale));
	         		map.put("projectOpenYn",	    	objs.getProjectOpenYn());
	         		map.put("explain",					objs.getExplain(locale));
	         		map.put("iconId",					objs.getIconId());
	         		
	         		resultList.add(map);
	         	}
	         }
         }		
		 
		 return resultList;
	}	

	/**
	 * Simulation Project List Count
	 * Use Link
	 * @param searchValue - 검색어
	 * @param simProIdList - 시뮬레이션 프로젝트 아이디 리스트
	 * @return int - count
	 * @throws SystemException
	 * @throws PortalException
	 * @throws ParseException
	 */
	public int getCustomLinkSimulationProjectCount(String searchValue, List<Long> simProIdList, Locale locale) throws SystemException, PortalException, ParseException{
		 Map<String,Object> searchParam = new HashMap<String,Object>();
		 
		 String simProIds = "";
         for(Long simProId : simProIdList){
         	if("".equals(simProIds)){
         		simProIds += String.valueOf(simProId);
         	}else{
         		simProIds += ", "+String.valueOf(simProId);
         	}
         }
         
		 if(!"".equals(simProIds) ){
			 searchParam.put("simProIds", simProIds);
		 }
		 
         if(!"".equals(CustomUtil.strNull(searchValue))){
         	searchParam.put("searchValue", searchValue);
         }
         long classNameId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class.getName());
         searchParam.put("classNameId", classNameId);
         searchParam.put("projectOpenYn", true);
         searchParam.put("languageId", locale.toString());
         
         int resultCount = 0;
         resultCount = simulationProjectFinder.getCustomSimulationProjectCount(searchParam);
		 
		 return resultCount;
	}
	
public int searchAssetEntryModelAppCount(Map<String, Object> params){
  return simProScienceAppLinkFinder.searchTextEntryScienceAppCount(params);
}
	
	// ===================  Use Link Service End ======================		
	
	/**
	 * Simulation Project 생성
	 * @param upload
	 * @param request
	 * @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	 * @param companyId - 팀생성을 위한 컴퍼니 아이디
	 * @param user - 시뮬레이션 프로젝트 생성 유저 객체
	 * @param params - 생성화면 form 에서 넘긴 데이터
	 * @return simulationProjectId
	 * @throws SystemException
	 */
	public long insertCustomSimulationProject(UploadPortletRequest upload, PortletRequest request, long portalGroupId, long companyId, User user, Map params) throws SystemException{
		
		try{
			long simulationProjectId = CounterLocalServiceUtil.increment(Team.class.getName());
			String serviceLanguage = "";
			
			//----Team 생성
			Team team = TeamLocalServiceUtil.createTeam(simulationProjectId);
			team.setCompanyId(companyId);
			team.setUserId(user.getUserId());
			team.setUserName(user.getFirstName());
			team.setCreateDate(new Date());
			team.setGroupId(portalGroupId);
			team.setName("SimulationProject_"+team.getTeamId());
			
			TeamLocalServiceUtil.addTeam(team);
			
			/*
			 * 아래의 addRole이 deprecated 되면 이런 식으로 롤을 생성
			 * Counter가 다른 곳에서 잘못써서 Role Counter를 쓰면 roleId가 중복 될수 있다고 함.
			 * 
			  long roleId = CounterLocalServiceUtil.increment(Role.class.getName());
			  Role role = RoleLocalServiceUtil.createRole(roleId);
			  role.setUserId(user.getUserId());  
			  role.setCompanyId(companyId);		
			  RoleLocalServiceUtil.addRole(role);
			*/
			
			//사이트 팀 롤을 추가
			RoleLocalServiceUtil.addRole(
					user.getUserId(), 
					companyId, 
					String.valueOf(team.getTeamId()), null, null, 
					RoleConstants.TYPE_PROVIDER, 
					Team.class.getName(), 
					team.getTeamId());
			
			//ResourcePermission에  팀에 대한 Owner권한 생성
			ResourceLocalServiceUtil.addResources(
					companyId, 
					portalGroupId, 
					user.getUserId(), 
					Team.class.getName(), team.getTeamId(), false, true, true);
			
			//User Role에 대해 권한을 부여하기 위한 Map 생성
			Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
			Map<Long, String[]> siteRoleMap = new HashMap<Long, String[]>();
			siteRoleMap.put(userRole.getRoleId(), new String[]{
					ActionKeys.ASSIGN_MEMBERS,
					ActionKeys.VIEW,
					ActionKeys.DELETE
			});
			
			//Team에 User에 대한 권한을 부여
			ResourcePermissionLocalServiceUtil.setResourcePermissions(
					companyId, 
					Team.class.getName(), 
					ResourceConstants.SCOPE_INDIVIDUAL, 
					String.valueOf(team.getTeamId()), 
					siteRoleMap
			);
			
			//Simulation Project 생성
			SimulationProject simulationProject = SimulationProjectLocalServiceUtil.createSimulationProject(simulationProjectId);
			//제목, 설명 등록
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
				String title = CustomUtil.strNull(params.get("title_"+languageId));
				String explain = CustomUtil.strNull(params.get("explain_"+languageId));
				simulationProject.setTitle(title, aLocale);
				simulationProject.setExplain(explain, aLocale);
			}
			
			simulationProject.setProjectOpenYn(false) ;	//생성시 비공계
			simulationProject.setOwnerId(user.getUserId());
			simulationProject.setInsertId(user.getUserId());
			simulationProject.setInsertDate(new Date());
			
			SimulationProjectLocalServiceUtil.updateSimulationProject(simulationProject);
			
			//----Simulation Project AssetEntry 등록
			/*
			 * updateEntry(long userId, long groupId, Date createDate, Date modifiedDate, String className, 
			 * long classPK, String classUuid, long classTypeId, long[] categoryIds, String[] tagNames, boolean visible, Date startDate, Date endDate, Date expirationDate, 
			 * String mimeType, String title, 
			 * String description, String summary, String url, String layoutUuid, int height, int width, Integer priority, boolean sync)
			 */
			AssetEntry simProAssetEntry = assetEntryLocalService.updateEntry(
					user.getUserId(), portalGroupId, simulationProject.getInsertDate(), simulationProject.getUpdateDate(), SimulationProject.class.getName(),
					simulationProject.getSimulationProjectId(), null, 0, null, null, true, null, null, null, 
					ContentTypes.TEXT_PLAIN, simulationProject.getTitle(), 
					null, null, null, null, 0, 0, null, false
			);
			
			long entryId = simProAssetEntry.getEntryId();
			
			//카테고리 등록
			List<String[]> categoryList = new ArrayList<String[]>(); 
			if(params.get("childrenCategoryCheckbox") instanceof String[]){
				String[] childrenCategoryArray = (String[]) params.get("childrenCategoryCheckbox");
				for(String childrenCategory:childrenCategoryArray){
					String[] childrenCategoryValue = childrenCategory.split("_");
					categoryList.add(childrenCategoryValue);
				}
			}else if(params.get("childrenCategoryCheckbox") instanceof String){
				String[] childrenCategoryValue = CustomUtil.strNull(params.get("childrenCategoryCheckbox")).split("_");
				categoryList.add(childrenCategoryValue);
			}
			
			if(entryId != 0){
				for(String[] categoryArray:categoryList){
					long parentCategoryId = GetterUtil.getLong(categoryArray[0],0l);
					long categoryId = GetterUtil.getLong(categoryArray[1],0l);
					
					AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
				}
			} 
			
			return simulationProjectId;
			
		}catch(Exception e){
			throw new SystemException(e);
		} 
		
	}//end insertCustomSimulationProject

	/**
	 * Simulation Project 수정
	 * @param upload
	 * @param request
	 * @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	 * @param companyId - 팀생성을 위한 컴퍼니 아이디
	 * @param user - 시뮬레이션 프로젝트 생성 유저 객체
	 * @param params - 생성화면 form 에서 넘긴 데이터
	 * @return simulationProjectId
	 * @throws SystemException
	 */
	public long updateCustomSimulationProject(UploadPortletRequest upload, PortletRequest request, long portalGroupId, long companyId, User user, Map params) throws SystemException{
		
		try{
			long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
			if(simulationProjectId == 0){
				throw new SystemException();
			}
			
			String serviceLanguage = "";
			
			//수정하는 simualtionProject 정보를 가지고 온다.
			SimulationProject simulationProject = SimulationProjectLocalServiceUtil.fetchSimulationProject(simulationProjectId);
			
			long iconId = simulationProject.getIconId();
			long imageFolderId = simulationProject.getImageFolderId();
			
			//제목, 설명 등록
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
				String title = CustomUtil.strNull(params.get("title_"+languageId));
				String explain = CustomUtil.strNull(params.get("explain_"+languageId));
				simulationProject.setTitle(title, aLocale);
				simulationProject.setExplain(explain, aLocale);
			}
			
			serviceLanguage = CustomUtil.strNull(params.get("targetLanguage"));
			
			if("".equals(serviceLanguage)){
				for(Locale aLocale : locales){
					String languageId = LocaleUtil.toLanguageId(aLocale);
					if("".equals(serviceLanguage)){
						serviceLanguage += languageId;
					}else {
						serviceLanguage += ","+languageId;
					}
				}
			}
			
			String projectOpenYn = CustomUtil.strNull(params.get("projectOpenYn"), "false");
			simulationProject.setServiceLanguage(serviceLanguage);
			simulationProject.setProjectOpenYn("true".equals(projectOpenYn) ? true : false) ;
			simulationProject.setUpdateId(user.getUserId());
			simulationProject.setUpdateDate(new Date());
			
			//icon 등록
			String simProIconStr = CustomUtil.strNull(upload.getFileName("project_icon"),"");
			if(!simProIconStr.equals("")){ //업로드 아이콘이 있는경우
				if(iconId != 0){
					try{
						DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
					}catch (NoSuchFileEntryException e) {
					}	
				}
				List<FileEntry> simProIcons = EdisonFileUtil.insertEdisonFile(
												request, upload,
												user.getUserId(), portalGroupId, 
												"", String.valueOf(simulationProjectId), "project_icon", EdisonFileConstants.SIMULATION_PROJECT
												);
				FileEntry simProEntry = simProIcons.get(0);
				simulationProject.setIconId(simProEntry.getFileEntryId());
			}
			
			//대표이미지 등록
			String[] simProImageStrList = upload.getFileNames("project_image");
			
			if(simProImageStrList != null){ //업로드 이미지가 있는경우
				
				List<FileEntry> simProImages = EdisonFileUtil.insertEdisonFile(
												request, upload,
												user.getUserId(), portalGroupId, 
												"", String.valueOf(simulationProjectId), "project_image", EdisonFileConstants.SIMULATION_PROJECT
												);
				if(simProImages != null && !simProImages.isEmpty() && imageFolderId == 0 ){
					FileEntry simProEntry = simProImages.get(0);
					simulationProject.setImageFolderId(simProEntry.getFolderId());
				}
			}
			
			SimulationProjectLocalServiceUtil.updateSimulationProject(simulationProject);
			
			//---- get Simulation Project AssetEntry
			/*
			 * 	fetchEntry(String className, long classPK) 
			 */
			AssetEntry simProAssetEntry = assetEntryLocalService.fetchEntry(SimulationProject.class.getName(), simulationProjectId);
			
			long entryId = simProAssetEntry.getEntryId();
			
			//기존 카테고리 삭제
			AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);
			
			//카테고리 등록
			List<String[]> categoryList = new ArrayList<String[]>(); 
			if(params.get("childrenCategoryCheckbox") instanceof String[]){
				String[] childrenCategoryArray = (String[]) params.get("childrenCategoryCheckbox");
				for(String childrenCategory:childrenCategoryArray){
					String[] childrenCategoryValue = childrenCategory.split("_");
					categoryList.add(childrenCategoryValue);
				}
			}else if(params.get("childrenCategoryCheckbox") instanceof String){
				String[] childrenCategoryValue = CustomUtil.strNull(params.get("childrenCategoryCheckbox")).split("_");
				categoryList.add(childrenCategoryValue);
			}
			
			if(entryId != 0){
				for(String[] categoryArray:categoryList){
					long parentCategoryId = GetterUtil.getLong(categoryArray[0],0l);
					long categoryId = GetterUtil.getLong(categoryArray[1],0l);
					
					AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
				}
			} 
			
			//기존 사이언스 앱 링크 삭제
			simProScienceAppLinkPersistence.removeBySimulationProjectId(simulationProjectId);
			
			//사이언스 앱 링크
			List<String> scienceAppIdList = new ArrayList<String>(); 
			if(params.get("scienceAppId") instanceof String[]){
				String[] scienceAppIdValue = (String[]) params.get("scienceAppId");
				for(String scienceAppId : scienceAppIdValue){
					scienceAppIdList.add(scienceAppId);
				}
			}else if(params.get("scienceAppId") instanceof String){
				String scienceAppId = CustomUtil.strNull(params.get("scienceAppId"));
				scienceAppIdList.add(scienceAppId);
			}
			
			for(String scienceAppIdString : scienceAppIdList){
				long scienceAppId = GetterUtil.getLong(scienceAppIdString, 0l);
				if(scienceAppId != 0){
					long simProScienceAppLinkId = CounterLocalServiceUtil.increment(SimProScienceAppLink.class.getName());
					SimProScienceAppLinkPK simProScienceAppLinkPK = new SimProScienceAppLinkPK(simProScienceAppLinkId, simulationProjectId); 
					SimProScienceAppLink simProScienceAppLink = SimProScienceAppLinkLocalServiceUtil.createSimProScienceAppLink(simProScienceAppLinkPK);
					simProScienceAppLink.setScienceAppId(scienceAppId);
					SimProScienceAppLinkLocalServiceUtil.updateSimProScienceAppLink(simProScienceAppLink);
				}
			}
			
			return simulationProjectId;
			
		}catch(Exception e){
			throw new SystemException(e);
		} 
		
	}//end updateCustomSimulationProject
	
	/**
	 * Simulation Project 삭제
	 * @param portalGroupId - 팀생성을 위한 포탈 그룹 아이디
	 * @param companyId - 팀생성을 위한 컴퍼니 아이디
	 * @param simulationProjectId - 삭제할 시뮬레이션프로젝트 아이디
	 * @throws SystemException
	 */
	public void deleteCustomSimulationProject(long simulationProjectId) throws SystemException{
		
		try{
			
			if(simulationProjectId == 0){
				throw new SystemException();
			}
			
			long teamId = simulationProjectId;
			//----Team 삭제
			TeamLocalServiceUtil.deleteTeam(teamId);
			
			//삭제하는 simualtionProject 정보를 가지고 온다.
			SimulationProject simulationProject = SimulationProjectLocalServiceUtil.fetchSimulationProject(simulationProjectId);
			
			long iconId = simulationProject.getIconId();
			long imageFolderId = simulationProject.getImageFolderId();
			
			//icon 삭제
			if(iconId !=0){ //아이콘이 있는경우
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
			}
			
			//대표이미지 삭제
			if(imageFolderId != 0){ //이미지가 있는경우
				DLFolderLocalServiceUtil.deleteDLFolder(imageFolderId);
			}
			
			AssetEntry simProAssetEntry = assetEntryLocalService.fetchEntry(SimulationProject.class.getName(), simulationProjectId);
			long entryId = simProAssetEntry.getEntryId();
			
			//카테고리 삭제 (AssetEntry_CategoryLink 삭제) 
			AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);
			
			// asset link 테이블 삭제
			List<AssetLink> entryLinkList = AssetLinkLocalServiceUtil.getLinks(entryId);
			for(AssetLink link : entryLinkList){
				AssetLinkLocalServiceUtil.deleteAssetLink(link);
			}
			
			//AssetEntry 삭제
			assetEntryLocalService.deleteAssetEntry(simProAssetEntry);
			
			//사이언스 앱 링크 삭제
			simProScienceAppLinkPersistence.removeBySimulationProjectId(simulationProjectId);
			
			//멤버 신청 정보 삭제
			requestMemberPersistence.removeBySimulationProjectId(simulationProjectId);
			
			//시뮬레이션 프로젝트 삭제
			SimulationProjectLocalServiceUtil.deleteSimulationProject(simulationProjectId);
			
		}catch(Exception e){
			throw new SystemException(e);
		} 
		
	}//end deleteCustomSimulationProject
	
	/**
	 * 시뮬레이션 프로젝트 기본정보
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param portalGroupId - 포탈 그룹 아이디
	 * @param companyGroupId - 전역의 그룹 아이디
	 * @return Map<String, Object> - 시뮬레이션 프로젝트 기본정보
	 * @throws SystemException
	 */
	public Map<String, Object> getCustomDefaultInfoSimulationProject(long simulationProjectId, long portalGroupId, long companyGroupId, Locale locale) throws SystemException{
		try{
			//기본정보
			Map<String, Object> data = new HashMap<String, Object>(); 
			SimulationProject simulationProject = SimulationProjectLocalServiceUtil.fetchSimulationProject(simulationProjectId);
			
			data.put("projectOpenYn", simulationProject.getProjectOpenYn());
			data.put("targetLanguage", CustomUtil.strNull(simulationProject.getServiceLanguage()));
			data.put("title", CustomUtil.strNull(simulationProject.getTitle()));
			data.put("currTitle", CustomUtil.strNull(simulationProject.getTitle(locale)));
			data.put("explain", CustomUtil.strNull(simulationProject.getExplain()));
			data.put("currExplain", CustomUtil.strNull(simulationProject.getExplain(locale)));
			data.put("ownerId", CustomUtil.strNull(simulationProject.getOwnerId()));
			
			Locale[] availableLocale = LanguageUtil.getAvailableLocales();
			for(Locale alocale : availableLocale){
				String languageId = LocaleUtil.toLanguageId(alocale);
				data.put("title_"+languageId, CustomUtil.strNull(simulationProject.getTitle(alocale) ));
				data.put("explain_"+languageId, CustomUtil.strNull(simulationProject.getExplain(alocale)));
			}
			
			//아이콘
			if(simulationProject.getIconId()!=0){
				try{
					DLFileEntry iconDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(simulationProject.getIconId());
					data.put("iconRepositoryId", iconDl.getRepositoryId());
					data.put("iconUuid", iconDl.getUuid());
					data.put("iconTitle", iconDl.getTitle());
					data.put("iconId", simulationProject.getIconId());
				}catch(Exception e){
					if(e instanceof NoSuchFileEntryException){
					}else{
						throw new PortalException(e);
					}							
				}
			}
			
			//대표이미지
			if(simulationProject.getImageFolderId() != 0){
				
				List<Map<String, Object>> imageList = EdisonFileUtil.getListEdisonFile(portalGroupId, "", String.valueOf(simulationProjectId), EdisonFileConstants.SIMULATION_PROJECT);
				List<Map<String, Object>> projectImageList = new ArrayList<Map<String, Object>>();
				Map<String, Object> imageMap;
				for(Map<String, Object> image : imageList){
					long fileEntryId = Long.parseLong(CustomUtil.strNull(image.get("fileEntryId"), "0"));
					imageMap = new HashMap<String, Object>();
					if(simulationProject.getIconId() != fileEntryId){
						imageMap.put("imageId", fileEntryId);
						imageMap.put("imageRepositoryId", image.get("fileRepositoryId"));
						imageMap.put("imageUuid", image.get("fileUuid"));
						imageMap.put("imageTitle", image.get("fileTitle"));
						projectImageList.add(imageMap);
					}
				}
				
				data.put("projectImageList", projectImageList);
			}
			
			//기본정보 - 카테고리
			String parentCategory = "";
			String childrenCategory = "";
			List<Map<String, Object>> childrenCategoryList = new ArrayList<Map<String, Object>>();
			AssetEntry simProAssetEntry = assetEntryLocalService.fetchEntry(SimulationProject.class.getName(), simulationProjectId); //	fetchEntry(String className, long classPK)
			List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(simProAssetEntry.getEntryId());
			
			if(assetCategoryList != null){
				for(AssetCategory assetCategory : assetCategoryList){
					String parentCategoryIdStr =  String.valueOf(assetCategory.getParentCategoryId());
					String categoryIdStr =  String.valueOf(assetCategory.getCategoryId());
					
					if(childrenCategory.equals("")){
						childrenCategory += parentCategoryIdStr+"_"+categoryIdStr;
					}else{
						childrenCategory += ","+parentCategoryIdStr+"_"+categoryIdStr;
					}
					
					if(parentCategory.equals("")){
						parentCategory += parentCategoryIdStr;
					}else{
						if(parentCategory.indexOf(parentCategoryIdStr) <= -1){
							parentCategory += ","+parentCategoryIdStr;
						}
					}
				}
			}
			
			//rootCategory 정보 조회			
			AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN); //전역의 GLOBAL_DOMAIN 의 Asset Vocabulary Id 가진 AssetVocabulary 객체를 가지고 온다.
			int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary.getVocabularyId());			
			
			if(rootCategoryCnt!=0){
				List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(aVocabulary.getVocabularyId(), -1, -1,null);
				for(AssetCategory rootCatogory : rootCategoryList){
					if(assetCategoryList != null){
						for(AssetCategory assetCategory : assetCategoryList){
							Map<String,Object> childrenCategoryMap = new HashMap<String,Object>();
							if(rootCatogory.getCategoryId() == assetCategory.getParentCategoryId()){
								childrenCategoryMap.put("value", assetCategory.getCategoryId());
								childrenCategoryMap.put("name", assetCategory.getTitle(locale));
								childrenCategoryList.add(childrenCategoryMap);
							}
						}
					}
				}
			}
			
			data.put("entryId", simProAssetEntry.getEntryId());
			data.put("childrenCategory", childrenCategory);
			data.put("parentCategory", parentCategory);
			data.put("childrenCategoryList", childrenCategoryList);
			
			//사이언스 앱 링크 정보
			List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
			List<SimProScienceAppLink> simProScienceAppLinkList = simProScienceAppLinkPersistence.findBySimulationProjectId(simulationProjectId);
			String[] scienceAppIdList = new String[simProScienceAppLinkList.size()];
			
			int size = 0;
			if(simProScienceAppLinkList != null && !simProScienceAppLinkList.isEmpty()){
				for(SimProScienceAppLink simProScienceAppLink: simProScienceAppLinkList){
					String scienceAppIdStr = String.valueOf(simProScienceAppLink.getScienceAppId()); 
					scienceAppIdList[size++] = scienceAppIdStr;
				}
			}
			
			if(scienceAppIdList != null && scienceAppIdList.length > 0){
				scienceAppList = SimProScienceAppLinkLocalServiceUtil.getScienceAppList(scienceAppIdList, locale);
			}
			
			if(scienceAppList !=null && !scienceAppList.isEmpty()){
				data.put("scienceAppList", scienceAppList);
			}
			
			return data;
		}catch(Exception e){
			throw new SystemException(e);
		}
		
		
	}

}