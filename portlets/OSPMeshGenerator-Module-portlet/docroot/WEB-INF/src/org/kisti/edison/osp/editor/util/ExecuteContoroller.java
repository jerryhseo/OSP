package org.kisti.edison.osp.editor.util;

import java.io.IOException;
import java.nio.file.Paths;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.osp.editor.blade.BladeEditorController;
import org.kisti.edison.osp.editor.helper.MeshAppHelper;
import org.kisti.edison.osp.service.ExecuteLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class ExecuteContoroller {
	private static Log log = LogFactory.getLog(BladeEditorController.class);
	@Autowired
	private MeshAppHelper meshAppHelper;
	
	@ResourceMapping(value = "removeExecuteWithPath")
	public void removeExecuteWithPath(
			@RequestParam("projectId") String projectId, 
			@RequestParam("executeId") String executeId,
			ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			String userScreenName = themeDisplay.getUser().getScreenName();
			ExecuteLocalServiceUtil.removeExecuteWithPath(GetterUtil.getLong(projectId), executeId, userScreenName, Paths.get(meshAppHelper.EXECUTE_BASE_PATH).toString());
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
		}
	}
	
	
	protected static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
