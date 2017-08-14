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
public class VirtualLabFinderUtil {
	public static java.util.List<java.lang.Object[]> getVirtualLabList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getVirtualLabList(params, locale);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabTabList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getVirtualLabTabList(params, locale);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabAuthList(
		long groupId, long userId) {
		return getFinder().getVirtualLabAuthList(groupId, userId);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabClassRegisterList(
		long groupId, long userId, long begin, long end) {
		return getFinder()
				   .getVirtualLabClassRegisterList(groupId, userId, begin, end);
	}

	public static java.lang.Object[] getVirtualLabClassRegisterInfo(
		long classId, long userId, long groupId) {
		return getFinder()
				   .getVirtualLabClassRegisterInfo(classId, userId, groupId);
	}

	public static java.util.List<java.lang.Object[]> getListVirtualLab(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getListVirtualLab(params, locale);
	}

	public static java.util.List<java.lang.Object[]> getListVirtualLabManagement(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getListVirtualLabManagement(params, locale);
	}

	public static java.util.List<java.lang.Object[]> getVirtualLabGroup() {
		return getFinder().getVirtualLabGroup();
	}

	public static int getCountVirtualLab(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getCountVirtualLab(params, locale);
	}

	public static int getCountVirtualLabClassRegisterList(long groupId,
		long userId, java.util.Locale locale) {
		return getFinder()
				   .getCountVirtualLabClassRegisterList(groupId, userId, locale);
	}

	public static int getCountVirtualLabList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getCountVirtualLabList(params, locale);
	}

	public static VirtualLabFinder getFinder() {
		if (_finder == null) {
			_finder = (VirtualLabFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					VirtualLabFinder.class.getName());

			ReferenceRegistry.registerReference(VirtualLabFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(VirtualLabFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(VirtualLabFinderUtil.class,
			"_finder");
	}

	private static VirtualLabFinder _finder;
}