package org.kisti.edison.content.portlet.mainResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class MainResourceContentController {
	
	private static Log log = LogFactoryUtil.getLog(MainResourceContentController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			//포털YN
			//String portalYn = request.getPreferences().getValue("portalYn", "");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			long companyId = PortalUtil.getCompanyId(request);
			long groupId = PortalUtil.getScopeGroupId(request);
			
			List<Map<String, Object>> clusterList = new ArrayList<Map<String, Object>>();
			String portalYn = "N";
			//Icebreaker 통합으로 인한 포털 구분 없음
			//포털일 경우 Tab View
			if(portalYn.equals("Y")){
				if(!tabUseStr.equals("")){
					String []tabUseArray = tabUseStr.split(",");
					for(int i=0;i<tabUseArray.length;i++){
						Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
						Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
						
						String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(group.getGroupId()).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
						
						if(!icebreakerUrl.equals("")){
							JSONObject jsonObj = retrieveCluster(icebreakerUrl);
							JSONArray jsonArray = jsonObj.getJSONObject("clusterList").getJSONArray("clusters");
							
							JSONObject returnObj = null;
							HashMap dataMap = null;
							for(int j=0; j<jsonArray.size();j++){
								dataMap = new HashMap<String, String>();
								returnObj = (JSONObject) jsonArray.get(j);
								dataMap.put("siteName", group.getName());
								dataMap.put("clusterCount", jsonArray.size());
								dataMap.put("clusterName", returnObj.get("name"));
								int total = (int) returnObj.getJSONObject("runtime").get("totalCores");
								dataMap.put("total", total); 
								int used = (int) returnObj.getJSONObject("runtime").get("busyCores");
								dataMap.put("used", used);
								dataMap.put("avail", returnObj.getJSONObject("runtime").get("freeCores"));
								dataMap.put("usage", ((double)used/(double)total*100));		// System Resource Statistics
								
								clusterList.add(dataMap);
							}
						}
					}
				}
			} else {
				String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
				
				if(!icebreakerUrl.equals("")){
					JSONObject jsonObj = retrieveCluster(icebreakerUrl);
					JSONArray jsonArray = jsonObj.getJSONObject("clusterList").getJSONArray("clusters");
					
					JSONObject returnObj = null;
					HashMap dataMap = null;
					
					Group group = GroupLocalServiceUtil.getGroup(groupId);
					String siteName = LanguageUtil.get(themeDisplay.getLocale(),StringUtil.upperCase(group.getDescriptiveName()));
					
					for(int i=0; i<jsonArray.size();i++){
						dataMap = new HashMap<String, String>();
						returnObj = (JSONObject) jsonArray.get(i);
						dataMap.put("siteName", siteName);
						dataMap.put("clusterCount", jsonArray.size());
						dataMap.put("clusterName", returnObj.get("name"));
						int total = (int) returnObj.getJSONObject("runtime").get("totalCores");
						dataMap.put("total", total); 
						int used = (int) returnObj.getJSONObject("runtime").get("busyCores");
						dataMap.put("used", used);
						dataMap.put("avail", returnObj.getJSONObject("runtime").get("freeCores"));
						dataMap.put("usage", ((double)used/(double)total*100));		// System Resource Statistics
						
						clusterList.add(dataMap);
					}
				}
			}
			//클러스터 조회
//			List<Map<String, Object>> clusterList = new ArrayList<Map<String, Object>>();
			model.addAttribute("clusterList", clusterList);
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	return "mainResource/contentList";
	}
	
	/**
	 * icebreaker 클러스터 리스트 조회
	 * @param icebreakerUrl
	 * @return
	 * @throws PortalException
	 * @throws IOException
	 */
	private JSONObject retrieveCluster(String icebreakerUrl) throws PortalException, IOException{
		StringBuffer responseBuffer = new StringBuffer();
		JSONObject json = new JSONObject();
		
		URL url = new URL(icebreakerUrl+"/api/cluster/list");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		
		
		if (conn.getResponseCode() == 400) {
			throw new PortalException("Failed IcebreakerService [ retrieveCluster ] : BAD REQUEST: bad parameters");
		}else{
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String  output = "";		
			while ((output = br.readLine()) != null) {			
				responseBuffer.append(output);
			}
			json.put("clusterList", responseBuffer.toString());
		}
		conn.disconnect();
		return json;
	}
	
	
}
