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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Challenge}.
 * </p>
 *
 * @author kyj
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

		attributes.put("challengeid", getChallengeid());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long challengeid = (Long)attributes.get("challengeid");

		if (challengeid != null) {
			setChallengeid(challengeid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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
	* Returns the challengeid of this challenge.
	*
	* @return the challengeid of this challenge
	*/
	@Override
	public long getChallengeid() {
		return _challenge.getChallengeid();
	}

	/**
	* Sets the challengeid of this challenge.
	*
	* @param challengeid the challengeid of this challenge
	*/
	@Override
	public void setChallengeid(long challengeid) {
		_challenge.setChallengeid(challengeid);
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
	public int compareTo(
		edison.challenge.service.builder.model.Challenge challenge) {
		return _challenge.compareTo(challenge);
	}

	@Override
	public int hashCode() {
		return _challenge.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Challenge> toCacheModel() {
		return _challenge.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Challenge toEscapedModel() {
		return new ChallengeWrapper(_challenge.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Challenge toUnescapedModel() {
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