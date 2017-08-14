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
 * This class is a wrapper for {@link DataEntryProvenance}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryProvenance
 * @generated
 */
public class DataEntryProvenanceWrapper implements DataEntryProvenance,
	ModelWrapper<DataEntryProvenance> {
	public DataEntryProvenanceWrapper(DataEntryProvenance dataEntryProvenance) {
		_dataEntryProvenance = dataEntryProvenance;
	}

	@Override
	public Class<?> getModelClass() {
		return DataEntryProvenance.class;
	}

	@Override
	public String getModelClassName() {
		return DataEntryProvenance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("inputData", getInputData());
		attributes.put("PROVStructure", getPROVStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		String inputData = (String)attributes.get("inputData");

		if (inputData != null) {
			setInputData(inputData);
		}

		String PROVStructure = (String)attributes.get("PROVStructure");

		if (PROVStructure != null) {
			setPROVStructure(PROVStructure);
		}
	}

	/**
	* Returns the primary key of this data entry provenance.
	*
	* @return the primary key of this data entry provenance
	*/
	@Override
	public long getPrimaryKey() {
		return _dataEntryProvenance.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data entry provenance.
	*
	* @param primaryKey the primary key of this data entry provenance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataEntryProvenance.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this data entry provenance.
	*
	* @return the entry ID of this data entry provenance
	*/
	@Override
	public long getEntryId() {
		return _dataEntryProvenance.getEntryId();
	}

	/**
	* Sets the entry ID of this data entry provenance.
	*
	* @param entryId the entry ID of this data entry provenance
	*/
	@Override
	public void setEntryId(long entryId) {
		_dataEntryProvenance.setEntryId(entryId);
	}

	/**
	* Returns the input data of this data entry provenance.
	*
	* @return the input data of this data entry provenance
	*/
	@Override
	public java.lang.String getInputData() {
		return _dataEntryProvenance.getInputData();
	}

	/**
	* Sets the input data of this data entry provenance.
	*
	* @param inputData the input data of this data entry provenance
	*/
	@Override
	public void setInputData(java.lang.String inputData) {
		_dataEntryProvenance.setInputData(inputData);
	}

	/**
	* Returns the p r o v structure of this data entry provenance.
	*
	* @return the p r o v structure of this data entry provenance
	*/
	@Override
	public java.lang.String getPROVStructure() {
		return _dataEntryProvenance.getPROVStructure();
	}

	/**
	* Sets the p r o v structure of this data entry provenance.
	*
	* @param PROVStructure the p r o v structure of this data entry provenance
	*/
	@Override
	public void setPROVStructure(java.lang.String PROVStructure) {
		_dataEntryProvenance.setPROVStructure(PROVStructure);
	}

	@Override
	public boolean isNew() {
		return _dataEntryProvenance.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataEntryProvenance.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataEntryProvenance.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataEntryProvenance.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataEntryProvenance.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataEntryProvenance.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataEntryProvenance.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataEntryProvenance.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataEntryProvenance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataEntryProvenance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataEntryProvenance.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataEntryProvenanceWrapper((DataEntryProvenance)_dataEntryProvenance.clone());
	}

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataEntryProvenance dataEntryProvenance) {
		return _dataEntryProvenance.compareTo(dataEntryProvenance);
	}

	@Override
	public int hashCode() {
		return _dataEntryProvenance.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataEntryProvenance> toCacheModel() {
		return _dataEntryProvenance.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntryProvenance toEscapedModel() {
		return new DataEntryProvenanceWrapper(_dataEntryProvenance.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntryProvenance toUnescapedModel() {
		return new DataEntryProvenanceWrapper(_dataEntryProvenance.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataEntryProvenance.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataEntryProvenance.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataEntryProvenance.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataEntryProvenanceWrapper)) {
			return false;
		}

		DataEntryProvenanceWrapper dataEntryProvenanceWrapper = (DataEntryProvenanceWrapper)obj;

		if (Validator.equals(_dataEntryProvenance,
					dataEntryProvenanceWrapper._dataEntryProvenance)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataEntryProvenance getWrappedDataEntryProvenance() {
		return _dataEntryProvenance;
	}

	@Override
	public DataEntryProvenance getWrappedModel() {
		return _dataEntryProvenance;
	}

	@Override
	public void resetOriginalValues() {
		_dataEntryProvenance.resetOriginalValues();
	}

	private DataEntryProvenance _dataEntryProvenance;
}