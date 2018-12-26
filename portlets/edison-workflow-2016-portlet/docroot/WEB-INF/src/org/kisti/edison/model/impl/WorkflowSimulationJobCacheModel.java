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

package org.kisti.edison.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.model.WorkflowSimulationJob;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowSimulationJob in entity cache.
 *
 * @author EDISON
 * @see WorkflowSimulationJob
 * @generated
 */
public class WorkflowSimulationJobCacheModel implements CacheModel<WorkflowSimulationJob>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{simulationJobId=");
		sb.append(simulationJobId);
		sb.append(", simulationId=");
		sb.append(simulationId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusResponse=");
		sb.append(statusResponse);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", workflowId=");
		sb.append(workflowId);
		sb.append(", workflowUUID=");
		sb.append(workflowUUID);
		sb.append(", reuseWorkflowUUID=");
		sb.append(reuseWorkflowUUID);
		sb.append(", screenLogic=");
		sb.append(screenLogic);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkflowSimulationJob toEntityModel() {
		WorkflowSimulationJobImpl workflowSimulationJobImpl = new WorkflowSimulationJobImpl();

		workflowSimulationJobImpl.setSimulationJobId(simulationJobId);
		workflowSimulationJobImpl.setSimulationId(simulationId);
		workflowSimulationJobImpl.setGroupId(groupId);
		workflowSimulationJobImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			workflowSimulationJobImpl.setCreateDate(null);
		}
		else {
			workflowSimulationJobImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowSimulationJobImpl.setModifiedDate(null);
		}
		else {
			workflowSimulationJobImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			workflowSimulationJobImpl.setTitle(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setTitle(title);
		}

		if (status == null) {
			workflowSimulationJobImpl.setStatus(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setStatus(status);
		}

		if (statusResponse == null) {
			workflowSimulationJobImpl.setStatusResponse(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setStatusResponse(statusResponse);
		}

		if (startTime == Long.MIN_VALUE) {
			workflowSimulationJobImpl.setStartTime(null);
		}
		else {
			workflowSimulationJobImpl.setStartTime(new Date(startTime));
		}

		if (endTime == Long.MIN_VALUE) {
			workflowSimulationJobImpl.setEndTime(null);
		}
		else {
			workflowSimulationJobImpl.setEndTime(new Date(endTime));
		}

		workflowSimulationJobImpl.setWorkflowId(workflowId);

		if (workflowUUID == null) {
			workflowSimulationJobImpl.setWorkflowUUID(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setWorkflowUUID(workflowUUID);
		}

		if (reuseWorkflowUUID == null) {
			workflowSimulationJobImpl.setReuseWorkflowUUID(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setReuseWorkflowUUID(reuseWorkflowUUID);
		}

		if (screenLogic == null) {
			workflowSimulationJobImpl.setScreenLogic(StringPool.BLANK);
		}
		else {
			workflowSimulationJobImpl.setScreenLogic(screenLogic);
		}

		workflowSimulationJobImpl.resetOriginalValues();

		return workflowSimulationJobImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simulationJobId = objectInput.readLong();
		simulationId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		status = objectInput.readUTF();
		statusResponse = objectInput.readUTF();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
		workflowId = objectInput.readLong();
		workflowUUID = objectInput.readUTF();
		reuseWorkflowUUID = objectInput.readUTF();
		screenLogic = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(simulationJobId);
		objectOutput.writeLong(simulationId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (statusResponse == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusResponse);
		}

		objectOutput.writeLong(startTime);
		objectOutput.writeLong(endTime);
		objectOutput.writeLong(workflowId);

		if (workflowUUID == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowUUID);
		}

		if (reuseWorkflowUUID == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reuseWorkflowUUID);
		}

		if (screenLogic == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(screenLogic);
		}
	}

	public long simulationJobId;
	public long simulationId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String status;
	public String statusResponse;
	public long startTime;
	public long endTime;
	public long workflowId;
	public String workflowUUID;
	public String reuseWorkflowUUID;
	public String screenLogic;
}