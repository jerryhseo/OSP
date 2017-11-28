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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class ScienceAppExecuteFinderUtil {
	public static java.util.List<java.lang.Object[]> getStatisticsSwExeTableOrganigation(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsSwExeTableOrganigation(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsSwNoneExeTableOrganigation(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsSwNoneExeTableOrganigation(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsSwExeBarChartDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsSwExeBarChartDate(searchParam);
	}

	public static int insertCustomScienceAppExecute(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().insertCustomScienceAppExecute(searchParam);
	}

	public static int deleteCustomScienceAppExecute(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().deleteCustomScienceAppExecute(searchParam);
	}

	public static ScienceAppExecuteFinder getFinder() {
		if (_finder == null) {
			_finder = (ScienceAppExecuteFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					ScienceAppExecuteFinder.class.getName());

			ReferenceRegistry.registerReference(ScienceAppExecuteFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ScienceAppExecuteFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ScienceAppExecuteFinderUtil.class,
			"_finder");
	}

	private static ScienceAppExecuteFinder _finder;
}