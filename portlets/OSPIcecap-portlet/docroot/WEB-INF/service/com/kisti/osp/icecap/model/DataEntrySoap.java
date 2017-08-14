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
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataEntryServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataEntryServiceSoap
 * @generated
 */
public class DataEntrySoap implements Serializable {
	public static DataEntrySoap toSoapModel(DataEntry model) {
		DataEntrySoap soapModel = new DataEntrySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setCollectionId(model.getCollectionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setComments(model.getComments());
		soapModel.setPath(model.getPath());
		soapModel.setSimulationSubjectId(model.getSimulationSubjectId());
		soapModel.setProductionTime(model.getProductionTime());
		soapModel.setViewCount(model.getViewCount());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setLastAccessedDate(model.getLastAccessedDate());
		soapModel.setFileEntryId(model.getFileEntryId());

		return soapModel;
	}

	public static DataEntrySoap[] toSoapModels(DataEntry[] models) {
		DataEntrySoap[] soapModels = new DataEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataEntrySoap[][] toSoapModels(DataEntry[][] models) {
		DataEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataEntrySoap[] toSoapModels(List<DataEntry> models) {
		List<DataEntrySoap> soapModels = new ArrayList<DataEntrySoap>(models.size());

		for (DataEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataEntrySoap[soapModels.size()]);
	}

	public DataEntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getCollectionId() {
		return _collectionId;
	}

	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;
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

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public long getSimulationSubjectId() {
		return _simulationSubjectId;
	}

	public void setSimulationSubjectId(long simulationSubjectId) {
		_simulationSubjectId = simulationSubjectId;
	}

	public long getProductionTime() {
		return _productionTime;
	}

	public void setProductionTime(long productionTime) {
		_productionTime = productionTime;
	}

	public long getViewCount() {
		return _viewCount;
	}

	public void setViewCount(long viewCount) {
		_viewCount = viewCount;
	}

	public long getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;
	}

	public Date getLastAccessedDate() {
		return _lastAccessedDate;
	}

	public void setLastAccessedDate(Date lastAccessedDate) {
		_lastAccessedDate = lastAccessedDate;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	private long _entryId;
	private long _collectionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _comments;
	private String _path;
	private long _simulationSubjectId;
	private long _productionTime;
	private long _viewCount;
	private long _downloadCount;
	private Date _lastAccessedDate;
	private long _fileEntryId;
}