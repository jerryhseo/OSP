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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import kisti.edison.challenge.NoSuchChallengeException;
import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.service.permission.ChallengePermission;
import kisti.edison.challenge.service.persistence.ChallengePersistence;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the challenge remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeServiceUtil
 */
public class ChallengeServiceImpl extends ChallengeServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeServiceUtil} to access the challenge remote service.
	 */
	public Challenge addChallenge(long userId,  Map<Locale, String> name, 
			Map<Locale, String> field, Map<Locale, String> description,
			ServiceContext serviceContext)
		throws SystemException, PortalException{
		ChallengeModelPermission.check(getPermissionChecker(), serviceContext.getScopeGroupId(), 
				ActionKeys.ADD_CHALLENGE);
		
		return ChallengeLocalServiceUtil.addChallenge(userId, name, field, description, serviceContext);
	}
	
	public Challenge deleteChallenge(long challengeId, ServiceContext serviceContext)
		throws PortalException, SystemException{
		ChallengePermission.check(getPermissionChecker(), 
				challengeId, ActionKeys.DELETE);
		
		return ChallengeLocalServiceUtil.deleteChallenge(challengeId, serviceContext);
	}
	
	public List<Challenge> getChallenges(long groupId)
		throws SystemException{
		return challengePersistence.filterFindByGroupId(groupId);
	}
	
	public List<Challenge> getChallenges(long groupId, int start, int end)
			throws SystemException{
			return challengePersistence.filterFindByGroupId(groupId, start, end);
	}
	
	public Challenge getChallengeGroupAndField(long groupId, String field, 
			OrderByComparator orderByComparator)
		throws SystemException, NoSuchChallengeException{
		return ChallengeLocalServiceUtil.getChallengeByGroupAndField(groupId, field, orderByComparator);
	}
	
	public Challenge updateChallenge(long userId, long challengeId, Map<Locale, String> name, 
			Map<Locale, String> field, Map<Locale, String> description,
			ServiceContext serviceContext)  
		throws PortalException, SystemException {
		ChallengePermission.check(getPermissionChecker(), 
				challengeId, ActionKeys.UPDATE);
		System.out.println("test challenge service after permission");
		return ChallengeLocalServiceUtil.updateChallenge(userId, 
				challengeId, name, field, description, serviceContext);
	}
}