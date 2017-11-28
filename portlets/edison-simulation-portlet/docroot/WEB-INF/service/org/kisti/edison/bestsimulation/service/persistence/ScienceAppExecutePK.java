/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class ScienceAppExecutePK implements Comparable<ScienceAppExecutePK>,
	Serializable {
	public String executeDate;
	public long scienceAppId;

	public ScienceAppExecutePK() {
	}

	public ScienceAppExecutePK(String executeDate, long scienceAppId) {
		this.executeDate = executeDate;
		this.scienceAppId = scienceAppId;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public long getScienceAppId() {
		return scienceAppId;
	}

	public void setScienceAppId(long scienceAppId) {
		this.scienceAppId = scienceAppId;
	}

	@Override
	public int compareTo(ScienceAppExecutePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = executeDate.compareTo(pk.executeDate);

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

		if (!(obj instanceof ScienceAppExecutePK)) {
			return false;
		}

		ScienceAppExecutePK pk = (ScienceAppExecutePK)obj;

		if ((executeDate.equals(pk.executeDate)) &&
				(scienceAppId == pk.scienceAppId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(executeDate) + String.valueOf(scienceAppId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("executeDate");
		sb.append(StringPool.EQUAL);
		sb.append(executeDate);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("scienceAppId");
		sb.append(StringPool.EQUAL);
		sb.append(scienceAppId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}