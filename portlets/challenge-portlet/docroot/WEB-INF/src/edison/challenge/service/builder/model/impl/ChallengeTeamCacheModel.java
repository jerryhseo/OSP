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

package edison.challenge.service.builder.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import edison.challenge.service.builder.model.ChallengeTeam;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChallengeTeam in entity cache.
 *
 * @author kyj
 * @see ChallengeTeam
 * @generated
 */
public class ChallengeTeamCacheModel implements CacheModel<ChallengeTeam>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{chTeamid=");
		sb.append(chTeamid);
		sb.append(", teamName=");
		sb.append(teamName);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", paperPDFstatus=");
		sb.append(paperPDFstatus);
		sb.append(", paperstatus=");
		sb.append(paperstatus);
		sb.append(", presentationstatus=");
		sb.append(presentationstatus);
		sb.append(", registerDay=");
		sb.append(registerDay);
		sb.append(", registerid=");
		sb.append(registerid);
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
		sb.append(", paperPDFFileName=");
		sb.append(paperPDFFileName);
		sb.append(", paperPDFSubmissionDay=");
		sb.append(paperPDFSubmissionDay);
		sb.append(", paperPDFModifyDay=");
		sb.append(paperPDFModifyDay);
		sb.append(", presentationName=");
		sb.append(presentationName);
		sb.append(", presentationFileName=");
		sb.append(presentationFileName);
		sb.append(", presentationSubmissionDay=");
		sb.append(presentationSubmissionDay);
		sb.append(", presentationModifyDay=");
		sb.append(presentationModifyDay);
		sb.append(", filepath=");
		sb.append(filepath);
		sb.append(", childid=");
		sb.append(childid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeTeam toEntityModel() {
		ChallengeTeamImpl challengeTeamImpl = new ChallengeTeamImpl();

		challengeTeamImpl.setChTeamid(chTeamid);

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

		challengeTeamImpl.setPaperPDFstatus(paperPDFstatus);
		challengeTeamImpl.setPaperstatus(paperstatus);
		challengeTeamImpl.setPresentationstatus(presentationstatus);

		if (registerDay == Long.MIN_VALUE) {
			challengeTeamImpl.setRegisterDay(null);
		}
		else {
			challengeTeamImpl.setRegisterDay(new Date(registerDay));
		}

		if (registerid == null) {
			challengeTeamImpl.setRegisterid(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setRegisterid(registerid);
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

		if (filepath == null) {
			challengeTeamImpl.setFilepath(StringPool.BLANK);
		}
		else {
			challengeTeamImpl.setFilepath(filepath);
		}

		challengeTeamImpl.setChildid(childid);

		challengeTeamImpl.resetOriginalValues();

		return challengeTeamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		chTeamid = objectInput.readLong();
		teamName = objectInput.readUTF();
		subject = objectInput.readUTF();
		paperPDFstatus = objectInput.readBoolean();
		paperstatus = objectInput.readBoolean();
		presentationstatus = objectInput.readBoolean();
		registerDay = objectInput.readLong();
		registerid = objectInput.readUTF();
		paperName = objectInput.readUTF();
		paperAbstract = objectInput.readUTF();
		paperFileName = objectInput.readUTF();
		paperSubmissionDay = objectInput.readLong();
		paperModifyDay = objectInput.readLong();
		paperPDFFileName = objectInput.readUTF();
		paperPDFSubmissionDay = objectInput.readLong();
		paperPDFModifyDay = objectInput.readLong();
		presentationName = objectInput.readUTF();
		presentationFileName = objectInput.readUTF();
		presentationSubmissionDay = objectInput.readLong();
		presentationModifyDay = objectInput.readLong();
		filepath = objectInput.readUTF();
		childid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(chTeamid);

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

		objectOutput.writeBoolean(paperPDFstatus);
		objectOutput.writeBoolean(paperstatus);
		objectOutput.writeBoolean(presentationstatus);
		objectOutput.writeLong(registerDay);

		if (registerid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(registerid);
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

		if (paperPDFFileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paperPDFFileName);
		}

		objectOutput.writeLong(paperPDFSubmissionDay);
		objectOutput.writeLong(paperPDFModifyDay);

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

		if (filepath == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filepath);
		}

		objectOutput.writeLong(childid);
	}

	public long chTeamid;
	public String teamName;
	public String subject;
	public boolean paperPDFstatus;
	public boolean paperstatus;
	public boolean presentationstatus;
	public long registerDay;
	public String registerid;
	public String paperName;
	public String paperAbstract;
	public String paperFileName;
	public long paperSubmissionDay;
	public long paperModifyDay;
	public String paperPDFFileName;
	public long paperPDFSubmissionDay;
	public long paperPDFModifyDay;
	public String presentationName;
	public String presentationFileName;
	public long presentationSubmissionDay;
	public long presentationModifyDay;
	public String filepath;
	public long childid;
}