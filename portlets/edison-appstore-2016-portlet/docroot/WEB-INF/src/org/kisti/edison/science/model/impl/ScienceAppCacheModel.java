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

package org.kisti.edison.science.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.science.model.ScienceApp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScienceApp in entity cache.
 *
 * @author EDISON
 * @see ScienceApp
 * @generated
 */
public class ScienceAppCacheModel implements CacheModel<ScienceApp>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(83);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append(", title=");
		sb.append(title);
		sb.append(", descriptionId=");
		sb.append(descriptionId);
		sb.append(", previousVersionId=");
		sb.append(previousVersionId);
		sb.append(", iconId=");
		sb.append(iconId);
		sb.append(", manualId=");
		sb.append(manualId);
		sb.append(", manualUrl=");
		sb.append(manualUrl);
		sb.append(", exeFileName=");
		sb.append(exeFileName);
		sb.append(", appType=");
		sb.append(appType);
		sb.append(", runType=");
		sb.append(runType);
		sb.append(", authorId=");
		sb.append(authorId);
		sb.append(", stage=");
		sb.append(stage);
		sb.append(", status=");
		sb.append(status);
		sb.append(", recentModifierId=");
		sb.append(recentModifierId);
		sb.append(", parallelModule=");
		sb.append(parallelModule);
		sb.append(", minCpus=");
		sb.append(minCpus);
		sb.append(", maxCpus=");
		sb.append(maxCpus);
		sb.append(", defaultCpus=");
		sb.append(defaultCpus);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", openLevel=");
		sb.append(openLevel);
		sb.append(", license=");
		sb.append(license);
		sb.append(", srcFileName=");
		sb.append(srcFileName);
		sb.append(", targetLanguage=");
		sb.append(targetLanguage);
		sb.append(", isStepLayout=");
		sb.append(isStepLayout);
		sb.append(", layout=");
		sb.append(layout);
		sb.append(", developers=");
		sb.append(developers);
		sb.append(", editorType=");
		sb.append(editorType);
		sb.append(", isPort=");
		sb.append(isPort);
		sb.append(", isCompile=");
		sb.append(isCompile);
		sb.append(", projectCategoryId=");
		sb.append(projectCategoryId);
		sb.append(", execute=");
		sb.append(execute);
		sb.append(", cluster=");
		sb.append(cluster);
		sb.append(", workflowId=");
		sb.append(workflowId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceApp toEntityModel() {
		ScienceAppImpl scienceAppImpl = new ScienceAppImpl();

		if (uuid == null) {
			scienceAppImpl.setUuid(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setUuid(uuid);
		}

		scienceAppImpl.setScienceAppId(scienceAppId);
		scienceAppImpl.setGroupId(groupId);
		scienceAppImpl.setCompanyId(companyId);
		scienceAppImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			scienceAppImpl.setCreateDate(null);
		}
		else {
			scienceAppImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scienceAppImpl.setModifiedDate(null);
		}
		else {
			scienceAppImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			scienceAppImpl.setName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setName(name);
		}

		if (version == null) {
			scienceAppImpl.setVersion(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setVersion(version);
		}

		if (title == null) {
			scienceAppImpl.setTitle(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setTitle(title);
		}

		scienceAppImpl.setDescriptionId(descriptionId);
		scienceAppImpl.setPreviousVersionId(previousVersionId);
		scienceAppImpl.setIconId(iconId);

		if (manualId == null) {
			scienceAppImpl.setManualId(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setManualId(manualId);
		}

		if (manualUrl == null) {
			scienceAppImpl.setManualUrl(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setManualUrl(manualUrl);
		}

		if (exeFileName == null) {
			scienceAppImpl.setExeFileName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setExeFileName(exeFileName);
		}

		if (appType == null) {
			scienceAppImpl.setAppType(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setAppType(appType);
		}

		if (runType == null) {
			scienceAppImpl.setRunType(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setRunType(runType);
		}

		scienceAppImpl.setAuthorId(authorId);

		if (stage == null) {
			scienceAppImpl.setStage(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setStage(stage);
		}

		scienceAppImpl.setStatus(status);
		scienceAppImpl.setRecentModifierId(recentModifierId);

		if (parallelModule == null) {
			scienceAppImpl.setParallelModule(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setParallelModule(parallelModule);
		}

		scienceAppImpl.setMinCpus(minCpus);
		scienceAppImpl.setMaxCpus(maxCpus);
		scienceAppImpl.setDefaultCpus(defaultCpus);

		if (statusDate == Long.MIN_VALUE) {
			scienceAppImpl.setStatusDate(null);
		}
		else {
			scienceAppImpl.setStatusDate(new Date(statusDate));
		}

		if (openLevel == null) {
			scienceAppImpl.setOpenLevel(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setOpenLevel(openLevel);
		}

		if (license == null) {
			scienceAppImpl.setLicense(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setLicense(license);
		}

		if (srcFileName == null) {
			scienceAppImpl.setSrcFileName(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setSrcFileName(srcFileName);
		}

		if (targetLanguage == null) {
			scienceAppImpl.setTargetLanguage(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setTargetLanguage(targetLanguage);
		}

		scienceAppImpl.setIsStepLayout(isStepLayout);

		if (layout == null) {
			scienceAppImpl.setLayout(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setLayout(layout);
		}

		if (developers == null) {
			scienceAppImpl.setDevelopers(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setDevelopers(developers);
		}

		if (editorType == null) {
			scienceAppImpl.setEditorType(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setEditorType(editorType);
		}

		scienceAppImpl.setIsPort(isPort);
		scienceAppImpl.setIsCompile(isCompile);
		scienceAppImpl.setProjectCategoryId(projectCategoryId);
		scienceAppImpl.setExecute(execute);

		if (cluster == null) {
			scienceAppImpl.setCluster(StringPool.BLANK);
		}
		else {
			scienceAppImpl.setCluster(cluster);
		}

		scienceAppImpl.setWorkflowId(workflowId);

		scienceAppImpl.resetOriginalValues();

		return scienceAppImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		scienceAppId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
		title = objectInput.readUTF();
		descriptionId = objectInput.readLong();
		previousVersionId = objectInput.readLong();
		iconId = objectInput.readLong();
		manualId = objectInput.readUTF();
		manualUrl = objectInput.readUTF();
		exeFileName = objectInput.readUTF();
		appType = objectInput.readUTF();
		runType = objectInput.readUTF();
		authorId = objectInput.readLong();
		stage = objectInput.readUTF();
		status = objectInput.readInt();
		recentModifierId = objectInput.readLong();
		parallelModule = objectInput.readUTF();
		minCpus = objectInput.readInt();
		maxCpus = objectInput.readInt();
		defaultCpus = objectInput.readInt();
		statusDate = objectInput.readLong();
		openLevel = objectInput.readUTF();
		license = objectInput.readUTF();
		srcFileName = objectInput.readUTF();
		targetLanguage = objectInput.readUTF();
		isStepLayout = objectInput.readBoolean();
		layout = objectInput.readUTF();
		developers = objectInput.readUTF();
		editorType = objectInput.readUTF();
		isPort = objectInput.readBoolean();
		isCompile = objectInput.readBoolean();
		projectCategoryId = objectInput.readLong();
		execute = objectInput.readLong();
		cluster = objectInput.readUTF();
		workflowId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(descriptionId);
		objectOutput.writeLong(previousVersionId);
		objectOutput.writeLong(iconId);

		if (manualId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manualId);
		}

		if (manualUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manualUrl);
		}

		if (exeFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(exeFileName);
		}

		if (appType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(appType);
		}

		if (runType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(runType);
		}

		objectOutput.writeLong(authorId);

		if (stage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stage);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(recentModifierId);

		if (parallelModule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parallelModule);
		}

		objectOutput.writeInt(minCpus);
		objectOutput.writeInt(maxCpus);
		objectOutput.writeInt(defaultCpus);
		objectOutput.writeLong(statusDate);

		if (openLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(openLevel);
		}

		if (license == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(license);
		}

		if (srcFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(srcFileName);
		}

		if (targetLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(targetLanguage);
		}

		objectOutput.writeBoolean(isStepLayout);

		if (layout == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(layout);
		}

		if (developers == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(developers);
		}

		if (editorType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(editorType);
		}

		objectOutput.writeBoolean(isPort);
		objectOutput.writeBoolean(isCompile);
		objectOutput.writeLong(projectCategoryId);
		objectOutput.writeLong(execute);

		if (cluster == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cluster);
		}

		objectOutput.writeLong(workflowId);
	}

	public String uuid;
	public long scienceAppId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String version;
	public String title;
	public long descriptionId;
	public long previousVersionId;
	public long iconId;
	public String manualId;
	public String manualUrl;
	public String exeFileName;
	public String appType;
	public String runType;
	public long authorId;
	public String stage;
	public int status;
	public long recentModifierId;
	public String parallelModule;
	public int minCpus;
	public int maxCpus;
	public int defaultCpus;
	public long statusDate;
	public String openLevel;
	public String license;
	public String srcFileName;
	public String targetLanguage;
	public Boolean isStepLayout;
	public String layout;
	public String developers;
	public String editorType;
	public boolean isPort;
	public boolean isCompile;
	public long projectCategoryId;
	public long execute;
	public String cluster;
	public long workflowId;
}