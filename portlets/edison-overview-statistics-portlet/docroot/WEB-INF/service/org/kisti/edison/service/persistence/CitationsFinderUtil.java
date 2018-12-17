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
public class CitationsFinderUtil {
	public static int getCitationsTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCitationsTotalCount();
	}

	public static java.util.List<java.lang.Object[]> getCitationsCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCitationsCountByDate();
	}

	public static java.util.List<java.lang.Object[]> getCitationsCountBySite()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCitationsCountBySite();
	}

	public static int updateCitationsStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateCitationsStatistics();
	}

	public static CitationsFinder getFinder() {
		if (_finder == null) {
			_finder = (CitationsFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					CitationsFinder.class.getName());

			ReferenceRegistry.registerReference(CitationsFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CitationsFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CitationsFinderUtil.class, "_finder");
	}

	private static CitationsFinder _finder;
}