package org.kisti.edison.migration.portlet.appmanual;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class AppManualController {
	
	private static Log log = LogFactoryUtil.getLog(AppManualController.class);
	
    @RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		
		return "view";
	}
    
    @ResourceMapping(value="execute")
	public void execute(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
    	List<ScienceApp> appList = ScienceAppLocalServiceUtil.getAll();
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
    	int errorCnt = 0;
    	for(ScienceApp scienceApp : appList){
    		if(!CustomUtil.strNull(scienceApp.getManualId()).equals("")){
    			for(Locale locale : LanguageUtil.getAvailableLocales()){
    				String manualId = scienceApp.getManualId(locale);
    				if(!CustomUtil.strNull(manualId,"0").equals("0")){
    					try{
    						DLFileEntryLocalServiceUtil.isFileEntryCheckedOut(Long.getLong(manualId));
    					}catch(NullPointerException e){
    						System.out.println(scienceApp.getName()+","+scienceApp.getVersion()+","+locale.toString());
    						errorCnt++;
    					}
    				}
				}
    		} 
    	}
    	
    	JSONObject json = new JSONObject();
    	json.put("errorCnt", errorCnt);
    	
    	response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
    }
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
