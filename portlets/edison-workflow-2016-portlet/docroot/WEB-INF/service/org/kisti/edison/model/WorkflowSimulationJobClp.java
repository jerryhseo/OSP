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

import org.kisti.edison.service.ClpSerializer;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;

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
public class WorkflowSimulationJobClp extends BaseModelImpl<WorkflowSimulationJob>
	implements WorkflowSimulationJob {
	public WorkflowSimulationJobClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowSimulationJob.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowSimulationJob.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _simulationJobId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSimulationJobId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _simulationJobId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simulationJobId", getSimulationJobId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("status", getStatus());
		attributes.put("statusResponse", getStatusResponse());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("workflowUUID", getWorkflowUUID());
		attributes.put("reuseWorkflowUUID", getReuseWorkflowUUID());
		attributes.put("screenLogic", getScreenLogic());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simulationJobId = (Long)attributes.get("simulationJobId");

		if (simulationJobId != null) {
			setSimulationJobId(simulationJobId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String statusResponse = (String)attributes.get("statusResponse");

		if (statusResponse != null) {
			setStatusResponse(statusResponse);
		}

		Date startTime = (Date)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		Date endTime = (Date)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		Long workflowId = (Long)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		String workflowUUID = (String)attributes.get("workflowUUID");

		if (workflowUUID != null) {
			setWorkflowUUID(workflowUUID);
		}

		String reuseWorkflowUUID = (String)attributes.get("reuseWorkflowUUID");

		if (reuseWorkflowUUID != null) {
			setReuseWorkflowUUID(reuseWorkflowUUID);
		}

		String screenLogic = (String)attributes.get("screenLogic");

		if (screenLogic != null) {
			setScreenLogic(screenLogic);
		}
	}

	@Override
	public long getSimulationJobId() {
		return _simulationJobId;
	}

	@Override
	public void setSimulationJobId(long simulationJobId) {
		_simulationJobId = simulationJobId;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationJobId", long.class);

				method.invoke(_workflowSimulationJobRemoteModel, simulationJobId);
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

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_workflowSimulationJobRemoteModel, groupId);
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

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_workflowSimulationJobRemoteModel, userId);
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
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_workflowSimulationJobRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_workflowSimulationJobRemoteModel, modifiedDate);
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

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_workflowSimulationJobRemoteModel, title);
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
	public String getStatus() {
		return _status;
	}

	@Override
	public void setStatus(String status) {
		_status = status;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", String.class);

				method.invoke(_workflowSimulationJobRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusResponse() {
		return _statusResponse;
	}

	@Override
	public void setStatusResponse(String statusResponse) {
		_statusResponse = statusResponse;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusResponse",
						String.class);

				method.invoke(_workflowSimulationJobRemoteModel, statusResponse);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartTime() {
		return _startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		_startTime = startTime;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setStartTime", Date.class);

				method.invoke(_workflowSimulationJobRemoteModel, startTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEndTime() {
		return _endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		_endTime = endTime;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setEndTime", Date.class);

				method.invoke(_workflowSimulationJobRemoteModel, endTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWorkflowId() {
		return _workflowId;
	}

	@Override
	public void setWorkflowId(long workflowId) {
		_workflowId = workflowId;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowId", long.class);

				method.invoke(_workflowSimulationJobRemoteModel, workflowId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWorkflowUUID() {
		return _workflowUUID;
	}

	@Override
	public void setWorkflowUUID(String workflowUUID) {
		_workflowUUID = workflowUUID;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowUUID", String.class);

				method.invoke(_workflowSimulationJobRemoteModel, workflowUUID);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReuseWorkflowUUID() {
		return _reuseWorkflowUUID;
	}

	@Override
	public void setReuseWorkflowUUID(String reuseWorkflowUUID) {
		_reuseWorkflowUUID = reuseWorkflowUUID;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setReuseWorkflowUUID",
						String.class);

				method.invoke(_workflowSimulationJobRemoteModel,
					reuseWorkflowUUID);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScreenLogic() {
		return _screenLogic;
	}

	@Override
	public void setScreenLogic(String screenLogic) {
		_screenLogic = screenLogic;

		if (_workflowSimulationJobRemoteModel != null) {
			try {
				Class<?> clazz = _workflowSimulationJobRemoteModel.getClass();

				Method method = clazz.getMethod("setScreenLogic", String.class);

				method.invoke(_workflowSimulationJobRemoteModel, screenLogic);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWorkflowSimulationJobRemoteModel() {
		return _workflowSimulationJobRemoteModel;
	}

	public void setWorkflowSimulationJobRemoteModel(
		BaseModel<?> workflowSimulationJobRemoteModel) {
		_workflowSimulationJobRemoteModel = workflowSimulationJobRemoteModel;
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

		Class<?> remoteModelClass = _workflowSimulationJobRemoteModel.getClass();

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

		Object returnValue = method.invoke(_workflowSimulationJobRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationJob(this);
		}
		else {
			WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob(this);
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
	}

	@Override
	public WorkflowSimulationJob toEscapedModel() {
		return (WorkflowSimulationJob)ProxyUtil.newProxyInstance(WorkflowSimulationJob.class.getClassLoader(),
			new Class[] { WorkflowSimulationJob.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WorkflowSimulationJobClp clone = new WorkflowSimulationJobClp();

		clone.setSimulationJobId(getSimulationJobId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTitle(getTitle());
		clone.setStatus(getStatus());
		clone.setStatusResponse(getStatusResponse());
		clone.setStartTime(getStartTime());
		clone.setEndTime(getEndTime());
		clone.setWorkflowId(getWorkflowId());
		clone.setWorkflowUUID(getWorkflowUUID());
		clone.setReuseWorkflowUUID(getReuseWorkflowUUID());
		clone.setScreenLogic(getScreenLogic());

		return clone;
	}

	@Override
	public int compareTo(WorkflowSimulationJob workflowSimulationJob) {
		int value = 0;

		if (getSimulationJobId() < workflowSimulationJob.getSimulationJobId()) {
			value = -1;
		}
		else if (getSimulationJobId() > workflowSimulationJob.getSimulationJobId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof WorkflowSimulationJobClp)) {
			return false;
		}

		WorkflowSimulationJobClp workflowSimulationJob = (WorkflowSimulationJobClp)obj;

		long primaryKey = workflowSimulationJob.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{simulationJobId=");
		sb.append(getSimulationJobId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusResponse=");
		sb.append(getStatusResponse());
		sb.append(", startTime=");
		sb.append(getStartTime());
		sb.append(", endTime=");
		sb.append(getEndTime());
		sb.append(", workflowId=");
		sb.append(getWorkflowId());
		sb.append(", workflowUUID=");
		sb.append(getWorkflowUUID());
		sb.append(", reuseWorkflowUUID=");
		sb.append(getReuseWorkflowUUID());
		sb.append(", screenLogic=");
		sb.append(getScreenLogic());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.WorkflowSimulationJob");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>simulationJobId</column-name><column-value><![CDATA[");
		sb.append(getSimulationJobId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusResponse</column-name><column-value><![CDATA[");
		sb.append(getStatusResponse());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startTime</column-name><column-value><![CDATA[");
		sb.append(getStartTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endTime</column-name><column-value><![CDATA[");
		sb.append(getEndTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowUUID</column-name><column-value><![CDATA[");
		sb.append(getWorkflowUUID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reuseWorkflowUUID</column-name><column-value><![CDATA[");
		sb.append(getReuseWorkflowUUID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>screenLogic</column-name><column-value><![CDATA[");
		sb.append(getScreenLogic());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _simulationJobId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _status;
	private String _statusResponse;
	private Date _startTime;
	private Date _endTime;
	private long _workflowId;
	private String _workflowUUID;
	private String _reuseWorkflowUUID;
	private String _screenLogic;
	private BaseModel<?> _workflowSimulationJobRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.service.ClpSerializer.class;
}