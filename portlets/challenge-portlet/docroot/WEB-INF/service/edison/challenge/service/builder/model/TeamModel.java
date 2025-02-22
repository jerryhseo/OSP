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
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Team service. Represents a row in the &quot;edison_Team&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link edison.challenge.service.builder.model.impl.TeamModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link edison.challenge.service.builder.model.impl.TeamImpl}.
 * </p>
 *
 * @author kyj
 * @see Team
 * @see edison.challenge.service.builder.model.impl.TeamImpl
 * @see edison.challenge.service.builder.model.impl.TeamModelImpl
 * @generated
 */
public interface TeamModel extends BaseModel<Team> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a team model instance should use the {@link Team} interface instead.
	 */

	/**
	 * Returns the primary key of this team.
	 *
	 * @return the primary key of this team
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this team.
	 *
	 * @param primaryKey the primary key of this team
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the teamid of this team.
	 *
	 * @return the teamid of this team
	 */
	public long getTeamid();

	/**
	 * Sets the teamid of this team.
	 *
	 * @param teamid the teamid of this team
	 */
	public void setTeamid(long teamid);

	/**
	 * Returns the team name of this team.
	 *
	 * @return the team name of this team
	 */
	public String getTeamName();

	/**
	 * Returns the localized team name of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized team name of this team
	 */
	@AutoEscape
	public String getTeamName(Locale locale);

	/**
	 * Returns the localized team name of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized team name of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTeamName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized team name of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized team name of this team
	 */
	@AutoEscape
	public String getTeamName(String languageId);

	/**
	 * Returns the localized team name of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized team name of this team
	 */
	@AutoEscape
	public String getTeamName(String languageId, boolean useDefault);

	@AutoEscape
	public String getTeamNameCurrentLanguageId();

	@AutoEscape
	public String getTeamNameCurrentValue();

	/**
	 * Returns a map of the locales and localized team names of this team.
	 *
	 * @return the locales and localized team names of this team
	 */
	public Map<Locale, String> getTeamNameMap();

	/**
	 * Sets the team name of this team.
	 *
	 * @param teamName the team name of this team
	 */
	public void setTeamName(String teamName);

	/**
	 * Sets the localized team name of this team in the language.
	 *
	 * @param teamName the localized team name of this team
	 * @param locale the locale of the language
	 */
	public void setTeamName(String teamName, Locale locale);

	/**
	 * Sets the localized team name of this team in the language, and sets the default locale.
	 *
	 * @param teamName the localized team name of this team
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTeamName(String teamName, Locale locale, Locale defaultLocale);

	public void setTeamNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized team names of this team from the map of locales and localized team names.
	 *
	 * @param teamNameMap the locales and localized team names of this team
	 */
	public void setTeamNameMap(Map<Locale, String> teamNameMap);

	/**
	 * Sets the localized team names of this team from the map of locales and localized team names, and sets the default locale.
	 *
	 * @param teamNameMap the locales and localized team names of this team
	 * @param defaultLocale the default locale
	 */
	public void setTeamNameMap(Map<Locale, String> teamNameMap,
		Locale defaultLocale);

	/**
	 * Returns the subject of this team.
	 *
	 * @return the subject of this team
	 */
	public String getSubject();

	/**
	 * Returns the localized subject of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subject of this team
	 */
	@AutoEscape
	public String getSubject(Locale locale);

	/**
	 * Returns the localized subject of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subject of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getSubject(Locale locale, boolean useDefault);

	/**
	 * Returns the localized subject of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subject of this team
	 */
	@AutoEscape
	public String getSubject(String languageId);

	/**
	 * Returns the localized subject of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subject of this team
	 */
	@AutoEscape
	public String getSubject(String languageId, boolean useDefault);

	@AutoEscape
	public String getSubjectCurrentLanguageId();

	@AutoEscape
	public String getSubjectCurrentValue();

	/**
	 * Returns a map of the locales and localized subjects of this team.
	 *
	 * @return the locales and localized subjects of this team
	 */
	public Map<Locale, String> getSubjectMap();

	/**
	 * Sets the subject of this team.
	 *
	 * @param subject the subject of this team
	 */
	public void setSubject(String subject);

	/**
	 * Sets the localized subject of this team in the language.
	 *
	 * @param subject the localized subject of this team
	 * @param locale the locale of the language
	 */
	public void setSubject(String subject, Locale locale);

	/**
	 * Sets the localized subject of this team in the language, and sets the default locale.
	 *
	 * @param subject the localized subject of this team
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setSubject(String subject, Locale locale, Locale defaultLocale);

	public void setSubjectCurrentLanguageId(String languageId);

	/**
	 * Sets the localized subjects of this team from the map of locales and localized subjects.
	 *
	 * @param subjectMap the locales and localized subjects of this team
	 */
	public void setSubjectMap(Map<Locale, String> subjectMap);

	/**
	 * Sets the localized subjects of this team from the map of locales and localized subjects, and sets the default locale.
	 *
	 * @param subjectMap the locales and localized subjects of this team
	 * @param defaultLocale the default locale
	 */
	public void setSubjectMap(Map<Locale, String> subjectMap,
		Locale defaultLocale);

	/**
	 * Returns the paperstatus of this team.
	 *
	 * @return the paperstatus of this team
	 */
	public boolean getPaperstatus();

	/**
	 * Returns <code>true</code> if this team is paperstatus.
	 *
	 * @return <code>true</code> if this team is paperstatus; <code>false</code> otherwise
	 */
	public boolean isPaperstatus();

	/**
	 * Sets whether this team is paperstatus.
	 *
	 * @param paperstatus the paperstatus of this team
	 */
	public void setPaperstatus(boolean paperstatus);

	/**
	 * Returns the presentationstatus of this team.
	 *
	 * @return the presentationstatus of this team
	 */
	public boolean getPresentationstatus();

	/**
	 * Returns <code>true</code> if this team is presentationstatus.
	 *
	 * @return <code>true</code> if this team is presentationstatus; <code>false</code> otherwise
	 */
	public boolean isPresentationstatus();

	/**
	 * Sets whether this team is presentationstatus.
	 *
	 * @param presentationstatus the presentationstatus of this team
	 */
	public void setPresentationstatus(boolean presentationstatus);

	/**
	 * Returns the register day of this team.
	 *
	 * @return the register day of this team
	 */
	public Date getRegisterDay();

	/**
	 * Sets the register day of this team.
	 *
	 * @param registerDay the register day of this team
	 */
	public void setRegisterDay(Date registerDay);

	/**
	 * Returns the registerid of this team.
	 *
	 * @return the registerid of this team
	 */
	@AutoEscape
	public String getRegisterid();

	/**
	 * Sets the registerid of this team.
	 *
	 * @param registerid the registerid of this team
	 */
	public void setRegisterid(String registerid);

	/**
	 * Returns the paper name of this team.
	 *
	 * @return the paper name of this team
	 */
	public String getPaperName();

	/**
	 * Returns the localized paper name of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized paper name of this team
	 */
	@AutoEscape
	public String getPaperName(Locale locale);

	/**
	 * Returns the localized paper name of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized paper name of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getPaperName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized paper name of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized paper name of this team
	 */
	@AutoEscape
	public String getPaperName(String languageId);

	/**
	 * Returns the localized paper name of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized paper name of this team
	 */
	@AutoEscape
	public String getPaperName(String languageId, boolean useDefault);

	@AutoEscape
	public String getPaperNameCurrentLanguageId();

	@AutoEscape
	public String getPaperNameCurrentValue();

	/**
	 * Returns a map of the locales and localized paper names of this team.
	 *
	 * @return the locales and localized paper names of this team
	 */
	public Map<Locale, String> getPaperNameMap();

	/**
	 * Sets the paper name of this team.
	 *
	 * @param paperName the paper name of this team
	 */
	public void setPaperName(String paperName);

	/**
	 * Sets the localized paper name of this team in the language.
	 *
	 * @param paperName the localized paper name of this team
	 * @param locale the locale of the language
	 */
	public void setPaperName(String paperName, Locale locale);

	/**
	 * Sets the localized paper name of this team in the language, and sets the default locale.
	 *
	 * @param paperName the localized paper name of this team
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setPaperName(String paperName, Locale locale,
		Locale defaultLocale);

	public void setPaperNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized paper names of this team from the map of locales and localized paper names.
	 *
	 * @param paperNameMap the locales and localized paper names of this team
	 */
	public void setPaperNameMap(Map<Locale, String> paperNameMap);

	/**
	 * Sets the localized paper names of this team from the map of locales and localized paper names, and sets the default locale.
	 *
	 * @param paperNameMap the locales and localized paper names of this team
	 * @param defaultLocale the default locale
	 */
	public void setPaperNameMap(Map<Locale, String> paperNameMap,
		Locale defaultLocale);

	/**
	 * Returns the paper abstract of this team.
	 *
	 * @return the paper abstract of this team
	 */
	public String getPaperAbstract();

	/**
	 * Returns the localized paper abstract of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized paper abstract of this team
	 */
	@AutoEscape
	public String getPaperAbstract(Locale locale);

	/**
	 * Returns the localized paper abstract of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized paper abstract of this team. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getPaperAbstract(Locale locale, boolean useDefault);

	/**
	 * Returns the localized paper abstract of this team in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized paper abstract of this team
	 */
	@AutoEscape
	public String getPaperAbstract(String languageId);

	/**
	 * Returns the localized paper abstract of this team in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized paper abstract of this team
	 */
	@AutoEscape
	public String getPaperAbstract(String languageId, boolean useDefault);

	@AutoEscape
	public String getPaperAbstractCurrentLanguageId();

	@AutoEscape
	public String getPaperAbstractCurrentValue();

	/**
	 * Returns a map of the locales and localized paper abstracts of this team.
	 *
	 * @return the locales and localized paper abstracts of this team
	 */
	public Map<Locale, String> getPaperAbstractMap();

	/**
	 * Sets the paper abstract of this team.
	 *
	 * @param paperAbstract the paper abstract of this team
	 */
	public void setPaperAbstract(String paperAbstract);

	/**
	 * Sets the localized paper abstract of this team in the language.
	 *
	 * @param paperAbstract the localized paper abstract of this team
	 * @param locale the locale of the language
	 */
	public void setPaperAbstract(String paperAbstract, Locale locale);

	/**
	 * Sets the localized paper abstract of this team in the language, and sets the default locale.
	 *
	 * @param paperAbstract the localized paper abstract of this team
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setPaperAbstract(String paperAbstract, Locale locale,
		Locale defaultLocale);

	public void setPaperAbstractCurrentLanguageId(String languageId);

	/**
	 * Sets the localized paper abstracts of this team from the map of locales and localized paper abstracts.
	 *
	 * @param paperAbstractMap the locales and localized paper abstracts of this team
	 */
	public void setPaperAbstractMap(Map<Locale, String> paperAbstractMap);

	/**
	 * Sets the localized paper abstracts of this team from the map of locales and localized paper abstracts, and sets the default locale.
	 *
	 * @param paperAbstractMap the locales and localized paper abstracts of this team
	 * @param defaultLocale the default locale
	 */
	public void setPaperAbstractMap(Map<Locale, String> paperAbstractMap,
		Locale defaultLocale);

	/**
	 * Returns the paper file name of this team.
	 *
	 * @return the paper file name of this team
	 */
	@AutoEscape
	public String getPaperFileName();

	/**
	 * Sets the paper file name of this team.
	 *
	 * @param paperFileName the paper file name of this team
	 */
	public void setPaperFileName(String paperFileName);

	/**
	 * Returns the paper submission day of this team.
	 *
	 * @return the paper submission day of this team
	 */
	public Date getPaperSubmissionDay();

	/**
	 * Sets the paper submission day of this team.
	 *
	 * @param paperSubmissionDay the paper submission day of this team
	 */
	public void setPaperSubmissionDay(Date paperSubmissionDay);

	/**
	 * Returns the paper modify day of this team.
	 *
	 * @return the paper modify day of this team
	 */
	public Date getPaperModifyDay();

	/**
	 * Sets the paper modify day of this team.
	 *
	 * @param paperModifyDay the paper modify day of this team
	 */
	public void setPaperModifyDay(Date paperModifyDay);

	/**
	 * Returns the presentation name of this team.
	 *
	 * @return the presentation name of this team
	 */
	@AutoEscape
	public String getPresentationName();

	/**
	 * Sets the presentation name of this team.
	 *
	 * @param presentationName the presentation name of this team
	 */
	public void setPresentationName(String presentationName);

	/**
	 * Returns the presentation file name of this team.
	 *
	 * @return the presentation file name of this team
	 */
	@AutoEscape
	public String getPresentationFileName();

	/**
	 * Sets the presentation file name of this team.
	 *
	 * @param presentationFileName the presentation file name of this team
	 */
	public void setPresentationFileName(String presentationFileName);

	/**
	 * Returns the presentation submission day of this team.
	 *
	 * @return the presentation submission day of this team
	 */
	public Date getPresentationSubmissionDay();

	/**
	 * Sets the presentation submission day of this team.
	 *
	 * @param presentationSubmissionDay the presentation submission day of this team
	 */
	public void setPresentationSubmissionDay(Date presentationSubmissionDay);

	/**
	 * Returns the presentation modify day of this team.
	 *
	 * @return the presentation modify day of this team
	 */
	public Date getPresentationModifyDay();

	/**
	 * Sets the presentation modify day of this team.
	 *
	 * @param presentationModifyDay the presentation modify day of this team
	 */
	public void setPresentationModifyDay(Date presentationModifyDay);

	/**
	 * Returns the filepath of this team.
	 *
	 * @return the filepath of this team
	 */
	@AutoEscape
	public String getFilepath();

	/**
	 * Sets the filepath of this team.
	 *
	 * @param filepath the filepath of this team
	 */
	public void setFilepath(String filepath);

	/**
	 * Returns the childid of this team.
	 *
	 * @return the childid of this team
	 */
	public long getChildid();

	/**
	 * Sets the childid of this team.
	 *
	 * @param childid the childid of this team
	 */
	public void setChildid(long childid);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public String[] getAvailableLanguageIds();

	public String getDefaultLanguageId();

	public void prepareLocalizedFieldsForImport() throws LocaleException;

	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(edison.challenge.service.builder.model.Team team);

	@Override
	public int hashCode();

	@Override
	public CacheModel<edison.challenge.service.builder.model.Team> toCacheModel();

	@Override
	public edison.challenge.service.builder.model.Team toEscapedModel();

	@Override
	public edison.challenge.service.builder.model.Team toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}