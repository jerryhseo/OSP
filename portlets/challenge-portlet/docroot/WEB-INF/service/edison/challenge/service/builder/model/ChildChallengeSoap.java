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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edison.challenge.service.builder.service.http.ChildChallengeServiceSoap}.
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.http.ChildChallengeServiceSoap
 * @generated
 */
public class ChildChallengeSoap implements Serializable {
	public static ChildChallengeSoap toSoapModel(ChildChallenge model) {
		ChildChallengeSoap soapModel = new ChildChallengeSoap();

		soapModel.setChildid(model.getChildid());
		soapModel.setNumber(model.getNumber());
		soapModel.setPresentationDay(model.getPresentationDay());
		soapModel.setPaperStartDay(model.getPaperStartDay());
		soapModel.setPaperEndDay(model.getPaperEndDay());
		soapModel.setEvaluationStartDay(model.getEvaluationStartDay());
		soapModel.setEvaluationEndDay(model.getEvaluationEndDay());
		soapModel.setChallengeStartDay(model.getChallengeStartDay());
		soapModel.setChallengeEndDay(model.getChallengeEndDay());
		soapModel.setStatus(model.getStatus());
		soapModel.setChallengeid(model.getChallengeid());

		return soapModel;
	}

	public static ChildChallengeSoap[] toSoapModels(ChildChallenge[] models) {
		ChildChallengeSoap[] soapModels = new ChildChallengeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ChildChallengeSoap[][] toSoapModels(ChildChallenge[][] models) {
		ChildChallengeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ChildChallengeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ChildChallengeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ChildChallengeSoap[] toSoapModels(List<ChildChallenge> models) {
		List<ChildChallengeSoap> soapModels = new ArrayList<ChildChallengeSoap>(models.size());

		for (ChildChallenge model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ChildChallengeSoap[soapModels.size()]);
	}

	public ChildChallengeSoap() {
	}

	public long getPrimaryKey() {
		return _childid;
	}

	public void setPrimaryKey(long pk) {
		setChildid(pk);
	}

	public long getChildid() {
		return _childid;
	}

	public void setChildid(long childid) {
		_childid = childid;
	}

	public int getNumber() {
		return _number;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public Date getPresentationDay() {
		return _presentationDay;
	}

	public void setPresentationDay(Date presentationDay) {
		_presentationDay = presentationDay;
	}

	public Date getPaperStartDay() {
		return _paperStartDay;
	}

	public void setPaperStartDay(Date paperStartDay) {
		_paperStartDay = paperStartDay;
	}

	public Date getPaperEndDay() {
		return _paperEndDay;
	}

	public void setPaperEndDay(Date paperEndDay) {
		_paperEndDay = paperEndDay;
	}

	public Date getEvaluationStartDay() {
		return _evaluationStartDay;
	}

	public void setEvaluationStartDay(Date evaluationStartDay) {
		_evaluationStartDay = evaluationStartDay;
	}

	public Date getEvaluationEndDay() {
		return _evaluationEndDay;
	}

	public void setEvaluationEndDay(Date evaluationEndDay) {
		_evaluationEndDay = evaluationEndDay;
	}

	public Date getChallengeStartDay() {
		return _challengeStartDay;
	}

	public void setChallengeStartDay(Date challengeStartDay) {
		_challengeStartDay = challengeStartDay;
	}

	public Date getChallengeEndDay() {
		return _challengeEndDay;
	}

	public void setChallengeEndDay(Date challengeEndDay) {
		_challengeEndDay = challengeEndDay;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public long getChallengeid() {
		return _challengeid;
	}

	public void setChallengeid(long challengeid) {
		_challengeid = challengeid;
	}

	private long _childid;
	private int _number;
	private Date _presentationDay;
	private Date _paperStartDay;
	private Date _paperEndDay;
	private Date _evaluationStartDay;
	private Date _evaluationEndDay;
	private Date _challengeStartDay;
	private Date _challengeEndDay;
	private String _status;
	private long _challengeid;
}