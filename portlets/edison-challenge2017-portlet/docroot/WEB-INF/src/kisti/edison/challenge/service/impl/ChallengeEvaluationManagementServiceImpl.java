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
import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeEvaluationManagementServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the challenge evaluation management remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeEvaluationManagementService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationManagementServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeEvaluationManagementServiceUtil
 */
public class ChallengeEvaluationManagementServiceImpl
	extends ChallengeEvaluationManagementServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeEvaluationManagementServiceUtil} to access the challenge evaluation management remote service.
	 */
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId) throws SystemException{
		return challengeEvaluationManagementPersistence.filterFindByGroupId(groupId);
	}
	
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId, long challengeTeamId) throws SystemException{
		return challengeEvaluationManagementPersistence.filterFindByGroupIdAndChildChallengeId(groupId, challengeTeamId);
	}
	
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId, long challengeTeamId, int start, int end) throws SystemException{
		return challengeEvaluationManagementPersistence.filterFindByGroupIdAndChildChallengeId(groupId, challengeTeamId, start, end);
	}
	
	public int getChallengeEvaluationCount(long groupId, long childChallengeId) throws SystemException{
		return challengeEvaluationManagementPersistence.countByGroupIdAndChildChallengeId(groupId, childChallengeId);
	}
	
	public ChallengeEvaluationManagement addChallengeEvalutionManagement(long userId, long childChallengeId, 
			Map<Locale, String> assessmentItem, double distribution, 
			ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		System.out.println("Add Evaluation Item Service 1 : Permission Check.");
		ChallengeModelPermission.check(getPermissionChecker(), serviceContext.getScopeGroupId(), ActionKeys.ADD_EVALUATION_ITEM);
		System.out.println("Add Evaluation Item Service 1 : Permission Check2.");
		return ChallengeEvaluationManagementLocalServiceUtil.addChallengeEvalutionManagement(userId, childChallengeId, 
				assessmentItem, distribution, serviceContext);
	}
	
	public ChallengeEvaluationManagement updateChallengeEvaluationManagement(long userId, long childChallengeId, 
			long challengeEvaluationManagementId,
			Map<Locale, String> assessmentItem, double distribution, 
			ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		
		ChallengeEvaluationManagementPermission.check(getPermissionChecker(), challengeEvaluationManagementId, ActionKeys.UPDATE);
		
		return ChallengeEvaluationManagementLocalServiceUtil.updateChallengeEvalutionManagement(userId, childChallengeId, 
				challengeEvaluationManagementId, assessmentItem, distribution, serviceContext);
	}
	
	public ChallengeEvaluationManagement deleteChallengeEvaluationManagement(long challengeEvaluationManagementId, ServiceContext serviceContext) throws PrincipalException, PortalException, SystemException{
		ChallengeEvaluationManagementPermission.check(getPermissionChecker(), challengeEvaluationManagementId, ActionKeys.DELETE);
		return ChallengeEvaluationManagementLocalServiceUtil.deleteChallengeEvalutionManagement(challengeEvaluationManagementId, serviceContext);
	}
}