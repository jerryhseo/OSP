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

import org.kisti.edison.model.WorkflowSimulation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowSimulation in entity cache.
 *
 * @author EDISON
 * @see WorkflowSimulation
 * @generated
 */
public class WorkflowSimulationCacheModel implements CacheModel<WorkflowSimulation>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{simulationId=");
		sb.append(simulationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", customId=");
		sb.append(customId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", testYn=");
		sb.append(testYn);
		sb.append(", workflowId=");
		sb.append(workflowId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkflowSimulation toEntityModel() {
		WorkflowSimulationImpl workflowSimulationImpl = new WorkflowSimulationImpl();

		workflowSimulationImpl.setSimulationId(simulationId);
		workflowSimulationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			workflowSimulationImpl.setCreateDate(null);
		}
		else {
			workflowSimulationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowSimulationImpl.setModifiedDate(null);
		}
		else {
			workflowSimulationImpl.setModifiedDate(new Date(modifiedDate));
		}

		workflowSimulationImpl.setClassId(classId);
		workflowSimulationImpl.setCustomId(customId);

		if (title == null) {
			workflowSimulationImpl.setTitle(StringPool.BLANK);
		}
		else {
			workflowSimulationImpl.setTitle(title);
		}

		workflowSimulationImpl.setTestYn(testYn);
		workflowSimulationImpl.setWorkflowId(workflowId);

		workflowSimulationImpl.resetOriginalValues();

		return workflowSimulationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simulationId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classId = objectInput.readLong();
		customId = objectInput.readLong();
		title = objectInput.readUTF();
		testYn = objectInput.readBoolean();
		workflowId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(simulationId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classId);
		objectOutput.writeLong(customId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeBoolean(testYn);
		objectOutput.writeLong(workflowId);
	}

	public long simulationId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classId;
	public long customId;
	public String title;
	public boolean testYn;
	public long workflowId;
}