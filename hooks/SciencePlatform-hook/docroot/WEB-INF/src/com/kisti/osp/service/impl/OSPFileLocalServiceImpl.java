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

package com.kisti.osp.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.PumpStreamHandler;

import com.kisti.osp.constants.OSPPropsUtil;
import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.service.base.OSPFileLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

/**
 * The implementation of the o s p file local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.service.OSPFileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Jerry h. Seo
 * @see com.kisti.osp.service.base.OSPFileLocalServiceBaseImpl
 * @see com.kisti.osp.service.OSPFileLocalServiceUtil
 */
public class OSPFileLocalServiceImpl extends OSPFileLocalServiceBaseImpl {
	
	 private String TEMP_DIR_NAME = "temp";
	private  long _DEFAULT_TIMEOUT = 60000;
	
	private  Log _log = LogFactoryUtil.getLog(OSPFileLocalServiceImpl.class);
	
	 private Path getRealTemporaryFolderPath( Path realBasePath ){
		Path baseTempPath = Paths.get(TEMP_DIR_NAME);
		return realBasePath.resolve(baseTempPath);
	}
	
	 private Path createBaseTemporaryFolderPath( Path realContextPath ) throws IOException{
		Path baseTempFolderPath = getRealTemporaryFolderPath(realContextPath);
		if( Files.notExists(baseTempFolderPath) ){
			baseTempFolderPath = Files.createDirectory(baseTempFolderPath);
			baseTempFolderPath.toFile().deleteOnExit();
		}
		return baseTempFolderPath;
	}

	 private Path createTemporaryFilePath( Path realContextPath, String prefix,  String suffix, boolean isFile ) throws IOException{
		Path baseTempFolderPath = createBaseTemporaryFolderPath(realContextPath);
//		System.out.println("rootFilePath: "+baseTempFolderPath);
		
		Path tempFilePath;
		if( isFile )
			tempFilePath = Files.createTempFile(baseTempFolderPath, prefix, suffix);
		else
			tempFilePath = Files.createTempDirectory(baseTempFolderPath, prefix );
		
		tempFilePath.toFile().deleteOnExit();
//		System.out.println("tempFilePath: "+tempFilePath);
		return tempFilePath;
	}
	
	 private Path getUniqueUuidFilePath( Path baseFolderPath, String prefix, String suffix ){
		Path uuidFilePath = null;
		do{
			UUID uuid = UUID.randomUUID();
			Path uuidFileName = Paths.get(prefix+uuid.toString()+suffix);
			uuidFilePath = baseFolderPath.resolve(uuidFileName);
		} while( Files.exists(uuidFilePath) );

		return uuidFilePath;
	}
	
	
	 private String extractExtension( String fileName ){
		int lastIndex = fileName.lastIndexOf('.');
		if( lastIndex < 0 )	return fileName;
		
		return fileName.substring(lastIndex+1);
	}
	
	 private JSONArray lookUpFolder( File folder, String filter ) throws IOException{
		if( !folder.isDirectory() || !folder.exists() )		throw new FileNotFoundException(folder.getAbsolutePath());

//		System.out.println("Folder: "+folder.getName());
//		System.out.println("Filter: "+filter);
		File[] files;
		if( filter == null || filter.isEmpty() )
			files = folder.listFiles();
		else{
			files = folder.listFiles( new FileExtensionFilter(filter) );
			File[] subFolders = folder.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			});
			
			files = ArrayUtil.append(files, subFolders);
		}
		
		JSONArray fileInfos = JSONFactoryUtil.createJSONArray();
		for( File file : files ){
			JSONObject fileInfo = getFileInformation(file);
//			System.out.println("File Info: "+fileInfo.toString());
			fileInfos.put(fileInfo);
		}
		
		return fileInfos;
	}
	
	 private JSONObject getFileInformation( File file ) throws IOException{
		JSONObject fileInfo = JSONFactoryUtil.createJSONObject();
		fileInfo.put("name", file.getName());
		fileInfo.put("size", convert2ReadableFileSize(file.length()));
		fileInfo.put("isFile", file.isFile());
		
		return fileInfo;
	}
	
	 private JSONObject getFileInformation( Path filePath ) throws IOException{
		JSONObject fileInfo = JSONFactoryUtil.createJSONObject();
//		System.out.println("To get fileInfo: "+filePath.toString());
		fileInfo.put("name", filePath.getFileName().toString());
		fileInfo.put("size", convert2ReadableFileSize(Files.size(filePath)));
		fileInfo.put("isFile", Files.isRegularFile(filePath, LinkOption.NOFOLLOW_LINKS));
		
		return fileInfo;
	}
	
	 private String convert2ReadableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
	 private void deleteFile(Path target) throws IOException{
		Files.deleteIfExists(target);
	}

	 private Path moveFile(
			Path source, 
			Path target, 
			boolean overwrite) throws IOException{
		CopyOption[] options;
		Path newPath;
		if( overwrite ){
			options = new CopyOption[]{
				StandardCopyOption.REPLACE_EXISTING
			};
			newPath = Files.move(source, target, options);
		}
		else
			newPath = Files.move(source, target);
		
		return newPath;
	}

	 private Path copyFile( Path source, Path target, boolean overwrite ) throws IOException{
		CopyOption[] options;
		Path newPath;
		if( overwrite ){
			options = new CopyOption[]{
				StandardCopyOption.REPLACE_EXISTING
			};
			newPath = Files.copy(source, target, options);
		}
		else
			newPath = Files.copy(source, target);
		
		return newPath;
	}

	 private void changeFileOwner( String target, String owner ) throws PortalException, SystemException{
		if( !System.getProperty("os.name").toLowerCase().contains("windows") ) {			 
			String strCmd = "";
			strCmd += "sudo /bin/chown -R ";
			strCmd += owner;
			strCmd += " ";
			strCmd += target;
			
			CommandLine cmdLine = CommandLine.parse( strCmd );
			
			//		System.out.println("chown Command: "+cmdLine.toString());
			
			final OutputStream outStream = new ByteArrayOutputStream();
			final OutputStream errorStream = new ByteArrayOutputStream();
			
			DefaultExecuteResultHandler resultHandler;
			try {
				resultHandler = execute(cmdLine, null, outStream, errorStream);
				resultHandler.waitFor(_DEFAULT_TIMEOUT);
			} catch (ExecuteException e) {
				throw new PortalException("Fail to chown exec");
			} catch (IOException e) {
				throw new PortalException("chown IOException: " + e.getMessage(), e);
			} catch (InterruptedException e) {
				throw new PortalException("chown InterruptedException: " + e.getMessage(), e);
			}
			
			int exitValue = resultHandler.getExitValue();
			
			if (exitValue != 0)
				throw new PortalException("Failed to chown : " + errorStream.toString());
			
		}
	}

	 private void changeFileMode( 
				String target, 
				String mode ) throws PortalException, SystemException{
		 if( !System.getProperty("os.name").toLowerCase().contains("windows") ) {
			 String strCmd = "";
			 strCmd += "sudo /bin/chmod -R ";
			 strCmd += mode;
			 strCmd += " ";
			 strCmd += target;
			 
			 CommandLine cmdLine = CommandLine.parse( strCmd );
			 
			 //		System.out.println("chmod Command: "+cmdLine.toString());
			 
			 final OutputStream outStream = new ByteArrayOutputStream();
			 final OutputStream errorStream = new ByteArrayOutputStream();
			 
			 DefaultExecuteResultHandler resultHandler;
			 try {
				 resultHandler = execute(cmdLine, null, outStream, errorStream);
				 resultHandler.waitFor(_DEFAULT_TIMEOUT);
			 } catch (ExecuteException e) {
				 throw new PortalException("Fail to chmod exec");
			 } catch (IOException e) {
				 throw new PortalException("chmod IOException: " + e.getMessage(), e);
			 } catch (InterruptedException e) {
				 throw new PortalException("chmod InterruptedException: " + e.getMessage(), e);
			 }
			 
			 int exitValue = resultHandler.getExitValue();
			 
			 if (exitValue != 0)
				 throw new PortalException("Failed to chmod : " + errorStream.toString());
		 }
	}

	 private DefaultExecuteResultHandler execute(CommandLine cmdLine, OutputStream outStream, OutputStream errorStream)
	        throws ExecuteException, IOException {
	    return execute(cmdLine, Collections.<String, String> emptyMap(), outStream, errorStream);
	}

	 private DefaultExecuteResultHandler execute(CommandLine cmdLine, Map<String, String> environment, OutputStream outStream,
	        OutputStream errorStream) throws ExecuteException, IOException {
	    DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	    ExecuteStreamHandler streamHandler = new PumpStreamHandler(outStream, errorStream);
	    DefaultExecutor executor = new DefaultExecutor();
	    executor.setExitValue(0);
	    executor.setStreamHandler(streamHandler);
	    if (environment != null) {
	        executor.execute(cmdLine, environment, resultHandler);
	    } else {
	        executor.execute(cmdLine, resultHandler);
	    }
	    return resultHandler;
	}

	 private void addZipEntry( ZipOutputStream zipOutputStream, Path fileName, Path base ) throws IOException{
		Path filePath = base.resolve(fileName);
		_log.debug(filePath.toString()+" just to be added");
		_log.debug("fileName: "+fileName.toString());
		_log.debug("base: "+base.toString());
		if( Files.isDirectory(filePath) ){
			String[] subFiles = filePath.toFile().list();
			for( String subFile : subFiles ){
				addZipEntry(zipOutputStream, fileName.resolve(subFile), base);
			}
		}
		else if( Files.isRegularFile(filePath) ){
			ZipEntry zipEntry = new ZipEntry(fileName.toString());
			zipOutputStream.putNextEntry(zipEntry);
			FileInputStream fileInputStream = new FileInputStream( filePath.toString() );
			int length;
			byte[] buffer = new byte[2048];
			while ((length = fileInputStream.read(buffer)) > 0) {
				zipOutputStream.write(buffer, 0, length);
			}
			// Closing the fileInputStream instance
			fileInputStream.close();
			zipOutputStream.closeEntry();
		}
		else{
			System.out.println("Un-known file type: "+filePath.toString());
		}
	}

	 private void zip( List<Path> sources, Path destination ) throws IOException{
		ZipOutputStream zipOutputStream = null;
		try {
			zipOutputStream = new ZipOutputStream(new FileOutputStream(destination.toString()));
		} catch (FileNotFoundException e) {
			_log.error("[ERROR] zip() - destination not found: "+destination.toString());
			throw e;
		}
		
		try {
			for( Path source : sources ){
				addZipEntry(zipOutputStream, source.getFileName(), source.getParent());
			}
		} catch (IOException e) {
			_log.error("[ERROR] addZipEntry() while zip()");
			throw e;
		} 
		
		if (zipOutputStream != null) {
			try {
				zipOutputStream.close();
			} catch (IOException iOException) {
				_log.error("[ERROR] zipOutputStream.close() while zip()");
			}
		}
	}

	private  void writeResult( PortletResponse portletResponse, String result ) throws PortalException, IOException{
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write( httpResponse, result );
	}

	private  void writeResult( PortletResponse portletResponse, Path path ) throws PortalException, IOException{
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write( httpResponse, path.toString() );
	}

	 private String getUserName( PortletRequest portletRequest ) throws PortalException, SystemException{
		String userName = PortalUtil.getUser(portletRequest).getScreenName();
		if( userName.equalsIgnoreCase("edison") )
			userName = "edisonadm";
		
		return userName;
	}

	 private TextAndLastPosition readTextAndLastPosition(Path path, long lastPoistion) throws IOException{
	    FileChannel fcIn = FileChannel.open(path, StandardOpenOption.READ);
	    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	    StringBuffer sb = new StringBuffer();
	    if(lastPoistion > 0){
	     fcIn.position(lastPoistion);   
	    }
	    int i = 0;
	    while(fcIn.read(buffer) != -1){
	        byte[] bytes = new byte[buffer.position()];
	        buffer.flip();
	        buffer.get(bytes);
	        sb.append(new String(bytes));
	        buffer.clear();
	    }
	    lastPoistion = fcIn.position();
	    fcIn.close();
	    TextAndLastPosition temp = new TextAndLastPosition(sb.toString(), lastPoistion);
	    return temp;
	}

	 private TextAndLastPosition readTextAndLastPosition(Path path, long startPosition, long size) throws IOException{
	    FileChannel fcIn = FileChannel.open(path, StandardOpenOption.READ);
	    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	    StringBuffer sb = new StringBuffer();
	    if(startPosition > 0){
	     fcIn.position(startPosition);   
	    }
	    while(fcIn.read(buffer) != -1){
	        byte[] bytes = new byte[buffer.position()];
	        buffer.flip();
	        buffer.get(bytes);
	        sb.append(new String(bytes));
	        buffer.clear();
	    }
	    long lastPosition = fcIn.position();
	    fcIn.close();
	    return new TextAndLastPosition(sb.toString(), lastPosition);
	}

	 private class FileExtensionFilter implements FilenameFilter {
		private String extension = "";
		public FileExtensionFilter ( String extension ){
			this.extension = "."+extension;
//			System.out.println("Filter: "+extension);
		}
		
		@Override
		public boolean accept(File directory, String fileName){
//			System.out.println("accept: "+fileName+", "+extension+", "+fileName.endsWith(extension));
			return fileName.endsWith(extension); 
		}
	}
	
	/*****************************************************************
	 * Public APIs Section
	 * @throws SystemException 
	 * @throws PortalException 
	 ****************************************************************/
	
	 class TextAndLastPosition{
	    private String text;
	    private long lastPosition;
	    public TextAndLastPosition(String text, long lastPosition){
	        this.text = text;
	        this.lastPosition = lastPosition;
	    }
	    public String getText(){
	        return this.text;
	    }
	    public long getLastPosition(){
	        return this.lastPosition;
	    }
	    
	}

	 class OSPFileVisitor extends SimpleFileVisitor<Path>{
		Path targetPath;
		Path tempFilePath;
		
		public OSPFileVisitor( Path target, Path tempPath ){
			this.targetPath = target;
			this.tempFilePath = tempPath;
		}
		
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Path path = this.targetPath.relativize(dir);
			Path tempPath = this.tempFilePath.resolve(path);
			if( !Files.exists(tempPath) ){
				Path tempFolder = null;
				try {
					tempFolder = Files.createDirectory(tempPath);
					tempFolder.toFile().deleteOnExit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return FileVisitResult.CONTINUE;
		}
	
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
			Path path = this.targetPath.relativize(file);
			Path tempPath = this.tempFilePath.resolve(path);
			try {
				Files.copy(file, tempPath, StandardCopyOption.REPLACE_EXISTING);
				tempPath.toFile().deleteOnExit();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return FileVisitResult.CONTINUE;
		}
	}

	/**
	 *  Link a file or a folder to servlet container template folder based on 
	 *  of the servlet context path.
	 *  Returns the template path based on of the servlet context path. 
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	@Deprecated
	public  String getLinkedTemporaryFilePath(
			PortletRequest portletRequest, 
	        String source, 
	        String prefix, 
	        String suffix,
	        String repoType) throws PortalException, SystemException, IOException{
			User user = PortalUtil.getUser(portletRequest);
			Path sourcePath = getRepositoryPath(portletRequest, source, repoType);
			
			if( Files.notExists(sourcePath, LinkOption.NOFOLLOW_LINKS) )	
					throw new FileNotFoundException(sourcePath.toString());
			
			Path tempFolderPath = Paths.get(portletRequest.getPortletSession().getPortletContext().getRealPath(TEMP_DIR_NAME));
			if( Files.notExists(tempFolderPath) )
				Files.createDirectories(tempFolderPath);
			
			Path tempUuidPath = getUniqueUuidFilePath(tempFolderPath, "", "").resolve(sourcePath.getFileName());
//			System.out.println("tempUuidPath: "+tempUuidPath.toString());

			Path symbolicLink = Files.createSymbolicLink(tempUuidPath, sourcePath );
			symbolicLink.toFile().deleteOnExit();
			
			return symbolicLink.getParent().getFileName().resolve(symbolicLink.getFileName()).toString();
	}

	public  void getLinkedTemporaryFilePath(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String srcScreenName, 
			String parent,
			String fileName,
			String repoType) throws IOException, PortalException, SystemException{
			Path sourcePath = getRepositoryPath(srcScreenName, parent, repoType);
			
			if( Files.notExists(sourcePath, LinkOption.NOFOLLOW_LINKS) )	
					throw new FileNotFoundException(sourcePath.toString());
			
			String contextPath = portletRequest.getPortletSession().getPortletContext().getRealPath("");
			Path tempFolderPath = Paths.get(contextPath, TEMP_DIR_NAME);
			if( Files.notExists(tempFolderPath) )
				Files.createDirectories(tempFolderPath);
			
			Path tempUuidPath = getUniqueUuidFilePath(tempFolderPath, "", "");
//			System.out.println("tempUuidPath: "+tempUuidPath.toString());

			Path symbolicLink = Files.createSymbolicLink(tempUuidPath, sourcePath );
			symbolicLink.toFile().deleteOnExit();
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("parentPath", Paths.get(TEMP_DIR_NAME, tempUuidPath.getFileName().toString() ).toString());
			result.put("fileName", fileName);
			
			writeResult(portletResponse, result.toString());
	}

	/**
	 * Copy a file or a folder to servlet container template folder based on 
	 *  of the servlet context path.
	 *  Returns the template path based on of the servlet context path.
	 */
	@Deprecated
	public  String getCopiedTemporaryFilePath(
			PortletRequest portletRequest, 
			String source, 
			String prefix, 
			String suffix,
			String repoType) throws IOException, PortalException, SystemException{
		final Path sourcePath = getRepositoryPath(portletRequest, source, repoType);
		OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(repoType);
		if( Files.notExists(sourcePath) )	throw new FileNotFoundException(sourcePath.toString());

		Path tempRealPath = Paths.get(portletRequest.getPortletSession().getPortletContext().getRealPath(TEMP_DIR_NAME));
		if( Files.notExists(tempRealPath)){
			Files.createDirectory(tempRealPath);
		}
		
		Path tempUuidPath = getUniqueUuidFilePath(tempRealPath, "", "");
		Files.createDirectory(tempUuidPath);
		tempUuidPath.toFile().deleteOnExit();
		
		
		if( Files.isRegularFile(sourcePath, LinkOption.NOFOLLOW_LINKS) ){
			tempUuidPath = tempUuidPath.resolve(sourcePath.getFileName());
			Files.copy(sourcePath, tempUuidPath, StandardCopyOption.REPLACE_EXISTING);
		}
		else if ( Files.isDirectory(sourcePath, LinkOption.NOFOLLOW_LINKS) ){
//			System.out.println("tempUuidPath: "+tempUuidPath.toString());
			Files.walkFileTree(sourcePath,  new OSPFileVisitor( sourcePath, tempUuidPath) );
		}
		else
			throw new FileNotFoundException(sourcePath.toString()+": is not a regular file.");
	
		return tempUuidPath.getParent().getFileName().resolve(tempUuidPath.getFileName()).toString();
	}

	public  void getCopiedTemporaryFilePath(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String srcScreenName, 
			String parent,
			String fileName,
			String repoType) throws IOException, PortalException, SystemException{
		final Path sourcePath = getRepositoryPath(srcScreenName, parent, repoType);
		if( Files.notExists(sourcePath) )	throw new FileNotFoundException(sourcePath.toString());

		String contextPath = portletRequest.getPortletSession().getPortletContext().getRealPath("");
		Path tempRealPath = Paths.get(contextPath, TEMP_DIR_NAME);
		System.out.println("tempRealPath: "+tempRealPath.toString());
		if( Files.notExists(tempRealPath)){
			Files.createDirectory(tempRealPath);
		}
		
		Path tempUuidPath = getUniqueUuidFilePath(tempRealPath, "", "");
		Files.createDirectory(tempUuidPath);
		tempUuidPath.toFile().deleteOnExit();
		
		if( Files.isRegularFile(sourcePath, LinkOption.NOFOLLOW_LINKS) ){
			tempUuidPath = tempUuidPath.resolve(sourcePath.getFileName());
			Files.copy(sourcePath, tempUuidPath, StandardCopyOption.REPLACE_EXISTING);
		}
		else if ( Files.isDirectory(sourcePath, LinkOption.NOFOLLOW_LINKS) ){
//			System.out.println("tempUuidPath: "+tempUuidPath.toString());
			Files.walkFileTree(sourcePath,  new OSPFileVisitor( sourcePath, tempUuidPath) );
		}
		else
			throw new FileNotFoundException(sourcePath.toString()+": is not a regular file.");
	
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("parentPath", Paths.get(TEMP_DIR_NAME, tempUuidPath.getFileName().toString() ).toString());
		result.put("fileName", fileName);
		
		writeResult(portletResponse, result.toString());
	}

	/**
	 *  List file information in a folder and return JSONArray.
	 *  [
	 *  	{
	 *  		name:String [file name],
	 *			size: String [file size],
	 *			isFile:boolean [ file or not ]
	 *  	},
	 *  	......
	 *  ]
	 */
	@Deprecated
	public  JSONArray getFolderInformation( 
			PortletRequest portletRequest, 
			String folderPath, 
			String filter,
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, folderPath, repositoryType);
		filter = extractExtension(filter);
		
		return lookUpFolder(targetPath.toFile(), filter);
	}

	public  JSONArray getFolderInformation( 
			String screenName, 
			String folderPath, 
			String filter,
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(screenName, folderPath, repositoryType);
		filter = extractExtension(filter);
		System.out.println("targetPath: "+targetPath.toString());
		return lookUpFolder(targetPath.toFile(), filter);
	}

	/**
	 *  Returns information of a specified file and return JSONObject.
	 *  {
	 *  	name:String [file name],
	 *		size: String [file size],
	 *		isFile:boolean [ file or not ]
	 *  }
	 */
	@Deprecated
	public  JSONObject getFileInformation( 
			PortletRequest portletRequest, 
			String filePath,
			String repositoryType ) throws IOException, PortalException, SystemException{
		return getFileInformation(getRepositoryPath(portletRequest, filePath, repositoryType));
	}

	public  JSONObject getFileInformation( 
			String screenName, 
			String filePath,
			String repositoryType ) throws IOException, PortalException, SystemException{
		return getFileInformation(getRepositoryPath(screenName, filePath, repositoryType));
	}

	@Deprecated
	public  Path createFile( PortletRequest portletRequest, String target, String repositoryType ) throws PortalException, SystemException, IOException{
		Path filePath = getRepositoryPath(portletRequest, target, repositoryType);
		Path parentPath = Files.createDirectories(filePath.getParent());
		String owner = getUserName(portletRequest)+":edisonuser";
		Files.deleteIfExists(filePath);
		Files.createFile(filePath);
		changeFileOwner(parentPath.toString(), owner);
		changeFileMode(parentPath.toString(), "g+w");
		
		return Paths.get(target);
	}
	
	public  Path createFile( String screenName, String target, String repositoryType ) throws PortalException, SystemException, IOException{
		Path filePath = getRepositoryPath(screenName, target, repositoryType);
		Path parentPath = Files.createDirectories(filePath.getParent());
		String owner = screenName+":";
		Files.deleteIfExists(filePath);
		Files.createFile(filePath);
		changeFileOwner(parentPath.toString(), owner);
		changeFileMode(parentPath.toString(), "g+w");
		
		return Paths.get(target);
	}

	@Deprecated
	public  void deleteFile( 
			PortletRequest portletRequest,  
			String target, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		deleteFile(targetPath);
	}

	public  void deleteFile( 
			String screenName,  
			String target, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		deleteFile(targetPath);
	}

	@Deprecated
	public  String moveFile( 
			PortletRequest portletRequest,  
			String source, 
			String target, 
			boolean overwrite, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		
		Path sourcePath = getRepositoryPath(portletRequest, source, repositoryType);
		if( Files.notExists(sourcePath) )
			throw new FileNotFoundException(sourcePath.toString());
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = getUserName(portletRequest)+":edisonuser";
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
		}
		
		moveFile(sourcePath, targetPath, overwrite);
        changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		return target;
	}

	public  String moveFile( 
			String srcScreenName,  
			String source,
			String srcRepositoryType,
			String targetScreenName,
			String target, 
			String targetRepositoryType,
			boolean overwrite ) throws IOException, PortalException, SystemException{
		
		Path sourcePath = getRepositoryPath(srcScreenName, source, srcRepositoryType);
		if( Files.notExists(sourcePath) )
			throw new FileNotFoundException(sourcePath.toString());
		
		Path targetPath = getRepositoryPath(targetScreenName, target, targetRepositoryType);
		Path targetFolder = targetPath.getParent();
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
		}
		
		String owner = targetScreenName + ":" + OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();
		moveFile(sourcePath, targetPath, overwrite);
        changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		return target;
	}

	@Deprecated
	public  String copyFile( 
			PortletRequest portletRequest,
			String source, 
			String target, 
			boolean overwrite, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		
		Path sourcePath = getRepositoryPath(portletRequest, source, repositoryType);
		if( Files.notExists(sourcePath) )
			throw new FileNotFoundException(sourcePath.toString());
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = getUserName(portletRequest)+":edisonuser";
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
		}
		
		copyFile(sourcePath, targetPath, overwrite);
		changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		return target;
	}
	
	public  String copyFile( 
			String srcScreenName,
			String source,
			String srcRepositoryType,
			String targetScreenName,
			String target, 
			String targetRepositoryType,
			boolean overwrite ) throws IOException, PortalException, SystemException{
		
		Path sourcePath = getRepositoryPath(srcScreenName, source, srcRepositoryType);
		if( Files.notExists(sourcePath) )
			throw new FileNotFoundException(sourcePath.toString());
		
		Path targetPath = getRepositoryPath(targetScreenName, target, targetRepositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = targetScreenName+ ":" + OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
		}
		
		copyFile(sourcePath, targetPath, overwrite);
		changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		return target;
	}

	
	/**
	 *  Copy an dlentry to the target file
	 * @throws IOException 
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	@Deprecated
	public  String copyDLEntryFile(
			PortletRequest portletRequest,
			long srcDLEntryId, 
			String target,
			boolean overwrite, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(srcDLEntryId);
		InputStream stream = null;
		
		stream = fileEntry.getContentStream();
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = getUserName(portletRequest)+":edisonuser";

		if( !Files.exists( targetFolder ) ){
			Files.createDirectories(targetFolder);
		}
		
		Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
		changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		if( Validator.isNotNull(stream) )
			stream.close();
		
		return target;
	}

	public  String copyDLEntryFile(
			long srcDLEntryId,
			String targetScreenName,
			String target,
			String targetRepository,
			boolean overwrite) throws IOException, PortalException, SystemException{
		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(srcDLEntryId);
		InputStream stream = null;
		
		stream = fileEntry.getContentStream();
		Path targetPath = getRepositoryPath(targetScreenName, target, targetRepository);
		Path targetFolder = targetPath.getParent();
		String owner = targetScreenName+ ":" + OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();

		if( !Files.exists( targetFolder ) ){
			Files.createDirectories(targetFolder);
		}
		
		Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
		changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
		if( Validator.isNotNull(stream) )
			stream.close();
		
		return target;
	}

	/**
	 * Change owner of a file
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	@Deprecated
	public  void changeFileOwner( 
			PortletRequest portletRequest, 
			String target, 
			String owner, 
			String repositoryType ) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		owner = owner + ":edisonuser";
		
		changeFileOwner(targetPath.toString(), owner);
	}
	
	public  void changeFileOwner( 
			String screenName, 
			String target, 
			String owner, 
			String repositoryType ) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		owner = owner + ":" + OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();
		
		changeFileOwner(targetPath.toString(), owner);
	}
	
	/**
	 * Change mode of a file
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	@Deprecated
	public  void changeFileMode( 
			PortletRequest portletRequest, 
			String target, 
			String mode, 
			String repositoryType) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		changeFileMode(targetPath.toString(), mode);
	}
	
	public  void changeFileMode( 
			String screenName, 
			String target, 
			String mode, 
			String repositoryType) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		changeFileMode(targetPath.toString(), mode);
	}

	
	/**
	 * Check a file is exist or not.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param filePath
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void duplicated( 
			PortletRequest portletRequest, 
			PortletResponse portletResponse, 
			String filePath,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, filePath, repositoryType);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("duplicated", Files.exists( targetPath ));
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}

	public  void duplicated(
			PortletResponse portletResponse,
			String screenName, 
			String filePath,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, filePath, repositoryType);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("duplicated", Files.exists( targetPath ));
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}

	/**
	 * Upload a file from session request as the target.
	 * 
	 * @param portletRequest
	 * @param target
	 * @param uploadFileName
	 * @param repositoryType
	 * 
	 * @throws SystemException
	 * @throws PortalException
	 * @throws IOException
	 */
	@Deprecated
	public  void upload( 
			PortletRequest portletRequest, 
			String target,
			String uploadFileName,
			String repositoryType) throws SystemException, PortalException, IOException{
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = getUserName(portletRequest)+":edisonuser";
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
		}

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(portletRequest);
		
		// Get the uploaded file as a file.
		File uploadedFile = uploadRequest.getFile(uploadFileName);
		
		copyFile(uploadedFile.toPath(), targetPath, true);
        changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
	}
	
	public  void upload(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			File uploadFile,
			String screenName, 
			String targetFolder,
			String fileName,
			String repositoryType) throws SystemException, PortalException, IOException{
		
		Path targetFolderPath = getRepositoryPath(screenName, targetFolder, repositoryType);
		String owner = screenName+":"+OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();
		if( Files.notExists(targetFolderPath) ){
			Files.createDirectories(targetFolderPath);
		}

		Path targetPath = targetFolderPath.resolve(fileName);
		copyFile(uploadFile.toPath(), targetPath, true);
		
		if( !System.getProperty("os.name").toLowerCase().contains("windows") ) {
			changeFileOwner(targetFolder.toString(), owner);
			changeFileMode(targetFolder.toString(), "g+w");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("user_", screenName);
		result.put("parent_", targetFolder);
		result.put("name_", fileName);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString());
	}


	/**
	 * Download a DLEntry file.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param dlFileEntryId
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	public  void downloadDLEntry(
		    PortletRequest portletRequest, 
	        PortletResponse portletResponse, 
	        long dlFileEntryId) throws PortalException, SystemException, IOException{
		    HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
	        HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
	        
		    DLFileEntry dlFileEntry = DLFileEntryServiceUtil.getFileEntry(dlFileEntryId);
		    InputStream inputStream = dlFileEntry.getContentStream();
	        String fileName = dlFileEntry.getTitle();
	        ServletResponseUtil.sendFile(
	            httpRequest, httpResponse, fileName, inputStream, 
	            dlFileEntry.getSize(), "application/octet-stream", "attachment");
	}
	
	/**
	 * Download a file or files.
	 * If count of the files is larger than 1, the files zipped as an file.
	 */
	@Deprecated
	public  void download(
			PortletRequest portletRequest, 
			PortletResponse portletResponse, 
			String targetFolder, 
			String[] files,
			String repositoryType) throws IOException, PortalException, SystemException{
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);

		Path targetPath = getRepositoryPath(portletRequest, targetFolder, repositoryType);
		if( files.length == 1 ){
			Path filePath = targetPath.resolve(files[0]);
			ServletResponseUtil.sendFile(httpRequest, httpResponse, filePath.getFileName().toString(), Files.readAllBytes(filePath), "application/octet-stream", "attachment");
		}
		else{
			final Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-xr--");
			final FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

			Path tempPath = getRepositoryPath(portletRequest, TEMP_DIR_NAME, repositoryType);
			Files.createDirectories(tempPath, attr );
			
			Path zipPath = tempPath.resolve(targetPath.getFileName()+".zip");
			Files.deleteIfExists(zipPath);
			Files.createFile(zipPath, attr);
			zipPath.toFile().deleteOnExit();

			List<Path> filePaths = new ArrayList<Path>();
			for( String file : files ){
				filePaths.add( targetPath.resolve(file) );
			}
			zip(filePaths, zipPath);

			InputStream inputStream = new FileInputStream(zipPath.toFile());
			ServletResponseUtil.sendFile(
					httpRequest, httpResponse, 
					zipPath.getFileName().toString(), 
					inputStream, 
					Files.size(zipPath),
					"application/octet-stream", "attachment");
		}
	}
	
	public  void download(
			PortletRequest portletRequest, 
			PortletResponse portletResponse, 
			String screenName,
			String targetFolder, 
			String[] files,
			String repositoryType) throws IOException, PortalException, SystemException{
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);

		Path targetPath = getRepositoryPath(screenName, targetFolder, repositoryType);
		InputStream inputStream = null;
		String fileName="";
		long fileSize=0;
		if( files.length == 1 ){
			Path filePath = targetPath.resolve(files[0]);
			inputStream = new FileInputStream(filePath.toFile());
			fileName = filePath.getFileName().toString();
			fileSize = Files.size( filePath );
		}
		else{
//			final Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-xr--");
//			final FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

			Path tempPath = getRepositoryPath(screenName, TEMP_DIR_NAME, repositoryType);
//			Files.createDirectories(tempPath, attr );
			Files.createDirectories(tempPath);
			
			Path zipPath = tempPath.resolve(targetPath.getFileName()+".zip");
			Files.deleteIfExists(zipPath);
//			Files.createFile(zipPath, attr);
			Files.createFile(zipPath);
			zipPath.toFile().deleteOnExit();

			List<Path> filePaths = new ArrayList<Path>();
			for( String file : files ){
				filePaths.add( targetPath.resolve(file) );
			}
			zip(filePaths, zipPath);

			inputStream = new FileInputStream(zipPath.toFile());
			fileName = zipPath.getFileName().toString();
			fileSize = Files.size( zipPath );
		}
		
		ServletResponseUtil.sendFile(
				httpRequest, httpResponse, 
				fileName, 
				inputStream, 
				fileSize,
				"application/octet-stream", 
				"attachment");
	}

	   
    public  void downloadFromText(
        PortletRequest portletRequest, 
        PortletResponse portletResponse,
        String fileName,
        String fileContext) throws PortalException, SystemException, IOException{
        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
        HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
        InputStream inputStream = new ByteArrayInputStream(fileContext.getBytes("UTF-8"));
        ServletResponseUtil.sendFile(
            httpRequest, httpResponse, fileName, inputStream, "text/plain");
    }
	
	/**
	 * Check a file is regular file or not.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param target
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
    @Deprecated
	public  void checkValidFile( 
			PortletRequest portletRequest, 
			PortletResponse portletResponse, 
			String target,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		_log.info("checkValidFile()target: "+target.toString());
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("valid", Files.isRegularFile( targetPath ));
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}
    
	public  void checkValidFile( 
			PortletResponse portletResponse, 
			String screenName,
			String target,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		_log.info("checkValidFile()target: "+target.toString());
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("valid", Files.isRegularFile( targetPath ));

		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}

	
	/**
	 * Read a file content as a character stream.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param target
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void readFileContent(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String target,
			String repositoryType) throws PortalException, SystemException, IOException{
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, targetPath.toFile() );
	}
	
	public  void readFileContent(
			PortletResponse portletResponse,
			String screenName,
			String target,
			String repositoryType) throws PortalException, SystemException, IOException{
		
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, targetPath.toFile() );
	}

	@Deprecated
	public  void getFile(
		    PortletRequest portletRequest,
	        PortletResponse portletResponse,
	        String target,
	        String repositoryType) throws PortalException, SystemException, IOException{
			Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
			
		    HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
	        HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		
	        InputStream inputStream = new FileInputStream(targetPath.toFile());
		    ServletResponseUtil.sendFile(
		        httpRequest, httpResponse, targetPath.getFileName().toString(), inputStream);
	}
	
	public  void getFile(
		    PortletRequest portletRequest,
	        PortletResponse portletResponse,
	        String screenName,
	        String target,
	        String repositoryType) throws PortalException, SystemException, IOException{
			Path targetPath = getRepositoryPath(screenName, target, repositoryType);
			
		    HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
	        HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		
	        InputStream inputStream = new FileInputStream(targetPath.toFile());
		    ServletResponseUtil.sendFile(
		        httpRequest, httpResponse, targetPath.getFileName().toString(), inputStream);
	}


	/**
	 * Download a file.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param target
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void downloadFile(
		    PortletRequest portletRequest,
	        PortletResponse portletResponse,
	        String target,
	        String repositoryType) throws PortalException, SystemException, IOException{
		
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		InputStream inputStream = new FileInputStream(targetPath.toFile());
		ServletResponseUtil.sendFile(
		        httpRequest, httpResponse, 
		        targetPath.getFileName().toString(),
		        inputStream,
		        Files.size(targetPath),
		        "application/octet-stream", "attachment");
	}

	public  void downloadFile(
		    PortletRequest portletRequest,
	        PortletResponse portletResponse,
	        String screenName,
	        String target,
	        String repositoryType) throws PortalException, SystemException, IOException{
		
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		InputStream inputStream = new FileInputStream(targetPath.toFile());
		ServletResponseUtil.sendFile(
		        httpRequest, httpResponse, 
		        targetPath.getFileName().toString(),
		        inputStream,
		        Files.size(targetPath),
		        "application/octet-stream", "attachment");
	}

	/**
	 * Read a file content with the specified content type.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param contentType
	 * @param target
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void readFileContent(
		    PortletRequest portletRequest,
		    PortletResponse portletResponse,
		    String contentType,
		    String target,
		    String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		if(contentType != null && !contentType.isEmpty()){
			httpResponse.setContentType(contentType);
		}
	    ServletResponseUtil.write(httpResponse, targetPath.toFile() );
	}
	
	public  void readFileContent(
		    PortletResponse portletResponse,
		    String contentType,
		    String screenName,
		    String target,
		    String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		if(contentType != null && !contentType.isEmpty()){
			httpResponse.setContentType(contentType);
		}
	    ServletResponseUtil.write(httpResponse, targetPath.toFile() );
	}
	
	/**
	 * Read file content or the first file in a folder.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param target
	 * @param filter
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void readFirstFileContent(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String target,
			String filter,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		if(Files.isDirectory(targetPath)){
			File[] subFiles = targetPath.toFile().listFiles( new FileExtensionFilter(filter) );
			for(File file : subFiles){
				if(file.isFile()){
					ServletResponseUtil.write(httpResponse, file);
					break;
				}
			}
		}else if(Files.isRegularFile(targetPath)){
			ServletResponseUtil.write(httpResponse, targetPath.toFile());
		}
	}

	public  void readFirstFileContent(
			PortletResponse portletResponse,
			String screenName,
			String target,
			String filter,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		if(Files.isDirectory(targetPath)){
			File[] subFiles = targetPath.toFile().listFiles( new FileExtensionFilter(filter) );
			for(File file : subFiles){
				if(file.isFile()){
					ServletResponseUtil.write(httpResponse, file);
					break;
				}
			}
		}else if(Files.isRegularFile(targetPath)){
			ServletResponseUtil.write(httpResponse, targetPath.toFile());
		}
	}

	/**
	 * Read a file content
	 * 
	 * @param portletRequest
	 * @param target
	 * @param repositoryType
	 * 
	 * @return
	 * 	byte[] file content
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  byte[] readFileContent( 
			PortletRequest portletRequest, 
			String target, 
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		return Files.readAllBytes(targetPath);
	}
	
	public  byte[] readFileContent( 
			String screenName, 
			String target, 
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		return Files.readAllBytes(targetPath);
	}

	
	/**
	 * Gets the file name of a path or the first file name of a folder.
	 * 
	 * @param portletRequest
	 * @param portletResponse
	 * @param target
	 * @param filter
	 * @param repositoryType
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void getFirstFileName(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String target,
			String filter,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		File[] files;
		if( Validator.isNotNull(filter) ){
			files = targetPath.toFile().listFiles( new FileExtensionFilter(filter) );
		}else {
			files = targetPath.toFile().listFiles();
		}
		
		for(File file : files){
			if(file.isFile()){
				result.put("parentPath", target);
				result.put("fileName", file.getName());
				ServletResponseUtil.write(httpResponse, result.toString());
				return;
			}
		}
	}

	public  void getFirstFileName(
			PortletResponse portletResponse,
			String screenName,
			String target,
			String filter,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		File[] files;
		if( Validator.isNotNull(filter) ){
			files = targetPath.toFile().listFiles( new FileExtensionFilter(extractExtension(filter)) );
		}else {
			files = targetPath.toFile().listFiles();
		}
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		for(File file : files){
			if(file.isFile()){
				result.put("parentPath", target);
				result.put("fileName", file.getName());
				ServletResponseUtil.write(httpResponse, result.toString());
				return;
			}
		}
		
		result.put("result", "no-file");
		ServletResponseUtil.write(httpResponse, result.toString());
	}

	/**
	 * Save the specified content to a file.
	 * 
	 * @param portletRequest
	 * @param target
	 * @param content
	 * @param repositoryType
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@Deprecated
	public  void saveFileContent(
			PortletRequest portletRequest,
			String target,
			String content,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = getUserName(portletRequest)+":edisonuser";
		if( Files.notExists( targetFolder ) ){
			Files.createDirectories(targetFolder);
		}
		
		OpenOption[] openOptions = new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
		Files.write(targetPath, content.getBytes(StandardCharsets.UTF_8), openOptions);
		changeFileOwner(targetFolder.toString(), owner);
        changeFileMode(targetFolder.toString(), "g+w");
		
	}
	
	public  void saveFileContent(
			PortletResponse portletResponse,
			String screenName,
			String target,
			String content,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		Path targetFolder = targetPath.getParent();
		String owner = screenName + ":" + OSPPropsUtil.OSP_PORTAL_SERVER_USER_GROUP();
		if( Files.notExists( targetFolder ) ){
			Files.createDirectories(targetFolder);
		}
		
		OpenOption[] openOptions = new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
		Files.write(targetPath, content.getBytes(StandardCharsets.UTF_8), openOptions);
		if( !System.getProperty("os.name").toLowerCase().contains("windows") ) {
			changeFileOwner(targetFolder.toString(), owner);
	        changeFileMode(targetFolder.toString(), "g+w");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("path", target);
		
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString());
	}


    public  void writeTextFile(String content, Path target) throws IOException{
        if(!Files.exists(target.getParent())){
            Files.createDirectories(target.getParent());
        }
        Files.write(target, content.getBytes(StandardCharsets.UTF_8), 
            new OpenOption[]{
                StandardOpenOption.CREATE_NEW, 
                StandardOpenOption.WRITE, 
                StandardOpenOption.TRUNCATE_EXISTING
                }
        );
    }
	
	public  void readDLAppEntry ( PortletResponse portletResponse, long dlEntryId ) throws SystemException, IOException{
		FileEntry fileEntry;
		JSONObject resultData = JSONFactoryUtil.createJSONObject();
		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(dlEntryId);
		} catch (PortalException e) {
			resultData.put("dlEntryId", dlEntryId);
			writeToClient(portletResponse, "Invalid DLEntry ID", resultData);
			return;
		}
		
		InputStream stream;
		try {
			stream = fileEntry.getContentStream();
		} catch (PortalException e) {
			resultData.put("dlEntryId", dlEntryId);
			writeToClient(portletResponse, "Invalid DLEntry ID", resultData);
			return;
		}

		String content = new String(FileUtil.getBytes(stream));
		stream.close();
		
		resultData.put("type_", "fileContent");
		resultData.put("context_", content);
		
		writeToClient( portletResponse, null, resultData );
	}
	
	public  void writeToClient( PortletResponse portletResponse, String errorMessage, JSONObject data ) throws IOException{
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if( errorMessage != null ){
			result.put( "error", errorMessage );
		}
		else{
			result.put( "success", data );
		}
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}
	
    public  String readTextFile(Path path) throws IOException{
        return readTextAndLastPosition(path, 0).getText();
    }
	
    @Deprecated
	public  JSONObject readFileAtPosition(
			PortletRequest portletRequest,
			String target,
			long startPosition,
			long size,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		TextAndLastPosition outLog = readTextAndLastPosition(targetPath, startPosition);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("outLog", outLog.getText());
		result.put("lastPosition", outLog.getLastPosition());
		return result;
	}
    
	public  JSONObject readFileAtPosition(
			String screenName,
			String target,
			long startPosition,
			long size,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(screenName, target, repositoryType);
		
		TextAndLastPosition outLog = readTextAndLastPosition(targetPath, startPosition);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("outLog", outLog.getText());
		result.put("lastPosition", outLog.getLastPosition());
		return result;
	}

	
    /**
     *  Get path based on repository type.
     */
	@Deprecated
    public  Path getRepositoryPath( PortletRequest portletRequest, String path, String repoType ) throws PortalException, SystemException{
    	Path targetPath;
    	OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(repoType);
    	switch( repositoryType ){
    		case USER_HOME:
    		case USER_JOBS:
    			targetPath = Paths.get( OSPPropsUtil.USER_ROOT_DIR_PATH(),
    													getUserName(portletRequest),
    													repositoryType.value(),
    													path );
    			break;
    		case PROVENANCE:
    			targetPath = Paths.get( OSPPropsUtil.PROVENANCE_ROOT_DIR_PATH() ).resolve(path);
    			break;
    		case ICECAP:
    			targetPath = Paths.get( OSPPropsUtil.ICECAP_ROOT_DIR_PATH() ).resolve(path);
    			break;
    		case SCIENCEAPP:
    			targetPath = Paths.get( OSPPropsUtil.SPYGLASS_ROOT_DIR_PATH() ).resolve(path);
    			break;
    		default:
    			targetPath = Paths.get("/");
    	}
    	
    	return targetPath;
    }
    
    public  Path getRepositoryPath(String userName, String path, String repoType)
        throws PortalException, SystemException{
    	System.out.println("Path: "+path);
        if("edison".equals(userName)){
            userName = "edisonadm";
        }
        Path targetPath;
        OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(repoType);
        switch (repositoryType){
            case USER_HOME:
            case USER_JOBS:
                targetPath = Paths.get(OSPPropsUtil.USER_ROOT_DIR_PATH(), userName,
                    repositoryType.value(), path);
                break;
            case PROVENANCE:
                targetPath = Paths.get(OSPPropsUtil.PROVENANCE_ROOT_DIR_PATH()).resolve(path);
                break;
            case ICECAP:
                targetPath = Paths.get(OSPPropsUtil.ICECAP_ROOT_DIR_PATH()).resolve(path);
                break;
            case SCIENCEAPP:
                targetPath = Paths.get(OSPPropsUtil.SPYGLASS_APPS_DIR_PATH()).resolve(path);
                break;
            default:
                targetPath = Paths.get("/");
        }
        return targetPath;
    }
    
    @Deprecated
    public  void getFileURL( 
    		PortletRequest portletRequest, 
    		PortletResponse portletResponse, 
    		String path, 
    		String repoType ) throws PortalException, SystemException, IOException{
    	Path targetPath = getRepositoryPath(portletRequest, path, repoType);
    	
    	writeResult( portletResponse, targetPath );
    }

    public  void getFileURL( 
    		PortletResponse portletResponse, 
    		String screenName,
    		String path, 
    		String repoType ) throws PortalException, SystemException, IOException{
    	Path targetPath = getRepositoryPath(screenName, path, repoType);
    	
    	writeResult( portletResponse, targetPath );
    }

    public  String getJobResultPath( String simulationUuid, String jobUuid, String path ){
    	return Paths.get(simulationUuid, jobUuid+".job", path).toString();
    }
    
    public void getFileInformation( PortletResponse portletResponse, String userScreenName, String pathType, String folderPath, String fileName, String repoType ) throws PortalException, SystemException, IOException {
		JSONArray fileInfos = null;
		JSONObject resultJSON = JSONFactoryUtil.createJSONObject();
		if( pathType.equalsIgnoreCase("file") ){
			Path targetPath = Paths.get(folderPath, fileName); 
			
			JSONObject fileInfo = getFileInformation(userScreenName, Paths.get(folderPath, fileName).toString(), repoType);
			if(targetPath.getParent() != null){
			    resultJSON.put("parentPath", targetPath.getParent().toString());
			}else{
			    resultJSON.put("parentPath", "");
			}
			fileInfos = JSONFactoryUtil.createJSONArray();
			fileInfos.put(fileInfo);
		}
		else if( pathType.equalsIgnoreCase("ext") ){
			fileInfos = getFolderInformation(userScreenName, folderPath, fileName, repoType);
			resultJSON.put("parentPath", folderPath);
		}
		else if( pathType.equalsIgnoreCase("folder") ){
			fileInfos = getFolderInformation(userScreenName, folderPath, "", repoType);
			resultJSON.put("parentPath", folderPath);
		}
		else
			throw new IOException("Unknown path type: " + pathType);
		
		resultJSON.put("fileName", fileName);
		resultJSON.put( "fileInfos", fileInfos );
		
		writeResult(portletResponse, resultJSON.toString());
    }
    
    public void processOSPResourceAction( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) {

		String command = ParamUtil.getString(resourceRequest, "command");
		String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_HOME.toString());
		System.out.println("command: "+command);
		System.out.println("RepositoryType: "+repositoryType);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		
		String userScreenName = ParamUtil.getString(resourceRequest, "userScreenName", user.getScreenName());
		String parentPath = ParamUtil.getString(resourceRequest, "parentPath", "");
		String fileName = ParamUtil.getString(resourceRequest, "fileName", "");
		String pathType = ParamUtil.getString(resourceRequest, "pathType", "file");
		
		if( command.equalsIgnoreCase("GET_FILE_INFO") ){
			try {
				getFileInformation(resourceResponse, userScreenName, pathType, parentPath, fileName, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.READ_HTML_INDEX_URL) ){
			try {
				getCopiedTemporaryFilePath( 
							resourceRequest, resourceResponse, userScreenName, parentPath, fileName, repositoryType );
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.READ_FILE) ){
			try {
				readFileContent(resourceResponse, userScreenName, Paths.get(parentPath, fileName).toString(), repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equalsIgnoreCase("READ_FIRST_FILE")){
            try {
				readFirstFileContent(resourceResponse, userScreenName, parentPath, fileName, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		else if(command.equalsIgnoreCase("GET_FIRST_FILE_NAME")){
            try {
				getFirstFileName(resourceResponse, userScreenName, parentPath, fileName, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		else if( command.equalsIgnoreCase(Commands.READ_DLENTRY) ){
			long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
			
			try {
				readDLAppEntry(resourceResponse, entryId);
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.CHECK_DUPLICATED) ){
			String target = ParamUtil.getString(resourceRequest, "target");
			try {
				duplicated(resourceResponse, userScreenName, target, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.GET_ABSOLUTE_PATH) ){
			String targetPath = ParamUtil.getString(resourceRequest, "targetPath", "");
			try {
				getFileURL( resourceResponse, userScreenName, targetPath, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.CREATE) ){
			
		}
		else if( command.equalsIgnoreCase(Commands.COPY) ){
			
		}
		else if( command.equalsIgnoreCase(Commands.DELETE) ){
			
		}
		else if( command.equalsIgnoreCase(Commands.MOVE) ){
			
		}
		else if( command.equalsIgnoreCase(Commands.SAVE) ){
			String content = ParamUtil.getString(resourceRequest, "content");
			try {
				saveFileContent( resourceResponse, userScreenName, Paths.get(parentPath,  fileName).toString(), content, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.UPLOAD)){
			String uploadFileParam = "uploadFile";
			
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			File uploadFile = uploadRequest.getFile(uploadFileParam);
			System.out.println("uploadedFile: "+uploadFile.getName());

			try {
				upload(resourceRequest, resourceResponse, uploadFile, userScreenName, parentPath, fileName, repositoryType);
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( command.equalsIgnoreCase(Commands.DOWNLOAD)){
			String fileNames = ParamUtil.getString(resourceRequest, "fileNames");
			
			JSONArray files = null;
			String[] sources = null;
			try {
				files = JSONFactoryUtil.createJSONArray(fileNames);
				sources = new String[files.length()];
				for (int i=0; i<files.length(); i++ ) {
					sources[i] = files.getString(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			try {
				download(resourceRequest, resourceResponse, userScreenName,  parentPath, sources, repositoryType);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("{ERROR] Unsupported command: "+command);
		}
    }
    
    
    public JSONObject setJobDataWithFileFormOutputData(String srcScreenName, String srcSimulationUuid, String srcJobUuid, JSONObject outputData, String targetScreenName) throws PortalException, SystemException, IOException{
    	if(!outputData.has("type_")){
    		throw new PortalException("JSONObject is Not Key : type_");
    	}else{
    		if(!outputData.getString("type_").equals("file")){
    			throw new PortalException("JSONObject is Not Type File - this : "+outputData.getString("type_"));
    		}
    	}
    	
    	try{
    		String fileName = outputData.getString("name_");
    		Path sourcePath = Paths.get(srcSimulationUuid,srcJobUuid+".job",outputData.getString("parent_"),fileName);
    		Path sourceFullPath = getRepositoryPath(srcScreenName, sourcePath.toString(), OSPRepositoryTypes.USER_JOBS.toString());
    	
    		return copyFileReturnInputData(sourceFullPath, fileName, targetScreenName, outputData);
    	}catch (JSONException e) {
			throw new PortalException(e);
		}
    }
    
    public JSONObject setJobDataWithFileFormInputData(String srcScreenName, JSONObject inputData, String targetScreenName) throws PortalException, SystemException, IOException{
    	if(!inputData.has("type_")){
    		throw new PortalException("JSONObject is Not Key : type_");
    	}else{
    		if(!inputData.getString("type_").equals("file")){
    			throw new PortalException("JSONObject is Not Type File - this : "+inputData.getString("type_"));
    		}
    	}
    	
    	try{
    		String fileName = inputData.getString("name_");
    		Path sourcePath = Paths.get(inputData.getString("parent_"),fileName);
    		Path sourceFullPath = getRepositoryPath(srcScreenName, sourcePath.toString(), OSPRepositoryTypes.USER_HOME.toString());
    		
    		return copyFileReturnInputData(sourceFullPath, fileName, targetScreenName, inputData);
    	}catch (JSONException e) {
			throw new PortalException(e);
		}
    }
    
    protected JSONObject copyFileReturnInputData(Path sourcePath, String sourceFileName,String targetScreenName, JSONObject data) throws IOException, PortalException, SystemException {
    	SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
		String parentFolderName = dateForm.format(new Date());
		Path targetPath = Paths.get(parentFolderName,sourceFileName);
		Path targetFullPath = getRepositoryPath(targetScreenName, targetPath.toString(), OSPRepositoryTypes.USER_HOME.toString());
		
    	File targetPathFile = new File(targetFullPath.toString());
		if(!targetPathFile.exists()){
			Files.createDirectories(targetFullPath);
		}
		
    	copyFile(sourcePath, targetFullPath, true);
    	
    	data.put("parent_", parentFolderName);
    	
    	return data;
	}
    
	public class Commands {
		public static final String CHECK_DUPLICATED = "CHECK_DUPLICATED";
		public static final String CHECK_VALID_FILE = "CHECK_VALID_FILE";
		public static final String COPY = "COPY";
		public static final String CREATE = "CREATE";
		public static final String DELETE = "DELETE";
		public static final String DOWNLOAD = "DOWNLOAD";
		public static final String READ_HTML_INDEX_URL="READ_HTML_INDEX_URL";
		public static final String GET_COPIED_TEMP_FILE_PATH = "GET_COPIED_TEMP_FILE_PATH";
		public static final String GET_COPIED_TEMP_HTML_PATH = "GET_COPIED_TEMP_HTML_PATH";
		public static final String GET_FILE_INFO = "GET_FILE_INFO";
		public static final String GET_LINKED_TEMP_FILE_PATH = "GET_LINKED_TEMP_FILE_PATH";
		public static final String GET_LINKED_TEMP_HTML_PATH = "GET_LINKED_TEMP_HTML_PATH";
		public static final String MOVE = "MOVE";
		public static final String READ_DLENTRY = "READ_DLENTRY";
		public static final String READ_FILE = "READ_FILE";
		public static final String SAVE = "SAVE";
		public static final String UPLOAD = "UPLOAD";
		public static final String  READ_FIRST_FILE="READ_FIRST_FILE";
		public static final String  GET_FIRST_FILE_NAME="GET_FIRST_FILE_NAME";
		public static final String  GET_ABSOLUTE_PATH="GET_ABSOLUTE_PATH";
	}

}