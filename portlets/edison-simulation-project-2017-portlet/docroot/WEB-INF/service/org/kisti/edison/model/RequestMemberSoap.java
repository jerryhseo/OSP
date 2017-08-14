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

import org.kisti.edison.service.persistence.RequestMemberPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.service.http.RequestMemberServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.service.http.RequestMemberServiceSoap
 * @generated
 */
public class RequestMemberSoap implements Serializable {
	public static RequestMemberSoap toSoapModel(RequestMember model) {
		RequestMemberSoap soapModel = new RequestMemberSoap();

		soapModel.setRequestSeq(model.getRequestSeq());
		soapModel.setSimulationProjectId(model.getSimulationProjectId());
		soapModel.setUserId(model.getUserId());
		soapModel.setRequestDate(model.getRequestDate());
		soapModel.setProcessDate(model.getProcessDate());
		soapModel.setRequestState(model.getRequestState());

		return soapModel;
	}

	public static RequestMemberSoap[] toSoapModels(RequestMember[] models) {
		RequestMemberSoap[] soapModels = new RequestMemberSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RequestMemberSoap[][] toSoapModels(RequestMember[][] models) {
		RequestMemberSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RequestMemberSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RequestMemberSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RequestMemberSoap[] toSoapModels(List<RequestMember> models) {
		List<RequestMemberSoap> soapModels = new ArrayList<RequestMemberSoap>(models.size());

		for (RequestMember model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RequestMemberSoap[soapModels.size()]);
	}

	public RequestMemberSoap() {
	}

	public RequestMemberPK getPrimaryKey() {
		return new RequestMemberPK(_requestSeq, _simulationProjectId);
	}

	public void setPrimaryKey(RequestMemberPK pk) {
		setRequestSeq(pk.requestSeq);
		setSimulationProjectId(pk.simulationProjectId);
	}

	public long getRequestSeq() {
		return _requestSeq;
	}

	public void setRequestSeq(long requestSeq) {
		_requestSeq = requestSeq;
	}

	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;
	}

	public Long getUserId() {
		return _userId;
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	public Date getRequestDate() {
		return _requestDate;
	}

	public void setRequestDate(Date requestDate) {
		_requestDate = requestDate;
	}

	public Date getProcessDate() {
		return _processDate;
	}

	public void setProcessDate(Date processDate) {
		_processDate = processDate;
	}

	public String getRequestState() {
		return _requestState;
	}

	public void setRequestState(String requestState) {
		_requestState = requestState;
	}

	private long _requestSeq;
	private long _simulationProjectId;
	private Long _userId;
	private Date _requestDate;
	private Date _processDate;
	private String _requestState;
}