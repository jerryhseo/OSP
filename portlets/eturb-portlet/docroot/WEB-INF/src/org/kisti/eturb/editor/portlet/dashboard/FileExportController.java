
package org.kisti.eturb.editor.portlet.dashboard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.dbservice.model.Project;
import org.kisti.eturb.dbservice.service.ProjectLocalServiceUtil;
import org.kisti.eturb.dbservice.service.persistence.ProjectPK;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class FileExportController{
    
    private static Log log = LogFactory.getLog(FileExportController.class);
    private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
    
    @ResourceMapping(value="fileExport")
    public void zipFileExport(ResourceRequest request, ResourceResponse response) throws IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        Group group = themeDisplay.getScopeGroup();
        
        try{
            User user = PortalUtil.getUser(request);
            
            String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
            String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            List<File> fileList = new ArrayList<File>();
            
//            String[] fileIds = (String[]) params.get("fileIds[]");
//            String[] fileNames = (String[]) params.get("fileNames[]");
            String[] fileIds = null;
            String[] fileNames = null;
            if(params.get("fileIds") instanceof String){
                fileIds = new String[]{(String) params.get("fileIds")};
                fileNames = new String[]{(String) params.get("fileNames")};
            } else {
                fileIds = (String[]) params.get("fileIds");
                fileNames = (String[]) params.get("fileNames");
            }
            
            for(int i=0;i<fileIds.length;i++){
                File file = IBFileUtil.getFile(icebreakerUrl, vcToken, fileIds[i],fileNames[i].trim());
                fileList.add(file);
            }
            
            if(!CustomUtil.strNull(params.get("bcData")).equals("")){
            	String[] bcData = null;
            	if(params.get("bcData") instanceof String){
                    bcData = new String[]{(String) params.get("bcData")};
                } else {
                    bcData = (String[]) params.get("bcData");
                }
                String bcFileName = "bc.inp";
                String fileContent = "";
                for(int i=0; i<bcData.length; i++){
                    fileContent += bcData[i].toString();
                }
                
                File bcFile = IBFileUtil.createBcFile(icebreakerUrl, vcToken, bcFileName, fileContent);
                fileList.add(bcFile);
            }
            
            File zipFile = createZipFile(request, response, fileList);
            
            //zip 파일을 myFile에 등록한 후 삭제
            List<File> zipFileList = new ArrayList<File>();
            zipFileList.add(zipFile);
            ibFileUpload(request, zipFileList);
            
            JSONObject json = new JSONObject();
            json.put("fileName", zipFile.getName());
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public File createZipFile(ResourceRequest request, ResourceResponse response, List<File> fileList){
        // multiFile --> zip
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        Group group = themeDisplay.getScopeGroup();
        
        try{
            User user = PortalUtil.getUser(request);
            
            String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
            String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            
            Date today = new Date();
            SimpleDateFormat fileNameForm = new SimpleDateFormat("yyyyMMddHHmmss");
            String zipFileName = fileNameForm.format(today)+"_output.zip";
            return IBFileUtil.createZipFile(icebreakerUrl, vcToken, zipFileName, fileList);
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public void ibFileUpload(ResourceRequest request, List<File> fileList){
        UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
        Map param = RequestUtil.getParameterMap(upload);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        Group group = themeDisplay.getScopeGroup();
        
        try{
            User user = PortalUtil.getUser(request);
            
            String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
            String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
            IBFileUtil.ibFileUpload(icebreakerUrl, vcToken, fileList, upload);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @ResourceMapping(value="getWorkbenchAppList")
    public static void getWorkbenchAppList(ResourceRequest request, ResourceResponse response) throws IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        Group group = themeDisplay.getScopeGroup();
        
        try{
            String[] appList = (String[]) params.get("appList");
            List<Long> appIdList = new ArrayList<Long>();
            List<String> appNames = new ArrayList<String>();
            
            for(int i=0; i<appList.length; i++){
                String[] appInfo = appList[i].split(",");
                ScienceApp scienceAppParam = ScienceAppLocalServiceUtil.getScienceApp(appInfo[0], appInfo[1]);
                
                appNames.add(appInfo[0]);
                appIdList.add(scienceAppParam.getScienceAppId());
            }
            
            JSONObject json = new JSONObject();
            json.put("appIdList", appIdList);
            json.put("appNames", appNames);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // appList 추출
        
    }
    
    protected static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(message);
        response.flushBuffer();
    }
}
