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

package org.kisti.edison.science.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kisti.edison.science.NoSuchScienceAppCompileException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppCompile;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.base.ScienceAppCompileLocalServiceBaseImpl;
import org.kisti.edison.science.service.persistence.ScienceAppCompilePersistence;
import org.kisti.edison.util.EdisonPropsUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

/**
 * The implementation of the science app compile local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.science.service.ScienceAppCompileLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppCompileLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppCompileLocalServiceUtil
 */
public class ScienceAppCompileLocalServiceImpl extends ScienceAppCompileLocalServiceBaseImpl{
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * org.kisti.edison.science.service.ScienceAppCompileLocalServiceUtil} to
	 * access the science app compile local service.
	 */

	public ScienceAppCompile compileFindByScienceAppId(long scienceAppId) throws SystemException{
		try{
			return scienceAppCompilePersistence.findByPrimaryKey(scienceAppId);
		}catch (NoSuchScienceAppCompileException e){
			return null;
		}
	}
	
	public ScienceAppCompile updateCompileAndScienceApp(long scienceAppId, long userId, String gitHubUrl,
		String result) throws SystemException, PortalException{
		
		ScienceAppCompile scienceAppCompile = null;
		try{
			scienceAppCompile = scienceAppCompilePersistence.findByPrimaryKey(scienceAppId);
		}catch (NoSuchScienceAppCompileException e){
			scienceAppCompile = scienceAppCompilePersistence.create(scienceAppId);
		}
		
		if(scienceAppCompile != null){
  		scienceAppCompile.setScienceAppId(scienceAppId);
  		scienceAppCompile.setUserId(userId);
  		scienceAppCompile.setCompileUrl(gitHubUrl);
  		scienceAppCompile.setResult(result);
  		scienceAppCompile.setCreateDate(new Date());
  
  		scienceAppCompile = scienceAppCompilePersistence.update(scienceAppCompile);
  
  		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
  		if(scienceApp != null){
  			scienceApp.setIsCompile(true);
  			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
  		}
		}

		return scienceAppCompile;
	}

	public void deleteCompileAndScienceApp(long companyId, long scienceAppId, String appName, String appVersion) throws PortalException, SystemException{
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		if(scienceApp != null){
			scienceApp.setIsCompile(false);
			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
		}
		ScienceAppCompile scienceAppCompile = scienceAppCompilePersistence.findByPrimaryKey(scienceAppId);

		if(scienceAppCompile != null){
			scienceAppCompilePersistence.remove(scienceAppId);
		}

/*		if(uploadCase.equals("clean")){
  		//appName / verison -> folder delete
  		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName;
  		String gitCloneFolerName = appBasePath + File.separator + appVersion;
  		
  		File appDirectory = new File(gitCloneFolerName);
  		if(appDirectory.exists()){
  			deleteDirectory(appDirectory);
  		}
		}*/
	}

	/**
	 * 
	 * @param gitHubUrl
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SystemException
	 */
	public String gitHubCloneToScienceAppFolders(long companyId, String appName, String appVersion,
		String gitHubUrl) throws SystemException, IOException, InterruptedException{

		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName;
		System.out.println("appBasePath --> " + appBasePath);
		String gitCloneFolerName = appBasePath + File.separator + appVersion;
		System.out.println("gitCloneFolderName --> " + gitCloneFolerName);

		deleteDirectory(new File(gitCloneFolerName));

		List<String> command = new ArrayList<String>();
		command.add("git");
		command.add("clone");
		command.add(gitHubUrl);
		command.add(appVersion);
		System.out.println("command --> " + command.toString());

		// git clone
		return executeCommand(command, appBasePath);
	}

	public String cleanMakeFileToTargetScienceApp(long companyId, String appName, String appVersion)
		throws SystemException, IOException, InterruptedException{
		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName;
		String makeFileSrc = appBasePath + File.separator + appVersion + File.separator + "src";
		// String appBasePath = PrefsPropsUtil.getString(companyId,
		// EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + "sleeTest";
		// String makeFileSrc = appBasePath + File.separator + "1.0.0" +
		// File.separator + "src";

		String result = "";
		if(new File(makeFileSrc).exists()){
			List<String> command = new ArrayList<String>();
			command.add("make");
			command.add("clean");
			result = executeCommand(command, makeFileSrc);
		}
		return result;
	}

	public String makeFileToTargetScienceApp(long companyId, String appName, String appVersion)
		throws SystemException, IOException, InterruptedException{
		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName;
		String makeFileSrc = appBasePath + File.separator + appVersion + File.separator + "src";
		// String appBasePath = PrefsPropsUtil.getString(companyId,
		// EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + "sleeTest";
		// String makeFileSrc = appBasePath + File.separator + "1.0.0" +
		// File.separator + "src";

		String result = "";
		if(new File(makeFileSrc).exists()){
			List<String> command = new ArrayList<String>();
			command.add("make");
			command.add("all");
			result = executeCommand(command, makeFileSrc);
		}
		return result;
	}

	public String retrieveListTartgetDir(long companyId, String appName, String appVersion)
		throws SystemException, IOException, InterruptedException{
		String appBasePath = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + appName;
		String appBinPath = appBasePath + File.separator + appVersion + File.separator + "bin";
		
		String result = "";
		if(new File(appBinPath).exists()){
			List<String> command = new ArrayList<String>();
//			command.add("pwd");
			command.add("ls");
//			command.add("-l");
			result = executeCommand(command, appBinPath);
		}else{
			result = "not found directory";
		}
		return result;
	}

	
	private String executeCommand(List<String> command, String tartgetFilePath) throws IOException,
		InterruptedException{
		System.out.println("executeCommand Start!!!");
		ProcessBuilder builder = new ProcessBuilder(command);

		File tartgetPath = new File(tartgetFilePath);
		if(!tartgetPath.exists()){
			tartgetPath.mkdirs();
		}
		System.out.println("mkdir");

		builder.directory(tartgetPath); // app path
		Process process = builder.start();

		String report = "";
		InputStream instd = process.getInputStream();
		BufferedReader buf_reader = new BufferedReader(new InputStreamReader(instd));
		String temp = "";
		if((temp = buf_reader.readLine()) != null){
			System.out.println("buf_reader null");
		} else {
			System.out.println("buf_reader not null");
		}
		while((temp = buf_reader.readLine()) != null){
			report += temp + "\n";
		}
		buf_reader.close();

		InputStream stderr = process.getErrorStream();
		BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(stderr));
		temp = "";
		while((temp = buf_err_reader.readLine()) != null){
			report += temp + "\n";
			System.out.println(report);
		}
		buf_err_reader.close();

		process.getInputStream().close();
		process.getOutputStream().close();
		process.getErrorStream().close();
		instd.close();
		process.waitFor();
		System.out.println("executeCommand End!!!");

		return report;
	}

	private boolean deleteDirectory(File path){
		if(!path.exists()){
			return false;
		}
		File[] files = path.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				deleteDirectory(file);
			}else{
				file.delete();
			}
		}
		return path.delete();
	}

}