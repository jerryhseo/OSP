package org.kisti.edison.mypage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class MyPageController {
	protected static Log  log = LogFactoryUtil.getLog(MyPageController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			boolean ownerThan = false;
			boolean isAdmin = false;

			String clickTab = CustomUtil.strNull(params.get("clickTab"),"");
			Locale locale = themeDisplay.getLocale();
			User user = PortalUtil.getUser(request);
				
			long companyId = themeDisplay.getCompanyId();
			Map<String,Object> tabAndButtonMap = tabCreateAndStatusButtonView(request, clickTab, locale, user);
			if(clickTab.equals("")){
				clickTab = GetterUtil.getString(tabAndButtonMap.get("clickTab"));
			}
			String tabStr = GetterUtil.getString(tabAndButtonMap.get("tabString"),"");
			String redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
			
			model.addAttribute("clickTab", clickTab);
			model.addAttribute("tabStr", tabStr);
			model.addAttribute("redirectUrl", redirectURL);
			model.addAttribute("ownerThan", ownerThan);
			model.addAttribute("isAdmin", isAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage/mypage";
	}
	
/*
 *  1. 즐겨찾기 앱(favoriteApp)
	2. 나의 앱 목록(myApp)
	3. 나의 데이터타입(myDataType)
	4. 나의 DataEntry(myScienceData)
	5. 강좌 현황(myCourse)
	6. 수강 현황(myClass)
	7. 내 파일 관리(myFile)
	8. 내 워크스페이스(myWorkspace)
	9. 나의 콘텐츠(myContent)
	10. 나의 프로젝트(myProject)
	11. 나의 데이터(my_Data)
	12. 나의 노트북(myNotebook)
	13. 사이트 가입/탈퇴(siteJoin)
	14. 나의이력관리(myHistory)
	15. 가스터빈 myFile(eturbMyFile)
 * 
 */
	
protected Map<String,Object> tabCreateAndStatusButtonView(RenderRequest request, String clickTab, Locale locale,User user) throws PortalException, SystemException{
	
	String mypageTabStr = request.getPreferences().getValue("mypageTab", "");

	Map<String,Object> returnMap = new HashMap<String,Object>();
	
	String userViewPageTabStr = "";
	boolean powerAdmin = false;
	if(EdisonUserUtil.isPowerUserThan(user)){
		powerAdmin = true;
	}else{
		if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.TEMP_USER)){
			userViewPageTabStr = "favoriteApp,myFile,myClass,eturbMyFile";
		}else{
			userViewPageTabStr = "favoriteApp,myCourse,myClass,myFile,myContent,myProject,siteJoin,myWorkspace";
			
			if(EdisonUserUtil.isDeveloperThan(user)){
				userViewPageTabStr = StringUtil.add(userViewPageTabStr, "myApp",",");
				userViewPageTabStr = StringUtil.add(userViewPageTabStr, "myDataType",",");
			}
			
			if(EdisonUserUtil.isTutorThan(user)){
//				userViewPageTabStr = StringUtil.add(userViewPageTabStr, "myCourse",",");
			}
			
			if(EdisonUserUtil.isProfessorThan(user)){
				userViewPageTabStr = StringUtil.add(userViewPageTabStr, "myHistory",",");
			}
		}
		
	}

	String tabsStr = "";
	if(!mypageTabStr.equals("")){
		String[] mypageTab = mypageTabStr.split(",");
		String[] userViewPage = userViewPageTabStr.split(",");
	
  	if(mypageTab != null){
  		for(int i=0;i<mypageTab.length;i++){
  			if(powerAdmin){
  				tabsStr += tabsStr.equals("") ?  mypageTab[i] : "," +  mypageTab[i];
  			}else{
  				if(ArrayUtil.contains(userViewPage, mypageTab[i])){
  					tabsStr += tabsStr.equals("") ?  mypageTab[i] : "," +  mypageTab[i];
  				}
  			}
  		}
  	}
	}
		String[] tabs = null;
		
		//작성 값에 따른 상태 변화 버튼 확인
		boolean appStatusButtonView = false;
		tabs = StringUtil.split(tabsStr);
		StringBuffer tabString = new StringBuffer();
		
		tabString.append("<ul>");
		
		for(int i=0; i<tabs.length;i++){
			String tab = tabs[i];
			String liClass = tab;
		
			if(clickTab.equals("")){
				if(i==0){
					liClass+=" select";
					clickTab =tab; 
				}
			}else{
				if(tab.contains(clickTab)){
					liClass+=" select";
				}
			}
			String tabName = "";
			String tabValue = "";
			if(tab.contains("favoriteApp")){
				tabName=LanguageUtil.get(locale,"edison-appstore-favorite-app");
				tabValue = "favoriteApp";
			}
			
			if(tab.contains("siteJoin")){
				tabName=LanguageUtil.get(locale,"edison-default-site-join-title");
				tabValue = "siteJoin";
			}
			
			if(tab.contains("myCourse")){
				tabName=LanguageUtil.get(locale,"edison-virtuallab-virtualLabRegistrationList-registration-status");
				tabValue = "myCourse";
			} 
			
			if(tab.contains("myClass")){
				tabName=LanguageUtil.get(locale,"edison-virtuallab-virtualLabClassRegistrationList-class-registration-status");
				tabValue = "myClass";
			}
			
			if(tab.contains("myWorkspace")){
				tabName=LanguageUtil.get(locale,"edison-appstore-developer-request-info");
				tabValue = "myWorkspace";
			}
			if(tab.contains("myFile")){
				tabName=LanguageUtil.get(locale,"edison-myfile-title");
				tabValue = "myFile";
			}
			if(tab.contains("myApp")){
				tabName=LanguageUtil.get(locale,"edison-appstore-myapp-list");
				tabValue = "myApp";
			}
			if(tab.contains("myContent")){
				tabName=LanguageUtil.get(locale,"edison-default-mycontent");
				tabValue = "myContent";
			}
			if(tab.contains("myProject")){
				tabName=LanguageUtil.get(locale,"edison-default-myproject");
				tabValue = "myProject";
			}
			if(tab.contains("myDataType")){
				tabName=LanguageUtil.get(locale,"edison-default-mydatatype");
				tabValue = "myDataType";
			}
			if(tab.contains("myHistory")){
				tabName=LanguageUtil.get(locale,"edison-default-myHistory");
				tabValue = "myHistory";
			}
			if(tab.contains("eturbMyFile")){
				tabName=LanguageUtil.get(locale,"edison-myfile-title");
				tabValue = "eturbMyFile";
			}
			
			if(tab.contains("my_Data")){
				tabName=LanguageUtil.get(locale,"edison-default-my-data-title");
				tabValue = "my_Data";
			}
			
			if(tab.contains("myNotebook")){
				tabName=LanguageUtil.get(locale,"edison-default-my-notebook-title");
				tabValue = "myNotebook";
			}
			
			if(liClass.contains("select")){                 
				tabString.append("<li class=\""+liClass+"\" onclick=\"javascript:tabAction('"+ tabValue+ "');\">"+tabName+ "&nbsp;&nbsp;&nbsp;&nbsp;<img src='/edison-default-2016-portlet/images/mypage/swarrow01.png' width='11' height='18'></li>");
			}else{
				tabString.append("<li class=\""+liClass+"\" onclick=\"javascript:tabAction('"+ tabValue+ "');\" >"+tabName+"</li>");
			}
		}
		
		tabString.append("</ul>");
		returnMap.put("clickTab", clickTab);
		returnMap.put("tabString", tabString.toString());
		returnMap.put("appStatusButtonView", appStatusButtonView);
		return returnMap;
	}
}




