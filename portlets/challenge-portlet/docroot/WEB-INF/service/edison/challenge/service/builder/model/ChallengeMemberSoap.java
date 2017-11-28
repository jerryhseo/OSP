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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.ChallengeMemberServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.ChallengeMemberServiceSoap
 * @generated
 */
public class ChallengeMemberSoap implements Serializable {
	public static ChallengeMemberSoap toSoapModel(ChallengeMember model) {
		ChallengeMemberSoap soapModel = new ChallengeMemberSoap();

		soapModel.setChMemberid(model.getChMemberid());
		soapModel.setFullName(model.getFullName());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setEmail(model.getEmail());
		soapModel.setLeader(model.getLeader());
		soapModel.setFalculty(model.getFalculty());
		soapModel.setMajor(model.getMajor());
		soapModel.setGrade(model.getGrade());
		soapModel.setChTeamid(model.getChTeamid());

		return soapModel;
	}

	public static ChallengeMemberSoap[] toSoapModels(ChallengeMember[] models) {
		ChallengeMemberSoap[] soapModels = new ChallengeMemberSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeMemberSoap[][] toSoapModels(
		ChallengeMember[][] models) {
		ChallengeMemberSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeMemberSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeMemberSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeMemberSoap[] toSoapModels(
		List<ChallengeMember> models) {
		List<ChallengeMemberSoap> soapModels = new ArrayList<ChallengeMemberSoap>(models.size());

		for (ChallengeMember model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeMemberSoap[soapModels.size()]);
	}

	public ChallengeMemberSoap() {
	}

	public long getPrimaryKey() {
		return _chMemberid;
	}

	public void setPrimaryKey(long pk) {
		setChMemberid(pk);
	}

	public long getChMemberid() {
		return _chMemberid;
	}

	public void setChMemberid(long chMemberid) {
		_chMemberid = chMemberid;
	}

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
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

	public String getFalculty() {
		return _falculty;
	}

	public void setFalculty(String falculty) {
		_falculty = falculty;
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

	public long getChTeamid() {
		return _chTeamid;
	}

	public void setChTeamid(long chTeamid) {
		_chTeamid = chTeamid;
	}

	private long _chMemberid;
	private String _fullName;
	private String _screenName;
	private String _email;
	private boolean _leader;
	private String _falculty;
	private String _major;
	private String _grade;
	private long _chTeamid;
}