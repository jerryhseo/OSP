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

package org.kisti.edison.virtuallaboratory.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class VirtualLabClassStsMigrationPK implements Comparable<VirtualLabClassStsMigrationPK>,
	Serializable {
	public long groupId;
	public long virtualLabId;
	public long classId;

	public VirtualLabClassStsMigrationPK() {
	}

	public VirtualLabClassStsMigrationPK(long groupId, long virtualLabId,
		long classId) {
		this.groupId = groupId;
		this.virtualLabId = virtualLabId;
		this.classId = classId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getVirtualLabId() {
		return virtualLabId;
	}

	public void setVirtualLabId(long virtualLabId) {
		this.virtualLabId = virtualLabId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	@Override
	public int compareTo(VirtualLabClassStsMigrationPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (groupId < pk.groupId) {
			value = -1;
		}
		else if (groupId > pk.groupId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (virtualLabId < pk.virtualLabId) {
			value = -1;
		}
		else if (virtualLabId > pk.virtualLabId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classId < pk.classId) {
			value = -1;
		}
		else if (classId > pk.classId) {
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

		if (!(obj instanceof VirtualLabClassStsMigrationPK)) {
			return false;
		}

		VirtualLabClassStsMigrationPK pk = (VirtualLabClassStsMigrationPK)obj;

		if ((groupId == pk.groupId) && (virtualLabId == pk.virtualLabId) &&
				(classId == pk.classId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(groupId) + String.valueOf(virtualLabId) +
		String.valueOf(classId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("groupId");
		sb.append(StringPool.EQUAL);
		sb.append(groupId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("virtualLabId");
		sb.append(StringPool.EQUAL);
		sb.append(virtualLabId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("classId");
		sb.append(StringPool.EQUAL);
		sb.append(classId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}