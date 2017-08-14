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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DataType}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataType
 * @generated
 */
public class DataTypeWrapper implements DataType, ModelWrapper<DataType> {
	public DataTypeWrapper(DataType dataType) {
		_dataType = dataType;
	}

	@Override
	public Class<?> getModelClass() {
		return DataType.class;
	}

	@Override
	public String getModelClassName() {
		return DataType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("typeId", getTypeId());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samplePath", getSamplePath());
		attributes.put("status", getStatus());
		attributes.put("description", getDescription());
		attributes.put("groupId", getGroupId());
		attributes.put("isFavorite", getIsFavorite());
		attributes.put("ownerId", getOwnerId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String samplePath = (String)attributes.get("samplePath");

		if (samplePath != null) {
			setSamplePath(samplePath);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Boolean isFavorite = (Boolean)attributes.get("isFavorite");

		if (isFavorite != null) {
			setIsFavorite(isFavorite);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}
	}

	/**
	* Returns the primary key of this data type.
	*
	* @return the primary key of this data type
	*/
	@Override
	public long getPrimaryKey() {
		return _dataType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data type.
	*
	* @param primaryKey the primary key of this data type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataType.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this data type.
	*
	* @return the uuid of this data type
	*/
	@Override
	public java.lang.String getUuid() {
		return _dataType.getUuid();
	}

	/**
	* Sets the uuid of this data type.
	*
	* @param uuid the uuid of this data type
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_dataType.setUuid(uuid);
	}

	/**
	* Returns the type ID of this data type.
	*
	* @return the type ID of this data type
	*/
	@Override
	public long getTypeId() {
		return _dataType.getTypeId();
	}

	/**
	* Sets the type ID of this data type.
	*
	* @param typeId the type ID of this data type
	*/
	@Override
	public void setTypeId(long typeId) {
		_dataType.setTypeId(typeId);
	}

	/**
	* Returns the name of this data type.
	*
	* @return the name of this data type
	*/
	@Override
	public java.lang.String getName() {
		return _dataType.getName();
	}

	/**
	* Sets the name of this data type.
	*
	* @param name the name of this data type
	*/
	@Override
	public void setName(java.lang.String name) {
		_dataType.setName(name);
	}

	/**
	* Returns the version of this data type.
	*
	* @return the version of this data type
	*/
	@Override
	public java.lang.String getVersion() {
		return _dataType.getVersion();
	}

	/**
	* Sets the version of this data type.
	*
	* @param version the version of this data type
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_dataType.setVersion(version);
	}

	/**
	* Returns the company ID of this data type.
	*
	* @return the company ID of this data type
	*/
	@Override
	public long getCompanyId() {
		return _dataType.getCompanyId();
	}

	/**
	* Sets the company ID of this data type.
	*
	* @param companyId the company ID of this data type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dataType.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this data type.
	*
	* @return the user ID of this data type
	*/
	@Override
	public long getUserId() {
		return _dataType.getUserId();
	}

	/**
	* Sets the user ID of this data type.
	*
	* @param userId the user ID of this data type
	*/
	@Override
	public void setUserId(long userId) {
		_dataType.setUserId(userId);
	}

	/**
	* Returns the user uuid of this data type.
	*
	* @return the user uuid of this data type
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataType.getUserUuid();
	}

	/**
	* Sets the user uuid of this data type.
	*
	* @param userUuid the user uuid of this data type
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dataType.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this data type.
	*
	* @return the create date of this data type
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _dataType.getCreateDate();
	}

	/**
	* Sets the create date of this data type.
	*
	* @param createDate the create date of this data type
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_dataType.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this data type.
	*
	* @return the modified date of this data type
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _dataType.getModifiedDate();
	}

	/**
	* Sets the modified date of this data type.
	*
	* @param modifiedDate the modified date of this data type
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_dataType.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the sample path of this data type.
	*
	* @return the sample path of this data type
	*/
	@Override
	public java.lang.String getSamplePath() {
		return _dataType.getSamplePath();
	}

	/**
	* Sets the sample path of this data type.
	*
	* @param samplePath the sample path of this data type
	*/
	@Override
	public void setSamplePath(java.lang.String samplePath) {
		_dataType.setSamplePath(samplePath);
	}

	/**
	* Returns the status of this data type.
	*
	* @return the status of this data type
	*/
	@Override
	public int getStatus() {
		return _dataType.getStatus();
	}

	/**
	* Sets the status of this data type.
	*
	* @param status the status of this data type
	*/
	@Override
	public void setStatus(int status) {
		_dataType.setStatus(status);
	}

	/**
	* Returns the description of this data type.
	*
	* @return the description of this data type
	*/
	@Override
	public java.lang.String getDescription() {
		return _dataType.getDescription();
	}

	/**
	* Returns the localized description of this data type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this data type
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _dataType.getDescription(locale);
	}

	/**
	* Returns the localized description of this data type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this data type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _dataType.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this data type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this data type
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _dataType.getDescription(languageId);
	}

	/**
	* Returns the localized description of this data type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this data type
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _dataType.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _dataType.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _dataType.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this data type.
	*
	* @return the locales and localized descriptions of this data type
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _dataType.getDescriptionMap();
	}

	/**
	* Sets the description of this data type.
	*
	* @param description the description of this data type
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_dataType.setDescription(description);
	}

	/**
	* Sets the localized description of this data type in the language.
	*
	* @param description the localized description of this data type
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_dataType.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this data type in the language, and sets the default locale.
	*
	* @param description the localized description of this data type
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_dataType.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_dataType.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this data type from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this data type
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_dataType.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this data type from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this data type
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_dataType.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the group ID of this data type.
	*
	* @return the group ID of this data type
	*/
	@Override
	public long getGroupId() {
		return _dataType.getGroupId();
	}

	/**
	* Sets the group ID of this data type.
	*
	* @param groupId the group ID of this data type
	*/
	@Override
	public void setGroupId(long groupId) {
		_dataType.setGroupId(groupId);
	}

	/**
	* Returns the is favorite of this data type.
	*
	* @return the is favorite of this data type
	*/
	@Override
	public boolean getIsFavorite() {
		return _dataType.getIsFavorite();
	}

	/**
	* Returns <code>true</code> if this data type is is favorite.
	*
	* @return <code>true</code> if this data type is is favorite; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsFavorite() {
		return _dataType.isIsFavorite();
	}

	/**
	* Sets whether this data type is is favorite.
	*
	* @param isFavorite the is favorite of this data type
	*/
	@Override
	public void setIsFavorite(boolean isFavorite) {
		_dataType.setIsFavorite(isFavorite);
	}

	/**
	* Returns the owner ID of this data type.
	*
	* @return the owner ID of this data type
	*/
	@Override
	public long getOwnerId() {
		return _dataType.getOwnerId();
	}

	/**
	* Sets the owner ID of this data type.
	*
	* @param ownerId the owner ID of this data type
	*/
	@Override
	public void setOwnerId(long ownerId) {
		_dataType.setOwnerId(ownerId);
	}

	@Override
	public boolean isNew() {
		return _dataType.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataType.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataType.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataType.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataType.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataType.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataType.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataType.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataType.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _dataType.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _dataType.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_dataType.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_dataType.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new DataTypeWrapper((DataType)_dataType.clone());
	}

	@Override
	public int compareTo(com.kisti.osp.icecap.model.DataType dataType) {
		return _dataType.compareTo(dataType);
	}

	@Override
	public int hashCode() {
		return _dataType.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataType> toCacheModel() {
		return _dataType.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataType toEscapedModel() {
		return new DataTypeWrapper(_dataType.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataType toUnescapedModel() {
		return new DataTypeWrapper(_dataType.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataType.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataType.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataType.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeWrapper)) {
			return false;
		}

		DataTypeWrapper dataTypeWrapper = (DataTypeWrapper)obj;

		if (Validator.equals(_dataType, dataTypeWrapper._dataType)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _dataType.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataType getWrappedDataType() {
		return _dataType;
	}

	@Override
	public DataType getWrappedModel() {
		return _dataType;
	}

	@Override
	public void resetOriginalValues() {
		_dataType.resetOriginalValues();
	}

	private DataType _dataType;
}