package org.kisti.edison.virtuallaboratory.portlet.virtualLabClassScienceAppList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClass;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassScienceAppLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabClassScienceAppListController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabClassScienceAppListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
			httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
			String classId_ = GetterUtil.get(httpRequest.getParameter("classId"), "0");
			long classId = GetterUtil.getLong(ParamUtil.get(request, "classId", Long.parseLong(classId_)),0);
			
			String virtualLabId_ = GetterUtil.get(httpRequest.getParameter("virtualLabId"), "0");
			long virtualLabId = ParamUtil.get(request, "virtualLabId", Long.parseLong(virtualLabId_));
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();
			
			User user = PortalUtil.getUser(request);
			long companyId = PortalUtil.getCompanyId(request);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			
			Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			Role virtualLabManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
			Role virtualLabClassOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
			Role virtualLabClassManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
			
			if(user!=null){
				if(virtualLabId != 0 && classId == 0){
					if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)	// PORTAL ADMINISTRATOR 체크
							|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)	// SITE ADMINSTRATOR	체크
							|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)	// SITE OWNER 체크
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwner.getRoleId(), virtualLabId)
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManager.getRoleId(), virtualLabId))
					{
						model.addAttribute("role", "admin");
					} else {
						model.addAttribute("role", "member");
					}
				}else{
					Map<String, String> classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId, locale);
					virtualLabId = Long.parseLong(classInfo.get("virtualLabId"));
					
					if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)	// PORTAL ADMINISTRATOR 체크
							|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)	// SITE ADMINSTRATOR	체크
							|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)	// SITE OWNER 체크
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwner.getRoleId(), virtualLabId)
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManager.getRoleId(), virtualLabId)
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassOwner.getRoleId(), classId)
							|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassManager.getRoleId(), classId)) // VIRTUAL LAB OWNER CHECK
					{
						model.addAttribute("role", "admin");
					} else {
						model.addAttribute("role", "member");
					}
				}
			}else{
				model.addAttribute("role", "member");
			}
			
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "Workbench_WAR_OSPWorkbenchportlet");
			model.addAttribute("workBenchPlid", plid);
			
			List<Map<String, Object>> virtualLabScienceAppList = null;
			if(classId == 0){
				virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getVirtualLabScienceAppList(themeDisplay.getCompanyId(), virtualLabId , classId,locale);
				VirtualLab courseInfo = VirtualLabLocalServiceUtil.getVirtualLab(virtualLabId);
				String redirectName = courseInfo.getVirtualLabTitle(locale);
				
				model.addAttribute("redirectName", redirectName);
			}else{
				virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getVirtualLabClassScienceAppList(themeDisplay.getCompanyId(), virtualLabId ,classId, locale);
				VirtualLabClass courseInfo = VirtualLabClassLocalServiceUtil.getVirtualLabClass(classId);
				String redirectName = courseInfo.getClassTitle(locale);
				model.addAttribute("redirectName", redirectName);
			}
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(VirtualLabClass.class.getName());
			model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
			model.addAttribute("classNameId", classNameId);
			model.addAttribute("virtualLabScienceAppList", virtualLabScienceAppList.size());
			model.addAttribute("groupId", groupId);
			model.addAttribute("classId", classId);
			model.addAttribute("virtualLabId", virtualLabId);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "virtualLabClassScienceAppList/virtualLabClassScienceAppList";
	}
	
	@ResourceMapping(value="virtualLabScienceAppList")
	public void getVirtualLabScienceAppList(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		long classId = ParamUtil.get(request, "classId", GetterUtil.get(params.get("classId"), 0));
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		
		List<Map<String, Object>> virtualLabScienceAppList =  new ArrayList();
		if(classId == 0){
			virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getVirtualLabScienceAppList(themeDisplay.getCompanyId(), virtualLabId , classId,locale);
		}else{
			virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getVirtualLabClassScienceAppList(themeDisplay.getCompanyId(), virtualLabId ,classId, locale);
		}
		
		List virtualScienceAppManualList =  new ArrayList();
		Map manualMap = null;
		
		for (int i = 0; i < virtualLabScienceAppList.size(); i++) {
			List scienceAppManualList = EdisonFileUtil.getListEdisonFile(Long.parseLong(String.valueOf(virtualLabScienceAppList.get(i).get("groupId"))), "", String.valueOf(virtualLabScienceAppList.get(i).get("scienceAppId")), EdisonFileConstants.SCIENCE_APPS);
			manualMap = new HashMap();
			if(scienceAppManualList != null && scienceAppManualList.size() > 0) {
				manualMap.put("fileEntryId", ((Map)scienceAppManualList.get(0)).get("fileEntryId"));
			} 
			virtualScienceAppManualList.add(manualMap);
		}
		JSONObject obj = new JSONObject();
		obj.put("virtualLabScienceAppList", virtualLabScienceAppList);
		obj.put("virtualScienceAppManualList", virtualScienceAppManualList);
		obj.put("groupId", groupId);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@RenderMapping(params = "myaction=popupScienceAppList")
	public String popupScienceAppList(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			String classId = ParamUtil.get(request, "classId", "0");
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
			
			model.addAttribute("virtualLabId", virtualLabId);
			model.addAttribute("classId", classId);
			model.addAttribute("groupId", groupId);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "virtualLabClassScienceAppList/scienceAppPopupList";
	}
	
	@ResourceMapping(value="getScienceAppList")
	public void getScienceAppList(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long classId = GetterUtil.get(params.get("classId"), 0);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
		long companyGroupId = themeDisplay.getCompany().getGroupId();
		Locale locale = themeDisplay.getLocale();
		
		String searchField = GetterUtil.get(params.get("searchField"), "");
		List<Map<String, Object>> virtualLabScienceAppList = null;
		if(classId == 0){
			virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getScienceAppList(virtualLabId, searchField);
		}else{
			virtualLabScienceAppList = VirtualLabClassScienceAppLocalServiceUtil.getVirtualLabScienceAppList(themeDisplay.getCompanyId(), virtualLabId, classId, locale);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("virtualLabScienceAppList", virtualLabScienceAppList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 클래스 솔버 insert
	 **/
	@ResourceMapping(value ="classScienceAppInsert" )
	public void classSolveInsert(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException, SQLException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		Map params = RequestUtil.getParameterMap(request);
		long classId = GetterUtil.get(params.get("classId"), 0L);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
		
		Object scienceAppCheck = null;
		
		if(classId == 0){
			scienceAppCheck = params.get("virtuallabScienceAppCheck");
			VirtualLabClassScienceAppLocalServiceUtil.insertVirtualLabScienceAppList(themeDisplay.getCompanyId(), virtualLabId, classId, scienceAppCheck, themeDisplay.getLocale());
		}else{
			scienceAppCheck = params.get("scienceAppCheck");
			VirtualLabClassScienceAppLocalServiceUtil.insertVirtualLabClassScienceAppList(themeDisplay.getCompanyId(), virtualLabId, classId, scienceAppCheck, themeDisplay.getLocale());
		}
		
		writer.write("SUCCESS");
		writer.close();
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws IOException, SQLException, PortalException, SystemException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
}
