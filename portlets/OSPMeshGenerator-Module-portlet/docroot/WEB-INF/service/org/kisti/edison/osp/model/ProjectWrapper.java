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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Project}.
 * </p>
 *
 * @author edison
 * @see Project
 * @generated
 */
public class ProjectWrapper implements Project, ModelWrapper<Project> {
	public ProjectWrapper(Project project) {
		_project = project;
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
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationUuid", getSimulationUuid());
		attributes.put("portletNamespace", getPortletNamespace());
		attributes.put("jobSeqNo", getJobSeqNo());
		attributes.put("projectId", getProjectId());
		attributes.put("projectStructure", getProjectStructure());
		attributes.put("analyzerStructure", getAnalyzerStructure());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());

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

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String projectStructure = (String)attributes.get("projectStructure");

		if (projectStructure != null) {
			setProjectStructure(projectStructure);
		}

		String analyzerStructure = (String)attributes.get("analyzerStructure");

		if (analyzerStructure != null) {
			setAnalyzerStructure(analyzerStructure);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	* Returns the primary key of this project.
	*
	* @return the primary key of this project
	*/
	@Override
	public org.kisti.edison.osp.service.persistence.ProjectPK getPrimaryKey() {
		return _project.getPrimaryKey();
	}

	/**
	* Sets the primary key of this project.
	*
	* @param primaryKey the primary key of this project
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.osp.service.persistence.ProjectPK primaryKey) {
		_project.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the simulation uuid of this project.
	*
	* @return the simulation uuid of this project
	*/
	@Override
	public java.lang.String getSimulationUuid() {
		return _project.getSimulationUuid();
	}

	/**
	* Sets the simulation uuid of this project.
	*
	* @param simulationUuid the simulation uuid of this project
	*/
	@Override
	public void setSimulationUuid(java.lang.String simulationUuid) {
		_project.setSimulationUuid(simulationUuid);
	}

	/**
	* Returns the portlet namespace of this project.
	*
	* @return the portlet namespace of this project
	*/
	@Override
	public java.lang.String getPortletNamespace() {
		return _project.getPortletNamespace();
	}

	/**
	* Sets the portlet namespace of this project.
	*
	* @param portletNamespace the portlet namespace of this project
	*/
	@Override
	public void setPortletNamespace(java.lang.String portletNamespace) {
		_project.setPortletNamespace(portletNamespace);
	}

	/**
	* Returns the job seq no of this project.
	*
	* @return the job seq no of this project
	*/
	@Override
	public long getJobSeqNo() {
		return _project.getJobSeqNo();
	}

	/**
	* Sets the job seq no of this project.
	*
	* @param jobSeqNo the job seq no of this project
	*/
	@Override
	public void setJobSeqNo(long jobSeqNo) {
		_project.setJobSeqNo(jobSeqNo);
	}

	/**
	* Returns the project ID of this project.
	*
	* @return the project ID of this project
	*/
	@Override
	public long getProjectId() {
		return _project.getProjectId();
	}

	/**
	* Sets the project ID of this project.
	*
	* @param projectId the project ID of this project
	*/
	@Override
	public void setProjectId(long projectId) {
		_project.setProjectId(projectId);
	}

	/**
	* Returns the project structure of this project.
	*
	* @return the project structure of this project
	*/
	@Override
	public java.lang.String getProjectStructure() {
		return _project.getProjectStructure();
	}

	/**
	* Sets the project structure of this project.
	*
	* @param projectStructure the project structure of this project
	*/
	@Override
	public void setProjectStructure(java.lang.String projectStructure) {
		_project.setProjectStructure(projectStructure);
	}

	/**
	* Returns the analyzer structure of this project.
	*
	* @return the analyzer structure of this project
	*/
	@Override
	public java.lang.String getAnalyzerStructure() {
		return _project.getAnalyzerStructure();
	}

	/**
	* Sets the analyzer structure of this project.
	*
	* @param analyzerStructure the analyzer structure of this project
	*/
	@Override
	public void setAnalyzerStructure(java.lang.String analyzerStructure) {
		_project.setAnalyzerStructure(analyzerStructure);
	}

	/**
	* Returns the user ID of this project.
	*
	* @return the user ID of this project
	*/
	@Override
	public long getUserId() {
		return _project.getUserId();
	}

	/**
	* Sets the user ID of this project.
	*
	* @param userId the user ID of this project
	*/
	@Override
	public void setUserId(long userId) {
		_project.setUserId(userId);
	}

	/**
	* Returns the user uuid of this project.
	*
	* @return the user uuid of this project
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _project.getUserUuid();
	}

	/**
	* Sets the user uuid of this project.
	*
	* @param userUuid the user uuid of this project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_project.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this project.
	*
	* @return the create date of this project
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _project.getCreateDate();
	}

	/**
	* Sets the create date of this project.
	*
	* @param createDate the create date of this project
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_project.setCreateDate(createDate);
	}

	@Override
	public boolean isNew() {
		return _project.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_project.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _project.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_project.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _project.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _project.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_project.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _project.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_project.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_project.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_project.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectWrapper((Project)_project.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.osp.model.Project project) {
		return _project.compareTo(project);
	}

	@Override
	public int hashCode() {
		return _project.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.osp.model.Project> toCacheModel() {
		return _project.toCacheModel();
	}

	@Override
	public org.kisti.edison.osp.model.Project toEscapedModel() {
		return new ProjectWrapper(_project.toEscapedModel());
	}

	@Override
	public org.kisti.edison.osp.model.Project toUnescapedModel() {
		return new ProjectWrapper(_project.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _project.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _project.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_project.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectWrapper)) {
			return false;
		}

		ProjectWrapper projectWrapper = (ProjectWrapper)obj;

		if (Validator.equals(_project, projectWrapper._project)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Project getWrappedProject() {
		return _project;
	}

	@Override
	public Project getWrappedModel() {
		return _project;
	}

	@Override
	public void resetOriginalValues() {
		_project.resetOriginalValues();
	}

	private Project _project;
}