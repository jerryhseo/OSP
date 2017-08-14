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

package org.kisti.edison.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.service.ClpSerializer;
import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;
import org.kisti.edison.service.persistence.SimProScienceAppLinkPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author edison
 */
public class SimProScienceAppLinkClp extends BaseModelImpl<SimProScienceAppLink>
	implements SimProScienceAppLink {
	public SimProScienceAppLinkClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SimProScienceAppLink.class;
	}

	@Override
	public String getModelClassName() {
		return SimProScienceAppLink.class.getName();
	}

	@Override
	public SimProScienceAppLinkPK getPrimaryKey() {
		return new SimProScienceAppLinkPK(_simProScienceAppLinkId,
			_simulationProjectId);
	}

	@Override
	public void setPrimaryKey(SimProScienceAppLinkPK primaryKey) {
		setSimProScienceAppLinkId(primaryKey.simProScienceAppLinkId);
		setSimulationProjectId(primaryKey.simulationProjectId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimProScienceAppLinkPK(_simProScienceAppLinkId,
			_simulationProjectId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimProScienceAppLinkPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simProScienceAppLinkId", getSimProScienceAppLinkId());
		attributes.put("simulationProjectId", getSimulationProjectId());
		attributes.put("scienceAppId", getScienceAppId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simProScienceAppLinkId = (Long)attributes.get(
				"simProScienceAppLinkId");

		if (simProScienceAppLinkId != null) {
			setSimProScienceAppLinkId(simProScienceAppLinkId);
		}

		Long simulationProjectId = (Long)attributes.get("simulationProjectId");

		if (simulationProjectId != null) {
			setSimulationProjectId(simulationProjectId);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}
	}

	@Override
	public long getSimProScienceAppLinkId() {
		return _simProScienceAppLinkId;
	}

	@Override
	public void setSimProScienceAppLinkId(long simProScienceAppLinkId) {
		_simProScienceAppLinkId = simProScienceAppLinkId;

		if (_simProScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _simProScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setSimProScienceAppLinkId",
						long.class);

				method.invoke(_simProScienceAppLinkRemoteModel,
					simProScienceAppLinkId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;

		if (_simProScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _simProScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationProjectId",
						long.class);

				method.invoke(_simProScienceAppLinkRemoteModel,
					simulationProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(Long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_simProScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _simProScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", Long.class);

				method.invoke(_simProScienceAppLinkRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSimProScienceAppLinkRemoteModel() {
		return _simProScienceAppLinkRemoteModel;
	}

	public void setSimProScienceAppLinkRemoteModel(
		BaseModel<?> simProScienceAppLinkRemoteModel) {
		_simProScienceAppLinkRemoteModel = simProScienceAppLinkRemoteModel;
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

		Class<?> remoteModelClass = _simProScienceAppLinkRemoteModel.getClass();

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

		Object returnValue = method.invoke(_simProScienceAppLinkRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimProScienceAppLinkLocalServiceUtil.addSimProScienceAppLink(this);
		}
		else {
			SimProScienceAppLinkLocalServiceUtil.updateSimProScienceAppLink(this);
		}
	}

	@Override
	public SimProScienceAppLink toEscapedModel() {
		return (SimProScienceAppLink)ProxyUtil.newProxyInstance(SimProScienceAppLink.class.getClassLoader(),
			new Class[] { SimProScienceAppLink.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SimProScienceAppLinkClp clone = new SimProScienceAppLinkClp();

		clone.setSimProScienceAppLinkId(getSimProScienceAppLinkId());
		clone.setSimulationProjectId(getSimulationProjectId());
		clone.setScienceAppId(getScienceAppId());

		return clone;
	}

	@Override
	public int compareTo(SimProScienceAppLink simProScienceAppLink) {
		SimProScienceAppLinkPK primaryKey = simProScienceAppLink.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimProScienceAppLinkClp)) {
			return false;
		}

		SimProScienceAppLinkClp simProScienceAppLink = (SimProScienceAppLinkClp)obj;

		SimProScienceAppLinkPK primaryKey = simProScienceAppLink.getPrimaryKey();

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

		sb.append("{simProScienceAppLinkId=");
		sb.append(getSimProScienceAppLinkId());
		sb.append(", simulationProjectId=");
		sb.append(getSimulationProjectId());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.SimProScienceAppLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simProScienceAppLinkId</column-name><column-value><![CDATA[");
		sb.append(getSimProScienceAppLinkId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationProjectId</column-name><column-value><![CDATA[");
		sb.append(getSimulationProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _simProScienceAppLinkId;
	private long _simulationProjectId;
	private Long _scienceAppId;
	private BaseModel<?> _simProScienceAppLinkRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.service.ClpSerializer.class;
}