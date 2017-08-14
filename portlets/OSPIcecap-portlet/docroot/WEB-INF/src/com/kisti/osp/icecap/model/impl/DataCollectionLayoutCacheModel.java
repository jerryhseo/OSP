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

import com.kisti.osp.icecap.model.DataCollectionLayout;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DataCollectionLayout in entity cache.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLayout
 * @generated
 */
public class DataCollectionLayoutCacheModel implements CacheModel<DataCollectionLayout>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{collectionId=");
		sb.append(collectionId);
		sb.append(", layoutStr=");
		sb.append(layoutStr);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataCollectionLayout toEntityModel() {
		DataCollectionLayoutImpl dataCollectionLayoutImpl = new DataCollectionLayoutImpl();

		dataCollectionLayoutImpl.setCollectionId(collectionId);

		if (layoutStr == null) {
			dataCollectionLayoutImpl.setLayoutStr(StringPool.BLANK);
		}
		else {
			dataCollectionLayoutImpl.setLayoutStr(layoutStr);
		}

		dataCollectionLayoutImpl.resetOriginalValues();

		return dataCollectionLayoutImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		collectionId = objectInput.readLong();
		layoutStr = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(collectionId);

		if (layoutStr == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(layoutStr);
		}
	}

	public long collectionId;
	public String layoutStr;
}