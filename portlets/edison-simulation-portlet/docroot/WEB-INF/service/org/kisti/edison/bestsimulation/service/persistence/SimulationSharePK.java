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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author EDISON
 * @generated
 */
public class SimulationSharePK implements Comparable<SimulationSharePK>,
	Serializable {
	public long shareSeqno;
	public long jobSeqNo;
	public String jobUuid;
	public String simulationUuid;

	public SimulationSharePK() {
	}

	public SimulationSharePK(long shareSeqno, long jobSeqNo, String jobUuid,
		String simulationUuid) {
		this.shareSeqno = shareSeqno;
		this.jobSeqNo = jobSeqNo;
		this.jobUuid = jobUuid;
		this.simulationUuid = simulationUuid;
	}

	public long getShareSeqno() {
		return shareSeqno;
	}

	public void setShareSeqno(long shareSeqno) {
		this.shareSeqno = shareSeqno;
	}

	public long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public String getJobUuid() {
		return jobUuid;
	}

	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}

	public String getSimulationUuid() {
		return simulationUuid;
	}

	public void setSimulationUuid(String simulationUuid) {
		this.simulationUuid = simulationUuid;
	}

	@Override
	public int compareTo(SimulationSharePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (shareSeqno < pk.shareSeqno) {
			value = -1;
		}
		else if (shareSeqno > pk.shareSeqno) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		value = jobUuid.compareTo(pk.jobUuid);

		if (value != 0) {
			return value;
		}

		value = simulationUuid.compareTo(pk.simulationUuid);

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

		if (!(obj instanceof SimulationSharePK)) {
			return false;
		}

		SimulationSharePK pk = (SimulationSharePK)obj;

		if ((shareSeqno == pk.shareSeqno) && (jobSeqNo == pk.jobSeqNo) &&
				(jobUuid.equals(pk.jobUuid)) &&
				(simulationUuid.equals(pk.simulationUuid))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(shareSeqno) + String.valueOf(jobSeqNo) +
		String.valueOf(jobUuid) + String.valueOf(simulationUuid)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("shareSeqno");
		sb.append(StringPool.EQUAL);
		sb.append(shareSeqno);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("jobSeqNo");
		sb.append(StringPool.EQUAL);
		sb.append(jobSeqNo);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("jobUuid");
		sb.append(StringPool.EQUAL);
		sb.append(jobUuid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("simulationUuid");
		sb.append(StringPool.EQUAL);
		sb.append(simulationUuid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}