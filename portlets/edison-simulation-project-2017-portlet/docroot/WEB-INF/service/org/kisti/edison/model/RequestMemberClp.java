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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.service.ClpSerializer;
import org.kisti.edison.service.RequestMemberLocalServiceUtil;
import org.kisti.edison.service.persistence.RequestMemberPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author edison
 */
public class RequestMemberClp extends BaseModelImpl<RequestMember>
	implements RequestMember {
	public RequestMemberClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return RequestMember.class;
	}

	@Override
	public String getModelClassName() {
		return RequestMember.class.getName();
	}

	@Override
	public RequestMemberPK getPrimaryKey() {
		return new RequestMemberPK(_requestSeq, _simulationProjectId);
	}

	@Override
	public void setPrimaryKey(RequestMemberPK primaryKey) {
		setRequestSeq(primaryKey.requestSeq);
		setSimulationProjectId(primaryKey.simulationProjectId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new RequestMemberPK(_requestSeq, _simulationProjectId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((RequestMemberPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("requestSeq", getRequestSeq());
		attributes.put("simulationProjectId", getSimulationProjectId());
		attributes.put("userId", getUserId());
		attributes.put("requestDate", getRequestDate());
		attributes.put("processDate", getProcessDate());
		attributes.put("requestState", getRequestState());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long requestSeq = (Long)attributes.get("requestSeq");

		if (requestSeq != null) {
			setRequestSeq(requestSeq);
		}

		Long simulationProjectId = (Long)attributes.get("simulationProjectId");

		if (simulationProjectId != null) {
			setSimulationProjectId(simulationProjectId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date requestDate = (Date)attributes.get("requestDate");

		if (requestDate != null) {
			setRequestDate(requestDate);
		}

		Date processDate = (Date)attributes.get("processDate");

		if (processDate != null) {
			setProcessDate(processDate);
		}

		String requestState = (String)attributes.get("requestState");

		if (requestState != null) {
			setRequestState(requestState);
		}
	}

	@Override
	public long getRequestSeq() {
		return _requestSeq;
	}

	@Override
	public void setRequestSeq(long requestSeq) {
		_requestSeq = requestSeq;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestSeq", long.class);

				method.invoke(_requestMemberRemoteModel, requestSeq);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSimulationProjectId() {
		return _simulationProjectId;
	}

	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simulationProjectId = simulationProjectId;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationProjectId",
						long.class);

				method.invoke(_requestMemberRemoteModel, simulationProjectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(Long userId) {
		_userId = userId;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", Long.class);

				method.invoke(_requestMemberRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getRequestDate() {
		return _requestDate;
	}

	@Override
	public void setRequestDate(Date requestDate) {
		_requestDate = requestDate;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestDate", Date.class);

				method.invoke(_requestMemberRemoteModel, requestDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getProcessDate() {
		return _processDate;
	}

	@Override
	public void setProcessDate(Date processDate) {
		_processDate = processDate;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setProcessDate", Date.class);

				method.invoke(_requestMemberRemoteModel, processDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRequestState() {
		return _requestState;
	}

	@Override
	public void setRequestState(String requestState) {
		_requestState = requestState;

		if (_requestMemberRemoteModel != null) {
			try {
				Class<?> clazz = _requestMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setRequestState", String.class);

				method.invoke(_requestMemberRemoteModel, requestState);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getRequestMemberRemoteModel() {
		return _requestMemberRemoteModel;
	}

	public void setRequestMemberRemoteModel(
		BaseModel<?> requestMemberRemoteModel) {
		_requestMemberRemoteModel = requestMemberRemoteModel;
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

		Class<?> remoteModelClass = _requestMemberRemoteModel.getClass();

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

		Object returnValue = method.invoke(_requestMemberRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			RequestMemberLocalServiceUtil.addRequestMember(this);
		}
		else {
			RequestMemberLocalServiceUtil.updateRequestMember(this);
		}
	}

	@Override
	public RequestMember toEscapedModel() {
		return (RequestMember)ProxyUtil.newProxyInstance(RequestMember.class.getClassLoader(),
			new Class[] { RequestMember.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		RequestMemberClp clone = new RequestMemberClp();

		clone.setRequestSeq(getRequestSeq());
		clone.setSimulationProjectId(getSimulationProjectId());
		clone.setUserId(getUserId());
		clone.setRequestDate(getRequestDate());
		clone.setProcessDate(getProcessDate());
		clone.setRequestState(getRequestState());

		return clone;
	}

	@Override
	public int compareTo(RequestMember requestMember) {
		RequestMemberPK primaryKey = requestMember.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RequestMemberClp)) {
			return false;
		}

		RequestMemberClp requestMember = (RequestMemberClp)obj;

		RequestMemberPK primaryKey = requestMember.getPrimaryKey();

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

		sb.append("{requestSeq=");
		sb.append(getRequestSeq());
		sb.append(", simulationProjectId=");
		sb.append(getSimulationProjectId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", requestDate=");
		sb.append(getRequestDate());
		sb.append(", processDate=");
		sb.append(getProcessDate());
		sb.append(", requestState=");
		sb.append(getRequestState());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.RequestMember");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>requestSeq</column-name><column-value><![CDATA[");
		sb.append(getRequestSeq());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationProjectId</column-name><column-value><![CDATA[");
		sb.append(getSimulationProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requestDate</column-name><column-value><![CDATA[");
		sb.append(getRequestDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>processDate</column-name><column-value><![CDATA[");
		sb.append(getProcessDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requestState</column-name><column-value><![CDATA[");
		sb.append(getRequestState());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _requestSeq;
	private long _simulationProjectId;
	private Long _userId;
	private Date _requestDate;
	private Date _processDate;
	private String _requestState;
	private BaseModel<?> _requestMemberRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.service.ClpSerializer.class;
}