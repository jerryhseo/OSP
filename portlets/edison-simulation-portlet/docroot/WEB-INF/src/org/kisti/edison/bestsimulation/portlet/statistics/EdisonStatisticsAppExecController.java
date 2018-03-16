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
import org.kisti.edison.util.EdisonExpndoUtil;
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
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryPropertyLocalServiceUtil;
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
			long companyGroupId = themeDisplay.getCompany().getGroupId();

			String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
			int preYear = Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyy"));
			String preDay = (preYear-1)+"-01-01";
			
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			
			if(!scienceAppName.equals("")){
				//APP 상세 보기
				model.addAttribute("scienceAppName", scienceAppName);
				model.addAttribute("tabViewYn", false);
			}else{
				//LIST
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
								model.addAttribute("selectedGroupId", Long.toString(group.getGroupId()));
							}
						}
						
						model.addAttribute("tabNames", groupName);
						model.addAttribute("tabsValues", groupId);
						model.addAttribute("tabViewYn", true);
					}
					model.addAttribute("visitSite", visitSite);
				}else{
					selectedGroupId = themeDisplay.getScopeGroupId();
					model.addAttribute("selectedGroupId", themeDisplay.getScopeGroupId());
					model.addAttribute("tabViewYn", false);
				}
				
				AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
				
				/* default thisGroupId = 23212(=CFD) */
				AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), selectedGroupId);
				List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
				
				List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>() ;
				for(AssetCategory aCategory : aCategoryList){
					if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()&& aCategory.getParentCategoryId() != 0){
						Map<String, Object> aCategoryMap = new HashMap<String, Object>();
						aCategoryMap.put("categoryId", aCategory.getCategoryId());
						aCategoryMap.put("categoryTitle", aCategory.getTitle(themeDisplay.getLocale()));
						aCategoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
						AssetCategoryProperty assetCategoryProperty = AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(aCategory.getCategoryId(), "IMAGE");
						aCategoryMap.put(assetCategoryProperty.getKey().toLowerCase(), assetCategoryProperty.getValue());
						categoryList.add(aCategoryMap);
					}
				}
				
				model.addAttribute("categoryList", categoryList);
				
				//기관 코드 조회
				model.addAttribute("orgCodes", EdisonExpndoUtil.getCodeListByUpCode(1501, themeDisplay.getLocale()));
			}
			
			model.addAttribute("pageTitle", themeDisplay.getLayout().getName(themeDisplay.getLocale()));
			model.addAttribute("preDay", preDay);
			model.addAttribute("toDay", toDay);
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
			
			long selectedGroupId = Long.parseLong(CustomUtil.strNull(params.get("selectedGroupId"), "0"));
			long categoryId = Long.parseLong(CustomUtil.strNull(params.get("categoryId"), "0"));
			
			long companyGroupId = themeDisplay.getCompanyGroupId();
			
			long groupId = 0;
			
			ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(themeDisplay.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
			
			//모든 버전 App 
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
			
			if(!"".equals(scienceAppName)){
				groupId = themeDisplay.getScopeGroupId();
				scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(companyGroupId, groupId, themeDisplay.getLocale(), scienceAppName, true);
			}else{
				groupId = selectedGroupId;
				Map<String,Object> appSearchMap = new HashMap<String,Object>();
				if(categoryId!=0){
					appSearchMap.put("categoryIds", new long[]{categoryId});
				}
				
				appSearchMap.put("likeOrgName", CustomUtil.strNull(params.get("likeOrgName")));
				
				String [] appTypes = new String []{"Solver"};
				Locale locale = themeDisplay.getLocale();
				
				scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppAsCategory(companyGroupId, groupId, locale, 0, appTypes, null, appSearchMap, "", 0, 0, true);
			}
			
			List<Map<String, Object>> tableOrganigationList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> barChartDateList = new ArrayList<Map<String, Object>>();
			
			if(0 < scienceAppList.size()){
				tableOrganigationList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeTableOrganigation(companyGroupId, groupId, themeDisplay.getLocale(), columnId, startDt, endDt, scienceAppList, true, 0);
				barChartDateList  = ScienceAppExecuteLocalServiceUtil.getStatisticsSwExeBarChartDate(companyGroupId, groupId, columnId, startDt, endDt, scienceAppList, true, 0);
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
			
			long selectedGroupId = Long.parseLong(CustomUtil.strNull(params.get("selectedGroupId"), "0"));
			long categoryId = Long.parseLong(CustomUtil.strNull(params.get("categoryId"), "0"));
			
			long companyGroupId = themeDisplay.getCompanyGroupId();
			
			long groupId = 0;
			
			ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(themeDisplay.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			long columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
			
			//모든 버전 App 
			String scienceAppName = CustomUtil.strNull(params.get("scienceAppName"));
			List<Map<String, Object>> scienceAppList = new ArrayList<Map<String, Object>>();
			
			if(!"".equals(scienceAppName)){
				groupId = themeDisplay.getScopeGroupId();
				scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(companyGroupId, groupId, themeDisplay.getLocale(), scienceAppName, true);
			}else{
				groupId = selectedGroupId;
				Map<String,Object> appSearchMap = new HashMap<String,Object>();
				if(categoryId!=0){
					appSearchMap.put("categoryIds", new long[]{categoryId});
				}
				
				appSearchMap.put("likeOrgName", CustomUtil.strNull(params.get("likeOrgName")));
				
				String [] appTypes = new String []{"Solver"};
				Locale locale = themeDisplay.getLocale();
				
				scienceAppList = ScienceAppLocalServiceUtil.retrieveListScienceAppAsCategory(companyGroupId, groupId, locale, 0, appTypes, null, appSearchMap, "", 0, 0, true);
			}
			
			List<Map<String, Object>> tableOrganigationList = new ArrayList<Map<String, Object>>();
			
			if(0 < scienceAppList.size()){
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
}
