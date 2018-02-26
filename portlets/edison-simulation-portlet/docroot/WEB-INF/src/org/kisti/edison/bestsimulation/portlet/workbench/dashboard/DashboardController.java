package org.kisti.edison.bestsimulation.portlet.workbench.dashboard;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
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
import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class DashboardController {
	private static Log log = LogFactoryUtil.getLog(DashboardController.class);
	
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
		model.addAttribute("icebreakerUrl", icebreakerUrl);
		return "view";
	}
	
	
	@ResourceMapping(value="searchSimulation")
	public void searchSimulation(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "scienceAppId", required = true) Long scienceAppId,
			@RequestParam(value = "paginFunction", required = true) String paginFunction,
			@RequestParam(value = "searchLine", required = true) int searchLine,
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
		
		int currentPage = ParamUtil.get(request, "currentPage", 1);
		int blockSize = 3;
		int begin = ((currentPage - 1) * searchLine);
		int totalCount = 0;
		List<Simulation> simulations = new ArrayList<Simulation>();
		SimulationJob searchJob = null;
		try{
			 if(StringUtils.hasText(simulationUuid)){
				simulations = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, begin, searchLine);
				totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
				
				while(!this.containSimulationListFromUuid(simulations,simulationUuid)){
					currentPage +=1;
					begin = ((currentPage - 1) * searchLine);
					simulations = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, begin, searchLine);
					totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
				}
			}else{
				simulations = SimulationLocalServiceUtil.getSimulationsWithScienceAppId(scienceAppId, userId, isTest, classId, customId, begin, searchLine);
				totalCount = (int) SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
			}
			
			 
			if(StringUtils.hasText(jobUuid)){
				searchJob = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobUuid);
			}			
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+paginFunction, totalCount, currentPage, searchLine, blockSize);
			
			JsonObject obj = new JsonObject();
			obj.addProperty("pagingStr", pagingStr);
			obj.add("searchJob", new Gson().toJsonTree(searchJob));
			obj.add("simulaitons", new Gson().toJsonTree(simulations, new TypeToken<List<Simulation>>() {}.getType()));
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println("OBJ->"+obj.toString());
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
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
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			Simulation simulation  = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
			long nowUserID = themeDisplay.getUserId();
			boolean isEdit = false;
			if(simulation.getUserId()==nowUserID){
				isEdit = true;
			}
			
			List<SimulationJob> jobs = SimulationJobLocalServiceUtil.getJobsWithSimulationUuid(simulationUuid,-1,0);
			JsonObject obj = new JsonObject();
			obj.add("jobs", new Gson().toJsonTree(jobs));
			obj.addProperty("isEdit",isEdit);
//			String obj = new Gson().toJson(jobs);
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
	@ResourceMapping(value="readOutLog")
	public void readOutLog(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
		    @RequestParam(value = "jobUuid", required = true) String jobUuid ,
		    @RequestParam(value = "lastPosition", required = false) String strLastPoistion
			) throws IOException{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long lastPosition = GetterUtil.getLong(strLastPoistion, 0);
		
		try{
			response.setContentType("application/json; charset=UTF-8");
			Map<String, Object> outLog = FileManagementLocalServiceUtil.readOutLogFile(request, simulationUuid, jobUuid, lastPosition);
			
			SimulationJob simulationJob = SimulationJobLocalServiceUtil.getJob(jobUuid);
			outLog.put("jobStatus", simulationJob.getJobStatus());
			response.getWriter().write(serializeJSON(outLog));
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	
	@ResourceMapping(value="jobResultFile")
	public void jobResultFile(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "simulationUuid", required = true) String simulationUuid,
		    @RequestParam(value = "jobUuid", required = true) String jobUuid
			) throws IOException{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			User user = themeDisplay.getUser();
			
		try{
			IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
			String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
			String publicIceBreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);
			
			String result = SimulationLocalServiceUtil.retrievePostProcessor(icebreakerUrl, vcToken.getVcToken(), jobUuid);
			
			List<Map<String, Object>> resultList = new ArrayList<>();
			
			if(!CustomUtil.strNull(result).equals("")){
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(result));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				
				Map<String, Object> resultMap = null;
				for(int i = 0; i < jsonArray.size(); i++){
					resultMap = new HashMap<String, Object>();
					JSONObject comandObj = (JSONObject) jsonArray.get(i);
					
					resultMap.put("fileName", comandObj.getString("name"));
					resultMap.put("fileId", comandObj.getString("id"));
					
					resultMap.put("filePureSize", comandObj.getDouble("size"));
					resultMap.put("fileSize",CustomUtil.fileVolumeCalc(String.valueOf(comandObj.getDouble("size"))));
					
					resultMap.put("jobUuid", jobUuid);
					
					resultList.add(resultMap);
				}
				Collections.sort(resultList, sortComp);
			}
			
			// 임시 : Result.zip File ID
			String resultFileStr = SimulationLocalServiceUtil.retrieveRemoteDir(icebreakerUrl, vcToken.getVcToken(), jobUuid, "/");
			String zipFileId = "";
			if(!CustomUtil.strNull(resultFileStr).equals("")){
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultFileStr));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				
				root1: for(int i = 0; i < jsonArray.size(); i++){
					JSONObject comandObj = (JSONObject) jsonArray.get(i);
					if(comandObj.getString("name").equals("result.zip")){
						zipFileId =  comandObj.getString("id");
						break root1;
					}
				}
			}
			
			JsonObject obj = new JsonObject();
			obj.addProperty("zipFileId", zipFileId);
			obj.add("resultList", new Gson().toJsonTree(resultList, new TypeToken<List<Map<String, Object>>>() {}.getType()));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
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
	
	private Comparator<Map<String, Object>> sortComp = new Comparator<Map<String, Object>>(){
	    @Override
	    public int compare(Map<String, Object> o1, Map<String, Object> o2){
	      String s1 = o1.get("fileName").toString();
	      String s2 = o2.get("fileName").toString();

	      int n1 = s1.length(), n2 = s2.length();
	      for(int i1 = 0, i2 = 0; i1 < n1 && i2 < n2; i1++, i2++){
	        char c1 = s1.charAt(i1);
	        char c2 = s2.charAt(i2);
	        if(c1 != c2){
	          c1 = Character.toUpperCase(c1);
	          c2 = Character.toUpperCase(c2);
	          if(c1 != c2){
	            c1 = Character.toLowerCase(c1);
	            c2 = Character.toLowerCase(c2);
	            if(c1 != c2){
	              return c1 - c2;
	            }
	          }
	        }
	      }
	      return n1 - n2;
	    }
	  };
}




