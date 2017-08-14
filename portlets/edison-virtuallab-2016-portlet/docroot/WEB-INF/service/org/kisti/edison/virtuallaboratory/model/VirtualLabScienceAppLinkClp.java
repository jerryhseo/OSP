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
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class VirtualLabScienceAppLinkClp extends BaseModelImpl<VirtualLabScienceAppLink>
	implements VirtualLabScienceAppLink {
	public VirtualLabScienceAppLinkClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabScienceAppLink.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabScienceAppLink.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppSeqNo(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppSeqNo", getScienceAppSeqNo());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("virtualLabId", getVirtualLabId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppSeqNo = (Long)attributes.get("scienceAppSeqNo");

		if (scienceAppSeqNo != null) {
			setScienceAppSeqNo(scienceAppSeqNo);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}
	}

	@Override
	public long getScienceAppSeqNo() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_scienceAppSeqNo = scienceAppSeqNo;

		if (_virtualLabScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppSeqNo", long.class);

				method.invoke(_virtualLabScienceAppLinkRemoteModel,
					scienceAppSeqNo);
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

		if (_virtualLabScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_virtualLabScienceAppLinkRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getVirtualLabId() {
		return _virtualLabId;
	}

	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabId = virtualLabId;

		if (_virtualLabScienceAppLinkRemoteModel != null) {
			try {
				Class<?> clazz = _virtualLabScienceAppLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setVirtualLabId", long.class);

				method.invoke(_virtualLabScienceAppLinkRemoteModel, virtualLabId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getVirtualLabScienceAppLinkRemoteModel() {
		return _virtualLabScienceAppLinkRemoteModel;
	}

	public void setVirtualLabScienceAppLinkRemoteModel(
		BaseModel<?> virtualLabScienceAppLinkRemoteModel) {
		_virtualLabScienceAppLinkRemoteModel = virtualLabScienceAppLinkRemoteModel;
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

		Class<?> remoteModelClass = _virtualLabScienceAppLinkRemoteModel.getClass();

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

		Object returnValue = method.invoke(_virtualLabScienceAppLinkRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			VirtualLabScienceAppLinkLocalServiceUtil.addVirtualLabScienceAppLink(this);
		}
		else {
			VirtualLabScienceAppLinkLocalServiceUtil.updateVirtualLabScienceAppLink(this);
		}
	}

	@Override
	public VirtualLabScienceAppLink toEscapedModel() {
		return (VirtualLabScienceAppLink)ProxyUtil.newProxyInstance(VirtualLabScienceAppLink.class.getClassLoader(),
			new Class[] { VirtualLabScienceAppLink.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		VirtualLabScienceAppLinkClp clone = new VirtualLabScienceAppLinkClp();

		clone.setScienceAppSeqNo(getScienceAppSeqNo());
		clone.setScienceAppId(getScienceAppId());
		clone.setVirtualLabId(getVirtualLabId());

		return clone;
	}

	@Override
	public int compareTo(VirtualLabScienceAppLink virtualLabScienceAppLink) {
		long primaryKey = virtualLabScienceAppLink.getPrimaryKey();

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

		if (!(obj instanceof VirtualLabScienceAppLinkClp)) {
			return false;
		}

		VirtualLabScienceAppLinkClp virtualLabScienceAppLink = (VirtualLabScienceAppLinkClp)obj;

		long primaryKey = virtualLabScienceAppLink.getPrimaryKey();

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

		sb.append("{scienceAppSeqNo=");
		sb.append(getScienceAppSeqNo());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", virtualLabId=");
		sb.append(getVirtualLabId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppSeqNo</column-name><column-value><![CDATA[");
		sb.append(getScienceAppSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabId</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppSeqNo;
	private long _scienceAppId;
	private long _virtualLabId;
	private BaseModel<?> _virtualLabScienceAppLinkRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.virtuallaboratory.service.ClpSerializer.class;
}