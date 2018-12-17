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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author edison
 */
public class SimulationJobFinderUtil {
	public static int getSimulationJobTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getSimulationJobTotalCount();
	}

	public static java.util.List<java.lang.Object[]> getSimulationJobCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getSimulationJobCountByDate();
	}

	public static java.util.List<java.lang.Object[]> getSimulationJobCountBySite()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getSimulationJobCountBySite();
	}

	public static int updateSimulationJobStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateSimulationJobStatistics();
	}

	public static SimulationJobFinder getFinder() {
		if (_finder == null) {
			_finder = (SimulationJobFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					SimulationJobFinder.class.getName());

			ReferenceRegistry.registerReference(SimulationJobFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SimulationJobFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SimulationJobFinderUtil.class,
			"_finder");
	}

	private static SimulationJobFinder _finder;
}