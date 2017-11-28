/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

import org.kisti.edison.bestsimulation.model.SimulationJob;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SimulationJob in entity cache.
 *
 * @author EDISON
 * @see SimulationJob
 * @generated
 */
public class SimulationJobCacheModel implements CacheModel<SimulationJob>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{jobSeqNo=");
		sb.append(jobSeqNo);
		sb.append(", simulationUuid=");
		sb.append(simulationUuid);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", simulationJobId=");
		sb.append(simulationJobId);
		sb.append(", jobUuid=");
		sb.append(jobUuid);
		sb.append(", jobStatus=");
		sb.append(jobStatus);
		sb.append(", jobStartDt=");
		sb.append(jobStartDt);
		sb.append(", jobEndDt=");
		sb.append(jobEndDt);
		sb.append(", jobTitle=");
		sb.append(jobTitle);
		sb.append(", jobExecPath=");
		sb.append(jobExecPath);
		sb.append(", jobPhase=");
		sb.append(jobPhase);
		sb.append(", jobSubmitDt=");
		sb.append(jobSubmitDt);
		sb.append(", jobUniversityField=");
		sb.append(jobUniversityField);
		sb.append(", jobInputDeckYn=");
		sb.append(jobInputDeckYn);
		sb.append(", jobInputDeckName=");
		sb.append(jobInputDeckName);
		sb.append(", jobSubmit=");
		sb.append(jobSubmit);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SimulationJob toEntityModel() {
		SimulationJobImpl simulationJobImpl = new SimulationJobImpl();

		simulationJobImpl.setJobSeqNo(jobSeqNo);

		if (simulationUuid == null) {
			simulationJobImpl.setSimulationUuid(StringPool.BLANK);
		}
		else {
			simulationJobImpl.setSimulationUuid(simulationUuid);
		}

		simulationJobImpl.setGroupId(groupId);
		simulationJobImpl.setSimulationJobId(simulationJobId);

		if (jobUuid == null) {
			simulationJobImpl.setJobUuid(StringPool.BLANK);
		}
		else {
			simulationJobImpl.setJobUuid(jobUuid);
		}

		simulationJobImpl.setJobStatus(jobStatus);

		if (jobStartDt == Long.MIN_VALUE) {
			simulationJobImpl.setJobStartDt(null);
		}
		else {
			simulationJobImpl.setJobStartDt(new Date(jobStartDt));
		}

		if (jobEndDt == Long.MIN_VALUE) {
			simulationJobImpl.setJobEndDt(null);
		}
		else {
			simulationJobImpl.setJobEndDt(new Date(jobEndDt));
		}

		if (jobTitle == null) {
			simulationJobImpl.setJobTitle(StringPool.BLANK);
		}
		else {
			simulationJobImpl.setJobTitle(jobTitle);
		}

		if (jobExecPath == null) {
			simulationJobImpl.setJobExecPath(StringPool.BLANK);
		}
		else {
			simulationJobImpl.setJobExecPath(jobExecPath);
		}

		simulationJobImpl.setJobPhase(jobPhase);

		if (jobSubmitDt == Long.MIN_VALUE) {
			simulationJobImpl.setJobSubmitDt(null);
		}
		else {
			simulationJobImpl.setJobSubmitDt(new Date(jobSubmitDt));
		}

		simulationJobImpl.setJobUniversityField(jobUniversityField);
		simulationJobImpl.setJobInputDeckYn(jobInputDeckYn);

		if (jobInputDeckName == null) {
			simulationJobImpl.setJobInputDeckName(StringPool.BLANK);
		}
		else {
			simulationJobImpl.setJobInputDeckName(jobInputDeckName);
		}

		simulationJobImpl.setJobSubmit(jobSubmit);

		simulationJobImpl.resetOriginalValues();

		return simulationJobImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jobSeqNo = objectInput.readLong();
		simulationUuid = objectInput.readUTF();
		groupId = objectInput.readLong();
		simulationJobId = objectInput.readLong();
		jobUuid = objectInput.readUTF();
		jobStatus = objectInput.readLong();
		jobStartDt = objectInput.readLong();
		jobEndDt = objectInput.readLong();
		jobTitle = objectInput.readUTF();
		jobExecPath = objectInput.readUTF();
		jobPhase = objectInput.readLong();
		jobSubmitDt = objectInput.readLong();
		jobUniversityField = objectInput.readLong();
		jobInputDeckYn = objectInput.readBoolean();
		jobInputDeckName = objectInput.readUTF();
		jobSubmit = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jobSeqNo);

		if (simulationUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(simulationUuid);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(simulationJobId);

		if (jobUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jobUuid);
		}

		objectOutput.writeLong(jobStatus);
		objectOutput.writeLong(jobStartDt);
		objectOutput.writeLong(jobEndDt);

		if (jobTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jobTitle);
		}

		if (jobExecPath == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jobExecPath);
		}

		objectOutput.writeLong(jobPhase);
		objectOutput.writeLong(jobSubmitDt);
		objectOutput.writeLong(jobUniversityField);
		objectOutput.writeBoolean(jobInputDeckYn);

		if (jobInputDeckName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jobInputDeckName);
		}

		objectOutput.writeBoolean(jobSubmit);
	}

	public long jobSeqNo;
	public String simulationUuid;
	public long groupId;
	public long simulationJobId;
	public String jobUuid;
	public long jobStatus;
	public long jobStartDt;
	public long jobEndDt;
	public String jobTitle;
	public String jobExecPath;
	public long jobPhase;
	public long jobSubmitDt;
	public long jobUniversityField;
	public boolean jobInputDeckYn;
	public String jobInputDeckName;
	public boolean jobSubmit;
}