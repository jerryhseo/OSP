package org.kisti.edison.migration.portlet.workspace;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kisti.edison.migration.util.ExcelUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.science.model.DeveloperInfo;
import org.kisti.edison.science.service.DeveloperInfoLocalServiceUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class WorkspaceController {
	
	private static Log log = LogFactoryUtil.getLog(WorkspaceController.class);
	String ExcelPath = PropsUtil.get(PropsKeys.LIFERAY_HOME) + File.separator+"migration"+File.separator+"workspace";
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
List<String> fileNameList = new ArrayList<String>();
		
		File dirFile = new File(ExcelPath);
		// 파일 경로가 없을 경우 생성
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		
		
		if(dirFile.isDirectory()){
			File[] fileList = dirFile.listFiles();
			
			for(int i=0; i<fileList.length; i++){
				File subFile = fileList[i];
				File[] subFileList = subFile.listFiles();
				fileNameList.add(fileList[i].getName()+"___"+subFileList.length);
			}
		}
		
		model.addAttribute("fileNameList",fileNameList);
		return "migration";
	}
	
	
	@ActionMapping(params="myaction=workspaceMigration")
	public void contentMigration(ActionRequest request, ActionResponse response, ModelMap model){
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		
		String fileFormName = "WorkSpaceExcelFile";
		
		File[] uploadFiles = upload.getFiles(fileFormName);
		String fileName = upload.getFileName(fileFormName);
		
		File file = null;
		
		String noSearchScreenName = "";
		int cellRow = 0;
		int insertWorkspaceCnt = 0;
		
		try{
			for(int i=0;i<uploadFiles.length;i++){
				byte[] bytes = FileUtil.getBytes(uploadFiles[i]);
				if(ArrayUtil.isNotEmpty(bytes)){
					file = FileUtil.createTempFile(bytes);
				}else{
					InputStream[] uploadInputStream = upload.getFilesAsStream(fileFormName,false);
					InputStream fileObj = uploadInputStream[i];
					file = FileUtil.createTempFile(fileObj);
				}
			}
			
			FileInputStream inputStream = new FileInputStream(file);
			System.out.println(fileName);
			
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				try{
					workbook = new XSSFWorkbook(inputStream);
				}catch (IOException e){
					e.printStackTrace();
				}
			}else if(fileName.toLowerCase().endsWith("xls")){
				try{
					workbook = new HSSFWorkbook(inputStream);
				}catch (IOException e){
					e.printStackTrace();
				}
			}else{
				if(file!=null){FileUtil.delete(file);}
				throw new SystemException("No Excel File"); 
			}

			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowWhile:while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				int rowNum = row.getRowNum();
				System.out.println(rowNum);
				
				if(rowNum>0){
					cellRow++;
					Map<String,Object> params = new HashMap<String,Object>();
					String screenName = ExcelUtil.getCellValue(row.getCell(2));
					try{
						User user = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);
						Long userId = user.getUserId();
						String groupId = ExcelUtil.getCellValue(row.getCell(32));
						
						String useStart = ExcelUtil.getCellValue(row.getCell(7));
						String useEnd = ExcelUtil.getCellValue(row.getCell(8));
						String developerSort = ExcelUtil.getCellValue(row.getCell(9));
						String developerId = ExcelUtil.getCellValue(row.getCell(30));
						String developerPassword = ExcelUtil.getCellValue(row.getCell(31));
						String languageFortran = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(10)))?"Y":"N";
						String languageCpp = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(11)))?"Y":"N";
						String languagePython = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(12)))?"Y":"N";
						String languageJava = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(13)))?"Y":"N";
						String languageOther = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(14)))?"Y":"N";
						String languageOtherDescription = ExcelUtil.getCellValue(row.getCell(15));
						String rem = ExcelUtil.getCellValue(row.getCell(16));
						String agreementYn = GetterUtil.getBoolean(ExcelUtil.getCellValue(row.getCell(17)))?"Y":"N";
						String writtenOathLogical = ExcelUtil.getCellValue(row.getCell(18));
						String writtenOathPhysical = ExcelUtil.getCellValue(row.getCell(19));
						String detailIp = ExcelUtil.getCellValue(row.getCell(20));
						String detailOs = ExcelUtil.getCellValue(row.getCell(21));
						String detailCpu = ExcelUtil.getCellValue(row.getCell(22));
						String detailStorate = ExcelUtil.getCellValue(row.getCell(23));
						String detailLibrary = ExcelUtil.getCellValue(row.getCell(24));
						
						
						params.put("userId", userId);
						params.put("groupId", Long.parseLong(groupId));
						params.put("screenName", screenName);
						params.put("firstName", user.getFirstName());
						params.put("emailAddress", user.getEmailAddress());
						params.put("universityField", user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY));
						params.put("majorField", user.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR));
						params.put("useStart", useStart);
						params.put("useEnd", useEnd);
						params.put("developerSort", developerSort);
						params.put("developerId", developerId);
						params.put("developerPassword", developerPassword);
						params.put("languageFortran", languageFortran);
						params.put("languageCpp", languageCpp);
						params.put("languagePython", languagePython);
						params.put("languageJava", languageJava);
						params.put("languageOther", languageOther);
						params.put("languageOtherDescription", languageOtherDescription);
						params.put("rem", rem);
						params.put("agreementYn", agreementYn);
						params.put("writtenOathLogical", writtenOathLogical);
						params.put("writtenOathPhysical", writtenOathPhysical);
						params.put("detailIp", detailIp);
						params.put("detailOs", detailOs);
						params.put("detailCpu", detailCpu);
						params.put("detailStorate", detailStorate);
						params.put("detailLibrary", detailLibrary);
						
						
//						params.put("insertDate", ExcelUtil.getDateCellCalue(row.getCell(26)));
//						params.put("updateDate", ExcelUtil.getDateCellCalue(row.getCell(28)));
//						params.put("folderId", ExcelUtil.getCellValue(row.getCell(35)));
//						params.put("auth", ExcelUtil.getCellValue(row.getCell(36)));
//						params.put("insertScreenName", ExcelUtil.getCellValue(row.getCell(33)));
//						params.put("updateScreenName", ExcelUtil.getCellValue(row.getCell(34)));
						
						
						for(String key:params.keySet()){
							System.out.print("key:"+key);
							System.out.println("   value:"+params.get(key));
						}
						System.out.println("-------------------------------------------------------------------");
						
						//DB 등록
						DeveloperInfo developerInfo =  DeveloperInfoLocalServiceUtil.insertCustomDeveloperInfo(params);
						
						//FILE check
						String folderId = ExcelUtil.getCellValue(row.getCell(35));
						if(!folderId.equals("0")){
							String filePath = ExcelPath+File.separator+groupId+File.separator+screenName;
							File dirPath = new File(filePath);
							//파일이 있을 경우
							if(dirPath.exists()){
								String[] fileList = dirPath.list();
								File docfile = new File(filePath+File.separator+fileList[0]);
								if(docfile.isFile()){
									insertWorkspaceDocFile(request, userId, Long.parseLong(groupId), docfile, screenName);
								}
								
								dirPath.delete();
							}
						}
						
						//권한 부여
						String requestStatus = ExcelUtil.getCellValue(row.getCell(36));
						if(requestStatus.equals("1202002")||requestStatus.equals("1202005")){
							EdisonUserUtil.addGroup(user, EdisonRoleConstants.DEVELOPER_GROUP);
							EdisonUserUtil.addSiteRole(user, Long.parseLong(groupId), EdisonRoleConstants.SOLVER_OWNER);
						}

						//DB 수정
						developerInfo.setInsertDate(ExcelUtil.getDateCellCalue(row.getCell(26)));
						developerInfo.setUpdateDate(ExcelUtil.getDateCellCalue(row.getCell(28)));
						String insertScreenName = ExcelUtil.getCellValue(row.getCell(33));
						String updateScreenName = ExcelUtil.getCellValue(row.getCell(34));
						if(!insertScreenName.equals("")){
							if(insertScreenName.equals(screenName)){
								developerInfo.setInsertId(user.getUserId());
							}else{
								User insertUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), insertScreenName);
								developerInfo.setInsertId(insertUser.getUserId());
							}
						}
						
						if(!updateScreenName.equals("")){
							if(updateScreenName.equals(screenName)){
								developerInfo.setInsertId(user.getUserId());
							}else{
								User updateUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), updateScreenName);
								developerInfo.setInsertId(updateUser.getUserId());
							}
						}
						
						DeveloperInfoLocalServiceUtil.updateDeveloperInfo(developerInfo);
					}catch(NoSuchUserException e){
						e.printStackTrace();
						noSearchScreenName = StringUtil.add(noSearchScreenName, screenName, ",");
					}
					insertWorkspaceCnt++;
				}
			}//end:rowWhile
		}catch(Exception e){
			if(file!=null){FileUtil.delete(file);}
			e.printStackTrace();
		}finally{
			if(file!=null){FileUtil.delete(file);}
			String[] duplicateArray = noSearchScreenName.split(",");
			System.out.println("total-->"+(cellRow)+"insertWorkspaceCnt-->"+insertWorkspaceCnt+"noSearchUser-->"+duplicateArray.length);
			System.out.println("noSearchScreenName-->"+noSearchScreenName);
		}
	}
	
	
	
	protected void insertWorkspaceDocFile(ActionRequest request, long userId, long groupId, File file, String screenName) throws PortalException, SystemException, IOException{
		//사이트별 파일 폴더 생성
		DLFolder edisonFolder=null;
		try {
			edisonFolder = DLFolderLocalServiceUtil.getFolder(groupId, DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT, String.valueOf(groupId)+"_EDISON_FILE");
			
		}catch (NoSuchFolderException nsfe) {
			//권한
			//Guest와 User에게 View 권한을 주기 위한 Setting
			ServiceContext sctx = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);
			sctx.setAddGroupPermissions(true);
			sctx.setAddGuestPermissions(true);
			edisonFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, false, DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT, String.valueOf(groupId)+"_EDISON_FILE", "", false,sctx);
		}
		
		//보안서약서 파일 폴더 생성
		DLFolder folder=null;
		try {
			folder = DLFolderLocalServiceUtil.getFolder(groupId, edisonFolder.getFolderId(), EdisonFileConstants.WORKSPACE);
			
		}catch (NoSuchFolderException nsfe) {
			//권한
			//Guest와 User에게 View 권한을 주기 위한 Setting
			ServiceContext sctx = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);
			sctx.setAddGroupPermissions(true);
			sctx.setAddGuestPermissions(true);
			folder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, false, edisonFolder.getFolderId(), EdisonFileConstants.WORKSPACE, "", false,sctx);
		}
		
		ServiceContext sctx = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);
		sctx.setAddGroupPermissions(true);
		sctx.setAddGuestPermissions(true);
		//파일 등록시 INDEXING을 하지 않도록 변경
		sctx.setIndexingEnabled(false);
		String editFileName = "";
		String fileNameNonExtension = "";
		String fileExtension = "";
		String fileNames = file.getName();
		
		if(fileNames.contains(screenName)){
			editFileName = fileNames;
		}else{
			int dotIndex = fileNames.indexOf(".");
			fileNameNonExtension = fileNames.substring(0, dotIndex);
			fileExtension = fileNames.substring(dotIndex, fileNames.length());
			editFileName = fileNameNonExtension+"_"+screenName+fileExtension;
		}
		
		
		
		String contentType = Files.probeContentType(file.toPath());
		
		FileEntry fe = DLAppLocalServiceUtil.addFileEntry( 
				userId,	groupId,	folder.getFolderId(),
				editFileName, contentType, editFileName, 
				editFileName, "", FileUtil.getBytes(file), 
				sctx
				);
	
	//1KB 미만의 파일을 upload시 temp 디렉토리에서 자동으로 삭제가 안되기 때문에 
	//파일이 남아 있을 경우 temp에서 임의 삭제
	if(file!=null){if(file.exists()){file.delete();}}
	}
}
