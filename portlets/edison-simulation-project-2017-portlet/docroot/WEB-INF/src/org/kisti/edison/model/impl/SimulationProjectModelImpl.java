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

package org.kisti.edison.model.impl;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
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

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.model.SimulationProjectModel;
import org.kisti.edison.model.SimulationProjectSoap;

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
 * The base model implementation for the SimulationProject service. Represents a row in the &quot;EDSIMPRO_SimulationProject&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.kisti.edison.model.SimulationProjectModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SimulationProjectImpl}.
 * </p>
 *
 * @author edison
 * @see SimulationProjectImpl
 * @see org.kisti.edison.model.SimulationProject
 * @see org.kisti.edison.model.SimulationProjectModel
 * @generated
 */
@JSON(strict = true)
public class SimulationProjectModelImpl extends BaseModelImpl<SimulationProject>
	implements SimulationProjectModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a simulation project model instance should use the {@link org.kisti.edison.model.SimulationProject} interface instead.
	 */
	public static final String TABLE_NAME = "EDSIMPRO_SimulationProject";
	public static final Object[][] TABLE_COLUMNS = {
			{ "simulationProjectId", Types.BIGINT },
			{ "title", Types.VARCHAR },
			{ "serviceLanguage", Types.VARCHAR },
			{ "projectOpenYn", Types.BOOLEAN },
			{ "explain_", Types.VARCHAR },
			{ "iconId", Types.BIGINT },
			{ "imageFolderId", Types.BIGINT },
			{ "ownerId", Types.BIGINT },
			{ "insertId", Types.BIGINT },
			{ "insertDate", Types.TIMESTAMP },
			{ "updateId", Types.BIGINT },
			{ "updateDate", Types.TIMESTAMP },
			{ "templetId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table EDSIMPRO_SimulationProject (simulationProjectId LONG not null primary key,title STRING null,serviceLanguage VARCHAR(75) null,projectOpenYn BOOLEAN,explain_ STRING null,iconId LONG,imageFolderId LONG,ownerId LONG,insertId LONG,insertDate DATE null,updateId LONG,updateDate DATE null,templetId LONG)";
	public static final String TABLE_SQL_DROP = "drop table EDSIMPRO_SimulationProject";
	public static final String ORDER_BY_JPQL = " ORDER BY simulationProject.insertDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY EDSIMPRO_SimulationProject.insertDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.kisti.edison.model.SimulationProject"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.kisti.edison.model.SimulationProject"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.kisti.edison.model.SimulationProject"),
			true);
	public static long OWNERID_COLUMN_BITMASK = 1L;
	public static long INSERTDATE_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SimulationProject toModel(SimulationProjectSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SimulationProject model = new SimulationProjectImpl();

		model.setSimulationProjectId(soapModel.getSimulationProjectId());
		model.setTitle(soapModel.getTitle());
		model.setServiceLanguage(soapModel.getServiceLanguage());
		model.setProjectOpenYn(soapModel.getProjectOpenYn());
		model.setExplain(soapModel.getExplain());
		model.setIconId(soapModel.getIconId());
		model.setImageFolderId(soapModel.getImageFolderId());
		model.setOwnerId(soapModel.getOwnerId());
		model.setInsertId(soapModel.getInsertId());
		model.setInsertDate(soapModel.getInsertDate());
		model.setUpdateId(soapModel.getUpdateId());
		model.setUpdateDate(soapModel.getUpdateDate());
		model.setTempletId(soapModel.getTempletId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SimulationProject> toModels(
		SimulationProjectSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SimulationProject> models = new ArrayList<SimulationProject>(soapModels.length);

		for (SimulationProjectSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.kisti.edison.model.SimulationProject"));

	public SimulationProjectModelImpl() {
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

	@JSON
	@Override
	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
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

	@JSON
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

		setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
				"Title", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getServiceLanguage() {
		if (_serviceLanguage == null) {
			return StringPool.BLANK;
		}
		else {
			return _serviceLanguage;
		}
	}

	@Override
	public void setServiceLanguage(String serviceLanguage) {
		_serviceLanguage = serviceLanguage;
	}

	@JSON
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
	}

	@JSON
	@Override
	public String getExplain() {
		if (_explain == null) {
			return StringPool.BLANK;
		}
		else {
			return _explain;
		}
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

	@JSON
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

		setExplain(LocalizationUtil.updateLocalization(explainMap,
				getExplain(), "Explain", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public long getIconId() {
		return _iconId;
	}

	@Override
	public void setIconId(long iconId) {
		_iconId = iconId;
	}

	@JSON
	@Override
	public long getImageFolderId() {
		return _imageFolderId;
	}

	@Override
	public void setImageFolderId(long imageFolderId) {
		_imageFolderId = imageFolderId;
	}

	@JSON
	@Override
	public long getOwnerId() {
		return _ownerId;
	}

	@Override
	public void setOwnerId(long ownerId) {
		_columnBitmask |= OWNERID_COLUMN_BITMASK;

		if (!_setOriginalOwnerId) {
			_setOriginalOwnerId = true;

			_originalOwnerId = _ownerId;
		}

		_ownerId = ownerId;
	}

	public long getOriginalOwnerId() {
		return _originalOwnerId;
	}

	@JSON
	@Override
	public long getInsertId() {
		return _insertId;
	}

	@Override
	public void setInsertId(long insertId) {
		_insertId = insertId;
	}

	@JSON
	@Override
	public Date getInsertDate() {
		return _insertDate;
	}

	@Override
	public void setInsertDate(Date insertDate) {
		_columnBitmask = -1L;

		_insertDate = insertDate;
	}

	@JSON
	@Override
	public long getUpdateId() {
		return _updateId;
	}

	@Override
	public void setUpdateId(long updateId) {
		_updateId = updateId;
	}

	@JSON
	@Override
	public Date getUpdateDate() {
		return _updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;
	}

	@JSON
	@Override
	public long getTempletId() {
		return _templetId;
	}

	@Override
	public void setTempletId(long templetId) {
		_templetId = templetId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			SimulationProject.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
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
		if (_escapedModel == null) {
			_escapedModel = (SimulationProject)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SimulationProjectImpl simulationProjectImpl = new SimulationProjectImpl();

		simulationProjectImpl.setSimulationProjectId(getSimulationProjectId());
		simulationProjectImpl.setTitle(getTitle());
		simulationProjectImpl.setServiceLanguage(getServiceLanguage());
		simulationProjectImpl.setProjectOpenYn(getProjectOpenYn());
		simulationProjectImpl.setExplain(getExplain());
		simulationProjectImpl.setIconId(getIconId());
		simulationProjectImpl.setImageFolderId(getImageFolderId());
		simulationProjectImpl.setOwnerId(getOwnerId());
		simulationProjectImpl.setInsertId(getInsertId());
		simulationProjectImpl.setInsertDate(getInsertDate());
		simulationProjectImpl.setUpdateId(getUpdateId());
		simulationProjectImpl.setUpdateDate(getUpdateDate());
		simulationProjectImpl.setTempletId(getTempletId());

		simulationProjectImpl.resetOriginalValues();

		return simulationProjectImpl;
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

		if (!(obj instanceof SimulationProject)) {
			return false;
		}

		SimulationProject simulationProject = (SimulationProject)obj;

		long primaryKey = simulationProject.getPrimaryKey();

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
		SimulationProjectModelImpl simulationProjectModelImpl = this;

		simulationProjectModelImpl._originalOwnerId = simulationProjectModelImpl._ownerId;

		simulationProjectModelImpl._setOriginalOwnerId = false;

		simulationProjectModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SimulationProject> toCacheModel() {
		SimulationProjectCacheModel simulationProjectCacheModel = new SimulationProjectCacheModel();

		simulationProjectCacheModel.simulationProjectId = getSimulationProjectId();

		simulationProjectCacheModel.title = getTitle();

		String title = simulationProjectCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			simulationProjectCacheModel.title = null;
		}

		simulationProjectCacheModel.serviceLanguage = getServiceLanguage();

		String serviceLanguage = simulationProjectCacheModel.serviceLanguage;

		if ((serviceLanguage != null) && (serviceLanguage.length() == 0)) {
			simulationProjectCacheModel.serviceLanguage = null;
		}

		simulationProjectCacheModel.projectOpenYn = getProjectOpenYn();

		simulationProjectCacheModel.explain = getExplain();

		String explain = simulationProjectCacheModel.explain;

		if ((explain != null) && (explain.length() == 0)) {
			simulationProjectCacheModel.explain = null;
		}

		simulationProjectCacheModel.iconId = getIconId();

		simulationProjectCacheModel.imageFolderId = getImageFolderId();

		simulationProjectCacheModel.ownerId = getOwnerId();

		simulationProjectCacheModel.insertId = getInsertId();

		Date insertDate = getInsertDate();

		if (insertDate != null) {
			simulationProjectCacheModel.insertDate = insertDate.getTime();
		}
		else {
			simulationProjectCacheModel.insertDate = Long.MIN_VALUE;
		}

		simulationProjectCacheModel.updateId = getUpdateId();

		Date updateDate = getUpdateDate();

		if (updateDate != null) {
			simulationProjectCacheModel.updateDate = updateDate.getTime();
		}
		else {
			simulationProjectCacheModel.updateDate = Long.MIN_VALUE;
		}

		simulationProjectCacheModel.templetId = getTempletId();

		return simulationProjectCacheModel;
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

	private static ClassLoader _classLoader = SimulationProject.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			SimulationProject.class
		};
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
	private long _originalOwnerId;
	private boolean _setOriginalOwnerId;
	private long _insertId;
	private Date _insertDate;
	private long _updateId;
	private Date _updateDate;
	private long _templetId;
	private long _columnBitmask;
	private SimulationProject _escapedModel;
}