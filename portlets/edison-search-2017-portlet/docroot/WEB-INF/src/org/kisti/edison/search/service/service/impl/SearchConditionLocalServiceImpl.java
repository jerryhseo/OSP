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

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.kisti.edison.search.service.model.SearchCondition;
import org.kisti.edison.search.service.service.base.SearchConditionLocalServiceBaseImpl;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * The implementation of the search condition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.search.service.service.SearchConditionLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author yjpark
 * @see org.kisti.edison.search.service.service.base.SearchConditionLocalServiceBaseImpl
 * @see org.kisti.edison.search.service.service.SearchConditionLocalServiceUtil
 */
public class SearchConditionLocalServiceImpl
	extends SearchConditionLocalServiceBaseImpl {
	/*
   * NOTE FOR DEVELOPERS:
   *
   * Never reference this interface directly. Always use {@link
   * org.kisti.edison.search.service.service.SearchConditionLocalServiceUtil} to
   * access the search condition local service.
   */
  public SearchCondition createSearchCondition(Map<String, Object> modelAttribute){
    SearchCondition searchCondition = this.createSearchCondition(0);
    if(modelAttribute != null && !modelAttribute.isEmpty()){
      searchCondition.setCustomModelAttributes(modelAttribute);
    }
    return searchCondition;
  }
  
  //companyGroupId, groupId, locale, categoryIds, searchText, begin, end
  public SearchCondition createSearchCondition(Map<String, Object> modelAttribute,
      long companyGroupId, long groupId, Locale locale){
    SearchCondition searchCondition = this.createSearchCondition(0);
    if(modelAttribute != null && !modelAttribute.isEmpty()){
      searchCondition.setCustomModelAttributes(modelAttribute);
      searchCondition
          .setSearchKeyword(StringUtils.trimWhitespace(searchCondition.getSearchKeyword()));
    }
    searchCondition.setCompanyGroupId(companyGroupId);
    searchCondition.setGroupId(groupId);
    searchCondition.setLocale(locale);
    return searchCondition;
  }
  
  public String createSearchParameters(
      SearchCondition searchCondition, String namespace, String p_p_id){
    StringBuffer sb = new StringBuffer();
    sb.append("?p_p_id=");
    sb.append(p_p_id);
    
    Map<String, Object> searchConditionMap = searchCondition.getModelAttributes();
    for(String key : searchConditionMap.keySet()){
      Object value = searchConditionMap.get(key);
      if(key.equals("companyGroupId") || key.equals("currentPage") || key.equals("id")
          || key.equals("groupId") || key.equals("blockSize") || key.equals("listSize")
          || value == null){
        continue;
      }
      if(value instanceof String && !StringUtils.hasText((String)value)){
        continue;
      }
      sb.append("&");
      sb.append(namespace);
      sb.append(key);
      sb.append("=");
      sb.append(String.valueOf(value));
    }
    return sb.toString();
  }

  public SearchCondition createSearchCondition(PortletRequest request)
      throws PortalException, SystemException{
    ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    long companyGroupId = PortalUtil.getCompany(request).getGroupId();
    long groupId = PortalUtil.getScopeGroupId(request);
    Locale locale = themeDisplay.getLocale();
    return createSearchCondition(transformParameterToModelAttribute(request.getParameterMap()),
        companyGroupId, groupId, locale);
  }
  
  public Map<String, Object> transformParameterToModelAttribute(Map<String, String[]> params){
    Map<String, Object> result = Maps.newHashMap();
    if(params != null && !params.isEmpty()){
      for(String key : params.keySet()){
        String[] value = params.get(key);
        if(value != null && value.length > 0){
          result.put(key, value[0]);
        }
      }
    }
    return result;
  }
  
}