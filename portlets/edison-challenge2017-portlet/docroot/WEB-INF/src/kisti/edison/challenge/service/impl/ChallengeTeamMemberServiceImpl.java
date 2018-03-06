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

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupUtil;

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeTeamMemberServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.service.permission.ChallengeTeamMemberPermission;
import kisti.edison.challenge.service.permission.ChallengeTeamPermission;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the challenge team member remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeTeamMemberService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeTeamMemberServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil
 */
public class ChallengeTeamMemberServiceImpl
	extends ChallengeTeamMemberServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil} to access the challenge team member remote service.
	 */
	
	public ChallengeTeamMember addChallengeTeamMember(long userId, long challengeTeamId, 
			long applyUserId, String grade, String phone, boolean isLeader, ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		System.out.println("Add challenge team member test 1");

			
		if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
			EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		}
		
		ChallengeModelPermission.check(getPermissionChecker(), 
				serviceContext.getScopeGroupId(), ActionKeys.PARTICIPATION_CHALLENGE);
		
		
		//System.out.println("Add challenge team member test 2");
		
		return ChallengeTeamMemberLocalServiceUtil.addChallengeTeamMember(userId, 
				challengeTeamId, applyUserId, grade, phone, isLeader, serviceContext);
	}
	
	public ChallengeTeamMember deleteChallengeTeamMember(long challengeTeamMemberId, 
			ServiceContext serviceContext) 
					throws PrincipalException, PortalException, SystemException{
		
		ChallengeTeamMemberPermission.check(getPermissionChecker(), 
				challengeTeamMemberId, ActionKeys.DELETE);
		
		return ChallengeTeamMemberLocalServiceUtil.deleteChallengeTeamMember(challengeTeamMemberId, 
				serviceContext);
	}
	
	public ChallengeTeamMember updateChallengeTeamMember(long userId, long challengeTeamMemberId, 
			String grade, String phone, boolean isLeader,
			ServiceContext serviceContext)
					throws PrincipalException, PortalException, SystemException{
		ChallengeTeamMemberPermission.check(getPermissionChecker(), 
				serviceContext.getScopeGroupId(), ActionKeys.UPDATE);
		
		return ChallengeTeamMemberLocalServiceUtil.updateChallengeTeamMember(userId, 
				challengeTeamMemberId, grade, phone, isLeader, serviceContext);
	}
}