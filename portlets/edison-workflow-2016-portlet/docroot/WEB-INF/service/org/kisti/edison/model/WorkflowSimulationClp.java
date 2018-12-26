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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.kisti.edison.service.ClpSerializer;
import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class WorkflowSimulationClp extends BaseModelImpl<WorkflowSimulation>
	implements WorkflowSimulation {
	public WorkflowSimulationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowSimulation.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowSimulation.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _simulationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSimulationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _simulationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationId", getSimulationId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classId", getClassId());
		attributes.put("customId", getCustomId());
		attributes.put("title", getTitle());
		attributes.put("testYn", getTestYn());
		attributes.put("workflowId", getWorkflowId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simulationId = (Long)attributes.get("simulationId");

		if (simulationId != null) {
			setSimulationId(simulationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long customId = (Long)attributes.get("customId");

		if (customId != null) {
			setCustomId(customId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Boolean testYn = (Boolean)attributes.get("testYn");

		if (testYn != null) {
			setTestYn(testYn);
		}

		Long workflowId = (Long)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}
	}

	@Override
	public long getSimulationId() {
		return _simulationId;
	}

	@Override
	public void setSimulationId(long simulationId) {
		_simulationId = simulationId;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationId", long.class);

				method.invoke(_workflowSimulationRemoteModel, simulationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_workflowSimulationRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_workflowSimulationRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_workflowSimulationRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassId() {
		return _classId;
	}

	@Override
	public void setClassId(long classId) {
		_classId = classId;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setClassId", long.class);

				method.invoke(_workflowSimulationRemoteModel, classId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCustomId() {
		return _customId;
	}

	@Override
	public void setCustomId(long customId) {
		_customId = customId;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomId", long.class);

				method.invoke(_workflowSimulationRemoteModel, customId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public void setTitle(String title) {
		_title = title;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_workflowSimulationRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getTestYn() {
		return _testYn;
	}

	@Override
	public boolean isTestYn() {
		return _testYn;
	}

	@Override
	public void setTestYn(boolean testYn) {
		_testYn = testYn;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setTestYn", boolean.class);

				method.invoke(_workflowSimulationRemoteModel, testYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWorkflowId() {
		return _workflowId;
	}

	@Override
	public void setWorkflowId(long workflowId) {
		_workflowId = workflowId;

		if (_workflowSimulationRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowId", long.class);

				method.invoke(_workflowSimulationRemoteModel, workflowId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWorkflowSimulationRemoteModel() {
		return _workflowSimulationRemoteModel;
	}

	public void setWorkflowSimulationRemoteModel(
		BaseModel<?> workflowSimulationRemoteModel) {
		_workflowSimulationRemoteModel = workflowSimulationRemoteModel;
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

		Class<?> remoteModelClass = _workflowSimulationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_workflowSimulationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			WorkflowSimulationLocalServiceUtil.addWorkflowSimulation(this);
		}
		else {
			WorkflowSimulationLocalServiceUtil.updateWorkflowSimulation(this);
		}
	}

	@Override
	public WorkflowSimulation toEscapedModel() {
		return (WorkflowSimulation)ProxyUtil.newProxyInstance(WorkflowSimulation.class.getClassLoader(),
			new Class[] { WorkflowSimulation.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WorkflowSimulationClp clone = new WorkflowSimulationClp();

		clone.setSimulationId(getSimulationId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassId(getClassId());
		clone.setCustomId(getCustomId());
		clone.setTitle(getTitle());
		clone.setTestYn(getTestYn());
		clone.setWorkflowId(getWorkflowId());

		return clone;
	}

	@Override
	public int compareTo(WorkflowSimulation workflowSimulation) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				workflowSimulation.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowSimulationClp)) {
			return false;
		}

		WorkflowSimulationClp workflowSimulation = (WorkflowSimulationClp)obj;

		long primaryKey = workflowSimulation.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{simulationId=");
		sb.append(getSimulationId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classId=");
		sb.append(getClassId());
		sb.append(", customId=");
		sb.append(getCustomId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", testYn=");
		sb.append(getTestYn());
		sb.append(", workflowId=");
		sb.append(getWorkflowId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.WorkflowSimulation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simulationId</column-name><column-value><![CDATA[");
		sb.append(getSimulationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classId</column-name><column-value><![CDATA[");
		sb.append(getClassId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customId</column-name><column-value><![CDATA[");
		sb.append(getCustomId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>testYn</column-name><column-value><![CDATA[");
		sb.append(getTestYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _simulationId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classId;
	private long _customId;
	private String _title;
	private boolean _testYn;
	private long _workflowId;
	private BaseModel<?> _workflowSimulationRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.service.ClpSerializer.class;
}