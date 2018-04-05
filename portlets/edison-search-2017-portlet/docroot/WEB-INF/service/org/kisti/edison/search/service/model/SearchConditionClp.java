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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.search.service.service.ClpSerializer;
import org.kisti.edison.search.service.service.SearchConditionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjpark
 */
public class SearchConditionClp extends BaseModelImpl<SearchCondition>
	implements SearchCondition {
	public SearchConditionClp() {
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
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setId", long.class);

				method.invoke(_searchConditionRemoteModel, id);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSearchKeyword() {
		return _searchKeyword;
	}

	@Override
	public void setSearchKeyword(String searchKeyword) {
		_searchKeyword = searchKeyword;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setSearchKeyword", String.class);

				method.invoke(_searchConditionRemoteModel, searchKeyword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAreaScienceApp() {
		return _areaScienceApp;
	}

	@Override
	public boolean isAreaScienceApp() {
		return _areaScienceApp;
	}

	@Override
	public void setAreaScienceApp(boolean areaScienceApp) {
		_areaScienceApp = areaScienceApp;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAreaScienceApp",
						boolean.class);

				method.invoke(_searchConditionRemoteModel, areaScienceApp);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAreaContents() {
		return _areaContents;
	}

	@Override
	public boolean isAreaContents() {
		return _areaContents;
	}

	@Override
	public void setAreaContents(boolean areaContents) {
		_areaContents = areaContents;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAreaContents", boolean.class);

				method.invoke(_searchConditionRemoteModel, areaContents);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAreaSimulationProject() {
		return _areaSimulationProject;
	}

	@Override
	public boolean isAreaSimulationProject() {
		return _areaSimulationProject;
	}

	@Override
	public void setAreaSimulationProject(boolean areaSimulationProject) {
		_areaSimulationProject = areaSimulationProject;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAreaSimulationProject",
						boolean.class);

				method.invoke(_searchConditionRemoteModel, areaSimulationProject);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAreaScienceData() {
		return _areaScienceData;
	}

	@Override
	public boolean isAreaScienceData() {
		return _areaScienceData;
	}

	@Override
	public void setAreaScienceData(boolean areaScienceData) {
		_areaScienceData = areaScienceData;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAreaScienceData",
						boolean.class);

				method.invoke(_searchConditionRemoteModel, areaScienceData);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getParentCategory() {
		return _parentCategory;
	}

	@Override
	public boolean isParentCategory() {
		return _parentCategory;
	}

	@Override
	public void setParentCategory(boolean parentCategory) {
		_parentCategory = parentCategory;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setParentCategory",
						boolean.class);

				method.invoke(_searchConditionRemoteModel, parentCategory);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setCategoryId", long.class);

				method.invoke(_searchConditionRemoteModel, categoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyGroupId() {
		return _companyGroupId;
	}

	@Override
	public void setCompanyGroupId(long companyGroupId) {
		_companyGroupId = companyGroupId;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyGroupId", long.class);

				method.invoke(_searchConditionRemoteModel, companyGroupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_searchConditionRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getCurrentPage() {
		return _currentPage;
	}

	@Override
	public void setCurrentPage(int currentPage) {
		_currentPage = currentPage;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrentPage", int.class);

				method.invoke(_searchConditionRemoteModel, currentPage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getListSize() {
		return _listSize;
	}

	@Override
	public void setListSize(int listSize) {
		_listSize = listSize;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setListSize", int.class);

				method.invoke(_searchConditionRemoteModel, listSize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getBlockSize() {
		return _blockSize;
	}

	@Override
	public void setBlockSize(int blockSize) {
		_blockSize = blockSize;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setBlockSize", int.class);

				method.invoke(_searchConditionRemoteModel, blockSize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getClassnote() {
		return _classnote;
	}

	@Override
	public boolean isClassnote() {
		return _classnote;
	}

	@Override
	public void setClassnote(boolean classnote) {
		_classnote = classnote;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setClassnote", boolean.class);

				method.invoke(_searchConditionRemoteModel, classnote);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getManual() {
		return _manual;
	}

	@Override
	public boolean isManual() {
		return _manual;
	}

	@Override
	public void setManual(boolean manual) {
		_manual = manual;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setManual", boolean.class);

				method.invoke(_searchConditionRemoteModel, manual);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getReference() {
		return _reference;
	}

	@Override
	public boolean isReference() {
		return _reference;
	}

	@Override
	public void setReference(boolean reference) {
		_reference = reference;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setReference", boolean.class);

				method.invoke(_searchConditionRemoteModel, reference);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAdvanced() {
		return _advanced;
	}

	@Override
	public boolean isAdvanced() {
		return _advanced;
	}

	@Override
	public void setAdvanced(boolean advanced) {
		_advanced = advanced;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAdvanced", boolean.class);

				method.invoke(_searchConditionRemoteModel, advanced);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSortOrder() {
		return _sortOrder;
	}

	@Override
	public void setSortOrder(String sortOrder) {
		_sortOrder = sortOrder;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setSortOrder", String.class);

				method.invoke(_searchConditionRemoteModel, sortOrder);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSortField() {
		return _sortField;
	}

	@Override
	public void setSortField(String sortField) {
		_sortField = sortField;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setSortField", String.class);

				method.invoke(_searchConditionRemoteModel, sortField);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getSolver() {
		return _Solver;
	}

	@Override
	public boolean isSolver() {
		return _Solver;
	}

	@Override
	public void setSolver(boolean Solver) {
		_Solver = Solver;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setSolver", boolean.class);

				method.invoke(_searchConditionRemoteModel, Solver);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getConverter() {
		return _Converter;
	}

	@Override
	public boolean isConverter() {
		return _Converter;
	}

	@Override
	public void setConverter(boolean Converter) {
		_Converter = Converter;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setConverter", boolean.class);

				method.invoke(_searchConditionRemoteModel, Converter);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEditor() {
		return _Editor;
	}

	@Override
	public boolean isEditor() {
		return _Editor;
	}

	@Override
	public void setEditor(boolean Editor) {
		_Editor = Editor;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setEditor", boolean.class);

				method.invoke(_searchConditionRemoteModel, Editor);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAnalyzer() {
		return _Analyzer;
	}

	@Override
	public boolean isAnalyzer() {
		return _Analyzer;
	}

	@Override
	public void setAnalyzer(boolean Analyzer) {
		_Analyzer = Analyzer;

		if (_searchConditionRemoteModel != null) {
			try {
				Class<?> clazz = _searchConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setAnalyzer", boolean.class);

				method.invoke(_searchConditionRemoteModel, Analyzer);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String SORT_ORDER_ASC() {
		try {
			String methodName = "SORT_ORDER_ASC";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String SORT_FIELD_CREATED() {
		try {
			String methodName = "SORT_FIELD_CREATED";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String[] getAppTypes() {
		try {
			String methodName = "getAppTypes";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String[] returnObj = (java.lang.String[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String SORT_FIELD_VIEW() {
		try {
			String methodName = "SORT_FIELD_VIEW";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getStart() {
		try {
			String methodName = "getStart";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setLocale(java.util.Locale locale) {
		try {
			String methodName = "setLocale";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Locale.class };

			Object[] parameterValues = new Object[] { locale };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setCustomModelAttributes(
		java.util.Map<java.lang.String, java.lang.Object> attributes) {
		try {
			String methodName = "setCustomModelAttributes";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Map.class };

			Object[] parameterValues = new Object[] { attributes };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public long[] getContentDivisions() {
		try {
			String methodName = "getContentDivisions";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			long[] returnObj = (long[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.Locale getLocale() {
		try {
			String methodName = "getLocale";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.Locale returnObj = (java.util.Locale)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String SORT_ORDER_DESC() {
		try {
			String methodName = "SORT_ORDER_DESC";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String SORT_FIELD_NAME() {
		try {
			String methodName = "SORT_FIELD_NAME";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSearchConditionRemoteModel() {
		return _searchConditionRemoteModel;
	}

	public void setSearchConditionRemoteModel(
		BaseModel<?> searchConditionRemoteModel) {
		_searchConditionRemoteModel = searchConditionRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _searchConditionRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_searchConditionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SearchConditionLocalServiceUtil.addSearchCondition(this);
		}
		else {
			SearchConditionLocalServiceUtil.updateSearchCondition(this);
		}
	}

	@Override
	public SearchCondition toEscapedModel() {
		return (SearchCondition)ProxyUtil.newProxyInstance(SearchCondition.class.getClassLoader(),
			new Class[] { SearchCondition.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SearchConditionClp clone = new SearchConditionClp();

		clone.setId(getId());
		clone.setSearchKeyword(getSearchKeyword());
		clone.setAreaScienceApp(getAreaScienceApp());
		clone.setAreaContents(getAreaContents());
		clone.setAreaSimulationProject(getAreaSimulationProject());
		clone.setAreaScienceData(getAreaScienceData());
		clone.setParentCategory(getParentCategory());
		clone.setCategoryId(getCategoryId());
		clone.setCompanyGroupId(getCompanyGroupId());
		clone.setGroupId(getGroupId());
		clone.setCurrentPage(getCurrentPage());
		clone.setListSize(getListSize());
		clone.setBlockSize(getBlockSize());
		clone.setClassnote(getClassnote());
		clone.setManual(getManual());
		clone.setReference(getReference());
		clone.setAdvanced(getAdvanced());
		clone.setSortOrder(getSortOrder());
		clone.setSortField(getSortField());
		clone.setSolver(getSolver());
		clone.setConverter(getConverter());
		clone.setEditor(getEditor());
		clone.setAnalyzer(getAnalyzer());

		return clone;
	}

	@Override
	public int compareTo(SearchCondition searchCondition) {
		long primaryKey = searchCondition.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchConditionClp)) {
			return false;
		}

		SearchConditionClp searchCondition = (SearchConditionClp)obj;

		long primaryKey = searchCondition.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", searchKeyword=");
		sb.append(getSearchKeyword());
		sb.append(", areaScienceApp=");
		sb.append(getAreaScienceApp());
		sb.append(", areaContents=");
		sb.append(getAreaContents());
		sb.append(", areaSimulationProject=");
		sb.append(getAreaSimulationProject());
		sb.append(", areaScienceData=");
		sb.append(getAreaScienceData());
		sb.append(", parentCategory=");
		sb.append(getParentCategory());
		sb.append(", categoryId=");
		sb.append(getCategoryId());
		sb.append(", companyGroupId=");
		sb.append(getCompanyGroupId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", currentPage=");
		sb.append(getCurrentPage());
		sb.append(", listSize=");
		sb.append(getListSize());
		sb.append(", blockSize=");
		sb.append(getBlockSize());
		sb.append(", classnote=");
		sb.append(getClassnote());
		sb.append(", manual=");
		sb.append(getManual());
		sb.append(", reference=");
		sb.append(getReference());
		sb.append(", advanced=");
		sb.append(getAdvanced());
		sb.append(", sortOrder=");
		sb.append(getSortOrder());
		sb.append(", sortField=");
		sb.append(getSortField());
		sb.append(", Solver=");
		sb.append(getSolver());
		sb.append(", Converter=");
		sb.append(getConverter());
		sb.append(", Editor=");
		sb.append(getEditor());
		sb.append(", Analyzer=");
		sb.append(getAnalyzer());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(73);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.search.service.model.SearchCondition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>searchKeyword</column-name><column-value><![CDATA[");
		sb.append(getSearchKeyword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>areaScienceApp</column-name><column-value><![CDATA[");
		sb.append(getAreaScienceApp());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>areaContents</column-name><column-value><![CDATA[");
		sb.append(getAreaContents());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>areaSimulationProject</column-name><column-value><![CDATA[");
		sb.append(getAreaSimulationProject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>areaScienceData</column-name><column-value><![CDATA[");
		sb.append(getAreaScienceData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentCategory</column-name><column-value><![CDATA[");
		sb.append(getParentCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>categoryId</column-name><column-value><![CDATA[");
		sb.append(getCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyGroupId</column-name><column-value><![CDATA[");
		sb.append(getCompanyGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentPage</column-name><column-value><![CDATA[");
		sb.append(getCurrentPage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>listSize</column-name><column-value><![CDATA[");
		sb.append(getListSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>blockSize</column-name><column-value><![CDATA[");
		sb.append(getBlockSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classnote</column-name><column-value><![CDATA[");
		sb.append(getClassnote());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>manual</column-name><column-value><![CDATA[");
		sb.append(getManual());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reference</column-name><column-value><![CDATA[");
		sb.append(getReference());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>advanced</column-name><column-value><![CDATA[");
		sb.append(getAdvanced());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sortOrder</column-name><column-value><![CDATA[");
		sb.append(getSortOrder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sortField</column-name><column-value><![CDATA[");
		sb.append(getSortField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>Solver</column-name><column-value><![CDATA[");
		sb.append(getSolver());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>Converter</column-name><column-value><![CDATA[");
		sb.append(getConverter());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>Editor</column-name><column-value><![CDATA[");
		sb.append(getEditor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>Analyzer</column-name><column-value><![CDATA[");
		sb.append(getAnalyzer());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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
	private BaseModel<?> _searchConditionRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.search.service.service.ClpSerializer.class;
}