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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author edison
 * @generated
 */
public class ScienceAppSoap implements Serializable {
	public static ScienceAppSoap toSoapModel(ScienceApp model) {
		ScienceAppSoap soapModel = new ScienceAppSoap();

		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCnt(model.getCnt());

		return soapModel;
	}

	public static ScienceAppSoap[] toSoapModels(ScienceApp[] models) {
		ScienceAppSoap[] soapModels = new ScienceAppSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppSoap[][] toSoapModels(ScienceApp[][] models) {
		ScienceAppSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppSoap[] toSoapModels(List<ScienceApp> models) {
		List<ScienceAppSoap> soapModels = new ArrayList<ScienceAppSoap>(models.size());

		for (ScienceApp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppSoap[soapModels.size()]);
	}

	public ScienceAppSoap() {
	}

	public String getPrimaryKey() {
		return _createDate;
	}

	public void setPrimaryKey(String pk) {
		setCreateDate(pk);
	}

	public String getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(String createDate) {
		_createDate = createDate;
	}

	public Long getCnt() {
		return _cnt;
	}

	public void setCnt(Long cnt) {
		_cnt = cnt;
	}

	private String _createDate;
	private Long _cnt;
}