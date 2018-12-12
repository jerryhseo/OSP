package org.kisti.edison.bestsimulation.portlet.workbench.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.NoSuchSimulationException;
import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationShare;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class DashboardController {
	private static Log log = LogFactoryUtil.getLog(DashboardController.class);
	
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException{
		return "view";
	}
	@ResourceMapping(value="searchSimulationWithPage")
	public void searchSimulationWithPage(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "scienceAppId", required = true) Long scienceAppId,
			@RequestParam(value = "paginFunction", required = true) String paginFunction,
			@RequestParam(value = "searchLine", required = true) int searchLine,
			@RequestParam(value = "currentPage", required = true) int currentPage,
			@RequestParam(value = "testYn", required = false) String testYn
			) throws IOException{
		
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			long userId = user.getUserId();
			
			long classId = 0;
			long customId = 0;
			boolean isTest = false;
			if(testYn != null && !testYn.isEmpty()){
				isTest = Boolean.valueOf(testYn);
			}
			
			int blockSize = 3;
			int begin = ((currentPage - 1) * searchLine);
			int totalCount = 0;
			List<Simulation> simulations = new ArrayList<Simulation>();
			try{
				simulations = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, begin, searchLine);
				totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
				 
				String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+paginFunction, totalCount, currentPage, searchLine, blockSize);
				
				JsonObject obj = new JsonObject();
				obj.addProperty("pagingStr", pagingStr);
				obj.add("simulaitons", new Gson().toJsonTree(simulations, new TypeToken<List<Simulation>>() {}.getType()));
				
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
			}catch (Exception e) {
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
				e.printStackTrace();
			}
		
	}
	
	
	@ResourceMapping(value="searchSimulation")
	public void searchSimulation(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "scienceAppId", required = true) Long scienceAppId,
			@RequestParam(value = "simulationUuid", required = false) String simulationUuid,
			@RequestParam(value = "jobUuid", required = false) String jobUuid,
			@RequestParam(value = "classId", required = false) String strClassId,
			@RequestParam(value = "customId", required = false) String strCustomId,
			@RequestParam(value = "testYn", required = false) String testYn
			) throws IOException{
		
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			long userId = user.getUserId();
			
			long classId = parseLongWithOutException(strClassId);
			long customId = parseLongWithOutException(strCustomId);
			boolean isTest = false;
			if(testYn != null && !testYn.isEmpty()){
				isTest = Boolean.valueOf(testYn);
			}
			
			Simulation simulation = null;
			SimulationJob searchJob = null;
			JsonObject obj = new JsonObject();
			
			try{
				 if(StringUtils.hasText(simulationUuid)){
					simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
				}else{
					simulation = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, 0, 1).get(0);
				}
				 
				if(StringUtils.hasText(jobUuid)){
					searchJob = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobUuid);
				}			
				
				obj.add("searchJob", new Gson().toJsonTree(searchJob));
				obj.add("simulation", new Gson().toJsonTree(simulation));
			}catch (Exception e) {
				e.printStackTrace();
				if(e instanceof IndexOutOfBoundsException){
					obj.add("simulation",null);
				}else if(e instanceof NoSuchSimulationException){
					obj.add("simulation",null);
				}else if(e instanceof NoSuchSimulationJobException){
					obj.add("simulation", new Gson().toJsonTree(simulation));
				}else{
					handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
					e.printStackTrace();
				}
			}finally {
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
			}
		
	}
	
	@ResourceMapping(value="updateSimulation")
	public void updateSimulation(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
			@RequestParam(value = "title", required = true) String title
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
			simulation.setSimulationTitle(title);
			SimulationLocalServiceUtil.updateSimulation(simulation);
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="updateSimulationJob")
	public void updateSimulationJob(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "jobUuid", required = true) String jobUuid,
			@RequestParam(value = "title", required = true) String title
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			SimulationJob simulationJob = SimulationJobLocalServiceUtil.getJob(jobUuid);
			if(!simulationJob.getJobTitle().equals(title)){
				simulationJob.setJobTitle(title);
				SimulationJobLocalServiceUtil.updateSimulationJob(simulationJob);
			}
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	
	
	@ResourceMapping(value="searchSimulationJob")
	public void searchSimulationJob(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
			@RequestParam(value = "paginFunction", required = true) String paginFunction,
			@RequestParam(value = "searchLine", required = true) int searchLine
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		int currentPage = ParamUtil.get(request, "currentPage", 1);
		int blockSize = 3;
		int begin = ((currentPage - 1) * searchLine);
		int totalCount = 0;
		
		try{
			Simulation simulation  = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
			long nowUserID = themeDisplay.getUserId();
			boolean isEdit = false;
			if(simulation.getUserId()==nowUserID){
				isEdit = true;
			}
			
			List<SimulationJob> jobs = SimulationJobLocalServiceUtil.getJobsWithSimulationUuid(simulationUuid,begin,searchLine);
			totalCount = (int) SimulationJobLocalServiceUtil.getJobsCountWithSimulationUuid(simulationUuid);
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+paginFunction, totalCount, currentPage, searchLine, blockSize);
			
			JsonObject obj = new JsonObject();
			obj.add("jobs", new Gson().toJsonTree(jobs));
			obj.addProperty("pagingStr", pagingStr);
			obj.addProperty("isEdit",isEdit);
			obj.addProperty("totalCount",totalCount);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="searchSimulationJobInfo")
	public void searchSimulationJobInfo(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "jobUuid", required = true) String jobUuid
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			SimulationJob simulationJob = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobUuid);
			
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JsonObject obj = new JsonObject();
			obj.add("simulation", new Gson().toJsonTree(simulationJob));
			String jobStatusImg = "";
			if(!CustomUtil.strNull(simulationJob.getJobStatus()).equals("0")){
				jobStatusImg = EdisonExpndoUtil.getCommonCdSearchFieldValue(simulationJob.getJobStatus(),EdisonExpando.OPTION1);
			}
			obj.addProperty("jobStatusImg",jobStatusImg);
			obj.addProperty("jobStatusNm",convertJobStatusToString((int)simulationJob.getJobStatus()));
			
			if(simulationJob.getJobEndDt()!=null&&simulationJob.getJobStartDt()!=null){
				obj.addProperty("startDt", simpleDateFormat.format(simulationJob.getJobStartDt()));
			}
			if(simulationJob.getJobEndDt()!=null){
				obj.addProperty("endDt", simpleDateFormat.format(simulationJob.getJobEndDt()));
			}
			if(simulationJob.getJobEndDt()!=null&&simulationJob.getJobStartDt()!=null){
				long endTime = simulationJob.getJobEndDt().getTime();
				long startTime = simulationJob.getJobStartDt().getTime();
				
				long executeTime = (long) Math.ceil((endTime - startTime) / 60000)==0?1:(long) Math.ceil((endTime - startTime) / 60000);
				obj.addProperty("executeTime", executeTime);
			}
			
			if(simulationJob.getJobStatus()>=1701006){
				obj.addProperty("logView", true);
			}
			
			if(simulationJob.getJobStatus()==1701011){
				obj.addProperty("resultFile", true);
			}
			
			Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationJob.getSimulationUuid());
			if(simulation.getUserId()==themeDisplay.getUserId()){
				obj.addProperty("isEdit", true);
			}
			
			//공유 여부 확인
			obj.addProperty("isShare", SimulationShareLocalServiceUtil.isExitByJobUUid(jobUuid));
			
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="readOutLog")
	public void readOutLog(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
		    @RequestParam(value = "jobUuid", required = true) String jobUuid ,
		    @RequestParam(value = "lastPosition", required = false) String strLastPoistion
			) throws IOException{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long lastPosition = GetterUtil.getLong(strLastPoistion, 0);
			com.liferay.portal.kernel.json.JSONObject result = JSONFactoryUtil.createJSONObject();
			
			long jobStatus = 0;
			
			SimulationJob simulationJob;
			boolean isOutLogExist = true;
			boolean isErrorLogExist = true;
			
			try {
				simulationJob = SimulationJobLocalServiceUtil.getJob(jobUuid);
				jobStatus = simulationJob.getJobStatus();
				try{
					String logFile = OSPFileUtil.getJobResultPath(simulationUuid, jobUuid, jobUuid+".out");
					com.liferay.portal.kernel.json.JSONObject outLog = getReadLogFile(request, jobUuid, logFile, lastPosition);;
					result.put("outLog", outLog);
				}catch(Exception e){
					if(e instanceof NoSuchFileException){
						isOutLogExist = false;
					}else{
						throw new SystemException(e);
					}
				}
				
				if(jobStatus>=1701011){
					try{
						String logFile = OSPFileUtil.getJobResultPath(simulationUuid, jobUuid, jobUuid+".err");
						com.liferay.portal.kernel.json.JSONObject errLog = getReadLogFile(request, jobUuid, logFile, lastPosition);
						result.put("errLog", errLog);
					}catch(Exception e){
						if(e instanceof NoSuchFileException){
							isErrorLogExist = false;
						}else{
							throw new SystemException(e);
						}
					}
				}else{
					isErrorLogExist = false;
				}
				
				if(!isOutLogExist&&!isErrorLogExist){
					throw new SystemException();
				}
				
				
				result.put( "jobStatus", simulationJob.getJobStatus() );
				response.setContentType("application/json; charset=UTF-8");
				HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
				ServletResponseUtil.write(httpResponse, result.toString());
			} catch (Exception e1) {
				handleRuntimeException(e1, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
				e1.printStackTrace();
			}
			
	}
	
	private com.liferay.portal.kernel.json.JSONObject getReadLogFile(ResourceRequest request,String jobUuid, String logFile, long lastPosition) throws Exception{
		com.liferay.portal.kernel.json.JSONObject log = JSONFactoryUtil.createJSONObject();
		try {
			log = OSPFileUtil.readFileAtPosition(request, logFile, lastPosition, 300, OSPRepositoryTypes.USER_JOBS.toString());
		} catch (Exception e) {
			if(e instanceof NoSuchFileException){
				e.printStackTrace();
				throw e;
			}else{
				throw new SystemException(e);
			}
		}
		
		return log;
	}
	
	@ResourceMapping(value="searchProject")
	public void searchProject(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
			@RequestParam(value = "jobUuid", required = true) String jobUuid) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long parentGroupId = themeDisplay.getScopeGroup().getParentGroupId();
		long groupId = themeDisplay.getScopeGroupId();
		User user = themeDisplay.getUser();
		Locale locale = themeDisplay.getLocale();
		
		int begin = 0;
		int end = 0;
		String searchValue = "";
		long ownerId = user.getUserId();
		
		List<Map<String,Object>> projectList = new ArrayList<Map<String,Object>>();
		
		try{
			if(parentGroupId == 0){
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)) {
					projectList = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(begin, end, searchValue, locale);
				}else{
					projectList = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(ownerId, begin, end, searchValue, locale);
				}
			}else{
				long globalGroupId = themeDisplay.getCompany().getGroupId();
				List<Long> siteCategoryIdList = SimulationProjectLocalServiceUtil.getSiteCategoryIdList(globalGroupId, groupId);
				
				if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)) {
					projectList = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(begin, end, searchValue, locale, siteCategoryIdList);
				}else{
					projectList = SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(ownerId, begin, end, searchValue, locale, siteCategoryIdList);
				}
			}
			
			List<SimulationShare> shareList = SimulationShareLocalServiceUtil.findListByJobUuid(jobUuid);
			
			JsonObject obj = new JsonObject();
			obj.add("projectList", new Gson().toJsonTree(projectList, new TypeToken<List<Map<String, Object>>>() {}.getType()));
			obj.add("shareList", new Gson().toJsonTree(shareList, new TypeToken<List<SimulationShare>>() {}.getType()));
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.close();
			
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
		
	}
	
	@ResourceMapping(value="addProjectShareJob")
	public void addProjectShareJob(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "jobUuid", required = true) String jobUuid) throws IOException, SystemException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		
		String customIds = "";
		if(param.get("customIds[]") instanceof String[]){
			customIds = StringUtil.merge((String[]) param.get("customIds[]"), ",");
		}else{
			customIds = CustomUtil.strNull(param.get("customIds[]"), "0");
		}
		
		try{
			List<SimulationShare> simulationShareList = SimulationShareLocalServiceUtil.findListByJobUuid(jobUuid);
			
			long classId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class);
			SimulationShareLocalServiceUtil.removeAndCreateByJobUUids(jobUuid, classId, customIds);
			
			// 2018.03.26, 공유 취소(또는 삭제)를 위한 SimulationJob 데이터 전달
			// customIds == 0 --> Delete simulationJob
			if(customIds.equals("0")){
				JsonObject jsonObj = new JsonObject();
				jsonObj.add("removeJobList", new Gson().toJsonTree(simulationShareList, new TypeToken<List<SimulationShare>>() {}.getType()));
				
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(jsonObj.toString());
			}
		}catch(Exception e){
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="removeProjectShare")
	public void removeProjectShare(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		
		String simulationUuid = CustomUtil.strNull(param.get("simulationUuid"),"0");
		String jobUuid = CustomUtil.strNull(param.get("jobUuid"),"0");
		try{
			if(!simulationUuid.equals("0")){
				SimulationShareLocalServiceUtil.removeBySimulationUuid(simulationUuid);
			}else{
				SimulationShareLocalServiceUtil.removeByJobUuid(jobUuid);
			}
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	private static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
	private long parseLongWithOutException(String strLong){
		long rValue = 0;
		if(!StringUtils.hasText(strLong)){
			return rValue;
		}
		
		try{
			rValue = Long.valueOf(strLong);
		}catch(Exception e){
			rValue = 0;
		}
		return rValue;
	}
	
	private static String serializeJSON(Map<String, Object> map){
		JSONObject json = JSONObject.fromObject(map);
		return json != null ? json.toString() : "{}";
	  }
	
	private String convertJobStatusToString(int jobStatus) throws SystemException, JSONException{
		String returnStr = "";
		switch ( jobStatus ){
		case 0:
			returnStr = "INITIALIZED";
		  break;
		case 1701001:
			returnStr = "Unknown";
			break;
		case 1701002:
			returnStr = "INITIALIZE_FAILED";
			break;
		case 1701003:
			returnStr = "INITIALIZED";
			break;
		case 1701004:
			returnStr = "SUBMISSION_FAILED";
			break;
		case 1701005:
			returnStr = "QUEUED";
			break;
		case 1701006:
			returnStr = "RUNNING";
			break;
		case 1701007:
			returnStr = "SUSPEND_REQUESTED";
			break;
		case 1701008:
			returnStr = "SUSPENDED";
			break;
		case 1701009:
			returnStr = "CANCEL_REQUESTED";
			break;
		case 1701010:
			returnStr = "CANCELED";
			break;
		case 1701011:
			returnStr = "SUCCESS";
			break;
		case 1701012:
			returnStr = "FAILED";
			break;
		}
		return returnStr;
	}
	
	private boolean containSimulationListFromUuid(List<Simulation> simulations, String simulationUuid) throws SystemException{
		boolean returnResult = false;
		if(simulations.size()==0){
			throw new SystemException("no simulation uuid--->"+simulationUuid);
		}else{
			for1:for(Simulation simulation : simulations){
				if(simulation.getSimulationUuid().equals(simulationUuid)){
					
					returnResult = true;
					break for1;
				}
			}
		}
		return returnResult;
	}
}




