package org.kisti.edison.bestsimulation.portlet.workbench.dashboard;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Controller
@RequestMapping("VIEW")
public class DashboardController {
	private static Log log = LogFactoryUtil.getLog(DashboardController.class);
	
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		return "view";
	}
}