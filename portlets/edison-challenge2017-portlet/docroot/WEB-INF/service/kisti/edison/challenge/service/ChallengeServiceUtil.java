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
 * Provides the remote service utility for Challenge. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author KYJ
 * @see ChallengeService
 * @see kisti.edison.challenge.service.base.ChallengeServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeServiceImpl
 * @generated
 */
public class ChallengeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static kisti.edison.challenge.model.Challenge addChallenge(
		long userId, java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallenge(userId, name, field, description,
			serviceContext);
	}

	public static kisti.edison.challenge.model.Challenge deleteChallenge(
		long challengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallenge(challengeId, serviceContext);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(groupId, start, end);
	}

	public static kisti.edison.challenge.model.Challenge getChallengeGroupAndField(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException {
		return getService()
				   .getChallengeGroupAndField(groupId, field, orderByComparator);
	}

	public static kisti.edison.challenge.model.Challenge updateChallenge(
		long userId, long challengeId,
		java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallenge(userId, challengeId, name, field,
			description, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeService.class.getName());

			if (invokableService instanceof ChallengeService) {
				_service = (ChallengeService)invokableService;
			}
			else {
				_service = new ChallengeServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(ChallengeServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeService service) {
	}

	private static ChallengeService _service;
}