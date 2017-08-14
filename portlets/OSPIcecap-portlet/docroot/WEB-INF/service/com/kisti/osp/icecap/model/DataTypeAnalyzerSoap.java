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

import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataTypeAnalyzerServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataTypeAnalyzerServiceSoap
 * @generated
 */
public class DataTypeAnalyzerSoap implements Serializable {
	public static DataTypeAnalyzerSoap toSoapModel(DataTypeAnalyzer model) {
		DataTypeAnalyzerSoap soapModel = new DataTypeAnalyzerSoap();

		soapModel.setTypeId(model.getTypeId());
		soapModel.setAnalyzerId(model.getAnalyzerId());
		soapModel.setIsDefault(model.getIsDefault());

		return soapModel;
	}

	public static DataTypeAnalyzerSoap[] toSoapModels(DataTypeAnalyzer[] models) {
		DataTypeAnalyzerSoap[] soapModels = new DataTypeAnalyzerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataTypeAnalyzerSoap[][] toSoapModels(
		DataTypeAnalyzer[][] models) {
		DataTypeAnalyzerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataTypeAnalyzerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataTypeAnalyzerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataTypeAnalyzerSoap[] toSoapModels(
		List<DataTypeAnalyzer> models) {
		List<DataTypeAnalyzerSoap> soapModels = new ArrayList<DataTypeAnalyzerSoap>(models.size());

		for (DataTypeAnalyzer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataTypeAnalyzerSoap[soapModels.size()]);
	}

	public DataTypeAnalyzerSoap() {
	}

	public DataTypeAnalyzerPK getPrimaryKey() {
		return new DataTypeAnalyzerPK(_typeId, _analyzerId);
	}

	public void setPrimaryKey(DataTypeAnalyzerPK pk) {
		setTypeId(pk.typeId);
		setAnalyzerId(pk.analyzerId);
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getAnalyzerId() {
		return _analyzerId;
	}

	public void setAnalyzerId(long analyzerId) {
		_analyzerId = analyzerId;
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
	private long _analyzerId;
	private boolean _isDefault;
}