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

package org.kisti.edison.virtuallaboratory.model;

import org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.virtuallaboratory.service.http.VirtualLabClassStsMigrationServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.http.VirtualLabClassStsMigrationServiceSoap
 * @generated
 */
public class VirtualLabClassStsMigrationSoap implements Serializable {
	public static VirtualLabClassStsMigrationSoap toSoapModel(
		VirtualLabClassStsMigration model) {
		VirtualLabClassStsMigrationSoap soapModel = new VirtualLabClassStsMigrationSoap();

		soapModel.setGroupId(model.getGroupId());
		soapModel.setVirtualLabId(model.getVirtualLabId());
		soapModel.setClassId(model.getClassId());
		soapModel.setUniversityField(model.getUniversityField());
		soapModel.setVirtualLabTitle(model.getVirtualLabTitle());
		soapModel.setVirtualLabPersonName(model.getVirtualLabPersonName());
		soapModel.setClassTitle(model.getClassTitle());
		soapModel.setVirtualClassCd(model.getVirtualClassCd());
		soapModel.setClassCreateDt(model.getClassCreateDt());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setScienceAppName(model.getScienceAppName());
		soapModel.setRegisterStudentCnt(model.getRegisterStudentCnt());
		soapModel.setExecuteCount(model.getExecuteCount());
		soapModel.setExecuteStudentcount(model.getExecuteStudentcount());
		soapModel.setAvgerageRuntime(model.getAvgerageRuntime());
		soapModel.setCputime(model.getCputime());

		return soapModel;
	}

	public static VirtualLabClassStsMigrationSoap[] toSoapModels(
		VirtualLabClassStsMigration[] models) {
		VirtualLabClassStsMigrationSoap[] soapModels = new VirtualLabClassStsMigrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabClassStsMigrationSoap[][] toSoapModels(
		VirtualLabClassStsMigration[][] models) {
		VirtualLabClassStsMigrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VirtualLabClassStsMigrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VirtualLabClassStsMigrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabClassStsMigrationSoap[] toSoapModels(
		List<VirtualLabClassStsMigration> models) {
		List<VirtualLabClassStsMigrationSoap> soapModels = new ArrayList<VirtualLabClassStsMigrationSoap>(models.size());

		for (VirtualLabClassStsMigration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VirtualLabClassStsMigrationSoap[soapModels.size()]);
	}

	public VirtualLabClassStsMigrationSoap() {
	}

	public VirtualLabClassStsMigrationPK getPrimaryKey() {
		return new VirtualLabClassStsMigrationPK(_groupId, _virtualLabId,
			_classId);
	}

	public void setPrimaryKey(VirtualLabClassStsMigrationPK pk) {
		setGroupId(pk.groupId);
		setVirtualLabId(pk.virtualLabId);
		setClassId(pk.classId);
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getVirtualLabId() {
		return _virtualLabId;
	}

	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;
	}

	public long getClassId() {
		return _classId;
	}

	public void setClassId(long classId) {
		_classId = classId;
	}

	public String getUniversityField() {
		return _universityField;
	}

	public void setUniversityField(String universityField) {
		_universityField = universityField;
	}

	public String getVirtualLabTitle() {
		return _virtualLabTitle;
	}

	public void setVirtualLabTitle(String virtualLabTitle) {
		_virtualLabTitle = virtualLabTitle;
	}

	public String getVirtualLabPersonName() {
		return _virtualLabPersonName;
	}

	public void setVirtualLabPersonName(String virtualLabPersonName) {
		_virtualLabPersonName = virtualLabPersonName;
	}

	public String getClassTitle() {
		return _classTitle;
	}

	public void setClassTitle(String classTitle) {
		_classTitle = classTitle;
	}

	public String getVirtualClassCd() {
		return _virtualClassCd;
	}

	public void setVirtualClassCd(String virtualClassCd) {
		_virtualClassCd = virtualClassCd;
	}

	public Date getClassCreateDt() {
		return _classCreateDt;
	}

	public void setClassCreateDt(Date classCreateDt) {
		_classCreateDt = classCreateDt;
	}

	public String getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(String scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public String getScienceAppName() {
		return _scienceAppName;
	}

	public void setScienceAppName(String scienceAppName) {
		_scienceAppName = scienceAppName;
	}

	public long getRegisterStudentCnt() {
		return _registerStudentCnt;
	}

	public void setRegisterStudentCnt(long registerStudentCnt) {
		_registerStudentCnt = registerStudentCnt;
	}

	public long getExecuteCount() {
		return _executeCount;
	}

	public void setExecuteCount(long executeCount) {
		_executeCount = executeCount;
	}

	public long getExecuteStudentcount() {
		return _executeStudentcount;
	}

	public void setExecuteStudentcount(long executeStudentcount) {
		_executeStudentcount = executeStudentcount;
	}

	public long getAvgerageRuntime() {
		return _avgerageRuntime;
	}

	public void setAvgerageRuntime(long avgerageRuntime) {
		_avgerageRuntime = avgerageRuntime;
	}

	public long getCputime() {
		return _cputime;
	}

	public void setCputime(long cputime) {
		_cputime = cputime;
	}

	private long _groupId;
	private long _virtualLabId;
	private long _classId;
	private String _universityField;
	private String _virtualLabTitle;
	private String _virtualLabPersonName;
	private String _classTitle;
	private String _virtualClassCd;
	private Date _classCreateDt;
	private String _scienceAppId;
	private String _scienceAppName;
	private long _registerStudentCnt;
	private long _executeCount;
	private long _executeStudentcount;
	private long _avgerageRuntime;
	private long _cputime;
}