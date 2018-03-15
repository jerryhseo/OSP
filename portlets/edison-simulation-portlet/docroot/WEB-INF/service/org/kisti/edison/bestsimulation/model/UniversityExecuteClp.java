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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.bestsimulation.service.ClpSerializer;
import org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class UniversityExecuteClp extends BaseModelImpl<UniversityExecute>
	implements UniversityExecute {
	public UniversityExecuteClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return UniversityExecute.class;
	}

	@Override
	public String getModelClassName() {
		return UniversityExecute.class.getName();
	}

	@Override
	public UniversityExecutePK getPrimaryKey() {
		return new UniversityExecutePK(_executeDate, _universityField);
	}

	@Override
	public void setPrimaryKey(UniversityExecutePK primaryKey) {
		setExecuteDate(primaryKey.executeDate);
		setUniversityField(primaryKey.universityField);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new UniversityExecutePK(_executeDate, _universityField);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((UniversityExecutePK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("executeDate", getExecuteDate());
		attributes.put("universityField", getUniversityField());
		attributes.put("userCnt", getUserCnt());
		attributes.put("avgExeTime", getAvgExeTime());
		attributes.put("exeCnt", getExeCnt());
		attributes.put("cpuTime", getCpuTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String executeDate = (String)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}

		Long universityField = (Long)attributes.get("universityField");

		if (universityField != null) {
			setUniversityField(universityField);
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

		Long cpuTime = (Long)attributes.get("cpuTime");

		if (cpuTime != null) {
			setCpuTime(cpuTime);
		}
	}

	@Override
	public String getExecuteDate() {
		return _executeDate;
	}

	@Override
	public void setExecuteDate(String executeDate) {
		_executeDate = executeDate;

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setExecuteDate", String.class);

				method.invoke(_universityExecuteRemoteModel, executeDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUniversityField() {
		return _universityField;
	}

	@Override
	public void setUniversityField(long universityField) {
		_universityField = universityField;

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setUniversityField", long.class);

				method.invoke(_universityExecuteRemoteModel, universityField);
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

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setUserCnt", long.class);

				method.invoke(_universityExecuteRemoteModel, userCnt);
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

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setAvgExeTime", long.class);

				method.invoke(_universityExecuteRemoteModel, avgExeTime);
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

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setExeCnt", long.class);

				method.invoke(_universityExecuteRemoteModel, exeCnt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCpuTime() {
		return _cpuTime;
	}

	@Override
	public void setCpuTime(long cpuTime) {
		_cpuTime = cpuTime;

		if (_universityExecuteRemoteModel != null) {
			try {
				Class<?> clazz = _universityExecuteRemoteModel.getClass();

				Method method = clazz.getMethod("setCpuTime", long.class);

				method.invoke(_universityExecuteRemoteModel, cpuTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getUniversityExecuteRemoteModel() {
		return _universityExecuteRemoteModel;
	}

	public void setUniversityExecuteRemoteModel(
		BaseModel<?> universityExecuteRemoteModel) {
		_universityExecuteRemoteModel = universityExecuteRemoteModel;
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

		Class<?> remoteModelClass = _universityExecuteRemoteModel.getClass();

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

		Object returnValue = method.invoke(_universityExecuteRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			UniversityExecuteLocalServiceUtil.addUniversityExecute(this);
		}
		else {
			UniversityExecuteLocalServiceUtil.updateUniversityExecute(this);
		}
	}

	@Override
	public UniversityExecute toEscapedModel() {
		return (UniversityExecute)ProxyUtil.newProxyInstance(UniversityExecute.class.getClassLoader(),
			new Class[] { UniversityExecute.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		UniversityExecuteClp clone = new UniversityExecuteClp();

		clone.setExecuteDate(getExecuteDate());
		clone.setUniversityField(getUniversityField());
		clone.setUserCnt(getUserCnt());
		clone.setAvgExeTime(getAvgExeTime());
		clone.setExeCnt(getExeCnt());
		clone.setCpuTime(getCpuTime());

		return clone;
	}

	@Override
	public int compareTo(UniversityExecute universityExecute) {
		UniversityExecutePK primaryKey = universityExecute.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UniversityExecuteClp)) {
			return false;
		}

		UniversityExecuteClp universityExecute = (UniversityExecuteClp)obj;

		UniversityExecutePK primaryKey = universityExecute.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{executeDate=");
		sb.append(getExecuteDate());
		sb.append(", universityField=");
		sb.append(getUniversityField());
		sb.append(", userCnt=");
		sb.append(getUserCnt());
		sb.append(", avgExeTime=");
		sb.append(getAvgExeTime());
		sb.append(", exeCnt=");
		sb.append(getExeCnt());
		sb.append(", cpuTime=");
		sb.append(getCpuTime());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.bestsimulation.model.UniversityExecute");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>executeDate</column-name><column-value><![CDATA[");
		sb.append(getExecuteDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>universityField</column-name><column-value><![CDATA[");
		sb.append(getUniversityField());
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
		sb.append(
			"<column><column-name>cpuTime</column-name><column-value><![CDATA[");
		sb.append(getCpuTime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _executeDate;
	private long _universityField;
	private long _userCnt;
	private long _avgExeTime;
	private long _exeCnt;
	private long _cpuTime;
	private BaseModel<?> _universityExecuteRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.bestsimulation.service.ClpSerializer.class;
}