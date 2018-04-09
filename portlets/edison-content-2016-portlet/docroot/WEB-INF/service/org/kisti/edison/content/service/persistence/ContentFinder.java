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

/**
 * @author EDISON
 */
public interface ContentFinder {
	public int getContentCount(long[] categoryIds, java.lang.String searchText,
		long[] contentDiv, java.lang.String languageId, long classNameId,
		boolean categoryJoin, boolean isTotalSearch);

	public java.util.List<java.lang.Object[]> getContentList(
		long[] categoryIds, java.lang.String searchText, long[] contentDiv,
		int start, int end, java.lang.String languageId, long classNameId,
		boolean categoryJoin, boolean isTotalSearch);

	public java.util.List<java.lang.Object[]> getContentList(
		long[] categoryIds, java.lang.String searchText, long[] contentDiv,
		int start, int end, java.lang.String languageId, long classNameId,
		boolean categoryJoin, boolean isTotalSearch,
		java.lang.String sortField, java.lang.String sortOrder);

	public int getContentUserCount(long[] categoryIds,
		java.lang.String searchText, java.lang.String languageId,
		long classNameId, long userId, long roleId);

	public java.util.List<java.lang.Object[]> getContentUserList(
		long[] categoryIds, java.lang.String searchText, int start, int end,
		java.lang.String languageId, long classNameId, long userId, long roleId);

	public java.util.List<java.lang.Object[]> searchTextEntryContentList(
		java.util.Map params);

	public java.lang.Integer searchTextEntryContentCount(java.util.Map params);

	public java.util.List<java.lang.Object[]> getContentForProjectList(
		long userId, java.lang.String searchText,
		java.lang.String projectCategoryId, java.lang.String languageId,
		int start, int end);

	public java.util.List<java.lang.Object[]> getContentDatailList(
		java.lang.String projectYn, long columnId, long categoryId,
		java.lang.String languageId);

	public java.util.List<java.lang.Object[]> getContentCenterList(
		java.lang.String projectYn, java.lang.String propertyKey);

	public int getGeneralContentForProjectListCount(long userId,
		java.lang.String searchText, java.lang.String projectCategoryId,
		java.lang.String languageId);
}