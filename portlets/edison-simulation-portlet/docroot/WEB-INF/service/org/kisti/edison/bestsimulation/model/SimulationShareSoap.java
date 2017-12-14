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

package org.kisti.edison.bestsimulation.model;

import org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.bestsimulation.service.http.SimulationShareServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.http.SimulationShareServiceSoap
 * @generated
 */
public class SimulationShareSoap implements Serializable {
	public static SimulationShareSoap toSoapModel(SimulationShare model) {
		SimulationShareSoap soapModel = new SimulationShareSoap();

		soapModel.setShareSeqno(model.getShareSeqno());
		soapModel.setJobSeqNo(model.getJobSeqNo());
		soapModel.setJobUuid(model.getJobUuid());
		soapModel.setSimulationUuid(model.getSimulationUuid());
		soapModel.setClassId(model.getClassId());
		soapModel.setCustomId(model.getCustomId());
		soapModel.setSimulationShareDt(model.getSimulationShareDt());

		return soapModel;
	}

	public static SimulationShareSoap[] toSoapModels(SimulationShare[] models) {
		SimulationShareSoap[] soapModels = new SimulationShareSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimulationShareSoap[][] toSoapModels(
		SimulationShare[][] models) {
		SimulationShareSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimulationShareSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimulationShareSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimulationShareSoap[] toSoapModels(
		List<SimulationShare> models) {
		List<SimulationShareSoap> soapModels = new ArrayList<SimulationShareSoap>(models.size());

		for (SimulationShare model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimulationShareSoap[soapModels.size()]);
	}

	public SimulationShareSoap() {
	}

	public SimulationSharePK getPrimaryKey() {
		return new SimulationSharePK(_shareSeqno, _jobSeqNo, _jobUuid,
			_simulationUuid);
	}

	public void setPrimaryKey(SimulationSharePK pk) {
		setShareSeqno(pk.shareSeqno);
		setJobSeqNo(pk.jobSeqNo);
		setJobUuid(pk.jobUuid);
		setSimulationUuid(pk.simulationUuid);
	}

	public long getShareSeqno() {
		return _shareSeqno;
	}

	public void setShareSeqno(long shareSeqno) {
		_shareSeqno = shareSeqno;
	}

	public long getJobSeqNo() {
		return _jobSeqNo;
	}

	public void setJobSeqNo(long jobSeqNo) {
		_jobSeqNo = jobSeqNo;
	}

	public String getJobUuid() {
		return _jobUuid;
	}

	public void setJobUuid(String jobUuid) {
		_jobUuid = jobUuid;
	}

	public String getSimulationUuid() {
		return _simulationUuid;
	}

	public void setSimulationUuid(String simulationUuid) {
		_simulationUuid = simulationUuid;
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

	public Date getSimulationShareDt() {
		return _simulationShareDt;
	}

	public void setSimulationShareDt(Date simulationShareDt) {
		_simulationShareDt = simulationShareDt;
	}

	private long _shareSeqno;
	private long _jobSeqNo;
	private String _jobUuid;
	private String _simulationUuid;
	private long _classId;
	private long _customId;
	private Date _simulationShareDt;
}