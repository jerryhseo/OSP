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
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChildChallengeServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChildChallengeServiceSoap
 * @generated
 */
public class ChildChallengeSoap implements Serializable {
	public static ChildChallengeSoap toSoapModel(ChildChallenge model) {
		ChildChallengeSoap soapModel = new ChildChallengeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChildChallengeId(model.getChildChallengeId());
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
		soapModel.setNumber(model.getNumber());
		soapModel.setPresentationDay(model.getPresentationDay());
		soapModel.setPaperStartDay(model.getPaperStartDay());
		soapModel.setPaperEndDay(model.getPaperEndDay());
		soapModel.setEvaluationDay(model.getEvaluationDay());
		soapModel.setChallengeStartDay(model.getChallengeStartDay());
		soapModel.setChallengeEndDay(model.getChallengeEndDay());
		soapModel.setChallengeStatus(model.getChallengeStatus());
		soapModel.setChallengeId(model.getChallengeId());

		return soapModel;
	}

	public static ChildChallengeSoap[] toSoapModels(ChildChallenge[] models) {
		ChildChallengeSoap[] soapModels = new ChildChallengeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChildChallengeSoap[][] toSoapModels(ChildChallenge[][] models) {
		ChildChallengeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChildChallengeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChildChallengeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChildChallengeSoap[] toSoapModels(List<ChildChallenge> models) {
		List<ChildChallengeSoap> soapModels = new ArrayList<ChildChallengeSoap>(models.size());

		for (ChildChallenge model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChildChallengeSoap[soapModels.size()]);
	}

	public ChildChallengeSoap() {
	}

	public long getPrimaryKey() {
		return _childChallengeId;
	}

	public void setPrimaryKey(long pk) {
		setChildChallengeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChildChallengeId() {
		return _childChallengeId;
	}

	public void setChildChallengeId(long childChallengeId) {
		_childChallengeId = childChallengeId;
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

	public int getNumber() {
		return _number;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public Date getPresentationDay() {
		return _presentationDay;
	}

	public void setPresentationDay(Date presentationDay) {
		_presentationDay = presentationDay;
	}

	public Date getPaperStartDay() {
		return _paperStartDay;
	}

	public void setPaperStartDay(Date paperStartDay) {
		_paperStartDay = paperStartDay;
	}

	public Date getPaperEndDay() {
		return _paperEndDay;
	}

	public void setPaperEndDay(Date paperEndDay) {
		_paperEndDay = paperEndDay;
	}

	public Date getEvaluationDay() {
		return _evaluationDay;
	}

	public void setEvaluationDay(Date evaluationDay) {
		_evaluationDay = evaluationDay;
	}

	public Date getChallengeStartDay() {
		return _challengeStartDay;
	}

	public void setChallengeStartDay(Date challengeStartDay) {
		_challengeStartDay = challengeStartDay;
	}

	public Date getChallengeEndDay() {
		return _challengeEndDay;
	}

	public void setChallengeEndDay(Date challengeEndDay) {
		_challengeEndDay = challengeEndDay;
	}

	public String getChallengeStatus() {
		return _challengeStatus;
	}

	public void setChallengeStatus(String challengeStatus) {
		_challengeStatus = challengeStatus;
	}

	public long getChallengeId() {
		return _challengeId;
	}

	public void setChallengeId(long challengeId) {
		_challengeId = challengeId;
	}

	private String _uuid;
	private long _childChallengeId;
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
	private int _number;
	private Date _presentationDay;
	private Date _paperStartDay;
	private Date _paperEndDay;
	private Date _evaluationDay;
	private Date _challengeStartDay;
	private Date _challengeEndDay;
	private String _challengeStatus;
	private long _challengeId;
}