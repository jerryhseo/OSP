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
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.editor.portlet.dashboard.helper.EturbAppHelper;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
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
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
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
		    ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		
		byte[] decodedBytes = Base64.decodeBase64(datData);
		String datFilePath = new String(decodedBytes);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Group group = themeDisplay.getScopeGroup();
		
		long projectId = GetterUtil.getLong(projectIdStr);
		try{
			User user = PortalUtil.getUser(request);
			AnalyzerJob analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion, user.getScreenName());
			
			
			String sdeFileName = "inp.inp";
			
			List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> sdeMap = new HashMap<String, String>();
			sdeMap.put("fileName", sdeFileName);
			sdeMap.put("fileContent", sdeData);
			fileList.add(sdeMap);
			
			
			String workingPath = eturbAppHelper.createInputFiles(projectId, fileList, themeDisplay, analyzerJob, user);
			
			String sdeFilePath = workingPath+File.separator+sdeFileName;
			
			boolean isComplete = eturbAppHelper.exeKflowMeshAnalyzer(projectId, datFilePath, sdeFilePath, themeDisplay, analyzerJob, user);
			SimpleDateFormat fileNameForm = new SimpleDateFormat("yyyyMMddHHmmss");
			String gridFileName = "";
			
			String fileId = "";
			if(isComplete){
				long sleepTime = 30*1000;
		    	Thread.sleep(sleepTime);
		    	String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
	            String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
	            
	            File resultPath = new File(analyzerJob.getResultPath());
	            String getFileIdFromExt = "p2d";
	            
	            if(resultPath.isDirectory()){
	            	File[] resultFileList = resultPath.listFiles();
	            	for(File file : resultFileList){
	            		String strFileName = file.getName();
	        			int pos = strFileName.lastIndexOf(".");
	        			String ext = strFileName.substring(pos+1);
	        			
	            		if(ext.equals(getFileIdFromExt)){
	            			gridFileName = file.getName();
	            		}
	            	}
	            	
	            	String userScreenName = "";
	                if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
	                    userScreenName = (String)group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
	                }else{
	                    userScreenName = String.valueOf(user.getScreenName());
	                }
//	            	fileId = IBFileUtil.createZipFileWithIbUpload(icebreakerUrl, vcToken, zipFileName, resultFileList, "KFLOW_Meshes", false,userScreenName);
	                fileId = IBFileUtil.kflowMeshFileListUpload(icebreakerUrl, vcToken, resultFileList, "KFLOW_Meshes/"+fileNameForm.format(new Date()), getFileIdFromExt,userScreenName);
	            }else{
	            	isComplete = false;
	            }
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JsonObject obj = new JsonObject();
			obj.addProperty("isComplete", isComplete);
			obj.addProperty("analyzerJob", new Gson().toJson(analyzerJob));
			obj.addProperty("fileId", fileId);
			obj.addProperty("fileName", gridFileName);
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
