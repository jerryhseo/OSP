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

package org.kisti.edison.osp.service.impl;

import java.io.IOException;
import java.util.List;

import org.kisti.edison.osp.NoSuchProjectException;
import org.kisti.edison.osp.model.Project;
import org.kisti.edison.osp.service.ExecuteLocalServiceUtil;
import org.kisti.edison.osp.service.base.ProjectLocalServiceBaseImpl;
import org.kisti.edison.osp.service.persistence.ProjectPK;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.osp.service.ProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.osp.service.base.ProjectLocalServiceBaseImpl
 * @see org.kisti.edison.osp.service.ProjectLocalServiceUtil
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.osp.service.ProjectLocalServiceUtil} to access the project local service.
	 */
	public void removeProjectBySimulationUuid(String simulationUuid) throws SystemException, NoSuchProjectException, IOException{
		List<Project> projectList = super.projectPersistence.findBysimulationUuid(simulationUuid);
		for(Project project : projectList){
			String portletNamespace = project.getPortletNamespace();
			long jobSeqNo = project.getJobSeqNo();
			this.removeProject(simulationUuid, portletNamespace, jobSeqNo);
		}
	}
	
	
	public void removeProject(String simulationUuid, String portletNamespace, long jobSeqNo) throws SystemException,IOException,NoSuchProjectException{
		ProjectPK projectPK = new ProjectPK(simulationUuid, portletNamespace, jobSeqNo);
		Project project = super.projectPersistence.fetchByPrimaryKey(projectPK);
		
		long projectId = project.getProjectId();
		ExecuteLocalServiceUtil.removeExecuteByProjectId(projectId);
		super.projectPersistence.remove(projectPK);
	}
}