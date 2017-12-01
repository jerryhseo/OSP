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

package org.kisti.edison.bestsimulation.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.bestsimulation.model.ScienceAppExecute;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ScienceAppExecute in entity cache.
 *
 * @author EDISON
 * @see ScienceAppExecute
 * @generated
 */
public class ScienceAppExecuteCacheModel implements CacheModel<ScienceAppExecute>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{executeDate=");
		sb.append(executeDate);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", userCnt=");
		sb.append(userCnt);
		sb.append(", avgExeTime=");
		sb.append(avgExeTime);
		sb.append(", exeCnt=");
		sb.append(exeCnt);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceAppExecute toEntityModel() {
		ScienceAppExecuteImpl scienceAppExecuteImpl = new ScienceAppExecuteImpl();

		if (executeDate == null) {
			scienceAppExecuteImpl.setExecuteDate(StringPool.BLANK);
		}
		else {
			scienceAppExecuteImpl.setExecuteDate(executeDate);
		}

		scienceAppExecuteImpl.setScienceAppId(scienceAppId);
		scienceAppExecuteImpl.setUserCnt(userCnt);
		scienceAppExecuteImpl.setAvgExeTime(avgExeTime);
		scienceAppExecuteImpl.setExeCnt(exeCnt);

		scienceAppExecuteImpl.resetOriginalValues();

		return scienceAppExecuteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		executeDate = objectInput.readUTF();
		scienceAppId = objectInput.readLong();
		userCnt = objectInput.readLong();
		avgExeTime = objectInput.readLong();
		exeCnt = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (executeDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(executeDate);
		}

		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(userCnt);
		objectOutput.writeLong(avgExeTime);
		objectOutput.writeLong(exeCnt);
	}

	public String executeDate;
	public long scienceAppId;
	public long userCnt;
	public long avgExeTime;
	public long exeCnt;
}