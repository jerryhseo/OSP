package org.kisti.edison.virtuallaboratory.portlet.virtualLabMainVisual;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabMainVisualController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabMainVisualController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException {
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		httpRequest = PortalUtil.getOriginalServletRequest(httpRequest);
		User user = PortalUtil.getUser(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		Group group = GroupLocalServiceUtil.getGroup(groupId);
		
		String labExcpetionJSP = "virtualLabMainVisual/virtualLab_Exception";
		String labVisualJSP = "virtualLabMainVisual/virtualLabMainVisual";
		try {
			String virtualLabId = ParamUtil.get(request, "virtualLabId", "0");
			
			Map<String, Object> params = RequestUtil.getParameterMap(httpRequest);
			for (Map.Entry<String,Object> str : params.entrySet()) {
				if(str.getKey().contains("virtualLabId")) {
					virtualLabId =  (String) str.getValue();
				}
			}
			
			Locale locale = themeDisplay.getLocale();
			
			Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			Role virtualLabManagerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
			
			Map<String, Object> labInfo = null;
			// 가상실험실 관리 목록으로 가는 plid
			model.addAttribute("lablistPlid", PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonvirtuallabmanagementlist_WAR_edisonvirtuallab2016portlet"));
			model.addAttribute("mylablistPlid", PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonmypage_WAR_edisondefault2016portlet"));
			
			if(user!=null){
				boolean manager = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManagerRole.getRoleId(), Long.parseLong(virtualLabId));
				boolean owner = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), Long.parseLong(virtualLabId));
				boolean admin = EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR);
				boolean siteAdmin = EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR);
				boolean siteOWNER = EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER);
				if(manager) {
					model.addAttribute("role", "MANAGER");
				} else if(owner || admin || siteAdmin || siteOWNER){
					model.addAttribute("role", "ADMIN");
				}
			}
			
			String groupField = "";
			String groupClass = "";
			String groupName = "";
			
			if(groupId == 23212){
				groupField = "edison-course-CFD";
				groupClass = "label_cfd";
				groupName = "cfd";
			}else if(groupId == 23318){
				groupField = "edison-course-NANO";
				groupClass = "label_nano";
				groupName = "nano";
			}else if(groupId == 23563){
				groupField = "edison-course-CHEM";
				groupClass = "label_chem";
				groupName = "chem";
			}else if(groupId == 23706){
				groupField = "edison-course-CSD";
				groupClass = "label_csd";
				groupName = "csd";
			}else if(groupId == 23849){
				groupField = "edison-course-DESIGN";
				groupClass = "label_design";
				groupName = "design";
			}else if(groupId == 284513){
				groupField = "edison-course-CMED";
				groupClass = "label_cmed";
				groupName = "cmed";
			} else if(group.getName().toUpperCase().equals("UE")){
				groupField = "edison-course-UE";
				groupClass = "label_ue";
				groupName = "ue";
			} else if(group.getName().toUpperCase().equals("CEM")){
				groupField = "edison-course-CEM";
				groupClass = "label_cem";
				groupName = "cem";
			}
			labInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(Long.parseLong(virtualLabId), locale);
			
			if (labInfo != null && CustomUtil.strNull(labInfo.get("virtualLabUseYn")).equals("Y")) {
				model.addAttribute("labInfo", labInfo);
			} else {
				return labExcpetionJSP;
			}
			
			model.addAttribute("groupId", groupId);
			model.addAttribute("groupClass", groupClass);
			model.addAttribute("groupField", groupField);
			model.addAttribute("groupName", groupName);
			
		} catch (Exception e) {
			return labExcpetionJSP;
		}
		return labVisualJSP;
	}
	
	@ActionMapping(params = "myaction=updateVirtualLabInfomation")
	public void updateVirtualLabInfomation(ActionRequest request, ActionResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map params = RequestUtil.getParameterMap(upload);
			
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			long userId = PortalUtil.getUserId(request);
			
			String select_languageId = CustomUtil.strNull(params.get("select_languageId"));
			String status = GetterUtil.get(params.get("status"), "");
			
			long companyId = PortalUtil.getCompanyId(request);
			Locale locale = themeDisplay.getLocale();
			
			if(status.equals("UPDATE")) {
				VirtualLabLocalServiceUtil.updateVirtualLabInfomation(upload, request, groupId ,userId, params, locale);
			}else if (status.equals("TRANSFER")) {
				String virtualLabOwnerName = GetterUtil.get(params.get("virtualLabOwnerName"), "");
				User user = UserLocalServiceUtil.getUserByScreenName(companyId, virtualLabOwnerName);
				Role role = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);		// Role Id 확인
				long virtualLabId = GetterUtil.get(params.get("virtualLabId"), 0);
				VirtualLabLocalServiceUtil.transferVirtualLabOwner(virtualLabId, user.getUserId(), role.getRoleId(), companyId);
			}
			
			// 주소창에 virtualLabId 를 get 방식으로 넘겨줘야 네비게이션에서 클릭했을때 다시 가상실험실 관리 페이지로 돌아갈수 있음
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setWindowState(WindowState.NORMAL);
			portletURL.setParameter("virtualLabId", CustomUtil.strNull(params.get("virtualLabId")));
			
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);

			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}
	
	@ResourceMapping(value="virtualLabDisable")
	public void virtualLabDisable(ResourceRequest request, ResourceResponse response) throws Exception {
	    if(log.isDebugEnabled()){
	        log.debug("virtualLabDisable parameters");
	        for(String key : request.getParameterMap().keySet()){
	            String[] values = (String[])request.getParameterMap().get(key);
	            for(String value : values){
	                log.debug(key + ": " + value);
	            }
	        }
	    }
		User user = PortalUtil.getUser(request);
		long groupId = ParamUtil.getLong(request, "groupId", PortalUtil.getScopeGroupId(request));
		long companyId = PortalUtil.getCompanyId(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
		String processStatus = "1401004";
		String requestUserId = user.getUserId()+"";
		String groupName = ParamUtil.get(request, "groupName", "");
		String mailSendYn = ParamUtil.get(request, "mailSendYn", "N");
		
		String result = "400";	// 삭제 실패
		
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0L);
		Role virtualLabOwnerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
		
		log.info(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR));
		log.info(EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR));
		log.info(EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER));
		log.info(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), virtualLabId));
		if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) ||	// 포털 Admin Check
				EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR) ||	// 사이트 Admin Check
				EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)	// 사이트 Owner Check
				) {
			// Portal Admin, Site Admin, Site Owner인 경우 강좌 삭제
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupId", groupId);
			params.put("virtualLabId", ParamUtil.get(request, "virtualLabId", "0"));
			
			int studentCount = VirtualLabUserLocalServiceUtil.getStudentCount(virtualLabId, 0);
			if(studentCount > 0) {
				result = "500";	// 해당 가상실험실에 수강하는 학생이 존재하여 삭제 실패
			} else {
				VirtualLabLocalServiceUtil.updateVirtualLabDisable(virtualLabId, "N");
				result = "200";	// 삭제 성공
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				params.put("groupId", groupId);
				params.put("virtualLabId", ParamUtil.get(request, "virtualLabId", "0"));
				List<Map<String, Object>> virtualLabClassList = null;
				
				Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
				
				if (!(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)
						|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwner.getRoleId(), virtualLabId))) {
					params.put("userId", user.getUserId());
					params.put("virtualLabClassOwnerName", EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
					params.put("virtualLabClassManagerName", EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
				}
				
				virtualLabClassList = VirtualLabClassLocalServiceUtil.getVirtualClassList(params, locale);
				
				for (Map<String, Object> resultMap : virtualLabClassList) {
					long classId = GetterUtil.get(resultMap.get("classId"), 0L);
					
					if(classId > 0) {
						VirtualLabClassLocalServiceUtil.updateVirtualLabClassDisable(classId, "N");
						
						// 게시판 파일 삭제
						List<Long> boardSeqList = VirtualLabClassLocalServiceUtil.getVirtualClassBoardSeqList(groupId, classId);
						if(boardSeqList != null && boardSeqList.size() > 0) {
							for (Long boardSeq : boardSeqList) {
								List fileList = EdisonFileUtil.getListEdisonFile(groupId, "", "_"+classId+"_" + boardSeq , EdisonFileConstants.BOARD_NOTICE);
								if(fileList != null){
									EdisonFileUtil.deleteGroupEdisonFile(groupId, EdisonFileConstants.BOARD_NOTICE , groupId+"__"+classId+"_" + boardSeq);
								}
							}
						}
					}
				}
				Map<String, Object> labInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(virtualLabId, locale);
				long iconId =  GetterUtil.get(labInfo.get("iconId"), 0L);
				if(iconId != 0){
					// 기존 파일 삭제
					try{
						DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
					}catch(NoSuchFileEntryException e){}
				}
				
			}
		} else if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwnerRole.getRoleId(), virtualLabId)) {
			// Portal Admin, Site Admin, Site Owner이 아니지만 강의 관리자의 경우 강좌 삭제 요청
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("groupId", String.valueOf(groupId));
			param.put("userId", String.valueOf(user.getUserId()));
			param.put("virtualLabId", String.valueOf(virtualLabId));
			param.put("virtualLabConfirmDescription",ParamUtil.get(request, "processDescription", ""));
			param.put("virtualLabStatus",processStatus);
			
			if(VirtualLabLocalServiceUtil.updateVirtualLabStatus(param) != null){
				result = "201";	// 삭제 성공
			}
			
		} else {
			result = "300";	// 삭제 실패 (권한 없음)
		}
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="virtualLabUserInfo")
	public void getVirtualLabUserInfo(ResourceRequest request, ResourceResponse response) throws Exception {
		String virtualLabId = ParamUtil.get(request, "virtualLabId", "");
		String userScreenName = ParamUtil.get(request, "userScreenName", "");
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		User user = null;
		user = UserLocalServiceUtil.fetchUserByScreenName(companyId, userScreenName);
		
		JSONObject obj = new JSONObject();
		Map<String, String> virtualLabUserInfo = null;
		if (user == null) {
			obj.put("result", "none");
		} else {
			Role ownerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);		// Owner Role Id 확인
			Role managerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);		// Manager Role Id 확인
			
			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)) {
				obj.put("result", "admin");
			}else if (EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.TEMP_USER)) {	// Temp User 체크
				obj.put("result", "tempuser");
			}else if (UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, ownerRole.getRoleId(), Long.parseLong(virtualLabId))) {	// Owner 체크
				obj.put("result", "owner");
			} else {
				virtualLabUserInfo = new HashMap<String, String>();
				
				virtualLabUserInfo.put("virtualLabAuthId", virtualLabId);
				virtualLabUserInfo.put("userId", String.valueOf(user.getUserId()));
				virtualLabUserInfo.put("userScreenName", user.getScreenName());
				virtualLabUserInfo.put("userFullName", user.getFullName());
				virtualLabUserInfo.put("userFirstName", user.getFirstName());
				virtualLabUserInfo.put("userEmailAddress", user.getEmailAddress());
				virtualLabUserInfo.put("userJobTitle", user.getJobTitle());
				obj.put("virtualLabUserInfo", virtualLabUserInfo);
				obj.put("result", "user");
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	//파일 다운로드
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
	
	//파일 삭제
	@ResourceMapping(value="deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String fileType = GetterUtil.getString(params.get("fileType"),"");
		String language = GetterUtil.getString(params.get("language"),"");
		long fileEntryId = GetterUtil.getLong(params.get("fileEntryId"),0);
		long virtualLabId = GetterUtil.getLong(params.get("virtualLabId"),0);
		
		if(fileEntryId!=0&&!fileType.equals("")){
			DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
			if(!fileType.equals("portType")){
				VirtualLab virtualLab = VirtualLabLocalServiceUtil.getVirtualLab(virtualLabId);
				
				if(fileType.equals("courseIcon")){
					virtualLab.setIconId(0);
				}
				VirtualLabLocalServiceUtil.updateVirtualLab(virtualLab);
			}
		}
	}
	
	//교수검색 팝업
	@RenderMapping(params = "myRender=searchProfessor")
	public String professorManagement(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		return "professorManagement/professorPopup";
	}
	
	//교수검색팝업 리스트
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
		String searchField = ParamUtil.get(request, "searchField", "");
		
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map<String, Object> params = RequestUtil.getParameterMap(upload);
		
		Role professorRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),EdisonRoleConstants.PROFESSOR);
		
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("roleId", professorRole.getRoleId());
		if(!"".equals(searchField)){
			params.put("searchField", searchField);
		}

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
