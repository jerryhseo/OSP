
package org.kisti.eturb.detailPage.portlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.HttpFileUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.util.icebreaker.IBFileUtil;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
            
        }catch (PortalException | SystemException e){
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
