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
 * This class is a wrapper for {@link Challenge}.
 * </p>
 *
 * @author KYJ
 * @see Challenge
 * @generated
 */
public class ChallengeWrapper implements Challenge, ModelWrapper<Challenge> {
	public ChallengeWrapper(Challenge challenge) {
		_challenge = challenge;
	}

	@Override
	public Class<?> getModelClass() {
		return Challenge.class;
	}

	@Override
	public String getModelClassName() {
		return Challenge.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeId", getChallengeId());
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
		attributes.put("name", getName());
		attributes.put("field", getField());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeId = (Long)attributes.get("challengeId");

		if (challengeId != null) {
			setChallengeId(challengeId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String field = (String)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	* Returns the primary key of this challenge.
	*
	* @return the primary key of this challenge
	*/
	@Override
	public long getPrimaryKey() {
		return _challenge.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge.
	*
	* @param primaryKey the primary key of this challenge
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challenge.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge.
	*
	* @return the uuid of this challenge
	*/
	@Override
	public java.lang.String getUuid() {
		return _challenge.getUuid();
	}

	/**
	* Sets the uuid of this challenge.
	*
	* @param uuid the uuid of this challenge
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challenge.setUuid(uuid);
	}

	/**
	* Returns the challenge ID of this challenge.
	*
	* @return the challenge ID of this challenge
	*/
	@Override
	public long getChallengeId() {
		return _challenge.getChallengeId();
	}

	/**
	* Sets the challenge ID of this challenge.
	*
	* @param challengeId the challenge ID of this challenge
	*/
	@Override
	public void setChallengeId(long challengeId) {
		_challenge.setChallengeId(challengeId);
	}

	/**
	* Returns the group ID of this challenge.
	*
	* @return the group ID of this challenge
	*/
	@Override
	public long getGroupId() {
		return _challenge.getGroupId();
	}

	/**
	* Sets the group ID of this challenge.
	*
	* @param groupId the group ID of this challenge
	*/
	@Override
	public void setGroupId(long groupId) {
		_challenge.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge.
	*
	* @return the company ID of this challenge
	*/
	@Override
	public long getCompanyId() {
		return _challenge.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge.
	*
	* @param companyId the company ID of this challenge
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challenge.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge.
	*
	* @return the user ID of this challenge
	*/
	@Override
	public long getUserId() {
		return _challenge.getUserId();
	}

	/**
	* Sets the user ID of this challenge.
	*
	* @param userId the user ID of this challenge
	*/
	@Override
	public void setUserId(long userId) {
		_challenge.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge.
	*
	* @return the user uuid of this challenge
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challenge.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge.
	*
	* @param userUuid the user uuid of this challenge
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challenge.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge.
	*
	* @return the user name of this challenge
	*/
	@Override
	public java.lang.String getUserName() {
		return _challenge.getUserName();
	}

	/**
	* Sets the user name of this challenge.
	*
	* @param userName the user name of this challenge
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challenge.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge.
	*
	* @return the create date of this challenge
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challenge.getCreateDate();
	}

	/**
	* Sets the create date of this challenge.
	*
	* @param createDate the create date of this challenge
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challenge.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge.
	*
	* @return the modified date of this challenge
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challenge.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge.
	*
	* @param modifiedDate the modified date of this challenge
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challenge.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge.
	*
	* @return the status of this challenge
	*/
	@Override
	public int getStatus() {
		return _challenge.getStatus();
	}

	/**
	* Sets the status of this challenge.
	*
	* @param status the status of this challenge
	*/
	@Override
	public void setStatus(int status) {
		_challenge.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge.
	*
	* @return the status by user ID of this challenge
	*/
	@Override
	public long getStatusByUserId() {
		return _challenge.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge.
	*
	* @param statusByUserId the status by user ID of this challenge
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challenge.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge.
	*
	* @return the status by user uuid of this challenge
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challenge.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge.
	*
	* @param statusByUserUuid the status by user uuid of this challenge
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challenge.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge.
	*
	* @return the status by user name of this challenge
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challenge.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge.
	*
	* @param statusByUserName the status by user name of this challenge
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challenge.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge.
	*
	* @return the status date of this challenge
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challenge.getStatusDate();
	}

	/**
	* Sets the status date of this challenge.
	*
	* @param statusDate the status date of this challenge
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challenge.setStatusDate(statusDate);
	}

	/**
	* Returns the name of this challenge.
	*
	* @return the name of this challenge
	*/
	@Override
	public java.lang.String getName() {
		return _challenge.getName();
	}

	/**
	* Returns the localized name of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this challenge
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _challenge.getName(locale);
	}

	/**
	* Returns the localized name of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this challenge. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _challenge.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this challenge
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _challenge.getName(languageId);
	}

	/**
	* Returns the localized name of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this challenge
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _challenge.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _challenge.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _challenge.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this challenge.
	*
	* @return the locales and localized names of this challenge
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _challenge.getNameMap();
	}

	/**
	* Sets the name of this challenge.
	*
	* @param name the name of this challenge
	*/
	@Override
	public void setName(java.lang.String name) {
		_challenge.setName(name);
	}

	/**
	* Sets the localized name of this challenge in the language.
	*
	* @param name the localized name of this challenge
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_challenge.setName(name, locale);
	}

	/**
	* Sets the localized name of this challenge in the language, and sets the default locale.
	*
	* @param name the localized name of this challenge
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challenge.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_challenge.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this challenge from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this challenge
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_challenge.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this challenge from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this challenge
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_challenge.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the field of this challenge.
	*
	* @return the field of this challenge
	*/
	@Override
	public java.lang.String getField() {
		return _challenge.getField();
	}

	/**
	* Returns the localized field of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized field of this challenge
	*/
	@Override
	public java.lang.String getField(java.util.Locale locale) {
		return _challenge.getField(locale);
	}

	/**
	* Returns the localized field of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized field of this challenge. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getField(java.util.Locale locale, boolean useDefault) {
		return _challenge.getField(locale, useDefault);
	}

	/**
	* Returns the localized field of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized field of this challenge
	*/
	@Override
	public java.lang.String getField(java.lang.String languageId) {
		return _challenge.getField(languageId);
	}

	/**
	* Returns the localized field of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized field of this challenge
	*/
	@Override
	public java.lang.String getField(java.lang.String languageId,
		boolean useDefault) {
		return _challenge.getField(languageId, useDefault);
	}

	@Override
	public java.lang.String getFieldCurrentLanguageId() {
		return _challenge.getFieldCurrentLanguageId();
	}

	@Override
	public java.lang.String getFieldCurrentValue() {
		return _challenge.getFieldCurrentValue();
	}

	/**
	* Returns a map of the locales and localized fields of this challenge.
	*
	* @return the locales and localized fields of this challenge
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getFieldMap() {
		return _challenge.getFieldMap();
	}

	/**
	* Sets the field of this challenge.
	*
	* @param field the field of this challenge
	*/
	@Override
	public void setField(java.lang.String field) {
		_challenge.setField(field);
	}

	/**
	* Sets the localized field of this challenge in the language.
	*
	* @param field the localized field of this challenge
	* @param locale the locale of the language
	*/
	@Override
	public void setField(java.lang.String field, java.util.Locale locale) {
		_challenge.setField(field, locale);
	}

	/**
	* Sets the localized field of this challenge in the language, and sets the default locale.
	*
	* @param field the localized field of this challenge
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setField(java.lang.String field, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challenge.setField(field, locale, defaultLocale);
	}

	@Override
	public void setFieldCurrentLanguageId(java.lang.String languageId) {
		_challenge.setFieldCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized fields of this challenge from the map of locales and localized fields.
	*
	* @param fieldMap the locales and localized fields of this challenge
	*/
	@Override
	public void setFieldMap(
		java.util.Map<java.util.Locale, java.lang.String> fieldMap) {
		_challenge.setFieldMap(fieldMap);
	}

	/**
	* Sets the localized fields of this challenge from the map of locales and localized fields, and sets the default locale.
	*
	* @param fieldMap the locales and localized fields of this challenge
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFieldMap(
		java.util.Map<java.util.Locale, java.lang.String> fieldMap,
		java.util.Locale defaultLocale) {
		_challenge.setFieldMap(fieldMap, defaultLocale);
	}

	/**
	* Returns the description of this challenge.
	*
	* @return the description of this challenge
	*/
	@Override
	public java.lang.String getDescription() {
		return _challenge.getDescription();
	}

	/**
	* Returns the localized description of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this challenge
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _challenge.getDescription(locale);
	}

	/**
	* Returns the localized description of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this challenge. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _challenge.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this challenge in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this challenge
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _challenge.getDescription(languageId);
	}

	/**
	* Returns the localized description of this challenge in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this challenge
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _challenge.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _challenge.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _challenge.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this challenge.
	*
	* @return the locales and localized descriptions of this challenge
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _challenge.getDescriptionMap();
	}

	/**
	* Sets the description of this challenge.
	*
	* @param description the description of this challenge
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_challenge.setDescription(description);
	}

	/**
	* Sets the localized description of this challenge in the language.
	*
	* @param description the localized description of this challenge
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_challenge.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this challenge in the language, and sets the default locale.
	*
	* @param description the localized description of this challenge
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challenge.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_challenge.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this challenge from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this challenge
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_challenge.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this challenge from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this challenge
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_challenge.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challenge.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge is approved.
	*
	* @return <code>true</code> if this challenge is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challenge.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge is denied.
	*
	* @return <code>true</code> if this challenge is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challenge.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge is a draft.
	*
	* @return <code>true</code> if this challenge is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challenge.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge is expired.
	*
	* @return <code>true</code> if this challenge is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challenge.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge is inactive.
	*
	* @return <code>true</code> if this challenge is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challenge.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge is incomplete.
	*
	* @return <code>true</code> if this challenge is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challenge.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge is pending.
	*
	* @return <code>true</code> if this challenge is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challenge.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge is scheduled.
	*
	* @return <code>true</code> if this challenge is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challenge.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challenge.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challenge.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challenge.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challenge.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challenge.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challenge.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challenge.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challenge.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challenge.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challenge.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challenge.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challenge.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challenge.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challenge.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challenge.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeWrapper((Challenge)_challenge.clone());
	}

	@Override
	public int compareTo(kisti.edison.challenge.model.Challenge challenge) {
		return _challenge.compareTo(challenge);
	}

	@Override
	public int hashCode() {
		return _challenge.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.Challenge> toCacheModel() {
		return _challenge.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.Challenge toEscapedModel() {
		return new ChallengeWrapper(_challenge.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.Challenge toUnescapedModel() {
		return new ChallengeWrapper(_challenge.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challenge.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challenge.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challenge.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeWrapper)) {
			return false;
		}

		ChallengeWrapper challengeWrapper = (ChallengeWrapper)obj;

		if (Validator.equals(_challenge, challengeWrapper._challenge)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challenge.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Challenge getWrappedChallenge() {
		return _challenge;
	}

	@Override
	public Challenge getWrappedModel() {
		return _challenge;
	}

	@Override
	public void resetOriginalValues() {
		_challenge.resetOriginalValues();
	}

	private Challenge _challenge;
}