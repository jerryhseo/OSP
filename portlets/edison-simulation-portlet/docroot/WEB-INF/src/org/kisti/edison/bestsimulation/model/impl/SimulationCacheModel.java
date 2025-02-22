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

import org.kisti.edison.bestsimulation.model.Simulation;

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
		StringBundler sb = new StringBundler(25);

		sb.append("{simulationUuid=");
		sb.append(simulationUuid);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", simulationTitle=");
		sb.append(simulationTitle);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", scienceAppName=");
		sb.append(scienceAppName);
		sb.append(", scienceAppVersion=");
		sb.append(scienceAppVersion);
		sb.append(", simulationCreateDt=");
		sb.append(simulationCreateDt);
		sb.append(", cluster=");
		sb.append(cluster);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", customId=");
		sb.append(customId);
		sb.append(", testYn=");
		sb.append(testYn);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Simulation toEntityModel() {
		SimulationImpl simulationImpl = new SimulationImpl();

		if (simulationUuid == null) {
			simulationImpl.setSimulationUuid(StringPool.BLANK);
		}
		else {
			simulationImpl.setSimulationUuid(simulationUuid);
		}

		simulationImpl.setGroupId(groupId);
		simulationImpl.setUserId(userId);

		if (simulationTitle == null) {
			simulationImpl.setSimulationTitle(StringPool.BLANK);
		}
		else {
			simulationImpl.setSimulationTitle(simulationTitle);
		}

		if (scienceAppId == null) {
			simulationImpl.setScienceAppId(StringPool.BLANK);
		}
		else {
			simulationImpl.setScienceAppId(scienceAppId);
		}

		if (scienceAppName == null) {
			simulationImpl.setScienceAppName(StringPool.BLANK);
		}
		else {
			simulationImpl.setScienceAppName(scienceAppName);
		}

		if (scienceAppVersion == null) {
			simulationImpl.setScienceAppVersion(StringPool.BLANK);
		}
		else {
			simulationImpl.setScienceAppVersion(scienceAppVersion);
		}

		if (simulationCreateDt == Long.MIN_VALUE) {
			simulationImpl.setSimulationCreateDt(null);
		}
		else {
			simulationImpl.setSimulationCreateDt(new Date(simulationCreateDt));
		}

		if (cluster == null) {
			simulationImpl.setCluster(StringPool.BLANK);
		}
		else {
			simulationImpl.setCluster(cluster);
		}

		simulationImpl.setClassId(classId);
		simulationImpl.setCustomId(customId);
		simulationImpl.setTestYn(testYn);

		simulationImpl.resetOriginalValues();

		return simulationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simulationUuid = objectInput.readUTF();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		simulationTitle = objectInput.readUTF();
		scienceAppId = objectInput.readUTF();
		scienceAppName = objectInput.readUTF();
		scienceAppVersion = objectInput.readUTF();
		simulationCreateDt = objectInput.readLong();
		cluster = objectInput.readUTF();
		classId = objectInput.readLong();
		customId = objectInput.readLong();
		testYn = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (simulationUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(simulationUuid);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);

		if (simulationTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(simulationTitle);
		}

		if (scienceAppId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppId);
		}

		if (scienceAppName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppName);
		}

		if (scienceAppVersion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppVersion);
		}

		objectOutput.writeLong(simulationCreateDt);

		if (cluster == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cluster);
		}

		objectOutput.writeLong(classId);
		objectOutput.writeLong(customId);
		objectOutput.writeBoolean(testYn);
	}

	public String simulationUuid;
	public long groupId;
	public long userId;
	public String simulationTitle;
	public String scienceAppId;
	public String scienceAppName;
	public String scienceAppVersion;
	public long simulationCreateDt;
	public String cluster;
	public long classId;
	public long customId;
	public boolean testYn;
}