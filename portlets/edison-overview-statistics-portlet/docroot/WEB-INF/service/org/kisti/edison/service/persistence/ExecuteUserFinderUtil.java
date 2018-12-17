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
public class ExecuteUserFinderUtil {
	public static int getExecuteUserTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getExecuteUserTotalCount();
	}

	public static java.util.List<java.lang.Object[]> getExecuteUserCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getExecuteUserCountByDate();
	}

	public static int updateExecuteUserStatistics()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().updateExecuteUserStatistics();
	}

	public static ExecuteUserFinder getFinder() {
		if (_finder == null) {
			_finder = (ExecuteUserFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					ExecuteUserFinder.class.getName());

			ReferenceRegistry.registerReference(ExecuteUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ExecuteUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ExecuteUserFinderUtil.class,
			"_finder");
	}

	private static ExecuteUserFinder _finder;
}