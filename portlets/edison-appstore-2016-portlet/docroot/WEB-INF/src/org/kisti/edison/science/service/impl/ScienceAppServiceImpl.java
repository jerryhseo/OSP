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

package org.kisti.edison.science.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.base.ScienceAppServiceBaseImpl;
import org.kisti.edison.util.EdisonExpndoUtil;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * The implementation of the science app remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.science.service.ScienceAppService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppServiceUtil
 */

public class ScienceAppServiceImpl extends ScienceAppServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.science.service.ScienceAppServiceUtil} to access the science app remote service.
	 */
	@JSONWebService(value = "apps", method = "GET")
	@AccessControlled(guestAccessEnabled=true)
	public String retrieveApiAppList() throws Exception{
		JSONObject json = new JSONObject();
		
		//Data Search
		long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,User.class.getName(),"CUSTOM_FIELDS");
		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("columnId", column.getColumnId());
		List<Object[]> scienceAppApiList = scienceAppFinder.retrieveApiAppList(params);
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<scienceAppApiList.size();i++){
			Object[] resultArray = scienceAppApiList.get(i);
			ScienceApp scienceApp = (ScienceApp) resultArray[0];
			long classPK = GetterUtil.getLong(resultArray[1]);
			
			Map<String,Object> dataRow = new HashMap<String,Object>();
			List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			
			long scienceAppId = scienceApp.getScienceAppId();
			
			map.put("scienceAppId", scienceAppId);
			List<Object[]> scienceAppCategories = scienceAppFinder.retrieveApiAppCategories(map);

			for(int j=0; j<scienceAppCategories.size();j++){
				Object[] resultCategory = scienceAppCategories.get(j);
				Map<String, Object> categoryMap = new HashMap<String, Object>();
				categoryMap.put("ChildCategory", resultCategory[0]);
				categoryMap.put("ParentCategory", resultCategory[1]);
				
				categoryList.add(categoryMap);
			}
			dataRow.put("Categorys", categoryList);
			
			String name = scienceApp.getName();
			String title = scienceApp.getTitle(Locale.KOREAN);
			String version = scienceApp.getVersion();
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = transFormat.format(scienceApp.getCreateDate());
			
			String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(classPK, EdisonExpando.CDNM, Locale.KOREAN);
			
			String detailUrl = "https://www.edison.re.kr/web/portal/scienceappstore"
			    + "?p_p_id=edisonscienceAppstore_WAR_edisonappstore2016portlet"
			    + "&_edisonscienceAppstore_WAR_edisonappstore2016portlet_myaction=detailView"
			    + "&p_p_state=maximized&_edisonscienceAppstore_WAR_edisonappstore2016portlet_solverId="
			    + scienceApp.getScienceAppId();
			String manualUrl = !scienceApp.getManualIdCurrentValue().equals("0") 
			    ? "https://www.edison.re.kr/web/portal/scienceappstore"
			        + "?p_p_id=edisonscienceAppstore_WAR_edisonappstore2016portlet&p_p_lifecycle=2"
			        + "&p_p_resource_id=edisonFileDownload&_edisonscienceAppstore_WAR_edisonappstore2016portlet_fileEntryId="
			        + scienceApp.getManualIdCurrentValue()
		        : "";
			
			dataRow.put("Name", name);
			dataRow.put("Title", title);
			dataRow.put("Version", version);
			dataRow.put("Affiliation", affiliation);
			dataRow.put("DetailUrl", detailUrl);
			dataRow.put("ManualUrl", manualUrl);
			dataRow.put("CreateDate", createDate);
			
			dataList.add(dataRow);
		}
		//JSON CREATE
		json.put("Total", scienceAppApiList.size());
		json.put("ScienceAppList", dataList);
		
		return json.toString();
	}
	
	@JSONWebService(value = "categories", method = "GET")
	@AccessControlled(guestAccessEnabled=true)
	public String retrieveApiCategory()throws Exception{
		JSONArray jsons = new JSONArray();
		//Data Search
		long companyGroupId = CompanyLocalServiceUtil.getCompanies().get(0).getGroupId();
		AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
		int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary.getVocabularyId());
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
		
		if(rootCategoryCnt!=0){
			List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(aVocabulary.getVocabularyId(), -1, -1,null);
			for(AssetCategory rootCatogory : rootCategoryList){
				Map<String,Object> parentCategoryMap = new HashMap<String,Object>();
				parentCategoryMap.put("CategoryTitle", rootCatogory.getTitle(Locale.KOREAN));
				
				List<Map<String,Object>> childCategoryList = new ArrayList<Map<String,Object>>();
				List<AssetCategory> childCategorys =  AssetCategoryLocalServiceUtil.getChildCategories(rootCatogory.getCategoryId());
				for(AssetCategory childCatogory : childCategorys){
					Map<String,Object> childCategoryMap = new HashMap<String,Object>();
					childCategoryMap.put("SubCategoryTitle", childCatogory.getTitle(Locale.KOREAN));
					childCategoryList.add(childCategoryMap);
				}
				parentCategoryMap.put("SubCategory", childCategoryList);
				categoryList.add(parentCategoryMap);
			}
			jsons = JSONArray.fromObject(categoryList);
		}
		
		return jsons.toString();
	}
	
}