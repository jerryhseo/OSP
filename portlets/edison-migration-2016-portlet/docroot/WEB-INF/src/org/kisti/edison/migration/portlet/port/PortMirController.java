package org.kisti.edison.migration.portlet.port;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
/*
 * 2016 -> 2017
 * 기존 OSP input,output port의 JSON 형태를 2017년도 formmat 으로 변경
 */
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class PortMirController {
	
	private static Log log = LogFactoryUtil.getLog(PortMirController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws SystemException, JSONException{
		model.addAttribute("inputTotalCnt", ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount());
		model.addAttribute("outputTotalCnt", ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount());
		return "migration";
	}
	
	@ResourceMapping(value="searchPort")
	public void searchInputDeck(ResourceRequest request, ResourceResponse response) throws Exception{
		Map params = RequestUtil.getParameterMap(request);
		String portType = GetterUtil.getString(params.get("type"),"INPUT");
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(portType.equals("INPUT")){
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppInputPorts.class);
			List<ScienceAppInputPorts> dataList = ScienceAppInputPortsLocalServiceUtil.dynamicQuery(query);
			
			for(ScienceAppInputPorts scienceAppInputPorts:dataList){
				Map <String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("appId", scienceAppInputPorts.getScienceAppId());
				dataMap.put("JSON", scienceAppInputPorts.getInputPorts());
				resultList.add(dataMap);
			}
		}else{
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppOutputPorts.class);
			List<ScienceAppOutputPorts> dataList = ScienceAppOutputPortsLocalServiceUtil.dynamicQuery(query);
					
			for(ScienceAppOutputPorts scienceAppOutputPorts:dataList){
				Map <String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("appId", scienceAppOutputPorts.getScienceAppId());
				dataMap.put("JSON", scienceAppOutputPorts.getOutputPorts());
				resultList.add(dataMap);
			}
		}
		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		String optionJson = jsonSerializer.serialize(resultList);
		JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
		JSONObject returnObject = JSONFactoryUtil.createJSONObject();
		returnObject.put("dataList", optionArr);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(returnObject.toString());
	}
	
	@ResourceMapping(value="updatePort")
	public void updateInputDeck(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		String portType = GetterUtil.getString(params.get("type"),"INPUT");
		long scienceAppId = GetterUtil.getLong(params.get("appId"),0);
		String port = GetterUtil.getString(params.get("port"));
		
		try {
			if(portType.equals("INPUT")){
				
				//SAMPLE FILE CHECK
				net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) net.sf.json.JSONSerializer.toJSON(port);
				Map jsonMap = jsonObject;
				Iterator iter = jsonMap.keySet().iterator();
				boolean isFile = false;
				
				while(iter.hasNext()) {
					String key = (String) iter.next();
					Map paramMap = (Map)jsonMap.get(key);
					Map dataTypeMap = (Map) paramMap.get("dataType_");
					String dataTypeName = (String) dataTypeMap.get("name");
					String dataTypeVersion = (String) dataTypeMap.get("version");
					
					DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
					
					if(!GetterUtil.getString(dataType.getSamplePath(),"0").equals("0")){
						long fileEntryId = GetterUtil.getLong(dataType.getSamplePath());
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
						Map fileMap = new HashMap();
						fileMap.put("id_", dataType.getSamplePath());
						fileMap.put("parent_", dlFileEntry.getTreePath());
						fileMap.put("type_", "dlEntryId");
						fileMap.put("name_", dlFileEntry.getTitle());
						fileMap.put("relative_", true);
						paramMap.put("sample_", fileMap);
						jsonMap.put(key, paramMap);
						
						isFile = true;
					}
				}
				
				port = net.sf.json.JSONSerializer.toJSON(jsonMap).toString();
				
				if(isFile){
					System.out.print("ISFILE________________");
				}
				
				System.out.print("scienceAppId--->"+scienceAppId+"______");
				System.out.println("INPUT_PORT--->"+port);
				
				ScienceAppInputPorts scienceAppInputPorts = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPorts(scienceAppId);
				scienceAppInputPorts.setInputPorts(port);
				ScienceAppInputPortsLocalServiceUtil.updateScienceAppInputPorts(scienceAppInputPorts);
			}else{
				
				System.out.print("scienceAppId--->"+scienceAppId+"______");
				System.out.println("OUTPUT_PORT--->"+port);
				ScienceAppOutputPorts scienceAppOutputPorts = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPorts(scienceAppId);
				scienceAppOutputPorts.setOutputPorts(port);
				ScienceAppOutputPortsLocalServiceUtil.updateScienceAppOutputPorts(scienceAppOutputPorts);
			}
		}catch (Exception e) {
			System.out.println("ERROR____SCIENCEAPP______"+scienceAppId);
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), "수정오류");
		}
		
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
