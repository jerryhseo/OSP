package org.kisti.edison.bestsimulation.portlet.workbench.monitoring;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.util.SimulationPagingHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class WorkbenchJobMonitoringController {
  private static Log log = LogFactoryUtil.getLog(WorkbenchJobMonitoringController.class);
  @RequestMapping
  public String view(
      RenderRequest request, RenderResponse response, ModelMap model,
      @RequestParam(value = "simulationsUuid", required = false)String simulationsUuid
      ) throws PortalException, SystemException, ParseException, IOException{
    log.info("workbench job monitoring");
    return "workbenchMonitoring/jobs-wrapper";
  }
  
  @ResourceMapping(value = "jobsFrame")
  public String jobsFrame(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "scienceAppId", required = false) Long scienceAppId,
      @RequestParam(value = "simulationUuid", required = false) String simulationUuid
      ) throws PortalException, SystemException, ParseException, IOException{
    response.setContentType("text/html; charset=UTF-8");
    
    if(StringUtils.hasText(simulationUuid)){
      ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
      String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
      int currentPage = ParamUtil.get(request, "currentPage", 1);
      int listSize = ParamUtil.get(request, "searchLine", 3);
      int blockSize = 2;
      int start = ((currentPage - 1) * listSize);
      
      int totalCount = (int) SimulationJobLocalServiceUtil
          .getJobsCountWithSimulationUuid(simulationUuid);
      List<SimulationJob> jobs = SimulationJobLocalServiceUtil
          .getJobsWithSimulationUuid(simulationUuid, start, listSize);
      
      String paging = SimulationPagingHelper.getPaging(request.getContextPath(), portletNameSpace + "loadJobs",
          totalCount, currentPage, listSize, blockSize);
      
      model.addAttribute("jobs", jobs);
      model.addAttribute("paging", paging);
    }
    
    return "workbenchMonitoring/jobs";
  }
  
  @ResourceMapping(value = "submitJob")
  public void submitJob(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "simulationUuid", required = false) String simulationUuid, 
      @RequestParam(value = "jobUuid", required = false) String jobUuid
      ){
    response.setContentType("text/html; charset=UTF-8");
    log.info("simulationUuid : " + simulationUuid);
    log.info("jobUuid : " + jobUuid);
    try{
      SimulationJobLocalServiceUtil.submitSimulationJob(simulationUuid, jobUuid, "");
    }catch (Exception e){
      log.error(e);
    }
  }
  
  @ResourceMapping(value = "removeJob")
  public void removeJob(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "simulationUuid", required = false) String simulationUuid, 
      @RequestParam(value = "jobUuid", required = false) String jobUuid
      ) throws PortalException, SystemException, ParseException, IOException{
    response.setContentType("text/html; charset=UTF-8");
    log.info("simulationUuid : " + simulationUuid);
    log.info("jobUuid : " + jobUuid);
    try{
      SimulationJobLocalServiceUtil.deleteSimulationJobByJobUuid(simulationUuid, jobUuid);
    }catch (SystemException e){
      log.error(e);
    }
  }
  
}