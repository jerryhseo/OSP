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
 * Provides a wrapper for {@link ChildChallengeService}.
 *
 * @author KYJ
 * @see ChildChallengeService
 * @generated
 */
public class ChildChallengeServiceWrapper implements ChildChallengeService,
	ServiceWrapper<ChildChallengeService> {
	public ChildChallengeServiceWrapper(
		ChildChallengeService childChallengeService) {
		_childChallengeService = childChallengeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _childChallengeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_childChallengeService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _childChallengeService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public kisti.edison.challenge.model.ChildChallenge addChildChallenge(
		long userId, long challengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.addChildChallenge(userId, challengeId,
			number, presentationDay, paperStartDay, paperEndDay, evaluationDay,
			challengeStartDay, challengeEndDay, challengeStatus, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChildChallenge deleteChildChallenge(
		long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.deleteChildChallenge(childChallengeId,
			serviceContext);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.getChildChallenges(groupId, challengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.getChildChallenges(groupId, challengeId,
			start, end);
	}

	@Override
	public int getChildChallengesCount(long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.getChildChallengesCount(groupId,
			challengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallengesByStatus(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.getChildChallengesByStatus(groupId,
			challengeStatus);
	}

	@Override
	public kisti.edison.challenge.model.ChildChallenge updateChildChallenge(
		long userId, long challengeId, long childChallengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeService.updateChildChallenge(userId, challengeId,
			childChallengeId, number, presentationDay, paperStartDay,
			paperEndDay, evaluationDay, challengeStartDay, challengeEndDay,
			challengeStatus, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChildChallengeService getWrappedChildChallengeService() {
		return _childChallengeService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChildChallengeService(
		ChildChallengeService childChallengeService) {
		_childChallengeService = childChallengeService;
	}

	@Override
	public ChildChallengeService getWrappedService() {
		return _childChallengeService;
	}

	@Override
	public void setWrappedService(ChildChallengeService childChallengeService) {
		_childChallengeService = childChallengeService;
	}

	private ChildChallengeService _childChallengeService;
}