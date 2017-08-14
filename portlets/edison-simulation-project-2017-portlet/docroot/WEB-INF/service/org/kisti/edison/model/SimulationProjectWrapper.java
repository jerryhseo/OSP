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

package org.kisti.edison.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SimulationProject}.
 * </p>
 *
 * @author edison
 * @see SimulationProject
 * @generated
 */
public class SimulationProjectWrapper implements SimulationProject,
	ModelWrapper<SimulationProject> {
	public SimulationProjectWrapper(SimulationProject simulationProject) {
		_simulationProject = simulationProject;
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationProject.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationProjectId", getSimulationProjectId());
		attributes.put("title", getTitle());
		attributes.put("serviceLanguage", getServiceLanguage());
		attributes.put("projectOpenYn", getProjectOpenYn());
		attributes.put("explain", getExplain());
		attributes.put("iconId", getIconId());
		attributes.put("imageFolderId", getImageFolderId());
		attributes.put("ownerId", getOwnerId());
		attributes.put("insertId", getInsertId());
		attributes.put("insertDate", getInsertDate());
		attributes.put("updateId", getUpdateId());
		attributes.put("updateDate", getUpdateDate());
		attributes.put("templetId", getTempletId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simulationProjectId = (Long)attributes.get("simulationProjectId");

		if (simulationProjectId != null) {
			setSimulationProjectId(simulationProjectId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String serviceLanguage = (String)attributes.get("serviceLanguage");

		if (serviceLanguage != null) {
			setServiceLanguage(serviceLanguage);
		}

		Boolean projectOpenYn = (Boolean)attributes.get("projectOpenYn");

		if (projectOpenYn != null) {
			setProjectOpenYn(projectOpenYn);
		}

		String explain = (String)attributes.get("explain");

		if (explain != null) {
			setExplain(explain);
		}

		Long iconId = (Long)attributes.get("iconId");

		if (iconId != null) {
			setIconId(iconId);
		}

		Long imageFolderId = (Long)attributes.get("imageFolderId");

		if (imageFolderId != null) {
			setImageFolderId(imageFolderId);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
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

		Long templetId = (Long)attributes.get("templetId");

		if (templetId != null) {
			setTempletId(templetId);
		}
	}

	/**
	* Returns the primary key of this simulation project.
	*
	* @return the primary key of this simulation project
	*/
	@Override
	public long getPrimaryKey() {
		return _simulationProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this simulation project.
	*
	* @param primaryKey the primary key of this simulation project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_simulationProject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the simulation project ID of this simulation project.
	*
	* @return the simulation project ID of this simulation project
	*/
	@Override
	public long getSimulationProjectId() {
		return _simulationProject.getSimulationProjectId();
	}

	/**
	* Sets the simulation project ID of this simulation project.
	*
	* @param simulationProjectId the simulation project ID of this simulation project
	*/
	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProject.setSimulationProjectId(simulationProjectId);
	}

	/**
	* Returns the title of this simulation project.
	*
	* @return the title of this simulation project
	*/
	@Override
	public java.lang.String getTitle() {
		return _simulationProject.getTitle();
	}

	/**
	* Returns the localized title of this simulation project in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this simulation project
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _simulationProject.getTitle(locale);
	}

	/**
	* Returns the localized title of this simulation project in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this simulation project. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _simulationProject.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this simulation project in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this simulation project
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _simulationProject.getTitle(languageId);
	}

	/**
	* Returns the localized title of this simulation project in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this simulation project
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _simulationProject.getTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _simulationProject.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _simulationProject.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this simulation project.
	*
	* @return the locales and localized titles of this simulation project
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _simulationProject.getTitleMap();
	}

	/**
	* Sets the title of this simulation project.
	*
	* @param title the title of this simulation project
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_simulationProject.setTitle(title);
	}

	/**
	* Sets the localized title of this simulation project in the language.
	*
	* @param title the localized title of this simulation project
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_simulationProject.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this simulation project in the language, and sets the default locale.
	*
	* @param title the localized title of this simulation project
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_simulationProject.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_simulationProject.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this simulation project from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this simulation project
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_simulationProject.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this simulation project from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this simulation project
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_simulationProject.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the service language of this simulation project.
	*
	* @return the service language of this simulation project
	*/
	@Override
	public java.lang.String getServiceLanguage() {
		return _simulationProject.getServiceLanguage();
	}

	/**
	* Sets the service language of this simulation project.
	*
	* @param serviceLanguage the service language of this simulation project
	*/
	@Override
	public void setServiceLanguage(java.lang.String serviceLanguage) {
		_simulationProject.setServiceLanguage(serviceLanguage);
	}

	/**
	* Returns the project open yn of this simulation project.
	*
	* @return the project open yn of this simulation project
	*/
	@Override
	public boolean getProjectOpenYn() {
		return _simulationProject.getProjectOpenYn();
	}

	/**
	* Returns <code>true</code> if this simulation project is project open yn.
	*
	* @return <code>true</code> if this simulation project is project open yn; <code>false</code> otherwise
	*/
	@Override
	public boolean isProjectOpenYn() {
		return _simulationProject.isProjectOpenYn();
	}

	/**
	* Sets whether this simulation project is project open yn.
	*
	* @param projectOpenYn the project open yn of this simulation project
	*/
	@Override
	public void setProjectOpenYn(boolean projectOpenYn) {
		_simulationProject.setProjectOpenYn(projectOpenYn);
	}

	/**
	* Returns the explain of this simulation project.
	*
	* @return the explain of this simulation project
	*/
	@Override
	public java.lang.String getExplain() {
		return _simulationProject.getExplain();
	}

	/**
	* Returns the localized explain of this simulation project in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized explain of this simulation project
	*/
	@Override
	public java.lang.String getExplain(java.util.Locale locale) {
		return _simulationProject.getExplain(locale);
	}

	/**
	* Returns the localized explain of this simulation project in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized explain of this simulation project. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getExplain(java.util.Locale locale,
		boolean useDefault) {
		return _simulationProject.getExplain(locale, useDefault);
	}

	/**
	* Returns the localized explain of this simulation project in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized explain of this simulation project
	*/
	@Override
	public java.lang.String getExplain(java.lang.String languageId) {
		return _simulationProject.getExplain(languageId);
	}

	/**
	* Returns the localized explain of this simulation project in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized explain of this simulation project
	*/
	@Override
	public java.lang.String getExplain(java.lang.String languageId,
		boolean useDefault) {
		return _simulationProject.getExplain(languageId, useDefault);
	}

	@Override
	public java.lang.String getExplainCurrentLanguageId() {
		return _simulationProject.getExplainCurrentLanguageId();
	}

	@Override
	public java.lang.String getExplainCurrentValue() {
		return _simulationProject.getExplainCurrentValue();
	}

	/**
	* Returns a map of the locales and localized explains of this simulation project.
	*
	* @return the locales and localized explains of this simulation project
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getExplainMap() {
		return _simulationProject.getExplainMap();
	}

	/**
	* Sets the explain of this simulation project.
	*
	* @param explain the explain of this simulation project
	*/
	@Override
	public void setExplain(java.lang.String explain) {
		_simulationProject.setExplain(explain);
	}

	/**
	* Sets the localized explain of this simulation project in the language.
	*
	* @param explain the localized explain of this simulation project
	* @param locale the locale of the language
	*/
	@Override
	public void setExplain(java.lang.String explain, java.util.Locale locale) {
		_simulationProject.setExplain(explain, locale);
	}

	/**
	* Sets the localized explain of this simulation project in the language, and sets the default locale.
	*
	* @param explain the localized explain of this simulation project
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setExplain(java.lang.String explain, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_simulationProject.setExplain(explain, locale, defaultLocale);
	}

	@Override
	public void setExplainCurrentLanguageId(java.lang.String languageId) {
		_simulationProject.setExplainCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized explains of this simulation project from the map of locales and localized explains.
	*
	* @param explainMap the locales and localized explains of this simulation project
	*/
	@Override
	public void setExplainMap(
		java.util.Map<java.util.Locale, java.lang.String> explainMap) {
		_simulationProject.setExplainMap(explainMap);
	}

	/**
	* Sets the localized explains of this simulation project from the map of locales and localized explains, and sets the default locale.
	*
	* @param explainMap the locales and localized explains of this simulation project
	* @param defaultLocale the default locale
	*/
	@Override
	public void setExplainMap(
		java.util.Map<java.util.Locale, java.lang.String> explainMap,
		java.util.Locale defaultLocale) {
		_simulationProject.setExplainMap(explainMap, defaultLocale);
	}

	/**
	* Returns the icon ID of this simulation project.
	*
	* @return the icon ID of this simulation project
	*/
	@Override
	public long getIconId() {
		return _simulationProject.getIconId();
	}

	/**
	* Sets the icon ID of this simulation project.
	*
	* @param iconId the icon ID of this simulation project
	*/
	@Override
	public void setIconId(long iconId) {
		_simulationProject.setIconId(iconId);
	}

	/**
	* Returns the image folder ID of this simulation project.
	*
	* @return the image folder ID of this simulation project
	*/
	@Override
	public long getImageFolderId() {
		return _simulationProject.getImageFolderId();
	}

	/**
	* Sets the image folder ID of this simulation project.
	*
	* @param imageFolderId the image folder ID of this simulation project
	*/
	@Override
	public void setImageFolderId(long imageFolderId) {
		_simulationProject.setImageFolderId(imageFolderId);
	}

	/**
	* Returns the owner ID of this simulation project.
	*
	* @return the owner ID of this simulation project
	*/
	@Override
	public long getOwnerId() {
		return _simulationProject.getOwnerId();
	}

	/**
	* Sets the owner ID of this simulation project.
	*
	* @param ownerId the owner ID of this simulation project
	*/
	@Override
	public void setOwnerId(long ownerId) {
		_simulationProject.setOwnerId(ownerId);
	}

	/**
	* Returns the insert ID of this simulation project.
	*
	* @return the insert ID of this simulation project
	*/
	@Override
	public long getInsertId() {
		return _simulationProject.getInsertId();
	}

	/**
	* Sets the insert ID of this simulation project.
	*
	* @param insertId the insert ID of this simulation project
	*/
	@Override
	public void setInsertId(long insertId) {
		_simulationProject.setInsertId(insertId);
	}

	/**
	* Returns the insert date of this simulation project.
	*
	* @return the insert date of this simulation project
	*/
	@Override
	public java.util.Date getInsertDate() {
		return _simulationProject.getInsertDate();
	}

	/**
	* Sets the insert date of this simulation project.
	*
	* @param insertDate the insert date of this simulation project
	*/
	@Override
	public void setInsertDate(java.util.Date insertDate) {
		_simulationProject.setInsertDate(insertDate);
	}

	/**
	* Returns the update ID of this simulation project.
	*
	* @return the update ID of this simulation project
	*/
	@Override
	public long getUpdateId() {
		return _simulationProject.getUpdateId();
	}

	/**
	* Sets the update ID of this simulation project.
	*
	* @param updateId the update ID of this simulation project
	*/
	@Override
	public void setUpdateId(long updateId) {
		_simulationProject.setUpdateId(updateId);
	}

	/**
	* Returns the update date of this simulation project.
	*
	* @return the update date of this simulation project
	*/
	@Override
	public java.util.Date getUpdateDate() {
		return _simulationProject.getUpdateDate();
	}

	/**
	* Sets the update date of this simulation project.
	*
	* @param updateDate the update date of this simulation project
	*/
	@Override
	public void setUpdateDate(java.util.Date updateDate) {
		_simulationProject.setUpdateDate(updateDate);
	}

	/**
	* Returns the templet ID of this simulation project.
	*
	* @return the templet ID of this simulation project
	*/
	@Override
	public long getTempletId() {
		return _simulationProject.getTempletId();
	}

	/**
	* Sets the templet ID of this simulation project.
	*
	* @param templetId the templet ID of this simulation project
	*/
	@Override
	public void setTempletId(long templetId) {
		_simulationProject.setTempletId(templetId);
	}

	@Override
	public boolean isNew() {
		return _simulationProject.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simulationProject.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simulationProject.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simulationProject.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simulationProject.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simulationProject.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simulationProject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simulationProject.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simulationProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simulationProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simulationProject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _simulationProject.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _simulationProject.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_simulationProject.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_simulationProject.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new SimulationProjectWrapper((SimulationProject)_simulationProject.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.model.SimulationProject simulationProject) {
		return _simulationProject.compareTo(simulationProject);
	}

	@Override
	public int hashCode() {
		return _simulationProject.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.SimulationProject> toCacheModel() {
		return _simulationProject.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.SimulationProject toEscapedModel() {
		return new SimulationProjectWrapper(_simulationProject.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.SimulationProject toUnescapedModel() {
		return new SimulationProjectWrapper(_simulationProject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simulationProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simulationProject.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationProject.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationProjectWrapper)) {
			return false;
		}

		SimulationProjectWrapper simulationProjectWrapper = (SimulationProjectWrapper)obj;

		if (Validator.equals(_simulationProject,
					simulationProjectWrapper._simulationProject)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SimulationProject getWrappedSimulationProject() {
		return _simulationProject;
	}

	@Override
	public SimulationProject getWrappedModel() {
		return _simulationProject;
	}

	@Override
	public void resetOriginalValues() {
		_simulationProject.resetOriginalValues();
	}

	private SimulationProject _simulationProject;
}