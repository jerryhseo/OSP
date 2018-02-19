package org.kisti.edison.search.portlet.searchbox;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.search.portlet.SearchController;
import org.kisti.edison.search.service.model.SearchCondition;
import org.kisti.edison.search.service.model.impl.SearchConditionModelImpl;
import org.kisti.edison.search.service.service.SearchConditionLocalServiceUtil;
import org.kisti.edison.search.service.service.SearchLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.permission.LayoutPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

@Controller
@RequestMapping("VIEW")
public class SearchBoxController {
    private static Log log = LogFactory.getLog(SearchController.class);

    @RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model, @ModelAttribute SearchConditionModelImpl searchConditionModel) throws PortalException, SystemException{
        log.debug("view rendering");
        
        try {
        	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long companyId = themeDisplay.getCompanyId();
		
			long parentGroupId = 0;
			Map params = RequestUtil.getParameterMap(request);
			
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			
			String visitSite ="";
			
			String portalYn = request.getPreferences().getValue("portalYn", "");
		
			parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();
			List<com.liferay.portal.model.Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
			
			//하위 사이트 존재 여부
			boolean isChildrenSite = false;
			if(GroupLocalServiceUtil.getGroups(companyId, PortalUtil.getScopeGroupId(request), true).size()>0){
				isChildrenSite = true;
			}
			
			
			//if(parentGroupId == 0 && isChildrenSite) {
			if(portalYn.equals("Y")){//포탈이면
				
				Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/project-download");
				boolean layoutViewPermission = LayoutPermissionUtil.contains(PermissionCheckerFactoryUtil.create(themeDisplay.getUser(), true), layout, ActionKeys.VIEW);
				PortletURL classURL = PortletURLFactoryUtil.create(request, "", layout.getPlid(), PortletRequest.RENDER_PHASE);
				
				
				model.addAttribute("projectDownloadURL", classURL);
				
				
				String searchGroupId =CustomUtil.strNull(params.get("search_groupId"));
				
				Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
				model.addAttribute("visitSite", Long.toString(defaultGroup.getGroupId()));
				
				if(themeDisplay.isSignedIn()){
					visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
				}else{
					model.addAttribute("visitSite", CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) ));
				}
				
				String groupName = "";
				int groupCnt = 0;
				List<Group> groupId = null;

				Map<String,Long> GroupMap = new HashMap<String,Long>();
				if(!tabUseStr.equals("")){
					String []tabUseArray = tabUseStr.split(",");
					
					groupId = new ArrayList<Group>();					
					for(int i=0;i<tabUseArray.length;i++){
						
						Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
						Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
						GroupMap.put(group.getName(), group.getGroupId());
						
						groupId.add(group);
					}
				}
				model.addAttribute("groupId", groupId);
				
				/*다른 곳에서 search_groupId 파라미터가 넘어 올경우 최종적으로 searchGroupId 값을 셋팅*/
				if(!searchGroupId.equals("")){
					model.addAttribute("visitSite", searchGroupId);
				}
				
				net.sf.json.JSONObject json = new net.sf.json.JSONObject();
				json.putAll(GroupMap);
				model.addAttribute("groupMap", json.toString());
			} else { //포탈이 아닌 경우
				
				String groupName = themeDisplay.getScopeGroup().getName();
				
				if(groupName != null){
				}else{
					groupName = CustomUtil.strNull(themeDisplay.getScopeGroup().getExpandoBridge().getAttribute("siteName"));
				}
				model.addAttribute("groupName", groupName);
			}
			
			//workflowUrl Create
			if(themeDisplay.isSignedIn()){
				long workflowPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonworkflow_WAR_edisonworkflow2016portlet");
				PortletURL workflowUrl = PortletURLFactoryUtil.create(request,"edisonworkflow_WAR_edisonworkflow2016portlet", workflowPlid, PortletRequest.RENDER_PHASE);
				workflowUrl.setWindowState(LiferayWindowState.MAXIMIZED);
				
				model.addAttribute("workflowUrl", workflowUrl);
			}
			
			/*통합검색*/
			long searchPlid = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/search").getPlid();
			PortletURL searchUrl = PortletURLFactoryUtil.create(request,"edisonsearch_WAR_edisonsearch2017portlet", searchPlid, PortletRequest.RENDER_PHASE);
			model.addAttribute("searchURL", searchUrl);
			System.out.println("searchUrl : " + model.get("searchURL"));
			
			long appStorePlid = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/scienceappstore").getPlid();
			PortletURL appStoreUrl = PortletURLFactoryUtil.create(request,"edisonsearch_WAR_edisonsearch2017portlet", appStorePlid, PortletRequest.RENDER_PHASE);
			appStoreUrl.setWindowState(LiferayWindowState.MAXIMIZED);
			model.addAttribute("appStoreURL", appStoreUrl);
			model.addAttribute("appstorePlid", appStorePlid);
			model.addAttribute("portalYn", portalYn);
			
			String redirectName = "Home";
			String redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
			
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			
			String communityURL = themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic();
			model.addAttribute("communityURL", communityURL);

			// parentGroupId가 0이면 포털 0이 아니면 분야 사이트
			return "search/searchBox";
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			
			return "search/searchBox";
		}
        
    }
    
    /* TODO search 기능 구현 */
    
    @ResourceMapping(value = "getEncodedUrl")
    @ResponseBody
    public void getEncodedUrl(
        @RequestParam( value = "currentUrl", required = true) String currentUrl, 
        ResourceRequest request, ResourceResponse response, ModelMap model) throws IOException, PortalException, SystemException{
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        currentUrl += SearchConditionLocalServiceUtil.createSearchParameters(
            searchCondition, response.getNamespace(), PortalUtil.getPortletId(request));
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(EdisonHttpUtil.removeAndencodeURL(currentUrl));
        writer.flush();
        writer.close();
    }
    
    @ResourceMapping(value = "totalSearch")
    public String totalSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("view rendering");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = PortalUtil.getScopeGroupId(request);
        
        long appstorePlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisonscienceAppstore_WAR_edisonappstore2016portlet");
        long contentsPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisoncontent_WAR_edisoncontent2016portlet");
        long simulationProjectPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisonsimulationproject_WAR_edisonsimulationproject2017portlet");
        long openDataPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisondatacollection_WAR_edisonsimulationportlet");
        long workBenchPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "Workbench_WAR_OSPWorkbenchportlet");
        
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        try{
            model.addAttribute("searchResults", SearchLocalServiceUtil.totalSearch(searchCondition));
        }catch (Exception e){
            throw new SystemException(e);
        }
        model.addAttribute("isSignedIn", themeDisplay.isSignedIn());
        model.addAttribute("workBenchPlid", workBenchPlid);
        model.addAttribute("appstorePlid", appstorePlid);
        model.addAttribute("contentsPlid", contentsPlid);
        model.addAttribute("simulationProjectPlid", simulationProjectPlid);
        model.addAttribute("openDataPlid", openDataPlid);
        model.addAttribute("groupId", groupId);
        return "search/total";
    }
}
