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

package org.kisti.eturb.dbservice.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.eturb.dbservice.model.Simulation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Simulation in entity cache.
 *
 * @author EDISON
 * @see Simulation
 * @generated
 */
public class SimulationCacheModel implements CacheModel<Simulation>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{projectId=");
		sb.append(projectId);
		sb.append(", executeId=");
		sb.append(executeId);
		sb.append(", executeDataStructure=");
		sb.append(executeDataStructure);
		sb.append(", executeDate=");
		sb.append(executeDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Simulation toEntityModel() {
		SimulationImpl simulationImpl = new SimulationImpl();

		simulationImpl.setProjectId(projectId);

		if (executeId == null) {
			simulationImpl.setExecuteId(StringPool.BLANK);
		}
		else {
			simulationImpl.setExecuteId(executeId);
		}

		if (executeDataStructure == null) {
			simulationImpl.setExecuteDataStructure(StringPool.BLANK);
		}
		else {
			simulationImpl.setExecuteDataStructure(executeDataStructure);
		}

		if (executeDate == Long.MIN_VALUE) {
			simulationImpl.setExecuteDate(null);
		}
		else {
			simulationImpl.setExecuteDate(new Date(executeDate));
		}

		simulationImpl.resetOriginalValues();

		return simulationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectId = objectInput.readLong();
		executeId = objectInput.readUTF();
		executeDataStructure = objectInput.readUTF();
		executeDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectId);

		if (executeId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(executeId);
		}

		if (executeDataStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(executeDataStructure);
		}

		objectOutput.writeLong(executeDate);
	}

	public long projectId;
	public String executeId;
	public String executeDataStructure;
	public long executeDate;
}