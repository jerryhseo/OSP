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
 * This class is a wrapper for {@link Contents}.
 * </p>
 *
 * @author edison
 * @see Contents
 * @generated
 */
public class ContentsWrapper implements Contents, ModelWrapper<Contents> {
	public ContentsWrapper(Contents contents) {
		_contents = contents;
	}

	@Override
	public Class<?> getModelClass() {
		return Contents.class;
	}

	@Override
	public String getModelClassName() {
		return Contents.class.getName();
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
	* Returns the primary key of this contents.
	*
	* @return the primary key of this contents
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _contents.getPrimaryKey();
	}

	/**
	* Sets the primary key of this contents.
	*
	* @param primaryKey the primary key of this contents
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_contents.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this contents.
	*
	* @return the create date of this contents
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _contents.getCreateDate();
	}

	/**
	* Sets the create date of this contents.
	*
	* @param createDate the create date of this contents
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_contents.setCreateDate(createDate);
	}

	/**
	* Returns the cnt of this contents.
	*
	* @return the cnt of this contents
	*/
	@Override
	public java.lang.Long getCnt() {
		return _contents.getCnt();
	}

	/**
	* Sets the cnt of this contents.
	*
	* @param cnt the cnt of this contents
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_contents.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _contents.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_contents.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _contents.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contents.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _contents.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _contents.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_contents.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _contents.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_contents.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_contents.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_contents.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ContentsWrapper((Contents)_contents.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.Contents contents) {
		return _contents.compareTo(contents);
	}

	@Override
	public int hashCode() {
		return _contents.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.Contents> toCacheModel() {
		return _contents.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.Contents toEscapedModel() {
		return new ContentsWrapper(_contents.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.Contents toUnescapedModel() {
		return new ContentsWrapper(_contents.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contents.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _contents.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_contents.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContentsWrapper)) {
			return false;
		}

		ContentsWrapper contentsWrapper = (ContentsWrapper)obj;

		if (Validator.equals(_contents, contentsWrapper._contents)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Contents getWrappedContents() {
		return _contents;
	}

	@Override
	public Contents getWrappedModel() {
		return _contents;
	}

	@Override
	public void resetOriginalValues() {
		_contents.resetOriginalValues();
	}

	private Contents _contents;
}