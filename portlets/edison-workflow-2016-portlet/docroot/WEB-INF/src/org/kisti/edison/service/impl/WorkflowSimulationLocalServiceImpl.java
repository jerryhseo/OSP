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

import org.kisti.edison.service.base.WorkflowSimulationLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the workflow simulation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.WorkflowSimulationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.base.WorkflowSimulationLocalServiceBaseImpl
 * @see org.kisti.edison.service.WorkflowSimulationLocalServiceUtil
 */
public class WorkflowSimulationLocalServiceImpl
	extends WorkflowSimulationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.service.WorkflowSimulationLocalServiceUtil} to access the workflow simulation local service.
	 */
	
	/* 2018.12.12 _ Get WorkflowApp Monitoring Job List */
	public List<Map<String, Object>> getWorkflowMonitoringJobList(long simulationId, Locale locale) throws SystemException{
		
		return getWorkflowMonitoringList(0, "", simulationId, 0, 0, 0, 0, locale);
	}
	
	/* 2018.12.19 _ Get WorkflowApp MonitoringList */
	public List<Map<String, Object>> getWorkflowMonitoringList(long userId, String searchValue, long simulationId, long jobStatus, long classId, int begin, int end, Locale locale) throws SystemException{
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> searchParams = new HashMap<String,Object>();
		
		if(userId > 0) {
			searchParams.put("userId", userId);
		}
		searchParams.put("searchValue", searchValue);
		if(userId==0 &&!searchValue.equals("")){
			searchParams.put("searchValue", "");
			searchParams.put("searchValueAndUserId", searchValue);
		}
		
		if(jobStatus > 0) {
			searchParams.put("jobStatus", jobStatus);
		}
		if(classId > 0){
			searchParams.put("classId", classId);
		}
		
		if(simulationId > 0) {
			searchParams.put("simulationId", simulationId);
			searchParams.put("jobListSearch", "Y");
		} else {
			searchParams.put("groupSearch", "Y");
			searchParams.put("begin", begin);
			searchParams.put("end", end);
		}
		
		searchParams.put("languageId", locale.toString());
		
		//관리자 및 가상실험실에서 조회 할 경우 userId값이 0.
		//검색 값이 있을 경우에는 admin 조회로 변경
		if(userId==0 &&!searchValue.equals("")){
			searchParams.put("searchValue", "");
			searchParams.put("searchValueAndUserId", searchValue);
		}
		
		List<Object[]> workflowMonitoringList = workflowFinder.getWorkflowMonitoringList(searchParams);
		if(workflowMonitoringList != null && 0 < workflowMonitoringList.size()){
			for (int i = 0; i < workflowMonitoringList.size(); i++) {
				Map <String, Object> resultMap = new HashMap<String, Object>();
				
				Object[] resultArray = workflowMonitoringList.get(i);
				
				resultMap.put("scienceAppId", 				CustomUtil.strNull(resultArray[0]));
				resultMap.put("scienceAppName", 			CustomUtil.strNull(resultArray[1]));
				resultMap.put("scienceAppVersion",			CustomUtil.strNull(resultArray[2]));
				resultMap.put("scienceAppTitle", 			CustomUtil.strNull(resultArray[3]));
				
				resultMap.put("simulationId", 				CustomUtil.strNull(resultArray[4]));
				resultMap.put("simulationUserId", 			CustomUtil.strNull(resultArray[5]));
				resultMap.put("simulationCreateDate", 		CustomUtil.strNull(resultArray[6]));
				resultMap.put("simulationModifiedDate",		CustomUtil.strNull(resultArray[7]));
				resultMap.put("classId", 					CustomUtil.strNull(resultArray[8]));
				resultMap.put("customId", 					CustomUtil.strNull(resultArray[9]));
				resultMap.put("simulationTitle", 			CustomUtil.strNull(resultArray[10]));
				resultMap.put("workflowId", 				CustomUtil.strNull(resultArray[11]));
				
				resultMap.put("simulationJobId", 			CustomUtil.strNull(resultArray[12]));
				resultMap.put("simulationJobUserId", 		CustomUtil.strNull(resultArray[13]));
				resultMap.put("simulationJobCreateDate", 	CustomUtil.strNull(resultArray[14]));
				resultMap.put("simulationJobModifiedDate",	CustomUtil.strNull(resultArray[15]));
				resultMap.put("simulationJobTitle",			CustomUtil.strNull(resultArray[16]));
				long status = Long.parseLong(CustomUtil.strNull(resultArray[17], "0"));
				resultMap.put("status", 					status);
				resultMap.put("statusResponse", 			CustomUtil.strNull(resultArray[18]));
				resultMap.put("startTime", 					CustomUtil.strNull(resultArray[19]));
				resultMap.put("endTime", 					CustomUtil.strNull(resultArray[20]));
				resultMap.put("workflowUUID", 				CustomUtil.strNull(resultArray[21]));
				resultMap.put("reuseWorkflowUUID", 			CustomUtil.strNull(resultArray[22]));
				resultMap.put("screenLogic", 				CustomUtil.strNull(resultArray[23]));
				
				resultMap.put("userScreenName", 			CustomUtil.strNull(resultArray[24]));
				
				resultMap.put("executeDate", 				CustomUtil.strNull(resultArray[25]));
				resultMap.put("jobCnt", 					CustomUtil.strNull(resultArray[26]));
				
				String jobStatusImg = "";
				if(status == 1701005){
					jobStatusImg = "QUEUED.png";
				} else if(status == 1701006){
					jobStatusImg = "RUNNING.png";
				} else if(status == 1701011){
					jobStatusImg = "SUCCESS.png";
				} else if(status == 1701012){
					jobStatusImg = "FAILED.png";
				}
				resultMap.put("jobStatusImg",jobStatusImg);
				
				returnList.add(resultMap);
			}
		}
		
		return returnList;
	}
}