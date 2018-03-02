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

package kisti.edison.challenge.model;

import com.liferay.portal.kernel.lar.StagedModelType;
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
 * @author KYJ
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

		attributes.put("uuid", getUuid());
		attributes.put("childChallengeId", getChildChallengeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("number", getNumber());
		attributes.put("presentationDay", getPresentationDay());
		attributes.put("paperStartDay", getPaperStartDay());
		attributes.put("paperEndDay", getPaperEndDay());
		attributes.put("evaluationDay", getEvaluationDay());
		attributes.put("challengeStartDay", getChallengeStartDay());
		attributes.put("challengeEndDay", getChallengeEndDay());
		attributes.put("challengeStatus", getChallengeStatus());
		attributes.put("challengeId", getChallengeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long childChallengeId = (Long)attributes.get("childChallengeId");

		if (childChallengeId != null) {
			setChildChallengeId(childChallengeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
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

		Date evaluationDay = (Date)attributes.get("evaluationDay");

		if (evaluationDay != null) {
			setEvaluationDay(evaluationDay);
		}

		Date challengeStartDay = (Date)attributes.get("challengeStartDay");

		if (challengeStartDay != null) {
			setChallengeStartDay(challengeStartDay);
		}

		Date challengeEndDay = (Date)attributes.get("challengeEndDay");

		if (challengeEndDay != null) {
			setChallengeEndDay(challengeEndDay);
		}

		String challengeStatus = (String)attributes.get("challengeStatus");

		if (challengeStatus != null) {
			setChallengeStatus(challengeStatus);
		}

		Long challengeId = (Long)attributes.get("challengeId");

		if (challengeId != null) {
			setChallengeId(challengeId);
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
	* Returns the uuid of this child challenge.
	*
	* @return the uuid of this child challenge
	*/
	@Override
	public java.lang.String getUuid() {
		return _childChallenge.getUuid();
	}

	/**
	* Sets the uuid of this child challenge.
	*
	* @param uuid the uuid of this child challenge
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_childChallenge.setUuid(uuid);
	}

	/**
	* Returns the child challenge ID of this child challenge.
	*
	* @return the child challenge ID of this child challenge
	*/
	@Override
	public long getChildChallengeId() {
		return _childChallenge.getChildChallengeId();
	}

	/**
	* Sets the child challenge ID of this child challenge.
	*
	* @param childChallengeId the child challenge ID of this child challenge
	*/
	@Override
	public void setChildChallengeId(long childChallengeId) {
		_childChallenge.setChildChallengeId(childChallengeId);
	}

	/**
	* Returns the group ID of this child challenge.
	*
	* @return the group ID of this child challenge
	*/
	@Override
	public long getGroupId() {
		return _childChallenge.getGroupId();
	}

	/**
	* Sets the group ID of this child challenge.
	*
	* @param groupId the group ID of this child challenge
	*/
	@Override
	public void setGroupId(long groupId) {
		_childChallenge.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this child challenge.
	*
	* @return the company ID of this child challenge
	*/
	@Override
	public long getCompanyId() {
		return _childChallenge.getCompanyId();
	}

	/**
	* Sets the company ID of this child challenge.
	*
	* @param companyId the company ID of this child challenge
	*/
	@Override
	public void setCompanyId(long companyId) {
		_childChallenge.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this child challenge.
	*
	* @return the user ID of this child challenge
	*/
	@Override
	public long getUserId() {
		return _childChallenge.getUserId();
	}

	/**
	* Sets the user ID of this child challenge.
	*
	* @param userId the user ID of this child challenge
	*/
	@Override
	public void setUserId(long userId) {
		_childChallenge.setUserId(userId);
	}

	/**
	* Returns the user uuid of this child challenge.
	*
	* @return the user uuid of this child challenge
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallenge.getUserUuid();
	}

	/**
	* Sets the user uuid of this child challenge.
	*
	* @param userUuid the user uuid of this child challenge
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_childChallenge.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this child challenge.
	*
	* @return the user name of this child challenge
	*/
	@Override
	public java.lang.String getUserName() {
		return _childChallenge.getUserName();
	}

	/**
	* Sets the user name of this child challenge.
	*
	* @param userName the user name of this child challenge
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_childChallenge.setUserName(userName);
	}

	/**
	* Returns the create date of this child challenge.
	*
	* @return the create date of this child challenge
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _childChallenge.getCreateDate();
	}

	/**
	* Sets the create date of this child challenge.
	*
	* @param createDate the create date of this child challenge
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_childChallenge.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this child challenge.
	*
	* @return the modified date of this child challenge
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _childChallenge.getModifiedDate();
	}

	/**
	* Sets the modified date of this child challenge.
	*
	* @param modifiedDate the modified date of this child challenge
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_childChallenge.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this child challenge.
	*
	* @return the status of this child challenge
	*/
	@Override
	public int getStatus() {
		return _childChallenge.getStatus();
	}

	/**
	* Sets the status of this child challenge.
	*
	* @param status the status of this child challenge
	*/
	@Override
	public void setStatus(int status) {
		_childChallenge.setStatus(status);
	}

	/**
	* Returns the status by user ID of this child challenge.
	*
	* @return the status by user ID of this child challenge
	*/
	@Override
	public long getStatusByUserId() {
		return _childChallenge.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this child challenge.
	*
	* @param statusByUserId the status by user ID of this child challenge
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_childChallenge.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this child challenge.
	*
	* @return the status by user uuid of this child challenge
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallenge.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this child challenge.
	*
	* @param statusByUserUuid the status by user uuid of this child challenge
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_childChallenge.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this child challenge.
	*
	* @return the status by user name of this child challenge
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _childChallenge.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this child challenge.
	*
	* @param statusByUserName the status by user name of this child challenge
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_childChallenge.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this child challenge.
	*
	* @return the status date of this child challenge
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _childChallenge.getStatusDate();
	}

	/**
	* Sets the status date of this child challenge.
	*
	* @param statusDate the status date of this child challenge
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_childChallenge.setStatusDate(statusDate);
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
	* Returns the evaluation day of this child challenge.
	*
	* @return the evaluation day of this child challenge
	*/
	@Override
	public java.util.Date getEvaluationDay() {
		return _childChallenge.getEvaluationDay();
	}

	/**
	* Sets the evaluation day of this child challenge.
	*
	* @param evaluationDay the evaluation day of this child challenge
	*/
	@Override
	public void setEvaluationDay(java.util.Date evaluationDay) {
		_childChallenge.setEvaluationDay(evaluationDay);
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
	* Returns the challenge status of this child challenge.
	*
	* @return the challenge status of this child challenge
	*/
	@Override
	public java.lang.String getChallengeStatus() {
		return _childChallenge.getChallengeStatus();
	}

	/**
	* Sets the challenge status of this child challenge.
	*
	* @param challengeStatus the challenge status of this child challenge
	*/
	@Override
	public void setChallengeStatus(java.lang.String challengeStatus) {
		_childChallenge.setChallengeStatus(challengeStatus);
	}

	/**
	* Returns the challenge ID of this child challenge.
	*
	* @return the challenge ID of this child challenge
	*/
	@Override
	public long getChallengeId() {
		return _childChallenge.getChallengeId();
	}

	/**
	* Sets the challenge ID of this child challenge.
	*
	* @param challengeId the challenge ID of this child challenge
	*/
	@Override
	public void setChallengeId(long challengeId) {
		_childChallenge.setChallengeId(challengeId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _childChallenge.getApproved();
	}

	/**
	* Returns <code>true</code> if this child challenge is approved.
	*
	* @return <code>true</code> if this child challenge is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _childChallenge.isApproved();
	}

	/**
	* Returns <code>true</code> if this child challenge is denied.
	*
	* @return <code>true</code> if this child challenge is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _childChallenge.isDenied();
	}

	/**
	* Returns <code>true</code> if this child challenge is a draft.
	*
	* @return <code>true</code> if this child challenge is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _childChallenge.isDraft();
	}

	/**
	* Returns <code>true</code> if this child challenge is expired.
	*
	* @return <code>true</code> if this child challenge is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _childChallenge.isExpired();
	}

	/**
	* Returns <code>true</code> if this child challenge is inactive.
	*
	* @return <code>true</code> if this child challenge is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _childChallenge.isInactive();
	}

	/**
	* Returns <code>true</code> if this child challenge is incomplete.
	*
	* @return <code>true</code> if this child challenge is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _childChallenge.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this child challenge is pending.
	*
	* @return <code>true</code> if this child challenge is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _childChallenge.isPending();
	}

	/**
	* Returns <code>true</code> if this child challenge is scheduled.
	*
	* @return <code>true</code> if this child challenge is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _childChallenge.isScheduled();
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
		kisti.edison.challenge.model.ChildChallenge childChallenge) {
		return _childChallenge.compareTo(childChallenge);
	}

	@Override
	public int hashCode() {
		return _childChallenge.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChildChallenge> toCacheModel() {
		return _childChallenge.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChildChallenge toEscapedModel() {
		return new ChildChallengeWrapper(_childChallenge.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChildChallenge toUnescapedModel() {
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

	@Override
	public StagedModelType getStagedModelType() {
		return _childChallenge.getStagedModelType();
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