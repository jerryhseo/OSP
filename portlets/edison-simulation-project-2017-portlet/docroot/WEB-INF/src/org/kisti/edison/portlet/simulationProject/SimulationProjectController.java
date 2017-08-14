package org.kisti.edison.portlet.simulationProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
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

import org.kisti.edison.constants.SimulationProjectConstants;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.service.RequestMemberLocalServiceUtil;
import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class SimulationProjectController {

	private static Log log = LogFactoryUtil.getLog(SimulationProjectController.class);
	
	/**
	 * 시뮬레이션 프로젝트 기본 보기
	 * @param request
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭
	 * @param currentPage - 현재 페이지
	 * @param listSize - 한페이지에 리스트 출력 개수
	 * @param blockSize - 페이징 버튼 최대 출력 개수	  
	 * @param response
	 * @param model
	 * @return 
	 */
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, ParseException, IOException {
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		int currentPage = Integer.parseInt(CustomUtil.strNull(params.get("currentPage"), "1"));
		int listSize = Integer.parseInt(CustomUtil.strNull(params.get("listSize"), "10"));
		int blockSize = Integer.parseInt(((PortletRequest) request).getPreferences().getValue("blockSize", "10"));
		int begin = ((currentPage - 1) * listSize);
		int end = listSize;
		
		long ownerId = themeDisplay.getUserId();
		long groupId = themeDisplay.getScopeGroupId();
		long globalGroupId = themeDisplay.getCompany().getGroupId();
		
		List<Map<String,Object>> mySimulationProjectMapList  = new ArrayList<Map<String,Object>>();
		int totalCount = 0;
		User user = themeDisplay.getUser();
		Locale locale = themeDisplay.getLocale();
		
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
		
		String searchValue = CustomUtil.strNull(params.get("searchText"));
		
		if(parentGroupId == 0){//포탈
			if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)) {
				mySimulationProjectMapList  = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(begin, end, searchValue, locale);
				totalCount = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(searchValue, locale);
			}else{
				mySimulationProjectMapList  = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(ownerId, begin, end, searchValue, locale);
				totalCount = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(ownerId, searchValue, locale);
			}
		}else{
			List<Long> siteCategoryIdList = SimulationProjectLocalServiceUtil.getSiteCategoryIdList(globalGroupId, groupId);
			
			if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)) {
				mySimulationProjectMapList  = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(begin, end, searchValue, locale, siteCategoryIdList);
				totalCount = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(searchValue, siteCategoryIdList, locale);
			}else{
				mySimulationProjectMapList  = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(ownerId, begin, end, searchValue, locale, siteCategoryIdList);
				totalCount = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(ownerId, searchValue, siteCategoryIdList, locale);
			}
		}
		
		String paging = PagingUtil.getPaging(request.getContextPath(), CustomUtil.strNull(params.get("methodName"), 
					"_edisonsimulationproject_WAR_edisonsimulationproject2017portlet_searchSimulationProjectList"), totalCount, currentPage, listSize, blockSize);
		
		model.addAttribute("mySimulationProjectList", mySimulationProjectMapList );
		model.addAttribute("seq", totalCount - ((currentPage - 1)*listSize));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("paging", paging);
		model.addAttribute("listSize", listSize);
		model.addAttribute("searchText", searchValue);
		
		boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
		model.addAttribute("isRedirect", isRedirect);
		if(isRedirect){
			String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
			String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
			
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
		}	
		
		model.addAttribute("portletWindowState", request.getWindowState().toString());
		
		model.addAttribute("userId", themeDisplay.getUserId());
		
		return "simulationProject/list"; 
	}
	
	/**
	 * 시뮬레이션 프로젝트 상세보기
	 * @param request
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭
	 * @param model
	 * @return 
	 */
	@RenderMapping(params = "myRender=detailView")
	public String detailView(RenderRequest request, ModelMap model){
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long simulationProjectId = Long.parseLong(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		long simulationClassId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class);
		long groupId = themeDisplay.getScopeGroupId();
		long portalGroupId = groupId;
		long userId = themeDisplay.getUserId();
		
		User user = themeDisplay.getUser();
		Locale locale = themeDisplay.getLocale();
		
		String currentPage = CustomUtil.strNull(params.get("currentPage"), "1");
		String listSize = CustomUtil.strNull(params.get("listSize"), "10");
        String methodName = CustomUtil.strNull(params.get("methodName"));
        String searchValue = CustomUtil.strNull(params.get("searchText"));
		
		try{
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("listSize", listSize);
	        model.addAttribute("methodName", methodName);
			model.addAttribute("searchText", searchValue);
			
			boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
			model.addAttribute("isRedirect", isRedirect);
			if(isRedirect){
				String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
				String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
				
				model.addAttribute("redirectName", redirectName);
				model.addAttribute("redirectURL", redirectURL);
				
				//redirectURL decode
				model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			}
			
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			Group group;
			group = GroupLocalServiceUtil.fetchGroup(portalGroupId);
			if(group.getParentGroupId() != 0){
				portalGroupId = group.getParentGroupId();
			}
			
			model.addAttribute("portalGroupId", portalGroupId);
			
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			
			model.addAttribute("simulationProjectId", simulationProjectId );
			model.addAttribute("simulationClassId", simulationClassId );
			data = SimulationProjectLocalServiceUtil.getCustomDefaultInfoSimulationProject(simulationProjectId, portalGroupId, themeDisplay.getCompany().getGroupId(), locale);
			long ownerId = Long.valueOf(CustomUtil.strNull(data.get("ownerId"), "0"));
			User ownerUser = UserLocalServiceUtil.getUser(ownerId);
			String universityField = (String)ownerUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
			
			Map<String, Object> ownerInfo = new HashMap<String, Object>();
			ownerInfo.put("firstName", ownerUser.getFirstName());
			ownerInfo.put("screenName", ownerUser.getScreenName());
			ownerInfo.put("universityField", universityField);
			ownerInfo.put("universityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(universityField, EdisonExpando.CDNM , locale));
			ownerInfo.put("portraitURL", ownerUser.getPortraitURL(themeDisplay));
			
			model.addAttribute("ownerInfo", ownerInfo);
			
			//소유자
			if(userId == ownerId){
				model.addAttribute("isOwner", true);
			}else{
				model.addAttribute("isOwner", false);
			}
			
			//관리자
			if(parentGroupId == 0){//포탈
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)) {
					model.addAttribute("isAdmin", true);
				}else{
					model.addAttribute("isAdmin", false);
				}
			}else{
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)) {
					model.addAttribute("isAdmin", true);
				}else{
					model.addAttribute("isAdmin", false);
				}
			}
			
			//프로젝트 유저			
			boolean isMember = false;
			isMember = RequestMemberLocalServiceUtil.isSimulationProjectMember(simulationProjectId, userId, portalGroupId);
			model.addAttribute("isMember", isMember);
			
			//멤버 신청중 유저
			boolean isMemberRequest = false;
			isMemberRequest = RequestMemberLocalServiceUtil.isSimulationProjectMemberRequest(simulationProjectId, userId);
			model.addAttribute("isMemberRequest", isMemberRequest);
			
			//temp User
			boolean isTempUser = false;
			isTempUser = EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.TEMP_USER);
			model.addAttribute("isTempUser", isTempUser);
			
			model.addAttribute("data", data );
			

			/*asset 바로가기 상태 체크*/
			String viewStatus = ParamUtil.getString(request, "viewStatus", "");
			model.addAttribute("viewStatus", viewStatus);
			
			return "simulationProject/view";
		
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.EVENT_ERROR);
			return "simulationProject/list";
		}
	}

	/**
	 * 시뮬레이션 프로젝트 관리 페이지의 앱관리 팝업 
	 * @param request
	 * @param scienceAppIdListJson 사이언스앱 아이디 (Json 문자열)   
	 * @param model
	 * @return 
	 */
	@RenderMapping(params = "myRender=scienceAppManagement")
	public String scienceAppManagement(RenderRequest request, ModelMap model){
		Map params = RequestUtil.getParameterMap(request);
		
		String scienceAppIdListJson = ParamUtil.getString(request, "scienceAppIdListJson", "");
		model.addAttribute("scienceAppIdListJson", scienceAppIdListJson);
		model.addAttribute("SCIENCE_APP", SimulationProjectConstants.SCIENCE_APP_CLASS_NAME);
		
		return "simulationProject/scienceApp_management";
	}
	
	/**
	 * 시뮬레이션 프로젝트 관리 페이지 이동
	 * @param request
	 * @param model
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭 
	 * @return string
	 * @throws SystemException 
	 */
	@RenderMapping(params = "myRender=management")
	public String managetment(RenderRequest request, ModelMap model) throws SystemException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long simulationProjectId = Long.parseLong(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		long groupId = themeDisplay.getScopeGroupId();
		long portalGroupId = groupId;
		
		String currentPage = CustomUtil.strNull(params.get("currentPage"), "1");
		String listSize = CustomUtil.strNull(params.get("listSize"), "10");
        String methodName = CustomUtil.strNull(params.get("methodName"));
        String searchValue = CustomUtil.strNull(params.get("searchText"));

		try{
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("listSize", listSize);
	        model.addAttribute("methodName", methodName);
			model.addAttribute("searchText", searchValue);
			
			boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
			model.addAttribute("isRedirect", isRedirect);
			if(isRedirect){
				String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
				String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
				
				model.addAttribute("redirectName", redirectName);
				model.addAttribute("redirectURL", redirectURL);			
				
				//redirectURL decode
				model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			}
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			if(simulationProjectId != 0){
				Group group;
				group = GroupLocalServiceUtil.fetchGroup(portalGroupId);
				if(group.getParentGroupId() != 0){
					portalGroupId = group.getParentGroupId();
				}
				
				model.addAttribute("simulationProjectId", simulationProjectId );
				data = SimulationProjectLocalServiceUtil.getCustomDefaultInfoSimulationProject(simulationProjectId, portalGroupId, themeDisplay.getCompany().getGroupId(), themeDisplay.getLocale());
			}
			
			model.addAttribute("data", data );
			
			//Category 정보 조회
			long companyGroupId = themeDisplay.getCompany().getGroupId();	//전역의 groupId
			AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN); //전역의 GLOBAL_DOMAIN 의 Asset Vocabulary Id 가진 AssetVocabulary 객체를 가지고 온다.
			
			int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary.getVocabularyId());
			Map<Long,List<Map<String,Object>>> childrenCategoryGroupMap =  new HashMap<Long,List<Map<String,Object>>>();
			List<Map<String,Object>> parentCategoryList = new ArrayList<Map<String,Object>>();
			
			if(rootCategoryCnt!=0){
				List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(aVocabulary.getVocabularyId(), -1, -1,null);
				for(AssetCategory rootCatogory : rootCategoryList){
					Map<String,Object> parentCategoryMap = new HashMap<String,Object>();
					parentCategoryMap.put("value", rootCatogory.getCategoryId());
					parentCategoryMap.put("name", rootCatogory.getTitle(themeDisplay.getLocale()));
					parentCategoryList.add(parentCategoryMap);
					
					List<AssetCategory> childCategoryList =  AssetCategoryLocalServiceUtil.getChildCategories(rootCatogory.getCategoryId());
					List<Map<String,Object>> childrenCategoryList = new ArrayList<Map<String,Object>>();
					for(AssetCategory childCatogory : childCategoryList){
						Map<String,Object> childrenCategoryMap = new HashMap<String,Object>();
						childrenCategoryMap.put("value", childCatogory.getCategoryId());
						childrenCategoryMap.put("name", childCatogory.getTitle(themeDisplay.getLocale()));
						childrenCategoryList.add(childrenCategoryMap);
					}
					childrenCategoryGroupMap.put(rootCatogory.getCategoryId(),childrenCategoryList);
				}
			}
			
			model.addAttribute("childrenCategoryGroupMap",childrenCategoryGroupMap);
			model.addAttribute("parentCategoryList",parentCategoryList);			
			
			return "simulationProject/management";
		
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.EVENT_ERROR);
			return "simulationProject/list";
		}
		
	}
	
	/**
	 * 시뮬레이션 프로젝트 생성
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭 
	 */
	@ActionMapping(params="myaction=createSimulationProject")
	public void createSimulationProject(ActionRequest request, ActionResponse response, Model model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map params = RequestUtil.getParameterMap(request);
		
		long groupId = themeDisplay.getScopeGroupId();
		long portalGroupId = groupId;
		long companyId = themeDisplay.getCompanyId();
		User user =  themeDisplay.getUser();
		Group group;
		
		String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
		String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
		
		String currentPage = CustomUtil.strNull(params.get("currentPage"), "1");
		String listSize = CustomUtil.strNull(params.get("listSize"), "10");
        String methodName = CustomUtil.strNull(params.get("methodName"));
        String searchValue = CustomUtil.strNull(params.get("searchText"));
        
		try{
			group = GroupLocalServiceUtil.fetchGroup(portalGroupId);
			
			if(group.getParentGroupId() !=0){
				portalGroupId = group.getParentGroupId();
			}
			
			long simulationProjectId = SimulationProjectLocalServiceUtil.insertCustomSimulationProject(upload, request, portalGroupId, companyId, user, params);
					
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myRender", "management");
			portletURL.setParameter("p_p_state", "maximized");
			portletURL.setParameter("simulationProjectId", String.valueOf(simulationProjectId));
			portletURL.setParameter("redirectName", redirectName);
			portletURL.setParameter("redirectURL", redirectURL);
			portletURL.setParameter("isRedirect", String.valueOf(isRedirect));
			portletURL.setParameter("currentPage", currentPage);
			portletURL.setParameter("listSize", listSize);
	        portletURL.setParameter("methodName", methodName);
			portletURL.setParameter("searchText", searchValue);
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		}catch(Exception e){
			e.printStackTrace();
			PortalUtil.copyRequestParameters(request, response);
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
			
	}
	
	/**
	 * 시뮬레이션 프로젝트 갱신
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭 
	 */
	@ActionMapping(params="myaction=updateSimulationProject")
	public void updateSimulationProject(ActionRequest request, ActionResponse response, Model model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map params = RequestUtil.getParameterMap(request);
		
		long groupId = themeDisplay.getScopeGroupId();
		long portalGroupId = groupId;
		long companyId = themeDisplay.getCompanyId();
		User user =  themeDisplay.getUser();
		Group group;
		
		String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
		String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
		
		String currentPage = CustomUtil.strNull(params.get("currentPage"), "1");
		String listSize = CustomUtil.strNull(params.get("listSize"), "10");
        String methodName = CustomUtil.strNull(params.get("methodName"));
        String searchValue = CustomUtil.strNull(params.get("searchText"));
		
		try{
			group = GroupLocalServiceUtil.fetchGroup(portalGroupId);
			
			if(group.getParentGroupId() !=0){
				portalGroupId = group.getParentGroupId();
			}
			
			long simulationProjectId = SimulationProjectLocalServiceUtil.updateCustomSimulationProject(upload, request, portalGroupId, companyId, user, params);
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myRender", "management");
			portletURL.setParameter("p_p_state", "maximized");
			portletURL.setParameter("simulationProjectId", String.valueOf(simulationProjectId));
			portletURL.setParameter("redirectName", redirectName);
			portletURL.setParameter("redirectURL", redirectURL);
			portletURL.setParameter("isRedirect", String.valueOf(isRedirect));
			portletURL.setParameter("currentPage", currentPage);
			portletURL.setParameter("listSize", listSize);
	        portletURL.setParameter("methodName", methodName);
			portletURL.setParameter("searchText", searchValue);
			
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		}catch(Exception e){
			e.printStackTrace();
			PortalUtil.copyRequestParameters(request, response);
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
			
	}	
	/**
	 * 시뮬레이션 프로젝트 삭제
	 * @param request
	 * @param response 
	 * @param model
	 * @param redirectURL - 암호화된 되돌아가기 URL
	 * @param redirectName - 되돌아갈곳의 명칭  
	 */
	@ActionMapping(params="myaction=deleteSimulationProject")
	public void deleteSimulationProject(ActionRequest request, ActionResponse response, Model model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map params = RequestUtil.getParameterMap(request);
		
		String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
		String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
		String currentPage = CustomUtil.strNull(params.get("currentPage"), "1");
		String listSize = CustomUtil.strNull(params.get("listSize"), "10");
        String methodName = CustomUtil.strNull(params.get("methodName"));
        String searchValue = CustomUtil.strNull(params.get("searchText"));
		
		try{
			long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
			
			SimulationProjectLocalServiceUtil.deleteCustomSimulationProject(simulationProjectId);
					
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("redirectName", redirectName);
			portletURL.setParameter("redirectURL", redirectURL);
			portletURL.setParameter("isRedirect", String.valueOf(isRedirect));
			portletURL.setParameter("currentPage", currentPage);
			portletURL.setParameter("listSize", listSize);
	        portletURL.setParameter("methodName", methodName);
			portletURL.setParameter("searchText", searchValue);
			
			SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
		}catch(Exception e){
			e.printStackTrace();
			PortalUtil.copyRequestParameters(request, response);
			SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
		}
			
	}	

	/**
	 * 시뮬레이션 프로젝트 멤버 리스트
	 * @param request
	 * @param response
	 * @param currentPage - 현재 페이지
	 * @param listSize - 한페이지에 리스트 출력 개수
	 * @param blockSize - 페이징 버튼 최대 출력 개수
	 * @param searchField - 검색어
	 * @param searchRequestState - 신청상태
	 * @throws PortalException
	 * @throws SystemException
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResourceMapping(value="getRequestMemberList")
	public void getRequestMemberList(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		int currentPage = Integer.parseInt(CustomUtil.strNull(params.get("currentPage"), "1"));
		int listSize = Integer.parseInt(CustomUtil.strNull(params.get("listSize"), "5"));
		int blockSize = Integer.parseInt(((PortletRequest) request).getPreferences().getValue("blockSize", "10"));
		int begin = ((currentPage - 1) * listSize);
		int end = listSize;
		
		List<Map<String,Object>> requestMemberList  = new ArrayList<Map<String,Object>>();
		int totalCount = 0;
		Locale locale = themeDisplay.getLocale();
		
		ExpandoTable table;
		long columnId = 0;
		
		table = ExpandoTableLocalServiceUtil.getTable(PortalUtil.getDefaultCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
		columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
			
		String searchText = CustomUtil.strNull(params.get("searchText"));
		String searchRequestState = CustomUtil.strNull(params.get("searchRequestState"));
		long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		
		requestMemberList  = RequestMemberLocalServiceUtil.getCustomRequestMemberList(simulationProjectId, begin, end, searchText, searchRequestState, locale, columnId);
		totalCount = RequestMemberLocalServiceUtil.getCustomRequestMemberCount(simulationProjectId, searchText, searchRequestState, columnId);
		
		String paging = PagingUtil.getPaging(request.getContextPath(), CustomUtil.strNull(params.get("methodName")), totalCount, currentPage, listSize, blockSize);
		
		JSONObject obj = new JSONObject();
		obj.put("requestMemberList", requestMemberList );
		obj.put("seq", totalCount - ((currentPage - 1)*listSize));
		obj.put("currentPage", currentPage);
		obj.put("paging", paging);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}	
	
	/**
	 * 시뮬레이션 프로젝트 맴버 가입요청
	 * @param request
	 * @param response
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @throws PortalException
	 * @throws SystemException
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResourceMapping(value="requestSimulationProjectMember")
	public void requestSimulationProjectMember(ResourceRequest request, ResourceResponse response) throws SystemException, IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		String requestState = "2003001";	//가입요청
		User user = themeDisplay.getUser();
		
		if(simulationProjectId == 0){
			throw new SystemException();
		}
		
		RequestMemberLocalServiceUtil.insertCustomSimulationProjectMemberRequest(user, simulationProjectId, requestState);
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 시뮬레이션 프로젝트 맴버 신청 수정
	 * @param request
	 * @param response
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param requestSeq - 멤버 신청 seq
	 * @param requestState - 변경해야 하는 상태
	 * @throws PortalException
	 * @throws SystemException
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResourceMapping(value="updateSimulationProjectMember")
	public void updateSimulationProjectMember(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		long requestSeq = Long.valueOf(CustomUtil.strNull(params.get("requestSeq"), "0"));
		long requestUserId = Long.valueOf(CustomUtil.strNull(params.get("requestUserId"), "0"));
		String requestState = CustomUtil.strNull(params.get("requestState"));
		User user = themeDisplay.getUser();
		
		if(requestSeq == 0 || simulationProjectId == 0 || "".equals(requestState) ){
			throw new SystemException();
		}
		
		RequestMemberLocalServiceUtil.updateCustomSimulationProjectMemberRequest(requestSeq, simulationProjectId, requestUserId, requestState);
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 시뮬레이션 프로젝트 맴버 신청 삭제
	 * @param request
	 * @param response
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param requestSeq - 멤버 신청 seq
	 * @throws PortalException
	 * @throws SystemException
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResourceMapping(value="deleteSimulationProjectMember")
	public void deleteSimulationProjectMember(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);
		
		long simulationProjectId = Long.valueOf(CustomUtil.strNull(params.get("simulationProjectId"), "0"));
		long requestSeq = Long.valueOf(CustomUtil.strNull(params.get("requestSeq"), "0"));
		
		if(requestSeq == 0 || simulationProjectId == 0 ){
			throw new SystemException();
		}
		
		RequestMemberLocalServiceUtil.deleteCustomSimulationProjectMemberRequest(requestSeq, simulationProjectId);
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}	
	
	/**
	 * 사이언스앱 정보 리스트(앱 관리 팝업)
	 * @param request
	 * @param response
	 * @param scienceAppIdList[] - 사이언스앱 아이디 리스트
	 * @param searchField - 검색어
	 * @throws Exception
	 */
	@ResourceMapping(value="scienceAppList")
	public void getScienceAppList(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		int currentPage = ParamUtil.get(request, "currentPage", 1);
		int listSize = ParamUtil.get(request, "searchLine", 5);
		int blockSize = 10;
		int start = ((currentPage - 1) * listSize);
		
		String searchText = GetterUtil.get(params.get("searchText"), "");
		List<Map<String, Object>> scienceAppList = null;
		
		scienceAppList = SimProScienceAppLinkLocalServiceUtil.getScienceAppList(searchText, themeDisplay.getLocale(), start, listSize);
		
		int totalCnt = SimProScienceAppLinkLocalServiceUtil.getScienceAppListCount(searchText, themeDisplay.getLocale());

		String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
		String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "searchListEntry",
			totalCnt, currentPage, listSize, blockSize);

		
		JSONObject obj = new JSONObject();
		obj.put("scienceAppList", scienceAppList);
		obj.put("paging", paging);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 사이언스앱 정보 리스트(시뮬레이션 프로젝트 관리)
	 * @param request
	 * @param response
	 * @param scienceAppIdList[] - 사이언스앱 아이디 리스트
	 * @throws Exception
	 */
	@ResourceMapping(value="selectScienceAppList")
	public void selectScienceAppList(ResourceRequest request, ResourceResponse response) throws Exception{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String[] scienceAppIdList = request.getParameterValues("scienceAppIdList[]");
		List<Map<String, Object>> scienceAppList = null;
		
		scienceAppList = SimProScienceAppLinkLocalServiceUtil.getScienceAppList(scienceAppIdList, themeDisplay.getLocale());
		
		JSONObject obj = new JSONObject();
		obj.put("scienceAppList", scienceAppList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @param fileEntryId - 파일엔트리 아이디
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
	
	/**
	 * Icon file 삭제
	 * @param request
	 * @param response
	 * @param fileEntryId - 파일엔트리 아이디
	 * @throws PortalException
	 * @throws SystemException
	 */
	@ResourceMapping(value="deleteIconFile")
	public void deleteIconFile(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		Map params = RequestUtil.getParameterMap(request);		
		long fileEntryId = GetterUtil.getLong(params.get("fileEntryId"),0);
		DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
	}

	/**
	 * image file 삭제
	 * @param request
	 * @param response
	 * @param deleteFileEntryId - 파일엔트리 아이디
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param iconId - 아이콘 아이디
	 * @throws PortalException
	 * @throws SystemException
	 */
	@ResourceMapping(value="deleteImageFile")
	public void deleteImageFile(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		long groupId = themeDisplay.getScopeGroupId();
		long portalGroupId = groupId;
		
		long deleteFileEntryId = GetterUtil.getLong(params.get("deleteFileEntryId"),0);
		long simulationProjectId = GetterUtil.getLong(params.get("simulationProjectId"),0);
		long iconId = GetterUtil.getLong(params.get("iconId"),0);
		
		DLFileEntryLocalServiceUtil.deleteDLFileEntry(deleteFileEntryId);
		
		Group group;
		group = GroupLocalServiceUtil.fetchGroup(portalGroupId);
			
		if(group.getParentGroupId() !=0){
			portalGroupId = group.getParentGroupId();
		}
		
		List<Map<String, Object>> imageList = EdisonFileUtil.getListEdisonFile(portalGroupId, "", String.valueOf(simulationProjectId), EdisonFileConstants.SIMULATION_PROJECT);
		List<Map<String, Object>> projectImageList = new ArrayList<Map<String, Object>>();
		Map<String, Object> imageMap;
		
		for(Map<String, Object> image : imageList){
			long fileEntryId = Long.parseLong(CustomUtil.strNull(image.get("fileEntryId"), "0"));
			imageMap = new HashMap<String, Object>();
			if(iconId != fileEntryId){
				imageMap.put("imageId", fileEntryId);
				imageMap.put("imageRepositoryId", image.get("fileRepositoryId"));
				imageMap.put("imageUuid", image.get("fileUuid"));
				imageMap.put("imageTitle", image.get("fileTitle"));
				projectImageList.add(imageMap);
			}
		}
		
		JSONObject obj = new JSONObject();
		obj.put("projectImageList", projectImageList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
}
