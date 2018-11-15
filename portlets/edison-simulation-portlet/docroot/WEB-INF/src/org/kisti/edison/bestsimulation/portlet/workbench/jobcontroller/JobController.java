package org.kisti.edison.bestsimulation.portlet.workbench.jobcontroller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.sdr.metadata.service.DatasetServiceUtil;

@Controller
@RequestMapping("VIEW")
public class JobController {
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Group sdrGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), "SDR");
		model.addAttribute("sdrGroupId", sdrGroup.getGroupId());
		return "view";
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
		inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dfe.getFileEntryId(), dfe.getVersion());
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		response.setContentType("application/octet-stream");
		
//		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
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
	
	
	@ResourceMapping(value = "transferJobData")
	public void transferJobData(
			@RequestParam(value = "collectionId") String collectionId,
			@RequestParam(value = "transMode") String transMode,
			@RequestParam(value = "simulations[]") String[] simulations,
			@RequestParam(value = "jobUuid", required=false) String jobUuid,
			ResourceRequest request, ResourceResponse response){
		
		Map params = RequestUtil.getParameterMap(request);
		try{
			for(String simulationUuid : simulations){
				Simulation simulation = SimulationLocalServiceUtil.getSimulationByUUID(simulationUuid);
				if(transMode.equals("TRANS_JOB")){
					SimulationJob simulationJob = SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid(jobUuid);
					
					String jobTitle = simulation.getSimulationTitle()+" - "+simulationJob.getJobTitle();
					long scienceAppId = GetterUtil.getLong(simulation.getScienceAppId());
					this.transferJobDataToSDR(request, collectionId, jobUuid, scienceAppId, jobTitle);
				}else if(transMode.equals("TRANS_SIMULATION")){
					List<SimulationJob> jobs = SimulationJobLocalServiceUtil.getJobsBySimulationUuid(simulationUuid);
					for(SimulationJob job : jobs){
						if(job.getJobStatus()==1701011){
							String jobTitle = simulation.getSimulationTitle()+" - "+job.getJobTitle();
							long scienceAppId = GetterUtil.getLong(simulation.getScienceAppId());
							this.transferJobDataToSDR(request, collectionId, job.getJobUuid(), scienceAppId, jobTitle);
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void transferJobDataToSDR(ResourceRequest request,String collectionId,String jobUuid,long scienceAppId, String jobTitle) throws PortalException, SystemException{
		final int REPO_ID = 1;
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
		ServiceContext sc = ServiceContextFactory.getInstance(request);
		
		JSONObject saveInfo = JSONFactoryUtil.createJSONObject();
		
		saveInfo = DatasetServiceUtil.save(
					GetterUtil.getLong(collectionId),
					jobUuid, 
					scienceApp.getName(), 
					scienceApp.getVersion(), 
					jobTitle,
					scienceAppId,
					REPO_ID, 
					simulationJobData.getJobData(),
					scienceApp.getLayout(), 
					sc);
		
		if(saveInfo.getBoolean("isValid")){
			JSONObject curateInfo = JSONFactoryUtil.createJSONObject();
			curateInfo = DatasetServiceUtil.curate(saveInfo.getLong("datasetId"), sc);
			
			if(!curateInfo.getBoolean("isValid")){
				throw new PortalException("Failed Transfer JobData To SDR From Curate");
			}
		}else{
			throw new PortalException("Failed Transfer JobData To SDR From Save");
		}
	}
}
