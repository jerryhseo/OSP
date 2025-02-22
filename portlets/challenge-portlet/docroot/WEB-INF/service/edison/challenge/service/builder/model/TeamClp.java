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

import edison.challenge.service.builder.service.ClpSerializer;
import edison.challenge.service.builder.service.TeamLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author kyj
 */
public class TeamClp extends BaseModelImpl<Team> implements Team {
	public TeamClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Team.class;
	}

	@Override
	public String getModelClassName() {
		return Team.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _teamid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTeamid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _teamid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("teamid", getTeamid());
		attributes.put("teamName", getTeamName());
		attributes.put("subject", getSubject());
		attributes.put("paperstatus", getPaperstatus());
		attributes.put("presentationstatus", getPresentationstatus());
		attributes.put("registerDay", getRegisterDay());
		attributes.put("registerid", getRegisterid());
		attributes.put("paperName", getPaperName());
		attributes.put("paperAbstract", getPaperAbstract());
		attributes.put("paperFileName", getPaperFileName());
		attributes.put("paperSubmissionDay", getPaperSubmissionDay());
		attributes.put("paperModifyDay", getPaperModifyDay());
		attributes.put("presentationName", getPresentationName());
		attributes.put("presentationFileName", getPresentationFileName());
		attributes.put("presentationSubmissionDay",
			getPresentationSubmissionDay());
		attributes.put("presentationModifyDay", getPresentationModifyDay());
		attributes.put("filepath", getFilepath());
		attributes.put("childid", getChildid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long teamid = (Long)attributes.get("teamid");

		if (teamid != null) {
			setTeamid(teamid);
		}

		String teamName = (String)attributes.get("teamName");

		if (teamName != null) {
			setTeamName(teamName);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Boolean paperstatus = (Boolean)attributes.get("paperstatus");

		if (paperstatus != null) {
			setPaperstatus(paperstatus);
		}

		Boolean presentationstatus = (Boolean)attributes.get(
				"presentationstatus");

		if (presentationstatus != null) {
			setPresentationstatus(presentationstatus);
		}

		Date registerDay = (Date)attributes.get("registerDay");

		if (registerDay != null) {
			setRegisterDay(registerDay);
		}

		String registerid = (String)attributes.get("registerid");

		if (registerid != null) {
			setRegisterid(registerid);
		}

		String paperName = (String)attributes.get("paperName");

		if (paperName != null) {
			setPaperName(paperName);
		}

		String paperAbstract = (String)attributes.get("paperAbstract");

		if (paperAbstract != null) {
			setPaperAbstract(paperAbstract);
		}

		String paperFileName = (String)attributes.get("paperFileName");

		if (paperFileName != null) {
			setPaperFileName(paperFileName);
		}

		Date paperSubmissionDay = (Date)attributes.get("paperSubmissionDay");

		if (paperSubmissionDay != null) {
			setPaperSubmissionDay(paperSubmissionDay);
		}

		Date paperModifyDay = (Date)attributes.get("paperModifyDay");

		if (paperModifyDay != null) {
			setPaperModifyDay(paperModifyDay);
		}

		String presentationName = (String)attributes.get("presentationName");

		if (presentationName != null) {
			setPresentationName(presentationName);
		}

		String presentationFileName = (String)attributes.get(
				"presentationFileName");

		if (presentationFileName != null) {
			setPresentationFileName(presentationFileName);
		}

		Date presentationSubmissionDay = (Date)attributes.get(
				"presentationSubmissionDay");

		if (presentationSubmissionDay != null) {
			setPresentationSubmissionDay(presentationSubmissionDay);
		}

		Date presentationModifyDay = (Date)attributes.get(
				"presentationModifyDay");

		if (presentationModifyDay != null) {
			setPresentationModifyDay(presentationModifyDay);
		}

		String filepath = (String)attributes.get("filepath");

		if (filepath != null) {
			setFilepath(filepath);
		}

		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
		}
	}

	@Override
	public long getTeamid() {
		return _teamid;
	}

	@Override
	public void setTeamid(long teamid) {
		_teamid = teamid;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setTeamid", long.class);

				method.invoke(_teamRemoteModel, teamid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTeamName() {
		return _teamName;
	}

	@Override
	public String getTeamName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTeamName(languageId);
	}

	@Override
	public String getTeamName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTeamName(languageId, useDefault);
	}

	@Override
	public String getTeamName(String languageId) {
		return LocalizationUtil.getLocalization(getTeamName(), languageId);
	}

	@Override
	public String getTeamName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTeamName(), languageId,
			useDefault);
	}

	@Override
	public String getTeamNameCurrentLanguageId() {
		return _teamNameCurrentLanguageId;
	}

	@Override
	public String getTeamNameCurrentValue() {
		Locale locale = getLocale(_teamNameCurrentLanguageId);

		return getTeamName(locale);
	}

	@Override
	public Map<Locale, String> getTeamNameMap() {
		return LocalizationUtil.getLocalizationMap(getTeamName());
	}

	@Override
	public void setTeamName(String teamName) {
		_teamName = teamName;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setTeamName", String.class);

				method.invoke(_teamRemoteModel, teamName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setTeamName(String teamName, Locale locale) {
		setTeamName(teamName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTeamName(String teamName, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(teamName)) {
			setTeamName(LocalizationUtil.updateLocalization(getTeamName(),
					"TeamName", teamName, languageId, defaultLanguageId));
		}
		else {
			setTeamName(LocalizationUtil.removeLocalization(getTeamName(),
					"TeamName", languageId));
		}
	}

	@Override
	public void setTeamNameCurrentLanguageId(String languageId) {
		_teamNameCurrentLanguageId = languageId;
	}

	@Override
	public void setTeamNameMap(Map<Locale, String> teamNameMap) {
		setTeamNameMap(teamNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTeamNameMap(Map<Locale, String> teamNameMap,
		Locale defaultLocale) {
		if (teamNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setTeamName(LocalizationUtil.updateLocalization(teamNameMap,
					getTeamName(), "TeamName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getSubject() {
		return _subject;
	}

	@Override
	public String getSubject(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getSubject(languageId);
	}

	@Override
	public String getSubject(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getSubject(languageId, useDefault);
	}

	@Override
	public String getSubject(String languageId) {
		return LocalizationUtil.getLocalization(getSubject(), languageId);
	}

	@Override
	public String getSubject(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getSubject(), languageId,
			useDefault);
	}

	@Override
	public String getSubjectCurrentLanguageId() {
		return _subjectCurrentLanguageId;
	}

	@Override
	public String getSubjectCurrentValue() {
		Locale locale = getLocale(_subjectCurrentLanguageId);

		return getSubject(locale);
	}

	@Override
	public Map<Locale, String> getSubjectMap() {
		return LocalizationUtil.getLocalizationMap(getSubject());
	}

	@Override
	public void setSubject(String subject) {
		_subject = subject;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setSubject", String.class);

				method.invoke(_teamRemoteModel, subject);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setSubject(String subject, Locale locale) {
		setSubject(subject, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setSubject(String subject, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(subject)) {
			setSubject(LocalizationUtil.updateLocalization(getSubject(),
					"Subject", subject, languageId, defaultLanguageId));
		}
		else {
			setSubject(LocalizationUtil.removeLocalization(getSubject(),
					"Subject", languageId));
		}
	}

	@Override
	public void setSubjectCurrentLanguageId(String languageId) {
		_subjectCurrentLanguageId = languageId;
	}

	@Override
	public void setSubjectMap(Map<Locale, String> subjectMap) {
		setSubjectMap(subjectMap, LocaleUtil.getDefault());
	}

	@Override
	public void setSubjectMap(Map<Locale, String> subjectMap,
		Locale defaultLocale) {
		if (subjectMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setSubject(LocalizationUtil.updateLocalization(subjectMap,
					getSubject(), "Subject",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public boolean getPaperstatus() {
		return _paperstatus;
	}

	@Override
	public boolean isPaperstatus() {
		return _paperstatus;
	}

	@Override
	public void setPaperstatus(boolean paperstatus) {
		_paperstatus = paperstatus;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperstatus", boolean.class);

				method.invoke(_teamRemoteModel, paperstatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPresentationstatus() {
		return _presentationstatus;
	}

	@Override
	public boolean isPresentationstatus() {
		return _presentationstatus;
	}

	@Override
	public void setPresentationstatus(boolean presentationstatus) {
		_presentationstatus = presentationstatus;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationstatus",
						boolean.class);

				method.invoke(_teamRemoteModel, presentationstatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getRegisterDay() {
		return _registerDay;
	}

	@Override
	public void setRegisterDay(Date registerDay) {
		_registerDay = registerDay;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setRegisterDay", Date.class);

				method.invoke(_teamRemoteModel, registerDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRegisterid() {
		return _registerid;
	}

	@Override
	public void setRegisterid(String registerid) {
		_registerid = registerid;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setRegisterid", String.class);

				method.invoke(_teamRemoteModel, registerid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPaperName() {
		return _paperName;
	}

	@Override
	public String getPaperName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaperName(languageId);
	}

	@Override
	public String getPaperName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaperName(languageId, useDefault);
	}

	@Override
	public String getPaperName(String languageId) {
		return LocalizationUtil.getLocalization(getPaperName(), languageId);
	}

	@Override
	public String getPaperName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getPaperName(), languageId,
			useDefault);
	}

	@Override
	public String getPaperNameCurrentLanguageId() {
		return _paperNameCurrentLanguageId;
	}

	@Override
	public String getPaperNameCurrentValue() {
		Locale locale = getLocale(_paperNameCurrentLanguageId);

		return getPaperName(locale);
	}

	@Override
	public Map<Locale, String> getPaperNameMap() {
		return LocalizationUtil.getLocalizationMap(getPaperName());
	}

	@Override
	public void setPaperName(String paperName) {
		_paperName = paperName;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperName", String.class);

				method.invoke(_teamRemoteModel, paperName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setPaperName(String paperName, Locale locale) {
		setPaperName(paperName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setPaperName(String paperName, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(paperName)) {
			setPaperName(LocalizationUtil.updateLocalization(getPaperName(),
					"PaperName", paperName, languageId, defaultLanguageId));
		}
		else {
			setPaperName(LocalizationUtil.removeLocalization(getPaperName(),
					"PaperName", languageId));
		}
	}

	@Override
	public void setPaperNameCurrentLanguageId(String languageId) {
		_paperNameCurrentLanguageId = languageId;
	}

	@Override
	public void setPaperNameMap(Map<Locale, String> paperNameMap) {
		setPaperNameMap(paperNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setPaperNameMap(Map<Locale, String> paperNameMap,
		Locale defaultLocale) {
		if (paperNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setPaperName(LocalizationUtil.updateLocalization(paperNameMap,
					getPaperName(), "PaperName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPaperAbstract() {
		return _paperAbstract;
	}

	@Override
	public String getPaperAbstract(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaperAbstract(languageId);
	}

	@Override
	public String getPaperAbstract(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaperAbstract(languageId, useDefault);
	}

	@Override
	public String getPaperAbstract(String languageId) {
		return LocalizationUtil.getLocalization(getPaperAbstract(), languageId);
	}

	@Override
	public String getPaperAbstract(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getPaperAbstract(), languageId,
			useDefault);
	}

	@Override
	public String getPaperAbstractCurrentLanguageId() {
		return _paperAbstractCurrentLanguageId;
	}

	@Override
	public String getPaperAbstractCurrentValue() {
		Locale locale = getLocale(_paperAbstractCurrentLanguageId);

		return getPaperAbstract(locale);
	}

	@Override
	public Map<Locale, String> getPaperAbstractMap() {
		return LocalizationUtil.getLocalizationMap(getPaperAbstract());
	}

	@Override
	public void setPaperAbstract(String paperAbstract) {
		_paperAbstract = paperAbstract;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperAbstract", String.class);

				method.invoke(_teamRemoteModel, paperAbstract);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setPaperAbstract(String paperAbstract, Locale locale) {
		setPaperAbstract(paperAbstract, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setPaperAbstract(String paperAbstract, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(paperAbstract)) {
			setPaperAbstract(LocalizationUtil.updateLocalization(
					getPaperAbstract(), "PaperAbstract", paperAbstract,
					languageId, defaultLanguageId));
		}
		else {
			setPaperAbstract(LocalizationUtil.removeLocalization(
					getPaperAbstract(), "PaperAbstract", languageId));
		}
	}

	@Override
	public void setPaperAbstractCurrentLanguageId(String languageId) {
		_paperAbstractCurrentLanguageId = languageId;
	}

	@Override
	public void setPaperAbstractMap(Map<Locale, String> paperAbstractMap) {
		setPaperAbstractMap(paperAbstractMap, LocaleUtil.getDefault());
	}

	@Override
	public void setPaperAbstractMap(Map<Locale, String> paperAbstractMap,
		Locale defaultLocale) {
		if (paperAbstractMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setPaperAbstract(LocalizationUtil.updateLocalization(
					paperAbstractMap, getPaperAbstract(), "PaperAbstract",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPaperFileName() {
		return _paperFileName;
	}

	@Override
	public void setPaperFileName(String paperFileName) {
		_paperFileName = paperFileName;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperFileName", String.class);

				method.invoke(_teamRemoteModel, paperFileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperSubmissionDay() {
		return _paperSubmissionDay;
	}

	@Override
	public void setPaperSubmissionDay(Date paperSubmissionDay) {
		_paperSubmissionDay = paperSubmissionDay;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperSubmissionDay",
						Date.class);

				method.invoke(_teamRemoteModel, paperSubmissionDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperModifyDay() {
		return _paperModifyDay;
	}

	@Override
	public void setPaperModifyDay(Date paperModifyDay) {
		_paperModifyDay = paperModifyDay;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperModifyDay", Date.class);

				method.invoke(_teamRemoteModel, paperModifyDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPresentationName() {
		return _presentationName;
	}

	@Override
	public void setPresentationName(String presentationName) {
		_presentationName = presentationName;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationName",
						String.class);

				method.invoke(_teamRemoteModel, presentationName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPresentationFileName() {
		return _presentationFileName;
	}

	@Override
	public void setPresentationFileName(String presentationFileName) {
		_presentationFileName = presentationFileName;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationFileName",
						String.class);

				method.invoke(_teamRemoteModel, presentationFileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPresentationSubmissionDay() {
		return _presentationSubmissionDay;
	}

	@Override
	public void setPresentationSubmissionDay(Date presentationSubmissionDay) {
		_presentationSubmissionDay = presentationSubmissionDay;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationSubmissionDay",
						Date.class);

				method.invoke(_teamRemoteModel, presentationSubmissionDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPresentationModifyDay() {
		return _presentationModifyDay;
	}

	@Override
	public void setPresentationModifyDay(Date presentationModifyDay) {
		_presentationModifyDay = presentationModifyDay;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationModifyDay",
						Date.class);

				method.invoke(_teamRemoteModel, presentationModifyDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFilepath() {
		return _filepath;
	}

	@Override
	public void setFilepath(String filepath) {
		_filepath = filepath;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setFilepath", String.class);

				method.invoke(_teamRemoteModel, filepath);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChildid() {
		return _childid;
	}

	@Override
	public void setChildid(long childid) {
		_childid = childid;

		if (_teamRemoteModel != null) {
			try {
				Class<?> clazz = _teamRemoteModel.getClass();

				Method method = clazz.getMethod("setChildid", long.class);

				method.invoke(_teamRemoteModel, childid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTeamRemoteModel() {
		return _teamRemoteModel;
	}

	public void setTeamRemoteModel(BaseModel<?> teamRemoteModel) {
		_teamRemoteModel = teamRemoteModel;
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

		Class<?> remoteModelClass = _teamRemoteModel.getClass();

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

		Object returnValue = method.invoke(_teamRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			TeamLocalServiceUtil.addTeam(this);
		}
		else {
			TeamLocalServiceUtil.updateTeam(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> teamNameMap = getTeamNameMap();

		for (Map.Entry<Locale, String> entry : teamNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> subjectMap = getSubjectMap();

		for (Map.Entry<Locale, String> entry : subjectMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> paperNameMap = getPaperNameMap();

		for (Map.Entry<Locale, String> entry : paperNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> paperAbstractMap = getPaperAbstractMap();

		for (Map.Entry<Locale, String> entry : paperAbstractMap.entrySet()) {
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
		String xml = getTeamName();

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

		String teamName = getTeamName(defaultLocale);

		if (Validator.isNull(teamName)) {
			setTeamName(getTeamName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTeamName(getTeamName(defaultLocale), defaultLocale, defaultLocale);
		}

		String subject = getSubject(defaultLocale);

		if (Validator.isNull(subject)) {
			setSubject(getSubject(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setSubject(getSubject(defaultLocale), defaultLocale, defaultLocale);
		}

		String paperName = getPaperName(defaultLocale);

		if (Validator.isNull(paperName)) {
			setPaperName(getPaperName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setPaperName(getPaperName(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String paperAbstract = getPaperAbstract(defaultLocale);

		if (Validator.isNull(paperAbstract)) {
			setPaperAbstract(getPaperAbstract(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setPaperAbstract(getPaperAbstract(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public Team toEscapedModel() {
		return (Team)ProxyUtil.newProxyInstance(Team.class.getClassLoader(),
			new Class[] { Team.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TeamClp clone = new TeamClp();

		clone.setTeamid(getTeamid());
		clone.setTeamName(getTeamName());
		clone.setSubject(getSubject());
		clone.setPaperstatus(getPaperstatus());
		clone.setPresentationstatus(getPresentationstatus());
		clone.setRegisterDay(getRegisterDay());
		clone.setRegisterid(getRegisterid());
		clone.setPaperName(getPaperName());
		clone.setPaperAbstract(getPaperAbstract());
		clone.setPaperFileName(getPaperFileName());
		clone.setPaperSubmissionDay(getPaperSubmissionDay());
		clone.setPaperModifyDay(getPaperModifyDay());
		clone.setPresentationName(getPresentationName());
		clone.setPresentationFileName(getPresentationFileName());
		clone.setPresentationSubmissionDay(getPresentationSubmissionDay());
		clone.setPresentationModifyDay(getPresentationModifyDay());
		clone.setFilepath(getFilepath());
		clone.setChildid(getChildid());

		return clone;
	}

	@Override
	public int compareTo(Team team) {
		int value = 0;

		if (getChildid() < team.getChildid()) {
			value = -1;
		}
		else if (getChildid() > team.getChildid()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TeamClp)) {
			return false;
		}

		TeamClp team = (TeamClp)obj;

		long primaryKey = team.getPrimaryKey();

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

		sb.append("{teamid=");
		sb.append(getTeamid());
		sb.append(", teamName=");
		sb.append(getTeamName());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", paperstatus=");
		sb.append(getPaperstatus());
		sb.append(", presentationstatus=");
		sb.append(getPresentationstatus());
		sb.append(", registerDay=");
		sb.append(getRegisterDay());
		sb.append(", registerid=");
		sb.append(getRegisterid());
		sb.append(", paperName=");
		sb.append(getPaperName());
		sb.append(", paperAbstract=");
		sb.append(getPaperAbstract());
		sb.append(", paperFileName=");
		sb.append(getPaperFileName());
		sb.append(", paperSubmissionDay=");
		sb.append(getPaperSubmissionDay());
		sb.append(", paperModifyDay=");
		sb.append(getPaperModifyDay());
		sb.append(", presentationName=");
		sb.append(getPresentationName());
		sb.append(", presentationFileName=");
		sb.append(getPresentationFileName());
		sb.append(", presentationSubmissionDay=");
		sb.append(getPresentationSubmissionDay());
		sb.append(", presentationModifyDay=");
		sb.append(getPresentationModifyDay());
		sb.append(", filepath=");
		sb.append(getFilepath());
		sb.append(", childid=");
		sb.append(getChildid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.Team");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>teamid</column-name><column-value><![CDATA[");
		sb.append(getTeamid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>teamName</column-name><column-value><![CDATA[");
		sb.append(getTeamName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperstatus</column-name><column-value><![CDATA[");
		sb.append(getPaperstatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationstatus</column-name><column-value><![CDATA[");
		sb.append(getPresentationstatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registerDay</column-name><column-value><![CDATA[");
		sb.append(getRegisterDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registerid</column-name><column-value><![CDATA[");
		sb.append(getRegisterid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperName</column-name><column-value><![CDATA[");
		sb.append(getPaperName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperAbstract</column-name><column-value><![CDATA[");
		sb.append(getPaperAbstract());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperFileName</column-name><column-value><![CDATA[");
		sb.append(getPaperFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperSubmissionDay</column-name><column-value><![CDATA[");
		sb.append(getPaperSubmissionDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperModifyDay</column-name><column-value><![CDATA[");
		sb.append(getPaperModifyDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationName</column-name><column-value><![CDATA[");
		sb.append(getPresentationName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationFileName</column-name><column-value><![CDATA[");
		sb.append(getPresentationFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationSubmissionDay</column-name><column-value><![CDATA[");
		sb.append(getPresentationSubmissionDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationModifyDay</column-name><column-value><![CDATA[");
		sb.append(getPresentationModifyDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>filepath</column-name><column-value><![CDATA[");
		sb.append(getFilepath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>childid</column-name><column-value><![CDATA[");
		sb.append(getChildid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _teamid;
	private String _teamName;
	private String _teamNameCurrentLanguageId;
	private String _subject;
	private String _subjectCurrentLanguageId;
	private boolean _paperstatus;
	private boolean _presentationstatus;
	private Date _registerDay;
	private String _registerid;
	private String _paperName;
	private String _paperNameCurrentLanguageId;
	private String _paperAbstract;
	private String _paperAbstractCurrentLanguageId;
	private String _paperFileName;
	private Date _paperSubmissionDay;
	private Date _paperModifyDay;
	private String _presentationName;
	private String _presentationFileName;
	private Date _presentationSubmissionDay;
	private Date _presentationModifyDay;
	private String _filepath;
	private long _childid;
	private BaseModel<?> _teamRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}