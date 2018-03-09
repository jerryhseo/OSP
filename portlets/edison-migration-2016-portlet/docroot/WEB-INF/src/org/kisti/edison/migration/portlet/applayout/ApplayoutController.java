package org.kisti.edison.migration.portlet.applayout;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;
/*
 * APP LAYOUT 정보 UPDATE
 */
@Controller
@RequestMapping("VIEW")
public class ApplayoutController {
	
	private static Log log = LogFactoryUtil.getLog(ApplayoutController.class);
	
    @RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
        List<ScienceApp> appList = ScienceAppLocalServiceUtil.retrieveListByTemplateId("2-6-column-i");
        int totalCnt = appList.size();
		
		List<String> swAppIds = new ArrayList<String>();
		for(ScienceApp scienceApp : appList){
			swAppIds.add(String.valueOf(scienceApp.getScienceAppId()));
		}
		
		JSONObject sw = new JSONObject();
		sw.put("sw", swAppIds);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("swList", sw.toString());
		
		return "migration";
	}
	
	
	@ResourceMapping(value="searchPort")
	public void searchPort(ResourceRequest request, ResourceResponse response) throws Exception{
		Map params = RequestUtil.getParameterMap(request);
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		if(scienceAppId!=0){
			long inputCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
			long outputCnt = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount(scienceAppId);
			long logCnt = ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPortsesCount(scienceAppId);
			Map<String,Object> data = new HashMap<String,Object>();
			
			boolean updateStatus = false;
			//port 조회
			String inputPorts = "";
			if(inputCnt!=0){
				inputPorts = ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
				data.put("inputPorts", inputPorts);
				updateStatus = true;
			}
			
			String outputPorts = "";
			if(outputCnt!=0){
				outputPorts = ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
				data.put("outputPorts", outputPorts);
				updateStatus = true;
			}
			
			String logPorts = "";
			if(logCnt!=0){
				logPorts = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
				data.put("logPorts", logPorts);
				updateStatus = true;
			}
			
			
			JSONObject json = new JSONObject();
			json.put("data", data);
			json.put("updateStatus", updateStatus);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}
	}
	
	@ResourceMapping(value="updateLayout")
	public void updateLayout(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		
		try{
			String layout = CustomUtil.strNull(params.get("layout"));
			String templetId = CustomUtil.strNull(params.get("templetId"));
			
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			scienceApp.setLayout(layout);
			scienceApp.setTempletId(templetId);
			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
			System.out.println("SUCCESS--->"+scienceAppId);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("EXCEPTION--->"+scienceAppId);
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), "수정오류");
		}
		
	}
	
	
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
