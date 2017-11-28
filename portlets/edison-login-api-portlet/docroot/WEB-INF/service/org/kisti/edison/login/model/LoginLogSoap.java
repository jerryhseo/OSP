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

package org.kisti.edison.login.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.login.service.http.LoginLogServiceSoap}.
 *
 * @author yjpark
 * @see org.kisti.edison.login.service.http.LoginLogServiceSoap
 * @generated
 */
public class LoginLogSoap implements Serializable {
	public static LoginLogSoap toSoapModel(LoginLog model) {
		LoginLogSoap soapModel = new LoginLogSoap();

		soapModel.setId(model.getId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static LoginLogSoap[] toSoapModels(LoginLog[] models) {
		LoginLogSoap[] soapModels = new LoginLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoginLogSoap[][] toSoapModels(LoginLog[][] models) {
		LoginLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoginLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoginLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoginLogSoap[] toSoapModels(List<LoginLog> models) {
		List<LoginLogSoap> soapModels = new ArrayList<LoginLogSoap>(models.size());

		for (LoginLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoginLogSoap[soapModels.size()]);
	}

	public LoginLogSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _id;
	private Date _createDate;
	private long _userId;
}