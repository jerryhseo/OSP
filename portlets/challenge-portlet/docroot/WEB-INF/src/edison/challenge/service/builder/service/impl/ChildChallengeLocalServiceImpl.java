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

package edison.challenge.service.builder.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edison.challenge.service.builder.model.Agency;
import edison.challenge.service.builder.model.Award;
import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.model.ChildChallenge;
import edison.challenge.service.builder.service.AgencyLocalServiceUtil;
import edison.challenge.service.builder.service.AwardLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeServiceUtil;
import edison.challenge.service.builder.service.base.ChildChallengeLocalServiceBaseImpl;
import edison.challenge.service.builder.service.persistence.ChildChallengePersistence;
import edison.challenge.service.builder.service.persistence.ChildChallengePersistenceImpl;

/**
 * The implementation of the child challenge local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link edison.challenge.service.builder.service.ChildChallengeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.base.ChildChallengeLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil
 */
public class ChildChallengeLocalServiceImpl
	extends ChildChallengeLocalServiceBaseImpl {
	private List<ChildChallenge> runningChild;
	private int runningChildCount;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil} to access the child challenge local service.
	 */
	public List<ChildChallenge> getChildByChallenge(long challengeid) throws SystemException{
		return this.childChallengePersistence.findBychallengeCollect(challengeid);
	}
	
	public int getChildByChallengeCount(long challengeid) throws SystemException{
		return childChallengePersistence.countBychallengeCollect(challengeid);
	}
	
	public void deleteChildChallengeAllCoverage(long childID) throws SystemException, PortalException{
		System.out.println("test3");
		List<Award> awardList = AwardLocalServiceUtil.getAwardByChildCollet(childID);
		for(Award award:awardList){
			System.out.println(award.toString());
			AwardLocalServiceUtil.deleteAward(award);
		}
		System.out.println("test4");
		List<Agency> agencyList = AgencyLocalServiceUtil.getAgencyByChild(childID);
		for(Agency agency:agencyList){
			System.out.println(agency.toString());
			AgencyLocalServiceUtil.deleteAgency(agency);
		}
		ChildChallengeLocalServiceUtil.deleteChildChallenge(childID);
	}
	
	public List<ChildChallenge> getRunningChild() throws SystemException{
		runningChild = null;
		runningChild = new ArrayList<ChildChallenge>();
		List<ChildChallenge> fullChild = null;
		fullChild = ChildChallengeLocalServiceUtil.getChildChallenges(0, ChildChallengeLocalServiceUtil.getChildChallengesCount());
		
		for(ChildChallenge child:fullChild){
			if(child.getStatus().equals("Running"))
				runningChild.add(child);
		}
		return runningChild;
	}
	
	public int getRunningChildCount() throws SystemException{
		runningChildCount = 0;
		runningChildCount = ChildChallengeLocalServiceUtil.getRunningChild().size();
		return runningChildCount;
	}
	
	public boolean isMemeberofTeam(long userID, long childID) throws PortalException, SystemException{
		List<ChallengeTeam> teamList = ChallengeTeamLocalServiceUtil.getChallengeTeamByChild(childID);
		for(ChallengeTeam team: teamList){
			List<ChallengeMember> memberList = ChallengeMemberLocalServiceUtil.getChallengeMemberByTeam(team.getPrimaryKey());
			for(ChallengeMember member : memberList){
				if(member.getPrimaryKey()==userID)
					return true;
			}
		}
		return false;
	}
}