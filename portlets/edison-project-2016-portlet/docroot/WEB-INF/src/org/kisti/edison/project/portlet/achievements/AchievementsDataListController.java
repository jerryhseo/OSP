package org.kisti.edison.project.portlet.achievements;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class AchievementsDataListController {
	
	private static Log log = LogFactoryUtil.getLog(AchievementsDataListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		try {
			long groupId = PortalUtil.getScopeGroupId(request);
			long usesPlid = PortalUtil.getPlidFromPortletId(groupId, false, "edisonprojectuses_WAR_edisonproject2016portlet");
			
			model.addAttribute("usesPlid", usesPlid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "achievements/achievementsDataList";
	}
	
	
	@ResourceMapping(value="getAchievementData")
	public void getAchievementData(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, JSONException, IOException{
		try{
			
			long groupId = PortalUtil.getScopeGroupId(request);
			/*List<Map<String,Object>>achievementList = SimulationJobLocalServiceUtil.getMajorAchievementsListForProjectStatistics("Y", "SITE", groupId);*/
			List<Map<String,Object>>achievementList = null;
			JSONObject obj = new JSONObject();
			obj.put("dataList", achievementList);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

