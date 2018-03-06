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

import kisti.edison.challenge.service.ChallengeEvaluationLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.base.ChallengeTeamLocalServiceBaseImpl;
import kisti.edison.challenge.service.persistence.ChallengeTeamFinderUtil;
import kisti.edison.challenge.util.WebKeys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.CountryConstants;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.RegionConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.OrganizationServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.apache.log4j.Logger;
import org.hsqldb.lib.Iterator;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.model.ChildChallenge;

/**
 * The implementation of the challenge team local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link kisti.edison.challenge.service.ChallengeTeamLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author KYJ
 * @see kisti.edison.challenge.service.base.ChallengeTeamLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil
 */
public class ChallengeTeamLocalServiceImpl
	extends ChallengeTeamLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil} to access the challenge team local service.
	 */
	
	private final static int ONE_GB = 1073741824;
	private final static String basefilePath = File.separator+"EDISON/PORTAL/dlstore/CHALLENGE/";
	private final static String paperFileName = "papersubmission";
	private final static String presenatationFileName = "presentationsubmission";
	private final static String paperFileNamePDF = "papersubmissionPDF";
	
	private String createPath;
	
	public List<ChallengeTeam> getChallengeTeames(long groupId)
			throws SystemException{
		return challengeTeamPersistence.findByGroupId(groupId);
	}
	
	public List<ChallengeTeam> getChallengeTeames(long groupId, 
			int status) throws SystemException{
		return challengeTeamPersistence.findByGroupIdAndStatus(groupId, 
				WorkflowConstants.STATUS_APPROVED);
	}
	
	public List<ChallengeTeam> getChallengeTeams(long groupId, 
			long childChallengeId) throws SystemException{
		return challengeTeamPersistence.findByGroupIdAndChildChallenge(groupId, childChallengeId);
	}
	
	public List<ChallengeTeam> getChallengeTeamsAndEvaluationOrder(long groupId, 
			long childChallengeId) throws SystemException{
		List<ChallengeTeam> challengeTeamList =  new ArrayList<ChallengeTeam>();
		challengeTeamList.addAll(challengeTeamPersistence.findByGroupIdAndChildChallenge(groupId, childChallengeId));
		
		if(challengeTeamList.size()>0){
			//System.out.println("quick sorting start...");
			quickSortToChallengeTeam(challengeTeamList, 0, challengeTeamList.size()-1);
			//System.out.println("quick sorting end...");
		}
		
		return challengeTeamList;
		
	}
	
	public List<ChallengeTeam> getChallengeTeamsAndEvaluationOrder(long groupId, long childChallengeId, int start, int end) throws SystemException{
		List<ChallengeTeam> challengeList = getChallengeTeamsAndEvaluationOrder(groupId, childChallengeId);
		
		
		if(end > challengeList.size())
			return challengeList.subList(start, challengeList.size());
		else
			return challengeList.subList(start, end);
	}
	private int partition(List<ChallengeTeam> teamList, int left, int right) throws SystemException{
		int i=left, j=right;
		ChallengeTeam pivot = teamList.get((int)(left+right)/2);
		double pivotValue = ChallengeEvaluationScoreLocalServiceUtil.getChallengeTeamTotalScore(pivot.getGroupId(), pivot.getChallengeTeamId());
		while(i<=j){
			while(ChallengeEvaluationScoreLocalServiceUtil.getChallengeTeamTotalScore(teamList.get(i).getGroupId(), teamList.get(i).getChallengeTeamId())
					> pivotValue){
				i++;
			}
			while(ChallengeEvaluationScoreLocalServiceUtil.getChallengeTeamTotalScore(teamList.get(j).getGroupId(), teamList.get(j).getChallengeTeamId())
					< pivotValue){
				j--;
			}
			if(i<=j){
				Collections.swap(teamList, i, j);
				i++;
				j--;
			}
		}
		return i;
	}
	
	private void quickSortToChallengeTeam(List<ChallengeTeam> teamList, int left, int right) throws SystemException{
		int index = partition(teamList, left, right);
		if(left < index-1){
			quickSortToChallengeTeam(teamList, left, index-1);
		}
		if(index < right){
			quickSortToChallengeTeam(teamList, index, right);
		}
	}
	


	
	public List<ChallengeTeam> getChallengeTeams(long groupId, 
			long childChallengeId, int start, int end) throws SystemException{
		//System.out.println("\n\n\n\nTest Team Service1\n\n\n");
		return challengeTeamPersistence
				.findByGroupIdAndChildChallenge(groupId, childChallengeId, start, end);
	}
	
	public int getChallengeTeamsCount(long groupId, 
			long childChallengeId) throws SystemException{
		return challengeTeamPersistence.countByGroupIdAndChildChallenge(groupId, childChallengeId);
	}
	
	public ChallengeTeam addChallengeTeam(long userId, 
			String teamName, Map<Locale, String> subject,
			Map<Locale, String> paperName, Map<Locale, String> paperAbstract,
			UploadPortletRequest uploadRequest, String grade, String phone,
			long childChallengeId, ServiceContext serviceContext) 
					throws SystemException, PortalException{
		
		
		
		
		String groupName = teamName;
		
		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();
		try{
			
			
			long groupId = serviceContext.getScopeGroupId();
			User user = userPersistence.findByPrimaryKey(userId);
			Date now = new Date();
		
		//validate();
		long challengeTeamId = counterLocalService.increment();
		ChallengeTeam challengeTeam = challengeTeamPersistence.create(challengeTeamId);
		
		challengeTeam.setUuid(serviceContext.getUuid());
		challengeTeam.setGroupId(groupId);
		challengeTeam.setUserId(user.getUserId());
		challengeTeam.setCompanyId(user.getCompanyId());
		challengeTeam.setUserName(user.getFullName());
		challengeTeam.setCreateDate(now);
		challengeTeam.setModifiedDate(now);
		challengeTeam.setExpandoBridgeAttributes(serviceContext);
		challengeTeam.setStatus(WorkflowConstants.STATUS_DRAFT);
		challengeTeam.setChildChallengeId(childChallengeId);
		if(phone.equals("010-0000-0000")){
			challengeTeam.setAggrement(false);
		}else{
			challengeTeam.setAggrement(true);
		}
			
		//file upload
		
		if(fileUpload(childChallengeId, paperFileName, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPaperStatusDOC(true);
			if(challengeTeam.getPaperSubmissionDay()==null){
				challengeTeam.setPaperSubmissionDay(now);
			}
			challengeTeam.setPaperModifyDay(now);
			challengeTeam.setPaperFileName(uploadRequest.getFileName(paperFileName));
		}
		if(fileUpload(childChallengeId,	paperFileNamePDF, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPaperStatusPDF(true);
			if(challengeTeam.getPaperPDFSubmissionDay()==null){
				challengeTeam.setPaperPDFSubmissionDay(now);
			}
			challengeTeam.setPaperPDFModifyDay(now);
			challengeTeam.setPaperPDFFileName(uploadRequest.getFileName(paperFileNamePDF));
		}
		if(fileUpload(childChallengeId,	presenatationFileName, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPresentationStatus(true);
			if(challengeTeam.getPresentationSubmissionDay()==null){
				challengeTeam.setPresentationSubmissionDay(now);
			}
			challengeTeam.setPresentationModifyDay(now);
			challengeTeam.setPresentationFileName(uploadRequest.getFileName(presenatationFileName));
		}
		
		//System.out.println("test local service team : file status 1 : "+challengeTeam.getPaperStatusDOC());
		//System.out.println("test local service team : file status 2 : "+challengeTeam.getPaperStatusPDF());
		//System.out.println("test local service team : file status 3 : "+challengeTeam.getPresentationStatus());
		
		challengeTeam.setFilepath(createPath);
		
		//other column
		challengeTeam.setTeamName(teamName);
		challengeTeam.setSubjectMap(subject);
		challengeTeam.setPaperNameMap(paperName);
		challengeTeam.setPaperAbstractMap(paperAbstract);
		
		
		challengeTeam = challengeTeamPersistence.update(challengeTeam);
		//System.out.println("challenge team local service updated : "+challengeTeamId);
		//System.out.println(challengeTeam.toString());
		//System.out.println(challengeTeam.getTeamName().toString());
		
		
		
		resourceLocalService.addResources(user.getCompanyId(), groupId, userId, 
				ChallengeTeam.class.getName(), challengeTeamId, false, true, true);
		//System.out.println("resouces service.");
		
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, challengeTeam.getCreateDate(), challengeTeam.getModifiedDate(), 
				ChallengeTeam.class.getName(), challengeTeamId, challengeTeam.getUuid(), 0,
				serviceContext.getAssetCategoryIds(), 
				serviceContext.getAssetTagNames(),
				true, null, null, null, ContentTypes.TEXT_HTML, 
				challengeTeam.getTeamName(), 
				null, null, null, null, 0, 0, null, false);
		
		//System.out.println("asset entry add service.");
		
		assetLinkLocalService.updateLinks(userId,  assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(), AssetLinkConstants.TYPE_RELATED);
		
		//System.out.println("asset entry add service2.");
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeam.class);
		
		//System.out.println("index add service.");
		indexer.reindex(challengeTeam);
		
		//System.out.println("workflow add service.");
		WorkflowHandlerRegistryUtil.startWorkflowInstance(challengeTeam.getCompanyId(), 
				challengeTeam.getGroupId(), challengeTeam.getUserId(), 
				ChallengeTeam.class.getName(),
				challengeTeam.getPrimaryKey(), challengeTeam, serviceContext);
		System.out.println("end challenge Team....");
		
		ChallengeTeamMember testMember = ChallengeTeamMemberServiceUtil.addChallengeTeamMember(userId, challengeTeam.getChallengeTeamId(),
				userId, grade, phone, true, serviceContext);
		System.out.println("add challenge team member");
		//System.out.println(testMember.toString());
		
		//Creating Organization Method
		//createOrganization(teamName, serviceContext);
		
		if(!GroupUtil.containsUser(serviceContext.getScopeGroupId(), userId)){
			EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(userId), serviceContext.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER);
		}
		
		return challengeTeam;
		
		}catch (PortalException e) {
			  System.out.println(e);
		} catch (SystemException e) {
			System.out.println(e);
		}
		return null;
	}
	
	protected Organization createOrganization(String organizationName, ServiceContext serviceContext) 
			throws PortalException, SystemException{
		Organization organization = null;
		//System.out.println("\n\n\n");
		//System.out.println("create Organization : "+organizationName);
		
		organization = OrganizationServiceUtil.addOrganization(
							OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
							organizationName,
							OrganizationConstants.TYPE_REGULAR_ORGANIZATION,
							RegionConstants.DEFAULT_REGION_ID,
							CountryConstants.DEFAULT_COUNTRY_ID,
							ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
							"",
							false,
							serviceContext
					);
		organization.setCompanyId(serviceContext.getCompanyId());
		
		organization = OrganizationLocalServiceUtil.updateOrganization(organization);
		//System.out.println("Creating Organization");
		//System.out.println("\n\n\n");
		return organization;
	}
	
	protected void deleteOrganization(String organizationName, ServiceContext serviceContext) 
			throws PortalException, SystemException{
		Organization organization = null;
		long organizationId = OrganizationLocalServiceUtil.getOrganizationId(serviceContext.getCompanyId(), organizationName);
		OrganizationServiceUtil.deleteOrganization(organizationId);
	}
	
	public ChallengeTeam updateChallengeTeam(long userId, 
			long childChallengeId, long challengeTeamId,
			String teamName, Map<Locale, String> subject,
			Map<Locale, String> paperName, Map<Locale, String> paperAbstract,
			UploadPortletRequest uploadRequest,
			String grade, String phone, 
			ServiceContext serviceContext
			) throws SystemException, PortalException{
		
		long groupId = serviceContext.getScopeGroupId();
		
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		//validate();
		
		ChallengeTeam challengeTeam = getChallengeTeam(challengeTeamId);
		challengeTeam.setUserId(userId);
		challengeTeam.setUserName(user.getFullName());
		challengeTeam.setModifiedDate(now);
		challengeTeam.setExpandoBridgeAttributes(serviceContext);
		
		if(phone.equals("010-0000-0000")){
			challengeTeam.setAggrement(false);
		}else{
			challengeTeam.setAggrement(true);
		}
				
		//file upload
		
		if(fileUpload(childChallengeId, paperFileName, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPaperStatusDOC(true);
			if(challengeTeam.getPaperSubmissionDay()==null){
				challengeTeam.setPaperSubmissionDay(now);
			}
			challengeTeam.setPaperModifyDay(now);
			challengeTeam.setPaperFileName(uploadRequest.getFileName(paperFileName));
			System.out.println("paper doc upload success.");
		}
		if(fileUpload(childChallengeId,	paperFileNamePDF, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPaperStatusPDF(true);
			if(challengeTeam.getPaperPDFSubmissionDay()==null){
				challengeTeam.setPaperPDFSubmissionDay(now);
			}
			challengeTeam.setPaperPDFModifyDay(now);
			challengeTeam.setPaperPDFFileName(uploadRequest.getFileName(paperFileNamePDF));
		}
		if(fileUpload(childChallengeId,	presenatationFileName, uploadRequest, challengeTeam.getChallengeTeamId())){
			challengeTeam.setPresentationStatus(true);
			if(challengeTeam.getPresentationSubmissionDay()==null){
				challengeTeam.setPresentationSubmissionDay(now);
			}
			challengeTeam.setPresentationModifyDay(now);
			challengeTeam.setPresentationFileName(uploadRequest.getFileName(presenatationFileName));
		}
		
		
		challengeTeam.setFilepath(createPath);
		//other column
		challengeTeam.setTeamName(teamName);
		challengeTeam.setSubjectMap(subject);
		challengeTeam.setPaperNameMap(paperName);
		challengeTeam.setPaperAbstractMap(paperAbstract);
		challengeTeam.setChildChallengeId(childChallengeId);
		
		//System.out.println("\n\n\n\ntest challenge team update by manager");
		//System.out.println(challengeTeam.getPaperAbstract());
		
		challengeTeamPersistence.update(challengeTeam);
		///////////////////////////////////////////////////
		
		resourceLocalService.updateResources(user.getCompanyId(), groupId, 
				ChallengeTeam.class.getName(), challengeTeamId, 
				serviceContext.getGroupPermissions(), 
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, 
				groupId, challengeTeam.getCreateDate(), challengeTeam.getModifiedDate(),
				ChallengeTeam.class.getName(), challengeTeamId, challengeTeam.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true,
				null, null, null, ContentTypes.TEXT_HTML, 
				challengeTeam.getTeamName(), null, null, null,
				null, 0, 0, null, false);
		
		assetLinkLocalService.updateLinks(userId,  assetEntry.getEntryId(), 
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeam.class);
		
		indexer.reindex(challengeTeam);
		
		ChallengeTeamMember challengeTeamMember = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamLeaderMember(challengeTeamId, groupId);
		challengeTeamMember.setGrade(grade);
		challengeTeamMember.setPhone(phone);
		challengeTeamMemberPersistence.update(challengeTeamMember);
		
		return challengeTeam;
	}
	
	public ChallengeTeam deleteChallengeTeam(long challengeTeamId,
			ServiceContext serviceContext) throws PortalException, SystemException{
		ChallengeTeam challengeTeam = getChallengeTeam(challengeTeamId);
		System.out.println("\n\n\n\ndelete challenge team : "+ challengeTeam.getChallengeTeamId());
		List<ChallengeTeamMember> challengeTeamMemberList = ChallengeTeamMemberLocalServiceUtil
				.getChallengeTeamMembers(serviceContext.getScopeGroupId(), challengeTeamId);
		
		for(ChallengeTeamMember challengeTeamMember : challengeTeamMemberList){
			System.out.println("delete team member1 : "+challengeTeamMember.getApplyUserName());
			ChallengeTeamMemberLocalServiceUtil.deleteChallengeTeamMember(challengeTeamMember.getChallengeTeamMemberId(), 
					serviceContext);
			
			System.out.println("delete team member2 : "+challengeTeamMember.getApplyUserName());
		}
		//System.out.println("test1");
		resourceLocalService.deleteResource(serviceContext.getCompanyId(), 
				ChallengeTeam.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, challengeTeamId);
		//System.out.println("test2");
		AssetEntry assetEntry = assetEntryLocalService
				.fetchEntry(ChallengeTeam.class.getName(), challengeTeamId);
		//System.out.println("test3");
		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		//System.out.println("test4");
		assetEntryLocalService.deleteEntry(assetEntry);
		//System.out.println("test5");
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(ChallengeTeam.class);
		indexer.delete(challengeTeam);
		//System.out.println("test6");
		challengeTeam = deleteChallengeTeam(challengeTeamId);
		//System.out.println("test7");
		
		//Delete Organization 
		//deleteOrganization(challengeTeam.getTeamName(), serviceContext);
		return challengeTeam;
	}
	
	//File upload to server
	public boolean fileUpload(long childChallengeId, 
			String fileType, UploadPortletRequest uploadRequest, long challengeTeamId){
		//UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		createPath = basefilePath + String.valueOf(childChallengeId)+"/" + challengeTeamId + "/";
		
		long sizeInBytes = uploadRequest.getSize(fileType);
		
		String sourceFileName = uploadRequest.getFileName(fileType);
		
		System.out.println("\n\n\nUpload File.");
		System.out.println("Upload File Type : "+fileType);
		System.out.println("createPath : "+ createPath);
		System.out.println("\n\n\n");
		
		if(sizeInBytes == 0 || sizeInBytes > ONE_GB){
			System.out.println("File size is 0 or over the one giga bytes.");
			System.out.println(" Get File Size : " +sizeInBytes);
			System.out.println(" Get File Name : "+sourceFileName);
			System.out.println(" Get File Created Path : " + createPath);
			return false;
		}
		
		File uploadFile = uploadRequest.getFile(fileType);
		
		File uploadFolder = new File(createPath+fileType+"/");
		
		File filePath = new File(uploadFolder.getAbsolutePath()+File.separator+sourceFileName);
		
		try{
			FileUtil.copyFile(uploadFile, filePath);
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//File Download to client
	public void fileDownload(long challengeTeamId, String fileType, String fileName,
			ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortalException, SystemException{
		ChallengeTeam challengeTeam = getChallengeTeam(challengeTeamId);
		
		String filePath = challengeTeam.getFilepath()+fileType+"/"+fileName;
		
		InputStream in = null;
		
		
		try{
			Path downFile = Paths.get(filePath);
			
			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(resourceRequest);
			HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(resourceResponse);
			
			ServletResponseUtil.sendFile(httpReq, httpRes, downFile.getFileName().toString(), Files.readAllBytes(downFile));
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		
	}
	
	
	public ChallengeTeam updateStatus(long userId, long challengeTeamId, 
			int status, ServiceContext serviceContext)
					throws SystemException, PortalException{

		User user = userLocalService.getUser(userId);
		ChallengeTeam challengeTeam = getChallengeTeam(challengeTeamId);
		
		challengeTeam.setStatus(status);
		challengeTeam.setStatusByUserId(userId);
		challengeTeam.setStatusByUserName(user.getFullName());
		challengeTeam.setStatusDate(new Date());
		
		challengeTeamPersistence.update(challengeTeam);
		
		if(status == WorkflowConstants.STATUS_APPROVED){
			assetEntryLocalService.updateVisible(ChallengeTeam.class.getName(), challengeTeamId, true);
		}else{
			assetEntryLocalService.updateVisible(ChallengeTeam.class.getName(), challengeTeamId, false);
		}
		return challengeTeam;
	}
	
	public List<ChallengeTeam> getChallengeTeamByMemberUser(long groupId, 
			long applyUserId, long childChallengeId) throws SystemException, PortalException{
		List<ChallengeTeamMember> challengeMemberList = challengeTeamMemberPersistence.findByGroupIdAndApplyUserId(groupId, applyUserId);
		
		List<ChallengeTeam> teamList = new ArrayList<ChallengeTeam>();
		for(ChallengeTeamMember member : challengeMemberList){
			teamList.add(ChallengeTeamLocalServiceUtil.getChallengeTeam(member.getChallengeTeamId()));
		}
		
		return teamList;
	}
	
	public List<ChallengeTeam> getChallengeTeamByMemberUser(long groupId, 
			long applyUserId, int start, int end) throws SystemException, PortalException{
		List<ChallengeTeamMember> challengeMemberList = challengeTeamMemberPersistence.findByGroupIdAndApplyUserId(groupId, applyUserId, start, end);
		
		List<ChallengeTeam> teamList = new ArrayList<ChallengeTeam>();
		for(ChallengeTeamMember member : challengeMemberList){
			teamList.add(ChallengeTeamLocalServiceUtil.getChallengeTeam(member.getChallengeTeamId()));
		}
		
		return teamList;
	}
	
	public List<String> getTeamAppList(long companyId, long challengeTeamId) 
			throws PortalException, SystemException{
		ChildChallenge childChallenge = null;
		long childChallengeId;
		
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		childChallengeId = challengeTeam.getChildChallengeId();
		childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId);
		
		List<String> appList = new ArrayList<String>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = dateFormat.format(childChallenge.getChallengeStartDay());
		String endDay = dateFormat.format(childChallenge.getPaperEndDay());
		
		if(ChallengeTeamFinderUtil.getChallengeTeamAppList(String.valueOf(challengeTeamId), startDay, endDay)!=null)
			appList.addAll(ChallengeTeamFinderUtil.getChallengeTeamAppList(String.valueOf(challengeTeamId), startDay, endDay));
		else
			return null;
		return appList;
	}
	
	public String getTeamAppListString(long companyId, long challengeTeamId) 
			throws PortalException, SystemException{
		ChildChallenge childChallenge = null;
		long childChallengeId;
		
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		
		List<String> appList = null;
		appList = getTeamAppList(companyId, challengeTeamId);
		if(appList == null)
			return "";
		HashSet hs = new HashSet(appList);
		List<String> appList2 = new ArrayList<String>(hs);
		String appListToString = "";
		
		for(String appName : appList2){
			appListToString += appName;
			appListToString +=", ";
		}
		return appListToString;
	}
	
	public String getTeamAppListStringForWeb(long companyId, long challengeTeamId) 
			throws PortalException, SystemException{
		ChildChallenge childChallenge = null;
		long childChallengeId;
		
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		
		List<String> appList = null;
		appList = getTeamAppList(companyId, challengeTeamId);
		if(appList == null)
			return "";
		HashSet hs = new HashSet(appList);
		List<String> appList2 = new ArrayList<String>(hs);
		String appListToString = "";
		
		for(String appName : appList2){
			appListToString += appName;
			appListToString +="<br>";
		}
		return appListToString;
	}
	
	public int getTeamSimulationNumber(long companyId, long challengeTeamId) throws PortalException, SystemException{
		List<String> appList = null;
		appList = getTeamAppList(companyId, challengeTeamId);
		if(appList != null)
			return appList.size();
		else
			return 0;
	}
	
	public String getCPUUseage(long companyId, long challengeTeamId) 
			throws PortalException, SystemException{
		ChildChallenge childChallenge = null;
		double CPUUseage = 0;
		long childChallengeId;
		
		childChallengeId = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId).getChildChallengeId();
		childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = dateFormat.format(childChallenge.getChallengeStartDay());
		String endDay = dateFormat.format(childChallenge.getPaperEndDay());
		String returnResult = ChallengeTeamFinderUtil.getCPUUseage(String.valueOf(challengeTeamId), startDay, endDay);
		if(!(returnResult.equals(null)||returnResult.equals("")||returnResult.equals("null")))
		{	
			CPUUseage += Double.parseDouble(returnResult);
			return String.valueOf(CPUUseage);
		}
		
		return String.valueOf(CPUUseage);
	}
	
	public boolean isChallengeTeamMember(long groupId, long challengeTeamId, long userId) throws SystemException{
		List<ChallengeTeamMember> memberList = challengeTeamMemberPersistence.findByGroupIdAndChallengeTeamId(groupId, challengeTeamId);
		
		for(ChallengeTeamMember challengeTeamMember : memberList){
			if(challengeTeamMember.getApplyUserId() == userId){
				return true;
			}
		}
		
		return false;
	}
	
	
	public void getUserInfo(long challengeTeamId, ResourceRequest request, ResourceResponse response) throws Exception {
		
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		String userScreenName = "";
		userScreenName = ParamUtil.getString(request, "searchName");
		
		System.out.println("test ger user information1 : "+userScreenName);
		
		
		//User userTest = UserServiceUtil.getUserById(Long.parseLong(userScreenName));
		//System.out.println("\n\n\nuser get information test ... ");
		//System.out.println("user screen name : "+userTest.getScreenName());
		//System.out.println("user email : " + userTest.getEmailAddress());
		
		User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, userScreenName);
		
		PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
		PermissionThreadLocal.setPermissionChecker(checker);
		
		JSONObject memberUserInfo = JSONFactoryUtil.createJSONObject();
		if (user == null) {
			memberUserInfo.put("userType", "none");
			//System.out.println("test json 3");
		} else {
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
				|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
				|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)) {
				memberUserInfo.put("userType", "admin");
				//System.out.println("test json 4");
			}else {
				
				
				String userUniversityCode = user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
				//long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
				ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
				ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
				
				//memberUserInfo = new HashMap<String, String>();
				memberUserInfo.put("userUniversity", universityValue.getString(themeDisplay.getLocale()));
				memberUserInfo.put("userScreenName", user.getScreenName());
				memberUserInfo.put("userScreenNameView", user.getScreenName());
				memberUserInfo.put("userId", user.getUserId());
				String userFullName = user.getFullName();
				String strtemp = userFullName.substring(userFullName.length()-2, userFullName.length()-1);
				userFullName = userFullName.replace(strtemp, "*");
				
				memberUserInfo.put("userFullName", userFullName);
				memberUserInfo.put("userType", "user");
				
				//obj.put("memberUserInfo", memberUserInfo);
				//obj.put("result", "user");
				
				//System.out.println(memberUserInfo.toString());
				//System.out.println("test json 5");
			}
		}
		try{
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(memberUserInfo);
			out.flush();
			out.close();
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public ChallengeTeam getUserCurrentTeam(long groupId, long userId) throws SystemException{
		//ChallengeTeam challengeTeam = null;
		List<ChallengeTeamMember> myTeamMemberList = null;
		myTeamMemberList = challengeTeamMemberPersistence.findByGroupIdAndApplyUserId(groupId, userId);
		
		
		if(myTeamMemberList==null){
			return null;
		}
		
		for(ChallengeTeamMember challengeTeamMember : myTeamMemberList){
			ChallengeTeam challengeTeam = null;
			long challengeTeamId = challengeTeamMember.getChallengeTeamId();
			long childChallengeId;
			try {
				childChallengeId = challengeTeamLocalService.getChallengeTeam(challengeTeamId).getChildChallengeId();
				if(childChallengeLocalService.getChildChallenge(childChallengeId).getChallengeStatus().equals("Running")){
					return challengeTeamLocalService.getChallengeTeam(challengeTeamId);
				}
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
}