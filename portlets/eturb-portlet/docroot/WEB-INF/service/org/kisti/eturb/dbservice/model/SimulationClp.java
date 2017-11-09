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

package org.kisti.eturb.dbservice.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.eturb.dbservice.service.ClpSerializer;
import org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil;
import org.kisti.eturb.dbservice.service.persistence.SimulationPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class SimulationClp extends BaseModelImpl<Simulation>
	implements Simulation {
	public SimulationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Simulation.class;
	}

	@Override
	public String getModelClassName() {
		return Simulation.class.getName();
	}

	@Override
	public SimulationPK getPrimaryKey() {
		return new SimulationPK(_projectId, _executeId);
	}

	@Override
	public void setPrimaryKey(SimulationPK primaryKey) {
		setProjectId(primaryKey.projectId);
		setExecuteId(primaryKey.executeId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimulationPK(_projectId, _executeId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimulationPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectId", getProjectId());
		attributes.put("executeId", getExecuteId());
		attributes.put("executeDataStructure", getExecuteDataStructure());
		attributes.put("executeDate", getExecuteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String executeId = (String)attributes.get("executeId");

		if (executeId != null) {
			setExecuteId(executeId);
		}

		String executeDataStructure = (String)attributes.get(
				"executeDataStructure");

		if (executeDataStructure != null) {
			setExecuteDataStructure(executeDataStructure);
		}

		Date executeDate = (Date)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}
	}

	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;

		if (_simulationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectId", long.class);

				method.invoke(_simulationRemoteModel, projectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExecuteId() {
		return _executeId;
	}

	@Override
	public void setExecuteId(String executeId) {
		_executeId = executeId;

		if (_simulationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteId", String.class);

				method.invoke(_simulationRemoteModel, executeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExecuteDataStructure() {
		return _executeDataStructure;
	}

	@Override
	public void setExecuteDataStructure(String executeDataStructure) {
		_executeDataStructure = executeDataStructure;

		if (_simulationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDataStructure",
						String.class);

				method.invoke(_simulationRemoteModel, executeDataStructure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getExecuteDate() {
		return _executeDate;
	}

	@Override
	public void setExecuteDate(Date executeDate) {
		_executeDate = executeDate;

		if (_simulationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDate", Date.class);

				method.invoke(_simulationRemoteModel, executeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSimulationRemoteModel() {
		return _simulationRemoteModel;
	}

	public void setSimulationRemoteModel(BaseModel<?> simulationRemoteModel) {
		_simulationRemoteModel = simulationRemoteModel;
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

		Class<?> remoteModelClass = _simulationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_simulationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimulationLocalServiceUtil.addSimulation(this);
		}
		else {
			SimulationLocalServiceUtil.updateSimulation(this);
		}
	}

	@Override
	public Simulation toEscapedModel() {
		return (Simulation)ProxyUtil.newProxyInstance(Simulation.class.getClassLoader(),
			new Class[] { Simulation.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SimulationClp clone = new SimulationClp();

		clone.setProjectId(getProjectId());
		clone.setExecuteId(getExecuteId());
		clone.setExecuteDataStructure(getExecuteDataStructure());
		clone.setExecuteDate(getExecuteDate());

		return clone;
	}

	@Override
	public int compareTo(Simulation simulation) {
		SimulationPK primaryKey = simulation.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationClp)) {
			return false;
		}

		SimulationClp simulation = (SimulationClp)obj;

		SimulationPK primaryKey = simulation.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{projectId=");
		sb.append(getProjectId());
		sb.append(", executeId=");
		sb.append(getExecuteId());
		sb.append(", executeDataStructure=");
		sb.append(getExecuteDataStructure());
		sb.append(", executeDate=");
		sb.append(getExecuteDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("org.kisti.eturb.dbservice.model.Simulation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeId</column-name><column-value><![CDATA[");
		sb.append(getExecuteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeDataStructure</column-name><column-value><![CDATA[");
		sb.append(getExecuteDataStructure());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeDate</column-name><column-value><![CDATA[");
		sb.append(getExecuteDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _projectId;
	private String _executeId;
	private String _executeDataStructure;
	private Date _executeDate;
	private BaseModel<?> _simulationRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.eturb.dbservice.service.ClpSerializer.class;
}