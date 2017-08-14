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

package org.kisti.edison.virtuallaboratory.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class VirtualLabClassScienceAppFinderUtil {
	public static java.util.List<java.lang.Object[]> getVirtualLabScienceAppList(
		long columnId, long virtualLabId, long classId, long scienceAppId,
		java.util.Locale locale) {
		return getFinder()
				   .getVirtualLabScienceAppList(columnId, virtualLabId,
			classId, scienceAppId, locale);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabClassScienceAppList(
		long columnId, long virtualLabId, long classId, java.util.Locale locale) {
		return getFinder()
				   .getVirtualLabClassScienceAppList(columnId, virtualLabId,
			classId, locale);
	}

	public static java.util.List<java.lang.Object[]> getScienceAppList(
		long virtualLabId, java.lang.String searchField) {
		return getFinder().getScienceAppList(virtualLabId, searchField);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabClassesInfo(
		long virtualLabId, long scienceAppId) {
		return getFinder().getVirtualLabClassesInfo(virtualLabId, scienceAppId);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabInfo(
		long virtualLabId, long scienceAppId) {
		return getFinder().getVirtualLabInfo(virtualLabId, scienceAppId);
	}

	public static VirtualLabClassScienceAppFinder getFinder() {
		if (_finder == null) {
			_finder = (VirtualLabClassScienceAppFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					VirtualLabClassScienceAppFinder.class.getName());

			ReferenceRegistry.registerReference(VirtualLabClassScienceAppFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(VirtualLabClassScienceAppFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(VirtualLabClassScienceAppFinderUtil.class,
			"_finder");
	}

	private static VirtualLabClassScienceAppFinder _finder;
}