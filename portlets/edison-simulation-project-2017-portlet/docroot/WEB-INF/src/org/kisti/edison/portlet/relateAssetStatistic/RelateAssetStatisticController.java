package org.kisti.edison.portlet.relateAssetStatistic;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.kisti.edison.constants.SimulationProjectConstants;
import org.kisti.edison.customService.RelateAssetStatisticCustomService;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class RelateAssetStatisticController {

	private static Log log = LogFactoryUtil.getLog(RelateAssetStatisticController.class);
	
	/**
	 * 관련 정보 통계 기본 보기
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
		Locale locale = themeDisplay.getLocale();
		long modelId = Long.parseLong(CustomUtil.strNull(params.get("modelId"), "0"));
		String authYn = CustomUtil.strNull(params.get("authYn"), "N");
		
		Map<String, Object> data = new HashMap<String, Object>();
		int linkScienceAppCount = 0;
		int linkConetentCount = 0;
		int linkScienceDataCount = 0;
		int linkSimulationProjectCount = 0;
		int linkCumunityCount = 0;
		
		linkScienceAppCount = RelateAssetStatisticCustomService.retrieveCountLinkedAssetEntry(locale, SimulationProjectConstants.SCIENCE_APP_CLASS_NAME, modelId);
		linkConetentCount = RelateAssetStatisticCustomService.retrieveCountLinkedAssetEntry(locale, SimulationProjectConstants.SCIENCE_INFORMATION_CLASS_NAME, modelId);
		linkScienceDataCount = RelateAssetStatisticCustomService.retrieveCountLinkedAssetEntry(locale, SimulationProjectConstants.SCIENCE_DATA_CLASS_NAME, modelId);
		linkSimulationProjectCount = RelateAssetStatisticCustomService.retrieveCountLinkedAssetEntry(locale, SimulationProjectConstants.SIMULATION_PROJECT_CLASS_NAME, modelId);
		linkCumunityCount = RelateAssetStatisticCustomService.retrieveCountLinkedAssetEntry(locale, SimulationProjectConstants.COMMUNITY_CLASS_NAME, modelId);
		
		data.put("linkScienceAppCount", linkScienceAppCount);
		data.put("linkConetentCount", linkConetentCount);
		data.put("linkScienceDataCount", linkScienceDataCount);
		data.put("linkSimulationProjectCount", linkSimulationProjectCount);
		data.put("linkCumunityCount", linkCumunityCount);
		
		model.addAttribute("data", data);
		model.addAttribute("authYn", authYn);
		
		return "relateAssetStatistic/statistic"; 
	}

}
