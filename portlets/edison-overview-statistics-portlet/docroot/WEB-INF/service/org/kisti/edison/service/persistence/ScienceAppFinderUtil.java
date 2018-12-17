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
public class ScienceAppFinderUtil {
	public static int getScienceAppTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppTotalCount();
	}

	public static java.util.List<java.lang.Object[]> getScienceAppCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppCountByDate();
	}

	public static java.util.List<java.lang.Object[]> getScienceAppCountBySite()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppCountBySite();
	}

	public static int updateScienceAppStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateScienceAppStatistics();
	}

	public static ScienceAppFinder getFinder() {
		if (_finder == null) {
			_finder = (ScienceAppFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					ScienceAppFinder.class.getName());

			ReferenceRegistry.registerReference(ScienceAppFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ScienceAppFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ScienceAppFinderUtil.class,
			"_finder");
	}

	private static ScienceAppFinder _finder;
}