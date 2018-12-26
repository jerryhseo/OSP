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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author EDISON
 * @generated
 */
public class WorkflowSimulationJobSoap implements Serializable {
	public static WorkflowSimulationJobSoap toSoapModel(
		WorkflowSimulationJob model) {
		WorkflowSimulationJobSoap soapModel = new WorkflowSimulationJobSoap();

		soapModel.setSimulationJobId(model.getSimulationJobId());
		soapModel.setSimulationId(model.getSimulationId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusResponse(model.getStatusResponse());
		soapModel.setStartTime(model.getStartTime());
		soapModel.setEndTime(model.getEndTime());
		soapModel.setWorkflowId(model.getWorkflowId());
		soapModel.setWorkflowUUID(model.getWorkflowUUID());
		soapModel.setReuseWorkflowUUID(model.getReuseWorkflowUUID());
		soapModel.setScreenLogic(model.getScreenLogic());

		return soapModel;
	}

	public static WorkflowSimulationJobSoap[] toSoapModels(
		WorkflowSimulationJob[] models) {
		WorkflowSimulationJobSoap[] soapModels = new WorkflowSimulationJobSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowSimulationJobSoap[][] toSoapModels(
		WorkflowSimulationJob[][] models) {
		WorkflowSimulationJobSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowSimulationJobSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowSimulationJobSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowSimulationJobSoap[] toSoapModels(
		List<WorkflowSimulationJob> models) {
		List<WorkflowSimulationJobSoap> soapModels = new ArrayList<WorkflowSimulationJobSoap>(models.size());

		for (WorkflowSimulationJob model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowSimulationJobSoap[soapModels.size()]);
	}

	public WorkflowSimulationJobSoap() {
	}

	public long getPrimaryKey() {
		return _simulationJobId;
	}

	public void setPrimaryKey(long pk) {
		setSimulationJobId(pk);
	}

	public long getSimulationJobId() {
		return _simulationJobId;
	}

	public void setSimulationJobId(long simulationJobId) {
		_simulationJobId = simulationJobId;
	}

	public long getSimulationId() {
		return _simulationId;
	}

	public void setSimulationId(long simulationId) {
		_simulationId = simulationId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getStatusResponse() {
		return _statusResponse;
	}

	public void setStatusResponse(String statusResponse) {
		_statusResponse = statusResponse;
	}

	public Date getStartTime() {
		return _startTime;
	}

	public void setStartTime(Date startTime) {
		_startTime = startTime;
	}

	public Date getEndTime() {
		return _endTime;
	}

	public void setEndTime(Date endTime) {
		_endTime = endTime;
	}

	public long getWorkflowId() {
		return _workflowId;
	}

	public void setWorkflowId(long workflowId) {
		_workflowId = workflowId;
	}

	public String getWorkflowUUID() {
		return _workflowUUID;
	}

	public void setWorkflowUUID(String workflowUUID) {
		_workflowUUID = workflowUUID;
	}

	public String getReuseWorkflowUUID() {
		return _reuseWorkflowUUID;
	}

	public void setReuseWorkflowUUID(String reuseWorkflowUUID) {
		_reuseWorkflowUUID = reuseWorkflowUUID;
	}

	public String getScreenLogic() {
		return _screenLogic;
	}

	public void setScreenLogic(String screenLogic) {
		_screenLogic = screenLogic;
	}

	private long _simulationJobId;
	private long _simulationId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _status;
	private String _statusResponse;
	private Date _startTime;
	private Date _endTime;
	private long _workflowId;
	private String _workflowUUID;
	private String _reuseWorkflowUUID;
	private String _screenLogic;
}