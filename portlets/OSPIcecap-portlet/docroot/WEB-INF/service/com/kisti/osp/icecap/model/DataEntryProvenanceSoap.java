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
 * This class is used by SOAP remote services, specifically {@link com.kisti.osp.icecap.service.http.DataEntryProvenanceServiceSoap}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.http.DataEntryProvenanceServiceSoap
 * @generated
 */
public class DataEntryProvenanceSoap implements Serializable {
	public static DataEntryProvenanceSoap toSoapModel(DataEntryProvenance model) {
		DataEntryProvenanceSoap soapModel = new DataEntryProvenanceSoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setInputData(model.getInputData());
		soapModel.setPROVStructure(model.getPROVStructure());

		return soapModel;
	}

	public static DataEntryProvenanceSoap[] toSoapModels(
		DataEntryProvenance[] models) {
		DataEntryProvenanceSoap[] soapModels = new DataEntryProvenanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataEntryProvenanceSoap[][] toSoapModels(
		DataEntryProvenance[][] models) {
		DataEntryProvenanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataEntryProvenanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataEntryProvenanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataEntryProvenanceSoap[] toSoapModels(
		List<DataEntryProvenance> models) {
		List<DataEntryProvenanceSoap> soapModels = new ArrayList<DataEntryProvenanceSoap>(models.size());

		for (DataEntryProvenance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataEntryProvenanceSoap[soapModels.size()]);
	}

	public DataEntryProvenanceSoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public String getInputData() {
		return _inputData;
	}

	public void setInputData(String inputData) {
		_inputData = inputData;
	}

	public String getPROVStructure() {
		return _PROVStructure;
	}

	public void setPROVStructure(String PROVStructure) {
		_PROVStructure = PROVStructure;
	}

	private long _entryId;
	private String _inputData;
	private String _PROVStructure;
}