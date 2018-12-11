package org.kisti.edison.migration.portlet.applayout;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 * APP LAYOUT 정보 UPDATE
 */
@Controller
@RequestMapping("VIEW")
public class ApplayoutController {
	
	private static Log log = LogFactoryUtil.getLog(ApplayoutController.class);
	
    @RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
    	
    	long groupId = PortalUtil.getScopeGroupId(request);
    	Locale locale = themeDisplay.getLocale();
    	long authorId = 0;
    	String[] appTypes = new String[]{"Solver"};
    	String[] editorTypes = null;
    	
    	Map<String,Object>searchMap = new HashMap<String,Object>();
    	String status = "";
    	int begin = 0;
    	int end = 0;
    	boolean lanuageSearch = false;
    	
    	int totalCnt = ScienceAppLocalServiceUtil.countListScienceApp(groupId, locale, authorId, appTypes, editorTypes, searchMap, status, lanuageSearch);
    	
    	model.addAttribute("totalCnt", totalCnt);
		return "migration";
	}
	
	
	@ResourceMapping(value="updateLayout")
	public void updateLayout(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
		int successCnt = 0;
		int errorCnt = 0;
		int notUpdateCnt = 0;
		
		try{
			List<ScienceApp> appList = ScienceAppLocalServiceUtil.getAll();
			for(ScienceApp scienceApp: appList){
				if(scienceApp.getAppType().equals(ScienceAppConstants.APP_TYPE_SOLVER)){
					if(scienceApp.getLayout().equals("")){
						notUpdateCnt++;
					}else{
						JSONObject appLayoutJson = JSONObject.fromObject(scienceApp.getLayout());
						if(appLayoutJson.has("isStepLayout_")){
							notUpdateCnt++;
						}else{
							String templateId = appLayoutJson.get("templateId_").toString();
							if(templateId.indexOf("flow")>-1){
								//FLOW UPDATE
								scienceApp.setLayout(this.flowLayoutUpdate(appLayoutJson,templateId));
								scienceApp.setIsStepLayout(true);
							}else{
								//ONECOLUMN UPDATE
								scienceApp.setLayout(this.oneLayoutUpdate(appLayoutJson,templateId));
								scienceApp.setIsStepLayout(false);
							}
							
							if(!this.updateScienceApp(scienceApp)){
								errorCnt++;
							}else{
								successCnt++;
							}
						}
					}
					
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("successCnt", successCnt);
			json.put("errorCnt", errorCnt);
			json.put("notUpdateCnt", notUpdateCnt);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}catch(SystemException e){
			
		}
	}
	
	private Boolean updateScienceApp(ScienceApp scienceApp){
		try {
			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
			return true;
		} catch (Exception e) {
			System.out.print("ERROR APPID==============>"+scienceApp.getScienceAppId());
			System.out.println("__APP_NAME==>"+scienceApp.getName()+"====APP_Version===>"+scienceApp.getVersion());
			e.printStackTrace();
			return false;
		}
	}
	
	/*FLOW LAYOUT UPDATE*/
	private String flowLayoutUpdate(JSONObject appLayoutJson, String templateId){
		System.out.println("===========================FLOW==PRE============================");
		System.out.println(appLayoutJson.toString());
		
		JSONObject updateLayoutJson = new JSONObject();
		updateLayoutJson.put("isStepLayout_", true);
		
		
		JSONArray preColumns = appLayoutJson.getJSONArray("columns_");
		JSONArray arrayKeys = new JSONArray();
		boolean isOutPutLayout = false;
		for(int i=0;i<preColumns.size();i++){
			JSONObject preColumn = preColumns.getJSONObject(i);
			if(preColumn.getString("id_").equals("column-1")){
				//SYSTEM
				arrayKeys.add("SYSTEM");
				updateLayoutJson.put("SYSTEM", this.preLayoutFlowColumnsUpdate(preColumn, "SYSTEM"));
			}else if(preColumn.getString("id_").equals("column-4")){
				//INPUT
				arrayKeys.add("INPUT");
				updateLayoutJson.put("INPUT", this.preLayoutFlowColumnsUpdate(preColumn, "INPUT"));
			}else if(preColumn.getString("id_").equals("column-5")){
				//LOG
				arrayKeys.add("LOG");
				updateLayoutJson.put("LOG", this.preLayoutFlowColumnsUpdate(preColumn, "LOG"));
			}else if(preColumn.getString("id_").equals("column-6")){
				isOutPutLayout = true;
			}
		}
		
		if(isOutPutLayout){
			//OUPUT
			arrayKeys.add("OUTPUT");
			updateLayoutJson.put("OUTPUT", this.preFlowLayoutOutput(appLayoutJson, templateId));
		}
		
		updateLayoutJson.put("arrayKeys_", arrayKeys);
		System.out.println("===========================FLOW==UPDATE============================");
		System.out.println(updateLayoutJson.toString());
		
		return updateLayoutJson.toString();
	}
	
	
	private JSONObject preFlowLayoutOutput(JSONObject appLayoutJson,String templateId){
		JSONObject updateLayoutJson = new JSONObject();
		
		String newTemplateId = templateId.replaceAll("flow-", "");
		updateLayoutJson.put("templateId_", newTemplateId);
		updateLayoutJson.put("layoutName_", "OUTPUT");
		
		JSONArray updateColumnsJson = new JSONArray();
		JSONArray preColumns = appLayoutJson.getJSONArray("columns_");
		for(int i=0;i<preColumns.size();i++){
			JSONObject preColumn = preColumns.getJSONObject(i);
			String preColumnId = preColumn.getString("id_");
			int preColumnIdIndex = Integer.parseInt(preColumnId.replaceAll("[^0-9]", ""));
			if(preColumnIdIndex>5){
				String newColumnId = "column-"+(preColumnIdIndex-2);
				updateColumnsJson.add(this.columnUpldate(preColumn, "OUTPUT",newColumnId));
			}
		}
		
		updateLayoutJson.put("columns_", updateColumnsJson);
		return updateLayoutJson;
	}
	
	private JSONObject preLayoutFlowColumnsUpdate(JSONObject preColumn, String layoutKey){
		JSONObject updateLayoutJson = new JSONObject();
		JSONArray updateColumnsJson = new JSONArray();
		if(layoutKey.equals("SYSTEM")){
			updateColumnsJson.add("{\"id_\":\"column-1\",\"portlets_\":[{\"instanceId_\":\"SimulationDashboard_WAR_edisonsimulationportlet_INSTANCE_SYSTEM\"}],\"currentPortlet_\":\"SimulationDashboard_WAR_edisonsimulationportlet_INSTANCE_SYSTEM\"}");
			updateColumnsJson.add("{\"id_\":\"column-2\",\"portlets_\":[{\"instanceId_\":\"SimulationJobController_WAR_edisonsimulationportlet_INSTANCE_SYSTEM\"}],\"currentPortlet_\":\"SimulationJobController_WAR_edisonsimulationportlet_INSTANCE_SYSTEM\"}");
		}else if(layoutKey.equals("INPUT")){
			updateColumnsJson.add(this.columnUpldate(preColumn, "INPUT", "column-4"));
			updateLayoutJson.put("templateId_", "1-row-1-column");
		}else if(layoutKey.equals("LOG")){
			updateColumnsJson.add(this.columnUpldate(preColumn, "LOG", "column-4"));
			updateLayoutJson.put("templateId_", "1-row-1-column");
		}
		
		updateLayoutJson.put("columns_", updateColumnsJson);
		updateLayoutJson.put("layoutName_", layoutKey);
		return updateLayoutJson;
	}
	
	/*NO-FLOW LAYOUT UPDATE*/
	private String oneLayoutUpdate(JSONObject appLayoutJson, String templateId){
		System.out.println("=============================PRE============================");
		System.out.println(appLayoutJson.toString());
		
		JSONObject updateLayoutJson = new JSONObject();
		updateLayoutJson.put("isStepLayout_", false);
		updateLayoutJson.put("arrayKeys_", new String[]{"LAYOUT"});
		
		JSONObject layoutJson = new JSONObject();
		layoutJson.put("templateId_", templateId);
		layoutJson.put("layoutName_", "LAYOUT");

		
		//layout AREA
		JSONArray uplodateColumns = this.preLayoutOneColumnsUpdate(appLayoutJson);
		layoutJson.put("columns_", uplodateColumns);
		updateLayoutJson.put("LAYOUT", layoutJson);
		
		System.out.println("=============================UPDATE============================");
		System.out.println(updateLayoutJson.toString());
		
		return updateLayoutJson.toString();
	}
	
	private JSONArray preLayoutOneColumnsUpdate(JSONObject appLayoutJson){
		JSONArray updateColumnsJson = new JSONArray();
		updateColumnsJson.add("{\"id_\":\"column-1\",\"portlets_\":[{\"instanceId_\":\"SimulationDashboard_WAR_edisonsimulationportlet_INSTANCE_LAYOUT\"}],\"currentPortlet_\":\"SimulationDashboard_WAR_edisonsimulationportlet_INSTANCE_LAYOUT\"}");
		updateColumnsJson.add("{\"id_\":\"column-2\",\"portlets_\":[{\"instanceId_\":\"SimulationJobController_WAR_edisonsimulationportlet_INSTANCE_LAYOUT\"}],\"currentPortlet_\":\"SimulationJobController_WAR_edisonsimulationportlet_INSTANCE_LAYOUT\"}");
		
		
		
		JSONArray preColumns = appLayoutJson.getJSONArray("columns_");
		for(int i=0;i<preColumns.size();i++){
			JSONObject preColumn = preColumns.getJSONObject(i);
			String preColumnId = preColumn.getString("id_");
			if(preColumnId.equals("column-1")||preColumnId.equals("column-2")||preColumnId.equals("column-3")){
				
			}else{
				updateColumnsJson.add(this.columnUpldate(preColumn, "LAYOUT", preColumnId));
			}
		}
		
		return updateColumnsJson;
	}
	
	private JSONObject columnUpldate(JSONObject preColumn,String layoutKey,String preColumnId){
		int preColumntIndex = Integer.parseInt(preColumnId.replaceAll("[^0-9]", ""))-1;
		String newColumnId = "column-"+preColumntIndex;
		
		JSONObject updateColumn = new JSONObject();
		updateColumn.put("id_", newColumnId);
		
		if(preColumn.has("currentPortlet_")){
			updateColumn.put("currentPortlet_", this.portletIdUpdate(preColumn.getString("currentPortlet_"),layoutKey));
			
			JSONArray preColumnPortlets = preColumn.getJSONArray("portlets_");
			JSONArray newColumnPortlets = new JSONArray();
			
			for(int i=0;i<preColumnPortlets.size();i++){
				JSONObject preColumnPortlet = preColumnPortlets.getJSONObject(i);
				JSONObject newColumnPortlet = new JSONObject();
				
				newColumnPortlet.put("instanceId_", this.portletIdUpdate(preColumnPortlet.getString("instanceId_"),layoutKey));
				if(preColumnPortlet.has("portName_")){
					newColumnPortlet.put("portName_", preColumnPortlet.getString("portName_"));
				}
				
				newColumnPortlets.add(newColumnPortlet);
			}
			
			updateColumn.put("portlets_", newColumnPortlets);
		}else{
			updateColumn.put("portlets_", preColumn.getJSONArray("portlets_"));
		}
		
		return updateColumn;
	}
	
	private String portletIdUpdate(String prePortletId, String layoutKey){
		String newPortletId = "";
		if(prePortletId.indexOf("INSTANCE")>-1){
			String prePortletIndex = prePortletId.replaceAll("[^0-9]", "");
			String preShortPortletId = prePortletId.substring(0,prePortletId.indexOf("INSTANCE")-1);
			newPortletId = preShortPortletId+"_INSTANCE_"+layoutKey+"_"+prePortletIndex;
		}else{
			newPortletId = prePortletId+"_INSTANCE_"+layoutKey;
		}
		
		return newPortletId;
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
