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

/**
 * @author EDISON
 */
public interface VirtualLabClassScienceAppFinder {
	public java.util.List<java.lang.Object[]> getVirtualLabScienceAppList(
		long columnId, long virtualLabId, long classId, long scienceAppId,
		java.util.Locale locale);

	public java.util.List<java.lang.Object[]> getVirtualLabClassScienceAppList(
		long columnId, long virtualLabId, long classId, java.util.Locale locale);

	public java.util.List<java.lang.Object[]> getScienceAppList(
		long virtualLabId, java.lang.String searchField);

	public java.util.List<java.lang.Object[]> getVirtualLabClassesInfo(
		long virtualLabId, long scienceAppId);

	public java.util.List<java.lang.Object[]> getVirtualLabInfo(
		long virtualLabId, long scienceAppId);
}