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

/**
 * @author edison
 */
public interface ContentsFinder {
	public int getContentsTotalCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getContentsCountByDate()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> getContentsCountBySite()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int updateContentsStatistics()
		throws com.liferay.portal.kernel.exception.SystemException;
}