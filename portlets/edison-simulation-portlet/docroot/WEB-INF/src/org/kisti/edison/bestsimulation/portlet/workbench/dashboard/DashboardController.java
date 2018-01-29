package org.kisti.edison.bestsimulation.portlet.workbench.dashboard;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class DashboardController {
	private static Log log = LogFactoryUtil.getLog(DashboardController.class);
	
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		return "view";
	}
	
	
	@ResourceMapping(value="searchSimulation")
	public void searchSimulation(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "scienceAppId", required = true) Long scienceAppId,
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
		
		int currentPage = ParamUtil.get(request, "currentPage", 1);
		int searchLine = ParamUtil.get(request, "searchLine", 3);
		int blockSize = 3;
		int begin = ((currentPage - 1) * searchLine);
		int totalCount = 0;
		List<Simulation> simulations = null;
		try{
			if(StringUtils.hasText(jobUuid)){
				simulations = SimulationLocalServiceUtil.getSimulationsWithJobUuid(scienceAppId, user.getUserId(), jobUuid, classId, customId, begin, searchLine);
				totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithJobUuid(scienceAppId, user.getUserId(), jobUuid, classId, customId);
			}else{
				simulations = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, begin, searchLine);
				totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
			}
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"loadSimulations", totalCount, currentPage, searchLine, blockSize);
			
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
	
	@ResourceMapping(value="searchSimulationJob")
	public void searchSimulationJob(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "scienceAppId", required = true) Long scienceAppId,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			List<SimulationJob> jobs = SimulationJobLocalServiceUtil.getJobsWithSimulationUuid(simulationUuid,-1,0);
			String obj = new Gson().toJson(jobs);
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
			String jobStatusImg = EdisonExpndoUtil.getCommonCdSearchFieldValue(CustomUtil.strNull(simulationJob.getJobStatus()),EdisonExpando.OPTION1);
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
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="downManualFile")
	public void searchSimulationJobInfo(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "fileEntryId", required = true) long fileEntryId
			){
		try {
			fileDownload(response, fileEntryId);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void fileDownload(ResourceResponse response, long fileEntryId) throws Exception{
		DLFileEntry dfe = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
		String fileName = dfe.getTitle();
		
		InputStream inputStream = null;
		inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dfe.getUserId(), dfe.getFileEntryId(), dfe.getVersion());
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		response.setContentType("application/octet-stream");
		
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setProperty("Content-Disposition", "attachment;filename="+fileName);
		
		int readBytes = 0;
		byte data[] = new byte[8192];
		OutputStream out = response.getPortletOutputStream();
		
		while ((readBytes = bis.read(data)) != -1) {
			out.write(data, 0, readBytes);
		}
		out.flush(); 
		out.close();
		bis.close();
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
}




