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

import org.kisti.edison.osp.service.persistence.ExecutePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.osp.service.http.ExecuteServiceSoap}.
 *
 * @author edison
 * @see org.kisti.edison.osp.service.http.ExecuteServiceSoap
 * @generated
 */
public class ExecuteSoap implements Serializable {
	public static ExecuteSoap toSoapModel(Execute model) {
		ExecuteSoap soapModel = new ExecuteSoap();

		soapModel.setProjectId(model.getProjectId());
		soapModel.setExecuteId(model.getExecuteId());
		soapModel.setExecuteDataStructure(model.getExecuteDataStructure());
		soapModel.setExecuteDate(model.getExecuteDate());

		return soapModel;
	}

	public static ExecuteSoap[] toSoapModels(Execute[] models) {
		ExecuteSoap[] soapModels = new ExecuteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExecuteSoap[][] toSoapModels(Execute[][] models) {
		ExecuteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExecuteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExecuteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExecuteSoap[] toSoapModels(List<Execute> models) {
		List<ExecuteSoap> soapModels = new ArrayList<ExecuteSoap>(models.size());

		for (Execute model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExecuteSoap[soapModels.size()]);
	}

	public ExecuteSoap() {
	}

	public ExecutePK getPrimaryKey() {
		return new ExecutePK(_projectId, _executeId);
	}

	public void setPrimaryKey(ExecutePK pk) {
		setProjectId(pk.projectId);
		setExecuteId(pk.executeId);
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public String getExecuteId() {
		return _executeId;
	}

	public void setExecuteId(String executeId) {
		_executeId = executeId;
	}

	public String getExecuteDataStructure() {
		return _executeDataStructure;
	}

	public void setExecuteDataStructure(String executeDataStructure) {
		_executeDataStructure = executeDataStructure;
	}

	public Date getExecuteDate() {
		return _executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		_executeDate = executeDate;
	}

	private long _projectId;
	private String _executeId;
	private String _executeDataStructure;
	private Date _executeDate;
}