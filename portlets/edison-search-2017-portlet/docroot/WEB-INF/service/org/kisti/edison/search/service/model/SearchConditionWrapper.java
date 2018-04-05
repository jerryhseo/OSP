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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SearchCondition}.
 * </p>
 *
 * @author yjpark
 * @see SearchCondition
 * @generated
 */
public class SearchConditionWrapper implements SearchCondition,
	ModelWrapper<SearchCondition> {
	public SearchConditionWrapper(SearchCondition searchCondition) {
		_searchCondition = searchCondition;
	}

	@Override
	public Class<?> getModelClass() {
		return SearchCondition.class;
	}

	@Override
	public String getModelClassName() {
		return SearchCondition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("searchKeyword", getSearchKeyword());
		attributes.put("areaScienceApp", getAreaScienceApp());
		attributes.put("areaContents", getAreaContents());
		attributes.put("areaSimulationProject", getAreaSimulationProject());
		attributes.put("areaScienceData", getAreaScienceData());
		attributes.put("parentCategory", getParentCategory());
		attributes.put("categoryId", getCategoryId());
		attributes.put("companyGroupId", getCompanyGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("currentPage", getCurrentPage());
		attributes.put("listSize", getListSize());
		attributes.put("blockSize", getBlockSize());
		attributes.put("classnote", getClassnote());
		attributes.put("manual", getManual());
		attributes.put("reference", getReference());
		attributes.put("advanced", getAdvanced());
		attributes.put("sortOrder", getSortOrder());
		attributes.put("sortField", getSortField());
		attributes.put("Solver", getSolver());
		attributes.put("Converter", getConverter());
		attributes.put("Editor", getEditor());
		attributes.put("Analyzer", getAnalyzer());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String searchKeyword = (String)attributes.get("searchKeyword");

		if (searchKeyword != null) {
			setSearchKeyword(searchKeyword);
		}

		Boolean areaScienceApp = (Boolean)attributes.get("areaScienceApp");

		if (areaScienceApp != null) {
			setAreaScienceApp(areaScienceApp);
		}

		Boolean areaContents = (Boolean)attributes.get("areaContents");

		if (areaContents != null) {
			setAreaContents(areaContents);
		}

		Boolean areaSimulationProject = (Boolean)attributes.get(
				"areaSimulationProject");

		if (areaSimulationProject != null) {
			setAreaSimulationProject(areaSimulationProject);
		}

		Boolean areaScienceData = (Boolean)attributes.get("areaScienceData");

		if (areaScienceData != null) {
			setAreaScienceData(areaScienceData);
		}

		Boolean parentCategory = (Boolean)attributes.get("parentCategory");

		if (parentCategory != null) {
			setParentCategory(parentCategory);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		Long companyGroupId = (Long)attributes.get("companyGroupId");

		if (companyGroupId != null) {
			setCompanyGroupId(companyGroupId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Integer currentPage = (Integer)attributes.get("currentPage");

		if (currentPage != null) {
			setCurrentPage(currentPage);
		}

		Integer listSize = (Integer)attributes.get("listSize");

		if (listSize != null) {
			setListSize(listSize);
		}

		Integer blockSize = (Integer)attributes.get("blockSize");

		if (blockSize != null) {
			setBlockSize(blockSize);
		}

		Boolean classnote = (Boolean)attributes.get("classnote");

		if (classnote != null) {
			setClassnote(classnote);
		}

		Boolean manual = (Boolean)attributes.get("manual");

		if (manual != null) {
			setManual(manual);
		}

		Boolean reference = (Boolean)attributes.get("reference");

		if (reference != null) {
			setReference(reference);
		}

		Boolean advanced = (Boolean)attributes.get("advanced");

		if (advanced != null) {
			setAdvanced(advanced);
		}

		String sortOrder = (String)attributes.get("sortOrder");

		if (sortOrder != null) {
			setSortOrder(sortOrder);
		}

		String sortField = (String)attributes.get("sortField");

		if (sortField != null) {
			setSortField(sortField);
		}

		Boolean Solver = (Boolean)attributes.get("Solver");

		if (Solver != null) {
			setSolver(Solver);
		}

		Boolean Converter = (Boolean)attributes.get("Converter");

		if (Converter != null) {
			setConverter(Converter);
		}

		Boolean Editor = (Boolean)attributes.get("Editor");

		if (Editor != null) {
			setEditor(Editor);
		}

		Boolean Analyzer = (Boolean)attributes.get("Analyzer");

		if (Analyzer != null) {
			setAnalyzer(Analyzer);
		}
	}

	/**
	* Returns the primary key of this search condition.
	*
	* @return the primary key of this search condition
	*/
	@Override
	public long getPrimaryKey() {
		return _searchCondition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this search condition.
	*
	* @param primaryKey the primary key of this search condition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_searchCondition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this search condition.
	*
	* @return the ID of this search condition
	*/
	@Override
	public long getId() {
		return _searchCondition.getId();
	}

	/**
	* Sets the ID of this search condition.
	*
	* @param id the ID of this search condition
	*/
	@Override
	public void setId(long id) {
		_searchCondition.setId(id);
	}

	/**
	* Returns the search keyword of this search condition.
	*
	* @return the search keyword of this search condition
	*/
	@Override
	public java.lang.String getSearchKeyword() {
		return _searchCondition.getSearchKeyword();
	}

	/**
	* Sets the search keyword of this search condition.
	*
	* @param searchKeyword the search keyword of this search condition
	*/
	@Override
	public void setSearchKeyword(java.lang.String searchKeyword) {
		_searchCondition.setSearchKeyword(searchKeyword);
	}

	/**
	* Returns the area science app of this search condition.
	*
	* @return the area science app of this search condition
	*/
	@Override
	public boolean getAreaScienceApp() {
		return _searchCondition.getAreaScienceApp();
	}

	/**
	* Returns <code>true</code> if this search condition is area science app.
	*
	* @return <code>true</code> if this search condition is area science app; <code>false</code> otherwise
	*/
	@Override
	public boolean isAreaScienceApp() {
		return _searchCondition.isAreaScienceApp();
	}

	/**
	* Sets whether this search condition is area science app.
	*
	* @param areaScienceApp the area science app of this search condition
	*/
	@Override
	public void setAreaScienceApp(boolean areaScienceApp) {
		_searchCondition.setAreaScienceApp(areaScienceApp);
	}

	/**
	* Returns the area contents of this search condition.
	*
	* @return the area contents of this search condition
	*/
	@Override
	public boolean getAreaContents() {
		return _searchCondition.getAreaContents();
	}

	/**
	* Returns <code>true</code> if this search condition is area contents.
	*
	* @return <code>true</code> if this search condition is area contents; <code>false</code> otherwise
	*/
	@Override
	public boolean isAreaContents() {
		return _searchCondition.isAreaContents();
	}

	/**
	* Sets whether this search condition is area contents.
	*
	* @param areaContents the area contents of this search condition
	*/
	@Override
	public void setAreaContents(boolean areaContents) {
		_searchCondition.setAreaContents(areaContents);
	}

	/**
	* Returns the area simulation project of this search condition.
	*
	* @return the area simulation project of this search condition
	*/
	@Override
	public boolean getAreaSimulationProject() {
		return _searchCondition.getAreaSimulationProject();
	}

	/**
	* Returns <code>true</code> if this search condition is area simulation project.
	*
	* @return <code>true</code> if this search condition is area simulation project; <code>false</code> otherwise
	*/
	@Override
	public boolean isAreaSimulationProject() {
		return _searchCondition.isAreaSimulationProject();
	}

	/**
	* Sets whether this search condition is area simulation project.
	*
	* @param areaSimulationProject the area simulation project of this search condition
	*/
	@Override
	public void setAreaSimulationProject(boolean areaSimulationProject) {
		_searchCondition.setAreaSimulationProject(areaSimulationProject);
	}

	/**
	* Returns the area science data of this search condition.
	*
	* @return the area science data of this search condition
	*/
	@Override
	public boolean getAreaScienceData() {
		return _searchCondition.getAreaScienceData();
	}

	/**
	* Returns <code>true</code> if this search condition is area science data.
	*
	* @return <code>true</code> if this search condition is area science data; <code>false</code> otherwise
	*/
	@Override
	public boolean isAreaScienceData() {
		return _searchCondition.isAreaScienceData();
	}

	/**
	* Sets whether this search condition is area science data.
	*
	* @param areaScienceData the area science data of this search condition
	*/
	@Override
	public void setAreaScienceData(boolean areaScienceData) {
		_searchCondition.setAreaScienceData(areaScienceData);
	}

	/**
	* Returns the parent category of this search condition.
	*
	* @return the parent category of this search condition
	*/
	@Override
	public boolean getParentCategory() {
		return _searchCondition.getParentCategory();
	}

	/**
	* Returns <code>true</code> if this search condition is parent category.
	*
	* @return <code>true</code> if this search condition is parent category; <code>false</code> otherwise
	*/
	@Override
	public boolean isParentCategory() {
		return _searchCondition.isParentCategory();
	}

	/**
	* Sets whether this search condition is parent category.
	*
	* @param parentCategory the parent category of this search condition
	*/
	@Override
	public void setParentCategory(boolean parentCategory) {
		_searchCondition.setParentCategory(parentCategory);
	}

	/**
	* Returns the category ID of this search condition.
	*
	* @return the category ID of this search condition
	*/
	@Override
	public long getCategoryId() {
		return _searchCondition.getCategoryId();
	}

	/**
	* Sets the category ID of this search condition.
	*
	* @param categoryId the category ID of this search condition
	*/
	@Override
	public void setCategoryId(long categoryId) {
		_searchCondition.setCategoryId(categoryId);
	}

	/**
	* Returns the company group ID of this search condition.
	*
	* @return the company group ID of this search condition
	*/
	@Override
	public long getCompanyGroupId() {
		return _searchCondition.getCompanyGroupId();
	}

	/**
	* Sets the company group ID of this search condition.
	*
	* @param companyGroupId the company group ID of this search condition
	*/
	@Override
	public void setCompanyGroupId(long companyGroupId) {
		_searchCondition.setCompanyGroupId(companyGroupId);
	}

	/**
	* Returns the group ID of this search condition.
	*
	* @return the group ID of this search condition
	*/
	@Override
	public long getGroupId() {
		return _searchCondition.getGroupId();
	}

	/**
	* Sets the group ID of this search condition.
	*
	* @param groupId the group ID of this search condition
	*/
	@Override
	public void setGroupId(long groupId) {
		_searchCondition.setGroupId(groupId);
	}

	/**
	* Returns the current page of this search condition.
	*
	* @return the current page of this search condition
	*/
	@Override
	public int getCurrentPage() {
		return _searchCondition.getCurrentPage();
	}

	/**
	* Sets the current page of this search condition.
	*
	* @param currentPage the current page of this search condition
	*/
	@Override
	public void setCurrentPage(int currentPage) {
		_searchCondition.setCurrentPage(currentPage);
	}

	/**
	* Returns the list size of this search condition.
	*
	* @return the list size of this search condition
	*/
	@Override
	public int getListSize() {
		return _searchCondition.getListSize();
	}

	/**
	* Sets the list size of this search condition.
	*
	* @param listSize the list size of this search condition
	*/
	@Override
	public void setListSize(int listSize) {
		_searchCondition.setListSize(listSize);
	}

	/**
	* Returns the block size of this search condition.
	*
	* @return the block size of this search condition
	*/
	@Override
	public int getBlockSize() {
		return _searchCondition.getBlockSize();
	}

	/**
	* Sets the block size of this search condition.
	*
	* @param blockSize the block size of this search condition
	*/
	@Override
	public void setBlockSize(int blockSize) {
		_searchCondition.setBlockSize(blockSize);
	}

	/**
	* Returns the classnote of this search condition.
	*
	* @return the classnote of this search condition
	*/
	@Override
	public boolean getClassnote() {
		return _searchCondition.getClassnote();
	}

	/**
	* Returns <code>true</code> if this search condition is classnote.
	*
	* @return <code>true</code> if this search condition is classnote; <code>false</code> otherwise
	*/
	@Override
	public boolean isClassnote() {
		return _searchCondition.isClassnote();
	}

	/**
	* Sets whether this search condition is classnote.
	*
	* @param classnote the classnote of this search condition
	*/
	@Override
	public void setClassnote(boolean classnote) {
		_searchCondition.setClassnote(classnote);
	}

	/**
	* Returns the manual of this search condition.
	*
	* @return the manual of this search condition
	*/
	@Override
	public boolean getManual() {
		return _searchCondition.getManual();
	}

	/**
	* Returns <code>true</code> if this search condition is manual.
	*
	* @return <code>true</code> if this search condition is manual; <code>false</code> otherwise
	*/
	@Override
	public boolean isManual() {
		return _searchCondition.isManual();
	}

	/**
	* Sets whether this search condition is manual.
	*
	* @param manual the manual of this search condition
	*/
	@Override
	public void setManual(boolean manual) {
		_searchCondition.setManual(manual);
	}

	/**
	* Returns the reference of this search condition.
	*
	* @return the reference of this search condition
	*/
	@Override
	public boolean getReference() {
		return _searchCondition.getReference();
	}

	/**
	* Returns <code>true</code> if this search condition is reference.
	*
	* @return <code>true</code> if this search condition is reference; <code>false</code> otherwise
	*/
	@Override
	public boolean isReference() {
		return _searchCondition.isReference();
	}

	/**
	* Sets whether this search condition is reference.
	*
	* @param reference the reference of this search condition
	*/
	@Override
	public void setReference(boolean reference) {
		_searchCondition.setReference(reference);
	}

	/**
	* Returns the advanced of this search condition.
	*
	* @return the advanced of this search condition
	*/
	@Override
	public boolean getAdvanced() {
		return _searchCondition.getAdvanced();
	}

	/**
	* Returns <code>true</code> if this search condition is advanced.
	*
	* @return <code>true</code> if this search condition is advanced; <code>false</code> otherwise
	*/
	@Override
	public boolean isAdvanced() {
		return _searchCondition.isAdvanced();
	}

	/**
	* Sets whether this search condition is advanced.
	*
	* @param advanced the advanced of this search condition
	*/
	@Override
	public void setAdvanced(boolean advanced) {
		_searchCondition.setAdvanced(advanced);
	}

	/**
	* Returns the sort order of this search condition.
	*
	* @return the sort order of this search condition
	*/
	@Override
	public java.lang.String getSortOrder() {
		return _searchCondition.getSortOrder();
	}

	/**
	* Sets the sort order of this search condition.
	*
	* @param sortOrder the sort order of this search condition
	*/
	@Override
	public void setSortOrder(java.lang.String sortOrder) {
		_searchCondition.setSortOrder(sortOrder);
	}

	/**
	* Returns the sort field of this search condition.
	*
	* @return the sort field of this search condition
	*/
	@Override
	public java.lang.String getSortField() {
		return _searchCondition.getSortField();
	}

	/**
	* Sets the sort field of this search condition.
	*
	* @param sortField the sort field of this search condition
	*/
	@Override
	public void setSortField(java.lang.String sortField) {
		_searchCondition.setSortField(sortField);
	}

	/**
	* Returns the solver of this search condition.
	*
	* @return the solver of this search condition
	*/
	@Override
	public boolean getSolver() {
		return _searchCondition.getSolver();
	}

	/**
	* Returns <code>true</code> if this search condition is solver.
	*
	* @return <code>true</code> if this search condition is solver; <code>false</code> otherwise
	*/
	@Override
	public boolean isSolver() {
		return _searchCondition.isSolver();
	}

	/**
	* Sets whether this search condition is solver.
	*
	* @param Solver the solver of this search condition
	*/
	@Override
	public void setSolver(boolean Solver) {
		_searchCondition.setSolver(Solver);
	}

	/**
	* Returns the converter of this search condition.
	*
	* @return the converter of this search condition
	*/
	@Override
	public boolean getConverter() {
		return _searchCondition.getConverter();
	}

	/**
	* Returns <code>true</code> if this search condition is converter.
	*
	* @return <code>true</code> if this search condition is converter; <code>false</code> otherwise
	*/
	@Override
	public boolean isConverter() {
		return _searchCondition.isConverter();
	}

	/**
	* Sets whether this search condition is converter.
	*
	* @param Converter the converter of this search condition
	*/
	@Override
	public void setConverter(boolean Converter) {
		_searchCondition.setConverter(Converter);
	}

	/**
	* Returns the editor of this search condition.
	*
	* @return the editor of this search condition
	*/
	@Override
	public boolean getEditor() {
		return _searchCondition.getEditor();
	}

	/**
	* Returns <code>true</code> if this search condition is editor.
	*
	* @return <code>true</code> if this search condition is editor; <code>false</code> otherwise
	*/
	@Override
	public boolean isEditor() {
		return _searchCondition.isEditor();
	}

	/**
	* Sets whether this search condition is editor.
	*
	* @param Editor the editor of this search condition
	*/
	@Override
	public void setEditor(boolean Editor) {
		_searchCondition.setEditor(Editor);
	}

	/**
	* Returns the analyzer of this search condition.
	*
	* @return the analyzer of this search condition
	*/
	@Override
	public boolean getAnalyzer() {
		return _searchCondition.getAnalyzer();
	}

	/**
	* Returns <code>true</code> if this search condition is analyzer.
	*
	* @return <code>true</code> if this search condition is analyzer; <code>false</code> otherwise
	*/
	@Override
	public boolean isAnalyzer() {
		return _searchCondition.isAnalyzer();
	}

	/**
	* Sets whether this search condition is analyzer.
	*
	* @param Analyzer the analyzer of this search condition
	*/
	@Override
	public void setAnalyzer(boolean Analyzer) {
		_searchCondition.setAnalyzer(Analyzer);
	}

	@Override
	public boolean isNew() {
		return _searchCondition.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_searchCondition.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _searchCondition.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_searchCondition.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _searchCondition.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _searchCondition.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_searchCondition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _searchCondition.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_searchCondition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_searchCondition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_searchCondition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SearchConditionWrapper((SearchCondition)_searchCondition.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.search.service.model.SearchCondition searchCondition) {
		return _searchCondition.compareTo(searchCondition);
	}

	@Override
	public int hashCode() {
		return _searchCondition.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.search.service.model.SearchCondition> toCacheModel() {
		return _searchCondition.toCacheModel();
	}

	@Override
	public org.kisti.edison.search.service.model.SearchCondition toEscapedModel() {
		return new SearchConditionWrapper(_searchCondition.toEscapedModel());
	}

	@Override
	public org.kisti.edison.search.service.model.SearchCondition toUnescapedModel() {
		return new SearchConditionWrapper(_searchCondition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _searchCondition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _searchCondition.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_searchCondition.persist();
	}

	@Override
	public java.util.Locale getLocale() {
		return _searchCondition.getLocale();
	}

	@Override
	public void setLocale(java.util.Locale locale) {
		_searchCondition.setLocale(locale);
	}

	@Override
	public int getStart() {
		return _searchCondition.getStart();
	}

	@Override
	public long[] getContentDivisions() {
		return _searchCondition.getContentDivisions();
	}

	@Override
	public java.lang.String SORT_FIELD_CREATED() {
		return _searchCondition.SORT_FIELD_CREATED();
	}

	@Override
	public java.lang.String SORT_FIELD_NAME() {
		return _searchCondition.SORT_FIELD_NAME();
	}

	@Override
	public java.lang.String SORT_FIELD_VIEW() {
		return _searchCondition.SORT_FIELD_VIEW();
	}

	@Override
	public java.lang.String SORT_ORDER_ASC() {
		return _searchCondition.SORT_ORDER_ASC();
	}

	@Override
	public java.lang.String SORT_ORDER_DESC() {
		return _searchCondition.SORT_ORDER_DESC();
	}

	@Override
	public java.lang.String[] getAppTypes() {
		return _searchCondition.getAppTypes();
	}

	@Override
	public void setCustomModelAttributes(
		java.util.Map<java.lang.String, java.lang.Object> attributes) {
		_searchCondition.setCustomModelAttributes(attributes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchConditionWrapper)) {
			return false;
		}

		SearchConditionWrapper searchConditionWrapper = (SearchConditionWrapper)obj;

		if (Validator.equals(_searchCondition,
					searchConditionWrapper._searchCondition)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SearchCondition getWrappedSearchCondition() {
		return _searchCondition;
	}

	@Override
	public SearchCondition getWrappedModel() {
		return _searchCondition;
	}

	@Override
	public void resetOriginalValues() {
		_searchCondition.resetOriginalValues();
	}

	private SearchCondition _searchCondition;
}