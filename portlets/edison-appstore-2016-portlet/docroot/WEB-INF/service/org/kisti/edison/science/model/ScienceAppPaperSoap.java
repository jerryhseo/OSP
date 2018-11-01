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

package org.kisti.edison.science.model;

import org.kisti.edison.science.service.persistence.ScienceAppPaperPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.science.service.http.ScienceAppPaperServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.http.ScienceAppPaperServiceSoap
 * @generated
 */
public class ScienceAppPaperSoap implements Serializable {
	public static ScienceAppPaperSoap toSoapModel(ScienceAppPaper model) {
		ScienceAppPaperSoap soapModel = new ScienceAppPaperSoap();

		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setPaperSeq(model.getPaperSeq());
		soapModel.setPaperType(model.getPaperType());
		soapModel.setPaperValue(model.getPaperValue());

		return soapModel;
	}

	public static ScienceAppPaperSoap[] toSoapModels(ScienceAppPaper[] models) {
		ScienceAppPaperSoap[] soapModels = new ScienceAppPaperSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppPaperSoap[][] toSoapModels(
		ScienceAppPaper[][] models) {
		ScienceAppPaperSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppPaperSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppPaperSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppPaperSoap[] toSoapModels(
		List<ScienceAppPaper> models) {
		List<ScienceAppPaperSoap> soapModels = new ArrayList<ScienceAppPaperSoap>(models.size());

		for (ScienceAppPaper model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppPaperSoap[soapModels.size()]);
	}

	public ScienceAppPaperSoap() {
	}

	public ScienceAppPaperPK getPrimaryKey() {
		return new ScienceAppPaperPK(_scienceAppId, _paperSeq);
	}

	public void setPrimaryKey(ScienceAppPaperPK pk) {
		setScienceAppId(pk.scienceAppId);
		setPaperSeq(pk.paperSeq);
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getPaperSeq() {
		return _paperSeq;
	}

	public void setPaperSeq(long paperSeq) {
		_paperSeq = paperSeq;
	}

	public String getPaperType() {
		return _paperType;
	}

	public void setPaperType(String paperType) {
		_paperType = paperType;
	}

	public String getPaperValue() {
		return _paperValue;
	}

	public void setPaperValue(String paperValue) {
		_paperValue = paperValue;
	}

	private long _scienceAppId;
	private long _paperSeq;
	private String _paperType;
	private String _paperValue;
}