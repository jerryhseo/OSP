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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.RequestUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.base.ChallengeMemberLocalServiceBaseImpl;

/**
 * The implementation of the challenge member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link edison.challenge.service.builder.service.ChallengeMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.base.ChallengeMemberLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil
 */
public class ChallengeMemberLocalServiceImpl
	extends ChallengeMemberLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil} to access the challenge member local service.
	 */
	
	private static final long managercompanyID = 20154;
	public List<ChallengeMember> getChallengeMemberByTeam(long temaID) throws SystemException{
		return this.challengeMemberPersistence.findByteamCollect(temaID);
	}
	
	public void addChallengeTeamMember(ActionRequest actionRequest, ActionResponse actionResponse, boolean isLeader, long chteamID) throws PortalException, SystemException{
		//long userID = ParamUtil.getLong(actionRequest, "userInfo");
		System.out.println("\n\n\n\n\n\n\n\n===========================");
		System.out.println("Team member adding");
		
		String userScreenName = ParamUtil.getString(actionRequest, "userScreenName");
		System.out.println(" ID : "+userScreenName);
		User applyUser = UserLocalServiceUtil.fetchUserByScreenName(managercompanyID, userScreenName);
		
		String userUniversityCode = applyUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
		long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
		ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
		
		ChallengeMember challengeMember = ChallengeMemberLocalServiceUtil.createChallengeMember(CounterLocalServiceUtil.increment());
		challengeMember.setFullName(applyUser.getFullName());
		System.out.println("Name : " + applyUser.getFullName());
		challengeMember.setScreenName(applyUser.getScreenName());
		challengeMember.setEmail(applyUser.getEmailAddress());
		System.out.println(" E-mail : "+challengeMember.getEmail());
		challengeMember.setLeader(isLeader);
		if(challengeMember.isLeader())
			System.out.println("Current member is Leader.");
		challengeMember.setFalcultyMap(universityValue.getStringMap());
		challengeMember.setMajor(applyUser.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR).toString());
		challengeMember.setGrade(ParamUtil.getString(actionRequest, "inputUserGrade"));
		challengeMember.setChTeamid(chteamID);
		ChallengeMemberLocalServiceUtil.updateChallengeMember(challengeMember);
		
	}
	
	//Challenge Team list that search by challengeMemeber ScreenName
	public List<ChallengeTeam> getChallengeTeamListByMember(String screenName) throws SystemException, PortalException{
		List<ChallengeMember> memberList = this.challengeMemberPersistence.findAll();
		List<ChallengeTeam> teamList = new ArrayList<ChallengeTeam>();
		for(ChallengeMember member:memberList){
			if(member.getScreenName().equals(screenName))
				teamList.add(ChallengeTeamLocalServiceUtil.getChallengeTeam(member.getChTeamid()));
		}
		return teamList;
	}
	
	public List<ChallengeMember> getChallengeTeamMemberByTeamID(long teamID) throws SystemException{
		return this.challengeMemberPersistence.findByteamCollect(teamID);
	}
	
	//Getting memberID method that search by team id and user's screenName
	public long getMemberIDbyScreenName(long teamID, String screenName) throws SystemException{
		List<ChallengeMember> chMemberList = ChallengeMemberLocalServiceUtil.getChallengeTeamMemberByTeamID(teamID);
		for(ChallengeMember member:chMemberList)
			if(member.getScreenName().equals(screenName))
				return member.getPrimaryKey();
		return 0;
	}
	
	public long getTeamLeaderToMemberID(long teamID) throws SystemException{
		List<ChallengeMember> memberList = this.challengeMemberPersistence.findByteamCollect(teamID);
		for(ChallengeMember member:memberList){
			if(member.getLeader())
				return member.getPrimaryKey();
		}
		
		
		return 0;
	}
	
	public int countOfTeamMember(long teamID) throws SystemException{
		int memberCount =0;
		memberCount = ChallengeMemberLocalServiceUtil.getChallengeTeamMemberByTeamID(teamID).size();
				
		return memberCount;
	}
	
	public ChallengeMember getLeaderMemeber(long teamID) throws SystemException{
		List<ChallengeMember> memberList = this.challengeMemberPersistence.findByteamCollect(teamID);
		for(ChallengeMember member : memberList){
			if(member.getLeader())
				return member;
		}
		return null;
	}
	
	public void deleteTeamMemberByService(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long teamID = ParamUtil.getLong(actionRequest, "chTeamID");
		long memberID = ParamUtil.getLong(actionRequest, "memberID");
		
		System.out.println("\n\n\n\n\n\n\n\n=======================");
		if(ChallengeMemberLocalServiceUtil.getChallengeMember(memberID).getLeader())
		{
			System.out.println("delete team member is leader ");
			List<ChallengeMember> teamMemberList = ChallengeMemberLocalServiceUtil.getChallengeMemberByTeam(teamID);
			for(ChallengeMember member : teamMemberList){
				System.out.println("delete member id : "+member.getScreenName());
				ChallengeMemberLocalServiceUtil.deleteChallengeMember(member);
			}
			System.out.println("delete team id : " + teamID);
			ChallengeTeamLocalServiceUtil.deleteChallengeTeam(teamID);
		}
		else{
			System.out.println("delete team member is just member");
			System.out.println("delete member id : "+ChallengeMemberLocalServiceUtil.getChallengeMember(memberID).getScreenName());
			ChallengeMemberLocalServiceUtil.deleteChallengeMember(memberID);
		}
		
		System.out.println("=======================\n\n\n\n\n\n\n\n");
	}
	
	public void deleteMemberByTeam(long teamID) throws SystemException{
		List<ChallengeMember> chMemberList = ChallengeMemberLocalServiceUtil.getChallengeTeamMemberByTeamID(teamID);
		for(ChallengeMember member : chMemberList ){
			ChallengeMemberLocalServiceUtil.deleteChallengeMember(member);
		}
	}
}