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
 * This class is a wrapper for {@link SimProScienceAppLink}.
 * </p>
 *
 * @author edison
 * @see SimProScienceAppLink
 * @generated
 */
public class SimProScienceAppLinkWrapper implements SimProScienceAppLink,
	ModelWrapper<SimProScienceAppLink> {
	public SimProScienceAppLinkWrapper(
		SimProScienceAppLink simProScienceAppLink) {
		_simProScienceAppLink = simProScienceAppLink;
	}

	@Override
	public Class<?> getModelClass() {
		return SimProScienceAppLink.class;
	}

	@Override
	public String getModelClassName() {
		return SimProScienceAppLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("simProScienceAppLinkId", getSimProScienceAppLinkId());
		attributes.put("simulationProjectId", getSimulationProjectId());
		attributes.put("scienceAppId", getScienceAppId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long simProScienceAppLinkId = (Long)attributes.get(
				"simProScienceAppLinkId");

		if (simProScienceAppLinkId != null) {
			setSimProScienceAppLinkId(simProScienceAppLinkId);
		}

		Long simulationProjectId = (Long)attributes.get("simulationProjectId");

		if (simulationProjectId != null) {
			setSimulationProjectId(simulationProjectId);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}
	}

	/**
	* Returns the primary key of this sim pro science app link.
	*
	* @return the primary key of this sim pro science app link
	*/
	@Override
	public org.kisti.edison.service.persistence.SimProScienceAppLinkPK getPrimaryKey() {
		return _simProScienceAppLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this sim pro science app link.
	*
	* @param primaryKey the primary key of this sim pro science app link
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK primaryKey) {
		_simProScienceAppLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the sim pro science app link ID of this sim pro science app link.
	*
	* @return the sim pro science app link ID of this sim pro science app link
	*/
	@Override
	public long getSimProScienceAppLinkId() {
		return _simProScienceAppLink.getSimProScienceAppLinkId();
	}

	/**
	* Sets the sim pro science app link ID of this sim pro science app link.
	*
	* @param simProScienceAppLinkId the sim pro science app link ID of this sim pro science app link
	*/
	@Override
	public void setSimProScienceAppLinkId(long simProScienceAppLinkId) {
		_simProScienceAppLink.setSimProScienceAppLinkId(simProScienceAppLinkId);
	}

	/**
	* Returns the simulation project ID of this sim pro science app link.
	*
	* @return the simulation project ID of this sim pro science app link
	*/
	@Override
	public long getSimulationProjectId() {
		return _simProScienceAppLink.getSimulationProjectId();
	}

	/**
	* Sets the simulation project ID of this sim pro science app link.
	*
	* @param simulationProjectId the simulation project ID of this sim pro science app link
	*/
	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_simProScienceAppLink.setSimulationProjectId(simulationProjectId);
	}

	/**
	* Returns the science app ID of this sim pro science app link.
	*
	* @return the science app ID of this sim pro science app link
	*/
	@Override
	public java.lang.Long getScienceAppId() {
		return _simProScienceAppLink.getScienceAppId();
	}

	/**
	* Sets the science app ID of this sim pro science app link.
	*
	* @param scienceAppId the science app ID of this sim pro science app link
	*/
	@Override
	public void setScienceAppId(java.lang.Long scienceAppId) {
		_simProScienceAppLink.setScienceAppId(scienceAppId);
	}

	@Override
	public boolean isNew() {
		return _simProScienceAppLink.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_simProScienceAppLink.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _simProScienceAppLink.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_simProScienceAppLink.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _simProScienceAppLink.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _simProScienceAppLink.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_simProScienceAppLink.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _simProScienceAppLink.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_simProScienceAppLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_simProScienceAppLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_simProScienceAppLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SimProScienceAppLinkWrapper((SimProScienceAppLink)_simProScienceAppLink.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink) {
		return _simProScienceAppLink.compareTo(simProScienceAppLink);
	}

	@Override
	public int hashCode() {
		return _simProScienceAppLink.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.SimProScienceAppLink> toCacheModel() {
		return _simProScienceAppLink.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.SimProScienceAppLink toEscapedModel() {
		return new SimProScienceAppLinkWrapper(_simProScienceAppLink.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.SimProScienceAppLink toUnescapedModel() {
		return new SimProScienceAppLinkWrapper(_simProScienceAppLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _simProScienceAppLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _simProScienceAppLink.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_simProScienceAppLink.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SimProScienceAppLinkWrapper)) {
			return false;
		}

		SimProScienceAppLinkWrapper simProScienceAppLinkWrapper = (SimProScienceAppLinkWrapper)obj;

		if (Validator.equals(_simProScienceAppLink,
					simProScienceAppLinkWrapper._simProScienceAppLink)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SimProScienceAppLink getWrappedSimProScienceAppLink() {
		return _simProScienceAppLink;
	}

	@Override
	public SimProScienceAppLink getWrappedModel() {
		return _simProScienceAppLink;
	}

	@Override
	public void resetOriginalValues() {
		_simProScienceAppLink.resetOriginalValues();
	}

	private SimProScienceAppLink _simProScienceAppLink;
}