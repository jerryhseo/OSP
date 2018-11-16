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

package org.kisti.edison.osp.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Execute}.
 * </p>
 *
 * @author edison
 * @see Execute
 * @generated
 */
public class ExecuteWrapper implements Execute, ModelWrapper<Execute> {
	public ExecuteWrapper(Execute execute) {
		_execute = execute;
	}

	@Override
	public Class<?> getModelClass() {
		return Execute.class;
	}

	@Override
	public String getModelClassName() {
		return Execute.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectId", getProjectId());
		attributes.put("executeId", getExecuteId());
		attributes.put("executeDataStructure", getExecuteDataStructure());
		attributes.put("executeDate", getExecuteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String executeId = (String)attributes.get("executeId");

		if (executeId != null) {
			setExecuteId(executeId);
		}

		String executeDataStructure = (String)attributes.get(
				"executeDataStructure");

		if (executeDataStructure != null) {
			setExecuteDataStructure(executeDataStructure);
		}

		Date executeDate = (Date)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}
	}

	/**
	* Returns the primary key of this execute.
	*
	* @return the primary key of this execute
	*/
	@Override
	public org.kisti.edison.osp.service.persistence.ExecutePK getPrimaryKey() {
		return _execute.getPrimaryKey();
	}

	/**
	* Sets the primary key of this execute.
	*
	* @param primaryKey the primary key of this execute
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.osp.service.persistence.ExecutePK primaryKey) {
		_execute.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the project ID of this execute.
	*
	* @return the project ID of this execute
	*/
	@Override
	public long getProjectId() {
		return _execute.getProjectId();
	}

	/**
	* Sets the project ID of this execute.
	*
	* @param projectId the project ID of this execute
	*/
	@Override
	public void setProjectId(long projectId) {
		_execute.setProjectId(projectId);
	}

	/**
	* Returns the execute ID of this execute.
	*
	* @return the execute ID of this execute
	*/
	@Override
	public java.lang.String getExecuteId() {
		return _execute.getExecuteId();
	}

	/**
	* Sets the execute ID of this execute.
	*
	* @param executeId the execute ID of this execute
	*/
	@Override
	public void setExecuteId(java.lang.String executeId) {
		_execute.setExecuteId(executeId);
	}

	/**
	* Returns the execute data structure of this execute.
	*
	* @return the execute data structure of this execute
	*/
	@Override
	public java.lang.String getExecuteDataStructure() {
		return _execute.getExecuteDataStructure();
	}

	/**
	* Sets the execute data structure of this execute.
	*
	* @param executeDataStructure the execute data structure of this execute
	*/
	@Override
	public void setExecuteDataStructure(java.lang.String executeDataStructure) {
		_execute.setExecuteDataStructure(executeDataStructure);
	}

	/**
	* Returns the execute date of this execute.
	*
	* @return the execute date of this execute
	*/
	@Override
	public java.util.Date getExecuteDate() {
		return _execute.getExecuteDate();
	}

	/**
	* Sets the execute date of this execute.
	*
	* @param executeDate the execute date of this execute
	*/
	@Override
	public void setExecuteDate(java.util.Date executeDate) {
		_execute.setExecuteDate(executeDate);
	}

	@Override
	public boolean isNew() {
		return _execute.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_execute.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _execute.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_execute.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _execute.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _execute.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_execute.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _execute.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_execute.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_execute.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_execute.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExecuteWrapper((Execute)_execute.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.osp.model.Execute execute) {
		return _execute.compareTo(execute);
	}

	@Override
	public int hashCode() {
		return _execute.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.osp.model.Execute> toCacheModel() {
		return _execute.toCacheModel();
	}

	@Override
	public org.kisti.edison.osp.model.Execute toEscapedModel() {
		return new ExecuteWrapper(_execute.toEscapedModel());
	}

	@Override
	public org.kisti.edison.osp.model.Execute toUnescapedModel() {
		return new ExecuteWrapper(_execute.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _execute.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _execute.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_execute.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExecuteWrapper)) {
			return false;
		}

		ExecuteWrapper executeWrapper = (ExecuteWrapper)obj;

		if (Validator.equals(_execute, executeWrapper._execute)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Execute getWrappedExecute() {
		return _execute;
	}

	@Override
	public Execute getWrappedModel() {
		return _execute;
	}

	@Override
	public void resetOriginalValues() {
		_execute.resetOriginalValues();
	}

	private Execute _execute;
}