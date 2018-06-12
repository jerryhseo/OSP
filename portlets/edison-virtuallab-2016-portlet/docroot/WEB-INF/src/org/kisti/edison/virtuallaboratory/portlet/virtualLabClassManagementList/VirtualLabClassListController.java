package org.kisti.edison.virtuallaboratory.portlet.virtualLabClassManagementList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabClassListController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabClassListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			long virtualLabId = ParamUtil.get(request, "virtualLabId", themeDisplay.getSiteGroupId());
			Map params = RequestUtil.getParameterMap(request);
			
			model.addAttribute("virtualLabId", virtualLabId);
			model.addAttribute("groupId", groupId);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "virtualLabClassManagementList/virtualLabClassManagementList";
	}
	
	@ResourceMapping(value="virtualLabClassManagementList")
	public void virtualLabList(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = PortalUtil.getUserId(request);
		User user = PortalUtil.getUser(request);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		long classId = ParamUtil.get(request, "classId", 0);
		long companyId = PortalUtil.getCompanyId(request);
		
		Locale locale = themeDisplay.getLocale();
		String searchField = ParamUtil.get(request, "search_parameter", "");
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		
		String portletNameSpace = response.getNamespace();
		String loginYn = "N";
		
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		Role virtualClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
		Role virtualClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", String.valueOf(groupId));
		params.put("virtualLabId", String.valueOf(virtualLabId));
		params.put("searchField", searchField);
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("statusSort", "1401002");
		params.put("companyId", themeDisplay.getCompanyId());
		params.put("curUserId", themeDisplay.getUserId());
		
		JSONObject obj = new JSONObject();
		if(user!=null){
			/* 관리하고 있는 LAB 리스트가 있거나, 관리자 일경우에만 리스트를 가져옴 */
			if (!(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER))) {
				params.put("userId", user.getUserId());
				params.put("virtualLabOwnerName", EdisonRoleConstants.VIRTUAL_LAB_OWNER);
				params.put("virtualLabClassOwnerName", EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
				params.put("virtualLabClassManagerName", EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
			}
			
			boolean isTempUser = EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.TEMP_USER);
			if(isTempUser){
				String tempClassId = user.getExpandoBridge().getAttribute(EdisonExpando.USER_CLASS_ID).toString();
				params.put("tempClassId", tempClassId);
			}
			
			if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||	// 사이트 Owner Check
					(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabOwnerRole.getRoleId(), virtualLabId) ||
							UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualClassOwnerRole.getRoleId(), classId) || 
							UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualClassManagerRole.getRoleId(), classId))) {	// Custom Role Check
				obj.put("result", "ADMINISTRATOR");
			}
		}
		
		
	    List<Map<String, Object>> virtualLabClassList = VirtualLabClassLocalServiceUtil.getVirtualLabClassList(params, locale);
	    int	virtualLabClassCount = VirtualLabClassLocalServiceUtil.getCountVirtualLabClassList(params, locale);
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabClassCount, curPage, linePerPage, pagePerBlock);
		
		boolean isLogin = themeDisplay.isSignedIn();
		if(isLogin){
			loginYn = "Y";
		}
		
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabClassList", virtualLabClassList);
		obj.put("virtualLabClassCount", virtualLabClassCount);
		obj.put("groupId", groupId);
		obj.put("loginYn", loginYn);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="virtualLabClassRegisterCheck")
	public void registerCheck(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		User user = PortalUtil.getUser(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		long userId = PortalUtil.getUserId(request);
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		long classId = ParamUtil.get(request, "classId", 0);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		long companyId = PortalUtil.getCompanyId(request);
		
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		Role virtualClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
		Role virtualClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);

		JSONObject obj = new JSONObject();
		
		if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
			EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
			EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||	// 사이트 Owner Check
			(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabOwnerRole.getRoleId(), virtualLabId) ||
			UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualClassOwnerRole.getRoleId(), classId) || 
			UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualClassManagerRole.getRoleId(), classId))) {	// Custom Role Check
			obj.put("result", "ADMINISTRATOR");
		} else {
			if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.TEMP_USER)){
				obj.put("result", "TEMP_USER");
			}
			Map<String, Object> virtualLabClassUserInfo = VirtualLabLocalServiceUtil.getVirtualLabClassRegisterInfo(classId, userId, groupId, locale);
			Object[] userCountArray = VirtualLabUserLocalServiceUtil.getCountVirtualClassRegisterUserList(classId);
			virtualLabClassUserInfo.put("totalUserCount", (Integer)userCountArray[0] + (Integer)userCountArray[1]);	// 현재 클래스 수강 인원
			obj.put("virtualLabClassUserInfo", virtualLabClassUserInfo);
		}
		
		long classPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getSiteGroupId(), "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet");
		PortletURL classURL = PortletURLFactoryUtil.create(request, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet", classPlid, PortletRequest.RENDER_PHASE);
		classURL.setParameter("myRender", "virtualLabClassDetail");
		obj.put("classURL", classURL.toString());
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@RenderMapping(params ="myRender=virtualLabClassManagementDetail")
	public String virtualLabClassManagementDetail(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = PortalUtil.getUser(request);
		long companyId = PortalUtil.getCompanyId(request);
		long classId = ParamUtil.get(request, "classId", 0L);
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		String classTitle = ParamUtil.get(request, "classTitle", "");
		boolean isCustomAdmin = false; 
		boolean isDefaultUserWrite = false;
		
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		Role virtualLabClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
		Role virtualLabClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
		
		if (UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(virtualLabId)))	|| //
		UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(virtualLabId)))	|| 
		UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classId)))	||
		UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classId)))) {
			isCustomAdmin = true;
		}else{
			isDefaultUserWrite = true;
		}
		
		model.addAttribute("classId", classId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("isCustomAdmin", isCustomAdmin);
		model.addAttribute("isDefaultUserWrite", isDefaultUserWrite);
		model.addAttribute("classTitle", classTitle);
		model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		
		return "virtualLabClassManagementList/virtualLabClassManagementDetail";
	}
	
	@ActionMapping(params = "myaction=registerRequest")
	public void registerRequest(ActionRequest request, ActionResponse response) {
		try {
			long userId = PortalUtil.getUserId(request);
			long virtualUserId = Long.parseLong(ParamUtil.get(request, "registerVirtualUserId", "0"));
			long classId = Long.parseLong(ParamUtil.get(request, "registerClassRequestNo", "0"));
			if (classId != 0 || userId != 0 ) {
				if (virtualUserId > 0) {
					VirtualLabUserLocalServiceUtil.removeVirtualLabUser(classId, virtualUserId);
				}else {
					VirtualLabUserLocalServiceUtil.addVirtualLabUser(classId, userId);
				}
			}
			
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}
	
	//탭 이벤트 저장
	@ActionMapping(params="myaction=saveClickTab")
	public void saveClickTab(ActionRequest request, ActionResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			if(themeDisplay.isSignedIn()) {
				if(groupId!=0){
					Group group = GroupLocalServiceUtil.getGroup(groupId);
					themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
				}
			}
			String responseNamespace = response.getNamespace();
			responseNamespace = responseNamespace.substring(1, responseNamespace.length()-1);
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), responseNamespace);
			PortletURL url = PortletURLFactoryUtil.create(request, responseNamespace, plid, "");
			url.setParameter("groupId", CustomUtil.strNull(groupId));
			response.sendRedirect(url.toString());
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
	}
	
	@ResourceMapping(value="cancelClassResource")
	public void cancelVirtualLabResource(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		long virtualUserId = ParamUtil.get(request, "virtualUserId", 0L);
		long classId = ParamUtil.get(request, "classId", 0L);

		VirtualLabUserLocalServiceUtil.deleteVirtualLabClassVirtualLabUser(classId, virtualUserId);
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
}
