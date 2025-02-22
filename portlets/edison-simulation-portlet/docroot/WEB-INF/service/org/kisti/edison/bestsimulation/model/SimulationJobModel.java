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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the SimulationJob service. Represents a row in the &quot;EDSIM_SimulationJob&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobImpl}.
 * </p>
 *
 * @author EDISON
 * @see SimulationJob
 * @see org.kisti.edison.bestsimulation.model.impl.SimulationJobImpl
 * @see org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl
 * @generated
 */
public interface SimulationJobModel extends BaseModel<SimulationJob> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a simulation job model instance should use the {@link SimulationJob} interface instead.
	 */

	/**
	 * Returns the primary key of this simulation job.
	 *
	 * @return the primary key of this simulation job
	 */
	public SimulationJobPK getPrimaryKey();

	/**
	 * Sets the primary key of this simulation job.
	 *
	 * @param primaryKey the primary key of this simulation job
	 */
	public void setPrimaryKey(SimulationJobPK primaryKey);

	/**
	 * Returns the job seq no of this simulation job.
	 *
	 * @return the job seq no of this simulation job
	 */
	public long getJobSeqNo();

	/**
	 * Sets the job seq no of this simulation job.
	 *
	 * @param jobSeqNo the job seq no of this simulation job
	 */
	public void setJobSeqNo(long jobSeqNo);

	/**
	 * Returns the simulation uuid of this simulation job.
	 *
	 * @return the simulation uuid of this simulation job
	 */
	@AutoEscape
	public String getSimulationUuid();

	/**
	 * Sets the simulation uuid of this simulation job.
	 *
	 * @param simulationUuid the simulation uuid of this simulation job
	 */
	public void setSimulationUuid(String simulationUuid);

	/**
	 * Returns the group ID of this simulation job.
	 *
	 * @return the group ID of this simulation job
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this simulation job.
	 *
	 * @param groupId the group ID of this simulation job
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the job uuid of this simulation job.
	 *
	 * @return the job uuid of this simulation job
	 */
	@AutoEscape
	public String getJobUuid();

	/**
	 * Sets the job uuid of this simulation job.
	 *
	 * @param jobUuid the job uuid of this simulation job
	 */
	public void setJobUuid(String jobUuid);

	/**
	 * Returns the job status of this simulation job.
	 *
	 * @return the job status of this simulation job
	 */
	public long getJobStatus();

	/**
	 * Sets the job status of this simulation job.
	 *
	 * @param jobStatus the job status of this simulation job
	 */
	public void setJobStatus(long jobStatus);

	/**
	 * Returns the job start dt of this simulation job.
	 *
	 * @return the job start dt of this simulation job
	 */
	public Date getJobStartDt();

	/**
	 * Sets the job start dt of this simulation job.
	 *
	 * @param jobStartDt the job start dt of this simulation job
	 */
	public void setJobStartDt(Date jobStartDt);

	/**
	 * Returns the job end dt of this simulation job.
	 *
	 * @return the job end dt of this simulation job
	 */
	public Date getJobEndDt();

	/**
	 * Sets the job end dt of this simulation job.
	 *
	 * @param jobEndDt the job end dt of this simulation job
	 */
	public void setJobEndDt(Date jobEndDt);

	/**
	 * Returns the job title of this simulation job.
	 *
	 * @return the job title of this simulation job
	 */
	public String getJobTitle();

	/**
	 * Returns the localized job title of this simulation job in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized job title of this simulation job
	 */
	@AutoEscape
	public String getJobTitle(Locale locale);

	/**
	 * Returns the localized job title of this simulation job in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job title of this simulation job. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getJobTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized job title of this simulation job in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized job title of this simulation job
	 */
	@AutoEscape
	public String getJobTitle(String languageId);

	/**
	 * Returns the localized job title of this simulation job in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job title of this simulation job
	 */
	@AutoEscape
	public String getJobTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getJobTitleCurrentLanguageId();

	@AutoEscape
	public String getJobTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized job titles of this simulation job.
	 *
	 * @return the locales and localized job titles of this simulation job
	 */
	public Map<Locale, String> getJobTitleMap();

	/**
	 * Sets the job title of this simulation job.
	 *
	 * @param jobTitle the job title of this simulation job
	 */
	public void setJobTitle(String jobTitle);

	/**
	 * Sets the localized job title of this simulation job in the language.
	 *
	 * @param jobTitle the localized job title of this simulation job
	 * @param locale the locale of the language
	 */
	public void setJobTitle(String jobTitle, Locale locale);

	/**
	 * Sets the localized job title of this simulation job in the language, and sets the default locale.
	 *
	 * @param jobTitle the localized job title of this simulation job
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setJobTitle(String jobTitle, Locale locale, Locale defaultLocale);

	public void setJobTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized job titles of this simulation job from the map of locales and localized job titles.
	 *
	 * @param jobTitleMap the locales and localized job titles of this simulation job
	 */
	public void setJobTitleMap(Map<Locale, String> jobTitleMap);

	/**
	 * Sets the localized job titles of this simulation job from the map of locales and localized job titles, and sets the default locale.
	 *
	 * @param jobTitleMap the locales and localized job titles of this simulation job
	 * @param defaultLocale the default locale
	 */
	public void setJobTitleMap(Map<Locale, String> jobTitleMap,
		Locale defaultLocale);

	/**
	 * Returns the job exec path of this simulation job.
	 *
	 * @return the job exec path of this simulation job
	 */
	public String getJobExecPath();

	/**
	 * Returns the localized job exec path of this simulation job in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized job exec path of this simulation job
	 */
	@AutoEscape
	public String getJobExecPath(Locale locale);

	/**
	 * Returns the localized job exec path of this simulation job in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job exec path of this simulation job. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getJobExecPath(Locale locale, boolean useDefault);

	/**
	 * Returns the localized job exec path of this simulation job in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized job exec path of this simulation job
	 */
	@AutoEscape
	public String getJobExecPath(String languageId);

	/**
	 * Returns the localized job exec path of this simulation job in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job exec path of this simulation job
	 */
	@AutoEscape
	public String getJobExecPath(String languageId, boolean useDefault);

	@AutoEscape
	public String getJobExecPathCurrentLanguageId();

	@AutoEscape
	public String getJobExecPathCurrentValue();

	/**
	 * Returns a map of the locales and localized job exec paths of this simulation job.
	 *
	 * @return the locales and localized job exec paths of this simulation job
	 */
	public Map<Locale, String> getJobExecPathMap();

	/**
	 * Sets the job exec path of this simulation job.
	 *
	 * @param jobExecPath the job exec path of this simulation job
	 */
	public void setJobExecPath(String jobExecPath);

	/**
	 * Sets the localized job exec path of this simulation job in the language.
	 *
	 * @param jobExecPath the localized job exec path of this simulation job
	 * @param locale the locale of the language
	 */
	public void setJobExecPath(String jobExecPath, Locale locale);

	/**
	 * Sets the localized job exec path of this simulation job in the language, and sets the default locale.
	 *
	 * @param jobExecPath the localized job exec path of this simulation job
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setJobExecPath(String jobExecPath, Locale locale,
		Locale defaultLocale);

	public void setJobExecPathCurrentLanguageId(String languageId);

	/**
	 * Sets the localized job exec paths of this simulation job from the map of locales and localized job exec paths.
	 *
	 * @param jobExecPathMap the locales and localized job exec paths of this simulation job
	 */
	public void setJobExecPathMap(Map<Locale, String> jobExecPathMap);

	/**
	 * Sets the localized job exec paths of this simulation job from the map of locales and localized job exec paths, and sets the default locale.
	 *
	 * @param jobExecPathMap the locales and localized job exec paths of this simulation job
	 * @param defaultLocale the default locale
	 */
	public void setJobExecPathMap(Map<Locale, String> jobExecPathMap,
		Locale defaultLocale);

	/**
	 * Returns the job phase of this simulation job.
	 *
	 * @return the job phase of this simulation job
	 */
	public long getJobPhase();

	/**
	 * Sets the job phase of this simulation job.
	 *
	 * @param jobPhase the job phase of this simulation job
	 */
	public void setJobPhase(long jobPhase);

	/**
	 * Returns the job submit dt of this simulation job.
	 *
	 * @return the job submit dt of this simulation job
	 */
	public Date getJobSubmitDt();

	/**
	 * Sets the job submit dt of this simulation job.
	 *
	 * @param jobSubmitDt the job submit dt of this simulation job
	 */
	public void setJobSubmitDt(Date jobSubmitDt);

	/**
	 * Returns the job university field of this simulation job.
	 *
	 * @return the job university field of this simulation job
	 */
	public long getJobUniversityField();

	/**
	 * Sets the job university field of this simulation job.
	 *
	 * @param jobUniversityField the job university field of this simulation job
	 */
	public void setJobUniversityField(long jobUniversityField);

	/**
	 * Returns the job input deck yn of this simulation job.
	 *
	 * @return the job input deck yn of this simulation job
	 */
	public boolean getJobInputDeckYn();

	/**
	 * Returns <code>true</code> if this simulation job is job input deck yn.
	 *
	 * @return <code>true</code> if this simulation job is job input deck yn; <code>false</code> otherwise
	 */
	public boolean isJobInputDeckYn();

	/**
	 * Sets whether this simulation job is job input deck yn.
	 *
	 * @param jobInputDeckYn the job input deck yn of this simulation job
	 */
	public void setJobInputDeckYn(boolean jobInputDeckYn);

	/**
	 * Returns the job input deck name of this simulation job.
	 *
	 * @return the job input deck name of this simulation job
	 */
	@AutoEscape
	public String getJobInputDeckName();

	/**
	 * Sets the job input deck name of this simulation job.
	 *
	 * @param jobInputDeckName the job input deck name of this simulation job
	 */
	public void setJobInputDeckName(String jobInputDeckName);

	/**
	 * Returns the job submit of this simulation job.
	 *
	 * @return the job submit of this simulation job
	 */
	public boolean getJobSubmit();

	/**
	 * Returns <code>true</code> if this simulation job is job submit.
	 *
	 * @return <code>true</code> if this simulation job is job submit; <code>false</code> otherwise
	 */
	public boolean isJobSubmit();

	/**
	 * Sets whether this simulation job is job submit.
	 *
	 * @param jobSubmit the job submit of this simulation job
	 */
	public void setJobSubmit(boolean jobSubmit);

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
	public int compareTo(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob);

	@Override
	public int hashCode();

	@Override
	public CacheModel<org.kisti.edison.bestsimulation.model.SimulationJob> toCacheModel();

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob toEscapedModel();

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}