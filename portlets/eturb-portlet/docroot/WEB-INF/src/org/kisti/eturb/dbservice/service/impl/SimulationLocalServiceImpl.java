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

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.eturb.dbservice.NoSuchSimulationException;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.dbservice.model.Simulation;
import org.kisti.eturb.dbservice.service.base.SimulationLocalServiceBaseImpl;
import org.kisti.eturb.dbservice.service.persistence.SimulationPK;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;

import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * The implementation of the simulation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.eturb.dbservice.service.SimulationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.eturb.dbservice.service.base.SimulationLocalServiceBaseImpl
 * @see org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil
 */
public class SimulationLocalServiceImpl extends SimulationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil} to access the simulation local service.
	 */
	
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
			
			modifySimulation(projectId, analyzerJob);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException();
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
			
			modifySimulation(projectId, analyzerJob);
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException();
		}
	}
	
	protected void modifySimulation(long projectId, AnalyzerJob analyzerJob) throws SystemException {
		SimulationPK simulationPK = new SimulationPK(projectId, analyzerJob.getAnalyzerUuid());
		Simulation simulation = null;
		try {
			simulation = super.simulationPersistence.findByPrimaryKey(simulationPK);
		} catch (NoSuchSimulationException e) {
			simulation = super.simulationPersistence.create(simulationPK);
		}
		
		simulation.setExecuteDataStructure(new Gson().toJson(analyzerJob).toString());
		simulation.setExecuteDate(new Date());
		super.simulationPersistence.update(simulation);
	}
	
	public void removeSimulationWithPath(long projectId, String executeId, String userScreenName, String executeBasePath) throws SystemException{
		try{	
			SimulationPK simulationPK = new SimulationPK(projectId, executeId);
			Simulation simulation = super.simulationPersistence.findByPrimaryKey(simulationPK);
			AnalyzerJob analyzerJob = new Gson().fromJson(simulation.getExecuteDataStructure(), AnalyzerJob.class);
			
			Path path = Paths.get(executeBasePath,analyzerJob.getAppName(),analyzerJob.getAppVersion(),userScreenName,executeId);
			
			FileUtils.cleanDirectory(path.toAbsolutePath().toFile());
			path.toAbsolutePath().toFile().delete();
			try{
				super.simulationPersistence.remove(simulationPK);
			}catch (NoSuchSimulationException e1) {
				
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException();
		}
	}
	
	public void removeSimulationByProjectId(long projectId){
		try{
			List<Simulation> simulationList = simulationPersistence.findByProjectId(projectId);
			for(Simulation simulation : simulationList){
				if(!CustomUtil.strNull(simulation.getExecuteDataStructure()).equals("")){
					AnalyzerJob analyzerJob = new Gson().fromJson(simulation.getExecuteDataStructure(), AnalyzerJob.class);
					Path path = Paths.get(CustomUtil.strNull(analyzerJob.getResultPath()));
					
					FileUtils.cleanDirectory(path.getParent().toFile());
					path.getParent().toAbsolutePath().toFile().delete();
				}
				
				super.simulationPersistence.remove(simulation);
			}
		}catch (Exception e) {
			
		}
	}
	
}