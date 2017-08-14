package org.kisti.edison.virtuallaboratory.portlet.virtualLabList;

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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.exception.EdisonException;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonEmailSenderUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
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
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabListController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			
			User user = PortalUtil.getUser(request);
			if(user==null){
				new EdisonException(0);
			}
			long userId = user.getUserId();
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			
		
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
				
				return "virtualLabList/virtualLabList";
			}else{
				new EdisonException(0);
			}
			
		} catch (Exception e) {
			
			if(e instanceof EdisonException){
				SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			}else{
				log.error(e);
				e.printStackTrace();
				
				//Session Error Message
				SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			}
		}

		return "";
	}
	
	@RenderMapping(params ="myRender=virtualLabManagementDetail")
	public String virtualLabManagementDetail(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
		model.addAttribute("virtualLabId", request.getParameter("virtualLabId"));
		return "virtualLabManagementList/virtualLabManagementDetail";
	}
	
	@RenderMapping(params ="myRender=virtualLabClassManagementDetail")
	public String virtualLabClassManagementDetail(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
		model.addAttribute("classId", request.getParameter("classId"));
		return "virtualLabClassManagementList/virtualLabClassManagementDetail";
	}
	
	@ResourceMapping(value="virtualLabList")
	public void getVirtualLabList(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		long userId = PortalUtil.getUserId(request);
		long groupId = PortalUtil.getScopeGroupId(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
		String searchField = ParamUtil.get(request, "search_parameter", "");
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		
		String portletNameSpace = response.getNamespace();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", String.valueOf(groupId));
		params.put("searchField", searchField);
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("statusSort", "1401002");
		
		params.put("companyId", themeDisplay.getCompanyId());
		params.put("curUserId", themeDisplay.getUserId());
		
		List<Map<String, Object>> virtualLabClassList = VirtualLabClassLocalServiceUtil.getListVirtualLabClass(params, locale);
		int virtualLabClassCount = VirtualLabClassLocalServiceUtil.getCountVirtualLabClass(params, locale);

		List<Map<String, Object>> virtualLabAuthList = null;
		List<Map<String, Object>> virtualLabClassRegisterList = null;
		
		if (curPage == 1) {
			virtualLabAuthList = VirtualLabLocalServiceUtil.getVirtualLabAuthList(groupId, userId, locale);
//			virtualLabClassRegisterList = VirtualLabLocalServiceUtil.getVirtualLabClassRegisterList(groupId, userId, locale);
		} else {
			virtualLabAuthList = new ArrayList<Map<String, Object>>();
			virtualLabClassRegisterList = new ArrayList<Map<String, Object>>();
		}
		
		JSONObject obj = new JSONObject();
	
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabClassCount, curPage, linePerPage, pagePerBlock);
		
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabList", virtualLabClassList);
		obj.put("virtualLabAuthList", virtualLabAuthList);
		obj.put("virtualLabClassRegisterList", virtualLabClassRegisterList);
		obj.put("virtualLabCount", virtualLabClassCount);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ActionMapping(params = "myaction=createVirtualLab")
	public void createVirtualLab(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			long userId = PortalUtil.getUserId(request);
			long groupId = PortalUtil.getScopeGroupId(request);
			long companyId = PortalUtil.getCompanyId(request);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();
			
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map params = RequestUtil.getParameterMap(upload);
			
			params.put("groupId", String.valueOf(groupId));
			params.put("userId", String.valueOf(userId));
			params.put("virtualLabUniversityField", ParamUtil.get(request, "universityField", "0"));
			
			// 일반 회원은 가상 실험실 생성 요청
			String statusSort = "1401001";	
			User user = UserLocalServiceUtil.fetchUser(userId);
			if (user != null) {
				if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) ||
						EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_OWNER) ||
						EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) ||
						EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.VIRTUAL_LAB_OWNER)) {
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
				if(statusSort.equals("1401002")) {
					Role role = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
					UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId, role.getRoleId(), virtualLab.getVirtualLabId());
				} else if (statusSort.equals("1401001")) {
					EdisonEmailSenderUtil.emailVirtualLabRequestSubmit(request, userId);	// 생성 요청 메일 보내기
				}
			}
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	@ResourceMapping(value="virtualLabClassRegisterCheck")
	public void registerCheck(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		User user = PortalUtil.getUser(request);
		long userId = PortalUtil.getUserId(request);
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		long classId = ParamUtil.get(request, "classId", 0);
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
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
			Map<String, Object> virtualLabClassUserInfo = VirtualLabLocalServiceUtil.getVirtualLabClassRegisterInfo(classId, userId, groupId, locale);
			Object[] userCountArray = VirtualLabUserLocalServiceUtil.getCountVirtualClassRegisterUserList(classId);
			virtualLabClassUserInfo.put("totalUserCount", (Integer)userCountArray[0] + (Integer)userCountArray[1]);	// 현재 클래스 수강 인원
			
			obj.put("virtualLabClassUserInfo", virtualLabClassUserInfo);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
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
	
	@ResourceMapping(value="createVirtualLabResource")
	public void createVirtualLabResource(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException, SQLException {
		long userId = PortalUtil.getUserId(request);
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		
		String select_languageId = ParamUtil.get(request, "select_languageId", "");
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("groupId", String.valueOf(groupId));
		param.put("userId", String.valueOf(userId));
		param.put("virtualLabId",ParamUtil.get(request, "virtualLabId", "0"));
		param.put("virtualLabPersonName",ParamUtil.get(request, "virtualLabPersonName", ""));
		param.put("virtualLabTitle",ParamUtil.get(request, "virtualLabTitle", ""));
		param.put("virtualLabUniversityField",ParamUtil.get(request, "universityField", ""));
		param.put("virtualLabDescription",ParamUtil.get(request, "virtualLabDescription", ""));
		
		Locale locale = Locale.US;
		if (select_languageId.equals("en_US")) {
			Locale availableLocale[] = LanguageUtil.getAvailableLocales();
			for(int i=0;i<availableLocale.length;i++){
				if(!availableLocale[i].equals(Locale.US)){
					locale = availableLocale[i];
				}
			}
		}
		
		String statusSort = "1401001";	
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.VIRTUAL_LAB_OWNER)) {
				statusSort = "1401002";
			}
		}
		param.put("statusSort", statusSort);
		
		VirtualLab virtualLab = VirtualLabLocalServiceUtil.addVirtualLab(upload, request, groupId ,userId, param, locale);
		
		if(statusSort.equals("1401002")) {
			Role role = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId, role.getRoleId(), virtualLab.getVirtualLabId());
		}
		
		param.put("virtualLabId", String.valueOf(virtualLab.getVirtualLabId()));
		
		String virtualLabProfessorName = "";
		long professorSeq = virtualLab.getProfessorSeq();
		if(professorSeq != 0){
			Professor professor =  ProfessorLocalServiceUtil.getProfessor(professorSeq);
			if(professor != null &&professor.getUserId() !=0){
				User professorUser = UserLocalServiceUtil.getUser(professor.getUserId());
				if(professorUser != null){
					virtualLabProfessorName = professorUser.getFirstName() + " "
											+ professorUser.getMiddleName() + " "
											+ professorUser.getLastName();
				}
			}
			
		}
		/*교수명 조회*/
		param.put("virtualLabPersonName", virtualLabProfessorName);
//		param.put("virtualLabPersonName", virtualLab.getVirtualLabPersonName(select_languageId));
		param.put("virtualLabTitle", virtualLab.getVirtualLabTitle(select_languageId));
		param.put("universityField", virtualLab.getVirtualLabUniversityField());
		param.put("virtualLabUniversityField", EdisonExpndoUtil.getCommonCdSearchFieldValue(virtualLab.getVirtualLabUniversityField(), EdisonExpando.CDNM , new Locale(select_languageId)));
		param.put("virtualLabDescription", virtualLab.getVirtualLabDescription(select_languageId));
		
		JSONObject obj = new JSONObject();
		
		obj.put("virtualLabInfo", param);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws IOException, SQLException, PortalException, SystemException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
}
