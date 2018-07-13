package org.kisti.edison.virtuallaboratory.portlet.virtualLabClassMainVisual;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLabUser;
import org.kisti.edison.virtuallaboratory.service.SurveyLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class VirtualClassMainController {
	private static Log log = LogFactoryUtil.getLog(VirtualClassMainController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		String classExcpetionJSP = "virtualLabClassMainVisual/virtualClass_Exception";
		String classVisualJSP = "virtualLabClassMainVisual/virtualClassMainVisual";
		try {
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
			httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
			String classId = httpRequest.getParameter("classId");
			User user = PortalUtil.getUser(request);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			long userId = PortalUtil.getUserId(request);
			long companyId = PortalUtil.getCompanyId(request);
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			Map<String, Object> params = RequestUtil.getParameterMap(httpRequest);
			
			for (Map.Entry<String,Object> str : params.entrySet()) {
				if(str.getKey().contains("classId")) {
					classId =  (String) str.getValue();
				}
			}
			long classId_ = ParamUtil.get(request, "classId", GetterUtil.get(classId, 0L));
			Locale locale = themeDisplay.getLocale();
			
			Map<String, String> classInfo = null;
			if(classId_ > 0L) {
				classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId_, locale);
			} else {
				// classId가 잘못된 값이 들어왔을때 가상실험실 목록 화면으로 이동
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)) {
					model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/virtual-lab");
				} else {
					model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/home");
				}
				return classExcpetionJSP;
			}
			
			String role;
			List<Map<String, Object>> surveyAttend = null;
			
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER) ||
					UserGroupRoleCustomLocalServiceUtil.checkRoleVirtualLabClass(companyId, groupId, user.getUserId(), GetterUtil.get(classInfo.get("virtualLabId"), 0L), classId_) != null){
				role = "ADMIN";
				surveyAttend = SurveyLocalServiceUtil.getSurveyMappingList(GetterUtil.get(classInfo.get("virtualLabId"), 0L), true, locale);
			} else {
				role = "STUDENT";
				surveyAttend = SurveyLocalServiceUtil.getSurveyMappingVoteList(GetterUtil.get(classInfo.get("virtualLabId"), 0L), classId_, user.getUserId(), true, locale);
			}
			// 등록된 설문조사 수
			model.addAttribute("surveyCnt", surveyAttend.size());
			
			if (classInfo == null || classInfo.size() == 0) {
				// 클래스 정보가 없을때 가상실험실 목록 화면으로 이동
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)) {
					model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/virtual-lab");
				} else {
					model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/home");
				}
				return classExcpetionJSP;
			}

			model.addAttribute("lablistPlid", PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet"));
			model.addAttribute("myClassPlid", PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonmypage_WAR_edisondefault2016portlet"));
			
			Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
			Role virtualLabClassOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
			Role virtualLabClassManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
			
			if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||	// 사이트 Owner Check
					(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("virtualLabId")))) ||
							UserGroupRoleCustomLocalServiceUtil.isRoleCustom(userId, groupId, virtualLabManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("virtualLabId"))))
							)) {	// Custom Role Check
				model.addAttribute("admin", "Y");
			}
			
			// 관리자 권한 확인
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
					EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER) ||	// 사이트 Owner Check
					UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("virtualLabId"))))	|| //
					UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("virtualLabId"))))	|| 
					UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassOwnerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("classId"))))	||
					UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassManagerRole.getRoleId(), Long.parseLong(CustomUtil.strNull(classInfo.get("classId"))))) {
				model.addAttribute("role", "ADMIN");
				model.addAttribute("classInfo", classInfo);
				model.addAttribute("groupId", groupId);
				model.addAttribute("isCustomAdmin", true);

				return classVisualJSP;
			}
			
			// 클래스 멤버 확인
			VirtualLabUser virtualLabUser = VirtualLabUserLocalServiceUtil.getVirtualLabUserInfo(classId_, user.getUserId());
			if(virtualLabUser != null) {
				String requestSort = virtualLabUser.getRequestSort();
				if (requestSort.equals("CONFIRM") || requestSort.equals("TEMP")) {
					model.addAttribute("role", requestSort);
					model.addAttribute("admin", "N");
					model.addAttribute("classInfo", classInfo);
					model.addAttribute("groupId", groupId);
					return classVisualJSP;
				}
			}
			model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/home");
			
			log.info("----> isAdmin? " + model.get("admin") + " / role? " + model.get("role"));
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return classExcpetionJSP;
	}
	
	/**
	 * 학생 비밀번호 수정
	 **/
	@ResourceMapping(value ="studentPasswordUpdate" )
	public void studentPasswordUpdate(ResourceRequest request, ResourceResponse response) throws Exception{
		String resultCode = "200";
		Map params = RequestUtil.getParameterMap(request);	
		User user = PortalUtil.getUser(request);
		long companyId = PortalUtil.getCompanyId(request);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String currentPassword = GetterUtil.get(params.get("currentPassword"),"");
		String newPassword = GetterUtil.get(params.get("newPassword"),"");
		String reNewPassword = GetterUtil.get(params.get("reNewPassword"),"");
		
		int oldPwdAuth = 0;
		oldPwdAuth = UserLocalServiceUtil.authenticateByScreenName(companyId, user.getScreenName(), currentPassword, null, null, null);
		
		try{
			if(oldPwdAuth == 1) {
				UserServiceUtil.updatePassword(user.getUserId(), newPassword, reNewPassword, false);
			} else {
				resultCode = "300";
			}
		}catch (NullPointerException ne) {
			resultCode = "400";
		}catch (Exception e) {
			resultCode = "900";
		}		
		writer.write(resultCode);
	}
	
}
