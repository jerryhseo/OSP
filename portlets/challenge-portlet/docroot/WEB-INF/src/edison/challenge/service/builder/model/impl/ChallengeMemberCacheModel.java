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

import edison.challenge.service.builder.model.ChallengeMember;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChallengeMember in entity cache.
 *
 * @author kyj
 * @see ChallengeMember
 * @generated
 */
public class ChallengeMemberCacheModel implements CacheModel<ChallengeMember>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{chMemberid=");
		sb.append(chMemberid);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", leader=");
		sb.append(leader);
		sb.append(", falculty=");
		sb.append(falculty);
		sb.append(", major=");
		sb.append(major);
		sb.append(", grade=");
		sb.append(grade);
		sb.append(", chTeamid=");
		sb.append(chTeamid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChallengeMember toEntityModel() {
		ChallengeMemberImpl challengeMemberImpl = new ChallengeMemberImpl();

		challengeMemberImpl.setChMemberid(chMemberid);

		if (fullName == null) {
			challengeMemberImpl.setFullName(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setFullName(fullName);
		}

		if (screenName == null) {
			challengeMemberImpl.setScreenName(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setScreenName(screenName);
		}

		if (email == null) {
			challengeMemberImpl.setEmail(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setEmail(email);
		}

		challengeMemberImpl.setLeader(leader);

		if (falculty == null) {
			challengeMemberImpl.setFalculty(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setFalculty(falculty);
		}

		if (major == null) {
			challengeMemberImpl.setMajor(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setMajor(major);
		}

		if (grade == null) {
			challengeMemberImpl.setGrade(StringPool.BLANK);
		}
		else {
			challengeMemberImpl.setGrade(grade);
		}

		challengeMemberImpl.setChTeamid(chTeamid);

		challengeMemberImpl.resetOriginalValues();

		return challengeMemberImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		chMemberid = objectInput.readLong();
		fullName = objectInput.readUTF();
		screenName = objectInput.readUTF();
		email = objectInput.readUTF();
		leader = objectInput.readBoolean();
		falculty = objectInput.readUTF();
		major = objectInput.readUTF();
		grade = objectInput.readUTF();
		chTeamid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(chMemberid);

		if (fullName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fullName);
		}

		if (screenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(screenName);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeBoolean(leader);

		if (falculty == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(falculty);
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

		objectOutput.writeLong(chTeamid);
	}

	public long chMemberid;
	public String fullName;
	public String screenName;
	public String email;
	public boolean leader;
	public String falculty;
	public String major;
	public String grade;
	public long chTeamid;
}