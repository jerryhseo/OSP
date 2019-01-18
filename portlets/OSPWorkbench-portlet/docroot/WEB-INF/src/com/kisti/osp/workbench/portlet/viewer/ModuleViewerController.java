package com.kisti.osp.workbench.portlet.viewer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("VIEW")
public class ModuleViewerController {
	protected static Log  _log = LogFactoryUtil.getLog(ModuleViewerController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String simulationUuid = ParamUtil.getString(request, "simulationUuid", "");
		String jobUuid = ParamUtil.getString(request, "jobUuid", "");
		String screenName = ParamUtil.getString(request, "screenName", themeDisplay.getUser().getScreenName());
		String portType = ParamUtil.getString(request, "portType", "inputPorts");
		String portData = ParamUtil.getString(request, "portData", "");
		String status = ParamUtil.getString(request, "status", "INITIALIZED");
		String nodeId = ParamUtil.getString(request, "nodeId");
		long userId = ParamUtil.getLong(request, "userId", 0);
		if(userId != 0){
		    screenName = UserLocalServiceUtil.getUser(userId).getScreenName();
		}
		
		if(simulationUuid.equals("")){
			simulationUuid = UUID.randomUUID().toString();
		}
		
		if(jobUuid.equals("")){
			jobUuid = UUID.randomUUID().toString();
		}
		
		
		
		/*TEST DATA*/
		if(portData.equals("")){
			//No-Input Data (SDE)
//			portData = "{ \"-o\": { \"editors_\": [{ \"name\": \"SDE\", \"value\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\" }], \"sample_\": { \"type_\": \"dlEntryId_\", \"relative_\": true, \"id_\": \"13283219\" }, \"defaultEditor_\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\", \"mandatory_\": true, \"isWfSample_\": true, \"name_\": \"-o\", \"dataType_\": { \"name\": \"1dPhononLab_inputDect\", \"version\": \"1.0.0\" }, \"wfSample_\": { \"id_\": 14428816, \"type_\": \"dlEntryId_\", \"name_\": \"00000.txt (1)_b1c.txt\" } } }";
			//Input Data Exist (SDE)
//			portData = "{ \"-o\": { \"editors_\": [{ \"name\": \"SDE\", \"value\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\" }], \"sample_\": { \"type_\": \"dlEntryId_\", \"relative_\": true, \"id_\": \"13283219\" }, \"defaultEditor_\": \"StructuredDataEditor_WAR_OSPStructuredDataEditorportlet\", \"mandatory_\": true, \"isWfSample_\": true, \"name_\": \"-o\", \"dataType_\": { \"name\": \"1dPhononLab_inputDect\", \"version\": \"1.0.0\" }, \"wfSample_\": { \"id_\": 14428816, \"type_\": \"dlEntryId_\", \"name_\": \"00000.txt (1)_b1c.txt\" }, \"inputs_\": { \"portName_\": \"-o\", \"order_\": 1, \"type_\": \"fileContent\", \"context_\": \"Type_of_phonon = Optical ;\\nSpring_constants = [1900 1250] ;\\nMass_of_particles = [11 30] ;\\nNumber_of_frame = 40 ;\\nWave_number = 0.9 ;\" } } }";
			
			//No-Input Data (FILE_SELECTOR)
//			portData = "{ \"-mesh\": { \"name_\": \"-mesh\", \"defaultEditor_\": \"FileExplorer_WAR_OSPFileExplorerportlet\", \"dataType_\": { \"name\": \"KFLOW_EDISON_UNSTEADY_mesh\", \"version\": \"1.0.0\" }, \"mandatory_\": true, \"order_\": 1, \"isWfSample_\": false, \"sample_\": { \"id_\": \"14110320\", \"type_\": \"dlEntryId_\", \"relative_\": true }, \"editors_\": [{ \"name\": \"FILE_SELECTOR\", \"value\": \"FileExplorer_WAR_OSPFileExplorerportlet\" }] } }";
			//Input Data (FILE_SELECTOR)
			portData = "{ \"-mesh\": { \"name_\": \"-mesh\", \"defaultEditor_\": \"FileExplorer_WAR_OSPFileExplorerportlet\", \"dataType_\": { \"name\": \"KFLOW_EDISON_UNSTEADY_mesh\", \"version\": \"1.0.0\" }, \"mandatory_\": true, \"order_\": 1, \"isWfSample_\": false, \"sample_\": { \"id_\": \"14110320\", \"type_\": \"dlEntryId_\", \"relative_\": true }, \"editors_\": [{ \"name\": \"FILE_SELECTOR\", \"value\": \"FileExplorer_WAR_OSPFileExplorerportlet\" }],\"inputs_\":{\"type_\":\"file\",\"parent_\":\"2018-05-30-07-08-56.574\",\"name_\":\"param\",\"portName_\":\"-mesh\",\"relative_\":true,\"dirty_\":true,\"order_\":1}} }";
			
			
			//No-Input Data (Text_Editor)
//			portData = "{ \"-mesh\": { \"name_\": \"-mesh\", \"defaultEditor_\": \"TextEditor_WAR_OSPTextEditorportlet\", \"dataType_\": { \"name\": \"KFLOW_EDISON_UNSTEADY_mesh\", \"version\": \"1.0.0\" }, \"mandatory_\": true, \"order_\": 1, \"isWfSample_\": false, \"sample_\": { \"id_\": \"14110320\", \"type_\": \"dlEntryId_\", \"relative_\": true }, \"editors_\": [{ \"name\": \"FILE_SELECTOR\", \"value\": \"FileExplorer_WAR_OSPFileExplorerportlet\" }] } }";
			//Input Data (Text_Editor)
//			portData = "{ \"-mesh\": { \"name_\": \"-mesh\", \"defaultEditor_\": \"TextEditor_WAR_OSPTextEditorportlet\", \"dataType_\": { \"name\": \"KFLOW_EDISON_UNSTEADY_mesh\", \"version\": \"1.0.0\" }, \"mandatory_\": true, \"order_\": 1, \"isWfSample_\": false, \"sample_\": { \"id_\": \"14110320\", \"type_\": \"dlEntryId_\", \"relative_\": true }, \"editors_\": [{ \"name\": \"FILE_SELECTOR\", \"value\": \"FileExplorer_WAR_OSPFileExplorerportlet\" }],\"inputs_\":{\"type_\":\"content\",\"content_\":\"gergergergsergergergerg\",\"portName_\":\"-mesh\",\"relative_\":true,\"dirty_\":true,\"order_\":1}} }";
			
			//No-Input Data (String_Editor)
//			portData = "{ \"-mesh\": { \"name_\": \"-mesh\", \"defaultEditor_\": \"StringEditor_WAR_OSPStringEditorportlet\", \"dataType_\": { \"name\": \"KFLOW_EDISON_UNSTEADY_mesh\", \"version\": \"1.0.0\" }, \"mandatory_\": true, \"order_\": 1, \"isWfSample_\": false, \"sample_\": { \"id_\": \"14110320\", \"type_\": \"dlEntryId_\", \"relative_\": true }, \"editors_\": [{ \"name\": \"FILE_SELECTOR\", \"value\": \"FileExplorer_WAR_OSPFileExplorerportlet\" }] } }";
		}
		boolean isAnalyzerTest = false;
		if(isAnalyzerTest){
			portType = "outputPorts";
			status = "SUCCESS";
			portData = "{ \"bowling\": { \"name_\": \"bowling\", \"outputData_\": { \"id_\": 0, \"parent_\": \"result/\", \"type_\": \"file\", \"name_\": \"bowling.png\", \"relative_\": true }, \"defaultAnalyzer_\": \"OSPImageViewer_WAR_OSPImageViewerportlet\", \"dataType_\": { \"name\": \"ImageViewer\", \"version\": \"1.0.0\" }, \"mandatory_\": true } }";
			simulationUuid = "b8e860aa-592c-408c-95a9-52e25e52f957";
			jobUuid = "21a3331e-64a6-44f4-8b08-7f649e1ef126";
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
		model.addAttribute("portData", StringEscapeUtils.escapeJavaScript(portData));
		model.addAttribute("portName", portName);
		model.addAttribute("simulationUuid", simulationUuid);
		model.addAttribute("jobUuid", jobUuid);
		model.addAttribute("screenName", screenName);
		model.addAttribute("status", status);
		model.addAttribute("nodeId", nodeId);
		
		
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
			}else if( command.equalsIgnoreCase("READ_DLENTRY")){
				this.readDLEntry(request, response);
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
	
	protected void readDLEntry( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException{
		long dlEntryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
		String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName");
		String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		String fileContent = this.readDLFileContent( dlEntryId );
		
		DataType dataType;
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
		
		if( dataTypeStructure == null ){
			result.put("contentType", "fileContent");
			result.put("fileContent", fileContent);
		}
		else{
			result.put("contentType", "structuredData");
			result.put("fileContent", fileContent);
			result.put("dataStructure", dataTypeStructure.getStructure());
		}
		
		ServletResponseUtil.write(httpResponse, result.toString());
	}
	
	private String readDLFileContent( long dlEntryId ) throws PortletException, IOException{
		if( dlEntryId <= 0)
			return "";
		
		FileEntry fileEntry;
		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(dlEntryId);
		} catch (NumberFormatException | PortalException | SystemException e1) {
			_log.error("[ERROR] Wrong file entry: "+dlEntryId);
			throw new PortletException();
		}
		
		InputStream stream = null;
		try {
			stream = fileEntry.getContentStream();
			
		} catch (PortalException | SystemException e1) {
			_log.error("[ERROR] readDLFileContent() : "+fileEntry.getFileEntryId());
			throw new PortletException();
		}

		String content = new String(FileUtil.getBytes(stream));
		if( stream != null )
			stream.close();
		
		return content;
	}
}
