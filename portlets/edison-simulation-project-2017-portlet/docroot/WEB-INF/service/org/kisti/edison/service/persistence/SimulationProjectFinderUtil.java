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
public class SimulationProjectFinderUtil {
	public static java.util.List<java.lang.Object[]> getCustomMySimulationProjectList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomMySimulationProjectList(searchParam);
	}

	public static int getCustomMySimulationProjectCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomMySimulationProjectCount(searchParam);
	}

	public static java.util.List<org.kisti.edison.model.SimulationProject> getCustomSimulationProjectList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomSimulationProjectList(searchParam);
	}

	public static int getCustomSimulationProjectCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomSimulationProjectCount(searchParam);
	}

	public static SimulationProjectFinder getFinder() {
		if (_finder == null) {
			_finder = (SimulationProjectFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					SimulationProjectFinder.class.getName());

			ReferenceRegistry.registerReference(SimulationProjectFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SimulationProjectFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SimulationProjectFinderUtil.class,
			"_finder");
	}

	private static SimulationProjectFinder _finder;
}