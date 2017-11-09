package org.kisti.edison.migration.portlet.appcategory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
/*
 * APP LAYOUT 정보 UPDATE
 */
@Controller
@RequestMapping("VIEW")
public class AppCategoryController {
	
	private static Log log = LogFactoryUtil.getLog(AppCategoryController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		//MIRGRATION CHECK
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(AssetEntry.class);
		query.add(RestrictionsFactoryUtil.eq("classNameId", ClassNameLocalServiceUtil.getClassNameId(ScienceApp.class.getName())));
		long mirCnt = AssetEntryLocalServiceUtil.dynamicQueryCount(query);
		if(mirCnt==0){
			model.addAttribute("scienceAppMir", true);
		}else{
			model.addAttribute("scienceAppMir", false);
		}
		
		long groupId = PortalUtil.getScopeGroupId(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		String searchType = "";
		String searchText = "";
		String status = "";
		
		int totalCnt = ScienceAppLocalServiceUtil.countListScienceApp(groupId, locale, 0, null, null, searchType, searchText, status,false);
		model.addAttribute("appCnt", totalCnt);
		
		return "migration";
	}
	
	@ResourceMapping(value="updateCategory")
	public void updateCategory(ResourceRequest request, ResourceResponse response) throws IOException{
		int migrationTargetCnt = 0;
		int existMigration = 0;
		int migration = 0;
		int error = 0;
		long scienceAppId = 0;
		try{
			List<ScienceApp> migrationTargetList = ScienceAppLocalServiceUtil.getAll();
			migrationTargetCnt = migrationTargetList.size();
			for(ScienceApp scienceApp : migrationTargetList){
				scienceAppId = scienceApp.getScienceAppId();
				
				try {
					boolean exist = ScienceAppLocalServiceUtil.migrationScienceApp(scienceApp);
					if(exist){existMigration++;}else{migration++;}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR_SCIENCE_APP-->"+scienceAppId);
					error++;
				}
			}
		}catch (Exception e) {
			
		}finally {
			String resultStr = "migrationTargetCnt-->"+migrationTargetCnt;
			resultStr+="||ExistMigration-->"+existMigration;
			resultStr+="||Migration-->"+migration;
			resultStr+="||ERROR-->"+error;
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			json.put("result", resultStr);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		}
		System.out.println("MIR____END");
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
