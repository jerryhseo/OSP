package org.kisti.edison.virtuallaboratory.portlet.virtualLabRegistrationList;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonEmailSenderUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
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
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
public class VirtualLabRegistrationListController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabRegistrationListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = PortalUtil.getUser(request);
			long groupId = themeDisplay.getScopeGroupId();
			long userId = user.getUserId();
			
			long labPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet");
			PortletURL labURL = PortletURLFactoryUtil.create(request, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet", labPlid, PortletRequest.RENDER_PHASE);
			labURL.setParameter("myRender", "virtualLabManagementDetail");
			model.addAttribute("labURL", labURL);
			
			if (userId != 0) {
				User userInfomation = UserLocalServiceUtil.getUser(userId);
				model.addAttribute("userInfomation", userInfomation);
			}
			
			if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
				model.addAttribute("role", "LABOWNER");
			}
			List<Map<String, Object>> virtualLabGroup = VirtualLabLocalServiceUtil.getVirtualLabGroup();
			
			String CurrentUrl = themeDisplay.getURLCurrent();
			String redirectURL= EdisonHttpUtil.removeAndencodeURL(CurrentUrl);
			String selectStatus = ParamUtil.get(request, "selectStatus", "");
			String searchField = ParamUtil.get(request, "searchField", "");
			
			model.addAttribute("searchField",	searchField);
			model.addAttribute("selectStatus",	selectStatus);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("virtualLabGroup", virtualLabGroup);
			model.addAttribute("groupId", groupId);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "virtualLabRegistrationList/virtualLabRegistrationList";
	}
	
	@ResourceMapping(value="virtualLabRegistrationList")
	public void getVirtualLabRegistrationList(ResourceRequest request, ResourceResponse response) throws Exception {
		Map requestParams = RequestUtil.getParameterMap(request);
		User user = PortalUtil.getUser(request);
		long groupId = PortalUtil.getScopeGroupId(request);
		String labOwner = "";
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
		List<Map<String, Object>> virtualLabManagementList = null;
		int virtualLabCount = 0;
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 10);
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		String portletNameSpace = response.getNamespace();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
		
		if(parentGroupId != 0){//포탈
			params.put("groupId", String.valueOf(groupId));
		}
		
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));

		/* 관리자 체크 */
		if (!(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
			|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
			|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER))) {
			params.put("userId", user.getUserId());
			params.put("virtualLabOwnerName", EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			params.put("virtualLabManagerName", EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
		}
		
		String searchField = CustomUtil.strNull(requestParams.get("searchField"));
		params.put("searchField", searchField);
		String statusSort = CustomUtil.strNull(requestParams.get("selectStatus"));
		params.put("statusSort", statusSort);
		virtualLabManagementList = VirtualLabLocalServiceUtil.getListVirtualLab(params, locale);
		virtualLabCount = VirtualLabLocalServiceUtil.getCountVirtualLab(params, locale);
		
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabCount, curPage, linePerPage, pagePerBlock);
		
		JSONObject obj = new JSONObject();
		obj.put("virtualLabRegisterList", virtualLabManagementList);
		obj.put("groupId", groupId);
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabCount", virtualLabCount);
		obj.put("labOwner", labOwner);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	//강좌 생성 요청
	@ActionMapping(params = "myaction=createVirtualLab")
	public void createVirtualLab(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
			long userId = PortalUtil.getUserId(request);
			long companyId = PortalUtil.getCompanyId(request);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			String groupId_ = httpRequest.getParameter("groupId");
			Locale locale = themeDisplay.getLocale();
			
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map params = RequestUtil.getParameterMap(upload);
			
			params.put("groupId", String.valueOf(groupId));
			params.put("userId", String.valueOf(userId));
			params.put("virtualLabUniversityField", ParamUtil.get(request, "universityField", "0"));
			
			// 일반 회원은 강좌 생성 요청
			String statusSort = "1401001";	
			User user = UserLocalServiceUtil.fetchUser(userId);
			if (user != null) {
				if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||
						EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||
						EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) 
						) {
					statusSort = "1401002";
				}
			}
			params.put("statusSort", statusSort);
			
			String select_languageId = ParamUtil.get(request, "select_languageId", "");
			if(select_languageId.equals("ko_KR")){
				locale = Locale.KOREA;
			}else if(select_languageId.equals("zh_TW")){
				locale = Locale.TAIWAN;
			}else {
				locale = Locale.US;
			}
			if(user != null) {
				VirtualLab virtualLab = VirtualLabLocalServiceUtil.addVirtualLab(upload, request, groupId ,userId, params, locale);
				Role role = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
				UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId, role.getRoleId(), virtualLab.getVirtualLabId());
			} 
			if (statusSort.equals("1401001")) {
				EdisonEmailSenderUtil.emailVirtualLabRequestSubmit(request, userId);	// 생성 요청 메일 보내기
			}
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	@ResourceMapping(value="cancelVirtualLabResource")
	public void cancelVirtualLabResource(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		long userId = PortalUtil.getUserId(request);
		String virtualLabId = ParamUtil.get(request, "virtualLabId", "0");
		
		VirtualLab virtualLab = VirtualLabLocalServiceUtil.getVirtualLab(Long.parseLong(virtualLabId));
		if(userId == virtualLab.getUserId()) {
			VirtualLabLocalServiceUtil.deleteVirtualLab(virtualLab);
		}
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@RenderMapping(params = "myrender=virtualLabRequest")
	public String virtualLabRequest(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = PortalUtil.getUser(request);
		long userId = user.getUserId();
		long groupId = PortalUtil.getScopeGroupId(request);
		boolean isPortal = themeDisplay.getScopeGroup().getParentGroupId()==0?true:false;
		
		List<Map<String, Object>> virtualLabGroup = VirtualLabLocalServiceUtil.getVirtualLabGroup();
		String redirectURL = HttpUtil.decodeURL(String.valueOf(ParamUtil.get(request, "redirectURL","")));
		
		if (userId != 0) {
			User userInfomation = UserLocalServiceUtil.getUser(userId);
			model.addAttribute("userInfomation", userInfomation);
		}
		
		model.addAttribute("isPortal", isPortal);
		model.addAttribute("virtualLabGroup", virtualLabGroup);
		model.addAttribute("groupId", groupId);
		model.addAttribute("URL", redirectURL);
		
		return "virtualLabRegistrationList/virtualLabRegistration";
	}
	
	@RenderMapping(params = "myRender=searchProfessor")
	public String professorManagement(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		return "professorManagement/professorPopup";
	}
	
	
	@ResourceMapping(value="professorSearchList")
	public void getProfessorList(ResourceRequest request, ResourceResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
		List<Map<String, Object>> professorList = null;
		int professorCount = 0;
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		String portletNameSpace = response.getNamespace();
		
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map<String, Object> params = RequestUtil.getParameterMap(upload);
		
		Role professorRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),EdisonRoleConstants.PROFESSOR);
		
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("roleId", professorRole.getRoleId());

		professorList = ProfessorLocalServiceUtil.getProfessorList(params, locale);
		professorCount = ProfessorLocalServiceUtil.getCountProfessor(params, locale);
		
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", professorCount, curPage, linePerPage, pagePerBlock);
		
		JSONObject obj = new JSONObject();
		obj.put("professorList", professorList);
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("professorCount", professorCount);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
}
