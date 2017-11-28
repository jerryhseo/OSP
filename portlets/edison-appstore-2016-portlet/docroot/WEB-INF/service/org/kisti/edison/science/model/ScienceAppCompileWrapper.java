/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScienceAppCompile}.
 * </p>
 *
 * @author EDISON
 * @see ScienceAppCompile
 * @generated
 */
public class ScienceAppCompileWrapper implements ScienceAppCompile,
	ModelWrapper<ScienceAppCompile> {
	public ScienceAppCompileWrapper(ScienceAppCompile scienceAppCompile) {
		_scienceAppCompile = scienceAppCompile;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppCompile.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppCompile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("userId", getUserId());
		attributes.put("compileUrl", getCompileUrl());
		attributes.put("result", getResult());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String compileUrl = (String)attributes.get("compileUrl");

		if (compileUrl != null) {
			setCompileUrl(compileUrl);
		}

		String result = (String)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	* Returns the primary key of this science app compile.
	*
	* @return the primary key of this science app compile
	*/
	@Override
	public long getPrimaryKey() {
		return _scienceAppCompile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this science app compile.
	*
	* @param primaryKey the primary key of this science app compile
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_scienceAppCompile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the science app ID of this science app compile.
	*
	* @return the science app ID of this science app compile
	*/
	@Override
	public long getScienceAppId() {
		return _scienceAppCompile.getScienceAppId();
	}

	/**
	* Sets the science app ID of this science app compile.
	*
	* @param scienceAppId the science app ID of this science app compile
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppCompile.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the user ID of this science app compile.
	*
	* @return the user ID of this science app compile
	*/
	@Override
	public java.lang.Long getUserId() {
		return _scienceAppCompile.getUserId();
	}

	/**
	* Sets the user ID of this science app compile.
	*
	* @param userId the user ID of this science app compile
	*/
	@Override
	public void setUserId(java.lang.Long userId) {
		_scienceAppCompile.setUserId(userId);
	}

	/**
	* Returns the compile url of this science app compile.
	*
	* @return the compile url of this science app compile
	*/
	@Override
	public java.lang.String getCompileUrl() {
		return _scienceAppCompile.getCompileUrl();
	}

	/**
	* Sets the compile url of this science app compile.
	*
	* @param compileUrl the compile url of this science app compile
	*/
	@Override
	public void setCompileUrl(java.lang.String compileUrl) {
		_scienceAppCompile.setCompileUrl(compileUrl);
	}

	/**
	* Returns the result of this science app compile.
	*
	* @return the result of this science app compile
	*/
	@Override
	public java.lang.String getResult() {
		return _scienceAppCompile.getResult();
	}

	/**
	* Sets the result of this science app compile.
	*
	* @param result the result of this science app compile
	*/
	@Override
	public void setResult(java.lang.String result) {
		_scienceAppCompile.setResult(result);
	}

	/**
	* Returns the create date of this science app compile.
	*
	* @return the create date of this science app compile
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _scienceAppCompile.getCreateDate();
	}

	/**
	* Sets the create date of this science app compile.
	*
	* @param createDate the create date of this science app compile
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_scienceAppCompile.setCreateDate(createDate);
	}

	@Override
	public boolean isNew() {
		return _scienceAppCompile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_scienceAppCompile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _scienceAppCompile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceAppCompile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceAppCompile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _scienceAppCompile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_scienceAppCompile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _scienceAppCompile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_scienceAppCompile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_scienceAppCompile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_scienceAppCompile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ScienceAppCompileWrapper((ScienceAppCompile)_scienceAppCompile.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.science.model.ScienceAppCompile scienceAppCompile) {
		return _scienceAppCompile.compareTo(scienceAppCompile);
	}

	@Override
	public int hashCode() {
		return _scienceAppCompile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.science.model.ScienceAppCompile> toCacheModel() {
		return _scienceAppCompile.toCacheModel();
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppCompile toEscapedModel() {
		return new ScienceAppCompileWrapper(_scienceAppCompile.toEscapedModel());
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppCompile toUnescapedModel() {
		return new ScienceAppCompileWrapper(_scienceAppCompile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _scienceAppCompile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scienceAppCompile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppCompile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppCompileWrapper)) {
			return false;
		}

		ScienceAppCompileWrapper scienceAppCompileWrapper = (ScienceAppCompileWrapper)obj;

		if (Validator.equals(_scienceAppCompile,
					scienceAppCompileWrapper._scienceAppCompile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ScienceAppCompile getWrappedScienceAppCompile() {
		return _scienceAppCompile;
	}

	@Override
	public ScienceAppCompile getWrappedModel() {
		return _scienceAppCompile;
	}

	@Override
	public void resetOriginalValues() {
		_scienceAppCompile.resetOriginalValues();
	}

	private ScienceAppCompile _scienceAppCompile;
}