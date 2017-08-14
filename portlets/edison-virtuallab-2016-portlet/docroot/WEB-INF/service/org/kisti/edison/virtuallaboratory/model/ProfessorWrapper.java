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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Professor}.
 * </p>
 *
 * @author EDISON
 * @see Professor
 * @generated
 */
public class ProfessorWrapper implements Professor, ModelWrapper<Professor> {
	public ProfessorWrapper(Professor professor) {
		_professor = professor;
	}

	@Override
	public Class<?> getModelClass() {
		return Professor.class;
	}

	@Override
	public String getModelClassName() {
		return Professor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("professorSeq", getProfessorSeq());
		attributes.put("userId", getUserId());
		attributes.put("record", getRecord());
		attributes.put("paper", getPaper());
		attributes.put("homepage", getHomepage());
		attributes.put("introduce", getIntroduce());
		attributes.put("phone", getPhone());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long professorSeq = (Long)attributes.get("professorSeq");

		if (professorSeq != null) {
			setProfessorSeq(professorSeq);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String record = (String)attributes.get("record");

		if (record != null) {
			setRecord(record);
		}

		String paper = (String)attributes.get("paper");

		if (paper != null) {
			setPaper(paper);
		}

		String homepage = (String)attributes.get("homepage");

		if (homepage != null) {
			setHomepage(homepage);
		}

		String introduce = (String)attributes.get("introduce");

		if (introduce != null) {
			setIntroduce(introduce);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}
	}

	/**
	* Returns the primary key of this professor.
	*
	* @return the primary key of this professor
	*/
	@Override
	public long getPrimaryKey() {
		return _professor.getPrimaryKey();
	}

	/**
	* Sets the primary key of this professor.
	*
	* @param primaryKey the primary key of this professor
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_professor.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the professor seq of this professor.
	*
	* @return the professor seq of this professor
	*/
	@Override
	public long getProfessorSeq() {
		return _professor.getProfessorSeq();
	}

	/**
	* Sets the professor seq of this professor.
	*
	* @param professorSeq the professor seq of this professor
	*/
	@Override
	public void setProfessorSeq(long professorSeq) {
		_professor.setProfessorSeq(professorSeq);
	}

	/**
	* Returns the user ID of this professor.
	*
	* @return the user ID of this professor
	*/
	@Override
	public long getUserId() {
		return _professor.getUserId();
	}

	/**
	* Sets the user ID of this professor.
	*
	* @param userId the user ID of this professor
	*/
	@Override
	public void setUserId(long userId) {
		_professor.setUserId(userId);
	}

	/**
	* Returns the user uuid of this professor.
	*
	* @return the user uuid of this professor
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _professor.getUserUuid();
	}

	/**
	* Sets the user uuid of this professor.
	*
	* @param userUuid the user uuid of this professor
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_professor.setUserUuid(userUuid);
	}

	/**
	* Returns the record of this professor.
	*
	* @return the record of this professor
	*/
	@Override
	public java.lang.String getRecord() {
		return _professor.getRecord();
	}

	/**
	* Returns the localized record of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized record of this professor
	*/
	@Override
	public java.lang.String getRecord(java.util.Locale locale) {
		return _professor.getRecord(locale);
	}

	/**
	* Returns the localized record of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized record of this professor. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getRecord(java.util.Locale locale,
		boolean useDefault) {
		return _professor.getRecord(locale, useDefault);
	}

	/**
	* Returns the localized record of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized record of this professor
	*/
	@Override
	public java.lang.String getRecord(java.lang.String languageId) {
		return _professor.getRecord(languageId);
	}

	/**
	* Returns the localized record of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized record of this professor
	*/
	@Override
	public java.lang.String getRecord(java.lang.String languageId,
		boolean useDefault) {
		return _professor.getRecord(languageId, useDefault);
	}

	@Override
	public java.lang.String getRecordCurrentLanguageId() {
		return _professor.getRecordCurrentLanguageId();
	}

	@Override
	public java.lang.String getRecordCurrentValue() {
		return _professor.getRecordCurrentValue();
	}

	/**
	* Returns a map of the locales and localized records of this professor.
	*
	* @return the locales and localized records of this professor
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getRecordMap() {
		return _professor.getRecordMap();
	}

	/**
	* Sets the record of this professor.
	*
	* @param record the record of this professor
	*/
	@Override
	public void setRecord(java.lang.String record) {
		_professor.setRecord(record);
	}

	/**
	* Sets the localized record of this professor in the language.
	*
	* @param record the localized record of this professor
	* @param locale the locale of the language
	*/
	@Override
	public void setRecord(java.lang.String record, java.util.Locale locale) {
		_professor.setRecord(record, locale);
	}

	/**
	* Sets the localized record of this professor in the language, and sets the default locale.
	*
	* @param record the localized record of this professor
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRecord(java.lang.String record, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_professor.setRecord(record, locale, defaultLocale);
	}

	@Override
	public void setRecordCurrentLanguageId(java.lang.String languageId) {
		_professor.setRecordCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized records of this professor from the map of locales and localized records.
	*
	* @param recordMap the locales and localized records of this professor
	*/
	@Override
	public void setRecordMap(
		java.util.Map<java.util.Locale, java.lang.String> recordMap) {
		_professor.setRecordMap(recordMap);
	}

	/**
	* Sets the localized records of this professor from the map of locales and localized records, and sets the default locale.
	*
	* @param recordMap the locales and localized records of this professor
	* @param defaultLocale the default locale
	*/
	@Override
	public void setRecordMap(
		java.util.Map<java.util.Locale, java.lang.String> recordMap,
		java.util.Locale defaultLocale) {
		_professor.setRecordMap(recordMap, defaultLocale);
	}

	/**
	* Returns the paper of this professor.
	*
	* @return the paper of this professor
	*/
	@Override
	public java.lang.String getPaper() {
		return _professor.getPaper();
	}

	/**
	* Returns the localized paper of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized paper of this professor
	*/
	@Override
	public java.lang.String getPaper(java.util.Locale locale) {
		return _professor.getPaper(locale);
	}

	/**
	* Returns the localized paper of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper of this professor. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPaper(java.util.Locale locale, boolean useDefault) {
		return _professor.getPaper(locale, useDefault);
	}

	/**
	* Returns the localized paper of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized paper of this professor
	*/
	@Override
	public java.lang.String getPaper(java.lang.String languageId) {
		return _professor.getPaper(languageId);
	}

	/**
	* Returns the localized paper of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper of this professor
	*/
	@Override
	public java.lang.String getPaper(java.lang.String languageId,
		boolean useDefault) {
		return _professor.getPaper(languageId, useDefault);
	}

	@Override
	public java.lang.String getPaperCurrentLanguageId() {
		return _professor.getPaperCurrentLanguageId();
	}

	@Override
	public java.lang.String getPaperCurrentValue() {
		return _professor.getPaperCurrentValue();
	}

	/**
	* Returns a map of the locales and localized papers of this professor.
	*
	* @return the locales and localized papers of this professor
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPaperMap() {
		return _professor.getPaperMap();
	}

	/**
	* Sets the paper of this professor.
	*
	* @param paper the paper of this professor
	*/
	@Override
	public void setPaper(java.lang.String paper) {
		_professor.setPaper(paper);
	}

	/**
	* Sets the localized paper of this professor in the language.
	*
	* @param paper the localized paper of this professor
	* @param locale the locale of the language
	*/
	@Override
	public void setPaper(java.lang.String paper, java.util.Locale locale) {
		_professor.setPaper(paper, locale);
	}

	/**
	* Sets the localized paper of this professor in the language, and sets the default locale.
	*
	* @param paper the localized paper of this professor
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaper(java.lang.String paper, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_professor.setPaper(paper, locale, defaultLocale);
	}

	@Override
	public void setPaperCurrentLanguageId(java.lang.String languageId) {
		_professor.setPaperCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized papers of this professor from the map of locales and localized papers.
	*
	* @param paperMap the locales and localized papers of this professor
	*/
	@Override
	public void setPaperMap(
		java.util.Map<java.util.Locale, java.lang.String> paperMap) {
		_professor.setPaperMap(paperMap);
	}

	/**
	* Sets the localized papers of this professor from the map of locales and localized papers, and sets the default locale.
	*
	* @param paperMap the locales and localized papers of this professor
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperMap(
		java.util.Map<java.util.Locale, java.lang.String> paperMap,
		java.util.Locale defaultLocale) {
		_professor.setPaperMap(paperMap, defaultLocale);
	}

	/**
	* Returns the homepage of this professor.
	*
	* @return the homepage of this professor
	*/
	@Override
	public java.lang.String getHomepage() {
		return _professor.getHomepage();
	}

	/**
	* Sets the homepage of this professor.
	*
	* @param homepage the homepage of this professor
	*/
	@Override
	public void setHomepage(java.lang.String homepage) {
		_professor.setHomepage(homepage);
	}

	/**
	* Returns the introduce of this professor.
	*
	* @return the introduce of this professor
	*/
	@Override
	public java.lang.String getIntroduce() {
		return _professor.getIntroduce();
	}

	/**
	* Returns the localized introduce of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized introduce of this professor
	*/
	@Override
	public java.lang.String getIntroduce(java.util.Locale locale) {
		return _professor.getIntroduce(locale);
	}

	/**
	* Returns the localized introduce of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized introduce of this professor. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getIntroduce(java.util.Locale locale,
		boolean useDefault) {
		return _professor.getIntroduce(locale, useDefault);
	}

	/**
	* Returns the localized introduce of this professor in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized introduce of this professor
	*/
	@Override
	public java.lang.String getIntroduce(java.lang.String languageId) {
		return _professor.getIntroduce(languageId);
	}

	/**
	* Returns the localized introduce of this professor in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized introduce of this professor
	*/
	@Override
	public java.lang.String getIntroduce(java.lang.String languageId,
		boolean useDefault) {
		return _professor.getIntroduce(languageId, useDefault);
	}

	@Override
	public java.lang.String getIntroduceCurrentLanguageId() {
		return _professor.getIntroduceCurrentLanguageId();
	}

	@Override
	public java.lang.String getIntroduceCurrentValue() {
		return _professor.getIntroduceCurrentValue();
	}

	/**
	* Returns a map of the locales and localized introduces of this professor.
	*
	* @return the locales and localized introduces of this professor
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getIntroduceMap() {
		return _professor.getIntroduceMap();
	}

	/**
	* Sets the introduce of this professor.
	*
	* @param introduce the introduce of this professor
	*/
	@Override
	public void setIntroduce(java.lang.String introduce) {
		_professor.setIntroduce(introduce);
	}

	/**
	* Sets the localized introduce of this professor in the language.
	*
	* @param introduce the localized introduce of this professor
	* @param locale the locale of the language
	*/
	@Override
	public void setIntroduce(java.lang.String introduce, java.util.Locale locale) {
		_professor.setIntroduce(introduce, locale);
	}

	/**
	* Sets the localized introduce of this professor in the language, and sets the default locale.
	*
	* @param introduce the localized introduce of this professor
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setIntroduce(java.lang.String introduce,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_professor.setIntroduce(introduce, locale, defaultLocale);
	}

	@Override
	public void setIntroduceCurrentLanguageId(java.lang.String languageId) {
		_professor.setIntroduceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized introduces of this professor from the map of locales and localized introduces.
	*
	* @param introduceMap the locales and localized introduces of this professor
	*/
	@Override
	public void setIntroduceMap(
		java.util.Map<java.util.Locale, java.lang.String> introduceMap) {
		_professor.setIntroduceMap(introduceMap);
	}

	/**
	* Sets the localized introduces of this professor from the map of locales and localized introduces, and sets the default locale.
	*
	* @param introduceMap the locales and localized introduces of this professor
	* @param defaultLocale the default locale
	*/
	@Override
	public void setIntroduceMap(
		java.util.Map<java.util.Locale, java.lang.String> introduceMap,
		java.util.Locale defaultLocale) {
		_professor.setIntroduceMap(introduceMap, defaultLocale);
	}

	/**
	* Returns the phone of this professor.
	*
	* @return the phone of this professor
	*/
	@Override
	public java.lang.String getPhone() {
		return _professor.getPhone();
	}

	/**
	* Sets the phone of this professor.
	*
	* @param phone the phone of this professor
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_professor.setPhone(phone);
	}

	@Override
	public boolean isNew() {
		return _professor.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_professor.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _professor.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_professor.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _professor.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _professor.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_professor.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _professor.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_professor.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_professor.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_professor.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _professor.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _professor.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_professor.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_professor.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ProfessorWrapper((Professor)_professor.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.virtuallaboratory.model.Professor professor) {
		return _professor.compareTo(professor);
	}

	@Override
	public int hashCode() {
		return _professor.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.virtuallaboratory.model.Professor> toCacheModel() {
		return _professor.toCacheModel();
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.Professor toEscapedModel() {
		return new ProfessorWrapper(_professor.toEscapedModel());
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.Professor toUnescapedModel() {
		return new ProfessorWrapper(_professor.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _professor.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _professor.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_professor.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProfessorWrapper)) {
			return false;
		}

		ProfessorWrapper professorWrapper = (ProfessorWrapper)obj;

		if (Validator.equals(_professor, professorWrapper._professor)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Professor getWrappedProfessor() {
		return _professor;
	}

	@Override
	public Professor getWrappedModel() {
		return _professor;
	}

	@Override
	public void resetOriginalValues() {
		_professor.resetOriginalValues();
	}

	private Professor _professor;
}