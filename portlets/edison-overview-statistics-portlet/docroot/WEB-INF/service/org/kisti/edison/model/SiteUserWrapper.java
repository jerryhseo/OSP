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
 * This class is a wrapper for {@link SiteUser}.
 * </p>
 *
 * @author edison
 * @see SiteUser
 * @generated
 */
public class SiteUserWrapper implements SiteUser, ModelWrapper<SiteUser> {
	public SiteUserWrapper(SiteUser siteUser) {
		_siteUser = siteUser;
	}

	@Override
	public Class<?> getModelClass() {
		return SiteUser.class;
	}

	@Override
	public String getModelClassName() {
		return SiteUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createDate", getCreateDate());
		attributes.put("groupId", getGroupId());
		attributes.put("cnt", getCnt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String createDate = (String)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long cnt = (Long)attributes.get("cnt");

		if (cnt != null) {
			setCnt(cnt);
		}
	}

	/**
	* Returns the primary key of this site user.
	*
	* @return the primary key of this site user
	*/
	@Override
	public org.kisti.edison.service.persistence.SiteUserPK getPrimaryKey() {
		return _siteUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this site user.
	*
	* @param primaryKey the primary key of this site user
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.service.persistence.SiteUserPK primaryKey) {
		_siteUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this site user.
	*
	* @return the create date of this site user
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _siteUser.getCreateDate();
	}

	/**
	* Sets the create date of this site user.
	*
	* @param createDate the create date of this site user
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_siteUser.setCreateDate(createDate);
	}

	/**
	* Returns the group ID of this site user.
	*
	* @return the group ID of this site user
	*/
	@Override
	public long getGroupId() {
		return _siteUser.getGroupId();
	}

	/**
	* Sets the group ID of this site user.
	*
	* @param groupId the group ID of this site user
	*/
	@Override
	public void setGroupId(long groupId) {
		_siteUser.setGroupId(groupId);
	}

	/**
	* Returns the cnt of this site user.
	*
	* @return the cnt of this site user
	*/
	@Override
	public java.lang.Long getCnt() {
		return _siteUser.getCnt();
	}

	/**
	* Sets the cnt of this site user.
	*
	* @param cnt the cnt of this site user
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_siteUser.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _siteUser.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_siteUser.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _siteUser.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_siteUser.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _siteUser.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _siteUser.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_siteUser.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _siteUser.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_siteUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_siteUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_siteUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SiteUserWrapper((SiteUser)_siteUser.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.SiteUser siteUser) {
		return _siteUser.compareTo(siteUser);
	}

	@Override
	public int hashCode() {
		return _siteUser.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.SiteUser> toCacheModel() {
		return _siteUser.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.SiteUser toEscapedModel() {
		return new SiteUserWrapper(_siteUser.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.SiteUser toUnescapedModel() {
		return new SiteUserWrapper(_siteUser.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _siteUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _siteUser.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_siteUser.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SiteUserWrapper)) {
			return false;
		}

		SiteUserWrapper siteUserWrapper = (SiteUserWrapper)obj;

		if (Validator.equals(_siteUser, siteUserWrapper._siteUser)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SiteUser getWrappedSiteUser() {
		return _siteUser;
	}

	@Override
	public SiteUser getWrappedModel() {
		return _siteUser;
	}

	@Override
	public void resetOriginalValues() {
		_siteUser.resetOriginalValues();
	}

	private SiteUser _siteUser;
}