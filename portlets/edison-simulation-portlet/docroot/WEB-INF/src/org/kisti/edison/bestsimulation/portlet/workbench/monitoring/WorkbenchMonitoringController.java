package org.kisti.edison.bestsimulation.portlet.workbench.monitoring;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
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
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class WorkbenchMonitoringController {
	private static Log log = LogFactoryUtil.getLog(WorkbenchMonitoringController.class);
	@RequestMapping
  public String view(
      RenderRequest request, RenderResponse response, ModelMap model,
      @RequestParam(value = "scienceAppId", required = false)Long scienceAppId,
      @RequestParam(value = "jobUuid", required = false)String jobUuid
      ) throws PortalException, SystemException, ParseException, IOException{
    log.info("workbench monitoring");
    return "workbenchMonitoring/simulations-wrapper";
  }
	
	private long parseLongWithOutException(String strLong){
	  long rValue = 0;
	  if(!StringUtils.hasText(strLong)){
	    return rValue;
	  }
	  try{
	    rValue = Long.valueOf(strLong);
    }catch(Exception e){
      log.error("classId or customId is not number");
      rValue = 0;
    }
	  return rValue;
	}
	
  @ResourceMapping(value = "simulationsFrame")
  public String simulationsFrame(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "scienceAppId", required = false) Long scienceAppId,
      @RequestParam(value = "jobUuid", required = false) String jobUuid,
      @RequestParam(value = "classId", required = false) String strClassId,
      @RequestParam(value = "customId", required = false) String strCustomId,
      @RequestParam(value = "testYn", required = false) String testYn
      ) throws PortalException, SystemException, ParseException, IOException{
    response.setContentType("text/html; charset=UTF-8");
    ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    User user = themeDisplay.getUser();
    String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
    long classId = parseLongWithOutException(strClassId);
    long customId = parseLongWithOutException(strCustomId);
    boolean isTest = false;
    if(testYn != null && !testYn.isEmpty()){
      isTest = Boolean.valueOf(testYn);
    }
    
    int currentPage = ParamUtil.get(request, "currentPage", 1);
    int listSize = ParamUtil.get(request, "searchLine", 3);
    int blockSize = 2;
    int start = ((currentPage - 1) * listSize);
    int totalCount = 0;
    if(scienceAppId != null){
      List<Simulation> simulations = null;
      if(StringUtils.hasText(jobUuid)){
        totalCount = (int) SimulationLocalServiceUtil
            .getSimulationsCountWithJobUuid(scienceAppId, user.getUserId(), jobUuid, classId, customId);
        simulations = SimulationLocalServiceUtil
            .getSimulationsWithJobUuid(scienceAppId, user.getUserId(), jobUuid, classId, customId, start, listSize);
      }else{
        totalCount = (int) SimulationLocalServiceUtil
            .getSimulationsCountWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId);
        simulations = SimulationLocalServiceUtil
            .getSimulationsWithScienceAppId(scienceAppId, user.getUserId(), isTest, classId, customId, start, listSize);
      }
      
      String paging = SimulationPagingHelper.getPaging(request.getContextPath(), portletNameSpace + "loadSimulations",
          totalCount, currentPage, listSize, blockSize);
      
      model.addAttribute("simulations", simulations);
      model.addAttribute("paging", paging);
    }
    
    return "workbenchMonitoring/simulations";
  }
	
}
