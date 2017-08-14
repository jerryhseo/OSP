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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DataEntry}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntry
 * @generated
 */
public class DataEntryWrapper implements DataEntry, ModelWrapper<DataEntry> {
	public DataEntryWrapper(DataEntry dataEntry) {
		_dataEntry = dataEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return DataEntry.class;
	}

	@Override
	public String getModelClassName() {
		return DataEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("collectionId", getCollectionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("comments", getComments());
		attributes.put("path", getPath());
		attributes.put("simulationSubjectId", getSimulationSubjectId());
		attributes.put("productionTime", getProductionTime());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("lastAccessedDate", getLastAccessedDate());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long collectionId = (Long)attributes.get("collectionId");

		if (collectionId != null) {
			setCollectionId(collectionId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String path = (String)attributes.get("path");

		if (path != null) {
			setPath(path);
		}

		Long simulationSubjectId = (Long)attributes.get("simulationSubjectId");

		if (simulationSubjectId != null) {
			setSimulationSubjectId(simulationSubjectId);
		}

		Long productionTime = (Long)attributes.get("productionTime");

		if (productionTime != null) {
			setProductionTime(productionTime);
		}

		Long viewCount = (Long)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}

		Long downloadCount = (Long)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Date lastAccessedDate = (Date)attributes.get("lastAccessedDate");

		if (lastAccessedDate != null) {
			setLastAccessedDate(lastAccessedDate);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	/**
	* Returns the primary key of this data entry.
	*
	* @return the primary key of this data entry
	*/
	@Override
	public long getPrimaryKey() {
		return _dataEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data entry.
	*
	* @param primaryKey the primary key of this data entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this data entry.
	*
	* @return the entry ID of this data entry
	*/
	@Override
	public long getEntryId() {
		return _dataEntry.getEntryId();
	}

	/**
	* Sets the entry ID of this data entry.
	*
	* @param entryId the entry ID of this data entry
	*/
	@Override
	public void setEntryId(long entryId) {
		_dataEntry.setEntryId(entryId);
	}

	/**
	* Returns the collection ID of this data entry.
	*
	* @return the collection ID of this data entry
	*/
	@Override
	public long getCollectionId() {
		return _dataEntry.getCollectionId();
	}

	/**
	* Sets the collection ID of this data entry.
	*
	* @param collectionId the collection ID of this data entry
	*/
	@Override
	public void setCollectionId(long collectionId) {
		_dataEntry.setCollectionId(collectionId);
	}

	/**
	* Returns the group ID of this data entry.
	*
	* @return the group ID of this data entry
	*/
	@Override
	public long getGroupId() {
		return _dataEntry.getGroupId();
	}

	/**
	* Sets the group ID of this data entry.
	*
	* @param groupId the group ID of this data entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_dataEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this data entry.
	*
	* @return the company ID of this data entry
	*/
	@Override
	public long getCompanyId() {
		return _dataEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this data entry.
	*
	* @param companyId the company ID of this data entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_dataEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this data entry.
	*
	* @return the user ID of this data entry
	*/
	@Override
	public long getUserId() {
		return _dataEntry.getUserId();
	}

	/**
	* Sets the user ID of this data entry.
	*
	* @param userId the user ID of this data entry
	*/
	@Override
	public void setUserId(long userId) {
		_dataEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this data entry.
	*
	* @return the user uuid of this data entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this data entry.
	*
	* @param userUuid the user uuid of this data entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_dataEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this data entry.
	*
	* @return the create date of this data entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _dataEntry.getCreateDate();
	}

	/**
	* Sets the create date of this data entry.
	*
	* @param createDate the create date of this data entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_dataEntry.setCreateDate(createDate);
	}

	/**
	* Returns the comments of this data entry.
	*
	* @return the comments of this data entry
	*/
	@Override
	public java.lang.String getComments() {
		return _dataEntry.getComments();
	}

	/**
	* Returns the localized comments of this data entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized comments of this data entry
	*/
	@Override
	public java.lang.String getComments(java.util.Locale locale) {
		return _dataEntry.getComments(locale);
	}

	/**
	* Returns the localized comments of this data entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comments of this data entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getComments(java.util.Locale locale,
		boolean useDefault) {
		return _dataEntry.getComments(locale, useDefault);
	}

	/**
	* Returns the localized comments of this data entry in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized comments of this data entry
	*/
	@Override
	public java.lang.String getComments(java.lang.String languageId) {
		return _dataEntry.getComments(languageId);
	}

	/**
	* Returns the localized comments of this data entry in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comments of this data entry
	*/
	@Override
	public java.lang.String getComments(java.lang.String languageId,
		boolean useDefault) {
		return _dataEntry.getComments(languageId, useDefault);
	}

	@Override
	public java.lang.String getCommentsCurrentLanguageId() {
		return _dataEntry.getCommentsCurrentLanguageId();
	}

	@Override
	public java.lang.String getCommentsCurrentValue() {
		return _dataEntry.getCommentsCurrentValue();
	}

	/**
	* Returns a map of the locales and localized commentses of this data entry.
	*
	* @return the locales and localized commentses of this data entry
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getCommentsMap() {
		return _dataEntry.getCommentsMap();
	}

	/**
	* Sets the comments of this data entry.
	*
	* @param comments the comments of this data entry
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_dataEntry.setComments(comments);
	}

	/**
	* Sets the localized comments of this data entry in the language.
	*
	* @param comments the localized comments of this data entry
	* @param locale the locale of the language
	*/
	@Override
	public void setComments(java.lang.String comments, java.util.Locale locale) {
		_dataEntry.setComments(comments, locale);
	}

	/**
	* Sets the localized comments of this data entry in the language, and sets the default locale.
	*
	* @param comments the localized comments of this data entry
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setComments(java.lang.String comments, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_dataEntry.setComments(comments, locale, defaultLocale);
	}

	@Override
	public void setCommentsCurrentLanguageId(java.lang.String languageId) {
		_dataEntry.setCommentsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized commentses of this data entry from the map of locales and localized commentses.
	*
	* @param commentsMap the locales and localized commentses of this data entry
	*/
	@Override
	public void setCommentsMap(
		java.util.Map<java.util.Locale, java.lang.String> commentsMap) {
		_dataEntry.setCommentsMap(commentsMap);
	}

	/**
	* Sets the localized commentses of this data entry from the map of locales and localized commentses, and sets the default locale.
	*
	* @param commentsMap the locales and localized commentses of this data entry
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCommentsMap(
		java.util.Map<java.util.Locale, java.lang.String> commentsMap,
		java.util.Locale defaultLocale) {
		_dataEntry.setCommentsMap(commentsMap, defaultLocale);
	}

	/**
	* Returns the path of this data entry.
	*
	* @return the path of this data entry
	*/
	@Override
	public java.lang.String getPath() {
		return _dataEntry.getPath();
	}

	/**
	* Sets the path of this data entry.
	*
	* @param path the path of this data entry
	*/
	@Override
	public void setPath(java.lang.String path) {
		_dataEntry.setPath(path);
	}

	/**
	* Returns the simulation subject ID of this data entry.
	*
	* @return the simulation subject ID of this data entry
	*/
	@Override
	public long getSimulationSubjectId() {
		return _dataEntry.getSimulationSubjectId();
	}

	/**
	* Sets the simulation subject ID of this data entry.
	*
	* @param simulationSubjectId the simulation subject ID of this data entry
	*/
	@Override
	public void setSimulationSubjectId(long simulationSubjectId) {
		_dataEntry.setSimulationSubjectId(simulationSubjectId);
	}

	/**
	* Returns the production time of this data entry.
	*
	* @return the production time of this data entry
	*/
	@Override
	public long getProductionTime() {
		return _dataEntry.getProductionTime();
	}

	/**
	* Sets the production time of this data entry.
	*
	* @param productionTime the production time of this data entry
	*/
	@Override
	public void setProductionTime(long productionTime) {
		_dataEntry.setProductionTime(productionTime);
	}

	/**
	* Returns the view count of this data entry.
	*
	* @return the view count of this data entry
	*/
	@Override
	public long getViewCount() {
		return _dataEntry.getViewCount();
	}

	/**
	* Sets the view count of this data entry.
	*
	* @param viewCount the view count of this data entry
	*/
	@Override
	public void setViewCount(long viewCount) {
		_dataEntry.setViewCount(viewCount);
	}

	/**
	* Returns the download count of this data entry.
	*
	* @return the download count of this data entry
	*/
	@Override
	public long getDownloadCount() {
		return _dataEntry.getDownloadCount();
	}

	/**
	* Sets the download count of this data entry.
	*
	* @param downloadCount the download count of this data entry
	*/
	@Override
	public void setDownloadCount(long downloadCount) {
		_dataEntry.setDownloadCount(downloadCount);
	}

	/**
	* Returns the last accessed date of this data entry.
	*
	* @return the last accessed date of this data entry
	*/
	@Override
	public java.util.Date getLastAccessedDate() {
		return _dataEntry.getLastAccessedDate();
	}

	/**
	* Sets the last accessed date of this data entry.
	*
	* @param lastAccessedDate the last accessed date of this data entry
	*/
	@Override
	public void setLastAccessedDate(java.util.Date lastAccessedDate) {
		_dataEntry.setLastAccessedDate(lastAccessedDate);
	}

	/**
	* Returns the file entry ID of this data entry.
	*
	* @return the file entry ID of this data entry
	*/
	@Override
	public long getFileEntryId() {
		return _dataEntry.getFileEntryId();
	}

	/**
	* Sets the file entry ID of this data entry.
	*
	* @param fileEntryId the file entry ID of this data entry
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_dataEntry.setFileEntryId(fileEntryId);
	}

	@Override
	public boolean isNew() {
		return _dataEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _dataEntry.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _dataEntry.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_dataEntry.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_dataEntry.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new DataEntryWrapper((DataEntry)_dataEntry.clone());
	}

	@Override
	public int compareTo(com.kisti.osp.icecap.model.DataEntry dataEntry) {
		return _dataEntry.compareTo(dataEntry);
	}

	@Override
	public int hashCode() {
		return _dataEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.icecap.model.DataEntry> toCacheModel() {
		return _dataEntry.toCacheModel();
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntry toEscapedModel() {
		return new DataEntryWrapper(_dataEntry.toEscapedModel());
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntry toUnescapedModel() {
		return new DataEntryWrapper(_dataEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataEntryWrapper)) {
			return false;
		}

		DataEntryWrapper dataEntryWrapper = (DataEntryWrapper)obj;

		if (Validator.equals(_dataEntry, dataEntryWrapper._dataEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataEntry getWrappedDataEntry() {
		return _dataEntry;
	}

	@Override
	public DataEntry getWrappedModel() {
		return _dataEntry;
	}

	@Override
	public void resetOriginalValues() {
		_dataEntry.resetOriginalValues();
	}

	private DataEntry _dataEntry;
}