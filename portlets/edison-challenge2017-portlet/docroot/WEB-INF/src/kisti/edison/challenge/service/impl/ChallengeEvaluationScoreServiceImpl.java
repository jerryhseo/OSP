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

package kisti.edison.challenge.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;

import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeEvaluationScoreServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission;
import kisti.edison.challenge.service.permission.ChallengeEvaluationScorePermission;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the challenge evaluation score remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeEvaluationScoreService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationScoreServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil
 */
public class ChallengeEvaluationScoreServiceImpl
	extends ChallengeEvaluationScoreServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil} to access the challenge evaluation score remote service.
	 */
	
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores(long groupId) throws SystemException{
		return challengeEvaluationScorePersistence.filterFindByGroupId(groupId);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores(long groupId, long challengeTeamId) throws SystemException{
		return challengeEvaluationScorePersistence.filterFindByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores2(long groupId, long childChallengeId) throws SystemException{
		return challengeEvaluationScorePersistence.filterFindByGroupIdAndChallengeEvaluationManagementId(groupId, childChallengeId);
	}
	
	public int getChallengeEvaluationCount2(long groupId, long challengeTeamId) throws SystemException{
		return challengeEvaluationScorePersistence.countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public int getChallengeEvaluationCount(long groupId, long childChallengeId) throws SystemException{
		return challengeEvaluationScorePersistence.countByGroupIdAndChallengeEvaluationManagementId(groupId, childChallengeId);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(long groupId, 
			long challengeTeamId, long userId) throws SystemException{
		return challengeEvaluationScorePersistence.filterFindByGroupIdAndChallengeTeamIdAndUserId(userId, groupId, challengeTeamId);
	}
	
	
	
	public ChallengeEvaluationScore addChallengeEvaluationScore(long userId, long challengeTeamId,
			long challengeEvaluationManagementId, double score, ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		System.out.println("Add Evaluation Score Service 1 : Permission Check.");
		ChallengeModelPermission.check(getPermissionChecker(), serviceContext.getScopeGroupId(), ActionKeys.EVALUATION_TEAM);
		System.out.println("Add Evaluation Score Service 1 : Permission Check2.");
		return ChallengeEvaluationScoreLocalServiceUtil.addChallengeEvaluationScore(userId, challengeTeamId, 
				challengeEvaluationManagementId, score, serviceContext);
	}
	
	public ChallengeEvaluationScore updateChallengeEvaluationScore(long userId, 
			long challengeEvaluationScoreId, double score, ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		
		ChallengeEvaluationScorePermission.check(getPermissionChecker(), challengeEvaluationScoreId, ActionKeys.UPDATE);
		
		
		return ChallengeEvaluationScoreLocalServiceUtil.updateChallengeEvaluationScore(challengeEvaluationScoreId, userId, score, serviceContext);
	}
	
	public ChallengeEvaluationScore deleteChallengeEvaluationScore(long challengeEvaluationScoreId, ServiceContext serviceContext) throws PrincipalException, PortalException, SystemException{
		ChallengeEvaluationScorePermission.check(getPermissionChecker(), challengeEvaluationScoreId, ActionKeys.DELETE);
		return ChallengeEvaluationScoreLocalServiceUtil.deleteChallengeEvaluationScore(challengeEvaluationScoreId, serviceContext);
	}
	
	
}