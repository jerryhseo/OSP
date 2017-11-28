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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Agency}.
 * </p>
 *
 * @author kyj
 * @see Agency
 * @generated
 */
public class AgencyWrapper implements Agency, ModelWrapper<Agency> {
	public AgencyWrapper(Agency agency) {
		_agency = agency;
	}

	@Override
	public Class<?> getModelClass() {
		return Agency.class;
	}

	@Override
	public String getModelClassName() {
		return Agency.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("agencyid", getAgencyid());
		attributes.put("name", getName());
		attributes.put("level", getLevel());
		attributes.put("url", getUrl());
		attributes.put("childid", getChildid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long agencyid = (Long)attributes.get("agencyid");

		if (agencyid != null) {
			setAgencyid(agencyid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String level = (String)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
		}
	}

	/**
	* Returns the primary key of this agency.
	*
	* @return the primary key of this agency
	*/
	@Override
	public long getPrimaryKey() {
		return _agency.getPrimaryKey();
	}

	/**
	* Sets the primary key of this agency.
	*
	* @param primaryKey the primary key of this agency
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_agency.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the agencyid of this agency.
	*
	* @return the agencyid of this agency
	*/
	@Override
	public long getAgencyid() {
		return _agency.getAgencyid();
	}

	/**
	* Sets the agencyid of this agency.
	*
	* @param agencyid the agencyid of this agency
	*/
	@Override
	public void setAgencyid(long agencyid) {
		_agency.setAgencyid(agencyid);
	}

	/**
	* Returns the name of this agency.
	*
	* @return the name of this agency
	*/
	@Override
	public java.lang.String getName() {
		return _agency.getName();
	}

	/**
	* Returns the localized name of this agency in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this agency
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _agency.getName(locale);
	}

	/**
	* Returns the localized name of this agency in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this agency. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _agency.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this agency in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this agency
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _agency.getName(languageId);
	}

	/**
	* Returns the localized name of this agency in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this agency
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _agency.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _agency.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _agency.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this agency.
	*
	* @return the locales and localized names of this agency
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _agency.getNameMap();
	}

	/**
	* Sets the name of this agency.
	*
	* @param name the name of this agency
	*/
	@Override
	public void setName(java.lang.String name) {
		_agency.setName(name);
	}

	/**
	* Sets the localized name of this agency in the language.
	*
	* @param name the localized name of this agency
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_agency.setName(name, locale);
	}

	/**
	* Sets the localized name of this agency in the language, and sets the default locale.
	*
	* @param name the localized name of this agency
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_agency.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_agency.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this agency from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this agency
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_agency.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this agency from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this agency
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_agency.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the level of this agency.
	*
	* @return the level of this agency
	*/
	@Override
	public java.lang.String getLevel() {
		return _agency.getLevel();
	}

	/**
	* Sets the level of this agency.
	*
	* @param level the level of this agency
	*/
	@Override
	public void setLevel(java.lang.String level) {
		_agency.setLevel(level);
	}

	/**
	* Returns the url of this agency.
	*
	* @return the url of this agency
	*/
	@Override
	public java.lang.String getUrl() {
		return _agency.getUrl();
	}

	/**
	* Sets the url of this agency.
	*
	* @param url the url of this agency
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_agency.setUrl(url);
	}

	/**
	* Returns the childid of this agency.
	*
	* @return the childid of this agency
	*/
	@Override
	public long getChildid() {
		return _agency.getChildid();
	}

	/**
	* Sets the childid of this agency.
	*
	* @param childid the childid of this agency
	*/
	@Override
	public void setChildid(long childid) {
		_agency.setChildid(childid);
	}

	@Override
	public boolean isNew() {
		return _agency.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_agency.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _agency.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_agency.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _agency.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _agency.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_agency.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _agency.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_agency.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_agency.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_agency.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _agency.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _agency.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_agency.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_agency.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new AgencyWrapper((Agency)_agency.clone());
	}

	@Override
	public int compareTo(edison.challenge.service.builder.model.Agency agency) {
		return _agency.compareTo(agency);
	}

	@Override
	public int hashCode() {
		return _agency.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Agency> toCacheModel() {
		return _agency.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Agency toEscapedModel() {
		return new AgencyWrapper(_agency.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Agency toUnescapedModel() {
		return new AgencyWrapper(_agency.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _agency.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _agency.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_agency.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgencyWrapper)) {
			return false;
		}

		AgencyWrapper agencyWrapper = (AgencyWrapper)obj;

		if (Validator.equals(_agency, agencyWrapper._agency)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Agency getWrappedAgency() {
		return _agency;
	}

	@Override
	public Agency getWrappedModel() {
		return _agency;
	}

	@Override
	public void resetOriginalValues() {
		_agency.resetOriginalValues();
	}

	private Agency _agency;
}