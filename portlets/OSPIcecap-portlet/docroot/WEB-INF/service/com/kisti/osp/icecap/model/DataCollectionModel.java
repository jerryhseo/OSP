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

package com.kisti.osp.icecap.model;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the DataCollection service. Represents a row in the &quot;icecap_DataCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.kisti.osp.icecap.model.impl.DataCollectionImpl}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollection
 * @see com.kisti.osp.icecap.model.impl.DataCollectionImpl
 * @see com.kisti.osp.icecap.model.impl.DataCollectionModelImpl
 * @generated
 */
public interface DataCollectionModel extends BaseModel<DataCollection>,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a data collection model instance should use the {@link DataCollection} interface instead.
	 */

	/**
	 * Returns the primary key of this data collection.
	 *
	 * @return the primary key of this data collection
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this data collection.
	 *
	 * @param primaryKey the primary key of this data collection
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this data collection.
	 *
	 * @return the uuid of this data collection
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this data collection.
	 *
	 * @param uuid the uuid of this data collection
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the name of this data collection.
	 *
	 * @return the name of this data collection
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this data collection.
	 *
	 * @param name the name of this data collection
	 */
	public void setName(String name);

	/**
	 * Returns the version of this data collection.
	 *
	 * @return the version of this data collection
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this data collection.
	 *
	 * @param version the version of this data collection
	 */
	public void setVersion(String version);

	/**
	 * Returns the title of this data collection.
	 *
	 * @return the title of this data collection
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this data collection in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this data collection
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this data collection in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this data collection. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this data collection in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this data collection
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this data collection in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this data collection
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this data collection.
	 *
	 * @return the locales and localized titles of this data collection
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this data collection.
	 *
	 * @param title the title of this data collection
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this data collection in the language.
	 *
	 * @param title the localized title of this data collection
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this data collection in the language, and sets the default locale.
	 *
	 * @param title the localized title of this data collection
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this data collection from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this data collection
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this data collection from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this data collection
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the group ID of this data collection.
	 *
	 * @return the group ID of this data collection
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this data collection.
	 *
	 * @param groupId the group ID of this data collection
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this data collection.
	 *
	 * @return the company ID of this data collection
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this data collection.
	 *
	 * @param companyId the company ID of this data collection
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this data collection.
	 *
	 * @return the user ID of this data collection
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this data collection.
	 *
	 * @param userId the user ID of this data collection
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this data collection.
	 *
	 * @return the user uuid of this data collection
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this data collection.
	 *
	 * @param userUuid the user uuid of this data collection
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this data collection.
	 *
	 * @return the create date of this data collection
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this data collection.
	 *
	 * @param createDate the create date of this data collection
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this data collection.
	 *
	 * @return the modified date of this data collection
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this data collection.
	 *
	 * @param modifiedDate the modified date of this data collection
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the description of this data collection.
	 *
	 * @return the description of this data collection
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this data collection in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this data collection
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this data collection in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this data collection. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this data collection in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this data collection
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this data collection in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this data collection
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this data collection.
	 *
	 * @return the locales and localized descriptions of this data collection
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this data collection.
	 *
	 * @param description the description of this data collection
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this data collection in the language.
	 *
	 * @param description the localized description of this data collection
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this data collection in the language, and sets the default locale.
	 *
	 * @param description the localized description of this data collection
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this data collection from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this data collection
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this data collection from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this data collection
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the target language of this data collection.
	 *
	 * @return the target language of this data collection
	 */
	@AutoEscape
	public String getTargetLanguage();

	/**
	 * Sets the target language of this data collection.
	 *
	 * @param targetLanguage the target language of this data collection
	 */
	public void setTargetLanguage(String targetLanguage);

	/**
	 * Returns the status of this data collection.
	 *
	 * @return the status of this data collection
	 */
	public int getStatus();

	/**
	 * Sets the status of this data collection.
	 *
	 * @param status the status of this data collection
	 */
	public void setStatus(int status);

	/**
	 * Returns the access method of this data collection.
	 *
	 * @return the access method of this data collection
	 */
	@AutoEscape
	public String getAccessMethod();

	/**
	 * Sets the access method of this data collection.
	 *
	 * @param accessMethod the access method of this data collection
	 */
	public void setAccessMethod(String accessMethod);

	/**
	 * Returns the collection ID of this data collection.
	 *
	 * @return the collection ID of this data collection
	 */
	public long getCollectionId();

	/**
	 * Sets the collection ID of this data collection.
	 *
	 * @param collectionId the collection ID of this data collection
	 */
	public void setCollectionId(long collectionId);

	/**
	 * Returns the type ID of this data collection.
	 *
	 * @return the type ID of this data collection
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this data collection.
	 *
	 * @param typeId the type ID of this data collection
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the folder ID of this data collection.
	 *
	 * @return the folder ID of this data collection
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this data collection.
	 *
	 * @param folderId the folder ID of this data collection
	 */
	public void setFolderId(long folderId);

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
		com.kisti.osp.icecap.model.DataCollection dataCollection);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.kisti.osp.icecap.model.DataCollection> toCacheModel();

	@Override
	public com.kisti.osp.icecap.model.DataCollection toEscapedModel();

	@Override
	public com.kisti.osp.icecap.model.DataCollection toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}