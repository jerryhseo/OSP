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

package org.kisti.edison.simulation.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.simulation.service.http.BatchMonitoringServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.simulation.service.http.BatchMonitoringServiceSoap
 * @generated
 */
public class BatchMonitoringSoap implements Serializable {
	public static BatchMonitoringSoap toSoapModel(BatchMonitoring model) {
		BatchMonitoringSoap soapModel = new BatchMonitoringSoap();

		soapModel.setBatSeqNo(model.getBatSeqNo());
		soapModel.setBatDiv(model.getBatDiv());
		soapModel.setManualYN(model.getManualYN());
		soapModel.setStatus(model.getStatus());
		soapModel.setMessage(model.getMessage());
		soapModel.setInsertId(model.getInsertId());
		soapModel.setExeDate(model.getExeDate());

		return soapModel;
	}

	public static BatchMonitoringSoap[] toSoapModels(BatchMonitoring[] models) {
		BatchMonitoringSoap[] soapModels = new BatchMonitoringSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BatchMonitoringSoap[][] toSoapModels(
		BatchMonitoring[][] models) {
		BatchMonitoringSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BatchMonitoringSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BatchMonitoringSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BatchMonitoringSoap[] toSoapModels(
		List<BatchMonitoring> models) {
		List<BatchMonitoringSoap> soapModels = new ArrayList<BatchMonitoringSoap>(models.size());

		for (BatchMonitoring model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BatchMonitoringSoap[soapModels.size()]);
	}

	public BatchMonitoringSoap() {
	}

	public long getPrimaryKey() {
		return _batSeqNo;
	}

	public void setPrimaryKey(long pk) {
		setBatSeqNo(pk);
	}

	public long getBatSeqNo() {
		return _batSeqNo;
	}

	public void setBatSeqNo(long batSeqNo) {
		_batSeqNo = batSeqNo;
	}

	public String getBatDiv() {
		return _batDiv;
	}

	public void setBatDiv(String batDiv) {
		_batDiv = batDiv;
	}

	public String getManualYN() {
		return _manualYN;
	}

	public void setManualYN(String manualYN) {
		_manualYN = manualYN;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public long getInsertId() {
		return _insertId;
	}

	public void setInsertId(long insertId) {
		_insertId = insertId;
	}

	public Date getExeDate() {
		return _exeDate;
	}

	public void setExeDate(Date exeDate) {
		_exeDate = exeDate;
	}

	private long _batSeqNo;
	private String _batDiv;
	private String _manualYN;
	private String _status;
	private String _message;
	private long _insertId;
	private Date _exeDate;
}