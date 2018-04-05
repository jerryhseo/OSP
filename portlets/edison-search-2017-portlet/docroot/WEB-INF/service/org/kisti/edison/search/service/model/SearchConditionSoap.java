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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author yjpark
 * @generated
 */
public class SearchConditionSoap implements Serializable {
	public static SearchConditionSoap toSoapModel(SearchCondition model) {
		SearchConditionSoap soapModel = new SearchConditionSoap();

		soapModel.setId(model.getId());
		soapModel.setSearchKeyword(model.getSearchKeyword());
		soapModel.setAreaScienceApp(model.getAreaScienceApp());
		soapModel.setAreaContents(model.getAreaContents());
		soapModel.setAreaSimulationProject(model.getAreaSimulationProject());
		soapModel.setAreaScienceData(model.getAreaScienceData());
		soapModel.setParentCategory(model.getParentCategory());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setCompanyGroupId(model.getCompanyGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCurrentPage(model.getCurrentPage());
		soapModel.setListSize(model.getListSize());
		soapModel.setBlockSize(model.getBlockSize());
		soapModel.setClassnote(model.getClassnote());
		soapModel.setManual(model.getManual());
		soapModel.setReference(model.getReference());
		soapModel.setAdvanced(model.getAdvanced());
		soapModel.setSortOrder(model.getSortOrder());
		soapModel.setSortField(model.getSortField());
		soapModel.setSolver(model.getSolver());
		soapModel.setConverter(model.getConverter());
		soapModel.setEditor(model.getEditor());
		soapModel.setAnalyzer(model.getAnalyzer());

		return soapModel;
	}

	public static SearchConditionSoap[] toSoapModels(SearchCondition[] models) {
		SearchConditionSoap[] soapModels = new SearchConditionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SearchConditionSoap[][] toSoapModels(
		SearchCondition[][] models) {
		SearchConditionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SearchConditionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SearchConditionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SearchConditionSoap[] toSoapModels(
		List<SearchCondition> models) {
		List<SearchConditionSoap> soapModels = new ArrayList<SearchConditionSoap>(models.size());

		for (SearchCondition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SearchConditionSoap[soapModels.size()]);
	}

	public SearchConditionSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getSearchKeyword() {
		return _searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		_searchKeyword = searchKeyword;
	}

	public boolean getAreaScienceApp() {
		return _areaScienceApp;
	}

	public boolean isAreaScienceApp() {
		return _areaScienceApp;
	}

	public void setAreaScienceApp(boolean areaScienceApp) {
		_areaScienceApp = areaScienceApp;
	}

	public boolean getAreaContents() {
		return _areaContents;
	}

	public boolean isAreaContents() {
		return _areaContents;
	}

	public void setAreaContents(boolean areaContents) {
		_areaContents = areaContents;
	}

	public boolean getAreaSimulationProject() {
		return _areaSimulationProject;
	}

	public boolean isAreaSimulationProject() {
		return _areaSimulationProject;
	}

	public void setAreaSimulationProject(boolean areaSimulationProject) {
		_areaSimulationProject = areaSimulationProject;
	}

	public boolean getAreaScienceData() {
		return _areaScienceData;
	}

	public boolean isAreaScienceData() {
		return _areaScienceData;
	}

	public void setAreaScienceData(boolean areaScienceData) {
		_areaScienceData = areaScienceData;
	}

	public boolean getParentCategory() {
		return _parentCategory;
	}

	public boolean isParentCategory() {
		return _parentCategory;
	}

	public void setParentCategory(boolean parentCategory) {
		_parentCategory = parentCategory;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public long getCompanyGroupId() {
		return _companyGroupId;
	}

	public void setCompanyGroupId(long companyGroupId) {
		_companyGroupId = companyGroupId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public int getCurrentPage() {
		return _currentPage;
	}

	public void setCurrentPage(int currentPage) {
		_currentPage = currentPage;
	}

	public int getListSize() {
		return _listSize;
	}

	public void setListSize(int listSize) {
		_listSize = listSize;
	}

	public int getBlockSize() {
		return _blockSize;
	}

	public void setBlockSize(int blockSize) {
		_blockSize = blockSize;
	}

	public boolean getClassnote() {
		return _classnote;
	}

	public boolean isClassnote() {
		return _classnote;
	}

	public void setClassnote(boolean classnote) {
		_classnote = classnote;
	}

	public boolean getManual() {
		return _manual;
	}

	public boolean isManual() {
		return _manual;
	}

	public void setManual(boolean manual) {
		_manual = manual;
	}

	public boolean getReference() {
		return _reference;
	}

	public boolean isReference() {
		return _reference;
	}

	public void setReference(boolean reference) {
		_reference = reference;
	}

	public boolean getAdvanced() {
		return _advanced;
	}

	public boolean isAdvanced() {
		return _advanced;
	}

	public void setAdvanced(boolean advanced) {
		_advanced = advanced;
	}

	public String getSortOrder() {
		return _sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		_sortOrder = sortOrder;
	}

	public String getSortField() {
		return _sortField;
	}

	public void setSortField(String sortField) {
		_sortField = sortField;
	}

	public boolean getSolver() {
		return _Solver;
	}

	public boolean isSolver() {
		return _Solver;
	}

	public void setSolver(boolean Solver) {
		_Solver = Solver;
	}

	public boolean getConverter() {
		return _Converter;
	}

	public boolean isConverter() {
		return _Converter;
	}

	public void setConverter(boolean Converter) {
		_Converter = Converter;
	}

	public boolean getEditor() {
		return _Editor;
	}

	public boolean isEditor() {
		return _Editor;
	}

	public void setEditor(boolean Editor) {
		_Editor = Editor;
	}

	public boolean getAnalyzer() {
		return _Analyzer;
	}

	public boolean isAnalyzer() {
		return _Analyzer;
	}

	public void setAnalyzer(boolean Analyzer) {
		_Analyzer = Analyzer;
	}

	private long _id;
	private String _searchKeyword;
	private boolean _areaScienceApp;
	private boolean _areaContents;
	private boolean _areaSimulationProject;
	private boolean _areaScienceData;
	private boolean _parentCategory;
	private long _categoryId;
	private long _companyGroupId;
	private long _groupId;
	private int _currentPage;
	private int _listSize;
	private int _blockSize;
	private boolean _classnote;
	private boolean _manual;
	private boolean _reference;
	private boolean _advanced;
	private String _sortOrder;
	private String _sortField;
	private boolean _Solver;
	private boolean _Converter;
	private boolean _Editor;
	private boolean _Analyzer;
}