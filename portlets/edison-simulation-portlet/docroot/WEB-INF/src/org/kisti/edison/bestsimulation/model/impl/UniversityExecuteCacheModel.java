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

package org.kisti.edison.bestsimulation.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.bestsimulation.model.UniversityExecute;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UniversityExecute in entity cache.
 *
 * @author EDISON
 * @see UniversityExecute
 * @generated
 */
public class UniversityExecuteCacheModel implements CacheModel<UniversityExecute>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{executeDate=");
		sb.append(executeDate);
		sb.append(", universityField=");
		sb.append(universityField);
		sb.append(", userCnt=");
		sb.append(userCnt);
		sb.append(", avgExeTime=");
		sb.append(avgExeTime);
		sb.append(", exeCnt=");
		sb.append(exeCnt);
		sb.append(", cpuTime=");
		sb.append(cpuTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UniversityExecute toEntityModel() {
		UniversityExecuteImpl universityExecuteImpl = new UniversityExecuteImpl();

		if (executeDate == null) {
			universityExecuteImpl.setExecuteDate(StringPool.BLANK);
		}
		else {
			universityExecuteImpl.setExecuteDate(executeDate);
		}

		universityExecuteImpl.setUniversityField(universityField);
		universityExecuteImpl.setUserCnt(userCnt);
		universityExecuteImpl.setAvgExeTime(avgExeTime);
		universityExecuteImpl.setExeCnt(exeCnt);
		universityExecuteImpl.setCpuTime(cpuTime);

		universityExecuteImpl.resetOriginalValues();

		return universityExecuteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		executeDate = objectInput.readUTF();
		universityField = objectInput.readLong();
		userCnt = objectInput.readLong();
		avgExeTime = objectInput.readLong();
		exeCnt = objectInput.readLong();
		cpuTime = objectInput.readLong();
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

		objectOutput.writeLong(universityField);
		objectOutput.writeLong(userCnt);
		objectOutput.writeLong(avgExeTime);
		objectOutput.writeLong(exeCnt);
		objectOutput.writeLong(cpuTime);
	}

	public String executeDate;
	public long universityField;
	public long userCnt;
	public long avgExeTime;
	public long exeCnt;
	public long cpuTime;
}