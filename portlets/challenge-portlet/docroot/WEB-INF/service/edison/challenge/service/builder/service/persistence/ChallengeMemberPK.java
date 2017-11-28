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

package edison.challenge.service.builder.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author kyj
 * @generated
 */
public class ChallengeMemberPK implements Comparable<ChallengeMemberPK>,
	Serializable {
	public long memberid;
	public long teamid;

	public ChallengeMemberPK() {
	}

	public ChallengeMemberPK(long memberid, long teamid) {
		this.memberid = memberid;
		this.teamid = teamid;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public long getTeamid() {
		return teamid;
	}

	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}

	@Override
	public int compareTo(ChallengeMemberPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (memberid < pk.memberid) {
			value = -1;
		}
		else if (memberid > pk.memberid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (teamid < pk.teamid) {
			value = -1;
		}
		else if (teamid > pk.teamid) {
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

		if (!(obj instanceof ChallengeMemberPK)) {
			return false;
		}

		ChallengeMemberPK pk = (ChallengeMemberPK)obj;

		if ((memberid == pk.memberid) && (teamid == pk.teamid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(memberid) + String.valueOf(teamid)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("memberid");
		sb.append(StringPool.EQUAL);
		sb.append(memberid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("teamid");
		sb.append(StringPool.EQUAL);
		sb.append(teamid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}