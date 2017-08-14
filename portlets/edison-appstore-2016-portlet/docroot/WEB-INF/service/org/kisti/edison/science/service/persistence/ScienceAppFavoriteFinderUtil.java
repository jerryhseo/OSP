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

package org.kisti.edison.science.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class ScienceAppFavoriteFinderUtil {
	public static java.util.List<java.lang.Object[]> getFavoriteAppList(
		long[] categoryIds, long userId, java.util.Locale locale) {
		return getFinder().getFavoriteAppList(categoryIds, userId, locale);
	}

	public static ScienceAppFavoriteFinder getFinder() {
		if (_finder == null) {
			_finder = (ScienceAppFavoriteFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.science.service.ClpSerializer.getServletContextName(),
					ScienceAppFavoriteFinder.class.getName());

			ReferenceRegistry.registerReference(ScienceAppFavoriteFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ScienceAppFavoriteFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ScienceAppFavoriteFinderUtil.class,
			"_finder");
	}

	private static ScienceAppFavoriteFinder _finder;
}