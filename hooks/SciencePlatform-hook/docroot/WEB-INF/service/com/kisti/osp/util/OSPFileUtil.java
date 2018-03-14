package com.kisti.osp.util;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

public class OSPFileUtil {
	static private String TEMP_DIR_NAME = "temp";
	private static long _DEFAULT_TIMEOUT = 60000;
	
	private static Log _log = LogFactoryUtil.getLog(OSPFileUtil.class);
	
	static private Path getRealTemporaryFolderPath( Path realBasePath ){
		Path baseTempPath = Paths.get(TEMP_DIR_NAME);
		return realBasePath.resolve(baseTempPath);
	}
	
	static private Path createBaseTemporaryFolderPath( Path realContextPath ) throws IOException{
		Path baseTempFolderPath = getRealTemporaryFolderPath(realContextPath);
		if( Files.notExists(baseTempFolderPath) ){
			baseTempFolderPath = Files.createDirectory(baseTempFolderPath);
			baseTempFolderPath.toFile().deleteOnExit();
		}
		return baseTempFolderPath;
	}

	static private Path createTemporaryFilePath( Path realContextPath, String prefix,  String suffix, boolean isFile ) throws IOException{
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
	
	static private Path getUniqueUuidFilePath( Path baseFolderPath, String prefix, String suffix ){
		Path uuidFilePath = null;
		do{
			UUID uuid = UUID.randomUUID();
			Path uuidFileName = Paths.get(prefix+uuid.toString()+suffix);
			uuidFilePath = baseFolderPath.resolve(uuidFileName);
		} while( Files.exists(uuidFilePath) );

		return uuidFilePath;
	}
	
	
	static private String extractExtension( String fileName ){
		int lastIndex = fileName.lastIndexOf('.');
		if( lastIndex < 0 )	return fileName;
		
		return fileName.substring(lastIndex);
	}
	
	static private JSONArray lookUpFolder( File folder, String filter ) throws IOException{
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
	
	static private JSONObject getFileInformation( File file ) throws IOException{
		JSONObject fileInfo = JSONFactoryUtil.createJSONObject();
		fileInfo.put("name", file.getName());
		fileInfo.put("size", convert2ReadableFileSize(file.length()));
		fileInfo.put("isFile", file.isFile());
		
		return fileInfo;
	}
	
	static private JSONObject getFileInformation( Path filePath ) throws IOException{
		JSONObject fileInfo = JSONFactoryUtil.createJSONObject();
//		System.out.println("To get fileInfo: "+filePath.toString());
		fileInfo.put("name", filePath.getFileName().toString());
		fileInfo.put("size", convert2ReadableFileSize(Files.size(filePath)));
		fileInfo.put("isFile", Files.isRegularFile(filePath, LinkOption.NOFOLLOW_LINKS));
		
		return fileInfo;
	}
	
	static private String convert2ReadableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
	static private class FileExtensionFilter implements FilenameFilter {
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
	
	/**
	 *  Link a file or a folder to servlet container template folder based on 
	 *  of the servlet context path.
	 *  Returns the template path based on of the servlet context path. 
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public static String getLinkedTemporaryFilePath(
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

	/**
	 * Copy a file or a folder to servlet container template folder based on 
	 *  of the servlet context path.
	 *  Returns the template path based on of the servlet context path.
	 */
	public static String getCopiedTemporaryFilePath(
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
	public static JSONArray getFolderInformation( 
			PortletRequest portletRequest, 
			String folderPath, 
			String filter,
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, folderPath, repositoryType);
		filter = extractExtension(filter);
		
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
	public static JSONObject getFileInformation( 
			PortletRequest portletRequest, 
			String filePath,
			String repositoryType ) throws IOException, PortalException, SystemException{
		return getFileInformation(getRepositoryPath(portletRequest, filePath, repositoryType));
	}
	
	public static Path createFile( PortletRequest portletRequest, String target, String repositoryType ) throws PortalException, SystemException, IOException{
		Path filePath = getRepositoryPath(portletRequest, target, repositoryType);
		Path parentPath = Files.createDirectories(filePath.getParent());
		Files.deleteIfExists(filePath);
		return Files.createFile(filePath);
	}
	
	public static void deleteFile( 
			PortletRequest portletRequest,  
			String target, 
			String repositoryType ) throws IOException, PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		deleteFile(targetPath);
	}
	
	static private void deleteFile(Path target) throws IOException{
		Files.deleteIfExists(target);
	}
	
	public static String moveFile( 
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
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
			String owner = getUserName(portletRequest);
			
			changeFileOwner(portletRequest, targetFolder.toString(), owner, repositoryType);
			changeFileMode(portletRequest, targetFolder.toString(), "755", repositoryType);
		}
		
		moveFile(sourcePath, targetPath, overwrite);
		
		return target;
	}
	
	static private Path moveFile(
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
	
	public static String copyFile( 
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
		if( Files.notExists(targetFolder) ){
			Files.createDirectories(targetFolder);
			String owner = getUserName(portletRequest);
			
			changeFileOwner(portletRequest, targetFolder.toString(), owner, repositoryType);
			changeFileMode(portletRequest, targetFolder.toString(), "755", repositoryType);
		}
		
		copyFile(sourcePath, targetPath, overwrite);
		
		return target;
	}
	
	static private Path copyFile( Path source, Path target, boolean overwrite ) throws IOException{
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
	
	/**
	 *  Copy an dlentry to the target file
	 * @throws IOException 
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public static String copyDLEntryFile(
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
		if( !Files.exists( targetFolder ) ){
			Files.createDirectories(targetFolder);
			String owner = getUserName(portletRequest);
			
			changeFileOwner(targetFolder.toString(), owner+":edisonuser");
			changeFileMode(targetFolder.toString(), "755");
		}
		
		Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
		changeFileOwner(targetPath.toString(), owner+":edisonuser");
		changeFileMode(targetPath.toString(), "755");
		
		if( Validator.isNotNull(stream) )
			stream.close();
		
		return target;
	}
	
	/**
	 * Change owner of a file
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public static void changeFileOwner( 
			PortletRequest portletRequest, 
			String target, 
			String owner, 
			String repositoryType ) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		owner = owner + ":edisonuser";
		
		changeFileOwner(targetPath.toString(), owner);
	}
	
	/**
	 * Change mode of a file
	 * @throws SystemException 
	 * @throws PortalException 
	 */
	public static void changeFileMode( 
			PortletRequest portletRequest, 
			String target, 
			String mode, 
			String repositoryType) throws PortalException, SystemException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		changeFileMode(targetPath.toString(), mode);
	}
	
	static private void changeFileOwner( String target, String owner ){
		String strCmd = "";
		strCmd += "sudo chown -R ";
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
	
	static private void changeFileMode( 
			String target, 
			String mode ) throws PortalException, SystemException{
		String strCmd = "";
		strCmd += "sudo chmod ";
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

	
	static private DefaultExecuteResultHandler execute(CommandLine cmdLine, OutputStream outStream, OutputStream errorStream)
            throws ExecuteException, IOException {
        return execute(cmdLine, Collections.<String, String> emptyMap(), outStream, errorStream);
    }

    static private DefaultExecuteResultHandler execute(CommandLine cmdLine, Map<String, String> environment, OutputStream outStream,
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
	public static void duplicated( 
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
	public static void upload( 
			PortletRequest portletRequest, 
			String target,
			String uploadFileName,
			String repositoryType) throws SystemException, PortalException, IOException{
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(portletRequest);
		
		// Get the uploaded file as a file.
		File uploadedFile = uploadRequest.getFile(uploadFileName);
		
		// Move the existing temporary file to new location.
		Files.copy(uploadedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
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
	public static void downloadDLEntry(
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
	public static void download(
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
	   
    public static void downloadFromText(
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
	
	static private void addZipEntry( ZipOutputStream zipOutputStream, Path fileName, Path base ) throws IOException{
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
	
	static private void zip( List<Path> sources, Path destination ) throws IOException{
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
	public static void checkValidFile( 
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
	public static void readFileContent(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String target,
			String repositoryType) throws PortalException, SystemException, IOException{
		
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		ServletResponseUtil.write(httpResponse, targetPath.toFile() );
	}
	
	public static void getFile(
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
	public static void downloadFile(
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
	public static void readFileContent(
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
	public static void readFirstFileContent(
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
	public static byte[] readFileContent( 
			PortletRequest portletRequest, 
			String target, 
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
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
	public static void getFirstFileName(
			PortletRequest portletRequest,
			PortletResponse portletResponse,
			String target,
			String filter,
			String repositoryType ) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if(Files.isDirectory(targetPath)){
			File[] subFiles = targetPath.toFile().listFiles( new FileExtensionFilter(filter) );
			for(File file : subFiles){
				if(file.isFile()){
					result.put("parentPath", file.getParent());
					result.put("fileName", file.getName());
					ServletResponseUtil.write(httpResponse, result.toString());
					break;
				}
			}
		}else if(Files.isRegularFile(targetPath)){
			_log.info("readFirstFileName() target: " + target.toString());
			result.put("parentPath", targetPath.getParent().toString());
			result.put("fileName", targetPath.getFileName().toString());
			ServletResponseUtil.write(httpResponse, result.toString());
		}
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
	public static void saveFileContent(
			PortletRequest portletRequest,
			String target,
			String content,
			String repositoryType) throws PortalException, SystemException, IOException{
		Path targetPath = getRepositoryPath(portletRequest, target, repositoryType);
		Path parent = targetPath.getParent();
		if( Files.notExists( parent ) ){
			Files.createDirectories(parent);
		}
		OpenOption[] openOptions = new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING};
		Files.write(targetPath, content.getBytes(StandardCharsets.UTF_8), openOptions);
	}

    public static void writeTextFile(String content, Path target) throws IOException{
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
	
	public static void readDLAppEntry ( PortletResponse portletResponse, long dlEntryId ) throws SystemException, IOException{
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
	
	public static void writeToClient( PortletResponse portletResponse, String errorMessage, JSONObject data ) throws IOException{
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
	
    public static String readTextFile(Path path) throws IOException{
        return readTextAndLastPosition(path, 0).getText();
    }
	
	public static JSONObject readFileAtPosition(
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
	
    /**
     *  Get path based on repository type.
     */
    public static Path getRepositoryPath( PortletRequest portletRequest, String path, String repoType ) throws PortalException, SystemException{
    	Path targetPath;
    	OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(repoType);
    	switch( repositoryType ){
    		case USER_HOME:
    		case USER_JOBS:
    			targetPath = Paths.get( OSPPropsUtil.getUserRootDirPath(),
    													getUserName(portletRequest),
    													repositoryType.value(),
    													path );
    			break;
    		case PROVENANCE:
    			targetPath = Paths.get( OSPPropsUtil.getProvenanceRootDirPath() ).resolve(path);
    			break;
    		case ICECAP:
    			targetPath = Paths.get( OSPPropsUtil.getIcecapRootDirPath() ).resolve(path);
    			break;
    		case SCIENCEAPP:
    			targetPath = Paths.get( OSPPropsUtil.getSpyGlassAppsDirPath() ).resolve(path);
    			break;
    		default:
    			targetPath = Paths.get("/");
    	}
    	
    	return targetPath;
    }
    
    public static Path getRepositoryPath(String userName, String path, String repoType)
        throws PortalException, SystemException{
        if("edison".equals(userName)){
            userName = "edisonadm";
        }
        Path targetPath;
        OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(repoType);
        switch (repositoryType){
            case USER_HOME:
            case USER_JOBS:
                targetPath = Paths.get(OSPPropsUtil.getUserRootDirPath(), userName,
                    repositoryType.value(), path);
                break;
            case PROVENANCE:
                targetPath = Paths.get(OSPPropsUtil.getProvenanceRootDirPath()).resolve(path);
                break;
            case ICECAP:
                targetPath = Paths.get(OSPPropsUtil.getIcecapRootDirPath()).resolve(path);
                break;
            case SCIENCEAPP:
                targetPath = Paths.get(OSPPropsUtil.getSpyGlassAppsDirPath()).resolve(path);
                break;
            default:
                targetPath = Paths.get("/");
        }
        return targetPath;
    }
    
    public static void getFileURL( 
    		PortletRequest portletRequest, 
    		PortletResponse portletResponse, 
    		String path, 
    		String repoType ) throws PortalException, SystemException, IOException{
    	Path targetPath = getRepositoryPath(portletRequest, path, repoType);
    	
    	writeResult( portletResponse, targetPath );
    }
    
    private static void writeResult( PortletResponse portletResponse, String result ) throws PortalException, IOException{
    	HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
    	ServletResponseUtil.write( httpResponse, result );
    }
    
    private static void writeResult( PortletResponse portletResponse, Path path ) throws PortalException, IOException{
    	HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
    	ServletResponseUtil.write( httpResponse, path.toString() );
    }
    
    public static String getJobResultPath( String simulationUuid, String jobUuid, String path ){
    	return Paths.get(simulationUuid, jobUuid+".job", path).toString();
    }
    
    static private String getUserName( PortletRequest portletRequest ) throws PortalException, SystemException{
    	String userName = PortalUtil.getUser(portletRequest).getScreenName();
    	if( userName.equalsIgnoreCase("edison") )
    		userName = "edisonadm";
    	
    	return userName;
    }
    
    static private TextAndLastPosition readTextAndLastPosition(Path path, long lastPoistion) throws IOException{
        FileChannel fcIn = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        StringBuffer sb = new StringBuffer();
        if(lastPoistion > 0){
         fcIn.position(lastPoistion);   
        }
        while(fcIn.read(buffer) != -1){
            byte[] bytes = new byte[buffer.position()];
            buffer.flip();
            buffer.get(bytes);
            sb.append(new String(bytes));
            buffer.clear();
        }
        lastPoistion = fcIn.position();
        fcIn.close();
        return new TextAndLastPosition(sb.toString(), lastPoistion);
    }
    
    static private TextAndLastPosition readTextAndLastPosition(Path path, long startPosition, long size) throws IOException{
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
    
    static private class TextAndLastPosition{
        private String text;
        private long lastPosition;
        public TextAndLastPosition(String text, long lastPosition){
            text = text;
            lastPosition = lastPosition;
        }
        public String getText(){
            return text;
        }
        public long getLastPosition(){
            return lastPosition;
        }
    }
    
    static private class OSPFileVisitor extends SimpleFileVisitor<Path>{
    	Path targetPath;
    	Path tempFilePath;
    	
    	public OSPFileVisitor( Path target, Path tempPath ){
    		targetPath = target;
    		tempFilePath = tempPath;
    	}
    	
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Path path = targetPath.relativize(dir);
			Path tempPath = tempFilePath.resolve(path);
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
			Path path = targetPath.relativize(file);
			Path tempPath = tempFilePath.resolve(path);
			try {
				Files.copy(file, tempPath, StandardCopyOption.REPLACE_EXISTING);
				tempPath.toFile().deleteOnExit();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return FileVisitResult.CONTINUE;
		}
    }
}
