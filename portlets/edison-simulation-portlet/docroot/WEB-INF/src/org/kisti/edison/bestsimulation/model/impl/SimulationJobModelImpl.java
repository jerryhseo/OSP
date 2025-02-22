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

package org.kisti.edison.bestsimulation.model.impl;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
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

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobModel;
import org.kisti.edison.bestsimulation.model.SimulationJobSoap;
import org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK;

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
 * The base model implementation for the SimulationJob service. Represents a row in the &quot;EDSIM_SimulationJob&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.kisti.edison.bestsimulation.model.SimulationJobModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SimulationJobImpl}.
 * </p>
 *
 * @author EDISON
 * @see SimulationJobImpl
 * @see org.kisti.edison.bestsimulation.model.SimulationJob
 * @see org.kisti.edison.bestsimulation.model.SimulationJobModel
 * @generated
 */
@JSON(strict = true)
public class SimulationJobModelImpl extends BaseModelImpl<SimulationJob>
	implements SimulationJobModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a simulation job model instance should use the {@link org.kisti.edison.bestsimulation.model.SimulationJob} interface instead.
	 */
	public static final String TABLE_NAME = "EDSIM_SimulationJob";
	public static final Object[][] TABLE_COLUMNS = {
			{ "jobSeqNo", Types.BIGINT },
			{ "simulationUuid", Types.VARCHAR },
			{ "groupId", Types.BIGINT },
			{ "jobUuid", Types.VARCHAR },
			{ "jobStatus", Types.BIGINT },
			{ "jobStartDt", Types.TIMESTAMP },
			{ "jobEndDt", Types.TIMESTAMP },
			{ "jobTitle", Types.VARCHAR },
			{ "jobExecPath", Types.VARCHAR },
			{ "jobPhase", Types.BIGINT },
			{ "jobSubmitDt", Types.TIMESTAMP },
			{ "jobUniversityField", Types.BIGINT },
			{ "jobInputDeckYn", Types.BOOLEAN },
			{ "jobInputDeckName", Types.VARCHAR },
			{ "jobSubmit", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table EDSIM_SimulationJob (jobSeqNo LONG not null,simulationUuid VARCHAR(75) not null,groupId LONG not null,jobUuid VARCHAR(75) null,jobStatus LONG,jobStartDt DATE null,jobEndDt DATE null,jobTitle STRING null,jobExecPath STRING null,jobPhase LONG,jobSubmitDt DATE null,jobUniversityField LONG,jobInputDeckYn BOOLEAN,jobInputDeckName VARCHAR(75) null,jobSubmit BOOLEAN,primary key (jobSeqNo, simulationUuid, groupId))";
	public static final String TABLE_SQL_DROP = "drop table EDSIM_SimulationJob";
	public static final String ORDER_BY_JPQL = " ORDER BY simulationJob.jobStatus ASC";
	public static final String ORDER_BY_SQL = " ORDER BY EDSIM_SimulationJob.jobStatus ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "true";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.kisti.edison.bestsimulation.model.SimulationJob"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.kisti.edison.bestsimulation.model.SimulationJob"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.kisti.edison.bestsimulation.model.SimulationJob"),
			true);
	public static long JOBSTATUS_COLUMN_BITMASK = 1L;
	public static long JOBSUBMIT_COLUMN_BITMASK = 2L;
	public static long JOBUUID_COLUMN_BITMASK = 4L;
	public static long SIMULATIONUUID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SimulationJob toModel(SimulationJobSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SimulationJob model = new SimulationJobImpl();

		model.setJobSeqNo(soapModel.getJobSeqNo());
		model.setSimulationUuid(soapModel.getSimulationUuid());
		model.setGroupId(soapModel.getGroupId());
		model.setJobUuid(soapModel.getJobUuid());
		model.setJobStatus(soapModel.getJobStatus());
		model.setJobStartDt(soapModel.getJobStartDt());
		model.setJobEndDt(soapModel.getJobEndDt());
		model.setJobTitle(soapModel.getJobTitle());
		model.setJobExecPath(soapModel.getJobExecPath());
		model.setJobPhase(soapModel.getJobPhase());
		model.setJobSubmitDt(soapModel.getJobSubmitDt());
		model.setJobUniversityField(soapModel.getJobUniversityField());
		model.setJobInputDeckYn(soapModel.getJobInputDeckYn());
		model.setJobInputDeckName(soapModel.getJobInputDeckName());
		model.setJobSubmit(soapModel.getJobSubmit());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SimulationJob> toModels(SimulationJobSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SimulationJob> models = new ArrayList<SimulationJob>(soapModels.length);

		for (SimulationJobSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.kisti.edison.bestsimulation.model.SimulationJob"));

	public SimulationJobModelImpl() {
	}

	@Override
	public SimulationJobPK getPrimaryKey() {
		return new SimulationJobPK(_jobSeqNo, _simulationUuid, _groupId);
	}

	@Override
	public void setPrimaryKey(SimulationJobPK primaryKey) {
		setJobSeqNo(primaryKey.jobSeqNo);
		setSimulationUuid(primaryKey.simulationUuid);
		setGroupId(primaryKey.groupId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimulationJobPK(_jobSeqNo, _simulationUuid, _groupId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimulationJobPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationJob.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationJob.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jobSeqNo", getJobSeqNo());
		attributes.put("simulationUuid", getSimulationUuid());
		attributes.put("groupId", getGroupId());
		attributes.put("jobUuid", getJobUuid());
		attributes.put("jobStatus", getJobStatus());
		attributes.put("jobStartDt", getJobStartDt());
		attributes.put("jobEndDt", getJobEndDt());
		attributes.put("jobTitle", getJobTitle());
		attributes.put("jobExecPath", getJobExecPath());
		attributes.put("jobPhase", getJobPhase());
		attributes.put("jobSubmitDt", getJobSubmitDt());
		attributes.put("jobUniversityField", getJobUniversityField());
		attributes.put("jobInputDeckYn", getJobInputDeckYn());
		attributes.put("jobInputDeckName", getJobInputDeckName());
		attributes.put("jobSubmit", getJobSubmit());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jobSeqNo = (Long)attributes.get("jobSeqNo");

		if (jobSeqNo != null) {
			setJobSeqNo(jobSeqNo);
		}

		String simulationUuid = (String)attributes.get("simulationUuid");

		if (simulationUuid != null) {
			setSimulationUuid(simulationUuid);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String jobUuid = (String)attributes.get("jobUuid");

		if (jobUuid != null) {
			setJobUuid(jobUuid);
		}

		Long jobStatus = (Long)attributes.get("jobStatus");

		if (jobStatus != null) {
			setJobStatus(jobStatus);
		}

		Date jobStartDt = (Date)attributes.get("jobStartDt");

		if (jobStartDt != null) {
			setJobStartDt(jobStartDt);
		}

		Date jobEndDt = (Date)attributes.get("jobEndDt");

		if (jobEndDt != null) {
			setJobEndDt(jobEndDt);
		}

		String jobTitle = (String)attributes.get("jobTitle");

		if (jobTitle != null) {
			setJobTitle(jobTitle);
		}

		String jobExecPath = (String)attributes.get("jobExecPath");

		if (jobExecPath != null) {
			setJobExecPath(jobExecPath);
		}

		Long jobPhase = (Long)attributes.get("jobPhase");

		if (jobPhase != null) {
			setJobPhase(jobPhase);
		}

		Date jobSubmitDt = (Date)attributes.get("jobSubmitDt");

		if (jobSubmitDt != null) {
			setJobSubmitDt(jobSubmitDt);
		}

		Long jobUniversityField = (Long)attributes.get("jobUniversityField");

		if (jobUniversityField != null) {
			setJobUniversityField(jobUniversityField);
		}

		Boolean jobInputDeckYn = (Boolean)attributes.get("jobInputDeckYn");

		if (jobInputDeckYn != null) {
			setJobInputDeckYn(jobInputDeckYn);
		}

		String jobInputDeckName = (String)attributes.get("jobInputDeckName");

		if (jobInputDeckName != null) {
			setJobInputDeckName(jobInputDeckName);
		}

		Boolean jobSubmit = (Boolean)attributes.get("jobSubmit");

		if (jobSubmit != null) {
			setJobSubmit(jobSubmit);
		}
	}

	@JSON
	@Override
	public long getJobSeqNo() {
		return _jobSeqNo;
	}

	@Override
	public void setJobSeqNo(long jobSeqNo) {
		_jobSeqNo = jobSeqNo;
	}

	@JSON
	@Override
	public String getSimulationUuid() {
		if (_simulationUuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _simulationUuid;
		}
	}

	@Override
	public void setSimulationUuid(String simulationUuid) {
		_columnBitmask |= SIMULATIONUUID_COLUMN_BITMASK;

		if (_originalSimulationUuid == null) {
			_originalSimulationUuid = _simulationUuid;
		}

		_simulationUuid = simulationUuid;
	}

	public String getOriginalSimulationUuid() {
		return GetterUtil.getString(_originalSimulationUuid);
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
	public String getJobUuid() {
		if (_jobUuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _jobUuid;
		}
	}

	@Override
	public void setJobUuid(String jobUuid) {
		_columnBitmask |= JOBUUID_COLUMN_BITMASK;

		if (_originalJobUuid == null) {
			_originalJobUuid = _jobUuid;
		}

		_jobUuid = jobUuid;
	}

	public String getOriginalJobUuid() {
		return GetterUtil.getString(_originalJobUuid);
	}

	@JSON
	@Override
	public long getJobStatus() {
		return _jobStatus;
	}

	@Override
	public void setJobStatus(long jobStatus) {
		_columnBitmask = -1L;

		if (!_setOriginalJobStatus) {
			_setOriginalJobStatus = true;

			_originalJobStatus = _jobStatus;
		}

		_jobStatus = jobStatus;
	}

	public long getOriginalJobStatus() {
		return _originalJobStatus;
	}

	@JSON
	@Override
	public Date getJobStartDt() {
		return _jobStartDt;
	}

	@Override
	public void setJobStartDt(Date jobStartDt) {
		_jobStartDt = jobStartDt;
	}

	@JSON
	@Override
	public Date getJobEndDt() {
		return _jobEndDt;
	}

	@Override
	public void setJobEndDt(Date jobEndDt) {
		_jobEndDt = jobEndDt;
	}

	@JSON
	@Override
	public String getJobTitle() {
		if (_jobTitle == null) {
			return StringPool.BLANK;
		}
		else {
			return _jobTitle;
		}
	}

	@Override
	public String getJobTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getJobTitle(languageId);
	}

	@Override
	public String getJobTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getJobTitle(languageId, useDefault);
	}

	@Override
	public String getJobTitle(String languageId) {
		return LocalizationUtil.getLocalization(getJobTitle(), languageId);
	}

	@Override
	public String getJobTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getJobTitle(), languageId,
			useDefault);
	}

	@Override
	public String getJobTitleCurrentLanguageId() {
		return _jobTitleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getJobTitleCurrentValue() {
		Locale locale = getLocale(_jobTitleCurrentLanguageId);

		return getJobTitle(locale);
	}

	@Override
	public Map<Locale, String> getJobTitleMap() {
		return LocalizationUtil.getLocalizationMap(getJobTitle());
	}

	@Override
	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	@Override
	public void setJobTitle(String jobTitle, Locale locale) {
		setJobTitle(jobTitle, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setJobTitle(String jobTitle, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(jobTitle)) {
			setJobTitle(LocalizationUtil.updateLocalization(getJobTitle(),
					"JobTitle", jobTitle, languageId, defaultLanguageId));
		}
		else {
			setJobTitle(LocalizationUtil.removeLocalization(getJobTitle(),
					"JobTitle", languageId));
		}
	}

	@Override
	public void setJobTitleCurrentLanguageId(String languageId) {
		_jobTitleCurrentLanguageId = languageId;
	}

	@Override
	public void setJobTitleMap(Map<Locale, String> jobTitleMap) {
		setJobTitleMap(jobTitleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setJobTitleMap(Map<Locale, String> jobTitleMap,
		Locale defaultLocale) {
		if (jobTitleMap == null) {
			return;
		}

		setJobTitle(LocalizationUtil.updateLocalization(jobTitleMap,
				getJobTitle(), "JobTitle",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getJobExecPath() {
		if (_jobExecPath == null) {
			return StringPool.BLANK;
		}
		else {
			return _jobExecPath;
		}
	}

	@Override
	public String getJobExecPath(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getJobExecPath(languageId);
	}

	@Override
	public String getJobExecPath(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getJobExecPath(languageId, useDefault);
	}

	@Override
	public String getJobExecPath(String languageId) {
		return LocalizationUtil.getLocalization(getJobExecPath(), languageId);
	}

	@Override
	public String getJobExecPath(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getJobExecPath(), languageId,
			useDefault);
	}

	@Override
	public String getJobExecPathCurrentLanguageId() {
		return _jobExecPathCurrentLanguageId;
	}

	@JSON
	@Override
	public String getJobExecPathCurrentValue() {
		Locale locale = getLocale(_jobExecPathCurrentLanguageId);

		return getJobExecPath(locale);
	}

	@Override
	public Map<Locale, String> getJobExecPathMap() {
		return LocalizationUtil.getLocalizationMap(getJobExecPath());
	}

	@Override
	public void setJobExecPath(String jobExecPath) {
		_jobExecPath = jobExecPath;
	}

	@Override
	public void setJobExecPath(String jobExecPath, Locale locale) {
		setJobExecPath(jobExecPath, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setJobExecPath(String jobExecPath, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(jobExecPath)) {
			setJobExecPath(LocalizationUtil.updateLocalization(
					getJobExecPath(), "JobExecPath", jobExecPath, languageId,
					defaultLanguageId));
		}
		else {
			setJobExecPath(LocalizationUtil.removeLocalization(
					getJobExecPath(), "JobExecPath", languageId));
		}
	}

	@Override
	public void setJobExecPathCurrentLanguageId(String languageId) {
		_jobExecPathCurrentLanguageId = languageId;
	}

	@Override
	public void setJobExecPathMap(Map<Locale, String> jobExecPathMap) {
		setJobExecPathMap(jobExecPathMap, LocaleUtil.getDefault());
	}

	@Override
	public void setJobExecPathMap(Map<Locale, String> jobExecPathMap,
		Locale defaultLocale) {
		if (jobExecPathMap == null) {
			return;
		}

		setJobExecPath(LocalizationUtil.updateLocalization(jobExecPathMap,
				getJobExecPath(), "JobExecPath",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public long getJobPhase() {
		return _jobPhase;
	}

	@Override
	public void setJobPhase(long jobPhase) {
		_jobPhase = jobPhase;
	}

	@JSON
	@Override
	public Date getJobSubmitDt() {
		return _jobSubmitDt;
	}

	@Override
	public void setJobSubmitDt(Date jobSubmitDt) {
		_jobSubmitDt = jobSubmitDt;
	}

	@JSON
	@Override
	public long getJobUniversityField() {
		return _jobUniversityField;
	}

	@Override
	public void setJobUniversityField(long jobUniversityField) {
		_jobUniversityField = jobUniversityField;
	}

	@JSON
	@Override
	public boolean getJobInputDeckYn() {
		return _jobInputDeckYn;
	}

	@Override
	public boolean isJobInputDeckYn() {
		return _jobInputDeckYn;
	}

	@Override
	public void setJobInputDeckYn(boolean jobInputDeckYn) {
		_jobInputDeckYn = jobInputDeckYn;
	}

	@JSON
	@Override
	public String getJobInputDeckName() {
		if (_jobInputDeckName == null) {
			return StringPool.BLANK;
		}
		else {
			return _jobInputDeckName;
		}
	}

	@Override
	public void setJobInputDeckName(String jobInputDeckName) {
		_jobInputDeckName = jobInputDeckName;
	}

	@JSON
	@Override
	public boolean getJobSubmit() {
		return _jobSubmit;
	}

	@Override
	public boolean isJobSubmit() {
		return _jobSubmit;
	}

	@Override
	public void setJobSubmit(boolean jobSubmit) {
		_columnBitmask |= JOBSUBMIT_COLUMN_BITMASK;

		if (!_setOriginalJobSubmit) {
			_setOriginalJobSubmit = true;

			_originalJobSubmit = _jobSubmit;
		}

		_jobSubmit = jobSubmit;
	}

	public boolean getOriginalJobSubmit() {
		return _originalJobSubmit;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> jobTitleMap = getJobTitleMap();

		for (Map.Entry<Locale, String> entry : jobTitleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> jobExecPathMap = getJobExecPathMap();

		for (Map.Entry<Locale, String> entry : jobExecPathMap.entrySet()) {
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
		String xml = getJobTitle();

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

		String jobTitle = getJobTitle(defaultLocale);

		if (Validator.isNull(jobTitle)) {
			setJobTitle(getJobTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setJobTitle(getJobTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String jobExecPath = getJobExecPath(defaultLocale);

		if (Validator.isNull(jobExecPath)) {
			setJobExecPath(getJobExecPath(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setJobExecPath(getJobExecPath(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public SimulationJob toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SimulationJob)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SimulationJobImpl simulationJobImpl = new SimulationJobImpl();

		simulationJobImpl.setJobSeqNo(getJobSeqNo());
		simulationJobImpl.setSimulationUuid(getSimulationUuid());
		simulationJobImpl.setGroupId(getGroupId());
		simulationJobImpl.setJobUuid(getJobUuid());
		simulationJobImpl.setJobStatus(getJobStatus());
		simulationJobImpl.setJobStartDt(getJobStartDt());
		simulationJobImpl.setJobEndDt(getJobEndDt());
		simulationJobImpl.setJobTitle(getJobTitle());
		simulationJobImpl.setJobExecPath(getJobExecPath());
		simulationJobImpl.setJobPhase(getJobPhase());
		simulationJobImpl.setJobSubmitDt(getJobSubmitDt());
		simulationJobImpl.setJobUniversityField(getJobUniversityField());
		simulationJobImpl.setJobInputDeckYn(getJobInputDeckYn());
		simulationJobImpl.setJobInputDeckName(getJobInputDeckName());
		simulationJobImpl.setJobSubmit(getJobSubmit());

		simulationJobImpl.resetOriginalValues();

		return simulationJobImpl;
	}

	@Override
	public int compareTo(SimulationJob simulationJob) {
		int value = 0;

		if (getJobStatus() < simulationJob.getJobStatus()) {
			value = -1;
		}
		else if (getJobStatus() > simulationJob.getJobStatus()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof SimulationJob)) {
			return false;
		}

		SimulationJob simulationJob = (SimulationJob)obj;

		SimulationJobPK primaryKey = simulationJob.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public void resetOriginalValues() {
		SimulationJobModelImpl simulationJobModelImpl = this;

		simulationJobModelImpl._originalSimulationUuid = simulationJobModelImpl._simulationUuid;

		simulationJobModelImpl._originalJobUuid = simulationJobModelImpl._jobUuid;

		simulationJobModelImpl._originalJobStatus = simulationJobModelImpl._jobStatus;

		simulationJobModelImpl._setOriginalJobStatus = false;

		simulationJobModelImpl._originalJobSubmit = simulationJobModelImpl._jobSubmit;

		simulationJobModelImpl._setOriginalJobSubmit = false;

		simulationJobModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SimulationJob> toCacheModel() {
		SimulationJobCacheModel simulationJobCacheModel = new SimulationJobCacheModel();

		simulationJobCacheModel.jobSeqNo = getJobSeqNo();

		simulationJobCacheModel.simulationUuid = getSimulationUuid();

		String simulationUuid = simulationJobCacheModel.simulationUuid;

		if ((simulationUuid != null) && (simulationUuid.length() == 0)) {
			simulationJobCacheModel.simulationUuid = null;
		}

		simulationJobCacheModel.groupId = getGroupId();

		simulationJobCacheModel.jobUuid = getJobUuid();

		String jobUuid = simulationJobCacheModel.jobUuid;

		if ((jobUuid != null) && (jobUuid.length() == 0)) {
			simulationJobCacheModel.jobUuid = null;
		}

		simulationJobCacheModel.jobStatus = getJobStatus();

		Date jobStartDt = getJobStartDt();

		if (jobStartDt != null) {
			simulationJobCacheModel.jobStartDt = jobStartDt.getTime();
		}
		else {
			simulationJobCacheModel.jobStartDt = Long.MIN_VALUE;
		}

		Date jobEndDt = getJobEndDt();

		if (jobEndDt != null) {
			simulationJobCacheModel.jobEndDt = jobEndDt.getTime();
		}
		else {
			simulationJobCacheModel.jobEndDt = Long.MIN_VALUE;
		}

		simulationJobCacheModel.jobTitle = getJobTitle();

		String jobTitle = simulationJobCacheModel.jobTitle;

		if ((jobTitle != null) && (jobTitle.length() == 0)) {
			simulationJobCacheModel.jobTitle = null;
		}

		simulationJobCacheModel.jobExecPath = getJobExecPath();

		String jobExecPath = simulationJobCacheModel.jobExecPath;

		if ((jobExecPath != null) && (jobExecPath.length() == 0)) {
			simulationJobCacheModel.jobExecPath = null;
		}

		simulationJobCacheModel.jobPhase = getJobPhase();

		Date jobSubmitDt = getJobSubmitDt();

		if (jobSubmitDt != null) {
			simulationJobCacheModel.jobSubmitDt = jobSubmitDt.getTime();
		}
		else {
			simulationJobCacheModel.jobSubmitDt = Long.MIN_VALUE;
		}

		simulationJobCacheModel.jobUniversityField = getJobUniversityField();

		simulationJobCacheModel.jobInputDeckYn = getJobInputDeckYn();

		simulationJobCacheModel.jobInputDeckName = getJobInputDeckName();

		String jobInputDeckName = simulationJobCacheModel.jobInputDeckName;

		if ((jobInputDeckName != null) && (jobInputDeckName.length() == 0)) {
			simulationJobCacheModel.jobInputDeckName = null;
		}

		simulationJobCacheModel.jobSubmit = getJobSubmit();

		return simulationJobCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{jobSeqNo=");
		sb.append(getJobSeqNo());
		sb.append(", simulationUuid=");
		sb.append(getSimulationUuid());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", jobUuid=");
		sb.append(getJobUuid());
		sb.append(", jobStatus=");
		sb.append(getJobStatus());
		sb.append(", jobStartDt=");
		sb.append(getJobStartDt());
		sb.append(", jobEndDt=");
		sb.append(getJobEndDt());
		sb.append(", jobTitle=");
		sb.append(getJobTitle());
		sb.append(", jobExecPath=");
		sb.append(getJobExecPath());
		sb.append(", jobPhase=");
		sb.append(getJobPhase());
		sb.append(", jobSubmitDt=");
		sb.append(getJobSubmitDt());
		sb.append(", jobUniversityField=");
		sb.append(getJobUniversityField());
		sb.append(", jobInputDeckYn=");
		sb.append(getJobInputDeckYn());
		sb.append(", jobInputDeckName=");
		sb.append(getJobInputDeckName());
		sb.append(", jobSubmit=");
		sb.append(getJobSubmit());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.bestsimulation.model.SimulationJob");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jobSeqNo</column-name><column-value><![CDATA[");
		sb.append(getJobSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationUuid</column-name><column-value><![CDATA[");
		sb.append(getSimulationUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobUuid</column-name><column-value><![CDATA[");
		sb.append(getJobUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobStatus</column-name><column-value><![CDATA[");
		sb.append(getJobStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobStartDt</column-name><column-value><![CDATA[");
		sb.append(getJobStartDt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobEndDt</column-name><column-value><![CDATA[");
		sb.append(getJobEndDt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobTitle</column-name><column-value><![CDATA[");
		sb.append(getJobTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobExecPath</column-name><column-value><![CDATA[");
		sb.append(getJobExecPath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobPhase</column-name><column-value><![CDATA[");
		sb.append(getJobPhase());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobSubmitDt</column-name><column-value><![CDATA[");
		sb.append(getJobSubmitDt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobUniversityField</column-name><column-value><![CDATA[");
		sb.append(getJobUniversityField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobInputDeckYn</column-name><column-value><![CDATA[");
		sb.append(getJobInputDeckYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobInputDeckName</column-name><column-value><![CDATA[");
		sb.append(getJobInputDeckName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobSubmit</column-name><column-value><![CDATA[");
		sb.append(getJobSubmit());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = SimulationJob.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			SimulationJob.class
		};
	private long _jobSeqNo;
	private String _simulationUuid;
	private String _originalSimulationUuid;
	private long _groupId;
	private String _jobUuid;
	private String _originalJobUuid;
	private long _jobStatus;
	private long _originalJobStatus;
	private boolean _setOriginalJobStatus;
	private Date _jobStartDt;
	private Date _jobEndDt;
	private String _jobTitle;
	private String _jobTitleCurrentLanguageId;
	private String _jobExecPath;
	private String _jobExecPathCurrentLanguageId;
	private long _jobPhase;
	private Date _jobSubmitDt;
	private long _jobUniversityField;
	private boolean _jobInputDeckYn;
	private String _jobInputDeckName;
	private boolean _jobSubmit;
	private boolean _originalJobSubmit;
	private boolean _setOriginalJobSubmit;
	private long _columnBitmask;
	private SimulationJob _escapedModel;
}