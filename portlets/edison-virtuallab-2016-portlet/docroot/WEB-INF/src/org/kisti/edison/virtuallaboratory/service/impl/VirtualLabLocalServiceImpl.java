/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package org.kisti.edison.virtuallaboratory.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.kisti.edison.customauthmanager.model.UserGroupRoleCustom;
import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.virtuallaboratory.NoSuchVirtualLabException;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClass;
import org.kisti.edison.virtuallaboratory.model.VirtualLabUser;
import org.kisti.edison.virtuallaboratory.portlet.virtualLabRegistrationList.VirtualLabRegistrationListController;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.base.VirtualLabLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the virtual lab local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.VirtualLabLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author jksang
 * @see org.kisti.edison.service.base.VirtualLabLocalServiceBaseImpl
 * @see org.kisti.edison.service.VirtualLabLocalServiceUtil
 */
public class VirtualLabLocalServiceImpl extends VirtualLabLocalServiceBaseImpl {
    
    private static Log log = LogFactoryUtil.getLog(VirtualLabLocalServiceBaseImpl.class);

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.service.VirtualLabLocalServiceUtil} to access the virtual lab local service.
	 */
	
	public VirtualLab addVirtualLab(UploadPortletRequest upload, PortletRequest request, long groupId, long userId, Map<String, String> param, Locale locale) throws SystemException, PortalException, SQLException, IOException {
		VirtualLab virtualLab = null;
		if(!param.get("virtualLabId").equals("0")) {
			try {
				String appIconStr = CustomUtil.strNull(upload.getFileName("course_icon"), "");
				
				virtualLab = virtualLabPersistence.findByPrimaryKey(Long.parseLong(param.get("virtualLabId")));
				virtualLab.setGroupId(Long.parseLong(param.get("groupId")));
				virtualLab.setVirtualLabUniversityField(param.get("virtualLabUniversityField"));
				virtualLab.setVirtualLabDescriptionMap(CustomUtil.getLocalizationMap(param,"virtualLabDescription"));
				virtualLab.setVirtualLabTitleMap(CustomUtil.getLocalizationMap(param,"virtualLabTitle"));
//				virtualLab.setVirtualLabPersonName(param.get("virtualLabProfessor"));
				virtualLab.setVirtualLabStatus(param.get("statusSort"));
				virtualLab.setProfessorSeq(Long.parseLong(param.get("professorSeq")));
				
				//아이콘 업데이트
				if(!appIconStr.equals("")){ // 아이콘이 있는경우
					long iconId = virtualLab.getIconId();
					if(iconId != 0){
						// 기존 파일 삭제
						try{
							DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
						}catch(NoSuchFileEntryException e){}
					}
					List<FileEntry> courseIcon = EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", param.get("virtualLabId"), "course_icon", EdisonFileConstants.COURSE);
					FileEntry appEntry = courseIcon.get(0);
					virtualLab.setIconId(appEntry.getFileEntryId());
				}
				virtualLabPersistence.update(virtualLab);
			} catch (NoSuchVirtualLabException e) {
				throw new SystemException(e);
			} catch (NumberFormatException e) {
			    throw new SystemException(e);
			}
		} else {
			long virtualLabSeq = CounterLocalServiceUtil.increment(VirtualLab.class.getName());
			
			virtualLab = virtualLabPersistence.create(virtualLabSeq);
			virtualLab.setUserId(Long.parseLong(param.get("userId")));
			virtualLab.setGroupId(Long.parseLong(param.get("groupId")));
			virtualLab.setVirtualLabUniversityField(param.get("virtualLabUniversityField"));
			virtualLab.setVirtualLabDescriptionMap(CustomUtil.getLocalizationMap(param,"virtualLabDescription"));
			virtualLab.setVirtualLabTitleMap(CustomUtil.getLocalizationMap(param,"virtualLabTitle"));
//			virtualLab.setVirtualLabPersonName(param.get("virtualLabProfessor"));
			virtualLab.setVirtualLabConfirmDt(new Date());
			virtualLab.setVirtualLabStatus(param.get("statusSort"));
			virtualLab.setVirtualLabUseYn("Y");
			virtualLab.setVirtualLabRequestDt(new Date());
			virtualLab.setProfessorSeq(Long.parseLong(param.get("professorSeq")));

			//아이콘 등록
			String appIconStr = CustomUtil.strNull(upload.getFileName("course_icon"), "");
			if(!appIconStr.equals("")){ // 아이콘이 있는경우
				List<FileEntry> courseIcon = EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", String.valueOf(virtualLabSeq), "course_icon", EdisonFileConstants.COURSE);
				FileEntry appEntry = courseIcon.get(0);
				virtualLab.setIconId(appEntry.getFileEntryId());
			}
			virtualLab = virtualLabPersistence.update(virtualLab); //가상 실험실 생성
		}
		return virtualLab;
	}
	
	public List<Map<String, Object>> getVirtualLabAuthList(long groupId, long userId, Locale locale) throws SystemException, PortalException {
		List<Object[]> virtualLabList = virtualLabFinder.getVirtualLabAuthList(groupId, userId);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < virtualLabList.size(); i++) {
			Object[] resultArray = virtualLabList.get(i);
			
			VirtualLab virtualLab = (VirtualLab) resultArray[0];
			VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
			int classCount = (Integer) resultArray[2];
			
			resultRow = new HashMap<String, Object>();
			if (virtualLab != null) {
				resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
				resultRow.put("userId", String.valueOf(virtualLab.getUserId()));
				resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
				
				String virtualLabProfessorName = "";
				long professorSeq = virtualLab.getProfessorSeq();
				if(professorSeq != 0){
					Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
					if(professor != null &&professor.getUserId() !=0){
						User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
						if(professorUser != null){
							virtualLabProfessorName = professorUser.getFirstName();
						}
					}
					
				}
				/*교수명 조회*/
				resultRow.put("virtualLabPersonName", virtualLabProfessorName);
//				resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
				resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
				resultRow.put("virtualLabUseYn", virtualLab.getVirtualLabUseYn());
				resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
				resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
				resultRow.put("classCount", String.valueOf(classCount));
			}

			if (classCount > 0 && virtualLabClass != null) {
				resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
				resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
				resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
				resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
			}

			returnList.add(resultRow);
		}
		return returnList;
	}
	
	public List<Map<String, Object>> getVirtualLabClassRegisterList(long groupId, long userId,long begin, long end,Locale locale) throws SystemException, PortalException {
		List<Object[]> virtualLabClassRegisterList = virtualLabFinder.getVirtualLabClassRegisterList(groupId, userId, begin, end);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < virtualLabClassRegisterList.size(); i++) {
			Object[] resultArray = virtualLabClassRegisterList.get(i);
			
			VirtualLab virtualLab = (VirtualLab) resultArray[0];
			VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
			VirtualLabUser virtualLabUser = (VirtualLabUser) resultArray[2];
			
				resultRow = new HashMap<String, Object>();
				
				if(virtualLab != null) {
					resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
					resultRow.put("userId", String.valueOf(virtualLab.getUserId()));
					resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
					String virtualLabProfessorName = "";
					long professorSeq = virtualLab.getProfessorSeq();
					if(professorSeq != 0){
						Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
						if(professor != null &&professor.getUserId() !=0){
							User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
							if(professorUser != null){
								virtualLabProfessorName = professorUser.getFirstName();
							}
						}
						
					}
					/*교수명 조회*/
					resultRow.put("virtualLabPersonName", virtualLabProfessorName);
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					resultRow.put("groupId", String.valueOf(virtualLab.getGroupId()));
					Group group = GroupLocalServiceUtil.fetchGroup(virtualLab.getGroupId());
					if (group != null) {
						resultRow.put("groupName", group.getName());
					}

				}
				
				if(virtualLabClass != null) {
					resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
					resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
					resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
					resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
				}
				
				if(virtualLabUser != null) {
					resultRow.put("processNote", String.valueOf(virtualLabUser.getProcessNote()));
					resultRow.put("requestSort", String.valueOf(virtualLabUser.getRequestSort()));
					resultRow.put("createDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabUser.getCreateDt()));
				}

			returnList.add(resultRow);
		}
		return returnList;
	}
	
	public Map<String, Object> getVirtualLabClassRegisterInfo(long classId, long userId, long groupId, Locale locale) throws SystemException, PortalException {
		Object[] virtualLabClassUserInfo = virtualLabFinder.getVirtualLabClassRegisterInfo(classId, userId, groupId);
		Map <String, Object> resultRow = null;
		
		if (virtualLabClassUserInfo != null) {
			VirtualLab virtualLab = (VirtualLab) virtualLabClassUserInfo[0];
			VirtualLabClass virtualLabClass = (VirtualLabClass) virtualLabClassUserInfo[1];
			VirtualLabUser virtualLabUser = (VirtualLabUser) virtualLabClassUserInfo[2];
				
			resultRow = new HashMap<String, Object>();
			
			if(virtualLab != null) {
				resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
				resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
				String virtualLabProfessorName = "";
				long professorSeq = virtualLab.getProfessorSeq();
				if(professorSeq != 0){
					Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
					if(professor != null &&professor.getUserId() !=0){
						User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
						if(professorUser != null){
							virtualLabProfessorName = professorUser.getFirstName();
						}
					}
					
				}
				/*교수명 조회*/
				resultRow.put("virtualLabPersonName", virtualLabProfessorName);
//				resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
				resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
				resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
				resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
			}
			
			if(virtualLabClass != null) {
				resultRow.put("classId", virtualLabClass.getClassId());
				resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
				resultRow.put("classStartDt", virtualLabClass.getClassStartDt());
				resultRow.put("classEndDt",virtualLabClass.getClassEndDt());
				resultRow.put("classDescription",virtualLabClass.getClassDescription(locale, true));
				resultRow.put("classPersonnel",virtualLabClass.getClassPersonnel());
				resultRow.put("classCreateDt",virtualLabClass.getClassCreateDt());
			}
			
			if (virtualLabUser != null) {
				resultRow.put("virtualUserId", virtualLabUser.getVirtualLabUserId());
				resultRow.put("userId",virtualLabUser.getUserId());
				resultRow.put("userUseYn",virtualLabUser.getUserUseYn());
				resultRow.put("requestSort",virtualLabUser.getRequestSort());
				resultRow.put("processNote",virtualLabUser.getProcessNote());
				resultRow.put("userStudentNumber", virtualLabUser.getUserStudentNumber());
			}
		}
		return resultRow;
	}
	
	public Map<String, Object> getVirtualLabInfomation(long virtualLabId, Locale locale) throws NoSuchVirtualLabException, SystemException, PortalException {
		VirtualLab virtualLab = virtualLabPersistence.fetchByPrimaryKey(virtualLabId);
		Map <String, Object> resultRow = null;
		long professorSeq = 0;
		if (virtualLab != null) {
			resultRow = new HashMap<String, Object>();
			User user = UserLocalServiceUtil.fetchUserById(virtualLab.getUserId());
			if (user != null) {
				resultRow.put("userName", user.getFullName());
				resultRow.put("userScreenName", user.getScreenName());
			} else {
				resultRow.put("userName", virtualLab.getUserId());
				resultRow.put("userScreenName", virtualLab.getUserId());
			}
			
			resultRow.put("groupId", virtualLab.getGroupId());
			resultRow.put("virtualLabTitleMap", virtualLab.getVirtualLabTitle());
//			resultRow.put("virtualLabPersonNameMap", virtualLab.getVirtualLabPersonName());
			resultRow.put("virtualLabDescriptionMap", virtualLab.getVirtualLabDescription());
			resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
			resultRow.put("requestUserId", String.valueOf(virtualLab.getUserId()));
			resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
			
			/*if(professorSeq == virtualLab.getProfessorSeq()){
				resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
			}else{
				resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName());
			}*/
			
			if(virtualLab.getProfessorSeq() != 0){
				Professor labProfessor = ProfessorLocalServiceUtil.getProfessor(virtualLab.getProfessorSeq());
				if(labProfessor.getUserId() != 0){
					User professor = UserLocalServiceUtil.fetchUserById(labProfessor.getUserId());
					if (professor != null) {
						resultRow.put("professorFirstName", professor.getFirstName());
						resultRow.put("professorMiddleName", professor.getMiddleName());
						resultRow.put("professorLastName", professor.getLastName());
					}			
				}
			}
			
			resultRow.put("virtualLabDescription", virtualLab.getVirtualLabDescription(locale, true));
			resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
			resultRow.put("virtualLabUseYn", virtualLab.getVirtualLabUseYn());
			resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
			resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
			resultRow.put("virtualLabProfessorSeq", virtualLab.getProfessorSeq());
			resultRow.put("virtualLabUniversityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
			resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
			resultRow.put("virtualLabConfirmDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabConfirmDt()));
			resultRow.put("iconId", virtualLab.getIconId());
			
			// 파일 - icon
			if(virtualLab.getIconId() != 0){
				try{
					DLFileEntry iconDl = DLFileEntryLocalServiceUtil.getDLFileEntry(virtualLab.getIconId());
					resultRow.put("iconRepositoryId", iconDl.getRepositoryId());
					resultRow.put("iconUuid", iconDl.getUuid());
					resultRow.put("iconTitle", iconDl.getTitle());
				}catch (Exception e){
					log.error("getVirtualLabInfomation DL Error", e);
				}
			}
		}
		return resultRow;
	}
	
	public VirtualLab updateVirtualLabStatus(Map<String, String> params) throws NoSuchVirtualLabException, SystemException {
		VirtualLab virtualLab = virtualLabPersistence.findByPrimaryKey(Long.parseLong(params.get("virtualLabId")));
		
		virtualLab.setVirtualLabStatus(String.valueOf(params.get("virtualLabStatus")));
		virtualLab.setVirtualLabConfirmDescription(String.valueOf(params.get("virtualLabConfirmDescription")));
		virtualLab.setVirtualLabConfirmDt(new Date());
		
		virtualLab = virtualLabPersistence.update(virtualLab);
		
		return virtualLab;
	}
	
	public List<Map<String, Object>> getListVirtualLab(Map<String, Object> params, Locale locale) throws SystemException, PortalException {
		
		//실험실, 소속, 지도교수
		String searchField = CustomUtil.strNull(params.get("searchField"));
		String searchUniversityField = "";
		if(!searchField.equals("")){
			List<Map<String, String>> commonCodeList = EdisonExpndoUtil.getCodeListByUpCode(1501, locale);
			for(Map<String,String> codeMap : commonCodeList){
				String codeName = CustomUtil.strNull(codeMap.get(EdisonExpando.CDNM)).toUpperCase();
				if(codeName.indexOf(searchField.toUpperCase())>-1){
					String codeValue = codeMap.get(EdisonExpando.CD);
					if(searchUniversityField.equals("")){
						searchUniversityField += codeValue;
					}else{
						searchUniversityField += ","+codeValue;
					}
				}
			}
		}
		params.put("searchUniversityField", searchUniversityField);
				
		List<Object[]> virtualLabList = virtualLabFinder.getListVirtualLab(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		if (virtualLabList != null) {
			Map <String, Object> resultRow = null;
			
			for (int i = 0; i < virtualLabList.size(); i++) {
				Object[] resultArray = virtualLabList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				int classCount = (Integer) resultArray[1];
				
				resultRow = new HashMap<String, Object>();
				if (virtualLab != null) {
					resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
					resultRow.put("userId", String.valueOf(virtualLab.getUserId()));
					resultRow.put("groupId", String.valueOf(virtualLab.getGroupId()));
					Group group = GroupLocalServiceUtil.fetchGroup(virtualLab.getGroupId());
					if (group != null) {
						resultRow.put("groupName", group.getName());
					}
					resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
					String virtualLabProfessorName = "";
					long professorSeq = virtualLab.getProfessorSeq();
					if(professorSeq != 0){
						Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
						if(professor != null &&professor.getUserId() !=0){
							User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
							if(professorUser != null){
								virtualLabProfessorName = professorUser.getFirstName();
							}
						}
						
					}
					/*교수명 조회*/
					resultRow.put("virtualLabPersonName", virtualLabProfessorName);
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabStatusNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabStatus(), EdisonExpando.CDNM, locale));
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
					resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					resultRow.put("classCount", String.valueOf(classCount));
					if(virtualLab.getUserId() > 0) {
						User user = userPersistence.fetchByPrimaryKey(virtualLab.getUserId());
						if(user != null) {
							resultRow.put("userScreenName", user.getScreenName());
							resultRow.put("userFirstName", user.getFirstName());
							resultRow.put("userName", user.getFullName() + "(" + user.getScreenName() + ")");
							resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
						} else {
							resultRow.put("userScreenName", virtualLab.getUserId());
							resultRow.put("userFirstName", virtualLab.getUserId());
							resultRow.put("userName", virtualLab.getUserId() + "(" + virtualLab.getUserId() + ")");
							resultRow.put("virtualLabOwnerName", virtualLab.getUserId() + "(" + virtualLab.getUserId() + ")");
						}
					}
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	
public List<Map<String, Object>> getListVirtualLabManagement(Map<String, Object> params, Locale locale) throws SystemException {
		
		List<Object[]> virtualLabList = virtualLabFinder.getListVirtualLabManagement(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		if (virtualLabList != null) {
			Map <String, Object> resultRow = null;
			
			for (int i = 0; i < virtualLabList.size(); i++) {
				Object[] resultArray = virtualLabList.get(i);
				
				resultRow = new HashMap<String, Object>();
				
				if (resultArray != null) {
					resultRow.put("virtualLabId", CustomUtil.strNull(resultArray[0]));
					resultRow.put("userId", CustomUtil.strNull(resultArray[1]));
					resultRow.put("groupId", CustomUtil.strNull(resultArray[2]));
					Group group = GroupLocalServiceUtil.fetchGroup(Long.parseLong(CustomUtil.strNull(resultArray[2])));
					if (group != null) {
						resultRow.put("groupName", group.getName());
					}
					resultRow.put("virtualLabTitle", CustomUtil.strNull(resultArray[3]));
					resultRow.put("virtualLabPersonName", CustomUtil.strNull(resultArray[4]));
					resultRow.put("virtualLabConfirmDescription", CustomUtil.strNull(resultArray[5]));
					resultRow.put("virtualLabStatus", CustomUtil.strNull(resultArray[6]));
					resultRow.put("virtualLabStatusNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(resultArray[6]), EdisonExpando.CDNM, locale));
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(resultArray[7]));
					resultRow.put("virtualLabConfirmDt", new SimpleDateFormat("yyyy-MM-dd").format(resultArray[9]));
					resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(resultArray[8]), EdisonExpando.CDNM, locale));
					if(Long.parseLong(CustomUtil.strNull(resultArray[1])) > 0) {
						User user = userPersistence.fetchByPrimaryKey(Long.parseLong(CustomUtil.strNull(resultArray[1])));
						if(user != null) {
							resultRow.put("userScreenName", user.getScreenName());
							resultRow.put("userFirstName", user.getFirstName());
							resultRow.put("userName", user.getFullName() + "(" + user.getScreenName() + ")");
							resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
						} else {
							resultRow.put("userScreenName", CustomUtil.strNull(resultArray[1]));
							resultRow.put("userFirstName", CustomUtil.strNull(resultArray[1]));
							resultRow.put("userName", CustomUtil.strNull(resultArray[1]) + "(" + CustomUtil.strNull(resultArray[1]) + ")");
							resultRow.put("virtualLabOwnerName", CustomUtil.strNull(resultArray[1]) + "(" + CustomUtil.strNull(resultArray[1]) + ")");
						}
					}
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}

public List<Map<String, Object>> getVirtualLabGroup() throws SystemException {
	
	List<Object[]> virtualLabList = virtualLabFinder.getVirtualLabGroup();
	
	List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	
	if (virtualLabList != null) {
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < virtualLabList.size(); i++) {
			Object[] resultArray = virtualLabList.get(i);
			
			resultRow = new HashMap<String, Object>();
			
			if (resultArray != null) {
				resultRow.put("groupId", CustomUtil.strNull(resultArray[0]));
				resultRow.put("name", CustomUtil.strNull(resultArray[1]));
			}
			
			
			returnList.add(resultRow);
		}
		
		
	}
	return returnList;
}
	
	/** 강좌 리스트 조회(클래스가 현재 날짜 기준으로 6개월안에 포함된 데이터)    */
	public List<Map<String, Object>> getVirtualLabList(Map<String, Object> params, Locale locale) throws SystemException,PortalException {
		
		//실험실, 소속, 지도교수
		String searchField = CustomUtil.strNull(params.get("searchField"));
		String searchUniversityField = "";
		String virtualLabUniversityField = CustomUtil.strNull(params.get("virtualLabUniversityField"));
		
		if(!searchField.equals("") && "".equals(virtualLabUniversityField)){
			List<Map<String, String>> commonCodeList = EdisonExpndoUtil.getCodeListByUpCode(1501, locale);
			for(Map<String,String> codeMap : commonCodeList){
				String codeName = CustomUtil.strNull(codeMap.get(EdisonExpando.CDNM)).toUpperCase();
				if(codeName.indexOf(searchField.toUpperCase())>-1){
					String codeValue = codeMap.get(EdisonExpando.CD);
					if(searchUniversityField.equals("")){
						searchUniversityField += codeValue;
					}else{
						searchUniversityField += ","+codeValue;
					}
				}
			}
		}
		
		params.put("searchUniversityField", searchUniversityField);
		String addClass = "addClass";
		String attending = "attending";
		
		if(params.get("searchType").equals("addClass")){
			params.put("addClass", addClass);
			params.put("attending", "");
		}else if(params.get("searchType").equals("attending")){
			params.put("attending", attending);
			params.put("addClass", "");
		}else{
			params.put("addClass", "");
			params.put("attending", "");
		}
		
		List<Object[]> virtualLabList = virtualLabFinder.getVirtualLabList(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		if (virtualLabList != null) {
			Map <String, Object> resultRow = null;
			
			for (int i = 0; i < virtualLabList.size(); i++) {
				Object[] resultArray = virtualLabList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				int classCount = (Integer) resultArray[1];
				
				resultRow = new HashMap<String, Object>();
				if (virtualLab != null) {
					resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
					resultRow.put("userId", String.valueOf(virtualLab.getUserId()));
					resultRow.put("groupId", String.valueOf(virtualLab.getGroupId()));
					Group group = GroupLocalServiceUtil.fetchGroup(virtualLab.getGroupId());
					if (group != null) {
						resultRow.put("groupName", group.getName());
					}
					resultRow.put("virtualLabTitle", virtualLab.getVirtualLabTitle(locale, true));
					String virtualLabProfessorName = "";
					long professorSeq = virtualLab.getProfessorSeq();
					if(professorSeq != 0){
						Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
						if(professor != null &&professor.getUserId() !=0){
							User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
							if(professorUser != null){
								virtualLabProfessorName = professorUser.getFirstName();
							}
						}
						
					}
					/*교수명 조회*/
					resultRow.put("virtualLabPersonName", virtualLabProfessorName);
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabStatusNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabStatus(), EdisonExpando.CDNM, locale));
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
					resultRow.put("virtualLabUniversityName", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
					resultRow.put("classCount", String.valueOf(classCount));
					resultRow.put("virtualLabDescription",virtualLab.getVirtualLabDescription(locale, true));
					resultRow.put("iconId",virtualLab.getIconId());
					
					if(virtualLab.getUserId() > 0) {
						User user = userPersistence.fetchByPrimaryKey(virtualLab.getUserId());
						if(user != null) {
							resultRow.put("userScreenName", user.getScreenName());
							resultRow.put("userFirstName", user.getFirstName());
							resultRow.put("userName", user.getFullName() + "(" + user.getScreenName() + ")");
							resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
						} else {
							resultRow.put("userScreenName", virtualLab.getUserId());
							resultRow.put("userFirstName", virtualLab.getUserId());
							resultRow.put("userName", virtualLab.getUserId() + "(" + virtualLab.getUserId() + ")");
							resultRow.put("virtualLabOwnerName", virtualLab.getUserId() + "(" + virtualLab.getUserId() + ")");
						}
					}
					// 파일 - icon
					if(virtualLab.getIconId() != 0){
						try{
							DLFileEntry iconDl = DLFileEntryLocalServiceUtil.getDLFileEntry(virtualLab.getIconId());
							resultRow.put("iconRepositoryId", iconDl.getRepositoryId());
							resultRow.put("iconUuid", iconDl.getUuid());
							resultRow.put("iconTitle", iconDl.getTitle());
						}catch (Exception e){
						    log.error("getVirtualLabInfomation DL Error", e);
						}
					}
					
				}
				
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	public List<Map<String, Object>> getVirtualLabTabList(Map<String, Object> params, Locale locale) throws SystemException {
		
		String addClass = "addClass";
		String attending = "attending";
		//실험실, 소속, 지도교수
		String searchField = CustomUtil.strNull(params.get("searchField"));
		String searchUniversityField = "";
		
		if(!searchField.equals("")){
			List<Map<String, String>> commonCodeList = EdisonExpndoUtil.getCodeListByUpCode(1501, locale);
			for(Map<String,String> codeMap : commonCodeList){
				String codeName = CustomUtil.strNull(codeMap.get(EdisonExpando.CDNM)).toUpperCase();
				if(codeName.indexOf(searchField.toUpperCase())>-1){
					String codeValue = codeMap.get(EdisonExpando.CD);
					if(searchUniversityField.equals("")){
						searchUniversityField += codeValue;
					}else{
						searchUniversityField += ","+codeValue;
					}
				}
			}
		}
		
		params.put("searchUniversityField", searchUniversityField);
		
		if(params.get("searchType").equals("addClass")){
			params.put("addClass", addClass);
			params.put("attending", "");
		}else if(params.get("searchType").equals("attending")){
			params.put("attending", attending);
			params.put("addClass", "");
		}else{
			params.put("addClass", "");
			params.put("attending", "");
		}
		
		List<Object[]> virtualLabList = virtualLabFinder.getVirtualLabTabList(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		if (virtualLabList != null) {
			Map <String, Object> resultRow = null;
			
			for (int i = 0; i < virtualLabList.size(); i++) {
				Object[] resultArray = virtualLabList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				int count = (Integer) resultArray[1];
				
				resultRow = new HashMap<String, Object>();
				if (virtualLab != null) {
					resultRow.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
					resultRow.put("userId", String.valueOf(virtualLab.getUserId()));
					resultRow.put("groupId", String.valueOf(virtualLab.getGroupId()));
					resultRow.put("virtualLabUniversityName", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
					resultRow.put("count", String.valueOf(count));
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	public int getCountVirtualLab(Map<String, Object> params, Locale locale) {
		return virtualLabFinder.getCountVirtualLab(params, locale);
	}
	
	public int getCountVirtualLabClassRegisterList(long groupId, long userId,Locale locale) {
		return virtualLabFinder.getCountVirtualLabClassRegisterList(groupId, userId, locale);
	}
	
	public int getCountVirtualLabList(Map<String, Object> params, Locale locale) {
		return virtualLabFinder.getCountVirtualLabList(params, locale);
	}
	
	
	public VirtualLab updateVirtualLabInfomation(UploadPortletRequest upload, PortletRequest request, long groupId, long userId, Map<String, String> params, Locale locale) throws NoSuchVirtualLabException, NumberFormatException, SystemException, PortalException, SQLException, IOException {
		VirtualLab virtualLab = virtualLabPersistence.findByPrimaryKey(Long.parseLong(params.get("virtualLabId")));
		virtualLab.setVirtualLabUniversityField(CustomUtil.strNull(params.get("universityField")));
		virtualLab.setVirtualLabDescriptionMap(CustomUtil.getLocalizationMap(params,"virtualLabDescription"));
		virtualLab.setVirtualLabTitleMap(CustomUtil.getLocalizationMap(params,"virtualLabTitle"));
//		virtualLab.setVirtualLabPersonName(CustomUtil.strNull(params.get("virtualLabProfessor")));
		virtualLab.setProfessorSeq(Long.parseLong(params.get("professorSeq")));
		
		//아이콘 업데이트
		String appIconStr = CustomUtil.strNull(upload.getFileName("course_icon"), "");
		if(!appIconStr.equals("")){ // 아이콘이 있는경우
			long iconId = virtualLab.getIconId();
			if(iconId != 0){
				// 기존 파일 삭제
				try{
					DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
				}catch(NoSuchFileEntryException e){}
			}
			List<FileEntry> courseIcon = EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", params.get("virtualLabId"), "course_icon", EdisonFileConstants.COURSE);
			FileEntry appEntry = courseIcon.get(0);
			virtualLab.setIconId(appEntry.getFileEntryId());
		}
			virtualLab = virtualLabPersistence.update(virtualLab);
			return virtualLab;
	}
	
	public VirtualLab updateVirtualLabDisable(long virtualLabId, String disableFlag) throws NoSuchVirtualLabException, NumberFormatException, SystemException {
		VirtualLab virtualLab = virtualLabPersistence.fetchByPrimaryKey(virtualLabId);
		
		if(virtualLab != null) {
			if(disableFlag.equals("Y") || disableFlag.equals("N")) {
				virtualLab.setVirtualLabUseYn(disableFlag);
				virtualLab = virtualLabPersistence.update(virtualLab);
			}
		}
		
		return virtualLab;
	}
	
	@Transactional
	public void transferVirtualLabOwner(long virtualLabId, long userId, long roleId, long companyId) throws NumberFormatException, SystemException, PortalException {
		VirtualLab virtualLab = virtualLabPersistence.fetchByPrimaryKey(virtualLabId);
		if (virtualLab != null) {
			UserGroupRoleCustom userGroupRoleCustom = UserGroupRoleCustomLocalServiceUtil.getUserGroupRoleCustom(virtualLab.getUserId(), virtualLab.getGroupId(), roleId, virtualLabId);
			Role labManagerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
			Role classOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
			Role classManagerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);

			long previousOwnerId = virtualLab.getUserId();
			long groupId = virtualLab.getGroupId();
			// 실험실 관리자 권한 삭제
			if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, labManagerRole.getRoleId(), virtualLabId)) {
				UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(userId, groupId, labManagerRole.getRoleId(), virtualLabId);
			}
			
			// 클래스 리스트 검색 및 Class Owner 소유자 이전, Class Manager 권한 삭제
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("virtualLabId", virtualLabId);
			params.put("groupId", groupId);
			
			List<Map<String, Object>> classList = VirtualLabClassLocalServiceUtil.getVirtualClassList(params, null);
			if(classList != null) {
				for (Map<String, Object> map : classList) {
					long classId = GetterUtil.get(map.get("classId"), 0L);
					if(classId != 0) {
						// 클래스 매니저로 지정되어 있을경우 매니저 권한 삭제
						if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, classManagerRole.getRoleId(), classId)) {
							UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(userId, groupId, classManagerRole.getRoleId(), classId);
						}
						// 이전 실험실 소유주가 클래스 소유 권한을 가지고 있을경우 이전
						if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(previousOwnerId, groupId, classOwnerRole.getRoleId(), classId)) {
							UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(previousOwnerId, groupId, classOwnerRole.getRoleId(), classId);
							UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId,  classOwnerRole.getRoleId(), classId);
						}
					}
				}
			}
			if(userGroupRoleCustom != null) {
				
				userGroupRoleCustom.setUserId(userId);
				virtualLab.setUserId(userId);
				
				virtualLabPersistence.update(virtualLab);
				UserGroupRoleCustomLocalServiceUtil.updateUserGroupRoleCustom(userGroupRoleCustom);
				
				User user = userPersistence.findByPrimaryKey(userId);
				EdisonUserUtil.addSiteRole(user, virtualLab.getGroupId(), EdisonRoleConstants.VIRTUAL_LAB_OWNER);	// VIRTUAL LAB OWNER 권한 추가
				
				UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(previousOwnerId, groupId, roleId, virtualLabId); // 이전 소유자 권한 삭제
				
				List<Map<String,String>> customList =  UserGroupRoleCustomLocalServiceUtil.getCustomList(previousOwnerId, groupId, roleId); // 이전 소유자 나머지 소유 갯수 체크
				
				if (customList == null || customList.size() == 0) {	// VIRTUAL LAB OWNER CUSTOM ROLE이 남아있는지 체크
					EdisonUserUtil.deleteSiteRole(UserLocalServiceUtil.getUser(previousOwnerId), groupId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);	// 없으면 삭제
				}
			} else {
				UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId, roleId, virtualLabId);
				
				virtualLab.setUserId(userId);
				virtualLabPersistence.update(virtualLab);
				
				User user = userPersistence.findByPrimaryKey(userId);
				EdisonUserUtil.addSiteRole(user, virtualLab.getGroupId(), EdisonRoleConstants.VIRTUAL_LAB_OWNER);	// VIRTUAL LAB OWNER 권한 추가
			}
		}
	}
}