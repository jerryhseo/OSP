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

import org.kisti.edison.bestsimulation.model.SimulationExeStsMigration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SimulationExeStsMigration in entity cache.
 *
 * @author EDISON
 * @see SimulationExeStsMigration
 * @generated
 */
public class SimulationExeStsMigrationCacheModel implements CacheModel<SimulationExeStsMigration>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", submitDate=");
		sb.append(submitDate);
		sb.append(", userCnt=");
		sb.append(userCnt);
		sb.append(", jobCnt=");
		sb.append(jobCnt);
		sb.append(", runtime=");
		sb.append(runtime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SimulationExeStsMigration toEntityModel() {
		SimulationExeStsMigrationImpl simulationExeStsMigrationImpl = new SimulationExeStsMigrationImpl();

		simulationExeStsMigrationImpl.setScienceAppId(scienceAppId);
		simulationExeStsMigrationImpl.setGroupId(groupId);

		if (submitDate == null) {
			simulationExeStsMigrationImpl.setSubmitDate(StringPool.BLANK);
		}
		else {
			simulationExeStsMigrationImpl.setSubmitDate(submitDate);
		}

		simulationExeStsMigrationImpl.setUserCnt(userCnt);
		simulationExeStsMigrationImpl.setJobCnt(jobCnt);
		simulationExeStsMigrationImpl.setRuntime(runtime);

		simulationExeStsMigrationImpl.resetOriginalValues();

		return simulationExeStsMigrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		groupId = objectInput.readLong();
		submitDate = objectInput.readUTF();
		userCnt = objectInput.readLong();
		jobCnt = objectInput.readLong();
		runtime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(groupId);

		if (submitDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(submitDate);
		}

		objectOutput.writeLong(userCnt);
		objectOutput.writeLong(jobCnt);
		objectOutput.writeLong(runtime);
	}

	public long scienceAppId;
	public long groupId;
	public String submitDate;
	public long userCnt;
	public long jobCnt;
	public long runtime;
}