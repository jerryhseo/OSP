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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.osp.NoSuchExecuteException;
import org.kisti.edison.osp.model.AnalyzerJob;
import org.kisti.edison.osp.model.Execute;
import org.kisti.edison.osp.service.base.ExecuteLocalServiceBaseImpl;
import org.kisti.edison.osp.service.persistence.ExecutePK;
import org.kisti.edison.osp.util.IBFileUtil;
import org.kisti.edison.osp.util.IBUserTokenUtil;
import org.kisti.edison.util.CustomUtil;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

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
	public void simulationWithInputFile(long projectId, AnalyzerJob analyzerJob, String fileContents, Path inputFilePath) throws SystemException{
		try{
			File inputFile = new File(inputFilePath.toAbsolutePath().toString());
			if(inputFile.exists()){
				inputFile.delete();
				inputFile.createNewFile();
			}else{
				FileUtil.mkdirs(inputFile.getParent());
				File dir = new File(inputFile.getParent());
				dir.setReadable(true);
				dir.setExecutable(true);
				dir.setWritable(true);
				inputFile.createNewFile();
			}
			
			FileWriter fileWriter = new FileWriter(inputFile);
			fileWriter.write(fileContents);
			fileWriter.close();
			
			modifyExecute(projectId, analyzerJob);
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException();
		}
	}
	
	
	public void simulationWithIBInputFile(ThemeDisplay themeDisplay, long projectId, AnalyzerJob analyzerJob, Path inputFilePath, String inputFileName, String fileId) throws SystemException{
		Group group = themeDisplay.getScopeGroup();
		User user = themeDisplay.getUser();
		
		try{
			String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
			String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			
			File ibFile = IBFileUtil.getFile(icebreakerUrl, vcToken, fileId, inputFileName);
			
			File inputFile = new File(inputFilePath.toAbsolutePath().toString());
			if(inputFile.exists()){
				inputFile.delete();
			}else{
				FileUtil.mkdirs(inputFile.getParent());
				File dir = new File(inputFile.getParent());
				dir.setReadable(true);
				dir.setExecutable(true);
				dir.setWritable(true);
				inputFile.createNewFile();
			}
			
			inputFile.setReadable(true);
			inputFile.setExecutable(true);
			inputFile.setWritable(true);
			
			FileUtil.copyFile(ibFile, inputFile);
			
			ibFile.delete();
			
			modifyExecute(projectId, analyzerJob);
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException();
		}
	}
	
	protected void modifyExecute(long projectId, AnalyzerJob analyzerJob) throws SystemException {
		ExecutePK executePK = new ExecutePK(projectId, analyzerJob.getAnalyzerUuid());
		Execute execute = null;
		try{
			execute = super.executePersistence.findByPrimaryKey(executePK);
		}catch (NoSuchExecuteException e) {
			execute = super.executePersistence.create(executePK);		
		}
		
		execute.setExecuteDataStructure(new Gson().toJson(analyzerJob).toString());
		execute.setExecuteDate(new Date());
		super.executePersistence.update(execute);
	}
	
}