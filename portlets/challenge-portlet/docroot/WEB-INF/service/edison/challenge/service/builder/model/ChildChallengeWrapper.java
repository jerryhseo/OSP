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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChildChallenge}.
 * </p>
 *
 * @author kyj
 * @see ChildChallenge
 * @generated
 */
public class ChildChallengeWrapper implements ChildChallenge,
	ModelWrapper<ChildChallenge> {
	public ChildChallengeWrapper(ChildChallenge childChallenge) {
		_childChallenge = childChallenge;
	}

	@Override
	public Class<?> getModelClass() {
		return ChildChallenge.class;
	}

	@Override
	public String getModelClassName() {
		return ChildChallenge.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("childid", getChildid());
		attributes.put("number", getNumber());
		attributes.put("presentationDay", getPresentationDay());
		attributes.put("paperStartDay", getPaperStartDay());
		attributes.put("paperEndDay", getPaperEndDay());
		attributes.put("evaluationStartDay", getEvaluationStartDay());
		attributes.put("evaluationEndDay", getEvaluationEndDay());
		attributes.put("challengeStartDay", getChallengeStartDay());
		attributes.put("challengeEndDay", getChallengeEndDay());
		attributes.put("status", getStatus());
		attributes.put("challengeid", getChallengeid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
		}

		Integer number = (Integer)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Date presentationDay = (Date)attributes.get("presentationDay");

		if (presentationDay != null) {
			setPresentationDay(presentationDay);
		}

		Date paperStartDay = (Date)attributes.get("paperStartDay");

		if (paperStartDay != null) {
			setPaperStartDay(paperStartDay);
		}

		Date paperEndDay = (Date)attributes.get("paperEndDay");

		if (paperEndDay != null) {
			setPaperEndDay(paperEndDay);
		}

		Date evaluationStartDay = (Date)attributes.get("evaluationStartDay");

		if (evaluationStartDay != null) {
			setEvaluationStartDay(evaluationStartDay);
		}

		Date evaluationEndDay = (Date)attributes.get("evaluationEndDay");

		if (evaluationEndDay != null) {
			setEvaluationEndDay(evaluationEndDay);
		}

		Date challengeStartDay = (Date)attributes.get("challengeStartDay");

		if (challengeStartDay != null) {
			setChallengeStartDay(challengeStartDay);
		}

		Date challengeEndDay = (Date)attributes.get("challengeEndDay");

		if (challengeEndDay != null) {
			setChallengeEndDay(challengeEndDay);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long challengeid = (Long)attributes.get("challengeid");

		if (challengeid != null) {
			setChallengeid(challengeid);
		}
	}

	/**
	* Returns the primary key of this child challenge.
	*
	* @return the primary key of this child challenge
	*/
	@Override
	public long getPrimaryKey() {
		return _childChallenge.getPrimaryKey();
	}

	/**
	* Sets the primary key of this child challenge.
	*
	* @param primaryKey the primary key of this child challenge
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_childChallenge.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the childid of this child challenge.
	*
	* @return the childid of this child challenge
	*/
	@Override
	public long getChildid() {
		return _childChallenge.getChildid();
	}

	/**
	* Sets the childid of this child challenge.
	*
	* @param childid the childid of this child challenge
	*/
	@Override
	public void setChildid(long childid) {
		_childChallenge.setChildid(childid);
	}

	/**
	* Returns the number of this child challenge.
	*
	* @return the number of this child challenge
	*/
	@Override
	public int getNumber() {
		return _childChallenge.getNumber();
	}

	/**
	* Sets the number of this child challenge.
	*
	* @param number the number of this child challenge
	*/
	@Override
	public void setNumber(int number) {
		_childChallenge.setNumber(number);
	}

	/**
	* Returns the presentation day of this child challenge.
	*
	* @return the presentation day of this child challenge
	*/
	@Override
	public java.util.Date getPresentationDay() {
		return _childChallenge.getPresentationDay();
	}

	/**
	* Sets the presentation day of this child challenge.
	*
	* @param presentationDay the presentation day of this child challenge
	*/
	@Override
	public void setPresentationDay(java.util.Date presentationDay) {
		_childChallenge.setPresentationDay(presentationDay);
	}

	/**
	* Returns the paper start day of this child challenge.
	*
	* @return the paper start day of this child challenge
	*/
	@Override
	public java.util.Date getPaperStartDay() {
		return _childChallenge.getPaperStartDay();
	}

	/**
	* Sets the paper start day of this child challenge.
	*
	* @param paperStartDay the paper start day of this child challenge
	*/
	@Override
	public void setPaperStartDay(java.util.Date paperStartDay) {
		_childChallenge.setPaperStartDay(paperStartDay);
	}

	/**
	* Returns the paper end day of this child challenge.
	*
	* @return the paper end day of this child challenge
	*/
	@Override
	public java.util.Date getPaperEndDay() {
		return _childChallenge.getPaperEndDay();
	}

	/**
	* Sets the paper end day of this child challenge.
	*
	* @param paperEndDay the paper end day of this child challenge
	*/
	@Override
	public void setPaperEndDay(java.util.Date paperEndDay) {
		_childChallenge.setPaperEndDay(paperEndDay);
	}

	/**
	* Returns the evaluation start day of this child challenge.
	*
	* @return the evaluation start day of this child challenge
	*/
	@Override
	public java.util.Date getEvaluationStartDay() {
		return _childChallenge.getEvaluationStartDay();
	}

	/**
	* Sets the evaluation start day of this child challenge.
	*
	* @param evaluationStartDay the evaluation start day of this child challenge
	*/
	@Override
	public void setEvaluationStartDay(java.util.Date evaluationStartDay) {
		_childChallenge.setEvaluationStartDay(evaluationStartDay);
	}

	/**
	* Returns the evaluation end day of this child challenge.
	*
	* @return the evaluation end day of this child challenge
	*/
	@Override
	public java.util.Date getEvaluationEndDay() {
		return _childChallenge.getEvaluationEndDay();
	}

	/**
	* Sets the evaluation end day of this child challenge.
	*
	* @param evaluationEndDay the evaluation end day of this child challenge
	*/
	@Override
	public void setEvaluationEndDay(java.util.Date evaluationEndDay) {
		_childChallenge.setEvaluationEndDay(evaluationEndDay);
	}

	/**
	* Returns the challenge start day of this child challenge.
	*
	* @return the challenge start day of this child challenge
	*/
	@Override
	public java.util.Date getChallengeStartDay() {
		return _childChallenge.getChallengeStartDay();
	}

	/**
	* Sets the challenge start day of this child challenge.
	*
	* @param challengeStartDay the challenge start day of this child challenge
	*/
	@Override
	public void setChallengeStartDay(java.util.Date challengeStartDay) {
		_childChallenge.setChallengeStartDay(challengeStartDay);
	}

	/**
	* Returns the challenge end day of this child challenge.
	*
	* @return the challenge end day of this child challenge
	*/
	@Override
	public java.util.Date getChallengeEndDay() {
		return _childChallenge.getChallengeEndDay();
	}

	/**
	* Sets the challenge end day of this child challenge.
	*
	* @param challengeEndDay the challenge end day of this child challenge
	*/
	@Override
	public void setChallengeEndDay(java.util.Date challengeEndDay) {
		_childChallenge.setChallengeEndDay(challengeEndDay);
	}

	/**
	* Returns the status of this child challenge.
	*
	* @return the status of this child challenge
	*/
	@Override
	public java.lang.String getStatus() {
		return _childChallenge.getStatus();
	}

	/**
	* Sets the status of this child challenge.
	*
	* @param status the status of this child challenge
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_childChallenge.setStatus(status);
	}

	/**
	* Returns the challengeid of this child challenge.
	*
	* @return the challengeid of this child challenge
	*/
	@Override
	public long getChallengeid() {
		return _childChallenge.getChallengeid();
	}

	/**
	* Sets the challengeid of this child challenge.
	*
	* @param challengeid the challengeid of this child challenge
	*/
	@Override
	public void setChallengeid(long challengeid) {
		_childChallenge.setChallengeid(challengeid);
	}

	@Override
	public boolean isNew() {
		return _childChallenge.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_childChallenge.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _childChallenge.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_childChallenge.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _childChallenge.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _childChallenge.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_childChallenge.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _childChallenge.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_childChallenge.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_childChallenge.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_childChallenge.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ChildChallengeWrapper((ChildChallenge)_childChallenge.clone());
	}

	@Override
	public int compareTo(
		edison.challenge.service.builder.model.ChildChallenge childChallenge) {
		return _childChallenge.compareTo(childChallenge);
	}

	@Override
	public int hashCode() {
		return _childChallenge.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.ChildChallenge> toCacheModel() {
		return _childChallenge.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.ChildChallenge toEscapedModel() {
		return new ChildChallengeWrapper(_childChallenge.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.ChildChallenge toUnescapedModel() {
		return new ChildChallengeWrapper(_childChallenge.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _childChallenge.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _childChallenge.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_childChallenge.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChildChallengeWrapper)) {
			return false;
		}

		ChildChallengeWrapper childChallengeWrapper = (ChildChallengeWrapper)obj;

		if (Validator.equals(_childChallenge,
					childChallengeWrapper._childChallenge)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChildChallenge getWrappedChildChallenge() {
		return _childChallenge;
	}

	@Override
	public ChildChallenge getWrappedModel() {
		return _childChallenge;
	}

	@Override
	public void resetOriginalValues() {
		_childChallenge.resetOriginalValues();
	}

	private ChildChallenge _childChallenge;
}