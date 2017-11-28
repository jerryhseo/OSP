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
public class UniversityExecutePK implements Comparable<UniversityExecutePK>,
	Serializable {
	public String executeDate;
	public long universityField;

	public UniversityExecutePK() {
	}

	public UniversityExecutePK(String executeDate, long universityField) {
		this.executeDate = executeDate;
		this.universityField = universityField;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public long getUniversityField() {
		return universityField;
	}

	public void setUniversityField(long universityField) {
		this.universityField = universityField;
	}

	@Override
	public int compareTo(UniversityExecutePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = executeDate.compareTo(pk.executeDate);

		if (value != 0) {
			return value;
		}

		if (universityField < pk.universityField) {
			value = -1;
		}
		else if (universityField > pk.universityField) {
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

		if (!(obj instanceof UniversityExecutePK)) {
			return false;
		}

		UniversityExecutePK pk = (UniversityExecutePK)obj;

		if ((executeDate.equals(pk.executeDate)) &&
				(universityField == pk.universityField)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(executeDate) + String.valueOf(universityField)).hashCode();
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
		sb.append("universityField");
		sb.append(StringPool.EQUAL);
		sb.append(universityField);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}