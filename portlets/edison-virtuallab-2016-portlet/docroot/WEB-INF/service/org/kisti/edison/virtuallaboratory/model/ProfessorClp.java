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
import com.liferay.portal.util.PortalUtil;

import org.kisti.edison.virtuallaboratory.service.ClpSerializer;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author EDISON
 */
public class ProfessorClp extends BaseModelImpl<Professor> implements Professor {
	public ProfessorClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Professor.class;
	}

	@Override
	public String getModelClassName() {
		return Professor.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _professorSeq;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProfessorSeq(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _professorSeq;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("professorSeq", getProfessorSeq());
		attributes.put("userId", getUserId());
		attributes.put("record", getRecord());
		attributes.put("paper", getPaper());
		attributes.put("homepage", getHomepage());
		attributes.put("introduce", getIntroduce());
		attributes.put("phone", getPhone());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long professorSeq = (Long)attributes.get("professorSeq");

		if (professorSeq != null) {
			setProfessorSeq(professorSeq);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String record = (String)attributes.get("record");

		if (record != null) {
			setRecord(record);
		}

		String paper = (String)attributes.get("paper");

		if (paper != null) {
			setPaper(paper);
		}

		String homepage = (String)attributes.get("homepage");

		if (homepage != null) {
			setHomepage(homepage);
		}

		String introduce = (String)attributes.get("introduce");

		if (introduce != null) {
			setIntroduce(introduce);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}
	}

	@Override
	public long getProfessorSeq() {
		return _professorSeq;
	}

	@Override
	public void setProfessorSeq(long professorSeq) {
		_professorSeq = professorSeq;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setProfessorSeq", long.class);

				method.invoke(_professorRemoteModel, professorSeq);
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

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_professorRemoteModel, userId);
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
	public String getRecord() {
		return _record;
	}

	@Override
	public String getRecord(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getRecord(languageId);
	}

	@Override
	public String getRecord(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getRecord(languageId, useDefault);
	}

	@Override
	public String getRecord(String languageId) {
		return LocalizationUtil.getLocalization(getRecord(), languageId);
	}

	@Override
	public String getRecord(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getRecord(), languageId,
			useDefault);
	}

	@Override
	public String getRecordCurrentLanguageId() {
		return _recordCurrentLanguageId;
	}

	@Override
	public String getRecordCurrentValue() {
		Locale locale = getLocale(_recordCurrentLanguageId);

		return getRecord(locale);
	}

	@Override
	public Map<Locale, String> getRecordMap() {
		return LocalizationUtil.getLocalizationMap(getRecord());
	}

	@Override
	public void setRecord(String record) {
		_record = record;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setRecord", String.class);

				method.invoke(_professorRemoteModel, record);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setRecord(String record, Locale locale) {
		setRecord(record, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setRecord(String record, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(record)) {
			setRecord(LocalizationUtil.updateLocalization(getRecord(),
					"Record", record, languageId, defaultLanguageId));
		}
		else {
			setRecord(LocalizationUtil.removeLocalization(getRecord(),
					"Record", languageId));
		}
	}

	@Override
	public void setRecordCurrentLanguageId(String languageId) {
		_recordCurrentLanguageId = languageId;
	}

	@Override
	public void setRecordMap(Map<Locale, String> recordMap) {
		setRecordMap(recordMap, LocaleUtil.getDefault());
	}

	@Override
	public void setRecordMap(Map<Locale, String> recordMap, Locale defaultLocale) {
		if (recordMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setRecord(LocalizationUtil.updateLocalization(recordMap,
					getRecord(), "Record",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPaper() {
		return _paper;
	}

	@Override
	public String getPaper(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaper(languageId);
	}

	@Override
	public String getPaper(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPaper(languageId, useDefault);
	}

	@Override
	public String getPaper(String languageId) {
		return LocalizationUtil.getLocalization(getPaper(), languageId);
	}

	@Override
	public String getPaper(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getPaper(), languageId,
			useDefault);
	}

	@Override
	public String getPaperCurrentLanguageId() {
		return _paperCurrentLanguageId;
	}

	@Override
	public String getPaperCurrentValue() {
		Locale locale = getLocale(_paperCurrentLanguageId);

		return getPaper(locale);
	}

	@Override
	public Map<Locale, String> getPaperMap() {
		return LocalizationUtil.getLocalizationMap(getPaper());
	}

	@Override
	public void setPaper(String paper) {
		_paper = paper;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setPaper", String.class);

				method.invoke(_professorRemoteModel, paper);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setPaper(String paper, Locale locale) {
		setPaper(paper, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setPaper(String paper, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(paper)) {
			setPaper(LocalizationUtil.updateLocalization(getPaper(), "Paper",
					paper, languageId, defaultLanguageId));
		}
		else {
			setPaper(LocalizationUtil.removeLocalization(getPaper(), "Paper",
					languageId));
		}
	}

	@Override
	public void setPaperCurrentLanguageId(String languageId) {
		_paperCurrentLanguageId = languageId;
	}

	@Override
	public void setPaperMap(Map<Locale, String> paperMap) {
		setPaperMap(paperMap, LocaleUtil.getDefault());
	}

	@Override
	public void setPaperMap(Map<Locale, String> paperMap, Locale defaultLocale) {
		if (paperMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setPaper(LocalizationUtil.updateLocalization(paperMap, getPaper(),
					"Paper", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getHomepage() {
		return _homepage;
	}

	@Override
	public void setHomepage(String homepage) {
		_homepage = homepage;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setHomepage", String.class);

				method.invoke(_professorRemoteModel, homepage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getIntroduce() {
		return _introduce;
	}

	@Override
	public String getIntroduce(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getIntroduce(languageId);
	}

	@Override
	public String getIntroduce(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getIntroduce(languageId, useDefault);
	}

	@Override
	public String getIntroduce(String languageId) {
		return LocalizationUtil.getLocalization(getIntroduce(), languageId);
	}

	@Override
	public String getIntroduce(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getIntroduce(), languageId,
			useDefault);
	}

	@Override
	public String getIntroduceCurrentLanguageId() {
		return _introduceCurrentLanguageId;
	}

	@Override
	public String getIntroduceCurrentValue() {
		Locale locale = getLocale(_introduceCurrentLanguageId);

		return getIntroduce(locale);
	}

	@Override
	public Map<Locale, String> getIntroduceMap() {
		return LocalizationUtil.getLocalizationMap(getIntroduce());
	}

	@Override
	public void setIntroduce(String introduce) {
		_introduce = introduce;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setIntroduce", String.class);

				method.invoke(_professorRemoteModel, introduce);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setIntroduce(String introduce, Locale locale) {
		setIntroduce(introduce, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setIntroduce(String introduce, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(introduce)) {
			setIntroduce(LocalizationUtil.updateLocalization(getIntroduce(),
					"Introduce", introduce, languageId, defaultLanguageId));
		}
		else {
			setIntroduce(LocalizationUtil.removeLocalization(getIntroduce(),
					"Introduce", languageId));
		}
	}

	@Override
	public void setIntroduceCurrentLanguageId(String languageId) {
		_introduceCurrentLanguageId = languageId;
	}

	@Override
	public void setIntroduceMap(Map<Locale, String> introduceMap) {
		setIntroduceMap(introduceMap, LocaleUtil.getDefault());
	}

	@Override
	public void setIntroduceMap(Map<Locale, String> introduceMap,
		Locale defaultLocale) {
		if (introduceMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setIntroduce(LocalizationUtil.updateLocalization(introduceMap,
					getIntroduce(), "Introduce",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPhone() {
		return _phone;
	}

	@Override
	public void setPhone(String phone) {
		_phone = phone;

		if (_professorRemoteModel != null) {
			try {
				Class<?> clazz = _professorRemoteModel.getClass();

				Method method = clazz.getMethod("setPhone", String.class);

				method.invoke(_professorRemoteModel, phone);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getProfessorRemoteModel() {
		return _professorRemoteModel;
	}

	public void setProfessorRemoteModel(BaseModel<?> professorRemoteModel) {
		_professorRemoteModel = professorRemoteModel;
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

		Class<?> remoteModelClass = _professorRemoteModel.getClass();

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

		Object returnValue = method.invoke(_professorRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ProfessorLocalServiceUtil.addProfessor(this);
		}
		else {
			ProfessorLocalServiceUtil.updateProfessor(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> recordMap = getRecordMap();

		for (Map.Entry<Locale, String> entry : recordMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> paperMap = getPaperMap();

		for (Map.Entry<Locale, String> entry : paperMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> introduceMap = getIntroduceMap();

		for (Map.Entry<Locale, String> entry : introduceMap.entrySet()) {
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
		String xml = getRecord();

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

		String record = getRecord(defaultLocale);

		if (Validator.isNull(record)) {
			setRecord(getRecord(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setRecord(getRecord(defaultLocale), defaultLocale, defaultLocale);
		}

		String paper = getPaper(defaultLocale);

		if (Validator.isNull(paper)) {
			setPaper(getPaper(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setPaper(getPaper(defaultLocale), defaultLocale, defaultLocale);
		}

		String introduce = getIntroduce(defaultLocale);

		if (Validator.isNull(introduce)) {
			setIntroduce(getIntroduce(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setIntroduce(getIntroduce(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public Professor toEscapedModel() {
		return (Professor)ProxyUtil.newProxyInstance(Professor.class.getClassLoader(),
			new Class[] { Professor.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ProfessorClp clone = new ProfessorClp();

		clone.setProfessorSeq(getProfessorSeq());
		clone.setUserId(getUserId());
		clone.setRecord(getRecord());
		clone.setPaper(getPaper());
		clone.setHomepage(getHomepage());
		clone.setIntroduce(getIntroduce());
		clone.setPhone(getPhone());

		return clone;
	}

	@Override
	public int compareTo(Professor professor) {
		long primaryKey = professor.getPrimaryKey();

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

		if (!(obj instanceof ProfessorClp)) {
			return false;
		}

		ProfessorClp professor = (ProfessorClp)obj;

		long primaryKey = professor.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{professorSeq=");
		sb.append(getProfessorSeq());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", record=");
		sb.append(getRecord());
		sb.append(", paper=");
		sb.append(getPaper());
		sb.append(", homepage=");
		sb.append(getHomepage());
		sb.append(", introduce=");
		sb.append(getIntroduce());
		sb.append(", phone=");
		sb.append(getPhone());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.virtuallaboratory.model.Professor");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>professorSeq</column-name><column-value><![CDATA[");
		sb.append(getProfessorSeq());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>record</column-name><column-value><![CDATA[");
		sb.append(getRecord());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paper</column-name><column-value><![CDATA[");
		sb.append(getPaper());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>homepage</column-name><column-value><![CDATA[");
		sb.append(getHomepage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>introduce</column-name><column-value><![CDATA[");
		sb.append(getIntroduce());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phone</column-name><column-value><![CDATA[");
		sb.append(getPhone());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _professorSeq;
	private long _userId;
	private String _userUuid;
	private String _record;
	private String _recordCurrentLanguageId;
	private String _paper;
	private String _paperCurrentLanguageId;
	private String _homepage;
	private String _introduce;
	private String _introduceCurrentLanguageId;
	private String _phone;
	private BaseModel<?> _professorRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.virtuallaboratory.service.ClpSerializer.class;
}