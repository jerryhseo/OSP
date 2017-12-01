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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.bestsimulation.service.ClpSerializer;
import org.kisti.edison.bestsimulation.service.SimulationExeStsMigrationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class SimulationExeStsMigrationClp extends BaseModelImpl<SimulationExeStsMigration>
	implements SimulationExeStsMigration {
	public SimulationExeStsMigrationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationExeStsMigration.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationExeStsMigration.class.getName();
	}

	@Override
	public SimulationExeStsMigrationPK getPrimaryKey() {
		return new SimulationExeStsMigrationPK(_scienceAppId, _groupId,
			_submitDate);
	}

	@Override
	public void setPrimaryKey(SimulationExeStsMigrationPK primaryKey) {
		setScienceAppId(primaryKey.scienceAppId);
		setGroupId(primaryKey.groupId);
		setSubmitDate(primaryKey.submitDate);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimulationExeStsMigrationPK(_scienceAppId, _groupId,
			_submitDate);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimulationExeStsMigrationPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("groupId", getGroupId());
		attributes.put("submitDate", getSubmitDate());
		attributes.put("userCnt", getUserCnt());
		attributes.put("jobCnt", getJobCnt());
		attributes.put("runtime", getRuntime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String submitDate = (String)attributes.get("submitDate");

		if (submitDate != null) {
			setSubmitDate(submitDate);
		}

		Long userCnt = (Long)attributes.get("userCnt");

		if (userCnt != null) {
			setUserCnt(userCnt);
		}

		Long jobCnt = (Long)attributes.get("jobCnt");

		if (jobCnt != null) {
			setJobCnt(jobCnt);
		}

		Long runtime = (Long)attributes.get("runtime");

		if (runtime != null) {
			setRuntime(runtime);
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_simulationExeStsMigrationRemoteModel,
					scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_simulationExeStsMigrationRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSubmitDate() {
		return _submitDate;
	}

	@Override
	public void setSubmitDate(String submitDate) {
		_submitDate = submitDate;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setSubmitDate", String.class);

				method.invoke(_simulationExeStsMigrationRemoteModel, submitDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserCnt() {
		return _userCnt;
	}

	@Override
	public void setUserCnt(long userCnt) {
		_userCnt = userCnt;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserCnt", long.class);

				method.invoke(_simulationExeStsMigrationRemoteModel, userCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getJobCnt() {
		return _jobCnt;
	}

	@Override
	public void setJobCnt(long jobCnt) {
		_jobCnt = jobCnt;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setJobCnt", long.class);

				method.invoke(_simulationExeStsMigrationRemoteModel, jobCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRuntime() {
		return _runtime;
	}

	@Override
	public void setRuntime(long runtime) {
		_runtime = runtime;

		if (_simulationExeStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _simulationExeStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setRuntime", long.class);

				method.invoke(_simulationExeStsMigrationRemoteModel, runtime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSimulationExeStsMigrationRemoteModel() {
		return _simulationExeStsMigrationRemoteModel;
	}

	public void setSimulationExeStsMigrationRemoteModel(
		BaseModel<?> simulationExeStsMigrationRemoteModel) {
		_simulationExeStsMigrationRemoteModel = simulationExeStsMigrationRemoteModel;
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

		Class<?> remoteModelClass = _simulationExeStsMigrationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_simulationExeStsMigrationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimulationExeStsMigrationLocalServiceUtil.addSimulationExeStsMigration(this);
		}
		else {
			SimulationExeStsMigrationLocalServiceUtil.updateSimulationExeStsMigration(this);
		}
	}

	@Override
	public SimulationExeStsMigration toEscapedModel() {
		return (SimulationExeStsMigration)ProxyUtil.newProxyInstance(SimulationExeStsMigration.class.getClassLoader(),
			new Class[] { SimulationExeStsMigration.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SimulationExeStsMigrationClp clone = new SimulationExeStsMigrationClp();

		clone.setScienceAppId(getScienceAppId());
		clone.setGroupId(getGroupId());
		clone.setSubmitDate(getSubmitDate());
		clone.setUserCnt(getUserCnt());
		clone.setJobCnt(getJobCnt());
		clone.setRuntime(getRuntime());

		return clone;
	}

	@Override
	public int compareTo(SimulationExeStsMigration simulationExeStsMigration) {
		SimulationExeStsMigrationPK primaryKey = simulationExeStsMigration.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationExeStsMigrationClp)) {
			return false;
		}

		SimulationExeStsMigrationClp simulationExeStsMigration = (SimulationExeStsMigrationClp)obj;

		SimulationExeStsMigrationPK primaryKey = simulationExeStsMigration.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", submitDate=");
		sb.append(getSubmitDate());
		sb.append(", userCnt=");
		sb.append(getUserCnt());
		sb.append(", jobCnt=");
		sb.append(getJobCnt());
		sb.append(", runtime=");
		sb.append(getRuntime());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.bestsimulation.model.SimulationExeStsMigration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>submitDate</column-name><column-value><![CDATA[");
		sb.append(getSubmitDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userCnt</column-name><column-value><![CDATA[");
		sb.append(getUserCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobCnt</column-name><column-value><![CDATA[");
		sb.append(getJobCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>runtime</column-name><column-value><![CDATA[");
		sb.append(getRuntime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppId;
	private long _groupId;
	private String _submitDate;
	private long _userCnt;
	private long _jobCnt;
	private long _runtime;
	private BaseModel<?> _simulationExeStsMigrationRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.bestsimulation.service.ClpSerializer.class;
}