
package org.kisti.eturb.editor.portlet.dashboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.dbservice.model.MeshData;
import org.kisti.eturb.editor.portlet.dashboard.helper.EturbAppHelper;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class CreateMeshController{
	
	private static Log log = LogFactory.getLog(CreateMeshController.class);
	
	@Autowired
	private EturbAppHelper eturbAppHelper;
	
	@RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model){
        return "view";
    }
	
    @ResourceMapping(value = "prepareAnalyzer")
    public void prepareAnalyzer(
        @RequestParam("meshData") String meshData, 
        @RequestParam("appName") String appName, 
        @RequestParam("appVersion") String appVersion, 
        ResourceRequest request, ResourceResponse response) throws IOException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        try{
        	User user = PortalUtil.getUser(request);
            AnalyzerJob analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion,user.getScreenName());
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(analyzerJob));
        }catch (Exception e) {
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),
                LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
        }
    }

    @ResourceMapping(value="saveMesh")
	public void saveMesh(
	    @RequestParam("meshData") String meshData,
	    @RequestParam("appName") String appName, 
        @RequestParam("appVersion") String appVersion,
        @RequestParam("projectId") String projectId,
        @RequestParam(value = "analyzerUuid", required=false) String analyzerUuid, 
	    ResourceRequest request, ResourceResponse response
	    ) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Group group = themeDisplay.getScopeGroup();
		
		Map params = RequestUtil.getParameterMap(request);
		try{
		    final String meshDataFileName = "create-mesh-data.json";
		    User user = PortalUtil.getUser(request);
		    MeshData data = new GsonBuilder().create().fromJson(meshData, MeshData.class);
		    AnalyzerJob analyzerJob = eturbAppHelper.prepareAnalyzer(appName, appVersion, analyzerUuid,user.getScreenName());
		    analyzerJob.setMeshFileContent(data);
		    boolean isComplete = eturbAppHelper.exeSymbolicAnalyzer(GetterUtil.getLong(projectId), meshDataFileName, "", meshData, themeDisplay, analyzerJob, user);
		    
		    String fileId = "";
		    
		    Date today = new Date();
            SimpleDateFormat fileNameForm = new SimpleDateFormat("yyyyMMddHHmmss");
            String zipFileName = fileNameForm.format(today)+"."+analyzerJob.getOutputData().getName_();
		    if(isComplete){
		    	long sleepTime = data.getAirfoilsCount()*3000;
		    	Thread.sleep(sleepTime);
		    	String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
	            String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
	            
	            File resultPath = new File(analyzerJob.getResultPath());
	            if(resultPath.isDirectory()){
	            	File[] fileList = resultPath.listFiles();
	            	String userScreenName = "";
	                if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
	                    userScreenName = (String)group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
	                }else{
	                    userScreenName = String.valueOf(user.getScreenName());
	                }
	            	fileId = IBFileUtil.createZipFileWithIbUpload(icebreakerUrl, vcToken, zipFileName, fileList, "eTURB_Meshes",false,userScreenName);
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
			obj.addProperty("fileName", zipFileName);
			out.write(obj.toString());
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response),LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
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
