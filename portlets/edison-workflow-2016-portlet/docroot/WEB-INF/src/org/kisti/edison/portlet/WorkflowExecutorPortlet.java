package org.kisti.edison.portlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

@Controller
@RequestMapping("VIEW")
public class WorkflowExecutorPortlet extends MVCPortlet{

    private static Log log = LogFactory.getLog(WorkflowExecutorPortlet.class);

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
            String workflowId = ParamUtil.get(request, "workflowId", "7608414");
            
            if(StringUtils.hasText(workflowId)){
                model.addAttribute("workflowId", workflowId);
            }
            model.addAttribute("workflowId", workflowId);
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
        return "executor/view";
    }
}
