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

import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeTeamMemberLocalServiceBaseImpl;
import kisti.edison.challenge.util.ActionKeys;

import java.util.Date;
import java.util.List;

import org.kisti.edison.model.EdisonExpando;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import kisti.edison.challenge.NoSuchChallengeTeamMemberException;
import kisti.edison.challenge.model.ChallengeTeamMember;

/**
 * The implementation of the challenge team member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeTeamMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeTeamMemberLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil
 */
public class ChallengeTeamMemberLocalServiceImpl
	extends ChallengeTeamMemberLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil} to access the challenge team member local service.
	 */
	public List<ChallengeTeamMember> getChallengeTeamMembers(long groupId) 
			throws SystemException{
		return challengeTeamMemberPersistence.findByGroupId(groupId);
	}
	
	public List<ChallengeTeamMember> getChallengeTeamMembers(long groupId, int status) 
			throws SystemException{
		return challengeTeamMemberPersistence.findByGroupIdAndStatus(groupId, status);
	}
	
	public List<ChallengeTeamMember> getChallengeTeamMembers(long groupId, long challengeTeamId) 
			throws SystemException{
		return challengeTeamMemberPersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public List<ChallengeTeamMember> getChallengeTeamMembers(long groupId, long challengeTeamId, 
			int start, int end) throws SystemException{
		return challengeTeamMemberPersistence.findByGroupIdAndChallengeTeamId(groupId, 
				challengeTeamId, start, end);
	}
	
	public int getChallengeTeamMemberCount(long groupId, long challengeTeamId) throws SystemException{
		return challengeTeamMemberPersistence.countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public int getCountChallengeTeamMembers(long groupId, long challengeTeamId) 
			throws SystemException{
		return challengeTeamMemberPersistence.countByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public ChallengeTeamMember getChallengeTeamMember(long groupId, long challengeTeamId, long applyUserId){
		ChallengeTeamMember challengeTeamMember = null;
		try {
			challengeTeamMember = challengeTeamMemberPersistence.findByGroupIdAndApplyUserIdAndChallengeTeamId(groupId, applyUserId, challengeTeamId);
			return challengeTeamMember;
		}catch (NoSuchChallengeTeamMemberException e){
			return null;
		}catch (SystemException e){
			return null;
		}
	}
	
	public ChallengeTeamMember addChallengeTeamMember(long userId, long challengeTeamId,
			long applyUserId, String grade, String phone, boolean isLeader,
			ServiceContext serviceContext) throws PortalException, SystemException {
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		//System.out.println("Challenge team member : test 1111");
		//validate();
		
		
		long challengeTeamMemberId = counterLocalService.increment();
		
		ChallengeTeamMember challengeTeamMember = challengeTeamMemberPersistence.create(challengeTeamMemberId);
		
		//System.out.println("Challenge team member : test 2222");
		
		challengeTeamMember.setUuid(serviceContext.getUuid());
		challengeTeamMember.setUserId(userId);
		challengeTeamMember.setGroupId(groupId);
		challengeTeamMember.setUserName(user.getFullName());
		challengeTeamMember.setCreateDate(now);
		challengeTeamMember.setModifiedDate(now);
		challengeTeamMember.setExpandoBridgeAttributes(serviceContext);
		challengeTeamMember.setChallengeTeamId(challengeTeamId);
		
		/////////////////////////////////////////////////////////////////////
		User applyUser = userPersistence.findByPrimaryKey(applyUserId);
		String userUniversityCode = applyUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
		long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
		ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
		//System.out.println("Challenge team member : test 3333");
		
		challengeTeamMember.setApplyUserId(applyUserId);
		challengeTeamMember.setApplyUserName(applyUser.getFullName());
		challengeTeamMember.setInstituteMap(universityValue.getStringMap());
		challengeTeamMember.setMajor(applyUser.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR).toString());
		challengeTeamMember.setGrade(grade);
		challengeTeamMember.setEmail(applyUser.getEmailAddress());
		challengeTeamMember.setPhone(phone);
		challengeTeamMember.setLeader(isLeader);
		
		challengeTeamMember = challengeTeamMemberPersistence.update(challengeTeamMember);
		
		//System.out.println("Challenge team member : Phone : " + challengeTeamMember.getPhone());
		//System.out.println("Challenge team member : Grade : " + grade);
		//System.out.println("Challenge team member : test 4444");
		//System.out.println(challengeTeamMember.toString());
		//System.out.println("testtestest : "+challengeTeamMemberId + ": testest : "+challengeTeamMember.getPrimaryKey());
		resourceLocalService.addResources(serviceContext.getCompanyId(), groupId, 
				userId, ChallengeTeamMember.class.getName(), challengeTeamMemberId, false, true, true);
		
		//System.out.println("Challenge team member : test end resoucelocalservice");
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, challengeTeamMember.getCreateDate(), 
				challengeTeamMember.getModifiedDate(), 
				ChallengeTeamMember.class.getName(), 
				challengeTeamMemberId, challengeTeamMember.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, challengeTeamMember.getApplyUserName(), 
				null, null, null, null, 0, 0, null, false);
		//System.out.println("Challenge team member : test 5555");
		assetLinkLocalService.updateLinks(userId,  assetEntry.getEntryId(), 
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		//System.out.println("Challenge team member : test 6666");
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeamMember.class);
		indexer.reindex(challengeTeamMember);
		//System.out.println("Challenge team member : test 7777");
		WorkflowHandlerRegistryUtil.startWorkflowInstance(challengeTeamMember.getCompanyId(), 
				challengeTeamMember.getGroupId(), challengeTeamMember.getUserId(), 
				ChallengeTeamMember.class.getName(), challengeTeamMember.getPrimaryKey(), 
				challengeTeamMember, serviceContext);
		
		//System.out.println("Challenge team member : test 8888");
		//set permission about team
		//applyUser
		//Role applyUserRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
		//RoleLocalServiceUtil.get
		//PermissionLocalServiceUtil
		//ResourcePermissionLocalServiceUtil.addResourcePermission(companyId, "kisti.edison.challenge.model.ChallengeTeam", groupId, challengeTeamMember.getChallengeTeamId(), applyUserRole.getPrimaryKey(), ActionKeys.PARTICIPATION_CHALLENGE);
		return challengeTeamMember;
	}
	
	public ChallengeTeamMember updateChallengeTeamMember(long userId, long challengeTeamMemberId,
			String grade, String phone, boolean isLeader, ServiceContext serviceContext) 
					throws SystemException, PortalException{
		long groupId = serviceContext.getScopeGroupId();
		
		ChallengeTeamMember challengeTeamMember = challengeTeamMemberPersistence.findByPrimaryKey(challengeTeamMemberId);
		
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate
		
		challengeTeamMember.setUserId(userId);
		challengeTeamMember.setUserName(user.getFullName());
		challengeTeamMember.setModifiedDate(now);
		challengeTeamMember.setExpandoBridgeAttributes(serviceContext);
		
		challengeTeamMember.setGrade(grade);
		challengeTeamMember.setPhone(phone);
		challengeTeamMember.setLeader(isLeader);
		
		challengeTeamMemberPersistence.update(challengeTeamMember);
		
		resourceLocalService.updateResources(user.getCompanyId(),
				groupId, ChallengeTeamMember.class.getName(),
				challengeTeamMemberId,
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, 
				groupId, challengeTeamMember.getCreateDate(), challengeTeamMember.getModifiedDate(),
				ChallengeTeamMember.class.getName(), challengeTeamMemberId, challengeTeamMember.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, challengeTeamMember.getApplyUserName(),
				null, null, null, null, 0 ,0, null, false);
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeamMember.class);
		indexer.reindex(challengeTeamMember);
		
		return challengeTeamMember;
	}
	public ChallengeTeamMember updateStatus(long userId, long challengeTeamMemberId, 
			int status, ServiceContext serviceContext) 
			throws PortalException, SystemException{
		User user = userLocalService.getUser(userId);
		ChallengeTeamMember challengeTeamMember = getChallengeTeamMember(challengeTeamMemberId);
		
		challengeTeamMember.setStatus(status);
		challengeTeamMember.setStatusByUserId(userId);
		challengeTeamMember.setStatusByUserName(user.getFullName());
		challengeTeamMember.setStatusDate(new Date());
		
		challengeTeamMemberPersistence.update(challengeTeamMember);
		
		if(status == WorkflowConstants.STATUS_APPROVED){
			assetEntryLocalService.updateVisible(ChallengeTeamMember.class.getName(), 
					challengeTeamMemberId, true);
		}else{
			assetEntryLocalService.updateVisible(ChallengeTeamMember.class.getName(), 
					challengeTeamMemberId, false);
		}
		
		return challengeTeamMember;
	}
	
	public ChallengeTeamMember deleteChallengeTeamMember(long challengeTeamMemberId, 
			ServiceContext serviceContext) throws PortalException, SystemException{
		ChallengeTeamMember challengeTeamMember = getChallengeTeamMember(challengeTeamMemberId);	
			resourceLocalService.deleteResource(serviceContext.getCompanyId(), 
					ChallengeTeamMember.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, challengeTeamMemberId);
			
			AssetEntry assetEntry = assetEntryLocalService.
					fetchEntry(ChallengeTeamMember.class.getName(), challengeTeamMemberId);
			
			assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
			assetEntryLocalService.deleteEntry(assetEntry);
			
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeamMember.class);
			
			indexer.delete(challengeTeamMember);
			challengeTeamMember = deleteChallengeTeamMember(challengeTeamMemberId);
			
			return challengeTeamMember;

	}
	
	public ChallengeTeamMember getChallengeTeamLeaderMember(long challengeTeamId, 
			long groupId) throws SystemException{
		List<ChallengeTeamMember> memberList = challengeTeamMemberPersistence
				.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		
		if(memberList.isEmpty()){
			return null;
		}else{
			for(ChallengeTeamMember challengeTeamMember : memberList){
				if(challengeTeamMember.getLeader()){
					return challengeTeamMember;
				}
			}
		}
		return null;
	}
}