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
 * Provides the remote service utility for ChallengeTeamMember. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeTeamMemberServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeTeamMemberService
 * @see kisti.edison.challenge.service.base.ChallengeTeamMemberServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeTeamMemberServiceImpl
 * @generated
 */
public class ChallengeTeamMemberServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeTeamMemberServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static kisti.edison.challenge.model.ChallengeTeamMember addChallengeTeamMember(
		long userId, long challengeTeamId, long applyUserId,
		java.lang.String grade, java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .addChallengeTeamMember(userId, challengeTeamId,
			applyUserId, grade, phone, isLeader, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember deleteChallengeTeamMember(
		long challengeTeamMemberId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .deleteChallengeTeamMember(challengeTeamMemberId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember updateChallengeTeamMember(
		long userId, long challengeTeamMemberId, java.lang.String grade,
		java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .updateChallengeTeamMember(userId, challengeTeamMemberId,
			grade, phone, isLeader, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeTeamMemberService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeTeamMemberService.class.getName());

			if (invokableService instanceof ChallengeTeamMemberService) {
				_service = (ChallengeTeamMemberService)invokableService;
			}
			else {
				_service = new ChallengeTeamMemberServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeTeamMemberServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeTeamMemberService service) {
	}

	private static ChallengeTeamMemberService _service;
}