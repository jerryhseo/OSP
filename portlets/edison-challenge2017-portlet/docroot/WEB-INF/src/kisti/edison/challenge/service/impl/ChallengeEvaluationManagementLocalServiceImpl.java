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
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;

import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.service.base.ChallengeEvaluationManagementLocalServiceBaseImpl;

/**
 * The implementation of the challenge evaluation management local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeEvaluationManagementLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationManagementLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil
 */
public class ChallengeEvaluationManagementLocalServiceImpl
	extends ChallengeEvaluationManagementLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil} to access the challenge evaluation management local service.
	 */
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId) throws SystemException{
		return challengeEvaluationManagementPersistence.findByGroupId(groupId);
	}
	
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId, long childChallengeId) throws SystemException{
		return challengeEvaluationManagementPersistence.findByGroupIdAndChildChallengeId(groupId, childChallengeId);
	}
	
	public List<ChallengeEvaluationManagement> getChallengeEvaluationManagementes(long groupId, long childChallengeId, int start, int end) throws SystemException{
		return challengeEvaluationManagementPersistence.findByGroupIdAndChildChallengeId(groupId, childChallengeId, start, end);
	}
	
	public ChallengeEvaluationManagement deleteChallengeEvalutionManagement(long challengeEvaluationManagementId, 
			ServiceContext serviceContext) throws PortalException, SystemException{
		ChallengeEvaluationManagement challengeEvaluationManagement = getChallengeEvaluationManagement(challengeEvaluationManagementId);
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
				ChallengeEvaluationManagement.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
				challengeEvaluationManagementId);
		
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagementId);
		
		AssetLinkLocalServiceUtil.deleteLinks(assetEntry.getEntryId());
		
		AssetEntryLocalServiceUtil.deleteEntry(assetEntry);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationManagement.class);
		
		indexer.delete(challengeEvaluationManagement);
		
		challengeEvaluationManagement = deleteChallengeEvaluationManagement(challengeEvaluationManagementId);
		
		return challengeEvaluationManagement;
	}
	
	public ChallengeEvaluationManagement addChallengeEvalutionManagement(long userId, long childChallengeId, 
			Map<Locale, String> assessmentItem, double distribution, 
			ServiceContext serviceContext) throws SystemException, PortalException{
		ChallengeEvaluationManagement challengeEvaluationManagement = null;
		System.out.println("Final Add Evaluation Item LocalService Util.");
		
		long groupId= serviceContext.getScopeGroupId();
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate();
		long challengeEvaluationManagementId = counterLocalService.increment();
		challengeEvaluationManagement = challengeEvaluationManagementPersistence.create(challengeEvaluationManagementId);
		
		challengeEvaluationManagement.setUuid(serviceContext.getUuid());
		challengeEvaluationManagement.setGroupId(groupId);
		challengeEvaluationManagement.setUserId(user.getUserId());
		challengeEvaluationManagement.setCompanyId(user.getCompanyId());
		challengeEvaluationManagement.setUserName(user.getFullName());
		challengeEvaluationManagement.setCreateDate(now);
		challengeEvaluationManagement.setModifiedDate(now);
		challengeEvaluationManagement.setExpandoBridgeAttributes(serviceContext);
		challengeEvaluationManagement.setStatus(WorkflowConstants.STATUS_DRAFT);
		challengeEvaluationManagement.setChildChallengeId(childChallengeId);
		
		challengeEvaluationManagement.setAssessmentItemMap(assessmentItem);
		challengeEvaluationManagement.setDistribution(distribution);
		
		challengeEvaluationManagement = challengeEvaluationManagementPersistence.update(challengeEvaluationManagement);
		
		System.out.println(challengeEvaluationManagement.toString());
		resourceLocalService.addResources(user.getCompanyId(), groupId, userId, 
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagementId, false, true, true);
		System.out.println("Add Evaluation Item Resource add");
		
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry(userId,
				groupId, challengeEvaluationManagement.getCreateDate(), challengeEvaluationManagement.getModifiedDate(), 
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagementId, challengeEvaluationManagement.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(),
				true, null, null, null, ContentTypes.TEXT_HTML, 
				challengeEvaluationManagement.getAssessmentItem(), 
				null, null, null, null, 0, 0, null, false);
		
		System.out.println("Add Evaluation Item Asset add");
		AssetLinkLocalServiceUtil.updateLinks(userId,  assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		System.out.println("Add Evaluation Item AssetLink add");
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationManagement.class);
		indexer.reindex(challengeEvaluationManagement);
		
		System.out.println("Add Evaluation Item Index add");
		WorkflowHandlerRegistryUtil.startWorkflowInstance(challengeEvaluationManagement.getCompanyId(), 
				challengeEvaluationManagement.getGroupId(), challengeEvaluationManagement.getUserId(), 
				ChallengeEvaluationManagement.class.getName(),
				challengeEvaluationManagement.getPrimaryKey(), challengeEvaluationManagement, serviceContext);
		System.out.println("Add Evaluation Item Workflow add");
		//if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
		//	EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		//}
		
		return challengeEvaluationManagement;
	}
	
	public ChallengeEvaluationManagement updateChallengeEvalutionManagement(long userId, long childChallengeId, long challengeEvaluationManagementId,
			Map<Locale, String> assessmentItem, double distribution, 
			ServiceContext serviceContext) throws SystemException, PortalException{
		ChallengeEvaluationManagement challengeEvaluationManagement = null;
		
		long groupId= serviceContext.getScopeGroupId();
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate();
		challengeEvaluationManagement = getChallengeEvaluationManagement(challengeEvaluationManagementId);
		
		challengeEvaluationManagement.setUuid(serviceContext.getUuid());
		challengeEvaluationManagement.setGroupId(groupId);
		challengeEvaluationManagement.setUserId(user.getUserId());
		challengeEvaluationManagement.setCompanyId(user.getCompanyId());
		challengeEvaluationManagement.setUserName(user.getFullName());
		challengeEvaluationManagement.setModifiedDate(now);
		challengeEvaluationManagement.setExpandoBridgeAttributes(serviceContext);
		challengeEvaluationManagement.setChildChallengeId(childChallengeId);
		
		
		challengeEvaluationManagement.setAssessmentItemMap(assessmentItem);
		challengeEvaluationManagement.setDistribution(distribution);
		
		
		challengeEvaluationManagement = challengeEvaluationManagementPersistence.update(challengeEvaluationManagement);
		
		resourceLocalService.updateResources(user.getCompanyId(), groupId, 
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagementId, 
				serviceContext.getGroupPermissions(), 
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry(userId,
				groupId, challengeEvaluationManagement.getCreateDate(), challengeEvaluationManagement.getModifiedDate(), 
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagementId, challengeEvaluationManagement.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(),
				true, null, null, null, ContentTypes.TEXT_HTML, 
				challengeEvaluationManagement.getAssessmentItem(), 
				null, null, null, null, 0, 0, null, false);
		
		AssetLinkLocalServiceUtil.updateLinks(userId,  assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationManagement.class);
		indexer.reindex(challengeEvaluationManagement);
		
		if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
			EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		}
		
		return challengeEvaluationManagement;
	}
	
	public ChallengeEvaluationManagement updateStatus(long userId, long challengeEvaluationManagementId, int status, ServiceContext serviceContext) throws PortalException, SystemException{
		User user = userLocalService.getUser(userId);
		ChallengeEvaluationManagement challengeEvaluationManagement = getChallengeEvaluationManagement(challengeEvaluationManagementId);
		
		challengeEvaluationManagement.setStatus(status);
		challengeEvaluationManagement.setStatusByUserId(userId);
		challengeEvaluationManagement.setStatusByUserName(user.getFullName());
		challengeEvaluationManagement.setStatusDate(new Date());
		
		challengeEvaluationManagementPersistence.update(challengeEvaluationManagement);
		
		if(status == WorkflowConstants.STATUS_APPROVED){
			AssetEntryLocalServiceUtil.updateVisible(ChallengeEvaluationManagement.class.getName(), 
					challengeEvaluationManagementId, true);
		}else{
			AssetEntryLocalServiceUtil.updateVisible(ChallengeEvaluationManagement.class.getName(), 
					challengeEvaluationManagementId, false);
		}
		
		
		return challengeEvaluationManagement;
	}
}