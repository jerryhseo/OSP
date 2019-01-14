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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.service.http.OSPFileServiceSoap}.
 *
 * @author Jerry h. Seo
 * @see com.kisti.osp.service.http.OSPFileServiceSoap
 * @generated
 */
public class OSPFileSoap implements Serializable {
	public static OSPFileSoap toSoapModel(OSPFile model) {
		OSPFileSoap soapModel = new OSPFileSoap();

		soapModel.setPropertyName(model.getPropertyName());
		soapModel.setPropertyValue(model.getPropertyValue());

		return soapModel;
	}

	public static OSPFileSoap[] toSoapModels(OSPFile[] models) {
		OSPFileSoap[] soapModels = new OSPFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OSPFileSoap[][] toSoapModels(OSPFile[][] models) {
		OSPFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OSPFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OSPFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OSPFileSoap[] toSoapModels(List<OSPFile> models) {
		List<OSPFileSoap> soapModels = new ArrayList<OSPFileSoap>(models.size());

		for (OSPFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OSPFileSoap[soapModels.size()]);
	}

	public OSPFileSoap() {
	}

	public String getPrimaryKey() {
		return _propertyName;
	}

	public void setPrimaryKey(String pk) {
		setPropertyName(pk);
	}

	public String getPropertyName() {
		return _propertyName;
	}

	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;
	}

	public String getPropertyValue() {
		return _propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		_propertyValue = propertyValue;
	}

	private String _propertyName;
	private String _propertyValue;
}