package org.kisti.edison.iitp.portlet.smithchart;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("VIEW")
public class SmithChartController{
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		
	return "view";
	}
}

