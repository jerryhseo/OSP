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

import kisti.edison.challenge.model.ChildChallenge;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChildChallenge in entity cache.
 *
 * @author KYJ
 * @see ChildChallenge
 * @generated
 */
public class ChildChallengeCacheModel implements CacheModel<ChildChallenge>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", childChallengeId=");
		sb.append(childChallengeId);
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
		sb.append(", number=");
		sb.append(number);
		sb.append(", presentationDay=");
		sb.append(presentationDay);
		sb.append(", paperStartDay=");
		sb.append(paperStartDay);
		sb.append(", paperEndDay=");
		sb.append(paperEndDay);
		sb.append(", evaluationDay=");
		sb.append(evaluationDay);
		sb.append(", challengeStartDay=");
		sb.append(challengeStartDay);
		sb.append(", challengeEndDay=");
		sb.append(challengeEndDay);
		sb.append(", challengeStatus=");
		sb.append(challengeStatus);
		sb.append(", challengeId=");
		sb.append(challengeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChildChallenge toEntityModel() {
		ChildChallengeImpl childChallengeImpl = new ChildChallengeImpl();

		if (uuid == null) {
			childChallengeImpl.setUuid(StringPool.BLANK);
		}
		else {
			childChallengeImpl.setUuid(uuid);
		}

		childChallengeImpl.setChildChallengeId(childChallengeId);
		childChallengeImpl.setGroupId(groupId);
		childChallengeImpl.setCompanyId(companyId);
		childChallengeImpl.setUserId(userId);

		if (userName == null) {
			childChallengeImpl.setUserName(StringPool.BLANK);
		}
		else {
			childChallengeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			childChallengeImpl.setCreateDate(null);
		}
		else {
			childChallengeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			childChallengeImpl.setModifiedDate(null);
		}
		else {
			childChallengeImpl.setModifiedDate(new Date(modifiedDate));
		}

		childChallengeImpl.setStatus(status);
		childChallengeImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			childChallengeImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			childChallengeImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			childChallengeImpl.setStatusDate(null);
		}
		else {
			childChallengeImpl.setStatusDate(new Date(statusDate));
		}

		childChallengeImpl.setNumber(number);

		if (presentationDay == Long.MIN_VALUE) {
			childChallengeImpl.setPresentationDay(null);
		}
		else {
			childChallengeImpl.setPresentationDay(new Date(presentationDay));
		}

		if (paperStartDay == Long.MIN_VALUE) {
			childChallengeImpl.setPaperStartDay(null);
		}
		else {
			childChallengeImpl.setPaperStartDay(new Date(paperStartDay));
		}

		if (paperEndDay == Long.MIN_VALUE) {
			childChallengeImpl.setPaperEndDay(null);
		}
		else {
			childChallengeImpl.setPaperEndDay(new Date(paperEndDay));
		}

		if (evaluationDay == Long.MIN_VALUE) {
			childChallengeImpl.setEvaluationDay(null);
		}
		else {
			childChallengeImpl.setEvaluationDay(new Date(evaluationDay));
		}

		if (challengeStartDay == Long.MIN_VALUE) {
			childChallengeImpl.setChallengeStartDay(null);
		}
		else {
			childChallengeImpl.setChallengeStartDay(new Date(challengeStartDay));
		}

		if (challengeEndDay == Long.MIN_VALUE) {
			childChallengeImpl.setChallengeEndDay(null);
		}
		else {
			childChallengeImpl.setChallengeEndDay(new Date(challengeEndDay));
		}

		if (challengeStatus == null) {
			childChallengeImpl.setChallengeStatus(StringPool.BLANK);
		}
		else {
			childChallengeImpl.setChallengeStatus(challengeStatus);
		}

		childChallengeImpl.setChallengeId(challengeId);

		childChallengeImpl.resetOriginalValues();

		return childChallengeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		childChallengeId = objectInput.readLong();
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
		number = objectInput.readInt();
		presentationDay = objectInput.readLong();
		paperStartDay = objectInput.readLong();
		paperEndDay = objectInput.readLong();
		evaluationDay = objectInput.readLong();
		challengeStartDay = objectInput.readLong();
		challengeEndDay = objectInput.readLong();
		challengeStatus = objectInput.readUTF();
		challengeId = objectInput.readLong();
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

		objectOutput.writeLong(childChallengeId);
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
		objectOutput.writeInt(number);
		objectOutput.writeLong(presentationDay);
		objectOutput.writeLong(paperStartDay);
		objectOutput.writeLong(paperEndDay);
		objectOutput.writeLong(evaluationDay);
		objectOutput.writeLong(challengeStartDay);
		objectOutput.writeLong(challengeEndDay);

		if (challengeStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(challengeStatus);
		}

		objectOutput.writeLong(challengeId);
	}

	public String uuid;
	public long childChallengeId;
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
	public int number;
	public long presentationDay;
	public long paperStartDay;
	public long paperEndDay;
	public long evaluationDay;
	public long challengeStartDay;
	public long challengeEndDay;
	public String challengeStatus;
	public long challengeId;
}