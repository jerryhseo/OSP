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

package com.kisti.osp.icecap.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Young-K. Suh and Jerry H. Seo
 */
public class DataEntryFinderUtil {
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> retrieveListDataEntry(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().retrieveListDataEntry(searchParam);
	}

	public static int retrieveCountDataEntry(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().retrieveCountDataEntry(searchParam);
	}

	public static DataEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (DataEntryFinder)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataEntryFinder.class.getName());

			ReferenceRegistry.registerReference(DataEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DataEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DataEntryFinderUtil.class, "_finder");
	}

	private static DataEntryFinder _finder;
}