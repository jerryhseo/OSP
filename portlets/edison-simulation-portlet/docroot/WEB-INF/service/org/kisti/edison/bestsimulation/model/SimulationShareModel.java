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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SimulationShare service. Represents a row in the &quot;EDSIM_SimulationShare&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareImpl}.
 * </p>
 *
 * @author EDISON
 * @see SimulationShare
 * @see org.kisti.edison.bestsimulation.model.impl.SimulationShareImpl
 * @see org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl
 * @generated
 */
public interface SimulationShareModel extends BaseModel<SimulationShare> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a simulation share model instance should use the {@link SimulationShare} interface instead.
	 */

	/**
	 * Returns the primary key of this simulation share.
	 *
	 * @return the primary key of this simulation share
	 */
	public SimulationSharePK getPrimaryKey();

	/**
	 * Sets the primary key of this simulation share.
	 *
	 * @param primaryKey the primary key of this simulation share
	 */
	public void setPrimaryKey(SimulationSharePK primaryKey);

	/**
	 * Returns the share seqno of this simulation share.
	 *
	 * @return the share seqno of this simulation share
	 */
	public long getShareSeqno();

	/**
	 * Sets the share seqno of this simulation share.
	 *
	 * @param shareSeqno the share seqno of this simulation share
	 */
	public void setShareSeqno(long shareSeqno);

	/**
	 * Returns the job seq no of this simulation share.
	 *
	 * @return the job seq no of this simulation share
	 */
	public long getJobSeqNo();

	/**
	 * Sets the job seq no of this simulation share.
	 *
	 * @param jobSeqNo the job seq no of this simulation share
	 */
	public void setJobSeqNo(long jobSeqNo);

	/**
	 * Returns the job uuid of this simulation share.
	 *
	 * @return the job uuid of this simulation share
	 */
	@AutoEscape
	public String getJobUuid();

	/**
	 * Sets the job uuid of this simulation share.
	 *
	 * @param jobUuid the job uuid of this simulation share
	 */
	public void setJobUuid(String jobUuid);

	/**
	 * Returns the simulation uuid of this simulation share.
	 *
	 * @return the simulation uuid of this simulation share
	 */
	@AutoEscape
	public String getSimulationUuid();

	/**
	 * Sets the simulation uuid of this simulation share.
	 *
	 * @param simulationUuid the simulation uuid of this simulation share
	 */
	public void setSimulationUuid(String simulationUuid);

	/**
	 * Returns the class ID of this simulation share.
	 *
	 * @return the class ID of this simulation share
	 */
	public long getClassId();

	/**
	 * Sets the class ID of this simulation share.
	 *
	 * @param classId the class ID of this simulation share
	 */
	public void setClassId(long classId);

	/**
	 * Returns the custom ID of this simulation share.
	 *
	 * @return the custom ID of this simulation share
	 */
	public long getCustomId();

	/**
	 * Sets the custom ID of this simulation share.
	 *
	 * @param customId the custom ID of this simulation share
	 */
	public void setCustomId(long customId);

	/**
	 * Returns the simulation share dt of this simulation share.
	 *
	 * @return the simulation share dt of this simulation share
	 */
	public Date getSimulationShareDt();

	/**
	 * Sets the simulation share dt of this simulation share.
	 *
	 * @param simulationShareDt the simulation share dt of this simulation share
	 */
	public void setSimulationShareDt(Date simulationShareDt);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare);

	@Override
	public int hashCode();

	@Override
	public CacheModel<org.kisti.edison.bestsimulation.model.SimulationShare> toCacheModel();

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare toEscapedModel();

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}