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
 * This class is a wrapper for {@link ChallengeMember}.
 * </p>
 *
 * @author kyj
 * @see ChallengeMember
 * @generated
 */
public class ChallengeMemberWrapper implements ChallengeMember,
	ModelWrapper<ChallengeMember> {
	public ChallengeMemberWrapper(ChallengeMember challengeMember) {
		_challengeMember = challengeMember;
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeMember.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeMember.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("chMemberid", getChMemberid());
		attributes.put("fullName", getFullName());
		attributes.put("screenName", getScreenName());
		attributes.put("email", getEmail());
		attributes.put("leader", getLeader());
		attributes.put("falculty", getFalculty());
		attributes.put("major", getMajor());
		attributes.put("grade", getGrade());
		attributes.put("chTeamid", getChTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long chMemberid = (Long)attributes.get("chMemberid");

		if (chMemberid != null) {
			setChMemberid(chMemberid);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Boolean leader = (Boolean)attributes.get("leader");

		if (leader != null) {
			setLeader(leader);
		}

		String falculty = (String)attributes.get("falculty");

		if (falculty != null) {
			setFalculty(falculty);
		}

		String major = (String)attributes.get("major");

		if (major != null) {
			setMajor(major);
		}

		String grade = (String)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}
	}

	/**
	* Returns the primary key of this challenge member.
	*
	* @return the primary key of this challenge member
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeMember.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge member.
	*
	* @param primaryKey the primary key of this challenge member
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeMember.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ch memberid of this challenge member.
	*
	* @return the ch memberid of this challenge member
	*/
	@Override
	public long getChMemberid() {
		return _challengeMember.getChMemberid();
	}

	/**
	* Sets the ch memberid of this challenge member.
	*
	* @param chMemberid the ch memberid of this challenge member
	*/
	@Override
	public void setChMemberid(long chMemberid) {
		_challengeMember.setChMemberid(chMemberid);
	}

	/**
	* Returns the full name of this challenge member.
	*
	* @return the full name of this challenge member
	*/
	@Override
	public java.lang.String getFullName() {
		return _challengeMember.getFullName();
	}

	/**
	* Returns the localized full name of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized full name of this challenge member
	*/
	@Override
	public java.lang.String getFullName(java.util.Locale locale) {
		return _challengeMember.getFullName(locale);
	}

	/**
	* Returns the localized full name of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full name of this challenge member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFullName(java.util.Locale locale,
		boolean useDefault) {
		return _challengeMember.getFullName(locale, useDefault);
	}

	/**
	* Returns the localized full name of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized full name of this challenge member
	*/
	@Override
	public java.lang.String getFullName(java.lang.String languageId) {
		return _challengeMember.getFullName(languageId);
	}

	/**
	* Returns the localized full name of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized full name of this challenge member
	*/
	@Override
	public java.lang.String getFullName(java.lang.String languageId,
		boolean useDefault) {
		return _challengeMember.getFullName(languageId, useDefault);
	}

	@Override
	public java.lang.String getFullNameCurrentLanguageId() {
		return _challengeMember.getFullNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getFullNameCurrentValue() {
		return _challengeMember.getFullNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized full names of this challenge member.
	*
	* @return the locales and localized full names of this challenge member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getFullNameMap() {
		return _challengeMember.getFullNameMap();
	}

	/**
	* Sets the full name of this challenge member.
	*
	* @param fullName the full name of this challenge member
	*/
	@Override
	public void setFullName(java.lang.String fullName) {
		_challengeMember.setFullName(fullName);
	}

	/**
	* Sets the localized full name of this challenge member in the language.
	*
	* @param fullName the localized full name of this challenge member
	* @param locale the locale of the language
	*/
	@Override
	public void setFullName(java.lang.String fullName, java.util.Locale locale) {
		_challengeMember.setFullName(fullName, locale);
	}

	/**
	* Sets the localized full name of this challenge member in the language, and sets the default locale.
	*
	* @param fullName the localized full name of this challenge member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullName(java.lang.String fullName, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeMember.setFullName(fullName, locale, defaultLocale);
	}

	@Override
	public void setFullNameCurrentLanguageId(java.lang.String languageId) {
		_challengeMember.setFullNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized full names of this challenge member from the map of locales and localized full names.
	*
	* @param fullNameMap the locales and localized full names of this challenge member
	*/
	@Override
	public void setFullNameMap(
		java.util.Map<java.util.Locale, java.lang.String> fullNameMap) {
		_challengeMember.setFullNameMap(fullNameMap);
	}

	/**
	* Sets the localized full names of this challenge member from the map of locales and localized full names, and sets the default locale.
	*
	* @param fullNameMap the locales and localized full names of this challenge member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFullNameMap(
		java.util.Map<java.util.Locale, java.lang.String> fullNameMap,
		java.util.Locale defaultLocale) {
		_challengeMember.setFullNameMap(fullNameMap, defaultLocale);
	}

	/**
	* Returns the screen name of this challenge member.
	*
	* @return the screen name of this challenge member
	*/
	@Override
	public java.lang.String getScreenName() {
		return _challengeMember.getScreenName();
	}

	/**
	* Returns the localized screen name of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized screen name of this challenge member
	*/
	@Override
	public java.lang.String getScreenName(java.util.Locale locale) {
		return _challengeMember.getScreenName(locale);
	}

	/**
	* Returns the localized screen name of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized screen name of this challenge member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getScreenName(java.util.Locale locale,
		boolean useDefault) {
		return _challengeMember.getScreenName(locale, useDefault);
	}

	/**
	* Returns the localized screen name of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized screen name of this challenge member
	*/
	@Override
	public java.lang.String getScreenName(java.lang.String languageId) {
		return _challengeMember.getScreenName(languageId);
	}

	/**
	* Returns the localized screen name of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized screen name of this challenge member
	*/
	@Override
	public java.lang.String getScreenName(java.lang.String languageId,
		boolean useDefault) {
		return _challengeMember.getScreenName(languageId, useDefault);
	}

	@Override
	public java.lang.String getScreenNameCurrentLanguageId() {
		return _challengeMember.getScreenNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getScreenNameCurrentValue() {
		return _challengeMember.getScreenNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized screen names of this challenge member.
	*
	* @return the locales and localized screen names of this challenge member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getScreenNameMap() {
		return _challengeMember.getScreenNameMap();
	}

	/**
	* Sets the screen name of this challenge member.
	*
	* @param screenName the screen name of this challenge member
	*/
	@Override
	public void setScreenName(java.lang.String screenName) {
		_challengeMember.setScreenName(screenName);
	}

	/**
	* Sets the localized screen name of this challenge member in the language.
	*
	* @param screenName the localized screen name of this challenge member
	* @param locale the locale of the language
	*/
	@Override
	public void setScreenName(java.lang.String screenName,
		java.util.Locale locale) {
		_challengeMember.setScreenName(screenName, locale);
	}

	/**
	* Sets the localized screen name of this challenge member in the language, and sets the default locale.
	*
	* @param screenName the localized screen name of this challenge member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setScreenName(java.lang.String screenName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeMember.setScreenName(screenName, locale, defaultLocale);
	}

	@Override
	public void setScreenNameCurrentLanguageId(java.lang.String languageId) {
		_challengeMember.setScreenNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized screen names of this challenge member from the map of locales and localized screen names.
	*
	* @param screenNameMap the locales and localized screen names of this challenge member
	*/
	@Override
	public void setScreenNameMap(
		java.util.Map<java.util.Locale, java.lang.String> screenNameMap) {
		_challengeMember.setScreenNameMap(screenNameMap);
	}

	/**
	* Sets the localized screen names of this challenge member from the map of locales and localized screen names, and sets the default locale.
	*
	* @param screenNameMap the locales and localized screen names of this challenge member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setScreenNameMap(
		java.util.Map<java.util.Locale, java.lang.String> screenNameMap,
		java.util.Locale defaultLocale) {
		_challengeMember.setScreenNameMap(screenNameMap, defaultLocale);
	}

	/**
	* Returns the email of this challenge member.
	*
	* @return the email of this challenge member
	*/
	@Override
	public java.lang.String getEmail() {
		return _challengeMember.getEmail();
	}

	/**
	* Sets the email of this challenge member.
	*
	* @param email the email of this challenge member
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_challengeMember.setEmail(email);
	}

	/**
	* Returns the leader of this challenge member.
	*
	* @return the leader of this challenge member
	*/
	@Override
	public boolean getLeader() {
		return _challengeMember.getLeader();
	}

	/**
	* Returns <code>true</code> if this challenge member is leader.
	*
	* @return <code>true</code> if this challenge member is leader; <code>false</code> otherwise
	*/
	@Override
	public boolean isLeader() {
		return _challengeMember.isLeader();
	}

	/**
	* Sets whether this challenge member is leader.
	*
	* @param leader the leader of this challenge member
	*/
	@Override
	public void setLeader(boolean leader) {
		_challengeMember.setLeader(leader);
	}

	/**
	* Returns the falculty of this challenge member.
	*
	* @return the falculty of this challenge member
	*/
	@Override
	public java.lang.String getFalculty() {
		return _challengeMember.getFalculty();
	}

	/**
	* Returns the localized falculty of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized falculty of this challenge member
	*/
	@Override
	public java.lang.String getFalculty(java.util.Locale locale) {
		return _challengeMember.getFalculty(locale);
	}

	/**
	* Returns the localized falculty of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized falculty of this challenge member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFalculty(java.util.Locale locale,
		boolean useDefault) {
		return _challengeMember.getFalculty(locale, useDefault);
	}

	/**
	* Returns the localized falculty of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized falculty of this challenge member
	*/
	@Override
	public java.lang.String getFalculty(java.lang.String languageId) {
		return _challengeMember.getFalculty(languageId);
	}

	/**
	* Returns the localized falculty of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized falculty of this challenge member
	*/
	@Override
	public java.lang.String getFalculty(java.lang.String languageId,
		boolean useDefault) {
		return _challengeMember.getFalculty(languageId, useDefault);
	}

	@Override
	public java.lang.String getFalcultyCurrentLanguageId() {
		return _challengeMember.getFalcultyCurrentLanguageId();
	}

	@Override
	public java.lang.String getFalcultyCurrentValue() {
		return _challengeMember.getFalcultyCurrentValue();
	}

	/**
	* Returns a map of the locales and localized falculties of this challenge member.
	*
	* @return the locales and localized falculties of this challenge member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getFalcultyMap() {
		return _challengeMember.getFalcultyMap();
	}

	/**
	* Sets the falculty of this challenge member.
	*
	* @param falculty the falculty of this challenge member
	*/
	@Override
	public void setFalculty(java.lang.String falculty) {
		_challengeMember.setFalculty(falculty);
	}

	/**
	* Sets the localized falculty of this challenge member in the language.
	*
	* @param falculty the localized falculty of this challenge member
	* @param locale the locale of the language
	*/
	@Override
	public void setFalculty(java.lang.String falculty, java.util.Locale locale) {
		_challengeMember.setFalculty(falculty, locale);
	}

	/**
	* Sets the localized falculty of this challenge member in the language, and sets the default locale.
	*
	* @param falculty the localized falculty of this challenge member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFalculty(java.lang.String falculty, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeMember.setFalculty(falculty, locale, defaultLocale);
	}

	@Override
	public void setFalcultyCurrentLanguageId(java.lang.String languageId) {
		_challengeMember.setFalcultyCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized falculties of this challenge member from the map of locales and localized falculties.
	*
	* @param falcultyMap the locales and localized falculties of this challenge member
	*/
	@Override
	public void setFalcultyMap(
		java.util.Map<java.util.Locale, java.lang.String> falcultyMap) {
		_challengeMember.setFalcultyMap(falcultyMap);
	}

	/**
	* Sets the localized falculties of this challenge member from the map of locales and localized falculties, and sets the default locale.
	*
	* @param falcultyMap the locales and localized falculties of this challenge member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFalcultyMap(
		java.util.Map<java.util.Locale, java.lang.String> falcultyMap,
		java.util.Locale defaultLocale) {
		_challengeMember.setFalcultyMap(falcultyMap, defaultLocale);
	}

	/**
	* Returns the major of this challenge member.
	*
	* @return the major of this challenge member
	*/
	@Override
	public java.lang.String getMajor() {
		return _challengeMember.getMajor();
	}

	/**
	* Returns the localized major of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized major of this challenge member
	*/
	@Override
	public java.lang.String getMajor(java.util.Locale locale) {
		return _challengeMember.getMajor(locale);
	}

	/**
	* Returns the localized major of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized major of this challenge member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getMajor(java.util.Locale locale, boolean useDefault) {
		return _challengeMember.getMajor(locale, useDefault);
	}

	/**
	* Returns the localized major of this challenge member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized major of this challenge member
	*/
	@Override
	public java.lang.String getMajor(java.lang.String languageId) {
		return _challengeMember.getMajor(languageId);
	}

	/**
	* Returns the localized major of this challenge member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized major of this challenge member
	*/
	@Override
	public java.lang.String getMajor(java.lang.String languageId,
		boolean useDefault) {
		return _challengeMember.getMajor(languageId, useDefault);
	}

	@Override
	public java.lang.String getMajorCurrentLanguageId() {
		return _challengeMember.getMajorCurrentLanguageId();
	}

	@Override
	public java.lang.String getMajorCurrentValue() {
		return _challengeMember.getMajorCurrentValue();
	}

	/**
	* Returns a map of the locales and localized majors of this challenge member.
	*
	* @return the locales and localized majors of this challenge member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getMajorMap() {
		return _challengeMember.getMajorMap();
	}

	/**
	* Sets the major of this challenge member.
	*
	* @param major the major of this challenge member
	*/
	@Override
	public void setMajor(java.lang.String major) {
		_challengeMember.setMajor(major);
	}

	/**
	* Sets the localized major of this challenge member in the language.
	*
	* @param major the localized major of this challenge member
	* @param locale the locale of the language
	*/
	@Override
	public void setMajor(java.lang.String major, java.util.Locale locale) {
		_challengeMember.setMajor(major, locale);
	}

	/**
	* Sets the localized major of this challenge member in the language, and sets the default locale.
	*
	* @param major the localized major of this challenge member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMajor(java.lang.String major, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeMember.setMajor(major, locale, defaultLocale);
	}

	@Override
	public void setMajorCurrentLanguageId(java.lang.String languageId) {
		_challengeMember.setMajorCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized majors of this challenge member from the map of locales and localized majors.
	*
	* @param majorMap the locales and localized majors of this challenge member
	*/
	@Override
	public void setMajorMap(
		java.util.Map<java.util.Locale, java.lang.String> majorMap) {
		_challengeMember.setMajorMap(majorMap);
	}

	/**
	* Sets the localized majors of this challenge member from the map of locales and localized majors, and sets the default locale.
	*
	* @param majorMap the locales and localized majors of this challenge member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMajorMap(
		java.util.Map<java.util.Locale, java.lang.String> majorMap,
		java.util.Locale defaultLocale) {
		_challengeMember.setMajorMap(majorMap, defaultLocale);
	}

	/**
	* Returns the grade of this challenge member.
	*
	* @return the grade of this challenge member
	*/
	@Override
	public java.lang.String getGrade() {
		return _challengeMember.getGrade();
	}

	/**
	* Sets the grade of this challenge member.
	*
	* @param grade the grade of this challenge member
	*/
	@Override
	public void setGrade(java.lang.String grade) {
		_challengeMember.setGrade(grade);
	}

	/**
	* Returns the ch teamid of this challenge member.
	*
	* @return the ch teamid of this challenge member
	*/
	@Override
	public long getChTeamid() {
		return _challengeMember.getChTeamid();
	}

	/**
	* Sets the ch teamid of this challenge member.
	*
	* @param chTeamid the ch teamid of this challenge member
	*/
	@Override
	public void setChTeamid(long chTeamid) {
		_challengeMember.setChTeamid(chTeamid);
	}

	@Override
	public boolean isNew() {
		return _challengeMember.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeMember.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeMember.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeMember.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeMember.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeMember.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeMember.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeMember.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeMember.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeMember.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeMember.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challengeMember.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challengeMember.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challengeMember.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challengeMember.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeMemberWrapper((ChallengeMember)_challengeMember.clone());
	}

	@Override
	public int compareTo(
		edison.challenge.service.builder.model.ChallengeMember challengeMember) {
		return _challengeMember.compareTo(challengeMember);
	}

	@Override
	public int hashCode() {
		return _challengeMember.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.ChallengeMember> toCacheModel() {
		return _challengeMember.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeMember toEscapedModel() {
		return new ChallengeMemberWrapper(_challengeMember.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeMember toUnescapedModel() {
		return new ChallengeMemberWrapper(_challengeMember.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeMember.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeMember.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeMember.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeMemberWrapper)) {
			return false;
		}

		ChallengeMemberWrapper challengeMemberWrapper = (ChallengeMemberWrapper)obj;

		if (Validator.equals(_challengeMember,
					challengeMemberWrapper._challengeMember)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeMember getWrappedChallengeMember() {
		return _challengeMember;
	}

	@Override
	public ChallengeMember getWrappedModel() {
		return _challengeMember;
	}

	@Override
	public void resetOriginalValues() {
		_challengeMember.resetOriginalValues();
	}

	private ChallengeMember _challengeMember;
}