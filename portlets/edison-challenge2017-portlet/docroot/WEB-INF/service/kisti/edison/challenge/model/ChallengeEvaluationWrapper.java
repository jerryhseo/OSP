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
 * This class is a wrapper for {@link ChallengeEvaluation}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluation
 * @generated
 */
public class ChallengeEvaluationWrapper implements ChallengeEvaluation,
	ModelWrapper<ChallengeEvaluation> {
	public ChallengeEvaluationWrapper(ChallengeEvaluation challengeEvaluation) {
		_challengeEvaluation = challengeEvaluation;
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeEvaluation.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeEvaluation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeEvaluationId", getChallengeEvaluationId());
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
		attributes.put("assessmentItem", getAssessmentItem());
		attributes.put("distribution", getDistribution());
		attributes.put("score", getScore());
		attributes.put("challengeTeamId", getChallengeTeamId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeEvaluationId = (Long)attributes.get(
				"challengeEvaluationId");

		if (challengeEvaluationId != null) {
			setChallengeEvaluationId(challengeEvaluationId);
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

		String assessmentItem = (String)attributes.get("assessmentItem");

		if (assessmentItem != null) {
			setAssessmentItem(assessmentItem);
		}

		Double distribution = (Double)attributes.get("distribution");

		if (distribution != null) {
			setDistribution(distribution);
		}

		Double score = (Double)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Long challengeTeamId = (Long)attributes.get("challengeTeamId");

		if (challengeTeamId != null) {
			setChallengeTeamId(challengeTeamId);
		}
	}

	/**
	* Returns the primary key of this challenge evaluation.
	*
	* @return the primary key of this challenge evaluation
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeEvaluation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge evaluation.
	*
	* @param primaryKey the primary key of this challenge evaluation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeEvaluation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge evaluation.
	*
	* @return the uuid of this challenge evaluation
	*/
	@Override
	public java.lang.String getUuid() {
		return _challengeEvaluation.getUuid();
	}

	/**
	* Sets the uuid of this challenge evaluation.
	*
	* @param uuid the uuid of this challenge evaluation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challengeEvaluation.setUuid(uuid);
	}

	/**
	* Returns the challenge evaluation ID of this challenge evaluation.
	*
	* @return the challenge evaluation ID of this challenge evaluation
	*/
	@Override
	public long getChallengeEvaluationId() {
		return _challengeEvaluation.getChallengeEvaluationId();
	}

	/**
	* Sets the challenge evaluation ID of this challenge evaluation.
	*
	* @param challengeEvaluationId the challenge evaluation ID of this challenge evaluation
	*/
	@Override
	public void setChallengeEvaluationId(long challengeEvaluationId) {
		_challengeEvaluation.setChallengeEvaluationId(challengeEvaluationId);
	}

	/**
	* Returns the group ID of this challenge evaluation.
	*
	* @return the group ID of this challenge evaluation
	*/
	@Override
	public long getGroupId() {
		return _challengeEvaluation.getGroupId();
	}

	/**
	* Sets the group ID of this challenge evaluation.
	*
	* @param groupId the group ID of this challenge evaluation
	*/
	@Override
	public void setGroupId(long groupId) {
		_challengeEvaluation.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge evaluation.
	*
	* @return the company ID of this challenge evaluation
	*/
	@Override
	public long getCompanyId() {
		return _challengeEvaluation.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge evaluation.
	*
	* @param companyId the company ID of this challenge evaluation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challengeEvaluation.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge evaluation.
	*
	* @return the user ID of this challenge evaluation
	*/
	@Override
	public long getUserId() {
		return _challengeEvaluation.getUserId();
	}

	/**
	* Sets the user ID of this challenge evaluation.
	*
	* @param userId the user ID of this challenge evaluation
	*/
	@Override
	public void setUserId(long userId) {
		_challengeEvaluation.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge evaluation.
	*
	* @return the user uuid of this challenge evaluation
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluation.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge evaluation.
	*
	* @param userUuid the user uuid of this challenge evaluation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challengeEvaluation.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge evaluation.
	*
	* @return the user name of this challenge evaluation
	*/
	@Override
	public java.lang.String getUserName() {
		return _challengeEvaluation.getUserName();
	}

	/**
	* Sets the user name of this challenge evaluation.
	*
	* @param userName the user name of this challenge evaluation
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challengeEvaluation.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge evaluation.
	*
	* @return the create date of this challenge evaluation
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challengeEvaluation.getCreateDate();
	}

	/**
	* Sets the create date of this challenge evaluation.
	*
	* @param createDate the create date of this challenge evaluation
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challengeEvaluation.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge evaluation.
	*
	* @return the modified date of this challenge evaluation
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challengeEvaluation.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge evaluation.
	*
	* @param modifiedDate the modified date of this challenge evaluation
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challengeEvaluation.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge evaluation.
	*
	* @return the status of this challenge evaluation
	*/
	@Override
	public int getStatus() {
		return _challengeEvaluation.getStatus();
	}

	/**
	* Sets the status of this challenge evaluation.
	*
	* @param status the status of this challenge evaluation
	*/
	@Override
	public void setStatus(int status) {
		_challengeEvaluation.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge evaluation.
	*
	* @return the status by user ID of this challenge evaluation
	*/
	@Override
	public long getStatusByUserId() {
		return _challengeEvaluation.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge evaluation.
	*
	* @param statusByUserId the status by user ID of this challenge evaluation
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challengeEvaluation.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge evaluation.
	*
	* @return the status by user uuid of this challenge evaluation
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluation.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge evaluation.
	*
	* @param statusByUserUuid the status by user uuid of this challenge evaluation
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challengeEvaluation.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge evaluation.
	*
	* @return the status by user name of this challenge evaluation
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challengeEvaluation.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge evaluation.
	*
	* @param statusByUserName the status by user name of this challenge evaluation
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challengeEvaluation.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge evaluation.
	*
	* @return the status date of this challenge evaluation
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challengeEvaluation.getStatusDate();
	}

	/**
	* Sets the status date of this challenge evaluation.
	*
	* @param statusDate the status date of this challenge evaluation
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challengeEvaluation.setStatusDate(statusDate);
	}

	/**
	* Returns the assessment item of this challenge evaluation.
	*
	* @return the assessment item of this challenge evaluation
	*/
	@Override
	public java.lang.String getAssessmentItem() {
		return _challengeEvaluation.getAssessmentItem();
	}

	/**
	* Returns the localized assessment item of this challenge evaluation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized assessment item of this challenge evaluation
	*/
	@Override
	public java.lang.String getAssessmentItem(java.util.Locale locale) {
		return _challengeEvaluation.getAssessmentItem(locale);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized assessment item of this challenge evaluation. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAssessmentItem(java.util.Locale locale,
		boolean useDefault) {
		return _challengeEvaluation.getAssessmentItem(locale, useDefault);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized assessment item of this challenge evaluation
	*/
	@Override
	public java.lang.String getAssessmentItem(java.lang.String languageId) {
		return _challengeEvaluation.getAssessmentItem(languageId);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized assessment item of this challenge evaluation
	*/
	@Override
	public java.lang.String getAssessmentItem(java.lang.String languageId,
		boolean useDefault) {
		return _challengeEvaluation.getAssessmentItem(languageId, useDefault);
	}

	@Override
	public java.lang.String getAssessmentItemCurrentLanguageId() {
		return _challengeEvaluation.getAssessmentItemCurrentLanguageId();
	}

	@Override
	public java.lang.String getAssessmentItemCurrentValue() {
		return _challengeEvaluation.getAssessmentItemCurrentValue();
	}

	/**
	* Returns a map of the locales and localized assessment items of this challenge evaluation.
	*
	* @return the locales and localized assessment items of this challenge evaluation
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getAssessmentItemMap() {
		return _challengeEvaluation.getAssessmentItemMap();
	}

	/**
	* Sets the assessment item of this challenge evaluation.
	*
	* @param assessmentItem the assessment item of this challenge evaluation
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem) {
		_challengeEvaluation.setAssessmentItem(assessmentItem);
	}

	/**
	* Sets the localized assessment item of this challenge evaluation in the language.
	*
	* @param assessmentItem the localized assessment item of this challenge evaluation
	* @param locale the locale of the language
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem,
		java.util.Locale locale) {
		_challengeEvaluation.setAssessmentItem(assessmentItem, locale);
	}

	/**
	* Sets the localized assessment item of this challenge evaluation in the language, and sets the default locale.
	*
	* @param assessmentItem the localized assessment item of this challenge evaluation
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeEvaluation.setAssessmentItem(assessmentItem, locale,
			defaultLocale);
	}

	@Override
	public void setAssessmentItemCurrentLanguageId(java.lang.String languageId) {
		_challengeEvaluation.setAssessmentItemCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized assessment items of this challenge evaluation from the map of locales and localized assessment items.
	*
	* @param assessmentItemMap the locales and localized assessment items of this challenge evaluation
	*/
	@Override
	public void setAssessmentItemMap(
		java.util.Map<java.util.Locale, java.lang.String> assessmentItemMap) {
		_challengeEvaluation.setAssessmentItemMap(assessmentItemMap);
	}

	/**
	* Sets the localized assessment items of this challenge evaluation from the map of locales and localized assessment items, and sets the default locale.
	*
	* @param assessmentItemMap the locales and localized assessment items of this challenge evaluation
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAssessmentItemMap(
		java.util.Map<java.util.Locale, java.lang.String> assessmentItemMap,
		java.util.Locale defaultLocale) {
		_challengeEvaluation.setAssessmentItemMap(assessmentItemMap,
			defaultLocale);
	}

	/**
	* Returns the distribution of this challenge evaluation.
	*
	* @return the distribution of this challenge evaluation
	*/
	@Override
	public double getDistribution() {
		return _challengeEvaluation.getDistribution();
	}

	/**
	* Sets the distribution of this challenge evaluation.
	*
	* @param distribution the distribution of this challenge evaluation
	*/
	@Override
	public void setDistribution(double distribution) {
		_challengeEvaluation.setDistribution(distribution);
	}

	/**
	* Returns the score of this challenge evaluation.
	*
	* @return the score of this challenge evaluation
	*/
	@Override
	public double getScore() {
		return _challengeEvaluation.getScore();
	}

	/**
	* Sets the score of this challenge evaluation.
	*
	* @param score the score of this challenge evaluation
	*/
	@Override
	public void setScore(double score) {
		_challengeEvaluation.setScore(score);
	}

	/**
	* Returns the challenge team ID of this challenge evaluation.
	*
	* @return the challenge team ID of this challenge evaluation
	*/
	@Override
	public long getChallengeTeamId() {
		return _challengeEvaluation.getChallengeTeamId();
	}

	/**
	* Sets the challenge team ID of this challenge evaluation.
	*
	* @param challengeTeamId the challenge team ID of this challenge evaluation
	*/
	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeEvaluation.setChallengeTeamId(challengeTeamId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challengeEvaluation.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is approved.
	*
	* @return <code>true</code> if this challenge evaluation is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challengeEvaluation.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is denied.
	*
	* @return <code>true</code> if this challenge evaluation is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challengeEvaluation.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is a draft.
	*
	* @return <code>true</code> if this challenge evaluation is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challengeEvaluation.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is expired.
	*
	* @return <code>true</code> if this challenge evaluation is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challengeEvaluation.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is inactive.
	*
	* @return <code>true</code> if this challenge evaluation is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challengeEvaluation.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is incomplete.
	*
	* @return <code>true</code> if this challenge evaluation is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challengeEvaluation.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is pending.
	*
	* @return <code>true</code> if this challenge evaluation is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challengeEvaluation.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation is scheduled.
	*
	* @return <code>true</code> if this challenge evaluation is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challengeEvaluation.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challengeEvaluation.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeEvaluation.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeEvaluation.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeEvaluation.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeEvaluation.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeEvaluation.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeEvaluation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeEvaluation.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeEvaluation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeEvaluation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeEvaluation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challengeEvaluation.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challengeEvaluation.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challengeEvaluation.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challengeEvaluation.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeEvaluationWrapper((ChallengeEvaluation)_challengeEvaluation.clone());
	}

	@Override
	public int compareTo(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation) {
		return _challengeEvaluation.compareTo(challengeEvaluation);
	}

	@Override
	public int hashCode() {
		return _challengeEvaluation.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChallengeEvaluation> toCacheModel() {
		return _challengeEvaluation.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluation toEscapedModel() {
		return new ChallengeEvaluationWrapper(_challengeEvaluation.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluation toUnescapedModel() {
		return new ChallengeEvaluationWrapper(_challengeEvaluation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeEvaluation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeEvaluation.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeEvaluation.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeEvaluationWrapper)) {
			return false;
		}

		ChallengeEvaluationWrapper challengeEvaluationWrapper = (ChallengeEvaluationWrapper)obj;

		if (Validator.equals(_challengeEvaluation,
					challengeEvaluationWrapper._challengeEvaluation)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challengeEvaluation.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeEvaluation getWrappedChallengeEvaluation() {
		return _challengeEvaluation;
	}

	@Override
	public ChallengeEvaluation getWrappedModel() {
		return _challengeEvaluation;
	}

	@Override
	public void resetOriginalValues() {
		_challengeEvaluation.resetOriginalValues();
	}

	private ChallengeEvaluation _challengeEvaluation;
}