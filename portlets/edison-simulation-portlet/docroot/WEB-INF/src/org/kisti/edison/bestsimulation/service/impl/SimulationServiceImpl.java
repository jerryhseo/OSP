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

package org.kisti.edison.bestsimulation.service.impl;

import java.util.Map;

import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.base.SimulationServiceBaseImpl;
import org.kisti.edison.bestsimulation.service.persistence.SimulationJobUtil;
import org.kisti.edison.bestsimulation.service.persistence.SimulationUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.springframework.util.StringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * The implementation of the simulation remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.SimulationService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author mhkang
 * @see org.kisti.edison.bestsimulation.service.base.SimulationServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.SimulationServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SimulationServiceImpl extends SimulationServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.bestsimulation.service.SimulationServiceUtil} to access the simulation remote service.
	 */
	private final static long EDISON_COMPANY_ID = 20154;
	
	@JSONWebService(method = "POST", value = "add-simulation")
	public JSONObject addSimulationWithJob(long userId, String appName, String appVersion, String simulationTitle, String jobData){
		JSONObject resultInfo = JSONFactoryUtil.createJSONObject();
		Boolean isValid = true;
		
		try {
			
			if(!StringUtils.hasText(simulationTitle)){
				throw new PortalException("SimulationTitle is Null");
			}
			
			if(!StringUtils.hasText(appName)){
				throw new PortalException("appName is Null");
			}
			
			if(!StringUtils.hasText(appVersion)){
				throw new PortalException("appVersion is Null");
			}
			
			if(!StringUtils.hasText(jobData)){
				throw new PortalException("jobData is Null");
			}
			
			if(!ScienceAppLocalServiceUtil.existApp(appName, appVersion)){
				throw new PortalException("No Such ScienceApp is Primary Key ["+appName+","+appVersion+"]");
			}
			
			JSONFactoryUtil.createJSONArray(jobData);
			
			User user = UserLocalServiceUtil.getUser(userId);
			Group group = GroupLocalServiceUtil.getGroup(EDISON_COMPANY_ID, "Guest");
			
			Map<String,Object> simulationAndJobMap = SimulationLocalServiceUtil.createSimulationWithJob(user, group.getGroupId(), appName, appVersion, simulationTitle);
			
			String simulationUuid = CustomUtil.strNull(simulationAndJobMap.get("simulationUuid"));
			String jobUuid = CustomUtil.strNull(simulationAndJobMap.get("jobUuid"));
			SimulationJobDataLocalServiceUtil.modifySimulationJobData(jobUuid, jobData);
			
			
			long plid = PortalUtil.getPlidFromPortletId(group.getGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
			resultInfo.put("isValid", isValid);
			resultInfo.put("workbencPlid", plid);
			resultInfo.put("simulationUuid", simulationUuid);
			resultInfo.put("simulationJobUuid", jobUuid);
		}catch (Exception e) {
			resultInfo.put("isValid", false);
			resultInfo.put("failMessage", e.getMessage());
		}finally {
			return resultInfo;
		}
	}
	
	@JSONWebService(method = "POST", value = "get-simulation-job")
	public JSONObject getSimulationJob(long userId, String appName, String appVersion, String simulationUuid, String jobUuid, String jobData){
		JSONObject resultInfo = JSONFactoryUtil.createJSONObject();
		boolean hasSimulationInfo = true; 
		
		try {
			
			if(!StringUtils.hasText(appName)){
				throw new PortalException("appName is Null");
			}
			
			if(!StringUtils.hasText(appVersion)){
				throw new PortalException("appVersion is Null");
			}
			
			if(!ScienceAppLocalServiceUtil.existApp(appName, appVersion)){
				throw new PortalException("No Such ScienceApp is Primary Key ["+appName+","+appVersion+"]");
			}
			
			if(!StringUtils.hasText(simulationUuid)){
				throw new PortalException("simulationUuid is Null");
			}
			
			if(!StringUtils.hasText(jobUuid)){
				throw new PortalException("jobUuid is Null");
			}
			
			if(!StringUtils.hasText(jobData)){
				throw new PortalException("jobData is Null");
			}
			
			JSONArray jobDataJsonArr = JSONFactoryUtil.createJSONArray(jobData);
			jobData = jobDataJsonArr.toString();
			
			int simulationCnt = SimulationUtil.countBySimulationUuid(simulationUuid);
			int simulationJobCnt = SimulationJobUtil.countByjobUuid(simulationUuid, jobUuid);
			
			if(0< simulationCnt && 0 < simulationJobCnt){
				resultInfo.put("hasSimulationInfo", hasSimulationInfo);
			} else {
				boolean hasSimulation = (0 < simulationCnt) ? true : false;
				Group group = GroupLocalServiceUtil.getGroup(EDISON_COMPANY_ID, "Guest");
				User user = UserLocalServiceUtil.getUser(userId);
				
				Map<String, Object> resultMap = SimulationLocalServiceUtil.createSimulationWithJob(user, group, appName, appVersion, simulationUuid, jobUuid, jobData, hasSimulation);
				
				hasSimulationInfo = Boolean.parseBoolean(CustomUtil.strNull(resultMap.get("hasSimulationInfo")));
				resultInfo.put("hasSimulationInfo", hasSimulationInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
			resultInfo.put("hasSimulationInfo", false);
		}finally {
			return resultInfo;
		}
	}
}