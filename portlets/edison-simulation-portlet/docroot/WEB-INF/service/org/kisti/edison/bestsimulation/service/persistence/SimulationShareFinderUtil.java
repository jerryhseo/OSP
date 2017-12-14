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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class SimulationShareFinderUtil {
	public static long getMaxShareSeqNoSimulationShare(int jobSeqNo,
		java.lang.String jobUuid, java.lang.String simulationUuid) {
		return getFinder()
				   .getMaxShareSeqNoSimulationShare(jobSeqNo, jobUuid,
			simulationUuid);
	}

	public static java.util.List<java.lang.Object[]> getSharedSimulationJobCustomId(
		int jobSeqNo, java.lang.String jobUuid, java.lang.String simulationUuid) {
		return getFinder()
				   .getSharedSimulationJobCustomId(jobSeqNo, jobUuid,
			simulationUuid);
	}

	public static int getSimulationShareCount(int jobSeqNo,
		java.lang.String jobUuid, java.lang.String simulationUuid) {
		return getFinder()
				   .getSimulationShareCount(jobSeqNo, jobUuid, simulationUuid);
	}

	public static SimulationShareFinder getFinder() {
		if (_finder == null) {
			_finder = (SimulationShareFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					SimulationShareFinder.class.getName());

			ReferenceRegistry.registerReference(SimulationShareFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SimulationShareFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SimulationShareFinderUtil.class,
			"_finder");
	}

	private static SimulationShareFinder _finder;
}