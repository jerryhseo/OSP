package org.kisti.edison.bestsimulation.portlet.statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.search.service.service.SearchLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.ExcelUtil;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.permission.LayoutPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class EdisonStatisticsAppExecController {

	private static Log log = LogFactoryUtil.getLog(EdisonStatisticsExecController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, WindowStateException {
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long companyId = themeDisplay.getCompanyId();
			long companyGroupId = themeDisplay.getCompany().getGroupId();

			String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
			int preYear = Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyy"));
			String preDay = (preYear-1)+"-01-01";
			
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			
			String visitSite ="";		
			Group thisGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getSiteGroupId());
			long thisGoupId = PortalUtil.getScopeGroupId(request);
			String groupId = "";
			boolean isPortalMain = true;		
			
			Locale locale = themeDisplay.getLocale();
			
			//사이언스앱 상세보기 -> SW통계
			String appExecGroupId = CustomUtil.strNull(params.get("appExecGroupId"));
			if(!"".equals(appExecGroupId)){
				tabViewYn = "N";
				visitSite = appExecGroupId;
			}
			
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
							visitSite = CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) );
						}
						
						String groupName = "";
						int groupCnt = 0;
						
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
					
				}//end if(thisGroup.getParentGroupId() > 0){
			}else{
				//상세 사이트 일경우 Tab Not View
				isPortalMain = false;
				if(!"".equals(appExecGroupId)){
					visitSite = appExecGroupId;
				}else{
					visitSite = Long.toString(PortalUtil.getScopeGroupId(request));
				}
			}//else if(tabViewYn.equals("Y")){
			
			model.addAttribute("isPortalMain", isPortalMain);
			model.addAttribute("visitSite", visitSite);
			model.addAttribute("preDay", preDay);
			model.addAttribute("toDay", toDay);
			
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			if(!"".equals(scienceAppName)){
				model.addAttribute("scienceAppName", scienceAppName);
			}
			
			AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
			
			/* default thisGroupId = 23212(=CFD) */
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), ParamUtil.getLong(request, "thisGroupId",23212));
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
			
			String categoryStr = "";
			List<Map<String, Object>> parentCategoryList = new ArrayList<Map<String, Object>>() ;
			List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>() ;
			
			//parentCategory 찾기
			for(AssetCategory aCategory : aCategoryList){
				if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()&& aCategory.getParentCategoryId() == 0){
					Map<String, Object> aCategoryMap = new HashMap<String, Object>();
					aCategoryMap.put("categoryId", aCategory.getCategoryId());
					aCategoryMap.put("categoryTitle", aCategory.getTitle(themeDisplay.getLocale()));
					aCategoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					parentCategoryList.add(aCategoryMap);
				}
			}
				
			for(AssetCategory aCategory : aCategoryList){
				if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()&& aCategory.getParentCategoryId() != 0){
					Map<String, Object> aCategoryMap = new HashMap<String, Object>();
					aCategoryMap.put("categoryId", aCategory.getCategoryId());
					aCategoryMap.put("categoryTitle", aCategory.getTitle(themeDisplay.getLocale()));
					aCategoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					categoryList.add(aCategoryMap);
				}
			}
			
			String categoriesJsonString = SearchLocalServiceUtil.getCategoriesJsonString(companyGroupId, thisGoupId, locale);
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("categoriesJsonString", categoriesJsonString);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "statistics/swExe"; 
	}

	@ResourceMapping(value="getStatisticsSwExe")
	public void getStatisticsSwExe(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
			Map params = RequestUtil.getParameterMap(request);

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	
			String startDt = CustomUtil.strNull(params.get("startDt"));
			String endDt = CustomUtil.strNull(params.get("endDt"));
			String visiteSiteStr = CustomUtil.strNull(params.get("visitSite"), "0");
			if(StringUtil.toUpperCase(visiteSiteStr).equals("GUEST")){
				// Defailt visiteSite is CFD
				visiteSiteStr = "23212";
			}
			long visitSite = Long.parseLong(visiteSiteStr);
			long groupId = themeDisplay.getScopeGroupId();
			Group group = GroupLocalServiceUtil.getGroup(visitSite);
			ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(group.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			//long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			long parentGroupId = 0l;
			String parentGroupIdStr = CustomUtil.strNull(params.get("parentGroupId"));
			if(parentGroupIdStr.equals("") || parentGroupIdStr == null){
				parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			} else {
				parentGroupId = Long.parseLong(parentGroupIdStr);
			}
			long categoryId = Long.parseLong(CustomUtil.strNull(params.get("categoryId"), "0"));
			
			//모든 버전 App 
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
			
			if(!"".equals(scienceAppName)){
				if(parentGroupId == 0){//포탈
					scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), visitSite, themeDisplay.getLocale(), scienceAppName, true);
				}else{
					scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), groupId, themeDisplay.getLocale(), scienceAppName, true);
				}
			}
			
			List tableOrganigationList = new ArrayList();
			List barChartDateList = new ArrayList();
			if(0 < scienceAppList.size()){
				if(parentGroupId == 0){//포탈
					tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, visitSite, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, 0);
					barChartDateList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeBarChartDate(companyGroupId, visitSite, columnId, startDt, endDt, scienceAppList, true, 0);
				}else if(parentGroupId != 0 && !"".equals(scienceAppName)){
					/* 카테고리 선택 시 해당  카테고리의 통계 정보 출력 */
					tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, groupId, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, categoryId);
					barChartDateList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeBarChartDate(companyGroupId, groupId, columnId, startDt, endDt, scienceAppList, true, categoryId);
				}else{
					tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, groupId, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, 0);
					barChartDateList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeBarChartDate(companyGroupId, groupId, columnId, startDt, endDt, scienceAppList, true, 0);
				}
			}
			
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
			long visitSite = Long.parseLong(CustomUtil.strNull(params.get("visitSite"), "0"));
			long groupId = themeDisplay.getScopeGroupId();
			Group group = GroupLocalServiceUtil.getGroup(visitSite);
			ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(group.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			
			//모든 버전 App 
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
			if(!"".equals(scienceAppName)){
				if(parentGroupId == 0){//포탈
					scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), visitSite, themeDisplay.getLocale(), scienceAppName, true);
				}else{
					scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), groupId, themeDisplay.getLocale(), scienceAppName, true);
				}
			}
			
			List tableOrganigationList = new ArrayList();
			
			if(parentGroupId == 0){//포탈
				tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, visitSite, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, 0);
			}else{
				tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, groupId, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, 0);
			}
			
			String appName = LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-solver-name");
			String affiliate = LanguageUtil.get(themeDisplay.getLocale(), "edison-create-account-field-title-university");
			String owner = LanguageUtil.get(themeDisplay.getLocale(), "edison-virtuallab-owner");
			String scienceAppVersion = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-version");
			String userCnt = LanguageUtil.get(themeDisplay.getLocale(), "edison-statistics-user-count");
			String avgTime = LanguageUtil.get(themeDisplay.getLocale(), "edison-simulation-monitoring-table-header-averate-running-time");
			String exeCnt = LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-view-Execution-count");
			String regDt = LanguageUtil.get(themeDisplay.getLocale(), "edison-table-list-header-date");
			
			String[] logical_names = {
					appName,		affiliate,		owner,		scienceAppVersion,		userCnt,		avgTime+" (sec)",		exeCnt, 	regDt
			};
			int[] widths = {
					50,				30,				20,				15,				20,				30,					20, 		20
			};
			String[] physical_names = {
					"scienceApp_name",	"scienceApp_affiliation_name",	"mgtName",	"scienceApp_version",	"user_count",	"averageRuntime",	"exe_count", "mgtDate"
			};

			String downFileName = "SwExeStatistics_"+CustomUtil.dateToStringFormat(new Date(), "yyyy.MM.dd");		
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
	
		
}
