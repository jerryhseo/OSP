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
public class RequestMemberFinderUtil {
	public static java.util.List<java.lang.Object[]> getCustomRequestMembertList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomRequestMembertList(searchParam);
	}

	public static int getCustomRequestMemberCount(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getCustomRequestMemberCount(searchParam);
	}

	public static RequestMemberFinder getFinder() {
		if (_finder == null) {
			_finder = (RequestMemberFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					RequestMemberFinder.class.getName());

			ReferenceRegistry.registerReference(RequestMemberFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(RequestMemberFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(RequestMemberFinderUtil.class,
			"_finder");
	}

	private static RequestMemberFinder _finder;
}