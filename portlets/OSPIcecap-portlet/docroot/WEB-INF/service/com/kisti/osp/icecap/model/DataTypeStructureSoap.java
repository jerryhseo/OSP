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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataTypeStructureServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataTypeStructureServiceSoap
 * @generated
 */
public class DataTypeStructureSoap implements Serializable {
	public static DataTypeStructureSoap toSoapModel(DataTypeStructure model) {
		DataTypeStructureSoap soapModel = new DataTypeStructureSoap();

		soapModel.setTypeId(model.getTypeId());
		soapModel.setStructure(model.getStructure());

		return soapModel;
	}

	public static DataTypeStructureSoap[] toSoapModels(
		DataTypeStructure[] models) {
		DataTypeStructureSoap[] soapModels = new DataTypeStructureSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataTypeStructureSoap[][] toSoapModels(
		DataTypeStructure[][] models) {
		DataTypeStructureSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataTypeStructureSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataTypeStructureSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataTypeStructureSoap[] toSoapModels(
		List<DataTypeStructure> models) {
		List<DataTypeStructureSoap> soapModels = new ArrayList<DataTypeStructureSoap>(models.size());

		for (DataTypeStructure model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataTypeStructureSoap[soapModels.size()]);
	}

	public DataTypeStructureSoap() {
	}

	public long getPrimaryKey() {
		return _typeId;
	}

	public void setPrimaryKey(long pk) {
		setTypeId(pk);
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public String getStructure() {
		return _structure;
	}

	public void setStructure(String structure) {
		_structure = structure;
	}

	private long _typeId;
	private String _structure;
}