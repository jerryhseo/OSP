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

package org.kisti.edison.science.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.science.service.ClpSerializer;
import org.kisti.edison.science.service.ScienceAppPaperLocalServiceUtil;
import org.kisti.edison.science.service.persistence.ScienceAppPaperPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EDISON
 */
public class ScienceAppPaperClp extends BaseModelImpl<ScienceAppPaper>
	implements ScienceAppPaper {
	public ScienceAppPaperClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppPaper.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppPaper.class.getName();
	}

	@Override
	public ScienceAppPaperPK getPrimaryKey() {
		return new ScienceAppPaperPK(_scienceAppId, _paperSeq);
	}

	@Override
	public void setPrimaryKey(ScienceAppPaperPK primaryKey) {
		setScienceAppId(primaryKey.scienceAppId);
		setPaperSeq(primaryKey.paperSeq);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ScienceAppPaperPK(_scienceAppId, _paperSeq);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ScienceAppPaperPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("paperSeq", getPaperSeq());
		attributes.put("paperType", getPaperType());
		attributes.put("paperValue", getPaperValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long paperSeq = (Long)attributes.get("paperSeq");

		if (paperSeq != null) {
			setPaperSeq(paperSeq);
		}

		String paperType = (String)attributes.get("paperType");

		if (paperType != null) {
			setPaperType(paperType);
		}

		String paperValue = (String)attributes.get("paperValue");

		if (paperValue != null) {
			setPaperValue(paperValue);
		}
	}

	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;

		if (_scienceAppPaperRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppPaperRemoteModel.getClass();

				Method method = clazz.getMethod("setScienceAppId", long.class);

				method.invoke(_scienceAppPaperRemoteModel, scienceAppId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPaperSeq() {
		return _paperSeq;
	}

	@Override
	public void setPaperSeq(long paperSeq) {
		_paperSeq = paperSeq;

		if (_scienceAppPaperRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppPaperRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperSeq", long.class);

				method.invoke(_scienceAppPaperRemoteModel, paperSeq);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPaperType() {
		return _paperType;
	}

	@Override
	public void setPaperType(String paperType) {
		_paperType = paperType;

		if (_scienceAppPaperRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppPaperRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperType", String.class);

				method.invoke(_scienceAppPaperRemoteModel, paperType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPaperValue() {
		return _paperValue;
	}

	@Override
	public void setPaperValue(String paperValue) {
		_paperValue = paperValue;

		if (_scienceAppPaperRemoteModel != null) {
			try {
				Class<?> clazz = _scienceAppPaperRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperValue", String.class);

				method.invoke(_scienceAppPaperRemoteModel, paperValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getScienceAppPaperRemoteModel() {
		return _scienceAppPaperRemoteModel;
	}

	public void setScienceAppPaperRemoteModel(
		BaseModel<?> scienceAppPaperRemoteModel) {
		_scienceAppPaperRemoteModel = scienceAppPaperRemoteModel;
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

		Class<?> remoteModelClass = _scienceAppPaperRemoteModel.getClass();

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

		Object returnValue = method.invoke(_scienceAppPaperRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ScienceAppPaperLocalServiceUtil.addScienceAppPaper(this);
		}
		else {
			ScienceAppPaperLocalServiceUtil.updateScienceAppPaper(this);
		}
	}

	@Override
	public ScienceAppPaper toEscapedModel() {
		return (ScienceAppPaper)ProxyUtil.newProxyInstance(ScienceAppPaper.class.getClassLoader(),
			new Class[] { ScienceAppPaper.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ScienceAppPaperClp clone = new ScienceAppPaperClp();

		clone.setScienceAppId(getScienceAppId());
		clone.setPaperSeq(getPaperSeq());
		clone.setPaperType(getPaperType());
		clone.setPaperValue(getPaperValue());

		return clone;
	}

	@Override
	public int compareTo(ScienceAppPaper scienceAppPaper) {
		ScienceAppPaperPK primaryKey = scienceAppPaper.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppPaperClp)) {
			return false;
		}

		ScienceAppPaperClp scienceAppPaper = (ScienceAppPaperClp)obj;

		ScienceAppPaperPK primaryKey = scienceAppPaper.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", paperSeq=");
		sb.append(getPaperSeq());
		sb.append(", paperType=");
		sb.append(getPaperType());
		sb.append(", paperValue=");
		sb.append(getPaperValue());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.science.model.ScienceAppPaper");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperSeq</column-name><column-value><![CDATA[");
		sb.append(getPaperSeq());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperType</column-name><column-value><![CDATA[");
		sb.append(getPaperType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperValue</column-name><column-value><![CDATA[");
		sb.append(getPaperValue());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _scienceAppId;
	private long _paperSeq;
	private String _paperType;
	private String _paperValue;
	private BaseModel<?> _scienceAppPaperRemoteModel;
	private Class<?> _clpSerializerClass = org.kisti.edison.science.service.ClpSerializer.class;
}