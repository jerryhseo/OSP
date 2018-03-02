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
public class ChallengeEvaluationPK implements Comparable<ChallengeEvaluationPK>,
	Serializable {
	public long challengeEvaluationId;
	public long childChallengeId;

	public ChallengeEvaluationPK() {
	}

	public ChallengeEvaluationPK(long challengeEvaluationId,
		long childChallengeId) {
		this.challengeEvaluationId = challengeEvaluationId;
		this.childChallengeId = childChallengeId;
	}

	public long getChallengeEvaluationId() {
		return challengeEvaluationId;
	}

	public void setChallengeEvaluationId(long challengeEvaluationId) {
		this.challengeEvaluationId = challengeEvaluationId;
	}

	public long getChildChallengeId() {
		return childChallengeId;
	}

	public void setChildChallengeId(long childChallengeId) {
		this.childChallengeId = childChallengeId;
	}

	@Override
	public int compareTo(ChallengeEvaluationPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (challengeEvaluationId < pk.challengeEvaluationId) {
			value = -1;
		}
		else if (challengeEvaluationId > pk.challengeEvaluationId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (childChallengeId < pk.childChallengeId) {
			value = -1;
		}
		else if (childChallengeId > pk.childChallengeId) {
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

		if (!(obj instanceof ChallengeEvaluationPK)) {
			return false;
		}

		ChallengeEvaluationPK pk = (ChallengeEvaluationPK)obj;

		if ((challengeEvaluationId == pk.challengeEvaluationId) &&
				(childChallengeId == pk.childChallengeId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(challengeEvaluationId) +
		String.valueOf(childChallengeId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("challengeEvaluationId");
		sb.append(StringPool.EQUAL);
		sb.append(challengeEvaluationId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("childChallengeId");
		sb.append(StringPool.EQUAL);
		sb.append(childChallengeId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}