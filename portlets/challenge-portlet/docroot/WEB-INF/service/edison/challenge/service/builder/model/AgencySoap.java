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

package edison.challenge.service.builder.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.AgencyServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.AgencyServiceSoap
 * @generated
 */
public class AgencySoap implements Serializable {
	public static AgencySoap toSoapModel(Agency model) {
		AgencySoap soapModel = new AgencySoap();

		soapModel.setAgencyid(model.getAgencyid());
		soapModel.setName(model.getName());
		soapModel.setLevel(model.getLevel());
		soapModel.setUrl(model.getUrl());
		soapModel.setChildid(model.getChildid());

		return soapModel;
	}

	public static AgencySoap[] toSoapModels(Agency[] models) {
		AgencySoap[] soapModels = new AgencySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AgencySoap[][] toSoapModels(Agency[][] models) {
		AgencySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AgencySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AgencySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AgencySoap[] toSoapModels(List<Agency> models) {
		List<AgencySoap> soapModels = new ArrayList<AgencySoap>(models.size());

		for (Agency model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AgencySoap[soapModels.size()]);
	}

	public AgencySoap() {
	}

	public long getPrimaryKey() {
		return _agencyid;
	}

	public void setPrimaryKey(long pk) {
		setAgencyid(pk);
	}

	public long getAgencyid() {
		return _agencyid;
	}

	public void setAgencyid(long agencyid) {
		_agencyid = agencyid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getLevel() {
		return _level;
	}

	public void setLevel(String level) {
		_level = level;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getChildid() {
		return _childid;
	}

	public void setChildid(long childid) {
		_childid = childid;
	}

	private long _agencyid;
	private String _name;
	private String _level;
	private String _url;
	private long _childid;
}