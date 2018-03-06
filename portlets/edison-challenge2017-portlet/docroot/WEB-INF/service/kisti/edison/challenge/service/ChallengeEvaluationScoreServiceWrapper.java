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
 * Provides a wrapper for {@link ChallengeEvaluationScoreService}.
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreService
 * @generated
 */
public class ChallengeEvaluationScoreServiceWrapper
	implements ChallengeEvaluationScoreService,
		ServiceWrapper<ChallengeEvaluationScoreService> {
	public ChallengeEvaluationScoreServiceWrapper(
		ChallengeEvaluationScoreService challengeEvaluationScoreService) {
		_challengeEvaluationScoreService = challengeEvaluationScoreService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeEvaluationScoreService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeEvaluationScoreService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeEvaluationScoreService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationScores(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationScores(groupId,
			challengeTeamId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores2(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationScores2(groupId,
			childChallengeId);
	}

	@Override
	public int getChallengeEvaluationCount2(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationCount2(groupId,
			challengeTeamId);
	}

	@Override
	public int getChallengeEvaluationCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationCount(groupId,
			childChallengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreService.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationScoreService.addChallengeEvaluationScore(userId,
			challengeTeamId, challengeEvaluationManagementId, score,
			serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		long userId, long challengeEvaluationScoreId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationScoreService.updateChallengeEvaluationScore(userId,
			challengeEvaluationScoreId, score, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _challengeEvaluationScoreService.deleteChallengeEvaluationScore(challengeEvaluationScoreId,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeEvaluationScoreService getWrappedChallengeEvaluationScoreService() {
		return _challengeEvaluationScoreService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeEvaluationScoreService(
		ChallengeEvaluationScoreService challengeEvaluationScoreService) {
		_challengeEvaluationScoreService = challengeEvaluationScoreService;
	}

	@Override
	public ChallengeEvaluationScoreService getWrappedService() {
		return _challengeEvaluationScoreService;
	}

	@Override
	public void setWrappedService(
		ChallengeEvaluationScoreService challengeEvaluationScoreService) {
		_challengeEvaluationScoreService = challengeEvaluationScoreService;
	}

	private ChallengeEvaluationScoreService _challengeEvaluationScoreService;
}