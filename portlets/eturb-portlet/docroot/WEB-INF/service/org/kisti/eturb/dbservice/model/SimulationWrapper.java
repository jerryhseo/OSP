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

package org.kisti.eturb.dbservice.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Simulation}.
 * </p>
 *
 * @author EDISON
 * @see Simulation
 * @generated
 */
public class SimulationWrapper implements Simulation, ModelWrapper<Simulation> {
	public SimulationWrapper(Simulation simulation) {
		_simulation = simulation;
	}

	@Override
	public Class<?> getModelClass() {
		return Simulation.class;
	}

	@Override
	public String getModelClassName() {
		return Simulation.class.getName();
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
	* Returns the primary key of this simulation.
	*
	* @return the primary key of this simulation
	*/
	@Override
	public org.kisti.eturb.dbservice.service.persistence.SimulationPK getPrimaryKey() {
		return _simulation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this simulation.
	*
	* @param primaryKey the primary key of this simulation
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK primaryKey) {
		_simulation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the project ID of this simulation.
	*
	* @return the project ID of this simulation
	*/
	@Override
	public long getProjectId() {
		return _simulation.getProjectId();
	}

	/**
	* Sets the project ID of this simulation.
	*
	* @param projectId the project ID of this simulation
	*/
	@Override
	public void setProjectId(long projectId) {
		_simulation.setProjectId(projectId);
	}

	/**
	* Returns the execute ID of this simulation.
	*
	* @return the execute ID of this simulation
	*/
	@Override
	public java.lang.String getExecuteId() {
		return _simulation.getExecuteId();
	}

	/**
	* Sets the execute ID of this simulation.
	*
	* @param executeId the execute ID of this simulation
	*/
	@Override
	public void setExecuteId(java.lang.String executeId) {
		_simulation.setExecuteId(executeId);
	}

	/**
	* Returns the execute data structure of this simulation.
	*
	* @return the execute data structure of this simulation
	*/
	@Override
	public java.lang.String getExecuteDataStructure() {
		return _simulation.getExecuteDataStructure();
	}

	/**
	* Sets the execute data structure of this simulation.
	*
	* @param executeDataStructure the execute data structure of this simulation
	*/
	@Override
	public void setExecuteDataStructure(java.lang.String executeDataStructure) {
		_simulation.setExecuteDataStructure(executeDataStructure);
	}

	/**
	* Returns the execute date of this simulation.
	*
	* @return the execute date of this simulation
	*/
	@Override
	public java.util.Date getExecuteDate() {
		return _simulation.getExecuteDate();
	}

	/**
	* Sets the execute date of this simulation.
	*
	* @param executeDate the execute date of this simulation
	*/
	@Override
	public void setExecuteDate(java.util.Date executeDate) {
		_simulation.setExecuteDate(executeDate);
	}

	@Override
	public boolean isNew() {
		return _simulation.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simulation.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simulation.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simulation.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simulation.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simulation.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simulation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simulation.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simulation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simulation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simulation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SimulationWrapper((Simulation)_simulation.clone());
	}

	@Override
	public int compareTo(org.kisti.eturb.dbservice.model.Simulation simulation) {
		return _simulation.compareTo(simulation);
	}

	@Override
	public int hashCode() {
		return _simulation.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.eturb.dbservice.model.Simulation> toCacheModel() {
		return _simulation.toCacheModel();
	}

	@Override
	public org.kisti.eturb.dbservice.model.Simulation toEscapedModel() {
		return new SimulationWrapper(_simulation.toEscapedModel());
	}

	@Override
	public org.kisti.eturb.dbservice.model.Simulation toUnescapedModel() {
		return new SimulationWrapper(_simulation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simulation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simulation.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulation.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationWrapper)) {
			return false;
		}

		SimulationWrapper simulationWrapper = (SimulationWrapper)obj;

		if (Validator.equals(_simulation, simulationWrapper._simulation)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Simulation getWrappedSimulation() {
		return _simulation;
	}

	@Override
	public Simulation getWrappedModel() {
		return _simulation;
	}

	@Override
	public void resetOriginalValues() {
		_simulation.resetOriginalValues();
	}

	private Simulation _simulation;
}