package org.kisti.edison.migration.portlet.inputdeck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
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
/*
 * 2016 -> 2017
 * 기존 OSP inputDeck의 JSON 형태를 2017년도 formmat 으로 변경
 */
@Controller
@RequestMapping("VIEW")
public class InputdeckController {
	
	private static Log log = LogFactoryUtil.getLog(InputdeckController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws SystemException, JSONException{
		model.addAttribute("totalCnt", DataTypeStructureLocalServiceUtil.getDataTypeStructuresCount());
		return "migration";
	}
	
	
	@ResourceMapping(value="searchInputDeck")
	public void searchInputDeck(ResourceRequest request, ResourceResponse response) throws Exception{
		List<DataTypeStructure> dataList =  DataTypeStructureLocalServiceUtil.getDataTypeStructures(-1, -1);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		for(DataTypeStructure dataTypeStructure:dataList){
			Map <String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("typeId", dataTypeStructure.getTypeId());
			dataMap.put("JSON", dataTypeStructure.getStructure());
			resultList.add(dataMap);
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
	
	@ResourceMapping(value="updateInputDeck")
	public void updateInputDeck(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			Map params = RequestUtil.getParameterMap(request);
			long typeId = GetterUtil.getLong(params.get("typeId"),0);
			String inputdeckForm = GetterUtil.getString(params.get("inputdeckForm"),"");
			
			System.out.print("portTypeId-->"+typeId+"_________");
			System.out.println("inputdeckForm-->"+inputdeckForm);
			
			DataTypeStructure dataTypeStructure =  DataTypeStructureLocalServiceUtil.getDataTypeStructure(typeId);
			dataTypeStructure.setStructure(inputdeckForm);
			DataTypeStructureLocalServiceUtil.updateDataTypeStructure(dataTypeStructure);
		}catch (Exception e) {
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
