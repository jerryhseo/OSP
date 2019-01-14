
package org.kisti.edison.science.portlet.appmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.science.NoSuchScienceAppCompileException;
import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.Exception.ScienceAppException;
import org.kisti.edison.science.model.CommonLib;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.RequiredLibConfirm;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppCompile;
import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.model.ScienceAppManager;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.CommonLibLocalServiceUtil;
import org.kisti.edison.science.service.DeveloperInfoLocalServiceUtil;
import org.kisti.edison.science.service.RequiredLibConfirmLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppCompileLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppManagerLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.science.service.constants.RequiredLibConstants;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.science.service.persistence.RequiredLibConfirmPK;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.HtmlFormUtils;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class AppManagerController{
	
	private static Log log = LogFactory.getLog(AppManagerController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws SystemException, IOException, PortalException, SQLException{
		Map params = RequestUtil.getParameterMap(request);
		User user = PortalUtil.getUser(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long groupId = PortalUtil.getScopeGroupId(request);
		/* Tabs - Setting */
		String tabNames = LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-toolkit-list-owned-app")+",";
		tabNames += LanguageUtil.get(themeDisplay.getLocale(), "edison-science-appstore-toolkit-list-management-app");
		model.addAttribute("tabNames", tabNames);
		String listTabValue = CustomUtil.strNull(params.get("tabValue"), "owner_sw");
		model.addAttribute("listTabValue", listTabValue);
		
		/* DATA - Search */
		int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
		int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("linePerPage"), "10"));
		int pagePerBlock = 5;
		
		int totalCnt = 0;
		int begin = (curPage - 1) * linePerPage;
		int end = linePerPage;

		List<Map<String,Object>> swList = new ArrayList<Map<String,Object>>();
		
		boolean isPortal = themeDisplay.getScopeGroup().getParentGroupId()==0?true:false;
		long companyGroupId = themeDisplay.getCompanyGroupId();
		Locale locale = themeDisplay.getLocale();
		long userId = themeDisplay.getUserId();
		
		String status = CustomUtil.strNull(params.get("searchStatus"));
		String searchAppType = CustomUtil.strNull(params.get("searchAppType"),"");
		
		Map<String,Object>searchParam = new HashMap<String,Object>();
		searchParam.put("likeSwNameAndSwTitle", CustomUtil.strNull(params.get("likeSwNameAndSwTitle")));
		searchParam.put("likeUserName", CustomUtil.strNull(params.get("likeUserName")));
		searchParam.put("likeOrgName", CustomUtil.strNull(params.get("likeOrgName")));
		
		String[] appTypes = null;
		if(!searchAppType.equals("")){
			appTypes = new String []{searchAppType};
		}
		if(listTabValue.equals("owner_sw")){
			
			if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)){
				if(isPortal){
					totalCnt = ScienceAppLocalServiceUtil.countListScienceApp(groupId, locale, 0, appTypes, null, searchParam, status,false);
					swList = ScienceAppLocalServiceUtil.retrieveListScienceApp(groupId, locale, 0, appTypes, null,searchParam, status, begin, end,false);
				}else{
					totalCnt = ScienceAppLocalServiceUtil.countListScienceAppAsCategory(companyGroupId, groupId, locale, 0, appTypes, null, searchParam, status,false);
					swList = ScienceAppLocalServiceUtil.retrieveListScienceAppAsCategory(companyGroupId, groupId,locale, 0, appTypes, null, searchParam, status, begin, end,false);
				}
			}else{
				if(isPortal){
					totalCnt = ScienceAppLocalServiceUtil.countListScienceApp(groupId, locale, userId, appTypes, null, searchParam, status,false);
					swList = ScienceAppLocalServiceUtil.retrieveListScienceApp(groupId, locale, userId, appTypes, null, searchParam, status, begin, end,false);
				}else{
					totalCnt = ScienceAppLocalServiceUtil.countListScienceAppAsCategory(companyGroupId, groupId, locale, userId, appTypes, null, searchParam, status,false);
					swList = ScienceAppLocalServiceUtil.retrieveListScienceAppAsCategory(companyGroupId, groupId,locale, userId, appTypes, null, searchParam, status, begin, end,false);
				}
			}
		}else{
			//ManagerApp 조회
			boolean categorySearch = false;
			if(!isPortal){
				categorySearch = true;
			}
			totalCnt = ScienceAppLocalServiceUtil.countScienceAppAsManager(companyGroupId, groupId, locale, userId, appTypes, null, searchParam, status, categorySearch);
			swList = ScienceAppLocalServiceUtil.retrieveListScienceAppAsManager(companyGroupId, groupId, locale, userId, appTypes, null, searchParam, status, categorySearch, begin, end);
		}
		
		String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"dataSearchList", totalCnt, curPage, linePerPage, pagePerBlock);
		
		model.addAttribute("searchStatus", CustomUtil.strNull(params.get("searchStatus")));
		model.addAttribute("searchValue", CustomUtil.strNull(params.get("searchValue")));
		model.addAttribute("searchAppType", CustomUtil.strNull(params.get("searchAppType")));
		model.addAttribute("swList", swList);
		model.addAttribute("pagingStr", pagingStr);
		model.addAttribute("pageNum", totalCnt- (curPage-1) * linePerPage);
		
		
		String redirectURL = GetterUtil.getString(params.get("redirectURL"),EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		String redirectName = GetterUtil.getString(params.get("redirectName"),"My EDISON");
		model.addAttribute("redirectURL", redirectURL);
		model.addAttribute("redirectName", redirectName);
		
		return "appmanager/list";
	}
	
	@RenderMapping(params="myRender=solverRender")
	public String solverRender(RenderRequest request, RenderResponse response, ModelMap model){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"), 0);
			ScienceApp scienceApp = null;
			boolean isPort = false;
			boolean ownerThan = false;
			boolean isAdmin = false;
			
			Map<String,Object> data = new HashMap<String,Object>();
			Locale locale = themeDisplay.getLocale();
			
			if(scienceAppId!=0){
				scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
				data = ScienceAppLocalServiceUtil.getScienceAppReturnObject(scienceAppId,locale);
				isPort = scienceApp.getIsPort();
				
				//관리자 권한 확인
				if (EdisonUserUtil.isRegularRole(themeDisplay.getUser(), RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), RoleConstants.SITE_ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), RoleConstants.SITE_OWNER)
						|| scienceApp.getAuthorId() == themeDisplay.getUser().getUserId()) {
					ownerThan = true;
				}else{
					//APP 권한 확인
					checkAppPermission(scienceApp,themeDisplay.getUserId());
				}
				
				if (EdisonUserUtil.isRegularRole(themeDisplay.getUser(), RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), RoleConstants.SITE_ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getScopeGroupId(), RoleConstants.SITE_OWNER)) {
					isAdmin = true;
				}
				
				//ASSET Entry 조회
				long entryId = 0;
				try{
					entryId = AssetEntryLocalServiceUtil.getEntry(ScienceApp.class.getName(), scienceAppId).getEntryId();
				}catch(NoSuchEntryException e){
					//마이그레이션이 안된 항목 일 경우 AssetEntry에 값 추가
					entryId = CounterLocalServiceUtil.increment(AssetEntry.class.getName());
					AssetEntry assetEntry =  AssetEntryLocalServiceUtil.createAssetEntry(entryId);
					assetEntry.setGroupId(GetterUtil.getLong(data.get("groupId")));
					assetEntry.setCompanyId(GetterUtil.getLong(data.get("companyId")));
					assetEntry.setUserId(GetterUtil.getLong(data.get("userId")));
					assetEntry.setUserName(GetterUtil.getString(data.get("userScreenName")));
					assetEntry.setClassName(ScienceApp.class.getName());
					assetEntry.setClassPK(scienceAppId);
					assetEntry.setTitle(GetterUtil.getString(data.get("name")));
					assetEntry.setVisible(true);
					AssetEntryLocalServiceUtil.addAssetEntry(assetEntry);
				}
				
				data.put("entryId",entryId);
				
			}else{
				isPort = GetterUtil.getBoolean(params.get("isPort"), false);
			}
			
			// 2018.12.18. WorkFlow App 등록 __ 0 == seq : 기존 사이언스앱, 0 < seq : 워크플로우앱 
			long workflowId = Long.parseLong(CustomUtil.strNull(params.get("workflowId"), "0"));
			model.addAttribute("workflowId", workflowId);
			
			String clickTab = CustomUtil.strNull(params.get("clickTab"),"m01");
			
			String mode = Constants.ADD;
			if(clickTab.equals("m01")){
				if(scienceAppId!=0){
					mode = Constants.UPDATE;
				}else{
					model.addAttribute("userScreenName", themeDisplay.getUser().getScreenName());
				}
			
			}else if(clickTab.equals("m02")){
				if(!scienceApp.getExeFileName().equals("")){
					
					if(!CustomUtil.strNull(scienceApp.getSrcFileName()).equals("")){
						long sourceFileId = GetterUtil.getLong(scienceApp.getSrcFileName());
						data.put("sourceFileId", sourceFileId);
						DLFileEntry sourceFileDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(sourceFileId);
						data.put("sourceFileRepositoryId", sourceFileDl.getRepositoryId());
						data.put("sourceFileUuid", sourceFileDl.getUuid());
						data.put("sourceFileTitle", sourceFileDl.getTitle());
					}
					mode = Constants.UPDATE;
					
					boolean isOsWindow = false;
					if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
						isOsWindow = true;
					}
					
					model.addAttribute("isOsWindow", isOsWindow);
				}
				
				//SELECT BOX 생성
				String openLevelOptions = HtmlFormUtils.makeCombo(ScienceAppConstants.getScienceAppOpenLevels(), scienceApp.getOpenLevel());
				
				String appTypeOptions = "";
				if(scienceApp.getIsPort()){
					appTypeOptions = HtmlFormUtils.makeCombo(ScienceAppConstants.getScienceAppSwTypes(), scienceApp.getAppType());
				}else{
					appTypeOptions = HtmlFormUtils.makeCombo(ScienceAppConstants.getScienceAppEditorTypes(), scienceApp.getAppType());
				}
				
				String editorTypeOptions = HtmlFormUtils.makeCombo(ScienceAppConstants.getEditorTypes(), scienceApp.getEditorType());
				String[] runTypes = new String[1];
				runTypes[0] = ScienceAppConstants.getScienceAppRunTypes()[0];
				String runTypeOptions = HtmlFormUtils.makeCombo(runTypes, scienceApp.getRunType());
				String parallelOptions = HtmlFormUtils.makeCombo(ScienceAppConstants.getScienceAppParallerModule(), scienceApp.getParallelModule());

				model.addAttribute("editorTypeOptions",editorTypeOptions);
				model.addAttribute("openLevelOptions",openLevelOptions);
				model.addAttribute("appTypeOptions",appTypeOptions);
				model.addAttribute("runTypeOptions",runTypeOptions);
				model.addAttribute("parallelOptions",parallelOptions);

				String binFolderListToStr = ScienceAppCompileLocalServiceUtil.retrieveListTartgetDir(themeDisplay.getCompanyId(), scienceApp.getName(), scienceApp.getVersion());
				model.addAttribute("binFolderListToStr",binFolderListToStr);
				
				long scienceAppExecute = scienceApp.getExecute();
				String scienceAppCluster = scienceApp.getCluster();
				model.addAttribute("execute",scienceAppExecute);
				model.addAttribute("cluster",scienceAppCluster);
				
				if(scienceApp.getIsCompile()){
					//git 
					try{
  					ScienceAppCompile scienceAppCompile = ScienceAppCompileLocalServiceUtil.getScienceAppCompile(scienceApp.getScienceAppId());
  					if(scienceAppCompile != null){
  						model.addAttribute("scienceAppCompile", scienceAppCompile);
  					}
					}catch (NoSuchScienceAppCompileException e){
						
					}
				}else{
  				//실행 파일 업로드 확인
					boolean exeFileUpload = false;
					
					String appBasePath = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), EdisonPropsUtil.SCIENCEAPP_BASE_PATH)
										+scienceApp.getName()+File.separator
										+scienceApp.getVersion();
					
					String targetPath = appBasePath+File.separator+"bin";
					
					exeFileUpload = ScienceAppLocalServiceUtil.existScienceAppPath(targetPath);
					model.addAttribute("exeFileUpload",exeFileUpload);
				
				}
				
			}else if(clickTab.equals("m03")){
				long inputCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
				long outputCnt = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount(scienceAppId);
				long logCnt = ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPortsesCount(scienceAppId);
				
				//port 조회
				String inputPorts = "";
				if(inputCnt!=0){
					inputPorts = ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
				}
				
				String outputPorts = "";
				if(outputCnt!=0){
					outputPorts = ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
				}
				
				String logPorts = "";
				if(logCnt!=0){
					logPorts = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
				}
				
				data.put("inputPorts", inputPorts);
				data.put("outputPorts", outputPorts);
				data.put("logPorts", logPorts);
				
				//port update 여부 확인을 위한 초기 변수 셋팅
				if(inputCnt==0&&outputCnt==0&&logCnt==0){
					data.put("portExist", false);
				}else{
					data.put("portExist", true);
				}
				mode = Constants.UPDATE;
			}else if(clickTab.equals("m04")){
				/*Flow WorkBench 셋팅*/
				Boolean isStepLayout = scienceApp.getIsStepLayout();
				if(!CustomUtil.strNull(params.get("isStepLayout")).equals("")){
					isStepLayout = GetterUtil.getBoolean(params.get("isStepLayout"));
				}else{
					if(scienceApp.getLayout().equals("")){
						//Layout 값이 없을 경우 Default false 셋팅
						//parameter도 없을 경우
						isStepLayout = false;
					}
				}
				model.addAttribute("isStepLayout", isStepLayout);
				model.addAttribute("appIsSteopLayout", scienceApp.getIsStepLayout());
				
				/*Layout Key 셋팅*/
				String stepTabsValue = "";
				if(!isStepLayout){
					stepTabsValue = "LAYOUT";
				}else{
					stepTabsValue = CustomUtil.strNull(params.get("stepTabsValue")).equals("")?"INPUT":params.get("stepTabsValue").toString();
				}
				
				/*TemplateId Setting*/
				String appTemplateId = "";
				String paramTemplateId = CustomUtil.strNull(params.get("templateId"));
				if(!scienceApp.getLayout().equals("")){
					net.sf.json.JSONObject appLayout = (net.sf.json.JSONObject) net.sf.json.JSONSerializer.toJSON(scienceApp.getLayout());
					if(isStepLayout){
						net.sf.json.JSONObject stepLayout =appLayout.getJSONObject(stepTabsValue);
						if(!stepLayout.isNullObject()){
							appTemplateId = stepLayout.getString("templateId_");
						}
					}else{
						net.sf.json.JSONObject layout =appLayout.getJSONObject("LAYOUT");
						if(!layout.isNullObject()){
							appTemplateId = layout.getString("templateId_");
						}
					}
				}
				
				if(paramTemplateId.equals("")&&!appTemplateId.equals("")){
					paramTemplateId = appTemplateId;
				}

				/*Sample Port Print*/
				boolean isSamplePortPrint = true;
				if(appTemplateId.equals(paramTemplateId)){
					isSamplePortPrint = false;
				}
				
				long inputCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
				boolean logExist = ScienceAppLogPortsLocalServiceUtil.isScienceAppLogPortsValus(scienceAppId);
				boolean outputExist = ScienceAppOutputPortsLocalServiceUtil.isScienceAppOutPortsValus(scienceAppId);
				
				/*Tab Validation*/
				List<Map<String,String>> tabList = new ArrayList<Map<String,String>>();
				if(inputCnt!=0){
					HashMap<String,String> tabMap = new HashMap<String,String>();
					tabMap.put("name", "INPUT");
					tabMap.put("className", getTabClassFromLayout("INPUT",scienceApp.getLayout()));
					tabList.add(tabMap);
				}
				
				if(logExist){
					HashMap<String,String> tabMap = new HashMap<String,String>();
					tabMap.put("name", "LOG");
					tabMap.put("className", getTabClassFromLayout("LOG",scienceApp.getLayout()));
					tabList.add(tabMap);
				}
				
				if(outputExist){
					HashMap<String,String> tabMap = new HashMap<String,String>();
					tabMap.put("name", "OUTPUT");
					tabMap.put("className", getTabClassFromLayout("OUTPUT",scienceApp.getLayout()));
					tabList.add(tabMap);
				}
				model.addAttribute("tabList", tabList);
				
				if(!paramTemplateId.equals("")){
					List<Map<String,Object>> portList = new ArrayList<Map<String,Object>>();
					if(inputCnt!=0){
						portList.addAll(ScienceAppInputPortsLocalServiceUtil.portAppList(scienceAppId,themeDisplay.getLocale()));
						
					}
					
					if(logExist){
						portList.addAll(ScienceAppLogPortsLocalServiceUtil.portAppList(scienceAppId,themeDisplay.getLocale()));
						
					}
					
					if(outputExist){
						portList.addAll(ScienceAppOutputPortsLocalServiceUtil.portAppList(scienceAppId,themeDisplay.getLocale()));
						
					}
					
					data.put("portList", portList);
					
					if(isSamplePortPrint){
						//port 조회
						String inputPorts = "";
						if(inputCnt!=0){
							inputPorts = ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
						}
						
						String logPorts = "";
						if(logExist){
							logPorts = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
						}
						
						String outputPorts = "";
						if(outputExist){
							outputPorts = ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
						}
						
						
						data.put("inputPorts", inputPorts);
						data.put("outputPorts", outputPorts);
						data.put("logPorts", logPorts);
					}
				}
				
				
				data.put("layouts", scienceApp.getLayout());
				data.put("isSamplePortPrint", isSamplePortPrint);
				data.put("templateId", paramTemplateId);
				model.addAttribute("templateJSP", paramTemplateId);
				
				model.addAttribute("stepTabsValue", stepTabsValue);
			}else if(clickTab.equals("m05")){
				mode = Constants.UPDATE;
				//CKEditor
				boolean isPortal = themeDisplay.getScopeGroup().getParentGroupId()==0?true:false;
				long portalGroupId = isPortal?themeDisplay.getScopeGroupId():themeDisplay.getScopeGroup().getParentGroupId();
				EdisonFileUtil.checkUserFolder(request, themeDisplay.getUserId(),portalGroupId, EdisonFileConstants.USER_IMAGE);
				String currunt_folder = "/" +portalGroupId+" - " +CompanyLocalServiceUtil.getCompany(PortalUtil.getCompanyId(request)).getName() + "/"+portalGroupId+"_EDISON_FILE"+"/"
										+EdisonFileConstants.USER_IMAGE+"/"+themeDisplay.getUserId()+ "/";
				model.addAttribute("currentFolder", currunt_folder);
				
				//Category 정보 조회
				long companyGroupId = themeDisplay.getCompany().getGroupId();	
				AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
				
				int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary.getVocabularyId());
				Map<Long,List<Map<String,Object>>> childrenCategoryGroupMap =  new HashMap<Long,List<Map<String,Object>>>();
				List<Map<String,Object>> parentCategoryList = new ArrayList<Map<String,Object>>();
				
				if(rootCategoryCnt!=0){
					List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(aVocabulary.getVocabularyId(), -1, -1,null);
					for(AssetCategory rootCatogory : rootCategoryList){
						Map<String,Object> parentCategoryMap = new HashMap<String,Object>();
						parentCategoryMap.put("value", rootCatogory.getCategoryId());
						parentCategoryMap.put("name", rootCatogory.getTitle(themeDisplay.getLocale()));
						parentCategoryList.add(parentCategoryMap);
						
						List<AssetCategory> childCategoryList =  AssetCategoryLocalServiceUtil.getChildCategories(rootCatogory.getCategoryId());
						List<Map<String,Object>> childrenCategoryList = new ArrayList<Map<String,Object>>();
						for(AssetCategory childCatogory : childCategoryList){
							Map<String,Object> childrenCategoryMap = new HashMap<String,Object>();
							childrenCategoryMap.put("value", childCatogory.getCategoryId());
							childrenCategoryMap.put("name", childCatogory.getTitle(themeDisplay.getLocale()));
							childrenCategoryList.add(childrenCategoryMap);
						}
						childrenCategoryGroupMap.put(rootCatogory.getCategoryId(),childrenCategoryList);
					}
				}
				model.addAttribute("childrenCategoryGroupMap",childrenCategoryGroupMap);
				model.addAttribute("parentCategoryList",parentCategoryList);
			}
			
			if(scienceAppId!=0){
				model.addAttribute("scienceAppId", scienceAppId);
			}
			
			if(isPort){
				//SCIENCEAPP_WORK_BENCH
				long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
				model.addAttribute("workBenchPlid", plid);
			}
			
			// Workflow App 등록 시 Tab Menu Setting
			Map<String,Object> tabAndButtonMap = tabCreateAndStatusButtonView(scienceAppId, isPort, clickTab, locale, data, workflowId);
			
			String tabStr = GetterUtil.getString(tabAndButtonMap.get("tabString"),"");
			boolean appStatusButtonView = GetterUtil.getBoolean(tabAndButtonMap.get("appStatusButtonView"),false);
			boolean appTestButtonView = GetterUtil.getBoolean(tabAndButtonMap.get("appTestButtonView"),false);

			model.addAttribute("data", data);
			model.addAttribute("mode", mode);
			model.addAttribute("clickTab", clickTab);
			model.addAttribute("tabStr", tabStr);
			model.addAttribute("appStatusButtonView", appStatusButtonView);
			model.addAttribute("appTestButtonView", appTestButtonView);
			model.addAttribute("isPort", isPort);
			model.addAttribute("ownerThan", ownerThan);
			model.addAttribute("isAdmin", isAdmin);
			
			//리스트 검색 파라미터
			model.addAttribute("searchValue",CustomUtil.strNull(params.get("searchValue")));
			model.addAttribute("searchOption",CustomUtil.strNull(params.get("searchOption")));
			model.addAttribute("searchStatus",CustomUtil.strNull(params.get("searchStatus")));
			model.addAttribute("p_curPage",CustomUtil.strNull(params.get("p_curPage")));
			
			
			String redirectURL = GetterUtil.getString(params.get("redirectURL"),EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
			String redirectName = GetterUtil.getString(params.get("redirectName"),"My EDISON");
			
			String redirectOrignURL=HttpUtil.decodeURL(redirectURL);
			if(!CustomUtil.strNull(params.get("searchValue")).equals("")){redirectOrignURL=HttpUtil.addParameter(redirectOrignURL, "searchValue", params.get("searchValue").toString());}
			if(!CustomUtil.strNull(params.get("searchOption")).equals("")){redirectOrignURL=HttpUtil.addParameter(redirectOrignURL, "searchOption", params.get("searchOption").toString());}
			if(!CustomUtil.strNull(params.get("searchStatus")).equals("")){redirectOrignURL=HttpUtil.addParameter(redirectOrignURL, "searchStatus", params.get("searchStatus").toString());}
			if(!CustomUtil.strNull(params.get("p_curPage")).equals("")){redirectOrignURL=HttpUtil.addParameter(redirectOrignURL, "p_curPage", params.get("p_curPage").toString());}
			
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectName", redirectName);
			
			if(!SessionErrors.isEmpty(request)){
				PortalUtil.clearRequestParameters(request);
			}
		} catch (Exception e) {
			if(e instanceof ScienceAppException){
				SessionErrors.add(request, e.getClass(), e);
				return "appmanager/list";
			}else{
				log.error(e);
				e.printStackTrace();
				SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			}
		}
		return "appmanager/view";
	}
	
	private boolean appLayoutStepValidation(ScienceApp scienceApp) throws SystemException{
		long scienceAppId = scienceApp.getScienceAppId();
		
		long inputCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
		boolean logExist = ScienceAppLogPortsLocalServiceUtil.isScienceAppLogPortsValus(scienceAppId);
		boolean outputExist = ScienceAppOutputPortsLocalServiceUtil.isScienceAppOutPortsValus(scienceAppId);
		
		
		boolean result = true;
		if(inputCnt!=0&&result){
			result = validationFromLayout("INPUT",scienceApp.getLayout());
		}
		
		if(logExist&&result){
			result = validationFromLayout("LOG",scienceApp.getLayout());
		}
		
		if(outputExist&&result){
			result = validationFromLayout("OUTPUT",scienceApp.getLayout());
		}
		
		return result;
	}
	
	private String getTabClassFromLayout(String key,String layout){
		if(validationFromLayout(key,layout)){
			return "";
		}else{
			return "isNULL";
		}
	}
	
	private boolean validationFromLayout(String key,String layout){
		Boolean result = false;
		if(!layout.equals("")){
			net.sf.json.JSONObject appLayout = (net.sf.json.JSONObject) net.sf.json.JSONSerializer.toJSON(layout);
			try{
				net.sf.json.JSONArray keys = appLayout.getJSONArray("arrayKeys_");
				if(!keys.isEmpty()){
					if(keys.contains(key)){
						result = true;
					}
				}
			}catch (net.sf.json.JSONException e) {
				result = false;
			}
		}
		return result;
	}
	
	@ActionMapping(value="appAction")
	public void appAction(ActionRequest request, ActionResponse response, Model model){
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		
		String actionType = CustomUtil.strNull(params.get("actionType"));
		String mode = CustomUtil.strNull(params.get("actionMode"));
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0l);
		
		try{
			if(mode.equals(Constants.DELETE)){
				ScienceAppLocalServiceUtil.deleteScienceAppRelation(scienceAppId);
			}else{
				if(actionType.equals("appInfomation")){
					if(mode.equals(Constants.ADD)){
						params.put("duplicationCheck", "check");
					}else if(mode.equals(Constants.UPDATE)){
						String version = CustomUtil.strNull(params.get("version"));
						String previousVersion = CustomUtil.strNull(params.get("previousVersion"));
						if(!version.equals(previousVersion)){
							params.put("duplicationCheck", "check");
						}
					}
					
					ServiceContext sc = ServiceContextFactory.getInstance(ScienceApp.class.getName(), request);
					scienceAppId = appInfomation(sc, params, groupId, companyId);
					response.setRenderParameter("scienceAppId", String.valueOf(scienceAppId));
					
				}else if(actionType.equals("exeInfomation")){
					ServiceContext sc = ServiceContextFactory.getInstance(ScienceApp.class.getName(), request);
					exeInfomation(sc, params, scienceAppId);
				}else if(actionType.equals("portInfomation")){
					
					String inputPorts = CustomUtil.strNull(params.get("inputPorts"));
					String outputPorts = CustomUtil.strNull(params.get("outputPorts"));
					String logPorts = CustomUtil.strNull(params.get("logPorts"));
					
					if(!inputPorts.equals("")){
						long inputCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
						
						if(inputCnt!=0){
							ScienceAppInputPorts ports=  ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPorts(scienceAppId);
							ports.setInputPorts(inputPorts);
							ScienceAppInputPortsLocalServiceUtil.updateScienceAppInputPorts(ports);
						}else{
							ScienceAppInputPortsLocalServiceUtil.create(scienceAppId, inputPorts);
						}
					}
					
					if(!outputPorts.equals("")){
						long outputCnt = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount(scienceAppId);
						
						if(outputCnt!=0){
							ScienceAppOutputPorts ports=  ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPorts(scienceAppId);
							ports.setOutputPorts(outputPorts);
							ScienceAppOutputPortsLocalServiceUtil.updateScienceAppOutputPorts(ports);
						}else{
							ScienceAppOutputPortsLocalServiceUtil.create(scienceAppId, outputPorts);
						}
					}
					
					if(!logPorts.equals("")){
						long logCnt = ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPortsesCount(scienceAppId);
						
						if(logCnt!=0){
							ScienceAppLogPorts ports=  ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
							ports.setLogPorts(logPorts);
							ScienceAppLogPortsLocalServiceUtil.updateScienceAppLogPorts(ports);
						}else{
							ScienceAppLogPortsLocalServiceUtil.create(scienceAppId, logPorts);
						}
					}
					
					boolean initLayout = GetterUtil.getBoolean(params.get("initLayout"),false);
					if(initLayout){
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
						scienceApp.setLayout("");
						ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
					}
				}else if(actionType.equals("appLayout")){
					String layout = CustomUtil.strNull(params.get("layout"));
					Boolean isStepLayout = GetterUtil.getBoolean(params.get("isStepLayout"),false);
					String stepTabsValue = CustomUtil.strNull(params.get("stepTabsValue"),"LAYOUT");
					
					if(!layout.equals("")){
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
						scienceApp.setLayout(layout);
						scienceApp.setIsStepLayout(isStepLayout);
						ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
						response.setRenderParameter("stepTabsValue", stepTabsValue);
					}
				}else if(actionType.equals("publicData")){
					ServiceContext sc = ServiceContextFactory.getInstance(ScienceApp.class.getName(), request);
					scienceAppId = appInfomation(sc, params, groupId, companyId);
					response.setRenderParameter("scienceAppId", String.valueOf(scienceAppId));
				}
				
			}
			
			
			
			if(!mode.equals(Constants.DELETE)){
				if(mode.equals(Constants.ADD)){
					SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
				}else if(mode.equals(Constants.UPDATE)){
					SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
				}
				RequestUtil.copyRequestParameters(request, response, new String[] {"searchValue", "searchOption","searchStatus", "p_curPage", "clickTab", "scienceAppId", "isPort","redirectURL","redirectName"});
				response.setWindowState(LiferayWindowState.MAXIMIZED);
			}else{
				response.setPortletMode(PortletMode.VIEW);
				SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			response.setRenderParameter("myRender", "solverRender");
			PortalUtil.copyRequestParameters(request, response);
			
			if (e instanceof ScienceAppException){
				SessionErrors.add(request, e.getClass(), e);
			}else{
				//Session Error Message
				if(mode.equals(Constants.ADD)){
					SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
				}else if(mode.equals(Constants.UPDATE)){
					SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
				}else if(mode.equals(Constants.DELETE)){
					SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
				}
			}
			
		}finally {
			if(!mode.equals(Constants.DELETE)){
				response.setRenderParameter("p_p_state", "maximized");
				response.setRenderParameter("myRender", "solverRender");
			}
		}
	}
	
	protected long appInfomation(ServiceContext sc,Map params, long groupId, long companyId) throws SystemException, ScienceAppException, NoSuchScienceAppException{
		String mode = CustomUtil.strNull(params.get("actionMode"));
		String duplicationCheck = CustomUtil.strNull(params.get("duplicationCheck"));
		long returnLong = 0;
		
		if(!duplicationCheck.equals("")){
			String appName = CustomUtil.strNull(params.get("name"));
			String appVersion = CustomUtil.strNull(params.get("version"));
			
			boolean isDuplicated = ScienceAppLocalServiceUtil.existApp(appName, appVersion);
			
			if(isDuplicated){
				throw new ScienceAppException(ScienceAppException.EXISTS_NAME_VERSION_DATABASE);
			}
		}
		
		if(mode.equals(Constants.ADD)){
			try {
				long workflowId = Long.parseLong(CustomUtil.strNull(params.get("workflowId"), "0"));
				int workflowAppCnt = ScienceAppLocalServiceUtil.countScienceAppByWorkflowId(workflowId);
				
				if(workflowAppCnt == 0){
					ScienceApp scienceApp = ScienceAppLocalServiceUtil.createScienceApp(sc, params);
					returnLong = scienceApp.getScienceAppId();
				} else {
					throw new ScienceAppException(ScienceAppException.EXISTS_WORKFLOW_APP_DATABASE);
				}
			} catch (Exception e) {
				log.error("Create ScienceApp Error...!!");
				throw new ScienceAppException(ScienceAppException.EXISTS_WORKFLOW_APP_DATABASE);
			}
		}else if(mode.equals(Constants.UPDATE)){
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"));
			ScienceAppLocalServiceUtil.updateScienceApp(sc, params, scienceAppId);
			returnLong = scienceAppId; 
		}else if(mode.equals(Constants.DELETE)){
			
		}
		return returnLong;
	}
	
	protected void exeInfomation(ServiceContext sc, Map params, long scienceAppId) throws PortalException, SystemException{
		ScienceAppLocalServiceUtil.updateExeInfomaionScienceApp(sc,params,scienceAppId);
	}
	
	protected Map<String,Object> tabCreateAndStatusButtonView(long scienceAppId, boolean isPort,String clickTab, Locale locale, Map<String,Object> data, long workflowId) throws PortalException, SystemException{
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		String[] tabs = null;
		int activateTab = 1;
		//작성 값에 따른 상태 변화 버튼 확인
		boolean appStatusButtonView = true;
		//Layout 에 따른 Workbench 이동
		boolean appTestButtonView = false;
		if(scienceAppId == 0){
			if(workflowId == 0){
				if(isPort){
					tabs = new String[]{"m01fail", "m02fail", "m03fail", "m04fail", "m05fail"};
				}else{
					tabs = new String[]{"m01fail", "m02fail", "m05fail"};
				}
			} else {
				tabs = new String[]{"m01fail", "m05fail"};
			}
		}else{
			String tabsStr = "";
			if(clickTab.equals("m01")){
				tabsStr +="m01over";
			}else{
				tabsStr +="m01out";
			}
			activateTab++;
			
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			
			// 2018.12.18 workflowId check _ workflowId == 0 : 기존 사이언스앱, workflowId != 0 : 워크플로우앱
			if(scienceApp.getWorkflowId() == 0){
				if(scienceApp.getExeFileName().equals("")){
					tabsStr +=",m02fail";
					appStatusButtonView = false;
				}else{
					if(clickTab.equals("m02")){
						tabsStr +=",m02over";
					}else{
						tabsStr +=",m02out";
					}
					activateTab++;
				}
			}
			
			
			//분석기, EDITOR 일 경우 입/출력 포트 정보 탭이 없음.
			if(isPort && !ScienceAppConstants.OPENLEVEL_DWN.equals(scienceApp.getOpenLevel())){
				long scienceAppInputPortsCnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
				
				if(scienceAppInputPortsCnt==0){
					tabsStr +=",m03fail";
					appStatusButtonView = false;
				}else{
					if(clickTab.equals("m03")){
						tabsStr +=",m03over";
					}else{
						tabsStr +=",m03out";
					}
					activateTab++;
				}
				if(scienceApp.getIsStepLayout()!=null){
					if(scienceApp.getIsStepLayout()){
						if(!GetterUtil.getString(scienceApp.getLayout(),"").equals("")){
							if(appLayoutStepValidation(scienceApp)){
								if(clickTab.equals("m04")){
									tabsStr +=",m04over";
								}else{
									tabsStr +=",m04out";
								}
								activateTab++;
								
								appTestButtonView = true;
							}else{
								tabsStr +=",m04fail";
								appStatusButtonView = false;
							}
						}else{
							tabsStr +=",m04fail";
							appStatusButtonView = false;
						}
					}else{
						if(!GetterUtil.getString(scienceApp.getLayout(),"").equals("")){
							if(clickTab.equals("m04")){
								tabsStr +=",m04over";
							}else{
								tabsStr +=",m04out";
							}
							activateTab++;
							
							appTestButtonView = true;
						}else{
							tabsStr +=",m04fail";
							appStatusButtonView = false;
						}
					}
				}else{
					tabsStr +=",m04fail";
					appStatusButtonView = false;
				}
			}
			
			
			//App 설명 CHECK - APP의 서비스 언어에 따른 설명이 등록 되어 있어야 한다.
			String[] targetLanguages = CustomUtil.strNull(data.get("targetLanguage")).split(",");
			boolean DescroptionCheck = true;
			Map<String, Object> descriptionMap = (Map<String, Object>) data.get("description");
			Map<String, Object> descriptionMdeMap = (Map<String, Object>) data.get("descriptionMDE");
			
			for1:for(String targetLanguage:targetLanguages){
				if(GetterUtil.getString(descriptionMap.get("description_"+targetLanguage)).equals("")){
					DescroptionCheck = false;
					break for1;
				}
				
				if(GetterUtil.getString(descriptionMdeMap.get("descriptionMDE_"+targetLanguage)).equals("")){
					DescroptionCheck = false;
					break for1;
				}
			}
			
			if(DescroptionCheck){
				if(clickTab.equals("m05")){
					tabsStr +=",m05over";
				}else{
					tabsStr +=",m05out";
				}
				activateTab++;
			}else{
				tabsStr +=",m05fail";
				appStatusButtonView = false;
			}
			
			tabs = StringUtil.split(tabsStr);
		}
		
		StringBuffer tabString = new StringBuffer();
		
		tabString.append("<ul>");
		
		for(int i=0; i<tabs.length;i++){
			String tab = tabs[i];
			String liClass = tab;
			
			if(clickTab.equals("")){
				if(i==0){
					liClass+=" select";
				}
			}else{
				if(tab.contains(clickTab)){
					liClass+=" select";
				}
			}
			
			
			String tabName = "";
			String tabValue = "";
			if(tab.contains("m01")){
				tabName=LanguageUtil.get(locale,"edison-science-appstore-view-tab-app-infomation");
				tabValue = "m01";
			};
			
			if(tab.contains("m02")){
				tabName=LanguageUtil.get(locale,"edison-science-appstore-view-tab-execute-infomation");
				tabValue = "m02";
			};
			
			if(tab.contains("m03")){
				tabName=LanguageUtil.get(locale,"edison-science-appstore-view-tab-port-infomation");
				tabValue = "m03";
			}; 
			
			if(tab.contains("m04")){
				tabName="Layout";
				tabValue = "m04";
			};
			
			if(tab.contains("m05")){
				tabName=LanguageUtil.get(locale,"edison-science-appstore-view-tab-public-data");
				tabValue = "m05";
			};
			
			if(liClass.contains("select")){
				tabString.append("<li class=\""+liClass+"\"><p>"+tabName+"</p></li>");
			}else{
				if(i<activateTab){
					tabString.append("<li class=\""+liClass+"\"><a href=\"javascript:tabAction('"+tabValue+"')\"><p>"+tabName+"</p></a></li>");
				}else{
					tabString.append("<li class=\""+liClass+"\"><p>"+tabName+"</p></li>");
				}
				
			}
		}
		tabString.append("</ul>");
		
		returnMap.put("tabString", tabString.toString());
		returnMap.put("appStatusButtonView", appStatusButtonView);
		returnMap.put("appTestButtonView", appTestButtonView);
		
		return returnMap;
	}
	
	
	@ResourceMapping(value="copyScienceApp")
	public void copyScienceApp(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0l);
			String newVersion = CustomUtil.strNull(params.get("newVersion"), "");
			ServiceContext sc = ServiceContextFactory.getInstance(PortType.class.getName(), request);
			
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			boolean isDuplicated = ScienceAppLocalServiceUtil.existApp(scienceApp.getName(), newVersion);
			
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			
			if(isDuplicated){
				obj.put("resultCopy", false);
			} else {
				long newAppId = ScienceAppLocalServiceUtil.copyScienceApp(sc, scienceAppId, newVersion);
				ScienceApp newScienceApp =  ScienceAppLocalServiceUtil.getScienceApp(newAppId);
				obj.put("newAppId", newScienceApp.getScienceAppId());
				obj.put("newAppVersion", newScienceApp.getVersion());
				obj.put("resultCopy", true);
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		}catch(Exception e){
			if(e instanceof ScienceAppException){
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-not-support-os"));
			}else{
				e.printStackTrace();
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
			}
		}
	}
	
	/**
	 * 포트 이름 입력
	 * @param request
	 * @param model
	 * @param scienceAppId 	- App ID
	 * @param portType		- Input, Output 여부
	 * @param portMode		- port 타입 추가(add), port 조회 여부(search)
	 * @return
	 */
	@RenderMapping(params = "myaction=portNameAdd")
	public String portNameAdd(RenderRequest request, ModelMap model){
		try{
			Map params = RequestUtil.getParameterMap(request);
			
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0l);
			String portType = CustomUtil.strNull(params.get("portType"));
			
			
			model.addAttribute("scienceAppId",scienceAppId);
			model.addAttribute("portType",portType);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		return "appmanager/port_name_add";
	}
	
	@ResourceMapping(value="addScienceAppFile")
	public void addScienceAppFile(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			ServiceContext sc = ServiceContextFactory.getInstance(PortType.class.getName(), request);
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
			String fileName = GetterUtil.getString(upload.getFileName("scienceAppFile"),"");
			
			if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
				throw new ScienceAppException(ScienceAppException.SCIENCE_APP_FILE_NOT_SUPPORT_OS);
			}else{
				long companyId = themeDisplay.getCompanyId();
				String appName = GetterUtil.getString(params.get("appName"),"");
				String appVersion = GetterUtil.getString(params.get("appVersion"),"");
				Long appId = GetterUtil.getLong(params.get("appId"),0);
				
				if(appName.equals("")||appVersion.equals("")){
					throw new Exception("AppName OR AppVersion IS NULL");
				}
				String uploadSelect = GetterUtil.getString(params.get("uploadSelect"),"upload");
				String uploadCaseSelect = GetterUtil.getString(params.get("uploadCaseSelect"),"update");
				
				if(uploadCaseSelect.equals("clean")){
					//해당 버전 밑에 있는 파일 몽땅 삭제
					ScienceAppLocalServiceUtil.cleanScienceAppDir(companyId, appName, appVersion);
				}
				
				//파일 올렸을 떄 isCompile 상태값 변경
				//compile 테이블 값 삭제
				if(uploadSelect.equals("upload")){//upload 일때
  				if(appId != 0){
  					ScienceAppCompile compileApp =  ScienceAppCompileLocalServiceUtil.compileFindByScienceAppId(appId);
  					if(compileApp != null){
  						ScienceAppCompileLocalServiceUtil.deleteCompileAndScienceApp(companyId, appId, appName, appVersion);
  					}
  				}
				}else if(uploadSelect.equals("compile")){//git file upload
					//compile update
					ScienceAppCompileLocalServiceUtil.updateCompileAndScienceApp(appId, themeDisplay.getUserId(), "", "");
				}

				ScienceAppLocalServiceUtil.addScienceAppFile(companyId, appName, appVersion, fileName, upload.getFileAsStream("scienceAppFile", false));
				
				String binFolderListToStr = ScienceAppCompileLocalServiceUtil.retrieveListTartgetDir(companyId, appName, appVersion);
				
				net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
				obj.put("binFolderListToStr", binFolderListToStr);
				obj.put("fileName", fileName);
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e instanceof ScienceAppException){
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-file-not-support-os"));
			}else{
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
			}
		}
	}
	

	@ResourceMapping(value="searchCommonLib")
	public void searchCommonLib(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String searchValue = GetterUtil.get(params.get("searchValue"), "");
		int curPage = ParamUtil.get(request, "p_curPage", 1);
		
		try {
			int linePerPage = 10;
			
			int pagePerBlock = 5;
			int begin = (curPage - 1) * linePerPage;
			int end = linePerPage;
			
			long companyId = themeDisplay.getCompanyId();
			
			int totalCnt = CommonLibLocalServiceUtil.countCommonLib(companyId,searchValue);
			
			List<CommonLib> resultList = CommonLibLocalServiceUtil.retrieveListCommonLib(searchValue, begin, end);
			
			String portletNameSpace = response.getNamespace();
			String pageStr = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"searchCommonLib", totalCnt, curPage, linePerPage, pagePerBlock);

			
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(resultList);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			jsonObj.put("dataList", optionArr);
			jsonObj.put("page", pageStr);
			jsonObj.put("seq", totalCnt - ((curPage - 1)*linePerPage));
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value="updateAppStatus")
	public void updateAppStatus(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			Map params = RequestUtil.getParameterMap(request);
			
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
			int status = GetterUtil.getInteger(params.get("status"),0);
			
			if(scienceAppId==0){throw new Exception("@ResourceMapping(value=updateAppStatus) scienceAppId IS NULL");}
			if(status==0){throw new Exception("@ResourceMapping(value=updateAppStatus) status IS NULL");}
			
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			// status :{ public : 1901004 , private : less than 1901004}  
			scienceApp.setStatus(status);
			scienceApp.setStatusDate(new Date());
			scienceApp.setRecentModifierId(themeDisplay.getUserId());
			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp, status);
			
			PrintWriter writer = response.getWriter();
			writer.write("SUCCESS");
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
		}
	}
	
	
	@ResourceMapping(value="saveRequestLib")
	public void saveRequestLib(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"));
		String name = GetterUtil.get(params.get("name"), "");
		String version = GetterUtil.get(params.get("version"), "");
		String content = GetterUtil.get(params.get("content"), "");

		try {
			long requiredLibId = CounterLocalServiceUtil.increment(RequiredLibConfirm.class.getName());
			RequiredLibConfirmPK requiredLibConfirmPK = new RequiredLibConfirmPK(requiredLibId, scienceAppId);
			RequiredLibConfirm requiredLibConfirm = RequiredLibConfirmLocalServiceUtil.createRequiredLibConfirm(requiredLibConfirmPK);
			requiredLibConfirm.setCompanyId(themeDisplay.getCompanyId());
			requiredLibConfirm.setUserId(themeDisplay.getUserId());
			requiredLibConfirm.setRequiredDate(new Date());
			requiredLibConfirm.setLibraryName(name);
			requiredLibConfirm.setLibraryVersion(version);
			requiredLibConfirm.setRequiredContent(content);
			requiredLibConfirm.setRequiredState(RequiredLibConstants.LIB_STATE_REQUIRE);
			RequiredLibConfirmLocalServiceUtil.addRequiredLibConfirm(requiredLibConfirm);
			
			PrintWriter writer = response.getWriter();
			writer.write("SUCCESS");
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
		}
	}
	
	
	@ResourceMapping(value="searchRequestLib")
	public void searchRequestLib(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"));
		try {
			int curPage = ParamUtil.get(request, "p_curPage", 1);
			int linePerPage = 5;
			
			int pagePerBlock = 10;
			int start = linePerPage * (curPage - 1);
			
			String portletNameSpace = response.getNamespace();

			int totalCnt = RequiredLibConfirmLocalServiceUtil.countByScienceAppId(scienceAppId);
			List<RequiredLibConfirm> resultList = RequiredLibConfirmLocalServiceUtil.findByScienceAppId(scienceAppId, start, curPage*linePerPage, null);
			
			String pageStr = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"searchRequestLib", totalCnt, curPage, linePerPage, pagePerBlock);
			
			response.setContentType("application/json; charset=UTF-8");
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(resultList);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			
			jsonObj.put("dataList", optionArr);
			jsonObj.put("page", pageStr);
			jsonObj.put("seq", totalCnt - ((curPage - 1)*linePerPage));
			
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@RenderMapping(params = "myRender=libChangeRender")
	public String libChangeRender(RenderRequest request, ModelMap model){
		try{
			Map params = RequestUtil.getParameterMap(request);
			long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"));
			long requiredLibId = GetterUtil.getLong(params.get("requiredLibId"),0);
			
			String mode = "";
			if(requiredLibId==0){
				mode = Constants.ADD;
			}else{
				RequiredLibConfirmPK requiredLibConfirmPK = new RequiredLibConfirmPK(requiredLibId, scienceAppId);
				RequiredLibConfirm requiredLibConfirm = RequiredLibConfirmLocalServiceUtil.getRequiredLibConfirm(requiredLibConfirmPK);
				model.addAttribute("data", requiredLibConfirm.getModelAttributes());
				mode = Constants.SEARCH;
			}
			
			model.addAttribute("mode", mode);
			model.addAttribute("requiredLibId", requiredLibId);
			model.addAttribute("scienceAppId", scienceAppId);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		return "appmanager/request_lib";
	}
	
	@RenderMapping(params = "myRender=commonLibRender")
	public String commonLibRender(RenderRequest request, ModelMap model){
		return "appmanager/common_lib";
	}
	
	@ResourceMapping(value="checkePortName")
	public void checkePortName(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, JSONException{
		Map params = RequestUtil.getParameterMap(request);
		
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0l);
		String portType = CustomUtil.strNull(params.get("portType"));
		String portName = CustomUtil.strNull(params.get("portName"));
		
		boolean result = false;
		if(StringUtil.equalsIgnoreCase(portType, "INPUT")){
			long cnt = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPortsesCount(scienceAppId);
			if(cnt==0){
				result = true;
			}else{
				String inputJson = ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
				JSONObject input = JSONFactoryUtil.createJSONObject(inputJson);
				if(input.getString(portName).equals("")){
					result = true;
				}
				
			}
		}else if(StringUtil.equalsIgnoreCase(portType, "OUTPUT")){
			long cnt = ScienceAppOutputPortsLocalServiceUtil.getScienceAppOutputPortsesCount(scienceAppId);
			if(cnt==0){
				result = true;
			}else{
				String outputJson = ScienceAppOutputPortsLocalServiceUtil.getOutputPortsJsonString(scienceAppId);
				JSONObject output = JSONFactoryUtil.createJSONObject(outputJson);
				if(output.getString(portName).equals("")){
					result = true;
				}
			}
		}else if(StringUtil.equalsIgnoreCase(portType, "LOG")){
			long cnt = ScienceAppLogPortsLocalServiceUtil.getScienceAppLogPortsesCount(scienceAppId);
			if(cnt==0){
				result = true;
			}else{
				String logJson = ScienceAppLocalServiceUtil.getScienceAppLogPorts(scienceAppId);
				JSONObject log = JSONFactoryUtil.createJSONObject(logJson);
				if(log.getString(portName).equals("")){
					result = true;
				}
			}
		}
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("result", result);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="searchDataType")
	public void searchDataType(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"),0);
		String portType = GetterUtil.getString(params.get("portType"));
		
		
		try{
			DataType dataType = DataTypeLocalServiceUtil.getDataType(dataTypeId);
			
			Map<String,Object> returnMap = dataType.getModelAttributes();
			
			//SEARCH SAMPLE FILE
			if(StringUtil.equalsIgnoreCase(portType, "INPUT")){
				if(!GetterUtil.getString(dataType.getSamplePath(),"0").equals("0")){
					try{
						DLFileEntry sampleFileDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(GetterUtil.getLong(dataType.getSamplePath()));
						returnMap.put("sampleFileParent", sampleFileDl.getTreePath());
						returnMap.put("sampleFileId", sampleFileDl.getFileEntryId());
						returnMap.put("sampleFileName", sampleFileDl.getTitle());
					}catch(NoSuchFileEntryException e){
						
					}
				}
			}else{
				returnMap.put("sampleFileParent", "");
				returnMap.put("sampleFileId", "");
				returnMap.put("sampleFileName", "");
			}
			
			//SEARCH_EDITOR, SEARCH_ANALYZER (DEFAULT)
			String defaultAppExeName = "";
			if(StringUtil.equalsIgnoreCase(portType, "INPUT")){
				List<Map<String, Object>> editorList = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataType.getTypeId());
				for1:for(Map<String,Object> editor : editorList){
					boolean isDefault = GetterUtil.getBoolean(editor.get("isDefault"));
					if(isDefault){
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(GetterUtil.getLong(editor.get("editorId")));
						defaultAppExeName = scienceApp.getExeFileName();
						break for1;
					}
				}
			}else{
				List<Map<String, Object>> analyzerList = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataType.getTypeId());
				for1:for(Map<String,Object> analyzer : analyzerList){
					boolean isDefault = GetterUtil.getBoolean(analyzer.get("isDefault"));
					if(isDefault){
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(GetterUtil.getLong(analyzer.get("analyzerId")));
						defaultAppExeName = scienceApp.getExeFileName();
						break for1;
					}
				}
			}
			returnMap.put("defaultApp", defaultAppExeName);
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			obj.putAll(returnMap);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value="searchDlFile")
	public void searchDlFileURL(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long fileEntryId = GetterUtil.getLong(params.get("fileEntryId"),0);
		
		try{
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
			
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			obj.putAll(dlFileEntry.getModelAttributes());
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	@ResourceMapping(value="addPortSampeFile")
	public void addDataTypeSampeFile(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		String portName = GetterUtil.getString(params.get("portName"));
		
		try{
			ServiceContext sc = ServiceContextFactory.getInstance(ScienceAppInputPorts.class.getName(), request);
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
			
			Map<String,Object> sampleFileMap = ScienceAppInputPortsLocalServiceUtil.addSampeFile(scienceAppId, portName, sc, upload);
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			obj.putAll(sampleFileMap);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-insert-error"));
		}
	}
	
	@RenderMapping(params = "myaction=portSampeFileSearch")
	public String portSampeFileSearch(RenderRequest request, ModelMap model){
		Map params = RequestUtil.getParameterMap(request);
		
		String portName = CustomUtil.strNull(params.get("portName"));
		String samplePath = CustomUtil.strNull(params.get("samplePath"));
		String dataTypeName = CustomUtil.strNull(params.get("dataTypeName"));
		String dataTypeVersion = CustomUtil.strNull(params.get("dataTypeVersion"));
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"));
		
		Map<String,Object> returnData = new HashMap<String,Object>();
		returnData.put("portName", portName);
		returnData.put("portSamplePath", samplePath);
		returnData.put("scienceAppId", scienceAppId);
		
		try{
			//SEARCH DATATYPE
			DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
			
			//SEARCH PORT SAMPLE FILE
			ScienceAppInputPorts scienceAppInputPorts = ScienceAppInputPortsLocalServiceUtil.getScienceAppInputPorts(scienceAppId);
			if(!GetterUtil.getString(scienceAppInputPorts.getInputPortsSampleFile()).equals("")){
				net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) net.sf.json.JSONSerializer.toJSON(scienceAppInputPorts.getInputPortsSampleFile());
				if(!CustomUtil.strNull(jsonObject.get(portName)).equals("")){
					long portSampleFileId = GetterUtil.getLong(jsonObject.get(portName));
					try{
						DLFileEntry sampleFileDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(portSampleFileId);
						returnData.put("portSampleRepositoryId", sampleFileDl.getRepositoryId());
						returnData.put("portSampleUuid", sampleFileDl.getUuid());
						returnData.put("portSampleTitle", sampleFileDl.getTitle());
						returnData.put("portSampleId", sampleFileDl.getFileEntryId());
					}catch(NoSuchFileEntryException e){
						
					}
					
				}
			}
			
			long fileEntryId = GetterUtil.getLong(dataType.getSamplePath(),0);
			if(fileEntryId!=0){
				DLFileEntry sampleFileDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
				returnData.put("dataTypeSampleRepositoryId", sampleFileDl.getRepositoryId());
				returnData.put("dataTypeSampleUuid", sampleFileDl.getUuid());
				returnData.put("dataTypeSampleTitle", sampleFileDl.getTitle());
				returnData.put("dataTypeSampleId", sampleFileDl.getFileEntryId());
			}
			
			model.addAttribute("data", returnData);
			
		}catch(Exception e){
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "appmanager/port_sample_file";
	}
	
	@RenderMapping(params = "myaction=portAppSelector")
	public String portAppSelector(RenderRequest request, ModelMap model){
		Map params = RequestUtil.getParameterMap(request);
		
		String portType = CustomUtil.strNull(params.get("portType"));
		String portName = CustomUtil.strNull(params.get("portName"));
		String dataTypeName = StringUtil.trim(CustomUtil.strNull(params.get("dataTypeName")));
		String dataTypeVersion = CustomUtil.strNull(params.get("dataTypeVersion"));
		String defaultAppId = GetterUtil.getString(params.get("defaultAppId"));
		
		try{
			//SEARCH DATATYPE
			DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
			List<Map<String, Object>> searchList = new ArrayList<Map<String, Object>>();
			if(StringUtil.equalsIgnoreCase(portType, "INPUT")){
				searchList = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataType.getTypeId());
			}else{
				searchList = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataType.getTypeId());
			}
			List<Map<String,Object>> appList = new ArrayList<Map<String, Object>>();
			for(Map<String,Object> searchMap : searchList){
				Map<String,Object> appMap = new HashMap<String,Object>();
				
				long scienceAppId = StringUtil.equalsIgnoreCase(portType, "INPUT")?GetterUtil.getLong(searchMap.get("editorId")):GetterUtil.getLong(searchMap.get("analyzerId"));
				if(scienceAppId!=0){
					ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
					if(scienceApp.getStatus()==1901004){
						appMap.put("name", scienceApp.getName());
						appMap.put("scienceAppId", scienceApp.getExeFileName());
						appList.add(appMap);
					}
				}
			}
			
			model.addAttribute("portType", portType);
			model.addAttribute("portName", portName);
			model.addAttribute("defaultAppId", defaultAppId);
			model.addAttribute("appList", appList);
		}catch(Exception e){
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "appmanager/port_app_selector";
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
	
	
	/**
	 * app Manager 조회
	 * @param request
	 * @param response
	 */
	@ResourceMapping(value="appManagerList")
	public void getAppManagerList(ResourceRequest request, ResourceResponse response) throws Exception{
		Map params = RequestUtil.getParameterMap(request);
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		Role role = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.SOLVER_MANAGER);		// Role Id 확인
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		if (scienceAppId != 0) {
			List<ScienceAppManager> scienceAppManagers= ScienceAppManagerLocalServiceUtil.getManagersByScienceAppId(scienceAppId);
			
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setTimeZone(TimeZoneUtil.getDefault());
			
			List<Map<String,String>> ajaxList = new ArrayList<Map<String,String>>();
			for(ScienceAppManager scienceAppManager:scienceAppManagers){
				Map<String,String> rowMap = new HashMap<String,String>();
				User user = UserLocalServiceUtil.getUser(scienceAppManager.getUserId());
				
				rowMap.put("userScreenName", user.getScreenName());
				rowMap.put("userFullName", user.getFirstName());
				rowMap.put("userEmailAddress", user.getEmailAddress());
				rowMap.put("createDate", df.format(scienceAppManager.getCreateDate()));
				rowMap.put("scienceAppManagerId", String.valueOf(scienceAppManager.getScienceAppManagerId()));
				
				ajaxList.add(rowMap);
			}
			
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String optionJson = jsonSerializer.serialize(ajaxList);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			
			obj.put("appManagerList", optionArr);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="deleteAppAuth")
	public void deleteAppAuth(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		
		long scienceAppManagerId = GetterUtil.getLong(params.get("scienceAppManagerId"),0);
		
		/*권한 삭제*/
		ScienceAppManager scienceAppManager = ScienceAppManagerLocalServiceUtil.getScienceAppManager(scienceAppManagerId);
		ScienceAppManagerLocalServiceUtil.deleteScienceAppManager(scienceAppManager);
		
		int managerCnt = ScienceAppManagerLocalServiceUtil.countByUserId(scienceAppManager.getUserId());
		if(managerCnt == 0){
			User user = UserLocalServiceUtil.getUser(scienceAppManager.getUserId());
			
			for(Group group : user.getMySiteGroups()){
				EdisonUserUtil.deleteSiteRole(user, group.getGroupId(), EdisonRoleConstants.SOLVER_MANAGER);
			}
//				EdisonUserUtil.deleteGroup(user, EdisonRoleConstants.DEVELOPER_GROUP);
		}
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	
	
	@ResourceMapping(value="appUserInfo")
	public void appUserInfo(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
				
		String type = CustomUtil.strNull(params.get("type"));
		
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		String userScreenName = "";
		String pre_userScreenName = "";
		
		if(type.equals("owner")){
			userScreenName = CustomUtil.strNull(params.get("userScreenName_owner"),"0");
			pre_userScreenName = CustomUtil.strNull(params.get("now_userScreenName"),"0");
		}else if(type.equals("manager")){
			userScreenName = CustomUtil.strNull(params.get("userScreenName_manager"),"0");
		}
		
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, userScreenName);
		
		//com.liferay.util.json.JSONFactoryUtil -  Deprecated. 6.2-ce-ga5
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		Map<String, String> appUserInfo = null;
		
		
		if (user == null) {
			obj.put("result", "none");
		} else {
			
			Role ownerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.SOLVER_OWNER);		// Owner Role Id 확인
			Role managerRole = RoleLocalServiceUtil.fetchRole(companyId, EdisonRoleConstants.SOLVER_MANAGER);		// Manager Role Id 확인
			
			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
			if(type.equals("manager")){
				long appUserId = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId).getAuthorId();
				long managerExistCnt = ScienceAppManagerLocalServiceUtil.countByAppIdAndUserId(scienceAppId, user.getUserId());
				
				if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR)
						|| EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)) {
					obj.put("result", "admin");
				}else if(user.getUserId()==appUserId) {	// Owner 체크
					obj.put("result", "owner");
				} else if(managerExistCnt>0) {	// Manager 체크
					obj.put("result", "manager");
				}else {
					appUserInfo = new HashMap<String, String>();
					appUserInfo.put("userScreenName", user.getScreenName());
					appUserInfo.put("userFullName", user.getFullName());
					appUserInfo.put("userEmailAddress", user.getEmailAddress());
					appUserInfo.put("userId", String.valueOf(user.getUserId()));
					
					obj.put("appUserInfo", appUserInfo);
					obj.put("result", "user");
				}
			}else if(type.equals("owner")){
				appUserInfo = new HashMap<String, String>();
				appUserInfo.put("userScreenName", user.getScreenName());
				appUserInfo.put("userFullName", user.getFullName());
				appUserInfo.put("userEmailAddress", user.getEmailAddress());
				appUserInfo.put("userId", String.valueOf(user.getUserId()));
				
				obj.put("appUserInfo", appUserInfo);
				obj.put("result", "user");
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	
	@ResourceMapping(value="appManagerAdd")
	public void appManagerAdd(ResourceRequest request, ResourceResponse response) throws Exception{
		Map params = RequestUtil.getParameterMap(request);
		
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		long userId = GetterUtil.getLong(params.get("managerUserId"),0);
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		
		User requestUser = UserLocalServiceUtil.fetchUser(userId);
		Role role = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.SOLVER_MANAGER);		// Role Id 확인
		
		//권한 추가
		EdisonUserUtil.addSiteRole(requestUser, groupId, EdisonRoleConstants.SOLVER_MANAGER);	// SOLVER MANAGER 권한 추가
		
		//DEVELOPER_GROUP에 추가
		EdisonUserUtil.addGroup(requestUser, EdisonRoleConstants.DEVELOPER_GROUP);
		
		//DB 추가
		long scienceAppManagerId = CounterLocalServiceUtil.increment(ScienceAppManager.class.getName());
		ScienceAppManager scienceAppManager =  ScienceAppManagerLocalServiceUtil.createScienceAppManager(scienceAppManagerId);
		scienceAppManager.setUserId(userId);
		scienceAppManager.setCreateDate(new Date());
		scienceAppManager.setScienceAppId(scienceAppId);
		scienceAppManager.setManagerId(role.getRoleId());
		ScienceAppManagerLocalServiceUtil.updateScienceAppManager(scienceAppManager);
		
		
		
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("scienceAppId", scienceAppId);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	
	@ResourceMapping(value="appOwnerUpdate")
	public void updateSolverOwner(ResourceRequest request, ResourceResponse response) throws PortalException{
		 //solver, auth 테이블 update
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			long groupId = PortalUtil.getScopeGroupId(request);
			long preUserId = GetterUtil.getLong(params.get("pre_userScreenName"),0);
			long userId = GetterUtil.getLong(params.get("ownerUserId"),0);
			long scienceAppId =  GetterUtil.getLong(params.get("scienceAppId"),0);
			
			
			User requestUser = UserLocalServiceUtil.fetchUser(userId);
			
			/*권한 등록*/
			//GROUP_추가
			EdisonUserUtil.addGroup(requestUser, EdisonRoleConstants.DEVELOPER_GROUP);
			
			//SiteRole 추가
			EdisonUserUtil.addSiteRole(requestUser, groupId, EdisonRoleConstants.SOLVER_OWNER);	// SOLVER OWNER 권한 추가
			
			/*권한 삭제*/
			//매니저 권한이 있었을 경우 제거 해줌
			if(ScienceAppManagerLocalServiceUtil.countByAppIdAndUserId(scienceAppId, userId)>0){
				List<ScienceAppManager> appManagerList = ScienceAppManagerLocalServiceUtil.findByAppIdAndUserId(scienceAppId, userId);
				for(ScienceAppManager scienceAppManager : appManagerList){
					ScienceAppManagerLocalServiceUtil.deleteScienceAppManager(scienceAppManager.getScienceAppManagerId());
				}
			}
			
			//Administrator, Site Administrator 가 아닐경우
			//WORKSPACE 승인 내역이 없을경우
			//솔버권한 확인 후 GROUP, SiteRole 삭제
			User preUser = UserLocalServiceUtil.fetchUser(preUserId);
			
			int developerInfoCnt = DeveloperInfoLocalServiceUtil.getScienceAppDeveloperInfoCount(preUserId);
					
			if(!EdisonUserUtil.isRegularRole(preUser, EdisonRoleConstants.ADMINISTRATOR)
			 &&!EdisonUserUtil.isSiteRole(preUser, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)
			 &&developerInfoCnt==0){
				//현재 관리 하고 있는 app 목록이 없을 경우 SOLVER_MANAGER 권한 삭제
				int managerCnt = ScienceAppManagerLocalServiceUtil.countByUserId(preUserId);
				if(managerCnt == 0){
					if(preUser.getMySiteGroups().size()!=0){
						for(Group group : preUser.getMySiteGroups()){
							EdisonUserUtil.deleteSiteRole(preUser, group.getGroupId(), EdisonRoleConstants.SOLVER_MANAGER);
						}
//								EdisonUserUtil.deleteGroup(user, EdisonRoleConstants.DEVELOPER_GROUP);
					}
				}
			}
			
			/*Solver Table userId 변경*/
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			scienceApp.setAuthorId(userId);
			scienceApp.setRecentModifierId(themeDisplay.getUserId());
			scienceApp.setModifiedDate(new Date());
			ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
			
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.write(requestUser.getScreenName());
		}catch(Exception e){
			e.printStackTrace();
			throw new PortalException(e);
		}
	}
	
	@ResourceMapping(value="deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String fileType = GetterUtil.getString(params.get("fileType"),"");
		String language = GetterUtil.getString(params.get("language"),"");
		long fileEntryId = GetterUtil.getLong(params.get("fileEntryId"),0);
		long scienceAppId = GetterUtil.getLong(params.get("scienceAppId"),0);
		
		if(fileEntryId!=0&&!fileType.equals("")){
			DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
			if(!fileType.equals("portSampleFile")){
				ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
				
				if(fileType.equals("soruceFile")){
					scienceApp.setSrcFileName("");
				}else if(fileType.equals("appIcon")){
					scienceApp.setIconId(0);
				}else if(fileType.equals("appManual")){
					scienceApp.setManualId("0", LocaleUtil.fromLanguageId(language));
				}
				scienceApp.setModifiedDate(new Date());
				scienceApp.setRecentModifierId(themeDisplay.getUserId());
				ScienceAppLocalServiceUtil.updateScienceApp(scienceApp);
			}else{
				//PORT SAMPLE FILE 삭제
			}
		}
	}
	
	
	protected void checkAppPermission(ScienceApp scienceApp, long userId) throws SystemException, ScienceAppException {
		
		int managerCnt = ScienceAppManagerLocalServiceUtil.countByAppIdAndUserId(scienceApp.getScienceAppId(), userId);
		
		if(managerCnt==0){
			throw new ScienceAppException(ScienceAppException.SCIENCE_APP_NO_AUTH);
		}
		
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
	@ResourceMapping(value="fileUploadByGitHubCompile")
	public void fileUploadByGitHubCompile(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{

			String resultTxt = "";
			long companyId = themeDisplay.getCompanyId();
			long scienceAppId = ParamUtil.getLong(request, "scienceAppId");
			String gitHubUrl = ParamUtil.getString(request, "gitHubUrl");
			String appName = ParamUtil.getString(request, "appName");
			String appVersion = ParamUtil.getString(request, "appVersion");
			
			if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
				throw new ScienceAppException(ScienceAppException.SCIENCE_APP_FILE_NOT_SUPPORT_OS);
			}else{
				
				if(appName.equals("")||appVersion.equals("")){
					throw new Exception("AppName OR AppVersion IS NULL");
				}
				
//				resultTxt += "git clone...\n";
				resultTxt += ScienceAppCompileLocalServiceUtil.gitHubCloneToScienceAppFolders(companyId, appName, appVersion, gitHubUrl);
				resultTxt += "<br/>"+ScienceAppCompileLocalServiceUtil.cleanMakeFileToTargetScienceApp(companyId, appName, appVersion);
				resultTxt += "<br/>"+ScienceAppCompileLocalServiceUtil.makeFileToTargetScienceApp(companyId, appName, appVersion);
				
				ScienceAppCompileLocalServiceUtil.updateCompileAndScienceApp(scienceAppId, themeDisplay.getUserId(),gitHubUrl, resultTxt);
			}

			JSONObject obj = JSONFactoryUtil.createJSONObject();
			
			String binFolderListToStr = ScienceAppCompileLocalServiceUtil.retrieveListTartgetDir(companyId, appName, appVersion);
			obj.put("binFolderListToStr", binFolderListToStr);
			obj.put("result", resultTxt);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		}catch (Exception e){
			e.printStackTrace();
			if(e instanceof ScienceAppException){
					handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-appstore-file-not-support-os"));
			}
		}
	}
	
	@ResourceMapping(value="getScienceAppInfo")
	public void getScienceAppInfo(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		String scienceAppVersion = "";
		boolean hasScienceApp = false;
		long scienceAppId = Long.parseLong(CustomUtil.strNull(params.get("scienceAppId"),"0"));
		
		
		try {
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			
			scienceAppVersion = scienceApp.getVersion();
			hasScienceApp = true;
		} catch (Exception e) {
			hasScienceApp = false;
		}
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		
		obj.put("scienceAppVersion", scienceAppVersion);
		obj.put("result", hasScienceApp);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="getClusterList")
	public void getClusterList(ResourceRequest request, ResourceResponse response) throws IOException{
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			StringBuffer responseBuffer = new StringBuffer();
			
			long groupId = themeDisplay.getScopeGroupId();
			String cluster = CustomUtil.strNull(params.get("cluster"),"EDISON_CFD");
			String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
			
			URL url = new URL(icebreakerUrl+"/api/cluster/list");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			
			if (conn.getResponseCode() == 400) {
				throw new PortalException("Failed IcebreakerService [ retrieveCluster ] : BAD REQUEST: bad parameters");
			}else{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String  output = "";		
				while ((output = br.readLine()) != null) {			
					responseBuffer.append(output);
				}
				
				JSONObject clusterObj = JSONFactoryUtil.createJSONObject(responseBuffer.toString());
				JSONArray clusterJsonArr =  clusterObj.getJSONArray("clusters");
				String[] clusterName = new String[clusterJsonArr.length()];
				for(int i=0; i<clusterJsonArr.length(); i++){
					clusterName[i] = clusterJsonArr.getJSONObject(i).getString("name");
				}
				String clusterList = HtmlFormUtils.makeCombo(clusterName, cluster);
				obj.put("clusterList", clusterList);
			}
			conn.disconnect();
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
