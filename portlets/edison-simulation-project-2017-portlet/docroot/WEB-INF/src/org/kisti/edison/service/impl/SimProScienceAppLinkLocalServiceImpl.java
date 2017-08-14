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

package org.kisti.edison.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.SimProScienceAppLink;
import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;
import org.kisti.edison.service.base.SimProScienceAppLinkLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the sim pro science app link local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.SimProScienceAppLinkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.service.base.SimProScienceAppLinkLocalServiceBaseImpl
 * @see org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil
 */
public class SimProScienceAppLinkLocalServiceImpl
	extends SimProScienceAppLinkLocalServiceBaseImpl {

	/**
	 * 사이언스앱 리스트(앱 관리 팝업)  
	 * @param searchField - 검색어
	 * @return List<Map<String, Object>> - 사이언스앱 리스트
	 */
	public List<Map<String, Object>> getScienceAppList(String searchField, Locale locale, int begin, int end) throws PortalException, SystemException {
		String scienceAppIds = "";
		
		List<Object[]> scienceAppList = simProScienceAppLinkFinder.getScienceAppList(searchField, locale.toString(), begin, end);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map <String, Object> resultRow = null;
		
		for(int i = 0; i < scienceAppList.size(); i++) {
			Object[] resultArray = scienceAppList.get(i);
			resultRow = new HashMap<String, Object>();
			resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
			resultRow.put("scienceAppName", CustomUtil.strNull((CustomUtil.strNull(resultArray[1]))));
			resultRow.put("scienceAppTitle", CustomUtil.strNull((CustomUtil.strNull(resultArray[2]))));
			resultRow.put("scienceAppVersion", CustomUtil.strNull(resultArray[3]));
			resultRow.put("userFirstName", CustomUtil.strNull(resultArray[4]));
			
			returnList.add(resultRow);
		}
		
		return returnList;
	}
	
	/**
	 * 사이언스앱 카운트(앱 관리 팝업)   
	 * @param searchField - 검색어
	 * @return int - 사이언스앱 총 개수
	 */
	public int getScienceAppListCount(String searchField, Locale locale) throws PortalException, SystemException {
		return simProScienceAppLinkFinder.getScienceAppListCount(searchField, locale.toString());
	}
	
	/**
	 * 사이언스앱 리스트
	 * @param scienceAppIdList - 사이언스앱 아이디 리스트
	 * @return List<Map<String, Object>> - 사이언스앱 리스트
	 */
	public List<Map<String, Object>> getScienceAppList(String[] scienceAppIdList, Locale locale) throws PortalException, SystemException {
		String scienceAppIds = "";
		scienceAppIds = getScienceAppIds(scienceAppIdList);
		List<Object[]> scienceAppList = new ArrayList<Object[]>();
		
		if(!"0".equals(scienceAppIds)){
			scienceAppList = simProScienceAppLinkFinder.getSelectScienceAppList(scienceAppIds, locale.toString());
		}
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		
		for(int i = 0; i < scienceAppList.size(); i++) {
			Object[] resultArray = scienceAppList.get(i);
			resultRow = new HashMap<String, Object>();
			resultRow.put("scienceAppId", CustomUtil.strNull(resultArray[0]));
			resultRow.put("scienceAppName", CustomUtil.strNull((CustomUtil.strNull(resultArray[1]))));
			resultRow.put("scienceAppVersion", CustomUtil.strNull(resultArray[2]));
			
			long iconId = Long.parseLong(CustomUtil.strNull(resultArray[3], "0"));
			resultRow.put("iconId", iconId);
			resultRow.put("userFirstName", CustomUtil.strNull(resultArray[4]));
			
			if(iconId != 0){
				try{
					DLFileEntry iconDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(iconId);
					resultRow.put("iconRepositoryId", iconDl.getRepositoryId());
					resultRow.put("iconUuid", iconDl.getUuid());
					resultRow.put("iconTitle", iconDl.getTitle());
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			returnList.add(resultRow);
		}
		
		return returnList;
	}
	
	protected String getScienceAppIds(String[] scienceAppIdList){
		String scienceAppIds = "";
		if(scienceAppIdList != null){
			for(String scienceAppId : scienceAppIdList){
				if("".equals(scienceAppIds)){
					scienceAppIds += scienceAppId;
				}else{
					scienceAppIds += ","+scienceAppId;
				}
			}
		}
		
		return CustomUtil.strNull(scienceAppIds, "0");
	}
	
	/**
	 * 사이언스앱 리스트
	 * @param simulationProjectId 시뮬레이션 프로젝트 아이디
	 * @return Map<String, Object> - 사이언스앱 리스트
	 */
	public Map<String, Object> getScienceAppList(long simulationProjectId, Locale locale) throws PortalException, SystemException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
		List<SimProScienceAppLink> simProScienceAppLinkList = simProScienceAppLinkPersistence.findBySimulationProjectId(simulationProjectId);
		String[] scienceAppIdList = new String[simProScienceAppLinkList.size()];
		
		int size = 0;
		if(simProScienceAppLinkList != null && !simProScienceAppLinkList.isEmpty()){
			for(SimProScienceAppLink simProScienceAppLink: simProScienceAppLinkList){
				String scienceAppIdStr = String.valueOf(simProScienceAppLink.getScienceAppId()); 
				scienceAppIdList[size++] = scienceAppIdStr;
			}
		}
		
		if(scienceAppIdList != null && scienceAppIdList.length > 0){
			scienceAppList = SimProScienceAppLinkLocalServiceUtil.getScienceAppList(scienceAppIdList, locale);
		}
		
		if(scienceAppList !=null && !scienceAppList.isEmpty()){
			data.put("scienceAppList", scienceAppList);
		}
		return data;
	}
}