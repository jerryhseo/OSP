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

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.service.persistence.RoleUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import kisti.edison.challenge.ChallengeDescriptionException;
import kisti.edison.challenge.ChallengeFieldException;
import kisti.edison.challenge.ChallengeNameException;
import kisti.edison.challenge.NoSuchChallengeException;
import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeLocalServiceBaseImpl;
import kisti.edison.challenge.util.ActionKeys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
/**
 * The implementation of the challenge local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeLocalServiceUtil
 */
public class ChallengeLocalServiceImpl extends ChallengeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeLocalServiceUtil} to access the challenge local service.
	 */
	
	public Challenge getChallengeByGroupAndField(long groupId, String field,
			OrderByComparator orderByComparator) throws SystemException,
			NoSuchChallengeException{
		//System.out.println("\n\n\n\n=======================");
		//System.out.println("Field Name : "+field);
		//System.out.println("======================\n\n\n\n");
		field = "%"+field+"%";
		return challengePersistence.findByGroupAndField_First(groupId, field, orderByComparator);
	}
	
	public List<Challenge> getChallenges(long groupId)
		throws SystemException{
		return challengePersistence.findByGroupId(groupId);
	}
	
	public boolean hasManagerRole(long groupId, long userId, long companyId) throws SystemException, PortalException{
		boolean isManager = false;
		
		if(getChallengesByManagerRole(groupId, userId, companyId).size() > 0){
			isManager = true;
		}
		
		return isManager;
	}
	
	public List<Challenge> getChallengesByManagerRole(long groupId, long userId, long companyId) throws SystemException, PortalException{
		List<Challenge> returnChallenges = new ArrayList<Challenge>();
		
		List<Challenge> challenges = challengePersistence.filterFindByGroupAndStatus(groupId, WorkflowConstants.STATUS_APPROVED);
		
		for(Challenge challenge : challenges){
			String roleName = "ChallengeManager_";
			roleName += challenge.getField(new Locale("en", "US"));
			Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
			
			//System.out.println("\n\n\n\nTest challenge manager role1 : " + UserServiceUtil.getUserById(userId).getScreenName() +" ID : " +userId);
			//System.out.println("Test challenge manager role2 : " + role.getName() + " ID : "+role.getRoleId());
			//System.out.println("Test challenge manager role3 : " + UserLocalServiceUtil.hasRoleUser(role.getRoleId(), userId));
			
			if(UserLocalServiceUtil.hasRoleUser(role.getRoleId(), userId)){
				returnChallenges.add(challenge);
				//System.out.println("Test challenge Manager role 4 : add challenge to return list");
				//System.out.println(returnChallenges.toString());
			}
			//System.out.println("Test challenge manager role 5");
		}
		//System.out.println("Test challenge manager role 6  :  Return result : ");
		//System.out.println(returnChallenges.toString());		
		return returnChallenges;
	}
	
	public boolean hasEvaluationRole(long groupId, long userId, long companyId) throws SystemException, PortalException{
		boolean isEvaluator = false;
		
		if(getChallengesByEvaluationRole(groupId, userId, companyId).size() > 0){
			isEvaluator = true;
		}
		
		return isEvaluator;
	}
	
	public List<Challenge> getChallengesByEvaluationRole(long groupId, long userId, long companyId) throws SystemException, PortalException{
		List<Challenge> returnChallenges = new ArrayList<Challenge>();
		List<Challenge> challenges = challengePersistence.filterFindByGroupAndStatus(groupId, WorkflowConstants.STATUS_APPROVED);
		
		for(Challenge challenge : challenges){
			String roleName = "ChallengeEvaluator_";
			roleName += challenge.getField(new Locale("en", "US"));
			Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
			
			if(UserLocalServiceUtil.hasRoleUser(role.getRoleId(), userId)){
				returnChallenges.add(challenge);
			}
		}
		return returnChallenges;
	}
	
	public List<Challenge> getChallenges(long groupId, int status)
		throws SystemException{
		return challengePersistence.findByGroupAndStatus(groupId, WorkflowConstants.STATUS_APPROVED);
	}
	
	public List<Challenge> getChallenges(long groupId, int start, int end)
		throws SystemException{
		return challengePersistence.findByGroupId(groupId, start, end);
	}
	
	public int getChallengesCount(long groupId)
		throws SystemException{
		return challengePersistence.countByGroupId(groupId);
	}
	
	public Challenge addChallenge(long userId, Map<Locale, String> name, 
			Map<Locale, String> field, Map<Locale, String> description, 
			ServiceContext serviceContext)
		throws SystemException, PortalException{
		long groupId = serviceContext.getScopeGroupId();
		
		List<Challenge> test = challengePersistence.findByGroupAndField(groupId, field.toString());
		
		if(test.size() > 0){
			throw new PortalException("existing challenge");
		}
		
		User user = userPersistence.findByPrimaryKey(userId);
		
		Date now = new Date();
		
		
		long challengeId = counterLocalService.increment();
		
		Challenge challenge = challengePersistence.create(challengeId);
		
		challenge.setUuid(serviceContext.getUuid());
		challenge.setUserId(userId);
		challenge.setGroupId(groupId);
		challenge.setCompanyId(user.getCompanyId());
		challenge.setCreateDate(serviceContext.getCreateDate(now));
		challenge.setModifiedDate(serviceContext.getCreateDate(now));
		challenge.setNameMap(name);
		challenge.setFieldMap(field);
		challenge.setDescriptionMap(description);
		challenge.setStatus(WorkflowConstants.STATUS_DRAFT);
		
		challengePersistence.update(challenge);
		
		resourceLocalService.addResources(user.getCompanyId(), groupId, userId, 
				Challenge.class.getName(), challengeId, false, true, true);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, 
				groupId, challenge.getCreateDate(), challenge.getModifiedDate(),
				Challenge.class.getName(), challengeId, challenge.getUserUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, challenge.getField(), null, null, null,
				null, 0, 0, null, false);
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), 
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Challenge.class);
		
		indexer.reindex(challenge);
		
		WorkflowHandlerRegistryUtil.startWorkflowInstance(
				challenge.getCompanyId(), challenge.getGroupId(), 
				challenge.getUserId(), Challenge.class.getName(), 
				challenge.getPrimaryKey(), challenge, serviceContext);
		
		//role create on automatically
		// 1. role for management of each center during when the challenge is running
		String centerManagerRoleName = "ChallengeManager_"+challenge.getField(new Locale("en", "US"));
		Role managerRole = RoleLocalServiceUtil.addRole(userId, serviceContext.getCompanyId(), centerManagerRoleName, null, null, RoleConstants.TYPE_REGULAR);
		//ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, name, scope, primKey, roleId, actionIds);
		//ResourcePermissionLocalServiceUtil.setResourcePermissions(serviceContext.getCompanyId(),
		//		ChallengeTeam.class.getName(), serviceContext.getScopeGroup(), String.valueOf(managerRole.getRoleId()), managerRole.getRoleId(), 
		//		new String[]{ActionKeys.MANAGE_TEAM, ActionKeys.VIEW, ActionKeys.UPDATE, ActionKeys.DELETE});
		
		// 2. evaluation role for each center;
		String evaluationManagerRoleName = "ChallengeEvaluator_"+challenge.getField(new Locale("en", "US"));
		Role evaluatorRole = RoleLocalServiceUtil.addRole(userId, serviceContext.getCompanyId(), evaluationManagerRoleName, null, null, RoleConstants.TYPE_REGULAR);
		
		
		return challenge;
		
	}
	
	public Challenge updateChallenge(long userId, long challengeId, Map<Locale, String> name, 
			Map<Locale, String> field, Map<Locale, String> description, ServiceContext serviceContext)
		throws SystemException, PortalException{
		Date now = new Date();
		//validate(name, field, description);
		System.out.println("challenge ID : "+ challengeId);
		Challenge challenge = getChallenge(challengeId);
		
		User user = UserLocalServiceUtil.getUser(userId);
		//System.out.println("Test 1 challenge update");
		challenge.setUserId(userId);
		challenge.setModifiedDate(serviceContext.getModifiedDate(now));
		challenge.setNameMap(name);
		challenge.setFieldMap(field);
		challenge.setDescriptionMap(description);
		challenge.setExpandoBridgeAttributes(serviceContext);
		
		//System.out.println("Test 2 challenge update");
		challengePersistence.update(challenge);
		
		//System.out.println("Test 3 challenge update");
		resourceLocalService.updateResources(
				serviceContext.getCompanyId(), 
				serviceContext.getScopeGroupId(),
				field.toString(), challengeId,
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(challenge.getUserId(),
				challenge.getGroupId(), challenge.getCreateDate(),
				challenge.getModifiedDate(), Challenge.class.getName(),
				challengeId, challenge.getUserUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, challenge.getName(), null, null, null,
				null, 0, 0, null, false);
		//System.out.println("Test 4 challenge update");
		assetLinkLocalService.updateLinks(serviceContext.getUserId(), 
				assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Challenge.class);
		
		indexer.reindex(challenge);
		
		//System.out.println("Test 5 challenge update");
		return challenge;
	
	}
	
	public Challenge deleteChallenge(long challengeId, 
			ServiceContext serviceContext) throws PortalException, SystemException {
		Challenge challenge = getChallenge(challengeId);
		
		List<ChildChallenge> childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(serviceContext.getScopeGroupId(), challengeId);
		
		for(ChildChallenge childChallenge : childChallenges){
			ChildChallengeLocalServiceUtil
				.deleteChildChallenge(childChallenge.getChildChallengeId(), serviceContext);
		}
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(), 
				Challenge.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, challengeId);
		
		
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				Challenge.class.getName(), challengeId);
		
		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		
		assetEntryLocalService.deleteEntry(assetEntry);
		
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Challenge.class);
		
		indexer.delete(challenge);
		
		challenge = deleteChallenge(challengeId);
		
		//role delete on automatically
		// 1. role for management of each center during when the challenge is running
		String centerManagerRoleName = "ChallengeManager_"+challenge.getField(new Locale("en", "US"));
		Role managerRole = RoleLocalServiceUtil.getRole(serviceContext.getCompanyId(), centerManagerRoleName);
		RoleLocalServiceUtil.deleteRole(managerRole);
		// 2. evaluation role for each center;
		String evaluationManagerRoleName = "ChallengeEvaluator_"+challenge.getField(new Locale("en", "US"));
		Role evaluatorRole = RoleLocalServiceUtil.getRole(serviceContext.getCompanyId(), evaluationManagerRoleName);
		RoleLocalServiceUtil.deleteRole(evaluatorRole);		
		
		
		
		return challenge;
	}
	
	protected void validate(Map<Locale, String> name, Map<Locale, String> field, Map<Locale, String> description)
			throws PortalException{
		if(Validator.isNull(name.get(LanguageUtil.getLocale("en"))) || Validator.isNull(name.get(LanguageUtil.getLocale("ko")))){
			throw new ChallengeNameException();
		}
		if(Validator.isNull(field.get(LanguageUtil.getLocale("en"))) || Validator.isNull(field.get(LanguageUtil.getLocale("ko")))){
			System.out.println("field is null" + ":" + field);
			throw new ChallengeFieldException();
		}
		if(Validator.isNull(description.get(LanguageUtil.getLocale("en"))) || Validator.isNull(description.get(LanguageUtil.getLocale("ko")))){
			System.out.println("descrption is null" + ":" + description);
			throw new ChallengeDescriptionException();
		}
	}
	
	public Challenge updateStatus(long userId, long challengeId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {
		User user = userLocalService.getUser(userId);
		Challenge challenge = getChallenge(challengeId);
		challenge.setStatus(status);
		challenge.setStatusByUserId(userId);
		challenge.setStatusByUserName(user.getFullName());
		challenge.setStatusDate(new Date());
		
		challengePersistence.update(challenge);
		
		if (status == WorkflowConstants.STATUS_APPROVED) {
			assetEntryLocalService.updateVisible(Challenge.class.getName(), challengeId, true);
		}else{
			assetEntryLocalService.updateVisible(Challenge.class.getName(), challengeId, false);
		}
		
		return challenge;
	}
}