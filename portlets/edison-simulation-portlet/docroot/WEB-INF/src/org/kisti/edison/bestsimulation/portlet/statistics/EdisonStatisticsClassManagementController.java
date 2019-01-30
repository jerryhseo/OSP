package org.kisti.edison.bestsimulation.portlet.statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.ExcelUtil;
import org.kisti.edison.util.HtmlFormUtils;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
public class EdisonStatisticsClassManagementController {

	private static Log log = LogFactoryUtil.getLog(EdisonStatisticsClassManagementController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response,ModelMap model){
		
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			
			String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
			int preYear = Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyy"));
			String preDay = (preYear-1)+"-01-01";
			
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			long selectedGroupId = 0;
			
			if(tabViewYn.equals("Y")){
				String visitSite = "";
				
				//User Expando 값 가지고 오기
				if(themeDisplay.isSignedIn()){
					visitSite = CustomUtil.strNull(themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE));
				}
				
				if(visitSite.equals("")){
					visitSite = "CFD";
				}
				
				Map<String,Long> GroupMap = new HashMap<String,Long>();
				if(!tabUseStr.equals("")){
					String groupName = "";
					int groupCnt = 0;
					String groupId = "";
					
					String []tabUseArray = tabUseStr.split(",");
					for(int i=0;i<tabUseArray.length;i++){
						Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
						Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
						GroupMap.put(group.getName(), group.getGroupId());
						
						if(StringUtil.toUpperCase(group.getName()).equals("GUEST")){
							group.setName("EDISON");
						}
						
						if(groupCnt==0){
							groupName += group.getName();
							groupId += group.getGroupId();
							groupCnt++;
						}else{
							groupName += ","+group.getName();
							groupId += ","+group.getGroupId();
						}
						
						if(visitSite.equals(group.getName())){
							selectedGroupId = group.getGroupId();
							
						}
					}
					
					model.addAttribute("tabNames", groupName);
					model.addAttribute("tabsValues", groupId);
					model.addAttribute("tabViewYn", true);
				}
				
				model.addAttribute("visitSite", visitSite);
				model.addAttribute("tabViewYn", true);
			}else{
				selectedGroupId = themeDisplay.getScopeGroupId();
				model.addAttribute("tabViewYn", false);
			}
			
			//기관 코드 조회
			model.addAttribute("orgCodes", EdisonExpndoUtil.getCodeListByUpCode(1501, themeDisplay.getLocale()));
			
			model.addAttribute("selectedGroupId", selectedGroupId);
			model.addAttribute("pageTitle", themeDisplay.getLayout().getName(themeDisplay.getLocale()));
			model.addAttribute("preDay", preDay);
			model.addAttribute("toDay", toDay);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
	
		return "statistics/classManagement";
	}
	
	//탭 이벤트 저장
	@ResourceMapping(value ="cickTab")
	public void fileList(ResourceRequest request, ResourceResponse response){
		try{
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.getLong(request, "groupId",0);
			if(groupId!=0){
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
			}
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value ="classStatisticsList" ) //하위사이트 groupId로 각 리스트 가져오기
	public void classStatisticsList(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			
			params.put("languageId", themeDisplay.getLocale().toString());
		
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("selectedGroupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long userId = PortalUtil.getUserId(request);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			params.put("userId", userId);
			params.put("groupId", groupId);
			params.put("universityCode", CustomUtil.strNull(params.get("universityCode")));
			
			/*List<Map<String, Object>> statisticsDataList = SimulationJobLocalServiceUtil.getVirtualClassStatisticsList(params, themeDisplay.getLocale(), false);*/
			
			// get VirtualClassStatisticsList in Statistics Table
			List<Map<String, Object>> virtualLabClassStatisticsList = SimulationJobLocalServiceUtil.getVirtualLabClassStatisticsList(params, themeDisplay.getLocale(), false);
			System.out.println("virtualLabClassStatisticsList : " + virtualLabClassStatisticsList.toString());
			
			JSONObject obj = new JSONObject();
			obj.put("dataList", virtualLabClassStatisticsList);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value ="updateClassStatistics" ) //하위사이트 groupId로 각 리스트 가져오기
	public void updateClassStatistics(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			System.out.println("1...updateClassStatistics");
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			
			String checkedLabIds = CustomUtil.strNull(params.get("checkedLabIds"), "");
			String[] checkedLabIdsArr = checkedLabIds.split(",");
			System.out.println("checkedLabIdsArr length : " + checkedLabIdsArr.length);
			
			for(String checkedLabId : checkedLabIdsArr){
				Map sendParams = new HashMap();
				sendParams.put("labId", checkedLabId);
				SimulationJobLocalServiceUtil.executeSchedulerOfClassStatistics(sendParams);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
