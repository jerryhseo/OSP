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
 * This class is a wrapper for {@link DataTypeStructure}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructure
 * @generated
 */
public class DataTypeStructureWrapper implements DataTypeStructure,
	ModelWrapper<DataTypeStructure> {
	public DataTypeStructureWrapper(DataTypeStructure dataTypeStructure) {
		_dataTypeStructure = dataTypeStructure;
	}

	@Override
	public Class<?> getModelClass() {
		return DataTypeStructure.class;
	}

	@Override
	public String getModelClassName() {
		return DataTypeStructure.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("structure", getStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String structure = (String)attributes.get("structure");

		if (structure != null) {
			setStructure(structure);
		}
	}

	/**
	* Returns the primary key of this data type structure.
	*
	* @return the primary key of this data type structure
	*/
	@Override
	public long getPrimaryKey() {
		return _dataTypeStructure.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data type structure.
	*
	* @param primaryKey the primary key of this data type structure
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataTypeStructure.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the type ID of this data type structure.
	*
	* @return the type ID of this data type structure
	*/
	@Override
	public long getTypeId() {
		return _dataTypeStructure.getTypeId();
	}

	/**
	* Sets the type ID of this data type structure.
	*
	* @param typeId the type ID of this data type structure
	*/
	@Override
	public void setTypeId(long typeId) {
		_dataTypeStructure.setTypeId(typeId);
	}

	/**
	* Returns the structure of this data type structure.
	*
	* @return the structure of this data type structure
	*/
	@Override
	public java.lang.String getStructure() {
		return _dataTypeStructure.getStructure();
	}

	/**
	* Sets the structure of this data type structure.
	*
	* @param structure the structure of this data type structure
	*/
	@Override
	public void setStructure(java.lang.String structure) {
		_dataTypeStructure.setStructure(structure);
	}

	@Override
	public boolean isNew() {
		return _dataTypeStructure.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataTypeStructure.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataTypeStructure.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataTypeStructure.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataTypeStructure.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataTypeStructure.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataTypeStructure.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataTypeStructure.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataTypeStructure.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataTypeStructure.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataTypeStructure.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataTypeStructureWrapper((DataTypeStructure)_dataTypeStructure.clone());
	}

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure) {
		return _dataTypeStructure.compareTo(dataTypeStructure);
	}

	@Override
	public int hashCode() {
		return _dataTypeStructure.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataTypeStructure> toCacheModel() {
		return _dataTypeStructure.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure toEscapedModel() {
		return new DataTypeStructureWrapper(_dataTypeStructure.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure toUnescapedModel() {
		return new DataTypeStructureWrapper(_dataTypeStructure.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataTypeStructure.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataTypeStructure.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataTypeStructure.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeStructureWrapper)) {
			return false;
		}

		DataTypeStructureWrapper dataTypeStructureWrapper = (DataTypeStructureWrapper)obj;

		if (Validator.equals(_dataTypeStructure,
					dataTypeStructureWrapper._dataTypeStructure)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataTypeStructure getWrappedDataTypeStructure() {
		return _dataTypeStructure;
	}

	@Override
	public DataTypeStructure getWrappedModel() {
		return _dataTypeStructure;
	}

	@Override
	public void resetOriginalValues() {
		_dataTypeStructure.resetOriginalValues();
	}

	private DataTypeStructure _dataTypeStructure;
}