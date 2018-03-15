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
 * This class is a wrapper for {@link ChallengeEvaluationManagement}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationManagement
 * @generated
 */
public class ChallengeEvaluationManagementWrapper
	implements ChallengeEvaluationManagement,
		ModelWrapper<ChallengeEvaluationManagement> {
	public ChallengeEvaluationManagementWrapper(
		ChallengeEvaluationManagement challengeEvaluationManagement) {
		_challengeEvaluationManagement = challengeEvaluationManagement;
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeEvaluationManagement.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeEvaluationManagement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeEvaluationManagementId",
			getChallengeEvaluationManagementId());
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
		attributes.put("childChallengeId", getChildChallengeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeEvaluationManagementId = (Long)attributes.get(
				"challengeEvaluationManagementId");

		if (challengeEvaluationManagementId != null) {
			setChallengeEvaluationManagementId(challengeEvaluationManagementId);
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

		Long childChallengeId = (Long)attributes.get("childChallengeId");

		if (childChallengeId != null) {
			setChildChallengeId(childChallengeId);
		}
	}

	/**
	* Returns the primary key of this challenge evaluation management.
	*
	* @return the primary key of this challenge evaluation management
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeEvaluationManagement.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge evaluation management.
	*
	* @param primaryKey the primary key of this challenge evaluation management
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeEvaluationManagement.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge evaluation management.
	*
	* @return the uuid of this challenge evaluation management
	*/
	@Override
	public java.lang.String getUuid() {
		return _challengeEvaluationManagement.getUuid();
	}

	/**
	* Sets the uuid of this challenge evaluation management.
	*
	* @param uuid the uuid of this challenge evaluation management
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challengeEvaluationManagement.setUuid(uuid);
	}

	/**
	* Returns the challenge evaluation management ID of this challenge evaluation management.
	*
	* @return the challenge evaluation management ID of this challenge evaluation management
	*/
	@Override
	public long getChallengeEvaluationManagementId() {
		return _challengeEvaluationManagement.getChallengeEvaluationManagementId();
	}

	/**
	* Sets the challenge evaluation management ID of this challenge evaluation management.
	*
	* @param challengeEvaluationManagementId the challenge evaluation management ID of this challenge evaluation management
	*/
	@Override
	public void setChallengeEvaluationManagementId(
		long challengeEvaluationManagementId) {
		_challengeEvaluationManagement.setChallengeEvaluationManagementId(challengeEvaluationManagementId);
	}

	/**
	* Returns the group ID of this challenge evaluation management.
	*
	* @return the group ID of this challenge evaluation management
	*/
	@Override
	public long getGroupId() {
		return _challengeEvaluationManagement.getGroupId();
	}

	/**
	* Sets the group ID of this challenge evaluation management.
	*
	* @param groupId the group ID of this challenge evaluation management
	*/
	@Override
	public void setGroupId(long groupId) {
		_challengeEvaluationManagement.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge evaluation management.
	*
	* @return the company ID of this challenge evaluation management
	*/
	@Override
	public long getCompanyId() {
		return _challengeEvaluationManagement.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge evaluation management.
	*
	* @param companyId the company ID of this challenge evaluation management
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challengeEvaluationManagement.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge evaluation management.
	*
	* @return the user ID of this challenge evaluation management
	*/
	@Override
	public long getUserId() {
		return _challengeEvaluationManagement.getUserId();
	}

	/**
	* Sets the user ID of this challenge evaluation management.
	*
	* @param userId the user ID of this challenge evaluation management
	*/
	@Override
	public void setUserId(long userId) {
		_challengeEvaluationManagement.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge evaluation management.
	*
	* @return the user uuid of this challenge evaluation management
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagement.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge evaluation management.
	*
	* @param userUuid the user uuid of this challenge evaluation management
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challengeEvaluationManagement.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge evaluation management.
	*
	* @return the user name of this challenge evaluation management
	*/
	@Override
	public java.lang.String getUserName() {
		return _challengeEvaluationManagement.getUserName();
	}

	/**
	* Sets the user name of this challenge evaluation management.
	*
	* @param userName the user name of this challenge evaluation management
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challengeEvaluationManagement.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge evaluation management.
	*
	* @return the create date of this challenge evaluation management
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challengeEvaluationManagement.getCreateDate();
	}

	/**
	* Sets the create date of this challenge evaluation management.
	*
	* @param createDate the create date of this challenge evaluation management
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challengeEvaluationManagement.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge evaluation management.
	*
	* @return the modified date of this challenge evaluation management
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challengeEvaluationManagement.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge evaluation management.
	*
	* @param modifiedDate the modified date of this challenge evaluation management
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challengeEvaluationManagement.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge evaluation management.
	*
	* @return the status of this challenge evaluation management
	*/
	@Override
	public int getStatus() {
		return _challengeEvaluationManagement.getStatus();
	}

	/**
	* Sets the status of this challenge evaluation management.
	*
	* @param status the status of this challenge evaluation management
	*/
	@Override
	public void setStatus(int status) {
		_challengeEvaluationManagement.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge evaluation management.
	*
	* @return the status by user ID of this challenge evaluation management
	*/
	@Override
	public long getStatusByUserId() {
		return _challengeEvaluationManagement.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge evaluation management.
	*
	* @param statusByUserId the status by user ID of this challenge evaluation management
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challengeEvaluationManagement.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge evaluation management.
	*
	* @return the status by user uuid of this challenge evaluation management
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagement.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge evaluation management.
	*
	* @param statusByUserUuid the status by user uuid of this challenge evaluation management
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challengeEvaluationManagement.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge evaluation management.
	*
	* @return the status by user name of this challenge evaluation management
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challengeEvaluationManagement.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge evaluation management.
	*
	* @param statusByUserName the status by user name of this challenge evaluation management
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challengeEvaluationManagement.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge evaluation management.
	*
	* @return the status date of this challenge evaluation management
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challengeEvaluationManagement.getStatusDate();
	}

	/**
	* Sets the status date of this challenge evaluation management.
	*
	* @param statusDate the status date of this challenge evaluation management
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challengeEvaluationManagement.setStatusDate(statusDate);
	}

	/**
	* Returns the assessment item of this challenge evaluation management.
	*
	* @return the assessment item of this challenge evaluation management
	*/
	@Override
	public java.lang.String getAssessmentItem() {
		return _challengeEvaluationManagement.getAssessmentItem();
	}

	/**
	* Returns the localized assessment item of this challenge evaluation management in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized assessment item of this challenge evaluation management
	*/
	@Override
	public java.lang.String getAssessmentItem(java.util.Locale locale) {
		return _challengeEvaluationManagement.getAssessmentItem(locale);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation management in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized assessment item of this challenge evaluation management. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAssessmentItem(java.util.Locale locale,
		boolean useDefault) {
		return _challengeEvaluationManagement.getAssessmentItem(locale,
			useDefault);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation management in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized assessment item of this challenge evaluation management
	*/
	@Override
	public java.lang.String getAssessmentItem(java.lang.String languageId) {
		return _challengeEvaluationManagement.getAssessmentItem(languageId);
	}

	/**
	* Returns the localized assessment item of this challenge evaluation management in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized assessment item of this challenge evaluation management
	*/
	@Override
	public java.lang.String getAssessmentItem(java.lang.String languageId,
		boolean useDefault) {
		return _challengeEvaluationManagement.getAssessmentItem(languageId,
			useDefault);
	}

	@Override
	public java.lang.String getAssessmentItemCurrentLanguageId() {
		return _challengeEvaluationManagement.getAssessmentItemCurrentLanguageId();
	}

	@Override
	public java.lang.String getAssessmentItemCurrentValue() {
		return _challengeEvaluationManagement.getAssessmentItemCurrentValue();
	}

	/**
	* Returns a map of the locales and localized assessment items of this challenge evaluation management.
	*
	* @return the locales and localized assessment items of this challenge evaluation management
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getAssessmentItemMap() {
		return _challengeEvaluationManagement.getAssessmentItemMap();
	}

	/**
	* Sets the assessment item of this challenge evaluation management.
	*
	* @param assessmentItem the assessment item of this challenge evaluation management
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem) {
		_challengeEvaluationManagement.setAssessmentItem(assessmentItem);
	}

	/**
	* Sets the localized assessment item of this challenge evaluation management in the language.
	*
	* @param assessmentItem the localized assessment item of this challenge evaluation management
	* @param locale the locale of the language
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem,
		java.util.Locale locale) {
		_challengeEvaluationManagement.setAssessmentItem(assessmentItem, locale);
	}

	/**
	* Sets the localized assessment item of this challenge evaluation management in the language, and sets the default locale.
	*
	* @param assessmentItem the localized assessment item of this challenge evaluation management
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAssessmentItem(java.lang.String assessmentItem,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeEvaluationManagement.setAssessmentItem(assessmentItem,
			locale, defaultLocale);
	}

	@Override
	public void setAssessmentItemCurrentLanguageId(java.lang.String languageId) {
		_challengeEvaluationManagement.setAssessmentItemCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized assessment items of this challenge evaluation management from the map of locales and localized assessment items.
	*
	* @param assessmentItemMap the locales and localized assessment items of this challenge evaluation management
	*/
	@Override
	public void setAssessmentItemMap(
		java.util.Map<java.util.Locale, java.lang.String> assessmentItemMap) {
		_challengeEvaluationManagement.setAssessmentItemMap(assessmentItemMap);
	}

	/**
	* Sets the localized assessment items of this challenge evaluation management from the map of locales and localized assessment items, and sets the default locale.
	*
	* @param assessmentItemMap the locales and localized assessment items of this challenge evaluation management
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAssessmentItemMap(
		java.util.Map<java.util.Locale, java.lang.String> assessmentItemMap,
		java.util.Locale defaultLocale) {
		_challengeEvaluationManagement.setAssessmentItemMap(assessmentItemMap,
			defaultLocale);
	}

	/**
	* Returns the distribution of this challenge evaluation management.
	*
	* @return the distribution of this challenge evaluation management
	*/
	@Override
	public double getDistribution() {
		return _challengeEvaluationManagement.getDistribution();
	}

	/**
	* Sets the distribution of this challenge evaluation management.
	*
	* @param distribution the distribution of this challenge evaluation management
	*/
	@Override
	public void setDistribution(double distribution) {
		_challengeEvaluationManagement.setDistribution(distribution);
	}

	/**
	* Returns the child challenge ID of this challenge evaluation management.
	*
	* @return the child challenge ID of this challenge evaluation management
	*/
	@Override
	public long getChildChallengeId() {
		return _challengeEvaluationManagement.getChildChallengeId();
	}

	/**
	* Sets the child challenge ID of this challenge evaluation management.
	*
	* @param childChallengeId the child challenge ID of this challenge evaluation management
	*/
	@Override
	public void setChildChallengeId(long childChallengeId) {
		_challengeEvaluationManagement.setChildChallengeId(childChallengeId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challengeEvaluationManagement.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is approved.
	*
	* @return <code>true</code> if this challenge evaluation management is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challengeEvaluationManagement.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is denied.
	*
	* @return <code>true</code> if this challenge evaluation management is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challengeEvaluationManagement.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is a draft.
	*
	* @return <code>true</code> if this challenge evaluation management is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challengeEvaluationManagement.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is expired.
	*
	* @return <code>true</code> if this challenge evaluation management is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challengeEvaluationManagement.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is inactive.
	*
	* @return <code>true</code> if this challenge evaluation management is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challengeEvaluationManagement.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is incomplete.
	*
	* @return <code>true</code> if this challenge evaluation management is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challengeEvaluationManagement.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is pending.
	*
	* @return <code>true</code> if this challenge evaluation management is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challengeEvaluationManagement.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge evaluation management is scheduled.
	*
	* @return <code>true</code> if this challenge evaluation management is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challengeEvaluationManagement.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challengeEvaluationManagement.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeEvaluationManagement.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeEvaluationManagement.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeEvaluationManagement.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeEvaluationManagement.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeEvaluationManagement.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeEvaluationManagement.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeEvaluationManagement.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeEvaluationManagement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeEvaluationManagement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeEvaluationManagement.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challengeEvaluationManagement.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challengeEvaluationManagement.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challengeEvaluationManagement.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challengeEvaluationManagement.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeEvaluationManagementWrapper((ChallengeEvaluationManagement)_challengeEvaluationManagement.clone());
	}

	@Override
	public int compareTo(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement) {
		return _challengeEvaluationManagement.compareTo(challengeEvaluationManagement);
	}

	@Override
	public int hashCode() {
		return _challengeEvaluationManagement.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChallengeEvaluationManagement> toCacheModel() {
		return _challengeEvaluationManagement.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationManagement toEscapedModel() {
		return new ChallengeEvaluationManagementWrapper(_challengeEvaluationManagement.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationManagement toUnescapedModel() {
		return new ChallengeEvaluationManagementWrapper(_challengeEvaluationManagement.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeEvaluationManagement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeEvaluationManagement.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeEvaluationManagement.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeEvaluationManagementWrapper)) {
			return false;
		}

		ChallengeEvaluationManagementWrapper challengeEvaluationManagementWrapper =
			(ChallengeEvaluationManagementWrapper)obj;

		if (Validator.equals(_challengeEvaluationManagement,
					challengeEvaluationManagementWrapper._challengeEvaluationManagement)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challengeEvaluationManagement.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeEvaluationManagement getWrappedChallengeEvaluationManagement() {
		return _challengeEvaluationManagement;
	}

	@Override
	public ChallengeEvaluationManagement getWrappedModel() {
		return _challengeEvaluationManagement;
	}

	@Override
	public void resetOriginalValues() {
		_challengeEvaluationManagement.resetOriginalValues();
	}

	private ChallengeEvaluationManagement _challengeEvaluationManagement;
}