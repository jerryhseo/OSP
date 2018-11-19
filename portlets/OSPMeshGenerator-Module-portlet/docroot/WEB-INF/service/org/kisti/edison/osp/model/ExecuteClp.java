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

package org.kisti.edison.osp.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.osp.service.ClpSerializer;
import org.kisti.edison.osp.service.ExecuteLocalServiceUtil;
import org.kisti.edison.osp.service.persistence.ExecutePK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author edison
 */
public class ExecuteClp extends BaseModelImpl<Execute> implements Execute {
	public ExecuteClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Execute.class;
	}

	@Override
	public String getModelClassName() {
		return Execute.class.getName();
	}

	@Override
	public ExecutePK getPrimaryKey() {
		return new ExecutePK(_projectId, _analyzerId);
	}

	@Override
	public void setPrimaryKey(ExecutePK primaryKey) {
		setProjectId(primaryKey.projectId);
		setAnalyzerId(primaryKey.analyzerId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ExecutePK(_projectId, _analyzerId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ExecutePK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectId", getProjectId());
		attributes.put("analyzerId", getAnalyzerId());
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

		String analyzerId = (String)attributes.get("analyzerId");

		if (analyzerId != null) {
			setAnalyzerId(analyzerId);
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

		if (_executeRemoteModel != null) {
			try {
				Class<?> clazz = _executeRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectId", long.class);

				method.invoke(_executeRemoteModel, projectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAnalyzerId() {
		return _analyzerId;
	}

	@Override
	public void setAnalyzerId(String analyzerId) {
		_analyzerId = analyzerId;

		if (_executeRemoteModel != null) {
			try {
				Class<?> clazz = _executeRemoteModel.getClass();

				Method method = clazz.getMethod("setAnalyzerId", String.class);

				method.invoke(_executeRemoteModel, analyzerId);
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

		if (_executeRemoteModel != null) {
			try {
				Class<?> clazz = _executeRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDataStructure",
						String.class);

				method.invoke(_executeRemoteModel, executeDataStructure);
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

		if (_executeRemoteModel != null) {
			try {
				Class<?> clazz = _executeRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDate", Date.class);

				method.invoke(_executeRemoteModel, executeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getExecuteRemoteModel() {
		return _executeRemoteModel;
	}

	public void setExecuteRemoteModel(BaseModel<?> executeRemoteModel) {
		_executeRemoteModel = executeRemoteModel;
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

		Class<?> remoteModelClass = _executeRemoteModel.getClass();

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

		Object returnValue = method.invoke(_executeRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ExecuteLocalServiceUtil.addExecute(this);
		}
		else {
			ExecuteLocalServiceUtil.updateExecute(this);
		}
	}

	@Override
	public Execute toEscapedModel() {
		return (Execute)ProxyUtil.newProxyInstance(Execute.class.getClassLoader(),
			new Class[] { Execute.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ExecuteClp clone = new ExecuteClp();

		clone.setProjectId(getProjectId());
		clone.setAnalyzerId(getAnalyzerId());
		clone.setExecuteDataStructure(getExecuteDataStructure());
		clone.setExecuteDate(getExecuteDate());

		return clone;
	}

	@Override
	public int compareTo(Execute execute) {
		ExecutePK primaryKey = execute.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExecuteClp)) {
			return false;
		}

		ExecuteClp execute = (ExecuteClp)obj;

		ExecutePK primaryKey = execute.getPrimaryKey();

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
		sb.append(", analyzerId=");
		sb.append(getAnalyzerId());
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
		sb.append("org.kisti.edison.osp.model.Execute");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>analyzerId</column-name><column-value><![CDATA[");
		sb.append(getAnalyzerId());
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
	private String _analyzerId;
	private String _executeDataStructure;
	private Date _executeDate;
	private BaseModel<?> _executeRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.osp.service.ClpSerializer.class;
}