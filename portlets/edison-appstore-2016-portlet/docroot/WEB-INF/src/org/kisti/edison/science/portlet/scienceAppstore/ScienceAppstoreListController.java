package org.kisti.edison.science.portlet.scienceAppstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.science.model.ScienceAppCategoryLink;
import org.kisti.edison.science.model.ScienceAppDescription;
import org.kisti.edison.science.service.ScienceAppCategoryLinkLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppDescriptionLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppFavoriteLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
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

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")   
public class ScienceAppstoreListController {
	private static Log log = LogFactoryUtil.getLog(ScienceAppstoreListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response,ModelMap model){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			
			boolean copyParam = GetterUtil.getBoolean(params.get("edionCopyParam"));
			
			if(copyParam){
				model.addAttribute("returnParams", params);
			}
			
			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			model.addAttribute("tabViewYn", tabViewYn);
			
			long companyId = themeDisplay.getCompanyId();
			
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");
			
			/* tab */
			String visitSite ="";
			long parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();
			
			String searchGroupId =CustomUtil.strNull(params.get("search_groupId"));
			
			//default visitSite
			Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
			model.addAttribute("visitSite", Long.toString(defaultGroup.getGroupId()));
			
			if(tabViewYn.equals("Y")){
				//User Expando 값 가지고 오기
				if(themeDisplay.isSignedIn()){
					visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
				}else{
					model.addAttribute("visitSite", CustomUtil.strNull(params.get("groupId"), Long.toString(defaultGroup.getGroupId()) ));
				}
			} else {
				visitSite =  themeDisplay.getSiteGroupName();
				if(visitSite.toUpperCase().equals("EDISON")){
					visitSite = "CFD";
				}
				model.addAttribute("visitSite", Long.toString(GroupLocalServiceUtil.getGroup(companyId, visitSite).getGroupId()));
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
			long plid  = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
			model.addAttribute("workBenchPlid", plid);
			
			long workflowPlid  = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "workflowdesigner_WAR_edisonworkflow2016portlet");
			model.addAttribute("workflowPlid", workflowPlid);
			params.put("solverStatus", "1901004");
			params.put("recommandation_flag", "true");
			
			String upCode = "1501";		//기관 upCode
			List<Map<String,String>> organList = EdisonExpndoUtil.getCodeListByUpCode(upCode, themeDisplay.getLocale());
			
			// 2019.03.22 _ get organization List registered with app.
			List<Long> organizationListRegisteredWithApp = ScienceAppLocalServiceUtil.getOrganizationRegisteredWithApp();
			Iterator<Map<String, String>> iter = organList.iterator();
			while(iter.hasNext()){
				Map<String, String> organMap = iter.next();
				
				long organCd = Long.parseLong(organMap.get("cd"));
				if(!organizationListRegisteredWithApp.contains(organCd)){
					iter.remove();
				}
			}
			
			model.addAttribute("searchField",searchField);
			model.addAttribute("params", params);
			model.addAttribute("organList", organList);
			model.addAttribute("isSignedIn", themeDisplay.isSignedIn());
			model.addAttribute("signedInUrl", themeDisplay.getURLSignIn());
			
			model.addAttribute("SORT_FIELD_CREATED", "latest");
			model.addAttribute("SORT_FIELD_EXECUTE", "execute");
			model.addAttribute("SORT_FIELD_NAME", "name");
			model.addAttribute("SORT_ORDER_ASC", "asc");
			model.addAttribute("SORT_ORDER_DESC", "desc");
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "scienceAppstore/scienceAppStoreList";
	}
	
	@ResourceMapping(value="viewScienceAppListBySort")
	public void viewScienceAppListBySort(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
		Map paramsMap = RequestUtil.getParameterMap(request);
		long groupId = Long.parseLong(CustomUtil.strNull(paramsMap.get("groupId"), "20181"));
		long listSize =  Long.parseLong(CustomUtil.strNull(paramsMap.get("listSize"), "10"));
		String searchKeyword = CustomUtil.strNull(paramsMap.get("searchKeyword"), "");
		String sortOrder = CustomUtil.strNull(paramsMap.get("sortOrder"), "");
		
		/*List<Map<String, Object>> apps = ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(themeDisplay.getCompanyGroupId(), groupId, themeDisplay.getLocale(),
				themeDisplay.
											searchCondition.getCompanyGroupId(), searchCondition.getGroupId(),
											searchCondition.getLocale(), appTypes, categoryIds, searchCondition.getSearchKeyword(),
											searchCondition.getStart(), searchCondition.getListSize(),
											searchCondition.getSortField(), searchCondition.getSortOrder());*/
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
			String searchValue = CustomUtil.strNull(params.get("searchValue"), "");
			String searchOrgCode = CustomUtil.strNull(params.get("searchOrgCode"), "");
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();

			int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
			int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("linePerPage"), "10"));
			String sortField = CustomUtil.strNull(params.get("sortField"), "latest");
			String sortOrder = CustomUtil.strNull(params.get("sortOrder"), "desc");
			int pagePerBlock = 5;
			
			params.put("groupId", groupId);
			params.put("status", "1901004");
			
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			
			String categoryValue = CustomUtil.strNull(params.get("categoryId"), "");
			long categoryId = 0;
			
			if(!categoryValue.equals("")){
				categoryId = Long.valueOf(categoryValue);
			}
			
			long globalGroupId = themeDisplay.getCompany().getGroupId();
			long companyId = themeDisplay.getCompanyId();
			
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, EdisonAssetCategory.GLOBAL_DOMAIN);
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
			
			int categoryIdsLength = 0;
			long[] categoryIds = null;
			if(categoryId == 0){
				categoryIdsLength = aCategoryList.size();
				categoryIds = new long[categoryIdsLength];
				for(int i=0; i<aCategoryList.size(); i++){
					categoryIds[i] = aCategoryList.get(i).getCategoryId();
				}
			} else {
				categoryIdsLength = 1;
				categoryIds = new long[categoryIdsLength];
				categoryIds[0] = categoryId;
			}
			
			String[] appTypes = null;
			int solverCount = ScienceAppLocalServiceUtil.countScienceAppFromExplore(
					companyGroupId, groupId,
					themeDisplay.getLocale(),  appTypes, categoryIds, searchValue, searchOrgCode);
			
			int begin = (curPage - 1) * linePerPage;
			int end = linePerPage;
			
			List<Map<String, Object>> writeDataList = ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(
					companyGroupId, groupId,
					themeDisplay.getLocale(), appTypes, categoryIds, searchValue, searchOrgCode,
					begin, linePerPage, sortField, sortOrder);
			
			// ScienceApp Date List for ScienceApp Page
			List<String> dateMapList = new ArrayList<String>();
			for(int i=0; i<writeDataList.size(); i++){
				dateMapList.add(new SimpleDateFormat("yyyy-MM-dd").format(writeDataList.get(i).get("modifiedDate")));
			}
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"dataSearchList", solverCount, curPage, linePerPage, pagePerBlock);
			
			int totalCnt = solverCount;
			
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			String optionJson = jsonSerializer.serialize(writeDataList);
			String dateJson = jsonSerializer.serialize(dateMapList);		// ScienceApp Date
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			JSONArray dateArr = JSONFactoryUtil.createJSONArray(dateJson);
			jsonObj.put("dataList", optionArr);
			jsonObj.put("dateList", dateArr);
			jsonObj.put("pageList", pagingStr);
			jsonObj.put("select_line", linePerPage);
			jsonObj.put("totalCnt", totalCnt);
			writer.write(jsonObj.toString());

		}catch(Exception e){
			log.error("searchList Exception");
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
	
	// 2018.05.28, Site Member 여부 확인
	@ResourceMapping(value ="isSiteMember")
	public void isSiteMember(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		boolean isSiteMember = false;
		if(EdisonUserUtil.isSiteRole(themeDisplay.getUser(), themeDisplay.getSiteGroupId(), EdisonRoleConstants.SITE_MEMBER)){
			isSiteMember = true;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("isSiteMember", isSiteMember);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
}
