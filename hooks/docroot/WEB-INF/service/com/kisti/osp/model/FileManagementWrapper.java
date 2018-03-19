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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FileManagement}.
 * </p>
 *
 * @author Jerry h. Seo
 * @see FileManagement
 * @generated
 */
public class FileManagementWrapper implements FileManagement,
	ModelWrapper<FileManagement> {
	public FileManagementWrapper(FileManagement fileManagement) {
		_fileManagement = fileManagement;
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

	/**
	* Returns the primary key of this file management.
	*
	* @return the primary key of this file management
	*/
	@Override
	public long getPrimaryKey() {
		return _fileManagement.getPrimaryKey();
	}

	/**
	* Sets the primary key of this file management.
	*
	* @param primaryKey the primary key of this file management
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fileManagement.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user ID of this file management.
	*
	* @return the user ID of this file management
	*/
	@Override
	public long getUserId() {
		return _fileManagement.getUserId();
	}

	/**
	* Sets the user ID of this file management.
	*
	* @param userId the user ID of this file management
	*/
	@Override
	public void setUserId(long userId) {
		_fileManagement.setUserId(userId);
	}

	/**
	* Returns the user uuid of this file management.
	*
	* @return the user uuid of this file management
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagement.getUserUuid();
	}

	/**
	* Sets the user uuid of this file management.
	*
	* @param userUuid the user uuid of this file management
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_fileManagement.setUserUuid(userUuid);
	}

	/**
	* Returns the assigned of this file management.
	*
	* @return the assigned of this file management
	*/
	@Override
	public int getAssigned() {
		return _fileManagement.getAssigned();
	}

	/**
	* Sets the assigned of this file management.
	*
	* @param assigned the assigned of this file management
	*/
	@Override
	public void setAssigned(int assigned) {
		_fileManagement.setAssigned(assigned);
	}

	/**
	* Returns the used of this file management.
	*
	* @return the used of this file management
	*/
	@Override
	public int getUsed() {
		return _fileManagement.getUsed();
	}

	/**
	* Sets the used of this file management.
	*
	* @param used the used of this file management
	*/
	@Override
	public void setUsed(int used) {
		_fileManagement.setUsed(used);
	}

	/**
	* Returns the last modified of this file management.
	*
	* @return the last modified of this file management
	*/
	@Override
	public java.util.Date getLastModified() {
		return _fileManagement.getLastModified();
	}

	/**
	* Sets the last modified of this file management.
	*
	* @param lastModified the last modified of this file management
	*/
	@Override
	public void setLastModified(java.util.Date lastModified) {
		_fileManagement.setLastModified(lastModified);
	}

	/**
	* Returns the status of this file management.
	*
	* @return the status of this file management
	*/
	@Override
	public int getStatus() {
		return _fileManagement.getStatus();
	}

	/**
	* Sets the status of this file management.
	*
	* @param status the status of this file management
	*/
	@Override
	public void setStatus(int status) {
		_fileManagement.setStatus(status);
	}

	@Override
	public boolean isNew() {
		return _fileManagement.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_fileManagement.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _fileManagement.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fileManagement.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _fileManagement.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _fileManagement.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_fileManagement.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _fileManagement.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_fileManagement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_fileManagement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_fileManagement.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FileManagementWrapper((FileManagement)_fileManagement.clone());
	}

	@Override
	public int compareTo(com.kisti.osp.model.FileManagement fileManagement) {
		return _fileManagement.compareTo(fileManagement);
	}

	@Override
	public int hashCode() {
		return _fileManagement.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.kisti.osp.model.FileManagement> toCacheModel() {
		return _fileManagement.toCacheModel();
	}

	@Override
	public com.kisti.osp.model.FileManagement toEscapedModel() {
		return new FileManagementWrapper(_fileManagement.toEscapedModel());
	}

	@Override
	public com.kisti.osp.model.FileManagement toUnescapedModel() {
		return new FileManagementWrapper(_fileManagement.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fileManagement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _fileManagement.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_fileManagement.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileManagementWrapper)) {
			return false;
		}

		FileManagementWrapper fileManagementWrapper = (FileManagementWrapper)obj;

		if (Validator.equals(_fileManagement,
					fileManagementWrapper._fileManagement)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public FileManagement getWrappedFileManagement() {
		return _fileManagement;
	}

	@Override
	public FileManagement getWrappedModel() {
		return _fileManagement;
	}

	@Override
	public void resetOriginalValues() {
		_fileManagement.resetOriginalValues();
	}

	private FileManagement _fileManagement;
}