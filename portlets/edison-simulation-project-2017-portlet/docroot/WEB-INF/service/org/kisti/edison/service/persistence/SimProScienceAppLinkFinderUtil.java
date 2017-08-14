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
public class SimProScienceAppLinkFinderUtil {
	public static java.util.List<java.lang.Object[]> getScienceAppList(
		java.lang.String searchField, java.lang.String languageId, int begin,
		int end) {
		return getFinder().getScienceAppList(searchField, languageId, begin, end);
	}

	public static int getScienceAppListCount(java.lang.String searchField,
		java.lang.String languageId) {
		return getFinder().getScienceAppListCount(searchField, languageId);
	}

	public static java.util.List<java.lang.Object[]> getSelectScienceAppList(
		java.lang.String scienceAppIds, java.lang.String languageId) {
		return getFinder().getSelectScienceAppList(scienceAppIds, languageId);
	}

	public static int searchTextEntryScienceAppCount(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().searchTextEntryScienceAppCount(params);
	}

	public static SimProScienceAppLinkFinder getFinder() {
		if (_finder == null) {
			_finder = (SimProScienceAppLinkFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					SimProScienceAppLinkFinder.class.getName());

			ReferenceRegistry.registerReference(SimProScienceAppLinkFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SimProScienceAppLinkFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SimProScienceAppLinkFinderUtil.class,
			"_finder");
	}

	private static SimProScienceAppLinkFinder _finder;
}