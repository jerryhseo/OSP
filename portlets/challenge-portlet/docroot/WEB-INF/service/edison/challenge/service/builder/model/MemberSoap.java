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
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.MemberServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.MemberServiceSoap
 * @generated
 */
public class MemberSoap implements Serializable {
	public static MemberSoap toSoapModel(Member model) {
		MemberSoap soapModel = new MemberSoap();

		soapModel.setMemberid(model.getMemberid());
		soapModel.setName(model.getName());
		soapModel.setLeader(model.getLeader());
		soapModel.setFalculty(model.getFalculty());
		soapModel.setMajor(model.getMajor());
		soapModel.setGrade(model.getGrade());
		soapModel.setTeamid(model.getTeamid());

		return soapModel;
	}

	public static MemberSoap[] toSoapModels(Member[] models) {
		MemberSoap[] soapModels = new MemberSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MemberSoap[][] toSoapModels(Member[][] models) {
		MemberSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MemberSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MemberSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MemberSoap[] toSoapModels(List<Member> models) {
		List<MemberSoap> soapModels = new ArrayList<MemberSoap>(models.size());

		for (Member model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MemberSoap[soapModels.size()]);
	}

	public MemberSoap() {
	}

	public String getPrimaryKey() {
		return _memberid;
	}

	public void setPrimaryKey(String pk) {
		setMemberid(pk);
	}

	public String getMemberid() {
		return _memberid;
	}

	public void setMemberid(String memberid) {
		_memberid = memberid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
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

	public long getTeamid() {
		return _teamid;
	}

	public void setTeamid(long teamid) {
		_teamid = teamid;
	}

	private String _memberid;
	private String _name;
	private boolean _leader;
	private String _falculty;
	private String _major;
	private String _grade;
	private long _teamid;
}