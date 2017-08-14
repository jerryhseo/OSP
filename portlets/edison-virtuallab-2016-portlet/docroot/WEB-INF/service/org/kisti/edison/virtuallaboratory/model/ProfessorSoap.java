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

package org.kisti.edison.virtuallaboratory.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.virtuallaboratory.service.http.ProfessorServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.http.ProfessorServiceSoap
 * @generated
 */
public class ProfessorSoap implements Serializable {
	public static ProfessorSoap toSoapModel(Professor model) {
		ProfessorSoap soapModel = new ProfessorSoap();

		soapModel.setProfessorSeq(model.getProfessorSeq());
		soapModel.setUserId(model.getUserId());
		soapModel.setRecord(model.getRecord());
		soapModel.setPaper(model.getPaper());
		soapModel.setHomepage(model.getHomepage());
		soapModel.setIntroduce(model.getIntroduce());
		soapModel.setPhone(model.getPhone());

		return soapModel;
	}

	public static ProfessorSoap[] toSoapModels(Professor[] models) {
		ProfessorSoap[] soapModels = new ProfessorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProfessorSoap[][] toSoapModels(Professor[][] models) {
		ProfessorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProfessorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProfessorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProfessorSoap[] toSoapModels(List<Professor> models) {
		List<ProfessorSoap> soapModels = new ArrayList<ProfessorSoap>(models.size());

		for (Professor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProfessorSoap[soapModels.size()]);
	}

	public ProfessorSoap() {
	}

	public long getPrimaryKey() {
		return _professorSeq;
	}

	public void setPrimaryKey(long pk) {
		setProfessorSeq(pk);
	}

	public long getProfessorSeq() {
		return _professorSeq;
	}

	public void setProfessorSeq(long professorSeq) {
		_professorSeq = professorSeq;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getRecord() {
		return _record;
	}

	public void setRecord(String record) {
		_record = record;
	}

	public String getPaper() {
		return _paper;
	}

	public void setPaper(String paper) {
		_paper = paper;
	}

	public String getHomepage() {
		return _homepage;
	}

	public void setHomepage(String homepage) {
		_homepage = homepage;
	}

	public String getIntroduce() {
		return _introduce;
	}

	public void setIntroduce(String introduce) {
		_introduce = introduce;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	private long _professorSeq;
	private long _userId;
	private String _record;
	private String _paper;
	private String _homepage;
	private String _introduce;
	private String _phone;
}