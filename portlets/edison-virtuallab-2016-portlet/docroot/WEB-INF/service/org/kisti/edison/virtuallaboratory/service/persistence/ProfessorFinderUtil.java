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
public class ProfessorFinderUtil {
	public static java.util.List<java.lang.Object[]> getProfessorList(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getProfessorList(params, locale);
	}

	public static int getCountProfessor(
		java.util.Map<java.lang.String, java.lang.Object> params,
		java.util.Locale locale) {
		return getFinder().getCountProfessor(params, locale);
	}

	public static ProfessorFinder getFinder() {
		if (_finder == null) {
			_finder = (ProfessorFinder)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					ProfessorFinder.class.getName());

			ReferenceRegistry.registerReference(ProfessorFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ProfessorFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ProfessorFinderUtil.class, "_finder");
	}

	private static ProfessorFinder _finder;
}