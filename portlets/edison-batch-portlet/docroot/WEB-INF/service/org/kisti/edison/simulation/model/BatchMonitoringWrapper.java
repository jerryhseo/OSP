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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BatchMonitoring}.
 * </p>
 *
 * @author edison
 * @see BatchMonitoring
 * @generated
 */
public class BatchMonitoringWrapper implements BatchMonitoring,
	ModelWrapper<BatchMonitoring> {
	public BatchMonitoringWrapper(BatchMonitoring batchMonitoring) {
		_batchMonitoring = batchMonitoring;
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

	/**
	* Returns the primary key of this batch monitoring.
	*
	* @return the primary key of this batch monitoring
	*/
	@Override
	public long getPrimaryKey() {
		return _batchMonitoring.getPrimaryKey();
	}

	/**
	* Sets the primary key of this batch monitoring.
	*
	* @param primaryKey the primary key of this batch monitoring
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_batchMonitoring.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the bat seq no of this batch monitoring.
	*
	* @return the bat seq no of this batch monitoring
	*/
	@Override
	public long getBatSeqNo() {
		return _batchMonitoring.getBatSeqNo();
	}

	/**
	* Sets the bat seq no of this batch monitoring.
	*
	* @param batSeqNo the bat seq no of this batch monitoring
	*/
	@Override
	public void setBatSeqNo(long batSeqNo) {
		_batchMonitoring.setBatSeqNo(batSeqNo);
	}

	/**
	* Returns the bat div of this batch monitoring.
	*
	* @return the bat div of this batch monitoring
	*/
	@Override
	public java.lang.String getBatDiv() {
		return _batchMonitoring.getBatDiv();
	}

	/**
	* Sets the bat div of this batch monitoring.
	*
	* @param batDiv the bat div of this batch monitoring
	*/
	@Override
	public void setBatDiv(java.lang.String batDiv) {
		_batchMonitoring.setBatDiv(batDiv);
	}

	/**
	* Returns the manual y n of this batch monitoring.
	*
	* @return the manual y n of this batch monitoring
	*/
	@Override
	public java.lang.String getManualYN() {
		return _batchMonitoring.getManualYN();
	}

	/**
	* Sets the manual y n of this batch monitoring.
	*
	* @param manualYN the manual y n of this batch monitoring
	*/
	@Override
	public void setManualYN(java.lang.String manualYN) {
		_batchMonitoring.setManualYN(manualYN);
	}

	/**
	* Returns the status of this batch monitoring.
	*
	* @return the status of this batch monitoring
	*/
	@Override
	public java.lang.String getStatus() {
		return _batchMonitoring.getStatus();
	}

	/**
	* Sets the status of this batch monitoring.
	*
	* @param status the status of this batch monitoring
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_batchMonitoring.setStatus(status);
	}

	/**
	* Returns the message of this batch monitoring.
	*
	* @return the message of this batch monitoring
	*/
	@Override
	public java.lang.String getMessage() {
		return _batchMonitoring.getMessage();
	}

	/**
	* Returns the localized message of this batch monitoring in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized message of this batch monitoring
	*/
	@Override
	public java.lang.String getMessage(java.util.Locale locale) {
		return _batchMonitoring.getMessage(locale);
	}

	/**
	* Returns the localized message of this batch monitoring in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized message of this batch monitoring. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getMessage(java.util.Locale locale,
		boolean useDefault) {
		return _batchMonitoring.getMessage(locale, useDefault);
	}

	/**
	* Returns the localized message of this batch monitoring in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized message of this batch monitoring
	*/
	@Override
	public java.lang.String getMessage(java.lang.String languageId) {
		return _batchMonitoring.getMessage(languageId);
	}

	/**
	* Returns the localized message of this batch monitoring in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized message of this batch monitoring
	*/
	@Override
	public java.lang.String getMessage(java.lang.String languageId,
		boolean useDefault) {
		return _batchMonitoring.getMessage(languageId, useDefault);
	}

	@Override
	public java.lang.String getMessageCurrentLanguageId() {
		return _batchMonitoring.getMessageCurrentLanguageId();
	}

	@Override
	public java.lang.String getMessageCurrentValue() {
		return _batchMonitoring.getMessageCurrentValue();
	}

	/**
	* Returns a map of the locales and localized messages of this batch monitoring.
	*
	* @return the locales and localized messages of this batch monitoring
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getMessageMap() {
		return _batchMonitoring.getMessageMap();
	}

	/**
	* Sets the message of this batch monitoring.
	*
	* @param message the message of this batch monitoring
	*/
	@Override
	public void setMessage(java.lang.String message) {
		_batchMonitoring.setMessage(message);
	}

	/**
	* Sets the localized message of this batch monitoring in the language.
	*
	* @param message the localized message of this batch monitoring
	* @param locale the locale of the language
	*/
	@Override
	public void setMessage(java.lang.String message, java.util.Locale locale) {
		_batchMonitoring.setMessage(message, locale);
	}

	/**
	* Sets the localized message of this batch monitoring in the language, and sets the default locale.
	*
	* @param message the localized message of this batch monitoring
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMessage(java.lang.String message, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_batchMonitoring.setMessage(message, locale, defaultLocale);
	}

	@Override
	public void setMessageCurrentLanguageId(java.lang.String languageId) {
		_batchMonitoring.setMessageCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized messages of this batch monitoring from the map of locales and localized messages.
	*
	* @param messageMap the locales and localized messages of this batch monitoring
	*/
	@Override
	public void setMessageMap(
		java.util.Map<java.util.Locale, java.lang.String> messageMap) {
		_batchMonitoring.setMessageMap(messageMap);
	}

	/**
	* Sets the localized messages of this batch monitoring from the map of locales and localized messages, and sets the default locale.
	*
	* @param messageMap the locales and localized messages of this batch monitoring
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMessageMap(
		java.util.Map<java.util.Locale, java.lang.String> messageMap,
		java.util.Locale defaultLocale) {
		_batchMonitoring.setMessageMap(messageMap, defaultLocale);
	}

	/**
	* Returns the insert ID of this batch monitoring.
	*
	* @return the insert ID of this batch monitoring
	*/
	@Override
	public long getInsertId() {
		return _batchMonitoring.getInsertId();
	}

	/**
	* Sets the insert ID of this batch monitoring.
	*
	* @param insertId the insert ID of this batch monitoring
	*/
	@Override
	public void setInsertId(long insertId) {
		_batchMonitoring.setInsertId(insertId);
	}

	/**
	* Returns the exe date of this batch monitoring.
	*
	* @return the exe date of this batch monitoring
	*/
	@Override
	public java.util.Date getExeDate() {
		return _batchMonitoring.getExeDate();
	}

	/**
	* Sets the exe date of this batch monitoring.
	*
	* @param exeDate the exe date of this batch monitoring
	*/
	@Override
	public void setExeDate(java.util.Date exeDate) {
		_batchMonitoring.setExeDate(exeDate);
	}

	@Override
	public boolean isNew() {
		return _batchMonitoring.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_batchMonitoring.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _batchMonitoring.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_batchMonitoring.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _batchMonitoring.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _batchMonitoring.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_batchMonitoring.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _batchMonitoring.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_batchMonitoring.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_batchMonitoring.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_batchMonitoring.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _batchMonitoring.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _batchMonitoring.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_batchMonitoring.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_batchMonitoring.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new BatchMonitoringWrapper((BatchMonitoring)_batchMonitoring.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.simulation.model.BatchMonitoring batchMonitoring) {
		return _batchMonitoring.compareTo(batchMonitoring);
	}

	@Override
	public int hashCode() {
		return _batchMonitoring.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.simulation.model.BatchMonitoring> toCacheModel() {
		return _batchMonitoring.toCacheModel();
	}

	@Override
	public org.kisti.edison.simulation.model.BatchMonitoring toEscapedModel() {
		return new BatchMonitoringWrapper(_batchMonitoring.toEscapedModel());
	}

	@Override
	public org.kisti.edison.simulation.model.BatchMonitoring toUnescapedModel() {
		return new BatchMonitoringWrapper(_batchMonitoring.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _batchMonitoring.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _batchMonitoring.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_batchMonitoring.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BatchMonitoringWrapper)) {
			return false;
		}

		BatchMonitoringWrapper batchMonitoringWrapper = (BatchMonitoringWrapper)obj;

		if (Validator.equals(_batchMonitoring,
					batchMonitoringWrapper._batchMonitoring)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public BatchMonitoring getWrappedBatchMonitoring() {
		return _batchMonitoring;
	}

	@Override
	public BatchMonitoring getWrappedModel() {
		return _batchMonitoring;
	}

	@Override
	public void resetOriginalValues() {
		_batchMonitoring.resetOriginalValues();
	}

	private BatchMonitoring _batchMonitoring;
}