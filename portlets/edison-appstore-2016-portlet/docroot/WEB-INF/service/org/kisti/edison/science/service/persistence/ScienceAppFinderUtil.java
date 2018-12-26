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
public class ScienceAppFinderUtil {
	public static int countScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		boolean categorySearch, boolean managerSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countScienceApp(searchParam, categorySearch, managerSearch);
	}

	public static java.util.List<java.lang.Object[]> retrieveListScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		boolean categorySearch, boolean managerSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .retrieveListScienceApp(searchParam, categorySearch,
			managerSearch);
	}

	public static long[] getScienceAppIdsByCategoryId(long categoryId) {
		return getFinder().getScienceAppIdsByCategoryId(categoryId);
	}

	public static java.util.Map<java.lang.Long, java.lang.Long> getLanguageSpecificCategories(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().getLanguageSpecificCategories(params);
	}

	public static java.util.List<java.lang.Object[]> retrieveListAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().retrieveListAppTest(params);
	}

	public static int countAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().countAppTest(params);
	}

	public static java.util.List<java.lang.Object[]> retrieveApiAppList(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return getFinder().retrieveApiAppList(params);
	}

	public static java.util.List<java.lang.Object[]> retrieveApiAppCategories(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException {
		return getFinder().retrieveApiAppCategories(params);
	}

	public static java.util.List<java.lang.Object[]> getMyAppListWithQna(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().getMyAppListWithQna(params);
	}

	public static java.util.List<java.lang.Object[]> getListMyAppQna(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().getListMyAppQna(params);
	}

	public static java.util.List<java.lang.Object[]> searchTextEntryScienceAPPList(
		java.util.Map params) {
		return getFinder().searchTextEntryScienceAPPList(params);
	}

	public static int searchTextEntryScienceAPPCount(java.util.Map params) {
		return getFinder().searchTextEntryScienceAPPCount(params);
	}

	public static java.util.List<org.kisti.edison.science.model.ScienceApp> getMyAppListForProject(
		java.util.Map<java.lang.String, java.lang.Object> params) {
		return getFinder().getMyAppListForProject(params);
	}

	public static int getMyAppListForProjectCount(java.util.Map params,
		java.util.Locale locale) {
		return getFinder().getMyAppListForProjectCount(params, locale);
	}

	public static int countScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countScienceApp(searchParam);
	}

	public static java.util.List<java.lang.Object[]> retrieveListScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().retrieveListScienceApp(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getScienceAppExecuteStatistics(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppExecuteStatistics(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getScienceAppHistoryList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppHistoryList(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getScienceAppReviewList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppReviewList(searchParam);
	}

	public static java.util.List<java.lang.Object[]> getSimulationUsersOfScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getSimulationUsersOfScienceApp(searchParam);
	}

	public static int countScienceAppByWorkflowId(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countScienceAppByWorkflowId(searchParam);
	}

	public static int getScienceAppByWorkflowId(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getScienceAppByWorkflowId(searchParam);
	}

	public static ScienceAppFinder getFinder() {
		if (_finder == null) {
			_finder = (ScienceAppFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.science.service.ClpSerializer.getServletContextName(),
					ScienceAppFinder.class.getName());

			ReferenceRegistry.registerReference(ScienceAppFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ScienceAppFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ScienceAppFinderUtil.class,
			"_finder");
	}

	private static ScienceAppFinder _finder;
}