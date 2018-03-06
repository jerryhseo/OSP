/**
challenge team service util 1challenge team service util 1challenge team service util 1challenge team service util 1challenge team service util 1challenge team service util 1challenge team service util 1 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeTeamServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.service.permission.ChallengeTeamPermission;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the challenge team remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeTeamService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeTeamServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeTeamServiceUtil
 */
public class ChallengeTeamServiceImpl extends ChallengeTeamServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeTeamServiceUtil} to access the challenge team remote service.
	 */
	
	public ChallengeTeam addChallengeTeam(long userId, String teamName,
			Map<Locale, String> subject, Map<Locale, String> paperName,
			Map<Locale, String> paperAbstract, UploadPortletRequest uploadRequest,
			String grade, String phone, long childChallengeId, ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		System.out.println("Check add challengeTeam permission.");
		ChallengeModelPermission.check(getPermissionChecker(), serviceContext.getScopeGroupId(), 
				ActionKeys.PARTICIPATION_CHALLENGE);
		System.out.println("Check add challengeTeam permission2.");
		return ChallengeTeamLocalServiceUtil.addChallengeTeam(userId, teamName, 
				subject, paperName, paperAbstract, uploadRequest, grade, phone, 
				childChallengeId, serviceContext);
		
	}
	
	public ChallengeTeam deleteChallengeTeam(long challengeTeamId, 
			ServiceContext serviceContext) throws PrincipalException, PortalException, SystemException{
		ChallengeTeamPermission.check(getPermissionChecker(), challengeTeamId, ActionKeys.DELETE);
		
		return ChallengeTeamLocalServiceUtil.deleteChallengeTeam(challengeTeamId, serviceContext);
	}
	
	public List<ChallengeTeam> getChallengeTeames(long groupId, 
			long childChallengeId) throws SystemException{
		
		return challengeTeamPersistence.filterFindByGroupIdAndChildChallenge(groupId, childChallengeId);
	}
	
	public List<ChallengeTeam> getChallengeTeames(long groupId, 
			long childChallengeId, int start, int end) throws SystemException{
		
		return challengeTeamPersistence.filterFindByGroupIdAndChildChallenge(groupId, childChallengeId, start, end);
	}
	
	public int getChallengeTeamesCount(long groupId, long childChallengeId) throws SystemException{
		return challengeTeamPersistence
				.filterCountByGroupIdAndChildChallenge(groupId, childChallengeId);
	}
	
	public ChallengeTeam updateChallengeTeam(long userId, long childChallengeId, long challengeTeamId,
			String teamName, Map<Locale, String> subject, Map<Locale, String> paperName,
			Map<Locale, String> paperAbstract, UploadPortletRequest uploadRequest,
			String grade, String phone, ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		
		ChallengeTeamPermission.check(getPermissionChecker(), challengeTeamId, ActionKeys.MANAGE_TEAM);
		
		return ChallengeTeamLocalServiceUtil.updateChallengeTeam(userId, childChallengeId, 
				challengeTeamId, teamName, subject, paperName, paperAbstract, uploadRequest, 
				grade, phone, serviceContext);
	}
	
}