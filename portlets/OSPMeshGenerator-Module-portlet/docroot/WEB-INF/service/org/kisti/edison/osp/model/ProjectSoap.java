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

package org.kisti.edison.osp.model;

import org.kisti.edison.osp.service.persistence.ProjectPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.osp.service.http.ProjectServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.osp.service.http.ProjectServiceSoap
 * @generated
 */
public class ProjectSoap implements Serializable {
	public static ProjectSoap toSoapModel(Project model) {
		ProjectSoap soapModel = new ProjectSoap();

		soapModel.setSimulationUuid(model.getSimulationUuid());
		soapModel.setPortletNamespace(model.getPortletNamespace());
		soapModel.setJobSeqNo(model.getJobSeqNo());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setProjectStructure(model.getProjectStructure());
		soapModel.setAnalyzerStructure(model.getAnalyzerStructure());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static ProjectSoap[] toSoapModels(Project[] models) {
		ProjectSoap[] soapModels = new ProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectSoap[][] toSoapModels(Project[][] models) {
		ProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectSoap[] toSoapModels(List<Project> models) {
		List<ProjectSoap> soapModels = new ArrayList<ProjectSoap>(models.size());

		for (Project model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectSoap[soapModels.size()]);
	}

	public ProjectSoap() {
	}

	public ProjectPK getPrimaryKey() {
		return new ProjectPK(_simulationUuid, _portletNamespace, _jobSeqNo);
	}

	public void setPrimaryKey(ProjectPK pk) {
		setSimulationUuid(pk.simulationUuid);
		setPortletNamespace(pk.portletNamespace);
		setJobSeqNo(pk.jobSeqNo);
	}

	public String getSimulationUuid() {
		return _simulationUuid;
	}

	public void setSimulationUuid(String simulationUuid) {
		_simulationUuid = simulationUuid;
	}

	public String getPortletNamespace() {
		return _portletNamespace;
	}

	public void setPortletNamespace(String portletNamespace) {
		_portletNamespace = portletNamespace;
	}

	public long getJobSeqNo() {
		return _jobSeqNo;
	}

	public void setJobSeqNo(long jobSeqNo) {
		_jobSeqNo = jobSeqNo;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public String getProjectStructure() {
		return _projectStructure;
	}

	public void setProjectStructure(String projectStructure) {
		_projectStructure = projectStructure;
	}

	public String getAnalyzerStructure() {
		return _analyzerStructure;
	}

	public void setAnalyzerStructure(String analyzerStructure) {
		_analyzerStructure = analyzerStructure;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private String _simulationUuid;
	private String _portletNamespace;
	private long _jobSeqNo;
	private long _projectId;
	private String _projectStructure;
	private String _analyzerStructure;
	private long _userId;
	private Date _createDate;
}