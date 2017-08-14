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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.science.NoSuchScienceAppFavoriteException;
import org.kisti.edison.science.model.ScienceAppFavorite;
import org.kisti.edison.science.service.base.ScienceAppFavoriteLocalServiceBaseImpl;
import org.kisti.edison.science.service.persistence.ScienceAppFavoritePK;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the science app favorite local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.science.service.ScienceAppFavoriteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppFavoriteLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppFavoriteLocalServiceUtil
 */
public class ScienceAppFavoriteLocalServiceImpl
	extends ScienceAppFavoriteLocalServiceBaseImpl {
	
	public int getScienceAppFavoriteCount(long scienceAppId, long userId) throws SystemException {
		
		int favoriteCount = 0;
		if(scienceAppId != 0){
			ScienceAppFavoritePK scienceAppFavoritePK = new ScienceAppFavoritePK(scienceAppId, userId);
			ScienceAppFavorite solverFavorite = scienceAppFavoritePersistence.fetchByPrimaryKey(scienceAppFavoritePK);
			if (solverFavorite != null) {
				favoriteCount = 1;
			}
		}
		return favoriteCount;
	}
	
	public int updateScienceAppFavorite(long userId, long scienceAppId, long groupId) throws NoSuchScienceAppFavoriteException, SystemException {
		int favoriteCount = 0;
		if(scienceAppId != 0){
			
			ScienceAppFavoritePK scienceAppFavoritePK = new ScienceAppFavoritePK(scienceAppId, userId);
			ScienceAppFavorite scienceAppFavorite = scienceAppFavoritePersistence.fetchByPrimaryKey(scienceAppFavoritePK);		
			if(scienceAppFavorite != null){
				scienceAppFavoritePersistence.remove(scienceAppFavoritePK);
				favoriteCount = 1;
			}else {
				scienceAppFavorite = scienceAppFavoritePersistence.create(scienceAppFavoritePK);
				scienceAppFavorite.setGroupId(groupId);
				scienceAppFavoritePersistence.update(scienceAppFavorite);
				favoriteCount = 0;
			}
		}
		return favoriteCount;
	}
	
	/**
	 * 즐겨찾기앱  리스트 조회 사이트 카테고리 생성하여 조회
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param locale
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getFavoriteAppList(long companyGroupId, long groupId, Locale locale, long userId) throws Exception{
		// 관리자/사이트 관리자이고, 현재 사이트가 하위사이트일때( 카테고리가 있음 )
		long[] categoryIds = makeCategoryEntryList(companyGroupId, groupId);
		return getFavoriteAppList(categoryIds, locale, userId);
	}
	
	public List<Map<String, Object>> getFavoriteAppList(long[] categoryIds,Locale locale, long userId ) throws SystemException, PortalException {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		try {
			List<Object[]> resultList = scienceAppFavoriteFinder.getFavoriteAppList(categoryIds, userId, locale);
			if(resultList != null) {
				Map<String, Object> resultRow = null;
				for (int i = 0; i < resultList.size(); i++) {
					Object[] resultArray = resultList.get(i);
					resultRow = new HashMap<String, Object>();
					resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
					resultRow.put("groupId", CustomUtil.strNull(resultArray[1]));
					resultRow.put("name", CustomUtil.strNull(resultArray[2]));
					resultRow.put("version", CustomUtil.strNull(resultArray[3]));
					resultRow.put("title",LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[4]), locale.getLanguage()));
					//메뉴얼
					long manualId = GetterUtil.getLong(LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[5]), locale.getLanguage()),0l);
					if(manualId !=0){
						resultRow.put("manualId", manualId);
						DLFileEntry manualDl =DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
						resultRow.put("manualRepositoryId", manualDl.getRepositoryId());
						resultRow.put("manualUuid", manualDl.getUuid());
						resultRow.put("manualTitle", manualDl.getTitle());
					}
					returnList.add(resultRow);
				}
			}
		} catch (NoSuchFileEntryException e) {
			// TODO Auto-generated catch block
			List<Object[]> resultList = scienceAppFavoriteFinder.getFavoriteAppList(categoryIds, userId, locale);
			if(resultList != null) {
				Map<String, Object> resultRow = null;
				for (int i = 0; i < resultList.size(); i++) {
					Object[] resultArray = resultList.get(i);
					resultRow = new HashMap<String, Object>();
					resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
					resultRow.put("groupId", CustomUtil.strNull(resultArray[1]));
					resultRow.put("name", CustomUtil.strNull(resultArray[2]));
					resultRow.put("version", CustomUtil.strNull(resultArray[3]));
					resultRow.put("title",LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[4]), locale.getLanguage()));
					returnList.add(resultRow);
				}
			}
		} 
		return returnList;
	}
	
	/**
	 * 카테고리 배열 생성 통합검색은 사용 안함. 콘텐츠는 포탈에서는 카테고리별로 조회하지 않으므로 parentCategory 가 0인
	 * 사이트(포탈)에 대해서 null을 반환함.
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @return long[]
	 * @throws PortalException
	 * @throws SystemException
	 */
	public long[] makeCategoryEntryList(long companyGroupId, long groupId) throws PortalException,
		SystemException{

		//
		try{
			// custom qurey에서 조회할 customId 값 long 배열을 만듬
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();

			String categoryStr = "";
			if(parentGroupId != 0){ // 포탈이 아닐때 사이트의 카테고리를 long배열로 생성
				AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
					EdisonAssetCategory.GLOBAL_DOMAIN);

				AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
				List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry
					.getEntryId()); // 사이트에서 사용중인 category

				for(AssetCategory aCategory : aCategoryList){
					// 글로벌 도메인이면서 parentCategory값
					if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory
						.getParentCategoryId() != 0){
						// parent 카테고리 아이디를 만듬.
						if(categoryStr.equals("")){

							categoryStr += aCategory.getCategoryId();
						}else{
							categoryStr += "," + aCategory.getCategoryId();
						}
					}
				}
			}
			return StringUtil.split(categoryStr, 0l);
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
	
	public ScienceAppFavorite deleteFavoriteApp(long scienceAppId, long userId) throws NoSuchScienceAppFavoriteException, SystemException {
		return scienceAppFavoritePersistence.remove(new ScienceAppFavoritePK(scienceAppId, userId));
	}
	
	public void deleteFavoriteApp(long scienceAppId) throws SystemException {
		scienceAppFavoritePersistence.removeByselectFavoriteList(scienceAppId);
	}
	
}