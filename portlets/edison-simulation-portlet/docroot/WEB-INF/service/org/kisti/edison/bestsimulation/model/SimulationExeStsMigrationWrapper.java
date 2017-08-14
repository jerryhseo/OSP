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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SimulationExeStsMigration}.
 * </p>
 *
 * @author EDISON
 * @see SimulationExeStsMigration
 * @generated
 */
public class SimulationExeStsMigrationWrapper
	implements SimulationExeStsMigration,
		ModelWrapper<SimulationExeStsMigration> {
	public SimulationExeStsMigrationWrapper(
		SimulationExeStsMigration simulationExeStsMigration) {
		_simulationExeStsMigration = simulationExeStsMigration;
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationExeStsMigration.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationExeStsMigration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("groupId", getGroupId());
		attributes.put("submitDate", getSubmitDate());
		attributes.put("userCnt", getUserCnt());
		attributes.put("jobCnt", getJobCnt());
		attributes.put("runtime", getRuntime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String submitDate = (String)attributes.get("submitDate");

		if (submitDate != null) {
			setSubmitDate(submitDate);
		}

		Long userCnt = (Long)attributes.get("userCnt");

		if (userCnt != null) {
			setUserCnt(userCnt);
		}

		Long jobCnt = (Long)attributes.get("jobCnt");

		if (jobCnt != null) {
			setJobCnt(jobCnt);
		}

		Long runtime = (Long)attributes.get("runtime");

		if (runtime != null) {
			setRuntime(runtime);
		}
	}

	/**
	* Returns the primary key of this simulation exe sts migration.
	*
	* @return the primary key of this simulation exe sts migration
	*/
	@Override
	public org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK getPrimaryKey() {
		return _simulationExeStsMigration.getPrimaryKey();
	}

	/**
	* Sets the primary key of this simulation exe sts migration.
	*
	* @param primaryKey the primary key of this simulation exe sts migration
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK primaryKey) {
		_simulationExeStsMigration.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the science app ID of this simulation exe sts migration.
	*
	* @return the science app ID of this simulation exe sts migration
	*/
	@Override
	public long getScienceAppId() {
		return _simulationExeStsMigration.getScienceAppId();
	}

	/**
	* Sets the science app ID of this simulation exe sts migration.
	*
	* @param scienceAppId the science app ID of this simulation exe sts migration
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_simulationExeStsMigration.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the group ID of this simulation exe sts migration.
	*
	* @return the group ID of this simulation exe sts migration
	*/
	@Override
	public long getGroupId() {
		return _simulationExeStsMigration.getGroupId();
	}

	/**
	* Sets the group ID of this simulation exe sts migration.
	*
	* @param groupId the group ID of this simulation exe sts migration
	*/
	@Override
	public void setGroupId(long groupId) {
		_simulationExeStsMigration.setGroupId(groupId);
	}

	/**
	* Returns the submit date of this simulation exe sts migration.
	*
	* @return the submit date of this simulation exe sts migration
	*/
	@Override
	public java.lang.String getSubmitDate() {
		return _simulationExeStsMigration.getSubmitDate();
	}

	/**
	* Sets the submit date of this simulation exe sts migration.
	*
	* @param submitDate the submit date of this simulation exe sts migration
	*/
	@Override
	public void setSubmitDate(java.lang.String submitDate) {
		_simulationExeStsMigration.setSubmitDate(submitDate);
	}

	/**
	* Returns the user cnt of this simulation exe sts migration.
	*
	* @return the user cnt of this simulation exe sts migration
	*/
	@Override
	public long getUserCnt() {
		return _simulationExeStsMigration.getUserCnt();
	}

	/**
	* Sets the user cnt of this simulation exe sts migration.
	*
	* @param userCnt the user cnt of this simulation exe sts migration
	*/
	@Override
	public void setUserCnt(long userCnt) {
		_simulationExeStsMigration.setUserCnt(userCnt);
	}

	/**
	* Returns the job cnt of this simulation exe sts migration.
	*
	* @return the job cnt of this simulation exe sts migration
	*/
	@Override
	public long getJobCnt() {
		return _simulationExeStsMigration.getJobCnt();
	}

	/**
	* Sets the job cnt of this simulation exe sts migration.
	*
	* @param jobCnt the job cnt of this simulation exe sts migration
	*/
	@Override
	public void setJobCnt(long jobCnt) {
		_simulationExeStsMigration.setJobCnt(jobCnt);
	}

	/**
	* Returns the runtime of this simulation exe sts migration.
	*
	* @return the runtime of this simulation exe sts migration
	*/
	@Override
	public long getRuntime() {
		return _simulationExeStsMigration.getRuntime();
	}

	/**
	* Sets the runtime of this simulation exe sts migration.
	*
	* @param runtime the runtime of this simulation exe sts migration
	*/
	@Override
	public void setRuntime(long runtime) {
		_simulationExeStsMigration.setRuntime(runtime);
	}

	@Override
	public boolean isNew() {
		return _simulationExeStsMigration.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simulationExeStsMigration.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simulationExeStsMigration.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simulationExeStsMigration.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simulationExeStsMigration.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simulationExeStsMigration.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simulationExeStsMigration.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simulationExeStsMigration.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simulationExeStsMigration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simulationExeStsMigration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simulationExeStsMigration.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SimulationExeStsMigrationWrapper((SimulationExeStsMigration)_simulationExeStsMigration.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.SimulationExeStsMigration simulationExeStsMigration) {
		return _simulationExeStsMigration.compareTo(simulationExeStsMigration);
	}

	@Override
	public int hashCode() {
		return _simulationExeStsMigration.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.bestsimulation.model.SimulationExeStsMigration> toCacheModel() {
		return _simulationExeStsMigration.toCacheModel();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration toEscapedModel() {
		return new SimulationExeStsMigrationWrapper(_simulationExeStsMigration.toEscapedModel());
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration toUnescapedModel() {
		return new SimulationExeStsMigrationWrapper(_simulationExeStsMigration.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simulationExeStsMigration.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simulationExeStsMigration.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationExeStsMigration.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationExeStsMigrationWrapper)) {
			return false;
		}

		SimulationExeStsMigrationWrapper simulationExeStsMigrationWrapper = (SimulationExeStsMigrationWrapper)obj;

		if (Validator.equals(_simulationExeStsMigration,
					simulationExeStsMigrationWrapper._simulationExeStsMigration)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SimulationExeStsMigration getWrappedSimulationExeStsMigration() {
		return _simulationExeStsMigration;
	}

	@Override
	public SimulationExeStsMigration getWrappedModel() {
		return _simulationExeStsMigration;
	}

	@Override
	public void resetOriginalValues() {
		_simulationExeStsMigration.resetOriginalValues();
	}

	private SimulationExeStsMigration _simulationExeStsMigration;
}