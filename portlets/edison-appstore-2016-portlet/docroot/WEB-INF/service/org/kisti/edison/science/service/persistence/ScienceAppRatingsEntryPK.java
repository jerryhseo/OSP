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

package org.kisti.edison.science.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class ScienceAppRatingsEntryPK implements Comparable<ScienceAppRatingsEntryPK>,
	Serializable {
	public long userId;
	public long scienceAppId;

	public ScienceAppRatingsEntryPK() {
	}

	public ScienceAppRatingsEntryPK(long userId, long scienceAppId) {
		this.userId = userId;
		this.scienceAppId = scienceAppId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getScienceAppId() {
		return scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		this.scienceAppId = scienceAppId;
	}

	@Override
	public int compareTo(ScienceAppRatingsEntryPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (userId < pk.userId) {
			value = -1;
		}
		else if (userId > pk.userId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (scienceAppId < pk.scienceAppId) {
			value = -1;
		}
		else if (scienceAppId > pk.scienceAppId) {
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

		if (!(obj instanceof ScienceAppRatingsEntryPK)) {
			return false;
		}

		ScienceAppRatingsEntryPK pk = (ScienceAppRatingsEntryPK)obj;

		if ((userId == pk.userId) && (scienceAppId == pk.scienceAppId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(userId) + String.valueOf(scienceAppId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("scienceAppId");
		sb.append(StringPool.EQUAL);
		sb.append(scienceAppId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}