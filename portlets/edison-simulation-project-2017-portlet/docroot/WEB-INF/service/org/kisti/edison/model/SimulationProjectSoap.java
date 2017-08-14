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
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.service.http.SimulationProjectServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.service.http.SimulationProjectServiceSoap
 * @generated
 */
public class SimulationProjectSoap implements Serializable {
	public static SimulationProjectSoap toSoapModel(SimulationProject model) {
		SimulationProjectSoap soapModel = new SimulationProjectSoap();

		soapModel.setSimulationProjectId(model.getSimulationProjectId());
		soapModel.setTitle(model.getTitle());
		soapModel.setServiceLanguage(model.getServiceLanguage());
		soapModel.setProjectOpenYn(model.getProjectOpenYn());
		soapModel.setExplain(model.getExplain());
		soapModel.setIconId(model.getIconId());
		soapModel.setImageFolderId(model.getImageFolderId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setInsertId(model.getInsertId());
		soapModel.setInsertDate(model.getInsertDate());
		soapModel.setUpdateId(model.getUpdateId());
		soapModel.setUpdateDate(model.getUpdateDate());
		soapModel.setTempletId(model.getTempletId());

		return soapModel;
	}

	public static SimulationProjectSoap[] toSoapModels(
		SimulationProject[] models) {
		SimulationProjectSoap[] soapModels = new SimulationProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimulationProjectSoap[][] toSoapModels(
		SimulationProject[][] models) {
		SimulationProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimulationProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimulationProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimulationProjectSoap[] toSoapModels(
		List<SimulationProject> models) {
		List<SimulationProjectSoap> soapModels = new ArrayList<SimulationProjectSoap>(models.size());

		for (SimulationProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimulationProjectSoap[soapModels.size()]);
	}

	public SimulationProjectSoap() {
	}

	public long getPrimaryKey() {
		return _simulationProjectId;
	}

	public void setPrimaryKey(long pk) {
		setSimulationProjectId(pk);
	}

	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getServiceLanguage() {
		return _serviceLanguage;
	}

	public void setServiceLanguage(String serviceLanguage) {
		_serviceLanguage = serviceLanguage;
	}

	public boolean getProjectOpenYn() {
		return _projectOpenYn;
	}

	public boolean isProjectOpenYn() {
		return _projectOpenYn;
	}

	public void setProjectOpenYn(boolean projectOpenYn) {
		_projectOpenYn = projectOpenYn;
	}

	public String getExplain() {
		return _explain;
	}

	public void setExplain(String explain) {
		_explain = explain;
	}

	public long getIconId() {
		return _iconId;
	}

	public void setIconId(long iconId) {
		_iconId = iconId;
	}

	public long getImageFolderId() {
		return _imageFolderId;
	}

	public void setImageFolderId(long imageFolderId) {
		_imageFolderId = imageFolderId;
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	public long getInsertId() {
		return _insertId;
	}

	public void setInsertId(long insertId) {
		_insertId = insertId;
	}

	public Date getInsertDate() {
		return _insertDate;
	}

	public void setInsertDate(Date insertDate) {
		_insertDate = insertDate;
	}

	public long getUpdateId() {
		return _updateId;
	}

	public void setUpdateId(long updateId) {
		_updateId = updateId;
	}

	public Date getUpdateDate() {
		return _updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;
	}

	public long getTempletId() {
		return _templetId;
	}

	public void setTempletId(long templetId) {
		_templetId = templetId;
	}

	private long _simulationProjectId;
	private String _title;
	private String _serviceLanguage;
	private boolean _projectOpenYn;
	private String _explain;
	private long _iconId;
	private long _imageFolderId;
	private long _ownerId;
	private long _insertId;
	private Date _insertDate;
	private long _updateId;
	private Date _updateDate;
	private long _templetId;
}