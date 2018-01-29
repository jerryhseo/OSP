package com.kisti.osp.workbench.portlet.simulation.layout;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.NoSuchSimulationException;
import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.workbench.Exception.SimulationWorkbenchException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import freemarker.template.Configuration;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("VIEW")
public class LayoutController {
	protected static Log  _log = LogFactoryUtil.getLog(LayoutController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		String workbenchType = ParamUtil.getString(request, "workbenchType", "SIMULATION_WITH_APP");
		long customId =  ParamUtil.getLong(request, "customId", 0);
		long classId =  ParamUtil.getLong(request, "classId", 0);
		try{
			model = this.evaluateScienceAppLayout(model, 27701);
			
			model.addAttribute("workbenchType", workbenchType);
			model.addAttribute("customId", customId);
			model.addAttribute("classId", classId);
			return "view";
		}catch(Exception e){
			if(e instanceof SimulationWorkbenchException){
				SessionErrors.add(request, e.getClass(), e);
			}else{
				_log.error(e);
				e.printStackTrace();
				SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			}
			return "error";
		}
	}
	
	
	@ResourceMapping(value="serveResource")
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException{
		String command = ParamUtil.getString(request, "command");
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			if( command.equalsIgnoreCase("RESOLVE_TEMPLATE")){
				this.resolveLayoutTemplate(request, response);
			}else if( command.equalsIgnoreCase("LOAD_JOB")){
				JSONObject loadJob = this.loadJob(request, response);
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(loadJob.toString());
			}else if( command.equalsIgnoreCase("GET_DATA_STRUCTURE")){
				JSONObject data = this.getDataStructure(request, response);
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(data.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
	protected void resolveLayoutTemplate(  ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException{
		String templateDir = ParamUtil.getString(resourceRequest, "templateDir");
		String templateFile = ParamUtil.getString(resourceRequest, "templateFile");
		String namespace = ParamUtil.getString(resourceRequest, "namespace");
		
		String realTemplateDir = resourceRequest.getPortletSession().getPortletContext().getRealPath(templateDir);
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(realTemplateDir));
		cfg.setDefaultEncoding("UTF-8");
		
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		
		Map<String, Object> params = new HashMap<>();
		params.put("namespace", namespace);
		JSONArray columns = null;
		try {
			columns = JSONFactoryUtil.createJSONArray(ParamUtil.getString(resourceRequest, "columns"));
		} catch (JSONException e1) {
			_log.error("Getting columns while resolving layout template");
			throw new PortletException();
		}
		try {
			params.put("columns", this.layoutJSONObject2Map(columns));
		} catch (JSONException e1) {
			_log.error("Getting columns while creating freemarker parameters");
			throw new PortletException();
		}
		
		Template template = cfg.getTemplate(templateFile);
		PrintWriter writer = resourceResponse.getWriter();
		try {
			template.process(params, writer);
		} catch (TemplateException e) {
			_log.error("[ERROR] template.process()");
			throw new PortletException();
		} finally{
			writer.close();
		}
		
	}
	
	protected SimpleSequence layoutJSONObject2Map( JSONArray jsonColumns ) throws JSONException{
		SimpleSequence columns = new SimpleSequence();
		
		for( int i=0; i<jsonColumns.length(); i++){
			JSONObject jsonColumn = jsonColumns.getJSONObject(i);
			Map<String, String> column = new HashMap<>();
			column.put("id", jsonColumn.getString("id"));
			column.put("portletId", jsonColumn.getString("portletId"));
			columns.add( column );
		}
		_log.info(columns.toString());
		
		return columns;
	}

	protected ModelMap evaluateScienceAppLayout(ModelMap model,long scienceAppId) throws SimulationWorkbenchException{
		ScienceApp scienceApp = null;
		try{
			scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			model.addAttribute("scienceApp", scienceApp);
		}catch (Exception e) {
			throw new SimulationWorkbenchException(SimulationWorkbenchException.NO_SCIENCEAPP_ID);
		}
		
		try{
			JSONObject workbenchLayout = JSONFactoryUtil.createJSONObject(scienceApp.getLayout());
			model.addAttribute("workbenchLayout", workbenchLayout);
		}catch (Exception e) {
			throw new SimulationWorkbenchException(SimulationWorkbenchException.NO_SCIENCEAPP_LAYOUT_EXIST);
		}
		
		String inputPorts = "";
		String logPorts = "";
		String outputPorts = "";
		
		try {
			inputPorts = ScienceAppLocalServiceUtil.getScienceAppInputPorts(scienceAppId);
			model.addAttribute("inputPorts", inputPorts);
		} catch (SystemException e) {
			_log.error("Getting input ports for:  "+scienceAppId);
			throw new SimulationWorkbenchException(SimulationWorkbenchException.SCIENCEAPP_PORT_SEARCH_EXCEPTION);
		}
		
		try {
			logPorts = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
			model.addAttribute("logPorts", logPorts);
		} catch (SystemException e) {
			_log.error("Getting log ports for:  "+scienceAppId);
			throw new SimulationWorkbenchException(SimulationWorkbenchException.SCIENCEAPP_PORT_SEARCH_EXCEPTION);
		}
		
		try {
			outputPorts = ScienceAppLocalServiceUtil.getScienceAppOutputPorts(scienceAppId);
			model.addAttribute("outputPorts", outputPorts);
		} catch (SystemException e) {
			_log.error("Getting output ports for:  "+scienceAppId);
			throw new SimulationWorkbenchException(SimulationWorkbenchException.SCIENCEAPP_PORT_SEARCH_EXCEPTION);
		}
		
		return model;
	}
	
	
	protected JSONObject loadJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws JSONException, SystemException, NoSuchSimulationException, NoSuchSimulationJobException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		
		
		Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
		
		SimulationJob job = SimulationJobLocalServiceUtil.getJob(jobUuid);
		
		SimulationJobData jobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(job.getJobUuid());
		
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("simulation_", this.convertSimulationToJSON(simulation));
		jsonObj.put("job_", this.convertJobToJSON(job));
		if( jobData != null ){
			jsonObj.put("inputs_", jobData.getJobData());
		}
		return jsonObj;
	}
	
	protected JSONObject convertSimulationToJSON( Simulation simulation ){
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		json.put("uuid_", simulation.getSimulationUuid());
		json.put("title_", simulation.getSimulationTitle());
		json.put("scienceAppId_", simulation.getScienceAppId());
		json.put("scienceAppName_", simulation.getScienceAppName());
		json.put("createTime_", simulation.getSimulationCreateDt());
		if( simulation.getCluster() != null )
			json.put("cluster_", simulation.getCluster());
		
		return json;
	}
	
	protected JSONObject convertJobToJSON( SimulationJob job ) throws SystemException, JSONException{
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		json.put("uuid_", job.getJobUuid());
		json.put("title_", job.getJobTitle());
		if(  job.getJobStartDt() != null )
			json.put("startTime_", job.getJobStartDt());
		if( job.getJobEndDt() != null )
			json.put("endTime_", job.getJobEndDt());
		json.put("submit_", job.getJobSubmit());
		
		json.put("status_", job.getJobStatus());
		
		return json;
	}
	
	
	protected JSONObject getDataStructure( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException, JSONException{
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
		JSONObject result = JSONFactoryUtil.createJSONObject(dataTypeStructure.getStructure());
		return result;
	}
	
}
