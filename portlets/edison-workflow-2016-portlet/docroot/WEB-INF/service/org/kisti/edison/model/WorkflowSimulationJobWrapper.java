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
 * This class is a wrapper for {@link WorkflowSimulationJob}.
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationJob
 * @generated
 */
public class WorkflowSimulationJobWrapper implements WorkflowSimulationJob,
	ModelWrapper<WorkflowSimulationJob> {
	public WorkflowSimulationJobWrapper(
		WorkflowSimulationJob workflowSimulationJob) {
		_workflowSimulationJob = workflowSimulationJob;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowSimulationJob.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowSimulationJob.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationJobId", getSimulationJobId());
		attributes.put("simulationId", getSimulationId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("status", getStatus());
		attributes.put("statusResponse", getStatusResponse());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("workflowUUID", getWorkflowUUID());
		attributes.put("reuseWorkflowUUID", getReuseWorkflowUUID());
		attributes.put("screenLogic", getScreenLogic());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simulationJobId = (Long)attributes.get("simulationJobId");

		if (simulationJobId != null) {
			setSimulationJobId(simulationJobId);
		}

		Long simulationId = (Long)attributes.get("simulationId");

		if (simulationId != null) {
			setSimulationId(simulationId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String statusResponse = (String)attributes.get("statusResponse");

		if (statusResponse != null) {
			setStatusResponse(statusResponse);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		Long workflowId = (Long)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		String workflowUUID = (String)attributes.get("workflowUUID");

		if (workflowUUID != null) {
			setWorkflowUUID(workflowUUID);
		}

		String reuseWorkflowUUID = (String)attributes.get("reuseWorkflowUUID");

		if (reuseWorkflowUUID != null) {
			setReuseWorkflowUUID(reuseWorkflowUUID);
		}

		String screenLogic = (String)attributes.get("screenLogic");

		if (screenLogic != null) {
			setScreenLogic(screenLogic);
		}
	}

	/**
	* Returns the primary key of this workflow simulation job.
	*
	* @return the primary key of this workflow simulation job
	*/
	@Override
	public long getPrimaryKey() {
		return _workflowSimulationJob.getPrimaryKey();
	}

	/**
	* Sets the primary key of this workflow simulation job.
	*
	* @param primaryKey the primary key of this workflow simulation job
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_workflowSimulationJob.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the simulation job ID of this workflow simulation job.
	*
	* @return the simulation job ID of this workflow simulation job
	*/
	@Override
	public long getSimulationJobId() {
		return _workflowSimulationJob.getSimulationJobId();
	}

	/**
	* Sets the simulation job ID of this workflow simulation job.
	*
	* @param simulationJobId the simulation job ID of this workflow simulation job
	*/
	@Override
	public void setSimulationJobId(long simulationJobId) {
		_workflowSimulationJob.setSimulationJobId(simulationJobId);
	}

	/**
	* Returns the simulation ID of this workflow simulation job.
	*
	* @return the simulation ID of this workflow simulation job
	*/
	@Override
	public long getSimulationId() {
		return _workflowSimulationJob.getSimulationId();
	}

	/**
	* Sets the simulation ID of this workflow simulation job.
	*
	* @param simulationId the simulation ID of this workflow simulation job
	*/
	@Override
	public void setSimulationId(long simulationId) {
		_workflowSimulationJob.setSimulationId(simulationId);
	}

	/**
	* Returns the group ID of this workflow simulation job.
	*
	* @return the group ID of this workflow simulation job
	*/
	@Override
	public long getGroupId() {
		return _workflowSimulationJob.getGroupId();
	}

	/**
	* Sets the group ID of this workflow simulation job.
	*
	* @param groupId the group ID of this workflow simulation job
	*/
	@Override
	public void setGroupId(long groupId) {
		_workflowSimulationJob.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this workflow simulation job.
	*
	* @return the user ID of this workflow simulation job
	*/
	@Override
	public long getUserId() {
		return _workflowSimulationJob.getUserId();
	}

	/**
	* Sets the user ID of this workflow simulation job.
	*
	* @param userId the user ID of this workflow simulation job
	*/
	@Override
	public void setUserId(long userId) {
		_workflowSimulationJob.setUserId(userId);
	}

	/**
	* Returns the user uuid of this workflow simulation job.
	*
	* @return the user uuid of this workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowSimulationJob.getUserUuid();
	}

	/**
	* Sets the user uuid of this workflow simulation job.
	*
	* @param userUuid the user uuid of this workflow simulation job
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_workflowSimulationJob.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this workflow simulation job.
	*
	* @return the create date of this workflow simulation job
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _workflowSimulationJob.getCreateDate();
	}

	/**
	* Sets the create date of this workflow simulation job.
	*
	* @param createDate the create date of this workflow simulation job
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_workflowSimulationJob.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this workflow simulation job.
	*
	* @return the modified date of this workflow simulation job
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _workflowSimulationJob.getModifiedDate();
	}

	/**
	* Sets the modified date of this workflow simulation job.
	*
	* @param modifiedDate the modified date of this workflow simulation job
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_workflowSimulationJob.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the title of this workflow simulation job.
	*
	* @return the title of this workflow simulation job
	*/
	@Override
	public java.lang.String getTitle() {
		return _workflowSimulationJob.getTitle();
	}

	/**
	* Sets the title of this workflow simulation job.
	*
	* @param title the title of this workflow simulation job
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_workflowSimulationJob.setTitle(title);
	}

	/**
	* Returns the status of this workflow simulation job.
	*
	* @return the status of this workflow simulation job
	*/
	@Override
	public java.lang.String getStatus() {
		return _workflowSimulationJob.getStatus();
	}

	/**
	* Sets the status of this workflow simulation job.
	*
	* @param status the status of this workflow simulation job
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_workflowSimulationJob.setStatus(status);
	}

	/**
	* Returns the status response of this workflow simulation job.
	*
	* @return the status response of this workflow simulation job
	*/
	@Override
	public java.lang.String getStatusResponse() {
		return _workflowSimulationJob.getStatusResponse();
	}

	/**
	* Sets the status response of this workflow simulation job.
	*
	* @param statusResponse the status response of this workflow simulation job
	*/
	@Override
	public void setStatusResponse(java.lang.String statusResponse) {
		_workflowSimulationJob.setStatusResponse(statusResponse);
	}

	/**
	* Returns the start time of this workflow simulation job.
	*
	* @return the start time of this workflow simulation job
	*/
	@Override
	public java.util.Date getStartTime() {
		return _workflowSimulationJob.getStartTime();
	}

	/**
	* Sets the start time of this workflow simulation job.
	*
	* @param startTime the start time of this workflow simulation job
	*/
	@Override
	public void setStartTime(java.util.Date startTime) {
		_workflowSimulationJob.setStartTime(startTime);
	}

	/**
	* Returns the end time of this workflow simulation job.
	*
	* @return the end time of this workflow simulation job
	*/
	@Override
	public java.util.Date getEndTime() {
		return _workflowSimulationJob.getEndTime();
	}

	/**
	* Sets the end time of this workflow simulation job.
	*
	* @param endTime the end time of this workflow simulation job
	*/
	@Override
	public void setEndTime(java.util.Date endTime) {
		_workflowSimulationJob.setEndTime(endTime);
	}

	/**
	* Returns the workflow ID of this workflow simulation job.
	*
	* @return the workflow ID of this workflow simulation job
	*/
	@Override
	public long getWorkflowId() {
		return _workflowSimulationJob.getWorkflowId();
	}

	/**
	* Sets the workflow ID of this workflow simulation job.
	*
	* @param workflowId the workflow ID of this workflow simulation job
	*/
	@Override
	public void setWorkflowId(long workflowId) {
		_workflowSimulationJob.setWorkflowId(workflowId);
	}

	/**
	* Returns the workflow u u i d of this workflow simulation job.
	*
	* @return the workflow u u i d of this workflow simulation job
	*/
	@Override
	public java.lang.String getWorkflowUUID() {
		return _workflowSimulationJob.getWorkflowUUID();
	}

	/**
	* Sets the workflow u u i d of this workflow simulation job.
	*
	* @param workflowUUID the workflow u u i d of this workflow simulation job
	*/
	@Override
	public void setWorkflowUUID(java.lang.String workflowUUID) {
		_workflowSimulationJob.setWorkflowUUID(workflowUUID);
	}

	/**
	* Returns the reuse workflow u u i d of this workflow simulation job.
	*
	* @return the reuse workflow u u i d of this workflow simulation job
	*/
	@Override
	public java.lang.String getReuseWorkflowUUID() {
		return _workflowSimulationJob.getReuseWorkflowUUID();
	}

	/**
	* Sets the reuse workflow u u i d of this workflow simulation job.
	*
	* @param reuseWorkflowUUID the reuse workflow u u i d of this workflow simulation job
	*/
	@Override
	public void setReuseWorkflowUUID(java.lang.String reuseWorkflowUUID) {
		_workflowSimulationJob.setReuseWorkflowUUID(reuseWorkflowUUID);
	}

	/**
	* Returns the screen logic of this workflow simulation job.
	*
	* @return the screen logic of this workflow simulation job
	*/
	@Override
	public java.lang.String getScreenLogic() {
		return _workflowSimulationJob.getScreenLogic();
	}

	/**
	* Sets the screen logic of this workflow simulation job.
	*
	* @param screenLogic the screen logic of this workflow simulation job
	*/
	@Override
	public void setScreenLogic(java.lang.String screenLogic) {
		_workflowSimulationJob.setScreenLogic(screenLogic);
	}

	@Override
	public boolean isNew() {
		return _workflowSimulationJob.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_workflowSimulationJob.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _workflowSimulationJob.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workflowSimulationJob.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _workflowSimulationJob.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowSimulationJob.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_workflowSimulationJob.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowSimulationJob.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_workflowSimulationJob.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_workflowSimulationJob.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowSimulationJob.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WorkflowSimulationJobWrapper((WorkflowSimulationJob)_workflowSimulationJob.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob) {
		return _workflowSimulationJob.compareTo(workflowSimulationJob);
	}

	@Override
	public int hashCode() {
		return _workflowSimulationJob.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.WorkflowSimulationJob> toCacheModel() {
		return _workflowSimulationJob.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob toEscapedModel() {
		return new WorkflowSimulationJobWrapper(_workflowSimulationJob.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.WorkflowSimulationJob toUnescapedModel() {
		return new WorkflowSimulationJobWrapper(_workflowSimulationJob.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowSimulationJob.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _workflowSimulationJob.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowSimulationJob.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowSimulationJobWrapper)) {
			return false;
		}

		WorkflowSimulationJobWrapper workflowSimulationJobWrapper = (WorkflowSimulationJobWrapper)obj;

		if (Validator.equals(_workflowSimulationJob,
					workflowSimulationJobWrapper._workflowSimulationJob)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WorkflowSimulationJob getWrappedWorkflowSimulationJob() {
		return _workflowSimulationJob;
	}

	@Override
	public WorkflowSimulationJob getWrappedModel() {
		return _workflowSimulationJob;
	}

	@Override
	public void resetOriginalValues() {
		_workflowSimulationJob.resetOriginalValues();
	}

	private WorkflowSimulationJob _workflowSimulationJob;
}