package com.kisti.osp.workbench.portlet.workbench;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
import org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException;
import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.kisti.osp.workbench.agent.ib.IBAgent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import freemarker.template.Configuration;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Portlet implementation class WorkbenchPortlet
 */
public class WorkbenchPortlet extends MVCPortlet {
	private static final String _templateDir = "/html/Workbench/templates/";
	private static final String DEFAULT_WORKBENCH_LAYOUT_TEMPLATE="DefaultWorkbenchTemplate";
	private static Log _log = LogFactoryUtil.getLog(WorkbenchPortlet.class);

	private static Pattern _columnIdPattern = Pattern.compile(
		"([<].*?id=[\"'])([^ ]*?)([\"'].*?[>])", Pattern.DOTALL);
	private static Pattern _processColumnPattern = Pattern.compile(
		"(processColumn[(]\")(.*?)(\"(?:, *\"(?:.*?)\")?[)])", Pattern.DOTALL);
	
	private static final String _SYSTEM_BASE_DIR = "/EDISON/LDAP/DATA";
	private static final String _SOLVER_BASE_DIR = "/EDISON/SOLVERS";
	private static final String _DEFAULT_CLUSTER = "EDISON-CFD";
	private static final String _DEFAULT_CYBERLAP_ID = " ";
	private static final String _DEFAULT_CLASS_ID = " ";
	private static final String _DEFAULT_JOB_TITLE = "job";
	private static final String _DEFAULT_JOB_DESCRIPTION = " ";
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String workbenchType = ParamUtil.getString(renderRequest, "workbenchType", "SIMULATION_WITH_APP");
		_log.info("workbench parameters");
		for(String key : renderRequest.getParameterMap().keySet()){
		    String[] values = (String[])renderRequest.getParameterMap().get(key);
		    for(String value : values){
		        _log.info(key + ": " + value);
		    }
		}
		
		long customId =  ParamUtil.getLong(renderRequest, "customId", 0);
		long classId =  ParamUtil.getLong(renderRequest, "classId", 0);
		
		if( workbenchType.equalsIgnoreCase("SIMULATION_WITH_APP")){
			try {
				this.evaluateScienceAppLayout(renderRequest, renderResponse, 0);
			} catch (SystemException e) {
				_log.error("workbench.doView: evaluateScienceAppLayout()");
				throw new PortletException();
			}
		}
		else if( workbenchType.equalsIgnoreCase("SIMULATION_RERUN")){
			String jobUuid = ParamUtil.getString(renderRequest, "jobUuid");
			
			SimulationJob job = null;
			try {
				job = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobUuid);
				_log.info("SIMULATION_RERUN job Info : " + job.getModelAttributes().toString());
				String simulationUUID = job.getSimulationUuid(); 
				Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUUID);
				String jobInputData = SimulationLocalServiceUtil.getJobData(jobUuid);
				this.evaluateScienceAppLayout(renderRequest, renderResponse, Long.valueOf(simulation.getScienceAppId()) );
				
				renderRequest.setAttribute("simulation", simulation);
				renderRequest.setAttribute("simulationJob", job);
				renderRequest.setAttribute("jobInputData", jobInputData);
				
			} catch (SystemException | PortalException e) {
				_log.debug("Gettin simulation info from job:  "+jobUuid);
				throw new PortletException();
			} 
		}
		else if( workbenchType.equalsIgnoreCase("CONTENT") ){
			String contentType = ParamUtil.getString(renderRequest, "contentType");
			String contentURL = ParamUtil.getString(renderRequest, "contentURL");
			String contentViewerId = ParamUtil.getString(renderRequest, "contentViewerId");
			float layoutHeight = ParamUtil.getFloat(renderRequest, "layoutHeight");
			
			renderRequest.setAttribute("contentURL", contentURL);
			renderRequest.setAttribute("contentType", contentType);
//			workbenchLayout = this.getContentLayout(layoutHeight, contentViewerId);
		}
		else if( workbenchType.equalsIgnoreCase("ANALYSIS")){
		}
		else{
			System.out.println("Un-supported workbench type: "+workbenchType);
		}
		
		renderRequest.setAttribute("workbenchType", workbenchType);
		renderRequest.setAttribute("customId", customId );
		renderRequest.setAttribute("classId", classId );
		
		/*
		templateJSP += workbenchLayout.getString("templateId_")+".jsp";
		
		super.include(templateJSP, renderRequest, renderResponse);
		*/
		super.doView(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		String command = ParamUtil.getString(resourceRequest, "command");
		_log.debug("ServeResource Command: "+command);
		String action = ParamUtil.getString(resourceRequest, "action", "input");
		boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;
		
		if( command.equalsIgnoreCase("RESOLVE_TEMPLATE")){
			this.resolveLayoutTemplate(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("READ_DLENTRY")){
			this.readDLEntry(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("READ_DATATYPE_SAMPLE")){
			this.readDataTypeSample(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("GET_DATATYPE_SAMPLE")){
			this.getDataTypeSample(resourceRequest, resourceResponse);
		}
		else if ( command.equalsIgnoreCase("CREATE_SIMULATION") ){
			this.createSimulation(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("LOAD_SIMULATION") ){
			this.loadSimulation(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("SAVE_SIMULATION")){
			this.saveSimulation(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("DELETE_SIMULATION") ){
			this.deleteSimulation(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("CREATE_JOB") ){
			this.createJob(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("LOAD_JOB" ) ){
			this.loadJob(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("DELETE_JOB") ){
			this.deleteJob(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("SUBMIT_JOBS")){
			this.submitJobs(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("CHECK_DUPLICATED")){
			String target = ParamUtil.getString(resourceRequest, "target");

			try {
				FileManagementLocalServiceUtil.duplicated( resourceRequest, resourceResponse, target, isJobResult);
			} catch (PortalException | SystemException e) {
				_log.error("Duplicated check error: "+ target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("DOWNLOAD")){
		}
		else if( command.equalsIgnoreCase("UPLOAD")){
			this.uploadIBFile(resourceRequest, resourceResponse);
		}
		else if( command.equalsIgnoreCase("SAVE_AS")){
			this.saveAsIBFile(resourceRequest, resourceResponse);
		}
		else{
			_log.error("[ERROR] Invalid command: "+command);
			throw new PortletException();
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	private JSONObject createJob( String simulationUuid, ServiceContext sc ) throws PortletException, JSONException, SystemException{
		SimulationJob job = null;
		
		try {
			job = SimulationLocalServiceUtil.addJob(simulationUuid, sc);
		} catch (SystemException e) {
			_log.error("Adding New Job Failed For:  "+ simulationUuid);
			throw new PortletException();
		}
		return this.convertJobToJSON(job);
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

	private JSONObject getContentLayout( float layoutHeight, String portletId){
		JSONObject layout = JSONFactoryUtil.createJSONObject();
		layout.put("templateId_", "ContentViewLayout");
		layout.put("height_", layoutHeight);
		JSONArray columns = JSONFactoryUtil.createJSONArray();
		
		JSONObject column = JSONFactoryUtil.createJSONObject();
		column.put("id_", "column-1");
		column.put("height_", 1);
		JSONArray portlets = JSONFactoryUtil.createJSONArray();
		JSONObject portlet = JSONFactoryUtil.createJSONObject();
		portlet.put("instanceId_", portletId);
		portlets.put(portlet);
		column.put("portlets_", portlets);
		column.put("currentPortlet_", portletId);
		columns.put(column);
		layout.put("columns_", columns);
		
		return layout;
	}
	
	private JSONObject getMonitoringLayout( String pathType, String portName, String portletId, float layoutHeight, String jobId ) throws JSONException, IOException{
		JSONObject layout = JSONFactoryUtil.createJSONObject();
		if( pathType.equalsIgnoreCase("file"))
			layout.put("templateId_", "MonitoringLayoutOne");
		else
			layout.put("templateId_", "MonitoringLayoutTwo");
		layout.put("templateId_", "default-simulation-layout.jspf");
		
		layout.put("height_", layoutHeight);
		JSONArray columns = JSONFactoryUtil.createJSONArray();
		
		JSONObject column_1 = JSONFactoryUtil.createJSONObject();
		column_1.put("id_", "column-1");
		column_1.put("height_", 1);
		JSONArray portlets = JSONFactoryUtil.createJSONArray();
		JSONObject portlet = JSONFactoryUtil.createJSONObject();
		if( pathType.equalsIgnoreCase("file")){
			portlet.put("instanceId_", portletId);
			column_1.put("currentPortlet_", portletId);
		}
		else{
			//portlet.put("instanceId_", "FileExplorer_WAR_OSPEditorsportlet");
			//column_1.put("currentPortlet_", "FileExplorer_WAR_OSPEditorsportlet");
//			portlet.put("instanceId_", "StructuredDataEditor_WAR_OSPEditorsportlet");
//			column_1.put("currentPortlet_", "StructuredDataEditor_WAR_OSPEditorsportlet");
			portlet.put("instanceId_", "FileExplorer_WAR_OSPEditorsportlet");
			column_1.put("currentPortlet_", "FileExplorer_WAR_OSPEditorsportlet");
//			portlet.put("instanceId_", "47");
//			column_1.put("currentPortlet_", "47");
		}
		portlets.put(portlet);
		column_1.put("portlets_", portlets);
		columns.put(column_1);
		
		if(!pathType.equalsIgnoreCase("file")){
			JSONObject column_2 = JSONFactoryUtil.createJSONObject();
			column_2.put("id_", "column-2");
			column_2.put("height_", 1);
			portlets = JSONFactoryUtil.createJSONArray();
			portlet = JSONFactoryUtil.createJSONObject();
			portlet.put("instanceId_", portletId);
			portlets.put(portlet);
			column_2.put("portlets_", portlets);
			column_2.put("currentPortlet_", portletId);
			columns.put(column_2);
		}

		layout.put("columns_", columns);
		
		_log.debug("=====================================");
		_log.debug(layout.toString(4));
		
		return layout;
	}
	
	private int getInstanceId( JSONArray columns, String portletId ){
		JSONArray found = JSONFactoryUtil.createJSONArray();
		
		for( int i=0; i<columns.length(); i++){
			JSONObject column = columns.getJSONObject(i);
			JSONArray portlets = column.getJSONArray("portlets_");
			for( int j=0; j<portlets.length(); j++){
				JSONObject portlet = portlets.getJSONObject(j);
				if( portlet.getString("id_").equalsIgnoreCase(portletId))
					found.put(portlet);
			}
		}
		
		int instanceId = 0;
		JSONObject portlet = null;
		switch( found.length() ){
		case 0:
			break;
		case 1:
			portlet = found.getJSONObject(0);
			portlet.put("instanceId_", 1);
			instanceId = 2;
		default:
			instanceId = 1;
			boolean duplicated = true;
			while( duplicated ){
				duplicated = false;
				for( int i=0; i<found.length(); i++){
					portlet = found.getJSONObject(i);
					if( portlet.getInt("instanceId_") == instanceId){
						instanceId++;
						duplicated = true;
						break;
					}
				}
			};
		}
		
		return instanceId;
	}
	
	protected SimpleSequence layoutJSONObject2Map( JSONArray jsonColumns ) throws JSONException{
		
		//System.out.println(jsonLayout.toString());
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
	
	protected void evaluateScienceAppLayout( RenderRequest renderRequest, RenderResponse renderResponse, long scienceAppId ) throws PortletException, SystemException{
		
		if( scienceAppId <= 0 )
			scienceAppId = ParamUtil.getLong(renderRequest, "scienceAppId", 40501);

		ScienceApp scienceApp = null;
		try {
			scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		} catch (PortalException | SystemException e) {
			_log.error("No scienceApp: "+scienceAppId);
			throw new PortletException();
		}
		
		String inputPorts = "";
		String logPorts = "";
		String outputPorts = "";
		
		try {
			inputPorts = ScienceAppLocalServiceUtil.getScienceAppInputPorts(scienceAppId);
			_log.info("input ports :"+inputPorts);
		} catch (SystemException e) {
			_log.error("Getting input ports for:  "+scienceAppId);
			throw new PortletException();
		} finally {
			renderRequest.setAttribute("inputPorts", inputPorts);
		}
		
		try {
			outputPorts = ScienceAppLocalServiceUtil.getScienceAppOutputPorts(scienceAppId);
			_log.info("output ports :"+outputPorts);
		} catch (SystemException e) {
			_log.error("Getting output ports for:  "+scienceAppId);
			throw new PortletException();
		} finally {
			renderRequest.setAttribute("outputPorts", outputPorts);
		}
		
		try {
			logPorts = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
			_log.info("log ports :"+logPorts);
		} catch (SystemException e) {
			_log.error("Getting logput ports for:  "+scienceAppId);
			throw new PortletException();
		} finally {
			renderRequest.setAttribute("logPorts", logPorts);
		}
		
		JSONObject workbenchLayout = null;
		try {
			workbenchLayout = JSONFactoryUtil.createJSONObject(scienceApp.getLayout());
			System.out.println(workbenchLayout.toString(4));
		} catch (JSONException e) {
			_log.error("Getting layout for:  "+scienceApp.getScienceAppId());
			throw new PortletException();
		}

		renderRequest.setAttribute("scienceApp", scienceApp);
		renderRequest.setAttribute("workbenchLayout", workbenchLayout );
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
		
		int jobStatus = (int)job.getJobStatus();
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
		
		String strInputs = "";
		try {
			strInputs = SimulationJobLocalServiceUtil.getJobInputData(job.getJobUuid());
		} catch (NoSuchSimulationJobDataException e) {
			// Do nothing
		}
		
		if( !strInputs.isEmpty() ){
			System.out.println("strInputs: "+strInputs);
			JSONArray jsonInputs = JSONFactoryUtil.createJSONArray(strInputs);
			json.put("inputs_", jsonInputs);
		}
		
		return json;
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
	
	protected void resolveLayoutTemplate(  ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws IOException, PortletException{
		String templateDir = ParamUtil.getString(resourceRequest, "templateDir");
		String templateFile = ParamUtil.getString(resourceRequest, "templateFile");
		String namespace = ParamUtil.getString(resourceRequest, "namespace");
		
		String realTemplateDir = resourceRequest.getPortletSession().getPortletContext().getRealPath(templateDir);
		_log.info("Real Template Dir: "+realTemplateDir);
		
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
	
	protected void submitJobs( PortletRequest portletRequest, PortletResponse portletResponse ) throws IOException, PortletException {
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
		
		Date now = new Date();
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
		String simulationTime = fomatter.format(now);
		System.out.println("Simulation Time: "+simulationTime);
		
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
		
//		this.jsonArrayPrint(jobs);
		
		//Set execution and files
		Map<String, String> files = new LinkedHashMap<>();
		JSONArray submitedJobs = JSONFactoryUtil.createJSONArray();
					
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
			
			try {
				System.out.println("Job Data will be stored: "+job.getJobUuid());
				SimulationJobDataLocalServiceUtil.modifySimulationJobData(job.getJobUuid(), jobData.toString());
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for( int dataIndex = 0; dataIndex<jobData.length(); dataIndex++){
				JSONObject inputData = jobData.getJSONObject(dataIndex);
				this.jsonObjectPrint(inputData);
				String portName = inputData.getString("portName_");
				String pathType = inputData.getString("type_");
				if( pathType.equalsIgnoreCase("fileContent") ){
					Date date = new Date();
					String fileName = portName.replaceAll("-", "" )+"_"+date.getTime();
					try {
						String fileId = ibAgent.uploadFile(fileName, inputData.getString("context_"), _DEFAULT_CLUSTER);
						System.out.println("File Id After IB Upload: "+fileId);
						files.put(portName, fileId);
					} catch (JSONException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if( pathType.equalsIgnoreCase("dlEntryId")){
					String content = this.readDLFileContent(inputData.getLong("id_"));
					Date date = new Date();
					String fileName = portName.replaceAll("-", "" )+"_"+date.getTime();
					try {
						String fileId = ibAgent.uploadFile(fileName, content, _DEFAULT_CLUSTER);
						System.out.println("File Id After IB Upload: "+fileId);
						files.put(portName, fileId);
					} catch (JSONException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if( pathType.equalsIgnoreCase("file")){
					String fileName = Paths.get(inputData.getString("parent_"))
												.resolve(inputData.getString("name_")).toString();
					try {
						// String fileId =  ibAgent.getFileId(fileName);
						String fileId =  ibAgent.getFileId(fileName, false);
						files.put(portName, fileId);
					} catch (JSONException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if ( pathType.equalsIgnoreCase("folder")){
					
				}
				else if ( pathType.equalsIgnoreCase("ext")){
					
				}
				else if ( pathType.equalsIgnoreCase("uri")){
					
				}
				else{
					_log.error("Un-defined path type: " + pathType);
					throw new PortletException();
				}
			}
			
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
					files,
					cluster, 
					mpiAttributes, 
					this.getJobStatusCallbackURL( portletRequest, simulationUuid, job.getJobSeqNo())
			);
			
			this.jsonObjectPrint(result);
			
			if( result.getInt("error") > 0 ){
				_log.error("Job submission failed: "+ result.getInt("error"));
			}
			else{
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
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				submitedJobs.put( submittedJob );
			}
		}
		
		ServletResponseUtil.write(httpResponse, submitedJobs.toString());
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
	
	protected void createSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
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
		
		System.out.println("scienceAppId: "+scienceAppId);
		System.out.println("scienceAppName: "+scienceAppName);
		System.out.println("scienceAppVersion: "+scienceAppVersion);
		System.out.println("srcClassCode: "+srcClassCode);
		System.out.println("srcClassId: "+srcClassId);
		System.out.println("title: "+title);

		IBAgent agent = new IBAgent(group, user);
		String simulationUuid = "";
		try {
			simulationUuid = agent.getSimulationUuid();
		} catch (SystemException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("New Simulation UUID: "+simulationUuid);
		
		Simulation simulation = null;
		try {
			simulation = SimulationLocalServiceUtil.addSimulation(simulationUuid, title, scienceAppId, scienceAppName, scienceAppVersion, srcClassCode, srcClassId, sc);
		} catch (SystemException e) {
			_log.error("Adding new Simulation Failed: "+title);
			throw new PortletException();
		} 
		
		JSONObject jsonSimulation = this.convertSimulationToJSON(simulation);
		
		JSONObject jsonJob = null;
		try {
			jsonJob = this.createJob(simulation.getSimulationUuid(), sc);
		} catch (JSONException | SystemException e1) {
			_log.error("Creating job : "+simulation.getSimulationUuid());
			throw new PortletException();
		}
		
		//JSONArray jsonJobs = JSONFactoryUtil.createJSONArray();
		//jsonJobs.put(jsonJob);
		//jsonSimulation.put("jobs_", jsonJobs);
		
		this.jsonObjectPrint(jsonSimulation);
		ServletResponseUtil.write(httpResponse, jsonSimulation.toString());
	}
	
	protected void loadSimulation(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException{
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
		this.jsonObjectPrint(jsonSimulation);
		ServletResponseUtil.write(httpResponse, jsonSimulation.toString() );

	}
	
	protected void saveSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		long classId = ParamUtil.getLong(resourceRequest, "srcClassCode");
		long customId = ParamUtil.getLong(resourceRequest, "srcClassId");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		JSONObject simulationDTO = null;
		try {
			simulationDTO = JSONFactoryUtil.createJSONObject(ParamUtil.getString(resourceRequest, "simulation"));
		} catch (JSONException e) {
			_log.error("JSONFactoryUtil: "+ParamUtil.getString(resourceRequest, "simulation"));
			throw new PortletException();
		}
		
		System.out.println("=== SimulationDTO=====================");
		this.jsonObjectPrint(simulationDTO);
		System.out.println("=== SimulationDTO=====================");
		
		Simulation simulation = null;
		try {
			simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationDTO.getString("uuid_"));
		} catch (NoSuchSimulationException | SystemException e) {
			_log.error("Getting simulation: "+e.getMessage());
			throw new PortletException();
		}
		
		simulation.setSimulationTitle(simulationDTO.getString("title_"));
		JSONArray jobsDTO = simulationDTO.getJSONArray("jobs_");
		for( int i=0; i<jobsDTO.length(); i++){
			JSONObject jobDTO = jobsDTO.getJSONObject(i);
			SimulationJob job = null;
			try {
				job = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobDTO.getString("uuid_"));
			} catch (SystemException e) {
				_log.error("Getting job: "+jobDTO.getString("uuid_"));
				throw new PortletException();
			}
			
			job.setJobTitle(jobDTO.getString("title_"));
			job.setJobSubmit(false);

			try {
				SimulationJobLocalServiceUtil.updateSimulationJob(job);
			} catch (SystemException e) {
				_log.error("Updating job: "+job.getJobUuid());
				throw new PortletException();
			}
			
			String inputs = jobDTO.getJSONObject("inputs_").toString();
			SimulationJobData jobData = null;
			try {
				jobData = SimulationJobDataLocalServiceUtil.modifySimulationJobData(job.getJobUuid(), inputs);
			} catch (SystemException e) {
				_log.error("Modifying job data: "+job.getJobUuid());
				throw new PortletException();
			}
		}
		
		ServletResponseUtil.write(httpResponse, String.valueOf(true));
	}
	
	protected void deleteSimulation( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		try {
			SimulationLocalServiceUtil.deleteSimulation(simulationUuid);
		} catch (NoSuchSimulationException | SystemException e) {
			_log.error("Deleting simulation: "+simulationUuid);
			throw new PortletException();
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("simulationUuid", simulationUuid);
		result.put("status", "DELETED");
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		ServletResponseUtil.write(httpResponse, result.toString());
	}
	
	protected void createJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		String scienceAppName = ParamUtil.getString(resourceRequest, "scienceAppName");
		String scienceAppVersion = ParamUtil.getString(resourceRequest, "scienceAppVersion");
		
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
		
		SimulationJob job = null;
		try {
			job = SimulationLocalServiceUtil.addJob(simulationUuid, scienceAppName, scienceAppVersion, sc);
		} catch ( SystemException e) {
			_log.error("Adding job: "+e.getMessage());
			throw new PortletException();
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
	
	protected void loadJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		
		System.out.println("loadJob()****************************************");
		System.out.println("SimulationUUID: "+ simulationUuid);
		System.out.println("JobUUID: "+ jobUuid);
		System.out.println("End of loadJob()****************************************");
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		SimulationJob job = null;
		try {
			job = SimulationJobLocalServiceUtil.getJob(jobUuid);
		} catch (SystemException e) {
			_log.error("Getting job info: ", e);
			throw new PortletException(e);
		} catch (NoSuchSimulationJobException e) {
			_log.error("Getting job info: ", e);
			throw new PortletException(e);
		}
		
		JSONObject jsonJob = null;
		try {
			jsonJob = this.convertJobToJSON(job);
		} catch (JSONException | SystemException e) {
			_log.error("Converting job to JSON: "+e.getMessage());
			throw new PortletException();
		} 
		
		SimulationJobData jobData = null;
		try {
			jobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(job.getJobUuid());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( jobData != null ){
			JSONArray inputs = null;
			try {
				System.out.println("loadJob inputs: "+jobData.getJobData());
				inputs = JSONFactoryUtil.createJSONArray(jobData.getJobData());
				System.out.println("++++++++JOB DATA:        ");
				this.jsonArrayPrint(inputs);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonJob.put("inputs_", inputs);
		}
		
		this.jsonObjectPrint(jsonJob);
		ServletResponseUtil.write(httpResponse, jsonJob.toString() );
	}
	
	protected void deleteJob( ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException, IOException{
		String simulationUuid = ParamUtil.getString(resourceRequest, "simulationUuid");
		String jobUuid = ParamUtil.getString(resourceRequest, "jobUuid");
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		try {
			SimulationJobLocalServiceUtil.deleteJob(jobUuid);
		} catch (SystemException e) {
			_log.error("Deleting job: "+jobUuid );
			throw new PortletException();
		} catch (NoSuchSimulationJobException e) {
			_log.warn("Deleting job with invalid uuid: "+jobUuid );
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("simulationUuid", simulationUuid);
		result.put("jobUuid", jobUuid);
		ServletResponseUtil.write(httpResponse, result.toString() );
	}
	
	protected void uploadIBFile(ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException{
		String targetFolder = ParamUtil.getString(resourceRequest, "targetFolder");
		String fileName = ParamUtil.getString(resourceRequest, "fileName");
		String uploadFileParam = "uploadFile";
		
		_log.debug("UPLOAD targetFolder: "+ targetFolder);
		_log.debug("UPLOAD fileName: "+ fileName);
		Path target = Paths.get(targetFolder).resolve(fileName);
		System.out.println("Upload target path: "+target.toString());
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		Group group = themeDisplay.getScopeGroup();

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		File uploadedFile = uploadRequest.getFile(uploadFileParam);
		byte[] content = null;
		try {
			content = Files.readAllBytes( uploadedFile.toPath() );
		} catch (IOException e) {
			_log.error("Upload File read error: "+ uploadedFile.toString());
			throw new PortletException();
		}
		
		String userScreenName = user.getScreenName();
		if( userScreenName.equalsIgnoreCase("edison") )
			userScreenName = "edisonadm";

		
		IBAgent ibAgent = new IBAgent(group, user);
		
		String fileId = "";
		try {
			fileId = ibAgent.uploadFile(target.toString(), new String(content), _DEFAULT_CLUSTER);
		} catch (JSONException | SystemException | IOException e) {
			_log.error("IB Upload File error "+ uploadedFile.toString());
			throw new PortletException();
		}
		
		JSONObject uploadResult = JSONFactoryUtil.createJSONObject();
		uploadResult.put("fileId", fileId);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		try {
			ServletResponseUtil.write(httpResponse, uploadResult.toString());
		} catch (IOException e) {
			_log.error("Upload File response error: "+ fileId);
			throw new PortletException();
		}
	}
	
	protected void saveAsIBFile(ResourceRequest resourceRequest, ResourceResponse resourceResponse ) throws PortletException{
		String filePath = ParamUtil.getString(resourceRequest, "filePath");
		String content = ParamUtil.getString(resourceRequest, "content");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		Group group = themeDisplay.getScopeGroup();

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		
		String userScreenName = user.getScreenName();
		if( userScreenName.equalsIgnoreCase("edison") )
			userScreenName = "edisonadm";

		
		IBAgent ibAgent = new IBAgent(group, user);
		
		String fileId = "";
		try {
			fileId = ibAgent.uploadFile(filePath, content, _DEFAULT_CLUSTER);
		} catch (JSONException | SystemException | IOException e) {
			_log.error("IB SaveAs File error "+ filePath);
			throw new PortletException();
		}
		
		JSONObject saveAsResult = JSONFactoryUtil.createJSONObject();
		saveAsResult.put("fileId", fileId);
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		try {
			ServletResponseUtil.write(httpResponse, saveAsResult.toString());
		} catch (IOException e) {
			_log.error("Upload File response error: "+ fileId);
			throw new PortletException();
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

