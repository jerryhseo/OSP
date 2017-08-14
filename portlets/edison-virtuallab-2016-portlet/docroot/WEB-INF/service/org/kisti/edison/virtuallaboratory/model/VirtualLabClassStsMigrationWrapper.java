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

package org.kisti.edison.virtuallaboratory.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VirtualLabClassStsMigration}.
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStsMigration
 * @generated
 */
public class VirtualLabClassStsMigrationWrapper
	implements VirtualLabClassStsMigration,
		ModelWrapper<VirtualLabClassStsMigration> {
	public VirtualLabClassStsMigrationWrapper(
		VirtualLabClassStsMigration virtualLabClassStsMigration) {
		_virtualLabClassStsMigration = virtualLabClassStsMigration;
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabClassStsMigration.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabClassStsMigration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("groupId", getGroupId());
		attributes.put("virtualLabId", getVirtualLabId());
		attributes.put("classId", getClassId());
		attributes.put("universityField", getUniversityField());
		attributes.put("virtualLabTitle", getVirtualLabTitle());
		attributes.put("virtualLabPersonName", getVirtualLabPersonName());
		attributes.put("classTitle", getClassTitle());
		attributes.put("virtualClassCd", getVirtualClassCd());
		attributes.put("classCreateDt", getClassCreateDt());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("scienceAppName", getScienceAppName());
		attributes.put("registerStudentCnt", getRegisterStudentCnt());
		attributes.put("executeCount", getExecuteCount());
		attributes.put("executeStudentcount", getExecuteStudentcount());
		attributes.put("avgerageRuntime", getAvgerageRuntime());
		attributes.put("cputime", getCputime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		String universityField = (String)attributes.get("universityField");

		if (universityField != null) {
			setUniversityField(universityField);
		}

		String virtualLabTitle = (String)attributes.get("virtualLabTitle");

		if (virtualLabTitle != null) {
			setVirtualLabTitle(virtualLabTitle);
		}

		String virtualLabPersonName = (String)attributes.get(
				"virtualLabPersonName");

		if (virtualLabPersonName != null) {
			setVirtualLabPersonName(virtualLabPersonName);
		}

		String classTitle = (String)attributes.get("classTitle");

		if (classTitle != null) {
			setClassTitle(classTitle);
		}

		String virtualClassCd = (String)attributes.get("virtualClassCd");

		if (virtualClassCd != null) {
			setVirtualClassCd(virtualClassCd);
		}

		Date classCreateDt = (Date)attributes.get("classCreateDt");

		if (classCreateDt != null) {
			setClassCreateDt(classCreateDt);
		}

		String scienceAppId = (String)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String scienceAppName = (String)attributes.get("scienceAppName");

		if (scienceAppName != null) {
			setScienceAppName(scienceAppName);
		}

		Long registerStudentCnt = (Long)attributes.get("registerStudentCnt");

		if (registerStudentCnt != null) {
			setRegisterStudentCnt(registerStudentCnt);
		}

		Long executeCount = (Long)attributes.get("executeCount");

		if (executeCount != null) {
			setExecuteCount(executeCount);
		}

		Long executeStudentcount = (Long)attributes.get("executeStudentcount");

		if (executeStudentcount != null) {
			setExecuteStudentcount(executeStudentcount);
		}

		Long avgerageRuntime = (Long)attributes.get("avgerageRuntime");

		if (avgerageRuntime != null) {
			setAvgerageRuntime(avgerageRuntime);
		}

		Long cputime = (Long)attributes.get("cputime");

		if (cputime != null) {
			setCputime(cputime);
		}
	}

	/**
	* Returns the primary key of this virtual lab class sts migration.
	*
	* @return the primary key of this virtual lab class sts migration
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK getPrimaryKey() {
		return _virtualLabClassStsMigration.getPrimaryKey();
	}

	/**
	* Sets the primary key of this virtual lab class sts migration.
	*
	* @param primaryKey the primary key of this virtual lab class sts migration
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK primaryKey) {
		_virtualLabClassStsMigration.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the group ID of this virtual lab class sts migration.
	*
	* @return the group ID of this virtual lab class sts migration
	*/
	@Override
	public long getGroupId() {
		return _virtualLabClassStsMigration.getGroupId();
	}

	/**
	* Sets the group ID of this virtual lab class sts migration.
	*
	* @param groupId the group ID of this virtual lab class sts migration
	*/
	@Override
	public void setGroupId(long groupId) {
		_virtualLabClassStsMigration.setGroupId(groupId);
	}

	/**
	* Returns the virtual lab ID of this virtual lab class sts migration.
	*
	* @return the virtual lab ID of this virtual lab class sts migration
	*/
	@Override
	public long getVirtualLabId() {
		return _virtualLabClassStsMigration.getVirtualLabId();
	}

	/**
	* Sets the virtual lab ID of this virtual lab class sts migration.
	*
	* @param virtualLabId the virtual lab ID of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabClassStsMigration.setVirtualLabId(virtualLabId);
	}

	/**
	* Returns the class ID of this virtual lab class sts migration.
	*
	* @return the class ID of this virtual lab class sts migration
	*/
	@Override
	public long getClassId() {
		return _virtualLabClassStsMigration.getClassId();
	}

	/**
	* Sets the class ID of this virtual lab class sts migration.
	*
	* @param classId the class ID of this virtual lab class sts migration
	*/
	@Override
	public void setClassId(long classId) {
		_virtualLabClassStsMigration.setClassId(classId);
	}

	/**
	* Returns the university field of this virtual lab class sts migration.
	*
	* @return the university field of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getUniversityField() {
		return _virtualLabClassStsMigration.getUniversityField();
	}

	/**
	* Sets the university field of this virtual lab class sts migration.
	*
	* @param universityField the university field of this virtual lab class sts migration
	*/
	@Override
	public void setUniversityField(java.lang.String universityField) {
		_virtualLabClassStsMigration.setUniversityField(universityField);
	}

	/**
	* Returns the virtual lab title of this virtual lab class sts migration.
	*
	* @return the virtual lab title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabTitle() {
		return _virtualLabClassStsMigration.getVirtualLabTitle();
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized virtual lab title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.util.Locale locale) {
		return _virtualLabClassStsMigration.getVirtualLabTitle(locale);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab title of this virtual lab class sts migration. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.util.Locale locale,
		boolean useDefault) {
		return _virtualLabClassStsMigration.getVirtualLabTitle(locale,
			useDefault);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized virtual lab title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.lang.String languageId) {
		return _virtualLabClassStsMigration.getVirtualLabTitle(languageId);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.lang.String languageId,
		boolean useDefault) {
		return _virtualLabClassStsMigration.getVirtualLabTitle(languageId,
			useDefault);
	}

	@Override
	public java.lang.String getVirtualLabTitleCurrentLanguageId() {
		return _virtualLabClassStsMigration.getVirtualLabTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getVirtualLabTitleCurrentValue() {
		return _virtualLabClassStsMigration.getVirtualLabTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized virtual lab titles of this virtual lab class sts migration.
	*
	* @return the locales and localized virtual lab titles of this virtual lab class sts migration
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getVirtualLabTitleMap() {
		return _virtualLabClassStsMigration.getVirtualLabTitleMap();
	}

	/**
	* Sets the virtual lab title of this virtual lab class sts migration.
	*
	* @param virtualLabTitle the virtual lab title of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle) {
		_virtualLabClassStsMigration.setVirtualLabTitle(virtualLabTitle);
	}

	/**
	* Sets the localized virtual lab title of this virtual lab class sts migration in the language.
	*
	* @param virtualLabTitle the localized virtual lab title of this virtual lab class sts migration
	* @param locale the locale of the language
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle,
		java.util.Locale locale) {
		_virtualLabClassStsMigration.setVirtualLabTitle(virtualLabTitle, locale);
	}

	/**
	* Sets the localized virtual lab title of this virtual lab class sts migration in the language, and sets the default locale.
	*
	* @param virtualLabTitle the localized virtual lab title of this virtual lab class sts migration
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setVirtualLabTitle(virtualLabTitle,
			locale, defaultLocale);
	}

	@Override
	public void setVirtualLabTitleCurrentLanguageId(java.lang.String languageId) {
		_virtualLabClassStsMigration.setVirtualLabTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized virtual lab titles of this virtual lab class sts migration from the map of locales and localized virtual lab titles.
	*
	* @param virtualLabTitleMap the locales and localized virtual lab titles of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualLabTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabTitleMap) {
		_virtualLabClassStsMigration.setVirtualLabTitleMap(virtualLabTitleMap);
	}

	/**
	* Sets the localized virtual lab titles of this virtual lab class sts migration from the map of locales and localized virtual lab titles, and sets the default locale.
	*
	* @param virtualLabTitleMap the locales and localized virtual lab titles of this virtual lab class sts migration
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabTitleMap,
		java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setVirtualLabTitleMap(virtualLabTitleMap,
			defaultLocale);
	}

	/**
	* Returns the virtual lab person name of this virtual lab class sts migration.
	*
	* @return the virtual lab person name of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabPersonName() {
		return _virtualLabClassStsMigration.getVirtualLabPersonName();
	}

	/**
	* Returns the localized virtual lab person name of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized virtual lab person name of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabPersonName(java.util.Locale locale) {
		return _virtualLabClassStsMigration.getVirtualLabPersonName(locale);
	}

	/**
	* Returns the localized virtual lab person name of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab person name of this virtual lab class sts migration. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getVirtualLabPersonName(java.util.Locale locale,
		boolean useDefault) {
		return _virtualLabClassStsMigration.getVirtualLabPersonName(locale,
			useDefault);
	}

	/**
	* Returns the localized virtual lab person name of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized virtual lab person name of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabPersonName(java.lang.String languageId) {
		return _virtualLabClassStsMigration.getVirtualLabPersonName(languageId);
	}

	/**
	* Returns the localized virtual lab person name of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab person name of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualLabPersonName(
		java.lang.String languageId, boolean useDefault) {
		return _virtualLabClassStsMigration.getVirtualLabPersonName(languageId,
			useDefault);
	}

	@Override
	public java.lang.String getVirtualLabPersonNameCurrentLanguageId() {
		return _virtualLabClassStsMigration.getVirtualLabPersonNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getVirtualLabPersonNameCurrentValue() {
		return _virtualLabClassStsMigration.getVirtualLabPersonNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized virtual lab person names of this virtual lab class sts migration.
	*
	* @return the locales and localized virtual lab person names of this virtual lab class sts migration
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getVirtualLabPersonNameMap() {
		return _virtualLabClassStsMigration.getVirtualLabPersonNameMap();
	}

	/**
	* Sets the virtual lab person name of this virtual lab class sts migration.
	*
	* @param virtualLabPersonName the virtual lab person name of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualLabPersonName(java.lang.String virtualLabPersonName) {
		_virtualLabClassStsMigration.setVirtualLabPersonName(virtualLabPersonName);
	}

	/**
	* Sets the localized virtual lab person name of this virtual lab class sts migration in the language.
	*
	* @param virtualLabPersonName the localized virtual lab person name of this virtual lab class sts migration
	* @param locale the locale of the language
	*/
	@Override
	public void setVirtualLabPersonName(java.lang.String virtualLabPersonName,
		java.util.Locale locale) {
		_virtualLabClassStsMigration.setVirtualLabPersonName(virtualLabPersonName,
			locale);
	}

	/**
	* Sets the localized virtual lab person name of this virtual lab class sts migration in the language, and sets the default locale.
	*
	* @param virtualLabPersonName the localized virtual lab person name of this virtual lab class sts migration
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabPersonName(java.lang.String virtualLabPersonName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setVirtualLabPersonName(virtualLabPersonName,
			locale, defaultLocale);
	}

	@Override
	public void setVirtualLabPersonNameCurrentLanguageId(
		java.lang.String languageId) {
		_virtualLabClassStsMigration.setVirtualLabPersonNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized virtual lab person names of this virtual lab class sts migration from the map of locales and localized virtual lab person names.
	*
	* @param virtualLabPersonNameMap the locales and localized virtual lab person names of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualLabPersonNameMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabPersonNameMap) {
		_virtualLabClassStsMigration.setVirtualLabPersonNameMap(virtualLabPersonNameMap);
	}

	/**
	* Sets the localized virtual lab person names of this virtual lab class sts migration from the map of locales and localized virtual lab person names, and sets the default locale.
	*
	* @param virtualLabPersonNameMap the locales and localized virtual lab person names of this virtual lab class sts migration
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabPersonNameMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabPersonNameMap,
		java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setVirtualLabPersonNameMap(virtualLabPersonNameMap,
			defaultLocale);
	}

	/**
	* Returns the class title of this virtual lab class sts migration.
	*
	* @return the class title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getClassTitle() {
		return _virtualLabClassStsMigration.getClassTitle();
	}

	/**
	* Returns the localized class title of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized class title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getClassTitle(java.util.Locale locale) {
		return _virtualLabClassStsMigration.getClassTitle(locale);
	}

	/**
	* Returns the localized class title of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized class title of this virtual lab class sts migration. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getClassTitle(java.util.Locale locale,
		boolean useDefault) {
		return _virtualLabClassStsMigration.getClassTitle(locale, useDefault);
	}

	/**
	* Returns the localized class title of this virtual lab class sts migration in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized class title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getClassTitle(java.lang.String languageId) {
		return _virtualLabClassStsMigration.getClassTitle(languageId);
	}

	/**
	* Returns the localized class title of this virtual lab class sts migration in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized class title of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getClassTitle(java.lang.String languageId,
		boolean useDefault) {
		return _virtualLabClassStsMigration.getClassTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getClassTitleCurrentLanguageId() {
		return _virtualLabClassStsMigration.getClassTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getClassTitleCurrentValue() {
		return _virtualLabClassStsMigration.getClassTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized class titles of this virtual lab class sts migration.
	*
	* @return the locales and localized class titles of this virtual lab class sts migration
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getClassTitleMap() {
		return _virtualLabClassStsMigration.getClassTitleMap();
	}

	/**
	* Sets the class title of this virtual lab class sts migration.
	*
	* @param classTitle the class title of this virtual lab class sts migration
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle) {
		_virtualLabClassStsMigration.setClassTitle(classTitle);
	}

	/**
	* Sets the localized class title of this virtual lab class sts migration in the language.
	*
	* @param classTitle the localized class title of this virtual lab class sts migration
	* @param locale the locale of the language
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle,
		java.util.Locale locale) {
		_virtualLabClassStsMigration.setClassTitle(classTitle, locale);
	}

	/**
	* Sets the localized class title of this virtual lab class sts migration in the language, and sets the default locale.
	*
	* @param classTitle the localized class title of this virtual lab class sts migration
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setClassTitle(classTitle, locale,
			defaultLocale);
	}

	@Override
	public void setClassTitleCurrentLanguageId(java.lang.String languageId) {
		_virtualLabClassStsMigration.setClassTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized class titles of this virtual lab class sts migration from the map of locales and localized class titles.
	*
	* @param classTitleMap the locales and localized class titles of this virtual lab class sts migration
	*/
	@Override
	public void setClassTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> classTitleMap) {
		_virtualLabClassStsMigration.setClassTitleMap(classTitleMap);
	}

	/**
	* Sets the localized class titles of this virtual lab class sts migration from the map of locales and localized class titles, and sets the default locale.
	*
	* @param classTitleMap the locales and localized class titles of this virtual lab class sts migration
	* @param defaultLocale the default locale
	*/
	@Override
	public void setClassTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> classTitleMap,
		java.util.Locale defaultLocale) {
		_virtualLabClassStsMigration.setClassTitleMap(classTitleMap,
			defaultLocale);
	}

	/**
	* Returns the virtual class cd of this virtual lab class sts migration.
	*
	* @return the virtual class cd of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getVirtualClassCd() {
		return _virtualLabClassStsMigration.getVirtualClassCd();
	}

	/**
	* Sets the virtual class cd of this virtual lab class sts migration.
	*
	* @param virtualClassCd the virtual class cd of this virtual lab class sts migration
	*/
	@Override
	public void setVirtualClassCd(java.lang.String virtualClassCd) {
		_virtualLabClassStsMigration.setVirtualClassCd(virtualClassCd);
	}

	/**
	* Returns the class create dt of this virtual lab class sts migration.
	*
	* @return the class create dt of this virtual lab class sts migration
	*/
	@Override
	public java.util.Date getClassCreateDt() {
		return _virtualLabClassStsMigration.getClassCreateDt();
	}

	/**
	* Sets the class create dt of this virtual lab class sts migration.
	*
	* @param classCreateDt the class create dt of this virtual lab class sts migration
	*/
	@Override
	public void setClassCreateDt(java.util.Date classCreateDt) {
		_virtualLabClassStsMigration.setClassCreateDt(classCreateDt);
	}

	/**
	* Returns the science app ID of this virtual lab class sts migration.
	*
	* @return the science app ID of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getScienceAppId() {
		return _virtualLabClassStsMigration.getScienceAppId();
	}

	/**
	* Sets the science app ID of this virtual lab class sts migration.
	*
	* @param scienceAppId the science app ID of this virtual lab class sts migration
	*/
	@Override
	public void setScienceAppId(java.lang.String scienceAppId) {
		_virtualLabClassStsMigration.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the science app name of this virtual lab class sts migration.
	*
	* @return the science app name of this virtual lab class sts migration
	*/
	@Override
	public java.lang.String getScienceAppName() {
		return _virtualLabClassStsMigration.getScienceAppName();
	}

	/**
	* Sets the science app name of this virtual lab class sts migration.
	*
	* @param scienceAppName the science app name of this virtual lab class sts migration
	*/
	@Override
	public void setScienceAppName(java.lang.String scienceAppName) {
		_virtualLabClassStsMigration.setScienceAppName(scienceAppName);
	}

	/**
	* Returns the register student cnt of this virtual lab class sts migration.
	*
	* @return the register student cnt of this virtual lab class sts migration
	*/
	@Override
	public long getRegisterStudentCnt() {
		return _virtualLabClassStsMigration.getRegisterStudentCnt();
	}

	/**
	* Sets the register student cnt of this virtual lab class sts migration.
	*
	* @param registerStudentCnt the register student cnt of this virtual lab class sts migration
	*/
	@Override
	public void setRegisterStudentCnt(long registerStudentCnt) {
		_virtualLabClassStsMigration.setRegisterStudentCnt(registerStudentCnt);
	}

	/**
	* Returns the execute count of this virtual lab class sts migration.
	*
	* @return the execute count of this virtual lab class sts migration
	*/
	@Override
	public long getExecuteCount() {
		return _virtualLabClassStsMigration.getExecuteCount();
	}

	/**
	* Sets the execute count of this virtual lab class sts migration.
	*
	* @param executeCount the execute count of this virtual lab class sts migration
	*/
	@Override
	public void setExecuteCount(long executeCount) {
		_virtualLabClassStsMigration.setExecuteCount(executeCount);
	}

	/**
	* Returns the execute studentcount of this virtual lab class sts migration.
	*
	* @return the execute studentcount of this virtual lab class sts migration
	*/
	@Override
	public long getExecuteStudentcount() {
		return _virtualLabClassStsMigration.getExecuteStudentcount();
	}

	/**
	* Sets the execute studentcount of this virtual lab class sts migration.
	*
	* @param executeStudentcount the execute studentcount of this virtual lab class sts migration
	*/
	@Override
	public void setExecuteStudentcount(long executeStudentcount) {
		_virtualLabClassStsMigration.setExecuteStudentcount(executeStudentcount);
	}

	/**
	* Returns the avgerage runtime of this virtual lab class sts migration.
	*
	* @return the avgerage runtime of this virtual lab class sts migration
	*/
	@Override
	public long getAvgerageRuntime() {
		return _virtualLabClassStsMigration.getAvgerageRuntime();
	}

	/**
	* Sets the avgerage runtime of this virtual lab class sts migration.
	*
	* @param avgerageRuntime the avgerage runtime of this virtual lab class sts migration
	*/
	@Override
	public void setAvgerageRuntime(long avgerageRuntime) {
		_virtualLabClassStsMigration.setAvgerageRuntime(avgerageRuntime);
	}

	/**
	* Returns the cputime of this virtual lab class sts migration.
	*
	* @return the cputime of this virtual lab class sts migration
	*/
	@Override
	public long getCputime() {
		return _virtualLabClassStsMigration.getCputime();
	}

	/**
	* Sets the cputime of this virtual lab class sts migration.
	*
	* @param cputime the cputime of this virtual lab class sts migration
	*/
	@Override
	public void setCputime(long cputime) {
		_virtualLabClassStsMigration.setCputime(cputime);
	}

	@Override
	public boolean isNew() {
		return _virtualLabClassStsMigration.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_virtualLabClassStsMigration.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _virtualLabClassStsMigration.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_virtualLabClassStsMigration.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _virtualLabClassStsMigration.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _virtualLabClassStsMigration.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_virtualLabClassStsMigration.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _virtualLabClassStsMigration.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_virtualLabClassStsMigration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_virtualLabClassStsMigration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_virtualLabClassStsMigration.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _virtualLabClassStsMigration.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _virtualLabClassStsMigration.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_virtualLabClassStsMigration.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_virtualLabClassStsMigration.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new VirtualLabClassStsMigrationWrapper((VirtualLabClassStsMigration)_virtualLabClassStsMigration.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration) {
		return _virtualLabClassStsMigration.compareTo(virtualLabClassStsMigration);
	}

	@Override
	public int hashCode() {
		return _virtualLabClassStsMigration.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> toCacheModel() {
		return _virtualLabClassStsMigration.toCacheModel();
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration toEscapedModel() {
		return new VirtualLabClassStsMigrationWrapper(_virtualLabClassStsMigration.toEscapedModel());
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration toUnescapedModel() {
		return new VirtualLabClassStsMigrationWrapper(_virtualLabClassStsMigration.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _virtualLabClassStsMigration.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _virtualLabClassStsMigration.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_virtualLabClassStsMigration.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabClassStsMigrationWrapper)) {
			return false;
		}

		VirtualLabClassStsMigrationWrapper virtualLabClassStsMigrationWrapper = (VirtualLabClassStsMigrationWrapper)obj;

		if (Validator.equals(_virtualLabClassStsMigration,
					virtualLabClassStsMigrationWrapper._virtualLabClassStsMigration)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public VirtualLabClassStsMigration getWrappedVirtualLabClassStsMigration() {
		return _virtualLabClassStsMigration;
	}

	@Override
	public VirtualLabClassStsMigration getWrappedModel() {
		return _virtualLabClassStsMigration;
	}

	@Override
	public void resetOriginalValues() {
		_virtualLabClassStsMigration.resetOriginalValues();
	}

	private VirtualLabClassStsMigration _virtualLabClassStsMigration;
}