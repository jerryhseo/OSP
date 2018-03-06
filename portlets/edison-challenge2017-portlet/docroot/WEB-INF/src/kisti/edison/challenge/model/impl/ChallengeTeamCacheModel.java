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

import kisti.edison.challenge.model.ChallengeTeam;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeTeam in entity cache.
 *
 * @author KYJ
 * @see ChallengeTeam
 * @generated
 */
public class ChallengeTeamCacheModel implements CacheModel<ChallengeTeam>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(65);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", challengeTeamId=");
		sb.append(challengeTeamId);
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
		sb.append(", teamName=");
		sb.append(teamName);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", paperName=");
		sb.append(paperName);
		sb.append(", paperAbstract=");
		sb.append(paperAbstract);
		sb.append(", paperFileName=");
		sb.append(paperFileName);
		sb.append(", paperSubmissionDay=");
		sb.append(paperSubmissionDay);
		sb.append(", paperModifyDay=");
		sb.append(paperModifyDay);
		sb.append(", paperStatusDOC=");
		sb.append(paperStatusDOC);
		sb.append(", paperPDFFileName=");
		sb.append(paperPDFFileName);
		sb.append(", paperPDFSubmissionDay=");
		sb.append(paperPDFSubmissionDay);
		sb.append(", paperPDFModifyDay=");
		sb.append(paperPDFModifyDay);
		sb.append(", paperStatusPDF=");
		sb.append(paperStatusPDF);
		sb.append(", presentationName=");
		sb.append(presentationName);
		sb.append(", presentationFileName=");
		sb.append(presentationFileName);
		sb.append(", presentationSubmissionDay=");
		sb.append(presentationSubmissionDay);
		sb.append(", presentationModifyDay=");
		sb.append(presentationModifyDay);
		sb.append(", presentationStatus=");
		sb.append(presentationStatus);
		sb.append(", filepath=");
		sb.append(filepath);
		sb.append(", aggrement=");
		sb.append(aggrement);
		sb.append(", childChallengeId=");
		sb.append(childChallengeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeTeam toEntityModel() {
		ChallengeTeamImpl challengeTeamImpl = new ChallengeTeamImpl();

		if (uuid == null) {
			challengeTeamImpl.setUuid(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setUuid(uuid);
		}

		challengeTeamImpl.setChallengeTeamId(challengeTeamId);
		challengeTeamImpl.setGroupId(groupId);
		challengeTeamImpl.setCompanyId(companyId);
		challengeTeamImpl.setUserId(userId);

		if (userName == null) {
			challengeTeamImpl.setUserName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			challengeTeamImpl.setCreateDate(null);
		}
		else {
			challengeTeamImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			challengeTeamImpl.setModifiedDate(null);
		}
		else {
			challengeTeamImpl.setModifiedDate(new Date(modifiedDate));
		}

		challengeTeamImpl.setStatus(status);
		challengeTeamImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			challengeTeamImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			challengeTeamImpl.setStatusDate(null);
		}
		else {
			challengeTeamImpl.setStatusDate(new Date(statusDate));
		}

		if (teamName == null) {
			challengeTeamImpl.setTeamName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setTeamName(teamName);
		}

		if (subject == null) {
			challengeTeamImpl.setSubject(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setSubject(subject);
		}

		if (paperName == null) {
			challengeTeamImpl.setPaperName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPaperName(paperName);
		}

		if (paperAbstract == null) {
			challengeTeamImpl.setPaperAbstract(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPaperAbstract(paperAbstract);
		}

		if (paperFileName == null) {
			challengeTeamImpl.setPaperFileName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPaperFileName(paperFileName);
		}

		if (paperSubmissionDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPaperSubmissionDay(null);
		}
		else {
			challengeTeamImpl.setPaperSubmissionDay(new Date(paperSubmissionDay));
		}

		if (paperModifyDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPaperModifyDay(null);
		}
		else {
			challengeTeamImpl.setPaperModifyDay(new Date(paperModifyDay));
		}

		challengeTeamImpl.setPaperStatusDOC(paperStatusDOC);

		if (paperPDFFileName == null) {
			challengeTeamImpl.setPaperPDFFileName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPaperPDFFileName(paperPDFFileName);
		}

		if (paperPDFSubmissionDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPaperPDFSubmissionDay(null);
		}
		else {
			challengeTeamImpl.setPaperPDFSubmissionDay(new Date(
					paperPDFSubmissionDay));
		}

		if (paperPDFModifyDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPaperPDFModifyDay(null);
		}
		else {
			challengeTeamImpl.setPaperPDFModifyDay(new Date(paperPDFModifyDay));
		}

		challengeTeamImpl.setPaperStatusPDF(paperStatusPDF);

		if (presentationName == null) {
			challengeTeamImpl.setPresentationName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPresentationName(presentationName);
		}

		if (presentationFileName == null) {
			challengeTeamImpl.setPresentationFileName(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setPresentationFileName(presentationFileName);
		}

		if (presentationSubmissionDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPresentationSubmissionDay(null);
		}
		else {
			challengeTeamImpl.setPresentationSubmissionDay(new Date(
					presentationSubmissionDay));
		}

		if (presentationModifyDay == Long.MIN_VALUE) {
			challengeTeamImpl.setPresentationModifyDay(null);
		}
		else {
			challengeTeamImpl.setPresentationModifyDay(new Date(
					presentationModifyDay));
		}

		challengeTeamImpl.setPresentationStatus(presentationStatus);

		if (filepath == null) {
			challengeTeamImpl.setFilepath(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setFilepath(filepath);
		}

		challengeTeamImpl.setAggrement(aggrement);
		challengeTeamImpl.setChildChallengeId(childChallengeId);

		challengeTeamImpl.resetOriginalValues();

		return challengeTeamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		challengeTeamId = objectInput.readLong();
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
		teamName = objectInput.readUTF();
		subject = objectInput.readUTF();
		paperName = objectInput.readUTF();
		paperAbstract = objectInput.readUTF();
		paperFileName = objectInput.readUTF();
		paperSubmissionDay = objectInput.readLong();
		paperModifyDay = objectInput.readLong();
		paperStatusDOC = objectInput.readBoolean();
		paperPDFFileName = objectInput.readUTF();
		paperPDFSubmissionDay = objectInput.readLong();
		paperPDFModifyDay = objectInput.readLong();
		paperStatusPDF = objectInput.readBoolean();
		presentationName = objectInput.readUTF();
		presentationFileName = objectInput.readUTF();
		presentationSubmissionDay = objectInput.readLong();
		presentationModifyDay = objectInput.readLong();
		presentationStatus = objectInput.readBoolean();
		filepath = objectInput.readUTF();
		aggrement = objectInput.readBoolean();
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

		objectOutput.writeLong(challengeTeamId);
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

		if (teamName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(teamName);
		}

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (paperName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperName);
		}

		if (paperAbstract == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperAbstract);
		}

		if (paperFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperFileName);
		}

		objectOutput.writeLong(paperSubmissionDay);
		objectOutput.writeLong(paperModifyDay);
		objectOutput.writeBoolean(paperStatusDOC);

		if (paperPDFFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperPDFFileName);
		}

		objectOutput.writeLong(paperPDFSubmissionDay);
		objectOutput.writeLong(paperPDFModifyDay);
		objectOutput.writeBoolean(paperStatusPDF);

		if (presentationName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(presentationName);
		}

		if (presentationFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(presentationFileName);
		}

		objectOutput.writeLong(presentationSubmissionDay);
		objectOutput.writeLong(presentationModifyDay);
		objectOutput.writeBoolean(presentationStatus);

		if (filepath == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filepath);
		}

		objectOutput.writeBoolean(aggrement);
		objectOutput.writeLong(childChallengeId);
	}

	public String uuid;
	public long challengeTeamId;
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
	public String teamName;
	public String subject;
	public String paperName;
	public String paperAbstract;
	public String paperFileName;
	public long paperSubmissionDay;
	public long paperModifyDay;
	public boolean paperStatusDOC;
	public String paperPDFFileName;
	public long paperPDFSubmissionDay;
	public long paperPDFModifyDay;
	public boolean paperStatusPDF;
	public String presentationName;
	public String presentationFileName;
	public long presentationSubmissionDay;
	public long presentationModifyDay;
	public boolean presentationStatus;
	public String filepath;
	public boolean aggrement;
	public long childChallengeId;
}