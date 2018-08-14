package org.kisti.edison.virtuallaboratory.portlet.virtualLabManagementList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.exception.EdisonException;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClass;
import org.kisti.edison.virtuallaboratory.model.VirtualLabUser;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabManagementController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabManagementController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			User user = PortalUtil.getUser(request);
			long companyId = PortalUtil.getCompanyId(request);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();
			String appBasePath = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), EdisonPropsUtil.SCIENCEAPP_BASE_PATH);
			System.out.println("app base path --> " + appBasePath);
			
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
			httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
			long classId = Long.parseLong(GetterUtil.get(httpRequest.getParameter("classId"), "0"));
			
			if(classId != 0){
				long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
				long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
				boolean isCustomAdmin = false; 
				boolean isDefaultUserWrite = false; 
				
				VirtualLabClass classInfo = VirtualLabClassLocalServiceUtil.getVirtualLabClass(classId);
				
				Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
				Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
				Role virtualLabClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
				Role virtualLabClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
				
				if (UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(virtualLabId)))	||
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
				model.addAttribute("classTitle", classInfo.getClassTitle(locale));
				model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
				
				return "virtualLabClassManagementList/virtualLabClassManagementDetail";
			}
			
			//tab뷰
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			Map params = RequestUtil.getParameterMap(request);
			String visitSite ="";
			String tabGroupId = "";
			String isLogin = "Y";
			Group group = null;
			
			
			if(tabViewYn.equals("Y")){
				String searchGroupId =CustomUtil.strNull(params.get("search_groupId"));
				
				//default visitSite
				Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
				model.addAttribute("visitSite", Long.toString(defaultGroup.getGroupId()));
				
				//User Expando 값 가지고 오기
				if(themeDisplay.isSignedIn()){
					visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
				}else{
					model.addAttribute("visitSite", CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) ));
				}
				
				String groupName = "";
				int groupCnt = 0;

				List<Map<String, Object>> tablist = new ArrayList<Map<String, Object>>();
				Map<String,Long> GroupMap = new HashMap<String,Long>();
				Map<String,Object> map = null;
				
				if(!tabUseStr.equals("")){
					String []tabUseArray = tabUseStr.split(",");
					for(int i=0;i<tabUseArray.length;i++){
						map = new HashMap<String, Object>();
						
						
						Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
						group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
						GroupMap.put(group.getName(), group.getGroupId());
						
						//분야별 이름과 아이디를 맵에 등록
						map.put("groupId", group.getGroupId());
						map.put("groupName", group.getName());
						
						if(groupCnt==0){
							groupName += group.getName();
							tabGroupId += group.getGroupId();
							groupCnt++;
						}else{
							groupName += ","+group.getName();
							tabGroupId += ","+group.getGroupId();
						}
						if(!visitSite.equals("")&&visitSite.equals(group.getName())){
							model.addAttribute("visitSite", Long.toString(group.getGroupId()));
						}
						tablist.add(map);
					}
				}
				
				model.addAttribute("tablist", tablist);
				model.addAttribute("tabNames", groupName);
				model.addAttribute("tabsValues", tabGroupId);
				
				/*다른 곳에서 search_groupId 파라미터가 넘어 올경우 최종적으로 searchGroupId 값을 셋팅*/
				if(!searchGroupId.equals("")){
					model.addAttribute("visitSite", searchGroupId);
				}
				net.sf.json.JSONObject json = new net.sf.json.JSONObject();
				json.putAll(GroupMap);
				model.addAttribute("groupMap", json.toString());
			}else{
				//상세 사이트 일경우 Tab Not View
				model.addAttribute("visitSite", PortalUtil.getScopeGroupId(request));
			}
			model.addAttribute("tabViewYn", tabViewYn);
			model.addAttribute("isLogin", isLogin);
			model.addAttribute("isSignedIn", themeDisplay.isSignedIn());

			long groupId = themeDisplay.getScopeGroupId();
			String searchType = ParamUtil.get(request, "searchType", "addClass");
			model.addAttribute("searchType", searchType);
			
			long classPlid = PortalUtil.getPlidFromPortletId(groupId, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet");
			PortletURL classURL = PortletURLFactoryUtil.create(request, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet", classPlid, PortletRequest.RENDER_PHASE);
			classURL.setParameter("myRender", "virtualLabClassDetail");
			model.addAttribute("classURL", classURL);
			
			if(user==null){
				isLogin = "N";
				model.addAttribute("isLogin", isLogin);
				return "virtualLabManagementList/virtualLabManagementList";
			}
			
			long userId = user.getUserId();
			
			if (userId != 0) {
				User userInfomation = UserLocalServiceUtil.getUser(userId);
				model.addAttribute("userInfomation", userInfomation);
			}
			
			
			if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) ||
					EdisonUserUtil.isSiteRole(themeDisplay.getUser(), groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||
					EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_MEMBER)){

				model.addAttribute("languageId", themeDisplay.getLanguageId());
				if(EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.VIRTUAL_LAB_OWNER)
					|| EdisonUserUtil.isPowerUserThan(user)) {
					model.addAttribute("role", "LABOWNER");
				}
				
				return "virtualLabManagementList/virtualLabManagementList";
			}else{
				new EdisonException(0);
			}
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "virtualLabManagementList/virtualLabManagementList";
	}

	@ResourceMapping(value="virtualLabManagementList")
	public void getVirtualLabManagementList(ResourceRequest request, ResourceResponse response) throws Exception {
		User user = PortalUtil.getUser(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = ParamUtil.get(request, "groupId", 0);
		String groupId_ = ParamUtil.get(request, "groupId", "");
		String universityField = ParamUtil.get(request, "universityField", "");		
		String searchType = ParamUtil.get(request, "searchType", "");
		
		Locale locale = themeDisplay.getLocale();
		String searchField = ParamUtil.get(request, "search_parameter", "");
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		
		if(themeDisplay.getScopeGroupId() != 20181 ){
			groupId_ =  Long.toString(themeDisplay.getScopeGroupId());
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(user!=null){
			long userId = user.getUserId();
			params.put("userId", userId);
		}
		String portletNameSpace = response.getNamespace();
		params.put("virtualLabUniversityField", universityField);
		params.put("groupId", groupId_);
		params.put("searchField", searchField);
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("statusSort", "1401002");
		params.put("searchType", searchType);
		
		List<Map<String, Object>> virtualLabManagementList = null;
		int virtualLabCount = 0;
		
		JSONObject obj = new JSONObject();
		virtualLabManagementList = VirtualLabLocalServiceUtil.getVirtualLabList(params, locale);
		virtualLabCount = VirtualLabLocalServiceUtil.getCountVirtualLabList(params, locale);
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabCount, curPage, linePerPage, pagePerBlock);
		
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabManagementList", virtualLabManagementList);
		obj.put("virtualLabCount", virtualLabCount);
		obj.put("groupId", groupId_);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 기관별 탭 필터 정보 조회
	 * @param groupId
	 * @param universityField
	 * @throws Exception
	 */
	@ResourceMapping(value="virtualLabManagementTabList")
	public void getVirtualLabManagementTabList(ResourceRequest request, ResourceResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = PortalUtil.getUser(request);
		String groupId_ = ParamUtil.get(request, "groupId", "");
		String searchField = ParamUtil.get(request, "search_parameter", "");
		Locale locale = themeDisplay.getLocale();
		String searchType = ParamUtil.get(request, "searchType", "");
		
		if(themeDisplay.getScopeGroupId() != 20181 ){
			groupId_ =  Long.toString(themeDisplay.getScopeGroupId());
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(user!=null){
			long userId = user.getUserId();
			params.put("userId", userId);
		}
		
		params.put("groupId", groupId_);
		params.put("searchField", searchField);
		params.put("searchType", searchType);
		
		/* 기관 탭 생성 */
		List<Map<String, Object>> virtualLabManagementTabList = VirtualLabLocalServiceUtil.getVirtualLabTabList(params, locale);
		List<Map<String, Object>> tablist = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = null;
		
		for(int i=0; i < virtualLabManagementTabList.size(); i++){
			virtualLabManagementTabList.get(i).get("virtualLabUniversityField");
			map = new HashMap<String, Object>();
			//기관 정보를 맵에 등록
			map.put("virtualLabUniversityName", virtualLabManagementTabList.get(i).get("virtualLabUniversityName") );
			map.put("virtualLabUniversityField", virtualLabManagementTabList.get(i).get("virtualLabUniversityField"));
			map.put("groupId", virtualLabManagementTabList.get(i).get("groupId"));
			map.put("count", virtualLabManagementTabList.get(i).get("count"));
			map.put("virCount", virtualLabManagementTabList.size());
			tablist.add(map);
		}
		
		JSONObject obj = new JSONObject();
		
		obj.put("universityTablist", tablist);
		obj.put("groupId", groupId_);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
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
	
	@RenderMapping(params ="myRender=virtualLabManagementDetail")
	public String virtualLabManagementDetail(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		Locale locale = themeDisplay.getLocale();
		User user = PortalUtil.getUser(request);
		long userId = PortalUtil.getUserId(request);
		long companyId = PortalUtil.getCompanyId(request);
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		String adminCheck = "N";
		String professorYn = "Y";
		
		if(user!=null){
			//관리자 체크
			if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||	// 사이트 Owner Check
					(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabOwnerRole.getRoleId(), virtualLabId) ||
							UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabManagerRole.getRoleId(), virtualLabId)
							)) {	// Custom Role Check
				adminCheck = "Y";
			}
		}
		
		//교수 정보 조회
		Map<String, Object> labInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(virtualLabId, locale);
		if(Long.parseLong(String.valueOf(labInfo.get("virtualLabProfessorSeq"))) == 0 ){
			professorYn = "N";
		}
		
		model.addAttribute("professorYn", professorYn);
		model.addAttribute("admin", adminCheck);
		model.addAttribute("virtualLabId", virtualLabId);
		model.addAttribute("groupId", groupId);
		
		long classId = ParamUtil.get(request, "classId", 0L);
		String classTitle = ParamUtil.get(request, "classTitle", "");
		boolean isCustomAdmin = false; 
		boolean isDefaultUserWrite = false; 
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
		model.addAttribute("isCustomAdmin", isCustomAdmin);
		model.addAttribute("isDefaultUserWrite", isDefaultUserWrite);
		model.addAttribute("classTitle", classTitle);
		model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
				
		return "virtualLabManagementList/virtualLabManagementDetail";
	}
	
	
	@RenderMapping(params ="myRender=virtualLabClassDetail")
	public String virtualLabClassDetail(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
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
		boolean isVirtualLabClassMmember = false;
		boolean isSiteAdministrator = false;
		String authYn = "N";
		
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		Role virtualLabClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
		Role virtualLabClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
		
		if (UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(virtualLabId)))	|| 
				UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(virtualLabId)))	|| 
				UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classId)))	||
				UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classId)))) {
			isCustomAdmin = true;
		}else{
			isDefaultUserWrite = true;
		}
		
		/* Check isSiteAdministrator */
		if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)){
			isSiteAdministrator = true;
		}
		
		/* Check virtualLabUser(Member) */
		VirtualLabUser virtualLabUser = VirtualLabUserLocalServiceUtil.getVirtualLabUserInfo(Long.parseLong(CustomUtil.strNull(classId)), user.getUserId());
		if(virtualLabUser != null && virtualLabUser.getAuthRole().equals("STUDENT")){
			isVirtualLabClassMmember = true;
		}
		
		/* 관리자, 수강생인 경우 코멘트 작성 기능 사용 가능  */
		if(isCustomAdmin || isSiteAdministrator || isVirtualLabClassMmember){
			authYn = "Y";
		}
			
		
		model.addAttribute("classId", classId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("isCustomAdmin", isCustomAdmin);
		model.addAttribute("isDefaultUserWrite", isDefaultUserWrite);
		model.addAttribute("classTitle", classTitle);
		model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		model.addAttribute("authYn", authYn);
		
		return "virtualLabClassManagementList/virtualLabClassManagementDetail";
	}
}
