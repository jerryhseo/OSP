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
 * Provides the remote service interface for ChallengeEvaluation. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationServiceUtil
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ChallengeEvaluationService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeEvaluationServiceUtil} to access the challenge evaluation remote service. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeEvaluationCount(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeEvaluation addChallengeEvaluation(
		long userId, long challengeTeamId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluation updateChallengeEvaluation(
		long userId, long challengeTeamId, long challengeEvaluationId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvaluation(
		long challengeEvaluationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;
}