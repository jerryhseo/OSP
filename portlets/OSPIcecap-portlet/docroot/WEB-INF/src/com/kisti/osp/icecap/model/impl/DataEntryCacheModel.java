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

package com.kisti.osp.icecap.model.impl;

import com.kisti.osp.icecap.model.DataEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DataEntry in entity cache.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntry
 * @generated
 */
public class DataEntryCacheModel implements CacheModel<DataEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", collectionId=");
		sb.append(collectionId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", path=");
		sb.append(path);
		sb.append(", simulationSubjectId=");
		sb.append(simulationSubjectId);
		sb.append(", productionTime=");
		sb.append(productionTime);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append(", lastAccessedDate=");
		sb.append(lastAccessedDate);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataEntry toEntityModel() {
		DataEntryImpl dataEntryImpl = new DataEntryImpl();

		dataEntryImpl.setEntryId(entryId);
		dataEntryImpl.setCollectionId(collectionId);
		dataEntryImpl.setGroupId(groupId);
		dataEntryImpl.setCompanyId(companyId);
		dataEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			dataEntryImpl.setCreateDate(null);
		}
		else {
			dataEntryImpl.setCreateDate(new Date(createDate));
		}

		if (comments == null) {
			dataEntryImpl.setComments(StringPool.BLANK);
		}
		else {
			dataEntryImpl.setComments(comments);
		}

		if (path == null) {
			dataEntryImpl.setPath(StringPool.BLANK);
		}
		else {
			dataEntryImpl.setPath(path);
		}

		dataEntryImpl.setSimulationSubjectId(simulationSubjectId);
		dataEntryImpl.setProductionTime(productionTime);
		dataEntryImpl.setViewCount(viewCount);
		dataEntryImpl.setDownloadCount(downloadCount);

		if (lastAccessedDate == Long.MIN_VALUE) {
			dataEntryImpl.setLastAccessedDate(null);
		}
		else {
			dataEntryImpl.setLastAccessedDate(new Date(lastAccessedDate));
		}

		dataEntryImpl.setFileEntryId(fileEntryId);

		dataEntryImpl.resetOriginalValues();

		return dataEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entryId = objectInput.readLong();
		collectionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		comments = objectInput.readUTF();
		path = objectInput.readUTF();
		simulationSubjectId = objectInput.readLong();
		productionTime = objectInput.readLong();
		viewCount = objectInput.readLong();
		downloadCount = objectInput.readLong();
		lastAccessedDate = objectInput.readLong();
		fileEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(entryId);
		objectOutput.writeLong(collectionId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}

		if (path == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(path);
		}

		objectOutput.writeLong(simulationSubjectId);
		objectOutput.writeLong(productionTime);
		objectOutput.writeLong(viewCount);
		objectOutput.writeLong(downloadCount);
		objectOutput.writeLong(lastAccessedDate);
		objectOutput.writeLong(fileEntryId);
	}

	public long entryId;
	public long collectionId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public String comments;
	public String path;
	public long simulationSubjectId;
	public long productionTime;
	public long viewCount;
	public long downloadCount;
	public long lastAccessedDate;
	public long fileEntryId;
}