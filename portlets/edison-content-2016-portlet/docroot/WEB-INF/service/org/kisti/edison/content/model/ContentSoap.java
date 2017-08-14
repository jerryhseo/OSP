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

package org.kisti.edison.content.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.content.service.http.ContentServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.content.service.http.ContentServiceSoap
 * @generated
 */
public class ContentSoap implements Serializable {
	public static ContentSoap toSoapModel(Content model) {
		ContentSoap soapModel = new ContentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setContentSeq(model.getContentSeq());
		soapModel.setContentDiv(model.getContentDiv());
		soapModel.setTitle(model.getTitle());
		soapModel.setResume(model.getResume());
		soapModel.setContentFileNm(model.getContentFileNm());
		soapModel.setAdvancedStartFileNm(model.getAdvancedStartFileNm());
		soapModel.setServiceLanguage(model.getServiceLanguage());
		soapModel.setProjectYn(model.getProjectYn());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setViewCnt(model.getViewCnt());
		soapModel.setInsertId(model.getInsertId());
		soapModel.setInsertDate(model.getInsertDate());
		soapModel.setUpdateId(model.getUpdateId());
		soapModel.setUpdateDate(model.getUpdateDate());
		soapModel.setVersion(model.getVersion());
		soapModel.setOpenYn(model.getOpenYn());

		return soapModel;
	}

	public static ContentSoap[] toSoapModels(Content[] models) {
		ContentSoap[] soapModels = new ContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentSoap[][] toSoapModels(Content[][] models) {
		ContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentSoap[] toSoapModels(List<Content> models) {
		List<ContentSoap> soapModels = new ArrayList<ContentSoap>(models.size());

		for (Content model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentSoap[soapModels.size()]);
	}

	public ContentSoap() {
	}

	public long getPrimaryKey() {
		return _contentSeq;
	}

	public void setPrimaryKey(long pk) {
		setContentSeq(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getContentSeq() {
		return _contentSeq;
	}

	public void setContentSeq(long contentSeq) {
		_contentSeq = contentSeq;
	}

	public long getContentDiv() {
		return _contentDiv;
	}

	public void setContentDiv(long contentDiv) {
		_contentDiv = contentDiv;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getResume() {
		return _resume;
	}

	public void setResume(String resume) {
		_resume = resume;
	}

	public String getContentFileNm() {
		return _contentFileNm;
	}

	public void setContentFileNm(String contentFileNm) {
		_contentFileNm = contentFileNm;
	}

	public String getAdvancedStartFileNm() {
		return _advancedStartFileNm;
	}

	public void setAdvancedStartFileNm(String advancedStartFileNm) {
		_advancedStartFileNm = advancedStartFileNm;
	}

	public String getServiceLanguage() {
		return _serviceLanguage;
	}

	public void setServiceLanguage(String serviceLanguage) {
		_serviceLanguage = serviceLanguage;
	}

	public String getProjectYn() {
		return _projectYn;
	}

	public void setProjectYn(String projectYn) {
		_projectYn = projectYn;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public long getViewCnt() {
		return _viewCnt;
	}

	public void setViewCnt(long viewCnt) {
		_viewCnt = viewCnt;
	}

	public long getInsertId() {
		return _insertId;
	}

	public void setInsertId(long insertId) {
		_insertId = insertId;
	}

	public Date getInsertDate() {
		return _insertDate;
	}

	public void setInsertDate(Date insertDate) {
		_insertDate = insertDate;
	}

	public long getUpdateId() {
		return _updateId;
	}

	public void setUpdateId(long updateId) {
		_updateId = updateId;
	}

	public Date getUpdateDate() {
		return _updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getOpenYn() {
		return _openYn;
	}

	public void setOpenYn(String openYn) {
		_openYn = openYn;
	}

	private String _uuid;
	private long _contentSeq;
	private long _contentDiv;
	private String _title;
	private String _resume;
	private String _contentFileNm;
	private String _advancedStartFileNm;
	private String _serviceLanguage;
	private String _projectYn;
	private long _projectId;
	private long _viewCnt;
	private long _insertId;
	private Date _insertDate;
	private long _updateId;
	private Date _updateDate;
	private String _version;
	private String _openYn;
}