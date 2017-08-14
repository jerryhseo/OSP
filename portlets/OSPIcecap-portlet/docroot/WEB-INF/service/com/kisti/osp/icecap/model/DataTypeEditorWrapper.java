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
 * This class is a wrapper for {@link DataTypeEditor}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditor
 * @generated
 */
public class DataTypeEditorWrapper implements DataTypeEditor,
	ModelWrapper<DataTypeEditor> {
	public DataTypeEditorWrapper(DataTypeEditor dataTypeEditor) {
		_dataTypeEditor = dataTypeEditor;
	}

	@Override
	public Class<?> getModelClass() {
		return DataTypeEditor.class;
	}

	@Override
	public String getModelClassName() {
		return DataTypeEditor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("editorId", getEditorId());
		attributes.put("isDefault", getIsDefault());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long editorId = (Long)attributes.get("editorId");

		if (editorId != null) {
			setEditorId(editorId);
		}

		Boolean isDefault = (Boolean)attributes.get("isDefault");

		if (isDefault != null) {
			setIsDefault(isDefault);
		}
	}

	/**
	* Returns the primary key of this data type editor.
	*
	* @return the primary key of this data type editor
	*/
	@Override
	public com.kisti.osp.icecap.service.persistence.DataTypeEditorPK getPrimaryKey() {
		return _dataTypeEditor.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data type editor.
	*
	* @param primaryKey the primary key of this data type editor
	*/
	@Override
	public void setPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK primaryKey) {
		_dataTypeEditor.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the type ID of this data type editor.
	*
	* @return the type ID of this data type editor
	*/
	@Override
	public long getTypeId() {
		return _dataTypeEditor.getTypeId();
	}

	/**
	* Sets the type ID of this data type editor.
	*
	* @param typeId the type ID of this data type editor
	*/
	@Override
	public void setTypeId(long typeId) {
		_dataTypeEditor.setTypeId(typeId);
	}

	/**
	* Returns the editor ID of this data type editor.
	*
	* @return the editor ID of this data type editor
	*/
	@Override
	public long getEditorId() {
		return _dataTypeEditor.getEditorId();
	}

	/**
	* Sets the editor ID of this data type editor.
	*
	* @param editorId the editor ID of this data type editor
	*/
	@Override
	public void setEditorId(long editorId) {
		_dataTypeEditor.setEditorId(editorId);
	}

	/**
	* Returns the is default of this data type editor.
	*
	* @return the is default of this data type editor
	*/
	@Override
	public boolean getIsDefault() {
		return _dataTypeEditor.getIsDefault();
	}

	/**
	* Returns <code>true</code> if this data type editor is is default.
	*
	* @return <code>true</code> if this data type editor is is default; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsDefault() {
		return _dataTypeEditor.isIsDefault();
	}

	/**
	* Sets whether this data type editor is is default.
	*
	* @param isDefault the is default of this data type editor
	*/
	@Override
	public void setIsDefault(boolean isDefault) {
		_dataTypeEditor.setIsDefault(isDefault);
	}

	@Override
	public boolean isNew() {
		return _dataTypeEditor.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataTypeEditor.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataTypeEditor.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataTypeEditor.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataTypeEditor.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataTypeEditor.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataTypeEditor.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataTypeEditor.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataTypeEditor.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataTypeEditor.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataTypeEditor.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataTypeEditorWrapper((DataTypeEditor)_dataTypeEditor.clone());
	}

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor) {
		return _dataTypeEditor.compareTo(dataTypeEditor);
	}

	@Override
	public int hashCode() {
		return _dataTypeEditor.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataTypeEditor> toCacheModel() {
		return _dataTypeEditor.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeEditor toEscapedModel() {
		return new DataTypeEditorWrapper(_dataTypeEditor.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeEditor toUnescapedModel() {
		return new DataTypeEditorWrapper(_dataTypeEditor.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataTypeEditor.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataTypeEditor.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataTypeEditor.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeEditorWrapper)) {
			return false;
		}

		DataTypeEditorWrapper dataTypeEditorWrapper = (DataTypeEditorWrapper)obj;

		if (Validator.equals(_dataTypeEditor,
					dataTypeEditorWrapper._dataTypeEditor)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataTypeEditor getWrappedDataTypeEditor() {
		return _dataTypeEditor;
	}

	@Override
	public DataTypeEditor getWrappedModel() {
		return _dataTypeEditor;
	}

	@Override
	public void resetOriginalValues() {
		_dataTypeEditor.resetOriginalValues();
	}

	private DataTypeEditor _dataTypeEditor;
}