
package org.kisti.eturb.simulation.portlet.myfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.HttpFileUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class MyFileController{
	
	private static Log log = LogFactory.getLog(MyFileController.class);
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		/* Tabs - Setting */
		String tabNames = LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-toolkit-list-owned-app")+",";
		tabNames += LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-toolkit-list-management-app");
		model.addAttribute("tabNames", tabNames);
		String listTabValue = CustomUtil.strNull(params.get("tabValue"), "myfile");
		model.addAttribute("listTabValue", listTabValue);
		
		// popUpState
		model.addAttribute("portletWindowState", request.getWindowState().toString());
		
		// extension value Param
		String fileExt = request.getParameter("fileExt");
		model.addAttribute("fileExt", fileExt);
		
		// fileSearchType
		String fileSearchType = CustomUtil.strNull(params.get("fileSearchType"), "");
		model.addAttribute("fileSearchType", fileSearchType);
		
		// fileIdFilter
		String fileIdFilter = CustomUtil.strNull(params.get("fileIdFilter"), "");
		model.addAttribute("fileIdFilter", fileIdFilter);
		    
		try{
			User user = PortalUtil.getUser(request);
			long groupId = PortalUtil.getScopeGroupId(request);
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			model.addAttribute("groupId", groupId);
			
			/*IceBreaker*/
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			String icebreakerPublicUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC));
			model.addAttribute("icebreakerUrl", icebreakerUrl);
			model.addAttribute("icebreakerPublicUrl", icebreakerPublicUrl);
			
			/*vcToken*/
			if(!user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId))){
				model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
			}else{
				String userVcToken = GetterUtil.getString(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId)),"");
				
				if(userVcToken.equals("")){
					model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
				}else{
					int userVcExpired = GetterUtil.getInteger(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED+ String.valueOf(groupId)),0);
					if(userVcExpired <= Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))){
						model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
					}else{
						model.addAttribute("vcToken", userVcToken);
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "myfile";
	}
	
	/**
	 * Search Child File
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@ResourceMapping(value ="getChildFile")
	public void getChildFile(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		
		try{
		    User user = PortalUtil.getUser(request);
		    long groupId = PortalUtil.getScopeGroupId(request);
		    String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		    String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
		    String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
		    String fileExt = CustomUtil.strNull(param.get("fileExt"));
		    String fileIdFilter = CustomUtil.strNull(param.get("fileIdFilter"));
		    
		    String responseValue = "";
            List<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
			if(selectFolderId.equals("HOME")){
			    resultList = IBFileUtil.apiHomeFileList(icebreakerUrl, icebreakerToken, selectFolderId, fileExt, fileIdFilter);
			}else{
			    resultList = IBFileUtil.apiFileList(icebreakerUrl, icebreakerToken, selectFolderId, fileExt, fileIdFilter);
			}
		    
			JSONObject json = new JSONObject();
			json.put("dataList", resultList);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	/* 입력파일 관리 Method - START */
	
	/**
	 * Default Base Path Folder Search
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@ResourceMapping(value ="getRepositoryFolder")
	public void getRepositoryFolder(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		
		try{
		    
		    User user = PortalUtil.getUser(request);
            long groupId = PortalUtil.getScopeGroupId(request);
		    String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		    String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
		    
			List<HashMap<String,String>> resultList =  IBFileUtil.apiHomeFolderList(icebreakerUrl, icebreakerToken);
			JSONObject json = new JSONObject();
			json.put("dataList", resultList);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}catch(Exception e){
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	/**
	 * Search Child Folder
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@ResourceMapping(value ="getChildFolder")
	public void getChildFolder(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		
		try{
		    
		    User user = PortalUtil.getUser(request);
            long groupId = PortalUtil.getScopeGroupId(request);
		    String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		    String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
		    String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
		
			List<HashMap<String,String>> resultList =  IBFileUtil.apiFolderList(icebreakerUrl, icebreakerToken,selectFolderId);
			JSONObject json = new JSONObject();
			json.put("dataList", resultList);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value ="createFolder")
	public void createFolder(ResourceRequest request, ResourceResponse response){
	    try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));

            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            int responseStatus = IBFileUtil.createFolder(icebreakerUrl, icebreakerToken, param);
            
            JSONObject json = new JSONObject();
            json.put("status", responseStatus);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@ResourceMapping(value ="renameFolder")
	public void renameFolder(ResourceRequest request, ResourceResponse response){
	    try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            
            int responseStatus = IBFileUtil.renameFolder(icebreakerUrl, icebreakerToken, param);
            
            JSONObject json = new JSONObject();
            json.put("status", responseStatus);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@ResourceMapping(value ="deleteFolder")
	public void deleteFolder(ResourceRequest request, ResourceResponse response){
	    try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            int responseStatus = IBFileUtil.deleteFolder(icebreakerUrl, icebreakerToken, param); 
            
            JSONObject json = new JSONObject();
            json.put("status", responseStatus);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@ResourceMapping(value ="moveNode")
	public void moveNode(ResourceRequest request, ResourceResponse response){
	    try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            int responseStatus = 0;
            
            String userScreenName = "";
            if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
                userScreenName = (String)thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
            }else{
                userScreenName = String.valueOf(user.getScreenName());
            }
            
            responseStatus = IBFileUtil.moveNode(icebreakerUrl, icebreakerToken, param, userScreenName);
            
            System.out.println("responseStatus : " + responseStatus);
            JSONObject json = new JSONObject();
            json.put("status", responseStatus);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@ResourceMapping(value ="deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response){
	    int deleteResponseCode = 0;
        
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            String deleteFileArray[] = CustomUtil.paramToArray(param.get("deletefileId[]"));
            
            
            if(deleteFileArray != null && deleteFileArray.length > 0){
                for(int i=0; i<deleteFileArray.length; i++){
                    String deleteFileObj = deleteFileArray[i];
                    
                    if(!"".equals(icebreakerUrl)){
                        
                        URL url = new URL(icebreakerUrl + "/api/file/delete/"+deleteFileObj);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        
                        conn.setDoOutput(true);
                        conn.setRequestMethod("DELETE");
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
                        
                        deleteResponseCode = conn.getResponseCode();
                        if(deleteResponseCode != 200){
                            break;
                        }
                        
                        conn.disconnect();
                    }
                }
            }
            
            JSONObject json = new JSONObject();
            
            json.put("status", deleteResponseCode);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	@ResourceMapping(value ="copyFile")
    public void copyFile(ResourceRequest request, ResourceResponse response){
	    try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            int responseStatus = 0;
            
            String userScreenName = "";
            if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
                userScreenName = (String)thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
            }else{
                userScreenName = String.valueOf(user.getScreenName());
            }
            
            responseStatus = IBFileUtil.copyFile(icebreakerUrl, icebreakerToken, param, userScreenName);
            
            System.out.println("responseStatus : " + responseStatus);
            JSONObject json = new JSONObject();
            json.put("status", responseStatus);
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	/**
     * Upload File Popup Open
     * @param request
     * @param response
     * @param model
     */
    @RenderMapping(params = "myaction=fileUploadPopUp")
    public String fileUploadPopUp(RenderRequest request,RenderResponse response, ModelMap model){
        /*try {
            Map param = RequestUtil.getParameterMap(request);
            
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            String icebreakerToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            String destFolderId = CustomUtil.strNull(param.get("destFolderId"));
            String destFolerParents = CustomUtil.strNull(param.get("destFolerParents"));
            String isPopUp = CustomUtil.strNull(param.get("isPopUp"), "false");
            
            String returnId = CustomUtil.strNull(param.get("returnId"));
            String returnFileName = CustomUtil.strNull(param.get("returnFileName"));
            String cluster = CustomUtil.strNull(param.get("cluster"));
            String workflowType = CustomUtil.strNull(param.get("workflowType"));
            
            model.addAttribute("groupId", groupId);
            model.addAttribute("vcToken", icebreakerToken);
            model.addAttribute("destFolderId", destFolderId);
            model.addAttribute("destFolerParents", destFolerParents);
            model.addAttribute("isPopUp", isPopUp);
            model.addAttribute("returnId", returnId);
            model.addAttribute("returnFileName", returnFileName);
            model.addAttribute("cluster", cluster);
            model.addAttribute("workflowType", workflowType);
            
            return "fileUpload";
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return "";
    }
    
	
	/**
	 * IB File Upload
	 * @param request
	 * @param response
	 */
	@ActionMapping(params="myaction=fileIBUpload")
	public void fileIBUpload(ActionRequest request, ActionResponse response){
	    
	    UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
        Map param = RequestUtil.getParameterMap(upload);
        
        //temp 폴더에 파일 업로드
        File[] uploadFiles = upload.getFiles("addFile");
        String[] fileNames = upload.getFileNames("addFile");
        
        String destFolderId = CustomUtil.strNull(param.get("destFolderId"));
        String destFolerParents = CustomUtil.strNull(param.get("destFolderParents"));
        
        try {
            User user = PortalUtil.getUser(request);
            long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
            String vcToken = IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken();
            
            Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
            String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            if(!"".equals(icebreakerUrl)){
                URL url = new URL(icebreakerUrl+"/api/file/upload?cluster=EDISON-CFD");
                
                InputStream[] uploadInputStream = upload.getFilesAsStream("addFile",false);
                int responseStatus = 0;
                
                for(int i=0;i<uploadFiles.length;i++){
                    
                    HttpFileUtil httpFileUtil = new HttpFileUtil(url);
                    httpFileUtil.Token = vcToken;
                    
                    /*temp 파일 생성 exception 처리 추가*/
                    File tempFile  = null;
                    byte[] bytes = FileUtil.getBytes(uploadFiles[i]);
                    if(ArrayUtil.isNotEmpty(bytes)){
                        tempFile = FileUtil.createTempFile(bytes);
                    }else{
                        InputStream fileObj = uploadInputStream[i];
                        try {
                            tempFile = FileUtil.createTempFile(fileObj);
                        } catch (IllegalArgumentException e) { 
                            //File.createTempFile(sdf.format(new Date()), ""); //파일 크기 0btye인 경우 java file 메소드 이용해서 빈 temp파일 생성
                            //빈 값의 temp 파일 생성 메소드 
                            tempFile = FileUtil.createTempFile();
                            //temp 파일 물리적 생성
                            FileUtil.write(tempFile, "");
                        }
                    }
                    
                    FileOutputStream output = new FileOutputStream(ICEBREAKER_TEMP_PATH + File.separator + fileNames[i]);
                    FileInputStream inputStream = new FileInputStream(tempFile);
                    byte[] buffer = new byte[1024 * 8];
                    int readcount = 0;
                    while((readcount=inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, readcount);
                    }
                    inputStream.close();
                    output.close();

                    if(tempFile.exists()){
                        tempFile.delete();
                    }
                    
                    File uploadfile = new File(ICEBREAKER_TEMP_PATH + File.separator + fileNames[i]);
                    
                    httpFileUtil.addFile("file", uploadfile);
                    
                    String resultJson = httpFileUtil.sendMultipartPost();
                    System.out.println("resultJson : " + resultJson);
                    
                    if(!"".equals(CustomUtil.strNull(resultJson))){
                        
                        JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(resultJson));
                        String fileId = json.getString("id");
                        
                        if(!"".equals(fileId) && !destFolderId.equals("HOME")){
                            URL fileMoveUrl = new URL(icebreakerUrl+"/api/file/move/"+fileId+"/"+destFolderId);
                            System.out.println("fileMoveUrl : " + fileMoveUrl.toString());
                            
                            HttpURLConnection conn = (HttpURLConnection) fileMoveUrl.openConnection();
                            
                            conn.setDoOutput(true);
                            conn.setRequestMethod("PUT");
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setRequestProperty("Authorization", "Basic " + vcToken);
                            
                            responseStatus =  conn.getResponseCode();
                            System.out.println(responseStatus);
                            
                            conn.disconnect();
                            if(responseStatus == 200){
                                request.getPortletSession().setAttribute("mode", "fileUpload");
                                request.getPortletSession().setAttribute("uploaddestFolderId", destFolderId);
                                request.getPortletSession().setAttribute("destFolerParents", destFolerParents);
                                
                                SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
                            }
                        }else if(destFolderId.equals("HOME")){
                            SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
                        }else{
                            SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
                        }
                    }else{
                        SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
                    }
                    
                    if(uploadfile.exists()){
                        uploadfile.delete();
                    }
                }
            }
            
            boolean isPopUp = Boolean.valueOf(CustomUtil.strNull(param.get("isPopUp"),"false"));
            
            if(isPopUp){
                response.setWindowState(LiferayWindowState.POP_UP);
                response.setRenderParameter("p_p_state", LiferayWindowState.POP_UP.toString());
                response.setRenderParameter("returnId", CustomUtil.strNull(param.get("returnId")));
                response.setRenderParameter("returnFileName", CustomUtil.strNull(param.get("returnFileName")));             
                response.setRenderParameter("cluster", CustomUtil.strNull(param.get("cluster")));
                response.setRenderParameter("workflowType", CustomUtil.strNull(param.get("workflowType")));
            }
        } catch (Exception e) {
            SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
            e.printStackTrace();
        }
	}
	/* 입력파일 관리 Method - END */
	
	/* 모니터링 Method - START */
	@ResourceMapping(value ="retrieveListMonitoring")
	public void retrieveListMonitoring(ResourceRequest request, ResourceResponse response){
		
	}
	
	/*@ResourceMapping(value ="deleteFile")
	public void retrieveListJob(ResourceRequest request, ResourceResponse response){
		
	}*/
	
	@ResourceMapping(value = "errorAPICall")
	public void errorView(ResourceRequest request, ResourceResponse response){
		
	}
	
	@ResourceMapping(value = "searchJobParam")
	public void searchJobParam(ResourceRequest request,ResourceResponse response){
		
	}
	
	@ResourceMapping(value = "deleteJob")
	public void deleteJob(ResourceRequest request,ResourceResponse response){
		
	}
	/* 모니터링 Method - END */
	
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
