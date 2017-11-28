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
 * This class is a wrapper for {@link ChallengeTeam}.
 * </p>
 *
 * @author kyj
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

		attributes.put("chTeamid", getChTeamid());
		attributes.put("teamName", getTeamName());
		attributes.put("subject", getSubject());
		attributes.put("paperPDFstatus", getPaperPDFstatus());
		attributes.put("paperstatus", getPaperstatus());
		attributes.put("presentationstatus", getPresentationstatus());
		attributes.put("registerDay", getRegisterDay());
		attributes.put("registerid", getRegisterid());
		attributes.put("paperName", getPaperName());
		attributes.put("paperAbstract", getPaperAbstract());
		attributes.put("paperFileName", getPaperFileName());
		attributes.put("paperSubmissionDay", getPaperSubmissionDay());
		attributes.put("paperModifyDay", getPaperModifyDay());
		attributes.put("paperPDFFileName", getPaperPDFFileName());
		attributes.put("paperPDFSubmissionDay", getPaperPDFSubmissionDay());
		attributes.put("paperPDFModifyDay", getPaperPDFModifyDay());
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
		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}

		String teamName = (String)attributes.get("teamName");

		if (teamName != null) {
			setTeamName(teamName);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Boolean paperPDFstatus = (Boolean)attributes.get("paperPDFstatus");

		if (paperPDFstatus != null) {
			setPaperPDFstatus(paperPDFstatus);
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
	* Returns the ch teamid of this challenge team.
	*
	* @return the ch teamid of this challenge team
	*/
	@Override
	public long getChTeamid() {
		return _challengeTeam.getChTeamid();
	}

	/**
	* Sets the ch teamid of this challenge team.
	*
	* @param chTeamid the ch teamid of this challenge team
	*/
	@Override
	public void setChTeamid(long chTeamid) {
		_challengeTeam.setChTeamid(chTeamid);
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
	* Returns the localized team name of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized team name of this challenge team
	*/
	@Override
	public java.lang.String getTeamName(java.util.Locale locale) {
		return _challengeTeam.getTeamName(locale);
	}

	/**
	* Returns the localized team name of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized team name of this challenge team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTeamName(java.util.Locale locale,
		boolean useDefault) {
		return _challengeTeam.getTeamName(locale, useDefault);
	}

	/**
	* Returns the localized team name of this challenge team in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized team name of this challenge team
	*/
	@Override
	public java.lang.String getTeamName(java.lang.String languageId) {
		return _challengeTeam.getTeamName(languageId);
	}

	/**
	* Returns the localized team name of this challenge team in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized team name of this challenge team
	*/
	@Override
	public java.lang.String getTeamName(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeam.getTeamName(languageId, useDefault);
	}

	@Override
	public java.lang.String getTeamNameCurrentLanguageId() {
		return _challengeTeam.getTeamNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getTeamNameCurrentValue() {
		return _challengeTeam.getTeamNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized team names of this challenge team.
	*
	* @return the locales and localized team names of this challenge team
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTeamNameMap() {
		return _challengeTeam.getTeamNameMap();
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
	* Sets the localized team name of this challenge team in the language.
	*
	* @param teamName the localized team name of this challenge team
	* @param locale the locale of the language
	*/
	@Override
	public void setTeamName(java.lang.String teamName, java.util.Locale locale) {
		_challengeTeam.setTeamName(teamName, locale);
	}

	/**
	* Sets the localized team name of this challenge team in the language, and sets the default locale.
	*
	* @param teamName the localized team name of this challenge team
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTeamName(java.lang.String teamName, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeTeam.setTeamName(teamName, locale, defaultLocale);
	}

	@Override
	public void setTeamNameCurrentLanguageId(java.lang.String languageId) {
		_challengeTeam.setTeamNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized team names of this challenge team from the map of locales and localized team names.
	*
	* @param teamNameMap the locales and localized team names of this challenge team
	*/
	@Override
	public void setTeamNameMap(
		java.util.Map<java.util.Locale, java.lang.String> teamNameMap) {
		_challengeTeam.setTeamNameMap(teamNameMap);
	}

	/**
	* Sets the localized team names of this challenge team from the map of locales and localized team names, and sets the default locale.
	*
	* @param teamNameMap the locales and localized team names of this challenge team
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTeamNameMap(
		java.util.Map<java.util.Locale, java.lang.String> teamNameMap,
		java.util.Locale defaultLocale) {
		_challengeTeam.setTeamNameMap(teamNameMap, defaultLocale);
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
	* Returns the paper p d fstatus of this challenge team.
	*
	* @return the paper p d fstatus of this challenge team
	*/
	@Override
	public boolean getPaperPDFstatus() {
		return _challengeTeam.getPaperPDFstatus();
	}

	/**
	* Returns <code>true</code> if this challenge team is paper p d fstatus.
	*
	* @return <code>true</code> if this challenge team is paper p d fstatus; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaperPDFstatus() {
		return _challengeTeam.isPaperPDFstatus();
	}

	/**
	* Sets whether this challenge team is paper p d fstatus.
	*
	* @param paperPDFstatus the paper p d fstatus of this challenge team
	*/
	@Override
	public void setPaperPDFstatus(boolean paperPDFstatus) {
		_challengeTeam.setPaperPDFstatus(paperPDFstatus);
	}

	/**
	* Returns the paperstatus of this challenge team.
	*
	* @return the paperstatus of this challenge team
	*/
	@Override
	public boolean getPaperstatus() {
		return _challengeTeam.getPaperstatus();
	}

	/**
	* Returns <code>true</code> if this challenge team is paperstatus.
	*
	* @return <code>true</code> if this challenge team is paperstatus; <code>false</code> otherwise
	*/
	@Override
	public boolean isPaperstatus() {
		return _challengeTeam.isPaperstatus();
	}

	/**
	* Sets whether this challenge team is paperstatus.
	*
	* @param paperstatus the paperstatus of this challenge team
	*/
	@Override
	public void setPaperstatus(boolean paperstatus) {
		_challengeTeam.setPaperstatus(paperstatus);
	}

	/**
	* Returns the presentationstatus of this challenge team.
	*
	* @return the presentationstatus of this challenge team
	*/
	@Override
	public boolean getPresentationstatus() {
		return _challengeTeam.getPresentationstatus();
	}

	/**
	* Returns <code>true</code> if this challenge team is presentationstatus.
	*
	* @return <code>true</code> if this challenge team is presentationstatus; <code>false</code> otherwise
	*/
	@Override
	public boolean isPresentationstatus() {
		return _challengeTeam.isPresentationstatus();
	}

	/**
	* Sets whether this challenge team is presentationstatus.
	*
	* @param presentationstatus the presentationstatus of this challenge team
	*/
	@Override
	public void setPresentationstatus(boolean presentationstatus) {
		_challengeTeam.setPresentationstatus(presentationstatus);
	}

	/**
	* Returns the register day of this challenge team.
	*
	* @return the register day of this challenge team
	*/
	@Override
	public java.util.Date getRegisterDay() {
		return _challengeTeam.getRegisterDay();
	}

	/**
	* Sets the register day of this challenge team.
	*
	* @param registerDay the register day of this challenge team
	*/
	@Override
	public void setRegisterDay(java.util.Date registerDay) {
		_challengeTeam.setRegisterDay(registerDay);
	}

	/**
	* Returns the registerid of this challenge team.
	*
	* @return the registerid of this challenge team
	*/
	@Override
	public java.lang.String getRegisterid() {
		return _challengeTeam.getRegisterid();
	}

	/**
	* Sets the registerid of this challenge team.
	*
	* @param registerid the registerid of this challenge team
	*/
	@Override
	public void setRegisterid(java.lang.String registerid) {
		_challengeTeam.setRegisterid(registerid);
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
	* Returns the childid of this challenge team.
	*
	* @return the childid of this challenge team
	*/
	@Override
	public long getChildid() {
		return _challengeTeam.getChildid();
	}

	/**
	* Sets the childid of this challenge team.
	*
	* @param childid the childid of this challenge team
	*/
	@Override
	public void setChildid(long childid) {
		_challengeTeam.setChildid(childid);
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
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam) {
		return _challengeTeam.compareTo(challengeTeam);
	}

	@Override
	public int hashCode() {
		return _challengeTeam.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.ChallengeTeam> toCacheModel() {
		return _challengeTeam.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeTeam toEscapedModel() {
		return new ChallengeTeamWrapper(_challengeTeam.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeTeam toUnescapedModel() {
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