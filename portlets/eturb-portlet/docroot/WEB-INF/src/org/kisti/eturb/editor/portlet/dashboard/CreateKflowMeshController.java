package org.kisti.eturb.editor.portlet.dashboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.editor.portlet.dashboard.helper.EturbAppHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class CreateKflowMeshController {
	
	@Autowired
	private EturbAppHelper eturbAppHelper;
	
	private static Log log = LogFactory.getLog(CreateKflowMeshController.class);
	
	
	@ResourceMapping(value="kflowSaveMesh")
	public void saveMesh(
			@RequestParam("projectId") String projectIdStr,
		    @RequestParam("appName") String appName, 
	        @RequestParam("appVersion") String appVersion,
	        @RequestParam("datData") String datData,
	        @RequestParam("sdeData") String sdeData,
	        @RequestParam("textData") String textData,
		    ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		
		byte[] decodedBytes = Base64.decodeBase64(datData);
		String datFilePath = new String(decodedBytes);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long projectId = GetterUtil.getLong(projectIdStr);
		try{
			User user = PortalUtil.getUser(request);
			AnalyzerJob analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion, user.getScreenName());
			
			
			String sdeFileName = "inp.inp";
			String textFileName = "text.inp";
			
			List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> sdeMap = new HashMap<String, String>();
			sdeMap.put("fileName", sdeFileName);
			sdeMap.put("fileContent", sdeData);
			fileList.add(sdeMap);
			HashMap<String, String> textMap = new HashMap<String, String>();
			textMap.put("fileName", textFileName);
			textMap.put("fileContent", textData);
			fileList.add(textMap);
			
			
			String workingPath = eturbAppHelper.createInputFiles(projectId, fileList, themeDisplay, analyzerJob, user);
			
			System.out.println(datFilePath);
			System.out.println(workingPath);
			
			String sdeFilePath = workingPath+File.separator+sdeFileName;
			String textFilePath = workingPath+File.separator+textFileName;
			
			boolean isComplete = eturbAppHelper.exeKflowMeshAnalyzer(projectId, datFilePath, sdeFilePath, textFilePath, themeDisplay, analyzerJob, user);
			
			Date today = new Date();
            SimpleDateFormat fileNameForm = new SimpleDateFormat("yyyyMMddHHmmss");
            String zipFileName = fileNameForm.format(today)+"."+analyzerJob.getOutputData().getName_();
            
            
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JsonObject obj = new JsonObject();
			//obj.addProperty("isComplete", isComplete);
			obj.addProperty("isComplete", true);
			obj.addProperty("analyzerJob", new Gson().toJson(analyzerJob));
			obj.addProperty("fileId", "test");
			obj.addProperty("fileName", zipFileName);
			out.write(obj.toString());
		}catch (Exception e) {
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
                    LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
            }
		
	}
	
	protected static void handleRuntimeException(Exception ex, HttpServletResponse response, String message)
	        throws IOException{
	        log.error(message, ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(message);
			response.flushBuffer();
		}
}
