package org.kisti.edison.migration.portlet.content;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class ContentController {
	
	private static Log log = LogFactoryUtil.getLog(ContentController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		
		return "content/migration";
	}

	@ActionMapping(params="myaction=contentMigration")
	public void contentMigration(ActionRequest request, ActionResponse response, ModelMap model){

		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			//일반 콘텐츠
			 ContentLocalServiceUtil.migrationGeneralContentTable(themeDisplay.getCompanyGroupId(), themeDisplay.getCompanyId()); 

			 //고급 콘텐츠
			 ContentLocalServiceUtil.migrationAdvancedContentTable(themeDisplay.getCompanyGroupId(),themeDisplay.getCompanyId());

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	

}
