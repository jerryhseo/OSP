/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.model;

import org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.bestsimulation.service.http.SimulationExeStsMigrationServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.http.SimulationExeStsMigrationServiceSoap
 * @generated
 */
public class SimulationExeStsMigrationSoap implements Serializable {
	public static SimulationExeStsMigrationSoap toSoapModel(
		SimulationExeStsMigration model) {
		SimulationExeStsMigrationSoap soapModel = new SimulationExeStsMigrationSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setSubmitDate(model.getSubmitDate());
		soapModel.setUserCnt(model.getUserCnt());
		soapModel.setJobCnt(model.getJobCnt());
		soapModel.setRuntime(model.getRuntime());

		return soapModel;
	}

	public static SimulationExeStsMigrationSoap[] toSoapModels(
		SimulationExeStsMigration[] models) {
		SimulationExeStsMigrationSoap[] soapModels = new SimulationExeStsMigrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimulationExeStsMigrationSoap[][] toSoapModels(
		SimulationExeStsMigration[][] models) {
		SimulationExeStsMigrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimulationExeStsMigrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimulationExeStsMigrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimulationExeStsMigrationSoap[] toSoapModels(
		List<SimulationExeStsMigration> models) {
		List<SimulationExeStsMigrationSoap> soapModels = new ArrayList<SimulationExeStsMigrationSoap>(models.size());

		for (SimulationExeStsMigration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimulationExeStsMigrationSoap[soapModels.size()]);
	}

	public SimulationExeStsMigrationSoap() {
	}

	public SimulationExeStsMigrationPK getPrimaryKey() {
		return new SimulationExeStsMigrationPK(_scienceAppId, _groupId,
			_submitDate);
	}

	public void setPrimaryKey(SimulationExeStsMigrationPK pk) {
		setScienceAppId(pk.scienceAppId);
		setGroupId(pk.groupId);
		setSubmitDate(pk.submitDate);
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getSubmitDate() {
		return _submitDate;
	}

	public void setSubmitDate(String submitDate) {
		_submitDate = submitDate;
	}

	public long getUserCnt() {
		return _userCnt;
	}

	public void setUserCnt(long userCnt) {
		_userCnt = userCnt;
	}

	public long getJobCnt() {
		return _jobCnt;
	}

	public void setJobCnt(long jobCnt) {
		_jobCnt = jobCnt;
	}

	public long getRuntime() {
		return _runtime;
	}

	public void setRuntime(long runtime) {
		_runtime = runtime;
	}

	private long _scienceAppId;
	private long _groupId;
	private String _submitDate;
	private long _userCnt;
	private long _jobCnt;
	private long _runtime;
}