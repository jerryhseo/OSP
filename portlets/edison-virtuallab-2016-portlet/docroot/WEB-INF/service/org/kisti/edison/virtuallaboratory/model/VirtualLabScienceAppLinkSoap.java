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
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.virtuallaboratory.service.http.VirtualLabScienceAppLinkServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.http.VirtualLabScienceAppLinkServiceSoap
 * @generated
 */
public class VirtualLabScienceAppLinkSoap implements Serializable {
	public static VirtualLabScienceAppLinkSoap toSoapModel(
		VirtualLabScienceAppLink model) {
		VirtualLabScienceAppLinkSoap soapModel = new VirtualLabScienceAppLinkSoap();

		soapModel.setScienceAppSeqNo(model.getScienceAppSeqNo());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setVirtualLabId(model.getVirtualLabId());

		return soapModel;
	}

	public static VirtualLabScienceAppLinkSoap[] toSoapModels(
		VirtualLabScienceAppLink[] models) {
		VirtualLabScienceAppLinkSoap[] soapModels = new VirtualLabScienceAppLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabScienceAppLinkSoap[][] toSoapModels(
		VirtualLabScienceAppLink[][] models) {
		VirtualLabScienceAppLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VirtualLabScienceAppLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VirtualLabScienceAppLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VirtualLabScienceAppLinkSoap[] toSoapModels(
		List<VirtualLabScienceAppLink> models) {
		List<VirtualLabScienceAppLinkSoap> soapModels = new ArrayList<VirtualLabScienceAppLinkSoap>(models.size());

		for (VirtualLabScienceAppLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VirtualLabScienceAppLinkSoap[soapModels.size()]);
	}

	public VirtualLabScienceAppLinkSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppSeqNo;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppSeqNo(pk);
	}

	public long getScienceAppSeqNo() {
		return _scienceAppSeqNo;
	}

	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_scienceAppSeqNo = scienceAppSeqNo;
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getVirtualLabId() {
		return _virtualLabId;
	}

	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;
	}

	private long _scienceAppSeqNo;
	private long _scienceAppId;
	private long _virtualLabId;
}