package org.kisti.edison.portlet;

import java.io.IOException;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

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
    
    @RenderMapping(params = "myaction=uploadWfSampleFilePopup")
    public String uploadWfSampleFilePopup(RenderRequest request, RenderResponse response, ModelMap model) {
    	
    	/*UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);*/
    	Map param = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
    	/*
    	 * TODO 업로드 method 추가해서
    	 * 1. DL에 파일 업로드
    	 * 2. 업로드된 파일의 title, fileEntryId, size_, extension, folderId, treePath 가져오기
    	 * 3. return
    	 */
    	
    	return "designer/wfFileUpload";
    }
    
    @ResourceMapping(value = "uploadWfSampleFile")
    public void uploadWfSampleFile(ResourceRequest request, ResourceResponse response) throws IOException, PortalException, SystemException {
    	
    	boolean deleteWfSampleFile = false;
    	
    	JSONObject obj = JSONFactoryUtil.createJSONObject();
    	obj.put("result", deleteWfSampleFile);
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

}
