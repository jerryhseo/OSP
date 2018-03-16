package org.kisti.edison.bestsimulation.portlet.statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.ExcelUtil;
import org.kisti.edison.util.HtmlFormUtils;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
public class EdisonStatisticsExecController {

	private static Log log = LogFactoryUtil.getLog(EdisonStatisticsExecController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, WindowStateException {
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
//										model.addAttribute("visitSite", Long.toString(group.getGroupId()));
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

			model.addAttribute("pageTitle", themeDisplay.getLayout().getName(themeDisplay.getLocale()));
			model.addAttribute("isPortalMain", isPortalMain);
			model.addAttribute("visitSite", visitSite);
			model.addAttribute("preDay", preDay);
			model.addAttribute("toDay", toDay);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return "statistics/exec"; 
	}

	@ResourceMapping(value="getStatisticsExec")
	public void getStatisticsExec(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		String startDt = CustomUtil.strNull(params.get("startDt"));
		String endDt = CustomUtil.strNull(params.get("endDt"));
		
		List tableOrganigationList  = UniversityExecuteLocalServiceUtil.getStatisticsExecTableOrganigation(themeDisplay.getLocale(), startDt, endDt);
		List barChartDateList  = UniversityExecuteLocalServiceUtil.getStatisticsExecBarChartDate(startDt, endDt);
		
		JSONObject obj = new JSONObject();
		obj.put("tableOrganigationList", tableOrganigationList);
		obj.put("pieChartOrganigationList", tableOrganigationList);
		obj.put("barChartDateList", barChartDateList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}	
	
	@ResourceMapping(value="excelDown")
	public void excelDown(ResourceRequest request, ResourceResponse response){
		Map params = RequestUtil.getParameterMap(request);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		try {		
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	
			String startDt = CustomUtil.strNull(params.get("startDt"));
			String endDt = CustomUtil.strNull(params.get("endDt"));
	
			List tableOrganigationList  = UniversityExecuteLocalServiceUtil.getStatisticsExecTableOrganigation(themeDisplay.getLocale(), startDt, endDt);
			
			String university = LanguageUtil.get(themeDisplay.getLocale(), "edison-create-account-field-title-university");
			String studentNum = LanguageUtil.get(themeDisplay.getLocale(), "edison-statistics-user-count");
			String runningTime = LanguageUtil.get(themeDisplay.getLocale(), "edison-simulation-monitoring-table-header-averate-running-time");
			String execCnt = LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-view-Execution-count");
			
			
			String[] logical_names = {
					university,		studentNum,		runningTime,		execCnt,		"CPU Time"
			};
			int[] widths = {
					30,				15,				20,					15,				20
			};
			String[] physical_names = {
					"affiliation",	"userCnt",			"averageRuntime",	"jobCnt",		"cputime"
			};

			String downFileName = "RunStatistics_"+CustomUtil.dateToStringFormat(new Date(), "yyyy.MM.dd");		
			ExcelUtil.excelDownload(httpResponse, logical_names, physical_names, widths, downFileName, tableOrganigationList);
			
		} catch (PortalException e) {
			log.error("PortalException");
		} catch (SystemException e) {
			log.error("SystemException");
		} catch (ParseException e) {
			log.error("ParseException");
		} catch (IOException e) {
			log.error("IOException");
		}
	}
	
	
	/**
	 * SELECT 박스 조회
	 * @param request
	 * @param response
	 * @throws ParseException 
	 * @throws SystemException 
	 * @throws PortalException 
	 * @throws NumberFormatException 
	 * @throws JSONException 
	 * @throws IOException
	 * @throws SQLException
	 */
	@ResourceMapping(value ="searchSelect" )
	public void searchSelect(ResourceRequest request, ResourceResponse response) throws NumberFormatException, PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List dataList = new ArrayList();
		
		String status = CustomUtil.strNull(params.get("status"));
		long groupId = Long.parseLong(CustomUtil.strNull(params.get("visitSite"), "0"));
		
		if(status.equals("uni")){
			dataList = (List) SimulationJobLocalServiceUtil.getListVirtualLabSearchUni(groupId, themeDisplay.getLanguageId(), Long.parseLong(CustomUtil.strNull(params.get("jobUniversityField"), "0")));
		}else if(status.equals("cyb")){
			dataList = (List) SimulationJobLocalServiceUtil.getListVirtualClassSearchLab(groupId, themeDisplay.getLanguageId(), Long.parseLong(CustomUtil.strNull(params.get("jobVirtualLabId"), "0")));
		}

		JSONObject obj = new JSONObject();
		
		obj.put("dataList", dataList);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}	
}
