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

import kisti.edison.challenge.model.ChallengeEvaluation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeEvaluation in entity cache.
 *
 * @author KYJ
 * @see ChallengeEvaluation
 * @generated
 */
public class ChallengeEvaluationCacheModel implements CacheModel<ChallengeEvaluation>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeEvaluationId=");
		sb.append(challengeEvaluationId);
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
		sb.append(", score=");
		sb.append(score);
		sb.append(", challengeTeamId=");
		sb.append(challengeTeamId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeEvaluation toEntityModel() {
		ChallengeEvaluationImpl challengeEvaluationImpl = new ChallengeEvaluationImpl();

		if (uuid == null) {
			challengeEvaluationImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeEvaluationImpl.setUuid(uuid);
		}

		challengeEvaluationImpl.setChallengeEvaluationId(challengeEvaluationId);
		challengeEvaluationImpl.setGroupId(groupId);
		challengeEvaluationImpl.setCompanyId(companyId);
		challengeEvaluationImpl.setUserId(userId);

		if (userName == null) {
			challengeEvaluationImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeEvaluationImpl.setCreateDate(null);
		}
		else {
			challengeEvaluationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeEvaluationImpl.setModifiedDate(null);
		}
		else {
			challengeEvaluationImpl.setModifiedDate(new Date(modifiedDate));
		}

		challengeEvaluationImpl.setStatus(status);
		challengeEvaluationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeEvaluationImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeEvaluationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeEvaluationImpl.setStatusDate(null);
		}
		else {
			challengeEvaluationImpl.setStatusDate(new Date(statusDate));
		}

		if (assessmentItem == null) {
			challengeEvaluationImpl.setAssessmentItem(StringPool.BLANK);
		}
		else {
			challengeEvaluationImpl.setAssessmentItem(assessmentItem);
		}

		challengeEvaluationImpl.setDistribution(distribution);
		challengeEvaluationImpl.setScore(score);
		challengeEvaluationImpl.setChallengeTeamId(challengeTeamId);

		challengeEvaluationImpl.resetOriginalValues();

		return challengeEvaluationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeEvaluationId = objectInput.readLong();
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
		score = objectInput.readDouble();
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

		objectOutput.writeLong(challengeEvaluationId);
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
		objectOutput.writeDouble(score);
		objectOutput.writeLong(challengeTeamId);
	}

	public String uuid;
	public long challengeEvaluationId;
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
	public double score;
	public long challengeTeamId;
}