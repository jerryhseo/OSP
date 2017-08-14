package org.kisti.edison.science.portlet.mainSearchApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
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
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
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

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class mainSearchAppController {
	
	private static Log log = LogFactory.getLog(mainSearchAppController.class);

	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
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
			if(portalYn.equals("Y")){
				return "mainSearchApp/view";
			} else {
				return "mainSearchApp/site_view";
			}
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			
			return "mainSearchApp/view";
		}
	}
	
	@ResourceMapping(value="bestSolverList")
	public void getBestSolverList(ResourceRequest request, ResourceResponse response)  throws PortalException, SystemException, MalformedURLException, IOException, SQLException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();
		String portalYn = ParamUtil.get(request, "portalYn", "N");
		
		String tabUseStr = request.getPreferences().getValue("tabUseList", "");
		
		List<Map<String, Object>> bestSolverList = new ArrayList<Map<String, Object>>();
		
		//CFD 	 = 23212
		//NANO   = 23318
		//CHEM   = 23563
		//CSD    = 23706
		//DESUGN = 23849
		Map<Long, long[]> bestSolverMap = new HashMap<Long, long[]>();
		//운영
		/*bestSolverMap.put((long)23212, new long[]{11101,11301});
		bestSolverMap.put((long)23318, new long[]{30001,34901});
		bestSolverMap.put((long)23563, new long[]{21301,20901});
		bestSolverMap.put((long)23706, new long[]{15401,14501});
		bestSolverMap.put((long)23849, new long[]{13301,12901});*/
		//개발
        bestSolverMap.put((long)23212, new long[]{3701,3901});
		bestSolverMap.put((long)23318, new long[]{25501,25601});
		bestSolverMap.put((long)23563, new long[]{16901,17001});
		bestSolverMap.put((long)23706, new long[]{13901,14001});
		bestSolverMap.put((long)23849, new long[]{12101,12201});
		
		
		if(portalYn.equals("Y")){//포탈일때
			List<com.liferay.portal.model.Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
			Group parentGroup = null;
			for(Group group:parentGroupList){
				if(StringUtil.toUpperCase(group.getName()).equals("GUEST")){
					parentGroup = group;
					break;
				}
			}
			
			long visitGroupId = 0;
			if(themeDisplay.isSignedIn()){	// 로그인 했을 경우에만 실행
				String visitSite = CustomUtil.strNull(themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString(),"");
				List<Group> groupListOrigin = parentGroup.getChildren(true);
				for (Group group : groupListOrigin) {
					if(group.getName().equals(visitSite)) {
						visitGroupId = group.getGroupId();	// 관심 분야의 groupId를 찾는다
					}
				}
			}
			
			if(visitGroupId > 0) {
				List<Map<String, Object>> hotSearchList = ScienceAppLocalServiceUtil.retrieveListHotScienceApp(themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId(), 
						themeDisplay.getLocale(), (long[])bestSolverMap.get(visitGroupId), 0, 2);
				bestSolverList.addAll(hotSearchList);
			}
			
			String []tabUseArray = tabUseStr.split(",");
			
			for(int i=0;i<tabUseArray.length;i++){
				
				Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
				Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
				if(bestSolverMap.get(group.getGroupId()) != null){
					List<Map<String, Object>> hotSearchList = ScienceAppLocalServiceUtil.retrieveListHotScienceApp(themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId(), 
							themeDisplay.getLocale(), (long[])bestSolverMap.get(group.getGroupId()), 0, 2);
					
					bestSolverList.addAll(hotSearchList);
				}
			}
			
			
		}else if(portalYn.equals("N")){//사이트일때
			List<Map<String, Object>> hotSearchList = ScienceAppLocalServiceUtil.retrieveListHotScienceApp(themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId(), 
					themeDisplay.getLocale(), (long[])bestSolverMap.get(themeDisplay.getScopeGroupId()), 0, 5);
			bestSolverList.addAll(hotSearchList);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("bestSolverList", bestSolverList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		
	}
}
