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

package org.kisti.edison.virtuallaboratory.model;

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

import org.kisti.edison.virtuallaboratory.service.ClpSerializer;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassStsMigrationLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author EDISON
 */
public class VirtualLabClassStsMigrationClp extends BaseModelImpl<VirtualLabClassStsMigration>
	implements VirtualLabClassStsMigration {
	public VirtualLabClassStsMigrationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabClassStsMigration.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabClassStsMigration.class.getName();
	}

	@Override
	public VirtualLabClassStsMigrationPK getPrimaryKey() {
		return new VirtualLabClassStsMigrationPK(_groupId, _virtualLabId,
			_classId);
	}

	@Override
	public void setPrimaryKey(VirtualLabClassStsMigrationPK primaryKey) {
		setGroupId(primaryKey.groupId);
		setVirtualLabId(primaryKey.virtualLabId);
		setClassId(primaryKey.classId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new VirtualLabClassStsMigrationPK(_groupId, _virtualLabId,
			_classId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((VirtualLabClassStsMigrationPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("groupId", getGroupId());
		attributes.put("virtualLabId", getVirtualLabId());
		attributes.put("classId", getClassId());
		attributes.put("universityField", getUniversityField());
		attributes.put("virtualLabTitle", getVirtualLabTitle());
		attributes.put("virtualLabPersonName", getVirtualLabPersonName());
		attributes.put("classTitle", getClassTitle());
		attributes.put("virtualClassCd", getVirtualClassCd());
		attributes.put("classCreateDt", getClassCreateDt());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("scienceAppName", getScienceAppName());
		attributes.put("registerStudentCnt", getRegisterStudentCnt());
		attributes.put("executeCount", getExecuteCount());
		attributes.put("executeStudentcount", getExecuteStudentcount());
		attributes.put("avgerageRuntime", getAvgerageRuntime());
		attributes.put("cputime", getCputime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		String universityField = (String)attributes.get("universityField");

		if (universityField != null) {
			setUniversityField(universityField);
		}

		String virtualLabTitle = (String)attributes.get("virtualLabTitle");

		if (virtualLabTitle != null) {
			setVirtualLabTitle(virtualLabTitle);
		}

		String virtualLabPersonName = (String)attributes.get(
				"virtualLabPersonName");

		if (virtualLabPersonName != null) {
			setVirtualLabPersonName(virtualLabPersonName);
		}

		String classTitle = (String)attributes.get("classTitle");

		if (classTitle != null) {
			setClassTitle(classTitle);
		}

		String virtualClassCd = (String)attributes.get("virtualClassCd");

		if (virtualClassCd != null) {
			setVirtualClassCd(virtualClassCd);
		}

		Date classCreateDt = (Date)attributes.get("classCreateDt");

		if (classCreateDt != null) {
			setClassCreateDt(classCreateDt);
		}

		String scienceAppId = (String)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		String scienceAppName = (String)attributes.get("scienceAppName");

		if (scienceAppName != null) {
			setScienceAppName(scienceAppName);
		}

		Long registerStudentCnt = (Long)attributes.get("registerStudentCnt");

		if (registerStudentCnt != null) {
			setRegisterStudentCnt(registerStudentCnt);
		}

		Long executeCount = (Long)attributes.get("executeCount");

		if (executeCount != null) {
			setExecuteCount(executeCount);
		}

		Long executeStudentcount = (Long)attributes.get("executeStudentcount");

		if (executeStudentcount != null) {
			setExecuteStudentcount(executeStudentcount);
		}

		Long avgerageRuntime = (Long)attributes.get("avgerageRuntime");

		if (avgerageRuntime != null) {
			setAvgerageRuntime(avgerageRuntime);
		}

		Long cputime = (Long)attributes.get("cputime");

		if (cputime != null) {
			setCputime(cputime);
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getVirtualLabId() {
		return _virtualLabId;
	}

	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabId", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					virtualLabId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassId() {
		return _classId;
	}

	@Override
	public void setClassId(long classId) {
		_classId = classId;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setClassId", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel, classId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUniversityField() {
		return _universityField;
	}

	@Override
	public void setUniversityField(String universityField) {
		_universityField = universityField;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setUniversityField",
						String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					universityField);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVirtualLabTitle() {
		return _virtualLabTitle;
	}

	@Override
	public String getVirtualLabTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getVirtualLabTitle(languageId);
	}

	@Override
	public String getVirtualLabTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getVirtualLabTitle(languageId, useDefault);
	}

	@Override
	public String getVirtualLabTitle(String languageId) {
		return LocalizationUtil.getLocalization(getVirtualLabTitle(), languageId);
	}

	@Override
	public String getVirtualLabTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getVirtualLabTitle(),
			languageId, useDefault);
	}

	@Override
	public String getVirtualLabTitleCurrentLanguageId() {
		return _virtualLabTitleCurrentLanguageId;
	}

	@Override
	public String getVirtualLabTitleCurrentValue() {
		Locale locale = getLocale(_virtualLabTitleCurrentLanguageId);

		return getVirtualLabTitle(locale);
	}

	@Override
	public Map<Locale, String> getVirtualLabTitleMap() {
		return LocalizationUtil.getLocalizationMap(getVirtualLabTitle());
	}

	@Override
	public void setVirtualLabTitle(String virtualLabTitle) {
		_virtualLabTitle = virtualLabTitle;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabTitle",
						String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					virtualLabTitle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setVirtualLabTitle(String virtualLabTitle, Locale locale) {
		setVirtualLabTitle(virtualLabTitle, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setVirtualLabTitle(String virtualLabTitle, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(virtualLabTitle)) {
			setVirtualLabTitle(LocalizationUtil.updateLocalization(
					getVirtualLabTitle(), "VirtualLabTitle", virtualLabTitle,
					languageId, defaultLanguageId));
		}
		else {
			setVirtualLabTitle(LocalizationUtil.removeLocalization(
					getVirtualLabTitle(), "VirtualLabTitle", languageId));
		}
	}

	@Override
	public void setVirtualLabTitleCurrentLanguageId(String languageId) {
		_virtualLabTitleCurrentLanguageId = languageId;
	}

	@Override
	public void setVirtualLabTitleMap(Map<Locale, String> virtualLabTitleMap) {
		setVirtualLabTitleMap(virtualLabTitleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setVirtualLabTitleMap(Map<Locale, String> virtualLabTitleMap,
		Locale defaultLocale) {
		if (virtualLabTitleMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setVirtualLabTitle(LocalizationUtil.updateLocalization(
					virtualLabTitleMap, getVirtualLabTitle(),
					"VirtualLabTitle", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getVirtualLabPersonName() {
		return _virtualLabPersonName;
	}

	@Override
	public String getVirtualLabPersonName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getVirtualLabPersonName(languageId);
	}

	@Override
	public String getVirtualLabPersonName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getVirtualLabPersonName(languageId, useDefault);
	}

	@Override
	public String getVirtualLabPersonName(String languageId) {
		return LocalizationUtil.getLocalization(getVirtualLabPersonName(),
			languageId);
	}

	@Override
	public String getVirtualLabPersonName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getVirtualLabPersonName(),
			languageId, useDefault);
	}

	@Override
	public String getVirtualLabPersonNameCurrentLanguageId() {
		return _virtualLabPersonNameCurrentLanguageId;
	}

	@Override
	public String getVirtualLabPersonNameCurrentValue() {
		Locale locale = getLocale(_virtualLabPersonNameCurrentLanguageId);

		return getVirtualLabPersonName(locale);
	}

	@Override
	public Map<Locale, String> getVirtualLabPersonNameMap() {
		return LocalizationUtil.getLocalizationMap(getVirtualLabPersonName());
	}

	@Override
	public void setVirtualLabPersonName(String virtualLabPersonName) {
		_virtualLabPersonName = virtualLabPersonName;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabPersonName",
						String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					virtualLabPersonName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setVirtualLabPersonName(String virtualLabPersonName,
		Locale locale) {
		setVirtualLabPersonName(virtualLabPersonName, locale,
			LocaleUtil.getDefault());
	}

	@Override
	public void setVirtualLabPersonName(String virtualLabPersonName,
		Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(virtualLabPersonName)) {
			setVirtualLabPersonName(LocalizationUtil.updateLocalization(
					getVirtualLabPersonName(), "VirtualLabPersonName",
					virtualLabPersonName, languageId, defaultLanguageId));
		}
		else {
			setVirtualLabPersonName(LocalizationUtil.removeLocalization(
					getVirtualLabPersonName(), "VirtualLabPersonName",
					languageId));
		}
	}

	@Override
	public void setVirtualLabPersonNameCurrentLanguageId(String languageId) {
		_virtualLabPersonNameCurrentLanguageId = languageId;
	}

	@Override
	public void setVirtualLabPersonNameMap(
		Map<Locale, String> virtualLabPersonNameMap) {
		setVirtualLabPersonNameMap(virtualLabPersonNameMap,
			LocaleUtil.getDefault());
	}

	@Override
	public void setVirtualLabPersonNameMap(
		Map<Locale, String> virtualLabPersonNameMap, Locale defaultLocale) {
		if (virtualLabPersonNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setVirtualLabPersonName(LocalizationUtil.updateLocalization(
					virtualLabPersonNameMap, getVirtualLabPersonName(),
					"VirtualLabPersonName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getClassTitle() {
		return _classTitle;
	}

	@Override
	public String getClassTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getClassTitle(languageId);
	}

	@Override
	public String getClassTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getClassTitle(languageId, useDefault);
	}

	@Override
	public String getClassTitle(String languageId) {
		return LocalizationUtil.getLocalization(getClassTitle(), languageId);
	}

	@Override
	public String getClassTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getClassTitle(), languageId,
			useDefault);
	}

	@Override
	public String getClassTitleCurrentLanguageId() {
		return _classTitleCurrentLanguageId;
	}

	@Override
	public String getClassTitleCurrentValue() {
		Locale locale = getLocale(_classTitleCurrentLanguageId);

		return getClassTitle(locale);
	}

	@Override
	public Map<Locale, String> getClassTitleMap() {
		return LocalizationUtil.getLocalizationMap(getClassTitle());
	}

	@Override
	public void setClassTitle(String classTitle) {
		_classTitle = classTitle;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setClassTitle", String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					classTitle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setClassTitle(String classTitle, Locale locale) {
		setClassTitle(classTitle, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setClassTitle(String classTitle, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(classTitle)) {
			setClassTitle(LocalizationUtil.updateLocalization(getClassTitle(),
					"ClassTitle", classTitle, languageId, defaultLanguageId));
		}
		else {
			setClassTitle(LocalizationUtil.removeLocalization(getClassTitle(),
					"ClassTitle", languageId));
		}
	}

	@Override
	public void setClassTitleCurrentLanguageId(String languageId) {
		_classTitleCurrentLanguageId = languageId;
	}

	@Override
	public void setClassTitleMap(Map<Locale, String> classTitleMap) {
		setClassTitleMap(classTitleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setClassTitleMap(Map<Locale, String> classTitleMap,
		Locale defaultLocale) {
		if (classTitleMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setClassTitle(LocalizationUtil.updateLocalization(classTitleMap,
					getClassTitle(), "ClassTitle",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getVirtualClassCd() {
		return _virtualClassCd;
	}

	@Override
	public void setVirtualClassCd(String virtualClassCd) {
		_virtualClassCd = virtualClassCd;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualClassCd",
						String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					virtualClassCd);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getClassCreateDt() {
		return _classCreateDt;
	}

	@Override
	public void setClassCreateDt(Date classCreateDt) {
		_classCreateDt = classCreateDt;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setClassCreateDt", Date.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					classCreateDt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(String scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScienceAppName() {
		return _scienceAppName;
	}

	@Override
	public void setScienceAppName(String scienceAppName) {
		_scienceAppName = scienceAppName;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppName",
						String.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					scienceAppName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRegisterStudentCnt() {
		return _registerStudentCnt;
	}

	@Override
	public void setRegisterStudentCnt(long registerStudentCnt) {
		_registerStudentCnt = registerStudentCnt;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setRegisterStudentCnt",
						long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					registerStudentCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExecuteCount() {
		return _executeCount;
	}

	@Override
	public void setExecuteCount(long executeCount) {
		_executeCount = executeCount;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteCount", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					executeCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExecuteStudentcount() {
		return _executeStudentcount;
	}

	@Override
	public void setExecuteStudentcount(long executeStudentcount) {
		_executeStudentcount = executeStudentcount;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteStudentcount",
						long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					executeStudentcount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAvgerageRuntime() {
		return _avgerageRuntime;
	}

	@Override
	public void setAvgerageRuntime(long avgerageRuntime) {
		_avgerageRuntime = avgerageRuntime;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setAvgerageRuntime", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel,
					avgerageRuntime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCputime() {
		return _cputime;
	}

	@Override
	public void setCputime(long cputime) {
		_cputime = cputime;

		if (_virtualLabClassStsMigrationRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStsMigrationRemoteModel.getClass();

				Method method = clazz.getMethod("setCputime", long.class);

				method.invoke(_virtualLabClassStsMigrationRemoteModel, cputime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getVirtualLabClassStsMigrationRemoteModel() {
		return _virtualLabClassStsMigrationRemoteModel;
	}

	public void setVirtualLabClassStsMigrationRemoteModel(
		BaseModel<?> virtualLabClassStsMigrationRemoteModel) {
		_virtualLabClassStsMigrationRemoteModel = virtualLabClassStsMigrationRemoteModel;
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

		Class<?> remoteModelClass = _virtualLabClassStsMigrationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_virtualLabClassStsMigrationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			VirtualLabClassStsMigrationLocalServiceUtil.addVirtualLabClassStsMigration(this);
		}
		else {
			VirtualLabClassStsMigrationLocalServiceUtil.updateVirtualLabClassStsMigration(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> virtualLabTitleMap = getVirtualLabTitleMap();

		for (Map.Entry<Locale, String> entry : virtualLabTitleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> virtualLabPersonNameMap = getVirtualLabPersonNameMap();

		for (Map.Entry<Locale, String> entry : virtualLabPersonNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> classTitleMap = getClassTitleMap();

		for (Map.Entry<Locale, String> entry : classTitleMap.entrySet()) {
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
		String xml = getVirtualLabTitle();

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

		String virtualLabTitle = getVirtualLabTitle(defaultLocale);

		if (Validator.isNull(virtualLabTitle)) {
			setVirtualLabTitle(getVirtualLabTitle(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setVirtualLabTitle(getVirtualLabTitle(defaultLocale),
				defaultLocale, defaultLocale);
		}

		String virtualLabPersonName = getVirtualLabPersonName(defaultLocale);

		if (Validator.isNull(virtualLabPersonName)) {
			setVirtualLabPersonName(getVirtualLabPersonName(
					modelDefaultLanguageId), defaultLocale);
		}
		else {
			setVirtualLabPersonName(getVirtualLabPersonName(defaultLocale),
				defaultLocale, defaultLocale);
		}

		String classTitle = getClassTitle(defaultLocale);

		if (Validator.isNull(classTitle)) {
			setClassTitle(getClassTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setClassTitle(getClassTitle(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public VirtualLabClassStsMigration toEscapedModel() {
		return (VirtualLabClassStsMigration)ProxyUtil.newProxyInstance(VirtualLabClassStsMigration.class.getClassLoader(),
			new Class[] { VirtualLabClassStsMigration.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		VirtualLabClassStsMigrationClp clone = new VirtualLabClassStsMigrationClp();

		clone.setGroupId(getGroupId());
		clone.setVirtualLabId(getVirtualLabId());
		clone.setClassId(getClassId());
		clone.setUniversityField(getUniversityField());
		clone.setVirtualLabTitle(getVirtualLabTitle());
		clone.setVirtualLabPersonName(getVirtualLabPersonName());
		clone.setClassTitle(getClassTitle());
		clone.setVirtualClassCd(getVirtualClassCd());
		clone.setClassCreateDt(getClassCreateDt());
		clone.setScienceAppId(getScienceAppId());
		clone.setScienceAppName(getScienceAppName());
		clone.setRegisterStudentCnt(getRegisterStudentCnt());
		clone.setExecuteCount(getExecuteCount());
		clone.setExecuteStudentcount(getExecuteStudentcount());
		clone.setAvgerageRuntime(getAvgerageRuntime());
		clone.setCputime(getCputime());

		return clone;
	}

	@Override
	public int compareTo(
		VirtualLabClassStsMigration virtualLabClassStsMigration) {
		VirtualLabClassStsMigrationPK primaryKey = virtualLabClassStsMigration.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabClassStsMigrationClp)) {
			return false;
		}

		VirtualLabClassStsMigrationClp virtualLabClassStsMigration = (VirtualLabClassStsMigrationClp)obj;

		VirtualLabClassStsMigrationPK primaryKey = virtualLabClassStsMigration.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
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
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{groupId=");
		sb.append(getGroupId());
		sb.append(", virtualLabId=");
		sb.append(getVirtualLabId());
		sb.append(", classId=");
		sb.append(getClassId());
		sb.append(", universityField=");
		sb.append(getUniversityField());
		sb.append(", virtualLabTitle=");
		sb.append(getVirtualLabTitle());
		sb.append(", virtualLabPersonName=");
		sb.append(getVirtualLabPersonName());
		sb.append(", classTitle=");
		sb.append(getClassTitle());
		sb.append(", virtualClassCd=");
		sb.append(getVirtualClassCd());
		sb.append(", classCreateDt=");
		sb.append(getClassCreateDt());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", scienceAppName=");
		sb.append(getScienceAppName());
		sb.append(", registerStudentCnt=");
		sb.append(getRegisterStudentCnt());
		sb.append(", executeCount=");
		sb.append(getExecuteCount());
		sb.append(", executeStudentcount=");
		sb.append(getExecuteStudentcount());
		sb.append(", avgerageRuntime=");
		sb.append(getAvgerageRuntime());
		sb.append(", cputime=");
		sb.append(getCputime());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabId</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classId</column-name><column-value><![CDATA[");
		sb.append(getClassId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>universityField</column-name><column-value><![CDATA[");
		sb.append(getUniversityField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabTitle</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabPersonName</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabPersonName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classTitle</column-name><column-value><![CDATA[");
		sb.append(getClassTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualClassCd</column-name><column-value><![CDATA[");
		sb.append(getVirtualClassCd());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classCreateDt</column-name><column-value><![CDATA[");
		sb.append(getClassCreateDt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppName</column-name><column-value><![CDATA[");
		sb.append(getScienceAppName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registerStudentCnt</column-name><column-value><![CDATA[");
		sb.append(getRegisterStudentCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeCount</column-name><column-value><![CDATA[");
		sb.append(getExecuteCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeStudentcount</column-name><column-value><![CDATA[");
		sb.append(getExecuteStudentcount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>avgerageRuntime</column-name><column-value><![CDATA[");
		sb.append(getAvgerageRuntime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cputime</column-name><column-value><![CDATA[");
		sb.append(getCputime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _groupId;
	private long _virtualLabId;
	private long _classId;
	private String _universityField;
	private String _virtualLabTitle;
	private String _virtualLabTitleCurrentLanguageId;
	private String _virtualLabPersonName;
	private String _virtualLabPersonNameCurrentLanguageId;
	private String _classTitle;
	private String _classTitleCurrentLanguageId;
	private String _virtualClassCd;
	private Date _classCreateDt;
	private String _scienceAppId;
	private String _scienceAppName;
	private long _registerStudentCnt;
	private long _executeCount;
	private long _executeStudentcount;
	private long _avgerageRuntime;
	private long _cputime;
	private BaseModel<?> _virtualLabClassStsMigrationRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.virtuallaboratory.service.ClpSerializer.class;
}