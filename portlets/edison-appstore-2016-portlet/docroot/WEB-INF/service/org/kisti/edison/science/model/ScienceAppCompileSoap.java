/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.science.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.science.service.http.ScienceAppCompileServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.http.ScienceAppCompileServiceSoap
 * @generated
 */
public class ScienceAppCompileSoap implements Serializable {
	public static ScienceAppCompileSoap toSoapModel(ScienceAppCompile model) {
		ScienceAppCompileSoap soapModel = new ScienceAppCompileSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCompileUrl(model.getCompileUrl());
		soapModel.setResult(model.getResult());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static ScienceAppCompileSoap[] toSoapModels(
		ScienceAppCompile[] models) {
		ScienceAppCompileSoap[] soapModels = new ScienceAppCompileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppCompileSoap[][] toSoapModels(
		ScienceAppCompile[][] models) {
		ScienceAppCompileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppCompileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppCompileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppCompileSoap[] toSoapModels(
		List<ScienceAppCompile> models) {
		List<ScienceAppCompileSoap> soapModels = new ArrayList<ScienceAppCompileSoap>(models.size());

		for (ScienceAppCompile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppCompileSoap[soapModels.size()]);
	}

	public ScienceAppCompileSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppId;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppId(pk);
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public Long getUserId() {
		return _userId;
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	public String getCompileUrl() {
		return _compileUrl;
	}

	public void setCompileUrl(String compileUrl) {
		_compileUrl = compileUrl;
	}

	public String getResult() {
		return _result;
	}

	public void setResult(String result) {
		_result = result;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _scienceAppId;
	private Long _userId;
	private String _compileUrl;
	private String _result;
	private Date _createDate;
}