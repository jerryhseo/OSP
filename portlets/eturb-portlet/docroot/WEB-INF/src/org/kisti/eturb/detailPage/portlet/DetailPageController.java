
package org.kisti.eturb.detailPage.portlet;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class DetailPageController{
	
	private static Log log = LogFactory.getLog(DetailPageController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		try{
		    
            long companyGroupId = PortalUtil.getCompany(request).getGroupId();
            long groupId = PortalUtil.getScopeGroupId(request);
            Locale locale = themeDisplay.getLocale();
            
            PortletPreferences prefs = request.getPreferences();
            
            /* eTURB sub menu */
            String subMenuName = themeDisplay.getLayout().getName(locale);
            model.addAttribute("subMenuName", subMenuName);
            
            /* page별 sub menu background */
            String imagePath = request.getContextPath()+"/images/sub-topvisualbg.jpg";
            model.addAttribute("imagePath", imagePath);
            
            System.out.println(request.getPortalContext());
            System.out.println(themeDisplay.getPathContext());
            System.out.println(themeDisplay.getCDNBaseURL());
            System.out.println(themeDisplay.getPortalURL());
            
        }catch (Exception e){
            e.printStackTrace();
            SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
        }
		
		return "list";
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
