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

package org.kisti.edison.simulation.model;

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

import org.kisti.edison.simulation.service.BatchMonitoringLocalServiceUtil;
import org.kisti.edison.simulation.service.ClpSerializer;

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
public class BatchMonitoringClp extends BaseModelImpl<BatchMonitoring>
	implements BatchMonitoring {
	public BatchMonitoringClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return BatchMonitoring.class;
	}

	@Override
	public String getModelClassName() {
		return BatchMonitoring.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _batSeqNo;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBatSeqNo(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _batSeqNo;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("batSeqNo", getBatSeqNo());
		attributes.put("batDiv", getBatDiv());
		attributes.put("manualYN", getManualYN());
		attributes.put("status", getStatus());
		attributes.put("message", getMessage());
		attributes.put("insertId", getInsertId());
		attributes.put("exeDate", getExeDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long batSeqNo = (Long)attributes.get("batSeqNo");

		if (batSeqNo != null) {
			setBatSeqNo(batSeqNo);
		}

		String batDiv = (String)attributes.get("batDiv");

		if (batDiv != null) {
			setBatDiv(batDiv);
		}

		String manualYN = (String)attributes.get("manualYN");

		if (manualYN != null) {
			setManualYN(manualYN);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Long insertId = (Long)attributes.get("insertId");

		if (insertId != null) {
			setInsertId(insertId);
		}

		Date exeDate = (Date)attributes.get("exeDate");

		if (exeDate != null) {
			setExeDate(exeDate);
		}
	}

	@Override
	public long getBatSeqNo() {
		return _batSeqNo;
	}

	@Override
	public void setBatSeqNo(long batSeqNo) {
		_batSeqNo = batSeqNo;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setBatSeqNo", long.class);

				method.invoke(_batchMonitoringRemoteModel, batSeqNo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getBatDiv() {
		return _batDiv;
	}

	@Override
	public void setBatDiv(String batDiv) {
		_batDiv = batDiv;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setBatDiv", String.class);

				method.invoke(_batchMonitoringRemoteModel, batDiv);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getManualYN() {
		return _manualYN;
	}

	@Override
	public void setManualYN(String manualYN) {
		_manualYN = manualYN;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setManualYN", String.class);

				method.invoke(_batchMonitoringRemoteModel, manualYN);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatus() {
		return _status;
	}

	@Override
	public void setStatus(String status) {
		_status = status;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", String.class);

				method.invoke(_batchMonitoringRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMessage() {
		return _message;
	}

	@Override
	public String getMessage(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMessage(languageId);
	}

	@Override
	public String getMessage(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMessage(languageId, useDefault);
	}

	@Override
	public String getMessage(String languageId) {
		return LocalizationUtil.getLocalization(getMessage(), languageId);
	}

	@Override
	public String getMessage(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getMessage(), languageId,
			useDefault);
	}

	@Override
	public String getMessageCurrentLanguageId() {
		return _messageCurrentLanguageId;
	}

	@Override
	public String getMessageCurrentValue() {
		Locale locale = getLocale(_messageCurrentLanguageId);

		return getMessage(locale);
	}

	@Override
	public Map<Locale, String> getMessageMap() {
		return LocalizationUtil.getLocalizationMap(getMessage());
	}

	@Override
	public void setMessage(String message) {
		_message = message;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setMessage", String.class);

				method.invoke(_batchMonitoringRemoteModel, message);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setMessage(String message, Locale locale) {
		setMessage(message, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setMessage(String message, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(message)) {
			setMessage(LocalizationUtil.updateLocalization(getMessage(),
					"Message", message, languageId, defaultLanguageId));
		}
		else {
			setMessage(LocalizationUtil.removeLocalization(getMessage(),
					"Message", languageId));
		}
	}

	@Override
	public void setMessageCurrentLanguageId(String languageId) {
		_messageCurrentLanguageId = languageId;
	}

	@Override
	public void setMessageMap(Map<Locale, String> messageMap) {
		setMessageMap(messageMap, LocaleUtil.getDefault());
	}

	@Override
	public void setMessageMap(Map<Locale, String> messageMap,
		Locale defaultLocale) {
		if (messageMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setMessage(LocalizationUtil.updateLocalization(messageMap,
					getMessage(), "Message",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
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

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setInsertId", long.class);

				method.invoke(_batchMonitoringRemoteModel, insertId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getExeDate() {
		return _exeDate;
	}

	@Override
	public void setExeDate(Date exeDate) {
		_exeDate = exeDate;

		if (_batchMonitoringRemoteModel != null) {
			try {
				Class<?> clazz = _batchMonitoringRemoteModel.getClass();

				Method method = clazz.getMethod("setExeDate", Date.class);

				method.invoke(_batchMonitoringRemoteModel, exeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBatchMonitoringRemoteModel() {
		return _batchMonitoringRemoteModel;
	}

	public void setBatchMonitoringRemoteModel(
		BaseModel<?> batchMonitoringRemoteModel) {
		_batchMonitoringRemoteModel = batchMonitoringRemoteModel;
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

		Class<?> remoteModelClass = _batchMonitoringRemoteModel.getClass();

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

		Object returnValue = method.invoke(_batchMonitoringRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			BatchMonitoringLocalServiceUtil.addBatchMonitoring(this);
		}
		else {
			BatchMonitoringLocalServiceUtil.updateBatchMonitoring(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> messageMap = getMessageMap();

		for (Map.Entry<Locale, String> entry : messageMap.entrySet()) {
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
		String xml = getMessage();

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

		String message = getMessage(defaultLocale);

		if (Validator.isNull(message)) {
			setMessage(getMessage(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setMessage(getMessage(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public BatchMonitoring toEscapedModel() {
		return (BatchMonitoring)ProxyUtil.newProxyInstance(BatchMonitoring.class.getClassLoader(),
			new Class[] { BatchMonitoring.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BatchMonitoringClp clone = new BatchMonitoringClp();

		clone.setBatSeqNo(getBatSeqNo());
		clone.setBatDiv(getBatDiv());
		clone.setManualYN(getManualYN());
		clone.setStatus(getStatus());
		clone.setMessage(getMessage());
		clone.setInsertId(getInsertId());
		clone.setExeDate(getExeDate());

		return clone;
	}

	@Override
	public int compareTo(BatchMonitoring batchMonitoring) {
		long primaryKey = batchMonitoring.getPrimaryKey();

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

		if (!(obj instanceof BatchMonitoringClp)) {
			return false;
		}

		BatchMonitoringClp batchMonitoring = (BatchMonitoringClp)obj;

		long primaryKey = batchMonitoring.getPrimaryKey();

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

		sb.append("{batSeqNo=");
		sb.append(getBatSeqNo());
		sb.append(", batDiv=");
		sb.append(getBatDiv());
		sb.append(", manualYN=");
		sb.append(getManualYN());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append(", insertId=");
		sb.append(getInsertId());
		sb.append(", exeDate=");
		sb.append(getExeDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.simulation.model.BatchMonitoring");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>batSeqNo</column-name><column-value><![CDATA[");
		sb.append(getBatSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batDiv</column-name><column-value><![CDATA[");
		sb.append(getBatDiv());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>manualYN</column-name><column-value><![CDATA[");
		sb.append(getManualYN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insertId</column-name><column-value><![CDATA[");
		sb.append(getInsertId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exeDate</column-name><column-value><![CDATA[");
		sb.append(getExeDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _batSeqNo;
	private String _batDiv;
	private String _manualYN;
	private String _status;
	private String _message;
	private String _messageCurrentLanguageId;
	private long _insertId;
	private Date _exeDate;
	private BaseModel<?> _batchMonitoringRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.simulation.service.ClpSerializer.class;
}