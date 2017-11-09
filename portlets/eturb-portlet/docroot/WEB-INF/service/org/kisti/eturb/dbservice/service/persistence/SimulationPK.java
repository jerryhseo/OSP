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

package org.kisti.eturb.dbservice.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class SimulationPK implements Comparable<SimulationPK>, Serializable {
	public long projectId;
	public String executeId;

	public SimulationPK() {
	}

	public SimulationPK(long projectId, String executeId) {
		this.projectId = projectId;
		this.executeId = executeId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}

	@Override
	public int compareTo(SimulationPK pk) {
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

		value = executeId.compareTo(pk.executeId);

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

		if (!(obj instanceof SimulationPK)) {
			return false;
		}

		SimulationPK pk = (SimulationPK)obj;

		if ((projectId == pk.projectId) && (executeId.equals(pk.executeId))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(projectId) + String.valueOf(executeId)).hashCode();
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
		sb.append("executeId");
		sb.append(StringPool.EQUAL);
		sb.append(executeId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}