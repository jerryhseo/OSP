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

package org.kisti.edison.bestsimulation.model;

import org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.bestsimulation.service.http.VirtualLabClassStatisticsServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.http.VirtualLabClassStatisticsServiceSoap
 * @generated
 */
public class VirtualLabClassStatisticsSoap implements Serializable {
	public static VirtualLabClassStatisticsSoap toSoapModel(
		VirtualLabClassStatistics model) {
		VirtualLabClassStatisticsSoap soapModel = new VirtualLabClassStatisticsSoap();

		soapModel.setVirtualLabId(model.getVirtualLabId());
		soapModel.setClassId(model.getClassId());
		soapModel.setVirtualLabTitle(model.getVirtualLabTitle());
		soapModel.setClassTitle(model.getClassTitle());
		soapModel.setVirtualLabPersonName(model.getVirtualLabPersonName());
		soapModel.setRegisterStudentCnt(model.getRegisterStudentCnt());
		soapModel.setVirtualLabUsersId(model.getVirtualLabUsersId());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setExecuteUserCnt(model.getExecuteUserCnt());
		soapModel.setExecuteCnt(model.getExecuteCnt());
		soapModel.setCputime(model.getCputime());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUniversity(model.getUniversity());
		soapModel.setClassCreateDt(model.getClassCreateDt());
		soapModel.setVirtualLabUseYn(model.getVirtualLabUseYn());
		soapModel.setClassUseYn(model.getClassUseYn());
		soapModel.setLastModifiedDt(model.getLastModifiedDt());

		return soapModel;
	}

	public static VirtualLabClassStatisticsSoap[] toSoapModels(
		VirtualLabClassStatistics[] models) {
		VirtualLabClassStatisticsSoap[] soapModels = new VirtualLabClassStatisticsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabClassStatisticsSoap[][] toSoapModels(
		VirtualLabClassStatistics[][] models) {
		VirtualLabClassStatisticsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VirtualLabClassStatisticsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VirtualLabClassStatisticsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabClassStatisticsSoap[] toSoapModels(
		List<VirtualLabClassStatistics> models) {
		List<VirtualLabClassStatisticsSoap> soapModels = new ArrayList<VirtualLabClassStatisticsSoap>(models.size());

		for (VirtualLabClassStatistics model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VirtualLabClassStatisticsSoap[soapModels.size()]);
	}

	public VirtualLabClassStatisticsSoap() {
	}

	public VirtualLabClassStatisticsPK getPrimaryKey() {
		return new VirtualLabClassStatisticsPK(_virtualLabId, _classId);
	}

	public void setPrimaryKey(VirtualLabClassStatisticsPK pk) {
		setVirtualLabId(pk.virtualLabId);
		setClassId(pk.classId);
	}

	public long getVirtualLabId() {
		return _virtualLabId;
	}

	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;
	}

	public String getClassId() {
		return _classId;
	}

	public void setClassId(String classId) {
		_classId = classId;
	}

	public String getVirtualLabTitle() {
		return _virtualLabTitle;
	}

	public void setVirtualLabTitle(String virtualLabTitle) {
		_virtualLabTitle = virtualLabTitle;
	}

	public String getClassTitle() {
		return _classTitle;
	}

	public void setClassTitle(String classTitle) {
		_classTitle = classTitle;
	}

	public String getVirtualLabPersonName() {
		return _virtualLabPersonName;
	}

	public void setVirtualLabPersonName(String virtualLabPersonName) {
		_virtualLabPersonName = virtualLabPersonName;
	}

	public long getRegisterStudentCnt() {
		return _registerStudentCnt;
	}

	public void setRegisterStudentCnt(long registerStudentCnt) {
		_registerStudentCnt = registerStudentCnt;
	}

	public String getVirtualLabUsersId() {
		return _virtualLabUsersId;
	}

	public void setVirtualLabUsersId(String virtualLabUsersId) {
		_virtualLabUsersId = virtualLabUsersId;
	}

	public String getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(String scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getExecuteUserCnt() {
		return _executeUserCnt;
	}

	public void setExecuteUserCnt(long executeUserCnt) {
		_executeUserCnt = executeUserCnt;
	}

	public long getExecuteCnt() {
		return _executeCnt;
	}

	public void setExecuteCnt(long executeCnt) {
		_executeCnt = executeCnt;
	}

	public String getCputime() {
		return _cputime;
	}

	public void setCputime(String cputime) {
		_cputime = cputime;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUniversity() {
		return _university;
	}

	public void setUniversity(long university) {
		_university = university;
	}

	public Date getClassCreateDt() {
		return _classCreateDt;
	}

	public void setClassCreateDt(Date classCreateDt) {
		_classCreateDt = classCreateDt;
	}

	public String getVirtualLabUseYn() {
		return _virtualLabUseYn;
	}

	public void setVirtualLabUseYn(String virtualLabUseYn) {
		_virtualLabUseYn = virtualLabUseYn;
	}

	public String getClassUseYn() {
		return _classUseYn;
	}

	public void setClassUseYn(String classUseYn) {
		_classUseYn = classUseYn;
	}

	public Date getLastModifiedDt() {
		return _lastModifiedDt;
	}

	public void setLastModifiedDt(Date lastModifiedDt) {
		_lastModifiedDt = lastModifiedDt;
	}

	private long _virtualLabId;
	private String _classId;
	private String _virtualLabTitle;
	private String _classTitle;
	private String _virtualLabPersonName;
	private long _registerStudentCnt;
	private String _virtualLabUsersId;
	private String _scienceAppId;
	private long _executeUserCnt;
	private long _executeCnt;
	private String _cputime;
	private long _groupId;
	private long _university;
	private Date _classCreateDt;
	private String _virtualLabUseYn;
	private String _classUseYn;
	private Date _lastModifiedDt;
}