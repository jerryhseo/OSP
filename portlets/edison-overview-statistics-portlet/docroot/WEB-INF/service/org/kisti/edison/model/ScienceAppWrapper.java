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
 * This class is a wrapper for {@link ScienceApp}.
 * </p>
 *
 * @author edison
 * @see ScienceApp
 * @generated
 */
public class ScienceAppWrapper implements ScienceApp, ModelWrapper<ScienceApp> {
	public ScienceAppWrapper(ScienceApp scienceApp) {
		_scienceApp = scienceApp;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceApp.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceApp.class.getName();
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
	* Returns the primary key of this science app.
	*
	* @return the primary key of this science app
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _scienceApp.getPrimaryKey();
	}

	/**
	* Sets the primary key of this science app.
	*
	* @param primaryKey the primary key of this science app
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_scienceApp.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this science app.
	*
	* @return the create date of this science app
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _scienceApp.getCreateDate();
	}

	/**
	* Sets the create date of this science app.
	*
	* @param createDate the create date of this science app
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_scienceApp.setCreateDate(createDate);
	}

	/**
	* Returns the cnt of this science app.
	*
	* @return the cnt of this science app
	*/
	@Override
	public java.lang.Long getCnt() {
		return _scienceApp.getCnt();
	}

	/**
	* Sets the cnt of this science app.
	*
	* @param cnt the cnt of this science app
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_scienceApp.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _scienceApp.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_scienceApp.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _scienceApp.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceApp.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceApp.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _scienceApp.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_scienceApp.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _scienceApp.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_scienceApp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_scienceApp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_scienceApp.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ScienceAppWrapper((ScienceApp)_scienceApp.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.ScienceApp scienceApp) {
		return _scienceApp.compareTo(scienceApp);
	}

	@Override
	public int hashCode() {
		return _scienceApp.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.ScienceApp> toCacheModel() {
		return _scienceApp.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.ScienceApp toEscapedModel() {
		return new ScienceAppWrapper(_scienceApp.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.ScienceApp toUnescapedModel() {
		return new ScienceAppWrapper(_scienceApp.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _scienceApp.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scienceApp.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceApp.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppWrapper)) {
			return false;
		}

		ScienceAppWrapper scienceAppWrapper = (ScienceAppWrapper)obj;

		if (Validator.equals(_scienceApp, scienceAppWrapper._scienceApp)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ScienceApp getWrappedScienceApp() {
		return _scienceApp;
	}

	@Override
	public ScienceApp getWrappedModel() {
		return _scienceApp;
	}

	@Override
	public void resetOriginalValues() {
		_scienceApp.resetOriginalValues();
	}

	private ScienceApp _scienceApp;
}