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

package org.kisti.edison.login.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoginLog}.
 * </p>
 *
 * @author yjpark
 * @see LoginLog
 * @generated
 */
public class LoginLogWrapper implements LoginLog, ModelWrapper<LoginLog> {
	public LoginLogWrapper(LoginLog loginLog) {
		_loginLog = loginLog;
	}

	@Override
	public Class<?> getModelClass() {
		return LoginLog.class;
	}

	@Override
	public String getModelClassName() {
		return LoginLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("createDate", getCreateDate());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	/**
	* Returns the primary key of this login log.
	*
	* @return the primary key of this login log
	*/
	@Override
	public long getPrimaryKey() {
		return _loginLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this login log.
	*
	* @param primaryKey the primary key of this login log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loginLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this login log.
	*
	* @return the ID of this login log
	*/
	@Override
	public long getId() {
		return _loginLog.getId();
	}

	/**
	* Sets the ID of this login log.
	*
	* @param id the ID of this login log
	*/
	@Override
	public void setId(long id) {
		_loginLog.setId(id);
	}

	/**
	* Returns the create date of this login log.
	*
	* @return the create date of this login log
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _loginLog.getCreateDate();
	}

	/**
	* Sets the create date of this login log.
	*
	* @param createDate the create date of this login log
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_loginLog.setCreateDate(createDate);
	}

	/**
	* Returns the user ID of this login log.
	*
	* @return the user ID of this login log
	*/
	@Override
	public long getUserId() {
		return _loginLog.getUserId();
	}

	/**
	* Sets the user ID of this login log.
	*
	* @param userId the user ID of this login log
	*/
	@Override
	public void setUserId(long userId) {
		_loginLog.setUserId(userId);
	}

	/**
	* Returns the user uuid of this login log.
	*
	* @return the user uuid of this login log
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLog.getUserUuid();
	}

	/**
	* Sets the user uuid of this login log.
	*
	* @param userUuid the user uuid of this login log
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_loginLog.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _loginLog.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_loginLog.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _loginLog.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loginLog.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _loginLog.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _loginLog.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_loginLog.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _loginLog.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_loginLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_loginLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_loginLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LoginLogWrapper((LoginLog)_loginLog.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.login.model.LoginLog loginLog) {
		return _loginLog.compareTo(loginLog);
	}

	@Override
	public int hashCode() {
		return _loginLog.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.login.model.LoginLog> toCacheModel() {
		return _loginLog.toCacheModel();
	}

	@Override
	public org.kisti.edison.login.model.LoginLog toEscapedModel() {
		return new LoginLogWrapper(_loginLog.toEscapedModel());
	}

	@Override
	public org.kisti.edison.login.model.LoginLog toUnescapedModel() {
		return new LoginLogWrapper(_loginLog.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _loginLog.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _loginLog.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_loginLog.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoginLogWrapper)) {
			return false;
		}

		LoginLogWrapper loginLogWrapper = (LoginLogWrapper)obj;

		if (Validator.equals(_loginLog, loginLogWrapper._loginLog)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public LoginLog getWrappedLoginLog() {
		return _loginLog;
	}

	@Override
	public LoginLog getWrappedModel() {
		return _loginLog;
	}

	@Override
	public void resetOriginalValues() {
		_loginLog.resetOriginalValues();
	}

	private LoginLog _loginLog;
}