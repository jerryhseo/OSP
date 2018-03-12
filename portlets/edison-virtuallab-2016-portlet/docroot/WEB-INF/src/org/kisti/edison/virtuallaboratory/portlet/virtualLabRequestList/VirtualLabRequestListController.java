package org.kisti.edison.virtuallaboratory.portlet.virtualLabRequestList;

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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.HtmlFormUtils;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabRequestListController {
    private static Log _log = LogFactoryUtil.getLog(VirtualLabRequestListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		List<Map<String, String>> statusList = EdisonExpndoUtil.getCodeListByUpCode("1401", themeDisplay.getLocale());
		String selectOptionStr = HtmlFormUtils.makeCombo(statusList, GetterUtil.get(params.get("selectOption"), ""));	// 공통 코드
		model.addAttribute("selectOptionStr", selectOptionStr);
		
		return "virtualLabRequestList/virtualLabRequestList";
	}
	
	@ResourceMapping(value="virtualLabRequestList")
	public void virtualLabRequestList(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		String searchField = ParamUtil.get(request, "search_parameter", "");
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		String status = ParamUtil.get(request, "select_virtualLab_status", "0");
		
		String portletNameSpace = response.getNamespace();
		
		long groupId = themeDisplay.getScopeGroupId();
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchField", searchField);
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("statusSort", status); // 생성 요청 코드
		
		if(parentGroupId != 0){//분야사이트
			params.put("groupId", groupId);
		}
		
		List<Map<String, Object>> virtualLabRequestList = VirtualLabLocalServiceUtil.getListVirtualLab(params, locale);
		int virtualLabCount = VirtualLabLocalServiceUtil.getCountVirtualLab(params, locale);
		
		JSONObject obj = new JSONObject();
		
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabCount, curPage, linePerPage, pagePerBlock);
		
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabRequestList", virtualLabRequestList);
		obj.put("virtualLabCount", virtualLabCount);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="getSitePagePlid")
	public void getSitePagePlid(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
	    if(_log.isDebugEnabled()){
            _log.debug("getSitePagePlid parameters");
            for(String key : request.getParameterMap().keySet()){
                String[] values = (String[])request.getParameterMap().get(key);
                for(String value : values){
                    _log.debug(key + ": " + value);
                }
            }
        }
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = ParamUtil.get(request, "groupId", 0);
		User user = PortalUtil.getUser(request);
		
		long requestPlid = 0;
		boolean isAdmin = false;
		if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) ||
			EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR) ||
			EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)) {
			requestPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonvirtuallabrequestmanagement_WAR_edisonvirtuallab2016portlet");
			isAdmin = true;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("requestPlid", requestPlid);
		obj.put("isAdmin", isAdmin);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="virtualLabInfomation")
	public void getVirtualLabInfomation(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();

		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		
		Map<String, Object> virtualLabInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(virtualLabId, locale);
		
		JSONObject obj = new JSONObject();
		
		obj.put("languageId", themeDisplay.getLanguageId());
		obj.put("virtualLabInfo", virtualLabInfo);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ActionMapping(params = "myaction=updateVirtualLabStatus")
	public void updateVirtualLabStatus(ActionRequest request, ActionResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			long userId = PortalUtil.getUserId(request);
			long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
			long companyId = PortalUtil.getCompanyId(request);
			
			Locale locale = themeDisplay.getLocale();
			User user = PortalUtil.getUser(request);
			
			String processStatus = ParamUtil.get(request, "processStatus", "1401001");
			String requestUserId = ParamUtil.get(request, "requestUserId", "0");
			String virtualLabId = ParamUtil.get(request, "processVirtualLabId", "0");
			String groupName = ParamUtil.get(request, "groupName", "");
			String mailSendYn = ParamUtil.get(request, "mailSendYn", "N");
			boolean isDelete = Boolean.parseBoolean(ParamUtil.get(request, "isDelete", "false"));
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("groupId", String.valueOf(groupId));
			param.put("userId", String.valueOf(userId));
			param.put("virtualLabId",virtualLabId);
			param.put("virtualLabConfirmDescription",ParamUtil.get(request, "processDescription", ""));
			param.put("virtualLabStatus",processStatus);
			
			User requestUser = UserUtil.fetchByPrimaryKey(Long.parseLong(requestUserId));
			
			if(processStatus.equals("1401002") && requestUser != null) {	// 생성요청 승인
				if(!EdisonUserUtil.isRegularRole(requestUser, RoleConstants.ADMINISTRATOR)
				&& !EdisonUserUtil.isSiteRole(requestUser, groupId, RoleConstants.SITE_ADMINISTRATOR)
				&& !EdisonUserUtil.isSiteRole(requestUser, groupId, RoleConstants.SITE_OWNER)) {
					Role role = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);		// Role Id 확인
					UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(Long.parseLong(requestUserId), groupId, role.getRoleId(), Long.parseLong(virtualLabId));
				}
			} else if (processStatus.equals("1401001") || processStatus.equals("1401003") || processStatus.equals("1401005")) {	// 요청 또는 반려, 비활성화 일때
				
			} else if(isDelete){	// 삭제
				VirtualLabLocalServiceUtil.updateVirtualLabDisable(Long.parseLong(virtualLabId), "N");
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("groupId", groupId);
				params.put("virtualLabId", ParamUtil.get(request, "virtualLabId", "0"));
				List<Map<String, Object>> virtualLabClassList = null;
				
				Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
				
				if (!(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)
						|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwner.getRoleId(), Long.parseLong(virtualLabId)))) {
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
				Map<String, Object> labInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(Long.parseLong(virtualLabId), locale);
				long iconId =  GetterUtil.get(labInfo.get("iconId"), 0L);
				if(iconId != 0){
					// 기존 파일 삭제
					try{
						DLFileEntryLocalServiceUtil.deleteDLFileEntry(iconId);
					}catch(NoSuchFileEntryException e){}
				}
				
				return;
			}
			
			VirtualLabLocalServiceUtil.updateVirtualLabStatus(param);
			
			if(!groupName.equals("")) {
				PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
				
				portletURL.setParameter("groupName", groupName);
				portletURL.setPortletMode(LiferayPortletMode.VIEW);
				portletURL.setWindowState(LiferayWindowState.POP_UP);
				response.sendRedirect(portletURL.toString());
			}
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
