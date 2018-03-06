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
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChallengeEvaluationScoreServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChallengeEvaluationScoreServiceSoap
 * @generated
 */
public class ChallengeEvaluationScoreSoap implements Serializable {
	public static ChallengeEvaluationScoreSoap toSoapModel(
		ChallengeEvaluationScore model) {
		ChallengeEvaluationScoreSoap soapModel = new ChallengeEvaluationScoreSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChallengeEvaluationScoreId(model.getChallengeEvaluationScoreId());
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
		soapModel.setScore(model.getScore());
		soapModel.setChallengeTeamId(model.getChallengeTeamId());
		soapModel.setChallengeEvaluationManagementId(model.getChallengeEvaluationManagementId());

		return soapModel;
	}

	public static ChallengeEvaluationScoreSoap[] toSoapModels(
		ChallengeEvaluationScore[] models) {
		ChallengeEvaluationScoreSoap[] soapModels = new ChallengeEvaluationScoreSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeEvaluationScoreSoap[][] toSoapModels(
		ChallengeEvaluationScore[][] models) {
		ChallengeEvaluationScoreSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeEvaluationScoreSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeEvaluationScoreSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeEvaluationScoreSoap[] toSoapModels(
		List<ChallengeEvaluationScore> models) {
		List<ChallengeEvaluationScoreSoap> soapModels = new ArrayList<ChallengeEvaluationScoreSoap>(models.size());

		for (ChallengeEvaluationScore model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeEvaluationScoreSoap[soapModels.size()]);
	}

	public ChallengeEvaluationScoreSoap() {
	}

	public long getPrimaryKey() {
		return _challengeEvaluationScoreId;
	}

	public void setPrimaryKey(long pk) {
		setChallengeEvaluationScoreId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChallengeEvaluationScoreId() {
		return _challengeEvaluationScoreId;
	}

	public void setChallengeEvaluationScoreId(long challengeEvaluationScoreId) {
		_challengeEvaluationScoreId = challengeEvaluationScoreId;
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

	public double getScore() {
		return _score;
	}

	public void setScore(double score) {
		_score = score;
	}

	public long getChallengeTeamId() {
		return _challengeTeamId;
	}

	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamId = challengeTeamId;
	}

	public long getChallengeEvaluationManagementId() {
		return _challengeEvaluationManagementId;
	}

	public void setChallengeEvaluationManagementId(
		long challengeEvaluationManagementId) {
		_challengeEvaluationManagementId = challengeEvaluationManagementId;
	}

	private String _uuid;
	private long _challengeEvaluationScoreId;
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
	private double _score;
	private long _challengeTeamId;
	private long _challengeEvaluationManagementId;
}