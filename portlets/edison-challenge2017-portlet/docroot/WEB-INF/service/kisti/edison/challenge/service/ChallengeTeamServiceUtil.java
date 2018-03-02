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
 * Provides the remote service utility for ChallengeTeam. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeTeamServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeTeamService
 * @see kisti.edison.challenge.service.base.ChallengeTeamServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeTeamServiceImpl
 * @generated
 */
public class ChallengeTeamServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeTeamServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
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
		return getService()
				   .addChallengeTeam(userId, teamName, subject, paperName,
			paperAbstract, uploadRequest, grade, phone, childChallengeId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService().deleteChallengeTeam(challengeTeamId, serviceContext);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeames(groupId, childChallengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeTeames(groupId, childChallengeId, start, end);
	}

	public static int getChallengeTeamesCount(long groupId,
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamesCount(groupId, childChallengeId);
	}

	public static kisti.edison.challenge.model.ChallengeTeam updateChallengeTeam(
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
		return getService()
				   .updateChallengeTeam(userId, childChallengeId,
			challengeTeamId, teamName, subject, paperName, paperAbstract,
			uploadRequest, grade, phone, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeTeamService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeTeamService.class.getName());

			if (invokableService instanceof ChallengeTeamService) {
				_service = (ChallengeTeamService)invokableService;
			}
			else {
				_service = new ChallengeTeamServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeTeamServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeTeamService service) {
	}

	private static ChallengeTeamService _service;
}