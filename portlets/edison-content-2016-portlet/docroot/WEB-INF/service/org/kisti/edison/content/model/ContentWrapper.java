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

package org.kisti.edison.content.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Content}.
 * </p>
 *
 * @author EDISON
 * @see Content
 * @generated
 */
public class ContentWrapper implements Content, ModelWrapper<Content> {
	public ContentWrapper(Content content) {
		_content = content;
	}

	@Override
	public Class<?> getModelClass() {
		return Content.class;
	}

	@Override
	public String getModelClassName() {
		return Content.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("contentSeq", getContentSeq());
		attributes.put("contentDiv", getContentDiv());
		attributes.put("title", getTitle());
		attributes.put("resume", getResume());
		attributes.put("contentFileNm", getContentFileNm());
		attributes.put("advancedStartFileNm", getAdvancedStartFileNm());
		attributes.put("serviceLanguage", getServiceLanguage());
		attributes.put("projectYn", getProjectYn());
		attributes.put("projectId", getProjectId());
		attributes.put("viewCnt", getViewCnt());
		attributes.put("insertId", getInsertId());
		attributes.put("insertDate", getInsertDate());
		attributes.put("updateId", getUpdateId());
		attributes.put("updateDate", getUpdateDate());
		attributes.put("version", getVersion());
		attributes.put("openYn", getOpenYn());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long contentSeq = (Long)attributes.get("contentSeq");

		if (contentSeq != null) {
			setContentSeq(contentSeq);
		}

		Long contentDiv = (Long)attributes.get("contentDiv");

		if (contentDiv != null) {
			setContentDiv(contentDiv);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String resume = (String)attributes.get("resume");

		if (resume != null) {
			setResume(resume);
		}

		String contentFileNm = (String)attributes.get("contentFileNm");

		if (contentFileNm != null) {
			setContentFileNm(contentFileNm);
		}

		String advancedStartFileNm = (String)attributes.get(
				"advancedStartFileNm");

		if (advancedStartFileNm != null) {
			setAdvancedStartFileNm(advancedStartFileNm);
		}

		String serviceLanguage = (String)attributes.get("serviceLanguage");

		if (serviceLanguage != null) {
			setServiceLanguage(serviceLanguage);
		}

		String projectYn = (String)attributes.get("projectYn");

		if (projectYn != null) {
			setProjectYn(projectYn);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long viewCnt = (Long)attributes.get("viewCnt");

		if (viewCnt != null) {
			setViewCnt(viewCnt);
		}

		Long insertId = (Long)attributes.get("insertId");

		if (insertId != null) {
			setInsertId(insertId);
		}

		Date insertDate = (Date)attributes.get("insertDate");

		if (insertDate != null) {
			setInsertDate(insertDate);
		}

		Long updateId = (Long)attributes.get("updateId");

		if (updateId != null) {
			setUpdateId(updateId);
		}

		Date updateDate = (Date)attributes.get("updateDate");

		if (updateDate != null) {
			setUpdateDate(updateDate);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String openYn = (String)attributes.get("openYn");

		if (openYn != null) {
			setOpenYn(openYn);
		}
	}

	/**
	* Returns the primary key of this content.
	*
	* @return the primary key of this content
	*/
	@Override
	public long getPrimaryKey() {
		return _content.getPrimaryKey();
	}

	/**
	* Sets the primary key of this content.
	*
	* @param primaryKey the primary key of this content
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_content.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this content.
	*
	* @return the uuid of this content
	*/
	@Override
	public java.lang.String getUuid() {
		return _content.getUuid();
	}

	/**
	* Sets the uuid of this content.
	*
	* @param uuid the uuid of this content
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_content.setUuid(uuid);
	}

	/**
	* Returns the content seq of this content.
	*
	* @return the content seq of this content
	*/
	@Override
	public long getContentSeq() {
		return _content.getContentSeq();
	}

	/**
	* Sets the content seq of this content.
	*
	* @param contentSeq the content seq of this content
	*/
	@Override
	public void setContentSeq(long contentSeq) {
		_content.setContentSeq(contentSeq);
	}

	/**
	* Returns the content div of this content.
	*
	* @return the content div of this content
	*/
	@Override
	public long getContentDiv() {
		return _content.getContentDiv();
	}

	/**
	* Sets the content div of this content.
	*
	* @param contentDiv the content div of this content
	*/
	@Override
	public void setContentDiv(long contentDiv) {
		_content.setContentDiv(contentDiv);
	}

	/**
	* Returns the title of this content.
	*
	* @return the title of this content
	*/
	@Override
	public java.lang.String getTitle() {
		return _content.getTitle();
	}

	/**
	* Returns the localized title of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this content
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _content.getTitle(locale);
	}

	/**
	* Returns the localized title of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this content. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _content.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this content
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _content.getTitle(languageId);
	}

	/**
	* Returns the localized title of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this content
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _content.getTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _content.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _content.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this content.
	*
	* @return the locales and localized titles of this content
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _content.getTitleMap();
	}

	/**
	* Sets the title of this content.
	*
	* @param title the title of this content
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_content.setTitle(title);
	}

	/**
	* Sets the localized title of this content in the language.
	*
	* @param title the localized title of this content
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_content.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this content in the language, and sets the default locale.
	*
	* @param title the localized title of this content
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_content.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_content.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this content from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this content
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_content.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this content from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this content
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_content.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the resume of this content.
	*
	* @return the resume of this content
	*/
	@Override
	public java.lang.String getResume() {
		return _content.getResume();
	}

	/**
	* Returns the localized resume of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized resume of this content
	*/
	@Override
	public java.lang.String getResume(java.util.Locale locale) {
		return _content.getResume(locale);
	}

	/**
	* Returns the localized resume of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized resume of this content. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getResume(java.util.Locale locale,
		boolean useDefault) {
		return _content.getResume(locale, useDefault);
	}

	/**
	* Returns the localized resume of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized resume of this content
	*/
	@Override
	public java.lang.String getResume(java.lang.String languageId) {
		return _content.getResume(languageId);
	}

	/**
	* Returns the localized resume of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized resume of this content
	*/
	@Override
	public java.lang.String getResume(java.lang.String languageId,
		boolean useDefault) {
		return _content.getResume(languageId, useDefault);
	}

	@Override
	public java.lang.String getResumeCurrentLanguageId() {
		return _content.getResumeCurrentLanguageId();
	}

	@Override
	public java.lang.String getResumeCurrentValue() {
		return _content.getResumeCurrentValue();
	}

	/**
	* Returns a map of the locales and localized resumes of this content.
	*
	* @return the locales and localized resumes of this content
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getResumeMap() {
		return _content.getResumeMap();
	}

	/**
	* Sets the resume of this content.
	*
	* @param resume the resume of this content
	*/
	@Override
	public void setResume(java.lang.String resume) {
		_content.setResume(resume);
	}

	/**
	* Sets the localized resume of this content in the language.
	*
	* @param resume the localized resume of this content
	* @param locale the locale of the language
	*/
	@Override
	public void setResume(java.lang.String resume, java.util.Locale locale) {
		_content.setResume(resume, locale);
	}

	/**
	* Sets the localized resume of this content in the language, and sets the default locale.
	*
	* @param resume the localized resume of this content
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setResume(java.lang.String resume, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_content.setResume(resume, locale, defaultLocale);
	}

	@Override
	public void setResumeCurrentLanguageId(java.lang.String languageId) {
		_content.setResumeCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized resumes of this content from the map of locales and localized resumes.
	*
	* @param resumeMap the locales and localized resumes of this content
	*/
	@Override
	public void setResumeMap(
		java.util.Map<java.util.Locale, java.lang.String> resumeMap) {
		_content.setResumeMap(resumeMap);
	}

	/**
	* Sets the localized resumes of this content from the map of locales and localized resumes, and sets the default locale.
	*
	* @param resumeMap the locales and localized resumes of this content
	* @param defaultLocale the default locale
	*/
	@Override
	public void setResumeMap(
		java.util.Map<java.util.Locale, java.lang.String> resumeMap,
		java.util.Locale defaultLocale) {
		_content.setResumeMap(resumeMap, defaultLocale);
	}

	/**
	* Returns the content file nm of this content.
	*
	* @return the content file nm of this content
	*/
	@Override
	public java.lang.String getContentFileNm() {
		return _content.getContentFileNm();
	}

	/**
	* Returns the localized content file nm of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized content file nm of this content
	*/
	@Override
	public java.lang.String getContentFileNm(java.util.Locale locale) {
		return _content.getContentFileNm(locale);
	}

	/**
	* Returns the localized content file nm of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content file nm of this content. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getContentFileNm(java.util.Locale locale,
		boolean useDefault) {
		return _content.getContentFileNm(locale, useDefault);
	}

	/**
	* Returns the localized content file nm of this content in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized content file nm of this content
	*/
	@Override
	public java.lang.String getContentFileNm(java.lang.String languageId) {
		return _content.getContentFileNm(languageId);
	}

	/**
	* Returns the localized content file nm of this content in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized content file nm of this content
	*/
	@Override
	public java.lang.String getContentFileNm(java.lang.String languageId,
		boolean useDefault) {
		return _content.getContentFileNm(languageId, useDefault);
	}

	@Override
	public java.lang.String getContentFileNmCurrentLanguageId() {
		return _content.getContentFileNmCurrentLanguageId();
	}

	@Override
	public java.lang.String getContentFileNmCurrentValue() {
		return _content.getContentFileNmCurrentValue();
	}

	/**
	* Returns a map of the locales and localized content file nms of this content.
	*
	* @return the locales and localized content file nms of this content
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getContentFileNmMap() {
		return _content.getContentFileNmMap();
	}

	/**
	* Sets the content file nm of this content.
	*
	* @param contentFileNm the content file nm of this content
	*/
	@Override
	public void setContentFileNm(java.lang.String contentFileNm) {
		_content.setContentFileNm(contentFileNm);
	}

	/**
	* Sets the localized content file nm of this content in the language.
	*
	* @param contentFileNm the localized content file nm of this content
	* @param locale the locale of the language
	*/
	@Override
	public void setContentFileNm(java.lang.String contentFileNm,
		java.util.Locale locale) {
		_content.setContentFileNm(contentFileNm, locale);
	}

	/**
	* Sets the localized content file nm of this content in the language, and sets the default locale.
	*
	* @param contentFileNm the localized content file nm of this content
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContentFileNm(java.lang.String contentFileNm,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_content.setContentFileNm(contentFileNm, locale, defaultLocale);
	}

	@Override
	public void setContentFileNmCurrentLanguageId(java.lang.String languageId) {
		_content.setContentFileNmCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized content file nms of this content from the map of locales and localized content file nms.
	*
	* @param contentFileNmMap the locales and localized content file nms of this content
	*/
	@Override
	public void setContentFileNmMap(
		java.util.Map<java.util.Locale, java.lang.String> contentFileNmMap) {
		_content.setContentFileNmMap(contentFileNmMap);
	}

	/**
	* Sets the localized content file nms of this content from the map of locales and localized content file nms, and sets the default locale.
	*
	* @param contentFileNmMap the locales and localized content file nms of this content
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContentFileNmMap(
		java.util.Map<java.util.Locale, java.lang.String> contentFileNmMap,
		java.util.Locale defaultLocale) {
		_content.setContentFileNmMap(contentFileNmMap, defaultLocale);
	}

	/**
	* Returns the advanced start file nm of this content.
	*
	* @return the advanced start file nm of this content
	*/
	@Override
	public java.lang.String getAdvancedStartFileNm() {
		return _content.getAdvancedStartFileNm();
	}

	/**
	* Sets the advanced start file nm of this content.
	*
	* @param advancedStartFileNm the advanced start file nm of this content
	*/
	@Override
	public void setAdvancedStartFileNm(java.lang.String advancedStartFileNm) {
		_content.setAdvancedStartFileNm(advancedStartFileNm);
	}

	/**
	* Returns the service language of this content.
	*
	* @return the service language of this content
	*/
	@Override
	public java.lang.String getServiceLanguage() {
		return _content.getServiceLanguage();
	}

	/**
	* Sets the service language of this content.
	*
	* @param serviceLanguage the service language of this content
	*/
	@Override
	public void setServiceLanguage(java.lang.String serviceLanguage) {
		_content.setServiceLanguage(serviceLanguage);
	}

	/**
	* Returns the project yn of this content.
	*
	* @return the project yn of this content
	*/
	@Override
	public java.lang.String getProjectYn() {
		return _content.getProjectYn();
	}

	/**
	* Sets the project yn of this content.
	*
	* @param projectYn the project yn of this content
	*/
	@Override
	public void setProjectYn(java.lang.String projectYn) {
		_content.setProjectYn(projectYn);
	}

	/**
	* Returns the project ID of this content.
	*
	* @return the project ID of this content
	*/
	@Override
	public long getProjectId() {
		return _content.getProjectId();
	}

	/**
	* Sets the project ID of this content.
	*
	* @param projectId the project ID of this content
	*/
	@Override
	public void setProjectId(long projectId) {
		_content.setProjectId(projectId);
	}

	/**
	* Returns the view cnt of this content.
	*
	* @return the view cnt of this content
	*/
	@Override
	public long getViewCnt() {
		return _content.getViewCnt();
	}

	/**
	* Sets the view cnt of this content.
	*
	* @param viewCnt the view cnt of this content
	*/
	@Override
	public void setViewCnt(long viewCnt) {
		_content.setViewCnt(viewCnt);
	}

	/**
	* Returns the insert ID of this content.
	*
	* @return the insert ID of this content
	*/
	@Override
	public long getInsertId() {
		return _content.getInsertId();
	}

	/**
	* Sets the insert ID of this content.
	*
	* @param insertId the insert ID of this content
	*/
	@Override
	public void setInsertId(long insertId) {
		_content.setInsertId(insertId);
	}

	/**
	* Returns the insert date of this content.
	*
	* @return the insert date of this content
	*/
	@Override
	public java.util.Date getInsertDate() {
		return _content.getInsertDate();
	}

	/**
	* Sets the insert date of this content.
	*
	* @param insertDate the insert date of this content
	*/
	@Override
	public void setInsertDate(java.util.Date insertDate) {
		_content.setInsertDate(insertDate);
	}

	/**
	* Returns the update ID of this content.
	*
	* @return the update ID of this content
	*/
	@Override
	public long getUpdateId() {
		return _content.getUpdateId();
	}

	/**
	* Sets the update ID of this content.
	*
	* @param updateId the update ID of this content
	*/
	@Override
	public void setUpdateId(long updateId) {
		_content.setUpdateId(updateId);
	}

	/**
	* Returns the update date of this content.
	*
	* @return the update date of this content
	*/
	@Override
	public java.util.Date getUpdateDate() {
		return _content.getUpdateDate();
	}

	/**
	* Sets the update date of this content.
	*
	* @param updateDate the update date of this content
	*/
	@Override
	public void setUpdateDate(java.util.Date updateDate) {
		_content.setUpdateDate(updateDate);
	}

	/**
	* Returns the version of this content.
	*
	* @return the version of this content
	*/
	@Override
	public java.lang.String getVersion() {
		return _content.getVersion();
	}

	/**
	* Sets the version of this content.
	*
	* @param version the version of this content
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_content.setVersion(version);
	}

	/**
	* Returns the open yn of this content.
	*
	* @return the open yn of this content
	*/
	@Override
	public java.lang.String getOpenYn() {
		return _content.getOpenYn();
	}

	/**
	* Sets the open yn of this content.
	*
	* @param openYn the open yn of this content
	*/
	@Override
	public void setOpenYn(java.lang.String openYn) {
		_content.setOpenYn(openYn);
	}

	@Override
	public boolean isNew() {
		return _content.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_content.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _content.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_content.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _content.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _content.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_content.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _content.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_content.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_content.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_content.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _content.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _content.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_content.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_content.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ContentWrapper((Content)_content.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.content.model.Content content) {
		return _content.compareTo(content);
	}

	@Override
	public int hashCode() {
		return _content.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.content.model.Content> toCacheModel() {
		return _content.toCacheModel();
	}

	@Override
	public org.kisti.edison.content.model.Content toEscapedModel() {
		return new ContentWrapper(_content.toEscapedModel());
	}

	@Override
	public org.kisti.edison.content.model.Content toUnescapedModel() {
		return new ContentWrapper(_content.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _content.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _content.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_content.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentWrapper)) {
			return false;
		}

		ContentWrapper contentWrapper = (ContentWrapper)obj;

		if (Validator.equals(_content, contentWrapper._content)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Content getWrappedContent() {
		return _content;
	}

	@Override
	public Content getWrappedModel() {
		return _content;
	}

	@Override
	public void resetOriginalValues() {
		_content.resetOriginalValues();
	}

	private Content _content;
}