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

package edison.challenge.service.builder.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import edison.challenge.service.builder.service.AwardhistoryLocalServiceUtil;
import edison.challenge.service.builder.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kyj
 */
public class AwardhistoryClp extends BaseModelImpl<Awardhistory>
	implements Awardhistory {
	public AwardhistoryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Awardhistory.class;
	}

	@Override
	public String getModelClassName() {
		return Awardhistory.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _awardhistoryid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAwardhistoryid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _awardhistoryid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("awardhistoryid", getAwardhistoryid());
		attributes.put("name", getName());
		attributes.put("prize", getPrize());
		attributes.put("chTeamid", getChTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long awardhistoryid = (Long)attributes.get("awardhistoryid");

		if (awardhistoryid != null) {
			setAwardhistoryid(awardhistoryid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String prize = (String)attributes.get("prize");

		if (prize != null) {
			setPrize(prize);
		}

		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}
	}

	@Override
	public long getAwardhistoryid() {
		return _awardhistoryid;
	}

	@Override
	public void setAwardhistoryid(long awardhistoryid) {
		_awardhistoryid = awardhistoryid;

		if (_awardhistoryRemoteModel != null) {
			try {
				Class<?> clazz = _awardhistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setAwardhistoryid", long.class);

				method.invoke(_awardhistoryRemoteModel, awardhistoryid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_awardhistoryRemoteModel != null) {
			try {
				Class<?> clazz = _awardhistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_awardhistoryRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPrize() {
		return _prize;
	}

	@Override
	public void setPrize(String prize) {
		_prize = prize;

		if (_awardhistoryRemoteModel != null) {
			try {
				Class<?> clazz = _awardhistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setPrize", String.class);

				method.invoke(_awardhistoryRemoteModel, prize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChTeamid() {
		return _chTeamid;
	}

	@Override
	public void setChTeamid(long chTeamid) {
		_chTeamid = chTeamid;

		if (_awardhistoryRemoteModel != null) {
			try {
				Class<?> clazz = _awardhistoryRemoteModel.getClass();

				Method method = clazz.getMethod("setChTeamid", long.class);

				method.invoke(_awardhistoryRemoteModel, chTeamid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAwardhistoryRemoteModel() {
		return _awardhistoryRemoteModel;
	}

	public void setAwardhistoryRemoteModel(BaseModel<?> awardhistoryRemoteModel) {
		_awardhistoryRemoteModel = awardhistoryRemoteModel;
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

		Class<?> remoteModelClass = _awardhistoryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_awardhistoryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			AwardhistoryLocalServiceUtil.addAwardhistory(this);
		}
		else {
			AwardhistoryLocalServiceUtil.updateAwardhistory(this);
		}
	}

	@Override
	public Awardhistory toEscapedModel() {
		return (Awardhistory)ProxyUtil.newProxyInstance(Awardhistory.class.getClassLoader(),
			new Class[] { Awardhistory.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AwardhistoryClp clone = new AwardhistoryClp();

		clone.setAwardhistoryid(getAwardhistoryid());
		clone.setName(getName());
		clone.setPrize(getPrize());
		clone.setChTeamid(getChTeamid());

		return clone;
	}

	@Override
	public int compareTo(Awardhistory awardhistory) {
		int value = 0;

		if (getChTeamid() < awardhistory.getChTeamid()) {
			value = -1;
		}
		else if (getChTeamid() > awardhistory.getChTeamid()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AwardhistoryClp)) {
			return false;
		}

		AwardhistoryClp awardhistory = (AwardhistoryClp)obj;

		long primaryKey = awardhistory.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{awardhistoryid=");
		sb.append(getAwardhistoryid());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", prize=");
		sb.append(getPrize());
		sb.append(", chTeamid=");
		sb.append(getChTeamid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.Awardhistory");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>awardhistoryid</column-name><column-value><![CDATA[");
		sb.append(getAwardhistoryid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>prize</column-name><column-value><![CDATA[");
		sb.append(getPrize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>chTeamid</column-name><column-value><![CDATA[");
		sb.append(getChTeamid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _awardhistoryid;
	private String _name;
	private String _prize;
	private long _chTeamid;
	private BaseModel<?> _awardhistoryRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}