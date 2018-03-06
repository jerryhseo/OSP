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
 * Provides a wrapper for {@link ChallengeTeamService}.
 *
 * @author KYJ
 * @see ChallengeTeamService
 * @generated
 */
public class ChallengeTeamServiceWrapper implements ChallengeTeamService,
	ServiceWrapper<ChallengeTeamService> {
	public ChallengeTeamServiceWrapper(
		ChallengeTeamService challengeTeamService) {
		_challengeTeamService = challengeTeamService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeTeamService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeTeamService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeTeamService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
		long userId, java.lang.String teamName,
		java.util.Map<java.util.Locale, java.lang.String> subject,
		java.util.Map<java.util.Locale, java.lang.String> paperName,
		java.util.Map<java.util.Locale, java.lang.String> paperAbstract,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone, long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamService.addChallengeTeam(userId, teamName,
			subject, paperName, paperAbstract, uploadRequest, grade, phone,
			childChallengeId, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamService.deleteChallengeTeam(challengeTeamId,
			serviceContext);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamService.getChallengeTeames(groupId,
			childChallengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamService.getChallengeTeames(groupId,
			childChallengeId, start, end);
	}

	@Override
	public int getChallengeTeamesCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamService.getChallengeTeamesCount(groupId,
			childChallengeId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam updateChallengeTeam(
		long userId, long childChallengeId, long challengeTeamId,
		java.lang.String teamName,
		java.util.Map<java.util.Locale, java.lang.String> subject,
		java.util.Map<java.util.Locale, java.lang.String> paperName,
		java.util.Map<java.util.Locale, java.lang.String> paperAbstract,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeTeamService.updateChallengeTeam(userId,
			childChallengeId, challengeTeamId, teamName, subject, paperName,
			paperAbstract, uploadRequest, grade, phone, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeTeamService getWrappedChallengeTeamService() {
		return _challengeTeamService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeTeamService(
		ChallengeTeamService challengeTeamService) {
		_challengeTeamService = challengeTeamService;
	}

	@Override
	public ChallengeTeamService getWrappedService() {
		return _challengeTeamService;
	}

	@Override
	public void setWrappedService(ChallengeTeamService challengeTeamService) {
		_challengeTeamService = challengeTeamService;
	}

	private ChallengeTeamService _challengeTeamService;
}