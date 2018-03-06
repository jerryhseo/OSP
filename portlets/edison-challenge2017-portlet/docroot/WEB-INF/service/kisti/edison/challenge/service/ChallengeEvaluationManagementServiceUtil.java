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
 * Provides the remote service utility for ChallengeEvaluationManagement. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationManagementServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationManagementServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationManagementServiceImpl
 * @generated
 */
public class ChallengeEvaluationManagementServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationManagementServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationManagementes(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementes(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementes(groupId,
			challengeTeamId, start, end);
	}

	public static int getChallengeEvaluationCount(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationCount(groupId, childChallengeId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement addChallengeEvalutionManagement(
		long userId, long childChallengeId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .addChallengeEvalutionManagement(userId, childChallengeId,
			assessmentItem, distribution, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement updateChallengeEvaluationManagement(
		long userId, long childChallengeId,
		long challengeEvaluationManagementId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .updateChallengeEvaluationManagement(userId,
			childChallengeId, challengeEvaluationManagementId, assessmentItem,
			distribution, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvaluationManagement(
		long challengeEvaluationManagementId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService()
				   .deleteChallengeEvaluationManagement(challengeEvaluationManagementId,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationManagementService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationManagementService.class.getName());

			if (invokableService instanceof ChallengeEvaluationManagementService) {
				_service = (ChallengeEvaluationManagementService)invokableService;
			}
			else {
				_service = new ChallengeEvaluationManagementServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationManagementServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationManagementService service) {
	}

	private static ChallengeEvaluationManagementService _service;
}