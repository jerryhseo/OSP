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
 * This class is a wrapper for {@link ChallengeEvaluationScore}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationScore
 * @generated
 */
public class ChallengeEvaluationScoreWrapper implements ChallengeEvaluationScore,
	ModelWrapper<ChallengeEvaluationScore> {
	public ChallengeEvaluationScoreWrapper(
		ChallengeEvaluationScore challengeEvaluationScore) {
		_challengeEvaluationScore = challengeEvaluationScore;
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeEvaluationScore.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeEvaluationScore.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeEvaluationScoreId",
			getChallengeEvaluationScoreId());
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
		attributes.put("score", getScore());
		attributes.put("challengeTeamId", getChallengeTeamId());
		attributes.put("challengeEvaluationManagementId",
			getChallengeEvaluationManagementId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeEvaluationScoreId = (Long)attributes.get(
				"challengeEvaluationScoreId");

		if (challengeEvaluationScoreId != null) {
			setChallengeEvaluationScoreId(challengeEvaluationScoreId);
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

		Double score = (Double)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Long challengeTeamId = (Long)attributes.get("challengeTeamId");

		if (challengeTeamId != null) {
			setChallengeTeamId(challengeTeamId);
		}

		Long challengeEvaluationManagementId = (Long)attributes.get(
				"challengeEvaluationManagementId");

		if (challengeEvaluationManagementId != null) {
			setChallengeEvaluationManagementId(challengeEvaluationManagementId);
		}
	}

	/**
	* Returns the primary key of this challenge evaluation score.
	*
	* @return the primary key of this challenge evaluation score
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeEvaluationScore.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge evaluation score.
	*
	* @param primaryKey the primary key of this challenge evaluation score
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeEvaluationScore.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge evaluation score.
	*
	* @return the uuid of this challenge evaluation score
	*/
	@Override
	public java.lang.String getUuid() {
		return _challengeEvaluationScore.getUuid();
	}

	/**
	* Sets the uuid of this challenge evaluation score.
	*
	* @param uuid the uuid of this challenge evaluation score
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challengeEvaluationScore.setUuid(uuid);
	}

	/**
	* Returns the challenge evaluation score ID of this challenge evaluation score.
	*
	* @return the challenge evaluation score ID of this challenge evaluation score
	*/
	@Override
	public long getChallengeEvaluationScoreId() {
		return _challengeEvaluationScore.getChallengeEvaluationScoreId();
	}

	/**
	* Sets the challenge evaluation score ID of this challenge evaluation score.
	*
	* @param challengeEvaluationScoreId the challenge evaluation score ID of this challenge evaluation score
	*/
	@Override
	public void setChallengeEvaluationScoreId(long challengeEvaluationScoreId) {
		_challengeEvaluationScore.setChallengeEvaluationScoreId(challengeEvaluationScoreId);
	}

	/**
	* Returns the group ID of this challenge evaluation score.
	*
	* @return the group ID of this challenge evaluation score
	*/
	@Override
	public long getGroupId() {
		return _challengeEvaluationScore.getGroupId();
	}

	/**
	* Sets the group ID of this challenge evaluation score.
	*
	* @param groupId the group ID of this challenge evaluation score
	*/
	@Override
	public void setGroupId(long groupId) {
		_challengeEvaluationScore.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge evaluation score.
	*
	* @return the company ID of this challenge evaluation score
	*/
	@Override
	public long getCompanyId() {
		return _challengeEvaluationScore.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge evaluation score.
	*
	* @param companyId the company ID of this challenge evaluation score
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challengeEvaluationScore.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge evaluation score.
	*
	* @return the user ID of this challenge evaluation score
	*/
	@Override
	public long getUserId() {
		return _challengeEvaluationScore.getUserId();
	}

	/**
	* Sets the user ID of this challenge evaluation score.
	*
	* @param userId the user ID of this challenge evaluation score
	*/
	@Override
	public void setUserId(long userId) {
		_challengeEvaluationScore.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge evaluation score.
	*
	* @return the user uuid of this challenge evaluation score
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScore.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge evaluation score.
	*
	* @param userUuid the user uuid of this challenge evaluation score
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challengeEvaluationScore.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge evaluation score.
	*
	* @return the user name of this challenge evaluation score
	*/
	@Override
	public java.lang.String getUserName() {
		return _challengeEvaluationScore.getUserName();
	}

	/**
	* Sets the user name of this challenge evaluation score.
	*
	* @param userName the user name of this challenge evaluation score
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challengeEvaluationScore.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge evaluation score.
	*
	* @return the create date of this challenge evaluation score
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challengeEvaluationScore.getCreateDate();
	}

	/**
	* Sets the create date of this challenge evaluation score.
	*
	* @param createDate the create date of this challenge evaluation score
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challengeEvaluationScore.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge evaluation score.
	*
	* @return the modified date of this challenge evaluation score
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challengeEvaluationScore.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge evaluation score.
	*
	* @param modifiedDate the modified date of this challenge evaluation score
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challengeEvaluationScore.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge evaluation score.
	*
	* @return the status of this challenge evaluation score
	*/
	@Override
	public int getStatus() {
		return _challengeEvaluationScore.getStatus();
	}

	/**
	* Sets the status of this challenge evaluation score.
	*
	* @param status the status of this challenge evaluation score
	*/
	@Override
	public void setStatus(int status) {
		_challengeEvaluationScore.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge evaluation score.
	*
	* @return the status by user ID of this challenge evaluation score
	*/
	@Override
	public long getStatusByUserId() {
		return _challengeEvaluationScore.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge evaluation score.
	*
	* @param statusByUserId the status by user ID of this challenge evaluation score
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challengeEvaluationScore.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge evaluation score.
	*
	* @return the status by user uuid of this challenge evaluation score
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScore.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge evaluation score.
	*
	* @param statusByUserUuid the status by user uuid of this challenge evaluation score
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challengeEvaluationScore.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge evaluation score.
	*
	* @return the status by user name of this challenge evaluation score
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challengeEvaluationScore.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge evaluation score.
	*
	* @param statusByUserName the status by user name of this challenge evaluation score
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challengeEvaluationScore.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge evaluation score.
	*
	* @return the status date of this challenge evaluation score
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challengeEvaluationScore.getStatusDate();
	}

	/**
	* Sets the status date of this challenge evaluation score.
	*
	* @param statusDate the status date of this challenge evaluation score
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challengeEvaluationScore.setStatusDate(statusDate);
	}

	/**
	* Returns the score of this challenge evaluation score.
	*
	* @return the score of this challenge evaluation score
	*/
	@Override
	public double getScore() {
		return _challengeEvaluationScore.getScore();
	}

	/**
	* Sets the score of this challenge evaluation score.
	*
	* @param score the score of this challenge evaluation score
	*/
	@Override
	public void setScore(double score) {
		_challengeEvaluationScore.setScore(score);
	}

	/**
	* Returns the challenge team ID of this challenge evaluation score.
	*
	* @return the challenge team ID of this challenge evaluation score
	*/
	@Override
	public long getChallengeTeamId() {
		return _challengeEvaluationScore.getChallengeTeamId();
	}

	/**
	* Sets the challenge team ID of this challenge evaluation score.
	*
	* @param challengeTeamId the challenge team ID of this challenge evaluation score
	*/
	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeEvaluationScore.setChallengeTeamId(challengeTeamId);
	}

	/**
	* Returns the challenge evaluation management ID of this challenge evaluation score.
	*
	* @return the challenge evaluation management ID of this challenge evaluation score
	*/
	@Override
	public long getChallengeEvaluationManagementId() {
		return _challengeEvaluationScore.getChallengeEvaluationManagementId();
	}

	/**
	* Sets the challenge evaluation management ID of this challenge evaluation score.
	*
	* @param challengeEvaluationManagementId the challenge evaluation management ID of this challenge evaluation score
	*/
	@Override
	public void setChallengeEvaluationManagementId(
		long challengeEvaluationManagementId) {
		_challengeEvaluationScore.setChallengeEvaluationManagementId(challengeEvaluationManagementId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challengeEvaluationScore.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is approved.
	*
	* @return <code>true</code> if this challenge evaluation score is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challengeEvaluationScore.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is denied.
	*
	* @return <code>true</code> if this challenge evaluation score is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challengeEvaluationScore.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is a draft.
	*
	* @return <code>true</code> if this challenge evaluation score is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challengeEvaluationScore.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is expired.
	*
	* @return <code>true</code> if this challenge evaluation score is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challengeEvaluationScore.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is inactive.
	*
	* @return <code>true</code> if this challenge evaluation score is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challengeEvaluationScore.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is incomplete.
	*
	* @return <code>true</code> if this challenge evaluation score is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challengeEvaluationScore.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is pending.
	*
	* @return <code>true</code> if this challenge evaluation score is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challengeEvaluationScore.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation score is scheduled.
	*
	* @return <code>true</code> if this challenge evaluation score is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challengeEvaluationScore.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challengeEvaluationScore.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeEvaluationScore.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeEvaluationScore.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeEvaluationScore.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeEvaluationScore.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeEvaluationScore.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeEvaluationScore.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeEvaluationScore.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeEvaluationScore.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeEvaluationScore.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeEvaluationScore.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeEvaluationScoreWrapper((ChallengeEvaluationScore)_challengeEvaluationScore.clone());
	}

	@Override
	public int compareTo(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore) {
		return _challengeEvaluationScore.compareTo(challengeEvaluationScore);
	}

	@Override
	public int hashCode() {
		return _challengeEvaluationScore.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChallengeEvaluationScore> toCacheModel() {
		return _challengeEvaluationScore.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore toEscapedModel() {
		return new ChallengeEvaluationScoreWrapper(_challengeEvaluationScore.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore toUnescapedModel() {
		return new ChallengeEvaluationScoreWrapper(_challengeEvaluationScore.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeEvaluationScore.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeEvaluationScore.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeEvaluationScore.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeEvaluationScoreWrapper)) {
			return false;
		}

		ChallengeEvaluationScoreWrapper challengeEvaluationScoreWrapper = (ChallengeEvaluationScoreWrapper)obj;

		if (Validator.equals(_challengeEvaluationScore,
					challengeEvaluationScoreWrapper._challengeEvaluationScore)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challengeEvaluationScore.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeEvaluationScore getWrappedChallengeEvaluationScore() {
		return _challengeEvaluationScore;
	}

	@Override
	public ChallengeEvaluationScore getWrappedModel() {
		return _challengeEvaluationScore;
	}

	@Override
	public void resetOriginalValues() {
		_challengeEvaluationScore.resetOriginalValues();
	}

	private ChallengeEvaluationScore _challengeEvaluationScore;
}