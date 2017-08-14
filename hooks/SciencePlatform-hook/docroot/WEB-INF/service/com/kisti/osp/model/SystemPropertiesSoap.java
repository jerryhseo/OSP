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
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.service.http.SystemPropertiesServiceSoap}.
 *
 * @author Jerry h. Seo
 * @see com.kisti.osp.service.http.SystemPropertiesServiceSoap
 * @generated
 */
public class SystemPropertiesSoap implements Serializable {
	public static SystemPropertiesSoap toSoapModel(SystemProperties model) {
		SystemPropertiesSoap soapModel = new SystemPropertiesSoap();

		soapModel.setPropertyName(model.getPropertyName());
		soapModel.setPropertyValue(model.getPropertyValue());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static SystemPropertiesSoap[] toSoapModels(SystemProperties[] models) {
		SystemPropertiesSoap[] soapModels = new SystemPropertiesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SystemPropertiesSoap[][] toSoapModels(
		SystemProperties[][] models) {
		SystemPropertiesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SystemPropertiesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SystemPropertiesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SystemPropertiesSoap[] toSoapModels(
		List<SystemProperties> models) {
		List<SystemPropertiesSoap> soapModels = new ArrayList<SystemPropertiesSoap>(models.size());

		for (SystemProperties model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SystemPropertiesSoap[soapModels.size()]);
	}

	public SystemPropertiesSoap() {
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private String _propertyName;
	private String _propertyValue;
	private boolean _active;
}