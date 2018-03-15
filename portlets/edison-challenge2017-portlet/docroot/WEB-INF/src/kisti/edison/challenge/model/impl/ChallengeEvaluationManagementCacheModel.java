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

import kisti.edison.challenge.model.ChallengeEvaluationManagement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeEvaluationManagement in entity cache.
 *
 * @author KYJ
 * @see ChallengeEvaluationManagement
 * @generated
 */
public class ChallengeEvaluationManagementCacheModel implements CacheModel<ChallengeEvaluationManagement>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeEvaluationManagementId=");
		sb.append(challengeEvaluationManagementId);
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
		sb.append(", assessmentItem=");
		sb.append(assessmentItem);
		sb.append(", distribution=");
		sb.append(distribution);
		sb.append(", childChallengeId=");
		sb.append(childChallengeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeEvaluationManagement toEntityModel() {
		ChallengeEvaluationManagementImpl challengeEvaluationManagementImpl = new ChallengeEvaluationManagementImpl();

		if (uuid == null) {
			challengeEvaluationManagementImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeEvaluationManagementImpl.setUuid(uuid);
		}

		challengeEvaluationManagementImpl.setChallengeEvaluationManagementId(challengeEvaluationManagementId);
		challengeEvaluationManagementImpl.setGroupId(groupId);
		challengeEvaluationManagementImpl.setCompanyId(companyId);
		challengeEvaluationManagementImpl.setUserId(userId);

		if (userName == null) {
			challengeEvaluationManagementImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationManagementImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeEvaluationManagementImpl.setCreateDate(null);
		}
		else {
			challengeEvaluationManagementImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeEvaluationManagementImpl.setModifiedDate(null);
		}
		else {
			challengeEvaluationManagementImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		challengeEvaluationManagementImpl.setStatus(status);
		challengeEvaluationManagementImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeEvaluationManagementImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationManagementImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeEvaluationManagementImpl.setStatusDate(null);
		}
		else {
			challengeEvaluationManagementImpl.setStatusDate(new Date(statusDate));
		}

		if (assessmentItem == null) {
			challengeEvaluationManagementImpl.setAssessmentItem(StringPool.BLANK);
		}
		else {
			challengeEvaluationManagementImpl.setAssessmentItem(assessmentItem);
		}

		challengeEvaluationManagementImpl.setDistribution(distribution);
		challengeEvaluationManagementImpl.setChildChallengeId(childChallengeId);

		challengeEvaluationManagementImpl.resetOriginalValues();

		return challengeEvaluationManagementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeEvaluationManagementId = objectInput.readLong();
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
		assessmentItem = objectInput.readUTF();
		distribution = objectInput.readDouble();
		childChallengeId = objectInput.readLong();
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

		objectOutput.writeLong(challengeEvaluationManagementId);
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

		if (assessmentItem == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(assessmentItem);
		}

		objectOutput.writeDouble(distribution);
		objectOutput.writeLong(childChallengeId);
	}

	public String uuid;
	public long challengeEvaluationManagementId;
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
	public String assessmentItem;
	public double distribution;
	public long childChallengeId;
}