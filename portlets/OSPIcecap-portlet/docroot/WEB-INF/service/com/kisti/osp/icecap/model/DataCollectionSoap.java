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

package com.kisti.osp.icecap.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataCollectionServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataCollectionServiceSoap
 * @generated
 */
public class DataCollectionSoap implements Serializable {
	public static DataCollectionSoap toSoapModel(DataCollection model) {
		DataCollectionSoap soapModel = new DataCollectionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setTitle(model.getTitle());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDescription(model.getDescription());
		soapModel.setTargetLanguage(model.getTargetLanguage());
		soapModel.setStatus(model.getStatus());
		soapModel.setAccessMethod(model.getAccessMethod());
		soapModel.setCollectionId(model.getCollectionId());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setFolderId(model.getFolderId());

		return soapModel;
	}

	public static DataCollectionSoap[] toSoapModels(DataCollection[] models) {
		DataCollectionSoap[] soapModels = new DataCollectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataCollectionSoap[][] toSoapModels(DataCollection[][] models) {
		DataCollectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataCollectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataCollectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataCollectionSoap[] toSoapModels(List<DataCollection> models) {
		List<DataCollectionSoap> soapModels = new ArrayList<DataCollectionSoap>(models.size());

		for (DataCollection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataCollectionSoap[soapModels.size()]);
	}

	public DataCollectionSoap() {
	}

	public long getPrimaryKey() {
		return _collectionId;
	}

	public void setPrimaryKey(long pk) {
		setCollectionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getTargetLanguage() {
		return _targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		_targetLanguage = targetLanguage;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getAccessMethod() {
		return _accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		_accessMethod = accessMethod;
	}

	public long getCollectionId() {
		return _collectionId;
	}

	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	private String _uuid;
	private String _name;
	private String _version;
	private String _title;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _description;
	private String _targetLanguage;
	private int _status;
	private String _accessMethod;
	private long _collectionId;
	private long _typeId;
	private long _folderId;
}