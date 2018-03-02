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

package kisti.edison.challenge.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChallengeServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChallengeServiceSoap
 * @generated
 */
public class ChallengeSoap implements Serializable {
	public static ChallengeSoap toSoapModel(Challenge model) {
		ChallengeSoap soapModel = new ChallengeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChallengeId(model.getChallengeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setName(model.getName());
		soapModel.setField(model.getField());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ChallengeSoap[] toSoapModels(Challenge[] models) {
		ChallengeSoap[] soapModels = new ChallengeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeSoap[][] toSoapModels(Challenge[][] models) {
		ChallengeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeSoap[] toSoapModels(List<Challenge> models) {
		List<ChallengeSoap> soapModels = new ArrayList<ChallengeSoap>(models.size());

		for (Challenge model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeSoap[soapModels.size()]);
	}

	public ChallengeSoap() {
	}

	public long getPrimaryKey() {
		return _challengeId;
	}

	public void setPrimaryKey(long pk) {
		setChallengeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChallengeId() {
		return _challengeId;
	}

	public void setChallengeId(long challengeId) {
		_challengeId = challengeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getField() {
		return _field;
	}

	public void setField(String field) {
		_field = field;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _challengeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _name;
	private String _field;
	private String _description;
}