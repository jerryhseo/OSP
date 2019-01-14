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

import com.kisti.osp.model.OSPFile;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OSPFile in entity cache.
 *
 * @author Jerry h. Seo
 * @see OSPFile
 * @generated
 */
public class OSPFileCacheModel implements CacheModel<OSPFile>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{propertyName=");
		sb.append(propertyName);
		sb.append(", propertyValue=");
		sb.append(propertyValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OSPFile toEntityModel() {
		OSPFileImpl ospFileImpl = new OSPFileImpl();

		if (propertyName == null) {
			ospFileImpl.setPropertyName(StringPool.BLANK);
		}
		else {
			ospFileImpl.setPropertyName(propertyName);
		}

		if (propertyValue == null) {
			ospFileImpl.setPropertyValue(StringPool.BLANK);
		}
		else {
			ospFileImpl.setPropertyValue(propertyValue);
		}

		ospFileImpl.resetOriginalValues();

		return ospFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		propertyName = objectInput.readUTF();
		propertyValue = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (propertyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyName);
		}

		if (propertyValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyValue);
		}
	}

	public String propertyName;
	public String propertyValue;
}