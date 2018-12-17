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

import org.kisti.edison.service.persistence.SimulationJobPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author edison
 * @generated
 */
public class SimulationJobSoap implements Serializable {
	public static SimulationJobSoap toSoapModel(SimulationJob model) {
		SimulationJobSoap soapModel = new SimulationJobSoap();

		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCnt(model.getCnt());

		return soapModel;
	}

	public static SimulationJobSoap[] toSoapModels(SimulationJob[] models) {
		SimulationJobSoap[] soapModels = new SimulationJobSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimulationJobSoap[][] toSoapModels(SimulationJob[][] models) {
		SimulationJobSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimulationJobSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimulationJobSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimulationJobSoap[] toSoapModels(List<SimulationJob> models) {
		List<SimulationJobSoap> soapModels = new ArrayList<SimulationJobSoap>(models.size());

		for (SimulationJob model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimulationJobSoap[soapModels.size()]);
	}

	public SimulationJobSoap() {
	}

	public SimulationJobPK getPrimaryKey() {
		return new SimulationJobPK(_createDate, _groupId);
	}

	public void setPrimaryKey(SimulationJobPK pk) {
		setCreateDate(pk.createDate);
		setGroupId(pk.groupId);
	}

	public String getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(String createDate) {
		_createDate = createDate;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Long getCnt() {
		return _cnt;
	}

	public void setCnt(Long cnt) {
		_cnt = cnt;
	}

	private String _createDate;
	private long _groupId;
	private Long _cnt;
}