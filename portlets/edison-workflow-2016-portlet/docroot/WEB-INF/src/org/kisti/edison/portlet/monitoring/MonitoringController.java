package org.kisti.edison.portlet.monitoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class MonitoringController {
	private static Log log = LogFactoryUtil.getLog(MonitoringController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);
			Locale locale = themeDisplay.getSiteDefaultLocale();
		
			//관리자 및 가상실험실에서 조회 할 경우 userId값이 0.
			long userId = Long.parseLong(CustomUtil.strNull(param.get("userId"), "0"));
			User user = themeDisplay.getUser();
			if(EdisonUserUtil.isPowerUserThan(user)
					|| EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
			}else{
				//현재 접속한 userId를 추가
				userId = user.getUserId();
			}
			
			long groupId = themeDisplay.getScopeGroupId();
			if(EdisonUserUtil.isPowerUserThan(user)
					|| EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.ADMINISTRATOR)){
				userId = 0;
			}
			
			long jobStatus = GetterUtil.getLong(param.get("jobStatus") ,0);
			long classId = GetterUtil.getLong(CustomUtil.strNull(param.get("classId"), "0"), 0);
			long searchClassUserId = GetterUtil.getLong(CustomUtil.strNull(param.get("searchClassUserId"), "0"), 0);
			if(0 < searchClassUserId){
				userId = searchClassUserId;
			}
			long simulationId = GetterUtil.getLong(CustomUtil.strNull(param.get("simulationId"), "0"),0);
			
			int currentPage = Integer.parseInt(CustomUtil.strNull(param.get("currentPage"), "1"));
			int listSize = Integer.parseInt(CustomUtil.strNull(param.get("searchLine"), "10"));
			int blockSize = 10;
			int start = ((currentPage - 1) * listSize);
			int totalCount = 0;
			
			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String searchValue = CustomUtil.strNull(param.get("searchValue"));
			
			List<Map<String, Object>> getWorkflowMonitoringList = WorkflowSimulationLocalServiceUtil.getWorkflowMonitoringList(userId, searchValue, simulationId, jobStatus, classId, start, listSize, locale);
			totalCount = getWorkflowMonitoringList.size();
			
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"pageSearch", totalCount,currentPage, listSize, blockSize);
			
			//WORKBENCH
			long workflowPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "workflowsimulationexecutor_WAR_edisonworkflow2016portlet");
			
			model.addAttribute("workflowPlid", workflowPlid);
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("listSize", listSize);
			model.addAttribute("jobStatus", jobStatus);
			model.addAttribute("searchValue", searchValue);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("paging", paging);
			model.addAttribute("searchClassUserId", searchClassUserId);
			model.addAttribute("getWorkflowMonitoringList", getWorkflowMonitoringList);
			
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
		
		return "monitoring/monitoring"; 
	}
	
	//시뮬레이션 조회
	@ResourceMapping(value = "searchJobList")
	public void searchJobList(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException, ParseException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map param = RequestUtil.getParameterMap(request);
		long simulationId = GetterUtil.getLong(CustomUtil.strNull(param.get("simulationId"), "0"),0);
		
		List<Map<String, Object>> getWorkflowJobMonitoringList = WorkflowSimulationLocalServiceUtil.getWorkflowMonitoringJobList(simulationId, themeDisplay.getSiteDefaultLocale());
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("getWorkflowJobMonitoringList", getWorkflowJobMonitoringList);
		jsonObj.put("getWorkflowJobMonitoringListSize", getWorkflowJobMonitoringList.size());
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
	}
	
}
