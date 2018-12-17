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
 * This class is a wrapper for {@link SimulationJob}.
 * </p>
 *
 * @author edison
 * @see SimulationJob
 * @generated
 */
public class SimulationJobWrapper implements SimulationJob,
	ModelWrapper<SimulationJob> {
	public SimulationJobWrapper(SimulationJob simulationJob) {
		_simulationJob = simulationJob;
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationJob.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationJob.class.getName();
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
	* Returns the primary key of this simulation job.
	*
	* @return the primary key of this simulation job
	*/
	@Override
	public org.kisti.edison.service.persistence.SimulationJobPK getPrimaryKey() {
		return _simulationJob.getPrimaryKey();
	}

	/**
	* Sets the primary key of this simulation job.
	*
	* @param primaryKey the primary key of this simulation job
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.service.persistence.SimulationJobPK primaryKey) {
		_simulationJob.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the create date of this simulation job.
	*
	* @return the create date of this simulation job
	*/
	@Override
	public java.lang.String getCreateDate() {
		return _simulationJob.getCreateDate();
	}

	/**
	* Sets the create date of this simulation job.
	*
	* @param createDate the create date of this simulation job
	*/
	@Override
	public void setCreateDate(java.lang.String createDate) {
		_simulationJob.setCreateDate(createDate);
	}

	/**
	* Returns the group ID of this simulation job.
	*
	* @return the group ID of this simulation job
	*/
	@Override
	public long getGroupId() {
		return _simulationJob.getGroupId();
	}

	/**
	* Sets the group ID of this simulation job.
	*
	* @param groupId the group ID of this simulation job
	*/
	@Override
	public void setGroupId(long groupId) {
		_simulationJob.setGroupId(groupId);
	}

	/**
	* Returns the cnt of this simulation job.
	*
	* @return the cnt of this simulation job
	*/
	@Override
	public java.lang.Long getCnt() {
		return _simulationJob.getCnt();
	}

	/**
	* Sets the cnt of this simulation job.
	*
	* @param cnt the cnt of this simulation job
	*/
	@Override
	public void setCnt(java.lang.Long cnt) {
		_simulationJob.setCnt(cnt);
	}

	@Override
	public boolean isNew() {
		return _simulationJob.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simulationJob.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simulationJob.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simulationJob.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simulationJob.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simulationJob.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simulationJob.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simulationJob.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simulationJob.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simulationJob.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simulationJob.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SimulationJobWrapper((SimulationJob)_simulationJob.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.SimulationJob simulationJob) {
		return _simulationJob.compareTo(simulationJob);
	}

	@Override
	public int hashCode() {
		return _simulationJob.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.SimulationJob> toCacheModel() {
		return _simulationJob.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.SimulationJob toEscapedModel() {
		return new SimulationJobWrapper(_simulationJob.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.SimulationJob toUnescapedModel() {
		return new SimulationJobWrapper(_simulationJob.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simulationJob.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simulationJob.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationJob.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationJobWrapper)) {
			return false;
		}

		SimulationJobWrapper simulationJobWrapper = (SimulationJobWrapper)obj;

		if (Validator.equals(_simulationJob, simulationJobWrapper._simulationJob)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SimulationJob getWrappedSimulationJob() {
		return _simulationJob;
	}

	@Override
	public SimulationJob getWrappedModel() {
		return _simulationJob;
	}

	@Override
	public void resetOriginalValues() {
		_simulationJob.resetOriginalValues();
	}

	private SimulationJob _simulationJob;
}