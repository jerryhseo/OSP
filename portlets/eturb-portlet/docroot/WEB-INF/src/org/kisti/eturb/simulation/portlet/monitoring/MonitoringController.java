
package org.kisti.eturb.simulation.portlet.monitoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class MonitoringController{
	
	private static Log log = LogFactory.getLog(MonitoringController.class);
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		/* Tabs - Setting */
		String tabNames = LanguageUtil.get(themeDisplay.getLocale(), "eturb-my-file")+",";
		tabNames += LanguageUtil.get(themeDisplay.getLocale(), "eturb-monitoring");
		model.addAttribute("tabNames", tabNames);
		String listTabValue = CustomUtil.strNull(params.get("tabValue"), "myfile");
		model.addAttribute("listTabValue", listTabValue);
		try{
			User user = PortalUtil.getUser(request);
			long groupId = PortalUtil.getScopeGroupId(request);
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			
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
			
			
			
			
			if(StringUtil.equalsIgnoreCase(listTabValue, "myfile")){
				
			}else{
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "list";
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
		
		String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
		String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
		
		try{
			List<HashMap<String,String>> resultList = null;
			if(StringUtil.equalsIgnoreCase(selectFolderId, "home")){
				resultList = IBFileUtil.apiHomeFileList(icebreakerUrl, icebreakerToken);
			}else{
				resultList = IBFileUtil.apiFileList(icebreakerUrl, icebreakerToken,selectFolderId);
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
		
		String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
		try{
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
		
		String icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
		String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
		String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
		
		try{
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
		
	}
	
	@ResourceMapping(value ="renameFolder")
	public void renameFolder(ResourceRequest request, ResourceResponse response){
		
	}
	
	@ResourceMapping(value ="deleteFolder")
	public void deleteFolder(ResourceRequest request, ResourceResponse response){
		
	}
	
	@ResourceMapping(value ="moveFolder")
	public void moveFolder(ResourceRequest request, ResourceResponse response){
		
	}
	
	@ResourceMapping(value ="deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response){
		
	}
	
	/**
	 * Upload File Popup Open
	 * @param request
	 * @param response
	 * @param model
	 */
	@RenderMapping(params = "myaction=fileUploadPopUp")
	public void fileUploadPopUp(RenderRequest request,RenderResponse response, ModelMap model){
		
	}
	
	/**
	 * IB File Upload
	 * @param request
	 * @param response
	 */
	@ActionMapping(params="myaction=fileIBUpload")
	public void fileIBUpload(ActionRequest request, ActionResponse response){
		
	}
	/* 입력파일 관리 Method - END */
	
	/* 모니터링 Method - START */
	@ResourceMapping(value ="retrieveListMonitoring")
	public void retrieveListMonitoring(ResourceRequest request, ResourceResponse response){
		
	}
	
	@ResourceMapping(value ="deleteFile")
	public void retrieveListJob(ResourceRequest request, ResourceResponse response){
		
	}
	
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
