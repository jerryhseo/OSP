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
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.AwardServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.AwardServiceSoap
 * @generated
 */
public class AwardSoap implements Serializable {
	public static AwardSoap toSoapModel(Award model) {
		AwardSoap soapModel = new AwardSoap();

		soapModel.setAwardid(model.getAwardid());
		soapModel.setAwardGradeName(model.getAwardGradeName());
		soapModel.setAwardName(model.getAwardName());
		soapModel.setPrize(model.getPrize());
		soapModel.setNumber(model.getNumber());
		soapModel.setChildid(model.getChildid());

		return soapModel;
	}

	public static AwardSoap[] toSoapModels(Award[] models) {
		AwardSoap[] soapModels = new AwardSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AwardSoap[][] toSoapModels(Award[][] models) {
		AwardSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AwardSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AwardSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AwardSoap[] toSoapModels(List<Award> models) {
		List<AwardSoap> soapModels = new ArrayList<AwardSoap>(models.size());

		for (Award model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AwardSoap[soapModels.size()]);
	}

	public AwardSoap() {
	}

	public long getPrimaryKey() {
		return _awardid;
	}

	public void setPrimaryKey(long pk) {
		setAwardid(pk);
	}

	public long getAwardid() {
		return _awardid;
	}

	public void setAwardid(long awardid) {
		_awardid = awardid;
	}

	public String getAwardGradeName() {
		return _awardGradeName;
	}

	public void setAwardGradeName(String awardGradeName) {
		_awardGradeName = awardGradeName;
	}

	public String getAwardName() {
		return _awardName;
	}

	public void setAwardName(String awardName) {
		_awardName = awardName;
	}

	public String getPrize() {
		return _prize;
	}

	public void setPrize(String prize) {
		_prize = prize;
	}

	public String getNumber() {
		return _number;
	}

	public void setNumber(String number) {
		_number = number;
	}

	public long getChildid() {
		return _childid;
	}

	public void setChildid(long childid) {
		_childid = childid;
	}

	private long _awardid;
	private String _awardGradeName;
	private String _awardName;
	private String _prize;
	private String _number;
	private long _childid;
}