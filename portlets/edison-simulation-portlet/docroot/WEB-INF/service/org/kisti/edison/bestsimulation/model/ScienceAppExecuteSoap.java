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

import org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.bestsimulation.service.http.ScienceAppExecuteServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.http.ScienceAppExecuteServiceSoap
 * @generated
 */
public class ScienceAppExecuteSoap implements Serializable {
	public static ScienceAppExecuteSoap toSoapModel(ScienceAppExecute model) {
		ScienceAppExecuteSoap soapModel = new ScienceAppExecuteSoap();

		soapModel.setExecuteDate(model.getExecuteDate());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setUserCnt(model.getUserCnt());
		soapModel.setAvgExeTime(model.getAvgExeTime());
		soapModel.setExeCnt(model.getExeCnt());

		return soapModel;
	}

	public static ScienceAppExecuteSoap[] toSoapModels(
		ScienceAppExecute[] models) {
		ScienceAppExecuteSoap[] soapModels = new ScienceAppExecuteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppExecuteSoap[][] toSoapModels(
		ScienceAppExecute[][] models) {
		ScienceAppExecuteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppExecuteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppExecuteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppExecuteSoap[] toSoapModels(
		List<ScienceAppExecute> models) {
		List<ScienceAppExecuteSoap> soapModels = new ArrayList<ScienceAppExecuteSoap>(models.size());

		for (ScienceAppExecute model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppExecuteSoap[soapModels.size()]);
	}

	public ScienceAppExecuteSoap() {
	}

	public ScienceAppExecutePK getPrimaryKey() {
		return new ScienceAppExecutePK(_executeDate, _scienceAppId);
	}

	public void setPrimaryKey(ScienceAppExecutePK pk) {
		setExecuteDate(pk.executeDate);
		setScienceAppId(pk.scienceAppId);
	}

	public String getExecuteDate() {
		return _executeDate;
	}

	public void setExecuteDate(String executeDate) {
		_executeDate = executeDate;
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getUserCnt() {
		return _userCnt;
	}

	public void setUserCnt(long userCnt) {
		_userCnt = userCnt;
	}

	public long getAvgExeTime() {
		return _avgExeTime;
	}

	public void setAvgExeTime(long avgExeTime) {
		_avgExeTime = avgExeTime;
	}

	public long getExeCnt() {
		return _exeCnt;
	}

	public void setExeCnt(long exeCnt) {
		_exeCnt = exeCnt;
	}

	private String _executeDate;
	private long _scienceAppId;
	private long _userCnt;
	private long _avgExeTime;
	private long _exeCnt;
}