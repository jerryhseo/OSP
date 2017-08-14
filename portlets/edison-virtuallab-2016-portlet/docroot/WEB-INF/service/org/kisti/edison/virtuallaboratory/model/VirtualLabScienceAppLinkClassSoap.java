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

package org.kisti.edison.virtuallaboratory.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.virtuallaboratory.service.http.VirtualLabScienceAppLinkClassServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.http.VirtualLabScienceAppLinkClassServiceSoap
 * @generated
 */
public class VirtualLabScienceAppLinkClassSoap implements Serializable {
	public static VirtualLabScienceAppLinkClassSoap toSoapModel(
		VirtualLabScienceAppLinkClass model) {
		VirtualLabScienceAppLinkClassSoap soapModel = new VirtualLabScienceAppLinkClassSoap();

		soapModel.setScienceAppClassSeqNo(model.getScienceAppClassSeqNo());
		soapModel.setClassId(model.getClassId());
		soapModel.setScienceAppSeqNo(model.getScienceAppSeqNo());

		return soapModel;
	}

	public static VirtualLabScienceAppLinkClassSoap[] toSoapModels(
		VirtualLabScienceAppLinkClass[] models) {
		VirtualLabScienceAppLinkClassSoap[] soapModels = new VirtualLabScienceAppLinkClassSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabScienceAppLinkClassSoap[][] toSoapModels(
		VirtualLabScienceAppLinkClass[][] models) {
		VirtualLabScienceAppLinkClassSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VirtualLabScienceAppLinkClassSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VirtualLabScienceAppLinkClassSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabScienceAppLinkClassSoap[] toSoapModels(
		List<VirtualLabScienceAppLinkClass> models) {
		List<VirtualLabScienceAppLinkClassSoap> soapModels = new ArrayList<VirtualLabScienceAppLinkClassSoap>(models.size());

		for (VirtualLabScienceAppLinkClass model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VirtualLabScienceAppLinkClassSoap[soapModels.size()]);
	}

	public VirtualLabScienceAppLinkClassSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppClassSeqNo;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppClassSeqNo(pk);
	}

	public long getScienceAppClassSeqNo() {
		return _scienceAppClassSeqNo;
	}

	public void setScienceAppClassSeqNo(long scienceAppClassSeqNo) {
		_scienceAppClassSeqNo = scienceAppClassSeqNo;
	}

	public long getClassId() {
		return _classId;
	}

	public void setClassId(long classId) {
		_classId = classId;
	}

	public long getScienceAppSeqNo() {
		return _scienceAppSeqNo;
	}

	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_scienceAppSeqNo = scienceAppSeqNo;
	}

	private long _scienceAppClassSeqNo;
	private long _classId;
	private long _scienceAppSeqNo;
}