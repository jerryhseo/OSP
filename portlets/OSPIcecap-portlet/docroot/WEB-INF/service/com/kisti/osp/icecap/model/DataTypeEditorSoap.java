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

import com.kisti.osp.icecap.service.persistence.DataTypeEditorPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataTypeEditorServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataTypeEditorServiceSoap
 * @generated
 */
public class DataTypeEditorSoap implements Serializable {
	public static DataTypeEditorSoap toSoapModel(DataTypeEditor model) {
		DataTypeEditorSoap soapModel = new DataTypeEditorSoap();

		soapModel.setTypeId(model.getTypeId());
		soapModel.setEditorId(model.getEditorId());
		soapModel.setIsDefault(model.getIsDefault());

		return soapModel;
	}

	public static DataTypeEditorSoap[] toSoapModels(DataTypeEditor[] models) {
		DataTypeEditorSoap[] soapModels = new DataTypeEditorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataTypeEditorSoap[][] toSoapModels(DataTypeEditor[][] models) {
		DataTypeEditorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataTypeEditorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataTypeEditorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataTypeEditorSoap[] toSoapModels(List<DataTypeEditor> models) {
		List<DataTypeEditorSoap> soapModels = new ArrayList<DataTypeEditorSoap>(models.size());

		for (DataTypeEditor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataTypeEditorSoap[soapModels.size()]);
	}

	public DataTypeEditorSoap() {
	}

	public DataTypeEditorPK getPrimaryKey() {
		return new DataTypeEditorPK(_typeId, _editorId);
	}

	public void setPrimaryKey(DataTypeEditorPK pk) {
		setTypeId(pk.typeId);
		setEditorId(pk.editorId);
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getEditorId() {
		return _editorId;
	}

	public void setEditorId(long editorId) {
		_editorId = editorId;
	}

	public boolean getIsDefault() {
		return _isDefault;
	}

	public boolean isIsDefault() {
		return _isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		_isDefault = isDefault;
	}

	private long _typeId;
	private long _editorId;
	private boolean _isDefault;
}