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
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChallengeTeamMemberServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChallengeTeamMemberServiceSoap
 * @generated
 */
public class ChallengeTeamMemberSoap implements Serializable {
	public static ChallengeTeamMemberSoap toSoapModel(ChallengeTeamMember model) {
		ChallengeTeamMemberSoap soapModel = new ChallengeTeamMemberSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChallengeTeamMemberId(model.getChallengeTeamMemberId());
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
		soapModel.setApplyUserId(model.getApplyUserId());
		soapModel.setApplyUserName(model.getApplyUserName());
		soapModel.setEmail(model.getEmail());
		soapModel.setInstitute(model.getInstitute());
		soapModel.setMajor(model.getMajor());
		soapModel.setGrade(model.getGrade());
		soapModel.setPhone(model.getPhone());
		soapModel.setLeader(model.getLeader());
		soapModel.setChallengeTeamId(model.getChallengeTeamId());

		return soapModel;
	}

	public static ChallengeTeamMemberSoap[] toSoapModels(
		ChallengeTeamMember[] models) {
		ChallengeTeamMemberSoap[] soapModels = new ChallengeTeamMemberSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeTeamMemberSoap[][] toSoapModels(
		ChallengeTeamMember[][] models) {
		ChallengeTeamMemberSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeTeamMemberSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeTeamMemberSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeTeamMemberSoap[] toSoapModels(
		List<ChallengeTeamMember> models) {
		List<ChallengeTeamMemberSoap> soapModels = new ArrayList<ChallengeTeamMemberSoap>(models.size());

		for (ChallengeTeamMember model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeTeamMemberSoap[soapModels.size()]);
	}

	public ChallengeTeamMemberSoap() {
	}

	public long getPrimaryKey() {
		return _challengeTeamMemberId;
	}

	public void setPrimaryKey(long pk) {
		setChallengeTeamMemberId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChallengeTeamMemberId() {
		return _challengeTeamMemberId;
	}

	public void setChallengeTeamMemberId(long challengeTeamMemberId) {
		_challengeTeamMemberId = challengeTeamMemberId;
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

	public long getApplyUserId() {
		return _applyUserId;
	}

	public void setApplyUserId(long applyUserId) {
		_applyUserId = applyUserId;
	}

	public String getApplyUserName() {
		return _applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		_applyUserName = applyUserName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getInstitute() {
		return _institute;
	}

	public void setInstitute(String institute) {
		_institute = institute;
	}

	public String getMajor() {
		return _major;
	}

	public void setMajor(String major) {
		_major = major;
	}

	public String getGrade() {
		return _grade;
	}

	public void setGrade(String grade) {
		_grade = grade;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public boolean getLeader() {
		return _leader;
	}

	public boolean isLeader() {
		return _leader;
	}

	public void setLeader(boolean leader) {
		_leader = leader;
	}

	public long getChallengeTeamId() {
		return _challengeTeamId;
	}

	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamId = challengeTeamId;
	}

	private String _uuid;
	private long _challengeTeamMemberId;
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
	private long _applyUserId;
	private String _applyUserName;
	private String _email;
	private String _institute;
	private String _major;
	private String _grade;
	private String _phone;
	private boolean _leader;
	private long _challengeTeamId;
}