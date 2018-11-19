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

import org.kisti.edison.osp.model.Execute;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Execute in entity cache.
 *
 * @author edison
 * @see Execute
 * @generated
 */
public class ExecuteCacheModel implements CacheModel<Execute>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{projectId=");
		sb.append(projectId);
		sb.append(", analyzerId=");
		sb.append(analyzerId);
		sb.append(", executeDataStructure=");
		sb.append(executeDataStructure);
		sb.append(", executeDate=");
		sb.append(executeDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Execute toEntityModel() {
		ExecuteImpl executeImpl = new ExecuteImpl();

		executeImpl.setProjectId(projectId);

		if (analyzerId == null) {
			executeImpl.setAnalyzerId(StringPool.BLANK);
		}
		else {
			executeImpl.setAnalyzerId(analyzerId);
		}

		if (executeDataStructure == null) {
			executeImpl.setExecuteDataStructure(StringPool.BLANK);
		}
		else {
			executeImpl.setExecuteDataStructure(executeDataStructure);
		}

		if (executeDate == Long.MIN_VALUE) {
			executeImpl.setExecuteDate(null);
		}
		else {
			executeImpl.setExecuteDate(new Date(executeDate));
		}

		executeImpl.resetOriginalValues();

		return executeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectId = objectInput.readLong();
		analyzerId = objectInput.readUTF();
		executeDataStructure = objectInput.readUTF();
		executeDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectId);

		if (analyzerId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(analyzerId);
		}

		if (executeDataStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(executeDataStructure);
		}

		objectOutput.writeLong(executeDate);
	}

	public long projectId;
	public String analyzerId;
	public String executeDataStructure;
	public long executeDate;
}