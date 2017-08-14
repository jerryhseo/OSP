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

import com.kisti.osp.service.ClpSerializer;
import com.kisti.osp.service.SystemPropertiesLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry h. Seo
 */
public class SystemPropertiesClp extends BaseModelImpl<SystemProperties>
	implements SystemProperties {
	public SystemPropertiesClp() {
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
	public String getPrimaryKey() {
		return _propertyName;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setPropertyName(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _propertyName;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
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

	@Override
	public String getPropertyName() {
		return _propertyName;
	}

	@Override
	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;

		if (_systemPropertiesRemoteModel != null) {
			try {
				Class<?> clazz = _systemPropertiesRemoteModel.getClass();

				Method method = clazz.getMethod("setPropertyName", String.class);

				method.invoke(_systemPropertiesRemoteModel, propertyName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPropertyValue() {
		return _propertyValue;
	}

	@Override
	public void setPropertyValue(String propertyValue) {
		_propertyValue = propertyValue;

		if (_systemPropertiesRemoteModel != null) {
			try {
				Class<?> clazz = _systemPropertiesRemoteModel.getClass();

				Method method = clazz.getMethod("setPropertyValue", String.class);

				method.invoke(_systemPropertiesRemoteModel, propertyValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;

		if (_systemPropertiesRemoteModel != null) {
			try {
				Class<?> clazz = _systemPropertiesRemoteModel.getClass();

				Method method = clazz.getMethod("setActive", boolean.class);

				method.invoke(_systemPropertiesRemoteModel, active);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSystemPropertiesRemoteModel() {
		return _systemPropertiesRemoteModel;
	}

	public void setSystemPropertiesRemoteModel(
		BaseModel<?> systemPropertiesRemoteModel) {
		_systemPropertiesRemoteModel = systemPropertiesRemoteModel;
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

		Class<?> remoteModelClass = _systemPropertiesRemoteModel.getClass();

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

		Object returnValue = method.invoke(_systemPropertiesRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SystemPropertiesLocalServiceUtil.addSystemProperties(this);
		}
		else {
			SystemPropertiesLocalServiceUtil.updateSystemProperties(this);
		}
	}

	@Override
	public SystemProperties toEscapedModel() {
		return (SystemProperties)ProxyUtil.newProxyInstance(SystemProperties.class.getClassLoader(),
			new Class[] { SystemProperties.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SystemPropertiesClp clone = new SystemPropertiesClp();

		clone.setPropertyName(getPropertyName());
		clone.setPropertyValue(getPropertyValue());
		clone.setActive(getActive());

		return clone;
	}

	@Override
	public int compareTo(SystemProperties systemProperties) {
		String primaryKey = systemProperties.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SystemPropertiesClp)) {
			return false;
		}

		SystemPropertiesClp systemProperties = (SystemPropertiesClp)obj;

		String primaryKey = systemProperties.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
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
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{propertyName=");
		sb.append(getPropertyName());
		sb.append(", propertyValue=");
		sb.append(getPropertyValue());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.model.SystemProperties");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>propertyName</column-name><column-value><![CDATA[");
		sb.append(getPropertyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>propertyValue</column-name><column-value><![CDATA[");
		sb.append(getPropertyValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _propertyName;
	private String _propertyValue;
	private boolean _active;
	private BaseModel<?> _systemPropertiesRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.service.ClpSerializer.class;
}