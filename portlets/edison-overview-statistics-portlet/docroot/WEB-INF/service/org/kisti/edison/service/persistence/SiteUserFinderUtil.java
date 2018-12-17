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
public class SiteUserFinderUtil {
	public static int getCategoryTotalCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCategoryTotalCount(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getCategoryCountByDate(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCategoryCountByDate(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getCategoryCountBySite(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCategoryCountBySite(searchParam);
	}

	public static int updateSiteUserStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateSiteUserStatistics();
	}

	public static SiteUserFinder getFinder() {
		if (_finder == null) {
			_finder = (SiteUserFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					SiteUserFinder.class.getName());

			ReferenceRegistry.registerReference(SiteUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SiteUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SiteUserFinderUtil.class, "_finder");
	}

	private static SiteUserFinder _finder;
}