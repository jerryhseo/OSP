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

package org.kisti.edison.osp.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.osp.model.Project;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Project in entity cache.
 *
 * @author edison
 * @see Project
 * @generated
 */
public class ProjectCacheModel implements CacheModel<Project>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{simulationUuid=");
		sb.append(simulationUuid);
		sb.append(", portletNamespace=");
		sb.append(portletNamespace);
		sb.append(", jobSeqNo=");
		sb.append(jobSeqNo);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", projectStructure=");
		sb.append(projectStructure);
		sb.append(", analyzerStructure=");
		sb.append(analyzerStructure);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Project toEntityModel() {
		ProjectImpl projectImpl = new ProjectImpl();

		if (simulationUuid == null) {
			projectImpl.setSimulationUuid(StringPool.BLANK);
		}
		else {
			projectImpl.setSimulationUuid(simulationUuid);
		}

		if (portletNamespace == null) {
			projectImpl.setPortletNamespace(StringPool.BLANK);
		}
		else {
			projectImpl.setPortletNamespace(portletNamespace);
		}

		projectImpl.setJobSeqNo(jobSeqNo);
		projectImpl.setProjectId(projectId);

		if (projectStructure == null) {
			projectImpl.setProjectStructure(StringPool.BLANK);
		}
		else {
			projectImpl.setProjectStructure(projectStructure);
		}

		if (analyzerStructure == null) {
			projectImpl.setAnalyzerStructure(StringPool.BLANK);
		}
		else {
			projectImpl.setAnalyzerStructure(analyzerStructure);
		}

		projectImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			projectImpl.setCreateDate(null);
		}
		else {
			projectImpl.setCreateDate(new Date(createDate));
		}

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simulationUuid = objectInput.readUTF();
		portletNamespace = objectInput.readUTF();
		jobSeqNo = objectInput.readLong();
		projectId = objectInput.readLong();
		projectStructure = objectInput.readUTF();
		analyzerStructure = objectInput.readUTF();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
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

		if (portletNamespace == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portletNamespace);
		}

		objectOutput.writeLong(jobSeqNo);
		objectOutput.writeLong(projectId);

		if (projectStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectStructure);
		}

		if (analyzerStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(analyzerStructure);
		}

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
	}

	public String simulationUuid;
	public String portletNamespace;
	public long jobSeqNo;
	public long projectId;
	public String projectStructure;
	public String analyzerStructure;
	public long userId;
	public long createDate;
}