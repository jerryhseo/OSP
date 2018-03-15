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
 * Provides a wrapper for {@link ChallengeService}.
 *
 * @author KYJ
 * @see ChallengeService
 * @generated
 */
public class ChallengeServiceWrapper implements ChallengeService,
	ServiceWrapper<ChallengeService> {
	public ChallengeServiceWrapper(ChallengeService challengeService) {
		_challengeService = challengeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public kisti.edison.challenge.model.Challenge addChallenge(long userId,
		java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeService.addChallenge(userId, name, field, description,
			serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.Challenge deleteChallenge(
		long challengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeService.deleteChallenge(challengeId, serviceContext);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeService.getChallenges(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeService.getChallenges(groupId, start, end);
	}

	@Override
	public kisti.edison.challenge.model.Challenge getChallengeGroupAndField(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException {
		return _challengeService.getChallengeGroupAndField(groupId, field,
			orderByComparator);
	}

	@Override
	public kisti.edison.challenge.model.Challenge updateChallenge(long userId,
		long challengeId,
		java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeService.updateChallenge(userId, challengeId, name,
			field, description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeService getWrappedChallengeService() {
		return _challengeService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeService(ChallengeService challengeService) {
		_challengeService = challengeService;
	}

	@Override
	public ChallengeService getWrappedService() {
		return _challengeService;
	}

	@Override
	public void setWrappedService(ChallengeService challengeService) {
		_challengeService = challengeService;
	}

	private ChallengeService _challengeService;
}