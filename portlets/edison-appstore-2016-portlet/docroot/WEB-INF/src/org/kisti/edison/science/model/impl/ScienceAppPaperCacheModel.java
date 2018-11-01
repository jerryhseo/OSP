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

import org.kisti.edison.science.model.ScienceAppPaper;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ScienceAppPaper in entity cache.
 *
 * @author EDISON
 * @see ScienceAppPaper
 * @generated
 */
public class ScienceAppPaperCacheModel implements CacheModel<ScienceAppPaper>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", paperSeq=");
		sb.append(paperSeq);
		sb.append(", paperType=");
		sb.append(paperType);
		sb.append(", paperValue=");
		sb.append(paperValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceAppPaper toEntityModel() {
		ScienceAppPaperImpl scienceAppPaperImpl = new ScienceAppPaperImpl();

		scienceAppPaperImpl.setScienceAppId(scienceAppId);
		scienceAppPaperImpl.setPaperSeq(paperSeq);

		if (paperType == null) {
			scienceAppPaperImpl.setPaperType(StringPool.BLANK);
		}
		else {
			scienceAppPaperImpl.setPaperType(paperType);
		}

		if (paperValue == null) {
			scienceAppPaperImpl.setPaperValue(StringPool.BLANK);
		}
		else {
			scienceAppPaperImpl.setPaperValue(paperValue);
		}

		scienceAppPaperImpl.resetOriginalValues();

		return scienceAppPaperImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		paperSeq = objectInput.readLong();
		paperType = objectInput.readUTF();
		paperValue = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(paperSeq);

		if (paperType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperType);
		}

		if (paperValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperValue);
		}
	}

	public long scienceAppId;
	public long paperSeq;
	public String paperType;
	public String paperValue;
}