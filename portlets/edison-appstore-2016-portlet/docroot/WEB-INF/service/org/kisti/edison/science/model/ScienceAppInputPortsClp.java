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
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class ScienceAppInputPortsClp extends BaseModelImpl<ScienceAppInputPorts>
	implements ScienceAppInputPorts {
	public ScienceAppInputPortsClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppInputPorts.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppInputPorts.class.getName();
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
		attributes.put("inputPortsSampleFile", getInputPortsSampleFile());
		attributes.put("inputPorts", getInputPorts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String inputPortsSampleFile = (String)attributes.get(
				"inputPortsSampleFile");

		if (inputPortsSampleFile != null) {
			setInputPortsSampleFile(inputPortsSampleFile);
		}

		String inputPorts = (String)attributes.get("inputPorts");

		if (inputPorts != null) {
			setInputPorts(inputPorts);
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_scienceAppInputPortsRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppInputPortsRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_scienceAppInputPortsRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInputPortsSampleFile() {
		return _inputPortsSampleFile;
	}

	@Override
	public void setInputPortsSampleFile(String inputPortsSampleFile) {
		_inputPortsSampleFile = inputPortsSampleFile;

		if (_scienceAppInputPortsRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppInputPortsRemoteModel.getClass();

				Method method = clazz.getMethod("setInputPortsSampleFile",
						String.class);

				method.invoke(_scienceAppInputPortsRemoteModel,
					inputPortsSampleFile);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInputPorts() {
		return _inputPorts;
	}

	@Override
	public void setInputPorts(String inputPorts) {
		_inputPorts = inputPorts;

		if (_scienceAppInputPortsRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppInputPortsRemoteModel.getClass();

				Method method = clazz.getMethod("setInputPorts", String.class);

				method.invoke(_scienceAppInputPortsRemoteModel, inputPorts);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getScienceAppInputPortsRemoteModel() {
		return _scienceAppInputPortsRemoteModel;
	}

	public void setScienceAppInputPortsRemoteModel(
		BaseModel<?> scienceAppInputPortsRemoteModel) {
		_scienceAppInputPortsRemoteModel = scienceAppInputPortsRemoteModel;
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

		Class<?> remoteModelClass = _scienceAppInputPortsRemoteModel.getClass();

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

		Object returnValue = method.invoke(_scienceAppInputPortsRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ScienceAppInputPortsLocalServiceUtil.addScienceAppInputPorts(this);
		}
		else {
			ScienceAppInputPortsLocalServiceUtil.updateScienceAppInputPorts(this);
		}
	}

	@Override
	public ScienceAppInputPorts toEscapedModel() {
		return (ScienceAppInputPorts)ProxyUtil.newProxyInstance(ScienceAppInputPorts.class.getClassLoader(),
			new Class[] { ScienceAppInputPorts.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ScienceAppInputPortsClp clone = new ScienceAppInputPortsClp();

		clone.setScienceAppId(getScienceAppId());
		clone.setInputPortsSampleFile(getInputPortsSampleFile());
		clone.setInputPorts(getInputPorts());

		return clone;
	}

	@Override
	public int compareTo(ScienceAppInputPorts scienceAppInputPorts) {
		long primaryKey = scienceAppInputPorts.getPrimaryKey();

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

		if (!(obj instanceof ScienceAppInputPortsClp)) {
			return false;
		}

		ScienceAppInputPortsClp scienceAppInputPorts = (ScienceAppInputPortsClp)obj;

		long primaryKey = scienceAppInputPorts.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", inputPortsSampleFile=");
		sb.append(getInputPortsSampleFile());
		sb.append(", inputPorts=");
		sb.append(getInputPorts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.science.model.ScienceAppInputPorts");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inputPortsSampleFile</column-name><column-value><![CDATA[");
		sb.append(getInputPortsSampleFile());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inputPorts</column-name><column-value><![CDATA[");
		sb.append(getInputPorts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppId;
	private String _inputPortsSampleFile;
	private String _inputPorts;
	private BaseModel<?> _scienceAppInputPortsRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.science.service.ClpSerializer.class;
}