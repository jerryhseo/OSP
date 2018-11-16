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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kisti.edison.osp.model.AnalyzerJob;
import org.kisti.edison.osp.model.Execute;
import org.kisti.edison.osp.service.base.ExecuteLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the execute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.osp.service.ExecuteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.osp.service.base.ExecuteLocalServiceBaseImpl
 * @see org.kisti.edison.osp.service.ExecuteLocalServiceUtil
 */
public class ExecuteLocalServiceImpl extends ExecuteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.osp.service.ExecuteLocalServiceUtil} to access the execute local service.
	 */
	
	public void removeExecuteByProjectId(long projectId) throws SystemException,IOException{
		List<Execute> executeList = executePersistence.findByProjectId(projectId);
		for(Execute execute : executeList){
			if(!CustomUtil.strNull(execute.getExecuteDataStructure()).equals("")){
				AnalyzerJob analyzerJob = new Gson().fromJson(execute.getExecuteDataStructure(),AnalyzerJob.class);
				Path path = Paths.get(CustomUtil.strNull(analyzerJob.getResultPath()));
				
				FileUtils.cleanDirectory(path.getParent().toFile());
				path.getParent().toAbsolutePath().toFile().delete();
			}
			
			super.executePersistence.remove(execute);
		}
	}
}