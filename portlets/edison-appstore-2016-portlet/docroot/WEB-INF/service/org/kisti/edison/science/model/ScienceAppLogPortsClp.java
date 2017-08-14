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

package org.kisti.edison.science.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.science.service.ClpSerializer;
import org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class ScienceAppLogPortsClp extends BaseModelImpl<ScienceAppLogPorts>
	implements ScienceAppLogPorts {
	public ScienceAppLogPortsClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppLogPorts.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppLogPorts.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("logPorts", getLogPorts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String logPorts = (String)attributes.get("logPorts");

		if (logPorts != null) {
			setLogPorts(logPorts);
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_scienceAppLogPortsRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppLogPortsRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_scienceAppLogPortsRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLogPorts() {
		return _logPorts;
	}

	@Override
	public void setLogPorts(String logPorts) {
		_logPorts = logPorts;

		if (_scienceAppLogPortsRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppLogPortsRemoteModel.getClass();

				Method method = clazz.getMethod("setLogPorts", String.class);

				method.invoke(_scienceAppLogPortsRemoteModel, logPorts);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getScienceAppLogPortsRemoteModel() {
		return _scienceAppLogPortsRemoteModel;
	}

	public void setScienceAppLogPortsRemoteModel(
		BaseModel<?> scienceAppLogPortsRemoteModel) {
		_scienceAppLogPortsRemoteModel = scienceAppLogPortsRemoteModel;
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

		Class<?> remoteModelClass = _scienceAppLogPortsRemoteModel.getClass();

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

		Object returnValue = method.invoke(_scienceAppLogPortsRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ScienceAppLogPortsLocalServiceUtil.addScienceAppLogPorts(this);
		}
		else {
			ScienceAppLogPortsLocalServiceUtil.updateScienceAppLogPorts(this);
		}
	}

	@Override
	public ScienceAppLogPorts toEscapedModel() {
		return (ScienceAppLogPorts)ProxyUtil.newProxyInstance(ScienceAppLogPorts.class.getClassLoader(),
			new Class[] { ScienceAppLogPorts.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ScienceAppLogPortsClp clone = new ScienceAppLogPortsClp();

		clone.setScienceAppId(getScienceAppId());
		clone.setLogPorts(getLogPorts());

		return clone;
	}

	@Override
	public int compareTo(ScienceAppLogPorts scienceAppLogPorts) {
		long primaryKey = scienceAppLogPorts.getPrimaryKey();

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

		if (!(obj instanceof ScienceAppLogPortsClp)) {
			return false;
		}

		ScienceAppLogPortsClp scienceAppLogPorts = (ScienceAppLogPortsClp)obj;

		long primaryKey = scienceAppLogPorts.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", logPorts=");
		sb.append(getLogPorts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.science.model.ScienceAppLogPorts");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logPorts</column-name><column-value><![CDATA[");
		sb.append(getLogPorts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppId;
	private String _logPorts;
	private BaseModel<?> _scienceAppLogPortsRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.science.service.ClpSerializer.class;
}