package com.kisti.osp.workbench.portlet.simulation.layout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
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
import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.util.OSPFileUtil;
import com.kisti.osp.workbench.Exception.SimulationWorkbenchException;
import com.kisti.osp.workbench.agent.ib.IBAgent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import freemarker.template.Configuration;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("VIEW")
public class LayoutController {
	protected static Log  _log = LogFactoryUtil.getLog(LayoutController.class);
	
	private static final String _SOLVER_BASE_DIR = "/EDISON/SOLVERS";
	private static final String _DEFAULT_CLUSTER = "EDISON-CFD";
	private static final String _DEFAULT_CYBERLAP_ID = " ";
	private static final String _DEFAULT_CLASS_ID = " ";
	private static final String _DEFAULT_JOB_TITLE = "job";
	private static final String _DEFAULT_JOB_DESCRIPTION = " ";
	
	private static final String _DEFAULT_TEMP_DIR="/EDISON/LDAP/TEMP";
	
	private static final HashMap<String,String> ProvenanceSupportApp = new HashMap<String,String>();
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		String workbenchType = ParamUtil.getString(request, "workbenchType", "SIMULATION_WITH_APP");
		long customId =  ParamUtil.getLong(request, "customId", 0);
		long classId =  ParamUtil.getLong(request, "classId", 0);
		long scienceAppId =  ParamUtil.getLong(request, "scienceAppId", 0);
		String redirectURL = ParamUtil.getString(request, "redirectURL", "");
		String redirectName = ParamUtil.getString(request, "redirectName", "");
		String jobUuid = ParamUtil.getString(request, "jobUuid", "");
		
//		ProvenanceSupportApp.put("uChem", "4.0.1");
//		ProvenanceSupportApp.put("pianostring", "1.0.0");
//		ProvenanceSupportApp.put("PhaseDiagramSW", "1.0.0");
//		ProvenanceSupportApp.put("gravityslingshot", "1.0.0");
//		ProvenanceSupportApp.put("WaveSimulation", "1.0.0");
//		ProvenanceSupportApp.put("Bowling", "1.0.0");
//		ProvenanceSupportApp.put("acuteSTMtip", "1.0.0");
//		ProvenanceSupportApp.put("roundSTMtip", "1.0.0");
		
		try{
			model = this.evaluateScienceAppLayout(model, scienceAppId);
			
			model.addAttribute("workbenchType", workbenchType);
			model.addAttribute("customId", customId);
			model.addAttribute("classId", classId);
			model.addAttribute("redirectURL", HttpUtil.decodeURL(HttpUtil.decodeURL(redirectURL)));
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("jobUuid", jobUuid);
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
			}else if ( command.equalsIgnoreCase("CREATE_SIMULATION") ){
				this.createSimulation(request, response);
			}else if( command.equalsIgnoreCase("DELETE_SIMULATION") ){
				this.deleteSimulation(request, response);
			}else if( command.equalsIgnoreCase("DELETE_JOB") ){
				this.deleteJob(request, response);
			}else if( command.equalsIgnoreCase("CREATE_JOB") ){
				this.createJob(request, response);
			}else if( command.equalsIgnoreCase("LOAD_SIMULATION") ){
				this.loadSimulation(request, response);
			}else if( command.equalsIgnoreCase("SAVE_SIMULATION")){
				this.saveSimulation(request, response);
			}else if( command.equalsIgnoreCase("SUBMIT_JOBS")){
				RequestUtil.getParameterMap(request);
				this.submitJobs(request, response);
			}else if( command.equalsIgnoreCase("CHECK_PROVENANCE")){
				this.provenanceCheckJob(request, response);
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
	
	private void resolveLayoutTemplate(  ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException{
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
	
	private SimpleSequence layoutJSONObject2Map( JSONArray jsonColumns ) throws JSONException{
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

	private ModelMap evaluateScienceAppLayout(ModelMap model,long scienceAppId) throws SimulationWorkbenchException{
		ScienceApp scienceApp = null;
		try{
			scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			model.addAttribute("scienceApp", scienceApp);
			
			if(scienceApp.getTempletId().indexOf("flow")>-1){
				model.addAttribute("isFlowLayout", true);
			}else{
				model.addAttribute("isFlowLayout", false);
			}
			
			boolean isProvenance = false;
			String isProvenanceSupportApp = CustomUtil.strNull(ProvenanceSupportApp.get(scienceApp.getName().trim()));
			if(!isProvenanceSupportApp.equals("")&&isProvenanceSupportApp.equals(scienceApp.getVersion().trim())){
				isProvenance = true;
			}
			model.addAttribute("isProvenance", isProvenance);
			
			
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
	
	private void saveSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		long classId = ParamUtil.getLong(resourceRequest, "srcClassCode");
		long customId = ParamUtil.getLong(resourceRequest, "srcClassId");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		JSONArray jobs = null;
		try {
			jobs = JSONFactoryUtil.createJSONArray(ParamUtil.getString(resourceRequest, "jobs" ));
		} catch (JSONException e) {
			_log.error("JSONFactoryUtil: "+ParamUtil.getString(resourceRequest, "jobs"));
			ServletResponseUtil.write(httpResponse, String.valueOf(false));
			throw new PortletException();
		}
		
		int jobCount = jobs.length();
		for( int i=0; i<jobCount; i++){
			JSONObject jsonJob = jobs.getJSONObject(i);
			String jobUuid = jsonJob.getString("uuid_", "");
			JSONArray jobData =  jsonJob.getJSONArray("inputs_");
//			this.jsonArrayPrint(jobData);
			
			try {
				SimulationJobDataLocalServiceUtil.modifySimulationJobData(jobUuid, jobData.toString());
			} catch (SystemException e) {
				e.printStackTrace();
				ServletResponseUtil.write(httpResponse, String.valueOf(false));
				throw new PortletException();
			}
			
		}
		ServletResponseUtil.write(httpResponse, String.valueOf(true));
	}
	
	private void loadSimulation(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		Simulation simulation = null;
		try {
			simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
		} catch (NoSuchSimulationException | SystemException e) {
			_log.error("Getting simulation: "+e.getMessage());
			throw new PortletException();
		}
		
		JSONObject jsonSimulation = this.convertSimulationToJSON(simulation);
		ServletResponseUtil.write(httpResponse, jsonSimulation.toString() );

	}
	
	private JSONObject loadJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws JSONException, SystemException, NoSuchSimulationException, NoSuchSimulationJobException{
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		
		SimulationJob job = SimulationJobLocalServiceUtil.getJob(jobUuid);
		
		SimulationJobData jobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(job.getJobUuid());
		
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("job_", this.convertJobToJSON(job));
		if( jobData != null ){
			jsonObj.put("inputs_", jobData.getJobData());
		}
		return jsonObj;
	}
	
	private JSONObject convertSimulationToJSON( Simulation simulation ){
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
	
	private JSONObject convertJobToJSON( SimulationJob job ) throws SystemException, JSONException{
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		json.put("uuid_", job.getJobUuid());
		json.put("title_", job.getJobTitle());
		if(  job.getJobStartDt() != null )
			json.put("startTime_", job.getJobStartDt());
		if( job.getJobEndDt() != null )
			json.put("endTime_", job.getJobEndDt());
		json.put("submit_", job.getJobSubmit());
		
		int jobStatus = (int) job.getJobStatus();
		switch ( jobStatus ){
		case 0:
			json.put("status_", "INITIALIZED");
		  break;
		case 1701001:
			json.put("status_", "Unknown");
			break;
		case 1701002:
			json.put("status_", "INITIALIZE_FAILED");
			break;
		case 1701003:
			json.put("status_", "INITIALIZED");
			break;
		case 1701004:
			json.put("status_", "SUBMISSION_FAILED");
			break;
		case 1701005:
			json.put("status_", "QUEUED");
			break;
		case 1701006:
			json.put("status_", "RUNNING");
			break;
		case 1701007:
			json.put("status_", "SUSPEND_REQUESTED");
			break;
		case 1701008:
			json.put("status_", "SUSPENDED");
			break;
		case 1701009:
			json.put("status_", "CANCEL_REQUESTED");
			break;
		case 1701010:
			json.put("status_", "CANCELED");
			break;
		case 1701011:
			json.put("status_", "SUCCESS");
			break;
		case 1701012:
			json.put("status_", "FAILED");
			break;
		}
		
		return json;
	}
	
	
	private JSONObject getDataStructure( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException, JSONException{
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
	
	
	private void createSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		Long scienceAppId = ParamUtil.getLong(resourceRequest, "scienceAppId");
		String scienceAppName = ParamUtil.getString(resourceRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(resourceRequest, "scienceAppVersion");
		long srcClassCode = ParamUtil.getLong(resourceRequest, "srcClassCode");
		long srcClassId = ParamUtil.getLong(resourceRequest, "srcClassId");
		String title = ParamUtil.getString(resourceRequest, "title");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		Group group = themeDisplay.getScopeGroup();
		
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(resourceRequest);
			//sc.setUserId( user.getUserId() );
			sc.setCreateDate( new Date() );
			sc.setModifiedDate( new Date() );
		} catch (PortalException | SystemException e) {
			_log.error("Creating ServiceContext Failed: "+Simulation.class.getName());
			throw new PortletException();
		}
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		IBAgent agent = new IBAgent(group, user);
		String simulationUuid = "";
		try {
			simulationUuid = agent.createSimulation();
		} catch (SystemException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Simulation simulation = null;
		try {
			simulation = SimulationLocalServiceUtil.addSimulation(simulationUuid, title, scienceAppId, scienceAppName, scienceAppVersion, srcClassCode, srcClassId, sc);
		} catch (SystemException e) {
			_log.error("Adding new Simulation Failed: "+title);
			throw new PortletException();
		} 
		
		JSONObject jsonSimulation = this.convertSimulationToJSON(simulation);
		
		try {
			String jobTitle = ParamUtil.getString(resourceRequest, "jobTitle");
			String jobInitData = ParamUtil.getString(resourceRequest, "jobInitData");
			
			this.createJob(simulation.getSimulationUuid(), scienceAppName, scienceAppVersion, sc, jobTitle, jobInitData);
		} catch (JSONException | SystemException e1) {
			_log.error("Creating job : "+simulation.getSimulationUuid());
			throw new PortletException();
		}
		ServletResponseUtil.write(httpResponse, jsonSimulation.toString());
	}
	
	private void deleteSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		try {
			SimulationLocalServiceUtil.deleteSimulation(simulationUuid);
		} catch (NoSuchSimulationException | SystemException e) {
			_log.error("Deleting simulation: "+simulationUuid);
			throw new PortletException();
		}
	}
	
	private JSONObject createJob( String simulationUuid, String scienceAppName, String scienceAppVersion, ServiceContext sc,String jobTitle,String jobInitData) throws PortletException, JSONException, SystemException{
		SimulationJob job = null;
		
		try {
			job = SimulationLocalServiceUtil.addJob(simulationUuid, scienceAppName, scienceAppVersion, sc);
			
			if(!jobTitle.equals("")){
				job.setJobTitle(jobTitle);
				SimulationJobLocalServiceUtil.updateSimulationJob(job);
				
				if( Validator.isNotNull(jobInitData) ){
					System.out.println("+++++++++Job Init Data: \n "+jobInitData);
					try {
						SimulationJobDataLocalServiceUtil.modifySimulationJobData(job.getJobUuid(), jobInitData);
					} catch (SystemException e) {
						_log.error("Adding job data: "+e.getMessage());
						throw new PortletException();
					}
				}
			}
		} catch (SystemException e) {
			_log.error("Adding New Job Failed For:  "+ simulationUuid);
			throw new PortletException();
		}
		return this.convertJobToJSON(job);
	}
	
	private void deleteJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		try {
			SimulationJobLocalServiceUtil.deleteJob(jobUuid);
		} catch (SystemException e) {
			_log.error("Deleting job: "+jobUuid );
			throw new PortletException();
		} catch (NoSuchSimulationJobException e) {
			_log.warn("Deleting job with invalid uuid: "+jobUuid );
		}
	}
	
	private void createJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		String title = ParamUtil.getString(resourceRequest, "title");
		String scienceAppName = ParamUtil.getString(resourceRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(resourceRequest, "scienceAppVersion");
		String initData = ParamUtil.getString(resourceRequest, "initData", "");
		
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(SimulationJob.class.getName(), resourceRequest);
			//sc.setUserId( user.getUserId() );
			sc.setCreateDate( new Date() );
			sc.setModifiedDate( new Date() );
		} catch (PortalException | SystemException e) {
			_log.error("Creating ServiceContext Failed: "+Simulation.class.getName());
			throw new PortletException();
		}
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		SimulationJob job = null;
		try {
			job = SimulationLocalServiceUtil.addJob(simulationUuid, scienceAppName, scienceAppVersion, sc);
			job.setJobTitle(title);
			SimulationJobLocalServiceUtil.updateSimulationJob(job);
		} catch ( SystemException e) {
			_log.error("Adding job: "+e.getMessage());
			throw new PortletException();
		}
		
		if( Validator.isNotNull(initData) ){
			System.out.println("+++++++++Job Init Data: \n "+initData);
			try {
				SimulationJobDataLocalServiceUtil.modifySimulationJobData(job.getJobUuid(), initData);
			} catch (SystemException e) {
				_log.error("Adding job data: "+e.getMessage());
				throw new PortletException();
			}
		}
		
		JSONObject jsonJob = null;
		try {
			jsonJob = this.convertJobToJSON(job);
		} catch (JSONException | SystemException e) {
			_log.error("Converting job to JSON: "+e.getMessage());
			throw new PortletException();
		}
		
		ServletResponseUtil.write(httpResponse, jsonJob.toString() );
	}
	
	private void provenanceCheckJob( PortletRequest request, PortletResponse response) throws JSONException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		JSONArray job = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "jobParameter" ));
		System.out.println(job.getJSONObject(0).getJSONArray("inputs_"));
	}
	
	
	protected void submitJobs( PortletRequest portletRequest, PortletResponse portletResponse ) throws PortletException, IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		User user = themeDisplay.getUser();
		Group group = themeDisplay.getScopeGroup();
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		
		
		String userScreenName = user.getScreenName();
		if( userScreenName.equalsIgnoreCase("edison") )
			userScreenName = "edisonadm";
		
		IBAgent ibAgent = new IBAgent(group, user);
		
		String simulationUuid = ParamUtil.getString(portletRequest, "simulationUuid");
		String scienceAppName = ParamUtil.getString(portletRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(portletRequest, "scienceAppVersion");
		
		JSONArray jobs = null;
		try {
			jobs = JSONFactoryUtil.createJSONArray(ParamUtil.getString(portletRequest, "jobs" ));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ScienceApp scienceApp = null;
		try {
			scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppName, scienceAppVersion);
		} catch (NoSuchScienceAppException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// Other common parameters for all jobs
		String runType = scienceApp.getRunType();
		Map<String, String> mpiAttributes = new HashMap<>();
		if( !runType.equalsIgnoreCase("SEQUENTIAL") ){
			String ncores = ParamUtil.getString(portletRequest, "ncores");
			mpiAttributes.put("np", ncores);
		}
		
		
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(portletRequest);
			//sc.setUserId( user.getUserId() );
			sc.setCreateDate( new Date() );
			sc.setModifiedDate( new Date() );
		} catch (PortalException | SystemException e) {
			_log.error("Creating ServiceContext Failed: "+Simulation.class.getName());
			throw new PortletException();
		}
		
		
		//executable of IB
		String exePath = Paths.get(_SOLVER_BASE_DIR).resolve(scienceApp.getBinPath()).toString();
		
		String mpiType = scienceApp.getParallelModule();
		String[] dependencies = null;
		String cluster = _DEFAULT_CLUSTER;
		String cyberLabId = _DEFAULT_CYBERLAP_ID; // deprecated
		String classId = _DEFAULT_CLASS_ID; // deprecated
		
		//Set execution and files
		Map<String, String> files = new LinkedHashMap<>();
		Map<String, JSONObject> progArgs = new LinkedHashMap<>();
		
		JSONArray submitedJobs = JSONFactoryUtil.createJSONArray();
		System.out.println("jobs.length()-->"+jobs.length());
		int jobCount = jobs.length();
		for( int i=0; i<jobCount; i++){
			JSONObject jsonJob = jobs.getJSONObject(i);
			SimulationJob job = null;
			String jobUuid = jsonJob.getString("uuid_", "");
			boolean isJobSubmitted = jsonJob.getBoolean("submit_");
			System.out.println("Job UUID to be submitted: "+jobUuid);
			if( jobUuid.isEmpty() || isJobSubmitted ){
				try {
					job = SimulationLocalServiceUtil.addJob(simulationUuid, scienceAppName, scienceAppVersion, sc);
				} catch (SystemException e) {
					_log.error("Job creation failed: "+jobUuid);
					throw new IOException();
				}
			}
			else{
				try {
					System.out.println("SimulationJobLocalServiceUtil.getJob(): "+jobUuid);
					job = SimulationJobLocalServiceUtil.getJob(jobUuid);
				} catch (NoSuchSimulationJobException | SystemException e2) {
					_log.error("Getting job failed: "+jobUuid);
					throw new IOException();
				}
			}
			
			jobUuid = job.getJobUuid();
			String jobSeqNo = String.valueOf(job.getJobSeqNo());
			System.out.println("Job Sequence No.: "+jobSeqNo);
			
			JSONArray jobData =  jsonJob.getJSONArray("inputs_");
			this.jsonArrayPrint(jobData);
			
			Date date = new Date();
			for( int dataIndex = 0; dataIndex<jobData.length(); dataIndex++){
				JSONObject inputData = jobData.getJSONObject(dataIndex);
				this.jsonObjectPrint(inputData);
				String portName = inputData.getString("portName_");
				String pathType = inputData.getString("type_");
				
				if( pathType.equalsIgnoreCase("fileContent") ){
					String inputParent = inputData.getString("parent_", "");
					String inputFileName = inputData.getString("name_", "");
					Path parentPath = null;
					if( Validator.isNull(parentPath) ){
						SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
						parentPath = Paths.get(dateForm.format(date));
					}
					else{
						parentPath = Paths.get(inputParent);
					}
					
					if( Validator.isNull(inputFileName) ){
						inputFileName = portName.replaceAll("-", "");
					}
					
					String content = inputData.getString("context_");
					
					try {
						String fileId = ibAgent.uploadFileContent( 
								portletRequest, 
								content, 
								Paths.get(parentPath.toString(), 
										inputFileName).toString(), _DEFAULT_CLUSTER);
						System.out.println("File Id After IB Upload: "+fileId);
						
						JSONObject argVal = JSONFactoryUtil.createJSONObject();
						argVal.put("type", "FILE_ID");
						argVal.put("value", fileId);
						progArgs.put(portName, argVal);
						
						files.put(portName, fileId);
						inputData.put("type_", "file");
						inputData.put("parent_", parentPath.toString());
						inputData.put("name_", inputFileName);
						inputData.remove("context_");
					} catch (JSONException | SystemException e) {
						e.printStackTrace();
					} catch (PortalException e) {
						e.printStackTrace();
					}
				}else if( pathType.equalsIgnoreCase("dlEntryId")){
					long fileEntryId = inputData.getLong("id_");
					String inputParent = inputData.getString("parent_", "");
					String inputFileName = inputData.getString("name_", "");
					Path parentPath = null;
					if( Validator.isNull(parentPath) ){
						SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
						parentPath = Paths.get(dateForm.format(date));
					}
					else{
						parentPath = Paths.get(inputParent);
					}
					
					if( Validator.isNull(inputFileName) ){
						try {
							FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
							inputFileName = fileEntry.getTitle();
						} catch (PortalException | SystemException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					String fileId = "";
					try {
						fileId = ibAgent.uploadDLEntryFile(
								portletRequest, 
								fileEntryId, 
								parentPath.resolve(inputFileName).toString(), 
								_DEFAULT_CLUSTER);
					} catch (PortalException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					JSONObject argVal = JSONFactoryUtil.createJSONObject();
					argVal.put("type", "FILE_ID");
					argVal.put("value", fileId);
					progArgs.put(portName, argVal);
					
					files.put(portName, fileId);
					
					inputData.put("type_", "file");
					inputData.put("parent_", parentPath.toString());
					inputData.put("name_", inputFileName);
					inputData.remove("id_");
				}else if( pathType.equalsIgnoreCase("file")){
					Path target = Paths.get(inputData.getString("parent_")).resolve(inputData.getString("name_"));
					
					System.out.println("Port Data get IB File ID: "+target.toString());
					try {
						String fileId =  ibAgent.getFileId( portletRequest, target.toString(), OSPRepositoryTypes.USER_HOME.toString());
						
						JSONObject argVal = JSONFactoryUtil.createJSONObject();
						argVal.put("type", "FILE_ID");
						argVal.put("value", fileId);
						progArgs.put(portName, argVal);
						
						files.put(portName, fileId);
					} catch (JSONException | SystemException e) {
						e.printStackTrace();
					} catch (PortalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if ( pathType.equalsIgnoreCase("folder")){
					
				}else if ( pathType.equalsIgnoreCase("ext")){
					
				}else if ( pathType.equalsIgnoreCase("uri")){
					
				}else if( pathType.equalsIgnoreCase("context") ){
					JSONObject argVal = JSONFactoryUtil.createJSONObject();
					argVal.put("type", "STRING");
					argVal.put("value", inputData.getString("context_"));
					progArgs.put(portName, argVal);
					
					files.put(portName, inputData.getString("context_"));
				}else{
					_log.error("Un-defined path type: " + pathType);
					throw new PortletException();
				}
				
				inputData.put( "dirty_", false);
			}
			
			/*
			JSONObject result = ibAgent.submit(
					simulationUuid, 
					runType, 
					mpiType, 
					_DEFAULT_JOB_TITLE, 
					_DEFAULT_JOB_DESCRIPTION, 
					String.valueOf(scienceApp.getScienceAppId() ), 
					scienceAppName, 
					_DEFAULT_CYBERLAP_ID, 
					_DEFAULT_CLASS_ID, 
					exePath, 
					dependencies, 
					progArgs,
					cluster, 
					mpiAttributes, 
					this.getJobStatusCallbackURL( portletRequest, simulationUuid, job.getJobSeqNo())
			);
			
			this.jsonObjectPrint(result);
			
			if( result.getInt("error") > 0 ){
				_log.error("Job submission failed: "+ result.getInt("error"));
			}else{
				JSONObject submittedJob = JSONFactoryUtil.createJSONObject();
				submittedJob.put("tempUuid", job.getJobUuid());
				submittedJob.put("uuid", result.getString("uuid"));
				
				job.setJobUuid(result.getString("uuid"));
				job.setJobStartDt( new Date(result.getLong("submitTime")) );
				job.setJobStatus(Integer.valueOf(result.getString("status")));
				job.setJobSubmit(true);
				
				try {
					SimulationJobLocalServiceUtil.updateSimulationJob(job);
					SimulationJobDataLocalServiceUtil.replaceJobData(jobUuid, job.getJobUuid(), jobData.toString());
					System.out.println("+++++Replaced Job Data ");
					System.out.println(jobData.toString());
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				submitedJobs.put( submittedJob );
			}
			*/
		} //END jobCount FOR
		
		ServletResponseUtil.write(httpResponse, submitedJobs.toString());
	}
	
	protected void jsonObjectPrint( JSONObject jsonObject ){
		try {
			System.out.println(jsonObject.toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void jsonArrayPrint( JSONArray jsonArray ){
		try {
			System.out.println(jsonArray.toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getJobStatusCallbackURL( PortletRequest portletRequest, String simulationUuid, long jobSeqNo ){
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portalUrl = "";
		
		if(themeDisplay.isSecure()){ 
			portalUrl += "https://"; 
		}else{ 
			portalUrl += "http://"; 
		} 
		
		String serverName = themeDisplay.getServerName(); 
		String virtualHostName = themeDisplay.getCompany().getVirtualHostname(); 

		if(serverName.equals(virtualHostName)){ 
			portalUrl += virtualHostName; 
		}else{ 
			portalUrl += serverName+":"+themeDisplay.getServerPort();
		}
		
		Simulation simulation = null;
        try{
            simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
        }catch (NoSuchSimulationException | SystemException e){
            _log.error("no simulation", e);
        }
		
		//portalUrl = "http://150.183.247.221:8080";
		
		String url = portalUrl +_callbackAPI;
		if(simulation == null){
		    url = HttpUtil.addParameter(url, "gid", themeDisplay.getScopeGroupId());
		}else{
		    url = HttpUtil.addParameter(url, "gid", simulation.getGroupId());
		}
		url = HttpUtil.addParameter(url, "simulationUuid", simulationUuid); 
		url = HttpUtil.addParameter(url, "jobSeqNo", jobSeqNo); 
		
		System.out.println("Callback: "+url);
		return url;
	}
	
	private static final String _callbackAPI = "/api/jsonws/edison-simulation-portlet.simulationjob/update-simulation-job";
}


