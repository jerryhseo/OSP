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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowSimulation}.
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulation
 * @generated
 */
public class WorkflowSimulationWrapper implements WorkflowSimulation,
	ModelWrapper<WorkflowSimulation> {
	public WorkflowSimulationWrapper(WorkflowSimulation workflowSimulation) {
		_workflowSimulation = workflowSimulation;
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

	/**
	* Returns the primary key of this workflow simulation.
	*
	* @return the primary key of this workflow simulation
	*/
	@Override
	public long getPrimaryKey() {
		return _workflowSimulation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this workflow simulation.
	*
	* @param primaryKey the primary key of this workflow simulation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workflowSimulation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the simulation ID of this workflow simulation.
	*
	* @return the simulation ID of this workflow simulation
	*/
	@Override
	public long getSimulationId() {
		return _workflowSimulation.getSimulationId();
	}

	/**
	* Sets the simulation ID of this workflow simulation.
	*
	* @param simulationId the simulation ID of this workflow simulation
	*/
	@Override
	public void setSimulationId(long simulationId) {
		_workflowSimulation.setSimulationId(simulationId);
	}

	/**
	* Returns the user ID of this workflow simulation.
	*
	* @return the user ID of this workflow simulation
	*/
	@Override
	public long getUserId() {
		return _workflowSimulation.getUserId();
	}

	/**
	* Sets the user ID of this workflow simulation.
	*
	* @param userId the user ID of this workflow simulation
	*/
	@Override
	public void setUserId(long userId) {
		_workflowSimulation.setUserId(userId);
	}

	/**
	* Returns the user uuid of this workflow simulation.
	*
	* @return the user uuid of this workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulation.getUserUuid();
	}

	/**
	* Sets the user uuid of this workflow simulation.
	*
	* @param userUuid the user uuid of this workflow simulation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_workflowSimulation.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this workflow simulation.
	*
	* @return the create date of this workflow simulation
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _workflowSimulation.getCreateDate();
	}

	/**
	* Sets the create date of this workflow simulation.
	*
	* @param createDate the create date of this workflow simulation
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_workflowSimulation.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this workflow simulation.
	*
	* @return the modified date of this workflow simulation
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _workflowSimulation.getModifiedDate();
	}

	/**
	* Sets the modified date of this workflow simulation.
	*
	* @param modifiedDate the modified date of this workflow simulation
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_workflowSimulation.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the class ID of this workflow simulation.
	*
	* @return the class ID of this workflow simulation
	*/
	@Override
	public long getClassId() {
		return _workflowSimulation.getClassId();
	}

	/**
	* Sets the class ID of this workflow simulation.
	*
	* @param classId the class ID of this workflow simulation
	*/
	@Override
	public void setClassId(long classId) {
		_workflowSimulation.setClassId(classId);
	}

	/**
	* Returns the custom ID of this workflow simulation.
	*
	* @return the custom ID of this workflow simulation
	*/
	@Override
	public long getCustomId() {
		return _workflowSimulation.getCustomId();
	}

	/**
	* Sets the custom ID of this workflow simulation.
	*
	* @param customId the custom ID of this workflow simulation
	*/
	@Override
	public void setCustomId(long customId) {
		_workflowSimulation.setCustomId(customId);
	}

	/**
	* Returns the title of this workflow simulation.
	*
	* @return the title of this workflow simulation
	*/
	@Override
	public java.lang.String getTitle() {
		return _workflowSimulation.getTitle();
	}

	/**
	* Sets the title of this workflow simulation.
	*
	* @param title the title of this workflow simulation
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_workflowSimulation.setTitle(title);
	}

	/**
	* Returns the test yn of this workflow simulation.
	*
	* @return the test yn of this workflow simulation
	*/
	@Override
	public boolean getTestYn() {
		return _workflowSimulation.getTestYn();
	}

	/**
	* Returns <code>true</code> if this workflow simulation is test yn.
	*
	* @return <code>true</code> if this workflow simulation is test yn; <code>false</code> otherwise
	*/
	@Override
	public boolean isTestYn() {
		return _workflowSimulation.isTestYn();
	}

	/**
	* Sets whether this workflow simulation is test yn.
	*
	* @param testYn the test yn of this workflow simulation
	*/
	@Override
	public void setTestYn(boolean testYn) {
		_workflowSimulation.setTestYn(testYn);
	}

	/**
	* Returns the workflow ID of this workflow simulation.
	*
	* @return the workflow ID of this workflow simulation
	*/
	@Override
	public long getWorkflowId() {
		return _workflowSimulation.getWorkflowId();
	}

	/**
	* Sets the workflow ID of this workflow simulation.
	*
	* @param workflowId the workflow ID of this workflow simulation
	*/
	@Override
	public void setWorkflowId(long workflowId) {
		_workflowSimulation.setWorkflowId(workflowId);
	}

	@Override
	public boolean isNew() {
		return _workflowSimulation.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_workflowSimulation.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _workflowSimulation.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workflowSimulation.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _workflowSimulation.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowSimulation.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_workflowSimulation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowSimulation.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_workflowSimulation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_workflowSimulation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowSimulation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WorkflowSimulationWrapper((WorkflowSimulation)_workflowSimulation.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation) {
		return _workflowSimulation.compareTo(workflowSimulation);
	}

	@Override
	public int hashCode() {
		return _workflowSimulation.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.WorkflowSimulation> toCacheModel() {
		return _workflowSimulation.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulation toEscapedModel() {
		return new WorkflowSimulationWrapper(_workflowSimulation.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulation toUnescapedModel() {
		return new WorkflowSimulationWrapper(_workflowSimulation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowSimulation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _workflowSimulation.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulation.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowSimulationWrapper)) {
			return false;
		}

		WorkflowSimulationWrapper workflowSimulationWrapper = (WorkflowSimulationWrapper)obj;

		if (Validator.equals(_workflowSimulation,
					workflowSimulationWrapper._workflowSimulation)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WorkflowSimulation getWrappedWorkflowSimulation() {
		return _workflowSimulation;
	}

	@Override
	public WorkflowSimulation getWrappedModel() {
		return _workflowSimulation;
	}

	@Override
	public void resetOriginalValues() {
		_workflowSimulation.resetOriginalValues();
	}

	private WorkflowSimulation _workflowSimulation;
}