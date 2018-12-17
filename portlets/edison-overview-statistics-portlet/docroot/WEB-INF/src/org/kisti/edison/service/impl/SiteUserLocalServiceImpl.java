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
import java.util.Map;

import org.kisti.edison.scheduler.OverviewMonthlySchedule;
import org.kisti.edison.service.base.SiteUserLocalServiceBaseImpl;
import org.kisti.edison.service.persistence.SiteUserFinderUtil;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * The implementation of the site user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.SiteUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.service.base.SiteUserLocalServiceBaseImpl
 * @see org.kisti.edison.service.SiteUserLocalServiceUtil
 */
public class SiteUserLocalServiceImpl extends SiteUserLocalServiceBaseImpl {
	
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.service.SiteUserLocalServiceUtil} to access the site user local service.
	 */

	public int getCategoryTotalCount(Map<String, Object> searchParam) {
		int getScienceAppTotalCount = 0; 
		
		try {
			getScienceAppTotalCount = SiteUserFinderUtil.getCategoryTotalCount(searchParam);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return getScienceAppTotalCount;
	}
	
	public List<Map<String, Object>> getCategoryCountByDate(Map<String, Object> searchParam){
		
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
		
		try {
			String dateFormat = CustomUtil.strNull(searchParam.get("dateFormat"));
			if(dateFormat.equals("MONTH")){
				searchParam.put("dateFormat", "%Y-%m");
			} else {
				searchParam.put("dateFormat", "%Y");
			}
			
			List<Object[]> getCategoryCountByDateList = SiteUserFinderUtil.getCategoryCountByDate(searchParam);
			
			for(int i = 0; i < getCategoryCountByDateList.size(); i++){
				Map<String, Object> resultParam = new HashMap<String, Object>();
				
				Object[] resultArray = getCategoryCountByDateList.get(i);
				String createDate = (String) resultArray[0];
				int count = (int) resultArray[1];
				int cumulativeCount = (int) resultArray[2];
				
				resultParam.put("createDate", createDate);
				resultParam.put("count", count);
				resultParam.put("cumulativeCount", cumulativeCount);
				
				resultMapList.add(resultParam);
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return resultMapList;
	}
	
	public List<Map<String, Object>> getCategoryCountBySite(Map<String, Object> searchParam){
		
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
		
		try {
			List<Object[]> getSiteUserCountBySite = SiteUserFinderUtil.getCategoryCountBySite(searchParam);
			
			for(int i = 0; i < getSiteUserCountBySite.size(); i++){
				Map<String, Object> resultParam = new HashMap<String, Object>();
				
				Object[] resultArray = getSiteUserCountBySite.get(i);
				String siteName = (String) resultArray[0];
				int count = (int) resultArray[1];
				
				resultParam.put("siteName", siteName.replace(" ", ""));
				resultParam.put("count", count);
				
				resultMapList.add(resultParam);
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return resultMapList;
	}
	
	public boolean updateSiteUserStatistics(){
		
		boolean updateSiteUserStatistics = false;
		
		try {
			if(0 < SiteUserFinderUtil.updateSiteUserStatistics()){
				updateSiteUserStatistics = true;
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		return updateSiteUserStatistics;
	}
}