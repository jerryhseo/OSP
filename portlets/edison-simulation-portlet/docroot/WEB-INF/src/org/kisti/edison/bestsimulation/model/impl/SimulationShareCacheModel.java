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

package org.kisti.edison.bestsimulation.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.bestsimulation.model.SimulationShare;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SimulationShare in entity cache.
 *
 * @author EDISON
 * @see SimulationShare
 * @generated
 */
public class SimulationShareCacheModel implements CacheModel<SimulationShare>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{shareSeqno=");
		sb.append(shareSeqno);
		sb.append(", jobSeqNo=");
		sb.append(jobSeqNo);
		sb.append(", jobUuid=");
		sb.append(jobUuid);
		sb.append(", simulationUuid=");
		sb.append(simulationUuid);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", customId=");
		sb.append(customId);
		sb.append(", simulationShareDt=");
		sb.append(simulationShareDt);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SimulationShare toEntityModel() {
		SimulationShareImpl simulationShareImpl = new SimulationShareImpl();

		simulationShareImpl.setShareSeqno(shareSeqno);
		simulationShareImpl.setJobSeqNo(jobSeqNo);

		if (jobUuid == null) {
			simulationShareImpl.setJobUuid(StringPool.BLANK);
		}
		else {
			simulationShareImpl.setJobUuid(jobUuid);
		}

		if (simulationUuid == null) {
			simulationShareImpl.setSimulationUuid(StringPool.BLANK);
		}
		else {
			simulationShareImpl.setSimulationUuid(simulationUuid);
		}

		simulationShareImpl.setClassId(classId);
		simulationShareImpl.setCustomId(customId);

		if (simulationShareDt == Long.MIN_VALUE) {
			simulationShareImpl.setSimulationShareDt(null);
		}
		else {
			simulationShareImpl.setSimulationShareDt(new Date(simulationShareDt));
		}

		simulationShareImpl.resetOriginalValues();

		return simulationShareImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		shareSeqno = objectInput.readLong();
		jobSeqNo = objectInput.readLong();
		jobUuid = objectInput.readUTF();
		simulationUuid = objectInput.readUTF();
		classId = objectInput.readLong();
		customId = objectInput.readLong();
		simulationShareDt = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(shareSeqno);
		objectOutput.writeLong(jobSeqNo);

		if (jobUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jobUuid);
		}

		if (simulationUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(simulationUuid);
		}

		objectOutput.writeLong(classId);
		objectOutput.writeLong(customId);
		objectOutput.writeLong(simulationShareDt);
	}

	public long shareSeqno;
	public long jobSeqNo;
	public String jobUuid;
	public String simulationUuid;
	public long classId;
	public long customId;
	public long simulationShareDt;
}