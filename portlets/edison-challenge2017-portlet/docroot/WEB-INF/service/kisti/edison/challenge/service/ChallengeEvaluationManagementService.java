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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for ChallengeEvaluationManagement. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementServiceUtil
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationManagementServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationManagementServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ChallengeEvaluationManagementService extends BaseService,
	InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeEvaluationManagementServiceUtil} to access the challenge evaluation management remote service. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationManagementServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeEvaluationCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeEvaluationManagement addChallengeEvalutionManagement(
		long userId, long childChallengeId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluationManagement updateChallengeEvaluationManagement(
		long userId, long childChallengeId,
		long challengeEvaluationManagementId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvaluationManagement(
		long challengeEvaluationManagementId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;
}