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

import java.util.List;
import java.util.Map;

/**
 * The extended model implementation for the Search service. Represents a row in
 * the &quot;EDSEARCH_Search&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link org.kisti.edison.search.service.model.Search} interface.
 * </p>
 *
 * @author yjpark
 */
public class SearchImpl extends SearchBaseImpl{
  /*
   * NOTE FOR DEVELOPERS:
   *
   * Never reference this class directly. All methods that expect a search model
   * instance should use the {@link
   * org.kisti.edison.search.service.model.Search} interface instead.
   */
  public SearchImpl(){
  }
  
  private int appCount = 0;
  private int contentCount = 0;
  private int projectCount = 0;
  private int dataCount = 0;
  private List<Map<String, Object>> appResults;
  private List<Map<String, Object>> contentResults;
  private List<Map<String, Object>> projectResults;
  private List<Map<String, Object>> dataResults;
  public int getAppCount(){
    return appCount;
  }
  public void setAppCount(int appCount){
    this.appCount = appCount;
  }
  public int getContentCount(){
    return contentCount;
  }
  public void setContentCount(int contentCount){
    this.contentCount = contentCount;
  }
  public int getProjectCount(){
    return projectCount;
  }
  public void setProjectCount(int projectCount){
    this.projectCount = projectCount;
  }
  public int getDataCount(){
    return dataCount;
  }
  public void setDataCount(int dataCount){
    this.dataCount = dataCount;
  }
  public List<Map<String, Object>> getAppResults(){
    return appResults;
  }
  public void setAppResults(List<Map<String, Object>> appResults){
    this.appResults = appResults;
  }
  public List<Map<String, Object>> getContentResults(){
    return contentResults;
  }
  public void setContentResults(List<Map<String, Object>> contentResults){
    this.contentResults = contentResults;
  }
  public List<Map<String, Object>> getProjectResults(){
    return projectResults;
  }
  public void setProjectResults(List<Map<String, Object>> projectResults){
    this.projectResults = projectResults;
  }
  public List<Map<String, Object>> getDataResults(){
    return dataResults;
  }
  public void setDataResults(List<Map<String, Object>> dataResults){
    this.dataResults = dataResults;
  }
}