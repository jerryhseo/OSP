/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

import org.kisti.edison.science.model.ScienceAppCompile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScienceAppCompile in entity cache.
 *
 * @author EDISON
 * @see ScienceAppCompile
 * @generated
 */
public class ScienceAppCompileCacheModel implements CacheModel<ScienceAppCompile>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", compileUrl=");
		sb.append(compileUrl);
		sb.append(", result=");
		sb.append(result);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceAppCompile toEntityModel() {
		ScienceAppCompileImpl scienceAppCompileImpl = new ScienceAppCompileImpl();

		scienceAppCompileImpl.setScienceAppId(scienceAppId);
		scienceAppCompileImpl.setUserId(userId);

		if (compileUrl == null) {
			scienceAppCompileImpl.setCompileUrl(StringPool.BLANK);
		}
		else {
			scienceAppCompileImpl.setCompileUrl(compileUrl);
		}

		if (result == null) {
			scienceAppCompileImpl.setResult(StringPool.BLANK);
		}
		else {
			scienceAppCompileImpl.setResult(result);
		}

		if (createDate == Long.MIN_VALUE) {
			scienceAppCompileImpl.setCreateDate(null);
		}
		else {
			scienceAppCompileImpl.setCreateDate(new Date(createDate));
		}

		scienceAppCompileImpl.resetOriginalValues();

		return scienceAppCompileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		userId = objectInput.readLong();
		compileUrl = objectInput.readUTF();
		result = objectInput.readUTF();
		createDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(userId);

		if (compileUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(compileUrl);
		}

		if (result == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(result);
		}

		objectOutput.writeLong(createDate);
	}

	public long scienceAppId;
	public long userId;
	public String compileUrl;
	public String result;
	public long createDate;
}