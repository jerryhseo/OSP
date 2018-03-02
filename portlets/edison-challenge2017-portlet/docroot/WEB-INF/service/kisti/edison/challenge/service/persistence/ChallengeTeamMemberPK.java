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

package kisti.edison.challenge.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeTeamMemberPK implements Comparable<ChallengeTeamMemberPK>,
	Serializable {
	public long challengeTeamMemberId;
	public long challengeTeamId;

	public ChallengeTeamMemberPK() {
	}

	public ChallengeTeamMemberPK(long challengeTeamMemberId,
		long challengeTeamId) {
		this.challengeTeamMemberId = challengeTeamMemberId;
		this.challengeTeamId = challengeTeamId;
	}

	public long getChallengeTeamMemberId() {
		return challengeTeamMemberId;
	}

	public void setChallengeTeamMemberId(long challengeTeamMemberId) {
		this.challengeTeamMemberId = challengeTeamMemberId;
	}

	public long getChallengeTeamId() {
		return challengeTeamId;
	}

	public void setChallengeTeamId(long challengeTeamId) {
		this.challengeTeamId = challengeTeamId;
	}

	@Override
	public int compareTo(ChallengeTeamMemberPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (challengeTeamMemberId < pk.challengeTeamMemberId) {
			value = -1;
		}
		else if (challengeTeamMemberId > pk.challengeTeamMemberId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (challengeTeamId < pk.challengeTeamId) {
			value = -1;
		}
		else if (challengeTeamId > pk.challengeTeamId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeTeamMemberPK)) {
			return false;
		}

		ChallengeTeamMemberPK pk = (ChallengeTeamMemberPK)obj;

		if ((challengeTeamMemberId == pk.challengeTeamMemberId) &&
				(challengeTeamId == pk.challengeTeamId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(challengeTeamMemberId) +
		String.valueOf(challengeTeamId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("challengeTeamMemberId");
		sb.append(StringPool.EQUAL);
		sb.append(challengeTeamMemberId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("challengeTeamId");
		sb.append(StringPool.EQUAL);
		sb.append(challengeTeamId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}