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
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.AwardhistoryServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.AwardhistoryServiceSoap
 * @generated
 */
public class AwardhistorySoap implements Serializable {
	public static AwardhistorySoap toSoapModel(Awardhistory model) {
		AwardhistorySoap soapModel = new AwardhistorySoap();

		soapModel.setAwardhistoryid(model.getAwardhistoryid());
		soapModel.setName(model.getName());
		soapModel.setPrize(model.getPrize());
		soapModel.setChTeamid(model.getChTeamid());

		return soapModel;
	}

	public static AwardhistorySoap[] toSoapModels(Awardhistory[] models) {
		AwardhistorySoap[] soapModels = new AwardhistorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AwardhistorySoap[][] toSoapModels(Awardhistory[][] models) {
		AwardhistorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AwardhistorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AwardhistorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AwardhistorySoap[] toSoapModels(List<Awardhistory> models) {
		List<AwardhistorySoap> soapModels = new ArrayList<AwardhistorySoap>(models.size());

		for (Awardhistory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AwardhistorySoap[soapModels.size()]);
	}

	public AwardhistorySoap() {
	}

	public long getPrimaryKey() {
		return _awardhistoryid;
	}

	public void setPrimaryKey(long pk) {
		setAwardhistoryid(pk);
	}

	public long getAwardhistoryid() {
		return _awardhistoryid;
	}

	public void setAwardhistoryid(long awardhistoryid) {
		_awardhistoryid = awardhistoryid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPrize() {
		return _prize;
	}

	public void setPrize(String prize) {
		_prize = prize;
	}

	public long getChTeamid() {
		return _chTeamid;
	}

	public void setChTeamid(long chTeamid) {
		_chTeamid = chTeamid;
	}

	private long _awardhistoryid;
	private String _name;
	private String _prize;
	private long _chTeamid;
}