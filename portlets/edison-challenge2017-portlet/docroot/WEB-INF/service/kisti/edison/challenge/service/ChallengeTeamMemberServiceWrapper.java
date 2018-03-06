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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChallengeTeamMemberService}.
 *
 * @author KYJ
 * @see ChallengeTeamMemberService
 * @generated
 */
public class ChallengeTeamMemberServiceWrapper
	implements ChallengeTeamMemberService,
		ServiceWrapper<ChallengeTeamMemberService> {
	public ChallengeTeamMemberServiceWrapper(
		ChallengeTeamMemberService challengeTeamMemberService) {
		_challengeTeamMemberService = challengeTeamMemberService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeTeamMemberService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeTeamMemberService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeTeamMemberService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeamMember addChallengeTeamMember(
		long userId, long challengeTeamId, long applyUserId,
		java.lang.String grade, java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamMemberService.addChallengeTeamMember(userId,
			challengeTeamId, applyUserId, grade, phone, isLeader, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeamMember deleteChallengeTeamMember(
		long challengeTeamMemberId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamMemberService.deleteChallengeTeamMember(challengeTeamMemberId,
			serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeamMember updateChallengeTeamMember(
		long userId, long challengeTeamMemberId, java.lang.String grade,
		java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamMemberService.updateChallengeTeamMember(userId,
			challengeTeamMemberId, grade, phone, isLeader, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeTeamMemberService getWrappedChallengeTeamMemberService() {
		return _challengeTeamMemberService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeTeamMemberService(
		ChallengeTeamMemberService challengeTeamMemberService) {
		_challengeTeamMemberService = challengeTeamMemberService;
	}

	@Override
	public ChallengeTeamMemberService getWrappedService() {
		return _challengeTeamMemberService;
	}

	@Override
	public void setWrappedService(
		ChallengeTeamMemberService challengeTeamMemberService) {
		_challengeTeamMemberService = challengeTeamMemberService;
	}

	private ChallengeTeamMemberService _challengeTeamMemberService;
}