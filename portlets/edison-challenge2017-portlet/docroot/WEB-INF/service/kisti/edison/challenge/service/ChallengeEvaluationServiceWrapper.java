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
 * Provides a wrapper for {@link ChallengeEvaluationService}.
 *
 * @author KYJ
 * @see ChallengeEvaluationService
 * @generated
 */
public class ChallengeEvaluationServiceWrapper
	implements ChallengeEvaluationService,
		ServiceWrapper<ChallengeEvaluationService> {
	public ChallengeEvaluationServiceWrapper(
		ChallengeEvaluationService challengeEvaluationService) {
		_challengeEvaluationService = challengeEvaluationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeEvaluationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeEvaluationService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeEvaluationService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationService.getChallengeEvaluations(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationService.getChallengeEvaluations(groupId,
			challengeTeamId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationService.getChallengeEvaluations(groupId,
			challengeTeamId, start, end);
	}

	@Override
	public int getChallengeEvaluationCount(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationService.getChallengeEvaluationCount(groupId,
			challengeTeamId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluation addChallengeEvaluation(
		long userId, long challengeTeamId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationService.addChallengeEvaluation(userId,
			challengeTeamId, assessmentItem, distribution, score, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluation updateChallengeEvaluation(
		long userId, long challengeTeamId, long challengeEvaluationId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationService.updateChallengeEvaluation(userId,
			challengeTeamId, challengeEvaluationId, assessmentItem,
			distribution, score, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvaluation(
		long challengeEvaluationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationService.deleteChallengeEvaluation(challengeEvaluationId,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeEvaluationService getWrappedChallengeEvaluationService() {
		return _challengeEvaluationService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeEvaluationService(
		ChallengeEvaluationService challengeEvaluationService) {
		_challengeEvaluationService = challengeEvaluationService;
	}

	@Override
	public ChallengeEvaluationService getWrappedService() {
		return _challengeEvaluationService;
	}

	@Override
	public void setWrappedService(
		ChallengeEvaluationService challengeEvaluationService) {
		_challengeEvaluationService = challengeEvaluationService;
	}

	private ChallengeEvaluationService _challengeEvaluationService;
}