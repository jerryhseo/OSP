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

package com.kisti.osp.model;

import com.kisti.osp.service.ClpSerializer;
import com.kisti.osp.service.FileManagementLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry h. Seo
 */
public class FileManagementClp extends BaseModelImpl<FileManagement>
	implements FileManagement {
	public FileManagementClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return FileManagement.class;
	}

	@Override
	public String getModelClassName() {
		return FileManagement.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _userId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("assigned", getAssigned());
		attributes.put("used", getUsed());
		attributes.put("lastModified", getLastModified());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer assigned = (Integer)attributes.get("assigned");

		if (assigned != null) {
			setAssigned(assigned);
		}

		Integer used = (Integer)attributes.get("used");

		if (used != null) {
			setUsed(used);
		}

		Date lastModified = (Date)attributes.get("lastModified");

		if (lastModified != null) {
			setLastModified(lastModified);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_fileManagementRemoteModel != null) {
			try {
				Class<?> clazz = _fileManagementRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_fileManagementRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public int getAssigned() {
		return _assigned;
	}

	@Override
	public void setAssigned(int assigned) {
		_assigned = assigned;

		if (_fileManagementRemoteModel != null) {
			try {
				Class<?> clazz = _fileManagementRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigned", int.class);

				method.invoke(_fileManagementRemoteModel, assigned);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getUsed() {
		return _used;
	}

	@Override
	public void setUsed(int used) {
		_used = used;

		if (_fileManagementRemoteModel != null) {
			try {
				Class<?> clazz = _fileManagementRemoteModel.getClass();

				Method method = clazz.getMethod("setUsed", int.class);

				method.invoke(_fileManagementRemoteModel, used);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastModified() {
		return _lastModified;
	}

	@Override
	public void setLastModified(Date lastModified) {
		_lastModified = lastModified;

		if (_fileManagementRemoteModel != null) {
			try {
				Class<?> clazz = _fileManagementRemoteModel.getClass();

				Method method = clazz.getMethod("setLastModified", Date.class);

				method.invoke(_fileManagementRemoteModel, lastModified);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_fileManagementRemoteModel != null) {
			try {
				Class<?> clazz = _fileManagementRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_fileManagementRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getFileManagementRemoteModel() {
		return _fileManagementRemoteModel;
	}

	public void setFileManagementRemoteModel(
		BaseModel<?> fileManagementRemoteModel) {
		_fileManagementRemoteModel = fileManagementRemoteModel;
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

		Class<?> remoteModelClass = _fileManagementRemoteModel.getClass();

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

		Object returnValue = method.invoke(_fileManagementRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			FileManagementLocalServiceUtil.addFileManagement(this);
		}
		else {
			FileManagementLocalServiceUtil.updateFileManagement(this);
		}
	}

	@Override
	public FileManagement toEscapedModel() {
		return (FileManagement)ProxyUtil.newProxyInstance(FileManagement.class.getClassLoader(),
			new Class[] { FileManagement.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		FileManagementClp clone = new FileManagementClp();

		clone.setUserId(getUserId());
		clone.setAssigned(getAssigned());
		clone.setUsed(getUsed());
		clone.setLastModified(getLastModified());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(FileManagement fileManagement) {
		long primaryKey = fileManagement.getPrimaryKey();

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

		if (!(obj instanceof FileManagementClp)) {
			return false;
		}

		FileManagementClp fileManagement = (FileManagementClp)obj;

		long primaryKey = fileManagement.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{userId=");
		sb.append(getUserId());
		sb.append(", assigned=");
		sb.append(getAssigned());
		sb.append(", used=");
		sb.append(getUsed());
		sb.append(", lastModified=");
		sb.append(getLastModified());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.kisti.osp.model.FileManagement");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigned</column-name><column-value><![CDATA[");
		sb.append(getAssigned());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>used</column-name><column-value><![CDATA[");
		sb.append(getUsed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastModified</column-name><column-value><![CDATA[");
		sb.append(getLastModified());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userId;
	private String _userUuid;
	private int _assigned;
	private int _used;
	private Date _lastModified;
	private int _status;
	private BaseModel<?> _fileManagementRemoteModel;
	private Class<?> _clpSerializerClass = com.kisti.osp.service.ClpSerializer.class;
}