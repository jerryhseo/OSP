package org.kisti.edison.multiboard.controller.board;

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
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.math.NumberUtils;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.multiboard.model.Board;
import org.kisti.edison.multiboard.model.BoardDiv;
import org.kisti.edison.multiboard.service.BoardDivLocalServiceUtil;
import org.kisti.edison.multiboard.service.BoardLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class BoardController {
	
	protected static Log  log = LogFactoryUtil.getLog(BoardController.class);
	
	/**
	 * 게시판 기본 보기
	 * @param request
	 * boardGroupId - 게시판을 조회 하고자 하는 GroupId
	 * redirectURL  - 현재 화면의 URL
	 * redirectName - 현재 화면의 타이틀
	 * customId     - 게시판 조회 ID
	 * isCustomAdmin- 수정, 삭제 권한 여부 (관리자일 경우 Board에서 판단) ex){"true", "t", "y", "on", "1"}
	 * isDefaultUserWrite - 일반 유저 글쓰기 권한 ex){"true", "t", "y", "on", "1"}
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		Map params = RequestUtil.getParameterMap(request);
		String mainListYn = request.getPreferences().getValue("mainListYn", "N");
		String maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), "N");
		String viewStructure = request.getPreferences().getValue("viewStructure", "N");
		Long divCd=0l;
		String customId="";
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			long companyId = PortalUtil.getCompanyId(request);
			
			//parameter
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");
			customId = ParamUtil.getString(request, "customId");
			boolean isCustomAdmin = ParamUtil.getBoolean(request, "isCustomAdmin",false);
			boolean isDefaultUserWrite = ParamUtil.getBoolean(request, "isDefaultUserWrite",false);
			
			if(themeDisplay.isSignedIn()){
				//관리자 일경우 글 수정,삭제 가능
				User user = PortalUtil.getUser(request);
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, boardGroupId, RoleConstants.SITE_ADMINISTRATOR)){
					isCustomAdmin = true;
				}
			}else{
				isDefaultUserWrite=false;
			}
			
			divCd = ParamUtil.getLong(request, "divCd",0);
			if(divCd==0){
				divCd = Long.parseLong(request.getPreferences().getValue("divCd", "100"));
			}
			//portlet 셋팅값
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			
			BoardDiv boardDiv = BoardDivLocalServiceUtil.getBoardDiv(divCd);
			String boardDivTitle = boardDiv.getTitleNm(themeDisplay.getLocale());
			boolean popState = GetterUtil.getBoolean(boardDiv.getPopupYn(),false);
			
			List popupList = new ArrayList();
			if(popState){		
				List boardPopupList = BoardDivLocalServiceUtil.getCustomListBoard(divCd, 0, 100, boardGroupId, customId, "", themeDisplay.getLocale(), 0, true, String.valueOf(boardGroupId), "");
				
				Map cookieMap = new java.util.HashMap();
	
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (int i = 0 ; i < cookies.length ; i++) {
						cookieMap.put(cookies[i].getName(), cookies[i]);
					}
				}
	
				
				List popupFileList = null;
				Map popupMap = null;
				for(int i=0;i<boardPopupList.size();i++){
	
					popupMap = (Map) boardPopupList.get(i);
	
					String popupMapSeq = "POPUP_"+CustomUtil.strNull(popupMap.get("boardSeq"));
					if(CustomUtil.strNull(cookieMap.get(popupMapSeq)).equals("")){
	
						popupMap = (Map)boardPopupList.get(i);
	
						String preFix = "_"+customId.replaceAll("[^0-9]", "");
						
						popupFileList = EdisonFileUtil.getListEdisonFile(
																	boardGroupId, 
																	preFix, 
																	CustomUtil.strNull(popupMap.get("boardSeq")),
																	divSort
																	);
	
						popupMap.put("fileList", popupFileList);
	
						popupList.add(popupMap);
					}
				}
	
				if(popupList != null && popupList.size() > 0){
					popState = true;
				}
			}
			
			/*통합검색*/
			long searchPlid = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/search").getPlid();
			PortletURL searchUrl = PortletURLFactoryUtil.create(request,"edisonsearch_WAR_edisonsearch2017portlet", searchPlid, PortletRequest.RENDER_PHASE);
			model.addAttribute("searchURL", searchUrl);
			
			//parameter
			model.addAttribute("isCustomAdmin", isCustomAdmin);
			model.addAttribute("customId", customId);
			model.addAttribute("isDefaultUserWrite", isDefaultUserWrite);
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("boardGroupId", String.valueOf(boardGroupId));
			
			model.addAttribute("currentPage", CustomUtil.strNull(params.get("currentPage"), "1"));
			model.addAttribute("searchValue", CustomUtil.strNull(params.get("searchValue")).equals("undefined") ? "" :  CustomUtil.strNull(params.get("searchValue")));
			model.addAttribute("listSize", CustomUtil.strNull(params.get("listSize"),"10"));
			model.addAttribute("popState", popState);
			model.addAttribute("viewStructure", viewStructure);
			model.addAttribute("siteName", themeDisplay.getSiteGroupName());
			
			//redirectURL decode
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			
			//data
			model.addAttribute("boardDiv", boardDiv);
			model.addAttribute("boardDivTitle", boardDivTitle);
			model.addAttribute("divSort", divSort);
			model.addAttribute("popupList", popupList);
			
			boolean isVirtualClass = Boolean.parseBoolean(ParamUtil.getString(request, "isVirtualClass"));
			model.addAttribute("isVirtualClass", isVirtualClass);
			String virtualLabId = ParamUtil.getString(request, "virtualLabId");
			model.addAttribute("virtualLabId", virtualLabId);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		
		//LIST 화면 이동 STATUS 
		if(mainListYn.equals("Y")){
			model.addAttribute("maxWindowStatus", "Y");
		}else{
			model.addAttribute("maxWindowStatus", "N");
		}
		
		if(mainListYn.equals("Y") && maxWindowStatus.equals("N")){
			if(divCd == 100 && viewStructure.equals("card")){
				return "noticeByViewType";
			} else if(divCd == 100 && !customId.contains("class") && (viewStructure.equals("list") || viewStructure.equals("N"))){
				return "list";
			} else if(customId.contains("class")){
				if(divCd==100){
					return "noticeInVirtualClass";
				} else if(divCd==200){
					return "qnaInVirtualClass";
				}
			}
			return "listMain";
		}else{
			if(customId.contains("class") && maxWindowStatus.equals("N")){
				if(divCd==100){
					return "noticeInVirtualClass";
				} else if(divCd==200){
					return "qnaInVirtualClass";
				}
			}
			return "list";
		}
	}

	@ResourceMapping(value="getBoardList")
	public void getBoardDivBoards(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		Map params = RequestUtil.getParameterMap(request);
		
		//parameter
		long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
		long groupBoardSeq = ParamUtil.get(request, "groupBoardSeq", 0);	// QnA > 답변 데이터 추출을 위한 Parameter 추가
		String customId = ParamUtil.getString(request, "customId");
		
		int listSize = Integer.parseInt(CustomUtil.strNull(params.get("listSize"), "10"));
		int currentPage = Integer.parseInt(CustomUtil.strNull(params.get("currentPage"), "1"));
		
		// 강좌에서 호출하였을 경우 virtualLabId추출
		boolean isVirtualClass = Boolean.parseBoolean(CustomUtil.strNull(params.get("isVirtualClass"), "false"));
		String virtualLabId = "";
		if(isVirtualClass){
			virtualLabId = CustomUtil.strNull(params.get("virtualLabId"), "");
		}
		
		String searchValue = CustomUtil.strNull(params.get("searchValue"));
		Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
		int blockSize = Integer.parseInt(((PortletRequest) request).getPreferences().getValue("blockSize", "10"));
		int start = ((currentPage - 1) * listSize);
		
		long parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();
		String isPortal = "true";
		String siteGroup = "";
		// 포털 체크 parentGroupId가 0이면 포털
		if(parentGroupId != 0) {
			isPortal = "false";
		}
		if(isPortal.equals("false") && divCd == 100){
			siteGroup = String.valueOf(ParamUtil.get(request, "groupId", ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId())));
		}
		
		// 강좌에서 호출 -> 강의들의 질의응답 추출 , 그 외에는 해당 페이지 내의 boardList만 추출 :: 추출을 위해 virtualLabId(Parameter) 추가
		List boardList = BoardDivLocalServiceUtil.getCustomListBoard(divCd, start, listSize, boardGroupId, customId, searchValue, locale, groupBoardSeq, false, siteGroup, virtualLabId);
		int totalCount = BoardDivLocalServiceUtil.getCustomCountBoard(divCd, boardGroupId, customId, searchValue, groupBoardSeq, siteGroup);
		String paging = PagingUtil.getPaging(request.getContextPath(), CustomUtil.strNull(params.get("methodName")), totalCount, currentPage, listSize, blockSize);
		
		int pageCount = 0;		// 구현해야하는 pagination 개수
		if(totalCount % listSize == 0){
			pageCount = (totalCount / listSize);
		} else {
			pageCount = (totalCount / listSize)+1;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("divCd", divCd);	// divCd(구분코드)를 넘겨주어 어떤 게시판인지 확인
		obj.put("boardList", boardList);
		obj.put("seq", totalCount - ((currentPage - 1)*listSize));
		obj.put("paging", paging);
		obj.put("isVirtualClass", isVirtualClass);
		obj.put("pageCount", pageCount);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}

	@RenderMapping(params = "myRender=getBoardRender")
	public String getBoardRender(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, SQLException, ParseException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		
		String mode = CustomUtil.strNull(params.get("RENDER_SORT"),"VIEW");
		//parameter
		long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
		String redirectURL = ParamUtil.getString(request, "redirectURL", "");
		String redirectName = ParamUtil.getString(request, "redirectName");
		String customId = CustomUtil.strNull(params.get("customId"), "");
		boolean isCustomAdmin = ParamUtil.getBoolean(request, "isCustomAdmin",false);
		boolean isDefaultUserWrite = ParamUtil.getBoolean(request, "isDefaultUserWrite",false);
		
		try {
		    
		    String boardSeq = ParamUtil.getString(request, "boardSeq", "");
            if( ( !StringUtils.hasText(boardSeq) && !mode.equals("WRITE") )
                || ( NumberUtils.isNumber(boardSeq) 
                    && NumberUtils.toLong(boardSeq) <= 0 && mode.equals("VIEW"))){
                log.error("BoardSeq is not valid.");
                SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
                return null;
            }
		    
			//VIEW가 아니면서 (게시물 보기) 로그인이 안되어있을 경우 홈 화면으로 보냄
			if(!mode.equals("VIEW") && !themeDisplay.isSignedIn()) {
				model.addAttribute("backURL", themeDisplay.getPortalURL() + "/c/portal/login");
				return "error";
			}
			
			
			//variable
			long groupId = PortalUtil.getScopeGroupId(request);
			long companyId = PortalUtil.getCompanyId(request);
			long userId = PortalUtil.getUserId(request);
			User user = PortalUtil.getUser(request);
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			String virClassNotice = request.getPreferences().getValue("virClassNotice", "N");
			
			model.addAttribute("virClassNotice", virClassNotice);
			
			// NOTICE(공지사항) 인데 권한이 없는 유저일때
			if(!mode.equals("VIEW") && divSort.equals("NOTICE")) {
				boolean isSiteAdministrator = EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR);
				boolean isSiteOwner = EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_OWNER);
				boolean isAdmin = EdisonUserUtil.isPowerUserThan(user);
				if(!isSiteAdministrator && !isSiteOwner && !isAdmin && !isCustomAdmin) {
					model.addAttribute("backURL", themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic() + group.getFriendlyURL() + "/home");
					return "error";
				}
			}
			
			
			Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
			BoardDiv boardDiv = BoardDivLocalServiceUtil.getBoardDiv(divCd);
			model.addAttribute("boardDiv", boardDiv);
			
			String boardDivTitle = boardDiv.getTitleNm(themeDisplay.getLocale());
			model.addAttribute("boardDivTitle", boardDivTitle);
			
			
			
			List<com.liferay.portal.model.Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
			Group portalGroup = null;
			for(Group parentGroup:parentGroupList){
				if(StringUtil.toUpperCase(parentGroup.getName()).equals("GUEST")){
					portalGroup = parentGroup;
					break;
				}
			}
			long portalGroupId = portalGroup.getGroupId();
			
			boolean isPortal = false; //포탈 여부
			boolean isChildrenSite = false;
			long parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();
			
			if(GroupLocalServiceUtil.getGroups(companyId, PortalUtil.getScopeGroupId(request), true).size()>0){
				isChildrenSite = true;
			}
			
			if(parentGroupId == 0 && isChildrenSite){
				isPortal = true; 
			}
			
			Map boardMap = null;
			
			Locale locale = themeDisplay.getLocale();
			
			if(mode.equals("WRITE") || mode.equals("UPDATE")){
				locale = CustomUtil.stringToLocale(CustomUtil.strNull(params.get("select_languageId")));
				if(isPortal == true && divCd == 100){
					List<Group> groupList = GroupLocalServiceUtil.getGroups(companyId, portalGroup.getGroupId(), true);//하위 그룹 리스트
					model.put("groupList", groupList) ;
				}
			}
			
			if(mode.equals("REPLY")){
				params.put("boardSeq", CustomUtil.strNull(params.get("groupBoardSeq")));
			}
			
			long getboardGroupId = boardGroupId;
			if(!CustomUtil.strNull(params.get("boardSeq")).equals("")){
				boardMap = BoardLocalServiceUtil.getBoard(divCd, customId, GetterUtil.get(params.get("boardSeq"), 0L), locale);
				String getGroupId = CustomUtil.strNull(boardMap.get("groupId"));
				if(!getGroupId.equals("")){
					getboardGroupId = Long.parseLong(getGroupId);
				}
				
				String[] siteGroups = CustomUtil.strNull(boardMap.get("siteGroup")).split(",");
				model.addAttribute("siteGroups", siteGroups);
			}
			
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			List fileList = null;
			if(!CustomUtil.strNull(params.get("boardSeq")).equals("")){
				fileList = EdisonFileUtil.getListEdisonFile(
															getboardGroupId, 
															preFix, 
															GetterUtil.get(params.get("boardSeq"), ""),
															divSort
															);
				
			}
			
			String siteGroup = "";
			if(isPortal == false && divCd == 100){
				siteGroup = String.valueOf(ParamUtil.get(request, "groupId", ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId())));
			}
			List replyList = new ArrayList();
			
			if(boardDiv.getReplyYn() && !GetterUtil.get(params.get("boardSeq"), "").equals("")){
				List boardList = BoardDivLocalServiceUtil.getCustomListBoard(divCd, 0, 100000, boardGroupId, customId, "", locale, GetterUtil.get(params.get("boardSeq"), 0L), false, siteGroup, "");
				
				List replyFileList = null;
				Map map = null;
				
				if(boardList != null && boardList.size() > 0){
					for(int i=0; i<boardList.size();i++){					
						map = (Map) boardList.get(i);					
						replyFileList = EdisonFileUtil.getListEdisonFile(
																	boardGroupId, 
																	preFix, 
																	GetterUtil.get(params.get("boardSeq"), "")
																	,divSort
																	);
						
						map.put("fileList", replyFileList);
						replyList.add(map);
					}
				}
			}
			
			model.addAttribute("isPortal", isPortal);
			model.addAttribute("boardGroupId", String.valueOf(boardGroupId));
			model.addAttribute("replyList",	replyList);
			model.addAttribute("isCustomAdmin", isCustomAdmin);
			model.addAttribute("boardMap",	boardMap);
			model.addAttribute("fileList",	fileList);
			model.addAttribute("boardSeq",	CustomUtil.strNull(params.get("boardSeq")));	
			model.addAttribute("replyBoardSeq",	CustomUtil.strNull(params.get("replyBoardSeq")));		
			model.addAttribute("select_languageId",	GetterUtil.get(params.get("select_languageId"), themeDisplay.getLocale().toString()));		
			model.addAttribute("RENDER_SORT",	mode);
			model.addAttribute("RENDER_MSG",	CustomUtil.strNull(params.get("RENDER_MSG")));

			model.addAttribute("currentPage",	CustomUtil.strNull(params.get("currentPage"), "1"));
			model.addAttribute("searchValue",	CustomUtil.strNull(params.get("searchValue")).equals("undefined") ? "" :  CustomUtil.strNull(params.get("searchValue")));
			model.addAttribute("listSize",	CustomUtil.strNull(params.get("listSize")));
			
			
			model.addAttribute("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			model.addAttribute("customId", customId);
			model.addAttribute("isDefaultUserWrite", isDefaultUserWrite);
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			
			//redirectURL decode
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			
			
			String currunt_folder = "/" +portalGroupId+" - " +CompanyLocalServiceUtil.getCompany(PortalUtil.getCompanyId(request)).getName() + "/"+portalGroupId+"_EDISON_FILE"+"/"+EdisonFileConstants.USER_IMAGE+"/"+userId+ "/";
			model.addAttribute("currentFolder", currunt_folder);
			
			if(mode.equals("WRITE")){
				EdisonFileUtil.checkUserFolder(request, userId, portalGroupId, EdisonFileConstants.USER_IMAGE);
				return "write";
			}else if(mode.equals("UPDATE")){
				EdisonFileUtil.checkUserFolder(request, userId, portalGroupId, EdisonFileConstants.USER_IMAGE);
				return "write";
			}else if(mode.equals("REPLY")){
				return "view";
			}else if(mode.equals("REPLY_DELETE")){
				return "view";			
			}else{
				Map upMap = new HashMap();

				upMap.put("userId", themeDisplay.getUserId());
				upMap.put("locale", locale);
				upMap.put("boardSeq", Long.parseLong(CustomUtil.strNull(params.get("boardSeq"), "0")));
				upMap.put("readCnt", (Integer.parseInt(CustomUtil.strNull(boardMap.get("readCnt"), "0"))+1));
				
				BoardLocalServiceUtil.updateBoard(upMap);
			}
			
			if(mode.equals("WRITE") || mode.equals("REPLY")){
				SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
			}else if(mode.equals("UPDATE")){
				SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
			}else if(mode.equals("DELETE") || mode.equals("REPLY_DELETE")){
				SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			if(mode.equals("WRITE") || mode.equals("REPLY")){
				SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
			}else if(mode.equals("UPDATE")){
				SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
			}else if(mode.equals("DELETE") || mode.equals("REPLY_DELETE")){
				SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
			}
		}
		return "view";
	}

	@ActionMapping(params="myAction=addBoardAction")
	public void addBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
			Map params = RequestUtil.getParameterMap(upload);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			String customId = ParamUtil.getString(request, "customId");
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			
			long userId = themeDisplay.getUserId();
			long boardGroupId = GetterUtil.get(params.get("boardGroupId"), themeDisplay.getScopeGroupId());
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			
			Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
			
			String[] siteGroups = request.getParameterValues("siteGroup");
			String siteGroup = "";
			if(siteGroups != null){
				for(String siteGroupId : siteGroups){
					if(!siteGroup.equals("")){
						siteGroup+=",";
					}
					siteGroup+= siteGroupId;
				}
				params.put("siteGroup", siteGroup);
			}
			params.put("groupId", boardGroupId);
			params.put("divCd", divCd);
			params.put("userId", themeDisplay.getUserId());
			params.put("locale", CustomUtil.stringToLocale(CustomUtil.strNull(params.get("select_languageId"))));
			params.put("allNoticeYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("allNoticeYn"), "0")));
			params.put("popupYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("popupYn"), "0")));
			params.put("popupStartDt", CustomUtil.strNull(params.get("popupStartDt")));
			params.put("popupEndDt", CustomUtil.strNull(params.get("popupEndDt")));
			params.put("groupBoardSeq", "0");
			params.put("groupBoardTurn", "0");
			params.put("replyDepth", 0);
			
			
			Board brd = BoardLocalServiceUtil.addBoard(params);
			
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			EdisonFileUtil.insertEdisonFile(request, upload, userId, boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			
			portletURL.setParameter("myRender", "getBoardRender");
			portletURL.setParameter("RENDER_SORT", "WRITE");
			portletURL.setParameter("RENDER_MSG", "WRITE");
			portletURL.setParameter("select_languageId", CustomUtil.strNull(params.get("select_languageId")));
			portletURL.setParameter("boardSeq", CustomUtil.strNull(brd.getBoardSeq(), "0"));
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(boardGroupId));
			
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
			
//			portletURL.setParameter("maxList", "Y");
			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	@ActionMapping(params="myAction=addReplyBoardAction")
	public void addReplyBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
	
			Map params = RequestUtil.getParameterMap(upload);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			//parameter
			String customId = ParamUtil.getString(request, "customId");
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			
			long userId = themeDisplay.getUserId();
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			
			Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
			
			
			params.put("customId", customId);
			params.put("groupId", boardGroupId);
			params.put("divCd", divCd);
			params.put("userId", themeDisplay.getUserId());
			params.put("locale", CustomUtil.stringToLocale(CustomUtil.strNull(params.get("select_languageId"))));
			params.put("allNoticeYn", false);
			params.put("popupYn", false);
			params.put("popupStartDt", "");
			params.put("popupEndDt", "");
	
			params.put("groupBoardSeq", Integer.parseInt(CustomUtil.strNull(params.get("groupBoardSeq"))));
			params.put("groupBoardTurn", 0);
			params.put("replyDepth", 0);
	
			Board brd = BoardLocalServiceUtil.addBoard(params);
	
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			
			EdisonFileUtil.insertEdisonFile(request, upload, userId, boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			
			portletURL.setParameter("myRender", "getBoardRender");
			portletURL.setParameter("RENDER_SORT", "REPLY");
			portletURL.setParameter("RENDER_MSG", "WRITE");
			portletURL.setParameter("groupBoardSeq", CustomUtil.strNull(params.get("groupBoardSeq")));
			portletURL.setParameter("boardSeq", CustomUtil.strNull(params.get("boardSeq")));
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(params.get("boardGroupId")));
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}

	@ActionMapping(params="myAction=updateBoardAction")
	public void updateBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
	
			Map params = RequestUtil.getParameterMap(upload);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			//parameter
			String customId = ParamUtil.getString(request, "customId");
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			
			long userId = themeDisplay.getUserId();
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			
			Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
			
			long boardSeq = Long.parseLong(CustomUtil.strNull(params.get("boardSeq")));
			params.put("customId", customId);
			params.put("userId", userId);
			params.put("boardSeq", boardSeq);
			params.put("locale", CustomUtil.stringToLocale(CustomUtil.strNull(params.get("current_languageId"), CustomUtil.strNull(params.get("select_languageId")))));
			params.put("allNoticeYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("allNoticeYn"), "0")));
			params.put("popupYn", Boolean.parseBoolean(CustomUtil.strNull(params.get("popupYn"), "0")));
			params.put("popupStartDt", CustomUtil.strNull(params.get("popupStartDt")));
			params.put("popupEndDt", CustomUtil.strNull(params.get("popupEndDt")));
			
			String[] siteGroups = request.getParameterValues("siteGroup");
			String siteGroup = "";
			if(siteGroups != null){
				for(String siteGroupId : siteGroups){
					if(!siteGroup.equals("")){
						siteGroup+=",";
					}
					siteGroup+= siteGroupId;
				}
				params.put("siteGroup", siteGroup);
			}
			
			Board brd = BoardLocalServiceUtil.updateBoard(params);
	
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			EdisonFileUtil.insertEdisonFile(request, upload, userId, boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			
			portletURL.setParameter("myRender", "getBoardRender");
			portletURL.setParameter("RENDER_SORT", "UPDATE");
			portletURL.setParameter("RENDER_MSG", "UPDATE");
			portletURL.setParameter("select_languageId", CustomUtil.strNull(params.get("select_languageId")));
			portletURL.setParameter("boardSeq", CustomUtil.strNull(params.get("boardSeq")));
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(params.get("boardGroupId")));
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}	

	@ActionMapping(params="myAction=updateReplyBoardAction")
	public void updateReplyBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
	
			Map params = RequestUtil.getParameterMap(upload);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();
			
			//parameter
			String customId = ParamUtil.getString(request, "customId");
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			
			long userId = themeDisplay.getUserId();
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			
			Long divCd = Long.parseLong(((PortletRequest) request).getPreferences().getValue("divCd", "100"));
	
			long replyBoardSeq = Long.parseLong(CustomUtil.strNull(params.get("replyBoardSeq")));
			
			params.put("locale", locale);
			params.put("userId", userId);
			params.put("boardSeq", replyBoardSeq);
	
			Board brd = BoardLocalServiceUtil.updateBoard(params);
	
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			
			EdisonFileUtil.insertEdisonFile(request, upload, brd.getWriterId(), boardGroupId, preFix, CustomUtil.strNull(String.valueOf(brd.getBoardSeq())), "addfile", divSort);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			portletURL.setParameter("myRender", "getBoardRender");
			portletURL.setParameter("RENDER_SORT", "REPLY");
			portletURL.setParameter("RENDER_MSG", "UPDATE");
			portletURL.setParameter("select_languageId", CustomUtil.strNull(params.get("select_languageId")));
			portletURL.setParameter("groupBoardSeq", CustomUtil.strNull(params.get("groupBoardSeq")));
			portletURL.setParameter("boardSeq", CustomUtil.strNull(params.get("boardSeq")));
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(params.get("boardGroupId")));
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}
	
	@ActionMapping(params="myAction=deleteBoardAction")
	public void deleteBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			String boardSeq = ParamUtil.getString(request, "boardSeq", "");
			if(!StringUtils.hasText(boardSeq) 
			    || ( NumberUtils.isNumber(boardSeq) 
			        && NumberUtils.toLong(boardSeq) <= 0)){
			    log.error("boardSeq is not valid.");
			    SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
			    return;
			}
			//parameter
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			String customId = ParamUtil.getString(request, "customId");
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			Board brd = BoardLocalServiceUtil.deleteBoard(Long.parseLong(CustomUtil.strNull(params.get("boardSeq"),"0")));
	
			String preFix = "";
			preFix += String.valueOf(boardGroupId);
			preFix += customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			String folderSeq = preFix + "_" + CustomUtil.strNull(params.get("boardSeq"));
			
			EdisonFileUtil.deleteGroupEdisonFile(boardGroupId, divSort, folderSeq);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(params.get("boardGroupId")));
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			
			SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			response.sendRedirect(portletURL.toString());			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
		}
	}

	@ActionMapping(params="myAction=deleteReplyBoardAction")
	public void deleteReplyBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			//parameter
			String customId = CustomUtil.strNull(params.get("customId"), "");
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			boolean maxWindowStatus = GetterUtil.get(params.get("maxWindowStatus"), false);
			
			long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
			
			
			Board brd = BoardLocalServiceUtil.deleteBoard(Long.parseLong(CustomUtil.strNull(params.get("replyBoardSeq"))));
			
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			if(!divSort.equals("")) preFix += "_"+divSort;
			
//			EdisonFileUtil.deleteGroupEdisonFile(boardGroupId, preFix, CustomUtil.strNull(params.get("replyBoardSeq")));
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			if(maxWindowStatus){portletURL.setWindowState(WindowState.MAXIMIZED);}
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myRender", "getBoardRender");
			portletURL.setParameter("RENDER_SORT", "REPLY");
			portletURL.setParameter("RENDER_MSG", "REPLY");
			portletURL.setParameter("groupBoardSeq", CustomUtil.strNull(params.get("boardSeq")));
			portletURL.setParameter("boardSeq", CustomUtil.strNull(params.get("replyBoardSeq")));
			
			portletURL.setParameter("boardGroupId", CustomUtil.strNull(params.get("boardGroupId")));
			
			portletURL.setParameter("customId", CustomUtil.strNull(params.get("customId")));
			portletURL.setParameter("isCustomAdmin", CustomUtil.strNull(params.get("isCustomAdmin")));
			portletURL.setParameter("isDefaultUserWrite", CustomUtil.strNull(params.get("isDefaultUserWrite")));
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("maxWindowStatus", CustomUtil.strNull(params.get("maxWindowStatus")));
			
			portletURL.setParameter("searchValue", CustomUtil.strNull(params.get("searchValue")));
			portletURL.setParameter("listSize", CustomUtil.strNull(params.get("listSize")));
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage")));
			
			SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
		}
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{

		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}	

	@ResourceMapping(value="deleteSingleEdisonFile")
	public void deleteSingleEdisonFile(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{
		
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(params.get("fileEntryId")));
		long boardGroupId = ParamUtil.get(request, "boardGroupId", themeDisplay.getSiteGroupId());
		String customId = CustomUtil.strNull(params.get("customId"), "");
		
		String resultMsg = "";
		List fileList = new ArrayList();
		
		if(EdisonFileUtil.deleteSingleEdisonFile(fileEntryId)){
			
			String divSort = request.getPreferences().getValue("divSort", "NOTICE");
			String boardSeq = CustomUtil.strNull(params.get("boardSeq"));
			
			String preFix = customId.equals("")?"":"_"+customId.replaceAll("\\D", "");
			
			fileList = EdisonFileUtil.getListEdisonFile(
														boardGroupId, 
														preFix, 
														boardSeq,
														divSort);
			
			resultMsg = "SUCCESS";
		}else{
			resultMsg = "DELETE_FAIL";
		}
		
		JSONObject obj = new JSONObject();
		
		obj.put("fileList", fileList);
		obj.put("resultMsg", resultMsg);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="getNoticeListForVirtualClass")
	public void getNoticeListForVirtualClass(ResourceRequest request, ResourceResponse response){
		
		try {
			
			getBoardDivBoards(request, response);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
