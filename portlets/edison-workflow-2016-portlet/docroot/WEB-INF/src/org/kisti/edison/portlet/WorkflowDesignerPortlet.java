package org.kisti.edison.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
