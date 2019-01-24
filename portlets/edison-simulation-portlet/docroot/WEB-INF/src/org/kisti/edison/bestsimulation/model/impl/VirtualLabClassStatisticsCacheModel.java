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

import org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VirtualLabClassStatistics in entity cache.
 *
 * @author EDISON
 * @see VirtualLabClassStatistics
 * @generated
 */
public class VirtualLabClassStatisticsCacheModel implements CacheModel<VirtualLabClassStatistics>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{virtualLabId=");
		sb.append(virtualLabId);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", virtualLabTitle=");
		sb.append(virtualLabTitle);
		sb.append(", classTitle=");
		sb.append(classTitle);
		sb.append(", virtualLabPersonName=");
		sb.append(virtualLabPersonName);
		sb.append(", registerStudentCnt=");
		sb.append(registerStudentCnt);
		sb.append(", virtualLabUsersId=");
		sb.append(virtualLabUsersId);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", executeUserCnt=");
		sb.append(executeUserCnt);
		sb.append(", executeCnt=");
		sb.append(executeCnt);
		sb.append(", cputime=");
		sb.append(cputime);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", university=");
		sb.append(university);
		sb.append(", classCreateDt=");
		sb.append(classCreateDt);
		sb.append(", virtualLabUseYn=");
		sb.append(virtualLabUseYn);
		sb.append(", classUseYn=");
		sb.append(classUseYn);
		sb.append(", lastModifiedDt=");
		sb.append(lastModifiedDt);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VirtualLabClassStatistics toEntityModel() {
		VirtualLabClassStatisticsImpl virtualLabClassStatisticsImpl = new VirtualLabClassStatisticsImpl();

		virtualLabClassStatisticsImpl.setVirtualLabId(virtualLabId);

		if (classId == null) {
			virtualLabClassStatisticsImpl.setClassId(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setClassId(classId);
		}

		if (virtualLabTitle == null) {
			virtualLabClassStatisticsImpl.setVirtualLabTitle(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setVirtualLabTitle(virtualLabTitle);
		}

		if (classTitle == null) {
			virtualLabClassStatisticsImpl.setClassTitle(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setClassTitle(classTitle);
		}

		if (virtualLabPersonName == null) {
			virtualLabClassStatisticsImpl.setVirtualLabPersonName(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setVirtualLabPersonName(virtualLabPersonName);
		}

		virtualLabClassStatisticsImpl.setRegisterStudentCnt(registerStudentCnt);

		if (virtualLabUsersId == null) {
			virtualLabClassStatisticsImpl.setVirtualLabUsersId(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setVirtualLabUsersId(virtualLabUsersId);
		}

		if (scienceAppId == null) {
			virtualLabClassStatisticsImpl.setScienceAppId(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setScienceAppId(scienceAppId);
		}

		virtualLabClassStatisticsImpl.setExecuteUserCnt(executeUserCnt);
		virtualLabClassStatisticsImpl.setExecuteCnt(executeCnt);

		if (cputime == null) {
			virtualLabClassStatisticsImpl.setCputime(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setCputime(cputime);
		}

		virtualLabClassStatisticsImpl.setGroupId(groupId);
		virtualLabClassStatisticsImpl.setUniversity(university);

		if (classCreateDt == Long.MIN_VALUE) {
			virtualLabClassStatisticsImpl.setClassCreateDt(null);
		}
		else {
			virtualLabClassStatisticsImpl.setClassCreateDt(new Date(
					classCreateDt));
		}

		if (virtualLabUseYn == null) {
			virtualLabClassStatisticsImpl.setVirtualLabUseYn(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setVirtualLabUseYn(virtualLabUseYn);
		}

		if (classUseYn == null) {
			virtualLabClassStatisticsImpl.setClassUseYn(StringPool.BLANK);
		}
		else {
			virtualLabClassStatisticsImpl.setClassUseYn(classUseYn);
		}

		if (lastModifiedDt == Long.MIN_VALUE) {
			virtualLabClassStatisticsImpl.setLastModifiedDt(null);
		}
		else {
			virtualLabClassStatisticsImpl.setLastModifiedDt(new Date(
					lastModifiedDt));
		}

		virtualLabClassStatisticsImpl.resetOriginalValues();

		return virtualLabClassStatisticsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		virtualLabId = objectInput.readLong();
		classId = objectInput.readUTF();
		virtualLabTitle = objectInput.readUTF();
		classTitle = objectInput.readUTF();
		virtualLabPersonName = objectInput.readUTF();
		registerStudentCnt = objectInput.readLong();
		virtualLabUsersId = objectInput.readUTF();
		scienceAppId = objectInput.readUTF();
		executeUserCnt = objectInput.readLong();
		executeCnt = objectInput.readLong();
		cputime = objectInput.readUTF();
		groupId = objectInput.readLong();
		university = objectInput.readLong();
		classCreateDt = objectInput.readLong();
		virtualLabUseYn = objectInput.readUTF();
		classUseYn = objectInput.readUTF();
		lastModifiedDt = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(virtualLabId);

		if (classId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classId);
		}

		if (virtualLabTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabTitle);
		}

		if (classTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classTitle);
		}

		if (virtualLabPersonName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabPersonName);
		}

		objectOutput.writeLong(registerStudentCnt);

		if (virtualLabUsersId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabUsersId);
		}

		if (scienceAppId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppId);
		}

		objectOutput.writeLong(executeUserCnt);
		objectOutput.writeLong(executeCnt);

		if (cputime == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cputime);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(university);
		objectOutput.writeLong(classCreateDt);

		if (virtualLabUseYn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabUseYn);
		}

		if (classUseYn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classUseYn);
		}

		objectOutput.writeLong(lastModifiedDt);
	}

	public long virtualLabId;
	public String classId;
	public String virtualLabTitle;
	public String classTitle;
	public String virtualLabPersonName;
	public long registerStudentCnt;
	public String virtualLabUsersId;
	public String scienceAppId;
	public long executeUserCnt;
	public long executeCnt;
	public String cputime;
	public long groupId;
	public long university;
	public long classCreateDt;
	public String virtualLabUseYn;
	public String classUseYn;
	public long lastModifiedDt;
}