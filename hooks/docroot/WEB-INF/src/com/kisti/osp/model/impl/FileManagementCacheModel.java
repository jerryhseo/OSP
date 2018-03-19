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

package com.kisti.osp.model.impl;

import com.kisti.osp.model.FileManagement;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FileManagement in entity cache.
 *
 * @author Jerry h. Seo
 * @see FileManagement
 * @generated
 */
public class FileManagementCacheModel implements CacheModel<FileManagement>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", assigned=");
		sb.append(assigned);
		sb.append(", used=");
		sb.append(used);
		sb.append(", lastModified=");
		sb.append(lastModified);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FileManagement toEntityModel() {
		FileManagementImpl fileManagementImpl = new FileManagementImpl();

		fileManagementImpl.setUserId(userId);
		fileManagementImpl.setAssigned(assigned);
		fileManagementImpl.setUsed(used);

		if (lastModified == Long.MIN_VALUE) {
			fileManagementImpl.setLastModified(null);
		}
		else {
			fileManagementImpl.setLastModified(new Date(lastModified));
		}

		fileManagementImpl.setStatus(status);

		fileManagementImpl.resetOriginalValues();

		return fileManagementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();
		assigned = objectInput.readInt();
		used = objectInput.readInt();
		lastModified = objectInput.readLong();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);
		objectOutput.writeInt(assigned);
		objectOutput.writeInt(used);
		objectOutput.writeLong(lastModified);
		objectOutput.writeInt(status);
	}

	public long userId;
	public int assigned;
	public int used;
	public long lastModified;
	public int status;
}