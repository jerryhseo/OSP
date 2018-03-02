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
 * Provides the remote service utility for ChallengeEvaluationScore. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationScoreServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationScoreServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationScoreServiceImpl
 * @generated
 */
public class ChallengeEvaluationScoreServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationScoreServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationScores(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScores(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores2(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScores2(groupId, childChallengeId);
	}

	public static int getChallengeEvaluationCount2(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationCount2(groupId, challengeTeamId);
	}

	public static int getChallengeEvaluationCount(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationCount(groupId, childChallengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .addChallengeEvaluationScore(userId, challengeTeamId,
			challengeEvaluationManagementId, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		long userId, long challengeEvaluationScoreId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .updateChallengeEvaluationScore(userId,
			challengeEvaluationScoreId, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .deleteChallengeEvaluationScore(challengeEvaluationScoreId,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationScoreService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationScoreService.class.getName());

			if (invokableService instanceof ChallengeEvaluationScoreService) {
				_service = (ChallengeEvaluationScoreService)invokableService;
			}
			else {
				_service = new ChallengeEvaluationScoreServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationScoreServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationScoreService service) {
	}

	private static ChallengeEvaluationScoreService _service;
}