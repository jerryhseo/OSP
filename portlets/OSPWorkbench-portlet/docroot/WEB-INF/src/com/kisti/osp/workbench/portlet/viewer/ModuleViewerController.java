package com.kisti.osp.workbench.portlet.viewer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("VIEW")
public class ModuleViewerController {
	protected static Log  _log = LogFactoryUtil.getLog(ModuleViewerController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws JSONException{
		String portType = ParamUtil.getString(request, "portType", "inputPorts");
		String portData = ParamUtil.getString(request, "portData", "");
		
		
		
		/*TEST DATA*/
		if(portData.equals("")){
			portData = "{ \"-o\": { \"editors_\": [{ \"name\": \"SDE\", \"value\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\" }], \"sample_\": { \"type_\": \"dlEntryId_\", \"relative_\": true, \"id_\": \"13283219\" }, \"defaultEditor_\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\", \"mandatory_\": true, \"isWfSample_\": true, \"name_\": \"-o\", \"dataType_\": { \"name\": \"1dPhononLab_inputDect\", \"version\": \"1.0.0\" }, \"wfSample_\": { \"id_\": 14423117, \"type_\": \"dlEntryId_\", \"name_\": \"00000.txt (1)_b1c.txt\" } } }";
		}
		
		JSONObject portDataJson = JSONFactoryUtil.createJSONObject(portData);
		String defaultPortlet = "";
		String portName = "";
		Iterator<String> itr = portDataJson.keys();
		while(itr.hasNext()){
			String key = itr.next();
			JSONObject portDataValue = portDataJson.getJSONObject(key);
			if(portType.equals("inputPorts")){
				defaultPortlet = portDataValue.getString("defaultEditor_");
			}else{
				defaultPortlet = portDataValue.getString("defaultAnalyzer_");
			}
			
			portName = portDataValue.getString("name_");
			
		}
		
		
		JSONObject moduleLayout = JSONFactoryUtil.createJSONObject();
		JSONArray moduleColumns = JSONFactoryUtil.createJSONArray();
		
		JSONArray modulePortlets = JSONFactoryUtil.createJSONArray();
		JSONObject portlet = JSONFactoryUtil.createJSONObject();
		portlet.put("instanceId_", defaultPortlet);
		portlet.put("portName_", portName);
		modulePortlets.put(portlet);
		
		
		JSONObject moduleColumn = JSONFactoryUtil.createJSONObject();
		moduleColumn.put("portlets_", modulePortlets);
		moduleColumn.put("currentPortlet_", defaultPortlet);
		moduleColumn.put("id_", "column-1");
		moduleColumns.put(moduleColumn);
		
		moduleLayout.put("columns_", moduleColumns);
		
		model.addAttribute("workbenchLayout", moduleLayout);
		model.addAttribute("portType", portType);
		model.addAttribute("portData", portData);
		model.addAttribute("portName", portName);
		return "view";
	}
	
	
	@ResourceMapping(value="serveResource")
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException{
		String command = ParamUtil.getString(request, "command");
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			if( command.equalsIgnoreCase("RESOLVE_TEMPLATE")){
				this.resolveLayoutTemplate(request, response);
			}else if( command.equalsIgnoreCase("GET_DATA_STRUCTURE")){
				this.getDataStructure(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	private static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
	
	private void resolveLayoutTemplate(  ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException, PortalException, SystemException{
		String templateDir = ParamUtil.getString(resourceRequest, "templateDir");
		String namespace = ParamUtil.getString(resourceRequest, "namespace");
		
		Map<String, Object> params = new HashMap<>();
		params.put("namespace", namespace);
		String templateFile = "module-viewer.ftl";
		
		String realTemplateDir = resourceRequest.getPortletSession().getPortletContext().getRealPath(templateDir);
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(realTemplateDir));
		cfg.setDefaultEncoding("UTF-8");
		
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		
		Template template = cfg.getTemplate(templateFile);
		PrintWriter writer = resourceResponse.getWriter();
		try {
			template.process(params, writer);
			template.getConfiguration();
		} catch (TemplateException e) {
			_log.error("[ERROR] template.process()");
			throw new PortletException();
		} finally{
			writer.close();
		}
	}
	
	
	protected void getDataStructure( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
		String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName");
		String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion");
		
		DataType dataType = null;
		try {
			dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
		} catch (SystemException e) {
			_log.error("[ERROR] Invalid data type: "+dataTypeName+"-"+dataTypeVersion);
			throw new PortletException();
		}
		DataTypeStructure dataTypeStructure = null;
		try {
			dataTypeStructure = DataTypeStructureLocalServiceUtil.getDataTypeStructure(dataType.getTypeId());
		} catch (PortalException e1) {
			_log.debug("Data type has no defined structure: "+dataTypeName+"-"+dataTypeVersion);
		} catch (SystemException e1) {
			_log.error("[ERROR] While getting data type structure: "+dataTypeName+"-"+dataTypeVersion);			
			throw new IOException();
		}
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		ServletResponseUtil.write(httpResponse, dataTypeStructure.getStructure());
	}
}
