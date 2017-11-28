/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

/**
 * @author EDISON
 */
public interface ScienceAppFinder {
	public int countScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		boolean categorySearch, boolean managerSearch)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> retrieveListScienceApp(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		boolean categorySearch, boolean managerSearch)
		throws com.liferay.portal.kernel.exception.SystemException;

	public long[] getScienceAppIdsByCategoryId(long categoryId);

	public java.util.Map<java.lang.Long, java.lang.Long> getLanguageSpecificCategories(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public java.util.List<java.lang.Object[]> retrieveListAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public int countAppTest(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public java.util.List<java.lang.Object[]> retrieveApiAppList(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException;

	public java.util.List<java.lang.Object[]> retrieveApiAppCategories(
		java.util.Map<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppException;

	public java.util.List<java.lang.Object[]> getMyAppListWithQna(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public java.util.List<java.lang.Object[]> getListMyAppQna(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public java.util.List<java.lang.Object[]> searchTextEntryScienceAPPList(
		java.util.Map params);

	public int searchTextEntryScienceAPPCount(java.util.Map params);

	public java.util.List<org.kisti.edison.science.model.ScienceApp> getMyAppListForProject(
		java.util.Map<java.lang.String, java.lang.Object> params);

	public int getMyAppListForProjectCount(java.util.Map params,
		java.util.Locale locale);
}