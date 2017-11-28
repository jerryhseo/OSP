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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.model.ChildChallenge;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.base.ChallengeTeamLocalServiceBaseImpl;
import edison.challenge.service.builder.service.persistence.ChallengeTeamFinderUtil;

/**
 * The implementation of the challenge team local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link edison.challenge.service.builder.service.ChallengeTeamLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.base.ChallengeTeamLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil
 */
public class ChallengeTeamLocalServiceImpl
	extends ChallengeTeamLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil} to access the challenge team local service.
	 */
	
	private final static int ONE_GB = 1073741824;
	private final static String basefilePath = File.separator+"EDISON/PORTAL/dlstore/CHALLENGE/";
	private final static String paperFileName = "papersubmission";
	private final static String presenatationFileName = "presentationsubmission";
	private final static String paperFileNamePDF = "papersubmissionPDF";
	//private String paperFileName;
	private String createPath;

	public List<ChallengeTeam> getChallengeTeamByChild(long childID) throws SystemException{
		return this.challengeTeamPersistence.findBychildCollect(childID);
	}
	

	public List<ChallengeTeam> getChallengeTeamByUserScreenName(String memberScreenName) throws SystemException{
		try {
			return ChallengeMemberLocalServiceUtil.getChallengeTeamListByMember(memberScreenName);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//File upload method
	public boolean fileUpload(long childID, String inputName, ActionRequest actionRequest, long teamID) throws PortalException, SystemException{
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		createPath = basefilePath + String.valueOf(childID) + "/" + teamID + "/";
		
		long sizeInBytes = uploadRequest.getSize(inputName);
		String sourceFileName = uploadRequest.getFileName(inputName);
		
		if (sizeInBytes == 0 || sizeInBytes >ONE_GB) {
			System.out.println("File size is 0 or over the one giga bytes.");
			System.out.println(" Get File Size : " +sizeInBytes);
			System.out.println(" Get File Name : "+sourceFileName);
			System.out.println(" Get File Created Path : " + createPath);
			return false;
		}
		
		//Get Upload file information 
		File uploadFile = uploadRequest.getFile(inputName);
		
		//Folder create 
		File uploadFolder = new File(createPath+ inputName + "/");
		
		File filePath = new File(uploadFolder.getAbsolutePath()+File.separator+sourceFileName);
		
		try {
			FileUtil.copyFile(uploadFile, filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public String getCreateFilePath(){
		return this.createPath;
	}
	
	
	public void teamFileDownLoad(long chTeamID, String inpuName, String fileName, ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		ChallengeTeam myTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
		String filePath = myTeam.getFilepath()+inpuName+"/"+fileName;
		
		File downFile = new File(filePath);
		
		System.out.println("test file download 3 : "+downFile.getName()+"\n\n\n\n\n\n\n");
		InputStream in;
		try {
			in = new FileInputStream(downFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(response);
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		try {
			ServletResponseUtil.sendFile(httpReq, httpRes,  downFile.getName(), Files.readAllBytes(downFile.toPath()), Files.probeContentType(downFile.toPath()));
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}
	
	public void deleteTeamandMember(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long teamID = ParamUtil.getLong(actionRequest, "teamID");
		ChallengeMemberLocalServiceUtil.deleteMemberByTeam(teamID);
		ChallengeTeamLocalServiceUtil.deleteChallengeTeam(teamID);
	}
	
	public String getTeamAppList(long companyID, long teamID){
		ChildChallenge cch = null;
		long cchID;
		try {
			cchID = ChallengeTeamLocalServiceUtil.getChallengeTeam(teamID).getChildid();
			cch = ChildChallengeLocalServiceUtil.getChildChallenge(cchID);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result = "";
		List<String> appList = new ArrayList<String>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = dateFormat.format(cch.getChallengeStartDay());
		String endDay = dateFormat.format(cch.getPaperEndDay());	
		appList.addAll(ChallengeTeamFinderUtil.getChallengeTeamAppList(String.valueOf(teamID), startDay, endDay));
		
		
		HashSet hs = new HashSet(appList);
		List<String> appList2 = new ArrayList<String>(hs);
		String appListToString = "";
		
		for(String appName : appList2){
			appListToString += appName;
			appListToString +="<br>";
		}
		return appListToString;
	}
	
	public String getTeamCPUUseage(long companyID, long teamID){
		ChildChallenge cch = null;
		double CPUTeam = 0;
		long cchID;
		try {
			cchID = ChallengeTeamLocalServiceUtil.getChallengeTeam(teamID).getChildid();
			cch = ChildChallengeLocalServiceUtil.getChildChallenge(cchID);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = dateFormat.format(cch.getChallengeStartDay());
		String endDay = dateFormat.format(cch.getPaperEndDay());
		String returnResult = ChallengeTeamFinderUtil.getCPUUseage(String.valueOf(teamID), startDay, endDay);
		if(!(returnResult.equals(null)||returnResult.equals("")||returnResult.equals("null")))
		{	
			CPUTeam += Double.parseDouble(returnResult);
			return String.valueOf(CPUTeam);
		}
		
		return String.valueOf(CPUTeam);
	}


}