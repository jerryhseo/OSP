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

package org.kisti.edison.search.service.model.impl;

import java.sql.Types;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The extended model implementation for the SearchCondition service. Represents
 * a row in the &quot;EDSEARCH_SearchCondition&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link org.kisti.edison.search.service.model.SearchCondition}
 * interface.
 * </p>
 *
 * @author yjpark
 */
public class SearchConditionImpl extends SearchConditionBaseImpl{
  /*
   * NOTE FOR DEVELOPERS:
   *
   * Never reference this class directly. All methods that expect a search
   * condition model instance should use the {@link
   * org.kisti.edison.search.service.model.SearchCondition} interface instead.
   */
  public static final long DIV_CONTENTS_CLASSNOTE = 2001001;
  public static final long DIV_CONTENTS_MANUAL = 2001002;
  public static final long DIV_CONTENTS_REFERENCE = 2001003;
  public static final long DIV_CONTENTS_ADVANCED = 2001004;
  
  public SearchConditionImpl(){
    setBlockSize(10);
    setListSize(10);
    setCurrentPage(1);
  }
  private Locale locale;
  public Locale getLocale(){
    return locale;
  }
  public void setLocale(Locale locale){
    this.locale = locale;
  }
  public int getStart(){
    return ((getCurrentPage() - 1) * getListSize());
  }
  
  public long[] getContentDivisions(){
    List<Long> divisions = Lists.newArrayList(); 
    if(isClassnote()){
      divisions.add(DIV_CONTENTS_CLASSNOTE);
    }
    if(isManual()){
      divisions.add(DIV_CONTENTS_MANUAL);
    }
    if(isReference()){
      divisions.add(DIV_CONTENTS_REFERENCE);
    }
    if(isAdvanced()){
      divisions.add(DIV_CONTENTS_ADVANCED);
    }
    if(divisions.isEmpty() || divisions.size() == 4){
      return null;
    }
    return ArrayUtils.toPrimitive(Iterables.toArray(divisions, Long.class));
  }
  
  public String[] getAppTypes(){
    List<String> divisions = Lists.newArrayList(); 
    if(isSolver()){
      divisions.add(ScienceAppConstants.APP_TYPE_SOLVER);
    }
    if(isAnalyzer()){
      divisions.add(ScienceAppConstants.APP_TYPE_ANALYZER);
    }
    if(isConverter()){
      divisions.add(ScienceAppConstants.APP_TYPE_CONVERTER);
    }
    if(isEditor()){
      divisions.add(ScienceAppConstants.APP_TYPE_EDITOR);
    }
    if(divisions.isEmpty() || divisions.size() == 4){
      return null;
    }
    return Iterables.toArray(divisions, String.class);
  }
  
  public void setCustomModelAttributes(Map<String, Object> attributes){
    for(Object[] column : TABLE_COLUMNS){
      String columnName = (String) column[0];
      int type = (int) column[1];
      Object attr = attributes.get(columnName);
      if(attr != null && attr instanceof String){
        String attrString = (String) attr;
        switch(type){
        case Types.INTEGER:
          if(StringUtils.isNumeric(attrString)){
            attributes.put(columnName, Integer.valueOf(attrString));
          }
          break;
        case Types.BIGINT:
          if(StringUtils.isNumeric(attrString)){
            attributes.put(columnName, Long.valueOf(attrString));
          }
          break;
        case Types.BOOLEAN:
          if(isBooleanString(attrString)){
            attributes.put(columnName, Boolean.valueOf(attrString));
          }
          break;
        }
      }
    }
    super.setModelAttributes(attributes);
  }
  
  private boolean isBooleanString(Object obj){
    return ObjectUtils.nullSafeEquals("true", obj) || ObjectUtils.nullSafeEquals("false", obj);
  }

}