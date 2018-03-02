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

package kisti.edison.challenge.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for ChildChallenge. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChildChallengeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChildChallengeService
 * @see kisti.edison.challenge.service.base.ChildChallengeServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChildChallengeServiceImpl
 * @generated
 */
public class ChildChallengeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChildChallengeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static kisti.edison.challenge.model.ChildChallenge addChildChallenge(
		long userId, long challengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChildChallenge(userId, challengeId, number,
			presentationDay, paperStartDay, paperEndDay, evaluationDay,
			challengeStartDay, challengeEndDay, challengeStatus, serviceContext);
	}

	public static kisti.edison.challenge.model.ChildChallenge deleteChildChallenge(
		long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChildChallenge(childChallengeId, serviceContext);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, challengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, challengeId, start, end);
	}

	public static int getChildChallengesCount(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengesCount(groupId, challengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallengesByStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengesByStatus(groupId, challengeStatus);
	}

	public static kisti.edison.challenge.model.ChildChallenge updateChildChallenge(
		long userId, long challengeId, long childChallengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChildChallenge(userId, challengeId, childChallengeId,
			number, presentationDay, paperStartDay, paperEndDay, evaluationDay,
			challengeStartDay, challengeEndDay, challengeStatus, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChildChallengeService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChildChallengeService.class.getName());

			if (invokableService instanceof ChildChallengeService) {
				_service = (ChildChallengeService)invokableService;
			}
			else {
				_service = new ChildChallengeServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChildChallengeServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChildChallengeService service) {
	}

	private static ChildChallengeService _service;
}