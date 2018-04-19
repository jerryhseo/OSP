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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.content.service.ClpSerializer;
import org.kisti.edison.content.service.ContentLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author EDISON
 */
public class ContentClp extends BaseModelImpl<Content> implements Content {
	public ContentClp() {
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
	public long getPrimaryKey() {
		return _contentSeq;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContentSeq(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentSeq;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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
		attributes.put("coverImageFileEntryId", getCoverImageFileEntryId());

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

		Long coverImageFileEntryId = (Long)attributes.get(
				"coverImageFileEntryId");

		if (coverImageFileEntryId != null) {
			setCoverImageFileEntryId(coverImageFileEntryId);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_contentRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getContentSeq() {
		return _contentSeq;
	}

	@Override
	public void setContentSeq(long contentSeq) {
		_contentSeq = contentSeq;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setContentSeq", long.class);

				method.invoke(_contentRemoteModel, contentSeq);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getContentDiv() {
		return _contentDiv;
	}

	@Override
	public void setContentDiv(long contentDiv) {
		_contentDiv = contentDiv;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setContentDiv", long.class);

				method.invoke(_contentRemoteModel, contentDiv);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_contentRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
					"Title", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getResume() {
		return _resume;
	}

	@Override
	public String getResume(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getResume(languageId);
	}

	@Override
	public String getResume(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getResume(languageId, useDefault);
	}

	@Override
	public String getResume(String languageId) {
		return LocalizationUtil.getLocalization(getResume(), languageId);
	}

	@Override
	public String getResume(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getResume(), languageId,
			useDefault);
	}

	@Override
	public String getResumeCurrentLanguageId() {
		return _resumeCurrentLanguageId;
	}

	@Override
	public String getResumeCurrentValue() {
		Locale locale = getLocale(_resumeCurrentLanguageId);

		return getResume(locale);
	}

	@Override
	public Map<Locale, String> getResumeMap() {
		return LocalizationUtil.getLocalizationMap(getResume());
	}

	@Override
	public void setResume(String resume) {
		_resume = resume;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setResume", String.class);

				method.invoke(_contentRemoteModel, resume);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setResume(String resume, Locale locale) {
		setResume(resume, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setResume(String resume, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(resume)) {
			setResume(LocalizationUtil.updateLocalization(getResume(),
					"Resume", resume, languageId, defaultLanguageId));
		}
		else {
			setResume(LocalizationUtil.removeLocalization(getResume(),
					"Resume", languageId));
		}
	}

	@Override
	public void setResumeCurrentLanguageId(String languageId) {
		_resumeCurrentLanguageId = languageId;
	}

	@Override
	public void setResumeMap(Map<Locale, String> resumeMap) {
		setResumeMap(resumeMap, LocaleUtil.getDefault());
	}

	@Override
	public void setResumeMap(Map<Locale, String> resumeMap, Locale defaultLocale) {
		if (resumeMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setResume(LocalizationUtil.updateLocalization(resumeMap,
					getResume(), "Resume",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getContentFileNm() {
		return _contentFileNm;
	}

	@Override
	public String getContentFileNm(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getContentFileNm(languageId);
	}

	@Override
	public String getContentFileNm(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getContentFileNm(languageId, useDefault);
	}

	@Override
	public String getContentFileNm(String languageId) {
		return LocalizationUtil.getLocalization(getContentFileNm(), languageId);
	}

	@Override
	public String getContentFileNm(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getContentFileNm(), languageId,
			useDefault);
	}

	@Override
	public String getContentFileNmCurrentLanguageId() {
		return _contentFileNmCurrentLanguageId;
	}

	@Override
	public String getContentFileNmCurrentValue() {
		Locale locale = getLocale(_contentFileNmCurrentLanguageId);

		return getContentFileNm(locale);
	}

	@Override
	public Map<Locale, String> getContentFileNmMap() {
		return LocalizationUtil.getLocalizationMap(getContentFileNm());
	}

	@Override
	public void setContentFileNm(String contentFileNm) {
		_contentFileNm = contentFileNm;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setContentFileNm", String.class);

				method.invoke(_contentRemoteModel, contentFileNm);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setContentFileNm(String contentFileNm, Locale locale) {
		setContentFileNm(contentFileNm, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setContentFileNm(String contentFileNm, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(contentFileNm)) {
			setContentFileNm(LocalizationUtil.updateLocalization(
					getContentFileNm(), "ContentFileNm", contentFileNm,
					languageId, defaultLanguageId));
		}
		else {
			setContentFileNm(LocalizationUtil.removeLocalization(
					getContentFileNm(), "ContentFileNm", languageId));
		}
	}

	@Override
	public void setContentFileNmCurrentLanguageId(String languageId) {
		_contentFileNmCurrentLanguageId = languageId;
	}

	@Override
	public void setContentFileNmMap(Map<Locale, String> contentFileNmMap) {
		setContentFileNmMap(contentFileNmMap, LocaleUtil.getDefault());
	}

	@Override
	public void setContentFileNmMap(Map<Locale, String> contentFileNmMap,
		Locale defaultLocale) {
		if (contentFileNmMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setContentFileNm(LocalizationUtil.updateLocalization(
					contentFileNmMap, getContentFileNm(), "ContentFileNm",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getAdvancedStartFileNm() {
		return _advancedStartFileNm;
	}

	@Override
	public void setAdvancedStartFileNm(String advancedStartFileNm) {
		_advancedStartFileNm = advancedStartFileNm;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setAdvancedStartFileNm",
						String.class);

				method.invoke(_contentRemoteModel, advancedStartFileNm);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServiceLanguage() {
		return _serviceLanguage;
	}

	@Override
	public void setServiceLanguage(String serviceLanguage) {
		_serviceLanguage = serviceLanguage;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setServiceLanguage",
						String.class);

				method.invoke(_contentRemoteModel, serviceLanguage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProjectYn() {
		return _projectYn;
	}

	@Override
	public void setProjectYn(String projectYn) {
		_projectYn = projectYn;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectYn", String.class);

				method.invoke(_contentRemoteModel, projectYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectId", long.class);

				method.invoke(_contentRemoteModel, projectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getViewCnt() {
		return _viewCnt;
	}

	@Override
	public void setViewCnt(long viewCnt) {
		_viewCnt = viewCnt;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setViewCnt", long.class);

				method.invoke(_contentRemoteModel, viewCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getInsertId() {
		return _insertId;
	}

	@Override
	public void setInsertId(long insertId) {
		_insertId = insertId;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setInsertId", long.class);

				method.invoke(_contentRemoteModel, insertId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getInsertDate() {
		return _insertDate;
	}

	@Override
	public void setInsertDate(Date insertDate) {
		_insertDate = insertDate;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setInsertDate", Date.class);

				method.invoke(_contentRemoteModel, insertDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUpdateId() {
		return _updateId;
	}

	@Override
	public void setUpdateId(long updateId) {
		_updateId = updateId;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setUpdateId", long.class);

				method.invoke(_contentRemoteModel, updateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getUpdateDate() {
		return _updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setUpdateDate", Date.class);

				method.invoke(_contentRemoteModel, updateDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVersion() {
		return _version;
	}

	@Override
	public void setVersion(String version) {
		_version = version;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", String.class);

				method.invoke(_contentRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOpenYn() {
		return _openYn;
	}

	@Override
	public void setOpenYn(String openYn) {
		_openYn = openYn;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setOpenYn", String.class);

				method.invoke(_contentRemoteModel, openYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCoverImageFileEntryId() {
		return _coverImageFileEntryId;
	}

	@Override
	public void setCoverImageFileEntryId(long coverImageFileEntryId) {
		_coverImageFileEntryId = coverImageFileEntryId;

		if (_contentRemoteModel != null) {
			try {
				Class<?> clazz = _contentRemoteModel.getClass();

				Method method = clazz.getMethod("setCoverImageFileEntryId",
						long.class);

				method.invoke(_contentRemoteModel, coverImageFileEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getContentRemoteModel() {
		return _contentRemoteModel;
	}

	public void setContentRemoteModel(BaseModel<?> contentRemoteModel) {
		_contentRemoteModel = contentRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _contentRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_contentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ContentLocalServiceUtil.addContent(this);
		}
		else {
			ContentLocalServiceUtil.updateContent(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> resumeMap = getResumeMap();

		for (Map.Entry<Locale, String> entry : resumeMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> contentFileNmMap = getContentFileNmMap();

		for (Map.Entry<Locale, String> entry : contentFileNmMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String resume = getResume(defaultLocale);

		if (Validator.isNull(resume)) {
			setResume(getResume(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setResume(getResume(defaultLocale), defaultLocale, defaultLocale);
		}

		String contentFileNm = getContentFileNm(defaultLocale);

		if (Validator.isNull(contentFileNm)) {
			setContentFileNm(getContentFileNm(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setContentFileNm(getContentFileNm(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public Content toEscapedModel() {
		return (Content)ProxyUtil.newProxyInstance(Content.class.getClassLoader(),
			new Class[] { Content.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ContentClp clone = new ContentClp();

		clone.setUuid(getUuid());
		clone.setContentSeq(getContentSeq());
		clone.setContentDiv(getContentDiv());
		clone.setTitle(getTitle());
		clone.setResume(getResume());
		clone.setContentFileNm(getContentFileNm());
		clone.setAdvancedStartFileNm(getAdvancedStartFileNm());
		clone.setServiceLanguage(getServiceLanguage());
		clone.setProjectYn(getProjectYn());
		clone.setProjectId(getProjectId());
		clone.setViewCnt(getViewCnt());
		clone.setInsertId(getInsertId());
		clone.setInsertDate(getInsertDate());
		clone.setUpdateId(getUpdateId());
		clone.setUpdateDate(getUpdateDate());
		clone.setVersion(getVersion());
		clone.setOpenYn(getOpenYn());
		clone.setCoverImageFileEntryId(getCoverImageFileEntryId());

		return clone;
	}

	@Override
	public int compareTo(Content content) {
		long primaryKey = content.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentClp)) {
			return false;
		}

		ContentClp content = (ContentClp)obj;

		long primaryKey = content.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", contentSeq=");
		sb.append(getContentSeq());
		sb.append(", contentDiv=");
		sb.append(getContentDiv());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", resume=");
		sb.append(getResume());
		sb.append(", contentFileNm=");
		sb.append(getContentFileNm());
		sb.append(", advancedStartFileNm=");
		sb.append(getAdvancedStartFileNm());
		sb.append(", serviceLanguage=");
		sb.append(getServiceLanguage());
		sb.append(", projectYn=");
		sb.append(getProjectYn());
		sb.append(", projectId=");
		sb.append(getProjectId());
		sb.append(", viewCnt=");
		sb.append(getViewCnt());
		sb.append(", insertId=");
		sb.append(getInsertId());
		sb.append(", insertDate=");
		sb.append(getInsertDate());
		sb.append(", updateId=");
		sb.append(getUpdateId());
		sb.append(", updateDate=");
		sb.append(getUpdateDate());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", openYn=");
		sb.append(getOpenYn());
		sb.append(", coverImageFileEntryId=");
		sb.append(getCoverImageFileEntryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.content.model.Content");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contentSeq</column-name><column-value><![CDATA[");
		sb.append(getContentSeq());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contentDiv</column-name><column-value><![CDATA[");
		sb.append(getContentDiv());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resume</column-name><column-value><![CDATA[");
		sb.append(getResume());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contentFileNm</column-name><column-value><![CDATA[");
		sb.append(getContentFileNm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>advancedStartFileNm</column-name><column-value><![CDATA[");
		sb.append(getAdvancedStartFileNm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceLanguage</column-name><column-value><![CDATA[");
		sb.append(getServiceLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectYn</column-name><column-value><![CDATA[");
		sb.append(getProjectYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>viewCnt</column-name><column-value><![CDATA[");
		sb.append(getViewCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertId</column-name><column-value><![CDATA[");
		sb.append(getInsertId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertDate</column-name><column-value><![CDATA[");
		sb.append(getInsertDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>updateId</column-name><column-value><![CDATA[");
		sb.append(getUpdateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>updateDate</column-name><column-value><![CDATA[");
		sb.append(getUpdateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>openYn</column-name><column-value><![CDATA[");
		sb.append(getOpenYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>coverImageFileEntryId</column-name><column-value><![CDATA[");
		sb.append(getCoverImageFileEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _contentSeq;
	private long _contentDiv;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _resume;
	private String _resumeCurrentLanguageId;
	private String _contentFileNm;
	private String _contentFileNmCurrentLanguageId;
	private String _advancedStartFileNm;
	private String _serviceLanguage;
	private String _projectYn;
	private long _projectId;
	private long _viewCnt;
	private long _insertId;
	private Date _insertDate;
	private long _updateId;
	private Date _updateDate;
	private String _version;
	private String _openYn;
	private long _coverImageFileEntryId;
	private BaseModel<?> _contentRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.content.service.ClpSerializer.class;
}