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
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChallengeEvaluationServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChallengeEvaluationServiceSoap
 * @generated
 */
public class ChallengeEvaluationSoap implements Serializable {
	public static ChallengeEvaluationSoap toSoapModel(ChallengeEvaluation model) {
		ChallengeEvaluationSoap soapModel = new ChallengeEvaluationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChallengeEvaluationId(model.getChallengeEvaluationId());
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
		soapModel.setAssessmentItem(model.getAssessmentItem());
		soapModel.setDistribution(model.getDistribution());
		soapModel.setScore(model.getScore());
		soapModel.setChallengeTeamId(model.getChallengeTeamId());

		return soapModel;
	}

	public static ChallengeEvaluationSoap[] toSoapModels(
		ChallengeEvaluation[] models) {
		ChallengeEvaluationSoap[] soapModels = new ChallengeEvaluationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeEvaluationSoap[][] toSoapModels(
		ChallengeEvaluation[][] models) {
		ChallengeEvaluationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeEvaluationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeEvaluationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeEvaluationSoap[] toSoapModels(
		List<ChallengeEvaluation> models) {
		List<ChallengeEvaluationSoap> soapModels = new ArrayList<ChallengeEvaluationSoap>(models.size());

		for (ChallengeEvaluation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeEvaluationSoap[soapModels.size()]);
	}

	public ChallengeEvaluationSoap() {
	}

	public long getPrimaryKey() {
		return _challengeEvaluationId;
	}

	public void setPrimaryKey(long pk) {
		setChallengeEvaluationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChallengeEvaluationId() {
		return _challengeEvaluationId;
	}

	public void setChallengeEvaluationId(long challengeEvaluationId) {
		_challengeEvaluationId = challengeEvaluationId;
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

	public String getAssessmentItem() {
		return _assessmentItem;
	}

	public void setAssessmentItem(String assessmentItem) {
		_assessmentItem = assessmentItem;
	}

	public double getDistribution() {
		return _distribution;
	}

	public void setDistribution(double distribution) {
		_distribution = distribution;
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

	private String _uuid;
	private long _challengeEvaluationId;
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
	private String _assessmentItem;
	private double _distribution;
	private double _score;
	private long _challengeTeamId;
}