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
public class ContentsFinderUtil {
	public static int getContentsTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getContentsTotalCount();
	}

	public static java.util.List<java.lang.Object[]> getContentsCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getContentsCountByDate();
	}

	public static java.util.List<java.lang.Object[]> getContentsCountBySite()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getContentsCountBySite();
	}

	public static int updateContentsStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateContentsStatistics();
	}

	public static ContentsFinder getFinder() {
		if (_finder == null) {
			_finder = (ContentsFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					ContentsFinder.class.getName());

			ReferenceRegistry.registerReference(ContentsFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ContentsFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ContentsFinderUtil.class, "_finder");
	}

	private static ContentsFinder _finder;
}