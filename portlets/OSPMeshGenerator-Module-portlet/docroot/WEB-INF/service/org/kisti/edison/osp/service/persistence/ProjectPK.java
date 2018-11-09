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
public class ProjectPK implements Comparable<ProjectPK>, Serializable {
	public String simulationUuid;
	public String portletNamespace;
	public long jobSeqNo;

	public ProjectPK() {
	}

	public ProjectPK(String simulationUuid, String portletNamespace,
		long jobSeqNo) {
		this.simulationUuid = simulationUuid;
		this.portletNamespace = portletNamespace;
		this.jobSeqNo = jobSeqNo;
	}

	public String getSimulationUuid() {
		return simulationUuid;
	}

	public void setSimulationUuid(String simulationUuid) {
		this.simulationUuid = simulationUuid;
	}

	public String getPortletNamespace() {
		return portletNamespace;
	}

	public void setPortletNamespace(String portletNamespace) {
		this.portletNamespace = portletNamespace;
	}

	public long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	@Override
	public int compareTo(ProjectPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = simulationUuid.compareTo(pk.simulationUuid);

		if (value != 0) {
			return value;
		}

		value = portletNamespace.compareTo(pk.portletNamespace);

		if (value != 0) {
			return value;
		}

		if (jobSeqNo < pk.jobSeqNo) {
			value = -1;
		}
		else if (jobSeqNo > pk.jobSeqNo) {
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

		if (!(obj instanceof ProjectPK)) {
			return false;
		}

		ProjectPK pk = (ProjectPK)obj;

		if ((simulationUuid.equals(pk.simulationUuid)) &&
				(portletNamespace.equals(pk.portletNamespace)) &&
				(jobSeqNo == pk.jobSeqNo)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(simulationUuid) +
		String.valueOf(portletNamespace) + String.valueOf(jobSeqNo)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("simulationUuid");
		sb.append(StringPool.EQUAL);
		sb.append(simulationUuid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("portletNamespace");
		sb.append(StringPool.EQUAL);
		sb.append(portletNamespace);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("jobSeqNo");
		sb.append(StringPool.EQUAL);
		sb.append(jobSeqNo);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}