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

package org.kisti.edison.science.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link org.kisti.edison.science.service.http.ScienceAppServiceSoap}.
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.http.ScienceAppServiceSoap
 * @generated
 */
public class ScienceAppSoap implements Serializable {
	public static ScienceAppSoap toSoapModel(ScienceApp model) {
		ScienceAppSoap soapModel = new ScienceAppSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setScienceAppId(model.getScienceAppId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescriptionId(model.getDescriptionId());
		soapModel.setPreviousVersionId(model.getPreviousVersionId());
		soapModel.setIconId(model.getIconId());
		soapModel.setManualId(model.getManualId());
		soapModel.setManualUrl(model.getManualUrl());
		soapModel.setExeFileName(model.getExeFileName());
		soapModel.setAppType(model.getAppType());
		soapModel.setRunType(model.getRunType());
		soapModel.setAuthorId(model.getAuthorId());
		soapModel.setStage(model.getStage());
		soapModel.setStatus(model.getStatus());
		soapModel.setRecentModifierId(model.getRecentModifierId());
		soapModel.setParallelModule(model.getParallelModule());
		soapModel.setMinCpus(model.getMinCpus());
		soapModel.setMaxCpus(model.getMaxCpus());
		soapModel.setDefaultCpus(model.getDefaultCpus());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setOpenLevel(model.getOpenLevel());
		soapModel.setLicense(model.getLicense());
		soapModel.setSrcFileName(model.getSrcFileName());
		soapModel.setTargetLanguage(model.getTargetLanguage());
		soapModel.setIsStepLayout(model.getIsStepLayout());
		soapModel.setLayout(model.getLayout());
		soapModel.setDevelopers(model.getDevelopers());
		soapModel.setEditorType(model.getEditorType());
		soapModel.setIsPort(model.getIsPort());
		soapModel.setIsCompile(model.getIsCompile());
		soapModel.setProjectCategoryId(model.getProjectCategoryId());
		soapModel.setExecute(model.getExecute());
		soapModel.setCluster(model.getCluster());
		soapModel.setWorkflowId(model.getWorkflowId());

		return soapModel;
	}

	public static ScienceAppSoap[] toSoapModels(ScienceApp[] models) {
		ScienceAppSoap[] soapModels = new ScienceAppSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppSoap[][] toSoapModels(ScienceApp[][] models) {
		ScienceAppSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScienceAppSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScienceAppSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScienceAppSoap[] toSoapModels(List<ScienceApp> models) {
		List<ScienceAppSoap> soapModels = new ArrayList<ScienceAppSoap>(models.size());

		for (ScienceApp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScienceAppSoap[soapModels.size()]);
	}

	public ScienceAppSoap() {
	}

	public long getPrimaryKey() {
		return _scienceAppId;
	}

	public void setPrimaryKey(long pk) {
		setScienceAppId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getScienceAppId() {
		return _scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public long getDescriptionId() {
		return _descriptionId;
	}

	public void setDescriptionId(long descriptionId) {
		_descriptionId = descriptionId;
	}

	public long getPreviousVersionId() {
		return _previousVersionId;
	}

	public void setPreviousVersionId(long previousVersionId) {
		_previousVersionId = previousVersionId;
	}

	public long getIconId() {
		return _iconId;
	}

	public void setIconId(long iconId) {
		_iconId = iconId;
	}

	public String getManualId() {
		return _manualId;
	}

	public void setManualId(String manualId) {
		_manualId = manualId;
	}

	public String getManualUrl() {
		return _manualUrl;
	}

	public void setManualUrl(String manualUrl) {
		_manualUrl = manualUrl;
	}

	public String getExeFileName() {
		return _exeFileName;
	}

	public void setExeFileName(String exeFileName) {
		_exeFileName = exeFileName;
	}

	public String getAppType() {
		return _appType;
	}

	public void setAppType(String appType) {
		_appType = appType;
	}

	public String getRunType() {
		return _runType;
	}

	public void setRunType(String runType) {
		_runType = runType;
	}

	public long getAuthorId() {
		return _authorId;
	}

	public void setAuthorId(long authorId) {
		_authorId = authorId;
	}

	public String getStage() {
		return _stage;
	}

	public void setStage(String stage) {
		_stage = stage;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getRecentModifierId() {
		return _recentModifierId;
	}

	public void setRecentModifierId(long recentModifierId) {
		_recentModifierId = recentModifierId;
	}

	public String getParallelModule() {
		return _parallelModule;
	}

	public void setParallelModule(String parallelModule) {
		_parallelModule = parallelModule;
	}

	public int getMinCpus() {
		return _minCpus;
	}

	public void setMinCpus(int minCpus) {
		_minCpus = minCpus;
	}

	public int getMaxCpus() {
		return _maxCpus;
	}

	public void setMaxCpus(int maxCpus) {
		_maxCpus = maxCpus;
	}

	public int getDefaultCpus() {
		return _defaultCpus;
	}

	public void setDefaultCpus(int defaultCpus) {
		_defaultCpus = defaultCpus;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getOpenLevel() {
		return _openLevel;
	}

	public void setOpenLevel(String openLevel) {
		_openLevel = openLevel;
	}

	public String getLicense() {
		return _license;
	}

	public void setLicense(String license) {
		_license = license;
	}

	public String getSrcFileName() {
		return _srcFileName;
	}

	public void setSrcFileName(String srcFileName) {
		_srcFileName = srcFileName;
	}

	public String getTargetLanguage() {
		return _targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		_targetLanguage = targetLanguage;
	}

	public Boolean getIsStepLayout() {
		return _isStepLayout;
	}

	public void setIsStepLayout(Boolean isStepLayout) {
		_isStepLayout = isStepLayout;
	}

	public String getLayout() {
		return _layout;
	}

	public void setLayout(String layout) {
		_layout = layout;
	}

	public String getDevelopers() {
		return _developers;
	}

	public void setDevelopers(String developers) {
		_developers = developers;
	}

	public String getEditorType() {
		return _editorType;
	}

	public void setEditorType(String editorType) {
		_editorType = editorType;
	}

	public boolean getIsPort() {
		return _isPort;
	}

	public boolean isIsPort() {
		return _isPort;
	}

	public void setIsPort(boolean isPort) {
		_isPort = isPort;
	}

	public boolean getIsCompile() {
		return _isCompile;
	}

	public boolean isIsCompile() {
		return _isCompile;
	}

	public void setIsCompile(boolean isCompile) {
		_isCompile = isCompile;
	}

	public long getProjectCategoryId() {
		return _projectCategoryId;
	}

	public void setProjectCategoryId(long projectCategoryId) {
		_projectCategoryId = projectCategoryId;
	}

	public long getExecute() {
		return _execute;
	}

	public void setExecute(long execute) {
		_execute = execute;
	}

	public String getCluster() {
		return _cluster;
	}

	public void setCluster(String cluster) {
		_cluster = cluster;
	}

	public long getWorkflowId() {
		return _workflowId;
	}

	public void setWorkflowId(long workflowId) {
		_workflowId = workflowId;
	}

	private String _uuid;
	private long _scienceAppId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _version;
	private String _title;
	private long _descriptionId;
	private long _previousVersionId;
	private long _iconId;
	private String _manualId;
	private String _manualUrl;
	private String _exeFileName;
	private String _appType;
	private String _runType;
	private long _authorId;
	private String _stage;
	private int _status;
	private long _recentModifierId;
	private String _parallelModule;
	private int _minCpus;
	private int _maxCpus;
	private int _defaultCpus;
	private Date _statusDate;
	private String _openLevel;
	private String _license;
	private String _srcFileName;
	private String _targetLanguage;
	private Boolean _isStepLayout;
	private String _layout;
	private String _developers;
	private String _editorType;
	private boolean _isPort;
	private boolean _isCompile;
	private long _projectCategoryId;
	private long _execute;
	private String _cluster;
	private long _workflowId;
}