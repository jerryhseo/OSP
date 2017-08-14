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

package com.kisti.osp.icecap.model;

import com.kisti.osp.icecap.service.ClpSerializer;
import com.kisti.osp.icecap.service.DataEntryProvenanceLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Young-K. Suh and Jerry H. Seo
 */
public class DataEntryProvenanceClp extends BaseModelImpl<DataEntryProvenance>
	implements DataEntryProvenance {
	public DataEntryProvenanceClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return DataEntryProvenance.class;
	}

	@Override
	public String getModelClassName() {
		return DataEntryProvenance.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("inputData", getInputData());
		attributes.put("PROVStructure", getPROVStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		String inputData = (String)attributes.get("inputData");

		if (inputData != null) {
			setInputData(inputData);
		}

		String PROVStructure = (String)attributes.get("PROVStructure");

		if (PROVStructure != null) {
			setPROVStructure(PROVStructure);
		}
	}

	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;

		if (_dataEntryProvenanceRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryProvenanceRemoteModel.getClass();

				Method method = clazz.getMethod("setEntryId", long.class);

				method.invoke(_dataEntryProvenanceRemoteModel, entryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInputData() {
		return _inputData;
	}

	@Override
	public void setInputData(String inputData) {
		_inputData = inputData;

		if (_dataEntryProvenanceRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryProvenanceRemoteModel.getClass();

				Method method = clazz.getMethod("setInputData", String.class);

				method.invoke(_dataEntryProvenanceRemoteModel, inputData);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPROVStructure() {
		return _PROVStructure;
	}

	@Override
	public void setPROVStructure(String PROVStructure) {
		_PROVStructure = PROVStructure;

		if (_dataEntryProvenanceRemoteModel != null) {
			try {
				Class<?> clazz = _dataEntryProvenanceRemoteModel.getClass();

				Method method = clazz.getMethod("setPROVStructure", String.class);

				method.invoke(_dataEntryProvenanceRemoteModel, PROVStructure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDataEntryProvenanceRemoteModel() {
		return _dataEntryProvenanceRemoteModel;
	}

	public void setDataEntryProvenanceRemoteModel(
		BaseModel<?> dataEntryProvenanceRemoteModel) {
		_dataEntryProvenanceRemoteModel = dataEntryProvenanceRemoteModel;
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

		Class<?> remoteModelClass = _dataEntryProvenanceRemoteModel.getClass();

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

		Object returnValue = method.invoke(_dataEntryProvenanceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataEntryProvenanceLocalServiceUtil.addDataEntryProvenance(this);
		}
		else {
			DataEntryProvenanceLocalServiceUtil.updateDataEntryProvenance(this);
		}
	}

	@Override
	public DataEntryProvenance toEscapedModel() {
		return (DataEntryProvenance)ProxyUtil.newProxyInstance(DataEntryProvenance.class.getClassLoader(),
			new Class[] { DataEntryProvenance.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DataEntryProvenanceClp clone = new DataEntryProvenanceClp();

		clone.setEntryId(getEntryId());
		clone.setInputData(getInputData());
		clone.setPROVStructure(getPROVStructure());

		return clone;
	}

	@Override
	public int compareTo(DataEntryProvenance dataEntryProvenance) {
		long primaryKey = dataEntryProvenance.getPrimaryKey();

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

		if (!(obj instanceof DataEntryProvenanceClp)) {
			return false;
		}

		DataEntryProvenanceClp dataEntryProvenance = (DataEntryProvenanceClp)obj;

		long primaryKey = dataEntryProvenance.getPrimaryKey();

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

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", inputData=");
		sb.append(getInputData());
		sb.append(", PROVStructure=");
		sb.append(getPROVStructure());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.icecap.model.DataEntryProvenance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inputData</column-name><column-value><![CDATA[");
		sb.append(getInputData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>PROVStructure</column-name><column-value><![CDATA[");
		sb.append(getPROVStructure());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private String _inputData;
	private String _PROVStructure;
	private BaseModel<?> _dataEntryProvenanceRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.icecap.service.ClpSerializer.class;
}