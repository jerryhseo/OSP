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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.bestsimulation.service.ClpSerializer;
import org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class ScienceAppExecuteClp extends BaseModelImpl<ScienceAppExecute>
	implements ScienceAppExecute {
	public ScienceAppExecuteClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppExecute.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppExecute.class.getName();
	}

	@Override
	public ScienceAppExecutePK getPrimaryKey() {
		return new ScienceAppExecutePK(_executeDate, _scienceAppId);
	}

	@Override
	public void setPrimaryKey(ScienceAppExecutePK primaryKey) {
		setExecuteDate(primaryKey.executeDate);
		setScienceAppId(primaryKey.scienceAppId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ScienceAppExecutePK(_executeDate, _scienceAppId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ScienceAppExecutePK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("executeDate", getExecuteDate());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("userCnt", getUserCnt());
		attributes.put("avgExeTime", getAvgExeTime());
		attributes.put("exeCnt", getExeCnt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String executeDate = (String)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long userCnt = (Long)attributes.get("userCnt");

		if (userCnt != null) {
			setUserCnt(userCnt);
		}

		Long avgExeTime = (Long)attributes.get("avgExeTime");

		if (avgExeTime != null) {
			setAvgExeTime(avgExeTime);
		}

		Long exeCnt = (Long)attributes.get("exeCnt");

		if (exeCnt != null) {
			setExeCnt(exeCnt);
		}
	}

	@Override
	public String getExecuteDate() {
		return _executeDate;
	}

	@Override
	public void setExecuteDate(String executeDate) {
		_executeDate = executeDate;

		if (_scienceAppExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDate", String.class);

				method.invoke(_scienceAppExecuteRemoteModel, executeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_scienceAppExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_scienceAppExecuteRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserCnt() {
		return _userCnt;
	}

	@Override
	public void setUserCnt(long userCnt) {
		_userCnt = userCnt;

		if (_scienceAppExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserCnt", long.class);

				method.invoke(_scienceAppExecuteRemoteModel, userCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAvgExeTime() {
		return _avgExeTime;
	}

	@Override
	public void setAvgExeTime(long avgExeTime) {
		_avgExeTime = avgExeTime;

		if (_scienceAppExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setAvgExeTime", long.class);

				method.invoke(_scienceAppExecuteRemoteModel, avgExeTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getExeCnt() {
		return _exeCnt;
	}

	@Override
	public void setExeCnt(long exeCnt) {
		_exeCnt = exeCnt;

		if (_scienceAppExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setExeCnt", long.class);

				method.invoke(_scienceAppExecuteRemoteModel, exeCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getScienceAppExecuteRemoteModel() {
		return _scienceAppExecuteRemoteModel;
	}

	public void setScienceAppExecuteRemoteModel(
		BaseModel<?> scienceAppExecuteRemoteModel) {
		_scienceAppExecuteRemoteModel = scienceAppExecuteRemoteModel;
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

		Class<?> remoteModelClass = _scienceAppExecuteRemoteModel.getClass();

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

		Object returnValue = method.invoke(_scienceAppExecuteRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ScienceAppExecuteLocalServiceUtil.addScienceAppExecute(this);
		}
		else {
			ScienceAppExecuteLocalServiceUtil.updateScienceAppExecute(this);
		}
	}

	@Override
	public ScienceAppExecute toEscapedModel() {
		return (ScienceAppExecute)ProxyUtil.newProxyInstance(ScienceAppExecute.class.getClassLoader(),
			new Class[] { ScienceAppExecute.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ScienceAppExecuteClp clone = new ScienceAppExecuteClp();

		clone.setExecuteDate(getExecuteDate());
		clone.setScienceAppId(getScienceAppId());
		clone.setUserCnt(getUserCnt());
		clone.setAvgExeTime(getAvgExeTime());
		clone.setExeCnt(getExeCnt());

		return clone;
	}

	@Override
	public int compareTo(ScienceAppExecute scienceAppExecute) {
		ScienceAppExecutePK primaryKey = scienceAppExecute.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppExecuteClp)) {
			return false;
		}

		ScienceAppExecuteClp scienceAppExecute = (ScienceAppExecuteClp)obj;

		ScienceAppExecutePK primaryKey = scienceAppExecute.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{executeDate=");
		sb.append(getExecuteDate());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", userCnt=");
		sb.append(getUserCnt());
		sb.append(", avgExeTime=");
		sb.append(getAvgExeTime());
		sb.append(", exeCnt=");
		sb.append(getExeCnt());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.bestsimulation.model.ScienceAppExecute");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>executeDate</column-name><column-value><![CDATA[");
		sb.append(getExecuteDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userCnt</column-name><column-value><![CDATA[");
		sb.append(getUserCnt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>avgExeTime</column-name><column-value><![CDATA[");
		sb.append(getAvgExeTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exeCnt</column-name><column-value><![CDATA[");
		sb.append(getExeCnt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _executeDate;
	private long _scienceAppId;
	private long _userCnt;
	private long _avgExeTime;
	private long _exeCnt;
	private BaseModel<?> _scienceAppExecuteRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.bestsimulation.service.ClpSerializer.class;
}