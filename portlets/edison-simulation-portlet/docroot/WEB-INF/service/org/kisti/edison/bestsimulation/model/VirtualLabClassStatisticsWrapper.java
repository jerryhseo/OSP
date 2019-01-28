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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VirtualLabClassStatistics}.
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStatistics
 * @generated
 */
public class VirtualLabClassStatisticsWrapper
	implements VirtualLabClassStatistics,
		ModelWrapper<VirtualLabClassStatistics> {
	public VirtualLabClassStatisticsWrapper(
		VirtualLabClassStatistics virtualLabClassStatistics) {
		_virtualLabClassStatistics = virtualLabClassStatistics;
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabClassStatistics.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabClassStatistics.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("virtualLabId", getVirtualLabId());
		attributes.put("classId", getClassId());
		attributes.put("virtualLabTitle", getVirtualLabTitle());
		attributes.put("classTitle", getClassTitle());
		attributes.put("virtualLabPersonName", getVirtualLabPersonName());
		attributes.put("registerStudentCnt", getRegisterStudentCnt());
		attributes.put("virtualLabUsersId", getVirtualLabUsersId());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("executeUserCnt", getExecuteUserCnt());
		attributes.put("executeCnt", getExecuteCnt());
		attributes.put("cputime", getCputime());
		attributes.put("groupId", getGroupId());
		attributes.put("university", getUniversity());
		attributes.put("classCreateDt", getClassCreateDt());
		attributes.put("virtualLabUseYn", getVirtualLabUseYn());
		attributes.put("classUseYn", getClassUseYn());
		attributes.put("lastModifiedDt", getLastModifiedDt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}

		String classId = (String)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		String virtualLabTitle = (String)attributes.get("virtualLabTitle");

		if (virtualLabTitle != null) {
			setVirtualLabTitle(virtualLabTitle);
		}

		String classTitle = (String)attributes.get("classTitle");

		if (classTitle != null) {
			setClassTitle(classTitle);
		}

		String virtualLabPersonName = (String)attributes.get(
				"virtualLabPersonName");

		if (virtualLabPersonName != null) {
			setVirtualLabPersonName(virtualLabPersonName);
		}

		Long registerStudentCnt = (Long)attributes.get("registerStudentCnt");

		if (registerStudentCnt != null) {
			setRegisterStudentCnt(registerStudentCnt);
		}

		String virtualLabUsersId = (String)attributes.get("virtualLabUsersId");

		if (virtualLabUsersId != null) {
			setVirtualLabUsersId(virtualLabUsersId);
		}

		String scienceAppId = (String)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long executeUserCnt = (Long)attributes.get("executeUserCnt");

		if (executeUserCnt != null) {
			setExecuteUserCnt(executeUserCnt);
		}

		Long executeCnt = (Long)attributes.get("executeCnt");

		if (executeCnt != null) {
			setExecuteCnt(executeCnt);
		}

		String cputime = (String)attributes.get("cputime");

		if (cputime != null) {
			setCputime(cputime);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long university = (Long)attributes.get("university");

		if (university != null) {
			setUniversity(university);
		}

		Date classCreateDt = (Date)attributes.get("classCreateDt");

		if (classCreateDt != null) {
			setClassCreateDt(classCreateDt);
		}

		String virtualLabUseYn = (String)attributes.get("virtualLabUseYn");

		if (virtualLabUseYn != null) {
			setVirtualLabUseYn(virtualLabUseYn);
		}

		String classUseYn = (String)attributes.get("classUseYn");

		if (classUseYn != null) {
			setClassUseYn(classUseYn);
		}

		Date lastModifiedDt = (Date)attributes.get("lastModifiedDt");

		if (lastModifiedDt != null) {
			setLastModifiedDt(lastModifiedDt);
		}
	}

	/**
	* Returns the primary key of this virtual lab class statistics.
	*
	* @return the primary key of this virtual lab class statistics
	*/
	@Override
	public org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK getPrimaryKey() {
		return _virtualLabClassStatistics.getPrimaryKey();
	}

	/**
	* Sets the primary key of this virtual lab class statistics.
	*
	* @param primaryKey the primary key of this virtual lab class statistics
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK primaryKey) {
		_virtualLabClassStatistics.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the virtual lab ID of this virtual lab class statistics.
	*
	* @return the virtual lab ID of this virtual lab class statistics
	*/
	@Override
	public long getVirtualLabId() {
		return _virtualLabClassStatistics.getVirtualLabId();
	}

	/**
	* Sets the virtual lab ID of this virtual lab class statistics.
	*
	* @param virtualLabId the virtual lab ID of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabClassStatistics.setVirtualLabId(virtualLabId);
	}

	/**
	* Returns the class ID of this virtual lab class statistics.
	*
	* @return the class ID of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassId() {
		return _virtualLabClassStatistics.getClassId();
	}

	/**
	* Sets the class ID of this virtual lab class statistics.
	*
	* @param classId the class ID of this virtual lab class statistics
	*/
	@Override
	public void setClassId(java.lang.String classId) {
		_virtualLabClassStatistics.setClassId(classId);
	}

	/**
	* Returns the virtual lab title of this virtual lab class statistics.
	*
	* @return the virtual lab title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabTitle() {
		return _virtualLabClassStatistics.getVirtualLabTitle();
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class statistics in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized virtual lab title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.util.Locale locale) {
		return _virtualLabClassStatistics.getVirtualLabTitle(locale);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class statistics in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab title of this virtual lab class statistics. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.util.Locale locale,
		boolean useDefault) {
		return _virtualLabClassStatistics.getVirtualLabTitle(locale, useDefault);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class statistics in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized virtual lab title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.lang.String languageId) {
		return _virtualLabClassStatistics.getVirtualLabTitle(languageId);
	}

	/**
	* Returns the localized virtual lab title of this virtual lab class statistics in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized virtual lab title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabTitle(java.lang.String languageId,
		boolean useDefault) {
		return _virtualLabClassStatistics.getVirtualLabTitle(languageId,
			useDefault);
	}

	@Override
	public java.lang.String getVirtualLabTitleCurrentLanguageId() {
		return _virtualLabClassStatistics.getVirtualLabTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getVirtualLabTitleCurrentValue() {
		return _virtualLabClassStatistics.getVirtualLabTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized virtual lab titles of this virtual lab class statistics.
	*
	* @return the locales and localized virtual lab titles of this virtual lab class statistics
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getVirtualLabTitleMap() {
		return _virtualLabClassStatistics.getVirtualLabTitleMap();
	}

	/**
	* Sets the virtual lab title of this virtual lab class statistics.
	*
	* @param virtualLabTitle the virtual lab title of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle) {
		_virtualLabClassStatistics.setVirtualLabTitle(virtualLabTitle);
	}

	/**
	* Sets the localized virtual lab title of this virtual lab class statistics in the language.
	*
	* @param virtualLabTitle the localized virtual lab title of this virtual lab class statistics
	* @param locale the locale of the language
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle,
		java.util.Locale locale) {
		_virtualLabClassStatistics.setVirtualLabTitle(virtualLabTitle, locale);
	}

	/**
	* Sets the localized virtual lab title of this virtual lab class statistics in the language, and sets the default locale.
	*
	* @param virtualLabTitle the localized virtual lab title of this virtual lab class statistics
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabTitle(java.lang.String virtualLabTitle,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_virtualLabClassStatistics.setVirtualLabTitle(virtualLabTitle, locale,
			defaultLocale);
	}

	@Override
	public void setVirtualLabTitleCurrentLanguageId(java.lang.String languageId) {
		_virtualLabClassStatistics.setVirtualLabTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized virtual lab titles of this virtual lab class statistics from the map of locales and localized virtual lab titles.
	*
	* @param virtualLabTitleMap the locales and localized virtual lab titles of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabTitleMap) {
		_virtualLabClassStatistics.setVirtualLabTitleMap(virtualLabTitleMap);
	}

	/**
	* Sets the localized virtual lab titles of this virtual lab class statistics from the map of locales and localized virtual lab titles, and sets the default locale.
	*
	* @param virtualLabTitleMap the locales and localized virtual lab titles of this virtual lab class statistics
	* @param defaultLocale the default locale
	*/
	@Override
	public void setVirtualLabTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> virtualLabTitleMap,
		java.util.Locale defaultLocale) {
		_virtualLabClassStatistics.setVirtualLabTitleMap(virtualLabTitleMap,
			defaultLocale);
	}

	/**
	* Returns the class title of this virtual lab class statistics.
	*
	* @return the class title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassTitle() {
		return _virtualLabClassStatistics.getClassTitle();
	}

	/**
	* Returns the localized class title of this virtual lab class statistics in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized class title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassTitle(java.util.Locale locale) {
		return _virtualLabClassStatistics.getClassTitle(locale);
	}

	/**
	* Returns the localized class title of this virtual lab class statistics in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized class title of this virtual lab class statistics. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getClassTitle(java.util.Locale locale,
		boolean useDefault) {
		return _virtualLabClassStatistics.getClassTitle(locale, useDefault);
	}

	/**
	* Returns the localized class title of this virtual lab class statistics in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized class title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassTitle(java.lang.String languageId) {
		return _virtualLabClassStatistics.getClassTitle(languageId);
	}

	/**
	* Returns the localized class title of this virtual lab class statistics in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized class title of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassTitle(java.lang.String languageId,
		boolean useDefault) {
		return _virtualLabClassStatistics.getClassTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getClassTitleCurrentLanguageId() {
		return _virtualLabClassStatistics.getClassTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getClassTitleCurrentValue() {
		return _virtualLabClassStatistics.getClassTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized class titles of this virtual lab class statistics.
	*
	* @return the locales and localized class titles of this virtual lab class statistics
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getClassTitleMap() {
		return _virtualLabClassStatistics.getClassTitleMap();
	}

	/**
	* Sets the class title of this virtual lab class statistics.
	*
	* @param classTitle the class title of this virtual lab class statistics
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle) {
		_virtualLabClassStatistics.setClassTitle(classTitle);
	}

	/**
	* Sets the localized class title of this virtual lab class statistics in the language.
	*
	* @param classTitle the localized class title of this virtual lab class statistics
	* @param locale the locale of the language
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle,
		java.util.Locale locale) {
		_virtualLabClassStatistics.setClassTitle(classTitle, locale);
	}

	/**
	* Sets the localized class title of this virtual lab class statistics in the language, and sets the default locale.
	*
	* @param classTitle the localized class title of this virtual lab class statistics
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setClassTitle(java.lang.String classTitle,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_virtualLabClassStatistics.setClassTitle(classTitle, locale,
			defaultLocale);
	}

	@Override
	public void setClassTitleCurrentLanguageId(java.lang.String languageId) {
		_virtualLabClassStatistics.setClassTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized class titles of this virtual lab class statistics from the map of locales and localized class titles.
	*
	* @param classTitleMap the locales and localized class titles of this virtual lab class statistics
	*/
	@Override
	public void setClassTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> classTitleMap) {
		_virtualLabClassStatistics.setClassTitleMap(classTitleMap);
	}

	/**
	* Sets the localized class titles of this virtual lab class statistics from the map of locales and localized class titles, and sets the default locale.
	*
	* @param classTitleMap the locales and localized class titles of this virtual lab class statistics
	* @param defaultLocale the default locale
	*/
	@Override
	public void setClassTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> classTitleMap,
		java.util.Locale defaultLocale) {
		_virtualLabClassStatistics.setClassTitleMap(classTitleMap, defaultLocale);
	}

	/**
	* Returns the virtual lab person name of this virtual lab class statistics.
	*
	* @return the virtual lab person name of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabPersonName() {
		return _virtualLabClassStatistics.getVirtualLabPersonName();
	}

	/**
	* Sets the virtual lab person name of this virtual lab class statistics.
	*
	* @param virtualLabPersonName the virtual lab person name of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabPersonName(java.lang.String virtualLabPersonName) {
		_virtualLabClassStatistics.setVirtualLabPersonName(virtualLabPersonName);
	}

	/**
	* Returns the register student cnt of this virtual lab class statistics.
	*
	* @return the register student cnt of this virtual lab class statistics
	*/
	@Override
	public long getRegisterStudentCnt() {
		return _virtualLabClassStatistics.getRegisterStudentCnt();
	}

	/**
	* Sets the register student cnt of this virtual lab class statistics.
	*
	* @param registerStudentCnt the register student cnt of this virtual lab class statistics
	*/
	@Override
	public void setRegisterStudentCnt(long registerStudentCnt) {
		_virtualLabClassStatistics.setRegisterStudentCnt(registerStudentCnt);
	}

	/**
	* Returns the virtual lab users ID of this virtual lab class statistics.
	*
	* @return the virtual lab users ID of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabUsersId() {
		return _virtualLabClassStatistics.getVirtualLabUsersId();
	}

	/**
	* Sets the virtual lab users ID of this virtual lab class statistics.
	*
	* @param virtualLabUsersId the virtual lab users ID of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabUsersId(java.lang.String virtualLabUsersId) {
		_virtualLabClassStatistics.setVirtualLabUsersId(virtualLabUsersId);
	}

	/**
	* Returns the science app ID of this virtual lab class statistics.
	*
	* @return the science app ID of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getScienceAppId() {
		return _virtualLabClassStatistics.getScienceAppId();
	}

	/**
	* Sets the science app ID of this virtual lab class statistics.
	*
	* @param scienceAppId the science app ID of this virtual lab class statistics
	*/
	@Override
	public void setScienceAppId(java.lang.String scienceAppId) {
		_virtualLabClassStatistics.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the execute user cnt of this virtual lab class statistics.
	*
	* @return the execute user cnt of this virtual lab class statistics
	*/
	@Override
	public long getExecuteUserCnt() {
		return _virtualLabClassStatistics.getExecuteUserCnt();
	}

	/**
	* Sets the execute user cnt of this virtual lab class statistics.
	*
	* @param executeUserCnt the execute user cnt of this virtual lab class statistics
	*/
	@Override
	public void setExecuteUserCnt(long executeUserCnt) {
		_virtualLabClassStatistics.setExecuteUserCnt(executeUserCnt);
	}

	/**
	* Returns the execute cnt of this virtual lab class statistics.
	*
	* @return the execute cnt of this virtual lab class statistics
	*/
	@Override
	public long getExecuteCnt() {
		return _virtualLabClassStatistics.getExecuteCnt();
	}

	/**
	* Sets the execute cnt of this virtual lab class statistics.
	*
	* @param executeCnt the execute cnt of this virtual lab class statistics
	*/
	@Override
	public void setExecuteCnt(long executeCnt) {
		_virtualLabClassStatistics.setExecuteCnt(executeCnt);
	}

	/**
	* Returns the cputime of this virtual lab class statistics.
	*
	* @return the cputime of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getCputime() {
		return _virtualLabClassStatistics.getCputime();
	}

	/**
	* Sets the cputime of this virtual lab class statistics.
	*
	* @param cputime the cputime of this virtual lab class statistics
	*/
	@Override
	public void setCputime(java.lang.String cputime) {
		_virtualLabClassStatistics.setCputime(cputime);
	}

	/**
	* Returns the group ID of this virtual lab class statistics.
	*
	* @return the group ID of this virtual lab class statistics
	*/
	@Override
	public long getGroupId() {
		return _virtualLabClassStatistics.getGroupId();
	}

	/**
	* Sets the group ID of this virtual lab class statistics.
	*
	* @param groupId the group ID of this virtual lab class statistics
	*/
	@Override
	public void setGroupId(long groupId) {
		_virtualLabClassStatistics.setGroupId(groupId);
	}

	/**
	* Returns the university of this virtual lab class statistics.
	*
	* @return the university of this virtual lab class statistics
	*/
	@Override
	public long getUniversity() {
		return _virtualLabClassStatistics.getUniversity();
	}

	/**
	* Sets the university of this virtual lab class statistics.
	*
	* @param university the university of this virtual lab class statistics
	*/
	@Override
	public void setUniversity(long university) {
		_virtualLabClassStatistics.setUniversity(university);
	}

	/**
	* Returns the class create dt of this virtual lab class statistics.
	*
	* @return the class create dt of this virtual lab class statistics
	*/
	@Override
	public java.util.Date getClassCreateDt() {
		return _virtualLabClassStatistics.getClassCreateDt();
	}

	/**
	* Sets the class create dt of this virtual lab class statistics.
	*
	* @param classCreateDt the class create dt of this virtual lab class statistics
	*/
	@Override
	public void setClassCreateDt(java.util.Date classCreateDt) {
		_virtualLabClassStatistics.setClassCreateDt(classCreateDt);
	}

	/**
	* Returns the virtual lab use yn of this virtual lab class statistics.
	*
	* @return the virtual lab use yn of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getVirtualLabUseYn() {
		return _virtualLabClassStatistics.getVirtualLabUseYn();
	}

	/**
	* Sets the virtual lab use yn of this virtual lab class statistics.
	*
	* @param virtualLabUseYn the virtual lab use yn of this virtual lab class statistics
	*/
	@Override
	public void setVirtualLabUseYn(java.lang.String virtualLabUseYn) {
		_virtualLabClassStatistics.setVirtualLabUseYn(virtualLabUseYn);
	}

	/**
	* Returns the class use yn of this virtual lab class statistics.
	*
	* @return the class use yn of this virtual lab class statistics
	*/
	@Override
	public java.lang.String getClassUseYn() {
		return _virtualLabClassStatistics.getClassUseYn();
	}

	/**
	* Sets the class use yn of this virtual lab class statistics.
	*
	* @param classUseYn the class use yn of this virtual lab class statistics
	*/
	@Override
	public void setClassUseYn(java.lang.String classUseYn) {
		_virtualLabClassStatistics.setClassUseYn(classUseYn);
	}

	/**
	* Returns the last modified dt of this virtual lab class statistics.
	*
	* @return the last modified dt of this virtual lab class statistics
	*/
	@Override
	public java.util.Date getLastModifiedDt() {
		return _virtualLabClassStatistics.getLastModifiedDt();
	}

	/**
	* Sets the last modified dt of this virtual lab class statistics.
	*
	* @param lastModifiedDt the last modified dt of this virtual lab class statistics
	*/
	@Override
	public void setLastModifiedDt(java.util.Date lastModifiedDt) {
		_virtualLabClassStatistics.setLastModifiedDt(lastModifiedDt);
	}

	@Override
	public boolean isNew() {
		return _virtualLabClassStatistics.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_virtualLabClassStatistics.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _virtualLabClassStatistics.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_virtualLabClassStatistics.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _virtualLabClassStatistics.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _virtualLabClassStatistics.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_virtualLabClassStatistics.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _virtualLabClassStatistics.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_virtualLabClassStatistics.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_virtualLabClassStatistics.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_virtualLabClassStatistics.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _virtualLabClassStatistics.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _virtualLabClassStatistics.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_virtualLabClassStatistics.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_virtualLabClassStatistics.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new VirtualLabClassStatisticsWrapper((VirtualLabClassStatistics)_virtualLabClassStatistics.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics virtualLabClassStatistics) {
		return _virtualLabClassStatistics.compareTo(virtualLabClassStatistics);
	}

	@Override
	public int hashCode() {
		return _virtualLabClassStatistics.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics> toCacheModel() {
		return _virtualLabClassStatistics.toCacheModel();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics toEscapedModel() {
		return new VirtualLabClassStatisticsWrapper(_virtualLabClassStatistics.toEscapedModel());
	}

	@Override
	public org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics toUnescapedModel() {
		return new VirtualLabClassStatisticsWrapper(_virtualLabClassStatistics.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _virtualLabClassStatistics.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _virtualLabClassStatistics.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_virtualLabClassStatistics.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabClassStatisticsWrapper)) {
			return false;
		}

		VirtualLabClassStatisticsWrapper virtualLabClassStatisticsWrapper = (VirtualLabClassStatisticsWrapper)obj;

		if (Validator.equals(_virtualLabClassStatistics,
					virtualLabClassStatisticsWrapper._virtualLabClassStatistics)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public VirtualLabClassStatistics getWrappedVirtualLabClassStatistics() {
		return _virtualLabClassStatistics;
	}

	@Override
	public VirtualLabClassStatistics getWrappedModel() {
		return _virtualLabClassStatistics;
	}

	@Override
	public void resetOriginalValues() {
		_virtualLabClassStatistics.resetOriginalValues();
	}

	private VirtualLabClassStatistics _virtualLabClassStatistics;
}