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

import com.kisti.osp.icecap.model.DataTypeStructure;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DataTypeStructure in entity cache.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructure
 * @generated
 */
public class DataTypeStructureCacheModel implements CacheModel<DataTypeStructure>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{typeId=");
		sb.append(typeId);
		sb.append(", structure=");
		sb.append(structure);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataTypeStructure toEntityModel() {
		DataTypeStructureImpl dataTypeStructureImpl = new DataTypeStructureImpl();

		dataTypeStructureImpl.setTypeId(typeId);

		if (structure == null) {
			dataTypeStructureImpl.setStructure(StringPool.BLANK);
		}
		else {
			dataTypeStructureImpl.setStructure(structure);
		}

		dataTypeStructureImpl.resetOriginalValues();

		return dataTypeStructureImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		typeId = objectInput.readLong();
		structure = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(typeId);

		if (structure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(structure);
		}
	}

	public long typeId;
	public String structure;
}