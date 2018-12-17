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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExecuteUser}.
 * </p>
 *
 * @author edison
 * @see ExecuteUser
 * @generated
 */
public class ExecuteUserWrapper implements ExecuteUser,
	ModelWrapper<ExecuteUser> {
	public ExecuteUserWrapper(ExecuteUser executeUser) {
		_executeUser = executeUser;
	}

	@Override
	public Class<?> getModelClass() {
		return ExecuteUser.class;
	}

	@Override
	public String getModelClassName() {
		return ExecuteUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createDate", getCreateDate());
		attributes.put("cnt", getCnt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String createDate = (String)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long cnt = (Long)attributes.get("cnt");

		if (cnt != null) {
			setCnt(cnt);
		}
	}

	/**
	* Returns the primary key of this execute user.
	*
	* @return the primary key of this execute user
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _executeUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this execute user.
	*
	* @param primaryKey the primary key of this execute user
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_executeUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this execute user.
	*
	* @return the create date of this execute user
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _executeUser.getCreateDate();
	}

	/**
	* Sets the create date of this execute user.
	*
	* @param createDate the create date of this execute user
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_executeUser.setCreateDate(createDate);
	}

	/**
	* Returns the cnt of this execute user.
	*
	* @return the cnt of this execute user
	*/
	@Override
	public java.lang.Long getCnt() {
		return _executeUser.getCnt();
	}

	/**
	* Sets the cnt of this execute user.
	*
	* @param cnt the cnt of this execute user
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_executeUser.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _executeUser.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_executeUser.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _executeUser.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_executeUser.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _executeUser.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _executeUser.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_executeUser.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _executeUser.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_executeUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_executeUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_executeUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExecuteUserWrapper((ExecuteUser)_executeUser.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.ExecuteUser executeUser) {
		return _executeUser.compareTo(executeUser);
	}

	@Override
	public int hashCode() {
		return _executeUser.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.ExecuteUser> toCacheModel() {
		return _executeUser.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.ExecuteUser toEscapedModel() {
		return new ExecuteUserWrapper(_executeUser.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.ExecuteUser toUnescapedModel() {
		return new ExecuteUserWrapper(_executeUser.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _executeUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _executeUser.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_executeUser.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExecuteUserWrapper)) {
			return false;
		}

		ExecuteUserWrapper executeUserWrapper = (ExecuteUserWrapper)obj;

		if (Validator.equals(_executeUser, executeUserWrapper._executeUser)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ExecuteUser getWrappedExecuteUser() {
		return _executeUser;
	}

	@Override
	public ExecuteUser getWrappedModel() {
		return _executeUser;
	}

	@Override
	public void resetOriginalValues() {
		_executeUser.resetOriginalValues();
	}

	private ExecuteUser _executeUser;
}