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
public class WorkflowSimulationSoap implements Serializable {
	public static WorkflowSimulationSoap toSoapModel(WorkflowSimulation model) {
		WorkflowSimulationSoap soapModel = new WorkflowSimulationSoap();

		soapModel.setSimulationId(model.getSimulationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassId(model.getClassId());
		soapModel.setCustomId(model.getCustomId());
		soapModel.setTitle(model.getTitle());
		soapModel.setTestYn(model.getTestYn());
		soapModel.setWorkflowId(model.getWorkflowId());

		return soapModel;
	}

	public static WorkflowSimulationSoap[] toSoapModels(
		WorkflowSimulation[] models) {
		WorkflowSimulationSoap[] soapModels = new WorkflowSimulationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowSimulationSoap[][] toSoapModels(
		WorkflowSimulation[][] models) {
		WorkflowSimulationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowSimulationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowSimulationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowSimulationSoap[] toSoapModels(
		List<WorkflowSimulation> models) {
		List<WorkflowSimulationSoap> soapModels = new ArrayList<WorkflowSimulationSoap>(models.size());

		for (WorkflowSimulation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowSimulationSoap[soapModels.size()]);
	}

	public WorkflowSimulationSoap() {
	}

	public long getPrimaryKey() {
		return _simulationId;
	}

	public void setPrimaryKey(long pk) {
		setSimulationId(pk);
	}

	public long getSimulationId() {
		return _simulationId;
	}

	public void setSimulationId(long simulationId) {
		_simulationId = simulationId;
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

	public long getClassId() {
		return _classId;
	}

	public void setClassId(long classId) {
		_classId = classId;
	}

	public long getCustomId() {
		return _customId;
	}

	public void setCustomId(long customId) {
		_customId = customId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public boolean getTestYn() {
		return _testYn;
	}

	public boolean isTestYn() {
		return _testYn;
	}

	public void setTestYn(boolean testYn) {
		_testYn = testYn;
	}

	public long getWorkflowId() {
		return _workflowId;
	}

	public void setWorkflowId(long workflowId) {
		_workflowId = workflowId;
	}

	private long _simulationId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classId;
	private long _customId;
	private String _title;
	private boolean _testYn;
	private long _workflowId;
}