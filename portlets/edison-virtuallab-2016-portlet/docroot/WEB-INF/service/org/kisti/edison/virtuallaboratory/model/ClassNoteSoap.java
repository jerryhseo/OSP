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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.virtuallaboratory.service.http.ClassNoteServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.http.ClassNoteServiceSoap
 * @generated
 */
public class ClassNoteSoap implements Serializable {
	public static ClassNoteSoap toSoapModel(ClassNote model) {
		ClassNoteSoap soapModel = new ClassNoteSoap();

		soapModel.setClassNoteSeq(model.getClassNoteSeq());
		soapModel.setClassId(model.getClassId());
		soapModel.setContentSeq(model.getContentSeq());
		soapModel.setIsContent(model.getIsContent());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setDescription(model.getDescription());
		soapModel.setInsertDate(model.getInsertDate());
		soapModel.setUpdateDate(model.getUpdateDate());
		soapModel.setInsertId(model.getInsertId());
		soapModel.setUpdateId(model.getUpdateId());

		return soapModel;
	}

	public static ClassNoteSoap[] toSoapModels(ClassNote[] models) {
		ClassNoteSoap[] soapModels = new ClassNoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClassNoteSoap[][] toSoapModels(ClassNote[][] models) {
		ClassNoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ClassNoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClassNoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClassNoteSoap[] toSoapModels(List<ClassNote> models) {
		List<ClassNoteSoap> soapModels = new ArrayList<ClassNoteSoap>(models.size());

		for (ClassNote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClassNoteSoap[soapModels.size()]);
	}

	public ClassNoteSoap() {
	}

	public long getPrimaryKey() {
		return _classNoteSeq;
	}

	public void setPrimaryKey(long pk) {
		setClassNoteSeq(pk);
	}

	public long getClassNoteSeq() {
		return _classNoteSeq;
	}

	public void setClassNoteSeq(long classNoteSeq) {
		_classNoteSeq = classNoteSeq;
	}

	public long getClassId() {
		return _classId;
	}

	public void setClassId(long classId) {
		_classId = classId;
	}

	public long getContentSeq() {
		return _contentSeq;
	}

	public void setContentSeq(long contentSeq) {
		_contentSeq = contentSeq;
	}

	public String getIsContent() {
		return _isContent;
	}

	public void setIsContent(String isContent) {
		_isContent = isContent;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getInsertDate() {
		return _insertDate;
	}

	public void setInsertDate(Date insertDate) {
		_insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return _updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;
	}

	public long getInsertId() {
		return _insertId;
	}

	public void setInsertId(long insertId) {
		_insertId = insertId;
	}

	public long getUpdateId() {
		return _updateId;
	}

	public void setUpdateId(long updateId) {
		_updateId = updateId;
	}

	private long _classNoteSeq;
	private long _classId;
	private long _contentSeq;
	private String _isContent;
	private long _fileEntryId;
	private String _description;
	private Date _insertDate;
	private Date _updateDate;
	private long _insertId;
	private long _updateId;
}