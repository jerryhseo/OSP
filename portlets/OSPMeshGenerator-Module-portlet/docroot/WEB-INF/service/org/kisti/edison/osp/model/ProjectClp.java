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
import org.kisti.edison.osp.service.ProjectLocalServiceUtil;
import org.kisti.edison.osp.service.persistence.ProjectPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author edison
 */
public class ProjectClp extends BaseModelImpl<Project> implements Project {
	public ProjectClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public ProjectPK getPrimaryKey() {
		return new ProjectPK(_simulationUuid, _portletNamespace, _jobSeqNo);
	}

	@Override
	public void setPrimaryKey(ProjectPK primaryKey) {
		setSimulationUuid(primaryKey.simulationUuid);
		setPortletNamespace(primaryKey.portletNamespace);
		setJobSeqNo(primaryKey.jobSeqNo);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ProjectPK(_simulationUuid, _portletNamespace, _jobSeqNo);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ProjectPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationUuid", getSimulationUuid());
		attributes.put("portletNamespace", getPortletNamespace());
		attributes.put("jobSeqNo", getJobSeqNo());
		attributes.put("projectStructure", getProjectStructure());
		attributes.put("analyzerStructure", getAnalyzerStructure());
		attributes.put("executeId", getExecuteId());
		attributes.put("executeDataStructure", getExecuteDataStructure());
		attributes.put("executeDate", getExecuteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String simulationUuid = (String)attributes.get("simulationUuid");

		if (simulationUuid != null) {
			setSimulationUuid(simulationUuid);
		}

		String portletNamespace = (String)attributes.get("portletNamespace");

		if (portletNamespace != null) {
			setPortletNamespace(portletNamespace);
		}

		Long jobSeqNo = (Long)attributes.get("jobSeqNo");

		if (jobSeqNo != null) {
			setJobSeqNo(jobSeqNo);
		}

		String projectStructure = (String)attributes.get("projectStructure");

		if (projectStructure != null) {
			setProjectStructure(projectStructure);
		}

		String analyzerStructure = (String)attributes.get("analyzerStructure");

		if (analyzerStructure != null) {
			setAnalyzerStructure(analyzerStructure);
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
	public String getSimulationUuid() {
		return _simulationUuid;
	}

	@Override
	public void setSimulationUuid(String simulationUuid) {
		_simulationUuid = simulationUuid;

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationUuid",
						String.class);

				method.invoke(_projectRemoteModel, simulationUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPortletNamespace() {
		return _portletNamespace;
	}

	@Override
	public void setPortletNamespace(String portletNamespace) {
		_portletNamespace = portletNamespace;

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setPortletNamespace",
						String.class);

				method.invoke(_projectRemoteModel, portletNamespace);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getJobSeqNo() {
		return _jobSeqNo;
	}

	@Override
	public void setJobSeqNo(long jobSeqNo) {
		_jobSeqNo = jobSeqNo;

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setJobSeqNo", long.class);

				method.invoke(_projectRemoteModel, jobSeqNo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProjectStructure() {
		return _projectStructure;
	}

	@Override
	public void setProjectStructure(String projectStructure) {
		_projectStructure = projectStructure;

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectStructure",
						String.class);

				method.invoke(_projectRemoteModel, projectStructure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAnalyzerStructure() {
		return _analyzerStructure;
	}

	@Override
	public void setAnalyzerStructure(String analyzerStructure) {
		_analyzerStructure = analyzerStructure;

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setAnalyzerStructure",
						String.class);

				method.invoke(_projectRemoteModel, analyzerStructure);
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

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteId", String.class);

				method.invoke(_projectRemoteModel, executeId);
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

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDataStructure",
						String.class);

				method.invoke(_projectRemoteModel, executeDataStructure);
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

		if (_projectRemoteModel != null) {
			try {
				Class<?> clazz = _projectRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDate", Date.class);

				method.invoke(_projectRemoteModel, executeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getProjectRemoteModel() {
		return _projectRemoteModel;
	}

	public void setProjectRemoteModel(BaseModel<?> projectRemoteModel) {
		_projectRemoteModel = projectRemoteModel;
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

		Class<?> remoteModelClass = _projectRemoteModel.getClass();

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

		Object returnValue = method.invoke(_projectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ProjectLocalServiceUtil.addProject(this);
		}
		else {
			ProjectLocalServiceUtil.updateProject(this);
		}
	}

	@Override
	public Project toEscapedModel() {
		return (Project)ProxyUtil.newProxyInstance(Project.class.getClassLoader(),
			new Class[] { Project.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ProjectClp clone = new ProjectClp();

		clone.setSimulationUuid(getSimulationUuid());
		clone.setPortletNamespace(getPortletNamespace());
		clone.setJobSeqNo(getJobSeqNo());
		clone.setProjectStructure(getProjectStructure());
		clone.setAnalyzerStructure(getAnalyzerStructure());
		clone.setExecuteId(getExecuteId());
		clone.setExecuteDataStructure(getExecuteDataStructure());
		clone.setExecuteDate(getExecuteDate());

		return clone;
	}

	@Override
	public int compareTo(Project project) {
		ProjectPK primaryKey = project.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectClp)) {
			return false;
		}

		ProjectClp project = (ProjectClp)obj;

		ProjectPK primaryKey = project.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{simulationUuid=");
		sb.append(getSimulationUuid());
		sb.append(", portletNamespace=");
		sb.append(getPortletNamespace());
		sb.append(", jobSeqNo=");
		sb.append(getJobSeqNo());
		sb.append(", projectStructure=");
		sb.append(getProjectStructure());
		sb.append(", analyzerStructure=");
		sb.append(getAnalyzerStructure());
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
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.osp.model.Project");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simulationUuid</column-name><column-value><![CDATA[");
		sb.append(getSimulationUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletNamespace</column-name><column-value><![CDATA[");
		sb.append(getPortletNamespace());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobSeqNo</column-name><column-value><![CDATA[");
		sb.append(getJobSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectStructure</column-name><column-value><![CDATA[");
		sb.append(getProjectStructure());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>analyzerStructure</column-name><column-value><![CDATA[");
		sb.append(getAnalyzerStructure());
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

	private String _simulationUuid;
	private String _portletNamespace;
	private long _jobSeqNo;
	private String _projectStructure;
	private String _analyzerStructure;
	private String _executeId;
	private String _executeDataStructure;
	private Date _executeDate;
	private BaseModel<?> _projectRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.osp.service.ClpSerializer.class;
}