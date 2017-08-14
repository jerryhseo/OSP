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

package com.kisti.osp.icecap.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DataTypeAnalyzer}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeAnalyzer
 * @generated
 */
public class DataTypeAnalyzerWrapper implements DataTypeAnalyzer,
	ModelWrapper<DataTypeAnalyzer> {
	public DataTypeAnalyzerWrapper(DataTypeAnalyzer dataTypeAnalyzer) {
		_dataTypeAnalyzer = dataTypeAnalyzer;
	}

	@Override
	public Class<?> getModelClass() {
		return DataTypeAnalyzer.class;
	}

	@Override
	public String getModelClassName() {
		return DataTypeAnalyzer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("analyzerId", getAnalyzerId());
		attributes.put("isDefault", getIsDefault());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long analyzerId = (Long)attributes.get("analyzerId");

		if (analyzerId != null) {
			setAnalyzerId(analyzerId);
		}

		Boolean isDefault = (Boolean)attributes.get("isDefault");

		if (isDefault != null) {
			setIsDefault(isDefault);
		}
	}

	/**
	* Returns the primary key of this data type analyzer.
	*
	* @return the primary key of this data type analyzer
	*/
	@Override
	public com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK getPrimaryKey() {
		return _dataTypeAnalyzer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data type analyzer.
	*
	* @param primaryKey the primary key of this data type analyzer
	*/
	@Override
	public void setPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK primaryKey) {
		_dataTypeAnalyzer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the type ID of this data type analyzer.
	*
	* @return the type ID of this data type analyzer
	*/
	@Override
	public long getTypeId() {
		return _dataTypeAnalyzer.getTypeId();
	}

	/**
	* Sets the type ID of this data type analyzer.
	*
	* @param typeId the type ID of this data type analyzer
	*/
	@Override
	public void setTypeId(long typeId) {
		_dataTypeAnalyzer.setTypeId(typeId);
	}

	/**
	* Returns the analyzer ID of this data type analyzer.
	*
	* @return the analyzer ID of this data type analyzer
	*/
	@Override
	public long getAnalyzerId() {
		return _dataTypeAnalyzer.getAnalyzerId();
	}

	/**
	* Sets the analyzer ID of this data type analyzer.
	*
	* @param analyzerId the analyzer ID of this data type analyzer
	*/
	@Override
	public void setAnalyzerId(long analyzerId) {
		_dataTypeAnalyzer.setAnalyzerId(analyzerId);
	}

	/**
	* Returns the is default of this data type analyzer.
	*
	* @return the is default of this data type analyzer
	*/
	@Override
	public boolean getIsDefault() {
		return _dataTypeAnalyzer.getIsDefault();
	}

	/**
	* Returns <code>true</code> if this data type analyzer is is default.
	*
	* @return <code>true</code> if this data type analyzer is is default; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsDefault() {
		return _dataTypeAnalyzer.isIsDefault();
	}

	/**
	* Sets whether this data type analyzer is is default.
	*
	* @param isDefault the is default of this data type analyzer
	*/
	@Override
	public void setIsDefault(boolean isDefault) {
		_dataTypeAnalyzer.setIsDefault(isDefault);
	}

	@Override
	public boolean isNew() {
		return _dataTypeAnalyzer.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataTypeAnalyzer.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataTypeAnalyzer.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataTypeAnalyzer.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataTypeAnalyzer.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataTypeAnalyzer.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataTypeAnalyzer.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataTypeAnalyzer.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataTypeAnalyzer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataTypeAnalyzer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataTypeAnalyzer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataTypeAnalyzerWrapper((DataTypeAnalyzer)_dataTypeAnalyzer.clone());
	}

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataTypeAnalyzer dataTypeAnalyzer) {
		return _dataTypeAnalyzer.compareTo(dataTypeAnalyzer);
	}

	@Override
	public int hashCode() {
		return _dataTypeAnalyzer.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataTypeAnalyzer> toCacheModel() {
		return _dataTypeAnalyzer.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeAnalyzer toEscapedModel() {
		return new DataTypeAnalyzerWrapper(_dataTypeAnalyzer.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeAnalyzer toUnescapedModel() {
		return new DataTypeAnalyzerWrapper(_dataTypeAnalyzer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataTypeAnalyzer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataTypeAnalyzer.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataTypeAnalyzer.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeAnalyzerWrapper)) {
			return false;
		}

		DataTypeAnalyzerWrapper dataTypeAnalyzerWrapper = (DataTypeAnalyzerWrapper)obj;

		if (Validator.equals(_dataTypeAnalyzer,
					dataTypeAnalyzerWrapper._dataTypeAnalyzer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataTypeAnalyzer getWrappedDataTypeAnalyzer() {
		return _dataTypeAnalyzer;
	}

	@Override
	public DataTypeAnalyzer getWrappedModel() {
		return _dataTypeAnalyzer;
	}

	@Override
	public void resetOriginalValues() {
		_dataTypeAnalyzer.resetOriginalValues();
	}

	private DataTypeAnalyzer _dataTypeAnalyzer;
}