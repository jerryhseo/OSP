package org.kisti.edison.osp.editor.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.osp.editor.helper.MeshAppHelper;
import org.kisti.edison.osp.model.AnalyzerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class ParameterController {
	private static Log log = LogFactory.getLog(ParameterController.class);
	
	@Autowired
	private MeshAppHelper meshAppHelper;
	
	@ResourceMapping(value = "prepareAnalyzer")
	public void prepareAnalyzer(
			@RequestParam("fileId") String dataFileId,
			@RequestParam("appName") String appName,
			@RequestParam("appVersion") String appVersion,
			@RequestParam(value = "analyzerUuid", required=false) String analyzerUuid,
			ResourceRequest request, ResourceResponse response) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			String userScreeName = themeDisplay.getUser().getScreenName();
			AnalyzerJob analyzerJob = null;
			if(!StringUtils.hasText(analyzerUuid)){
				analyzerJob = meshAppHelper.prepareAnalyzer(appName, appVersion,userScreeName);
			}else{
				analyzerJob = meshAppHelper.prepareAnalyzer(appName, appVersion,analyzerUuid,userScreeName);
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(analyzerJob));
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value="executeAnalyzer")
	public void executeAnalyzer(
			@RequestParam("analyzerJob") String analyzerJobJson,
			@RequestParam("inputFileName") String inputFileName,
			@RequestParam("fileId") String fileId,
			@RequestParam("projectId") String projectId,
			@RequestParam(value = "fileContent", required=false) String fileContent,
			ResourceRequest request, ResourceResponse response) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			AnalyzerJob analyzerJob = new Gson().fromJson(analyzerJobJson, AnalyzerJob.class);
			User user = PortalUtil.getUser(request);
			boolean isCompleteAnalyzer = meshAppHelper.exeAnalyzer(GetterUtil.getLong(projectId), inputFileName, fileId, fileContent, themeDisplay, analyzerJob, user);
			
			long sleepTime = 2*1000;
			Thread.sleep(sleepTime);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
//			out.write("{\"isComplete\": " + isCompleteAnalyzer + "}");
			out.write("{\"isComplete\": " + true + "}");
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value="checkAnalyzer")
	public void checkAnalyzer(
			@RequestParam("analyzerJob") String analyzerJobJson,
			 ResourceRequest request, ResourceResponse response) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			AnalyzerJob analyzerJob = new Gson().fromJson(analyzerJobJson, AnalyzerJob.class);
			
			User user = PortalUtil.getUser(request);
			String time = meshAppHelper.getTimeLog(themeDisplay, analyzerJob, user,100).trim();
			
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(time!=""){
				String outText = meshAppHelper.getOutText(themeDisplay, analyzerJob, user).trim();
				JsonObject obj = new JsonObject();
				obj.addProperty("out", outText);
				out.write(obj.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	
	@ResourceMapping(value="removeRemoteFilePath")
	public void removeRemoteFilePath(
			@RequestParam("analyzerJob") String analyzerJobJson,
			ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			AnalyzerJob analyzerJob = new Gson().fromJson(analyzerJobJson, AnalyzerJob.class);
			User user = PortalUtil.getUser(request);
			meshAppHelper.removeRemoteFilePath(themeDisplay, analyzerJob, user);
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-delete-error"));
		}
	}
	
	@ResourceMapping(value="getInputDataPathFromFileId")
	public void getInputDataPathFromFileId(
			@RequestParam("fileId") String fileId,
			ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			byte[] decodedBytes = Base64.decodeBase64(fileId);
			String filePath = new String(decodedBytes);
			
			String[] filePaths = filePath.split("/");
			OSPRepositoryTypes repositoryType = OSPRepositoryTypes.valueOf(OSPRepositoryTypes.USER_HOME.toString());
			boolean isBasePathCheck = false;
			String parentPath = "";
			String fileName = "";
			for(int i=0;i<filePaths.length;i++){
				String path = filePaths[i];
				if(i==filePaths.length-1){
					fileName = path;
				}else{
					if(path.equals(repositoryType.value())){
						isBasePathCheck = true;
						continue;
					}
					if(isBasePathCheck){
						if(parentPath.equals("")){
							parentPath+=path;
						}else{
							parentPath+=File.separator+path;
						}
					}
				}
				
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JsonObject obj = new JsonObject();
			obj.addProperty("parentPath", parentPath);
			obj.addProperty("fileName", fileName);
			out.write(obj.toString());
			
			Path ospPath = OSPFileUtil.getRepositoryPath(themeDisplay.getUser().getScreenName(), parentPath+"/"+fileName, OSPRepositoryTypes.USER_HOME.toString());
			String ospPathStr = ospPath.toString();
			String opsPathStrReplace = ospPathStr.replaceAll("\\\\", "/");
			
			System.out.println("originPath-->"+filePath);
			System.out.println("ospPath-->"+opsPathStrReplace);
			byte[] decodeFileId = Base64.encodeBase64(opsPathStrReplace.getBytes());
			System.out.println("orign-->"+fileId);
			
			String reconstitutedString = new String(decodeFileId);
			System.out.println("path_to_decode-->"+reconstitutedString);
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-delete-error"));
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
