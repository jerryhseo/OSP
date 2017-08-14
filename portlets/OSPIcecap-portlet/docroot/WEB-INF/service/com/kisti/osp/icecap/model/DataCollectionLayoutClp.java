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
import com.kisti.osp.icecap.service.DataCollectionLayoutLocalServiceUtil;

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
public class DataCollectionLayoutClp extends BaseModelImpl<DataCollectionLayout>
	implements DataCollectionLayout {
	public DataCollectionLayoutClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return DataCollectionLayout.class;
	}

	@Override
	public String getModelClassName() {
		return DataCollectionLayout.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _collectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCollectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _collectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("collectionId", getCollectionId());
		attributes.put("layoutStr", getLayoutStr());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long collectionId = (Long)attributes.get("collectionId");

		if (collectionId != null) {
			setCollectionId(collectionId);
		}

		String layoutStr = (String)attributes.get("layoutStr");

		if (layoutStr != null) {
			setLayoutStr(layoutStr);
		}
	}

	@Override
	public long getCollectionId() {
		return _collectionId;
	}

	@Override
	public void setCollectionId(long collectionId) {
		_collectionId = collectionId;

		if (_dataCollectionLayoutRemoteModel != null) {
			try {
				Class<?> clazz = _dataCollectionLayoutRemoteModel.getClass();

				Method method = clazz.getMethod("setCollectionId", long.class);

				method.invoke(_dataCollectionLayoutRemoteModel, collectionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLayoutStr() {
		return _layoutStr;
	}

	@Override
	public void setLayoutStr(String layoutStr) {
		_layoutStr = layoutStr;

		if (_dataCollectionLayoutRemoteModel != null) {
			try {
				Class<?> clazz = _dataCollectionLayoutRemoteModel.getClass();

				Method method = clazz.getMethod("setLayoutStr", String.class);

				method.invoke(_dataCollectionLayoutRemoteModel, layoutStr);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDataCollectionLayoutRemoteModel() {
		return _dataCollectionLayoutRemoteModel;
	}

	public void setDataCollectionLayoutRemoteModel(
		BaseModel<?> dataCollectionLayoutRemoteModel) {
		_dataCollectionLayoutRemoteModel = dataCollectionLayoutRemoteModel;
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

		Class<?> remoteModelClass = _dataCollectionLayoutRemoteModel.getClass();

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

		Object returnValue = method.invoke(_dataCollectionLayoutRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataCollectionLayoutLocalServiceUtil.addDataCollectionLayout(this);
		}
		else {
			DataCollectionLayoutLocalServiceUtil.updateDataCollectionLayout(this);
		}
	}

	@Override
	public DataCollectionLayout toEscapedModel() {
		return (DataCollectionLayout)ProxyUtil.newProxyInstance(DataCollectionLayout.class.getClassLoader(),
			new Class[] { DataCollectionLayout.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DataCollectionLayoutClp clone = new DataCollectionLayoutClp();

		clone.setCollectionId(getCollectionId());
		clone.setLayoutStr(getLayoutStr());

		return clone;
	}

	@Override
	public int compareTo(DataCollectionLayout dataCollectionLayout) {
		long primaryKey = dataCollectionLayout.getPrimaryKey();

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

		if (!(obj instanceof DataCollectionLayoutClp)) {
			return false;
		}

		DataCollectionLayoutClp dataCollectionLayout = (DataCollectionLayoutClp)obj;

		long primaryKey = dataCollectionLayout.getPrimaryKey();

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

		sb.append("{collectionId=");
		sb.append(getCollectionId());
		sb.append(", layoutStr=");
		sb.append(getLayoutStr());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.icecap.model.DataCollectionLayout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>collectionId</column-name><column-value><![CDATA[");
		sb.append(getCollectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>layoutStr</column-name><column-value><![CDATA[");
		sb.append(getLayoutStr());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _collectionId;
	private String _layoutStr;
	private BaseModel<?> _dataCollectionLayoutRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.icecap.service.ClpSerializer.class;
}