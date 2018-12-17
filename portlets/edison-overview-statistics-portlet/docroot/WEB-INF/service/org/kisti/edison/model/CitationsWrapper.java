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
 * This class is a wrapper for {@link Citations}.
 * </p>
 *
 * @author edison
 * @see Citations
 * @generated
 */
public class CitationsWrapper implements Citations, ModelWrapper<Citations> {
	public CitationsWrapper(Citations citations) {
		_citations = citations;
	}

	@Override
	public Class<?> getModelClass() {
		return Citations.class;
	}

	@Override
	public String getModelClassName() {
		return Citations.class.getName();
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
	* Returns the primary key of this citations.
	*
	* @return the primary key of this citations
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _citations.getPrimaryKey();
	}

	/**
	* Sets the primary key of this citations.
	*
	* @param primaryKey the primary key of this citations
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_citations.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this citations.
	*
	* @return the create date of this citations
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _citations.getCreateDate();
	}

	/**
	* Sets the create date of this citations.
	*
	* @param createDate the create date of this citations
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_citations.setCreateDate(createDate);
	}

	/**
	* Returns the cnt of this citations.
	*
	* @return the cnt of this citations
	*/
	@Override
	public java.lang.Long getCnt() {
		return _citations.getCnt();
	}

	/**
	* Sets the cnt of this citations.
	*
	* @param cnt the cnt of this citations
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_citations.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _citations.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_citations.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _citations.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_citations.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _citations.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _citations.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_citations.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _citations.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_citations.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_citations.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_citations.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CitationsWrapper((Citations)_citations.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.Citations citations) {
		return _citations.compareTo(citations);
	}

	@Override
	public int hashCode() {
		return _citations.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.Citations> toCacheModel() {
		return _citations.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.Citations toEscapedModel() {
		return new CitationsWrapper(_citations.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.Citations toUnescapedModel() {
		return new CitationsWrapper(_citations.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _citations.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _citations.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_citations.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CitationsWrapper)) {
			return false;
		}

		CitationsWrapper citationsWrapper = (CitationsWrapper)obj;

		if (Validator.equals(_citations, citationsWrapper._citations)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Citations getWrappedCitations() {
		return _citations;
	}

	@Override
	public Citations getWrappedModel() {
		return _citations;
	}

	@Override
	public void resetOriginalValues() {
		_citations.resetOriginalValues();
	}

	private Citations _citations;
}