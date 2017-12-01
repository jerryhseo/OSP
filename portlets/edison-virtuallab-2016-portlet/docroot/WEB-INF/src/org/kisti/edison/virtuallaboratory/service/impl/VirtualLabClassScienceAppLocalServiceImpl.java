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

package org.kisti.edison.virtuallaboratory.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.base.VirtualLabClassScienceAppLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * The implementation of the virtual lab class science app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.virtuallaboratory.service.VirtualLabClassScienceAppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.base.VirtualLabClassScienceAppLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.VirtualLabClassScienceAppLocalServiceUtil
 */
public class VirtualLabClassScienceAppLocalServiceImpl
	extends VirtualLabClassScienceAppLocalServiceBaseImpl {
public List<Map<String, Object>> getVirtualLabScienceAppList (long companyId, long virtualLabId, long classId , Locale locale) throws PortalException, SystemException {
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
	
	List<Object[]> scienceAppList = virtualLabClassScienceAppFinder.getVirtualLabScienceAppList(columnId, virtualLabId, classId, 0, locale);
	List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	
	Map <String, Object> resultRow = null;
	for(int i = 0; i < scienceAppList.size(); i++) {
		Object[] resultArray = scienceAppList.get(i);
		resultRow = new HashMap<String, Object>();
		resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
		resultRow.put("scienceAppName", CustomUtil.strNull(CustomUtil.strNull(resultArray[1])));
		resultRow.put("scienceAppTitle", CustomUtil.strNull(LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[2]), locale.getLanguage())));
		resultRow.put("scienceAppVersion", CustomUtil.strNull(resultArray[3]));
		resultRow.put("userId", CustomUtil.strNull(resultArray[4]));
		resultRow.put("virtualLabId", CustomUtil.strNull(resultArray[6]));
		resultRow.put("scienceAppSeqNo", CustomUtil.strNull(resultArray[7]));
		resultRow.put("groupId", CustomUtil.strNull(resultArray[9]));
		
		if(classId != 0){
			resultRow.put("scienceAppCheck", CustomUtil.strNull(resultArray[10]));
		}
		
		String scienceAppUniversityNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(resultArray[5]), EdisonExpando.CDNM, locale);
		resultRow.put("scienceAppUniversityNm", scienceAppUniversityNm);
		resultRow.put("scienceAppUniversityId", CustomUtil.strNull(resultArray[5]));
		try {
			User user = userPersistence.fetchByPrimaryKey((Long) resultArray[4]);
			if(user != null) {
				resultRow.put("userFirstName", user.getFirstName());
			}
			
			//메뉴얼
			long manualId = GetterUtil.getLong(LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[8]), locale.getLanguage()),0l);
			if(manualId !=0){
				resultRow.put("manualId", manualId);
				DLFileEntry manualDl =DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
				resultRow.put("manualRepositoryId", manualDl.getRepositoryId());
				resultRow.put("manualUuid", manualDl.getUuid());
				resultRow.put("manualTitle", manualDl.getTitle());
			}
		
		} catch (NoSuchFileEntryException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			User user = userPersistence.fetchByPrimaryKey((Long) resultArray[4]);
			if(user != null) {
				resultRow.put("userFirstName", user.getFirstName());
			}
		}
		returnList.add(resultRow);
	}
	
	return returnList;
}

public List<Map<String, Object>> getVirtualLabClassScienceAppList (long companyId, long virtualLabId , long classId, Locale locale) throws PortalException, SystemException {
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
	
	List<Object[]> scienceAppList = virtualLabClassScienceAppFinder.getVirtualLabClassScienceAppList(columnId,virtualLabId , classId, locale);
	List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	
	Map <String, Object> resultRow = null;
	for(int i = 0; i < scienceAppList.size(); i++) {
		Object[] resultArray = scienceAppList.get(i);
		resultRow = new HashMap<String, Object>();
		resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
		resultRow.put("scienceAppName", CustomUtil.strNull(CustomUtil.strNull(resultArray[1])));
		resultRow.put("scienceAppTitle", CustomUtil.strNull(LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[2]), locale.getLanguage())));
		resultRow.put("scienceAppVersion", CustomUtil.strNull(resultArray[3]));
		resultRow.put("userId", CustomUtil.strNull(resultArray[4]));
		resultRow.put("classId", CustomUtil.strNull(resultArray[8]));
		resultRow.put("groupId", CustomUtil.strNull(resultArray[10]));
		
		String scienceAppUniversityNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(resultArray[5]), EdisonExpando.CDNM, locale);
		resultRow.put("scienceAppUniversityNm", scienceAppUniversityNm);
		resultRow.put("scienceAppUniversityId", CustomUtil.strNull(resultArray[5]));
		try {
			User user = userPersistence.fetchByPrimaryKey((Long) resultArray[4]);
			if(user != null) {
				resultRow.put("userFirstName", user.getFirstName());
			}
			
			//메뉴얼
			long manualId = GetterUtil.getLong(LocalizationUtil.getLocalization(CustomUtil.strNull(resultArray[9]), locale.getLanguage()),0l);
			if(manualId !=0){
				resultRow.put("manualId", manualId);
				DLFileEntry manualDl =DLFileEntryLocalServiceUtil.getDLFileEntry(manualId);
				resultRow.put("manualRepositoryId", manualDl.getRepositoryId());
				resultRow.put("manualUuid", manualDl.getUuid());
				resultRow.put("manualTitle", manualDl.getTitle());
			}
			
		} catch (NoSuchFileEntryException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			User user = userPersistence.fetchByPrimaryKey((Long) resultArray[4]);
			if(user != null) {
				resultRow.put("userFirstName", user.getFirstName());
			}
		}
		returnList.add(resultRow);
	}
	
	return returnList;
}



public List<Map<String, Object>> getScienceAppList(long virtualLabId, String searchField) throws PortalException, SystemException {
	List<Object[]> scienceAppList = virtualLabClassScienceAppFinder.getScienceAppList(virtualLabId,searchField);
	List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	
	Map <String, Object> resultRow = null;
	
	for(int i = 0; i < scienceAppList.size(); i++) {
		Object[] resultArray = scienceAppList.get(i);
		resultRow = new HashMap<String, Object>();
		resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
		resultRow.put("scienceAppName", CustomUtil.strNull((CustomUtil.strNull(resultArray[1]))));
		resultRow.put("scienceAppVersion", CustomUtil.strNull(resultArray[2]));
		resultRow.put("userFirstName", CustomUtil.strNull(resultArray[3]));
		resultRow.put("virtuallabScienceAppCheck", CustomUtil.strNull(resultArray[4]));
		
		returnList.add(resultRow);
	}
	
	return returnList;
}

public void insertVirtualLabScienceAppList(long companyId, long virtualLabId, long classId, Object scienceAppCheck, Locale locale) throws PortalException, SystemException {
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
	
	List<Object[]> virtualLabScienceAppList = virtualLabClassScienceAppFinder.getVirtualLabScienceAppList(columnId, virtualLabId, classId, 0, locale);
	
	/* 문자 비교 */
	/* source = scienceAppList 기존 정보
	 * target = scienceAppCheck(scienceAppId) 새설정 정보
	 */
	List<String> source = new ArrayList<String>();
	List<String> target = new ArrayList<String>();
	
	if(scienceAppCheck != null){
		if(scienceAppCheck instanceof String) {
			String scienceAppId = (String)scienceAppCheck;
			target.add(scienceAppId);
		}else if(scienceAppCheck instanceof String[]) { 
			String[] scienceAppId = (String[]) scienceAppCheck;
			for(String comScienceApp : scienceAppId) {
				target.add(comScienceApp);
			}
		}
	}
	for(Object[] comScienceAppIdBase : virtualLabScienceAppList ){
		source.add(comScienceAppIdBase[0].toString());
	}
	
	Collection<String> comTargetAppId = CollectionUtils.subtract(target, source);
	Collection<String> comSourceAppId = CollectionUtils.subtract(source, target);
	
	// 기존 설정 삭제
	for(String sourceAppId : comSourceAppId){
		List<Object[]> virtualLabClassesInfo = virtualLabClassScienceAppFinder.getVirtualLabClassesInfo(virtualLabId,Long.parseLong(sourceAppId));
		List<Object[]> virtualLabInfo = virtualLabClassScienceAppFinder.getVirtualLabInfo(virtualLabId,Long.parseLong(sourceAppId));
		
		for(Object[] virtualLabClassInfo : virtualLabClassesInfo ){
			virtualLabScienceAppLinkClassPersistence.remove(Long.parseLong(virtualLabClassInfo[0].toString()));
		}
		for(Object[] virtualLabsInfo : virtualLabInfo){
			virtualLabScienceAppLinkPersistence.remove(Long.parseLong(virtualLabsInfo[0].toString())); 
		}
	}
	
	//새설정 적용
	if (scienceAppCheck != null) {
		for (String targetAppId : comTargetAppId) {
			VirtualLabScienceAppLink virtualLabScienceAppTarget = virtualLabScienceAppLinkPersistence.create(counterLocalService.increment("scienceAppSeqNo"));
			virtualLabScienceAppTarget.setScienceAppId(Long.parseLong(targetAppId));		
			virtualLabScienceAppTarget.setVirtualLabId(virtualLabId);
			VirtualLabScienceAppLinkLocalServiceUtil.addVirtualLabScienceAppLink(virtualLabScienceAppTarget);
		}
	}
}

public void insertVirtualLabClassScienceAppList(long companyId, long virtualLabId, long classId, Object scienceAppCheck, Locale locale) throws PortalException, SystemException {
	ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
	List<Object[]> classScienceAppList = virtualLabClassScienceAppFinder.getVirtualLabClassScienceAppList(columnId, virtualLabId, classId, locale);
	
	/* 문자 비교 */
	/* source = scienceAppList 기존 정보
	 * target = scienceAppCheck(scienceAppId) 새설정 정보
	 */
	List<String> source = new ArrayList<String>();
	List<String> target = new ArrayList<String>();
	
	if(scienceAppCheck != null){
		if(scienceAppCheck instanceof String) {
			String scienceAppId = (String)scienceAppCheck;
			target.add(scienceAppId);
		}else if(scienceAppCheck instanceof String[]) { 
			String[] scienceAppId = (String[]) scienceAppCheck;
			for(String comScienceApp : scienceAppId) {
				target.add(comScienceApp);
			}
		}
	}
	
	for(Object[] comClassScienceAppIdBase : classScienceAppList ){
		source.add(comClassScienceAppIdBase[0].toString());
	}
	
	Collection<String> comTargetAppId = CollectionUtils.subtract(target, source);
	Collection<String> comSourceAppId = CollectionUtils.subtract(source, target);
	
	// 기존 설정 삭제
	for(String sourceAppId : comSourceAppId){
		List<Object[]> virtualLabClassesInfo = virtualLabClassScienceAppFinder.getVirtualLabClassesInfo(virtualLabId,Long.parseLong(sourceAppId));
		
		for(Object[] virtualLabClassInfo : virtualLabClassesInfo ){
			virtualLabScienceAppLinkClassPersistence.remove(Long.parseLong(virtualLabClassInfo[0].toString()));
		}
	}
	
	//새설정 적용
	if (scienceAppCheck != null) {
		for (String targetAppId : comTargetAppId) {
			List<Object[]> virtualLabInfo = virtualLabClassScienceAppFinder.getVirtualLabInfo(virtualLabId,Long.parseLong(targetAppId));
			for(Object[] virtualLabsInfo : virtualLabInfo){
				VirtualLabScienceAppLinkClass virtualLabClassScienceAppTarget = virtualLabScienceAppLinkClassPersistence.create(counterLocalService.increment("scienceAppClassSeqNo"));
				virtualLabClassScienceAppTarget.setScienceAppSeqNo(Long.parseLong(virtualLabsInfo[0].toString()));		
				virtualLabClassScienceAppTarget.setClassId(classId);
				VirtualLabScienceAppLinkClassLocalServiceUtil.addVirtualLabScienceAppLinkClass(virtualLabClassScienceAppTarget);
			}
		}
	}
}
}