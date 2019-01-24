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

package org.kisti.edison.bestsimulation.model;

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

import org.kisti.edison.bestsimulation.service.ClpSerializer;
import org.kisti.edison.bestsimulation.service.VirtualLabClassStatisticsLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK;

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
public class VirtualLabClassStatisticsClp extends BaseModelImpl<VirtualLabClassStatistics>
	implements VirtualLabClassStatistics {
	public VirtualLabClassStatisticsClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabClassStatistics.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabClassStatistics.class.getName();
	}

	@Override
	public VirtualLabClassStatisticsPK getPrimaryKey() {
		return new VirtualLabClassStatisticsPK(_virtualLabId, _classId);
	}

	@Override
	public void setPrimaryKey(VirtualLabClassStatisticsPK primaryKey) {
		setVirtualLabId(primaryKey.virtualLabId);
		setClassId(primaryKey.classId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new VirtualLabClassStatisticsPK(_virtualLabId, _classId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((VirtualLabClassStatisticsPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("virtualLabId", getVirtualLabId());
		attributes.put("classId", getClassId());
		attributes.put("virtualLabTitle", getVirtualLabTitle());
		attributes.put("classTitle", getClassTitle());
		attributes.put("virtualLabPersonName", getVirtualLabPersonName());
		attributes.put("registerStudentCnt", getRegisterStudentCnt());
		attributes.put("virtualLabUsersId", getVirtualLabUsersId());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("executeUserCnt", getExecuteUserCnt());
		attributes.put("executeCnt", getExecuteCnt());
		attributes.put("cputime", getCputime());
		attributes.put("groupId", getGroupId());
		attributes.put("university", getUniversity());
		attributes.put("classCreateDt", getClassCreateDt());
		attributes.put("virtualLabUseYn", getVirtualLabUseYn());
		attributes.put("classUseYn", getClassUseYn());
		attributes.put("lastModifiedDt", getLastModifiedDt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}

		String classId = (String)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		String virtualLabTitle = (String)attributes.get("virtualLabTitle");

		if (virtualLabTitle != null) {
			setVirtualLabTitle(virtualLabTitle);
		}

		String classTitle = (String)attributes.get("classTitle");

		if (classTitle != null) {
			setClassTitle(classTitle);
		}

		String virtualLabPersonName = (String)attributes.get(
				"virtualLabPersonName");

		if (virtualLabPersonName != null) {
			setVirtualLabPersonName(virtualLabPersonName);
		}

		Long registerStudentCnt = (Long)attributes.get("registerStudentCnt");

		if (registerStudentCnt != null) {
			setRegisterStudentCnt(registerStudentCnt);
		}

		String virtualLabUsersId = (String)attributes.get("virtualLabUsersId");

		if (virtualLabUsersId != null) {
			setVirtualLabUsersId(virtualLabUsersId);
		}

		String scienceAppId = (String)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long executeUserCnt = (Long)attributes.get("executeUserCnt");

		if (executeUserCnt != null) {
			setExecuteUserCnt(executeUserCnt);
		}

		Long executeCnt = (Long)attributes.get("executeCnt");

		if (executeCnt != null) {
			setExecuteCnt(executeCnt);
		}

		String cputime = (String)attributes.get("cputime");

		if (cputime != null) {
			setCputime(cputime);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long university = (Long)attributes.get("university");

		if (university != null) {
			setUniversity(university);
		}

		Date classCreateDt = (Date)attributes.get("classCreateDt");

		if (classCreateDt != null) {
			setClassCreateDt(classCreateDt);
		}

		String virtualLabUseYn = (String)attributes.get("virtualLabUseYn");

		if (virtualLabUseYn != null) {
			setVirtualLabUseYn(virtualLabUseYn);
		}

		String classUseYn = (String)attributes.get("classUseYn");

		if (classUseYn != null) {
			setClassUseYn(classUseYn);
		}

		Date lastModifiedDt = (Date)attributes.get("lastModifiedDt");

		if (lastModifiedDt != null) {
			setLastModifiedDt(lastModifiedDt);
		}
	}

	@Override
	public long getVirtualLabId() {
		return _virtualLabId;
	}

	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabId", long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					virtualLabId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassId() {
		return _classId;
	}

	@Override
	public void setClassId(String classId) {
		_classId = classId;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setClassId", String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, classId);
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabTitle",
						String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setClassTitle", String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, classTitle);
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
	public String getVirtualLabPersonName() {
		return _virtualLabPersonName;
	}

	@Override
	public void setVirtualLabPersonName(String virtualLabPersonName) {
		_virtualLabPersonName = virtualLabPersonName;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabPersonName",
						String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					virtualLabPersonName);
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setRegisterStudentCnt",
						long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					registerStudentCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVirtualLabUsersId() {
		return _virtualLabUsersId;
	}

	@Override
	public void setVirtualLabUsersId(String virtualLabUsersId) {
		_virtualLabUsersId = virtualLabUsersId;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabUsersId",
						String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					virtualLabUsersId);
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExecuteUserCnt() {
		return _executeUserCnt;
	}

	@Override
	public void setExecuteUserCnt(long executeUserCnt) {
		_executeUserCnt = executeUserCnt;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteUserCnt", long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					executeUserCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExecuteCnt() {
		return _executeCnt;
	}

	@Override
	public void setExecuteCnt(long executeCnt) {
		_executeCnt = executeCnt;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteCnt", long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, executeCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCputime() {
		return _cputime;
	}

	@Override
	public void setCputime(String cputime) {
		_cputime = cputime;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setCputime", String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, cputime);
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUniversity() {
		return _university;
	}

	@Override
	public void setUniversity(long university) {
		_university = university;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setUniversity", long.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, university);
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

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setClassCreateDt", Date.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					classCreateDt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVirtualLabUseYn() {
		return _virtualLabUseYn;
	}

	@Override
	public void setVirtualLabUseYn(String virtualLabUseYn) {
		_virtualLabUseYn = virtualLabUseYn;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabUseYn",
						String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					virtualLabUseYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassUseYn() {
		return _classUseYn;
	}

	@Override
	public void setClassUseYn(String classUseYn) {
		_classUseYn = classUseYn;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setClassUseYn", String.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel, classUseYn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastModifiedDt() {
		return _lastModifiedDt;
	}

	@Override
	public void setLastModifiedDt(Date lastModifiedDt) {
		_lastModifiedDt = lastModifiedDt;

		if (_virtualLabClassStatisticsRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabClassStatisticsRemoteModel.getClass();

				Method method = clazz.getMethod("setLastModifiedDt", Date.class);

				method.invoke(_virtualLabClassStatisticsRemoteModel,
					lastModifiedDt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getVirtualLabClassStatisticsRemoteModel() {
		return _virtualLabClassStatisticsRemoteModel;
	}

	public void setVirtualLabClassStatisticsRemoteModel(
		BaseModel<?> virtualLabClassStatisticsRemoteModel) {
		_virtualLabClassStatisticsRemoteModel = virtualLabClassStatisticsRemoteModel;
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

		Class<?> remoteModelClass = _virtualLabClassStatisticsRemoteModel.getClass();

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

		Object returnValue = method.invoke(_virtualLabClassStatisticsRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			VirtualLabClassStatisticsLocalServiceUtil.addVirtualLabClassStatistics(this);
		}
		else {
			VirtualLabClassStatisticsLocalServiceUtil.updateVirtualLabClassStatistics(this);
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
	public VirtualLabClassStatistics toEscapedModel() {
		return (VirtualLabClassStatistics)ProxyUtil.newProxyInstance(VirtualLabClassStatistics.class.getClassLoader(),
			new Class[] { VirtualLabClassStatistics.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		VirtualLabClassStatisticsClp clone = new VirtualLabClassStatisticsClp();

		clone.setVirtualLabId(getVirtualLabId());
		clone.setClassId(getClassId());
		clone.setVirtualLabTitle(getVirtualLabTitle());
		clone.setClassTitle(getClassTitle());
		clone.setVirtualLabPersonName(getVirtualLabPersonName());
		clone.setRegisterStudentCnt(getRegisterStudentCnt());
		clone.setVirtualLabUsersId(getVirtualLabUsersId());
		clone.setScienceAppId(getScienceAppId());
		clone.setExecuteUserCnt(getExecuteUserCnt());
		clone.setExecuteCnt(getExecuteCnt());
		clone.setCputime(getCputime());
		clone.setGroupId(getGroupId());
		clone.setUniversity(getUniversity());
		clone.setClassCreateDt(getClassCreateDt());
		clone.setVirtualLabUseYn(getVirtualLabUseYn());
		clone.setClassUseYn(getClassUseYn());
		clone.setLastModifiedDt(getLastModifiedDt());

		return clone;
	}

	@Override
	public int compareTo(VirtualLabClassStatistics virtualLabClassStatistics) {
		int value = 0;

		value = DateUtil.compareTo(getClassCreateDt(),
				virtualLabClassStatistics.getClassCreateDt());

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

		if (!(obj instanceof VirtualLabClassStatisticsClp)) {
			return false;
		}

		VirtualLabClassStatisticsClp virtualLabClassStatistics = (VirtualLabClassStatisticsClp)obj;

		VirtualLabClassStatisticsPK primaryKey = virtualLabClassStatistics.getPrimaryKey();

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
		StringBundler sb = new StringBundler(35);

		sb.append("{virtualLabId=");
		sb.append(getVirtualLabId());
		sb.append(", classId=");
		sb.append(getClassId());
		sb.append(", virtualLabTitle=");
		sb.append(getVirtualLabTitle());
		sb.append(", classTitle=");
		sb.append(getClassTitle());
		sb.append(", virtualLabPersonName=");
		sb.append(getVirtualLabPersonName());
		sb.append(", registerStudentCnt=");
		sb.append(getRegisterStudentCnt());
		sb.append(", virtualLabUsersId=");
		sb.append(getVirtualLabUsersId());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", executeUserCnt=");
		sb.append(getExecuteUserCnt());
		sb.append(", executeCnt=");
		sb.append(getExecuteCnt());
		sb.append(", cputime=");
		sb.append(getCputime());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", university=");
		sb.append(getUniversity());
		sb.append(", classCreateDt=");
		sb.append(getClassCreateDt());
		sb.append(", virtualLabUseYn=");
		sb.append(getVirtualLabUseYn());
		sb.append(", classUseYn=");
		sb.append(getClassUseYn());
		sb.append(", lastModifiedDt=");
		sb.append(getLastModifiedDt());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>virtualLabId</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classId</column-name><column-value><![CDATA[");
		sb.append(getClassId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabTitle</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classTitle</column-name><column-value><![CDATA[");
		sb.append(getClassTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabPersonName</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabPersonName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registerStudentCnt</column-name><column-value><![CDATA[");
		sb.append(getRegisterStudentCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabUsersId</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabUsersId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeUserCnt</column-name><column-value><![CDATA[");
		sb.append(getExecuteUserCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executeCnt</column-name><column-value><![CDATA[");
		sb.append(getExecuteCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cputime</column-name><column-value><![CDATA[");
		sb.append(getCputime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>university</column-name><column-value><![CDATA[");
		sb.append(getUniversity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classCreateDt</column-name><column-value><![CDATA[");
		sb.append(getClassCreateDt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabUseYn</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabUseYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classUseYn</column-name><column-value><![CDATA[");
		sb.append(getClassUseYn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastModifiedDt</column-name><column-value><![CDATA[");
		sb.append(getLastModifiedDt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _virtualLabId;
	private String _classId;
	private String _virtualLabTitle;
	private String _virtualLabTitleCurrentLanguageId;
	private String _classTitle;
	private String _classTitleCurrentLanguageId;
	private String _virtualLabPersonName;
	private long _registerStudentCnt;
	private String _virtualLabUsersId;
	private String _scienceAppId;
	private long _executeUserCnt;
	private long _executeCnt;
	private String _cputime;
	private long _groupId;
	private long _university;
	private Date _classCreateDt;
	private String _virtualLabUseYn;
	private String _classUseYn;
	private Date _lastModifiedDt;
	private BaseModel<?> _virtualLabClassStatisticsRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.bestsimulation.service.ClpSerializer.class;
}