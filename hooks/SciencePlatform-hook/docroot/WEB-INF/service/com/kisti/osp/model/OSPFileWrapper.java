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

package com.kisti.osp.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OSPFile}.
 * </p>
 *
 * @author Jerry h. Seo
 * @see OSPFile
 * @generated
 */
public class OSPFileWrapper implements OSPFile, ModelWrapper<OSPFile> {
	public OSPFileWrapper(OSPFile ospFile) {
		_ospFile = ospFile;
	}

	@Override
	public Class<?> getModelClass() {
		return OSPFile.class;
	}

	@Override
	public String getModelClassName() {
		return OSPFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("propertyName", getPropertyName());
		attributes.put("propertyValue", getPropertyValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String propertyName = (String)attributes.get("propertyName");

		if (propertyName != null) {
			setPropertyName(propertyName);
		}

		String propertyValue = (String)attributes.get("propertyValue");

		if (propertyValue != null) {
			setPropertyValue(propertyValue);
		}
	}

	/**
	* Returns the primary key of this o s p file.
	*
	* @return the primary key of this o s p file
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _ospFile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o s p file.
	*
	* @param primaryKey the primary key of this o s p file
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_ospFile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the property name of this o s p file.
	*
	* @return the property name of this o s p file
	*/
	@Override
	public java.lang.String getPropertyName() {
		return _ospFile.getPropertyName();
	}

	/**
	* Sets the property name of this o s p file.
	*
	* @param propertyName the property name of this o s p file
	*/
	@Override
	public void setPropertyName(java.lang.String propertyName) {
		_ospFile.setPropertyName(propertyName);
	}

	/**
	* Returns the property value of this o s p file.
	*
	* @return the property value of this o s p file
	*/
	@Override
	public java.lang.String getPropertyValue() {
		return _ospFile.getPropertyValue();
	}

	/**
	* Sets the property value of this o s p file.
	*
	* @param propertyValue the property value of this o s p file
	*/
	@Override
	public void setPropertyValue(java.lang.String propertyValue) {
		_ospFile.setPropertyValue(propertyValue);
	}

	@Override
	public boolean isNew() {
		return _ospFile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_ospFile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _ospFile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ospFile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _ospFile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _ospFile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ospFile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ospFile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_ospFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_ospFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ospFile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OSPFileWrapper((OSPFile)_ospFile.clone());
	}

	@Override
	public int compareTo(com.kisti.osp.model.OSPFile ospFile) {
		return _ospFile.compareTo(ospFile);
	}

	@Override
	public int hashCode() {
		return _ospFile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.model.OSPFile> toCacheModel() {
		return _ospFile.toCacheModel();
	}

	@Override
	public com.kisti.osp.model.OSPFile toEscapedModel() {
		return new OSPFileWrapper(_ospFile.toEscapedModel());
	}

	@Override
	public com.kisti.osp.model.OSPFile toUnescapedModel() {
		return new OSPFileWrapper(_ospFile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ospFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ospFile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ospFile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OSPFileWrapper)) {
			return false;
		}

		OSPFileWrapper ospFileWrapper = (OSPFileWrapper)obj;

		if (Validator.equals(_ospFile, ospFileWrapper._ospFile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public OSPFile getWrappedOSPFile() {
		return _ospFile;
	}

	@Override
	public OSPFile getWrappedModel() {
		return _ospFile;
	}

	@Override
	public void resetOriginalValues() {
		_ospFile.resetOriginalValues();
	}

	private OSPFile _ospFile;
}