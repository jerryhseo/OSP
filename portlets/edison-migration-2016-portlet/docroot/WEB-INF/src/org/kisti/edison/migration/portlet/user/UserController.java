package org.kisti.edison.migration.portlet.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kisti.edison.migration.util.ExcelUtil;
import org.kisti.edison.model.EdisonExpando;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class UserController {
	
	private static Log log = LogFactoryUtil.getLog(UserController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		
		return "migration";
	}
	
	
	
	@ActionMapping(params="myaction=userMigration")
	public void userMigration(ActionRequest request, ActionResponse response, ModelMap model) throws SystemException{
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		
		String fileFormName = "UserExcelFile";
		
		File[] uploadFiles = upload.getFiles(fileFormName);
		String fileName = upload.getFileName(fileFormName);
		
		File file = null;
		
		String screenName = "";
		String duplicateScreenName = "";
		int cellRow = 0;
		int createUserCnt = 0;
		
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
			
			//Get Groups & Set Attribute GroupSties
			Group parentGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.GUEST);
			List<Group> childGroups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(),parentGroup.getGroupId(),true);
			ServiceContext userConstext = ServiceContextFactory.getInstance(User.class.getName(), request);
			
			for(Group childGroup : childGroups){
				userConstext.setAttribute("join_site_id_"+childGroup.getGroupId()+"Checkbox", "true");
			}
			
			rowWhile:while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				int rowNum = row.getRowNum();
				System.out.println(rowNum);
				
				if(rowNum>0){
					cellRow++;
					screenName 	= ExcelUtil.getCellValue(row.getCell(3));
					String emailAddress = ExcelUtil.getCellValue(row.getCell(4));
					String firstName 	= ExcelUtil.getCellValue(row.getCell(5));
					
					try{
						User cUser = UserLocalServiceUtil.addUserWithWorkflow(0, 
								themeDisplay.getCompanyId(), false, 
								"edison2016!!", "edison2016!!", false, 
								screenName, 
								emailAddress, 0, "", themeDisplay.getSiteDefaultLocale(), 
								firstName, "", "", 
								0, 0, true, Calendar.JANUARY, 
								1, 1970, "", null, 
								null, null, null, false, 
								userConstext);
						
						//update User Expando
						String termsDate = ExcelUtil.getCellValue(row.getCell(11));
						String userMajor = ExcelUtil.getCellValue(row.getCell(12));
						String userUniversity = ExcelUtil.getCellValue(row.getCell(13));
						
						cUser.getExpandoBridge().setAttribute(EdisonExpando.USER_TERMS_OF_USE_DATE, termsDate);
						cUser.getExpandoBridge().setAttribute(EdisonExpando.USER_MAJOR, userMajor);
						cUser.getExpandoBridge().setAttribute(EdisonExpando.USER_UNIVERSITY, userUniversity);
						
						//update User model
						updateUserModel(cUser, row);
					}catch(DuplicateUserScreenNameException e){
						duplicateScreenName = StringUtil.add(duplicateScreenName, screenName, ",");
					}catch(DuplicateUserEmailAddressException e){
						
					}
					createUserCnt++;
				}
			}//end:rowWhile
		}catch(Exception e){
			if(file!=null){FileUtil.delete(file);}
			if(e instanceof PortalException){
				e.printStackTrace();
			}else{
				throw new SystemException("Unable to write temporary file", e);
			}
		}finally{
			if(file!=null){FileUtil.delete(file);}
			//debug Result
			String[] duplicateArray = duplicateScreenName.split(",");
			System.out.println("total-->"+(cellRow-1)+"createUser-->"+createUserCnt+"duplicateUser-->"+duplicateArray.length);
			System.out.println("duplicateUser-->"+duplicateScreenName);
		}
	}
	
	
	private void updateUserModel(User user,Row row) throws SystemException, ParseException{
		user.setCreateDate(ExcelUtil.getDateCellCalue(row.getCell(0)));
		user.setModifiedDate(ExcelUtil.getDateCellCalue(row.getCell(1)));
		user.setPasswordModifiedDate(ExcelUtil.getDateCellCalue(row.getCell(2)));
		user.setLoginDate(ExcelUtil.getDateCellCalue(row.getCell(6)));
		user.setLoginIP(ExcelUtil.getCellValue(row.getCell(7)));
		user.setLastLoginDate(ExcelUtil.getDateCellCalue(row.getCell(8)));
		user.setLastLoginIP(ExcelUtil.getCellValue(row.getCell(9)));
		user.setLastFailedLoginDate(ExcelUtil.getDateCellCalue(row.getCell(10)));
		
		user.setEmailAddressVerified(true);
		UserLocalServiceUtil.updateUser(user);
	}
}
