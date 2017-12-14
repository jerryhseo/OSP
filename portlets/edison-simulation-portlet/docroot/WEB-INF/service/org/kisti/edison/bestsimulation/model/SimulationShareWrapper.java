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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SimulationShare}.
 * </p>
 *
 * @author EDISON
 * @see SimulationShare
 * @generated
 */
public class SimulationShareWrapper implements SimulationShare,
	ModelWrapper<SimulationShare> {
	public SimulationShareWrapper(SimulationShare simulationShare) {
		_simulationShare = simulationShare;
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationShare.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationShare.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("shareSeqno", getShareSeqno());
		attributes.put("jobSeqNo", getJobSeqNo());
		attributes.put("jobUuid", getJobUuid());
		attributes.put("simulationUuid", getSimulationUuid());
		attributes.put("classId", getClassId());
		attributes.put("customId", getCustomId());
		attributes.put("simulationShareDt", getSimulationShareDt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long shareSeqno = (Long)attributes.get("shareSeqno");

		if (shareSeqno != null) {
			setShareSeqno(shareSeqno);
		}

		Long jobSeqNo = (Long)attributes.get("jobSeqNo");

		if (jobSeqNo != null) {
			setJobSeqNo(jobSeqNo);
		}

		String jobUuid = (String)attributes.get("jobUuid");

		if (jobUuid != null) {
			setJobUuid(jobUuid);
		}

		String simulationUuid = (String)attributes.get("simulationUuid");

		if (simulationUuid != null) {
			setSimulationUuid(simulationUuid);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long customId = (Long)attributes.get("customId");

		if (customId != null) {
			setCustomId(customId);
		}

		Date simulationShareDt = (Date)attributes.get("simulationShareDt");

		if (simulationShareDt != null) {
			setSimulationShareDt(simulationShareDt);
		}
	}

	/**
	* Returns the primary key of this simulation share.
	*
	* @return the primary key of this simulation share
	*/
	@Override
	public org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK getPrimaryKey() {
		return _simulationShare.getPrimaryKey();
	}

	/**
	* Sets the primary key of this simulation share.
	*
	* @param primaryKey the primary key of this simulation share
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK primaryKey) {
		_simulationShare.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the share seqno of this simulation share.
	*
	* @return the share seqno of this simulation share
	*/
	@Override
	public long getShareSeqno() {
		return _simulationShare.getShareSeqno();
	}

	/**
	* Sets the share seqno of this simulation share.
	*
	* @param shareSeqno the share seqno of this simulation share
	*/
	@Override
	public void setShareSeqno(long shareSeqno) {
		_simulationShare.setShareSeqno(shareSeqno);
	}

	/**
	* Returns the job seq no of this simulation share.
	*
	* @return the job seq no of this simulation share
	*/
	@Override
	public long getJobSeqNo() {
		return _simulationShare.getJobSeqNo();
	}

	/**
	* Sets the job seq no of this simulation share.
	*
	* @param jobSeqNo the job seq no of this simulation share
	*/
	@Override
	public void setJobSeqNo(long jobSeqNo) {
		_simulationShare.setJobSeqNo(jobSeqNo);
	}

	/**
	* Returns the job uuid of this simulation share.
	*
	* @return the job uuid of this simulation share
	*/
	@Override
	public java.lang.String getJobUuid() {
		return _simulationShare.getJobUuid();
	}

	/**
	* Sets the job uuid of this simulation share.
	*
	* @param jobUuid the job uuid of this simulation share
	*/
	@Override
	public void setJobUuid(java.lang.String jobUuid) {
		_simulationShare.setJobUuid(jobUuid);
	}

	/**
	* Returns the simulation uuid of this simulation share.
	*
	* @return the simulation uuid of this simulation share
	*/
	@Override
	public java.lang.String getSimulationUuid() {
		return _simulationShare.getSimulationUuid();
	}

	/**
	* Sets the simulation uuid of this simulation share.
	*
	* @param simulationUuid the simulation uuid of this simulation share
	*/
	@Override
	public void setSimulationUuid(java.lang.String simulationUuid) {
		_simulationShare.setSimulationUuid(simulationUuid);
	}

	/**
	* Returns the class ID of this simulation share.
	*
	* @return the class ID of this simulation share
	*/
	@Override
	public long getClassId() {
		return _simulationShare.getClassId();
	}

	/**
	* Sets the class ID of this simulation share.
	*
	* @param classId the class ID of this simulation share
	*/
	@Override
	public void setClassId(long classId) {
		_simulationShare.setClassId(classId);
	}

	/**
	* Returns the custom ID of this simulation share.
	*
	* @return the custom ID of this simulation share
	*/
	@Override
	public long getCustomId() {
		return _simulationShare.getCustomId();
	}

	/**
	* Sets the custom ID of this simulation share.
	*
	* @param customId the custom ID of this simulation share
	*/
	@Override
	public void setCustomId(long customId) {
		_simulationShare.setCustomId(customId);
	}

	/**
	* Returns the simulation share dt of this simulation share.
	*
	* @return the simulation share dt of this simulation share
	*/
	@Override
	public java.util.Date getSimulationShareDt() {
		return _simulationShare.getSimulationShareDt();
	}

	/**
	* Sets the simulation share dt of this simulation share.
	*
	* @param simulationShareDt the simulation share dt of this simulation share
	*/
	@Override
	public void setSimulationShareDt(java.util.Date simulationShareDt) {
		_simulationShare.setSimulationShareDt(simulationShareDt);
	}

	@Override
	public boolean isNew() {
		return _simulationShare.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simulationShare.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simulationShare.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simulationShare.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simulationShare.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simulationShare.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simulationShare.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simulationShare.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simulationShare.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simulationShare.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simulationShare.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SimulationShareWrapper((SimulationShare)_simulationShare.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare) {
		return _simulationShare.compareTo(simulationShare);
	}

	@Override
	public int hashCode() {
		return _simulationShare.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.bestsimulation.model.SimulationShare> toCacheModel() {
		return _simulationShare.toCacheModel();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare toEscapedModel() {
		return new SimulationShareWrapper(_simulationShare.toEscapedModel());
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare toUnescapedModel() {
		return new SimulationShareWrapper(_simulationShare.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simulationShare.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simulationShare.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationShare.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimulationShareWrapper)) {
			return false;
		}

		SimulationShareWrapper simulationShareWrapper = (SimulationShareWrapper)obj;

		if (Validator.equals(_simulationShare,
					simulationShareWrapper._simulationShare)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SimulationShare getWrappedSimulationShare() {
		return _simulationShare;
	}

	@Override
	public SimulationShare getWrappedModel() {
		return _simulationShare;
	}

	@Override
	public void resetOriginalValues() {
		_simulationShare.resetOriginalValues();
	}

	private SimulationShare _simulationShare;
}