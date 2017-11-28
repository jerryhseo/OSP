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
public class UniversityExecuteFinderUtil {
	public static java.util.List<java.lang.Object[]> getStatisticsExecTableOrganigation(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsExecTableOrganigation(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getStatisticsExecBarChartDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getStatisticsExecBarChartDate(searchParam);
	}

	public static int insertCustomUniversityExecute(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().insertCustomUniversityExecute(searchParam);
	}

	public static int deleteCustomUniversityExecute(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().deleteCustomUniversityExecute(searchParam);
	}

	public static UniversityExecuteFinder getFinder() {
		if (_finder == null) {
			_finder = (UniversityExecuteFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					UniversityExecuteFinder.class.getName());

			ReferenceRegistry.registerReference(UniversityExecuteFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(UniversityExecuteFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(UniversityExecuteFinderUtil.class,
			"_finder");
	}

	private static UniversityExecuteFinder _finder;
}