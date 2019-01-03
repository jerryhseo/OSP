package org.kisti.edison.portlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class WorkflowDesignerPortlet{

    private static Log log = LogFactory.getLog(WorkflowDesignerPortlet.class);

    @RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model)
        throws SystemException, IOException, PortalException, SQLException, ParseException{
        try{
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long appstorePlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
                "edisonscienceAppstore_WAR_edisonappstore2016portlet");
            Locale[] availableLanguage = LanguageUtil.getAvailableLocales(); // 서비스언어
            User currentUser = themeDisplay.getUser();
            ScienceApp textEditor = ScienceAppLocalServiceUtil.getTextEditorScienceApp();
            ScienceApp fileEditor = ScienceAppLocalServiceUtil.getFileEditorScienceApp();
            ScienceApp structuredEditor = ScienceAppLocalServiceUtil.getStructuredEditorScienceApp();
            String workflowId = ParamUtil.get(request, "workflowId", "");
            
            long plid  = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "scienceappmanager_WAR_edisonappstore2016portlet");
			model.addAttribute("scienceAppPlid", plid);
            
            if(StringUtils.hasText(workflowId)){
                model.addAttribute("workflowId", workflowId);
            }
            model.addAttribute("textEditor", textEditor);
            model.addAttribute("fileEditor", fileEditor);
            model.addAttribute("structuredEditor", structuredEditor);
            model.addAttribute("ableLang", availableLanguage);
            model.addAttribute("defaultLang", themeDisplay.getLanguageId());
            model.addAttribute("appstorePlid", appstorePlid);
            model.addAttribute("username", currentUser.getScreenName());
            model.addAttribute("companyGroupId", themeDisplay.getCompanyGroupId());
            model.addAttribute("groupId", PortalUtil.getScopeGroupId(request));
            
            
            //개발자 권한 체크
            model.addAttribute("isDeveloper", EdisonUserUtil.isDeveloperThan(themeDisplay.getUser()));
            

        }catch (Exception e){
            log.error(e);
            SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
        }
        return "designer/view";
    }

    @ResourceMapping(value = "getSpecificSiteGroupId")
    public void getSpecificSiteGroupId(ResourceRequest request, ResourceResponse response)
            throws IOException, PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        List<Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
        Group parentGroup = null;
        for (Group group : parentGroupList) {
            if (StringUtil.toUpperCase(group.getName()).equals("GUEST")) {
                parentGroup = group;
                break;
            }
        }
        List<Group> groupList = CustomUtil.getGroupIdASC(GroupLocalServiceUtil.getGroups(companyId, parentGroup.getGroupId(), true));
        JSONObject obj = JSONFactoryUtil.createJSONObject();
        if (groupList.size() > 0) {
            obj.put("groupId", groupList.get(0).getGroupId());
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(obj.toString());
    }
    
    @ResourceMapping(value = "deleteWfSampleFile")
    public void deleteWfSampleFile(ResourceRequest request, ResourceResponse response) throws IOException, PortalException, SystemException {
    	
    	boolean deleteWfSampleFile = false;
    	
    	JSONObject obj = JSONFactoryUtil.createJSONObject();
    	obj.put("result", deleteWfSampleFile);
    	response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(obj.toString());
    }
    
    
    @ResourceMapping(value="addSampleFile")
    public void addDataTypeSampeFile(ResourceRequest request, ResourceResponse response,
    		@RequestParam(value = "fileType", required = true) String fileType,
    		@RequestParam(value = "nodeId", required = true) String nodeId
    		) throws IOException{
    	Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
    	try{
    		ServiceContext sc = ServiceContextFactory.getInstance(WorkflowDefinition.class.getName(), request);
    		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
    		
    		JSONObject obj = JSONFactoryUtil.createJSONObject();
    		
    		String[] fileNames = upload.getFileNames("sampleFile");
    		if(fileNames != null){
    			if(!CustomUtil.strNull(params.get("preFileId")).equals("")){
    				//기존 파일 삭제
    				long preSampleFileId = GetterUtil.getLong((params.get("preFileId")));
    				try{
    					DLFileEntryLocalServiceUtil.deleteDLFileEntry(preSampleFileId);
    				}catch(NoSuchFileEntryException e){
    					
    				}
    				obj.put("preFileId", CustomUtil.strNull(params.get("preFileId")));
    			}
    			
    			List<FileEntry> sampleFileList = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc.getUserId(),
    					themeDisplay.getScopeGroupId(), "", "", "sampleFile",
    					EdisonFileConstants.WORKFLOW_SAMPLE);
    			
    			FileEntry sampeFileEntry = sampleFileList.get(0);
    			obj.put("fileEntryId", sampeFileEntry.getFileEntryId());
    			obj.put("fileName", sampeFileEntry.getTitle());
    		}
    		
    		obj.put("fileType", fileType);
    		obj.put("nodeId", nodeId);
    		
    		if(fileType.equals("wf-app")){
    			
    		}else if(fileType.equals("wf-port")){
    			obj.put("defaultEditor", CustomUtil.strNull(params.get("editor")));
    			obj.put("isWfSample", CustomUtil.strNull(params.get("isWfSample")));
    			obj.put("portId", CustomUtil.strNull(params.get("portId")));
    			obj.put("portType", CustomUtil.strNull(params.get("portType")));
    		}
    		
        	response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(obj.toString());
    	}catch(Exception e){
    		e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
    	}
    }
    
    public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
    
    @ResourceMapping(value="fileDownload")
	public void searchSimulationJobInfo(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "fileEntryId", required = true) long fileEntryId
			){
		try {
			fileDownload(response, fileEntryId);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fileDownload(ResourceResponse response, long fileEntryId) throws Exception{
		DLFileEntry dfe = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
		String fileName = dfe.getTitle();
		
		InputStream inputStream = null;
		inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dfe.getFileEntryId(), dfe.getVersion());
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		response.setContentType("application/octet-stream");
		
//		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setProperty("Content-Disposition", "attachment;filename="+fileName);
		
		int readBytes = 0;
		byte data[] = new byte[8192];
		OutputStream out = response.getPortletOutputStream();
		
		while ((readBytes = bis.read(data)) != -1) {
			out.write(data, 0, readBytes);
		}
		out.flush(); 
		out.close();
		bis.close();
	}
	
	@ResourceMapping(value="deleteWfSampleFiles")
	public void deleteWfSampleFiles(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "wfSampleFileIds", required = true) String wfSampleFileIds) throws IOException{
		try {
			Map params = RequestUtil.getParameterMap(request);
			
			String[] wfSampleFileIdsArr = wfSampleFileIds.split(",");
			for(String fileEntryId : wfSampleFileIdsArr){
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.parseLong(fileEntryId));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}
	
}
