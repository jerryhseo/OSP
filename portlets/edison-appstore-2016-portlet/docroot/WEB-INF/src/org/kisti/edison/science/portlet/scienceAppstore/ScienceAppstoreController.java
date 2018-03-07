package org.kisti.edison.science.portlet.scienceAppstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppCategoryLink;
import org.kisti.edison.science.model.ScienceAppDescription;
import org.kisti.edison.science.service.ScienceAppCategoryLinkLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppDescriptionLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppFavoriteLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppManagerLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

/*import com.kisti.osp.workbench.service.WorkbenchLayoutLocalServiceUtil;*/
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.NoSuchCategoryPropertyException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")   
public class ScienceAppstoreController {
	private static Log log = LogFactoryUtil.getLog(ScienceAppstoreController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response,ModelMap model){
		try {
			Map params = RequestUtil.getParameterMap(request);
			boolean copyParam = GetterUtil.getBoolean(params.get("edionCopyParam"));
			if(copyParam){
				model.addAttribute("returnParams", params);
			}
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
			
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			if(tabViewYn.equals("Y")){
				model = scienceAppstoreList(request, response, model);
				model.addAttribute("tabViewYn", tabViewYn);
				return "scienceAppstore/scienceAppStoreList";
			}
			
			//다른곳에서 solverID가 넘어올경우
			if(!CustomUtil.strNull(params.get("search_solverId")).equals("")){
				params.put("solverId", params.get("search_solverId").toString());
				params.put("groupId", params.get("search_groupId").toString());
			}
			
			//다른곳에서 solverID가 넘어올경우
			if(!CustomUtil.strNull(params.get("boardSeq")).equals("") && !CustomUtil.strNull(params.get("back")).equals("Y")){
				model.addAttribute("boardSeq", params.get("boardSeq").toString());
			} else {
				model.addAttribute("boardSeq", "");
			}
			
			long scopeGroupId = themeDisplay.getScopeGroupId();
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long parentGroupId = GroupLocalServiceUtil.getGroup(scopeGroupId).getParentGroupId();
			long userId = PortalUtil.getUserId(request);
			
			long companyId = PortalUtil.getCompanyId(request);
			
			List<com.liferay.portal.model.Group> parentGroupList = GroupLocalServiceUtil.getGroups(companyId, 0, true);
			Group portalGroup = null;
			for(Group parentGroup:parentGroupList){
				if(StringUtil.toUpperCase(parentGroup.getName()).equals("GUEST")){
					portalGroup = parentGroup;
					break;
				}
			}
			long portalGroupId = portalGroup.getGroupId();
			
			String currunt_folder = "/" +portalGroupId+" - " +CompanyLocalServiceUtil.getCompany(PortalUtil.getCompanyId(request)).getName() + "/"+portalGroupId+"_EDISON_FILE"+"/"+EdisonFileConstants.USER_IMAGE+"/"+userId+ "/";
			model.addAttribute("currentFolder", currunt_folder);
			
			Locale selectLocale = themeDisplay.getLocale();
			if(params.get("selectLocaleId") != null){
				selectLocale = LocaleUtil.fromLanguageId(CustomUtil.strNull(params.get("selectLocaleId")));  
			}
			Map<String, Object> solver = ScienceAppLocalServiceUtil.getScienceAppReturnObject(Long.parseLong(CustomUtil.strNull(params.get("solverId"))), selectLocale);
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date transDate = transFormat.parse(CustomUtil.strNull(solver.get("createDate")));
			Date transStatusDate = transFormat.parse(CustomUtil.strNull(solver.get("statusDate")));
			solver.put("createDate", df.format(transDate));
			solver.put("statusDate", df.format(transStatusDate));
			
			String languageId = LocaleUtil.toLanguageId(themeDisplay.getLocale());
			if(solver.get("manualId_"+languageId) != null){
				solver.put("current_manualId", solver.get("manualId_"+languageId));
			}
			
			StringBuffer jsonStringBuffer = new StringBuffer();
			long scienceAppId = Long.parseLong(CustomUtil.strNull(solver.get("scienceAppId"), "0"));
			Locale[] availableLocales = LanguageUtil.getAvailableLocales();
			
			if(solver != null){
				params.put("solverGroupId", solver.get("groupId"));
				params.put("solverId", scienceAppId);
				params.put("userId", userId);
			}
			
			String contentCheckAuth = "FALSE";
			long authorId = Long.parseLong(CustomUtil.strNull(solver.get("authorId")));
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			if(themeDisplay.isSignedIn()){
				if(EdisonUserUtil.isPowerUserThan(user)){
					contentCheckAuth = "TRUE";
				}else if(EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
					contentCheckAuth = "TRUE";
				}else if(userId == authorId){
					contentCheckAuth = "TRUE";
				}else{
					long[] managerIds = ScienceAppManagerLocalServiceUtil.getManagerIdsByScienceAppId(scienceAppId);
					
					for(long managerId : managerIds){
						if(managerId == userId){
							contentCheckAuth = "TRUE";
						}
					}
				}
				
				if(contentCheckAuth.equals("TRUE")){
					EdisonFileUtil.checkUserFolder(request, userId, portalGroupId, EdisonFileConstants.USER_IMAGE);
				}
				
			}
			model.addAttribute("contentCheckAuth" , contentCheckAuth);
			String responseNamespace = response.getNamespace();
			responseNamespace = responseNamespace.substring(1, responseNamespace.length()-1);
			//long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "Workbench_WAR_OSPWorkbenchportlet");
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
			
			model.addAttribute("workBenchPlid", plid);
			model.addAttribute("isSignedIn", themeDisplay.isSignedIn());
			model.addAttribute("solver", solver);
			
			model.addAttribute("params",params);
			model.addAttribute("availableLocales", availableLocales);
			
			String favorite = String.valueOf(ScienceAppFavoriteLocalServiceUtil.getScienceAppFavoriteCount(scienceAppId, userId));
			model.addAttribute("favorite",favorite);
			
			//sw 통계 Plid
			long scienceAppExecPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
			        "edisonstatisticsappexec_WAR_edisonsimulationportlet");
			
			model.addAttribute("scienceAppExecPlid", scienceAppExecPlid);
			
			//SciemceApp EntryId and categoryInfo
			AssetEntry scienceAppAssetEntry = AssetEntryLocalServiceUtil.fetchEntry(ScienceApp.class.getName(), GetterUtil.getLong(solver.get("scienceAppId"))); //	fetchEntry(String className, long classPK)
			List<Map<String, Object>> childrenCategoryList = new ArrayList<Map<String, Object>>();
			List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(scienceAppAssetEntry.getEntryId());
			
			/*
			 * 카테고리중 첫번째 카테고리의 부모 카테고리 아이디를 app의 그룹아이디로 사용(통계 이동시 사용)
			 * 카테고리 적용으로 인해 ScienceApp 테이블의 groupId를 사용하면 통계가 안나오는 현상이 생긴다.
			 */
			Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
			long appGroupId = defaultGroup.getGroupId();
			
			//그룹의 AssetEntry 리스트 구하기
			AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getCompanyGroupId(), EdisonAssetCategory.GLOBAL_DOMAIN);
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setClassName(Group.class.getName());
			List<AssetEntry> groupEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
			int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary.getVocabularyId());
			
			if(rootCategoryCnt!=0){
				List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(aVocabulary.getVocabularyId(), -1, -1,null);
				int i = 0;
				for(AssetCategory rootCatogory : rootCategoryList){
					if(assetCategoryList != null){
						for(AssetCategory assetCategory : assetCategoryList){
							Map<String,Object> childrenCategoryMap = new HashMap<String,Object>();
							if(i == 0){
								if(parentGroupId == 0){//포탈
									long parentCategoryId = assetCategory.getParentCategoryId(); //사이언스앱의 첫번째 카테고리의 부모 카테고리 아이디
									
									loop1 : for(AssetEntry groupEntry : groupEntryList){
										if(groupEntry.getClassPK() != themeDisplay.getScopeGroupId()){	//하위 사이트의 그룹아이디가 필요하기에 포탈그룹아이디는 제외한다.
											AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupEntry.getClassPK()); //groupEntry.getClassPK() 는 groupId
											List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId()); //해당 group의 모든 AssetCategory를 가지고 온다.
											
											for(AssetCategory aCategory : aCategoryList){
												//GLOBAL_DOMAIN에 등록된 카테고리
												if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()){ 
													if(parentCategoryId == aCategory.getCategoryId()){	//parentCategoryId 가 포함된 그룹이면
														appGroupId = groupEntry.getClassPK();
														break loop1;
													}
												}
											}
											AssetCategory assetCtegory = AssetCategoryLocalServiceUtil.getAssetCategory(parentCategoryId);
											assetCtegory.getGroupId();
										}
									}
									
								}else{
									appGroupId = themeDisplay.getScopeGroupId();
								}
								
								i++;
							}
							
							if(rootCatogory.getCategoryId() == assetCategory.getParentCategoryId()){
								childrenCategoryMap.put("value", assetCategory.getCategoryId());
								childrenCategoryMap.put("name", assetCategory.getTitle(themeDisplay.getLocale()));
								childrenCategoryList.add(childrenCategoryMap);
							}
						}
					}
				}
			}
			
			model.addAttribute("scienceAppEntryId", scienceAppAssetEntry.getEntryId());
			model.addAttribute("childrenCategoryList", childrenCategoryList);
			model.addAttribute("solverGroupId", appGroupId);
			
			//모든 버전 App 
			String appName = CustomUtil.strNull(solver.get("name"));
			List<Map<String, Object>> scienceAppByNameList = new ArrayList<Map<String, Object>>();
			
			if(parentGroupId == 0){//포탈
				scienceAppByNameList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), groupId, themeDisplay.getLocale(), appName, false);
			}else{
				scienceAppByNameList = ScienceAppLocalServiceUtil.retrieveListScienceAppByName(themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId(), themeDisplay.getLocale(), appName, true);
			}
			
			List<Map<String, Object>> historyAppList = new ArrayList<Map<String, Object>>();
			List<String> historyAppIdList = new ArrayList<String>();
			for(Map<String, Object> scienceApp : scienceAppByNameList){
				long historyScienceAppId = GetterUtil.getLong(scienceApp.get("scienceAppId"));
				if(scienceAppId != historyScienceAppId){
					transDate = transFormat.parse(CustomUtil.strNull(scienceApp.get("createDate")));
					transStatusDate = transFormat.parse(CustomUtil.strNull(scienceApp.get("statusDate")));
					scienceApp.put("createDate", df.format(transDate));
					scienceApp.put("statusDate", df.format(transStatusDate));
					if(scienceApp.get("manualId_"+languageId) != null){
						scienceApp.put("current_manualId", solver.get("manualId_"+languageId));
					}
					if(historyScienceAppId != 0){
						historyAppIdList.add(String.valueOf(historyScienceAppId));
					}
					
					historyAppList.add(scienceApp);
				}
			}
			
			model.addAttribute("historyAppList", historyAppList);
			model.addAttribute("historyAppIdList", historyAppIdList);
			
			//app 소유자 정보
			long ownerId = Long.valueOf(CustomUtil.strNull(solver.get("authorId"), "0"));
			User ownerUser = UserLocalServiceUtil.getUser(ownerId);
			String universityField = (String)ownerUser.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
			
			Map<String, Object> ownerInfo = new HashMap<String, Object>();
			ownerInfo.put("firstName", ownerUser.getFirstName());
			ownerInfo.put("screenName", ownerUser.getScreenName());
			ownerInfo.put("universityField", universityField);
			ownerInfo.put("universityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(universityField, EdisonExpando.CDNM , themeDisplay.getLocale()));
			ownerInfo.put("portraitURL", ownerUser.getPortraitURL(themeDisplay));
			
			model.addAttribute("ownerInfo", ownerInfo);
			
			boolean isRedirect = ParamUtil.getBoolean(request, "isRedirect", true);
			model.addAttribute("isRedirect", isRedirect);
			if(isRedirect){
				String redirectName = ParamUtil.getString(request, "redirectName", "My EDISON");
				String redirectURL = ParamUtil.getString(request, "redirectURL", EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
				
				model.addAttribute("redirectName", redirectName);
				model.addAttribute("redirectURL", redirectURL);
				
				//redirectURL decode
				model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
			}
			
			model.addAttribute("isMainAppSearch", ParamUtil.getBoolean(request, "isMainAppSearch", false));
			
			String classId = CustomUtil.strNull(params.get("classId"), "0");
			String customId = CustomUtil.strNull(params.get("customid"), "0");
			model.addAttribute("classId", classId);
			model.addAttribute("customId", customId);
			
			/*asset 바로가기 상태 체크*/
			String viewStatus = ParamUtil.getString(request, "viewStatus", "");
			model.addAttribute("viewStatus", viewStatus);
<<<<<<< HEAD
			model.addAttribute("downloadOnly", ScienceAppConstants.OPENLEVEL_DWN);

=======
			
>>>>>>> work-local-imjeong
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "scienceAppstore/scienceAppStoreView";
	}
	
	public ModelMap scienceAppstoreList(RenderRequest request, RenderResponse response,ModelMap model){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long companyId = themeDisplay.getCompanyId();
			
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			
			/* tab */
			String visitSite ="";
			long parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();
			
			String searchGroupId =CustomUtil.strNull(params.get("search_groupId"));
			
			//default visitSite
			Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
			model.addAttribute("visitSite", Long.toString(defaultGroup.getGroupId()));
			
			//User Expando 값 가지고 오기
			if(themeDisplay.isSignedIn()){
				visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
			}else{
				model.addAttribute("visitSite", CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) ));
			}
			
			String groupName = "";
			int groupCnt = 0;
			String groupId = "";

			Map<String,Long> GroupMap = new HashMap<String,Long>();
			if(!tabUseStr.equals("")){
				String []tabUseArray = tabUseStr.split(",");
				for(int i=0;i<tabUseArray.length;i++){
					
					Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
					Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
					GroupMap.put(group.getName(), group.getGroupId());
					
					if(groupCnt==0){
						groupName += group.getName();
						groupId += group.getGroupId();
						groupCnt++;
					}else{
						groupName += ","+group.getName();
						groupId += ","+group.getGroupId();
					}
					
					if(!visitSite.equals("")&&visitSite.equals(group.getName())){
						model.addAttribute("visitSite", Long.toString(group.getGroupId()));
					}
				}
			}
			model.addAttribute("tabNames", groupName);
			model.addAttribute("tabsValues", groupId);
			
			/*다른 곳에서 search_groupId 파라미터가 넘어 올경우 최종적으로 searchGroupId 값을 셋팅*/
			if(!searchGroupId.equals("")){
				model.addAttribute("visitSite", searchGroupId);
			}
			
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			json.putAll(GroupMap);
			model.addAttribute("groupMap", json.toString());
			
			String searchField = ParamUtil.get(request, "searchField", "");
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonbestsimulation_WAR_edisonsimulationportlet");
			model.addAttribute("simulationPlid", plid);
			params.put("solverStatus", "1901004");
			params.put("recommandation_flag", "true");
			
			model.addAttribute("searchField",searchField);
			model.addAttribute("params", params);
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return model;
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{

		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
	
	@ResourceMapping(value ="solverTypeList" )
	public void solverTypeList(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long globalGroupId = themeDisplay.getCompany().getGroupId();
			long companyId = themeDisplay.getCompanyId();
			
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, EdisonAssetCategory.GLOBAL_DOMAIN);
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
			
			List<Map> solverTypeList =  new ArrayList<Map>();
			
			for(AssetCategory aCategory : aCategoryList){
				Map categoryMap = new HashMap();
				if(aCategory.getVocabularyId()== assetVocabulary.getVocabularyId() && aCategory.getParentCategoryId() != 0){
					categoryMap.put("categoryId", aCategory.getCategoryId());
					categoryMap.put("title", aCategory.getTitle(themeDisplay.getLanguageId()));
					categoryMap.put("companyId", aCategory.getCompanyId());
					categoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					categoryMap.put("userId", aCategory.getUserId());
					categoryMap.put("userName", aCategory.getUserName());

					try{
						AssetCategoryProperty categoryProperty = AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(aCategory.getCategoryId(), "IMAGE");
						categoryMap.put("image", categoryProperty);
					}catch(NoSuchCategoryPropertyException e){
						
					}
					
					solverTypeList.add(categoryMap);
				}
			}
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(solverTypeList);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			jsonObj.put("solverTypeList", optionArr);
			writer.write(jsonObj.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@ActionMapping(params="myAction=updateSolverInfoAction")
	public void updateBoardAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			ScienceAppDescription scienceAppDescription = ScienceAppDescriptionLocalServiceUtil.getScienceAppDescription(Long.parseLong(CustomUtil.strNull(params.get("descriptionId"))));
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale aLocale : locales){
				String languageId = LocaleUtil.toLanguageId(aLocale);
				String description = CustomUtil.strNull(params.get("description_"+languageId));
				scienceAppDescription.setContent(description, aLocale);
			}
			scienceAppDescription.setUpdateId(themeDisplay.getUserId());
			scienceAppDescription.setUpdateDt(new Date());
			ScienceAppDescriptionLocalServiceUtil.updateScienceAppDescription(scienceAppDescription);			
	
			String solverId = CustomUtil.strNull(params.get("solverId"), "0");
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myaction", "detailView");
			portletURL.setParameter("edionCopyParam", "true");
			portletURL.setParameter("selectLocaleId", CustomUtil.strNull(params.get("selectLocaleId")));
			portletURL.setParameter("solverId", CustomUtil.strNull(params.get("solverId")));
			portletURL.setParameter("groupId", CustomUtil.strNull(params.get("groupId")));			
			portletURL.setParameter("redirectName", CustomUtil.strNull(params.get("redirectName")));
			portletURL.setParameter("redirectURL", CustomUtil.strNull(params.get("redirectURL")));
			portletURL.setParameter("p_p_state", "maximized");
			SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
			response.sendRedirect(portletURL.toString());
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}
	
	@ResourceMapping(value ="scienceAppCategory" )
	public void scienceAppCategory(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long companyId = themeDisplay.getCompanyId();
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId")));
			long scienceAppId = Long.parseLong(CustomUtil.strNull(params.get("solverId")));
			
			AssetVocabulary aVocabulary =  AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,EdisonAssetCategory.GLOBAL_DOMAIN);
			
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
			
			String categoryStr = "";
			List<Map<String, Object>> parentCategoryList = new ArrayList<Map<String, Object>>() ;
			List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>() ;
			//parentCategory 찾기
			for(AssetCategory aCategory : aCategoryList){
				if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()&& aCategory.getParentCategoryId() == 0){
					Map<String, Object> aCategoryMap = new HashMap<String, Object>();
					aCategoryMap.put("categoryId", aCategory.getCategoryId());
					aCategoryMap.put("categoryTitle", aCategory.getTitle(themeDisplay.getLocale()));
					aCategoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					parentCategoryList.add(aCategoryMap);
				}
			}
				
			for(AssetCategory aCategory : aCategoryList){
				if(aCategory.getVocabularyId()==aVocabulary.getVocabularyId()&& aCategory.getParentCategoryId() != 0){
					Map<String, Object> aCategoryMap = new HashMap<String, Object>();
					aCategoryMap.put("categoryId", aCategory.getCategoryId());
					aCategoryMap.put("categoryTitle", aCategory.getTitle(themeDisplay.getLocale()));
					aCategoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					categoryList.add(aCategoryMap);
				}
			}
			
			List<ScienceAppCategoryLink> ScienceAppCategoryLinkList = ScienceAppCategoryLinkLocalServiceUtil.getScienceAppCategorysByscienceAppId(scienceAppId);
			List<Map<String, Object>> appCategoryIds = new ArrayList<Map<String,Object>>();
			
			for(ScienceAppCategoryLink ScienceAppCategoryLink : ScienceAppCategoryLinkList){
				Map<String, Object> appCategoryIdMap = new HashMap<String, Object>();
				appCategoryIdMap.put("categoryId", ScienceAppCategoryLink.getCategoryId());
				appCategoryIds.add(appCategoryIdMap);
			}
			
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			
			obj.put("parentCategoryList", parentCategoryList);
			obj.put("categoryList", categoryList);
			obj.put("appCategoryIds", appCategoryIds);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/* 나의 즐겨찾기 앱 목록 조회 */
	@ResourceMapping(value ="favoriteAppList" )
	public void favoriteAppList(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long groupId = PortalUtil.getScopeGroupId(request);
			long userId = PortalUtil.getUserId(request);
			long companyId = PortalUtil.getCompanyId(request);
			Locale locale = themeDisplay.getLocale();
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 즐겨찾기 앱 추가 */
	@ResourceMapping(value ="addFavoriteApp" )
	public void addFavoriteApp(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			String result = "200";
			Map params = RequestUtil.getParameterMap(request);
			long userId = PortalUtil.getUserId(request);
			long scienceAppId = GetterUtil.get(params.get("solverId"), 0L);
			long groupId = GetterUtil.get(params.get("groupId"), 0L);
			ScienceAppFavoriteLocalServiceUtil.updateScienceAppFavorite(userId, scienceAppId, groupId);
			
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			
			obj.put("result", result);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
		}
	}
	
	/* 즐겨찾기 앱 삭제 */
	@ResourceMapping(value ="deleteFavoriteApp" )
	public void deleteFavoriteApp(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			String result = "200";
			Map params = RequestUtil.getParameterMap(request);
			long userId = PortalUtil.getUserId(request);
			long scienceAppId = GetterUtil.get(params.get("solverId"), 0L);
			long groupId = GetterUtil.get(params.get("groupId"), 0L);
			ScienceAppFavoriteLocalServiceUtil.deleteFavoriteApp(scienceAppId, userId);
			
			net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
			
			obj.put("result", result);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
		}
	}

	@ResourceMapping(value = "saveWorkbenchSession")
	public void saveWorkbenchSession(ResourceRequest request, ResourceResponse response){
		String command = ParamUtil.getString(request, "command");
		if( "saveWorkbenchLayout".equalsIgnoreCase(command) ){
			String workbenchLayout = ParamUtil.getString(request, "workbenchLayout");
			
			String sessionId = request.getPortletSession().getId();
			
			try {
				/*WorkbenchLayoutLocalServiceUtil.saveWorkbenchLayout(sessionId, workbenchLayout);*/
				
				JSONObject obj = new JSONObject();
				obj.put("msg", "success");
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@ResourceMapping(value ="searchList" ) //하위사이트 groupId로 각 리스트 가져오기
	public void searchList(ResourceRequest request, ResourceResponse response) throws IOException{
		List solverIconList = new ArrayList();
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			
			params.put("languageId", themeDisplay.getLocale().toString());
			
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long userId = PortalUtil.getUserId(request);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();

			int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
			int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("linePerPage"), "10"));
			int pagePerBlock = 5;
			
			params.put("groupId", groupId);
			params.put("status", "1901004");
			
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			
			String categoryValue = CustomUtil.strNull(params.get("categoryId"), "");
			long categoryId = 0;
			
			if(!categoryValue.equals("")){
				categoryId = Long.valueOf(categoryValue);
			}
			
			int solverCount = ScienceAppLocalServiceUtil.countScienceApp(companyGroupId, groupId, categoryId ,themeDisplay.getLocale(), params); //총 개수
			
			int begin = (curPage - 1) * linePerPage;
			int end = linePerPage;
			
			List<Map<String, Object>> writeDataList = ScienceAppLocalServiceUtil.retrieveListScienceApp(companyGroupId, groupId, categoryId ,themeDisplay.getLocale(), params, begin, end, true);
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"dataSearchList", solverCount, curPage, linePerPage, pagePerBlock);
			
			int totalCnt = solverCount;
			
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(writeDataList);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			jsonObj.put("dataList", optionArr);
			jsonObj.put("pageList", pagingStr);
			jsonObj.put("select_line", linePerPage);
			jsonObj.put("totalCnt", totalCnt);
			writer.write(jsonObj.toString());

		}catch(Exception e){
			System.out.println("searchList Exception");
			e.printStackTrace();
		}
	}
	
	//탭 이벤트 저장
	@ResourceMapping(value ="cickTab")
	public void cickTab(ResourceRequest request, ResourceResponse response){
		try{
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.getLong(request, "groupId",0);
			if(groupId!=0){
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//탭 이벤트 저장
	@ActionMapping(params="myaction=saveClickTab")
	public void saveClickTab(ActionRequest request, ActionResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.getLong(request, "groupId",0);
			if(themeDisplay.isSignedIn()) {
				if(groupId!=0){
					Group group = GroupLocalServiceUtil.getGroup(groupId);
					themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
				}
			}
			String responseNamespace = response.getNamespace();
			responseNamespace = responseNamespace.substring(1, responseNamespace.length()-1);
			long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), responseNamespace);
			PortletURL url = PortletURLFactoryUtil.create(request, responseNamespace, plid, "");
			url.setParameter("groupId", CustomUtil.strNull(groupId));
			response.sendRedirect(url.toString());
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
	}
}
