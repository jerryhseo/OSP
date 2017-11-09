
package org.kisti.eturb.editor.portlet.dashboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.editor.portlet.dashboard.helper.EturbAppHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
public class ParameterController{
	
	private static Log log = LogFactory.getLog(ParameterController.class);
	
	@Autowired
	private EturbAppHelper eturbAppHelper;
	
	@RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model){
        return "view";
    }

	@ResourceMapping(value="checkAnalyzer")
	public void checkAnalyzer(
	    @RequestParam("analyzerJob") String analyzerJobJson, 
	    ResourceRequest request, ResourceResponse response) throws IOException{
	    ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	    try{
	        AnalyzerJob analyzerJob = new Gson().fromJson(analyzerJobJson, AnalyzerJob.class);
	        
	        User user = PortalUtil.getUser(request);
	        String timeLog = eturbAppHelper.getTimeLog(themeDisplay, analyzerJob, user).trim();
	        
	        int timeLogInt = Integer.parseInt(timeLog.substring(0, timeLog.lastIndexOf(".")));
	        
	        response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
	        if(timeLogInt>=100){
	        	String outText = eturbAppHelper.getOutText(themeDisplay, analyzerJob, user).trim();
	        	JsonObject obj = new JsonObject();
	        	obj.addProperty("out", outText);
	        	obj.addProperty("time", timeLog);
	        	out.write(obj.toString());
	        }else{
	        	out.write("{ \"time\": " + timeLog + "}");
	        }
	    }catch (Exception e) {
	        handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
	            LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
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
	        boolean isCompleteAnalyzer = eturbAppHelper.exeAnalyzer(GetterUtil.getLong(projectId), inputFileName, fileId, fileContent, themeDisplay, analyzerJob, user);
	        response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("{\"isComplete\": " + isCompleteAnalyzer + "}");
	    }catch (Exception e) {
	    	e.printStackTrace();
	        handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
	            LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
	    }
	}

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
        		analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion,userScreeName);
            }else{
            	analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion,analyzerUuid,userScreeName);
            }
        	
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(analyzerJob));
        }catch (Exception e) {
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
                LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
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
	    	eturbAppHelper.removeRemoteFilePath(themeDisplay, analyzerJob, user);
	    }catch (Exception e) {
	    	e.printStackTrace();
	        handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
	            LanguageUtil.get(themeDisplay.getLocale(), "edison-data-delete-error"));
	    }
	}
    
    @ResourceMapping(value="readFile")
    public void readFile(
        @RequestParam("analyzerJob") String analyzerJobJson,
        ResourceRequest request, ResourceResponse response) throws IOException{
	    ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	    try{
	    	AnalyzerJob analyzerJob = new Gson().fromJson(analyzerJobJson, AnalyzerJob.class);
	    	User user = PortalUtil.getUser(request);
	    	String result = eturbAppHelper.getViewText(themeDisplay, analyzerJob, user);
	    	response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(result);
	    }catch (Exception e) {
	    	e.printStackTrace();
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
