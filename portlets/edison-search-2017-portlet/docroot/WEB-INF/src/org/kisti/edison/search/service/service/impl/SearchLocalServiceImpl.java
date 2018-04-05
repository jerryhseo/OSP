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

package org.kisti.edison.search.service.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.search.service.model.Search;
import org.kisti.edison.search.service.model.SearchCondition;
import org.kisti.edison.search.service.service.base.SearchLocalServiceBaseImpl;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Longs;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.DynamicResourceRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Group;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.rits.cloning.Cloner;
import com.sdr.metadata.service.CollectionLocalServiceUtil;

/**
 * The implementation of the search local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.search.service.service.SearchLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author yjpark
 * @see org.kisti.edison.search.service.service.base.SearchLocalServiceBaseImpl
 * @see org.kisti.edison.search.service.service.SearchLocalServiceUtil
 */
public class SearchLocalServiceImpl extends SearchLocalServiceBaseImpl{
  /*
   * NOTE FOR DEVELOPERS:
   *
   * Never reference this interface directly. Always use {@link
   * org.kisti.edison.search.service.service.SearchLocalServiceUtil} to access
   * the search local service.
   */
  
  private static Log log = LogFactory.getLog(SearchLocalServiceImpl.class);
  private static final String DATA_COLLECTION_SEARCH_SELECT = "titleDescription";
  private static final String DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_ASC = "asc";
  private static final String DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_DESC = "desc";
  private static final String DATA_COLLECTION_SEARCH_SORT_ORDER_LATEST = "lastest";
  private static final String DATA_COLLECTION_SEARCH_SORT_ORDER_OLDEST = "oldest";
  
  /** deep copy cloner **/
  private Cloner cloner = new Cloner();
  
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

  /**
   * 사이트 분야 카테고리 조회
   */
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
  
  /**
   * 카테고리 JSTree 데이터 조회 
   */
  public String getCategoriesJsonString(long companyGroupId, long groupId, Locale locale) 
      throws PortalException, SystemException{
    List<Map<String, Object>> results = getCategories(companyGroupId, groupId, locale);
    try{
      return list2JsonString(results);
    }catch (IOException e){
      throw new SystemException(e);
    }
  }

  public List<Map<String, Object>> getCategories(long companyGroupId, long groupId, Locale locale)
      throws PortalException, SystemException{
    List<Map<String, Object>> results = Lists.newArrayList();
    List<AssetCategory> lv1Categories = getLv1Categories(companyGroupId, groupId, locale);
    List<Map<String, Object>> rootCategories = assetCategoryToJstreeModel(lv1Categories, locale);
    results.addAll(rootCategories);
    for(Map<String, Object> lv1 : rootCategories){
      long lv1CategoryId = GetterUtil.getLong(lv1.get("categoryId"));
      List<Map<String, Object>> subCategories =
          assetCategoryToJstreeModel(
              getSiteAssetCategoriesByParentId(companyGroupId, groupId, lv1CategoryId), locale);
      results.addAll(subCategories);
    }
    return results;
  }
  
  private String list2JsonString(List<?> list) throws IOException{
    ObjectMapper om = new ObjectMapper();
    try{
      return om.writeValueAsString(list);
    }catch (IOException e){
      throw e;
    }
  }
  
  public List<Map<String, Object>> assetCategoryToJstreeModel(
      List<AssetCategory> assetCategories, Locale locale) throws PortalException, SystemException{
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for(AssetCategory assetCategory : assetCategories){
      Map<String, Object> categoryMap = assetCategoryToJstreeModel(assetCategory, locale);
      result.add(categoryMap);
    }
    return result;
  }

  public Map<String, Object> assetCategoryToJstreeModel(
      AssetCategory assetCategory, Locale locale) throws PortalException, SystemException{
    Map<String, Object> modelMap = assetCategory.getModelAttributes(); 
    if(!assetCategory.isRootCategory()){
      AssetCategoryProperty assetCategoryProperty = AssetCategoryPropertyLocalServiceUtil
          .getCategoryProperty(assetCategory.getCategoryId(), "IMAGE");
      modelMap.put(assetCategoryProperty.getKey().toLowerCase(), assetCategoryProperty.getValue());
    }
    modelMap.put("data", cloner.deepClone(modelMap));
    modelMap.put("id", assetCategory.getCategoryId());
    modelMap.put("title", assetCategory.getTitle(locale));
    modelMap.put("description", assetCategory.getDescription(locale));
    modelMap.put("text", assetCategory.getTitle(locale));
    if(assetCategory.getParentCategoryId() == 0){
      modelMap.put("parent", "#");
      modelMap.put("type", "category");
    }else{
      modelMap.put("parent", assetCategory.getParentCategoryId());
      modelMap.put("type", "subCategory");
    }
    return modelMap;
  }
  
  public Search appSearch(SearchCondition searchCondition)
      throws PortalException, SystemException{
    return appSearch(searchCondition, null);
  }
  
  public Search appSearch(SearchCondition searchCondition, Search searchResults)
      throws PortalException, SystemException{
    long[] categoryIds = getCategoryIdArrays(searchCondition);
    String[] appTypes = searchCondition.getAppTypes();
    if(searchResults == null){
      searchResults = this.createSearch(0);
    }
    int count = ScienceAppLocalServiceUtil.countScienceAppFromExplore(
        searchCondition.getCompanyGroupId(), searchCondition.getGroupId(),
        searchCondition.getLocale(),  appTypes, categoryIds, searchCondition.getSearchKeyword());
    List<Map<String, Object>> apps = ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(
        searchCondition.getCompanyGroupId(), searchCondition.getGroupId(),
        searchCondition.getLocale(), appTypes, categoryIds, searchCondition.getSearchKeyword(),
        searchCondition.getStart(), searchCondition.getListSize(),
        searchCondition.getSortField(), searchCondition.getSortOrder());
    if(count > 0){
      searchResults.setAppCount(count);
      searchResults.setAppResults(apps);
    }
    return searchResults;
  }
  
  public Search contentSearch(SearchCondition searchCondition)
      throws PortalException, SystemException{
    return contentSearch(searchCondition, null);
  }
  
  public Search contentSearch(SearchCondition searchCondition, Search searchResults)
      throws PortalException, SystemException{
    long[] categoryIds = getCategoryIdArrays(searchCondition);
    boolean categoryJoinFlag = categoryIds != null ? true : false;
    long[] contentDiv = searchCondition.getContentDivisions();
    if(searchResults == null){
      searchResults = this.createSearch(0);
    }
    try{
      int count = ContentLocalServiceUtil.retrieveCountContent(categoryIds,
          searchCondition.getSearchKeyword(), contentDiv, searchCondition.getLocale().toString(), 
          categoryJoinFlag, true);
      List<Map<String, Object>> contents = ContentLocalServiceUtil.retrieveListContent(categoryIds,
          searchCondition.getSearchKeyword(), contentDiv,
          searchCondition.getStart(), searchCondition.getListSize(),
          searchCondition.getLocale(), categoryJoinFlag, true,
          searchCondition.getSortField(), searchCondition.getSortOrder());
      if(count > 0){
        searchResults.setContentCount(count);
        searchResults.setContentResults(contents);
      }
    }catch(Exception e){
      throw new SystemException(e);
    }
    return searchResults;
  }
  
  public Search projectSearch(SearchCondition searchCondition)
      throws PortalException, SystemException{
    return projectSearch(searchCondition, null);
  }
  
  public Search projectSearch(SearchCondition searchCondition, Search searchResults)
      throws PortalException, SystemException{
    List<Long> categoryIds = getCategoryIds(searchCondition);
    if(searchResults == null){
      searchResults = this.createSearch(0);
    }
    try{
      int count = SimulationProjectLocalServiceUtil
          .getCustomIntegratedSearchSimulationProjectCount(
              searchCondition.getSearchKeyword(), categoryIds, searchCondition.getLocale());
      List<Map<String, Object>> projects = SimulationProjectLocalServiceUtil
          .getCustomIntegratedSearchSimulationProjectList(
              searchCondition.getStart(), searchCondition.getListSize(),
              searchCondition.getSearchKeyword(), searchCondition.getLocale(), categoryIds);
      if(count > 0){
        searchResults.setProjectCount(count);
        searchResults.setProjectResults(projects);
      }
    }catch(ParseException e){
      throw new SystemException(e);
    }
    return searchResults;
  }
  
  public Search dataSearch(SearchCondition searchCondition)
      throws PortalException, SystemException{
    return dataSearch(searchCondition, null);
  }
  
    public Search dataSearch(ResourceRequest request, ResourceResponse response, SearchCondition searchCondition)
        throws PortalException, SystemException{
        return dataSearch(request, response, searchCondition, null);
    }
  
    @SuppressWarnings("unchecked")
    public Search dataSearch(ResourceRequest request, ResourceResponse response, SearchCondition searchCondition, Search searchResults)
        throws PortalException, SystemException{
        if(searchResults == null){
            searchResults = this.createSearch(0);
        }
        List<Long> categoryIds = getCategoryIds(searchCondition);

        DynamicResourceRequest newRequest = new DynamicResourceRequest(request);
        newRequest.setParameter("keywords", searchCondition.getSearchKeyword());
        newRequest.setParameter("cur", String.valueOf(searchCondition.getCurrentPage()));
        newRequest.setParameter("searchSelect", DATA_COLLECTION_SEARCH_SELECT);
        newRequest.setParameter("delta", String.valueOf(searchCondition.getListSize()));
        
        if(searchCondition.SORT_FIELD_NAME().equals(searchCondition.getSortField())){
            if(searchCondition.SORT_ORDER_ASC().equals(searchCondition.getSortOrder())){
                newRequest.setParameter("sort", DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_ASC);
            }else{
                newRequest.setParameter("sort", DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_DESC);
            }
        }else if(searchCondition.SORT_FIELD_CREATED().equals(searchCondition.getSortField())){
            if(searchCondition.SORT_ORDER_ASC().equals(searchCondition.getSortOrder())){
                newRequest.setParameter("sort", DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_ASC);
            }else{
                newRequest.setParameter("sort", DATA_COLLECTION_SEARCH_SORT_ORDER_TITLE_DESC);
            }
        }
        
        if(categoryIds != null){
            newRequest.setParameterValues("categories",
                Iterables.toArray(
                    Lists.transform(categoryIds, Functions.toStringFunction()), String.class));
        }
        
        Map<String, Object> result = CollectionLocalServiceUtil.search(newRequest, response);
        int count = GetterUtil.getInteger(result.get("total"));
        if(count > 0){
            searchResults.setDataCount(count);
            searchResults.setDataResults((List<Map<String, Object>>) result.get("collectionList"));
        }
        return searchResults;
    }  
  
  public Search dataSearch(SearchCondition searchCondition, Search searchResults)
      throws PortalException, SystemException{
    long[] categoryIds = getCategoryIdArrays(searchCondition);
    if(searchResults == null){
      searchResults = this.createSearch(0);
    }
    int count = DataCollectionLocalServiceUtil
        .retrieveCountDataCollection(
            categoryIds, searchCondition.getSearchKeyword(), 
            searchCondition.getLocale()); 
    List<Map<String, Object>> datas = DataCollectionLocalServiceUtil
        .retrieveListDataCollection(
            categoryIds, searchCondition.getSearchKeyword(), 
            searchCondition.getStart(), searchCondition.getListSize(),
            searchCondition.getLocale());
    if(count > 0){
      searchResults.setDataCount(count);
      searchResults.setDataResults(datas);
    }
    return searchResults;
  }
  
  public Search totalSearch(ResourceRequest request, ResourceResponse response, SearchCondition searchCondition)
    throws Exception{
    Search searchResults = this.createSearch(0);
    if(searchCondition.getAreaScienceApp()){
      appSearch(searchCondition, searchResults);
    }
    if(searchCondition.getAreaContents()){
      contentSearch(searchCondition, searchResults);
    }
    if(searchCondition.getAreaSimulationProject()){
      projectSearch(searchCondition, searchResults);
    }
    if(searchCondition.getAreaScienceData()){
      dataSearch(request, response, searchCondition, searchResults);
    }
    return searchResults;
  }
  
  
  public long[] getCategoryIdArrays(SearchCondition searchCondition) throws SystemException{
    List<Long> ids = getCategoryIds(searchCondition);
    if(ids == null){
      return null;
    }
    return ArrayUtils.toPrimitive(Iterables.toArray(ids, Long.class));
  }
  
  public List<Long> getCategoryIds(SearchCondition searchCondition) throws SystemException{
    if(searchCondition.isParentCategory()){
      List<AssetCategory> subCategories = AssetCategoryLocalServiceUtil
          .getChildCategories(searchCondition.getCategoryId());
      return Lists.transform(subCategories, new Function<AssetCategory, Long>(){
        @Override
        public Long apply(AssetCategory assetCategory){
          return assetCategory.getCategoryId();
        }
      });
    }else if(searchCondition.getCategoryId() > 0){
      return Lists.newArrayList(searchCondition.getCategoryId());
    }
    return null;
  }
}