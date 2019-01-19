package com.kisti.osp.workbench.portlet.simulation.layout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.kisti.osp.service.OSPFileLocalServiceUtil;
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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
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
	
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		String workbenchType = ParamUtil.getString(request, "workbenchType", "SIMULATION_WITH_APP");
		long customId =  ParamUtil.getLong(request, "customId", 0);
		long classId =  ParamUtil.getLong(request, "classId", 0);
		long scienceAppId =  ParamUtil.getLong(request, "scienceAppId", 0);
		String redirectURL = ParamUtil.getString(request, "redirectURL", "");
		String redirectName = ParamUtil.getString(request, "redirectName", "");
		String simulationUuid = ParamUtil.getString(request, "simulationUuid", "");
		String jobUuid = ParamUtil.getString(request, "jobUuid", "");
		String blockInputPorts = ParamUtil.getString(request, "blockInputPorts", "");
		/*From WorkFlow Parameter*/
		String nodeId = ParamUtil.getString(request, "nodeId", "");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			model = this.evaluateScienceAppLayout(model, scienceAppId);
			
			model.addAttribute("workbenchType", workbenchType);
			model.addAttribute("customId", customId);
			model.addAttribute("classId", classId);
			model.addAttribute("redirectURL", HttpUtil.decodeURL(HttpUtil.decodeURL(redirectURL)));
			model.addAttribute("redirectName", redirectName);
			model.addAttribute("jobUuid", jobUuid);
			model.addAttribute("blockInputPorts", blockInputPorts);
			model.addAttribute("simulationUuid", simulationUuid);
			model.addAttribute("currentUserName", themeDisplay.getUser().getScreenName());
			model.addAttribute("nodeId", nodeId);
			
			
			IBAgent agent = new IBAgent(themeDisplay.getScopeGroup(), themeDisplay.getUser());
			agent.ibAgentLog();
			
			
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
				this.getDataStructure(request, response);
			}else if ( command.equalsIgnoreCase("GET_DATA_STRUCTURE_WITH_DATA") ){
				this.getDataStructureWithData(request, response);
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
			}else if( command.equalsIgnoreCase("GET_DATATYPE_SAMPLE")){
				this.getDataTypeSample(request, response);
			}else if( command.equalsIgnoreCase("READ_DLENTRY")){
				this.readDLEntry(request, response);
			}else if( command.equalsIgnoreCase("READ_DATATYPE_SAMPLE")){
				this.readDataTypeSample(request, response);
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
		long scienceAppId = ParamUtil.getLong(resourceRequest, "scienceAppId");
		
		
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		Boolean isStepWorkbench  =  scienceApp.getIsStepLayout();
		JSONObject workbenchLayouts = JSONFactoryUtil.createJSONObject(scienceApp.getLayout());
		JSONArray layoutKeys = workbenchLayouts.getJSONArray("arrayKeys_");
		
		
		/*FreeMaker Template Parameter Setting*/
		Map<String, Object> params = new HashMap<>();
		params.put("namespace", namespace);
		params.put("isStepWorkbench", scienceApp.getIsStepLayout());
		String templateFile = "";
		String layoutKey = "";
		if(isStepWorkbench){
			templateFile = "step-layout.ftl";
			for(int i=0;i<layoutKeys.length();i++){
				String key = layoutKeys.getString(i);
				JSONObject layout = workbenchLayouts.getJSONObject(key);
				String templateParameterKey = key+"_FILE_PATH";
				String templateParameterValue = layout.getString("templateId_")+".ftl";
				params.put(templateParameterKey, templateParameterValue);
			}
			layoutKey = "SYSTEM";
		}else{
			JSONObject layout = workbenchLayouts.getJSONObject("LAYOUT");
			templateFile = layout.getString("templateId_")+".ftl";
			layoutKey = "LAYOUT";
		}
		params.put("layoutKey", layoutKey+"_");
		
		
		
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
			/*Cluster*/
			scienceApp.setCluster(CustomUtil.strNull(scienceApp.getCluster(), _DEFAULT_CLUSTER));
			model.addAttribute("scienceApp", scienceApp);
			if(scienceApp.getIsStepLayout()){
				model.addAttribute("isFlowLayout", true);
			}else{
				model.addAttribute("isFlowLayout", false);
			}
		}catch (Exception e) {
			throw new SimulationWorkbenchException(SimulationWorkbenchException.NO_SCIENCEAPP_ID);
		}
		
		
		try{
			JSONObject workbenchLayouts = JSONFactoryUtil.createJSONObject(scienceApp.getLayout());
			JSONArray layoutKeys = workbenchLayouts.getJSONArray("arrayKeys_");
			
			
			JSONObject lodingPortlets = JSONFactoryUtil.createJSONObject();
			
			JSONObject totalLayout = JSONFactoryUtil.createJSONObject();
			JSONArray totalColumns = JSONFactoryUtil.createJSONArray();
			for(int i=0;i<layoutKeys.length();i++){
				String key = layoutKeys.getString(i);
				JSONObject layout = workbenchLayouts.getJSONObject(key);
				JSONArray jsonColumns = layout.getJSONArray("columns_");
				for(int j=0;j<jsonColumns.length();j++){
					
					JSONObject column = jsonColumns.getJSONObject(j);
					//ID를 유니크 하게 변경
					column.put("id_", key+"_"+column.getString("id_"));
					totalColumns.put(column);
					if(!column.isNull("currentPortlet_")){
						lodingPortlets.put(column.getString("currentPortlet_"), "view");
					}
				}
			}
			
			totalLayout.put("columns_", totalColumns);
			model.addAttribute("totalLayout", totalLayout);
			
			System.out.println(totalLayout);
			
			model.addAttribute("lodingPortlets", lodingPortlets.toString());
			model.addAttribute("workbenchLayout", workbenchLayouts);
		}catch (Exception e) {
			e.printStackTrace();
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
	
	private JSONObject loadJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws SystemException, PortalException{
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		
		SimulationJob job = SimulationJobLocalServiceUtil.getJob(jobUuid);
		
		SimulationJobData jobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(job.getJobUuid());
		
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(job.getSimulationUuid());
		long userId = PortalUtil.getUser(resourceRequest).getUserId();
		
		boolean isEdit = false;
		if(simulation.getUserId()==userId){
			isEdit=true;
		}
		
		
		
		String userScreenName = UserLocalServiceUtil.getUser(simulation.getUserId()).getScreenName();
		jsonObj.put("job_", this.convertJobToJSON(job,isEdit,userScreenName));
		
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
	
	private JSONObject convertJobToJSON( SimulationJob job,boolean isEdit,String screenName) throws SystemException, JSONException{
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		json.put("uuid_", job.getJobUuid());
		json.put("isEdit_", isEdit);
		json.put("user_", screenName);
		json.put("title_", job.getJobTitle());
		json.put("seqNo_", job.getJobSeqNo());
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
	
	protected void getDataStructureWithData( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
		String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName");
		String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion");
		String parentPath = ParamUtil.getString(resourceRequest, "parentPath");
		String fileName = ParamUtil.getString(resourceRequest, "fileName");
		String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType",OSPRepositoryTypes.USER_HOME.toString());
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		DataType dataType = null;
		JSONObject result = JSONFactoryUtil.createJSONObject();
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
			throw new PortletException("No Data Structure: "+dataTypeName);
		}
		
		byte[] fileContent = null;
		try {
			fileContent = OSPFileLocalServiceUtil.readFileContent(resourceRequest, Paths.get(parentPath).resolve(fileName).toString(), repositoryType);
		} catch (PortalException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result.put("dataStructure", dataTypeStructure.getStructure());
		result.put("structuredData", new String(fileContent) );
		
		this.jsonObjectPrint(result);
		ServletResponseUtil.write(httpResponse, result.toString());
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
			
			String jobOwenerName = ParamUtil.getString(resourceRequest, "jobOwenerName");
			String userScreenName = themeDisplay.getUser().getScreenName();
			
			this.createJob(simulation.getSimulationUuid(), jobOwenerName, userScreenName, scienceAppName, scienceAppVersion, sc, jobTitle, jobInitData);
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
	
	private String copyJobInitDataConvertor(String jobOwenerName, String userScreenName, String jobInitData){
		if(jobOwenerName.equals("")){
			return jobInitData;
		}
		
		if(jobOwenerName.equals(userScreenName)){
			return jobInitData;
		}
		
		try {
			JSONArray jobInitDatas = JSONFactoryUtil.createJSONArray(jobInitData);
			for(int i=0; i<jobInitDatas.length();i++){
				JSONObject jobData = jobInitDatas.getJSONObject(i);
				
				JSONObject newJobData = OSPFileLocalServiceUtil.setJobDataWithFileFormInputData(jobOwenerName, jobData, userScreenName);
				
				jobInitDatas.getJSONArray(i).put(newJobData);
			}
			
			jobInitData = jobInitDatas.toString();
		} catch (PortalException | SystemException | IOException e) {
			e.printStackTrace();
		} finally {
			return jobInitData;
		}
	}
	
	private JSONObject createJob( String simulationUuid, String jobOwenerName, String userScreenName, String scienceAppName, String scienceAppVersion, ServiceContext sc,String jobTitle,String jobInitData) throws PortletException, JSONException, SystemException{
		SimulationJob job = null;
		
		try {
			job = SimulationLocalServiceUtil.addJob(simulationUuid, sc);
			
			if(!jobTitle.equals("")){
				job.setJobTitle(jobTitle);
				SimulationJobLocalServiceUtil.updateSimulationJob(job);
				
				if( Validator.isNotNull(jobInitData) ){
					System.out.println("+++++++++Job Init Data: \n "+jobInitData);
					try {
						jobInitData = this.copyJobInitDataConvertor(jobOwenerName, userScreenName, jobInitData);
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
		return this.convertJobToJSON(job,true,userScreenName);
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
		String initData = ParamUtil.getString(resourceRequest, "initData", "");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String userScreenName = themeDisplay.getUser().getScreenName();
		
		
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
			job = SimulationLocalServiceUtil.addJob(simulationUuid, sc);
			job.setJobTitle(title);
			SimulationJobLocalServiceUtil.updateSimulationJob(job);
		} catch ( SystemException e) {
			_log.error("Adding job: "+e.getMessage());
			throw new PortletException();
		}
		
		if( Validator.isNotNull(initData) ){
			System.out.println("+++++++++Job Init Data: \n "+initData);
			try {
				String jobOwenerName = ParamUtil.getString(resourceRequest, "jobOwenerName", "");
				initData = this.copyJobInitDataConvertor(jobOwenerName, userScreenName, initData);
				SimulationJobDataLocalServiceUtil.modifySimulationJobData(job.getJobUuid(), initData);
			} catch (SystemException e) {
				_log.error("Adding job data: "+e.getMessage());
				throw new PortletException();
			}
		}
		
		JSONObject jsonJob = null;
		try {
			
			jsonJob = this.convertJobToJSON(job,true,userScreenName);
		} catch (JSONException | SystemException e) {
			_log.error("Converting job to JSON: "+e.getMessage());
			throw new PortletException();
		}
		
		ServletResponseUtil.write(httpResponse, jsonJob.toString() );
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
	
	protected void readDataTypeSample( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
		String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName");
		String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		DataType dataType;
		JSONObject result = JSONFactoryUtil.createJSONObject();
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
		
		long sampleDLEntryId = Long.parseLong(dataType.getSamplePath());
		System.out.println("Data Type Sample ID: "+ dataType.getSamplePath() );
		if( sampleDLEntryId > 0 ){
			String fileContent = this.readDLFileContent(sampleDLEntryId);
			if( dataTypeStructure == null ){
				result.put("contentType", "fileContent");
				result.put("fileContent", fileContent);
			}
			else{
				result.put("contentType", "structuredData");
				result.put("fileContent", fileContent);
				result.put("dataStructure", dataTypeStructure.getStructure());
			}
		}
		else{
			if( dataTypeStructure == null ){
				result.put("error", "Data type ["+dataTypeName+"-"+dataTypeVersion+"] has no sample file.");
			}
			else{
				result.put("contentType", "structuredData");
				result.put("dataStructure", dataTypeStructure.getStructure());
			}
		}

		this.jsonObjectPrint(result);
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
	
	
	protected void getDataTypeSample( ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
		String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName");
		String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		DataType dataType;
		JSONObject sample = JSONFactoryUtil.createJSONObject();
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
			sample.put("dlEntryId", dataType.getSamplePath());
		}
		else{
			sample.put("dataStructure", dataTypeStructure.getStructure());
		}

		this.jsonObjectPrint(sample);
		ServletResponseUtil.write(httpResponse, sample.toString());
	}
	
	
	private void provenanceCheckJob( PortletRequest request, PortletResponse response) throws JSONException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		JSONArray job = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "jobParameter" ));
//		ProvDataLoader
		System.out.println(job.getJSONObject(0).getJSONArray("inputs_"));
	}
	
	
	protected void submitJobs( PortletRequest portletRequest, PortletResponse portletResponse ) throws PortletException, IOException, SystemException, PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		User user = themeDisplay.getUser();
		Group group = themeDisplay.getScopeGroup();
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(portletResponse);
		
		
		IBAgent ibAgent = new IBAgent(group, user);
		
		String simulationUuid = ParamUtil.getString(portletRequest, "simulationUuid");
		String scienceAppName = ParamUtil.getString(portletRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(portletRequest, "scienceAppVersion");
		
		JSONArray jobs = JSONFactoryUtil.createJSONArray(ParamUtil.getString(portletRequest, "jobs"));
		
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppName, scienceAppVersion);
		
		// Other common parameters for all jobs
		String runType = scienceApp.getRunType();
		Map<String, String> mpiAttributes = new HashMap<>();
		if( !runType.equalsIgnoreCase("SEQUENTIAL") ){
			String ncores = ParamUtil.getString(portletRequest, "ncores");
			mpiAttributes.put("np", ncores);
		}
		
		
		ServiceContext sc = ServiceContextFactory.getInstance(portletRequest);
		sc.setCreateDate(new Date());
		sc.setModifiedDate(new Date());
		
		//executable of IB
		String exePath = Paths.get(_SOLVER_BASE_DIR).resolve(scienceApp.getBinPath()).toString();
		
		String mpiType = scienceApp.getParallelModule();
		String[] dependencies = null;
		String cluster = CustomUtil.strNull(scienceApp.getCluster(), _DEFAULT_CLUSTER);
		
		//Set execution and files
		Map<String, String> files = new LinkedHashMap<>();
		Map<String, JSONObject> progArgs = new LinkedHashMap<>();
		
		//SubmitJob return
		String submittedJobUuid = "";
		String tempJobUuid = "";
		
		_log.info("jobs.length()-->"+jobs.length());
		
		int jobCount = jobs.length();
		for( int i=0; i<jobCount; i++){
			JSONObject jsonJob = jobs.getJSONObject(i);
			SimulationJob job = null;
			String jobUuid = jsonJob.getString("uuid_", "");
			boolean isJobSubmitted = jsonJob.getBoolean("submit_");
			System.out.println("Job UUID to be submitted: "+jobUuid);
			if( jobUuid.isEmpty() || isJobSubmitted ){
				try {
					job = SimulationLocalServiceUtil.addJob(simulationUuid, sc);
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
			_log.info("Job Sequence No.: "+jobSeqNo);
			
			JSONArray jobData =  jsonJob.getJSONArray("inputs_");
			this.jsonArrayPrint(jobData);
			
			Date date = new Date();
			for( int dataIndex = 0; dataIndex<jobData.length(); dataIndex++){
				/*App Execute Update*/
				this.incrementAppExecute(scienceApp);
				
				JSONObject inputData = jobData.getJSONObject(dataIndex);
				this.jsonObjectPrint(inputData);
				String portName = inputData.getString("portName_");
				String pathType = inputData.getString("type_");
				
				if( pathType.equalsIgnoreCase("fileContent")||pathType.equalsIgnoreCase("content") ){
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
					
					String content = inputData.getString("content_");
					
					String fileId = ibAgent.uploadFileContent( 
							portletRequest, 
							content, 
							Paths.get(parentPath.toString(), 
									inputFileName).toString(), cluster);
					_log.info("File Id After IB Upload: "+fileId);
					
					JSONObject argVal = JSONFactoryUtil.createJSONObject();
					argVal.put("type", "FILE_ID");
					argVal.put("value", fileId);
					progArgs.put(portName, argVal);
					
					files.put(portName, fileId);
					inputData.put("type_", "file");
					inputData.put("parent_", parentPath.toString());
					inputData.put("name_", inputFileName);
					inputData.remove("content_");
					
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
					
					String fileId = ibAgent.uploadDLEntryFile(
							portletRequest, 
							fileEntryId, 
							parentPath.resolve(inputFileName).toString(), 
							cluster);
					
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
					
					_log.info("Port Data get IB File ID: "+target.toString());
					
					String repositoryType = OSPRepositoryTypes.USER_HOME.toString();
					if(inputData.has("repositoryType_")){
						repositoryType = inputData.getString("repositoryType_");
					}
					
					String fileId =  ibAgent.getFileId( portletRequest, target.toString(), repositoryType);
					
					JSONObject argVal = JSONFactoryUtil.createJSONObject();
					argVal.put("type", "FILE_ID");
					argVal.put("value", fileId);
					progArgs.put(portName, argVal);
					
					files.put(portName, fileId);
					
				}else if ( pathType.equalsIgnoreCase("folder")){
					
				}else if ( pathType.equalsIgnoreCase("ext")){
					
				}else if ( pathType.equalsIgnoreCase("uri")){
					
				}else{
					_log.error("Un-defined path type: " + pathType);
					throw new PortletException();
				}
				
				/*String Editor 사용시 작성*/
//				if( pathType.equalsIgnoreCase("content") ){
//					JSONObject argVal = JSONFactoryUtil.createJSONObject();
//					argVal.put("type", "STRING");
//					argVal.put("value", inputData.getString("content_"));
//					progArgs.put(portName, argVal);
//					
//					files.put(portName, inputData.getString("content_"));
//				}
				
				inputData.put( "dirty_", false);
			}
			
			ibAgent.ibAgentLog();
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
					this.getJobStatusCallbackURL( portletRequest, simulationUuid, job.getJobSeqNo(), job.getGroupId())
			);
			
			this.jsonObjectPrint(result);
			
			if( result.getInt("error") > 0 ){
				_log.error("Job submission failed: "+ result.getInt("error"));
				throw new PortletException("Job submission failed: "+ result.getInt("error"));
			}else{
				if(submittedJobUuid.equals("")){
					submittedJobUuid = result.getString("uuid");
					tempJobUuid = job.getJobUuid();
				}
				
				job.setJobUuid(result.getString("uuid"));
				job.setJobStartDt( new Date(result.getLong("submitTime")) );
				job.setJobStatus(Integer.valueOf(result.getString("status")));
				job.setJobSubmit(true);
				job.setJobSubmitDt(new Date());
				
				try {
					SimulationJobLocalServiceUtil.updateSimulationJob(job);
					SimulationJobDataLocalServiceUtil.replaceJobData(jobUuid, job.getJobUuid(), jobData.toString());
					
					_log.info("+++++Replaced Job Data ");
					_log.info(jobData.toString());
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		} //END jobCount FOR
		
		//reload Job Setting
		JSONObject submittedJob = JSONFactoryUtil.createJSONObject();
		submittedJob.put("tempJobUuid", tempJobUuid);
		submittedJob.put("jobUuid", submittedJobUuid);
		submittedJob.put("jobSubmitCnt", jobCount);
		
		ServletResponseUtil.write(httpResponse, submittedJob.toString());
	}
	
	protected void jsonObjectPrint( JSONObject jsonObject ){
		try {
			_log.info("+++++jsonObjectPrint+++++");
			_log.info("\r\n"+jsonObject.toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void jsonArrayPrint( JSONArray jsonArray ){
		try {
			_log.info("+++++jsonArrayPrint+++++");
			_log.info("\r\n"+jsonArray.toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getJobStatusCallbackURL( PortletRequest portletRequest, String simulationUuid, long jobSeqNo, long jobGroupId){
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portalUrl = "";
		
		if(themeDisplay.isSecure()){ 
			portalUrl += "https://"; 
		}else{ 
			portalUrl += "http://"; 
		} 
		
		String serverName = themeDisplay.getServerName(); 
		String virtualHostName = themeDisplay.getCompany().getVirtualHostname(); 

		if(serverName.equals(virtualHostName)&&!ip(serverName)){ 
			portalUrl += virtualHostName;
		}else{ 
			portalUrl += serverName+":"+themeDisplay.getServerPort();
		}
		
		String url = portalUrl +_callbackAPI;
		url = HttpUtil.addParameter(url, "gid", jobGroupId);
		url = HttpUtil.addParameter(url, "simulationUuid", simulationUuid); 
		url = HttpUtil.addParameter(url, "jobSeqNo", jobSeqNo); 
		
		System.out.println("Callback: "+url);
		return url;
	}
	
	private void incrementAppExecute(ScienceApp scienceApp) throws SystemException{
		long preExecute = GetterUtil.getLong(scienceApp.getExecute(),0);
		scienceApp.setExecute(preExecute+1);
		ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
	}
	
	public static boolean ip(String text) {
		Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Matcher m = p.matcher(text);
		return m.find();
	}
	
	private static final String _callbackAPI = "/api/jsonws/edison-simulation-portlet.simulationjob/update-simulation-job";
}


