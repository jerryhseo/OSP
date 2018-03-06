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

import kisti.edison.challenge.model.ChallengeEvaluationScore;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeEvaluationScore in entity cache.
 *
 * @author KYJ
 * @see ChallengeEvaluationScore
 * @generated
 */
public class ChallengeEvaluationScoreCacheModel implements CacheModel<ChallengeEvaluationScore>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeEvaluationScoreId=");
		sb.append(challengeEvaluationScoreId);
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
		sb.append(", score=");
		sb.append(score);
		sb.append(", challengeTeamId=");
		sb.append(challengeTeamId);
		sb.append(", challengeEvaluationManagementId=");
		sb.append(challengeEvaluationManagementId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeEvaluationScore toEntityModel() {
		ChallengeEvaluationScoreImpl challengeEvaluationScoreImpl = new ChallengeEvaluationScoreImpl();

		if (uuid == null) {
			challengeEvaluationScoreImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeEvaluationScoreImpl.setUuid(uuid);
		}

		challengeEvaluationScoreImpl.setChallengeEvaluationScoreId(challengeEvaluationScoreId);
		challengeEvaluationScoreImpl.setGroupId(groupId);
		challengeEvaluationScoreImpl.setCompanyId(companyId);
		challengeEvaluationScoreImpl.setUserId(userId);

		if (userName == null) {
			challengeEvaluationScoreImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationScoreImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeEvaluationScoreImpl.setCreateDate(null);
		}
		else {
			challengeEvaluationScoreImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeEvaluationScoreImpl.setModifiedDate(null);
		}
		else {
			challengeEvaluationScoreImpl.setModifiedDate(new Date(modifiedDate));
		}

		challengeEvaluationScoreImpl.setStatus(status);
		challengeEvaluationScoreImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeEvaluationScoreImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationScoreImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeEvaluationScoreImpl.setStatusDate(null);
		}
		else {
			challengeEvaluationScoreImpl.setStatusDate(new Date(statusDate));
		}

		challengeEvaluationScoreImpl.setScore(score);
		challengeEvaluationScoreImpl.setChallengeTeamId(challengeTeamId);
		challengeEvaluationScoreImpl.setChallengeEvaluationManagementId(challengeEvaluationManagementId);

		challengeEvaluationScoreImpl.resetOriginalValues();

		return challengeEvaluationScoreImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeEvaluationScoreId = objectInput.readLong();
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
		score = objectInput.readDouble();
		challengeTeamId = objectInput.readLong();
		challengeEvaluationManagementId = objectInput.readLong();
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

		objectOutput.writeLong(challengeEvaluationScoreId);
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
		objectOutput.writeDouble(score);
		objectOutput.writeLong(challengeTeamId);
		objectOutput.writeLong(challengeEvaluationManagementId);
	}

	public String uuid;
	public long challengeEvaluationScoreId;
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
	public double score;
	public long challengeTeamId;
	public long challengeEvaluationManagementId;
}