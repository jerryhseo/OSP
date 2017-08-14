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

package org.kisti.edison.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author edison
 * @generated
 */
public class SimProScienceAppLinkPK implements Comparable<SimProScienceAppLinkPK>,
	Serializable {
	public long simProScienceAppLinkId;
	public long simulationProjectId;

	public SimProScienceAppLinkPK() {
	}

	public SimProScienceAppLinkPK(long simProScienceAppLinkId,
		long simulationProjectId) {
		this.simProScienceAppLinkId = simProScienceAppLinkId;
		this.simulationProjectId = simulationProjectId;
	}

	public long getSimProScienceAppLinkId() {
		return simProScienceAppLinkId;
	}

	public void setSimProScienceAppLinkId(long simProScienceAppLinkId) {
		this.simProScienceAppLinkId = simProScienceAppLinkId;
	}

	public long getSimulationProjectId() {
		return simulationProjectId;
	}

	public void setSimulationProjectId(long simulationProjectId) {
		this.simulationProjectId = simulationProjectId;
	}

	@Override
	public int compareTo(SimProScienceAppLinkPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (simProScienceAppLinkId < pk.simProScienceAppLinkId) {
			value = -1;
		}
		else if (simProScienceAppLinkId > pk.simProScienceAppLinkId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (simulationProjectId < pk.simulationProjectId) {
			value = -1;
		}
		else if (simulationProjectId > pk.simulationProjectId) {
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

		if (!(obj instanceof SimProScienceAppLinkPK)) {
			return false;
		}

		SimProScienceAppLinkPK pk = (SimProScienceAppLinkPK)obj;

		if ((simProScienceAppLinkId == pk.simProScienceAppLinkId) &&
				(simulationProjectId == pk.simulationProjectId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(simProScienceAppLinkId) +
		String.valueOf(simulationProjectId)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("simProScienceAppLinkId");
		sb.append(StringPool.EQUAL);
		sb.append(simProScienceAppLinkId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("simulationProjectId");
		sb.append(StringPool.EQUAL);
		sb.append(simulationProjectId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}