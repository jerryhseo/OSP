package org.kisti.edison.search.portlet;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.kisti.edison.search.service.service.SearchLocalServiceUtil;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;

public class ConfigurationController extends DefaultConfigurationAction{
  @Override
  public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,
      ActionResponse actionResponse) throws Exception{
    super.processAction(portletConfig, actionRequest, actionResponse);
    PortletPreferences prefs = actionRequest.getPreferences();
  }
  
  @Override
  public String render(PortletConfig portletConfig, RenderRequest renderRequest,
      RenderResponse renderResponse) throws Exception{
    
    ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
    long companyGroupId = PortalUtil.getCompany(renderRequest).getGroupId();
    long groupId = PortalUtil.getScopeGroupId(renderRequest);
    Locale locale = themeDisplay.getLocale();
    
    List<AssetCategory> rootCategories = SearchLocalServiceUtil.getLv1Categories(companyGroupId,
        groupId, locale);
    
    renderRequest.setAttribute("rootCategories", rootCategories);
    
    return super.render(portletConfig, renderRequest, renderResponse);
  }

}
