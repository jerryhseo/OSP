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
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class SimulationShareClp extends BaseModelImpl<SimulationShare>
	implements SimulationShare {
	public SimulationShareClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationShare.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationShare.class.getName();
	}

	@Override
	public SimulationSharePK getPrimaryKey() {
		return new SimulationSharePK(_shareSeqno, _jobSeqNo, _jobUuid,
			_simulationUuid);
	}

	@Override
	public void setPrimaryKey(SimulationSharePK primaryKey) {
		setShareSeqno(primaryKey.shareSeqno);
		setJobSeqNo(primaryKey.jobSeqNo);
		setJobUuid(primaryKey.jobUuid);
		setSimulationUuid(primaryKey.simulationUuid);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimulationSharePK(_shareSeqno, _jobSeqNo, _jobUuid,
			_simulationUuid);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimulationSharePK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("shareSeqno", getShareSeqno());
		attributes.put("jobSeqNo", getJobSeqNo());
		attributes.put("jobUuid", getJobUuid());
		attributes.put("simulationUuid", getSimulationUuid());
		attributes.put("classId", getClassId());
		attributes.put("customId", getCustomId());
		attributes.put("simulationShareDt", getSimulationShareDt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long shareSeqno = (Long)attributes.get("shareSeqno");

		if (shareSeqno != null) {
			setShareSeqno(shareSeqno);
		}

		Long jobSeqNo = (Long)attributes.get("jobSeqNo");

		if (jobSeqNo != null) {
			setJobSeqNo(jobSeqNo);
		}

		String jobUuid = (String)attributes.get("jobUuid");

		if (jobUuid != null) {
			setJobUuid(jobUuid);
		}

		String simulationUuid = (String)attributes.get("simulationUuid");

		if (simulationUuid != null) {
			setSimulationUuid(simulationUuid);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long customId = (Long)attributes.get("customId");

		if (customId != null) {
			setCustomId(customId);
		}

		Date simulationShareDt = (Date)attributes.get("simulationShareDt");

		if (simulationShareDt != null) {
			setSimulationShareDt(simulationShareDt);
		}
	}

	@Override
	public long getShareSeqno() {
		return _shareSeqno;
	}

	@Override
	public void setShareSeqno(long shareSeqno) {
		_shareSeqno = shareSeqno;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setShareSeqno", long.class);

				method.invoke(_simulationShareRemoteModel, shareSeqno);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getJobSeqNo() {
		return _jobSeqNo;
	}

	@Override
	public void setJobSeqNo(long jobSeqNo) {
		_jobSeqNo = jobSeqNo;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setJobSeqNo", long.class);

				method.invoke(_simulationShareRemoteModel, jobSeqNo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getJobUuid() {
		return _jobUuid;
	}

	@Override
	public void setJobUuid(String jobUuid) {
		_jobUuid = jobUuid;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setJobUuid", String.class);

				method.invoke(_simulationShareRemoteModel, jobUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSimulationUuid() {
		return _simulationUuid;
	}

	@Override
	public void setSimulationUuid(String simulationUuid) {
		_simulationUuid = simulationUuid;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationUuid",
						String.class);

				method.invoke(_simulationShareRemoteModel, simulationUuid);
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

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setClassId", long.class);

				method.invoke(_simulationShareRemoteModel, classId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCustomId() {
		return _customId;
	}

	@Override
	public void setCustomId(long customId) {
		_customId = customId;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setCustomId", long.class);

				method.invoke(_simulationShareRemoteModel, customId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getSimulationShareDt() {
		return _simulationShareDt;
	}

	@Override
	public void setSimulationShareDt(Date simulationShareDt) {
		_simulationShareDt = simulationShareDt;

		if (_simulationShareRemoteModel != null) {
			try {
				Class<?> clazz = _simulationShareRemoteModel.getClass();

				Method method = clazz.getMethod("setSimulationShareDt",
						Date.class);

				method.invoke(_simulationShareRemoteModel, simulationShareDt);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSimulationShareRemoteModel() {
		return _simulationShareRemoteModel;
	}

	public void setSimulationShareRemoteModel(
		BaseModel<?> simulationShareRemoteModel) {
		_simulationShareRemoteModel = simulationShareRemoteModel;
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

		Class<?> remoteModelClass = _simulationShareRemoteModel.getClass();

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

		Object returnValue = method.invoke(_simulationShareRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimulationShareLocalServiceUtil.addSimulationShare(this);
		}
		else {
			SimulationShareLocalServiceUtil.updateSimulationShare(this);
		}
	}

	@Override
	public SimulationShare toEscapedModel() {
		return (SimulationShare)ProxyUtil.newProxyInstance(SimulationShare.class.getClassLoader(),
			new Class[] { SimulationShare.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SimulationShareClp clone = new SimulationShareClp();

		clone.setShareSeqno(getShareSeqno());
		clone.setJobSeqNo(getJobSeqNo());
		clone.setJobUuid(getJobUuid());
		clone.setSimulationUuid(getSimulationUuid());
		clone.setClassId(getClassId());
		clone.setCustomId(getCustomId());
		clone.setSimulationShareDt(getSimulationShareDt());

		return clone;
	}

	@Override
	public int compareTo(SimulationShare simulationShare) {
		SimulationSharePK primaryKey = simulationShare.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationShareClp)) {
			return false;
		}

		SimulationShareClp simulationShare = (SimulationShareClp)obj;

		SimulationSharePK primaryKey = simulationShare.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{shareSeqno=");
		sb.append(getShareSeqno());
		sb.append(", jobSeqNo=");
		sb.append(getJobSeqNo());
		sb.append(", jobUuid=");
		sb.append(getJobUuid());
		sb.append(", simulationUuid=");
		sb.append(getSimulationUuid());
		sb.append(", classId=");
		sb.append(getClassId());
		sb.append(", customId=");
		sb.append(getCustomId());
		sb.append(", simulationShareDt=");
		sb.append(getSimulationShareDt());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.bestsimulation.model.SimulationShare");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>shareSeqno</column-name><column-value><![CDATA[");
		sb.append(getShareSeqno());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobSeqNo</column-name><column-value><![CDATA[");
		sb.append(getJobSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jobUuid</column-name><column-value><![CDATA[");
		sb.append(getJobUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationUuid</column-name><column-value><![CDATA[");
		sb.append(getSimulationUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classId</column-name><column-value><![CDATA[");
		sb.append(getClassId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customId</column-name><column-value><![CDATA[");
		sb.append(getCustomId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulationShareDt</column-name><column-value><![CDATA[");
		sb.append(getSimulationShareDt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _shareSeqno;
	private long _jobSeqNo;
	private String _jobUuid;
	private String _simulationUuid;
	private long _classId;
	private long _customId;
	private Date _simulationShareDt;
	private BaseModel<?> _simulationShareRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.bestsimulation.service.ClpSerializer.class;
}