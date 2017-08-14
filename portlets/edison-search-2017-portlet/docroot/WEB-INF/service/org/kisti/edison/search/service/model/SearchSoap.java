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

package org.kisti.edison.search.service.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author yjpark
 * @generated
 */
public class SearchSoap implements Serializable {
	public static SearchSoap toSoapModel(Search model) {
		SearchSoap soapModel = new SearchSoap();

		soapModel.setId(model.getId());

		return soapModel;
	}

	public static SearchSoap[] toSoapModels(Search[] models) {
		SearchSoap[] soapModels = new SearchSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SearchSoap[][] toSoapModels(Search[][] models) {
		SearchSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SearchSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SearchSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SearchSoap[] toSoapModels(List<Search> models) {
		List<SearchSoap> soapModels = new ArrayList<SearchSoap>(models.size());

		for (Search model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SearchSoap[soapModels.size()]);
	}

	public SearchSoap() {
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

	private long _id;
}