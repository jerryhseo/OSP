package org.kisti.edison.osp.analyzer.mesh;

import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class MeshViewerController {
	private static Log log = LogFactory.getLog(MeshViewerController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		Group group = themeDisplay.getScopeGroup();
		String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
		
		model.addAttribute("icebreakerUrl", icebreakerUrl);
		
		
		String viewerCallType = CustomUtil.strNull(params.get("viewerFromType"),"analyzer");
		
		String returnJspFile = "";
		if(viewerCallType.equals("editor")){
			returnJspFile = "editorViewer";
		}else{
			returnJspFile = "analyzerViewer";
		}
		return returnJspFile;
	}
}
