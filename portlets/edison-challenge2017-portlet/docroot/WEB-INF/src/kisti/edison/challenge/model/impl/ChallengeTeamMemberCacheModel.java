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

package kisti.edison.challenge.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import kisti.edison.challenge.model.ChallengeTeamMember;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeTeamMember in entity cache.
 *
 * @author KYJ
 * @see ChallengeTeamMember
 * @generated
 */
public class ChallengeTeamMemberCacheModel implements CacheModel<ChallengeTeamMember>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeTeamMemberId=");
		sb.append(challengeTeamMemberId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", applyUserId=");
		sb.append(applyUserId);
		sb.append(", applyUserName=");
		sb.append(applyUserName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", institute=");
		sb.append(institute);
		sb.append(", major=");
		sb.append(major);
		sb.append(", grade=");
		sb.append(grade);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", leader=");
		sb.append(leader);
		sb.append(", challengeTeamId=");
		sb.append(challengeTeamId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeTeamMember toEntityModel() {
		ChallengeTeamMemberImpl challengeTeamMemberImpl = new ChallengeTeamMemberImpl();

		if (uuid == null) {
			challengeTeamMemberImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setUuid(uuid);
		}

		challengeTeamMemberImpl.setChallengeTeamMemberId(challengeTeamMemberId);
		challengeTeamMemberImpl.setGroupId(groupId);
		challengeTeamMemberImpl.setCompanyId(companyId);
		challengeTeamMemberImpl.setUserId(userId);

		if (userName == null) {
			challengeTeamMemberImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeTeamMemberImpl.setCreateDate(null);
		}
		else {
			challengeTeamMemberImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeTeamMemberImpl.setModifiedDate(null);
		}
		else {
			challengeTeamMemberImpl.setModifiedDate(new Date(modifiedDate));
		}

		challengeTeamMemberImpl.setStatus(status);
		challengeTeamMemberImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeTeamMemberImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeTeamMemberImpl.setStatusDate(null);
		}
		else {
			challengeTeamMemberImpl.setStatusDate(new Date(statusDate));
		}

		challengeTeamMemberImpl.setApplyUserId(applyUserId);

		if (applyUserName == null) {
			challengeTeamMemberImpl.setApplyUserName(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setApplyUserName(applyUserName);
		}

		if (email == null) {
			challengeTeamMemberImpl.setEmail(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setEmail(email);
		}

		if (institute == null) {
			challengeTeamMemberImpl.setInstitute(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setInstitute(institute);
		}

		if (major == null) {
			challengeTeamMemberImpl.setMajor(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setMajor(major);
		}

		if (grade == null) {
			challengeTeamMemberImpl.setGrade(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setGrade(grade);
		}

		if (phone == null) {
			challengeTeamMemberImpl.setPhone(StringPool.BLANK);
		}
		else {
			challengeTeamMemberImpl.setPhone(phone);
		}

		challengeTeamMemberImpl.setLeader(leader);
		challengeTeamMemberImpl.setChallengeTeamId(challengeTeamId);

		challengeTeamMemberImpl.resetOriginalValues();

		return challengeTeamMemberImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeTeamMemberId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		status = objectInput.readInt();
		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		applyUserId = objectInput.readLong();
		applyUserName = objectInput.readUTF();
		email = objectInput.readUTF();
		institute = objectInput.readUTF();
		major = objectInput.readUTF();
		grade = objectInput.readUTF();
		phone = objectInput.readUTF();
		leader = objectInput.readBoolean();
		challengeTeamId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(challengeTeamMemberId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeInt(status);
		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
		objectOutput.writeLong(applyUserId);

		if (applyUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applyUserName);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (institute == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(institute);
		}

		if (major == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(major);
		}

		if (grade == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grade);
		}

		if (phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phone);
		}

		objectOutput.writeBoolean(leader);
		objectOutput.writeLong(challengeTeamId);
	}

	public String uuid;
	public long challengeTeamMemberId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long applyUserId;
	public String applyUserName;
	public String email;
	public String institute;
	public String major;
	public String grade;
	public String phone;
	public boolean leader;
	public long challengeTeamId;
}