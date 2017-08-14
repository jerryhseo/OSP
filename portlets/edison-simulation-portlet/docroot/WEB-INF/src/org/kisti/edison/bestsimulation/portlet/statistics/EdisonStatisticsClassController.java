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
public class EdisonStatisticsClassController {

	private static Log log = LogFactoryUtil.getLog(EdisonStatisticsClassController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response,ModelMap model){
		
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long companyId = themeDisplay.getCompanyId();

			String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
			int preYear = Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyy"));
			String preDay = (preYear-1)+"-01-01";
			
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			
			String visitSite ="";		
			Group thisGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getSiteGroupId());			
			boolean isPortalMain = true;		
			
			if(tabViewYn.equals("Y")){
				
				Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/project-download");
				boolean layoutViewPermission = LayoutPermissionUtil.contains(PermissionCheckerFactoryUtil.create(themeDisplay.getUser(), true), layout, ActionKeys.VIEW);
				PortletURL classURL = PortletURLFactoryUtil.create(request, "", layout.getPlid(), PortletRequest.RENDER_PHASE);
				
				model.addAttribute("projectDownloadURL", classURL);
				
				String searchGroupId =CustomUtil.strNull(params.get("search_groupId"));
				
								
				if(thisGroup.getParentGroupId() > 0){//child site
					isPortalMain = false;
					visitSite = String.valueOf(themeDisplay.getSiteGroupId());
				}else{
					
					//하위 사이트 존재 여부
					boolean isChildrenSite = false;
					if(GroupLocalServiceUtil.getGroups(companyId, PortalUtil.getScopeGroupId(request), true).size()>0){
						isChildrenSite = true;
					}
					
					if(isChildrenSite){
						long selectGroupId = ParamUtil.getLong(request, "thisGroupId",0);				
						Group selectGroup = null;
						
						if(selectGroupId == 0){
							selectGroup = thisGroup;
						}else{
							selectGroup = GroupLocalServiceUtil.getGroup(selectGroupId);	
						}
	
						if(selectGroupId != 0){
							Group group = GroupLocalServiceUtil.getGroup(selectGroupId);
							themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,selectGroup.getName());				
						}
						
						//TABS VALUE CREATE
						List<com.liferay.portal.model.Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
						Group parentGroup = null;
						for(Group group:parentGroupList){
							if(group.getName().toUpperCase().equals("GUEST")){
								parentGroup = group;
								break;
							}
						}
						
						Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
						model.addAttribute("visitSite", Long.toString(defaultGroup.getGroupId()));
						
						if(themeDisplay.isSignedIn()){
							visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
						}else{
							model.addAttribute("visitSite", CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) ));
						}
						
						String groupName = "";
						int groupCnt = 0;
						String groupId = "";
						
						
						Map<String,Long> GroupMap = new HashMap<String,Long>();
						if(!tabUseStr.equals("")){
							String []tabUseArray = tabUseStr.split(",");
							for(int i=0;i<tabUseArray.length;i++){
								
								Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
								Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
								GroupMap.put(group.getName(), group.getGroupId());
						
								if(groupCnt==0){
									groupName += group.getName();
									groupId += group.getGroupId();
									groupCnt++;
								}else{
									groupName += ","+group.getName();
									groupId += ","+group.getGroupId();
								}
								
								if(!visitSite.equals("")&&visitSite.equals(group.getName())){
									visitSite = Long.toString(group.getGroupId());
								}
							}
							
						}
						
						model.addAttribute("tabNames", groupName);
						model.addAttribute("tabsValues", groupId);
					}else{
						isPortalMain = false;
						visitSite = Long.toString(PortalUtil.getScopeGroupId(request));
					}
					
				}
			}else{
				//상세 사이트 일경우 Tab Not View
				isPortalMain = false;
				visitSite = Long.toString(PortalUtil.getScopeGroupId(request));
			}

			model.addAttribute("isPortalMain", isPortalMain);
			model.addAttribute("visitSite", visitSite);
			model.addAttribute("preDay", preDay);
			model.addAttribute("toDay", toDay);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
	
		return "statistics/class";
	}
	
	//탭 이벤트 저장
	@ActionMapping(params="myaction=saveClickTabSatis")
	public void saveClickTab(ActionRequest request, ActionResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.getLong(request, "groupId",0);
			
			if(themeDisplay.isSignedIn()) {
				if(groupId!=0){
					Group group = GroupLocalServiceUtil.getGroup(groupId);
					themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
				}
			}
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonstatisticsclass_WAR_edisonsimulationportlet");
			PortletURL url = PortletURLFactoryUtil.create(request, "edisonstatisticsclass_WAR_edisonsimulationportlet", plid, "");
			url.setParameter("groupId", CustomUtil.strNull(groupId));
			response.sendRedirect(url.toString());
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
	}
	
	@ResourceMapping(value ="classStatisticsList" ) //하위사이트 groupId로 각 리스트 가져오기
	public void classStatisticsList(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			
			params.put("languageId", themeDisplay.getLocale().toString());
		
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("visitSite"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long userId = PortalUtil.getUserId(request);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			params.put("userId", userId);
			params.put("groupId", groupId);

			List<Map<String, Object>> statisticsDataList = SimulationJobLocalServiceUtil.getVirtualClassStatisticsList(params, themeDisplay.getLocale(), false);
			
			JSONObject obj = new JSONObject();
			obj.put("dataList", statisticsDataList);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="classStatisticsExcelDownLoad")
	public void surveyExcelDownLoad(ResourceRequest request, ResourceResponse response)  throws IOException{
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		
		try {
			Map params = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("visitSite"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			params.put("groupId", groupId);
			params.put("languageId", themeDisplay.getLocale().toString());
			
			List resultList = SimulationJobLocalServiceUtil.getVirtualClassStatisticsList(params, themeDisplay.getLocale(), true);
			
			String affiliate = LanguageUtil.get(themeDisplay.getLocale(), "edison-create-account-field-title-university");
			String classTitle = LanguageUtil.get(themeDisplay.getLocale(), "edison-virtuallab-tablerow-virtualclass");
			String professor = LanguageUtil.get(themeDisplay.getLocale(), "edison-virtuallab-tablerow-professor");
			String scienceApp = LanguageUtil.get(themeDisplay.getLocale(), "edison-class-statistics-sw-data");
			String executeStudent = LanguageUtil.get(themeDisplay.getLocale(), "edison-class-statistics-execute-student-count");
			String classCode = LanguageUtil.get(themeDisplay.getLocale(), "edison-class-statistics-class-code");
			String executeCnt = LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-view-Execution-count");
			
			
			String[] logical_names = {
					affiliate,		classTitle,			professor,		scienceApp, 
					executeStudent,		classCode,	 	executeCnt, 	"CPU Time"
			};
			int[] widths = {
					20,				30,				10,					100,
					10,				10,					10,			10
			};
			
			String[] physical_names = {
					"university",	"classTitle",	"virtualLabPersonName",	"scienceAppTitle",
					"executeStudentcount",	"classId",	"executeCount",	"avgerageRuntime"
			};
	
			String downFileName = "VirtualClassStatistics_"+CustomUtil.dateToStringFormat(new Date(), "yyyy.MM.dd");		
			
			ExcelUtil.excelDownload(httpResponse, logical_names, physical_names, widths, downFileName, resultList);
			//The method makeHSSFWorkbook(String[], String[], int[], String, List)
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
