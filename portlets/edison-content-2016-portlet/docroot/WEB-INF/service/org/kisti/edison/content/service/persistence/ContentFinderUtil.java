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

package org.kisti.edison.content.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author EDISON
 */
public class ContentFinderUtil {
	public static int getContentCount(long[] categoryIds,
		java.lang.String searchText, long[] contentDiv,
		java.lang.String languageId, long classNameId, boolean categoryJoin,
		boolean isTotalSearch) {
		return getFinder()
				   .getContentCount(categoryIds, searchText, contentDiv,
			languageId, classNameId, categoryJoin, isTotalSearch);
	}

	public static java.util.List<java.lang.Object[]> getContentList(
		long[] categoryIds, java.lang.String searchText, long[] contentDiv,
		int start, int end, java.lang.String languageId, long classNameId,
		boolean categoryJoin, boolean isTotalSearch) {
		return getFinder()
				   .getContentList(categoryIds, searchText, contentDiv, start,
			end, languageId, classNameId, categoryJoin, isTotalSearch);
	}

	public static java.util.List<java.lang.Object[]> getContentList(
		long[] categoryIds, java.lang.String searchText, long[] contentDiv,
		int start, int end, java.lang.String languageId, long classNameId,
		boolean categoryJoin, boolean isTotalSearch,
		java.lang.String sortField, java.lang.String sortOrder) {
		return getFinder()
				   .getContentList(categoryIds, searchText, contentDiv, start,
			end, languageId, classNameId, categoryJoin, isTotalSearch,
			sortField, sortOrder);
	}

	public static int getContentUserCount(long[] categoryIds,
		java.lang.String searchText, java.lang.String languageId,
		long classNameId, long userId, long roleId) {
		return getFinder()
				   .getContentUserCount(categoryIds, searchText, languageId,
			classNameId, userId, roleId);
	}

	public static java.util.List<java.lang.Object[]> getContentUserList(
		long[] categoryIds, java.lang.String searchText, int start, int end,
		java.lang.String languageId, long classNameId, long userId, long roleId) {
		return getFinder()
				   .getContentUserList(categoryIds, searchText, start, end,
			languageId, classNameId, userId, roleId);
	}

	public static java.util.List<java.lang.Object[]> searchTextEntryContentList(
		java.util.Map params) {
		return getFinder().searchTextEntryContentList(params);
	}

	public static java.lang.Integer searchTextEntryContentCount(
		java.util.Map params) {
		return getFinder().searchTextEntryContentCount(params);
	}

	public static java.util.List<java.lang.Object[]> getContentForProjectList(
		long userId, java.lang.String searchText,
		java.lang.String projectCategoryId, java.lang.String languageId,
		int start, int end) {
		return getFinder()
				   .getContentForProjectList(userId, searchText,
			projectCategoryId, languageId, start, end);
	}

	public static java.util.List<java.lang.Object[]> getContentDatailList(
		java.lang.String projectYn, long columnId, long categoryId,
		java.lang.String languageId) {
		return getFinder()
				   .getContentDatailList(projectYn, columnId, categoryId,
			languageId);
	}

	public static java.util.List<java.lang.Object[]> getContentCenterList(
		java.lang.String projectYn, java.lang.String propertyKey) {
		return getFinder().getContentCenterList(projectYn, propertyKey);
	}

	public static int getGeneralContentForProjectListCount(long userId,
		java.lang.String searchText, java.lang.String projectCategoryId,
		java.lang.String languageId) {
		return getFinder()
				   .getGeneralContentForProjectListCount(userId, searchText,
			projectCategoryId, languageId);
	}

	public static ContentFinder getFinder() {
		if (_finder == null) {
			_finder = (ContentFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.content.service.ClpSerializer.getServletContextName(),
					ContentFinder.class.getName());

			ReferenceRegistry.registerReference(ContentFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ContentFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ContentFinderUtil.class, "_finder");
	}

	private static ContentFinder _finder;
}