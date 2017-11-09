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

package org.kisti.eturb.dbservice.model;

import org.kisti.eturb.dbservice.service.persistence.SimulationPK;

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
public class SimulationSoap implements Serializable {
	public static SimulationSoap toSoapModel(Simulation model) {
		SimulationSoap soapModel = new SimulationSoap();

		soapModel.setProjectId(model.getProjectId());
		soapModel.setExecuteId(model.getExecuteId());
		soapModel.setExecuteDataStructure(model.getExecuteDataStructure());
		soapModel.setExecuteDate(model.getExecuteDate());

		return soapModel;
	}

	public static SimulationSoap[] toSoapModels(Simulation[] models) {
		SimulationSoap[] soapModels = new SimulationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimulationSoap[][] toSoapModels(Simulation[][] models) {
		SimulationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimulationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimulationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimulationSoap[] toSoapModels(List<Simulation> models) {
		List<SimulationSoap> soapModels = new ArrayList<SimulationSoap>(models.size());

		for (Simulation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimulationSoap[soapModels.size()]);
	}

	public SimulationSoap() {
	}

	public SimulationPK getPrimaryKey() {
		return new SimulationPK(_projectId, _executeId);
	}

	public void setPrimaryKey(SimulationPK pk) {
		setProjectId(pk.projectId);
		setExecuteId(pk.executeId);
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public String getExecuteId() {
		return _executeId;
	}

	public void setExecuteId(String executeId) {
		_executeId = executeId;
	}

	public String getExecuteDataStructure() {
		return _executeDataStructure;
	}

	public void setExecuteDataStructure(String executeDataStructure) {
		_executeDataStructure = executeDataStructure;
	}

	public Date getExecuteDate() {
		return _executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		_executeDate = executeDate;
	}

	private long _projectId;
	private String _executeId;
	private String _executeDataStructure;
	private Date _executeDate;
}