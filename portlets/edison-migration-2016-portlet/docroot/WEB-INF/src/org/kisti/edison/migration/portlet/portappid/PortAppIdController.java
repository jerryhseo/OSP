package org.kisti.edison.migration.portlet.portappid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import net.sf.json.JSONObject;
/*
 * APP LAYOUT 정보 UPDATE
 */
@Controller
@RequestMapping("VIEW")
public class PortAppIdController {
	
	private static Log log = LogFactoryUtil.getLog(PortAppIdController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		model.addAttribute("inputTotalCnt", ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount());
		model.addAttribute("logTotalCnt", ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPortsesCount());
		model.addAttribute("outputTotalCnt", ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount());
		return "migration";
	}
	
	@ResourceMapping(value="updatePort")
	public void updateInputDeck(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		String portType = GetterUtil.getString(params.get("type"),"INPUT");
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		long portScienceAppId = 0;
		List<String> errorMsgList = new ArrayList<String>();
		try{
			if(portType.equals("INPUT")){
				DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppInputPorts.class);
				List<ScienceAppInputPorts> dataList = ScienceAppInputPortsLocalServiceUtil.dynamicQuery(query);
				for(ScienceAppInputPorts scienceAppInputPorts:dataList){
					System.out.println("BEFORE--->"+scienceAppInputPorts.getInputPorts());
					portScienceAppId = scienceAppInputPorts.getScienceAppId();
					String updateJSON = updateJSON(portType,portScienceAppId,scienceAppInputPorts.getInputPorts());
					
					// INPUT PORT 에서 sample_ 제거
//					updateJSON = deleteSampleJSON(updateJSON);
					System.out.println("AFTER--->"+updateJSON);
					scienceAppInputPorts.setInputPorts(updateJSON);
					ScienceAppInputPortsLocalServiceUtil.updateScienceAppInputPorts(scienceAppInputPorts);
					System.out.println("----------------------------------------------------------------------");
				}
			}else if(portType.equals("OUTPUT")){
				DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppOutputPorts.class);
				List<ScienceAppOutputPorts> dataList = ScienceAppOutputPortsLocalServiceUtil.dynamicQuery(query);
				for(ScienceAppOutputPorts scienceAppOutputPorts:dataList){
//					System.out.println("BEFORE--->"+scienceAppOutputPorts.getOutputPorts());
					portScienceAppId = scienceAppOutputPorts.getScienceAppId();
					String updateJSON = updateJSON(portType,scienceAppOutputPorts.getScienceAppId(),scienceAppOutputPorts.getOutputPorts());
					System.out.print(portScienceAppId+"*");
					System.out.println("AFTER--->"+updateJSON);
					scienceAppOutputPorts.setOutputPorts(updateJSON);
					ScienceAppOutputPortsLocalServiceUtil.updateScienceAppOutputPorts(scienceAppOutputPorts);
					System.out.println("----------------------------------------------------------------------");
				}
			}else if(portType.equals("LOG")){
				DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppLogPorts.class);
				List<ScienceAppLogPorts> dataList = ScienceAppLogPortsLocalServiceUtil.dynamicQuery(query);
				for(ScienceAppLogPorts scienceAppLogPorts:dataList){
					portScienceAppId = scienceAppLogPorts.getScienceAppId();
					String updateJSON = updateJSON(portType,scienceAppLogPorts.getScienceAppId(),scienceAppLogPorts.getLogPorts());
					System.out.print(portScienceAppId+"*");
					System.out.println("AFTER--->"+updateJSON);
					scienceAppLogPorts.setLogPorts(updateJSON);
					ScienceAppLogPortsLocalServiceUtil.updateScienceAppLogPorts(scienceAppLogPorts);
					System.out.println("----------------------------------------------------------------------");
					
				}
			}
			
		}catch(Exception e){
			if(e instanceof NoSuchScienceAppException){
				String errorMsg = "NO_SUCH_SCIENCEAPPID PORT "+portType+" PORT APPID : "+portScienceAppId;
				errorMsgList.add(errorMsg);
			}else{
				e.printStackTrace();
			}
			
//			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
		}finally{
			if(errorMsgList.size()!=0){
				System.out.println("-----------------------------------------ERROR----------------------------");
				for(String errorMsg : errorMsgList){
					System.out.println(errorMsg);
				}
				System.out.println("-----------------------------------------ERROR----------------------------");
			}
		}
	}
	
	private String updateJSON(String portType,long portAppId,String portDataJSON) throws PortalException, SystemException {
		JSONObject portJson = JSONObject.fromObject(portDataJSON);
		Set<String> set = portJson.keySet();
		String defaultKey = "";
		if(portType.equals("INPUT")){
			defaultKey = "defaultEditor_";
		}else{
			defaultKey = "defaultAnalyzer_";
		}
		
		for (String portNameStr : set) {
			JSONObject parameterData = portJson.getJSONObject(portNameStr);
			long scienceAppId = 0;
			try{
				
				//sample_ type_ 변경 file->dlEntryId_
//				String sampleExist = CustomUtil.strNull(parameterData.get("sample_"));
//				if(!sampleExist.equals("")){
//					JSONObject sample = parameterData.getJSONObject("sample_");
//					sample.put("type_", "dlEntryId_");
//				}
				
				scienceAppId = Integer.parseInt(parameterData.get(defaultKey).toString());
				ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
				if(scienceApp.getAppType().equals(ScienceAppConstants.APP_TYPE_EDITOR)||scienceApp.getAppType().equals(ScienceAppConstants.APP_TYPE_ANALYZER)){
					parameterData.put(defaultKey, ScienceAppLocalServiceUtil.getScienceApp(scienceAppId).getExeFileName());
				}else{
					throw new NoSuchScienceAppException();
				}
				
			}catch(NumberFormatException e){
				//UPGRADE PORT APPNAME
				upgradeAppName(portType, parameterData, defaultKey, portAppId, portDataJSON);
			}catch(NoSuchScienceAppException e1){
				//UPGRADE PORT APPNAME
				upgradeAppName(portType, parameterData, defaultKey, portAppId, portDataJSON);
			}
		}
		
		return portJson.toString();
	}
	
	private JSONObject upgradeAppName(String portType, JSONObject parameterData,String defaultKey,long portAppId,String portDataJSON) throws SystemException, PortalException{
		long scienceAppId = 0;
		
		JSONObject dataTypeData = parameterData.getJSONObject("dataType_");
		String dtName = dataTypeData.getString("name");
		String dtVersion = dataTypeData.getString("version");
		DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dtName, dtVersion);
		
		List<Map<String, Object>> dataTypeList = new ArrayList<Map<String,Object>>();
		String dataTypeSearchKey = "";
		if(portType.equals("INPUT")){
			dataTypeList = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataType.getTypeId());
			dataTypeSearchKey = "editorId";
		}else{
			dataTypeList = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataType.getTypeId());
			dataTypeSearchKey = "analyzerId";
		}
		
		boolean searchDataTypeApp = false;
		
		try{
			for1:for(Map<String, Object> dataTypeMap : dataTypeList){
				if(GetterUtil.getBoolean(dataTypeMap.get("isDefault"))){
					scienceAppId = GetterUtil.getLong(dataTypeMap.get(dataTypeSearchKey));
					parameterData.put(defaultKey, ScienceAppLocalServiceUtil.getScienceApp(scienceAppId).getExeFileName());
					searchDataTypeApp = true;
					break for1;
				}
			}
			
			if(!searchDataTypeApp){throw new NoSuchScienceAppException();}
		}catch(NoSuchScienceAppException e2){
			System.out.print("NO_SUCH_SCIENCEAPPID PORT "+portType+" PORT APPID : "+portAppId);
			System.out.println(" SCIENCE APP ID : "+scienceAppId);
			System.out.println(portDataJSON);
			throw new NoSuchScienceAppException(e2);
			
		}finally {
			return parameterData;
		}
	}
	
	private String deleteSampleJSON(String portDataJSON) {
		JSONObject portJson = JSONObject.fromObject(portDataJSON);
		Set<String> set = portJson.keySet();
		for (String portNameStr : set) {
			JSONObject parameterData = portJson.getJSONObject(portNameStr);
			parameterData.remove("sample_");
		}
		return portJson.toString();
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
