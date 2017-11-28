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
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.ChallengeServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.ChallengeServiceSoap
 * @generated
 */
public class ChallengeSoap implements Serializable {
	public static ChallengeSoap toSoapModel(Challenge model) {
		ChallengeSoap soapModel = new ChallengeSoap();

		soapModel.setChallengeid(model.getChallengeid());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ChallengeSoap[] toSoapModels(Challenge[] models) {
		ChallengeSoap[] soapModels = new ChallengeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChallengeSoap[][] toSoapModels(Challenge[][] models) {
		ChallengeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChallengeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChallengeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChallengeSoap[] toSoapModels(List<Challenge> models) {
		List<ChallengeSoap> soapModels = new ArrayList<ChallengeSoap>(models.size());

		for (Challenge model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChallengeSoap[soapModels.size()]);
	}

	public ChallengeSoap() {
	}

	public long getPrimaryKey() {
		return _challengeid;
	}

	public void setPrimaryKey(long pk) {
		setChallengeid(pk);
	}

	public long getChallengeid() {
		return _challengeid;
	}

	public void setChallengeid(long challengeid) {
		_challengeid = challengeid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _challengeid;
	private String _name;
	private String _description;
}