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

package edison.challenge.service.builder.model;

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

import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author kyj
 */
public class ChallengeMemberClp extends BaseModelImpl<ChallengeMember>
	implements ChallengeMember {
	public ChallengeMemberClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeMember.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeMember.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _chMemberid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChMemberid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chMemberid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("chMemberid", getChMemberid());
		attributes.put("fullName", getFullName());
		attributes.put("screenName", getScreenName());
		attributes.put("email", getEmail());
		attributes.put("leader", getLeader());
		attributes.put("falculty", getFalculty());
		attributes.put("major", getMajor());
		attributes.put("grade", getGrade());
		attributes.put("chTeamid", getChTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long chMemberid = (Long)attributes.get("chMemberid");

		if (chMemberid != null) {
			setChMemberid(chMemberid);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Boolean leader = (Boolean)attributes.get("leader");

		if (leader != null) {
			setLeader(leader);
		}

		String falculty = (String)attributes.get("falculty");

		if (falculty != null) {
			setFalculty(falculty);
		}

		String major = (String)attributes.get("major");

		if (major != null) {
			setMajor(major);
		}

		String grade = (String)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}
	}

	@Override
	public long getChMemberid() {
		return _chMemberid;
	}

	@Override
	public void setChMemberid(long chMemberid) {
		_chMemberid = chMemberid;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setChMemberid", long.class);

				method.invoke(_challengeMemberRemoteModel, chMemberid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFullName() {
		return _fullName;
	}

	@Override
	public String getFullName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFullName(languageId);
	}

	@Override
	public String getFullName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFullName(languageId, useDefault);
	}

	@Override
	public String getFullName(String languageId) {
		return LocalizationUtil.getLocalization(getFullName(), languageId);
	}

	@Override
	public String getFullName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getFullName(), languageId,
			useDefault);
	}

	@Override
	public String getFullNameCurrentLanguageId() {
		return _fullNameCurrentLanguageId;
	}

	@Override
	public String getFullNameCurrentValue() {
		Locale locale = getLocale(_fullNameCurrentLanguageId);

		return getFullName(locale);
	}

	@Override
	public Map<Locale, String> getFullNameMap() {
		return LocalizationUtil.getLocalizationMap(getFullName());
	}

	@Override
	public void setFullName(String fullName) {
		_fullName = fullName;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setFullName", String.class);

				method.invoke(_challengeMemberRemoteModel, fullName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setFullName(String fullName, Locale locale) {
		setFullName(fullName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setFullName(String fullName, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(fullName)) {
			setFullName(LocalizationUtil.updateLocalization(getFullName(),
					"FullName", fullName, languageId, defaultLanguageId));
		}
		else {
			setFullName(LocalizationUtil.removeLocalization(getFullName(),
					"FullName", languageId));
		}
	}

	@Override
	public void setFullNameCurrentLanguageId(String languageId) {
		_fullNameCurrentLanguageId = languageId;
	}

	@Override
	public void setFullNameMap(Map<Locale, String> fullNameMap) {
		setFullNameMap(fullNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setFullNameMap(Map<Locale, String> fullNameMap,
		Locale defaultLocale) {
		if (fullNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setFullName(LocalizationUtil.updateLocalization(fullNameMap,
					getFullName(), "FullName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getScreenName() {
		return _screenName;
	}

	@Override
	public String getScreenName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getScreenName(languageId);
	}

	@Override
	public String getScreenName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getScreenName(languageId, useDefault);
	}

	@Override
	public String getScreenName(String languageId) {
		return LocalizationUtil.getLocalization(getScreenName(), languageId);
	}

	@Override
	public String getScreenName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getScreenName(), languageId,
			useDefault);
	}

	@Override
	public String getScreenNameCurrentLanguageId() {
		return _screenNameCurrentLanguageId;
	}

	@Override
	public String getScreenNameCurrentValue() {
		Locale locale = getLocale(_screenNameCurrentLanguageId);

		return getScreenName(locale);
	}

	@Override
	public Map<Locale, String> getScreenNameMap() {
		return LocalizationUtil.getLocalizationMap(getScreenName());
	}

	@Override
	public void setScreenName(String screenName) {
		_screenName = screenName;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setScreenName", String.class);

				method.invoke(_challengeMemberRemoteModel, screenName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setScreenName(String screenName, Locale locale) {
		setScreenName(screenName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setScreenName(String screenName, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(screenName)) {
			setScreenName(LocalizationUtil.updateLocalization(getScreenName(),
					"ScreenName", screenName, languageId, defaultLanguageId));
		}
		else {
			setScreenName(LocalizationUtil.removeLocalization(getScreenName(),
					"ScreenName", languageId));
		}
	}

	@Override
	public void setScreenNameCurrentLanguageId(String languageId) {
		_screenNameCurrentLanguageId = languageId;
	}

	@Override
	public void setScreenNameMap(Map<Locale, String> screenNameMap) {
		setScreenNameMap(screenNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setScreenNameMap(Map<Locale, String> screenNameMap,
		Locale defaultLocale) {
		if (screenNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setScreenName(LocalizationUtil.updateLocalization(screenNameMap,
					getScreenName(), "ScreenName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getEmail() {
		return _email;
	}

	@Override
	public void setEmail(String email) {
		_email = email;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setEmail", String.class);

				method.invoke(_challengeMemberRemoteModel, email);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getLeader() {
		return _leader;
	}

	@Override
	public boolean isLeader() {
		return _leader;
	}

	@Override
	public void setLeader(boolean leader) {
		_leader = leader;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setLeader", boolean.class);

				method.invoke(_challengeMemberRemoteModel, leader);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFalculty() {
		return _falculty;
	}

	@Override
	public String getFalculty(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFalculty(languageId);
	}

	@Override
	public String getFalculty(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFalculty(languageId, useDefault);
	}

	@Override
	public String getFalculty(String languageId) {
		return LocalizationUtil.getLocalization(getFalculty(), languageId);
	}

	@Override
	public String getFalculty(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getFalculty(), languageId,
			useDefault);
	}

	@Override
	public String getFalcultyCurrentLanguageId() {
		return _falcultyCurrentLanguageId;
	}

	@Override
	public String getFalcultyCurrentValue() {
		Locale locale = getLocale(_falcultyCurrentLanguageId);

		return getFalculty(locale);
	}

	@Override
	public Map<Locale, String> getFalcultyMap() {
		return LocalizationUtil.getLocalizationMap(getFalculty());
	}

	@Override
	public void setFalculty(String falculty) {
		_falculty = falculty;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setFalculty", String.class);

				method.invoke(_challengeMemberRemoteModel, falculty);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setFalculty(String falculty, Locale locale) {
		setFalculty(falculty, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setFalculty(String falculty, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(falculty)) {
			setFalculty(LocalizationUtil.updateLocalization(getFalculty(),
					"Falculty", falculty, languageId, defaultLanguageId));
		}
		else {
			setFalculty(LocalizationUtil.removeLocalization(getFalculty(),
					"Falculty", languageId));
		}
	}

	@Override
	public void setFalcultyCurrentLanguageId(String languageId) {
		_falcultyCurrentLanguageId = languageId;
	}

	@Override
	public void setFalcultyMap(Map<Locale, String> falcultyMap) {
		setFalcultyMap(falcultyMap, LocaleUtil.getDefault());
	}

	@Override
	public void setFalcultyMap(Map<Locale, String> falcultyMap,
		Locale defaultLocale) {
		if (falcultyMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setFalculty(LocalizationUtil.updateLocalization(falcultyMap,
					getFalculty(), "Falculty",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getMajor() {
		return _major;
	}

	@Override
	public String getMajor(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMajor(languageId);
	}

	@Override
	public String getMajor(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMajor(languageId, useDefault);
	}

	@Override
	public String getMajor(String languageId) {
		return LocalizationUtil.getLocalization(getMajor(), languageId);
	}

	@Override
	public String getMajor(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getMajor(), languageId,
			useDefault);
	}

	@Override
	public String getMajorCurrentLanguageId() {
		return _majorCurrentLanguageId;
	}

	@Override
	public String getMajorCurrentValue() {
		Locale locale = getLocale(_majorCurrentLanguageId);

		return getMajor(locale);
	}

	@Override
	public Map<Locale, String> getMajorMap() {
		return LocalizationUtil.getLocalizationMap(getMajor());
	}

	@Override
	public void setMajor(String major) {
		_major = major;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setMajor", String.class);

				method.invoke(_challengeMemberRemoteModel, major);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setMajor(String major, Locale locale) {
		setMajor(major, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setMajor(String major, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(major)) {
			setMajor(LocalizationUtil.updateLocalization(getMajor(), "Major",
					major, languageId, defaultLanguageId));
		}
		else {
			setMajor(LocalizationUtil.removeLocalization(getMajor(), "Major",
					languageId));
		}
	}

	@Override
	public void setMajorCurrentLanguageId(String languageId) {
		_majorCurrentLanguageId = languageId;
	}

	@Override
	public void setMajorMap(Map<Locale, String> majorMap) {
		setMajorMap(majorMap, LocaleUtil.getDefault());
	}

	@Override
	public void setMajorMap(Map<Locale, String> majorMap, Locale defaultLocale) {
		if (majorMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setMajor(LocalizationUtil.updateLocalization(majorMap, getMajor(),
					"Major", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getGrade() {
		return _grade;
	}

	@Override
	public void setGrade(String grade) {
		_grade = grade;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setGrade", String.class);

				method.invoke(_challengeMemberRemoteModel, grade);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChTeamid() {
		return _chTeamid;
	}

	@Override
	public void setChTeamid(long chTeamid) {
		_chTeamid = chTeamid;

		if (_challengeMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setChTeamid", long.class);

				method.invoke(_challengeMemberRemoteModel, chTeamid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getChallengeMemberRemoteModel() {
		return _challengeMemberRemoteModel;
	}

	public void setChallengeMemberRemoteModel(
		BaseModel<?> challengeMemberRemoteModel) {
		_challengeMemberRemoteModel = challengeMemberRemoteModel;
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

		Class<?> remoteModelClass = _challengeMemberRemoteModel.getClass();

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

		Object returnValue = method.invoke(_challengeMemberRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChallengeMemberLocalServiceUtil.addChallengeMember(this);
		}
		else {
			ChallengeMemberLocalServiceUtil.updateChallengeMember(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> fullNameMap = getFullNameMap();

		for (Map.Entry<Locale, String> entry : fullNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> screenNameMap = getScreenNameMap();

		for (Map.Entry<Locale, String> entry : screenNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> falcultyMap = getFalcultyMap();

		for (Map.Entry<Locale, String> entry : falcultyMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> majorMap = getMajorMap();

		for (Map.Entry<Locale, String> entry : majorMap.entrySet()) {
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
		String xml = getFullName();

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

		String fullName = getFullName(defaultLocale);

		if (Validator.isNull(fullName)) {
			setFullName(getFullName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setFullName(getFullName(defaultLocale), defaultLocale, defaultLocale);
		}

		String screenName = getScreenName(defaultLocale);

		if (Validator.isNull(screenName)) {
			setScreenName(getScreenName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setScreenName(getScreenName(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String falculty = getFalculty(defaultLocale);

		if (Validator.isNull(falculty)) {
			setFalculty(getFalculty(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setFalculty(getFalculty(defaultLocale), defaultLocale, defaultLocale);
		}

		String major = getMajor(defaultLocale);

		if (Validator.isNull(major)) {
			setMajor(getMajor(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setMajor(getMajor(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public ChallengeMember toEscapedModel() {
		return (ChallengeMember)ProxyUtil.newProxyInstance(ChallengeMember.class.getClassLoader(),
			new Class[] { ChallengeMember.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ChallengeMemberClp clone = new ChallengeMemberClp();

		clone.setChMemberid(getChMemberid());
		clone.setFullName(getFullName());
		clone.setScreenName(getScreenName());
		clone.setEmail(getEmail());
		clone.setLeader(getLeader());
		clone.setFalculty(getFalculty());
		clone.setMajor(getMajor());
		clone.setGrade(getGrade());
		clone.setChTeamid(getChTeamid());

		return clone;
	}

	@Override
	public int compareTo(ChallengeMember challengeMember) {
		int value = 0;

		if (getChTeamid() < challengeMember.getChTeamid()) {
			value = -1;
		}
		else if (getChTeamid() > challengeMember.getChTeamid()) {
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

		if (!(obj instanceof ChallengeMemberClp)) {
			return false;
		}

		ChallengeMemberClp challengeMember = (ChallengeMemberClp)obj;

		long primaryKey = challengeMember.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{chMemberid=");
		sb.append(getChMemberid());
		sb.append(", fullName=");
		sb.append(getFullName());
		sb.append(", screenName=");
		sb.append(getScreenName());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", leader=");
		sb.append(getLeader());
		sb.append(", falculty=");
		sb.append(getFalculty());
		sb.append(", major=");
		sb.append(getMajor());
		sb.append(", grade=");
		sb.append(getGrade());
		sb.append(", chTeamid=");
		sb.append(getChTeamid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.ChallengeMember");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>chMemberid</column-name><column-value><![CDATA[");
		sb.append(getChMemberid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fullName</column-name><column-value><![CDATA[");
		sb.append(getFullName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>screenName</column-name><column-value><![CDATA[");
		sb.append(getScreenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>leader</column-name><column-value><![CDATA[");
		sb.append(getLeader());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>falculty</column-name><column-value><![CDATA[");
		sb.append(getFalculty());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>major</column-name><column-value><![CDATA[");
		sb.append(getMajor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grade</column-name><column-value><![CDATA[");
		sb.append(getGrade());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>chTeamid</column-name><column-value><![CDATA[");
		sb.append(getChTeamid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _chMemberid;
	private String _fullName;
	private String _fullNameCurrentLanguageId;
	private String _screenName;
	private String _screenNameCurrentLanguageId;
	private String _email;
	private boolean _leader;
	private String _falculty;
	private String _falcultyCurrentLanguageId;
	private String _major;
	private String _majorCurrentLanguageId;
	private String _grade;
	private long _chTeamid;
	private BaseModel<?> _challengeMemberRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}