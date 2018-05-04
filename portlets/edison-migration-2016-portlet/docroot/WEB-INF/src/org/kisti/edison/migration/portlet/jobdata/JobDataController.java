package org.kisti.edison.migration.portlet.jobdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class JobDataController {
	private static Log log = LogFactoryUtil.getLog(JobDataController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws SystemException, JSONException{
		
		return "migration";
	}
	
	@ResourceMapping(value="searchJob")
	public void searchJob(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "userScreenName", required = true) String screenName
			) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long companyId = themeDisplay.getCompanyId();
		User user;
		try {
			user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
			
			long userId = user.getUserId();
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(Simulation.class,"sim",PortletClassLoaderUtil.getClassLoader());
			query.add(RestrictionsFactoryUtil.eq("sim.userId", userId));
			
			List<Simulation> simulations = SimulationLocalServiceUtil.dynamicQuery(query);
			
			List<SimulationJob> returnJob = new ArrayList<SimulationJob>();
			
			JSONArray returnJobsUuids = JSONFactoryUtil.createJSONArray();
			for(Simulation simulation : simulations){
				List<SimulationJob> jobs = SimulationJobLocalServiceUtil.getJobsBySimulationUuid(simulation.getSimulationUuid());
				
				for(SimulationJob simulationJob:jobs){
					if(simulationJob.getJobSubmit()){
						JSONObject returnObject = JSONFactoryUtil.createJSONObject();
						
						SimulationJobData simulationJobData =  SimulationJobDataLocalServiceUtil.getSimulationJobData(simulationJob.getJobUuid());
						
						try {
							JSONArray optionArr = JSONFactoryUtil.createJSONArray(simulationJobData.getJobData());
						} catch (JSONException e) {
							returnObject.put("jobUuid", simulationJob.getJobUuid());
							returnObject.put("appId", simulation.getScienceAppId());
							returnJobsUuids.put(returnObject);
						}
					}
				}
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(returnJobsUuids.toString());
			
		} catch (PortalException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@ResourceMapping(value="searchJobData")
	public void searchJobData(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "jobUuid", required = true) String jobUuid,
			@RequestParam(value = "appId", required = true) long appId
			) throws IOException{
		
		try {
			JSONObject returnObject = JSONFactoryUtil.createJSONObject();
			SimulationJobData simulationJobData =  SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
			
			returnObject.put("oldJSON", simulationJobData.getJobData());
			returnObject.put("newJSON", convertorJSON(simulationJobData.getJobData(),appId));
			
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(returnObject.toString());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@ResourceMapping(value="updateJobData")
	public void updateJobData(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "jobUuid", required = true) String jobUuid,
			@RequestParam(value = "appId", required = true) long appId
			) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			JSONObject returnObject = JSONFactoryUtil.createJSONObject();
			SimulationJobData simulationJobData =  SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
			
			String resultJSON = convertorJSON(simulationJobData.getJobData(),appId);
			if(!resultJSON.equals("false")){
				simulationJobData.setJobData(convertorJSON(simulationJobData.getJobData(),appId));
				SimulationJobDataLocalServiceUtil.updateSimulationJobData(simulationJobData);
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(returnObject.toString());
		} catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
		}
	}
	
	
	private String convertorJSON(String orignJson,long appId) throws PortalException, SystemException{
		//최신 포맷 여부 확인
		try {
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(orignJson);
			return "false";
		} catch (JSONException e) {
			//최신 포맷 아님 Convertor 대상
			try {
				JSONArray returnArray = JSONFactoryUtil.createJSONArray();
				
				JSONObject orignJsonOBJ = JSONFactoryUtil.createJSONObject(orignJson);
				
				//APP 정보 조회
				ScienceAppInputPorts appInputPorts =  ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPorts(appId);
				net.sf.json.JSONObject portJson = net.sf.json.JSONObject.fromObject(appInputPorts.getInputPorts());
				
				Set<String> set = portJson.keySet();
				int i=1;
				for (String portNameStr : set) {
					String portType = orignJsonOBJ.getString(portNameStr+"_type");
					if(portType.equals("Inputdeck")){
						JSONObject returnOBJ = JSONFactoryUtil.createJSONObject();
						returnOBJ.put("type_", "fileContent");
						returnOBJ.put("portName_", portNameStr);
						returnOBJ.put("context_", orignJsonOBJ.getJSONArray(portNameStr).getJSONObject(0).getString("file-content"));
						returnOBJ.put("order_", i++);
						returnArray.put(returnOBJ);
					}else if(portType.equals("File")){
						String portFileId = portJson.getJSONObject(portNameStr).getString("fileId");
						
						if(portFileId.equals("SAMPLE")){
							JSONObject returnOBJ = JSONFactoryUtil.createJSONObject();
							returnOBJ.put("type_", "dlEntryId");
							returnOBJ.put("dirty_", true);
							String fileId = "";
							if(portJson.getJSONObject(portNameStr).get("sample")==null){
								net.sf.json.JSONObject dataTypeData = portJson.getJSONObject(portNameStr).getJSONObject("dataType_");
								String dtName = dataTypeData.getString("name");
								String dtVersion = dataTypeData.getString("version");
								DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dtName, dtVersion);
								fileId = dataType.getSamplePath();
							}else{
								fileId = portJson.getJSONObject(portNameStr).getString("sample_");
							}
							returnOBJ.put("id_", fileId);
							returnOBJ.put("portName_", portNameStr);
							returnOBJ.put("order_", i++);
							returnArray.put(returnOBJ);
						}else{
							JSONObject returnOBJ = JSONFactoryUtil.createJSONObject();
							returnOBJ.put("type_", "file");
							returnOBJ.put("parent_", "FILE_PARENT");
							returnOBJ.put("dirty_", false);
							returnOBJ.put("relative_", true);
							returnOBJ.put("portName_", portNameStr);
							returnOBJ.put("name_", "FILE_NAME");
							returnOBJ.put("order_", i++);
							returnArray.put(returnOBJ);
						}
						
					}
				}
				
				return returnArray.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
				return "false";
			}
		}
	}
	
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
