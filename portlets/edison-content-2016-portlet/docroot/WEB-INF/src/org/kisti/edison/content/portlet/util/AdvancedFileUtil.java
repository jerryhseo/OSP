package org.kisti.edison.content.portlet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;

public class AdvancedFileUtil {
	
	public static String filePathCreate(long companyId, String contentFilePath){
		String contentPath = PropsUtil.get(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR) + File.separator +"content";
//		String appBasePath = "C:\\Users\\YS\\Desktop\\content";
		contentPath += contentFilePath + File.separator;
		return contentPath;
	}
	
	/*
	 * 파일생성
	 */
	public static String createFile(UploadPortletRequest upload, long companyId, String contentFilePath, String contentSeq, String fileFormName) throws SystemException{
		String advancedFilePath = filePathCreate(companyId, contentFilePath);
		
		File folderPath = new File(advancedFilePath);
		if(!folderPath.isDirectory()) {
			folderPath.mkdirs();
		}
		File[] uploadFiles = upload.getFiles(fileFormName);
		String[] fileNames = upload.getFileNames(fileFormName);
		
		File file = null;
		try{
			
			InputStream[] uploadInputStream = upload.getFilesAsStream(fileFormName,false);
			if(fileNames != null&&Validator.isNotNull(fileNames[0])){
				
				for(int i=0;i<uploadFiles.length;i++){
					byte[] bytes = FileUtil.getBytes(uploadFiles[i]);
					if(ArrayUtil.isNotEmpty(bytes)){
						file = FileUtil.createTempFile(bytes);
					}else{
						InputStream fileObj = uploadInputStream[i];
						
						try {
							file = FileUtil.createTempFile(fileObj);
		 					fileObj.close();
	 					} catch (IllegalArgumentException e) { 
	 						//빈 값의 temp 파일 생성 메소드 
	 						file = FileUtil.createTempFile();
	 						//temp 파일 물리적 생성
	 						FileUtil.write(file, "");
	 					}
					}
					
					String fileExt = "."+fileNames[i].substring(fileNames[i].lastIndexOf(".")+1);
					FileOutputStream output = new FileOutputStream(advancedFilePath+contentSeq+fileExt); //contentSeq로 파일명생성
					FileInputStream inputStream = new FileInputStream(file);
					byte[] buffer = new byte[1024 * 8];
					int readcount = 0;
					while((readcount=inputStream.read(buffer)) != -1) {
						output.write(buffer, 0, readcount);
					}
					inputStream.close();
					output.close();
					FileUtil.delete(file);
				}
			}
		}catch(IOException ioe){
			if(file!=null){FileUtil.delete(file);}
			throw new SystemException("Unable to write temporary file", ioe);
		}
		return upload.getFileName(fileFormName);
	}
	
	public static String unzipFile(UploadPortletRequest upload, long companyId, String contentFilePath, String contentSeq, String fileFormName) throws SystemException{
		String advancedFilePath = filePathCreate(companyId, contentFilePath);
		File folderPath = new File(advancedFilePath);
		if(!folderPath.isDirectory()) {
			folderPath.mkdirs();
		}
		File[] uploadFiles = upload.getFiles(fileFormName);
		String[] fileNames = upload.getFileNames(fileFormName);
		File file = null;
		try{
			InputStream[] uploadInputStream = upload.getFilesAsStream(fileFormName,false);
			if(fileNames != null&&Validator.isNotNull(fileNames[0])){
				for(int i=0;i<uploadFiles.length;i++){
					String folder = contentSeq;
					/*String folder = "";
					if(fileNames[i].contains(".zip")){
						 folder = fileNames[i].split(".zip")[0];
					}*/
//					System.out.println("Begin unzip "+ advancedFilePath+fileNames[i] + " into "+advancedFilePath);
					//Charset charsetISO = Charset.forName("UTF-8");
					//Charset.forName("ISO-8859-1")
					
					String fileExt = "."+fileNames[i].substring(fileNames[i].lastIndexOf(".")+1);
					
			        ZipInputStream zis = new ZipInputStream(new FileInputStream(advancedFilePath + contentSeq + fileExt),Charset.forName("euc-kr"));
			         
			        ZipEntry ze = zis.getNextEntry();
			        while(ze!=null){
			        	
			            String entryName = ze.getName();
			            File f = new File(advancedFilePath+folder+File.separator +  entryName);
			            f.getParentFile().mkdirs();
			            FileOutputStream fos = new FileOutputStream(f);
			            int len;
			            byte buffer[] = new byte[1024];
			            while ((len = zis.read(buffer)) > 0) {
			                fos.write(buffer, 0, len);
			            }
			            fos.close();   
			            
			            ze = zis.getNextEntry();
			        }
			        zis.closeEntry();
			        zis.close();
				}
			}
		}catch(IOException ioe){
			if(file!=null){FileUtil.delete(file);}
			throw new SystemException("Unable to write temporary file", ioe);
		}
		return upload.getFileName(fileFormName);
	}
	
	public static void fileDownload(PortletRequest request, MimeResponse response, long companyId,
		String contentFilePath, String contentSeq, String contentFileNm){

		InputStream is = null;
		InputStream tempFileIs = null;
		try{
			
			String fileExt = "."+contentFileNm.substring(contentFileNm.lastIndexOf(".")+1);
			
			String advancedFilePath = filePathCreate(companyId, contentFilePath + File.separator + contentSeq+fileExt);
			File dirFile = new File(advancedFilePath);

			is = new FileInputStream(dirFile);

			/*String fileName = URLEncoder.encode(dirFile.getName(), "UTF-8");
			String contentType = MimeTypesUtil.getContentType(fileName);*/

			File tempFile = FileUtil.createTempFile(is); // tempFile 생성

			tempFileIs = new FileInputStream(tempFile);
			PortletResponseUtil.sendFile(request, response, contentFileNm, tempFileIs,ContentTypes.APPLICATION_OCTET_STREAM);
			if(tempFile.exists()){
				tempFile.delete();
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			// StreamUtil.cleanUp(tempFileIs);
			StreamUtil.cleanUp(is);
		}
        

	}

	
	
	public static void deleteFile(long companyId,String contentFilePath,String deleteFileNm){
		String advancedFilePath = filePathCreate(companyId, contentFilePath);
		File file = new File(advancedFilePath+deleteFileNm);
		file.delete();
	}
	
	
	public static void deleteAllFile(long companyId,String contentFilePath) throws SystemException{
		String advancedFilePath = filePathCreate(companyId, contentFilePath);
		File file = new File(advancedFilePath);
		
		if(file!=null){
			if(file.exists()){
				if (file.isDirectory()) {
					removeDIR(file.getPath());
					file.delete();
				}else{
					throw new SystemException("AdvancedFileUtil.deleteAllFile is Not Dir"+advancedFilePath);
				}
			}
		}
	}
	
	public static void removeDIR(String source){ 
		File[] listFile = new File(source).listFiles(); 
		try{
			if(listFile.length > 0){
				for(int i = 0 ; i < listFile.length ; i++){
					if(listFile[i].isFile()){
						listFile[i].delete(); 
					}else{
						removeDIR(listFile[i].getPath());
					}
					listFile[i].delete();
				}
			}
		}catch(Exception e){
			System.err.println(System.err);
			System.exit(-1); 
		}
	}
	
	
	public static void compressFolderToZip(long companyId, long contentSeq, long contentDiv, String fileName){
		String targetFolder = filePathCreate(companyId, File.separator + String.valueOf(contentSeq));
		
		
		byte buffer[] = new byte[1024];
		Locale[] availableLanguage = LanguageUtil.getAvailableLocales();
		System.out.println(targetFolder + " ---> " + new File(targetFolder).exists());
		if(new File(targetFolder).exists()){
			if(contentDiv == 2001002){
				for(int i = 0; i < availableLanguage.length; i++){
					String manualPath = targetFolder + availableLanguage[i];

					File dirFolder = new File(manualPath);
					if(dirFolder.exists() && dirFolder.isDirectory()){

						String compressManualFileName = manualPath + File.separator +  contentSeq + ".zip";

						File newCompressFilePath = new File(manualPath); // folder
						File compressManualZipFile = new File(compressManualFileName); // zip

						if(!newCompressFilePath.exists()){
							newCompressFilePath.mkdirs();
						}
						
						if(!compressManualZipFile.exists()){
							try{
								compressManualZipFile.createNewFile();
							}catch (IOException e){
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						try{
							FileOutputStream fos = new FileOutputStream(compressManualFileName);
							ZipOutputStream zos = new ZipOutputStream(fos);

							for(File file : dirFolder.listFiles()){
								if(!file.getName().equals(contentSeq+".zip")){
									FileInputStream fis = new FileInputStream(file);
									ZipEntry zipentry = new ZipEntry(file.getName());
									zos.putNextEntry(zipentry);
									int len;
	
									while((len = fis.read(buffer)) > 0){
										zos.write(buffer, 0, len);
									}
	
									zos.closeEntry();
									fis.close();
									file.delete();
								}
							}
							zos.close();

						}catch (Exception e){
							e.printStackTrace();
						}
					}
				}
			}else{
				File dirFolder = new File(targetFolder);
				if(dirFolder.exists() && dirFolder.isDirectory()){

					String compressName = targetFolder +  contentSeq + ".zip";

					File newCompressFile = new File(compressName);

					if(!newCompressFile.exists()){
						try{
							newCompressFile.createNewFile();
						}catch (IOException e1){
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					try{
						FileOutputStream fos = new FileOutputStream(compressName);
						ZipOutputStream zos = new ZipOutputStream(fos);

						for(File file : dirFolder.listFiles()){
							if(!file.getName().equals(contentSeq+".zip")){
								FileInputStream fis = new FileInputStream(file);
								ZipEntry zipentry = new ZipEntry(file.getName());
								zos.putNextEntry(zipentry);
								int len;
	
								// file.deleteOnExit();
								while((len = fis.read(buffer)) > 0){
									zos.write(buffer, 0, len);
								}
	
								zos.closeEntry();
								fis.close();
								
								file.delete();
							}
						}
						zos.close();

					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
}

