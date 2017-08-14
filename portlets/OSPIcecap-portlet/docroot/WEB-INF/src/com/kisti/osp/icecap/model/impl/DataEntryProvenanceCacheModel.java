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

import com.kisti.osp.icecap.model.DataEntryProvenance;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DataEntryProvenance in entity cache.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryProvenance
 * @generated
 */
public class DataEntryProvenanceCacheModel implements CacheModel<DataEntryProvenance>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", inputData=");
		sb.append(inputData);
		sb.append(", PROVStructure=");
		sb.append(PROVStructure);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataEntryProvenance toEntityModel() {
		DataEntryProvenanceImpl dataEntryProvenanceImpl = new DataEntryProvenanceImpl();

		dataEntryProvenanceImpl.setEntryId(entryId);

		if (inputData == null) {
			dataEntryProvenanceImpl.setInputData(StringPool.BLANK);
		}
		else {
			dataEntryProvenanceImpl.setInputData(inputData);
		}

		if (PROVStructure == null) {
			dataEntryProvenanceImpl.setPROVStructure(StringPool.BLANK);
		}
		else {
			dataEntryProvenanceImpl.setPROVStructure(PROVStructure);
		}

		dataEntryProvenanceImpl.resetOriginalValues();

		return dataEntryProvenanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entryId = objectInput.readLong();
		inputData = objectInput.readUTF();
		PROVStructure = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(entryId);

		if (inputData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inputData);
		}

		if (PROVStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(PROVStructure);
		}
	}

	public long entryId;
	public String inputData;
	public String PROVStructure;
}