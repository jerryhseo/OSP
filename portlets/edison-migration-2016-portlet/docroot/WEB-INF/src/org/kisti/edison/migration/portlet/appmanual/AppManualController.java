package org.kisti.edison.migration.portlet.appmanual;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class AppManualController {
	
	private static Log log = LogFactoryUtil.getLog(AppManualController.class);
	
    @RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		
		return "view";
	}
    
    @ResourceMapping(value="execute")
	public void execute(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
    	List<ScienceApp> appList = ScienceAppLocalServiceUtil.getAll();
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
    	int totalCnt = appList.size();
    	int successAppCnt = 0;
    	int errorAppCnt = 0;
    	int errorCnt = 0;
    	
    	for(ScienceApp scienceApp : appList){
    		boolean isErrorApp = false;
    		if(!CustomUtil.strNull(scienceApp.getManualId()).equals("")){
    			for(Locale locale : LanguageUtil.getAvailableLocales()){
    				String manualId = scienceApp.getManualId(locale);
    				if(!CustomUtil.strNull(manualId,"0").equals("0")){
    					try{
    						DLFileEntryLocalServiceUtil.isFileEntryCheckedOut(Long.getLong(manualId));
    					}catch(NullPointerException e){
    						String category = "";
    						try{
    							List<AssetCategory> categoriList = AssetCategoryLocalServiceUtil.getCategories(ScienceApp.class.getName(), scienceApp.getScienceAppId());
    							long preParentCategoryId = 0;
        						for(AssetCategory assetCategory : categoriList){
        							if(preParentCategoryId!=assetCategory.getParentCategoryId()){
        								preParentCategoryId = assetCategory.getParentCategoryId();
        								AssetCategory parentAssetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(preParentCategoryId);
        								category = StringUtil.add(category, parentAssetCategory.getName(), "&&");
        							}
        						}
    						}catch (SystemException e1) {
//    							System.out.println("SystemException==>"+scienceApp.getName());
							}
    						
    						User authorUser = UserLocalServiceUtil.getUser(scienceApp.getAuthorId());
    						
    						
    						String universityField = (String)authorUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
    						String univerFiledValue = EdisonExpndoUtil.getCommonCdSearchFieldValue(universityField, EdisonExpando.CDNM, themeDisplay.getLocale());
    						
    						String msg = "";
    						msg = StringUtil.add(msg, scienceApp.getName());
    						msg = StringUtil.add(msg, scienceApp.getVersion());
    						msg = StringUtil.add(msg, authorUser.getScreenName());
    						msg = StringUtil.add(msg, authorUser.getFirstName());
    						msg = StringUtil.add(msg, univerFiledValue);
    						msg = StringUtil.add(msg, category);
    						msg = StringUtil.add(msg, locale.toString());
    						System.out.println(msg);
    						
    						errorCnt++;
    						isErrorApp = true;
    					}
    				}
				}
    		}
    		
    		if(isErrorApp){
    			errorAppCnt++;
    		}else{
    			successAppCnt++;
    		}
    	}
    	
    	JSONObject json = new JSONObject();
    	json.put("totalCnt", totalCnt);
    	json.put("successAppCnt", successAppCnt);
    	json.put("errorAppCnt", errorAppCnt);
    	
    	json.put("errorCnt", errorCnt);
    	
    	response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
    }
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
