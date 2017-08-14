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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.virtuallaboratory.service.ClpSerializer;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkClassLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class VirtualLabScienceAppLinkClassClp extends BaseModelImpl<VirtualLabScienceAppLinkClass>
	implements VirtualLabScienceAppLinkClass {
	public VirtualLabScienceAppLinkClassClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabScienceAppLinkClass.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabScienceAppLinkClass.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppClassSeqNo;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppClassSeqNo(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppClassSeqNo;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppClassSeqNo", getScienceAppClassSeqNo());
		attributes.put("classId", getClassId());
		attributes.put("scienceAppSeqNo", getScienceAppSeqNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppClassSeqNo = (Long)attributes.get("scienceAppClassSeqNo");

		if (scienceAppClassSeqNo != null) {
			setScienceAppClassSeqNo(scienceAppClassSeqNo);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long scienceAppSeqNo = (Long)attributes.get("scienceAppSeqNo");

		if (scienceAppSeqNo != null) {
			setScienceAppSeqNo(scienceAppSeqNo);
		}
	}

	@Override
	public long getScienceAppClassSeqNo() {
		return _scienceAppClassSeqNo;
	}

	@Override
	public void setScienceAppClassSeqNo(long scienceAppClassSeqNo) {
		_scienceAppClassSeqNo = scienceAppClassSeqNo;

		if (_virtualLabScienceAppLinkClassRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkClassRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppClassSeqNo",
						long.class);

				method.invoke(_virtualLabScienceAppLinkClassRemoteModel,
					scienceAppClassSeqNo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassId() {
		return _classId;
	}

	@Override
	public void setClassId(long classId) {
		_classId = classId;

		if (_virtualLabScienceAppLinkClassRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkClassRemoteModel.getClass();

				Method method = clazz.getMethod("setClassId", long.class);

				method.invoke(_virtualLabScienceAppLinkClassRemoteModel, classId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getScienceAppSeqNo() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_scienceAppSeqNo = scienceAppSeqNo;

		if (_virtualLabScienceAppLinkClassRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkClassRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppSeqNo", long.class);

				method.invoke(_virtualLabScienceAppLinkClassRemoteModel,
					scienceAppSeqNo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getVirtualLabScienceAppLinkClassRemoteModel() {
		return _virtualLabScienceAppLinkClassRemoteModel;
	}

	public void setVirtualLabScienceAppLinkClassRemoteModel(
		BaseModel<?> virtualLabScienceAppLinkClassRemoteModel) {
		_virtualLabScienceAppLinkClassRemoteModel = virtualLabScienceAppLinkClassRemoteModel;
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

		Class<?> remoteModelClass = _virtualLabScienceAppLinkClassRemoteModel.getClass();

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

		Object returnValue = method.invoke(_virtualLabScienceAppLinkClassRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			VirtualLabScienceAppLinkClassLocalServiceUtil.addVirtualLabScienceAppLinkClass(this);
		}
		else {
			VirtualLabScienceAppLinkClassLocalServiceUtil.updateVirtualLabScienceAppLinkClass(this);
		}
	}

	@Override
	public VirtualLabScienceAppLinkClass toEscapedModel() {
		return (VirtualLabScienceAppLinkClass)ProxyUtil.newProxyInstance(VirtualLabScienceAppLinkClass.class.getClassLoader(),
			new Class[] { VirtualLabScienceAppLinkClass.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		VirtualLabScienceAppLinkClassClp clone = new VirtualLabScienceAppLinkClassClp();

		clone.setScienceAppClassSeqNo(getScienceAppClassSeqNo());
		clone.setClassId(getClassId());
		clone.setScienceAppSeqNo(getScienceAppSeqNo());

		return clone;
	}

	@Override
	public int compareTo(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		long primaryKey = virtualLabScienceAppLinkClass.getPrimaryKey();

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

		if (!(obj instanceof VirtualLabScienceAppLinkClassClp)) {
			return false;
		}

		VirtualLabScienceAppLinkClassClp virtualLabScienceAppLinkClass = (VirtualLabScienceAppLinkClassClp)obj;

		long primaryKey = virtualLabScienceAppLinkClass.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{scienceAppClassSeqNo=");
		sb.append(getScienceAppClassSeqNo());
		sb.append(", classId=");
		sb.append(getClassId());
		sb.append(", scienceAppSeqNo=");
		sb.append(getScienceAppSeqNo());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppClassSeqNo</column-name><column-value><![CDATA[");
		sb.append(getScienceAppClassSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classId</column-name><column-value><![CDATA[");
		sb.append(getClassId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppSeqNo</column-name><column-value><![CDATA[");
		sb.append(getScienceAppSeqNo());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppClassSeqNo;
	private long _classId;
	private long _scienceAppSeqNo;
	private BaseModel<?> _virtualLabScienceAppLinkClassRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.virtuallaboratory.service.ClpSerializer.class;
}