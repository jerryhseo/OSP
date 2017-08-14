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

package org.kisti.edison.model;

import org.kisti.edison.service.persistence.SimProScienceAppLinkPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.service.http.SimProScienceAppLinkServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.service.http.SimProScienceAppLinkServiceSoap
 * @generated
 */
public class SimProScienceAppLinkSoap implements Serializable {
	public static SimProScienceAppLinkSoap toSoapModel(
		SimProScienceAppLink model) {
		SimProScienceAppLinkSoap soapModel = new SimProScienceAppLinkSoap();

		soapModel.setSimProScienceAppLinkId(model.getSimProScienceAppLinkId());
		soapModel.setSimulationProjectId(model.getSimulationProjectId());
		soapModel.setScienceAppId(model.getScienceAppId());

		return soapModel;
	}

	public static SimProScienceAppLinkSoap[] toSoapModels(
		SimProScienceAppLink[] models) {
		SimProScienceAppLinkSoap[] soapModels = new SimProScienceAppLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SimProScienceAppLinkSoap[][] toSoapModels(
		SimProScienceAppLink[][] models) {
		SimProScienceAppLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SimProScienceAppLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SimProScienceAppLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SimProScienceAppLinkSoap[] toSoapModels(
		List<SimProScienceAppLink> models) {
		List<SimProScienceAppLinkSoap> soapModels = new ArrayList<SimProScienceAppLinkSoap>(models.size());

		for (SimProScienceAppLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SimProScienceAppLinkSoap[soapModels.size()]);
	}

	public SimProScienceAppLinkSoap() {
	}

	public SimProScienceAppLinkPK getPrimaryKey() {
		return new SimProScienceAppLinkPK(_simProScienceAppLinkId,
			_simulationProjectId);
	}

	public void setPrimaryKey(SimProScienceAppLinkPK pk) {
		setSimProScienceAppLinkId(pk.simProScienceAppLinkId);
		setSimulationProjectId(pk.simulationProjectId);
	}

	public long getSimProScienceAppLinkId() {
		return _simProScienceAppLinkId;
	}

	public void setSimProScienceAppLinkId(long simProScienceAppLinkId) {
		_simProScienceAppLinkId = simProScienceAppLinkId;
	}

	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;
	}

	public Long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(Long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	private long _simProScienceAppLinkId;
	private long _simulationProjectId;
	private Long _scienceAppId;
}