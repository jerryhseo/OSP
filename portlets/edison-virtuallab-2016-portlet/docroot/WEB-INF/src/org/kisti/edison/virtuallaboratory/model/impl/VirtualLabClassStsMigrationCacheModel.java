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

package org.kisti.edison.virtuallaboratory.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VirtualLabClassStsMigration in entity cache.
 *
 * @author EDISON
 * @see VirtualLabClassStsMigration
 * @generated
 */
public class VirtualLabClassStsMigrationCacheModel implements CacheModel<VirtualLabClassStsMigration>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{groupId=");
		sb.append(groupId);
		sb.append(", virtualLabId=");
		sb.append(virtualLabId);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", universityField=");
		sb.append(universityField);
		sb.append(", virtualLabTitle=");
		sb.append(virtualLabTitle);
		sb.append(", virtualLabPersonName=");
		sb.append(virtualLabPersonName);
		sb.append(", classTitle=");
		sb.append(classTitle);
		sb.append(", virtualClassCd=");
		sb.append(virtualClassCd);
		sb.append(", classCreateDt=");
		sb.append(classCreateDt);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", scienceAppName=");
		sb.append(scienceAppName);
		sb.append(", registerStudentCnt=");
		sb.append(registerStudentCnt);
		sb.append(", executeCount=");
		sb.append(executeCount);
		sb.append(", executeStudentcount=");
		sb.append(executeStudentcount);
		sb.append(", avgerageRuntime=");
		sb.append(avgerageRuntime);
		sb.append(", cputime=");
		sb.append(cputime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VirtualLabClassStsMigration toEntityModel() {
		VirtualLabClassStsMigrationImpl virtualLabClassStsMigrationImpl = new VirtualLabClassStsMigrationImpl();

		virtualLabClassStsMigrationImpl.setGroupId(groupId);
		virtualLabClassStsMigrationImpl.setVirtualLabId(virtualLabId);
		virtualLabClassStsMigrationImpl.setClassId(classId);

		if (universityField == null) {
			virtualLabClassStsMigrationImpl.setUniversityField(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setUniversityField(universityField);
		}

		if (virtualLabTitle == null) {
			virtualLabClassStsMigrationImpl.setVirtualLabTitle(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setVirtualLabTitle(virtualLabTitle);
		}

		if (virtualLabPersonName == null) {
			virtualLabClassStsMigrationImpl.setVirtualLabPersonName(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setVirtualLabPersonName(virtualLabPersonName);
		}

		if (classTitle == null) {
			virtualLabClassStsMigrationImpl.setClassTitle(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setClassTitle(classTitle);
		}

		if (virtualClassCd == null) {
			virtualLabClassStsMigrationImpl.setVirtualClassCd(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setVirtualClassCd(virtualClassCd);
		}

		if (classCreateDt == Long.MIN_VALUE) {
			virtualLabClassStsMigrationImpl.setClassCreateDt(null);
		}
		else {
			virtualLabClassStsMigrationImpl.setClassCreateDt(new Date(
					classCreateDt));
		}

		if (scienceAppId == null) {
			virtualLabClassStsMigrationImpl.setScienceAppId(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setScienceAppId(scienceAppId);
		}

		if (scienceAppName == null) {
			virtualLabClassStsMigrationImpl.setScienceAppName(StringPool.BLANK);
		}
		else {
			virtualLabClassStsMigrationImpl.setScienceAppName(scienceAppName);
		}

		virtualLabClassStsMigrationImpl.setRegisterStudentCnt(registerStudentCnt);
		virtualLabClassStsMigrationImpl.setExecuteCount(executeCount);
		virtualLabClassStsMigrationImpl.setExecuteStudentcount(executeStudentcount);
		virtualLabClassStsMigrationImpl.setAvgerageRuntime(avgerageRuntime);
		virtualLabClassStsMigrationImpl.setCputime(cputime);

		virtualLabClassStsMigrationImpl.resetOriginalValues();

		return virtualLabClassStsMigrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		groupId = objectInput.readLong();
		virtualLabId = objectInput.readLong();
		classId = objectInput.readLong();
		universityField = objectInput.readUTF();
		virtualLabTitle = objectInput.readUTF();
		virtualLabPersonName = objectInput.readUTF();
		classTitle = objectInput.readUTF();
		virtualClassCd = objectInput.readUTF();
		classCreateDt = objectInput.readLong();
		scienceAppId = objectInput.readUTF();
		scienceAppName = objectInput.readUTF();
		registerStudentCnt = objectInput.readLong();
		executeCount = objectInput.readLong();
		executeStudentcount = objectInput.readLong();
		avgerageRuntime = objectInput.readLong();
		cputime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(virtualLabId);
		objectOutput.writeLong(classId);

		if (universityField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(universityField);
		}

		if (virtualLabTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabTitle);
		}

		if (virtualLabPersonName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualLabPersonName);
		}

		if (classTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classTitle);
		}

		if (virtualClassCd == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(virtualClassCd);
		}

		objectOutput.writeLong(classCreateDt);

		if (scienceAppId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppId);
		}

		if (scienceAppName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scienceAppName);
		}

		objectOutput.writeLong(registerStudentCnt);
		objectOutput.writeLong(executeCount);
		objectOutput.writeLong(executeStudentcount);
		objectOutput.writeLong(avgerageRuntime);
		objectOutput.writeLong(cputime);
	}

	public long groupId;
	public long virtualLabId;
	public long classId;
	public String universityField;
	public String virtualLabTitle;
	public String virtualLabPersonName;
	public String classTitle;
	public String virtualClassCd;
	public long classCreateDt;
	public String scienceAppId;
	public String scienceAppName;
	public long registerStudentCnt;
	public long executeCount;
	public long executeStudentcount;
	public long avgerageRuntime;
	public long cputime;
}