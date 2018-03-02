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

import kisti.edison.challenge.model.Challenge;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Challenge in entity cache.
 *
 * @author KYJ
 * @see Challenge
 * @generated
 */
public class ChallengeCacheModel implements CacheModel<Challenge>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeId=");
		sb.append(challengeId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", field=");
		sb.append(field);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Challenge toEntityModel() {
		ChallengeImpl challengeImpl = new ChallengeImpl();

		if (uuid == null) {
			challengeImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeImpl.setUuid(uuid);
		}

		challengeImpl.setChallengeId(challengeId);
		challengeImpl.setGroupId(groupId);
		challengeImpl.setCompanyId(companyId);
		challengeImpl.setUserId(userId);

		if (userName == null) {
			challengeImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeImpl.setCreateDate(null);
		}
		else {
			challengeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeImpl.setModifiedDate(null);
		}
		else {
			challengeImpl.setModifiedDate(new Date(modifiedDate));
		}

		challengeImpl.setStatus(status);
		challengeImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeImpl.setStatusDate(null);
		}
		else {
			challengeImpl.setStatusDate(new Date(statusDate));
		}

		if (name == null) {
			challengeImpl.setName(StringPool.BLANK);
		}
		else {
			challengeImpl.setName(name);
		}

		if (field == null) {
			challengeImpl.setField(StringPool.BLANK);
		}
		else {
			challengeImpl.setField(field);
		}

		if (description == null) {
			challengeImpl.setDescription(StringPool.BLANK);
		}
		else {
			challengeImpl.setDescription(description);
		}

		challengeImpl.resetOriginalValues();

		return challengeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeId = objectInput.readLong();
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
		name = objectInput.readUTF();
		field = objectInput.readUTF();
		description = objectInput.readUTF();
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

		objectOutput.writeLong(challengeId);
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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (field == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(field);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long challengeId;
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
	public String name;
	public String field;
	public String description;
}