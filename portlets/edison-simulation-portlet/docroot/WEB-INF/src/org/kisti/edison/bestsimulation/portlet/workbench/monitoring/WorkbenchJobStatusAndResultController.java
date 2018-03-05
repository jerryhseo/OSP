package org.kisti.edison.bestsimulation.portlet.workbench.monitoring;

import java.io.IOException;
import java.text.ParseException;
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

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class WorkbenchJobStatusAndResultController {
  private static Log log = LogFactoryUtil.getLog(WorkbenchJobStatusAndResultController.class);
  
  private Comparator<Map<String, Object>> kdComp = new Comparator<Map<String, Object>>(){
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
  
  @RequestMapping
  public String view(
      RenderRequest request, RenderResponse response, ModelMap model,
      @RequestParam(value = "jobUuid", required = false)String jobUuid
      ) throws PortalException, SystemException, ParseException, IOException{
    log.info("workbench job status and result");
    
    return "workbenchMonitoring/job-status-and-result";
  }
  
  @ResourceMapping(value = "jobInfo")
  public void jobInfo(
    ResourceRequest request, ResourceResponse response, ModelMap model,
    @RequestParam(value = "jobUuid", required = false)String jobUuid) 
      throws PortalException, SystemException, ParseException, IOException{
    try{
      response.setContentType("application/json; charset=UTF-8");
      SimulationJob job = SimulationJobLocalServiceUtil.getJob(jobUuid);
      Map<String, Object> jobModelAttribute 
        = job != null ? job.getModelAttributes() : new HashMap<String, Object>();
      jobModelAttribute.put("jobStatusString", getJobStatusString(job.getJobStatus()));
      response.getWriter().write(serializeJSON(jobModelAttribute));
    }catch(Exception e){
      log.error("jobInfo Error", e);
      handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), "Job Info Error");
    }
  }
  
  @ResourceMapping(value = "readOutLog")
  public void readOutLog(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "lastPosition", required = false) String strLastPoistion,
      @RequestParam(value = "simulationUuid", required = false) String simulationUuid,
      @RequestParam(value = "jobUuid", required = true) String jobUuid) 
          throws PortalException, SystemException, ParseException, IOException{
        try{
            response.setContentType("application/json; charset=UTF-8");
            long lastPosition = GetterUtil.getLong(strLastPoistion, 0);
            Map<String, Object> outLog = FileManagementLocalServiceUtil.readOutLogFile(
                request, simulationUuid, jobUuid, lastPosition);
            response.getWriter().write(serializeJSON(outLog));
        }catch (Exception e){
            log.error("readOutLog Error", e);
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), "readOutLog Error");
        }
  }
  
  @ResourceMapping(value = "jobResultFrame")
  public String jobResultFrame(
      ResourceRequest request, ResourceResponse response, ModelMap model,
      @RequestParam(value = "jobUuid", required = false)String jobUuid) 
        throws PortalException, SystemException, ParseException, IOException{
    log.info("jobResultFrame - jobUuid" + jobUuid);
    response.setContentType("text/html; charset=UTF-8");
    ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    long groupId = themeDisplay.getScopeGroupId();
    User user = themeDisplay.getUser();

    IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
    String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge()
      .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
    String publicIceBreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge()
      .getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);
    
    String result = SimulationLocalServiceUtil.retrievePostProcessor(icebreakerUrl, vcToken.getVcToken(), jobUuid);
    if(!CustomUtil.strNull(result).equals("")){
      JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(result));
      JSONArray jsonArray = jsonObj.getJSONArray("files");
      Map<String, Object> resultMap = null;
      List<Map<String, Object>> resultList = new ArrayList<>();

      for(int i = 0; i < jsonArray.size(); i++){
        resultMap = new HashMap<String, Object>();
        JSONObject comandObj = (JSONObject) jsonArray.get(i);

        resultMap.put("fileName", comandObj.getString("name"));
        resultMap.put("fileId", comandObj.getString("id"));

        resultMap.put("filePureSize", comandObj.getDouble("size"));
        resultMap.put("fileSize",
          CustomUtil.fileVolumeCalc(String.valueOf(comandObj.getDouble("size"))));
        resultMap.put("jobUuid", jobUuid);

        resultList.add(resultMap);
      }
      Collections.sort(resultList, kdComp);
      model.addAttribute("resultList", resultList);
    }
    
    // 임시 : Result.zip File ID
    String resultFileStr = SimulationLocalServiceUtil
      .retrieveRemoteDir(icebreakerUrl, vcToken.getVcToken(), jobUuid, "/");
    if(!CustomUtil.strNull(resultFileStr).equals("")){
      JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultFileStr));
      JSONArray jsonArray = jsonObj.getJSONArray("files");

      root1: for(int i = 0; i < jsonArray.size(); i++){
        JSONObject comandObj = (JSONObject) jsonArray.get(i);
        if(comandObj.getString("name").equals("result.zip")){
          model.addAttribute("zipFileId", comandObj.getString("id"));
          break root1;
        }
      }
    }

    model.addAttribute("icebreakerUrl", publicIceBreakerUrl);
    model.addAttribute("selectedGroupId", groupId);
    
    return "workbenchMonitoring/job-result";
  }
  
  public static String serializeJSON(Map<String, Object> map){
    JSONObject json = JSONObject.fromObject(map);
    return json != null ? json.toString() : "{}";
  }
  
  public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    response.getWriter().write(message);
    response.flushBuffer();
  }
  
  public static String getJobStatusString(long jobStatus){
    if(jobStatus == 0){ return "INITIALIZED";}
    else if(jobStatus == 1701001){ return "UNKNOWN";}
    else if(jobStatus == 1701002){ return "INITIALIZE_FAILED";}
    else if(jobStatus == 1701003){ return "INITIALIZED";}
    else if(jobStatus == 1701004){ return "SUBMISSION_FAILED";}
    else if(jobStatus == 1701005){ return "QUEUED";}
    else if(jobStatus == 1701006){ return "RUNNING";}
    else if(jobStatus == 1701007){ return "SUSPEND_REQUESTED";}
    else if(jobStatus == 1701008){ return "SUSPENDED";}
    else if(jobStatus == 1701009){ return "CANCEL_REQUESTED";}
    else if(jobStatus == 1701010){ return "CANCEL";}
    else if(jobStatus == 1701011){ return "SUCCESS";}
    else if(jobStatus == 1701012){ return "FAILED";}
    else{ return "UNKNOWN";}
  } 
}