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

package org.kisti.edison.model;

import org.kisti.edison.service.persistence.SiteUserPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author edison
 * @generated
 */
public class SiteUserSoap implements Serializable {
	public static SiteUserSoap toSoapModel(SiteUser model) {
		SiteUserSoap soapModel = new SiteUserSoap();

		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCnt(model.getCnt());

		return soapModel;
	}

	public static SiteUserSoap[] toSoapModels(SiteUser[] models) {
		SiteUserSoap[] soapModels = new SiteUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SiteUserSoap[][] toSoapModels(SiteUser[][] models) {
		SiteUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SiteUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SiteUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SiteUserSoap[] toSoapModels(List<SiteUser> models) {
		List<SiteUserSoap> soapModels = new ArrayList<SiteUserSoap>(models.size());

		for (SiteUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SiteUserSoap[soapModels.size()]);
	}

	public SiteUserSoap() {
	}

	public SiteUserPK getPrimaryKey() {
		return new SiteUserPK(_createDate, _groupId);
	}

	public void setPrimaryKey(SiteUserPK pk) {
		setCreateDate(pk.createDate);
		setGroupId(pk.groupId);
	}

	public String getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(String createDate) {
		_createDate = createDate;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Long getCnt() {
		return _cnt;
	}

	public void setCnt(Long cnt) {
		_cnt = cnt;
	}

	private String _createDate;
	private long _groupId;
	private Long _cnt;
}