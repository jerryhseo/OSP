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

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.base.ChildChallengeServiceBaseImpl;
import kisti.edison.challenge.service.permission.ChallengeModelPermission;
import kisti.edison.challenge.service.permission.ChildChallengePermission;
import kisti.edison.challenge.util.ActionKeys;

/**
 * The implementation of the child challenge remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChildChallengeService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChildChallengeServiceBaseImpl
 * @see kisti.edison.challenge.service.ChildChallengeServiceUtil
 */
public class ChildChallengeServiceImpl extends ChildChallengeServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChildChallengeServiceUtil} to access the child challenge remote service.
	 */
	
	public ChildChallenge addChildChallenge(long userId, long challengeId, 
			int number, Date presentationDay, 
			Date paperStartDay, Date paperEndDay, Date evaluationDay, 
			Date challengeStartDay, Date challengeEndDay,
			String challengeStatus, ServiceContext serviceContext)
		throws PortalException, SystemException {
		
		ChallengeModelPermission.check(getPermissionChecker(), 
				serviceContext.getScopeGroupId(), ActionKeys.ADD_CHILD_CHALLENGE);
		
		return ChildChallengeLocalServiceUtil.addChildChallenge(userId, 
				challengeId, number, presentationDay, paperStartDay, 
				paperEndDay, evaluationDay, challengeStartDay, 
				challengeEndDay, challengeStatus, serviceContext);
	}
	
	public ChildChallenge deleteChildChallenge(long childChallengeId, 
			ServiceContext serviceContext)
		throws PortalException, SystemException {
		ChildChallengePermission.check(getPermissionChecker(), 
				childChallengeId, ActionKeys.DELETE);
		
		return ChildChallengeLocalServiceUtil.deleteChildChallenge(childChallengeId, serviceContext);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId)
		throws SystemException {
		return childChallengePersistence.filterFindByGroupIdAndChallenge(groupId, challengeId);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId, 
		int start, int end) throws SystemException {
		return childChallengePersistence.filterFindByGroupIdAndChallenge(groupId, challengeId, start, end);
	}
	
	public int getChildChallengesCount(long groupId, long challengeId) 
		throws SystemException {
		return childChallengePersistence.filterCountByGroupIdAndChallenge(groupId, challengeId);
	}
	
	public List<ChildChallenge> getChildChallengesByStatus(long groupId, String challengeStatus) 
			throws SystemException{
		return childChallengePersistence.filterFindByGroupAndChallengeStatus(groupId, challengeStatus);
	}
	
	public ChildChallenge updateChildChallenge(long userId, long challengeId, 
			long childChallengeId, int number, Date presentationDay,
			Date paperStartDay, Date paperEndDay,
			Date evaluationDay, Date challengeStartDay,
			Date challengeEndDay, String challengeStatus, 
			ServiceContext serviceContext)
		throws PortalException, SystemException{
		ChildChallengePermission.check(getPermissionChecker(), 
				childChallengeId, ActionKeys.UPDATE);
		
		return ChildChallengeLocalServiceUtil.updateChildChallenge(userId, 
				challengeId, childChallengeId, number, presentationDay, 
				paperStartDay, paperEndDay, evaluationDay, 
				challengeStartDay, challengeEndDay, 
				challengeStatus, serviceContext);
	}
}