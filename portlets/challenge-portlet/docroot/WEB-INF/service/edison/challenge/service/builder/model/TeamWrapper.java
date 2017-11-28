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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Team}.
 * </p>
 *
 * @author kyj
 * @see Team
 * @generated
 */
public class TeamWrapper implements Team, ModelWrapper<Team> {
	public TeamWrapper(Team team) {
		_team = team;
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

	/**
	* Returns the primary key of this team.
	*
	* @return the primary key of this team
	*/
	@Override
	public long getPrimaryKey() {
		return _team.getPrimaryKey();
	}

	/**
	* Sets the primary key of this team.
	*
	* @param primaryKey the primary key of this team
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_team.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the teamid of this team.
	*
	* @return the teamid of this team
	*/
	@Override
	public long getTeamid() {
		return _team.getTeamid();
	}

	/**
	* Sets the teamid of this team.
	*
	* @param teamid the teamid of this team
	*/
	@Override
	public void setTeamid(long teamid) {
		_team.setTeamid(teamid);
	}

	/**
	* Returns the team name of this team.
	*
	* @return the team name of this team
	*/
	@Override
	public java.lang.String getTeamName() {
		return _team.getTeamName();
	}

	/**
	* Returns the localized team name of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized team name of this team
	*/
	@Override
	public java.lang.String getTeamName(java.util.Locale locale) {
		return _team.getTeamName(locale);
	}

	/**
	* Returns the localized team name of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized team name of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTeamName(java.util.Locale locale,
		boolean useDefault) {
		return _team.getTeamName(locale, useDefault);
	}

	/**
	* Returns the localized team name of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized team name of this team
	*/
	@Override
	public java.lang.String getTeamName(java.lang.String languageId) {
		return _team.getTeamName(languageId);
	}

	/**
	* Returns the localized team name of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized team name of this team
	*/
	@Override
	public java.lang.String getTeamName(java.lang.String languageId,
		boolean useDefault) {
		return _team.getTeamName(languageId, useDefault);
	}

	@Override
	public java.lang.String getTeamNameCurrentLanguageId() {
		return _team.getTeamNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getTeamNameCurrentValue() {
		return _team.getTeamNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized team names of this team.
	*
	* @return the locales and localized team names of this team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTeamNameMap() {
		return _team.getTeamNameMap();
	}

	/**
	* Sets the team name of this team.
	*
	* @param teamName the team name of this team
	*/
	@Override
	public void setTeamName(java.lang.String teamName) {
		_team.setTeamName(teamName);
	}

	/**
	* Sets the localized team name of this team in the language.
	*
	* @param teamName the localized team name of this team
	* @param locale the locale of the language
	*/
	@Override
	public void setTeamName(java.lang.String teamName, java.util.Locale locale) {
		_team.setTeamName(teamName, locale);
	}

	/**
	* Sets the localized team name of this team in the language, and sets the default locale.
	*
	* @param teamName the localized team name of this team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTeamName(java.lang.String teamName, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_team.setTeamName(teamName, locale, defaultLocale);
	}

	@Override
	public void setTeamNameCurrentLanguageId(java.lang.String languageId) {
		_team.setTeamNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized team names of this team from the map of locales and localized team names.
	*
	* @param teamNameMap the locales and localized team names of this team
	*/
	@Override
	public void setTeamNameMap(
		java.util.Map<java.util.Locale, java.lang.String> teamNameMap) {
		_team.setTeamNameMap(teamNameMap);
	}

	/**
	* Sets the localized team names of this team from the map of locales and localized team names, and sets the default locale.
	*
	* @param teamNameMap the locales and localized team names of this team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTeamNameMap(
		java.util.Map<java.util.Locale, java.lang.String> teamNameMap,
		java.util.Locale defaultLocale) {
		_team.setTeamNameMap(teamNameMap, defaultLocale);
	}

	/**
	* Returns the subject of this team.
	*
	* @return the subject of this team
	*/
	@Override
	public java.lang.String getSubject() {
		return _team.getSubject();
	}

	/**
	* Returns the localized subject of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subject of this team
	*/
	@Override
	public java.lang.String getSubject(java.util.Locale locale) {
		return _team.getSubject(locale);
	}

	/**
	* Returns the localized subject of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subject of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSubject(java.util.Locale locale,
		boolean useDefault) {
		return _team.getSubject(locale, useDefault);
	}

	/**
	* Returns the localized subject of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subject of this team
	*/
	@Override
	public java.lang.String getSubject(java.lang.String languageId) {
		return _team.getSubject(languageId);
	}

	/**
	* Returns the localized subject of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subject of this team
	*/
	@Override
	public java.lang.String getSubject(java.lang.String languageId,
		boolean useDefault) {
		return _team.getSubject(languageId, useDefault);
	}

	@Override
	public java.lang.String getSubjectCurrentLanguageId() {
		return _team.getSubjectCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubjectCurrentValue() {
		return _team.getSubjectCurrentValue();
	}

	/**
	* Returns a map of the locales and localized subjects of this team.
	*
	* @return the locales and localized subjects of this team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getSubjectMap() {
		return _team.getSubjectMap();
	}

	/**
	* Sets the subject of this team.
	*
	* @param subject the subject of this team
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_team.setSubject(subject);
	}

	/**
	* Sets the localized subject of this team in the language.
	*
	* @param subject the localized subject of this team
	* @param locale the locale of the language
	*/
	@Override
	public void setSubject(java.lang.String subject, java.util.Locale locale) {
		_team.setSubject(subject, locale);
	}

	/**
	* Sets the localized subject of this team in the language, and sets the default locale.
	*
	* @param subject the localized subject of this team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubject(java.lang.String subject, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_team.setSubject(subject, locale, defaultLocale);
	}

	@Override
	public void setSubjectCurrentLanguageId(java.lang.String languageId) {
		_team.setSubjectCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subjects of this team from the map of locales and localized subjects.
	*
	* @param subjectMap the locales and localized subjects of this team
	*/
	@Override
	public void setSubjectMap(
		java.util.Map<java.util.Locale, java.lang.String> subjectMap) {
		_team.setSubjectMap(subjectMap);
	}

	/**
	* Sets the localized subjects of this team from the map of locales and localized subjects, and sets the default locale.
	*
	* @param subjectMap the locales and localized subjects of this team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubjectMap(
		java.util.Map<java.util.Locale, java.lang.String> subjectMap,
		java.util.Locale defaultLocale) {
		_team.setSubjectMap(subjectMap, defaultLocale);
	}

	/**
	* Returns the paperstatus of this team.
	*
	* @return the paperstatus of this team
	*/
	@Override
	public boolean getPaperstatus() {
		return _team.getPaperstatus();
	}

	/**
	* Returns <code>true</code> if this team is paperstatus.
	*
	* @return <code>true</code> if this team is paperstatus; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaperstatus() {
		return _team.isPaperstatus();
	}

	/**
	* Sets whether this team is paperstatus.
	*
	* @param paperstatus the paperstatus of this team
	*/
	@Override
	public void setPaperstatus(boolean paperstatus) {
		_team.setPaperstatus(paperstatus);
	}

	/**
	* Returns the presentationstatus of this team.
	*
	* @return the presentationstatus of this team
	*/
	@Override
	public boolean getPresentationstatus() {
		return _team.getPresentationstatus();
	}

	/**
	* Returns <code>true</code> if this team is presentationstatus.
	*
	* @return <code>true</code> if this team is presentationstatus; <code>false</code> otherwise
	*/
	@Override
	public boolean isPresentationstatus() {
		return _team.isPresentationstatus();
	}

	/**
	* Sets whether this team is presentationstatus.
	*
	* @param presentationstatus the presentationstatus of this team
	*/
	@Override
	public void setPresentationstatus(boolean presentationstatus) {
		_team.setPresentationstatus(presentationstatus);
	}

	/**
	* Returns the register day of this team.
	*
	* @return the register day of this team
	*/
	@Override
	public java.util.Date getRegisterDay() {
		return _team.getRegisterDay();
	}

	/**
	* Sets the register day of this team.
	*
	* @param registerDay the register day of this team
	*/
	@Override
	public void setRegisterDay(java.util.Date registerDay) {
		_team.setRegisterDay(registerDay);
	}

	/**
	* Returns the registerid of this team.
	*
	* @return the registerid of this team
	*/
	@Override
	public java.lang.String getRegisterid() {
		return _team.getRegisterid();
	}

	/**
	* Sets the registerid of this team.
	*
	* @param registerid the registerid of this team
	*/
	@Override
	public void setRegisterid(java.lang.String registerid) {
		_team.setRegisterid(registerid);
	}

	/**
	* Returns the paper name of this team.
	*
	* @return the paper name of this team
	*/
	@Override
	public java.lang.String getPaperName() {
		return _team.getPaperName();
	}

	/**
	* Returns the localized paper name of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized paper name of this team
	*/
	@Override
	public java.lang.String getPaperName(java.util.Locale locale) {
		return _team.getPaperName(locale);
	}

	/**
	* Returns the localized paper name of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper name of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPaperName(java.util.Locale locale,
		boolean useDefault) {
		return _team.getPaperName(locale, useDefault);
	}

	/**
	* Returns the localized paper name of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized paper name of this team
	*/
	@Override
	public java.lang.String getPaperName(java.lang.String languageId) {
		return _team.getPaperName(languageId);
	}

	/**
	* Returns the localized paper name of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper name of this team
	*/
	@Override
	public java.lang.String getPaperName(java.lang.String languageId,
		boolean useDefault) {
		return _team.getPaperName(languageId, useDefault);
	}

	@Override
	public java.lang.String getPaperNameCurrentLanguageId() {
		return _team.getPaperNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getPaperNameCurrentValue() {
		return _team.getPaperNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized paper names of this team.
	*
	* @return the locales and localized paper names of this team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPaperNameMap() {
		return _team.getPaperNameMap();
	}

	/**
	* Sets the paper name of this team.
	*
	* @param paperName the paper name of this team
	*/
	@Override
	public void setPaperName(java.lang.String paperName) {
		_team.setPaperName(paperName);
	}

	/**
	* Sets the localized paper name of this team in the language.
	*
	* @param paperName the localized paper name of this team
	* @param locale the locale of the language
	*/
	@Override
	public void setPaperName(java.lang.String paperName, java.util.Locale locale) {
		_team.setPaperName(paperName, locale);
	}

	/**
	* Sets the localized paper name of this team in the language, and sets the default locale.
	*
	* @param paperName the localized paper name of this team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperName(java.lang.String paperName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_team.setPaperName(paperName, locale, defaultLocale);
	}

	@Override
	public void setPaperNameCurrentLanguageId(java.lang.String languageId) {
		_team.setPaperNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized paper names of this team from the map of locales and localized paper names.
	*
	* @param paperNameMap the locales and localized paper names of this team
	*/
	@Override
	public void setPaperNameMap(
		java.util.Map<java.util.Locale, java.lang.String> paperNameMap) {
		_team.setPaperNameMap(paperNameMap);
	}

	/**
	* Sets the localized paper names of this team from the map of locales and localized paper names, and sets the default locale.
	*
	* @param paperNameMap the locales and localized paper names of this team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperNameMap(
		java.util.Map<java.util.Locale, java.lang.String> paperNameMap,
		java.util.Locale defaultLocale) {
		_team.setPaperNameMap(paperNameMap, defaultLocale);
	}

	/**
	* Returns the paper abstract of this team.
	*
	* @return the paper abstract of this team
	*/
	@Override
	public java.lang.String getPaperAbstract() {
		return _team.getPaperAbstract();
	}

	/**
	* Returns the localized paper abstract of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized paper abstract of this team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.util.Locale locale) {
		return _team.getPaperAbstract(locale);
	}

	/**
	* Returns the localized paper abstract of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper abstract of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPaperAbstract(java.util.Locale locale,
		boolean useDefault) {
		return _team.getPaperAbstract(locale, useDefault);
	}

	/**
	* Returns the localized paper abstract of this team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized paper abstract of this team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.lang.String languageId) {
		return _team.getPaperAbstract(languageId);
	}

	/**
	* Returns the localized paper abstract of this team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized paper abstract of this team
	*/
	@Override
	public java.lang.String getPaperAbstract(java.lang.String languageId,
		boolean useDefault) {
		return _team.getPaperAbstract(languageId, useDefault);
	}

	@Override
	public java.lang.String getPaperAbstractCurrentLanguageId() {
		return _team.getPaperAbstractCurrentLanguageId();
	}

	@Override
	public java.lang.String getPaperAbstractCurrentValue() {
		return _team.getPaperAbstractCurrentValue();
	}

	/**
	* Returns a map of the locales and localized paper abstracts of this team.
	*
	* @return the locales and localized paper abstracts of this team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPaperAbstractMap() {
		return _team.getPaperAbstractMap();
	}

	/**
	* Sets the paper abstract of this team.
	*
	* @param paperAbstract the paper abstract of this team
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract) {
		_team.setPaperAbstract(paperAbstract);
	}

	/**
	* Sets the localized paper abstract of this team in the language.
	*
	* @param paperAbstract the localized paper abstract of this team
	* @param locale the locale of the language
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract,
		java.util.Locale locale) {
		_team.setPaperAbstract(paperAbstract, locale);
	}

	/**
	* Sets the localized paper abstract of this team in the language, and sets the default locale.
	*
	* @param paperAbstract the localized paper abstract of this team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperAbstract(java.lang.String paperAbstract,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_team.setPaperAbstract(paperAbstract, locale, defaultLocale);
	}

	@Override
	public void setPaperAbstractCurrentLanguageId(java.lang.String languageId) {
		_team.setPaperAbstractCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized paper abstracts of this team from the map of locales and localized paper abstracts.
	*
	* @param paperAbstractMap the locales and localized paper abstracts of this team
	*/
	@Override
	public void setPaperAbstractMap(
		java.util.Map<java.util.Locale, java.lang.String> paperAbstractMap) {
		_team.setPaperAbstractMap(paperAbstractMap);
	}

	/**
	* Sets the localized paper abstracts of this team from the map of locales and localized paper abstracts, and sets the default locale.
	*
	* @param paperAbstractMap the locales and localized paper abstracts of this team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPaperAbstractMap(
		java.util.Map<java.util.Locale, java.lang.String> paperAbstractMap,
		java.util.Locale defaultLocale) {
		_team.setPaperAbstractMap(paperAbstractMap, defaultLocale);
	}

	/**
	* Returns the paper file name of this team.
	*
	* @return the paper file name of this team
	*/
	@Override
	public java.lang.String getPaperFileName() {
		return _team.getPaperFileName();
	}

	/**
	* Sets the paper file name of this team.
	*
	* @param paperFileName the paper file name of this team
	*/
	@Override
	public void setPaperFileName(java.lang.String paperFileName) {
		_team.setPaperFileName(paperFileName);
	}

	/**
	* Returns the paper submission day of this team.
	*
	* @return the paper submission day of this team
	*/
	@Override
	public java.util.Date getPaperSubmissionDay() {
		return _team.getPaperSubmissionDay();
	}

	/**
	* Sets the paper submission day of this team.
	*
	* @param paperSubmissionDay the paper submission day of this team
	*/
	@Override
	public void setPaperSubmissionDay(java.util.Date paperSubmissionDay) {
		_team.setPaperSubmissionDay(paperSubmissionDay);
	}

	/**
	* Returns the paper modify day of this team.
	*
	* @return the paper modify day of this team
	*/
	@Override
	public java.util.Date getPaperModifyDay() {
		return _team.getPaperModifyDay();
	}

	/**
	* Sets the paper modify day of this team.
	*
	* @param paperModifyDay the paper modify day of this team
	*/
	@Override
	public void setPaperModifyDay(java.util.Date paperModifyDay) {
		_team.setPaperModifyDay(paperModifyDay);
	}

	/**
	* Returns the presentation name of this team.
	*
	* @return the presentation name of this team
	*/
	@Override
	public java.lang.String getPresentationName() {
		return _team.getPresentationName();
	}

	/**
	* Sets the presentation name of this team.
	*
	* @param presentationName the presentation name of this team
	*/
	@Override
	public void setPresentationName(java.lang.String presentationName) {
		_team.setPresentationName(presentationName);
	}

	/**
	* Returns the presentation file name of this team.
	*
	* @return the presentation file name of this team
	*/
	@Override
	public java.lang.String getPresentationFileName() {
		return _team.getPresentationFileName();
	}

	/**
	* Sets the presentation file name of this team.
	*
	* @param presentationFileName the presentation file name of this team
	*/
	@Override
	public void setPresentationFileName(java.lang.String presentationFileName) {
		_team.setPresentationFileName(presentationFileName);
	}

	/**
	* Returns the presentation submission day of this team.
	*
	* @return the presentation submission day of this team
	*/
	@Override
	public java.util.Date getPresentationSubmissionDay() {
		return _team.getPresentationSubmissionDay();
	}

	/**
	* Sets the presentation submission day of this team.
	*
	* @param presentationSubmissionDay the presentation submission day of this team
	*/
	@Override
	public void setPresentationSubmissionDay(
		java.util.Date presentationSubmissionDay) {
		_team.setPresentationSubmissionDay(presentationSubmissionDay);
	}

	/**
	* Returns the presentation modify day of this team.
	*
	* @return the presentation modify day of this team
	*/
	@Override
	public java.util.Date getPresentationModifyDay() {
		return _team.getPresentationModifyDay();
	}

	/**
	* Sets the presentation modify day of this team.
	*
	* @param presentationModifyDay the presentation modify day of this team
	*/
	@Override
	public void setPresentationModifyDay(java.util.Date presentationModifyDay) {
		_team.setPresentationModifyDay(presentationModifyDay);
	}

	/**
	* Returns the filepath of this team.
	*
	* @return the filepath of this team
	*/
	@Override
	public java.lang.String getFilepath() {
		return _team.getFilepath();
	}

	/**
	* Sets the filepath of this team.
	*
	* @param filepath the filepath of this team
	*/
	@Override
	public void setFilepath(java.lang.String filepath) {
		_team.setFilepath(filepath);
	}

	/**
	* Returns the childid of this team.
	*
	* @return the childid of this team
	*/
	@Override
	public long getChildid() {
		return _team.getChildid();
	}

	/**
	* Sets the childid of this team.
	*
	* @param childid the childid of this team
	*/
	@Override
	public void setChildid(long childid) {
		_team.setChildid(childid);
	}

	@Override
	public boolean isNew() {
		return _team.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_team.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _team.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_team.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _team.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _team.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_team.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _team.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_team.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_team.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_team.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _team.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _team.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_team.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_team.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new TeamWrapper((Team)_team.clone());
	}

	@Override
	public int compareTo(edison.challenge.service.builder.model.Team team) {
		return _team.compareTo(team);
	}

	@Override
	public int hashCode() {
		return _team.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Team> toCacheModel() {
		return _team.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Team toEscapedModel() {
		return new TeamWrapper(_team.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Team toUnescapedModel() {
		return new TeamWrapper(_team.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _team.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _team.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_team.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TeamWrapper)) {
			return false;
		}

		TeamWrapper teamWrapper = (TeamWrapper)obj;

		if (Validator.equals(_team, teamWrapper._team)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Team getWrappedTeam() {
		return _team;
	}

	@Override
	public Team getWrappedModel() {
		return _team;
	}

	@Override
	public void resetOriginalValues() {
		_team.resetOriginalValues();
	}

	private Team _team;
}