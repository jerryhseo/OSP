package org.kisti.edison.migration.portlet.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.TokenProviderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class SimulationController {
	private static Log log = LogFactoryUtil.getLog(SimulationController.class);
	String path = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/simulation_status_update_log";
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws SystemException, PortalException{
		
		List<SimulationJob> simulationGroupQueueStatus =  SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(0, 1701005, false);
		List<SimulationJob> simulationGroupRunningStatus =  SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(0, 1701006, false);
		
		model.addAttribute("queueStatusSize", simulationGroupQueueStatus.size()); 
		model.addAttribute("runningStatusSize", simulationGroupRunningStatus.size());

		return "migration";
	}
	
	@ResourceMapping(value ="icebreakerAccess")
	public void icebreakerAccess(ResourceRequest request, ResourceResponse response){
		try {
			List<SimulationJob> simulationGroupQueueStatus =  SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(0, 1701005, false);//대기
			List<SimulationJob> simulationGroupRunningStatus =  SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(0, 1701006, false);//실행
			
			List<SimulationJob> list = new ArrayList<SimulationJob>();
			list.addAll(simulationGroupQueueStatus);
		    list.addAll(simulationGroupRunningStatus);
		    
		    Map<String, Object> statusMap = migrationSimulationJobStatus(list);
		    
		    JSONObject json = new JSONObject();
			json.put("statusMap", statusMap);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> migrationSimulationJobStatus(List<SimulationJob> list) throws Exception  {
		
		HttpURLConnection conn = null;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int updateCnt = 0;
		List errorList = new ArrayList<>();
		
		//파일
		SimpleDateFormat fileNameFormat = new SimpleDateFormat ("yyyy-MM-dd_HH:mm:ss");
		String dTime = fileNameFormat.format (new Date ());
		String txtFilePath = path +"/"+ dTime + ".txt";
		
		File folderPath = new File(path);
		if(!folderPath.isDirectory()) {
			folderPath.mkdirs();
		}

		//BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
		File txtFile = new File(txtFilePath);
		BufferedWriter fw = new BufferedWriter(new FileWriter(txtFile,false));
		
		String content = "SimulationUuid / JobUuid / JobSeq / JobSubmitDt / JobExecuteDt \r\n";
		content += "===================================================================================================================\r\n";
		
		for(SimulationJob simulationJob : list){
			boolean updateJob = false;
			String simulationUuid = CustomUtil.strNull(simulationJob.getSimulationUuid());
			String jobUuid = CustomUtil.strNull(simulationJob.getJobUuid());
			
			if(simulationUuid.equals("")){
				continue;
			}
			
			if(jobUuid.equals("")){
				continue;
			}
			
			String iceBreakerUrl = "";
			IcebreakerVcToken vcToken = null;
			Long groupId = Long.parseLong(CustomUtil.strNull(simulationJob.getGroupId()));
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			
			iceBreakerUrl = (String) group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
			vcToken = TokenProviderUtil.getAdminVcToken(group.getGroupId());
		
			try{
				
				URL url = new URL(iceBreakerUrl+"/api/simulation/"+simulationUuid+"/job/"+jobUuid+"/status");
				
				conn = (HttpURLConnection) url.openConnection();
				
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				//GET Token
				conn.setRequestProperty("Authorization", "Basic "+vcToken.getVcToken());
				if (conn.getResponseCode() == 200) {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					
					String  output = "";
					StringBuffer responseBuffer = new StringBuffer();
					
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseBuffer.append(CustomUtil.strNull(output));	
													
							JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(responseBuffer.toString()));
							//JSONObject json = new JSONObject(responseBuffer.toString());
							String status = json.getString("status");
							int dataStatus = Integer.parseInt(CustomUtil.strNull(simulationJob.getJobStatus()));
							int restStatus = Integer.parseInt(CustomUtil.getStatusConvertIceToPortal(status));
							
							System.out.print(simulationUuid);
							System.out.println("_______________"+dataStatus+" < "+restStatus);
							
							if(dataStatus!=restStatus){
								updateJob = true;
								simulationJob.setJobStatus(restStatus);
								
								if(!CustomUtil.strNull(json.getString("startTime")).equals("")){
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									
									String startTime = CustomUtil.strNull(json.getString("startTime"));
									if(startTime.length() > 19){
										startTime = startTime.substring(0, 19);  //2012-07-12T20:50:37
										//data.put("startTime", CustomUtil.replace(startTime, "T", " "));
										startTime = CustomUtil.replace(startTime, "T", " ");
										simulationJob.setJobStartDt(simpleDateFormat.parse(startTime));
									}
								}
								
								if(!CustomUtil.strNull(json.getString("endTime")).equals("")){
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String endTime = CustomUtil.strNull(json.getString("endTime"));
									
									if(endTime.length() > 19){
										endTime = endTime.substring(0, 19);  //2012-07-12T20:50:37
										endTime = CustomUtil.replace(endTime, "T", " ");
										//data.put("endTime", CustomUtil.replace(endTime, "T", " "));
										simulationJob.setJobEndDt(simpleDateFormat.parse(endTime));
									}
								}
							}
						}
					}
					br.close();
				}else if (conn.getResponseCode() == 400) {
					throw new Exception("400__BAD REQUEST__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else if (conn.getResponseCode() == 401) {
					throw new Exception("401__UNAUTHORIZED__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else if (conn.getResponseCode() == 404) {
					throw new Exception("404__NOT FOUND__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else{
					throw new Exception("ETC__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}
				
				conn.disconnect();
				
				if(updateJob){
					updateCnt++;
					SimulationJobLocalServiceUtil.updateSimulationJob(simulationJob);

					content += simulationJob.getSimulationUuid(); 
					content	+= " / " + simulationJob.getJobUuid();
					content	+= " / " + simulationJob.getJobSeqNo();
					content	+= " / " + simulationJob.getJobSubmitDt();
					
					if(simulationJob.getJobStartDt() != null && simulationJob.getJobEndDt() != null){
						SimpleDateFormat executeTimeFormat = new SimpleDateFormat ( "HH:mm:ss" );
						executeTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
						String executeTime = executeTimeFormat.format(simulationJob.getJobEndDt().getTime() - simulationJob.getJobStartDt().getTime());
						content += " / " + executeTime;
					}else{
						content += " / " + 0;
					}
					
					content	+= "\r\n";
					
				}
				
			}catch (Exception e) {
				errorList.add(e.getMessage());
				conn.disconnect();
			}finally{
				if(conn != null){conn.disconnect();}
			}
			
		}//end for
			
		resultMap.put("updateCnt", updateCnt);
		resultMap.put("errorList", errorList);

		if(updateCnt > 0){
			fw.write(content); 
			fw.flush(); 
		}else{
			fw.write("no update"); 
			fw.flush();
		}
		if(fw != null) fw.close(); 
		
		return resultMap;
	}
	
	@ResourceMapping(value ="icebreakerAccessDateUpdate")
	public void icebreakerAccessDateUpdate(ResourceRequest request, ResourceResponse response){
		List<SimulationJob> simulationDateList =  SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(0, 0, true);//대기

		HttpURLConnection conn = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List errorList = new ArrayList<>();
		int updateCnt = 0;
		for(SimulationJob simulationJob : simulationDateList){

			Boolean updateStartYn = false, updateEndYn = false, updateSubmitYn = false;
			String simulationUuid = CustomUtil.strNull(simulationJob.getSimulationUuid());
			String jobUuid = CustomUtil.strNull(simulationJob.getJobUuid());
			
			if(simulationUuid.equals("")){
				continue;
			}
			
			if(jobUuid.equals("")){
				continue;
			}
			
			try {
				
				String iceBreakerUrl = "";
				IcebreakerVcToken vcToken = null;
				Long groupId = Long.parseLong(CustomUtil.strNull(simulationJob.getGroupId()));
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				
				iceBreakerUrl = (String) group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
				vcToken = TokenProviderUtil.getAdminVcToken(group.getGroupId());
				
				URL url = new URL(iceBreakerUrl+"/api/simulation/"+simulationUuid+"/job/"+jobUuid+"/status");
				
				conn = (HttpURLConnection) url.openConnection();
				
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				//GET Token
				conn.setRequestProperty("Authorization", "Basic "+vcToken.getVcToken());
				if (conn.getResponseCode() == 200) {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					
					String  output = "";
					StringBuffer responseBuffer = new StringBuffer();
					
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseBuffer.append(CustomUtil.strNull(output));	
													
							JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(responseBuffer.toString()));
							//JSONObject json = new JSONObject(responseBuffer.toString());
							
							System.out.println(simulationUuid + " _____________ " + jobUuid);
								
							if(!CustomUtil.strNull(json.getString("startTime")).equals("")){
								String startTime = CustomUtil.strNull(json.getString("startTime"));
								if(startTime.length() > 19){
									startTime = startTime.substring(0, 19);  //2012-07-12T20:50:37
									//data.put("startTime", CustomUtil.replace(startTime, "T", " "));
									startTime = CustomUtil.replace(startTime, "T", " ");
									
									simulationJob.setJobStartDt(simpleDateFormat.parse(startTime));
								}
								updateStartYn = true;
							}
							
							if(!CustomUtil.strNull(json.getString("endTime")).equals("")){
								String endTime = CustomUtil.strNull(json.getString("endTime"));
								
								if(endTime.length() > 19){
									endTime = endTime.substring(0, 19);  //2012-07-12T20:50:37
									endTime = CustomUtil.replace(endTime, "T", " ");
									//data.put("endTime", CustomUtil.replace(endTime, "T", " "));
									
									simulationJob.setJobEndDt(simpleDateFormat.parse(endTime));
								}
								updateEndYn = true;
							}
							
							if(!CustomUtil.strNull(json.getString("submittedTime")).equals("")){
								String submittedTime = CustomUtil.strNull(json.getString("submittedTime"));
								if(submittedTime.length() > 19){
									submittedTime = submittedTime.substring(0, 19);  //2012-07-12T20:50:37
									submittedTime = CustomUtil.replace(submittedTime, "T", " ");
									//data.put("endTime", CustomUtil.replace(endTime, "T", " "));
									simulationJob.setJobSubmitDt(simpleDateFormat.parse(submittedTime));
								}
								updateSubmitYn = true;
							}
						}
					}
					br.close();
				}else if (conn.getResponseCode() == 400) {
					throw new Exception("400__BAD REQUEST__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else if (conn.getResponseCode() == 401) {
					throw new Exception("401__UNAUTHORIZED__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else if (conn.getResponseCode() == 404) {
					throw new Exception("404__NOT FOUND__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}else{
					throw new Exception("ETC__SimulationUuid="+simulationJob.getSimulationUuid()+"__jobUuid="+simulationJob.getJobUuid());
				}
					
				conn.disconnect();
				
				if(updateStartYn && updateEndYn && updateSubmitYn){
					updateCnt++;
					SimulationJobLocalServiceUtil.updateSimulationJob(simulationJob);
				}
			} catch (Exception e) {
				errorList.add(e.getMessage());
				conn.disconnect();
			}finally{
				if(conn != null){conn.disconnect();}
			}
		}
		
	    
		try {
			Map<String, Object> statusMap = new HashMap<String, Object>();
			statusMap.put("updateCnt", updateCnt + "( totalCnt : "+simulationDateList.size()+" )");
		    statusMap.put("errorList", errorList);
		    
		    JSONObject json = new JSONObject();
			json.put("statusMap", statusMap);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
