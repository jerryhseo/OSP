package org.kisti.edison.content.portlet.content;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.portlet.util.AdvancedFileUtil;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.exception.EdisonException;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.HtmlFormUtils;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
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
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
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
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class ContentListController{

	private static Log log = LogFactoryUtil.getLog(ContentListController.class);
	private String contentFilePreFix = EdisonFileConstants.INFORMATION;

	@RequestMapping // default
	public String view(RenderRequest request, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		try{
			Map param = RequestUtil.getParameterMap(request);
			
			long groupId = ParamUtil.getLong(request, "groupId", PortalUtil.getScopeGroupId(request));
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();

			
			String portalYn = request.getPreferences().getValue("portalYn", "N");
			if(themeDisplay.getSiteGroupName().toUpperCase().equals("EDISON")){
				// 2018.04.06. EDISON Portal인 경우 Y
				portalYn = "Y";
			}

			model.addAttribute("groupId", groupId);

			/* Tabs - Setting */
			String tabNames = LanguageUtil.get(themeDisplay.getLocale(), "edison-content-list-owned-content") + ",";
			tabNames += LanguageUtil.get(themeDisplay.getLocale(), "edison-content-list-management-content");

			model.addAttribute("tabNames", tabNames);
			String listTabValue = CustomUtil.strNull(param.get("tabValue"), "owner_content");
			model.addAttribute("listTabValue", listTabValue);

			/* 포탈여부에 따라 admin 분류 */
			boolean isAdmin = false;
		/*	if(portalYn.equals("Y")){// 포탈
				// admin 만 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
					isAdmin = true;
				}
			}else{
				// admin, site admin 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
					isAdmin = true;
				}
			}*/

			// 프로젝트 성과관리에서 링크로 연결될때 사용
			/*
			 * Map param = RequestUtil.getParameterMap(request); if(param != null &&
			 * param.size() > 0){ model.addAttribute("projectDetailView",param);s
			 * model.addAttribute("visitSite",
			 * Long.parseLong(CustomUtil.strNull(param.get("groupId"),
			 * visitGroupId))); }
			 */

			//목록
			String searchText = CustomUtil.strNull(param.get("searchText"));

			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int searchLine = ParamUtil.get(request, "searchLine", 10);
			int blockSize = 10;
			int start = ((currentPage - 1) * searchLine);

			Role ownerRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),
				EdisonRoleConstants.CONTENT_OWNER);
			Role managerRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),
				EdisonRoleConstants.CONTENT_MANAGER);
			

			Locale locale = themeDisplay.getLocale();
			long companyGroupId = themeDisplay.getCompanyGroupId();

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			int totalCount = 0;

			boolean isCategoryJoin = false;
			if(portalYn.equals("Y")){// 포탈
				// admin 만 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
					isAdmin = true;
					isCategoryJoin = false;
					// 카테고리 없음
					dataList = ContentLocalServiceUtil.retrieveListContent(companyGroupId, groupId, searchText, null,
						start, searchLine, locale, isCategoryJoin);
					totalCount = ContentLocalServiceUtil.retrieveCountContent(companyGroupId, groupId, searchText, null,
						locale.toString(), isCategoryJoin);
				}
			}else{
				// admin, site admin 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
					isAdmin = true;
					isCategoryJoin = true;
					// 카테고리 있음, 조인
					dataList = ContentLocalServiceUtil.retrieveListContent(companyGroupId, groupId, searchText, null,
						start, searchLine, locale, isCategoryJoin);
					totalCount = ContentLocalServiceUtil.retrieveCountContent(companyGroupId, groupId, searchText, null,
						locale.toString(), isCategoryJoin);
				}
			}

			model.addAttribute("isAdmin", isAdmin);
			
			if(!isAdmin){
				long roleId = 0;
				if(listTabValue.equals("owner_content")){ // owner 인것
					roleId = ownerRole.getRoleId();
				}else if(listTabValue.equals("manager_content")){ // manager 인것
					roleId = managerRole.getRoleId();
				}

				dataList = ContentLocalServiceUtil.retrieveListUserContent(companyGroupId, parentGroupId, groupId,
					searchText, start, searchLine, locale, themeDisplay.getUserId(), roleId);
				totalCount = ContentLocalServiceUtil.retrieveCountUserContent(companyGroupId, parentGroupId, groupId,
					searchText, locale.toString(), themeDisplay.getUserId(), roleId);
			}

			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace
				+ "generalContentPageSearch", totalCount, currentPage, searchLine, blockSize);
			

			model.addAttribute("searchLine", searchLine);
			model.addAttribute("dataList", dataList);
			model.addAttribute("seq", totalCount - ((currentPage - 1) * searchLine));
			model.addAttribute("paging", paging);
			
			if(!searchText.equals("")){
				model.addAttribute("searchText", searchText);
			}
			// redirectURL encode
			model.addAttribute("redirectName",  "My Edison");
			String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
			model.addAttribute("redirectURL", redirectURL);
			
		}catch (Exception e){
			e.printStackTrace();
		}

		return "content/contentList";

	}

	/**
	 * 일반컨텐츠 조회
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping(value = "retrieveListGeneral")
	public void retrieveListGeneral(ResourceRequest request, ResourceResponse response){
		try{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId")));
			// Long contentDiv = ParamUtil.getLong(request, "contentDiv",0);
			String searchText = CustomUtil.strNull(param.get("searchText"));

			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int searchLine = ParamUtil.get(request, "searchLine", 10);
			int blockSize = 10;
			int start = ((currentPage - 1) * searchLine);
			String listTabValue = CustomUtil.strNull(param.get("listTabValue"), "owner_content");

			Role ownerRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),
				EdisonRoleConstants.CONTENT_OWNER);
			Role managerRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),
				EdisonRoleConstants.CONTENT_MANAGER);

			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();

			Locale locale = themeDisplay.getLocale();
			long companyGroupId = themeDisplay.getCompanyGroupId();

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			int totalCount = 0;

			String portalYn = request.getPreferences().getValue("portalYn", "N");

			boolean isCategoryJoin = false;
			boolean isAdmin = false;
			if(portalYn.equals("Y")){// 포탈
				// admin 만 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
					isAdmin = true;
					isCategoryJoin = false;
					// 카테고리 없음
					dataList = ContentLocalServiceUtil.retrieveListContent(companyGroupId, groupId, searchText, null,
						start, searchLine, locale, isCategoryJoin);
					totalCount = ContentLocalServiceUtil.retrieveCountContent(companyGroupId, groupId, searchText, null,
						locale.toString(), isCategoryJoin);
				}
			}else{
				// admin, site admin 모든 목록
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
					isAdmin = true;
					isCategoryJoin = true;
					// 카테고리 있음, 조인
					dataList = ContentLocalServiceUtil.retrieveListContent(companyGroupId, groupId, searchText, null,
						start, searchLine, locale, isCategoryJoin);
					totalCount = ContentLocalServiceUtil.retrieveCountContent(companyGroupId, groupId, searchText, null,
						locale.toString(), isCategoryJoin);
				}
			}

			if(!isAdmin){
				long roleId = 0;
				if(listTabValue.equals("owner_content")){ // owner 인것
					roleId = ownerRole.getRoleId();
				}else if(listTabValue.equals("manager_content")){ // manager 인것
					roleId = managerRole.getRoleId();
				}

				dataList = ContentLocalServiceUtil.retrieveListUserContent(companyGroupId, parentGroupId, groupId,
					searchText, start, searchLine, locale, themeDisplay.getUserId(), roleId);
				totalCount = ContentLocalServiceUtil.retrieveCountUserContent(companyGroupId, parentGroupId, groupId,
					searchText, locale.toString(), themeDisplay.getUserId(), roleId);
			}

			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace
				+ "generalContentPageSearch", totalCount, currentPage, searchLine, blockSize);

			JSONObject json = new JSONObject();
			json.put("dataList", dataList);
			json.put("seq", totalCount - ((currentPage - 1) * searchLine));
			json.put("paging", paging);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 콘텐츠 등록
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws SystemException
	 * @throws PortalException
	 * @throws SQLException
	 * @throws IOException
	 */
	@ActionMapping(params = "myaction=generalModify")
	public void generalModify(ActionRequest request, ActionResponse response, ModelMap model){
		String mode = String.valueOf(ParamUtil.getString(request, "mode"));
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map param = RequestUtil.getParameterMap(upload);

			long groupId = themeDisplay.getScopeGroupId();
			long companyId = themeDisplay.getCompanyId();

			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request),
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			portletURL.setPortletMode(PortletMode.VIEW);

			portletURL.setParameter("redirectName", CustomUtil.strNull(param.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(param.get("redirectURL")));

			//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(param.get("currentPage"));
			String searchLine = CustomUtil.strNull(param.get("searchLine"));
			String searchText = CustomUtil.strNull(param.get("searchText"));
			
			portletURL.setParameter("currentPage",currentPage);
			portletURL.setParameter("searchLine",searchLine);
			portletURL.setParameter("searchText",searchText);
			
			if(mode.equals(Constants.ADD)){// 등록
				Content content = ContentLocalServiceUtil.createContent(upload, request, companyId, groupId,
					themeDisplay.getUserId(), param);
				portletURL.setWindowState(WindowState.MAXIMIZED);
				portletURL.setParameter("mode", Constants.UPDATE);
				portletURL.setParameter("myaction", "contentManageView");
				portletURL.setParameter("contentSeq", String.valueOf(content.getContentSeq()));
				portletURL.setParameter("contentDiv", String.valueOf(content.getContentDiv()));
				SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);

			}else{
				if(mode.equals(Constants.UPDATE)){
					Content content = ContentLocalServiceUtil.updateContent(upload, request, companyId, groupId,
						themeDisplay.getUserId(), param);
					portletURL.setWindowState(WindowState.MAXIMIZED);
					portletURL.setParameter("mode", Constants.UPDATE);
					portletURL.setParameter("myaction", "contentManageView");
					portletURL.setParameter("contentSeq", String.valueOf(content.getContentSeq()));
					portletURL.setParameter("contentDiv", String.valueOf(content.getContentDiv()));
					SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);

				}else if(mode.equals(Constants.DELETE)){
					ContentLocalServiceUtil.deleteContent(companyId, groupId, param);
					SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
				}
			}
			response.sendRedirect(portletURL.toString());
		}catch (Exception e){
			e.printStackTrace();
			if(mode.equals(Constants.ADD)){// 등록
				SessionMessages.add(request, EdisonMessageConstants.INSERT_ERROR);
			}else if(mode.equals(Constants.UPDATE)){// 등록
				SessionMessages.add(request, EdisonMessageConstants.UPDATE_ERROR);
			}else if(mode.equals(Constants.DELETE)){
				SessionMessages.add(request, EdisonMessageConstants.DELETE_ERROR);
			}

		}
	}

	/**
	 * 콘텐츠 상세 페이지
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RenderMapping(params = "myaction=generalModifyView")
	public String defaultModifyView(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = PortalUtil.getCompanyId(request);
			Long groupId = themeDisplay.getScopeGroupId();// Long.parseLong(CustomUtil.strNull(param.get("groupId")));
			String mode = GetterUtil.getString(param.get("mode"), Constants.VIEW);

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			model.addAttribute("mode", mode);
			model.addAttribute("groupNm", group.getName());
			model.addAttribute("groupId", String.valueOf(group.getGroupId()));
			// searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(param.get("currentPage"));
			String searchLine = CustomUtil.strNull(param.get("searchLine"));
			String searchText = CustomUtil.strNull(param.get("searchText"));

			model.addAttribute("currentPage", currentPage);
			model.addAttribute("searchLine", searchLine);
			model.addAttribute("searchText", searchText);

			Long contentSeq = Long.parseLong(CustomUtil.strNull(param.get("contentSeq")));
			Long contentDiv = Long.parseLong(CustomUtil.strNull(param.get("contentDiv")));

			AssetEntry contentEntry = AssetEntryLocalServiceUtil.getEntry(Content.class.getName(), contentSeq);
			model.addAttribute("entryId", String.valueOf(contentEntry.getEntryId()));
			
			Map<String, Object> content = ContentLocalServiceUtil.retrieveMapContent(companyId, themeDisplay.getLocale(),
				contentSeq);
			model.addAttribute("content", content);
			
			model.addAttribute("contentSeq", String.valueOf(contentSeq));
			model.addAttribute("contentDiv", String.valueOf(contentDiv));
			
//			List fileList = EdisonFileUtil.getListEdisonFile(groupId, "", contentSeq + "", contentFilePreFix); // 대표이미지
//			model.addAttribute("fileList", fileList);
			
			// Content Table에 FileEntry Id 추가 -> FileEntryId로 대표이미지 출력
			if((long)content.get("CoverImageFileEntryId") != 0 && content.get("CoverImageFileEntryId") != null && !content.get("CoverImageFileEntryId").equals("")){
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry((long) content.get("CoverImageFileEntryId"));
				model.addAttribute("coverImageFile", dlFileEntry);
			}
			
			Role managerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_MANAGER);

			// manager List
			List<Map<String, String>> contentManagerList = UserGroupRoleCustomLocalServiceUtil.getUserList(groupId,
				managerRole.getRoleId(), contentSeq);
			model.addAttribute("contentManagerList", contentManagerList);

			// 접속한사람이 owner인지 검사
			Role contentOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_OWNER);
			Role contentManagerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_MANAGER);

			Boolean isOwner = true;
			Boolean isManager = true;

			if(themeDisplay.isSignedIn()){

				User user = PortalUtil.getUser(request);

				if(!EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) && !EdisonUserUtil
					.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
					isOwner = false;
					isOwner = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId,
						contentOwnerRole.getRoleId(), contentSeq);
					isManager = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId,
						contentManagerRole.getRoleId(), contentSeq);
				}

				model.addAttribute("isOwner", isOwner);
				model.addAttribute("isManager", isManager);
			}

			// 카테고리 & 프로젝트
			List<AssetCategory> linkedAssetCategoriesList = AssetCategoryLocalServiceUtil
				.getAssetEntryAssetCategories(contentEntry.getEntryId());

			String parentCategory = "";
			String childrenCategory = "";

			List<String> categoryNameList = new ArrayList<String>();

			for(AssetCategory categoryLink : linkedAssetCategoriesList){
				if(childrenCategory.equals("")){
					childrenCategory += categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
				}else{
					childrenCategory += "," + categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
				}

				if(parentCategory.equals("")){
					parentCategory += categoryLink.getParentCategoryId();
				}else{
					parentCategory += "," + categoryLink.getParentCategoryId();
				}

				categoryNameList.add(categoryLink.getTitle(themeDisplay.getLocale()));
			}
			model.addAttribute("childrenCategory", childrenCategory);
			model.addAttribute("parentCategory", parentCategory);
			model.addAttribute("categoryNameList", categoryNameList);

			int viewCnt = Integer.parseInt(CustomUtil.strNull(content.get("viewCnt"), "0")) + 1;

			// 상세보기 들어갈때마다 viewCnt 업데이트
			Content updateViewCntContent = ContentLocalServiceUtil.getContent(contentSeq);
			updateViewCntContent.setViewCnt(viewCnt);
			ContentLocalServiceUtil.updateContent(updateViewCntContent);

			long ownerId = updateViewCntContent.getInsertId();
			User ownerUser = UserLocalServiceUtil.getUser(ownerId);
			String universityField = (String) ownerUser.getExpandoBridge().getAttribute(
				EdisonExpando.USER_UNIVERSITY);

			Map<String, Object> ownerInfo = new HashMap<String, Object>();
			ownerInfo.put("firstName", ownerUser.getFirstName());
			ownerInfo.put("screenName", ownerUser.getScreenName());
			ownerInfo.put("universityField", universityField);
			ownerInfo.put("universityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(universityField,
				EdisonExpando.CDNM, themeDisplay.getLocale()));
			ownerInfo.put("portraitURL", ownerUser.getPortraitURL(themeDisplay));

			model.addAttribute("ownerInfo", ownerInfo);

			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));

			model.addAttribute("locale", themeDisplay.getLanguageId());
			

			/*asset 바로가기 상태 체크*/
			String viewStatus = ParamUtil.getString(request, "viewStatus", "");
			model.addAttribute("viewStatus", viewStatus);
			return "content/contentDetailView";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.EVENT_ERROR);
			return "";
		}
	}

	/**
	 * 콘텐츠 등록/수정 페이지
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RenderMapping(params = "myaction=contentManageView")
	public String contentManageView(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = PortalUtil.getCompanyId(request);
			Long groupId = themeDisplay.getScopeGroupId();// Long.parseLong(CustomUtil.strNull(param.get("groupId")));
			String mode = GetterUtil.getString(param.get("mode"), Constants.VIEW);
			
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			model.addAttribute("mode", mode);
			model.addAttribute("groupNm", group.getName());
			model.addAttribute("groupId", String.valueOf(group.getGroupId()));

			// 서비스 언어
			Locale[] availableLanguage = LanguageUtil.getAvailableLocales();
			model.addAttribute("ableLang", availableLanguage);

			//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(param.get("currentPage"));
			String searchLine = CustomUtil.strNull(param.get("searchLine"));
			String searchText = CustomUtil.strNull(param.get("searchText"));
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("searchLine", searchLine);
			model.addAttribute("searchText", searchText);
			
			// 공통코드
			List<Map<String, String>> codeList = EdisonExpndoUtil.getCodeListByUpCode("2001", themeDisplay
				.getLocale());
			
			if(!mode.equals(Constants.ADD)){
				Long contentSeq = Long.parseLong(CustomUtil.strNull(param.get("contentSeq")));
				Long contentDiv = Long.parseLong(CustomUtil.strNull(param.get("contentDiv")));

				AssetEntry contentEntry = AssetEntryLocalServiceUtil.getEntry(Content.class.getName(), contentSeq);
				model.addAttribute("entryId", String.valueOf(contentEntry.getEntryId()));

				Map<String, Object> content = ContentLocalServiceUtil.retrieveMapContent(companyId, themeDisplay.getLocale(),
					contentSeq);
				model.addAttribute("content", content);

				model.addAttribute("contentSeq", String.valueOf(contentSeq));
				model.addAttribute("contentDiv", String.valueOf(contentDiv));

//				List fileList = EdisonFileUtil.getListEdisonFile(groupId, "", contentSeq + "", contentFilePreFix); // 대표이미지
//				model.addAttribute("fileList", fileList);
				
				// Content Table에 FileEntry Id 추가 -> FileEntryId로 대표이미지 출력
				if((long)content.get("CoverImageFileEntryId") != 0 && content.get("CoverImageFileEntryId") != null && !content.get("CoverImageFileEntryId").equals("")){
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry((long) content.get("CoverImageFileEntryId"));
					model.addAttribute("coverImageFile", dlFileEntry);
				}

				String codeOption = HtmlFormUtils.makeCombo(codeList, String.valueOf(contentDiv));
				model.addAttribute("codeOption", String.valueOf(codeOption));

				Role managerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_MANAGER);
				Role contentOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_OWNER);

				// manager List
				List<Map<String, String>> contentManagerList = UserGroupRoleCustomLocalServiceUtil.getUserList(
					groupId, managerRole.getRoleId(), contentSeq);
				model.addAttribute("contentManagerList", contentManagerList);

				Boolean isOwner = true;
				Boolean isManager = true;

				// 접속한사람이 owner인지 검사
				if(themeDisplay.isSignedIn()){

					User user = PortalUtil.getUser(request);
					if(mode.equals(Constants.UPDATE)){
						model.addAttribute("userScreenName", user.getScreenName());
					}

					if(!EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) && !EdisonUserUtil
						.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
						isOwner = false;
						isOwner = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId,
							contentOwnerRole.getRoleId(), contentSeq);
						isManager = UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId,
							managerRole.getRoleId(), contentSeq);
					}

					model.addAttribute("isOwner", isOwner);
					model.addAttribute("isManager", isManager);
				}

				// 카테고리 & 프로젝트
				List<AssetCategory> linkedAssetCategoriesList = AssetCategoryLocalServiceUtil
					.getAssetEntryAssetCategories(contentEntry.getEntryId());

				String parentCategory = "";
				String childrenCategory = "";

				List<String> categoryNameList = new ArrayList<String>();

				for(AssetCategory categoryLink : linkedAssetCategoriesList){
					if(childrenCategory.equals("")){
						childrenCategory += categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
					}else{
						childrenCategory += "," + categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
					}

					if(parentCategory.equals("")){
						parentCategory += categoryLink.getParentCategoryId();
					}else{
						parentCategory += "," + categoryLink.getParentCategoryId();
					}

					categoryNameList.add(categoryLink.getTitle(themeDisplay.getLocale()));
				}
				model.addAttribute("childrenCategory", childrenCategory);
				model.addAttribute("parentCategory", parentCategory);
				model.addAttribute("categoryNameList", categoryNameList);
				
				model.addAttribute("contentUrl", content.get("contentUrl"));

			}else{
				long contentSeq = Long.parseLong(CustomUtil.strNull(param.get("contentSeq"), "0"));
				String codeOption = HtmlFormUtils.makeCombo(codeList, null);
				model.addAttribute("codeOption", String.valueOf(codeOption));
			}

			// Category 정보 조회
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
				EdisonAssetCategory.GLOBAL_DOMAIN);

			int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary
				.getVocabularyId());
			Map<Long, List<Map<String, Object>>> childrenCategoryGroupMap = new HashMap<Long, List<Map<String, Object>>>();
			List<Map<String, Object>> parentCategoryList = new ArrayList<Map<String, Object>>();

			if(rootCategoryCnt != 0){
				List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(
					aVocabulary.getVocabularyId(), -1, -1, null);
				for(AssetCategory rootCatogory : rootCategoryList){
					Map<String, Object> parentCategoryMap = new HashMap<String, Object>();
					parentCategoryMap.put("value", rootCatogory.getCategoryId());
					parentCategoryMap.put("name", rootCatogory.getTitle(themeDisplay.getLocale()));
					parentCategoryList.add(parentCategoryMap);

					List<AssetCategory> childCategoryList = AssetCategoryLocalServiceUtil.getChildCategories(
						rootCatogory.getCategoryId());
					List<Map<String, Object>> childrenCategoryList = new ArrayList<Map<String, Object>>();
					for(AssetCategory childCatogory : childCategoryList){
						Map<String, Object> childrenCategoryMap = new HashMap<String, Object>();
						childrenCategoryMap.put("value", childCatogory.getCategoryId());
						childrenCategoryMap.put("name", childCatogory.getTitle(themeDisplay.getLocale()));
						childrenCategoryList.add(childrenCategoryMap);
					}
					childrenCategoryGroupMap.put(rootCatogory.getCategoryId(), childrenCategoryList);
				}
			}

			model.addAttribute("childrenCategoryGroupMap", childrenCategoryGroupMap);
			model.addAttribute("parentCategoryList", parentCategoryList);

			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));

			model.addAttribute("locale", themeDisplay.getLanguageId());

			return "content/generalModify";
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.EVENT_ERROR);
			return "";
		}
	}
	
	/**
	 * 일반 콘텐츠 파일 다운로드 POPUP OPEN
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RenderMapping(params = "myaction=generalFileDownload")
	public String defaultModifyFileListView(RenderRequest request, ModelMap model){
		try{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			Long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), "0"));
			String contentSeq = CustomUtil.strNull(param.get("contentSeq"), "0");
			String contentDiv = CustomUtil.strNull(param.get("contentDiv"), "0");

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			model.addAttribute("groupNm", group.getName());
			model.addAttribute("groupId", String.valueOf(group.getGroupId()));

			List resultFileList = EdisonFileUtil.getListEdisonFile(groupId, "", contentSeq, contentFilePreFix);

			model.addAttribute("resultFileList", resultFileList);
			return "content/generalFileList";
		}catch (Exception e){
			return "";
		}
	}

	@ResourceMapping(value = "contentfiledownload")
	public void contentfiledownload(ResourceRequest request, ResourceResponse response){

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();
		JSONObject obj = new JSONObject();

		try{
			long contentSeq = ParamUtil.getLong(request, "contentSeq", 0);
			if(contentSeq != 0){
				Content content = ContentLocalServiceUtil.getContent(contentSeq);

				String filePath = File.separator + content.getContentSeq();
				String languageId = ParamUtil.getString(request, "languageId", themeDisplay.getLanguageId());

				if(content.getContentDiv() == 2001002){
					filePath += File.separator + languageId;
				}
				
				AdvancedFileUtil.fileDownload(request, response, companyId, filePath, String.valueOf(content.getContentSeq()), content.getContentFileNm(languageId));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@ResourceMapping(value = "retrieveGeneralFileList")
	public void retrieveGeneralFileList(ResourceRequest request, ResourceResponse response){

		try{

			Map param = RequestUtil.getParameterMap(request);

			Long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), "0"));
			String contentSeq = CustomUtil.strNull(param.get("contentSeq"), "0");
			String contentDiv = CustomUtil.strNull(param.get("contentDiv"), "0");

			List resultFileList = EdisonFileUtil.getListEdisonFile(groupId, "", contentSeq, contentFilePreFix);

			JSONObject obj = new JSONObject();

			obj.put("fileList", resultFileList);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * 일반 컨텐츠 등록 파일 삭제
	 * 
	 * @param request
	 * @param response
	 */
	@ResourceMapping(value = "deleteSingleEdisonFile")
	public void deleteSingleEdisonFile(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			long fileEntryId = Long.parseLong(CustomUtil.strNull(params.get("fileEntryId")));
//			long contentDiv = Long.parseLong(CustomUtil.strNull(params.get("contentDiv")));
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId")));
			String contentSeq = CustomUtil.strNull(params.get("contentSeq"));

			String resultMsg = "";
			List fileList = new ArrayList();

			if(EdisonFileUtil.deleteSingleEdisonFile(fileEntryId)){
				resultMsg = "SUCCESS";
			}else{
				resultMsg = "DELETE_FAIL";
			}

			fileList = EdisonFileUtil.getListEdisonFile(groupId, "", String.valueOf(contentSeq), contentFilePreFix);

			JSONObject obj = new JSONObject();

			obj.put("fileList", fileList);
			obj.put("resultMsg", resultMsg);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * 일반 콘텐츠 다운로드 수 UPDATE
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	/*
	 * @ResourceMapping(value = "edisonFileCountDownload") public void
	 * edisonFileCountDownload(ResourceRequest request, ResourceResponse
	 * response){ try{ Map param = RequestUtil.getParameterMap(request);
	 * 
	 * Long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId")));
	 * Long contentDiv =
	 * Long.parseLong(CustomUtil.strNull(param.get("contentDiv"))); Long
	 * contentSeq = Long.parseLong(CustomUtil.strNull(param.get("contentSeq")));
	 * 
	 * GeneralContentPK generalContentPK = new GeneralContentPK(contentSeq,
	 * groupId); generalContentPK.setContentSeq(contentSeq); GeneralContent
	 * generalContent =
	 * GeneralContentLocalServiceUtil.getGeneralContent(generalContentPK);
	 * 
	 * Long downloadCnt = generalContent.getDownloadCnt() + 1;
	 * generalContent.setDownloadCnt(downloadCnt);
	 * 
	 * GeneralContentLocalServiceUtil.updateGeneralContent(generalContent);
	 * 
	 * JSONObject obj = new JSONObject(); obj.put("downloadCnt", downloadCnt);
	 * 
	 * response.setContentType("application/json; charset=UTF-8"); PrintWriter out
	 * = response.getWriter(); out.write(obj.toString()); }catch (Exception e){
	 * log.error(e); e.printStackTrace(); } }
	 */

	/**
	 * 일반 컨텐츠 다운로드
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping(value = "edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response){
		try{
			Map paramsMap = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
			EdisonFileUtil.edisonFileDownload(response, fileEntryId);
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * 사용자 정보 검색
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping(value = "contentUserInfo")
	public void getContentUserInfo(ResourceRequest request, ResourceResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);

			String type = CustomUtil.strNull(params.get("type"));

			String newName = "";
			String pastName = "";
			if(type.equals("owner")){
				newName = CustomUtil.strNull(params.get("newOwnerName"), "0");
				pastName = CustomUtil.strNull(params.get("nowOwnerName"), "0");
			}else if(type.equals("manager")){
				newName = CustomUtil.strNull(params.get("newMgrName"), "0");
				pastName = CustomUtil.strNull(params.get("nowMgrName"), "0");
			}

			Long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId")));
			long companyId = PortalUtil.getCompanyId(request);
			String contentSeq = CustomUtil.strNull(params.get("contentSeq"), "0");

			User preUser = null;
			if(!pastName.equals("0")){
				preUser = UserLocalServiceUtil.getUserByScreenName(companyId, pastName);
			}
			User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, newName);

			JSONObject obj = new JSONObject();
			Map<String, String> contentUserInfo = null;

			if(user == null){
				obj.put("result", "none");
			}else{

				PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

				if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(
					user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId,
						EdisonRoleConstants.SITE_MEMBER)){
					Role ownerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.CONTENT_OWNER); // Owner
																																																				 // Role
																																																				 // Id
																																																				 // 확인
					Role managerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.CONTENT_MANAGER); // Manager
																																																						 // Role
																																																						 // Id
																																																						 // 확인

					PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
					PermissionThreadLocal.setPermissionChecker(checker);

					if(type.equals("manager")){
						if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) || EdisonUserUtil
							.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR) || EdisonUserUtil.isSiteRole(
								user, groupId, EdisonRoleConstants.SITE_OWNER)){
							obj.put("result", "admin");
						}else if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, ownerRole
							.getRoleId(), Long.parseLong(contentSeq))){ // Owner 체크
							obj.put("result", "owner");
						}else if(UserGroupRoleCustomLocalServiceUtil.isRoleCustom(user.getUserId(), groupId, managerRole
							.getRoleId(), Long.parseLong(contentSeq))){ // Manager 체크
							obj.put("result", "manager");
						}else{
							contentUserInfo = new HashMap<String, String>();

							String pastMgrId = "";
							if(preUser != null && !pastName.equals("0")){
								pastMgrId = String.valueOf(preUser.getUserId());
							}
							contentUserInfo.put("pastMgrId", pastMgrId);
							contentUserInfo.put("userId", String.valueOf(user.getUserId()));
							contentUserInfo.put("userScreenName", user.getScreenName());
							contentUserInfo.put("userFullName", user.getFullName());
							contentUserInfo.put("userFirstName", user.getFirstName());
							contentUserInfo.put("userEmailAddress", user.getEmailAddress());
							contentUserInfo.put("userJobTitle", user.getJobTitle());

							obj.put("contentUserInfo", contentUserInfo);
							obj.put("result", "user");
						}
					}else if(type.equals("owner")){
						contentUserInfo = new HashMap<String, String>();

						contentUserInfo.put("pastOnwerId", String.valueOf(preUser.getUserId()));
						contentUserInfo.put("userId", String.valueOf(user.getUserId()));
						contentUserInfo.put("userScreenName", user.getScreenName());
						contentUserInfo.put("userFullName", user.getFullName());
						contentUserInfo.put("userFirstName", user.getFirstName());
						contentUserInfo.put("userEmailAddress", user.getEmailAddress());
						contentUserInfo.put("userJobTitle", user.getJobTitle());

						obj.put("contentUserInfo", contentUserInfo);
						obj.put("result", "user");

						if(contentUserInfo != null){
							boolean projectYn = EdisonUserUtil.isProjectThan(user);
							obj.put("projectUser", projectYn);
						}
					}

				}else{
					obj.put("result", "not siteMember");
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * Content Owner Update
	 */
	@ResourceMapping(value = "updateContentOwner")
	public void updateContentOwner(ResourceRequest request, ResourceResponse response){
		// content, auth 테이블 update
		try{
			Map params = RequestUtil.getParameterMap(request);

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = PortalUtil.getCompanyId(request);
			Long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"), CustomUtil.strNull(PortalUtil
				.getScopeGroupId(request), "0")));

			long contentSeq = Long.parseLong(CustomUtil.strNull(params.get("contentSeq"), "0"));
			long pastName = Long.parseLong(CustomUtil.strNull(params.get("pastOnwerId"), "0"));
			long newId = Long.parseLong(CustomUtil.strNull(params.get("ownerUserId"), "0"));
			String newName = CustomUtil.strNull(params.get("ownerUserName"));

			User requestUser = UserLocalServiceUtil.fetchUser(newId);

			if(requestUser != null){
				String projectYn = CustomUtil.strNull(params.get("projectYn"));

				// insertId, projectId update
				ContentLocalServiceUtil.updateContentInsertId(groupId, contentSeq, newId, projectYn, 0);

				Role contentOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_OWNER); // Role
																																																						// Id
																																																						// 확인
				Role contentManagerRole = RoleLocalServiceUtil.getRole(companyId,
					EdisonRoleConstants.CONTENT_MANAGER); // CONTENT_MANAGER Role Id 확인

				// 권한 등록
				// CUSTOM 권한 테이블 등록
				UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(newId, groupId, contentOwnerRole
					.getRoleId(), contentSeq); // SOLVER OWNER CUSTOM ROLE 추가

				// SiteRole 추가
				EdisonUserUtil.addSiteRole(requestUser, groupId, EdisonRoleConstants.CONTENT_OWNER); // SOLVER
																																														 // OWNER
																																														 // 권한
																																														 // 추가

				// 기존 OWNER 권한 삭제
				// CUSTOM 권한 테이블 삭제
				UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(pastName, groupId, contentOwnerRole
					.getRoleId(), contentSeq);

				// Administrator, Site Administrator 가 아닐경우
				// 권한 확인 후 GROUP, SiteRole 삭제
				User preUser = UserLocalServiceUtil.fetchUser(pastName);
				if(preUser != null){
					if(!EdisonUserUtil.isRegularRole(preUser, EdisonRoleConstants.ADMINISTRATOR) && !EdisonUserUtil
						.isSiteRole(preUser, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
						List<Long> contentOwnerCustomRoleList = UserGroupRoleCustomLocalServiceUtil.getCustomIdList(
							preUser.getUserId(), groupId, contentOwnerRole.getRoleId());
						List<Long> contentManagerCustomRoleList = UserGroupRoleCustomLocalServiceUtil.getCustomIdList(
							preUser.getUserId(), groupId, contentManagerRole.getRoleId());

						// 솔버 OWNER 권한이 없을 경우 사이트 CONTENT_OWNER 권한 삭제
						if(contentOwnerCustomRoleList == null){
							EdisonUserUtil.deleteSiteRole(preUser, groupId, EdisonRoleConstants.CONTENT_OWNER);
						}

						// 솔버 MANAGER 권한이 없을 경우 사이트 CONTENT_MANAGER 권한 삭제
						if(contentManagerCustomRoleList == null){
							EdisonUserUtil.deleteSiteRole(preUser, groupId, EdisonRoleConstants.CONTENT_MANAGER);
						}
					}
				}
			}
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}

	/* content manager Update */
	@ResourceMapping(value = "updateContentManager")
	public void updateContentManager(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long companyId = PortalUtil.getCompanyId(request);
			Long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"), CustomUtil.strNull(PortalUtil
				.getScopeGroupId(request), "0")));

			long contentSeq = Long.parseLong(CustomUtil.strNull(params.get("contentSeq"), "0"));
			long newId = Long.parseLong(CustomUtil.strNull(params.get("managerUserId"), "0"));
			long pastName = Long.parseLong(CustomUtil.strNull(params.get("pastManagerId"), "0"));

			User requestUser = UserLocalServiceUtil.fetchUser(newId);
			if(requestUser != null){
				Role contentManagerRole = RoleLocalServiceUtil.getRole(companyId,
					EdisonRoleConstants.CONTENT_MANAGER); // Role Id 확인

				List<Map<String, String>> contentManagerList = UserGroupRoleCustomLocalServiceUtil.getUserList(
					groupId, contentManagerRole.getRoleId(), contentSeq);
				// content MANAGER add
				// MANAGER가 없을때
				if(contentManagerList == null && pastName == 0){
					// SiteRole 추가
					Boolean addSiteRoleManager = EdisonUserUtil.addSiteRole(requestUser, groupId,
						EdisonRoleConstants.CONTENT_MANAGER);

					// CUSTOM 권한 테이블 등록
					UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(newId, groupId, contentManagerRole
						.getRoleId(), contentSeq);
				}else{
					// content manager update
					// 기존 MANAGER가 있을때
					// 새로운 MANAGER 권한 등록

					// SiteRole 추가
					Boolean addSiteRoleManager = EdisonUserUtil.addSiteRole(requestUser, groupId,
						EdisonRoleConstants.CONTENT_MANAGER);

					// CUSTOM 권한 테이블 등록
					UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(newId, groupId, contentManagerRole
						.getRoleId(), contentSeq);

					// 기존 OWNER 권한 삭제
					// CUSTOM 권한 테이블 삭제
					UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(pastName, groupId, contentManagerRole
						.getRoleId(), contentSeq);

					List<Map<String, String>> customList = UserGroupRoleCustomLocalServiceUtil.getCustomList(pastName,
						groupId, contentManagerRole.getRoleId());

					User preUser = UserLocalServiceUtil.fetchUser(pastName);
					if(preUser != null){
						if(customList == null || customList.size() == 0){ // CONTENT_MANAGER
																															// CUSTOM ROLE이
																															// 남아있는지 체크
							EdisonUserUtil.deleteSiteRole(preUser, groupId, EdisonRoleConstants.CONTENT_MANAGER); // 없으면
																																																		// 삭제
						}
					}
				}
			}

		}catch (Exception e){
			if(e instanceof EdisonException){
				System.out.println("EDISON  ERROR");
			}else{
				e.printStackTrace();
			}
		}
	}

	/* content manager delete */
	@ResourceMapping(value = "deleteContentManager")
	public void deleteContentManager(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long companyId = PortalUtil.getCompanyId(request);
			Long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"), CustomUtil.strNull(PortalUtil
				.getScopeGroupId(request), "0")));

			long contentSeq = Long.parseLong(CustomUtil.strNull(params.get("contentSeq"), "0"));
			long managerId = Long.parseLong(CustomUtil.strNull(params.get("managerUserId"), "0"));

			User user = UserLocalServiceUtil.fetchUser(managerId);

			Role contentManagerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_MANAGER); // Role
																																																							// Id
																																																							// 확인

			List<Map<String, String>> contentManagerList = UserGroupRoleCustomLocalServiceUtil.getUserList(groupId,
				contentManagerRole.getRoleId(), contentSeq);
			if(contentManagerList != null){
				// content manager delete

				// CUSTOM 권한 테이블 삭제
				UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(managerId, groupId, contentManagerRole
					.getRoleId(), contentSeq);

				List<Map<String, String>> customList = UserGroupRoleCustomLocalServiceUtil.getCustomList(managerId,
					groupId, contentManagerRole.getRoleId());

				if(customList == null || customList.size() == 0){ // CONTENT_MANAGER
																													// CUSTOM ROLE이 남아있는지
																													// 체크
					EdisonUserUtil.deleteSiteRole(user, groupId, EdisonRoleConstants.CONTENT_MANAGER); // 없으면
																																														 // 삭제
				}
			}
		}catch (Exception e){
			if(e instanceof EdisonException){
				System.out.println("EDISON  ERROR");
			}else{
				e.printStackTrace();
			}
		}
	}
}
