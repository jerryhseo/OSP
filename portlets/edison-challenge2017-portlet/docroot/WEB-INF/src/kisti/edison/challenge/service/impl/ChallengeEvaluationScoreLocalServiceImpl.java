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

import java.util.ArrayList;
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

import kisti.edison.challenge.NoSuchChallengeEvaluationScoreException;
import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.base.ChallengeEvaluationScoreLocalServiceBaseImpl;

/**
 * The implementation of the challenge evaluation score local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeEvaluationScoreLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationScoreLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil
 */
public class ChallengeEvaluationScoreLocalServiceImpl
	extends ChallengeEvaluationScoreLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil} to access the challenge evaluation score local service.
	 */
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores(long groupId) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupId(groupId);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores(long groupId, long challengeTeamId) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
	}
	
	public List<Long> getChallengeEvaluatiorCount(long groupId, long challengeTeamId) throws SystemException{
		List<ChallengeEvaluationScore> challengeEvaluationScoreList = challengeEvaluationScorePersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		List<Long> evaluatorList = new ArrayList<Long>();
		if(challengeEvaluationScoreList.size() > 0){
			for(ChallengeEvaluationScore score : challengeEvaluationScoreList){
				if(evaluatorList.size()==0){
					evaluatorList.add(score.getUserId());
				}else{
					for(int i=0; i<evaluatorList.size(); i++){
						if(evaluatorList.get(i) == score.getUserId())
							break;
						else{
							if(i==evaluatorList.size()-1){
								evaluatorList.add(score.getUserId());
							}
						}
					}
				}
			}
			
			return evaluatorList;
		}else{
			return null;
		}
	}
	
	public int countChallengeEvaluationScore(long groupId, long challengeTeamId) throws SystemException{
		List<Long> evaluatorList = getChallengeEvaluatiorCount(groupId, challengeTeamId);
		if(evaluatorList == null){
			return 0;
		}else{
			return evaluatorList.size();
		}
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScores(long groupId, long challengeTeamId, int start, int end) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId, start, end);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(long groupId, long challengeEvaluationManagementId) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupIdAndChallengeEvaluationManagementId(groupId, challengeEvaluationManagementId);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(long groupId, long challengeEvaluationManagementId, int start, int end) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupIdAndChallengeEvaluationManagementId(groupId, challengeEvaluationManagementId, start, end);
	}
	
	public List<ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(long groupId, 
			long challengeTeamId, long userId) throws SystemException{
		return challengeEvaluationScorePersistence.findByGroupIdAndChallengeTeamIdAndUserId(userId, groupId, challengeTeamId);
	}
	
	public int countChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(long groupId, long challengeTeamId, long userId) throws SystemException{
		return challengeEvaluationScorePersistence.countByGroupIdAndChallengeTeamIdAndUserId(userId, groupId, challengeTeamId);
	}
	
	public ChallengeEvaluationScore getChallengeEvaluationScoreByGroupIdAndChallengeEvaluationManagementIdAndUserId(long userId, long challengeEvaluationScoreId, long groupId) throws SystemException{
		//NoSuchChallengeEvaluationScoreException, SystemException
		ChallengeEvaluationScore challengeEvaluationScore;
		try{
			challengeEvaluationScore = challengeEvaluationScorePersistence.findByGroupIdAndChallengeEvaluatinManagementIdAndUserId(userId, groupId, challengeEvaluationScoreId);
			return challengeEvaluationScore;
		}catch(NoSuchChallengeEvaluationScoreException e){
			return null;
		}
	}
	public double getChallengeTeamTotalScore(long groupId, long challengeTeamId) throws SystemException{
		double total=0;
		List<ChallengeEvaluationScore> sortList = new ArrayList<ChallengeEvaluationScore>();
		sortList = challengeEvaluationScorePersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		if(sortList.size() > 0){
			for(ChallengeEvaluationScore score : sortList){
				total += score.getScore();
			}
		}
		return total;
	}
	public ChallengeEvaluationScore deleteChallengeEvaluationScore(long challengeEvaluationScoreId, 
			ServiceContext serviceContext) throws PortalException, SystemException{
		ChallengeEvaluationScore challengeEvaluationScore = getChallengeEvaluationScore(challengeEvaluationScoreId);
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
				ChallengeEvaluationScore.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
				challengeEvaluationScoreId);
		
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScoreId);
		
		AssetLinkLocalServiceUtil.deleteLinks(assetEntry.getEntryId());
		
		AssetEntryLocalServiceUtil.deleteEntry(assetEntry);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationScore.class);
		
		indexer.delete(challengeEvaluationScore);
		
		challengeEvaluationScore = deleteChallengeEvaluationScore(challengeEvaluationScoreId);
		
		return challengeEvaluationScore;
	}
	
	
	
	public ChallengeEvaluationScore addChallengeEvaluationScore(long userId, 
			long challengeTeamId, long challengeEvaluationManagementId,
			double score, ServiceContext serviceContext) throws SystemException, PortalException{
		ChallengeEvaluationScore challengeEvaluationScore = null;
		System.out.println("Final Add Evaluation Item LocalService Util.");
		
		long groupId= serviceContext.getScopeGroupId();
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate();
		long challengeEvaluationScoreId = counterLocalService.increment();
		challengeEvaluationScore = challengeEvaluationScorePersistence.create(challengeEvaluationScoreId);
		
		challengeEvaluationScore.setUuid(serviceContext.getUuid());
		challengeEvaluationScore.setGroupId(groupId);
		challengeEvaluationScore.setUserId(user.getUserId());
		challengeEvaluationScore.setCompanyId(user.getCompanyId());
		challengeEvaluationScore.setUserName(user.getFullName());
		challengeEvaluationScore.setCreateDate(now);
		challengeEvaluationScore.setModifiedDate(now);
		challengeEvaluationScore.setExpandoBridgeAttributes(serviceContext);
		challengeEvaluationScore.setStatus(WorkflowConstants.STATUS_DRAFT);
		challengeEvaluationScore.setChallengeEvaluationManagementId(challengeEvaluationManagementId);
		challengeEvaluationScore.setChallengeTeamId(challengeTeamId);
		
		challengeEvaluationScore.setScore(score);
		
		challengeEvaluationScore = challengeEvaluationScorePersistence.update(challengeEvaluationScore);
		
		System.out.println(challengeEvaluationScore.toString());
		resourceLocalService.addResources(user.getCompanyId(), groupId, userId, 
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScoreId, false, true, true);
		System.out.println("Add Evaluation Resource add");
		
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry(userId,
				groupId, challengeEvaluationScore.getCreateDate(), challengeEvaluationScore.getModifiedDate(), 
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScoreId, challengeEvaluationScore.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(),
				true, null, null, null, ContentTypes.TEXT_HTML, 
				String.valueOf(challengeEvaluationScore.getScore()), 
				null, null, null, null, 0, 0, null, false);
		
		System.out.println("Add Evaluation Score Asset add");
		AssetLinkLocalServiceUtil.updateLinks(userId,  assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		System.out.println("Add Evaluation Score AssetLink add");
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationScore.class);
		indexer.reindex(challengeEvaluationScore);
		
		System.out.println("Add Evaluation Score Index add");
		WorkflowHandlerRegistryUtil.startWorkflowInstance(challengeEvaluationScore.getCompanyId(), 
				challengeEvaluationScore.getGroupId(), challengeEvaluationScore.getUserId(), 
				ChallengeEvaluationScore.class.getName(),
				challengeEvaluationScore.getPrimaryKey(), challengeEvaluationScore, serviceContext);
		System.out.println("Add Evaluation Item Workflow add");
		//if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
		//	EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		//}
		
		return challengeEvaluationScore;
	}
	
	public ChallengeEvaluationScore updateChallengeEvaluationScore(long challengeEvaluationScoreId, 
			long userId, double score, ServiceContext serviceContext) throws SystemException, PortalException{
		ChallengeEvaluationScore challengeEvaluationScore = null;
		System.out.println("Final Add Evaluation Item LocalService Util.");
		
		long groupId= serviceContext.getScopeGroupId();
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate();
		challengeEvaluationScore = getChallengeEvaluationScore(challengeEvaluationScoreId);
		System.out.println("Test 1 evalutaion update1");
		challengeEvaluationScore.setUuid(serviceContext.getUuid());
		challengeEvaluationScore.setGroupId(groupId);
		challengeEvaluationScore.setUserId(user.getUserId());
		challengeEvaluationScore.setCompanyId(user.getCompanyId());
		challengeEvaluationScore.setUserName(user.getFullName());
		challengeEvaluationScore.setCreateDate(now);
		challengeEvaluationScore.setModifiedDate(now);
		challengeEvaluationScore.setExpandoBridgeAttributes(serviceContext);
		
		challengeEvaluationScore.setScore(score);
		
		System.out.println("Test 2 evalutaion update2");
		challengeEvaluationScore = challengeEvaluationScorePersistence.update(challengeEvaluationScore);
		
		System.out.println("Test 3 evalutaion update3");
		System.out.println(challengeEvaluationScore.toString());
		resourceLocalService.updateResources(user.getCompanyId(), groupId, 
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScoreId, 
				serviceContext.getGroupPermissions(), 
				serviceContext.getGuestPermissions());
		System.out.println("Test 4 evalutaion update4 : resource update");
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry(userId,
				groupId, challengeEvaluationScore.getCreateDate(), challengeEvaluationScore.getModifiedDate(), 
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScoreId, challengeEvaluationScore.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(),
				true, null, null, null, ContentTypes.TEXT_HTML, 
				String.valueOf(challengeEvaluationScore.getScore()), 
				null, null, null, null, 0, 0, null, false);
		System.out.println("Test 5 evalutaion update5 : asset update");
		
		AssetLinkLocalServiceUtil.updateLinks(userId,  assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		System.out.println("Test 6 evalutaion update6 : asset link update");
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeEvaluationScore.class);
		indexer.reindex(challengeEvaluationScore);
		System.out.println("Test 7 evalutaion update7 : index update");
		
		if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
			EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		}
		System.out.println("Test 8 evalutaion update8 : before return");
		return challengeEvaluationScore;
	}
	
	public ChallengeEvaluationScore updateStatus(long userId, long challengeEvaluationScoreId, int status, ServiceContext serviceContext) throws PortalException, SystemException{
		User user = userLocalService.getUser(userId);
		ChallengeEvaluationScore challengeEvaluationScore = getChallengeEvaluationScore(challengeEvaluationScoreId);
		
		challengeEvaluationScore.setStatus(status);
		challengeEvaluationScore.setStatusByUserId(userId);
		challengeEvaluationScore.setStatusByUserName(user.getFullName());
		challengeEvaluationScore.setStatusDate(new Date());
		
		challengeEvaluationScorePersistence.update(challengeEvaluationScore);
		
		if(status == WorkflowConstants.STATUS_APPROVED){
			AssetEntryLocalServiceUtil.updateVisible(ChallengeEvaluationScore.class.getName(), 
					challengeEvaluationScoreId, true);
		}else{
			AssetEntryLocalServiceUtil.updateVisible(ChallengeEvaluationScore.class.getName(), 
					challengeEvaluationScoreId, false);
		}
		return challengeEvaluationScore;
	}
}