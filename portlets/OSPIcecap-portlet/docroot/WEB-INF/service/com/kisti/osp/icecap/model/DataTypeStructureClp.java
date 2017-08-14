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
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;

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
public class DataTypeStructureClp extends BaseModelImpl<DataTypeStructure>
	implements DataTypeStructure {
	public DataTypeStructureClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return DataTypeStructure.class;
	}

	@Override
	public String getModelClassName() {
		return DataTypeStructure.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _typeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTypeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _typeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("structure", getStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String structure = (String)attributes.get("structure");

		if (structure != null) {
			setStructure(structure);
		}
	}

	@Override
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		_typeId = typeId;

		if (_dataTypeStructureRemoteModel != null) {
			try {
				Class<?> clazz = _dataTypeStructureRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeId", long.class);

				method.invoke(_dataTypeStructureRemoteModel, typeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStructure() {
		return _structure;
	}

	@Override
	public void setStructure(String structure) {
		_structure = structure;

		if (_dataTypeStructureRemoteModel != null) {
			try {
				Class<?> clazz = _dataTypeStructureRemoteModel.getClass();

				Method method = clazz.getMethod("setStructure", String.class);

				method.invoke(_dataTypeStructureRemoteModel, structure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDataTypeStructureRemoteModel() {
		return _dataTypeStructureRemoteModel;
	}

	public void setDataTypeStructureRemoteModel(
		BaseModel<?> dataTypeStructureRemoteModel) {
		_dataTypeStructureRemoteModel = dataTypeStructureRemoteModel;
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

		Class<?> remoteModelClass = _dataTypeStructureRemoteModel.getClass();

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

		Object returnValue = method.invoke(_dataTypeStructureRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataTypeStructureLocalServiceUtil.addDataTypeStructure(this);
		}
		else {
			DataTypeStructureLocalServiceUtil.updateDataTypeStructure(this);
		}
	}

	@Override
	public DataTypeStructure toEscapedModel() {
		return (DataTypeStructure)ProxyUtil.newProxyInstance(DataTypeStructure.class.getClassLoader(),
			new Class[] { DataTypeStructure.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DataTypeStructureClp clone = new DataTypeStructureClp();

		clone.setTypeId(getTypeId());
		clone.setStructure(getStructure());

		return clone;
	}

	@Override
	public int compareTo(DataTypeStructure dataTypeStructure) {
		long primaryKey = dataTypeStructure.getPrimaryKey();

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

		if (!(obj instanceof DataTypeStructureClp)) {
			return false;
		}

		DataTypeStructureClp dataTypeStructure = (DataTypeStructureClp)obj;

		long primaryKey = dataTypeStructure.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{typeId=");
		sb.append(getTypeId());
		sb.append(", structure=");
		sb.append(getStructure());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.icecap.model.DataTypeStructure");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>typeId</column-name><column-value><![CDATA[");
		sb.append(getTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>structure</column-name><column-value><![CDATA[");
		sb.append(getStructure());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _typeId;
	private String _structure;
	private BaseModel<?> _dataTypeStructureRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.icecap.service.ClpSerializer.class;
}