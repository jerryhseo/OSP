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
 * This class is a wrapper for {@link SystemProperties}.
 * </p>
 *
 * @author Jerry h. Seo
 * @see SystemProperties
 * @generated
 */
public class SystemPropertiesWrapper implements SystemProperties,
	ModelWrapper<SystemProperties> {
	public SystemPropertiesWrapper(SystemProperties systemProperties) {
		_systemProperties = systemProperties;
	}

	@Override
	public Class<?> getModelClass() {
		return SystemProperties.class;
	}

	@Override
	public String getModelClassName() {
		return SystemProperties.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("propertyName", getPropertyName());
		attributes.put("propertyValue", getPropertyValue());
		attributes.put("active", getActive());

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

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this system properties.
	*
	* @return the primary key of this system properties
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _systemProperties.getPrimaryKey();
	}

	/**
	* Sets the primary key of this system properties.
	*
	* @param primaryKey the primary key of this system properties
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_systemProperties.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the property name of this system properties.
	*
	* @return the property name of this system properties
	*/
	@Override
	public java.lang.String getPropertyName() {
		return _systemProperties.getPropertyName();
	}

	/**
	* Sets the property name of this system properties.
	*
	* @param propertyName the property name of this system properties
	*/
	@Override
	public void setPropertyName(java.lang.String propertyName) {
		_systemProperties.setPropertyName(propertyName);
	}

	/**
	* Returns the property value of this system properties.
	*
	* @return the property value of this system properties
	*/
	@Override
	public java.lang.String getPropertyValue() {
		return _systemProperties.getPropertyValue();
	}

	/**
	* Sets the property value of this system properties.
	*
	* @param propertyValue the property value of this system properties
	*/
	@Override
	public void setPropertyValue(java.lang.String propertyValue) {
		_systemProperties.setPropertyValue(propertyValue);
	}

	/**
	* Returns the active of this system properties.
	*
	* @return the active of this system properties
	*/
	@Override
	public boolean getActive() {
		return _systemProperties.getActive();
	}

	/**
	* Returns <code>true</code> if this system properties is active.
	*
	* @return <code>true</code> if this system properties is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _systemProperties.isActive();
	}

	/**
	* Sets whether this system properties is active.
	*
	* @param active the active of this system properties
	*/
	@Override
	public void setActive(boolean active) {
		_systemProperties.setActive(active);
	}

	@Override
	public boolean isNew() {
		return _systemProperties.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_systemProperties.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _systemProperties.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_systemProperties.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _systemProperties.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _systemProperties.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_systemProperties.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _systemProperties.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_systemProperties.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_systemProperties.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_systemProperties.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SystemPropertiesWrapper((SystemProperties)_systemProperties.clone());
	}

	@Override
	public int compareTo(com.kisti.osp.model.SystemProperties systemProperties) {
		return _systemProperties.compareTo(systemProperties);
	}

	@Override
	public int hashCode() {
		return _systemProperties.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.model.SystemProperties> toCacheModel() {
		return _systemProperties.toCacheModel();
	}

	@Override
	public com.kisti.osp.model.SystemProperties toEscapedModel() {
		return new SystemPropertiesWrapper(_systemProperties.toEscapedModel());
	}

	@Override
	public com.kisti.osp.model.SystemProperties toUnescapedModel() {
		return new SystemPropertiesWrapper(_systemProperties.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _systemProperties.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _systemProperties.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_systemProperties.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SystemPropertiesWrapper)) {
			return false;
		}

		SystemPropertiesWrapper systemPropertiesWrapper = (SystemPropertiesWrapper)obj;

		if (Validator.equals(_systemProperties,
					systemPropertiesWrapper._systemProperties)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SystemProperties getWrappedSystemProperties() {
		return _systemProperties;
	}

	@Override
	public SystemProperties getWrappedModel() {
		return _systemProperties;
	}

	@Override
	public void resetOriginalValues() {
		_systemProperties.resetOriginalValues();
	}

	private SystemProperties _systemProperties;
}