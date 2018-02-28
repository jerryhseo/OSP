/**
 * Copyright (c) 2016-EDISON, KISTI. All rights reserved.
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassException;
import org.kisti.edison.virtuallaboratory.NoSuchVirtualLabException;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClass;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.base.VirtualLabClassLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the virtual lab class local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author jksang
 * @see org.kisti.edison.virtuallaboratory.service.base.VirtualLabClassLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil
 */
public class VirtualLabClassLocalServiceImpl
	extends VirtualLabClassLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil} to access the virtual lab class local service.
	 */

	public Map<String, String> getVirtualClassMainVisual(long classId, Locale locale) throws SystemException, PortalException {
		Object[] result = virtualLabClassFinder.getVirtualClassInfo(classId);
		Map<String, String> resultParam = null;

		if (result != null) {
			VirtualLab virtualLab = (VirtualLab) result[0];
			VirtualLabClass virtualLabClass = (VirtualLabClass) result[1];
			
			if (virtualLab != null && virtualLabClass != null) {
				resultParam = new HashMap<String, String>();
				resultParam.put("classTitle", virtualLabClass.getClassTitle(locale, true));
				resultParam.put("classId", String.valueOf(virtualLabClass.getClassId()));
				resultParam.put("classDescription", virtualLabClass.getClassDescription(locale, true));
				resultParam.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
				resultParam.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
				resultParam.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
				resultParam.put("classStartDt", virtualLabClass.getClassStartDt());
				resultParam.put("classEndDt", virtualLabClass.getClassEndDt());
				resultParam.put("classUseYn", virtualLabClass.getClassUseYn());
				resultParam.put("virtualClassCd", virtualLabClass.getVirtualClassCd());
				resultParam.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
				resultParam.put("virtualLabUniversityFieldNM", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
				resultParam.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
				resultParam.put("virtualLabTitle", String.valueOf(virtualLab.getVirtualLabTitle(locale)));
				
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
				resultParam.put("virtualLabPersonName", virtualLabProfessorName);
//				resultParam.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
				
				resultParam.put("groupId", String.valueOf(virtualLab.getGroupId()));
			}
		}
		
		return resultParam;
	}
	
	public Map<String, Object> getVirtualLabClassInfo(long classId, Locale locale) throws NoSuchVirtualLabClassException, SystemException {
		Map <String, Object> resultRow = null;
		if (classId > 0) {
			resultRow = new HashMap<String, Object>();
			VirtualLabClass virtualLabClass = virtualLabClassPersistence.findByPrimaryKey(classId);
			if (virtualLabClass != null) {
				resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
				resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
				resultRow.put("classTitle_en_US", virtualLabClass.getClassTitle(Locale.US, true));
				resultRow.put("classTitle_ko_KR", virtualLabClass.getClassTitle(Locale.KOREA, true));
				
				resultRow.put("classTitleMap", virtualLabClass.getClassTitle());
				resultRow.put("classDescription", virtualLabClass.getClassDescription(locale, true));
				resultRow.put("classDescription_en_US", virtualLabClass.getClassDescription(Locale.US, true));
				resultRow.put("classDescription_ko_KR", virtualLabClass.getClassDescription(Locale.KOREA, true));
				resultRow.put("classDescriptionMap", virtualLabClass.getClassDescription());
				resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
				resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
				resultRow.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
				resultRow.put("classCurriculumUrl", virtualLabClass.getClassCurriculumUrl());
				resultRow.put("classStartDt", virtualLabClass.getClassStartDt());
				resultRow.put("classUseYn", virtualLabClass.getClassUseYn());
				resultRow.put("classEndDt", virtualLabClass.getClassEndDt());
			}
		}
		return resultRow;
	}
	
	public List<Map<String, Object>> getVirtualClassList(Map<String, Object> params, Locale locale) throws SystemException, PortalException {
		List<Object[]> virtualClassList = virtualLabClassFinder.getVirtualClassList(params);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		
		for (int i = 0; i < virtualClassList.size(); i++) {
			Object[] resultArray = virtualClassList.get(i);
			
			if(resultArray != null) {
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
				int userCount = (Integer) resultArray[2];
				
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
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabUseYn", virtualLab.getVirtualLabUseYn());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
//					resultRow.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
				}
				
				if (virtualLabClass != null) {
					resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
					resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
					resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
					resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
					resultRow.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
					resultRow.put("classStartDt", virtualLabClass.getClassStartDt());
					resultRow.put("classUseYn", virtualLabClass.getClassUseYn());
					resultRow.put("classEndDt", virtualLabClass.getClassEndDt());
					resultRow.put("userCount", userCount);
				}
				
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	public int getVirtualClassListCount(Map<String, Object> params) {
		return virtualLabClassFinder.getVirtualClassListCount(params);
	}
	
	public VirtualLabClass insertVirtualLabClass(Map<String, Object> params, Locale locale) throws NoSuchModelException, SystemException {
		Long classId = (Long) params.get("classId");
		Long virtualLabId = (Long) params.get("virtualLabId");
		VirtualLabClass virtualLabClass = null;
		if(classId > 0) {
			virtualLabClass = virtualLabClassPersistence.findByPrimaryKey(classId);
		} else {
			virtualLabClass = virtualLabClassPersistence.create(CounterLocalServiceUtil.increment(VirtualLabClass.class.getName()));
			virtualLabClass.setClassCreateDt(new Date());
			virtualLabClass.setVirtualClassCd("c" + new DecimalFormat("0000").format(virtualLabClass.getClassId()));
			virtualLabClass.setClassUseYn("Y");
		}
		virtualLabClass.setClassTitleMap(CustomUtil.getLocalizationMap(params,"classTitle"));
		virtualLabClass.setClassDescriptionMap(CustomUtil.getLocalizationMap(params,"classDescription"));
		virtualLabClass.setClassStartDt((String) params.get("classStartDt"));
		virtualLabClass.setClassEndDt((String) params.get("classEndDt"));
		virtualLabClass.setClassPersonnel((Integer) params.get("classPersonnel"));
		virtualLabClass.setClassCurriculumUrl((String) params.get("classCurriculumUrl"));
		virtualLabClass.setClassExternalPersonnel((Integer) params.get("classExternalPersonnel"));
		virtualLabClass = virtualLabClassPersistence.update(virtualLabClass);
		virtualLabPersistence.addVirtualLabClass(virtualLabId, virtualLabClass);
		
		if(classId == 0){
			List<VirtualLabScienceAppLink> scienceAppLink = virtualLabScienceAppLinkPersistence.findByVirtualLabId(virtualLabId);
			for(VirtualLabScienceAppLink scienceAppLinkClass : scienceAppLink){
				VirtualLabScienceAppLinkClass virtualLabClassScienceAppTarget = virtualLabScienceAppLinkClassPersistence.create(counterLocalService.increment("scienceAppClassSeqNo"));
				virtualLabClassScienceAppTarget.setScienceAppSeqNo(scienceAppLinkClass.getScienceAppSeqNo());		
				virtualLabClassScienceAppTarget.setClassId(virtualLabClass.getClassId());
				VirtualLabScienceAppLinkClassLocalServiceUtil.addVirtualLabScienceAppLinkClass(virtualLabClassScienceAppTarget);
			}
		}
		
		return virtualLabClass;
	}
	
	public List<Map<String, Object>> getListVirtualLabClassManagement(Map<String, Object> params, Locale locale) throws SystemException, PortalException {
		
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
		
		List<Object[]> virtualLabClassList = virtualLabClassFinder.getListVirtualLabClassManagement(params, locale);
		
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		if (virtualLabClassList != null) {
			
			for (int i = 0; i < virtualLabClassList.size(); i++) {
				Object[] resultArray = virtualLabClassList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
				int classCount = (Integer) resultArray[2];
				int tempUserCount = (Integer) resultArray[3];
				int userCount = (Integer) resultArray[4];
				
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
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
					resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
					resultRow.put("virtualLabUniversityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					try {
						User user = userPersistence.findByPrimaryKey(virtualLab.getUserId());
						resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
					} catch (NoSuchUserException e) {
						resultRow.put("virtualLabOwnerName", String.valueOf(virtualLab.getUserId()));
					}
				}
				
				if (virtualLabClass != null) {
					resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
					resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
					resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
					resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
					resultRow.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
					resultRow.put("classCount", String.valueOf(classCount));
					resultRow.put("classUseYn", virtualLabClass.getClassUseYn());
					resultRow.put("tempUserCount", tempUserCount);
					resultRow.put("userCount", userCount);
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	public List<Map<String, Object>> getListVirtualLabClass(Map<String, Object> params, Locale locale) throws SystemException, PortalException {
		
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
		long companyId = Long.parseLong(CustomUtil.strNull(params.get("companyId"),"0"));
		long curUserId = Long.parseLong(CustomUtil.strNull(params.get("curUserId"),"0"));
		
		
		Role virtualLabManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		
		List<Object[]> virtualLabClassList = virtualLabClassFinder.getListVirtualLabClass(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		if (virtualLabClassList != null) {
			
			for (int i = 0; i < virtualLabClassList.size(); i++) {
				Object[] resultArray = virtualLabClassList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
				int classCount = (Integer) resultArray[2];
				int tempUserCount = (Integer) resultArray[3];
				int userCount = (Integer) resultArray[4];
				
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
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
					resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
					resultRow.put("virtualLabUniversityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					
					resultRow.put("virtualLabManagerYn", UserGroupRoleCustomLocalServiceUtil.isRoleCustom(curUserId, virtualLab.getGroupId(), virtualLabManager.getRoleId(), virtualLab.getVirtualLabId()));
					resultRow.put("virtualLabOwnerYn", UserGroupRoleCustomLocalServiceUtil.isRoleCustom(curUserId, virtualLab.getGroupId(), virtualLabOwner.getRoleId(), virtualLab.getVirtualLabId()));
					
					try {
						User user = userPersistence.findByPrimaryKey(virtualLab.getUserId());
						resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
					} catch (NoSuchUserException e) {
						resultRow.put("virtualLabOwnerName", String.valueOf(virtualLab.getUserId()));
					}
				}
				
				if (virtualLabClass != null) {
					resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
					resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
					resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
					resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
					resultRow.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
					resultRow.put("classCount", String.valueOf(classCount));
					resultRow.put("classUseYn", virtualLabClass.getClassUseYn());
					resultRow.put("tempUserCount", tempUserCount);
					resultRow.put("userCount", userCount);
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	
public List<Map<String, Object>> getVirtualLabClassList(Map<String, Object> params, Locale locale) throws SystemException, PortalException {
		
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
		long companyId = Long.parseLong(CustomUtil.strNull(params.get("companyId"),"0"));
		long curUserId = Long.parseLong(CustomUtil.strNull(params.get("curUserId"),"0"));
		
		
		Role virtualLabManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		
		List<Object[]> virtualLabClassList = virtualLabClassFinder.getVirtualLabClassList(params, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		if (virtualLabClassList != null) {
			
			for (int i = 0; i < virtualLabClassList.size(); i++) {
				Object[] resultArray = virtualLabClassList.get(i);
				
				VirtualLab virtualLab = (VirtualLab) resultArray[0];
				VirtualLabClass virtualLabClass = (VirtualLabClass) resultArray[1];
				int userCount = (Integer) resultArray[2];
				
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
//					resultRow.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(locale, true));
					resultRow.put("virtualLabConfirmDescription", virtualLab.getVirtualLabConfirmDescription());
					resultRow.put("virtualLabStatus", virtualLab.getVirtualLabStatus());
					resultRow.put("virtualLabRequestDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLab.getVirtualLabRequestDt()));
					resultRow.put("virtualLabUniversityField", virtualLab.getVirtualLabUniversityField());
					resultRow.put("virtualLabUniversityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM, locale));
					
					resultRow.put("virtualLabManagerYn", UserGroupRoleCustomLocalServiceUtil.isRoleCustom(curUserId, virtualLab.getGroupId(), virtualLabManager.getRoleId(), virtualLab.getVirtualLabId()));
					resultRow.put("virtualLabOwnerYn", UserGroupRoleCustomLocalServiceUtil.isRoleCustom(curUserId, virtualLab.getGroupId(), virtualLabOwner.getRoleId(), virtualLab.getVirtualLabId()));
					
					try {
						User user = userPersistence.findByPrimaryKey(virtualLab.getUserId());
						resultRow.put("virtualLabOwnerName", user.getFirstName() + "(" + user.getScreenName() + ")");
					} catch (NoSuchUserException e) {
						resultRow.put("virtualLabOwnerName", String.valueOf(virtualLab.getUserId()));
					}
				}
				
				if (virtualLabClass != null) {
					resultRow.put("classId", String.valueOf(virtualLabClass.getClassId()));
					resultRow.put("classTitle", virtualLabClass.getClassTitle(locale, true));
					resultRow.put("classCreateDt", new SimpleDateFormat("yyyy-MM-dd").format(virtualLabClass.getClassCreateDt()));
					resultRow.put("classPersonnel", String.valueOf(virtualLabClass.getClassPersonnel()));
					resultRow.put("classExternalPersonnel", String.valueOf(virtualLabClass.getClassExternalPersonnel()));
					resultRow.put("classStartDt", virtualLabClass.getClassStartDt());
					resultRow.put("classEndDt", virtualLabClass.getClassEndDt());
					resultRow.put("classUseYn", virtualLabClass.getClassUseYn());
					resultRow.put("userCount", userCount);
				}
				returnList.add(resultRow);
			}
		}
		return returnList;
	}
	
	public int getCountVirtualLabManagementClass(Map<String, Object> params, Locale locale) {
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
		
		return virtualLabClassFinder.getCountVirtualLabManagementClass(params, locale);
	}
	
	public int getCountVirtualLabClass(Map<String, Object> params, Locale locale) {
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
		
		return virtualLabClassFinder.getCountVirtualLabClass(params, locale);
	}
	
	public int getCountVirtualLabClassList(Map<String, Object> params, Locale locale) {
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
		
		return virtualLabClassFinder.getCountVirtualLabClassList(params, locale);
	}

	public VirtualLabClass updateVirtualLabClassDisable(long classId, String disableFlag) throws NoSuchVirtualLabException, NumberFormatException, SystemException {
		VirtualLabClass virtualLabClass = virtualLabClassPersistence.fetchByPrimaryKey(classId);
		
		if(virtualLabClass != null) {
			if(disableFlag.equals("Y") || disableFlag.equals("N")) {
				virtualLabClass.setClassUseYn(disableFlag);
				virtualLabClass = virtualLabClassPersistence.update(virtualLabClass);
			}
		}
		
		return virtualLabClass;
	}
	
	public List<Long> getVirtualClassBoardSeqList(long groupId, long classId) {
		return virtualLabClassFinder.getVirtualClassBoardSeqList(groupId, classId);
	}
	
}