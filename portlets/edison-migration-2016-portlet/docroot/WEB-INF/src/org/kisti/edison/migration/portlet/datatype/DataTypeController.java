package org.kisti.edison.migration.portlet.datatype;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.service.PortTypeLocalServiceUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
/*
 * APP LAYOUT 정보 UPDATE
 */
@Controller
@RequestMapping("VIEW")
public class DataTypeController {
	
	private static Log log = LogFactoryUtil.getLog(DataTypeController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		//마이그레이션 버튼 여부
		if(DataTypeLocalServiceUtil.getDataTypesCount()==0){
			model.addAttribute("dataTypeMir", true);
		}else{
			model.addAttribute("dataTypeMir", false);
		}
		
		
		return "migration";
	}
	
	@ResourceMapping(value="dataTypeMirgration")
	public void datatypeMirgration(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			Map params = RequestUtil.getParameterMap(request);
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			Map<String, Integer> result = PortTypeLocalServiceUtil.migrationPortType(themeDisplay.getCompanyId());
			System.out.println("mirdatatypeCnt--->"+result.get("mirPortTypeCnt"));
			json.putAll(result);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
		}
	}
	
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
