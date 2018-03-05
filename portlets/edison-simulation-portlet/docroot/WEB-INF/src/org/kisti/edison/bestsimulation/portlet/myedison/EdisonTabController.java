package org.kisti.edison.bestsimulation.portlet.myedison;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class EdisonTabController{
    @RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model){
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String[] tabNames = {LanguageUtil.get(themeDisplay.getLocale(), "eturb-my-file"),
            LanguageUtil.get(themeDisplay.getLocale(), "eturb-monitoring")};
        String listTabValue = ParamUtil.getString(request, "tabValue", "myfile");
        
        model.addAttribute("tabNames", StringUtils.join(tabNames, ","));
        model.addAttribute("listTabValue", listTabValue);
        return "tab";
    }
}
