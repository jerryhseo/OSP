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
 * This class is a wrapper for {@link ChallengeTeam}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeam
 * @generated
 */
public class ChallengeTeamWrapper implements ChallengeTeam,
	ModelWrapper<ChallengeTeam> {
	public ChallengeTeamWrapper(ChallengeTeam challengeTeam) {
		_challengeTeam = challengeTeam;
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

	/**
	* Returns the primary key of this challenge team.
	*
	* @return the primary key of this challenge team
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeTeam.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge team.
	*
	* @param primaryKey the primary key of this challenge team
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeTeam.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge team.
	*
	* @return the uuid of this challenge team
	*/
	@Override
	public java.lang.String getUuid() {
		return _challengeTeam.getUuid();
	}

	/**
	* Sets the uuid of this challenge team.
	*
	* @param uuid the uuid of this challenge team
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challengeTeam.setUuid(uuid);
	}

	/**
	* Returns the challenge team ID of this challenge team.
	*
	* @return the challenge team ID of this challenge team
	*/
	@Override
	public long getChallengeTeamId() {
		return _challengeTeam.getChallengeTeamId();
	}

	/**
	* Sets the challenge team ID of this challenge team.
	*
	* @param challengeTeamId the challenge team ID of this challenge team
	*/
	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeam.setChallengeTeamId(challengeTeamId);
	}

	/**
	* Returns the group ID of this challenge team.
	*
	* @return the group ID of this challenge team
	*/
	@Override
	public long getGroupId() {
		return _challengeTeam.getGroupId();
	}

	/**
	* Sets the group ID of this challenge team.
	*
	* @param groupId the group ID of this challenge team
	*/
	@Override
	public void setGroupId(long groupId) {
		_challengeTeam.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge team.
	*
	* @return the company ID of this challenge team
	*/
	@Override
	public long getCompanyId() {
		return _challengeTeam.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge team.
	*
	* @param companyId the company ID of this challenge team
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challengeTeam.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge team.
	*
	* @return the user ID of this challenge team
	*/
	@Override
	public long getUserId() {
		return _challengeTeam.getUserId();
	}

	/**
	* Sets the user ID of this challenge team.
	*
	* @param userId the user ID of this challenge team
	*/
	@Override
	public void setUserId(long userId) {
		_challengeTeam.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge team.
	*
	* @return the user uuid of this challenge team
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeam.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge team.
	*
	* @param userUuid the user uuid of this challenge team
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challengeTeam.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge team.
	*
	* @return the user name of this challenge team
	*/
	@Override
	public java.lang.String getUserName() {
		return _challengeTeam.getUserName();
	}

	/**
	* Sets the user name of this challenge team.
	*
	* @param userName the user name of this challenge team
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challengeTeam.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge team.
	*
	* @return the create date of this challenge team
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challengeTeam.getCreateDate();
	}

	/**
	* Sets the create date of this challenge team.
	*
	* @param createDate the create date of this challenge team
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challengeTeam.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge team.
	*
	* @return the modified date of this challenge team
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challengeTeam.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge team.
	*
	* @param modifiedDate the modified date of this challenge team
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challengeTeam.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge team.
	*
	* @return the status of this challenge team
	*/
	@Override
	public int getStatus() {
		return _challengeTeam.getStatus();
	}

	/**
	* Sets the status of this challenge team.
	*
	* @param status the status of this challenge team
	*/
	@Override
	public void setStatus(int status) {
		_challengeTeam.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge team.
	*
	* @return the status by user ID of this challenge team
	*/
	@Override
	public long getStatusByUserId() {
		return _challengeTeam.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge team.
	*
	* @param statusByUserId the status by user ID of this challenge team
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challengeTeam.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge team.
	*
	* @return the status by user uuid of this challenge team
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeam.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge team.
	*
	* @param statusByUserUuid the status by user uuid of this challenge team
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challengeTeam.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge team.
	*
	* @return the status by user name of this challenge team
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challengeTeam.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge team.
	*
	* @param statusByUserName the status by user name of this challenge team
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challengeTeam.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge team.
	*
	* @return the status date of this challenge team
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challengeTeam.getStatusDate();
	}

	/**
	* Sets the status date of this challenge team.
	*
	* @param statusDate the status date of this challenge team
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challengeTeam.setStatusDate(statusDate);
	}

	/**
	* Returns the team name of this challenge team.
	*
	* @return the team name of this challenge team
	*/
	@Override
	public java.lang.String getTeamName() {
		return _challengeTeam.getTeamName();
	}

	/**
	* Sets the team name of this challenge team.
	*
	* @param teamName the team name of this challenge team
	*/
	@Override
	public void setTeamName(java.lang.String teamName) {
		_challengeTeam.setTeamName(teamName);
	}

	/**
	* Returns the subject of this challenge team.
	*
	* @return the subject of this challenge team
	*/
	@Override
	public java.lang.String getSubject() {
		return _challengeTeam.getSubject();
	}

	/**
	* Returns the localized subject of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subject of this challenge team
	*/
	@Override
	public java.lang.String getSubject(java.util.Locale locale) {
		return _challengeTeam.getSubject(locale);
	}

	/**
	* Returns the localized subject of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subject of this challenge team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSubject(java.util.Locale locale,
		boolean useDefault) {
		return _challengeTeam.getSubject(locale, useDefault);
	}

	/**
	* Returns the localized subject of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subject of this challenge team
	*/
	@Override
	public java.lang.String getSubject(java.lang.String languageId) {
		return _challengeTeam.getSubject(languageId);
	}

	/**
	* Returns the localized subject of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subject of this challenge team
	*/
	@Override
	public java.lang.String getSubject(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeam.getSubject(languageId, useDefault);
	}

	@Override
	public java.lang.String getSubjectCurrentLanguageId() {
		return _challengeTeam.getSubjectCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubjectCurrentValue() {
		return _challengeTeam.getSubjectCurrentValue();
	}

	/**
	* Returns a map of the locales and localized subjects of this challenge team.
	*
	* @return the locales and localized subjects of this challenge team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getSubjectMap() {
		return _challengeTeam.getSubjectMap();
	}

	/**
	* Sets the subject of this challenge team.
	*
	* @param subject the subject of this challenge team
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_challengeTeam.setSubject(subject);
	}

	/**
	* Sets the localized subject of this challenge team in the language.
	*
	* @param subject the localized subject of this challenge team
	* @param locale the locale of the language
	*/
	@Override
	public void setSubject(java.lang.String subject, java.util.Locale locale) {
		_challengeTeam.setSubject(subject, locale);
	}

	/**
	* Sets the localized subject of this challenge team in the language, and sets the default locale.
	*
	* @param subject the localized subject of this challenge team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubject(java.lang.String subject, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeTeam.setSubject(subject, locale, defaultLocale);
	}

	@Override
	public void setSubjectCurrentLanguageId(java.lang.String languageId) {
		_challengeTeam.setSubjectCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subjects of this challenge team from the map of locales and localized subjects.
	*
	* @param subjectMap the locales and localized subjects of this challenge team
	*/
	@Override
	public void setSubjectMap(
		java.util.Map<java.util.Locale, java.lang.String> subjectMap) {
		_challengeTeam.setSubjectMap(subjectMap);
	}

	/**
	* Sets the localized subjects of this challenge team from the map of locales and localized subjects, and sets the default locale.
	*
	* @param subjectMap the locales and localized subjects of this challenge team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubjectMap(
		java.util.Map<java.util.Locale, java.lang.String> subjectMap,
		java.util.Locale defaultLocale) {
		_challengeTeam.setSubjectMap(subjectMap, defaultLocale);
	}

	/**
	* Returns the paper name of this challenge team.
	*
	* @return the paper name of this challenge team
	*/
	@Override
	public java.lang.String getPaperName() {
		return _challengeTeam.getPaperName();
	}

	/**
	* Returns the localized paper name of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized paper name of this challenge team
	*/
	@Override
	public java.lang.String getPaperName(java.util.Locale locale) {
		return _challengeTeam.getPaperName(locale);
	}

	/**
	* Returns the localized paper name of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper name of this challenge team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPaperName(java.util.Locale locale,
		boolean useDefault) {
		return _challengeTeam.getPaperName(locale, useDefault);
	}

	/**
	* Returns the localized paper name of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized paper name of this challenge team
	*/
	@Override
	public java.lang.String getPaperName(java.lang.String languageId) {
		return _challengeTeam.getPaperName(languageId);
	}

	/**
	* Returns the localized paper name of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper name of this challenge team
	*/
	@Override
	public java.lang.String getPaperName(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeam.getPaperName(languageId, useDefault);
	}

	@Override
	public java.lang.String getPaperNameCurrentLanguageId() {
		return _challengeTeam.getPaperNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getPaperNameCurrentValue() {
		return _challengeTeam.getPaperNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized paper names of this challenge team.
	*
	* @return the locales and localized paper names of this challenge team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPaperNameMap() {
		return _challengeTeam.getPaperNameMap();
	}

	/**
	* Sets the paper name of this challenge team.
	*
	* @param paperName the paper name of this challenge team
	*/
	@Override
	public void setPaperName(java.lang.String paperName) {
		_challengeTeam.setPaperName(paperName);
	}

	/**
	* Sets the localized paper name of this challenge team in the language.
	*
	* @param paperName the localized paper name of this challenge team
	* @param locale the locale of the language
	*/
	@Override
	public void setPaperName(java.lang.String paperName, java.util.Locale locale) {
		_challengeTeam.setPaperName(paperName, locale);
	}

	/**
	* Sets the localized paper name of this challenge team in the language, and sets the default locale.
	*
	* @param paperName the localized paper name of this challenge team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperName(java.lang.String paperName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeTeam.setPaperName(paperName, locale, defaultLocale);
	}

	@Override
	public void setPaperNameCurrentLanguageId(java.lang.String languageId) {
		_challengeTeam.setPaperNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized paper names of this challenge team from the map of locales and localized paper names.
	*
	* @param paperNameMap the locales and localized paper names of this challenge team
	*/
	@Override
	public void setPaperNameMap(
		java.util.Map<java.util.Locale, java.lang.String> paperNameMap) {
		_challengeTeam.setPaperNameMap(paperNameMap);
	}

	/**
	* Sets the localized paper names of this challenge team from the map of locales and localized paper names, and sets the default locale.
	*
	* @param paperNameMap the locales and localized paper names of this challenge team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperNameMap(
		java.util.Map<java.util.Locale, java.lang.String> paperNameMap,
		java.util.Locale defaultLocale) {
		_challengeTeam.setPaperNameMap(paperNameMap, defaultLocale);
	}

	/**
	* Returns the paper abstract of this challenge team.
	*
	* @return the paper abstract of this challenge team
	*/
	@Override
	public java.lang.String getPaperAbstract() {
		return _challengeTeam.getPaperAbstract();
	}

	/**
	* Returns the localized paper abstract of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized paper abstract of this challenge team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.util.Locale locale) {
		return _challengeTeam.getPaperAbstract(locale);
	}

	/**
	* Returns the localized paper abstract of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper abstract of this challenge team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPaperAbstract(java.util.Locale locale,
		boolean useDefault) {
		return _challengeTeam.getPaperAbstract(locale, useDefault);
	}

	/**
	* Returns the localized paper abstract of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized paper abstract of this challenge team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.lang.String languageId) {
		return _challengeTeam.getPaperAbstract(languageId);
	}

	/**
	* Returns the localized paper abstract of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper abstract of this challenge team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeam.getPaperAbstract(languageId, useDefault);
	}

	@Override
	public java.lang.String getPaperAbstractCurrentLanguageId() {
		return _challengeTeam.getPaperAbstractCurrentLanguageId();
	}

	@Override
	public java.lang.String getPaperAbstractCurrentValue() {
		return _challengeTeam.getPaperAbstractCurrentValue();
	}

	/**
	* Returns a map of the locales and localized paper abstracts of this challenge team.
	*
	* @return the locales and localized paper abstracts of this challenge team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPaperAbstractMap() {
		return _challengeTeam.getPaperAbstractMap();
	}

	/**
	* Sets the paper abstract of this challenge team.
	*
	* @param paperAbstract the paper abstract of this challenge team
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract) {
		_challengeTeam.setPaperAbstract(paperAbstract);
	}

	/**
	* Sets the localized paper abstract of this challenge team in the language.
	*
	* @param paperAbstract the localized paper abstract of this challenge team
	* @param locale the locale of the language
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract,
		java.util.Locale locale) {
		_challengeTeam.setPaperAbstract(paperAbstract, locale);
	}

	/**
	* Sets the localized paper abstract of this challenge team in the language, and sets the default locale.
	*
	* @param paperAbstract the localized paper abstract of this challenge team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeTeam.setPaperAbstract(paperAbstract, locale, defaultLocale);
	}

	@Override
	public void setPaperAbstractCurrentLanguageId(java.lang.String languageId) {
		_challengeTeam.setPaperAbstractCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized paper abstracts of this challenge team from the map of locales and localized paper abstracts.
	*
	* @param paperAbstractMap the locales and localized paper abstracts of this challenge team
	*/
	@Override
	public void setPaperAbstractMap(
		java.util.Map<java.util.Locale, java.lang.String> paperAbstractMap) {
		_challengeTeam.setPaperAbstractMap(paperAbstractMap);
	}

	/**
	* Sets the localized paper abstracts of this challenge team from the map of locales and localized paper abstracts, and sets the default locale.
	*
	* @param paperAbstractMap the locales and localized paper abstracts of this challenge team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperAbstractMap(
		java.util.Map<java.util.Locale, java.lang.String> paperAbstractMap,
		java.util.Locale defaultLocale) {
		_challengeTeam.setPaperAbstractMap(paperAbstractMap, defaultLocale);
	}

	/**
	* Returns the paper file name of this challenge team.
	*
	* @return the paper file name of this challenge team
	*/
	@Override
	public java.lang.String getPaperFileName() {
		return _challengeTeam.getPaperFileName();
	}

	/**
	* Sets the paper file name of this challenge team.
	*
	* @param paperFileName the paper file name of this challenge team
	*/
	@Override
	public void setPaperFileName(java.lang.String paperFileName) {
		_challengeTeam.setPaperFileName(paperFileName);
	}

	/**
	* Returns the paper submission day of this challenge team.
	*
	* @return the paper submission day of this challenge team
	*/
	@Override
	public java.util.Date getPaperSubmissionDay() {
		return _challengeTeam.getPaperSubmissionDay();
	}

	/**
	* Sets the paper submission day of this challenge team.
	*
	* @param paperSubmissionDay the paper submission day of this challenge team
	*/
	@Override
	public void setPaperSubmissionDay(java.util.Date paperSubmissionDay) {
		_challengeTeam.setPaperSubmissionDay(paperSubmissionDay);
	}

	/**
	* Returns the paper modify day of this challenge team.
	*
	* @return the paper modify day of this challenge team
	*/
	@Override
	public java.util.Date getPaperModifyDay() {
		return _challengeTeam.getPaperModifyDay();
	}

	/**
	* Sets the paper modify day of this challenge team.
	*
	* @param paperModifyDay the paper modify day of this challenge team
	*/
	@Override
	public void setPaperModifyDay(java.util.Date paperModifyDay) {
		_challengeTeam.setPaperModifyDay(paperModifyDay);
	}

	/**
	* Returns the paper status d o c of this challenge team.
	*
	* @return the paper status d o c of this challenge team
	*/
	@Override
	public boolean getPaperStatusDOC() {
		return _challengeTeam.getPaperStatusDOC();
	}

	/**
	* Returns <code>true</code> if this challenge team is paper status d o c.
	*
	* @return <code>true</code> if this challenge team is paper status d o c; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaperStatusDOC() {
		return _challengeTeam.isPaperStatusDOC();
	}

	/**
	* Sets whether this challenge team is paper status d o c.
	*
	* @param paperStatusDOC the paper status d o c of this challenge team
	*/
	@Override
	public void setPaperStatusDOC(boolean paperStatusDOC) {
		_challengeTeam.setPaperStatusDOC(paperStatusDOC);
	}

	/**
	* Returns the paper p d f file name of this challenge team.
	*
	* @return the paper p d f file name of this challenge team
	*/
	@Override
	public java.lang.String getPaperPDFFileName() {
		return _challengeTeam.getPaperPDFFileName();
	}

	/**
	* Sets the paper p d f file name of this challenge team.
	*
	* @param paperPDFFileName the paper p d f file name of this challenge team
	*/
	@Override
	public void setPaperPDFFileName(java.lang.String paperPDFFileName) {
		_challengeTeam.setPaperPDFFileName(paperPDFFileName);
	}

	/**
	* Returns the paper p d f submission day of this challenge team.
	*
	* @return the paper p d f submission day of this challenge team
	*/
	@Override
	public java.util.Date getPaperPDFSubmissionDay() {
		return _challengeTeam.getPaperPDFSubmissionDay();
	}

	/**
	* Sets the paper p d f submission day of this challenge team.
	*
	* @param paperPDFSubmissionDay the paper p d f submission day of this challenge team
	*/
	@Override
	public void setPaperPDFSubmissionDay(java.util.Date paperPDFSubmissionDay) {
		_challengeTeam.setPaperPDFSubmissionDay(paperPDFSubmissionDay);
	}

	/**
	* Returns the paper p d f modify day of this challenge team.
	*
	* @return the paper p d f modify day of this challenge team
	*/
	@Override
	public java.util.Date getPaperPDFModifyDay() {
		return _challengeTeam.getPaperPDFModifyDay();
	}

	/**
	* Sets the paper p d f modify day of this challenge team.
	*
	* @param paperPDFModifyDay the paper p d f modify day of this challenge team
	*/
	@Override
	public void setPaperPDFModifyDay(java.util.Date paperPDFModifyDay) {
		_challengeTeam.setPaperPDFModifyDay(paperPDFModifyDay);
	}

	/**
	* Returns the paper status p d f of this challenge team.
	*
	* @return the paper status p d f of this challenge team
	*/
	@Override
	public boolean getPaperStatusPDF() {
		return _challengeTeam.getPaperStatusPDF();
	}

	/**
	* Returns <code>true</code> if this challenge team is paper status p d f.
	*
	* @return <code>true</code> if this challenge team is paper status p d f; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaperStatusPDF() {
		return _challengeTeam.isPaperStatusPDF();
	}

	/**
	* Sets whether this challenge team is paper status p d f.
	*
	* @param paperStatusPDF the paper status p d f of this challenge team
	*/
	@Override
	public void setPaperStatusPDF(boolean paperStatusPDF) {
		_challengeTeam.setPaperStatusPDF(paperStatusPDF);
	}

	/**
	* Returns the presentation name of this challenge team.
	*
	* @return the presentation name of this challenge team
	*/
	@Override
	public java.lang.String getPresentationName() {
		return _challengeTeam.getPresentationName();
	}

	/**
	* Sets the presentation name of this challenge team.
	*
	* @param presentationName the presentation name of this challenge team
	*/
	@Override
	public void setPresentationName(java.lang.String presentationName) {
		_challengeTeam.setPresentationName(presentationName);
	}

	/**
	* Returns the presentation file name of this challenge team.
	*
	* @return the presentation file name of this challenge team
	*/
	@Override
	public java.lang.String getPresentationFileName() {
		return _challengeTeam.getPresentationFileName();
	}

	/**
	* Sets the presentation file name of this challenge team.
	*
	* @param presentationFileName the presentation file name of this challenge team
	*/
	@Override
	public void setPresentationFileName(java.lang.String presentationFileName) {
		_challengeTeam.setPresentationFileName(presentationFileName);
	}

	/**
	* Returns the presentation submission day of this challenge team.
	*
	* @return the presentation submission day of this challenge team
	*/
	@Override
	public java.util.Date getPresentationSubmissionDay() {
		return _challengeTeam.getPresentationSubmissionDay();
	}

	/**
	* Sets the presentation submission day of this challenge team.
	*
	* @param presentationSubmissionDay the presentation submission day of this challenge team
	*/
	@Override
	public void setPresentationSubmissionDay(
		java.util.Date presentationSubmissionDay) {
		_challengeTeam.setPresentationSubmissionDay(presentationSubmissionDay);
	}

	/**
	* Returns the presentation modify day of this challenge team.
	*
	* @return the presentation modify day of this challenge team
	*/
	@Override
	public java.util.Date getPresentationModifyDay() {
		return _challengeTeam.getPresentationModifyDay();
	}

	/**
	* Sets the presentation modify day of this challenge team.
	*
	* @param presentationModifyDay the presentation modify day of this challenge team
	*/
	@Override
	public void setPresentationModifyDay(java.util.Date presentationModifyDay) {
		_challengeTeam.setPresentationModifyDay(presentationModifyDay);
	}

	/**
	* Returns the presentation status of this challenge team.
	*
	* @return the presentation status of this challenge team
	*/
	@Override
	public boolean getPresentationStatus() {
		return _challengeTeam.getPresentationStatus();
	}

	/**
	* Returns <code>true</code> if this challenge team is presentation status.
	*
	* @return <code>true</code> if this challenge team is presentation status; <code>false</code> otherwise
	*/
	@Override
	public boolean isPresentationStatus() {
		return _challengeTeam.isPresentationStatus();
	}

	/**
	* Sets whether this challenge team is presentation status.
	*
	* @param presentationStatus the presentation status of this challenge team
	*/
	@Override
	public void setPresentationStatus(boolean presentationStatus) {
		_challengeTeam.setPresentationStatus(presentationStatus);
	}

	/**
	* Returns the filepath of this challenge team.
	*
	* @return the filepath of this challenge team
	*/
	@Override
	public java.lang.String getFilepath() {
		return _challengeTeam.getFilepath();
	}

	/**
	* Sets the filepath of this challenge team.
	*
	* @param filepath the filepath of this challenge team
	*/
	@Override
	public void setFilepath(java.lang.String filepath) {
		_challengeTeam.setFilepath(filepath);
	}

	/**
	* Returns the aggrement of this challenge team.
	*
	* @return the aggrement of this challenge team
	*/
	@Override
	public boolean getAggrement() {
		return _challengeTeam.getAggrement();
	}

	/**
	* Returns <code>true</code> if this challenge team is aggrement.
	*
	* @return <code>true</code> if this challenge team is aggrement; <code>false</code> otherwise
	*/
	@Override
	public boolean isAggrement() {
		return _challengeTeam.isAggrement();
	}

	/**
	* Sets whether this challenge team is aggrement.
	*
	* @param aggrement the aggrement of this challenge team
	*/
	@Override
	public void setAggrement(boolean aggrement) {
		_challengeTeam.setAggrement(aggrement);
	}

	/**
	* Returns the child challenge ID of this challenge team.
	*
	* @return the child challenge ID of this challenge team
	*/
	@Override
	public long getChildChallengeId() {
		return _challengeTeam.getChildChallengeId();
	}

	/**
	* Sets the child challenge ID of this challenge team.
	*
	* @param childChallengeId the child challenge ID of this challenge team
	*/
	@Override
	public void setChildChallengeId(long childChallengeId) {
		_challengeTeam.setChildChallengeId(childChallengeId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challengeTeam.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge team is approved.
	*
	* @return <code>true</code> if this challenge team is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challengeTeam.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge team is denied.
	*
	* @return <code>true</code> if this challenge team is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challengeTeam.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge team is a draft.
	*
	* @return <code>true</code> if this challenge team is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challengeTeam.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge team is expired.
	*
	* @return <code>true</code> if this challenge team is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challengeTeam.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge team is inactive.
	*
	* @return <code>true</code> if this challenge team is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challengeTeam.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge team is incomplete.
	*
	* @return <code>true</code> if this challenge team is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challengeTeam.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge team is pending.
	*
	* @return <code>true</code> if this challenge team is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challengeTeam.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge team is scheduled.
	*
	* @return <code>true</code> if this challenge team is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challengeTeam.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challengeTeam.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeTeam.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeTeam.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeTeam.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeTeam.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeTeam.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeTeam.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeTeam.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeTeam.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeTeam.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeTeam.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challengeTeam.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challengeTeam.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challengeTeam.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challengeTeam.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeTeamWrapper((ChallengeTeam)_challengeTeam.clone());
	}

	@Override
	public int compareTo(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam) {
		return _challengeTeam.compareTo(challengeTeam);
	}

	@Override
	public int hashCode() {
		return _challengeTeam.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChallengeTeam> toCacheModel() {
		return _challengeTeam.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam toEscapedModel() {
		return new ChallengeTeamWrapper(_challengeTeam.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam toUnescapedModel() {
		return new ChallengeTeamWrapper(_challengeTeam.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeTeam.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeTeam.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeTeam.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeTeamWrapper)) {
			return false;
		}

		ChallengeTeamWrapper challengeTeamWrapper = (ChallengeTeamWrapper)obj;

		if (Validator.equals(_challengeTeam, challengeTeamWrapper._challengeTeam)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challengeTeam.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeTeam getWrappedChallengeTeam() {
		return _challengeTeam;
	}

	@Override
	public ChallengeTeam getWrappedModel() {
		return _challengeTeam;
	}

	@Override
	public void resetOriginalValues() {
		_challengeTeam.resetOriginalValues();
	}

	private ChallengeTeam _challengeTeam;
}