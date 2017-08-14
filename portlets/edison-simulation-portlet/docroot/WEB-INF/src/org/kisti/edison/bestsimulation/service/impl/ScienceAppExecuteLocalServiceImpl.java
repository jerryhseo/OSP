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

package org.kisti.edison.bestsimulation.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.bestsimulation.service.base.ScienceAppExecuteLocalServiceBaseImpl;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

/**
 * The implementation of the science app execute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.base.ScienceAppExecuteLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil
 */
public class ScienceAppExecuteLocalServiceImpl
	extends ScienceAppExecuteLocalServiceBaseImpl {

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
			String startDt, String endDt, List<Map<String, Object>> scienceAppList, boolean categorySearch) 
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

		if(scienceAppList != null && !scienceAppList.isEmpty()){
			String scienceAppIds = "";
			for(Map<String, Object> scienceApp : scienceAppList){
				String scienceAppId = CustomUtil.strNull(scienceApp.get("scienceAppId"));
				if(!"".equals(scienceAppId)){
					if(scienceAppIds == ""){
						scienceAppIds += scienceAppId;
					}else{
						scienceAppIds += ","+scienceAppId;
					}
				}
			}
			searchParam.put("scienceAppIds", scienceAppIds);
		}
		
		searchParam.put("columnId", columnId);
		searchParam.put("startDt", startDt);
		searchParam.put("endDt", endDt);
		
		return searchParam;
	}
	
	/*앱실행 통계*/
	public List<Map<String, Object>> getStatisticsSwExeTableOrganigation(long companyGroupId, long groupId, Locale locale, 
			long columnId, String startDt, String endDt, List<Map<String, Object>> scienceAppList, boolean categorySearch) 
					throws SystemException, PortalException, ParseException{		
		
		Map<String,Object> searchParam = settingStatisticsParameter(companyGroupId, groupId, columnId, startDt, endDt, scienceAppList, categorySearch);
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class.getName());
        searchParam.put("classNameId", classNameId);
        
		List<Object[]> tempList = scienceAppExecuteFinder.getStatisticsSwExeTableOrganigation(searchParam);
		
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("scienceApp_name",		objs[0]);
					map.put("user_count",		objs[1]);
					map.put("averageRuntime",	objs[2]);
					map.put("exe_count",		objs[3]);
					
					String universityNm = "";
					if(!String.valueOf(objs[4]).equals("")){
						universityNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(objs[4]), EdisonExpando.CDNM, locale);
					}
					
					map.put("scienceApp_affiliation_name",		universityNm);
					map.put("mgtName",						objs[5]);
					map.put("mgtDate",						objs[6]);
					map.put("scienceApp_version",				objs[7]);
					
					resultList.add(map);
				}
			}
		}
		
		if(scienceAppList != null && !scienceAppList.isEmpty()){
			List<Object[]> swNoneExeList = scienceAppExecuteFinder.getStatisticsSwNoneExeTableOrganigation(searchParam);
			
			Object[] swNoneExeobjs = null;
			Map swNoneExeMap = null;
			
			if(swNoneExeList != null){
				if(swNoneExeList.size() > 0){
					for(int i=0;i < swNoneExeList.size();i++){
						swNoneExeobjs = (Object[])swNoneExeList.get(i);
						swNoneExeMap = new HashMap();
						swNoneExeMap.put("scienceApp_name",		swNoneExeobjs[0]);
						swNoneExeMap.put("user_count",		swNoneExeobjs[1]);
						swNoneExeMap.put("averageRuntime",	swNoneExeobjs[2]);
						swNoneExeMap.put("exe_count",		swNoneExeobjs[3]);
						
						String universityNm = "";
						if(!String.valueOf(swNoneExeobjs[4]).equals("")){
							universityNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(swNoneExeobjs[4]), EdisonExpando.CDNM, locale);
						}
						
						swNoneExeMap.put("scienceApp_affiliation_name",		universityNm);
						swNoneExeMap.put("mgtName",						swNoneExeobjs[5]);
						swNoneExeMap.put("mgtDate",						swNoneExeobjs[6]);
						swNoneExeMap.put("scienceApp_version",				swNoneExeobjs[7]);
						
						resultList.add(swNoneExeMap);
					}
				}
			}		
		}
		
		return resultList;
	}
	
	/*월별 앱실행 통계*/
	public List<Map<String, Object>> getStatisticsSwExeBarChartDate(long companyGroupId, long groupId, 
			long columnId, String startDt, String endDt, List<Map<String, Object>> scienceAppList, boolean categorySearch) 
					throws SystemException, PortalException, ParseException{
		Map<String,Object> searchParam = settingStatisticsParameter(companyGroupId, groupId, columnId, startDt, endDt, scienceAppList, categorySearch);
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class.getName());
        searchParam.put("classNameId", classNameId);
        
		List<Object[]> tempList = scienceAppExecuteFinder.getStatisticsSwExeBarChartDate(searchParam);
		
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
	
	/*ScienceAppExecute Insert*/
	public int insertCustomScienceAppExecute(String startDt, String endDt) throws SystemException, NumberFormatException, NoSuchModelException {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("startDt", CustomUtil.strNull(startDt,""));
		searchParam.put("endDt", CustomUtil.strNull(endDt,""));
		
		int result = 0;
		try{
			result = scienceAppExecuteFinder.insertCustomScienceAppExecute(searchParam);
		}catch (Exception e) {
			result = scienceAppExecuteFinder.deleteCustomScienceAppExecute(searchParam);
			result = scienceAppExecuteFinder.insertCustomScienceAppExecute(searchParam);
		}
		
		return result;
	}
	
}