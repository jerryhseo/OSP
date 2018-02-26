package org.kisti.edison.portlet.useApp;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.kisti.edison.constants.SimulationProjectConstants;
import org.kisti.edison.model.SimulationProject;
import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class UseAppController {

	private static Log log = LogFactoryUtil.getLog(UseAppController.class);
	
	/**
	 * 사용 앱 기본 보기
	 * @param modelName 앱과 Link 되어진 모델명 ex) SimulationProject
	 * @param modelId 모델Id
	 * @param model
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException {
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String modelName = CustomUtil.strNull(params.get("modelName"));
		long modelId = Long.parseLong(CustomUtil.strNull(params.get("modelId"), "0"));
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(SimulationProjectConstants.SIMULATION_PROJECT.equals(modelName)){
			data = SimProScienceAppLinkLocalServiceUtil.getScienceAppList(modelId, themeDisplay.getLocale());
		}
		
		long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
		long classId = ClassNameLocalServiceUtil.getClassNameId(SimulationProject.class);
		String authYn = CustomUtil.strNull(params.get("authYn"), "N");
		
		model.addAttribute("data", data);
		model.addAttribute("classId", classId);
		model.addAttribute("simulationProjectId", modelId);
		model.addAttribute("workBenchPlid", plid);
		model.addAttribute("authYn", authYn);
		model.addAttribute("redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		
		return "useApp/appList"; 
	}

}
