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
 * Provides the remote service utility for ChallengeEvaluation. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationServiceImpl
 * @generated
 */
public class ChallengeEvaluationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluations(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluations(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluations(groupId, challengeTeamId, start, end);
	}

	public static int getChallengeEvaluationCount(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationCount(groupId, challengeTeamId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation addChallengeEvaluation(
		long userId, long challengeTeamId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .addChallengeEvaluation(userId, challengeTeamId,
			assessmentItem, distribution, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation updateChallengeEvaluation(
		long userId, long challengeTeamId, long challengeEvaluationId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .updateChallengeEvaluation(userId, challengeTeamId,
			challengeEvaluationId, assessmentItem, distribution, score,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvaluation(
		long challengeEvaluationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .deleteChallengeEvaluation(challengeEvaluationId,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationService.class.getName());

			if (invokableService instanceof ChallengeEvaluationService) {
				_service = (ChallengeEvaluationService)invokableService;
			}
			else {
				_service = new ChallengeEvaluationServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationService service) {
	}

	private static ChallengeEvaluationService _service;
}