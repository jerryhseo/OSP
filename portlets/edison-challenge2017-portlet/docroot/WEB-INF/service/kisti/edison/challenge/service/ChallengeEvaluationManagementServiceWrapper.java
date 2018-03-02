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
 * Provides a wrapper for {@link ChallengeEvaluationManagementService}.
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementService
 * @generated
 */
public class ChallengeEvaluationManagementServiceWrapper
	implements ChallengeEvaluationManagementService,
		ServiceWrapper<ChallengeEvaluationManagementService> {
	public ChallengeEvaluationManagementServiceWrapper(
		ChallengeEvaluationManagementService challengeEvaluationManagementService) {
		_challengeEvaluationManagementService = challengeEvaluationManagementService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeEvaluationManagementService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeEvaluationManagementService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeEvaluationManagementService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagementService.getChallengeEvaluationManagementes(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagementService.getChallengeEvaluationManagementes(groupId,
			challengeTeamId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagementService.getChallengeEvaluationManagementes(groupId,
			challengeTeamId, start, end);
	}

	@Override
	public int getChallengeEvaluationCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationManagementService.getChallengeEvaluationCount(groupId,
			childChallengeId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationManagement addChallengeEvalutionManagement(
		long userId, long childChallengeId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationManagementService.addChallengeEvalutionManagement(userId,
			childChallengeId, assessmentItem, distribution, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationManagement updateChallengeEvaluationManagement(
		long userId, long childChallengeId,
		long challengeEvaluationManagementId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationManagementService.updateChallengeEvaluationManagement(userId,
			childChallengeId, challengeEvaluationManagementId, assessmentItem,
			distribution, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvaluationManagement(
		long challengeEvaluationManagementId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationManagementService.deleteChallengeEvaluationManagement(challengeEvaluationManagementId,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeEvaluationManagementService getWrappedChallengeEvaluationManagementService() {
		return _challengeEvaluationManagementService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeEvaluationManagementService(
		ChallengeEvaluationManagementService challengeEvaluationManagementService) {
		_challengeEvaluationManagementService = challengeEvaluationManagementService;
	}

	@Override
	public ChallengeEvaluationManagementService getWrappedService() {
		return _challengeEvaluationManagementService;
	}

	@Override
	public void setWrappedService(
		ChallengeEvaluationManagementService challengeEvaluationManagementService) {
		_challengeEvaluationManagementService = challengeEvaluationManagementService;
	}

	private ChallengeEvaluationManagementService _challengeEvaluationManagementService;
}