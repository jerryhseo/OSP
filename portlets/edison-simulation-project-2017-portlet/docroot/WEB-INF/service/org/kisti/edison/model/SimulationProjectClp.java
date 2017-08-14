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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.service.ClpSerializer;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author edison
 */
public class SimulationProjectClp extends BaseModelImpl<SimulationProject>
	implements SimulationProject {
	public SimulationProjectClp() {
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
	public long getPrimaryKey() {
		return _simulationProjectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSimulationProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _simulationProjectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationProjectId",
						long.class);

				method.invoke(_simulationProjectRemoteModel, simulationProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_simulationProjectRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
					"Title", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getServiceLanguage() {
		return _serviceLanguage;
	}

	@Override
	public void setServiceLanguage(String serviceLanguage) {
		_serviceLanguage = serviceLanguage;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setServiceLanguage",
						String.class);

				method.invoke(_simulationProjectRemoteModel, serviceLanguage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getProjectOpenYn() {
		return _projectOpenYn;
	}

	@Override
	public boolean isProjectOpenYn() {
		return _projectOpenYn;
	}

	@Override
	public void setProjectOpenYn(boolean projectOpenYn) {
		_projectOpenYn = projectOpenYn;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectOpenYn",
						boolean.class);

				method.invoke(_simulationProjectRemoteModel, projectOpenYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExplain() {
		return _explain;
	}

	@Override
	public String getExplain(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getExplain(languageId);
	}

	@Override
	public String getExplain(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getExplain(languageId, useDefault);
	}

	@Override
	public String getExplain(String languageId) {
		return LocalizationUtil.getLocalization(getExplain(), languageId);
	}

	@Override
	public String getExplain(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getExplain(), languageId,
			useDefault);
	}

	@Override
	public String getExplainCurrentLanguageId() {
		return _explainCurrentLanguageId;
	}

	@Override
	public String getExplainCurrentValue() {
		Locale locale = getLocale(_explainCurrentLanguageId);

		return getExplain(locale);
	}

	@Override
	public Map<Locale, String> getExplainMap() {
		return LocalizationUtil.getLocalizationMap(getExplain());
	}

	@Override
	public void setExplain(String explain) {
		_explain = explain;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setExplain", String.class);

				method.invoke(_simulationProjectRemoteModel, explain);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setExplain(String explain, Locale locale) {
		setExplain(explain, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setExplain(String explain, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(explain)) {
			setExplain(LocalizationUtil.updateLocalization(getExplain(),
					"Explain", explain, languageId, defaultLanguageId));
		}
		else {
			setExplain(LocalizationUtil.removeLocalization(getExplain(),
					"Explain", languageId));
		}
	}

	@Override
	public void setExplainCurrentLanguageId(String languageId) {
		_explainCurrentLanguageId = languageId;
	}

	@Override
	public void setExplainMap(Map<Locale, String> explainMap) {
		setExplainMap(explainMap, LocaleUtil.getDefault());
	}

	@Override
	public void setExplainMap(Map<Locale, String> explainMap,
		Locale defaultLocale) {
		if (explainMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setExplain(LocalizationUtil.updateLocalization(explainMap,
					getExplain(), "Explain",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public long getIconId() {
		return _iconId;
	}

	@Override
	public void setIconId(long iconId) {
		_iconId = iconId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setIconId", long.class);

				method.invoke(_simulationProjectRemoteModel, iconId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getImageFolderId() {
		return _imageFolderId;
	}

	@Override
	public void setImageFolderId(long imageFolderId) {
		_imageFolderId = imageFolderId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setImageFolderId", long.class);

				method.invoke(_simulationProjectRemoteModel, imageFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOwnerId() {
		return _ownerId;
	}

	@Override
	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setOwnerId", long.class);

				method.invoke(_simulationProjectRemoteModel, ownerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getInsertId() {
		return _insertId;
	}

	@Override
	public void setInsertId(long insertId) {
		_insertId = insertId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setInsertId", long.class);

				method.invoke(_simulationProjectRemoteModel, insertId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getInsertDate() {
		return _insertDate;
	}

	@Override
	public void setInsertDate(Date insertDate) {
		_insertDate = insertDate;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setInsertDate", Date.class);

				method.invoke(_simulationProjectRemoteModel, insertDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUpdateId() {
		return _updateId;
	}

	@Override
	public void setUpdateId(long updateId) {
		_updateId = updateId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setUpdateId", long.class);

				method.invoke(_simulationProjectRemoteModel, updateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getUpdateDate() {
		return _updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setUpdateDate", Date.class);

				method.invoke(_simulationProjectRemoteModel, updateDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTempletId() {
		return _templetId;
	}

	@Override
	public void setTempletId(long templetId) {
		_templetId = templetId;

		if (_simulationProjectRemoteModel != null) {
			try {
				Class<?> clazz = _simulationProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setTempletId", long.class);

				method.invoke(_simulationProjectRemoteModel, templetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSimulationProjectRemoteModel() {
		return _simulationProjectRemoteModel;
	}

	public void setSimulationProjectRemoteModel(
		BaseModel<?> simulationProjectRemoteModel) {
		_simulationProjectRemoteModel = simulationProjectRemoteModel;
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

		Class<?> remoteModelClass = _simulationProjectRemoteModel.getClass();

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

		Object returnValue = method.invoke(_simulationProjectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimulationProjectLocalServiceUtil.addSimulationProject(this);
		}
		else {
			SimulationProjectLocalServiceUtil.updateSimulationProject(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> explainMap = getExplainMap();

		for (Map.Entry<Locale, String> entry : explainMap.entrySet()) {
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
		String xml = getTitle();

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

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String explain = getExplain(defaultLocale);

		if (Validator.isNull(explain)) {
			setExplain(getExplain(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setExplain(getExplain(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public SimulationProject toEscapedModel() {
		return (SimulationProject)ProxyUtil.newProxyInstance(SimulationProject.class.getClassLoader(),
			new Class[] { SimulationProject.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SimulationProjectClp clone = new SimulationProjectClp();

		clone.setSimulationProjectId(getSimulationProjectId());
		clone.setTitle(getTitle());
		clone.setServiceLanguage(getServiceLanguage());
		clone.setProjectOpenYn(getProjectOpenYn());
		clone.setExplain(getExplain());
		clone.setIconId(getIconId());
		clone.setImageFolderId(getImageFolderId());
		clone.setOwnerId(getOwnerId());
		clone.setInsertId(getInsertId());
		clone.setInsertDate(getInsertDate());
		clone.setUpdateId(getUpdateId());
		clone.setUpdateDate(getUpdateDate());
		clone.setTempletId(getTempletId());

		return clone;
	}

	@Override
	public int compareTo(SimulationProject simulationProject) {
		int value = 0;

		value = DateUtil.compareTo(getInsertDate(),
				simulationProject.getInsertDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationProjectClp)) {
			return false;
		}

		SimulationProjectClp simulationProject = (SimulationProjectClp)obj;

		long primaryKey = simulationProject.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{simulationProjectId=");
		sb.append(getSimulationProjectId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", serviceLanguage=");
		sb.append(getServiceLanguage());
		sb.append(", projectOpenYn=");
		sb.append(getProjectOpenYn());
		sb.append(", explain=");
		sb.append(getExplain());
		sb.append(", iconId=");
		sb.append(getIconId());
		sb.append(", imageFolderId=");
		sb.append(getImageFolderId());
		sb.append(", ownerId=");
		sb.append(getOwnerId());
		sb.append(", insertId=");
		sb.append(getInsertId());
		sb.append(", insertDate=");
		sb.append(getInsertDate());
		sb.append(", updateId=");
		sb.append(getUpdateId());
		sb.append(", updateDate=");
		sb.append(getUpdateDate());
		sb.append(", templetId=");
		sb.append(getTempletId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.SimulationProject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simulationProjectId</column-name><column-value><![CDATA[");
		sb.append(getSimulationProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceLanguage</column-name><column-value><![CDATA[");
		sb.append(getServiceLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectOpenYn</column-name><column-value><![CDATA[");
		sb.append(getProjectOpenYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>explain</column-name><column-value><![CDATA[");
		sb.append(getExplain());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>iconId</column-name><column-value><![CDATA[");
		sb.append(getIconId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imageFolderId</column-name><column-value><![CDATA[");
		sb.append(getImageFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ownerId</column-name><column-value><![CDATA[");
		sb.append(getOwnerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertId</column-name><column-value><![CDATA[");
		sb.append(getInsertId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertDate</column-name><column-value><![CDATA[");
		sb.append(getInsertDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>updateId</column-name><column-value><![CDATA[");
		sb.append(getUpdateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>updateDate</column-name><column-value><![CDATA[");
		sb.append(getUpdateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>templetId</column-name><column-value><![CDATA[");
		sb.append(getTempletId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _simulationProjectId;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _serviceLanguage;
	private boolean _projectOpenYn;
	private String _explain;
	private String _explainCurrentLanguageId;
	private long _iconId;
	private long _imageFolderId;
	private long _ownerId;
	private long _insertId;
	private Date _insertDate;
	private long _updateId;
	private Date _updateDate;
	private long _templetId;
	private BaseModel<?> _simulationProjectRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.service.ClpSerializer.class;
}