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
public class ContentsSoap implements Serializable {
	public static ContentsSoap toSoapModel(Contents model) {
		ContentsSoap soapModel = new ContentsSoap();

		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCnt(model.getCnt());

		return soapModel;
	}

	public static ContentsSoap[] toSoapModels(Contents[] models) {
		ContentsSoap[] soapModels = new ContentsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentsSoap[][] toSoapModels(Contents[][] models) {
		ContentsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentsSoap[] toSoapModels(List<Contents> models) {
		List<ContentsSoap> soapModels = new ArrayList<ContentsSoap>(models.size());

		for (Contents model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentsSoap[soapModels.size()]);
	}

	public ContentsSoap() {
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