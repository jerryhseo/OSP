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
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import kisti.edison.challenge.ChildChallengeStatusException;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.base.ChildChallengeLocalServiceBaseImpl;

/**
 * The implementation of the child challenge local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChildChallengeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChildChallengeLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChildChallengeLocalServiceUtil
 */
public class ChildChallengeLocalServiceImpl
	extends ChildChallengeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChildChallengeLocalServiceUtil} to access the child challenge local service.
	 */
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId)
		throws SystemException{
		return childChallengePersistence.findByGroupIdAndChallenge(groupId, challengeId);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId, int status, int start, int end)
		throws SystemException{
		//GroupIdAndChallengeAndStatus
		//return childChallengePersistence.findByGroupIdAndChallenge(groupId, challengeId, WorkflowConstants.STATUS_APPROVED, start, end);
		return childChallengePersistence.findByGroupIdAndChallengeAndStatus(groupId, challengeId, WorkflowConstants.STATUS_APPROVED, start, end);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId, String challengeStatus)
		throws SystemException{
		return childChallengePersistence.findByGroupIdAndChallengeAndChallengeStatus(groupId, challengeId, challengeStatus);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, String challengeStatus)
		throws SystemException{
		return childChallengePersistence.findByGroupAndChallengeStatus(groupId, challengeStatus);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, int number)
		throws SystemException{
		return childChallengePersistence.findByGroupIdAndNumber(groupId, number);
	}
	
	public List<ChildChallenge> getChildChallenges(long groupId, long challengeId, int status)
		throws SystemException{
		return childChallengePersistence.findByGroupIdAndChallengeAndStatus(groupId, challengeId, WorkflowConstants.STATUS_APPROVED);
	}
	
	public int getChildChallengesCount(long groupId, long challengeId, int status)
		throws SystemException{
		return childChallengePersistence.countByGroupIdAndChallengeAndStatus(groupId, challengeId, WorkflowConstants.STATUS_APPROVED);
	}
	
	public ChildChallenge deleteChildChallenge(long childChallengeId, 
			ServiceContext serviceContext)
		throws PortalException, SystemException{
		ChildChallenge childChallenge = getChildChallenge(childChallengeId);
		
		List<ChallengeTeam> challengeTeamList = ChallengeTeamLocalServiceUtil
				.getChallengeTeams(serviceContext.getScopeGroupId(), childChallengeId);
		
		for(ChallengeTeam challengeTeam : challengeTeamList){
			ChallengeTeamLocalServiceUtil.deleteChallengeTeam(challengeTeam.getChallengeTeamId(), serviceContext);
		}
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(), 
				ChildChallenge.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
				childChallengeId);
		
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				ChildChallenge.class.getName(), childChallengeId);
		
		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		
		assetEntryLocalService.deleteEntry(assetEntry);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChildChallenge.class);
		
		indexer.delete(childChallenge);
		
		childChallenge = deleteChildChallenge(childChallengeId);
		
		return childChallenge;
	}
	
	public ChildChallenge addChildChallenge(long userId, long challengeId, 
			int number, Date presentationDay, Date paperStartDay,
			Date paperEndDay, Date evaluationDay, Date challengeStartDay,
			Date challengeEndDay, String challengeStatus,
			ServiceContext serviceContext)
			throws PortalException, SystemException {
		long groupId = serviceContext.getScopeGroupId();
		
		User user = userPersistence.findByPrimaryKey(userId);
		
		Date now = new Date();
		
		long childChallengeId = counterLocalService.increment();
		ChildChallenge childChallenge = childChallengePersistence.create(childChallengeId);

		validate(challengeStatus);
		
		childChallenge.setUuid(serviceContext.getUuid());
		childChallenge.setUserId(userId);
		childChallenge.setGroupId(groupId);
		childChallenge.setCompanyId(user.getCompanyId());
		childChallenge.setUserName(user.getFullName());
		childChallenge.setCreateDate(serviceContext.getCreateDate(now));
		childChallenge.setModifiedDate(serviceContext.getModifiedDate(now));
		childChallenge.setExpandoBridgeAttributes(serviceContext);
		childChallenge.setChallengeId(challengeId);
		childChallenge.setStatus(WorkflowConstants.STATUS_DRAFT);
		
		childChallenge.setNumber(number);
		childChallenge.setPresentationDay(presentationDay);
		childChallenge.setPaperStartDay(paperStartDay);
		childChallenge.setPaperEndDay(paperEndDay);
		childChallenge.setEvaluationDay(evaluationDay);
		childChallenge.setChallengeStartDay(challengeStartDay);
		childChallenge.setChallengeEndDay(challengeEndDay);
		childChallenge.setChallengeStatus(challengeStatus);
		
		childChallenge = childChallengePersistence.update(childChallenge);
		

		System.out.println("\n\n\n\n\n\n\n\n===============================");
		System.out.println(" child challenge 1");
		System.out.println(childChallenge.toString());
		System.out.println("childChallengeId : " +childChallengeId);
		System.out.println("childChallenge.getPrimaryKey() : " +childChallenge.getPrimaryKey());
		System.out.println("childChallenge.getChildChallengeId() : " +childChallenge.getChildChallengeId());
		System.out.println("============================================\n\n\n\n\n\n\n\n");
		 
		
		
		resourceLocalService.addResources(user.getCompanyId(), 
				groupId, userId, ChildChallenge.class.getName(), 
				childChallengeId, false, true, true);
		
		System.out.println("\n\n\n\n\n\n\n\n===============================");
		System.out.println(" child challenge 2");
		System.out.println(childChallenge.toString());
		System.out.println("============================================\n\n\n\n\n\n\n\n");
		
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
				userId, groupId, childChallenge.getCreateDate(), childChallenge.getModifiedDate(),
				ChildChallenge.class.getName(), childChallengeId, childChallenge.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, childChallenge.getChallengeStatus(), 
				null, null, null, null, 0, 0, null, false);
		
		System.out.println("\n\n\n\n\n\n\n\n===============================");
		System.out.println(" child challenge 3");
		System.out.println(childChallenge.toString());
		System.out.println("============================================\n\n\n\n\n\n\n\n");
		
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), 
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		System.out.println("\n\n\n\n\n\n\n\n===============================");
		System.out.println(" child challenge 4");
		System.out.println(childChallenge.toString());
		System.out.println("============================================\n\n\n\n\n\n\n\n");
		
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChildChallenge.class);
		
		indexer.reindex(childChallenge);
		
		WorkflowHandlerRegistryUtil.startWorkflowInstance(childChallenge.getCompanyId(), 
				childChallenge.getGroupId(), childChallenge.getUserId(), 
				ChildChallenge.class.getName(), childChallenge.getPrimaryKey(), 
				childChallenge, serviceContext);
		
		System.out.println("\n\n\n\n\n\n\n\n===============================");
		System.out.println(" child challenge 4");
		System.out.println(childChallenge.toString());
		System.out.println("============================================\n\n\n\n\n\n\n\n");

		return childChallenge;
	}
	
	public ChildChallenge updateChildChallenge(long userId, long challengeId,long childChallengeId, 
			int number, Date presentationDay, Date paperStartDay,
			Date paperEndDay, Date evaluationDay, Date challengeStartDay,
			Date challengeEndDay, String challengeStatus,
			ServiceContext serviceContext)
			throws PortalException, SystemException {
		long groupId = serviceContext.getScopeGroupId();
		
		User user = userPersistence.findByPrimaryKey(userId);
		
		Date now = new Date();
		
		validate(challengeStatus);
		
		ChildChallenge childChallenge = getChildChallenge(childChallengeId);
		
		childChallenge.setUserId(userId);
		childChallenge.setUserName(user.getFullName());
		childChallenge.setModifiedDate(serviceContext.getModifiedDate(now));
		childChallenge.setExpandoBridgeAttributes(serviceContext);
		
		childChallenge.setNumber(number);
		childChallenge.setPresentationDay(presentationDay);
		childChallenge.setPaperStartDay(paperStartDay);
		childChallenge.setPaperEndDay(paperEndDay);
		childChallenge.setEvaluationDay(evaluationDay);
		childChallenge.setChallengeStartDay(challengeStartDay);
		childChallenge.setChallengeEndDay(challengeEndDay);
		childChallenge.setChallengeStatus(challengeStatus);
		
		childChallengePersistence.update(childChallenge);
		
		resourceLocalService.updateResources(user.getCompanyId(), groupId, 
				ChildChallenge.class.getName(), childChallengeId, 
				serviceContext.getGroupPermissions(), 
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, childChallenge.getCreateDate(), childChallenge.getModifiedDate(),
				ChildChallenge.class.getName(), childChallengeId, childChallenge.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, childChallenge.getChallengeStatus(), null, null, null,
				null, 0, 0, null, false);
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChildChallenge.class);
		
		indexer.reindex(childChallenge);
		
		return childChallenge;
	}
	
	protected void validate (String challengeStatus) throws PortalException{
		if(Validator.isNull(challengeStatus)){
			throw new ChildChallengeStatusException();
		}
	}
	
	public ChildChallenge updateStatus(long userId, long childChallengeId,
			int status, ServiceContext serviceContext) 
		throws PortalException, SystemException {
		User user = userLocalService.getUser(userId);
		ChildChallenge childChallenge = getChildChallenge(childChallengeId);
		
		childChallenge.setStatus(status);
		childChallenge.setStatusByUserId(userId);
		childChallenge.setStatusByUserName(user.getFullName());
		childChallenge.setStatusDate(new Date());
		
		childChallengePersistence.update(childChallenge);
		
		if(status == WorkflowConstants.STATUS_APPROVED){
			assetEntryLocalService.updateVisible(
					ChildChallenge.class.getName(), 
					childChallengeId, true);
		}else{
			assetEntryLocalService.updateVisible(
					ChildChallenge.class.getName(), 
					childChallengeId, false);
		}
		
		return childChallenge;
	}
}