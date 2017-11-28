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
 * This class is a wrapper for {@link Award}.
 * </p>
 *
 * @author kyj
 * @see Award
 * @generated
 */
public class AwardWrapper implements Award, ModelWrapper<Award> {
	public AwardWrapper(Award award) {
		_award = award;
	}

	@Override
	public Class<?> getModelClass() {
		return Award.class;
	}

	@Override
	public String getModelClassName() {
		return Award.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("awardid", getAwardid());
		attributes.put("awardGradeName", getAwardGradeName());
		attributes.put("awardName", getAwardName());
		attributes.put("prize", getPrize());
		attributes.put("number", getNumber());
		attributes.put("childid", getChildid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long awardid = (Long)attributes.get("awardid");

		if (awardid != null) {
			setAwardid(awardid);
		}

		String awardGradeName = (String)attributes.get("awardGradeName");

		if (awardGradeName != null) {
			setAwardGradeName(awardGradeName);
		}

		String awardName = (String)attributes.get("awardName");

		if (awardName != null) {
			setAwardName(awardName);
		}

		String prize = (String)attributes.get("prize");

		if (prize != null) {
			setPrize(prize);
		}

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
		}
	}

	/**
	* Returns the primary key of this award.
	*
	* @return the primary key of this award
	*/
	@Override
	public long getPrimaryKey() {
		return _award.getPrimaryKey();
	}

	/**
	* Sets the primary key of this award.
	*
	* @param primaryKey the primary key of this award
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_award.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the awardid of this award.
	*
	* @return the awardid of this award
	*/
	@Override
	public long getAwardid() {
		return _award.getAwardid();
	}

	/**
	* Sets the awardid of this award.
	*
	* @param awardid the awardid of this award
	*/
	@Override
	public void setAwardid(long awardid) {
		_award.setAwardid(awardid);
	}

	/**
	* Returns the award grade name of this award.
	*
	* @return the award grade name of this award
	*/
	@Override
	public java.lang.String getAwardGradeName() {
		return _award.getAwardGradeName();
	}

	/**
	* Returns the localized award grade name of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized award grade name of this award
	*/
	@Override
	public java.lang.String getAwardGradeName(java.util.Locale locale) {
		return _award.getAwardGradeName(locale);
	}

	/**
	* Returns the localized award grade name of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized award grade name of this award. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAwardGradeName(java.util.Locale locale,
		boolean useDefault) {
		return _award.getAwardGradeName(locale, useDefault);
	}

	/**
	* Returns the localized award grade name of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized award grade name of this award
	*/
	@Override
	public java.lang.String getAwardGradeName(java.lang.String languageId) {
		return _award.getAwardGradeName(languageId);
	}

	/**
	* Returns the localized award grade name of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized award grade name of this award
	*/
	@Override
	public java.lang.String getAwardGradeName(java.lang.String languageId,
		boolean useDefault) {
		return _award.getAwardGradeName(languageId, useDefault);
	}

	@Override
	public java.lang.String getAwardGradeNameCurrentLanguageId() {
		return _award.getAwardGradeNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getAwardGradeNameCurrentValue() {
		return _award.getAwardGradeNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized award grade names of this award.
	*
	* @return the locales and localized award grade names of this award
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getAwardGradeNameMap() {
		return _award.getAwardGradeNameMap();
	}

	/**
	* Sets the award grade name of this award.
	*
	* @param awardGradeName the award grade name of this award
	*/
	@Override
	public void setAwardGradeName(java.lang.String awardGradeName) {
		_award.setAwardGradeName(awardGradeName);
	}

	/**
	* Sets the localized award grade name of this award in the language.
	*
	* @param awardGradeName the localized award grade name of this award
	* @param locale the locale of the language
	*/
	@Override
	public void setAwardGradeName(java.lang.String awardGradeName,
		java.util.Locale locale) {
		_award.setAwardGradeName(awardGradeName, locale);
	}

	/**
	* Sets the localized award grade name of this award in the language, and sets the default locale.
	*
	* @param awardGradeName the localized award grade name of this award
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAwardGradeName(java.lang.String awardGradeName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_award.setAwardGradeName(awardGradeName, locale, defaultLocale);
	}

	@Override
	public void setAwardGradeNameCurrentLanguageId(java.lang.String languageId) {
		_award.setAwardGradeNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized award grade names of this award from the map of locales and localized award grade names.
	*
	* @param awardGradeNameMap the locales and localized award grade names of this award
	*/
	@Override
	public void setAwardGradeNameMap(
		java.util.Map<java.util.Locale, java.lang.String> awardGradeNameMap) {
		_award.setAwardGradeNameMap(awardGradeNameMap);
	}

	/**
	* Sets the localized award grade names of this award from the map of locales and localized award grade names, and sets the default locale.
	*
	* @param awardGradeNameMap the locales and localized award grade names of this award
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAwardGradeNameMap(
		java.util.Map<java.util.Locale, java.lang.String> awardGradeNameMap,
		java.util.Locale defaultLocale) {
		_award.setAwardGradeNameMap(awardGradeNameMap, defaultLocale);
	}

	/**
	* Returns the award name of this award.
	*
	* @return the award name of this award
	*/
	@Override
	public java.lang.String getAwardName() {
		return _award.getAwardName();
	}

	/**
	* Returns the localized award name of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized award name of this award
	*/
	@Override
	public java.lang.String getAwardName(java.util.Locale locale) {
		return _award.getAwardName(locale);
	}

	/**
	* Returns the localized award name of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized award name of this award. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAwardName(java.util.Locale locale,
		boolean useDefault) {
		return _award.getAwardName(locale, useDefault);
	}

	/**
	* Returns the localized award name of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized award name of this award
	*/
	@Override
	public java.lang.String getAwardName(java.lang.String languageId) {
		return _award.getAwardName(languageId);
	}

	/**
	* Returns the localized award name of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized award name of this award
	*/
	@Override
	public java.lang.String getAwardName(java.lang.String languageId,
		boolean useDefault) {
		return _award.getAwardName(languageId, useDefault);
	}

	@Override
	public java.lang.String getAwardNameCurrentLanguageId() {
		return _award.getAwardNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getAwardNameCurrentValue() {
		return _award.getAwardNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized award names of this award.
	*
	* @return the locales and localized award names of this award
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getAwardNameMap() {
		return _award.getAwardNameMap();
	}

	/**
	* Sets the award name of this award.
	*
	* @param awardName the award name of this award
	*/
	@Override
	public void setAwardName(java.lang.String awardName) {
		_award.setAwardName(awardName);
	}

	/**
	* Sets the localized award name of this award in the language.
	*
	* @param awardName the localized award name of this award
	* @param locale the locale of the language
	*/
	@Override
	public void setAwardName(java.lang.String awardName, java.util.Locale locale) {
		_award.setAwardName(awardName, locale);
	}

	/**
	* Sets the localized award name of this award in the language, and sets the default locale.
	*
	* @param awardName the localized award name of this award
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAwardName(java.lang.String awardName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_award.setAwardName(awardName, locale, defaultLocale);
	}

	@Override
	public void setAwardNameCurrentLanguageId(java.lang.String languageId) {
		_award.setAwardNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized award names of this award from the map of locales and localized award names.
	*
	* @param awardNameMap the locales and localized award names of this award
	*/
	@Override
	public void setAwardNameMap(
		java.util.Map<java.util.Locale, java.lang.String> awardNameMap) {
		_award.setAwardNameMap(awardNameMap);
	}

	/**
	* Sets the localized award names of this award from the map of locales and localized award names, and sets the default locale.
	*
	* @param awardNameMap the locales and localized award names of this award
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAwardNameMap(
		java.util.Map<java.util.Locale, java.lang.String> awardNameMap,
		java.util.Locale defaultLocale) {
		_award.setAwardNameMap(awardNameMap, defaultLocale);
	}

	/**
	* Returns the prize of this award.
	*
	* @return the prize of this award
	*/
	@Override
	public java.lang.String getPrize() {
		return _award.getPrize();
	}

	/**
	* Returns the localized prize of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized prize of this award
	*/
	@Override
	public java.lang.String getPrize(java.util.Locale locale) {
		return _award.getPrize(locale);
	}

	/**
	* Returns the localized prize of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized prize of this award. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPrize(java.util.Locale locale, boolean useDefault) {
		return _award.getPrize(locale, useDefault);
	}

	/**
	* Returns the localized prize of this award in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized prize of this award
	*/
	@Override
	public java.lang.String getPrize(java.lang.String languageId) {
		return _award.getPrize(languageId);
	}

	/**
	* Returns the localized prize of this award in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized prize of this award
	*/
	@Override
	public java.lang.String getPrize(java.lang.String languageId,
		boolean useDefault) {
		return _award.getPrize(languageId, useDefault);
	}

	@Override
	public java.lang.String getPrizeCurrentLanguageId() {
		return _award.getPrizeCurrentLanguageId();
	}

	@Override
	public java.lang.String getPrizeCurrentValue() {
		return _award.getPrizeCurrentValue();
	}

	/**
	* Returns a map of the locales and localized prizes of this award.
	*
	* @return the locales and localized prizes of this award
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getPrizeMap() {
		return _award.getPrizeMap();
	}

	/**
	* Sets the prize of this award.
	*
	* @param prize the prize of this award
	*/
	@Override
	public void setPrize(java.lang.String prize) {
		_award.setPrize(prize);
	}

	/**
	* Sets the localized prize of this award in the language.
	*
	* @param prize the localized prize of this award
	* @param locale the locale of the language
	*/
	@Override
	public void setPrize(java.lang.String prize, java.util.Locale locale) {
		_award.setPrize(prize, locale);
	}

	/**
	* Sets the localized prize of this award in the language, and sets the default locale.
	*
	* @param prize the localized prize of this award
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPrize(java.lang.String prize, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_award.setPrize(prize, locale, defaultLocale);
	}

	@Override
	public void setPrizeCurrentLanguageId(java.lang.String languageId) {
		_award.setPrizeCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized prizes of this award from the map of locales and localized prizes.
	*
	* @param prizeMap the locales and localized prizes of this award
	*/
	@Override
	public void setPrizeMap(
		java.util.Map<java.util.Locale, java.lang.String> prizeMap) {
		_award.setPrizeMap(prizeMap);
	}

	/**
	* Sets the localized prizes of this award from the map of locales and localized prizes, and sets the default locale.
	*
	* @param prizeMap the locales and localized prizes of this award
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPrizeMap(
		java.util.Map<java.util.Locale, java.lang.String> prizeMap,
		java.util.Locale defaultLocale) {
		_award.setPrizeMap(prizeMap, defaultLocale);
	}

	/**
	* Returns the number of this award.
	*
	* @return the number of this award
	*/
	@Override
	public java.lang.String getNumber() {
		return _award.getNumber();
	}

	/**
	* Sets the number of this award.
	*
	* @param number the number of this award
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_award.setNumber(number);
	}

	/**
	* Returns the childid of this award.
	*
	* @return the childid of this award
	*/
	@Override
	public long getChildid() {
		return _award.getChildid();
	}

	/**
	* Sets the childid of this award.
	*
	* @param childid the childid of this award
	*/
	@Override
	public void setChildid(long childid) {
		_award.setChildid(childid);
	}

	@Override
	public boolean isNew() {
		return _award.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_award.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _award.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_award.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _award.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _award.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_award.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _award.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_award.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_award.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_award.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _award.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _award.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_award.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_award.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new AwardWrapper((Award)_award.clone());
	}

	@Override
	public int compareTo(edison.challenge.service.builder.model.Award award) {
		return _award.compareTo(award);
	}

	@Override
	public int hashCode() {
		return _award.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Award> toCacheModel() {
		return _award.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Award toEscapedModel() {
		return new AwardWrapper(_award.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Award toUnescapedModel() {
		return new AwardWrapper(_award.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _award.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _award.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_award.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AwardWrapper)) {
			return false;
		}

		AwardWrapper awardWrapper = (AwardWrapper)obj;

		if (Validator.equals(_award, awardWrapper._award)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Award getWrappedAward() {
		return _award;
	}

	@Override
	public Award getWrappedModel() {
		return _award;
	}

	@Override
	public void resetOriginalValues() {
		_award.resetOriginalValues();
	}

	private Award _award;
}