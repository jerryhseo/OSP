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
 * This class is a wrapper for {@link DataCollectionLayout}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLayout
 * @generated
 */
public class DataCollectionLayoutWrapper implements DataCollectionLayout,
	ModelWrapper<DataCollectionLayout> {
	public DataCollectionLayoutWrapper(
		DataCollectionLayout dataCollectionLayout) {
		_dataCollectionLayout = dataCollectionLayout;
	}

	@Override
	public Class<?> getModelClass() {
		return DataCollectionLayout.class;
	}

	@Override
	public String getModelClassName() {
		return DataCollectionLayout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("collectionId", getCollectionId());
		attributes.put("layoutStr", getLayoutStr());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long collectionId = (Long)attributes.get("collectionId");

		if (collectionId != null) {
			setCollectionId(collectionId);
		}

		String layoutStr = (String)attributes.get("layoutStr");

		if (layoutStr != null) {
			setLayoutStr(layoutStr);
		}
	}

	/**
	* Returns the primary key of this data collection layout.
	*
	* @return the primary key of this data collection layout
	*/
	@Override
	public long getPrimaryKey() {
		return _dataCollectionLayout.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data collection layout.
	*
	* @param primaryKey the primary key of this data collection layout
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataCollectionLayout.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the collection ID of this data collection layout.
	*
	* @return the collection ID of this data collection layout
	*/
	@Override
	public long getCollectionId() {
		return _dataCollectionLayout.getCollectionId();
	}

	/**
	* Sets the collection ID of this data collection layout.
	*
	* @param collectionId the collection ID of this data collection layout
	*/
	@Override
	public void setCollectionId(long collectionId) {
		_dataCollectionLayout.setCollectionId(collectionId);
	}

	/**
	* Returns the layout str of this data collection layout.
	*
	* @return the layout str of this data collection layout
	*/
	@Override
	public java.lang.String getLayoutStr() {
		return _dataCollectionLayout.getLayoutStr();
	}

	/**
	* Sets the layout str of this data collection layout.
	*
	* @param layoutStr the layout str of this data collection layout
	*/
	@Override
	public void setLayoutStr(java.lang.String layoutStr) {
		_dataCollectionLayout.setLayoutStr(layoutStr);
	}

	@Override
	public boolean isNew() {
		return _dataCollectionLayout.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataCollectionLayout.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataCollectionLayout.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataCollectionLayout.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataCollectionLayout.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataCollectionLayout.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataCollectionLayout.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataCollectionLayout.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataCollectionLayout.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataCollectionLayout.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataCollectionLayout.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataCollectionLayoutWrapper((DataCollectionLayout)_dataCollectionLayout.clone());
	}

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout) {
		return _dataCollectionLayout.compareTo(dataCollectionLayout);
	}

	@Override
	public int hashCode() {
		return _dataCollectionLayout.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataCollectionLayout> toCacheModel() {
		return _dataCollectionLayout.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataCollectionLayout toEscapedModel() {
		return new DataCollectionLayoutWrapper(_dataCollectionLayout.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataCollectionLayout toUnescapedModel() {
		return new DataCollectionLayoutWrapper(_dataCollectionLayout.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataCollectionLayout.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataCollectionLayout.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataCollectionLayout.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataCollectionLayoutWrapper)) {
			return false;
		}

		DataCollectionLayoutWrapper dataCollectionLayoutWrapper = (DataCollectionLayoutWrapper)obj;

		if (Validator.equals(_dataCollectionLayout,
					dataCollectionLayoutWrapper._dataCollectionLayout)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataCollectionLayout getWrappedDataCollectionLayout() {
		return _dataCollectionLayout;
	}

	@Override
	public DataCollectionLayout getWrappedModel() {
		return _dataCollectionLayout;
	}

	@Override
	public void resetOriginalValues() {
		_dataCollectionLayout.resetOriginalValues();
	}

	private DataCollectionLayout _dataCollectionLayout;
}