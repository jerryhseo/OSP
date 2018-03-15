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
 * This class is used by SOAP remote services, specifically {@link kisti.edison.challenge.service.http.ChallengeTeamServiceSoap}.
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.http.ChallengeTeamServiceSoap
 * @generated
 */
public class ChallengeTeamSoap implements Serializable {
	public static ChallengeTeamSoap toSoapModel(ChallengeTeam model) {
		ChallengeTeamSoap soapModel = new ChallengeTeamSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChallengeTeamId(model.getChallengeTeamId());
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
		soapModel.setTeamName(model.getTeamName());
		soapModel.setSubject(model.getSubject());
		soapModel.setPaperName(model.getPaperName());
		soapModel.setPaperAbstract(model.getPaperAbstract());
		soapModel.setPaperFileName(model.getPaperFileName());
		soapModel.setPaperSubmissionDay(model.getPaperSubmissionDay());
		soapModel.setPaperModifyDay(model.getPaperModifyDay());
		soapModel.setPaperStatusDOC(model.getPaperStatusDOC());
		soapModel.setPaperPDFFileName(model.getPaperPDFFileName());
		soapModel.setPaperPDFSubmissionDay(model.getPaperPDFSubmissionDay());
		soapModel.setPaperPDFModifyDay(model.getPaperPDFModifyDay());
		soapModel.setPaperStatusPDF(model.getPaperStatusPDF());
		soapModel.setPresentationName(model.getPresentationName());
		soapModel.setPresentationFileName(model.getPresentationFileName());
		soapModel.setPresentationSubmissionDay(model.getPresentationSubmissionDay());
		soapModel.setPresentationModifyDay(model.getPresentationModifyDay());
		soapModel.setPresentationStatus(model.getPresentationStatus());
		soapModel.setFilepath(model.getFilepath());
		soapModel.setAggrement(model.getAggrement());
		soapModel.setChildChallengeId(model.getChildChallengeId());

		return soapModel;
	}

	public static ChallengeTeamSoap[] toSoapModels(ChallengeTeam[] models) {
		ChallengeTeamSoap[] soapModels = new ChallengeTeamSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeTeamSoap[][] toSoapModels(ChallengeTeam[][] models) {
		ChallengeTeamSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeTeamSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeTeamSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeTeamSoap[] toSoapModels(List<ChallengeTeam> models) {
		List<ChallengeTeamSoap> soapModels = new ArrayList<ChallengeTeamSoap>(models.size());

		for (ChallengeTeam model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeTeamSoap[soapModels.size()]);
	}

	public ChallengeTeamSoap() {
	}

	public long getPrimaryKey() {
		return _challengeTeamId;
	}

	public void setPrimaryKey(long pk) {
		setChallengeTeamId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChallengeTeamId() {
		return _challengeTeamId;
	}

	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamId = challengeTeamId;
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

	public String getTeamName() {
		return _teamName;
	}

	public void setTeamName(String teamName) {
		_teamName = teamName;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getPaperName() {
		return _paperName;
	}

	public void setPaperName(String paperName) {
		_paperName = paperName;
	}

	public String getPaperAbstract() {
		return _paperAbstract;
	}

	public void setPaperAbstract(String paperAbstract) {
		_paperAbstract = paperAbstract;
	}

	public String getPaperFileName() {
		return _paperFileName;
	}

	public void setPaperFileName(String paperFileName) {
		_paperFileName = paperFileName;
	}

	public Date getPaperSubmissionDay() {
		return _paperSubmissionDay;
	}

	public void setPaperSubmissionDay(Date paperSubmissionDay) {
		_paperSubmissionDay = paperSubmissionDay;
	}

	public Date getPaperModifyDay() {
		return _paperModifyDay;
	}

	public void setPaperModifyDay(Date paperModifyDay) {
		_paperModifyDay = paperModifyDay;
	}

	public boolean getPaperStatusDOC() {
		return _paperStatusDOC;
	}

	public boolean isPaperStatusDOC() {
		return _paperStatusDOC;
	}

	public void setPaperStatusDOC(boolean paperStatusDOC) {
		_paperStatusDOC = paperStatusDOC;
	}

	public String getPaperPDFFileName() {
		return _paperPDFFileName;
	}

	public void setPaperPDFFileName(String paperPDFFileName) {
		_paperPDFFileName = paperPDFFileName;
	}

	public Date getPaperPDFSubmissionDay() {
		return _paperPDFSubmissionDay;
	}

	public void setPaperPDFSubmissionDay(Date paperPDFSubmissionDay) {
		_paperPDFSubmissionDay = paperPDFSubmissionDay;
	}

	public Date getPaperPDFModifyDay() {
		return _paperPDFModifyDay;
	}

	public void setPaperPDFModifyDay(Date paperPDFModifyDay) {
		_paperPDFModifyDay = paperPDFModifyDay;
	}

	public boolean getPaperStatusPDF() {
		return _paperStatusPDF;
	}

	public boolean isPaperStatusPDF() {
		return _paperStatusPDF;
	}

	public void setPaperStatusPDF(boolean paperStatusPDF) {
		_paperStatusPDF = paperStatusPDF;
	}

	public String getPresentationName() {
		return _presentationName;
	}

	public void setPresentationName(String presentationName) {
		_presentationName = presentationName;
	}

	public String getPresentationFileName() {
		return _presentationFileName;
	}

	public void setPresentationFileName(String presentationFileName) {
		_presentationFileName = presentationFileName;
	}

	public Date getPresentationSubmissionDay() {
		return _presentationSubmissionDay;
	}

	public void setPresentationSubmissionDay(Date presentationSubmissionDay) {
		_presentationSubmissionDay = presentationSubmissionDay;
	}

	public Date getPresentationModifyDay() {
		return _presentationModifyDay;
	}

	public void setPresentationModifyDay(Date presentationModifyDay) {
		_presentationModifyDay = presentationModifyDay;
	}

	public boolean getPresentationStatus() {
		return _presentationStatus;
	}

	public boolean isPresentationStatus() {
		return _presentationStatus;
	}

	public void setPresentationStatus(boolean presentationStatus) {
		_presentationStatus = presentationStatus;
	}

	public String getFilepath() {
		return _filepath;
	}

	public void setFilepath(String filepath) {
		_filepath = filepath;
	}

	public boolean getAggrement() {
		return _aggrement;
	}

	public boolean isAggrement() {
		return _aggrement;
	}

	public void setAggrement(boolean aggrement) {
		_aggrement = aggrement;
	}

	public long getChildChallengeId() {
		return _childChallengeId;
	}

	public void setChildChallengeId(long childChallengeId) {
		_childChallengeId = childChallengeId;
	}

	private String _uuid;
	private long _challengeTeamId;
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
	private String _teamName;
	private String _subject;
	private String _paperName;
	private String _paperAbstract;
	private String _paperFileName;
	private Date _paperSubmissionDay;
	private Date _paperModifyDay;
	private boolean _paperStatusDOC;
	private String _paperPDFFileName;
	private Date _paperPDFSubmissionDay;
	private Date _paperPDFModifyDay;
	private boolean _paperStatusPDF;
	private String _presentationName;
	private String _presentationFileName;
	private Date _presentationSubmissionDay;
	private Date _presentationModifyDay;
	private boolean _presentationStatus;
	private String _filepath;
	private boolean _aggrement;
	private long _childChallengeId;
}