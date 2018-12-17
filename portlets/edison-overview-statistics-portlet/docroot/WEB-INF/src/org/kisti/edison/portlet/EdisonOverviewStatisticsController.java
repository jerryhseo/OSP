package org.kisti.edison.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;

import org.kisti.edison.service.SiteUserLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class EdisonOverviewStatisticsController {
 
	private static Log log = LogFactoryUtil.getLog(EdisonOverviewStatisticsController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, WindowStateException {
		
		String groupName = "";
		int groupCnt = 0;
		String groupId = "";
		String tabUseStr = request.getPreferences().getValue("tabUseList", "");
		
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
			}
		}
		
		model.addAttribute("pieChartCategory", groupId);
		
		return "overview/overview";
	}
	
	@ResourceMapping(value="getCategoryTotalCount")
	public void getCategoryTotalCount(ResourceRequest request, ResourceResponse response){
		
		try {
			Map params = RequestUtil.getParameterMap(request);
			String searchCategory = CustomUtil.strNull(params.get("searchCategory"), "siteUser");
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			searchParam.put("searchCategory", searchCategory);
			
			int getCategoryTotalCount = SiteUserLocalServiceUtil.getCategoryTotalCount(searchParam);
			
			JsonObject obj = new JsonObject();
			obj.addProperty("getCategoryTotalCount", getCategoryTotalCount);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.write(obj.toString());
		} catch (IOException e) {
			log.error("Overview Total User Statistics Error.");
		}
	}
	
	@ResourceMapping(value="getCategoryDataForChart")
	public void getCategoryDataForChart(ResourceRequest request, ResourceResponse response){
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			String searchCategory = CustomUtil.strNull(params.get("searchCategory"), "siteUser");
			String searchDateForm = CustomUtil.strNull(params.get("dateFormat"), "MONTH");
			String pieChartCategory = CustomUtil.strNull(params.get("pieChartCategory"), "");
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			searchParam.put("dateFormat", searchDateForm);
			searchParam.put("searchCategory", searchCategory);
			
			List<Map<String, Object>> getCategoryDataByDate = SiteUserLocalServiceUtil.getCategoryCountByDate(searchParam);
			List<Map<String, Object>> getCategoryDataBySite = null;
			
			if(!searchCategory.equals("executeUser")){
				/* 분야 사이트 별 데이터 */
				searchParam.put("languageId", themeDisplay.getLocale().toString());
				if(pieChartCategory != null && !pieChartCategory.equals("")){
					String[] pieChartCategoryArr = pieChartCategory.split(",");
					searchParam.put("pieChartCategoryIds", pieChartCategoryArr);
				}
				
				getCategoryDataBySite = SiteUserLocalServiceUtil.getCategoryCountBySite(searchParam);
			}
			
			JsonObject obj = new JsonObject();
			JsonParser parser = new JsonParser();
			obj.add("getCategoryDataByDate", (getCategoryDataByDate==null) ? parser.parse("[]") : (parser.parse(getCategoryDataByDate.toString())));
			obj.add("getCategoryDataBySite", (getCategoryDataBySite==null) ? parser.parse("[]") : (parser.parse(getCategoryDataBySite.toString())));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.write(obj.toString());
		} catch (IOException e) {
			log.error("Overview User Statistics By Date Error.");
		}
	}
}
