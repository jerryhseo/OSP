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

package com.kisti.osp.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.service.http.FileManagementServiceSoap}.
 *
 * @author Jerry h. Seo
 * @see com.kisti.osp.service.http.FileManagementServiceSoap
 * @generated
 */
public class FileManagementSoap implements Serializable {
	public static FileManagementSoap toSoapModel(FileManagement model) {
		FileManagementSoap soapModel = new FileManagementSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setAssigned(model.getAssigned());
		soapModel.setUsed(model.getUsed());
		soapModel.setLastModified(model.getLastModified());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static FileManagementSoap[] toSoapModels(FileManagement[] models) {
		FileManagementSoap[] soapModels = new FileManagementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FileManagementSoap[][] toSoapModels(FileManagement[][] models) {
		FileManagementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FileManagementSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FileManagementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FileManagementSoap[] toSoapModels(List<FileManagement> models) {
		List<FileManagementSoap> soapModels = new ArrayList<FileManagementSoap>(models.size());

		for (FileManagement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FileManagementSoap[soapModels.size()]);
	}

	public FileManagementSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public int getAssigned() {
		return _assigned;
	}

	public void setAssigned(int assigned) {
		_assigned = assigned;
	}

	public int getUsed() {
		return _used;
	}

	public void setUsed(int used) {
		_used = used;
	}

	public Date getLastModified() {
		return _lastModified;
	}

	public void setLastModified(Date lastModified) {
		_lastModified = lastModified;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _userId;
	private int _assigned;
	private int _used;
	private Date _lastModified;
	private int _status;
}