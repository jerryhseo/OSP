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

package org.kisti.edison.osp.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author edison
 * @generated
 */
public class ExecutePK implements Comparable<ExecutePK>, Serializable {
	public long projectId;
	public String analyzerId;

	public ExecutePK() {
	}

	public ExecutePK(long projectId, String analyzerId) {
		this.projectId = projectId;
		this.analyzerId = analyzerId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	@Override
	public int compareTo(ExecutePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (projectId < pk.projectId) {
			value = -1;
		}
		else if (projectId > pk.projectId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = analyzerId.compareTo(pk.analyzerId);

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

		if (!(obj instanceof ExecutePK)) {
			return false;
		}

		ExecutePK pk = (ExecutePK)obj;

		if ((projectId == pk.projectId) && (analyzerId.equals(pk.analyzerId))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(projectId) + String.valueOf(analyzerId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("projectId");
		sb.append(StringPool.EQUAL);
		sb.append(projectId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("analyzerId");
		sb.append(StringPool.EQUAL);
		sb.append(analyzerId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}