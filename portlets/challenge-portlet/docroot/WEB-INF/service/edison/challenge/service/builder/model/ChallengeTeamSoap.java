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

package edison.challenge.service.builder.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.ChallengeTeamServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.ChallengeTeamServiceSoap
 * @generated
 */
public class ChallengeTeamSoap implements Serializable {
	public static ChallengeTeamSoap toSoapModel(ChallengeTeam model) {
		ChallengeTeamSoap soapModel = new ChallengeTeamSoap();

		soapModel.setChTeamid(model.getChTeamid());
		soapModel.setTeamName(model.getTeamName());
		soapModel.setSubject(model.getSubject());
		soapModel.setPaperPDFstatus(model.getPaperPDFstatus());
		soapModel.setPaperstatus(model.getPaperstatus());
		soapModel.setPresentationstatus(model.getPresentationstatus());
		soapModel.setRegisterDay(model.getRegisterDay());
		soapModel.setRegisterid(model.getRegisterid());
		soapModel.setPaperName(model.getPaperName());
		soapModel.setPaperAbstract(model.getPaperAbstract());
		soapModel.setPaperFileName(model.getPaperFileName());
		soapModel.setPaperSubmissionDay(model.getPaperSubmissionDay());
		soapModel.setPaperModifyDay(model.getPaperModifyDay());
		soapModel.setPaperPDFFileName(model.getPaperPDFFileName());
		soapModel.setPaperPDFSubmissionDay(model.getPaperPDFSubmissionDay());
		soapModel.setPaperPDFModifyDay(model.getPaperPDFModifyDay());
		soapModel.setPresentationName(model.getPresentationName());
		soapModel.setPresentationFileName(model.getPresentationFileName());
		soapModel.setPresentationSubmissionDay(model.getPresentationSubmissionDay());
		soapModel.setPresentationModifyDay(model.getPresentationModifyDay());
		soapModel.setFilepath(model.getFilepath());
		soapModel.setChildid(model.getChildid());

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
		return _chTeamid;
	}

	public void setPrimaryKey(long pk) {
		setChTeamid(pk);
	}

	public long getChTeamid() {
		return _chTeamid;
	}

	public void setChTeamid(long chTeamid) {
		_chTeamid = chTeamid;
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

	public boolean getPaperPDFstatus() {
		return _paperPDFstatus;
	}

	public boolean isPaperPDFstatus() {
		return _paperPDFstatus;
	}

	public void setPaperPDFstatus(boolean paperPDFstatus) {
		_paperPDFstatus = paperPDFstatus;
	}

	public boolean getPaperstatus() {
		return _paperstatus;
	}

	public boolean isPaperstatus() {
		return _paperstatus;
	}

	public void setPaperstatus(boolean paperstatus) {
		_paperstatus = paperstatus;
	}

	public boolean getPresentationstatus() {
		return _presentationstatus;
	}

	public boolean isPresentationstatus() {
		return _presentationstatus;
	}

	public void setPresentationstatus(boolean presentationstatus) {
		_presentationstatus = presentationstatus;
	}

	public Date getRegisterDay() {
		return _registerDay;
	}

	public void setRegisterDay(Date registerDay) {
		_registerDay = registerDay;
	}

	public String getRegisterid() {
		return _registerid;
	}

	public void setRegisterid(String registerid) {
		_registerid = registerid;
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

	public String getFilepath() {
		return _filepath;
	}

	public void setFilepath(String filepath) {
		_filepath = filepath;
	}

	public long getChildid() {
		return _childid;
	}

	public void setChildid(long childid) {
		_childid = childid;
	}

	private long _chTeamid;
	private String _teamName;
	private String _subject;
	private boolean _paperPDFstatus;
	private boolean _paperstatus;
	private boolean _presentationstatus;
	private Date _registerDay;
	private String _registerid;
	private String _paperName;
	private String _paperAbstract;
	private String _paperFileName;
	private Date _paperSubmissionDay;
	private Date _paperModifyDay;
	private String _paperPDFFileName;
	private Date _paperPDFSubmissionDay;
	private Date _paperPDFModifyDay;
	private String _presentationName;
	private String _presentationFileName;
	private Date _presentationSubmissionDay;
	private Date _presentationModifyDay;
	private String _filepath;
	private long _childid;
}