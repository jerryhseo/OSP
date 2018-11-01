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

package org.kisti.edison.science.model;

import org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.science.service.http.ScienceAppRatingsEntryServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.http.ScienceAppRatingsEntryServiceSoap
 * @generated
 */
public class ScienceAppRatingsEntrySoap implements Serializable {
	public static ScienceAppRatingsEntrySoap toSoapModel(
		ScienceAppRatingsEntry model) {
		ScienceAppRatingsEntrySoap soapModel = new ScienceAppRatingsEntrySoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setScore(model.getScore());

		return soapModel;
	}

	public static ScienceAppRatingsEntrySoap[] toSoapModels(
		ScienceAppRatingsEntry[] models) {
		ScienceAppRatingsEntrySoap[] soapModels = new ScienceAppRatingsEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppRatingsEntrySoap[][] toSoapModels(
		ScienceAppRatingsEntry[][] models) {
		ScienceAppRatingsEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppRatingsEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppRatingsEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppRatingsEntrySoap[] toSoapModels(
		List<ScienceAppRatingsEntry> models) {
		List<ScienceAppRatingsEntrySoap> soapModels = new ArrayList<ScienceAppRatingsEntrySoap>(models.size());

		for (ScienceAppRatingsEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppRatingsEntrySoap[soapModels.size()]);
	}

	public ScienceAppRatingsEntrySoap() {
	}

	public ScienceAppRatingsEntryPK getPrimaryKey() {
		return new ScienceAppRatingsEntryPK(_userId, _scienceAppId);
	}

	public void setPrimaryKey(ScienceAppRatingsEntryPK pk) {
		setUserId(pk.userId);
		setScienceAppId(pk.scienceAppId);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getScore() {
		return _score;
	}

	public void setScore(long score) {
		_score = score;
	}

	private long _userId;
	private long _scienceAppId;
	private long _companyId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _score;
}