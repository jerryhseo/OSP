package org.kisti.edison.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import javax.servlet.ServletContext;

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletConfigFactoryUtil;

public class ConfigurationController implements ConfigurationAction{

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		Map param = RequestUtil.getParameterMap(actionRequest);
		preferenceOption(actionRequest,actionResponse);
	}

	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		PortletConfig selPortletConfig = getSelPortletConfig(renderRequest);

		String tabArray[] = {
			"favoriteApp", "myApp", "myDataType", "myHistory", "myCourse",
			"myClass", "myFile", "myWorkspace", "myContent", "myProject", "siteJoin" ,"eturbMyFile"
		};
		renderRequest.setAttribute("tabArray", tabArray);

		String selectedTab = renderRequest.getPreferences().getValue("mypageTab", "");
		
		if(!selectedTab.equals("")){
			String[] selectTabArr = selectedTab.split(",");
			renderRequest.setAttribute("selectedTab", selectTabArr);
		}
		
		String configTemplate = selPortletConfig.getInitParameter("config-template");
		if (Validator.isNotNull(configTemplate)) {
			return configTemplate;
		}
		
		
		String configJSP = selPortletConfig.getInitParameter("config-jsp");
		
		if (Validator.isNotNull(configJSP)) {
			return configJSP;
		}
		return "mypage/configuration.jsp";
	}
	
	public void preferenceOption(ActionRequest actionRequest, ActionResponse actionResponse) throws ReadOnlyException, ValidatorException, IOException {
		PortletPreferences prefs = actionRequest.getPreferences();
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);				
				
		Set es = prefs.getMap().entrySet();
		Iterator entries = null;
		if(es != null){
			entries = es.iterator();
		}
		while (entries.hasNext()) {
			Map.Entry<String, String[]> thisEntry = (Map.Entry) entries.next();
			String key = CustomUtil.strNull(thisEntry.getKey());
			prefs.reset(key);
		}
		
		
		String[] keyTextBox		= actionRequest.getParameterValues("keyTextBox");
		String[] valueTextBox	= actionRequest.getParameterValues("valueTextBox");
		String[] mypageTab	= actionRequest.getParameterValues("mypageTab");
		String[] prefPortletId = null;
		
		if(keyTextBox != null){
			for(int i=0;i<keyTextBox.length;i++){
				if(CustomUtil.strNull(keyTextBox[i]).equals("")) continue;
												
				prefs.setValue(keyTextBox[i], String.valueOf(valueTextBox[i]));
			}
		}		
		
		if(mypageTab != null){
			prefs.setValues("mypageTab", mypageTab);
		}		
		prefs.store();
	}
	
	protected PortletConfig getSelPortletConfig(PortletRequest portletRequest)throws SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portletResource = ParamUtil.getString(portletRequest, "portletResource");
		Portlet selPortlet = PortletLocalServiceUtil.getPortletById(themeDisplay.getCompanyId(), portletResource);
		ServletContext servletContext = (ServletContext)portletRequest.getAttribute(WebKeys.CTX);
		PortletConfig selPortletConfig = PortletConfigFactoryUtil.create(selPortlet, servletContext);

		return selPortletConfig;
	}
}
