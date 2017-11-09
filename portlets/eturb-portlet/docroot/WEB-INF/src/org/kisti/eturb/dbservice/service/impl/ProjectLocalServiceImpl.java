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

package org.kisti.eturb.dbservice.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kisti.eturb.dbservice.NoSuchProjectException;
import org.kisti.eturb.dbservice.model.Project;
import org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil;
import org.kisti.eturb.dbservice.service.base.ProjectLocalServiceBaseImpl;
import org.kisti.eturb.dbservice.service.persistence.ProjectPK;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.eturb.dbservice.service.ProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.eturb.dbservice.service.base.ProjectLocalServiceBaseImpl
 * @see org.kisti.eturb.dbservice.service.ProjectLocalServiceUtil
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.eturb.dbservice.service.ProjectLocalServiceUtil} to access the project local service.
	 */
	
	public int countProjectByUserId(long userId) throws SystemException{
		return super.projectPersistence.countByUserId(userId);
	}
	
	
	public List<Map<String,Object>> retrieveListProjectByUserId(long userId,int start, int end) throws SystemException{
		List<Map<String,Object>>  returnList = new ArrayList<Map<String,Object>> ();
		List<Project> dataList = super.projectPersistence.findByUserId(userId,start,end);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZoneUtil.getDefault());
		
		
		for(Project project : dataList){
			Map<String, Object> returnMap = project.getModelAttributes();
			returnMap.put("createDate", df.format(project.getCreateDate()));
			returnList.add(returnMap);
		}
		return returnList;
	}
	
	public Project modifyProject(long projectId, long userId, String name, String projectStructure, String analyzerStructure, String mode) throws NoSuchProjectException, SystemException{
		Project project = null;
		if(mode.equals(Constants.UPDATE)){
			ProjectPK projectPK = new ProjectPK(projectId, userId);
			project = super.projectPersistence.findByPrimaryKey(projectPK);
			project.setModifiedDate(new Date());
//			project.setProjectStructure(updateJSON(projectStructure));
			project.setProjectStructure(projectStructure);
			if(!analyzerStructure.equals("")){
				project.setAnalyzerStructure(analyzerStructure);
			}
		}else{
			projectId = CounterLocalServiceUtil.increment(Project.class.getName());
			ProjectPK projectPK = new ProjectPK(projectId, userId);
			project = super.projectPersistence.create(projectPK);
			project.setCreateDate(new Date());
			project.setName(name);
//			project.setProjectStructure(updateJSON(projectStructure));
			project.setProjectStructure(projectStructure);
			project.setAnalyzerStructure(analyzerStructure);
			
		}
		
		return super.projectPersistence.update(project);
	}
	
	public void removeProject(long projectId, long userId) throws NoSuchProjectException, SystemException{
		//remove simulation
		SimulationLocalServiceUtil.removeSimulationByProjectId(projectId);
		
		ProjectPK projectPK = new ProjectPK(projectId, userId);
		super.projectPersistence.remove(projectPK);
	}
	
	private String updateJSON(String projectStructure) {
		JSONArray projectArrayJson = JSONArray.fromObject(projectStructure);
		for(int i=0; i<projectArrayJson.size();i++){
			JSONObject projectJson = projectArrayJson.getJSONObject(i);
			Set<String> set = projectJson.keySet();
			projectJson.remove("icon");
			
			for (String key : set) {
				if(key.equals("children")){
					JSONArray childrenArrayJson = projectJson.getJSONArray("children");
					for(int j=0; i<projectArrayJson.size();i++){
						JSONObject projectChildrenJson = childrenArrayJson.getJSONObject(i);
						projectChildrenJson.remove("icon");
					}
				}
			}
		}
		
		
		return projectArrayJson.toString();
	}
	
	
}