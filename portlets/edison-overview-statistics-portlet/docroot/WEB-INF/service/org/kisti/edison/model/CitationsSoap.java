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
public class CitationsSoap implements Serializable {
	public static CitationsSoap toSoapModel(Citations model) {
		CitationsSoap soapModel = new CitationsSoap();

		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCnt(model.getCnt());

		return soapModel;
	}

	public static CitationsSoap[] toSoapModels(Citations[] models) {
		CitationsSoap[] soapModels = new CitationsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CitationsSoap[][] toSoapModels(Citations[][] models) {
		CitationsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CitationsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CitationsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CitationsSoap[] toSoapModels(List<Citations> models) {
		List<CitationsSoap> soapModels = new ArrayList<CitationsSoap>(models.size());

		for (Citations model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CitationsSoap[soapModels.size()]);
	}

	public CitationsSoap() {
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