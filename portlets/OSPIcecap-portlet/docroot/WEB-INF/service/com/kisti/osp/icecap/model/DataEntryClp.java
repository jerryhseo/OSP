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

import com.kisti.osp.icecap.service.ClpSerializer;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Young-K. Suh and Jerry H. Seo
 */
public class DataEntryClp extends BaseModelImpl<DataEntry> implements DataEntry {
	public DataEntryClp() {
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

	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setEntryId", long.class);

				method.invoke(_dataEntryRemoteModel, entryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCollectionId() {
		return _collectionId;
	}

	@Override
	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCollectionId", long.class);

				method.invoke(_dataEntryRemoteModel, collectionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_dataEntryRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_dataEntryRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_dataEntryRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_dataEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComments() {
		return _comments;
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

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_dataEntryRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
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

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setComments(LocalizationUtil.updateLocalization(commentsMap,
					getComments(), "Comments",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPath() {
		return _path;
	}

	@Override
	public void setPath(String path) {
		_path = path;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPath", String.class);

				method.invoke(_dataEntryRemoteModel, path);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSimulationSubjectId() {
		return _simulationSubjectId;
	}

	@Override
	public void setSimulationSubjectId(long simulationSubjectId) {
		_simulationSubjectId = simulationSubjectId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationSubjectId",
						long.class);

				method.invoke(_dataEntryRemoteModel, simulationSubjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProductionTime() {
		return _productionTime;
	}

	@Override
	public void setProductionTime(long productionTime) {
		_productionTime = productionTime;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setProductionTime", long.class);

				method.invoke(_dataEntryRemoteModel, productionTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getViewCount() {
		return _viewCount;
	}

	@Override
	public void setViewCount(long viewCount) {
		_viewCount = viewCount;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setViewCount", long.class);

				method.invoke(_dataEntryRemoteModel, viewCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDownloadCount() {
		return _downloadCount;
	}

	@Override
	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setDownloadCount", long.class);

				method.invoke(_dataEntryRemoteModel, downloadCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastAccessedDate() {
		return _lastAccessedDate;
	}

	@Override
	public void setLastAccessedDate(Date lastAccessedDate) {
		_lastAccessedDate = lastAccessedDate;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setLastAccessedDate",
						Date.class);

				method.invoke(_dataEntryRemoteModel, lastAccessedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;

		if (_dataEntryRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFileEntryId", long.class);

				method.invoke(_dataEntryRemoteModel, fileEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDataEntryRemoteModel() {
		return _dataEntryRemoteModel;
	}

	public void setDataEntryRemoteModel(BaseModel<?> dataEntryRemoteModel) {
		_dataEntryRemoteModel = dataEntryRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _dataEntryRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_dataEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataEntryLocalServiceUtil.addDataEntry(this);
		}
		else {
			DataEntryLocalServiceUtil.updateDataEntry(this);
		}
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
		return (DataEntry)ProxyUtil.newProxyInstance(DataEntry.class.getClassLoader(),
			new Class[] { DataEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DataEntryClp clone = new DataEntryClp();

		clone.setEntryId(getEntryId());
		clone.setCollectionId(getCollectionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setComments(getComments());
		clone.setPath(getPath());
		clone.setSimulationSubjectId(getSimulationSubjectId());
		clone.setProductionTime(getProductionTime());
		clone.setViewCount(getViewCount());
		clone.setDownloadCount(getDownloadCount());
		clone.setLastAccessedDate(getLastAccessedDate());
		clone.setFileEntryId(getFileEntryId());

		return clone;
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

		if (!(obj instanceof DataEntryClp)) {
			return false;
		}

		DataEntryClp dataEntry = (DataEntryClp)obj;

		long primaryKey = dataEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
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

	private long _entryId;
	private long _collectionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private String _comments;
	private String _commentsCurrentLanguageId;
	private String _path;
	private long _simulationSubjectId;
	private long _productionTime;
	private long _viewCount;
	private long _downloadCount;
	private Date _lastAccessedDate;
	private long _fileEntryId;
	private BaseModel<?> _dataEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.icecap.service.ClpSerializer.class;
}