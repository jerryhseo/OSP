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

package org.kisti.edison.search.service.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SearchCondition service. Represents a row in the &quot;EDSEARCH_SearchCondition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.kisti.edison.search.service.model.impl.SearchConditionImpl}.
 * </p>
 *
 * @author yjpark
 * @see SearchCondition
 * @see org.kisti.edison.search.service.model.impl.SearchConditionImpl
 * @see org.kisti.edison.search.service.model.impl.SearchConditionModelImpl
 * @generated
 */
public interface SearchConditionModel extends BaseModel<SearchCondition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a search condition model instance should use the {@link SearchCondition} interface instead.
	 */

	/**
	 * Returns the primary key of this search condition.
	 *
	 * @return the primary key of this search condition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this search condition.
	 *
	 * @param primaryKey the primary key of this search condition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this search condition.
	 *
	 * @return the ID of this search condition
	 */
	public long getId();

	/**
	 * Sets the ID of this search condition.
	 *
	 * @param id the ID of this search condition
	 */
	public void setId(long id);

	/**
	 * Returns the search keyword of this search condition.
	 *
	 * @return the search keyword of this search condition
	 */
	@AutoEscape
	public String getSearchKeyword();

	/**
	 * Sets the search keyword of this search condition.
	 *
	 * @param searchKeyword the search keyword of this search condition
	 */
	public void setSearchKeyword(String searchKeyword);

	/**
	 * Returns the area science app of this search condition.
	 *
	 * @return the area science app of this search condition
	 */
	public boolean getAreaScienceApp();

	/**
	 * Returns <code>true</code> if this search condition is area science app.
	 *
	 * @return <code>true</code> if this search condition is area science app; <code>false</code> otherwise
	 */
	public boolean isAreaScienceApp();

	/**
	 * Sets whether this search condition is area science app.
	 *
	 * @param areaScienceApp the area science app of this search condition
	 */
	public void setAreaScienceApp(boolean areaScienceApp);

	/**
	 * Returns the area contents of this search condition.
	 *
	 * @return the area contents of this search condition
	 */
	public boolean getAreaContents();

	/**
	 * Returns <code>true</code> if this search condition is area contents.
	 *
	 * @return <code>true</code> if this search condition is area contents; <code>false</code> otherwise
	 */
	public boolean isAreaContents();

	/**
	 * Sets whether this search condition is area contents.
	 *
	 * @param areaContents the area contents of this search condition
	 */
	public void setAreaContents(boolean areaContents);

	/**
	 * Returns the area simulation project of this search condition.
	 *
	 * @return the area simulation project of this search condition
	 */
	public boolean getAreaSimulationProject();

	/**
	 * Returns <code>true</code> if this search condition is area simulation project.
	 *
	 * @return <code>true</code> if this search condition is area simulation project; <code>false</code> otherwise
	 */
	public boolean isAreaSimulationProject();

	/**
	 * Sets whether this search condition is area simulation project.
	 *
	 * @param areaSimulationProject the area simulation project of this search condition
	 */
	public void setAreaSimulationProject(boolean areaSimulationProject);

	/**
	 * Returns the area science data of this search condition.
	 *
	 * @return the area science data of this search condition
	 */
	public boolean getAreaScienceData();

	/**
	 * Returns <code>true</code> if this search condition is area science data.
	 *
	 * @return <code>true</code> if this search condition is area science data; <code>false</code> otherwise
	 */
	public boolean isAreaScienceData();

	/**
	 * Sets whether this search condition is area science data.
	 *
	 * @param areaScienceData the area science data of this search condition
	 */
	public void setAreaScienceData(boolean areaScienceData);

	/**
	 * Returns the parent category of this search condition.
	 *
	 * @return the parent category of this search condition
	 */
	public boolean getParentCategory();

	/**
	 * Returns <code>true</code> if this search condition is parent category.
	 *
	 * @return <code>true</code> if this search condition is parent category; <code>false</code> otherwise
	 */
	public boolean isParentCategory();

	/**
	 * Sets whether this search condition is parent category.
	 *
	 * @param parentCategory the parent category of this search condition
	 */
	public void setParentCategory(boolean parentCategory);

	/**
	 * Returns the category ID of this search condition.
	 *
	 * @return the category ID of this search condition
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this search condition.
	 *
	 * @param categoryId the category ID of this search condition
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the company group ID of this search condition.
	 *
	 * @return the company group ID of this search condition
	 */
	public long getCompanyGroupId();

	/**
	 * Sets the company group ID of this search condition.
	 *
	 * @param companyGroupId the company group ID of this search condition
	 */
	public void setCompanyGroupId(long companyGroupId);

	/**
	 * Returns the group ID of this search condition.
	 *
	 * @return the group ID of this search condition
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this search condition.
	 *
	 * @param groupId the group ID of this search condition
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the current page of this search condition.
	 *
	 * @return the current page of this search condition
	 */
	public int getCurrentPage();

	/**
	 * Sets the current page of this search condition.
	 *
	 * @param currentPage the current page of this search condition
	 */
	public void setCurrentPage(int currentPage);

	/**
	 * Returns the list size of this search condition.
	 *
	 * @return the list size of this search condition
	 */
	public int getListSize();

	/**
	 * Sets the list size of this search condition.
	 *
	 * @param listSize the list size of this search condition
	 */
	public void setListSize(int listSize);

	/**
	 * Returns the block size of this search condition.
	 *
	 * @return the block size of this search condition
	 */
	public int getBlockSize();

	/**
	 * Sets the block size of this search condition.
	 *
	 * @param blockSize the block size of this search condition
	 */
	public void setBlockSize(int blockSize);

	/**
	 * Returns the classnote of this search condition.
	 *
	 * @return the classnote of this search condition
	 */
	public boolean getClassnote();

	/**
	 * Returns <code>true</code> if this search condition is classnote.
	 *
	 * @return <code>true</code> if this search condition is classnote; <code>false</code> otherwise
	 */
	public boolean isClassnote();

	/**
	 * Sets whether this search condition is classnote.
	 *
	 * @param classnote the classnote of this search condition
	 */
	public void setClassnote(boolean classnote);

	/**
	 * Returns the manual of this search condition.
	 *
	 * @return the manual of this search condition
	 */
	public boolean getManual();

	/**
	 * Returns <code>true</code> if this search condition is manual.
	 *
	 * @return <code>true</code> if this search condition is manual; <code>false</code> otherwise
	 */
	public boolean isManual();

	/**
	 * Sets whether this search condition is manual.
	 *
	 * @param manual the manual of this search condition
	 */
	public void setManual(boolean manual);

	/**
	 * Returns the reference of this search condition.
	 *
	 * @return the reference of this search condition
	 */
	public boolean getReference();

	/**
	 * Returns <code>true</code> if this search condition is reference.
	 *
	 * @return <code>true</code> if this search condition is reference; <code>false</code> otherwise
	 */
	public boolean isReference();

	/**
	 * Sets whether this search condition is reference.
	 *
	 * @param reference the reference of this search condition
	 */
	public void setReference(boolean reference);

	/**
	 * Returns the advanced of this search condition.
	 *
	 * @return the advanced of this search condition
	 */
	public boolean getAdvanced();

	/**
	 * Returns <code>true</code> if this search condition is advanced.
	 *
	 * @return <code>true</code> if this search condition is advanced; <code>false</code> otherwise
	 */
	public boolean isAdvanced();

	/**
	 * Sets whether this search condition is advanced.
	 *
	 * @param advanced the advanced of this search condition
	 */
	public void setAdvanced(boolean advanced);

	/**
	 * Returns the sort order of this search condition.
	 *
	 * @return the sort order of this search condition
	 */
	@AutoEscape
	public String getSortOrder();

	/**
	 * Sets the sort order of this search condition.
	 *
	 * @param sortOrder the sort order of this search condition
	 */
	public void setSortOrder(String sortOrder);

	/**
	 * Returns the sort field of this search condition.
	 *
	 * @return the sort field of this search condition
	 */
	@AutoEscape
	public String getSortField();

	/**
	 * Sets the sort field of this search condition.
	 *
	 * @param sortField the sort field of this search condition
	 */
	public void setSortField(String sortField);

	/**
	 * Returns the solver of this search condition.
	 *
	 * @return the solver of this search condition
	 */
	public boolean getSolver();

	/**
	 * Returns <code>true</code> if this search condition is solver.
	 *
	 * @return <code>true</code> if this search condition is solver; <code>false</code> otherwise
	 */
	public boolean isSolver();

	/**
	 * Sets whether this search condition is solver.
	 *
	 * @param Solver the solver of this search condition
	 */
	public void setSolver(boolean Solver);

	/**
	 * Returns the converter of this search condition.
	 *
	 * @return the converter of this search condition
	 */
	public boolean getConverter();

	/**
	 * Returns <code>true</code> if this search condition is converter.
	 *
	 * @return <code>true</code> if this search condition is converter; <code>false</code> otherwise
	 */
	public boolean isConverter();

	/**
	 * Sets whether this search condition is converter.
	 *
	 * @param Converter the converter of this search condition
	 */
	public void setConverter(boolean Converter);

	/**
	 * Returns the editor of this search condition.
	 *
	 * @return the editor of this search condition
	 */
	public boolean getEditor();

	/**
	 * Returns <code>true</code> if this search condition is editor.
	 *
	 * @return <code>true</code> if this search condition is editor; <code>false</code> otherwise
	 */
	public boolean isEditor();

	/**
	 * Sets whether this search condition is editor.
	 *
	 * @param Editor the editor of this search condition
	 */
	public void setEditor(boolean Editor);

	/**
	 * Returns the analyzer of this search condition.
	 *
	 * @return the analyzer of this search condition
	 */
	public boolean getAnalyzer();

	/**
	 * Returns <code>true</code> if this search condition is analyzer.
	 *
	 * @return <code>true</code> if this search condition is analyzer; <code>false</code> otherwise
	 */
	public boolean isAnalyzer();

	/**
	 * Sets whether this search condition is analyzer.
	 *
	 * @param Analyzer the analyzer of this search condition
	 */
	public void setAnalyzer(boolean Analyzer);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		org.kisti.edison.search.service.model.SearchCondition searchCondition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<org.kisti.edison.search.service.model.SearchCondition> toCacheModel();

	@Override
	public org.kisti.edison.search.service.model.SearchCondition toEscapedModel();

	@Override
	public org.kisti.edison.search.service.model.SearchCondition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}