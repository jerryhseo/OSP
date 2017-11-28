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

import edison.challenge.service.builder.service.AwardLocalServiceUtil;
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
public class AwardClp extends BaseModelImpl<Award> implements Award {
	public AwardClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Award.class;
	}

	@Override
	public String getModelClassName() {
		return Award.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _awardid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAwardid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _awardid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("awardid", getAwardid());
		attributes.put("awardGradeName", getAwardGradeName());
		attributes.put("awardName", getAwardName());
		attributes.put("prize", getPrize());
		attributes.put("number", getNumber());
		attributes.put("childid", getChildid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long awardid = (Long)attributes.get("awardid");

		if (awardid != null) {
			setAwardid(awardid);
		}

		String awardGradeName = (String)attributes.get("awardGradeName");

		if (awardGradeName != null) {
			setAwardGradeName(awardGradeName);
		}

		String awardName = (String)attributes.get("awardName");

		if (awardName != null) {
			setAwardName(awardName);
		}

		String prize = (String)attributes.get("prize");

		if (prize != null) {
			setPrize(prize);
		}

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
		}
	}

	@Override
	public long getAwardid() {
		return _awardid;
	}

	@Override
	public void setAwardid(long awardid) {
		_awardid = awardid;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setAwardid", long.class);

				method.invoke(_awardRemoteModel, awardid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAwardGradeName() {
		return _awardGradeName;
	}

	@Override
	public String getAwardGradeName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getAwardGradeName(languageId);
	}

	@Override
	public String getAwardGradeName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getAwardGradeName(languageId, useDefault);
	}

	@Override
	public String getAwardGradeName(String languageId) {
		return LocalizationUtil.getLocalization(getAwardGradeName(), languageId);
	}

	@Override
	public String getAwardGradeName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getAwardGradeName(),
			languageId, useDefault);
	}

	@Override
	public String getAwardGradeNameCurrentLanguageId() {
		return _awardGradeNameCurrentLanguageId;
	}

	@Override
	public String getAwardGradeNameCurrentValue() {
		Locale locale = getLocale(_awardGradeNameCurrentLanguageId);

		return getAwardGradeName(locale);
	}

	@Override
	public Map<Locale, String> getAwardGradeNameMap() {
		return LocalizationUtil.getLocalizationMap(getAwardGradeName());
	}

	@Override
	public void setAwardGradeName(String awardGradeName) {
		_awardGradeName = awardGradeName;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setAwardGradeName",
						String.class);

				method.invoke(_awardRemoteModel, awardGradeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setAwardGradeName(String awardGradeName, Locale locale) {
		setAwardGradeName(awardGradeName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setAwardGradeName(String awardGradeName, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(awardGradeName)) {
			setAwardGradeName(LocalizationUtil.updateLocalization(
					getAwardGradeName(), "AwardGradeName", awardGradeName,
					languageId, defaultLanguageId));
		}
		else {
			setAwardGradeName(LocalizationUtil.removeLocalization(
					getAwardGradeName(), "AwardGradeName", languageId));
		}
	}

	@Override
	public void setAwardGradeNameCurrentLanguageId(String languageId) {
		_awardGradeNameCurrentLanguageId = languageId;
	}

	@Override
	public void setAwardGradeNameMap(Map<Locale, String> awardGradeNameMap) {
		setAwardGradeNameMap(awardGradeNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setAwardGradeNameMap(Map<Locale, String> awardGradeNameMap,
		Locale defaultLocale) {
		if (awardGradeNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setAwardGradeName(LocalizationUtil.updateLocalization(
					awardGradeNameMap, getAwardGradeName(), "AwardGradeName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getAwardName() {
		return _awardName;
	}

	@Override
	public String getAwardName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getAwardName(languageId);
	}

	@Override
	public String getAwardName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getAwardName(languageId, useDefault);
	}

	@Override
	public String getAwardName(String languageId) {
		return LocalizationUtil.getLocalization(getAwardName(), languageId);
	}

	@Override
	public String getAwardName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getAwardName(), languageId,
			useDefault);
	}

	@Override
	public String getAwardNameCurrentLanguageId() {
		return _awardNameCurrentLanguageId;
	}

	@Override
	public String getAwardNameCurrentValue() {
		Locale locale = getLocale(_awardNameCurrentLanguageId);

		return getAwardName(locale);
	}

	@Override
	public Map<Locale, String> getAwardNameMap() {
		return LocalizationUtil.getLocalizationMap(getAwardName());
	}

	@Override
	public void setAwardName(String awardName) {
		_awardName = awardName;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setAwardName", String.class);

				method.invoke(_awardRemoteModel, awardName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setAwardName(String awardName, Locale locale) {
		setAwardName(awardName, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setAwardName(String awardName, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(awardName)) {
			setAwardName(LocalizationUtil.updateLocalization(getAwardName(),
					"AwardName", awardName, languageId, defaultLanguageId));
		}
		else {
			setAwardName(LocalizationUtil.removeLocalization(getAwardName(),
					"AwardName", languageId));
		}
	}

	@Override
	public void setAwardNameCurrentLanguageId(String languageId) {
		_awardNameCurrentLanguageId = languageId;
	}

	@Override
	public void setAwardNameMap(Map<Locale, String> awardNameMap) {
		setAwardNameMap(awardNameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setAwardNameMap(Map<Locale, String> awardNameMap,
		Locale defaultLocale) {
		if (awardNameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setAwardName(LocalizationUtil.updateLocalization(awardNameMap,
					getAwardName(), "AwardName",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPrize() {
		return _prize;
	}

	@Override
	public String getPrize(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPrize(languageId);
	}

	@Override
	public String getPrize(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getPrize(languageId, useDefault);
	}

	@Override
	public String getPrize(String languageId) {
		return LocalizationUtil.getLocalization(getPrize(), languageId);
	}

	@Override
	public String getPrize(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getPrize(), languageId,
			useDefault);
	}

	@Override
	public String getPrizeCurrentLanguageId() {
		return _prizeCurrentLanguageId;
	}

	@Override
	public String getPrizeCurrentValue() {
		Locale locale = getLocale(_prizeCurrentLanguageId);

		return getPrize(locale);
	}

	@Override
	public Map<Locale, String> getPrizeMap() {
		return LocalizationUtil.getLocalizationMap(getPrize());
	}

	@Override
	public void setPrize(String prize) {
		_prize = prize;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setPrize", String.class);

				method.invoke(_awardRemoteModel, prize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setPrize(String prize, Locale locale) {
		setPrize(prize, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setPrize(String prize, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(prize)) {
			setPrize(LocalizationUtil.updateLocalization(getPrize(), "Prize",
					prize, languageId, defaultLanguageId));
		}
		else {
			setPrize(LocalizationUtil.removeLocalization(getPrize(), "Prize",
					languageId));
		}
	}

	@Override
	public void setPrizeCurrentLanguageId(String languageId) {
		_prizeCurrentLanguageId = languageId;
	}

	@Override
	public void setPrizeMap(Map<Locale, String> prizeMap) {
		setPrizeMap(prizeMap, LocaleUtil.getDefault());
	}

	@Override
	public void setPrizeMap(Map<Locale, String> prizeMap, Locale defaultLocale) {
		if (prizeMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setPrize(LocalizationUtil.updateLocalization(prizeMap, getPrize(),
					"Prize", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getNumber() {
		return _number;
	}

	@Override
	public void setNumber(String number) {
		_number = number;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setNumber", String.class);

				method.invoke(_awardRemoteModel, number);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChildid() {
		return _childid;
	}

	@Override
	public void setChildid(long childid) {
		_childid = childid;

		if (_awardRemoteModel != null) {
			try {
				Class<?> clazz = _awardRemoteModel.getClass();

				Method method = clazz.getMethod("setChildid", long.class);

				method.invoke(_awardRemoteModel, childid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAwardRemoteModel() {
		return _awardRemoteModel;
	}

	public void setAwardRemoteModel(BaseModel<?> awardRemoteModel) {
		_awardRemoteModel = awardRemoteModel;
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

		Class<?> remoteModelClass = _awardRemoteModel.getClass();

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

		Object returnValue = method.invoke(_awardRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			AwardLocalServiceUtil.addAward(this);
		}
		else {
			AwardLocalServiceUtil.updateAward(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> awardGradeNameMap = getAwardGradeNameMap();

		for (Map.Entry<Locale, String> entry : awardGradeNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> awardNameMap = getAwardNameMap();

		for (Map.Entry<Locale, String> entry : awardNameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> prizeMap = getPrizeMap();

		for (Map.Entry<Locale, String> entry : prizeMap.entrySet()) {
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
		String xml = getAwardGradeName();

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

		String awardGradeName = getAwardGradeName(defaultLocale);

		if (Validator.isNull(awardGradeName)) {
			setAwardGradeName(getAwardGradeName(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setAwardGradeName(getAwardGradeName(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String awardName = getAwardName(defaultLocale);

		if (Validator.isNull(awardName)) {
			setAwardName(getAwardName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setAwardName(getAwardName(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String prize = getPrize(defaultLocale);

		if (Validator.isNull(prize)) {
			setPrize(getPrize(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setPrize(getPrize(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Award toEscapedModel() {
		return (Award)ProxyUtil.newProxyInstance(Award.class.getClassLoader(),
			new Class[] { Award.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AwardClp clone = new AwardClp();

		clone.setAwardid(getAwardid());
		clone.setAwardGradeName(getAwardGradeName());
		clone.setAwardName(getAwardName());
		clone.setPrize(getPrize());
		clone.setNumber(getNumber());
		clone.setChildid(getChildid());

		return clone;
	}

	@Override
	public int compareTo(Award award) {
		int value = 0;

		if (getChildid() < award.getChildid()) {
			value = -1;
		}
		else if (getChildid() > award.getChildid()) {
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

		if (!(obj instanceof AwardClp)) {
			return false;
		}

		AwardClp award = (AwardClp)obj;

		long primaryKey = award.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{awardid=");
		sb.append(getAwardid());
		sb.append(", awardGradeName=");
		sb.append(getAwardGradeName());
		sb.append(", awardName=");
		sb.append(getAwardName());
		sb.append(", prize=");
		sb.append(getPrize());
		sb.append(", number=");
		sb.append(getNumber());
		sb.append(", childid=");
		sb.append(getChildid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.Award");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>awardid</column-name><column-value><![CDATA[");
		sb.append(getAwardid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>awardGradeName</column-name><column-value><![CDATA[");
		sb.append(getAwardGradeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>awardName</column-name><column-value><![CDATA[");
		sb.append(getAwardName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>prize</column-name><column-value><![CDATA[");
		sb.append(getPrize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>number</column-name><column-value><![CDATA[");
		sb.append(getNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>childid</column-name><column-value><![CDATA[");
		sb.append(getChildid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _awardid;
	private String _awardGradeName;
	private String _awardGradeNameCurrentLanguageId;
	private String _awardName;
	private String _awardNameCurrentLanguageId;
	private String _prize;
	private String _prizeCurrentLanguageId;
	private String _number;
	private long _childid;
	private BaseModel<?> _awardRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}