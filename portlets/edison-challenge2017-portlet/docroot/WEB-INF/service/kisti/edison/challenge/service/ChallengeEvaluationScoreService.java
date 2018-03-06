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
 * Provides the remote service interface for ChallengeEvaluationScore. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreServiceUtil
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationScoreServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationScoreServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ChallengeEvaluationScoreService extends BaseService,
	InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeEvaluationScoreServiceUtil} to access the challenge evaluation score remote service. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationScoreServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores2(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeEvaluationCount2(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeEvaluationCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		long userId, long challengeEvaluationScoreId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;

	public kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException;
}