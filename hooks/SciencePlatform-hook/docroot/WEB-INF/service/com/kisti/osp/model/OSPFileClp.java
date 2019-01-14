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
import com.kisti.osp.service.OSPFileLocalServiceUtil;

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
public class OSPFileClp extends BaseModelImpl<OSPFile> implements OSPFile {
	public OSPFileClp() {
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

	@Override
	public String getPropertyName() {
		return _propertyName;
	}

	@Override
	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;

		if (_ospFileRemoteModel != null) {
			try {
				Class<?> clazz = _ospFileRemoteModel.getClass();

				Method method = clazz.getMethod("setPropertyName", String.class);

				method.invoke(_ospFileRemoteModel, propertyName);
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

		if (_ospFileRemoteModel != null) {
			try {
				Class<?> clazz = _ospFileRemoteModel.getClass();

				Method method = clazz.getMethod("setPropertyValue", String.class);

				method.invoke(_ospFileRemoteModel, propertyValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getOSPFileRemoteModel() {
		return _ospFileRemoteModel;
	}

	public void setOSPFileRemoteModel(BaseModel<?> ospFileRemoteModel) {
		_ospFileRemoteModel = ospFileRemoteModel;
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

		Class<?> remoteModelClass = _ospFileRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ospFileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			OSPFileLocalServiceUtil.addOSPFile(this);
		}
		else {
			OSPFileLocalServiceUtil.updateOSPFile(this);
		}
	}

	@Override
	public OSPFile toEscapedModel() {
		return (OSPFile)ProxyUtil.newProxyInstance(OSPFile.class.getClassLoader(),
			new Class[] { OSPFile.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OSPFileClp clone = new OSPFileClp();

		clone.setPropertyName(getPropertyName());
		clone.setPropertyValue(getPropertyValue());

		return clone;
	}

	@Override
	public int compareTo(OSPFile ospFile) {
		String primaryKey = ospFile.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OSPFileClp)) {
			return false;
		}

		OSPFileClp ospFile = (OSPFileClp)obj;

		String primaryKey = ospFile.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{propertyName=");
		sb.append(getPropertyName());
		sb.append(", propertyValue=");
		sb.append(getPropertyValue());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.model.OSPFile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>propertyName</column-name><column-value><![CDATA[");
		sb.append(getPropertyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>propertyValue</column-name><column-value><![CDATA[");
		sb.append(getPropertyValue());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _propertyName;
	private String _propertyValue;
	private BaseModel<?> _ospFileRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.service.ClpSerializer.class;
}