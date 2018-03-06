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

package kisti.edison.challenge.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author KYJ
 */
public class ChallengeTeamFinderUtil {
	public static java.util.List<java.lang.String> getChallengeTeamAppList(
		java.lang.String teamID, java.lang.String startDate,
		java.lang.String endDate) {
		return getFinder().getChallengeTeamAppList(teamID, startDate, endDate);
	}

	public static java.lang.String getCPUUseage(java.lang.String teamID,
		java.lang.String startDate, java.lang.String endDate) {
		return getFinder().getCPUUseage(teamID, startDate, endDate);
	}

	public static ChallengeTeamFinder getFinder() {
		if (_finder == null) {
			_finder = (ChallengeTeamFinder)PortletBeanLocatorUtil.locate(kisti.edison.challenge.service.ClpSerializer.getServletContextName(),
					ChallengeTeamFinder.class.getName());

			ReferenceRegistry.registerReference(ChallengeTeamFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ChallengeTeamFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ChallengeTeamFinderUtil.class,
			"_finder");
	}

	private static ChallengeTeamFinder _finder;
}