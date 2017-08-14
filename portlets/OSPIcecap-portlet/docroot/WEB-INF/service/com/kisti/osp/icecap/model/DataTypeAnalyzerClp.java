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
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK;

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
public class DataTypeAnalyzerClp extends BaseModelImpl<DataTypeAnalyzer>
	implements DataTypeAnalyzer {
	public DataTypeAnalyzerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return DataTypeAnalyzer.class;
	}

	@Override
	public String getModelClassName() {
		return DataTypeAnalyzer.class.getName();
	}

	@Override
	public DataTypeAnalyzerPK getPrimaryKey() {
		return new DataTypeAnalyzerPK(_typeId, _analyzerId);
	}

	@Override
	public void setPrimaryKey(DataTypeAnalyzerPK primaryKey) {
		setTypeId(primaryKey.typeId);
		setAnalyzerId(primaryKey.analyzerId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new DataTypeAnalyzerPK(_typeId, _analyzerId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((DataTypeAnalyzerPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("analyzerId", getAnalyzerId());
		attributes.put("isDefault", getIsDefault());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long analyzerId = (Long)attributes.get("analyzerId");

		if (analyzerId != null) {
			setAnalyzerId(analyzerId);
		}

		Boolean isDefault = (Boolean)attributes.get("isDefault");

		if (isDefault != null) {
			setIsDefault(isDefault);
		}
	}

	@Override
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		_typeId = typeId;

		if (_dataTypeAnalyzerRemoteModel != null) {
			try {
				Class<?> clazz = _dataTypeAnalyzerRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeId", long.class);

				method.invoke(_dataTypeAnalyzerRemoteModel, typeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAnalyzerId() {
		return _analyzerId;
	}

	@Override
	public void setAnalyzerId(long analyzerId) {
		_analyzerId = analyzerId;

		if (_dataTypeAnalyzerRemoteModel != null) {
			try {
				Class<?> clazz = _dataTypeAnalyzerRemoteModel.getClass();

				Method method = clazz.getMethod("setAnalyzerId", long.class);

				method.invoke(_dataTypeAnalyzerRemoteModel, analyzerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getIsDefault() {
		return _isDefault;
	}

	@Override
	public boolean isIsDefault() {
		return _isDefault;
	}

	@Override
	public void setIsDefault(boolean isDefault) {
		_isDefault = isDefault;

		if (_dataTypeAnalyzerRemoteModel != null) {
			try {
				Class<?> clazz = _dataTypeAnalyzerRemoteModel.getClass();

				Method method = clazz.getMethod("setIsDefault", boolean.class);

				method.invoke(_dataTypeAnalyzerRemoteModel, isDefault);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDataTypeAnalyzerRemoteModel() {
		return _dataTypeAnalyzerRemoteModel;
	}

	public void setDataTypeAnalyzerRemoteModel(
		BaseModel<?> dataTypeAnalyzerRemoteModel) {
		_dataTypeAnalyzerRemoteModel = dataTypeAnalyzerRemoteModel;
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

		Class<?> remoteModelClass = _dataTypeAnalyzerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_dataTypeAnalyzerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataTypeAnalyzerLocalServiceUtil.addDataTypeAnalyzer(this);
		}
		else {
			DataTypeAnalyzerLocalServiceUtil.updateDataTypeAnalyzer(this);
		}
	}

	@Override
	public DataTypeAnalyzer toEscapedModel() {
		return (DataTypeAnalyzer)ProxyUtil.newProxyInstance(DataTypeAnalyzer.class.getClassLoader(),
			new Class[] { DataTypeAnalyzer.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DataTypeAnalyzerClp clone = new DataTypeAnalyzerClp();

		clone.setTypeId(getTypeId());
		clone.setAnalyzerId(getAnalyzerId());
		clone.setIsDefault(getIsDefault());

		return clone;
	}

	@Override
	public int compareTo(DataTypeAnalyzer dataTypeAnalyzer) {
		DataTypeAnalyzerPK primaryKey = dataTypeAnalyzer.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataTypeAnalyzerClp)) {
			return false;
		}

		DataTypeAnalyzerClp dataTypeAnalyzer = (DataTypeAnalyzerClp)obj;

		DataTypeAnalyzerPK primaryKey = dataTypeAnalyzer.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{typeId=");
		sb.append(getTypeId());
		sb.append(", analyzerId=");
		sb.append(getAnalyzerId());
		sb.append(", isDefault=");
		sb.append(getIsDefault());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.icecap.model.DataTypeAnalyzer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>typeId</column-name><column-value><![CDATA[");
		sb.append(getTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>analyzerId</column-name><column-value><![CDATA[");
		sb.append(getAnalyzerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isDefault</column-name><column-value><![CDATA[");
		sb.append(getIsDefault());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _typeId;
	private long _analyzerId;
	private boolean _isDefault;
	private BaseModel<?> _dataTypeAnalyzerRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.icecap.service.ClpSerializer.class;
}