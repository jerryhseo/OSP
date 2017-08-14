package org.kisti.edison.virtuallaboratory.portlet.virtualLabClassNote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.ClassNote;
import org.kisti.edison.virtuallaboratory.service.ClassNoteLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class VirtualLabClassNoteController {
	private static Log log = LogFactoryUtil.getLog(VirtualLabClassNoteController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = PortalUtil.getUser(request);
			long companyId = PortalUtil.getCompanyId(request);
			long classId = ParamUtil.get(request, "classId", 0L);
			Locale locale = themeDisplay.getLocale();
			Map<String, String> classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId, locale);
			long groupId = ParamUtil.get(request, "groupId", Long.parseLong(String.valueOf(classInfo.get("groupId"))));
	
			Role virtualLabOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_OWNER);
			Role virtualLabManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_LAB_MANAGER);
			Role virtualLabClassOwner = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_OWNER);
			Role virtualLabClassManager = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.VIRTUAL_CLASS_MANAGER);
			
			//관라자 구별
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)	// PORTAL ADMINISTRATOR 체크
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)	// SITE ADMINSTRATOR	체크
					|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)	// SITE OWNER 체크
					|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabOwner.getRoleId(), Long.parseLong(classInfo.get("virtualLabId")))
					|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabManager.getRoleId(), Long.parseLong(classInfo.get("virtualLabId")))
					|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassOwner.getRoleId(), classId)
					|| UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, virtualLabClassManager.getRoleId(), classId)) // VIRTUAL LAB OWNER CHECK
			{
				model.addAttribute("role", "admin");
			} else {
				model.addAttribute("role", "member");
			}
			
			List<Map<String, Object>> getVirtualLabClassNoteList = ClassNoteLocalServiceUtil.getVirtualLabClassNoteList(classId, locale);
			model.addAttribute("getVirtualLabClassNoteList", getVirtualLabClassNoteList.size());
			model.addAttribute("groupId", groupId);
			model.addAttribute("classId", classId);
			return "virtualLabClassNote/virtualLabClassNote";
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@ResourceMapping(value="virtualLabClassNoteList")
	public void virtualLabClassNoteList(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		long classId = ParamUtil.get(request, "classId", GetterUtil.get(params.get("classId"), 0));
		
		List<Map<String, Object>> getVirtualLabClassNoteList = ClassNoteLocalServiceUtil.getVirtualLabClassNoteList(classId, locale);
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		String ClassNoteListJson = jsonSerializer.serialize(getVirtualLabClassNoteList);
		obj.put("getVirtualLabClassNoteList", JSONFactoryUtil.createJSONArray(ClassNoteListJson));
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@RenderMapping(params = "myaction=popupClassNote")
	public String popupClassNote(RenderRequest request, RenderResponse response, ModelMap model) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		// tab 생성
		try{
			long classId = ParamUtil.get(request, "classId", 0L);
			Locale locale = themeDisplay.getLocale();
			Map<String, String> classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId, locale);
			long groupId = ParamUtil.get(request, "groupId", Long.parseLong(String.valueOf(classInfo.get("groupId"))));
			
			String tabsValues =   "org.kisti.edison.content.model.Content" + "," + "edison-course-class-note-file";
			String tabNames =LanguageUtil.get(themeDisplay.getLocale(),  "org.kisti.edison.content.model.Content") + ","
				+ LanguageUtil.get(themeDisplay.getLocale(), "edison-course-class-note-file");

			model.addAttribute("classId", classId);
			model.addAttribute("groupId", groupId);

			model.addAttribute("tabNames", tabNames);
			model.addAttribute("tabsValues", tabsValues);
			model.addAttribute("MY_FILE", "edison-course-class-note-file");
			model.addAttribute("CONTENT",  "org.kisti.edison.content.model.Content");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return "virtualLabClassNote/virtualLabClassNotePopup";
	}
	
	@ResourceMapping(value = "getListClassNote")
	public void getListclassNote(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			Long classId = ParamUtil.getLong(request, "classId", 0);
			boolean isContent = ParamUtil.getBoolean(request, "isContent", true);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

			List<Map<String, Object>> assetEntryLinkList = ClassNoteLocalServiceUtil.retrieveListRelatedClassNote(
				classId, isContent, themeDisplay.getLocale());
			String linkJson = jsonSerializer.serialize(assetEntryLinkList);
			
			jsonObj.put("dataLinkList", JSONFactoryUtil.createJSONArray(linkJson));

			out.write(jsonObj.toString());

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value = "searchListClassNote")
	public void searchListClassNote(ResourceRequest request, ResourceResponse response){
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyGroupId = themeDisplay.getCompanyGroupId();
		long groupId = ParamUtil.get(request, "groupId", themeDisplay.getSiteGroupId());
		
		try{
			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int listSize = ParamUtil.get(request, "searchLine", 5);
			int blockSize = 10;
			int start = ((currentPage - 1) * listSize);

			String searchText = ParamUtil.getString(request, "searchText", "");

			List<Map<String, Object>> assetEntryList = ClassNoteLocalServiceUtil.retrievetListClassNote(
				themeDisplay.getLocale(), searchText, start, listSize, groupId, companyGroupId);
			
			int totalCnt = ClassNoteLocalServiceUtil.retrieveCountClassNote(companyGroupId, groupId, 
				themeDisplay.getLocale(),  searchText);

			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "searchListEntry",
				totalCnt, currentPage, listSize, blockSize);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String entryJson = jsonSerializer.serialize(assetEntryList);
			jsonObj.put("dataList", JSONFactoryUtil.createJSONArray(entryJson));
			jsonObj.put("paging", paging);

			out.write(jsonObj.toString());

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value = "updateClassNote")
	public void updateClassNote(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			Boolean checkYn = ParamUtil.getBoolean(request, "checkYn");
			long classId = ParamUtil.getLong(request, "classId", 0L);
			long contentSeq = ParamUtil.getLong(request, "contentSeq", 0L);
			
			Locale locale = themeDisplay.getLocale();
			Map<String, String> classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId, locale);
			long groupId = ParamUtil.get(request, "groupId", Long.parseLong(String.valueOf(classInfo.get("groupId"))));
			
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			String status = "fail";
			long userId = 0;
			if(themeDisplay.isSignedIn()){
				userId = themeDisplay.getUserId();
			}
			
			ClassNote classNote = null;
			//classNote table에 update 및 delete
			if(checkYn){
				//create
				classNote = ClassNoteLocalServiceUtil.addFileClassNote(request, contentSeq, classId,
					groupId, userId, params, themeDisplay.getLocale());
				status = "success";
			}else{
				//delete
				ClassNote retrieveClassNote = ClassNoteLocalServiceUtil.retrieveClassNote(classId, contentSeq);
				if(retrieveClassNote.getFileEntryId() != 0){
					// 기존 파일 삭제
					EdisonFileUtil.deleteSingleEdisonFile(retrieveClassNote.getFileEntryId());
				}
				ClassNoteLocalServiceUtil.deleteClassNote(retrieveClassNote.getClassNoteSeq());
				status = "success";
			}
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter(); 

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			jsonObj.put("status", status);
			out.write(jsonObj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	//내 파일 등록
	@ActionMapping(params = "myaction=fileCreate")
	public void createVirtualLab(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
			long userId = PortalUtil.getUserId(request);
			long companyId = PortalUtil.getCompanyId(request);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();

			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map params = RequestUtil.getParameterMap(upload);
			String fileName = GetterUtil.getString(upload.getFileName("my_local_file"),"");
			
			long classId = ParamUtil.getLong(request, "classId", 0L);
			Map<String, String> classInfo = VirtualLabClassLocalServiceUtil.getVirtualClassMainVisual(classId, locale);
			long groupId = ParamUtil.get(request, "groupId", Long.parseLong(String.valueOf(classInfo.get("groupId"))));
			
			ClassNoteLocalServiceUtil.addFileClassNote(upload, request, groupId ,userId, params, locale);
			response.setWindowState(LiferayWindowState.POP_UP);
			response.setRenderParameter("p_p_state", "maximized");
			response.setRenderParameter("myaction", "popupClassNote");
			response.setRenderParameter("classId", classInfo.get("classId"));
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	//강의 자료 삭제
	@ResourceMapping(value="classNoteDelete")
	public void classNoteDelete(ResourceRequest request, ResourceResponse response) throws Exception {
		long classNoteSeq = ParamUtil.get(request, "classNoteSeq", 0L);
		ClassNote retrieveClassNote = ClassNoteLocalServiceUtil.retrieveClassNote(classNoteSeq);
		if(retrieveClassNote.getFileEntryId() != 0){
			// 기존 파일 삭제
			try{
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(retrieveClassNote.getFileEntryId());
			}catch(NoSuchFileEntryException e){}
		}
		
		ClassNoteLocalServiceUtil.deleteClassNote(classNoteSeq);
		
		PrintWriter out = response.getWriter();
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		response.setContentType("application/json; charset=UTF-8");
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws IOException, SQLException, PortalException, SystemException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
}
	
	