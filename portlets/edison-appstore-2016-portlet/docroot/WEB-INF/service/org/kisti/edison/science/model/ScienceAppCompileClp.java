/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.science.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import org.kisti.edison.science.service.ClpSerializer;
import org.kisti.edison.science.service.ScienceAppCompileLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class ScienceAppCompileClp extends BaseModelImpl<ScienceAppCompile>
	implements ScienceAppCompile {
	public ScienceAppCompileClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppCompile.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppCompile.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("userId", getUserId());
		attributes.put("compileUrl", getCompileUrl());
		attributes.put("result", getResult());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String compileUrl = (String)attributes.get("compileUrl");

		if (compileUrl != null) {
			setCompileUrl(compileUrl);
		}

		String result = (String)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_scienceAppCompileRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppCompileRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_scienceAppCompileRemoteModel, scienceAppId);
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

		if (_scienceAppCompileRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppCompileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_scienceAppCompileRemoteModel, userId);
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
	public String getCompileUrl() {
		return _compileUrl;
	}

	@Override
	public void setCompileUrl(String compileUrl) {
		_compileUrl = compileUrl;

		if (_scienceAppCompileRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppCompileRemoteModel.getClass();

				Method method = clazz.getMethod("setCompileUrl", String.class);

				method.invoke(_scienceAppCompileRemoteModel, compileUrl);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getResult() {
		return _result;
	}

	@Override
	public void setResult(String result) {
		_result = result;

		if (_scienceAppCompileRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppCompileRemoteModel.getClass();

				Method method = clazz.getMethod("setResult", String.class);

				method.invoke(_scienceAppCompileRemoteModel, result);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_scienceAppCompileRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppCompileRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_scienceAppCompileRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getScienceAppCompileRemoteModel() {
		return _scienceAppCompileRemoteModel;
	}

	public void setScienceAppCompileRemoteModel(
		BaseModel<?> scienceAppCompileRemoteModel) {
		_scienceAppCompileRemoteModel = scienceAppCompileRemoteModel;
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

		Class<?> remoteModelClass = _scienceAppCompileRemoteModel.getClass();

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

		Object returnValue = method.invoke(_scienceAppCompileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ScienceAppCompileLocalServiceUtil.addScienceAppCompile(this);
		}
		else {
			ScienceAppCompileLocalServiceUtil.updateScienceAppCompile(this);
		}
	}

	@Override
	public ScienceAppCompile toEscapedModel() {
		return (ScienceAppCompile)ProxyUtil.newProxyInstance(ScienceAppCompile.class.getClassLoader(),
			new Class[] { ScienceAppCompile.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ScienceAppCompileClp clone = new ScienceAppCompileClp();

		clone.setScienceAppId(getScienceAppId());
		clone.setUserId(getUserId());
		clone.setCompileUrl(getCompileUrl());
		clone.setResult(getResult());
		clone.setCreateDate(getCreateDate());

		return clone;
	}

	@Override
	public int compareTo(ScienceAppCompile scienceAppCompile) {
		long primaryKey = scienceAppCompile.getPrimaryKey();

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

		if (!(obj instanceof ScienceAppCompileClp)) {
			return false;
		}

		ScienceAppCompileClp scienceAppCompile = (ScienceAppCompileClp)obj;

		long primaryKey = scienceAppCompile.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", compileUrl=");
		sb.append(getCompileUrl());
		sb.append(", result=");
		sb.append(getResult());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.science.model.ScienceAppCompile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>compileUrl</column-name><column-value><![CDATA[");
		sb.append(getCompileUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>result</column-name><column-value><![CDATA[");
		sb.append(getResult());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppId;
	private long _userId;
	private String _userUuid;
	private String _compileUrl;
	private String _result;
	private Date _createDate;
	private BaseModel<?> _scienceAppCompileRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.science.service.ClpSerializer.class;
}