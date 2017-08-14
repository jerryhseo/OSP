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

package org.kisti.edison.search.service.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Search}.
 * </p>
 *
 * @author yjpark
 * @see Search
 * @generated
 */
public class SearchWrapper implements Search, ModelWrapper<Search> {
	public SearchWrapper(Search search) {
		_search = search;
	}

	@Override
	public Class<?> getModelClass() {
		return Search.class;
	}

	@Override
	public String getModelClassName() {
		return Search.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}
	}

	/**
	* Returns the primary key of this search.
	*
	* @return the primary key of this search
	*/
	@Override
	public long getPrimaryKey() {
		return _search.getPrimaryKey();
	}

	/**
	* Sets the primary key of this search.
	*
	* @param primaryKey the primary key of this search
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_search.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this search.
	*
	* @return the ID of this search
	*/
	@Override
	public long getId() {
		return _search.getId();
	}

	/**
	* Sets the ID of this search.
	*
	* @param id the ID of this search
	*/
	@Override
	public void setId(long id) {
		_search.setId(id);
	}

	@Override
	public boolean isNew() {
		return _search.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_search.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _search.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_search.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _search.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _search.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_search.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _search.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_search.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_search.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_search.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SearchWrapper((Search)_search.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.search.service.model.Search search) {
		return _search.compareTo(search);
	}

	@Override
	public int hashCode() {
		return _search.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.search.service.model.Search> toCacheModel() {
		return _search.toCacheModel();
	}

	@Override
	public org.kisti.edison.search.service.model.Search toEscapedModel() {
		return new SearchWrapper(_search.toEscapedModel());
	}

	@Override
	public org.kisti.edison.search.service.model.Search toUnescapedModel() {
		return new SearchWrapper(_search.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _search.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _search.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_search.persist();
	}

	@Override
	public int getAppCount() {
		return _search.getAppCount();
	}

	@Override
	public void setAppCount(int appCount) {
		_search.setAppCount(appCount);
	}

	@Override
	public int getContentCount() {
		return _search.getContentCount();
	}

	@Override
	public void setContentCount(int contentCount) {
		_search.setContentCount(contentCount);
	}

	@Override
	public int getProjectCount() {
		return _search.getProjectCount();
	}

	@Override
	public void setProjectCount(int projectCount) {
		_search.setProjectCount(projectCount);
	}

	@Override
	public int getDataCount() {
		return _search.getDataCount();
	}

	@Override
	public void setDataCount(int dataCount) {
		_search.setDataCount(dataCount);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getAppResults() {
		return _search.getAppResults();
	}

	@Override
	public void setAppResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> appResults) {
		_search.setAppResults(appResults);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getContentResults() {
		return _search.getContentResults();
	}

	@Override
	public void setContentResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> contentResults) {
		_search.setContentResults(contentResults);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getProjectResults() {
		return _search.getProjectResults();
	}

	@Override
	public void setProjectResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> projectResults) {
		_search.setProjectResults(projectResults);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getDataResults() {
		return _search.getDataResults();
	}

	@Override
	public void setDataResults(
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> dataResults) {
		_search.setDataResults(dataResults);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchWrapper)) {
			return false;
		}

		SearchWrapper searchWrapper = (SearchWrapper)obj;

		if (Validator.equals(_search, searchWrapper._search)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Search getWrappedSearch() {
		return _search;
	}

	@Override
	public Search getWrappedModel() {
		return _search;
	}

	@Override
	public void resetOriginalValues() {
		_search.resetOriginalValues();
	}

	private Search _search;
}