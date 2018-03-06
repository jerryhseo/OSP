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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author KYJ
 */
public class ChallengeTeamClp extends BaseModelImpl<ChallengeTeam>
	implements ChallengeTeam {
	public ChallengeTeamClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeTeam.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeTeam.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _challengeTeamId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChallengeTeamId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _challengeTeamId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeTeamId", getChallengeTeamId());
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
		attributes.put("teamName", getTeamName());
		attributes.put("subject", getSubject());
		attributes.put("paperName", getPaperName());
		attributes.put("paperAbstract", getPaperAbstract());
		attributes.put("paperFileName", getPaperFileName());
		attributes.put("paperSubmissionDay", getPaperSubmissionDay());
		attributes.put("paperModifyDay", getPaperModifyDay());
		attributes.put("paperStatusDOC", getPaperStatusDOC());
		attributes.put("paperPDFFileName", getPaperPDFFileName());
		attributes.put("paperPDFSubmissionDay", getPaperPDFSubmissionDay());
		attributes.put("paperPDFModifyDay", getPaperPDFModifyDay());
		attributes.put("paperStatusPDF", getPaperStatusPDF());
		attributes.put("presentationName", getPresentationName());
		attributes.put("presentationFileName", getPresentationFileName());
		attributes.put("presentationSubmissionDay",
			getPresentationSubmissionDay());
		attributes.put("presentationModifyDay", getPresentationModifyDay());
		attributes.put("presentationStatus", getPresentationStatus());
		attributes.put("filepath", getFilepath());
		attributes.put("aggrement", getAggrement());
		attributes.put("childChallengeId", getChildChallengeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeTeamId = (Long)attributes.get("challengeTeamId");

		if (challengeTeamId != null) {
			setChallengeTeamId(challengeTeamId);
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

		String teamName = (String)attributes.get("teamName");

		if (teamName != null) {
			setTeamName(teamName);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
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

		Boolean paperStatusDOC = (Boolean)attributes.get("paperStatusDOC");

		if (paperStatusDOC != null) {
			setPaperStatusDOC(paperStatusDOC);
		}

		String paperPDFFileName = (String)attributes.get("paperPDFFileName");

		if (paperPDFFileName != null) {
			setPaperPDFFileName(paperPDFFileName);
		}

		Date paperPDFSubmissionDay = (Date)attributes.get(
				"paperPDFSubmissionDay");

		if (paperPDFSubmissionDay != null) {
			setPaperPDFSubmissionDay(paperPDFSubmissionDay);
		}

		Date paperPDFModifyDay = (Date)attributes.get("paperPDFModifyDay");

		if (paperPDFModifyDay != null) {
			setPaperPDFModifyDay(paperPDFModifyDay);
		}

		Boolean paperStatusPDF = (Boolean)attributes.get("paperStatusPDF");

		if (paperStatusPDF != null) {
			setPaperStatusPDF(paperStatusPDF);
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

		Boolean presentationStatus = (Boolean)attributes.get(
				"presentationStatus");

		if (presentationStatus != null) {
			setPresentationStatus(presentationStatus);
		}

		String filepath = (String)attributes.get("filepath");

		if (filepath != null) {
			setFilepath(filepath);
		}

		Boolean aggrement = (Boolean)attributes.get("aggrement");

		if (aggrement != null) {
			setAggrement(aggrement);
		}

		Long childChallengeId = (Long)attributes.get("childChallengeId");

		if (childChallengeId != null) {
			setChildChallengeId(childChallengeId);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_challengeTeamRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeTeamId() {
		return _challengeTeamId;
	}

	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamId = challengeTeamId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeTeamId", long.class);

				method.invoke(_challengeTeamRemoteModel, challengeTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_challengeTeamRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_challengeTeamRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_challengeTeamRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_challengeTeamRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_challengeTeamRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_challengeTeamRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_challengeTeamRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_challengeTeamRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	@Override
	public String getStatusByUserName() {
		return _statusByUserName;
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_challengeTeamRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_challengeTeamRemoteModel, statusDate);
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
	public void setTeamName(String teamName) {
		_teamName = teamName;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setTeamName", String.class);

				method.invoke(_challengeTeamRemoteModel, teamName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setSubject", String.class);

				method.invoke(_challengeTeamRemoteModel, subject);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperName", String.class);

				method.invoke(_challengeTeamRemoteModel, paperName);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperAbstract", String.class);

				method.invoke(_challengeTeamRemoteModel, paperAbstract);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperFileName", String.class);

				method.invoke(_challengeTeamRemoteModel, paperFileName);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperSubmissionDay",
						Date.class);

				method.invoke(_challengeTeamRemoteModel, paperSubmissionDay);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperModifyDay", Date.class);

				method.invoke(_challengeTeamRemoteModel, paperModifyDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPaperStatusDOC() {
		return _paperStatusDOC;
	}

	@Override
	public boolean isPaperStatusDOC() {
		return _paperStatusDOC;
	}

	@Override
	public void setPaperStatusDOC(boolean paperStatusDOC) {
		_paperStatusDOC = paperStatusDOC;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperStatusDOC",
						boolean.class);

				method.invoke(_challengeTeamRemoteModel, paperStatusDOC);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPaperPDFFileName() {
		return _paperPDFFileName;
	}

	@Override
	public void setPaperPDFFileName(String paperPDFFileName) {
		_paperPDFFileName = paperPDFFileName;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperPDFFileName",
						String.class);

				method.invoke(_challengeTeamRemoteModel, paperPDFFileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperPDFSubmissionDay() {
		return _paperPDFSubmissionDay;
	}

	@Override
	public void setPaperPDFSubmissionDay(Date paperPDFSubmissionDay) {
		_paperPDFSubmissionDay = paperPDFSubmissionDay;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperPDFSubmissionDay",
						Date.class);

				method.invoke(_challengeTeamRemoteModel, paperPDFSubmissionDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperPDFModifyDay() {
		return _paperPDFModifyDay;
	}

	@Override
	public void setPaperPDFModifyDay(Date paperPDFModifyDay) {
		_paperPDFModifyDay = paperPDFModifyDay;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperPDFModifyDay",
						Date.class);

				method.invoke(_challengeTeamRemoteModel, paperPDFModifyDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPaperStatusPDF() {
		return _paperStatusPDF;
	}

	@Override
	public boolean isPaperStatusPDF() {
		return _paperStatusPDF;
	}

	@Override
	public void setPaperStatusPDF(boolean paperStatusPDF) {
		_paperStatusPDF = paperStatusPDF;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperStatusPDF",
						boolean.class);

				method.invoke(_challengeTeamRemoteModel, paperStatusPDF);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationName",
						String.class);

				method.invoke(_challengeTeamRemoteModel, presentationName);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationFileName",
						String.class);

				method.invoke(_challengeTeamRemoteModel, presentationFileName);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationSubmissionDay",
						Date.class);

				method.invoke(_challengeTeamRemoteModel,
					presentationSubmissionDay);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationModifyDay",
						Date.class);

				method.invoke(_challengeTeamRemoteModel, presentationModifyDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPresentationStatus() {
		return _presentationStatus;
	}

	@Override
	public boolean isPresentationStatus() {
		return _presentationStatus;
	}

	@Override
	public void setPresentationStatus(boolean presentationStatus) {
		_presentationStatus = presentationStatus;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationStatus",
						boolean.class);

				method.invoke(_challengeTeamRemoteModel, presentationStatus);
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

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setFilepath", String.class);

				method.invoke(_challengeTeamRemoteModel, filepath);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAggrement() {
		return _aggrement;
	}

	@Override
	public boolean isAggrement() {
		return _aggrement;
	}

	@Override
	public void setAggrement(boolean aggrement) {
		_aggrement = aggrement;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setAggrement", boolean.class);

				method.invoke(_challengeTeamRemoteModel, aggrement);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChildChallengeId() {
		return _childChallengeId;
	}

	@Override
	public void setChildChallengeId(long childChallengeId) {
		_childChallengeId = childChallengeId;

		if (_challengeTeamRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamRemoteModel.getClass();

				Method method = clazz.getMethod("setChildChallengeId",
						long.class);

				method.invoke(_challengeTeamRemoteModel, childChallengeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ChallengeTeam.class.getName()));
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved}
	 */
	@Override
	public boolean getApproved() {
		return isApproved();
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getChallengeTeamRemoteModel() {
		return _challengeTeamRemoteModel;
	}

	public void setChallengeTeamRemoteModel(
		BaseModel<?> challengeTeamRemoteModel) {
		_challengeTeamRemoteModel = challengeTeamRemoteModel;
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

		Class<?> remoteModelClass = _challengeTeamRemoteModel.getClass();

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

		Object returnValue = method.invoke(_challengeTeamRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChallengeTeamLocalServiceUtil.addChallengeTeam(this);
		}
		else {
			ChallengeTeamLocalServiceUtil.updateChallengeTeam(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

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
		String xml = getSubject();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

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
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

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
	public ChallengeTeam toEscapedModel() {
		return (ChallengeTeam)ProxyUtil.newProxyInstance(ChallengeTeam.class.getClassLoader(),
			new Class[] { ChallengeTeam.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ChallengeTeamClp clone = new ChallengeTeamClp();

		clone.setUuid(getUuid());
		clone.setChallengeTeamId(getChallengeTeamId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setTeamName(getTeamName());
		clone.setSubject(getSubject());
		clone.setPaperName(getPaperName());
		clone.setPaperAbstract(getPaperAbstract());
		clone.setPaperFileName(getPaperFileName());
		clone.setPaperSubmissionDay(getPaperSubmissionDay());
		clone.setPaperModifyDay(getPaperModifyDay());
		clone.setPaperStatusDOC(getPaperStatusDOC());
		clone.setPaperPDFFileName(getPaperPDFFileName());
		clone.setPaperPDFSubmissionDay(getPaperPDFSubmissionDay());
		clone.setPaperPDFModifyDay(getPaperPDFModifyDay());
		clone.setPaperStatusPDF(getPaperStatusPDF());
		clone.setPresentationName(getPresentationName());
		clone.setPresentationFileName(getPresentationFileName());
		clone.setPresentationSubmissionDay(getPresentationSubmissionDay());
		clone.setPresentationModifyDay(getPresentationModifyDay());
		clone.setPresentationStatus(getPresentationStatus());
		clone.setFilepath(getFilepath());
		clone.setAggrement(getAggrement());
		clone.setChildChallengeId(getChildChallengeId());

		return clone;
	}

	@Override
	public int compareTo(ChallengeTeam challengeTeam) {
		int value = 0;

		if (getChallengeTeamId() < challengeTeam.getChallengeTeamId()) {
			value = -1;
		}
		else if (getChallengeTeamId() > challengeTeam.getChallengeTeamId()) {
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

		if (!(obj instanceof ChallengeTeamClp)) {
			return false;
		}

		ChallengeTeamClp challengeTeam = (ChallengeTeamClp)obj;

		long primaryKey = challengeTeam.getPrimaryKey();

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
		StringBundler sb = new StringBundler(65);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", challengeTeamId=");
		sb.append(getChallengeTeamId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", teamName=");
		sb.append(getTeamName());
		sb.append(", subject=");
		sb.append(getSubject());
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
		sb.append(", paperStatusDOC=");
		sb.append(getPaperStatusDOC());
		sb.append(", paperPDFFileName=");
		sb.append(getPaperPDFFileName());
		sb.append(", paperPDFSubmissionDay=");
		sb.append(getPaperPDFSubmissionDay());
		sb.append(", paperPDFModifyDay=");
		sb.append(getPaperPDFModifyDay());
		sb.append(", paperStatusPDF=");
		sb.append(getPaperStatusPDF());
		sb.append(", presentationName=");
		sb.append(getPresentationName());
		sb.append(", presentationFileName=");
		sb.append(getPresentationFileName());
		sb.append(", presentationSubmissionDay=");
		sb.append(getPresentationSubmissionDay());
		sb.append(", presentationModifyDay=");
		sb.append(getPresentationModifyDay());
		sb.append(", presentationStatus=");
		sb.append(getPresentationStatus());
		sb.append(", filepath=");
		sb.append(getFilepath());
		sb.append(", aggrement=");
		sb.append(getAggrement());
		sb.append(", childChallengeId=");
		sb.append(getChildChallengeId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(100);

		sb.append("<model><model-name>");
		sb.append("kisti.edison.challenge.model.ChallengeTeam");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeTeamId</column-name><column-value><![CDATA[");
		sb.append(getChallengeTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
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
			"<column><column-name>paperStatusDOC</column-name><column-value><![CDATA[");
		sb.append(getPaperStatusDOC());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperPDFFileName</column-name><column-value><![CDATA[");
		sb.append(getPaperPDFFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperPDFSubmissionDay</column-name><column-value><![CDATA[");
		sb.append(getPaperPDFSubmissionDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperPDFModifyDay</column-name><column-value><![CDATA[");
		sb.append(getPaperPDFModifyDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperStatusPDF</column-name><column-value><![CDATA[");
		sb.append(getPaperStatusPDF());
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
			"<column><column-name>presentationStatus</column-name><column-value><![CDATA[");
		sb.append(getPresentationStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>filepath</column-name><column-value><![CDATA[");
		sb.append(getFilepath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>aggrement</column-name><column-value><![CDATA[");
		sb.append(getAggrement());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>childChallengeId</column-name><column-value><![CDATA[");
		sb.append(getChildChallengeId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _challengeTeamId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
	private String _teamName;
	private String _subject;
	private String _subjectCurrentLanguageId;
	private String _paperName;
	private String _paperNameCurrentLanguageId;
	private String _paperAbstract;
	private String _paperAbstractCurrentLanguageId;
	private String _paperFileName;
	private Date _paperSubmissionDay;
	private Date _paperModifyDay;
	private boolean _paperStatusDOC;
	private String _paperPDFFileName;
	private Date _paperPDFSubmissionDay;
	private Date _paperPDFModifyDay;
	private boolean _paperStatusPDF;
	private String _presentationName;
	private String _presentationFileName;
	private Date _presentationSubmissionDay;
	private Date _presentationModifyDay;
	private boolean _presentationStatus;
	private String _filepath;
	private boolean _aggrement;
	private long _childChallengeId;
	private BaseModel<?> _challengeTeamRemoteModel;
	private Class<?> _clpSerializerClass = kisti.edison.challenge.service.ClpSerializer.class;
}