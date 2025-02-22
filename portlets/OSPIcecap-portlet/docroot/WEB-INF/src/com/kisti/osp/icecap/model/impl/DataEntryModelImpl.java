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

package com.kisti.osp.icecap.model.impl;

import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.DataEntryModel;
import com.kisti.osp.icecap.model.DataEntrySoap;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the DataEntry service. Represents a row in the &quot;icecap_DataEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.kisti.osp.icecap.model.DataEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DataEntryImpl}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryImpl
 * @see com.kisti.osp.icecap.model.DataEntry
 * @see com.kisti.osp.icecap.model.DataEntryModel
 * @generated
 */
@JSON(strict = true)
public class DataEntryModelImpl extends BaseModelImpl<DataEntry>
	implements DataEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a data entry model instance should use the {@link com.kisti.osp.icecap.model.DataEntry} interface instead.
	 */
	public static final String TABLE_NAME = "icecap_DataEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "entryId", Types.BIGINT },
			{ "collectionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "comments", Types.VARCHAR },
			{ "path_", Types.VARCHAR },
			{ "simulationSubjectId", Types.BIGINT },
			{ "productionTime", Types.BIGINT },
			{ "viewCount", Types.BIGINT },
			{ "downloadCount", Types.BIGINT },
			{ "lastAccessedDate", Types.TIMESTAMP },
			{ "fileEntryId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table icecap_DataEntry (entryId LONG not null primary key,collectionId LONG,groupId LONG,companyId LONG,userId LONG,createDate DATE null,comments STRING null,path_ VARCHAR(75) null,simulationSubjectId LONG,productionTime LONG,viewCount LONG,downloadCount LONG,lastAccessedDate DATE null,fileEntryId LONG)";
	public static final String TABLE_SQL_DROP = "drop table icecap_DataEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY dataEntry.entryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY icecap_DataEntry.entryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.kisti.osp.icecap.model.DataEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.kisti.osp.icecap.model.DataEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.kisti.osp.icecap.model.DataEntry"),
			true);
	public static long COLLECTIONID_COLUMN_BITMASK = 1L;
	public static long SIMULATIONSUBJECTID_COLUMN_BITMASK = 2L;
	public static long ENTRYID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static DataEntry toModel(DataEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		DataEntry model = new DataEntryImpl();

		model.setEntryId(soapModel.getEntryId());
		model.setCollectionId(soapModel.getCollectionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setComments(soapModel.getComments());
		model.setPath(soapModel.getPath());
		model.setSimulationSubjectId(soapModel.getSimulationSubjectId());
		model.setProductionTime(soapModel.getProductionTime());
		model.setViewCount(soapModel.getViewCount());
		model.setDownloadCount(soapModel.getDownloadCount());
		model.setLastAccessedDate(soapModel.getLastAccessedDate());
		model.setFileEntryId(soapModel.getFileEntryId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DataEntry> toModels(DataEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<DataEntry> models = new ArrayList<DataEntry>(soapModels.length);

		for (DataEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.kisti.osp.icecap.model.DataEntry"));

	public DataEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@JSON
	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	@JSON
	@Override
	public long getCollectionId() {
		return _collectionId;
	}

	@Override
	public void setCollectionId(long collectionId) {
		_columnBitmask |= COLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalCollectionId) {
			_setOriginalCollectionId = true;

			_originalCollectionId = _collectionId;
		}

		_collectionId = collectionId;
	}

	public long getOriginalCollectionId() {
		return _originalCollectionId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public String getComments() {
		if (_comments == null) {
			return StringPool.BLANK;
		}
		else {
			return _comments;
		}
	}

	@Override
	public String getComments(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getComments(languageId);
	}

	@Override
	public String getComments(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getComments(languageId, useDefault);
	}

	@Override
	public String getComments(String languageId) {
		return LocalizationUtil.getLocalization(getComments(), languageId);
	}

	@Override
	public String getComments(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getComments(), languageId,
			useDefault);
	}

	@Override
	public String getCommentsCurrentLanguageId() {
		return _commentsCurrentLanguageId;
	}

	@JSON
	@Override
	public String getCommentsCurrentValue() {
		Locale locale = getLocale(_commentsCurrentLanguageId);

		return getComments(locale);
	}

	@Override
	public Map<Locale, String> getCommentsMap() {
		return LocalizationUtil.getLocalizationMap(getComments());
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;
	}

	@Override
	public void setComments(String comments, Locale locale) {
		setComments(comments, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setComments(String comments, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(comments)) {
			setComments(LocalizationUtil.updateLocalization(getComments(),
					"Comments", comments, languageId, defaultLanguageId));
		}
		else {
			setComments(LocalizationUtil.removeLocalization(getComments(),
					"Comments", languageId));
		}
	}

	@Override
	public void setCommentsCurrentLanguageId(String languageId) {
		_commentsCurrentLanguageId = languageId;
	}

	@Override
	public void setCommentsMap(Map<Locale, String> commentsMap) {
		setCommentsMap(commentsMap, LocaleUtil.getDefault());
	}

	@Override
	public void setCommentsMap(Map<Locale, String> commentsMap,
		Locale defaultLocale) {
		if (commentsMap == null) {
			return;
		}

		setComments(LocalizationUtil.updateLocalization(commentsMap,
				getComments(), "Comments",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getPath() {
		if (_path == null) {
			return StringPool.BLANK;
		}
		else {
			return _path;
		}
	}

	@Override
	public void setPath(String path) {
		_path = path;
	}

	@JSON
	@Override
	public long getSimulationSubjectId() {
		return _simulationSubjectId;
	}

	@Override
	public void setSimulationSubjectId(long simulationSubjectId) {
		_columnBitmask |= SIMULATIONSUBJECTID_COLUMN_BITMASK;

		if (!_setOriginalSimulationSubjectId) {
			_setOriginalSimulationSubjectId = true;

			_originalSimulationSubjectId = _simulationSubjectId;
		}

		_simulationSubjectId = simulationSubjectId;
	}

	public long getOriginalSimulationSubjectId() {
		return _originalSimulationSubjectId;
	}

	@JSON
	@Override
	public long getProductionTime() {
		return _productionTime;
	}

	@Override
	public void setProductionTime(long productionTime) {
		_productionTime = productionTime;
	}

	@JSON
	@Override
	public long getViewCount() {
		return _viewCount;
	}

	@Override
	public void setViewCount(long viewCount) {
		_viewCount = viewCount;
	}

	@JSON
	@Override
	public long getDownloadCount() {
		return _downloadCount;
	}

	@Override
	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;
	}

	@JSON
	@Override
	public Date getLastAccessedDate() {
		return _lastAccessedDate;
	}

	@Override
	public void setLastAccessedDate(Date lastAccessedDate) {
		_lastAccessedDate = lastAccessedDate;
	}

	@JSON
	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DataEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> commentsMap = getCommentsMap();

		for (Map.Entry<Locale, String> entry : commentsMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getComments();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String comments = getComments(defaultLocale);

		if (Validator.isNull(comments)) {
			setComments(getComments(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setComments(getComments(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public DataEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DataEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DataEntryImpl dataEntryImpl = new DataEntryImpl();

		dataEntryImpl.setEntryId(getEntryId());
		dataEntryImpl.setCollectionId(getCollectionId());
		dataEntryImpl.setGroupId(getGroupId());
		dataEntryImpl.setCompanyId(getCompanyId());
		dataEntryImpl.setUserId(getUserId());
		dataEntryImpl.setCreateDate(getCreateDate());
		dataEntryImpl.setComments(getComments());
		dataEntryImpl.setPath(getPath());
		dataEntryImpl.setSimulationSubjectId(getSimulationSubjectId());
		dataEntryImpl.setProductionTime(getProductionTime());
		dataEntryImpl.setViewCount(getViewCount());
		dataEntryImpl.setDownloadCount(getDownloadCount());
		dataEntryImpl.setLastAccessedDate(getLastAccessedDate());
		dataEntryImpl.setFileEntryId(getFileEntryId());

		dataEntryImpl.resetOriginalValues();

		return dataEntryImpl;
	}

	@Override
	public int compareTo(DataEntry dataEntry) {
		long primaryKey = dataEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataEntry)) {
			return false;
		}

		DataEntry dataEntry = (DataEntry)obj;

		long primaryKey = dataEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		DataEntryModelImpl dataEntryModelImpl = this;

		dataEntryModelImpl._originalCollectionId = dataEntryModelImpl._collectionId;

		dataEntryModelImpl._setOriginalCollectionId = false;

		dataEntryModelImpl._originalSimulationSubjectId = dataEntryModelImpl._simulationSubjectId;

		dataEntryModelImpl._setOriginalSimulationSubjectId = false;

		dataEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DataEntry> toCacheModel() {
		DataEntryCacheModel dataEntryCacheModel = new DataEntryCacheModel();

		dataEntryCacheModel.entryId = getEntryId();

		dataEntryCacheModel.collectionId = getCollectionId();

		dataEntryCacheModel.groupId = getGroupId();

		dataEntryCacheModel.companyId = getCompanyId();

		dataEntryCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			dataEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			dataEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		dataEntryCacheModel.comments = getComments();

		String comments = dataEntryCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			dataEntryCacheModel.comments = null;
		}

		dataEntryCacheModel.path = getPath();

		String path = dataEntryCacheModel.path;

		if ((path != null) && (path.length() == 0)) {
			dataEntryCacheModel.path = null;
		}

		dataEntryCacheModel.simulationSubjectId = getSimulationSubjectId();

		dataEntryCacheModel.productionTime = getProductionTime();

		dataEntryCacheModel.viewCount = getViewCount();

		dataEntryCacheModel.downloadCount = getDownloadCount();

		Date lastAccessedDate = getLastAccessedDate();

		if (lastAccessedDate != null) {
			dataEntryCacheModel.lastAccessedDate = lastAccessedDate.getTime();
		}
		else {
			dataEntryCacheModel.lastAccessedDate = Long.MIN_VALUE;
		}

		dataEntryCacheModel.fileEntryId = getFileEntryId();

		return dataEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", collectionId=");
		sb.append(getCollectionId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", path=");
		sb.append(getPath());
		sb.append(", simulationSubjectId=");
		sb.append(getSimulationSubjectId());
		sb.append(", productionTime=");
		sb.append(getProductionTime());
		sb.append(", viewCount=");
		sb.append(getViewCount());
		sb.append(", downloadCount=");
		sb.append(getDownloadCount());
		sb.append(", lastAccessedDate=");
		sb.append(getLastAccessedDate());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.icecap.model.DataEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>collectionId</column-name><column-value><![CDATA[");
		sb.append(getCollectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>path</column-name><column-value><![CDATA[");
		sb.append(getPath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationSubjectId</column-name><column-value><![CDATA[");
		sb.append(getSimulationSubjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>productionTime</column-name><column-value><![CDATA[");
		sb.append(getProductionTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>viewCount</column-name><column-value><![CDATA[");
		sb.append(getViewCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>downloadCount</column-name><column-value><![CDATA[");
		sb.append(getDownloadCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastAccessedDate</column-name><column-value><![CDATA[");
		sb.append(getLastAccessedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = DataEntry.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			DataEntry.class
		};
	private long _entryId;
	private long _collectionId;
	private long _originalCollectionId;
	private boolean _setOriginalCollectionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private String _comments;
	private String _commentsCurrentLanguageId;
	private String _path;
	private long _simulationSubjectId;
	private long _originalSimulationSubjectId;
	private boolean _setOriginalSimulationSubjectId;
	private long _productionTime;
	private long _viewCount;
	private long _downloadCount;
	private Date _lastAccessedDate;
	private long _fileEntryId;
	private long _columnBitmask;
	private DataEntry _escapedModel;
}