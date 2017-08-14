package org.kisti.edison.bestsimulation.portlet.datacollection;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import org.kisti.edison.bestsimulation.portlet.datacollection.Exception.DataCollectionException;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.persistence.LayoutUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;


@Controller
@RequestMapping("VIEW")
public class DataCollectionController{
	private static Log log = LogFactoryUtil.getLog(DataCollectionController.class);

	@RequestMapping // default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){

		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);

			model.addAttribute("portletWindowState", request.getWindowState().toString());
			
			int totalCount = 0;
			long companyGroupId = themeDisplay.getCompanyGroupId();
			long groupId = ParamUtil.getLong(request, "groupId", PortalUtil.getScopeGroupId(request));
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			
			boolean isAdmin = false;
			
			if(themeDisplay.isSignedIn()){
  			if(parentGroupId == 0){// 포탈
  				// admin 만 모든 목록
  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
  					isAdmin = true;
  				}
  			}else{
  				// admin, site admin 모든 목록
  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
  					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
  					isAdmin = true;
  				}
  			}
			}
			model.addAttribute("groupId", groupId);
			model.addAttribute("isAdmin", isAdmin);
			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int searchLine = ParamUtil.get(request, "searchLine", 10);
			int blockSize = 10;
			int start = ((currentPage - 1) * searchLine);

			String searchText = CustomUtil.strNull(param.get("searchText"));

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			/*if(isAdmin){
				// 전체 조회
				dataList = DataCollectionLocalServiceUtil.retrieveListDataCollection(companyGroupId, groupId,
					searchText, start, searchLine, null);
				totalCount = DataCollectionLocalServiceUtil.retrieveCountDataCollection(companyGroupId, groupId,
					searchText, null);
			}else{
				// 내것만 조회
				// userId
				long userId = themeDisplay.getUserId();
				dataList = DataCollectionLocalServiceUtil.retrieveListDataCollection(companyGroupId, groupId, userId,
					searchText, start, searchLine, null);
				totalCount = DataCollectionLocalServiceUtil.retrieveCountDataCollection(companyGroupId, groupId,
					userId, searchText, null);
			}*/
			
		// 전체 조회
			dataList = DataCollectionLocalServiceUtil.retrieveListDataCollection(companyGroupId, groupId,
				searchText, start, searchLine, null);
			totalCount = DataCollectionLocalServiceUtil.retrieveCountDataCollection(companyGroupId, groupId,
				searchText, null);
			
			
			List<Map<String, Object>> collectionList = new ArrayList<Map<String, Object>>();
			if(dataList != null){
				for(Map<String, Object> dataMap : dataList){
					DataType dataType = null;
					long dataTypeId = Long.parseLong(CustomUtil.strNull(dataMap.get("dataTypeId"), "0"));
					if(dataTypeId != 0){
						dataType = DataTypeLocalServiceUtil.getDataType(dataTypeId);
						
						dataMap.put("dataType", dataType.getModelAttributes());
						collectionList.add(dataMap);
					}
				}
			}
			
			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "dataCollectionList",
				totalCount, currentPage, searchLine, blockSize);

			model.addAttribute("searchLine", searchLine); 
			model.addAttribute("collectionList", collectionList); 
			model.addAttribute("seq", totalCount - ((currentPage - 1) * searchLine));
			model.addAttribute("paging", paging);
			model.addAttribute("searchText", searchText);
			
			Layout dataCollectionLayout = LayoutUtil.findByPrimaryKey(themeDisplay.getPlid());

			String LayoutName = "";
			if(dataCollectionLayout != null){
				LayoutName = dataCollectionLayout.getName(themeDisplay.getLocale());
			}
			String redirectName = ParamUtil.getString(request, "redirectName", LayoutName);
			model.addAttribute("redirectName",  redirectName);
			
			String redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
			model.addAttribute("redirectURL", ParamUtil.get(request, "redirectURL", redirectURL));
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return "datacollection/collectionList";
	}

	@RenderMapping(params = "myRender=detailViewDataCollection")
	public String detailViewDataCollection(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			Map params = RequestUtil.getParameterMap(request);
			
			//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(params.get("currentPage"));
			String searchLine = CustomUtil.strNull(params.get("searchLine"));
			String searchText = CustomUtil.strNull(params.get("searchText"));
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("searchLine", searchLine);
			model.addAttribute("searchText", searchText);
			
			// Data Collection Model Get
			long collectionId = GetterUtil.getLong(params.get("collectionId"), 0);

			DataCollection collection = DataCollectionLocalServiceUtil.getDataCollection(collectionId);
			//Map<String, Object> collection = DataCollectionLocalServiceUtil.findDataCollectionObject(dtId, dcName, dcVersion);

			AssetEntry collectionEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(), collectionId);
			model.addAttribute("entryId", String.valueOf(collectionEntry.getEntryId()));
			
			Map<String, Object> collectionNewMap = null;
			if(collection != null){
				boolean isAdmin = ParamUtil.getBoolean(request, "isAdmin", false);
				
				if(themeDisplay.isSignedIn()){
					
					long groupId = ParamUtil.getLong(request, "groupId", PortalUtil.getScopeGroupId(request));
					long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
					
	  			if(parentGroupId == 0){// 포탈
	  				// admin 만 모든 목록
	  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
	  					isAdmin = true;
	  				}
	  			}else{
	  				// admin, site admin 모든 목록
	  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
	  					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
	  					isAdmin = true;
	  				}
	  			}
				}
				
				model.addAttribute("isAdmin", isAdmin);
				
				/*String titleXml = String.valueOf(collection.get("title"));
				String descriptionXml = String.valueOf(collection.get("description"));
				if(!titleXml.equals("")){
					collection.put("title_"+languageId, LocalizationUtil.getLocalization(titleXml,languageId));
				}
				if(!descriptionXml.equals("")){
					collection.put("description_"+languageId, LocalizationUtil.getLocalization(descriptionXml,languageId));
				}*/
				
				long dataTypeId = GetterUtil.getLong(collection.getTypeId(), 0);
				model.addAttribute("dataTypeId" , dataTypeId);
				DataType dataType= DataTypeLocalServiceUtil.getDataType(dataTypeId);
				
				if(dataType != null){
				//DataTypeEditor
					Map<String,Object> dataTypeEditorMap = new HashMap<String,Object>();
					int columnCnt = 0;
						//DataTypeEditr,Analyze,Structure exist check
					Map<String, Object> datTypeViewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);

					if(GetterUtil.getInteger(datTypeViewMap.get("numEditors"), 0) != 0){
						List<Map<String, Object>> dataTypeEditorMapList = DataTypeEditorLocalServiceUtil
							.retrieveDataTypeEditorList(dataTypeId);
						
						if(dataTypeEditorMapList.size() > 0){
							for(Map<String, Object> editorMap : dataTypeEditorMapList){
								long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
								ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
								editorMap.put("typeName", scienceApp.getName());
//								editorMap.put("portletName", scienceApp.getExeFileName());
							}
							dataTypeEditorMap.put("editor", dataTypeEditorMapList);
							columnCnt++;
						}
					}

					// DataTypeAnalyzer
					if(GetterUtil.getInteger(datTypeViewMap.get("numAnalyzers"), 0) != 0){
						List<Map<String, Object>> dataTypeAnalyzerMapList = DataTypeAnalyzerLocalServiceUtil
							.retrieveDataTypeAnalyzerList(dataTypeId);
						if(dataTypeAnalyzerMapList.size() > 0){
							for(Map<String, Object> analyzerMap : dataTypeAnalyzerMapList){
								long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
								ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
								analyzerMap.put("typeName", scienceApp.getName());
//								analyzerMap.put("portletName", scienceApp.getExeFileName());
							}
							dataTypeEditorMap.put("analyzer", dataTypeAnalyzerMapList);
							columnCnt++;
						}
					}

					dataTypeEditorMap.put("columnCnt", columnCnt);
					model.addAttribute("dataTypeEditorMap", dataTypeEditorMap);
					
				}
				model.addAttribute("dataType", dataType);
				
				AssetEntry conllectionEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(), Long
					.parseLong(String.valueOf(collection.getCollectionId())));
				model.addAttribute("assetEntryId", String.valueOf(conllectionEntry.getEntryId()));

				// 카테고리 & 프로젝트
				List<AssetCategory> linkedAssetCategoriesList = AssetCategoryLocalServiceUtil
					.getAssetEntryAssetCategories(conllectionEntry.getEntryId());

				List<String> categoryNameList = new ArrayList<String>();
				for(AssetCategory categoryLink : linkedAssetCategoriesList){
					categoryNameList.add(categoryLink.getName());
				}
				model.addAttribute("categoryNameList", categoryNameList);
				

				collectionNewMap = collection.getModelAttributes();
				collectionNewMap.put("title", collection.getTitle(themeDisplay.getLocale()));
				collectionNewMap.put("description", StringUtil.split(collection.getDescription(themeDisplay.getLocale()), StringPool.NEW_LINE));
			}
			model.addAttribute("collection", collectionNewMap);
			
			List<Map<String, Object>> collectionHistory = DataCollectionLocalServiceUtil.retrieveListDataCollectionHistory(collection.getName(), collection.getVersion(), themeDisplay.getLocale());
			model.addAttribute("collectionHistory", collectionHistory);
			
			List<Map<String, Object>> dataEntryList = DataEntryLocalServiceUtil.retrieveDataEntryByCollectionId(collectionId);
			
			for(Map<String, Object> dataEntryMap : dataEntryList){
				String comments = LocalizationUtil.getLocalization(GetterUtil.getString(dataEntryMap.get("comments")), themeDisplay.getLanguageId());
				dataEntryMap.put("comments", comments);
				
				/*DataEntryProvenance dataEntryProvenance =  DataEntryProvenanceLocalServiceUtil.getDataEntryProvenance(GetterUtil.getLong(dataEntryMap.get("entryId")));
				JSONObject dataTypeJsonObject= JSONFactoryUtil.createJSONObject(dataEntryProvenance.getInputData());
				System.out.println(dataTypeJsonObject);
				dataEntryMap.put("inputData", dataTypeJsonObject.toString());*/
			}
				
			model.addAttribute("dataEntryList", dataEntryList);	
			
			/*get icon list*/
			List iconList = EdisonFileUtil.getListEdisonFile(collection.getGroupId(), "icon", 
				String.valueOf(collection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);
			
			Map iconMap = null;
			if(iconList != null && iconList.size() > 0){
				iconMap = (Map)iconList.get(0);
			}
			model.addAttribute("dcIcon", iconMap);

			/*get main Image list*/
			List mainImgList = EdisonFileUtil.getListEdisonFile(Long.parseLong(String.valueOf(collection.getGroupId())), "mainImg", 
				String.valueOf(collection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);
			model.addAttribute("dcMainImg", mainImgList);
			
			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));

			/*asset 바로가기 상태 체크*/
			String viewStatus = ParamUtil.getString(request, "viewStatus", "");
			model.addAttribute("viewStatus", viewStatus);
			
		}catch (Exception e){
			e.printStackTrace();
		}

		return "datacollection/collectionView";
	}

	@RenderMapping(params = "myRender=manageDataCollection")
	public String manageDataCollection(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			Map params = RequestUtil.getParameterMap(request);
			String mode = CustomUtil.strNull(params.get("mode"), Constants.ADD);
			
			boolean isAdmin = ParamUtil.getBoolean(request, "isAdmin", false);
			model.addAttribute("isAdmin", isAdmin);
			
			// 카테고리 & 프로젝트
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
				EdisonAssetCategory.GLOBAL_DOMAIN);

			int rootCategoryCnt = AssetCategoryLocalServiceUtil.getVocabularyRootCategoriesCount(aVocabulary
				.getVocabularyId());
			Map<Long, List<Map<String, Object>>> childrenCategoryGroupMap = new HashMap<Long, List<Map<String, Object>>>();
			List<Map<String, Object>> parentCategoryList = new ArrayList<Map<String, Object>>();

			if(rootCategoryCnt != 0){
				List<AssetCategory> rootCategoryList = AssetCategoryLocalServiceUtil.getVocabularyRootCategories(
					aVocabulary.getVocabularyId(), -1, -1, null);
				for(AssetCategory rootCatogory : rootCategoryList){
					Map<String, Object> parentCategoryMap = new HashMap<String, Object>();
					parentCategoryMap.put("value", rootCatogory.getCategoryId());
					parentCategoryMap.put("name", rootCatogory.getTitle(themeDisplay.getLocale()));
					parentCategoryList.add(parentCategoryMap);

					List<AssetCategory> childCategoryList = AssetCategoryLocalServiceUtil.getChildCategories(
						rootCatogory.getCategoryId());
					List<Map<String, Object>> childrenCategoryList = new ArrayList<Map<String, Object>>();
					for(AssetCategory childCatogory : childCategoryList){
						Map<String, Object> childrenCategoryMap = new HashMap<String, Object>();
						childrenCategoryMap.put("value", childCatogory.getCategoryId());
						childrenCategoryMap.put("name", childCatogory.getTitle(themeDisplay.getLocale()));
						childrenCategoryList.add(childrenCategoryMap);
					}
					childrenCategoryGroupMap.put(rootCatogory.getCategoryId(), childrenCategoryList);
				}
			}
			
			model.addAttribute("childrenCategoryGroupMap", childrenCategoryGroupMap);
			model.addAttribute("parentCategoryList", parentCategoryList);

			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));

			long myPagePlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonmypage_WAR_edisondefault2016portlet_INSTANCE_O4nIvvThWJnl");
			model.addAttribute("myPagePlid", myPagePlid);
			
			long collectionId = GetterUtil.getLong(params.get("collectionId"), 0);
			int defaultRowNum = 0;
			if(collectionId != 0){ // update
				mode = Constants.UPDATE;
				DataCollection collection = DataCollectionLocalServiceUtil.getDataCollection(collectionId);
				
				model.addAttribute("collection", collection);
				
				long dataTypeId = collection.getTypeId();
				DataType dataType= DataTypeLocalServiceUtil.getDataType(dataTypeId);
				if(dataType != null){
					defaultRowNum = 2;
				//DataTypeEditor
					Map<String,Object> dataTypeEditorMap = new HashMap<String,Object>();
						//DataTypeEditr,Analyze,Structure exist check
					Map<String, Object> datTypeViewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);

					if(GetterUtil.getInteger(datTypeViewMap.get("numEditors"), 0) != 0){
						List<Map<String, Object>> dataTypeEditorMapList = DataTypeEditorLocalServiceUtil
							.retrieveDataTypeEditorList(dataTypeId);
						String editorStr = "";
						for(Map<String, Object> editorMap : dataTypeEditorMapList){
							long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
							ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
							editorStr = editorStr.equals("") ? scienceApp.getName()
								: editorStr + "," + scienceApp.getName();
						}
						dataTypeEditorMap.put("editor", editorStr);
						defaultRowNum++;
					}

					// DataTypeAnalyzer
					if(GetterUtil.getInteger(datTypeViewMap.get("numAnalyzers"), 0) != 0){
						List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil
							.retrieveDataTypeAnalyzerList(dataTypeId);
						String analyzerStr = "";
						for(Map<String, Object> analyzerMap : dataTypeAnalyzerMap){
							long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
							ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
							analyzerStr = analyzerStr.equals("") ? scienceApp.getName()
								: analyzerStr + "," + scienceApp.getName();
						}
						dataTypeEditorMap.put("analyzer", analyzerStr);
						defaultRowNum++;
					}

					model.addAttribute("defaultRowNum", defaultRowNum);
					model.addAttribute("dataTypeEditorMap", dataTypeEditorMap);
					
				}
				model.addAttribute("dataType", dataType);
				
				AssetEntry conllectionEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(), Long
					.parseLong(String.valueOf(collection.getCollectionId())));
				
				if(conllectionEntry != null){
					List<AssetCategory> linkedAssetCategoriesList = AssetCategoryLocalServiceUtil
						.getAssetEntryAssetCategories(conllectionEntry.getEntryId());

					String parentCategory = "";
					String childrenCategory = "";

					List<String> categoryNameList = new ArrayList<String>();

					for(AssetCategory categoryLink : linkedAssetCategoriesList){
						if(childrenCategory.equals("")){
							childrenCategory += categoryLink.getParentCategoryId() + "_" + categoryLink.getCategoryId();
						}else{
							childrenCategory += "," + categoryLink.getParentCategoryId() + "_" + categoryLink
								.getCategoryId();
						}

						if(parentCategory.equals("")){
							parentCategory += categoryLink.getParentCategoryId();
						}else{
							parentCategory += "," + categoryLink.getParentCategoryId();
						}
						categoryNameList.add(categoryLink.getTitle(themeDisplay.getLocale()));
					}

					model.addAttribute("childrenCategory", childrenCategory);
					model.addAttribute("parentCategory", parentCategory);
					model.addAttribute("categoryNameList", categoryNameList);
					
				}
				/*get icon list*/
				List iconList = EdisonFileUtil.getListEdisonFile(Long.parseLong(String.valueOf(collection.getGroupId())), "icon", 
					String.valueOf(collection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);
				
				Map iconMap = null;
				if(iconList != null && iconList.size() > 0){
					iconMap = (Map)iconList.get(0);
				}
				model.addAttribute("dcIcon", iconMap);

				/*get main Image list*/
				List mainImgList = EdisonFileUtil.getListEdisonFile(Long.parseLong(String.valueOf(collection.getGroupId())), "mainImg", 
					String.valueOf(collection.getCollectionId()), EdisonFileConstants.DATA_COLLECTION);
				model.addAttribute("dcMainImg", mainImgList);
				
				AssetEntry collectionEntry = AssetEntryLocalServiceUtil.getEntry(DataCollection.class.getName(), collectionId);
				model.addAttribute("entryId", String.valueOf(collectionEntry.getEntryId()));
				
				long workBenchPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "Workbench_WAR_OSPWorkbenchportlet");
				model.addAttribute("workBenchPlid", workBenchPlid);
			}
			
			model.addAttribute("mode", mode);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return "datacollection/collectionManage";
	}

	
	@ActionMapping(params = "myAction=deleteDataCollection")
	public void deleteDataCollection(ActionRequest request, ActionResponse response, ModelMap model){
		try{
			long collectionId = ParamUtil.getLong(request, "collectionId", 0);
			if(collectionId != 0){
				DataCollectionLocalServiceUtil.removeDataCollectionByCollectionId(collectionId);
				SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@ActionMapping(params = "myAction=manageDataCollection")
	public void manageDataCollection(ActionRequest request, ActionResponse response, ModelMap model){
		try{
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			Map params = RequestUtil.getParameterMap(request);
			String mode = CustomUtil.strNull(params.get("mode"), Constants.ADD);
			
			//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(params.get("currentPage"));
			String searchLine = CustomUtil.strNull(params.get("searchLine"));
			String searchText = CustomUtil.strNull(params.get("searchText"));
			
			// Data Collection Find Object
			ServiceContext sc = ServiceContextFactory.getInstance(DataCollection.class.getName(), request);
			long companyGroupId = themeDisplay.getCompanyGroupId();

			long dataTypeId = GetterUtil.getLong(String.valueOf(params.get("dataTypeId")), 0);
			String dcName = CustomUtil.strNull(params.get("name"));
			String dcVersion = CustomUtil.strNull(params.get("version"));
			
			if(mode.equals(Constants.ADD)){
  			//name, version validation check
  			boolean validationCheck = DataCollectionLocalServiceUtil.checkDataCollectionValidity(dcName, dcVersion);

  			response.setPortletMode(PortletMode.VIEW);
  			if(validationCheck){
  				throw new DataCollectionException(DataCollectionException.EXISTS_NAME_VERSION_DATABASE);
  			}
			}
			
			String dcStatus = CustomUtil.strNull(params.get("status"));
			
			String dcTargetLanguage = CustomUtil.strNull(params.get("targetLanguage"));
			
			if(dcTargetLanguage.equals("")){ //make default TargetLanguage 
  			Locale[] localesArray = LanguageUtil.getAvailableLocales();
  			String defaultTargetLanguage = "";
  			for(Locale locale : localesArray){
  				String languageId = LocaleUtil.toLanguageId(locale);
  				if(defaultTargetLanguage.equals("")){
  					defaultTargetLanguage += languageId;
  				}else{
  					defaultTargetLanguage += ","+languageId;
  				}
  			}
  			dcTargetLanguage = defaultTargetLanguage;
			}

			Map<Locale, String> dcTitleMap = CustomUtil.getLocalizationMap(params, "title");
			Map<Locale, String> dcDescriptionMap = CustomUtil.getLocalizationMap(params, "description");
			
			// 카테고리 배열만들기
			long[] categoryIds = null;
			int categoryIndex = 0;
			if(params.get("childrenCategoryCheckbox") instanceof String[]){
				String[] childrenCategoryArray = (String[]) params.get("childrenCategoryCheckbox");
				categoryIds = new long[childrenCategoryArray.length];
				for(String childrenCategory : childrenCategoryArray){
					String[] childrenCategoryValue = childrenCategory.split("_");
					categoryIds[categoryIndex++] = GetterUtil.getLong(childrenCategoryValue[1], 0l);
				}
			}else if(params.get("childrenCategoryCheckbox") instanceof String){
				categoryIds = new long[1];
				String[] childrenCategoryValue = CustomUtil.strNull(params.get("childrenCategoryCheckbox")).split(
					"_");
				categoryIds[0] = GetterUtil.getLong(childrenCategoryValue[1], 0l);
			}
			String dcAccessMethod = "local";
			
			//create service call
			DataCollectionLocalServiceUtil.createDataCollectionObject(dataTypeId, dcName, dcVersion, dcTitleMap,
				dcDescriptionMap, dcAccessMethod, dcStatus, dcTargetLanguage, categoryIds, companyGroupId, sc);
			
			Map<String, Object> dataCollectionMap = DataCollectionLocalServiceUtil.findDataCollectionObject(dataTypeId, dcName, dcVersion);

			boolean isAdmin = ParamUtil.getBoolean(request, "isAdmin", false);
			
			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			response.setPortletMode(PortletMode.VIEW);
			response.setRenderParameter("myRender", "manageDataCollection");
			response.setRenderParameter("mode", mode);
			response.setRenderParameter("p_p_state", "maximized");
			response.setRenderParameter("collectionId",CustomUtil.strNull(dataCollectionMap.get("collectionId")));
			response.setRenderParameter("redirectName", redirectName);
			response.setRenderParameter("redirectURL", redirectURL);
			response.setRenderParameter("currentPage", currentPage);
			response.setRenderParameter("searchLine", searchLine);
			response.setRenderParameter("searchText", searchText);
			response.setRenderParameter("isAdmin", String.valueOf(isAdmin));
			
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
			
		}catch (Exception e){
//			e.printStackTrace();
			
			response.setRenderParameter("myRender", "manageDataCollection");
			response.setRenderParameter("p_p_state", "maximized");
			RequestUtil.copyRequestParameters(request, response, new String[] {"name", "version"});

			if (e instanceof DataCollectionException){
				SessionErrors.add(request, e.getClass(), e);
			}else{
				SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
			}
			
		}
	}

	@ResourceMapping(value = "retrieveMapdataType")
	public void retrieveMapdataType(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			
			long dataTypeId = Long.parseLong(CustomUtil.strNull(params.get("dataTypeId"), "0"));

			Map<String, Object> dataTypeMap = new HashMap<String, Object>();
			String resultMsg = "FAIL";
			int defaultRowNum = 0;
			if(dataTypeId != 0){
				DataType dataTypeObj = DataTypeLocalServiceUtil.getDataType(dataTypeId);
				
				if(dataTypeObj != null){
					defaultRowNum = 2;
					Map<String,Object> dataTypeEditorMap = new HashMap<String,Object>();
						//DataTypeEditr,Analyze,Structure exist check
					Map<String, Object> datTypeViewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);

					if(GetterUtil.getInteger(datTypeViewMap.get("numEditors"), 0) != 0){
						List<Map<String, Object>> dataTypeEditorMapList = DataTypeEditorLocalServiceUtil
							.retrieveDataTypeEditorList(dataTypeId);
						String editorStr = "";
						for(Map<String, Object> editorMap : dataTypeEditorMapList){
							long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
							ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
							editorStr = editorStr.equals("") ? scienceApp.getName()
								: editorStr + "," + scienceApp.getName();
							
						}
						dataTypeEditorMap.put("editor", editorStr);
						defaultRowNum++;
					}

					// DataTypeAnalyzer
					if(GetterUtil.getInteger(datTypeViewMap.get("numAnalyzers"), 0) != 0){
						List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil
							.retrieveDataTypeAnalyzerList(dataTypeId);
						String analyzerStr = "";
						for(Map<String, Object> analyzerMap : dataTypeAnalyzerMap){
							long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
							ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
							analyzerStr = analyzerStr.equals("") ? scienceApp.getName()
								: analyzerStr + "," + scienceApp.getName();
						}
						dataTypeEditorMap.put("analyzer", analyzerStr);
						defaultRowNum++;
					}
					
					dataTypeMap = dataTypeObj.getModelAttributes();
					dataTypeMap.put("dataTypeEditorMap", dataTypeEditorMap);
					dataTypeMap.put("defaultRowNum", defaultRowNum);
				}
				resultMsg = "SUCCESS";
			}
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String dataTypeJsonStr = jsonSerializer.serialize(dataTypeMap);
			JSONObject dataTypeJsonOjbect= JSONFactoryUtil.createJSONObject(dataTypeJsonStr);
			
			jsonObj.put("dataTypeObj", dataTypeJsonOjbect);
			jsonObj.put("resultMsg", resultMsg);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value = "retrieveListAnalyzer")
	public void retrieveListAnalyzer(ResourceRequest request, ResourceResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			Map params = RequestUtil.getParameterMap(request);
			Long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"));
			List<Map<String,Object>> dataTypeAnalyzerList = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataTypeId);
			
			for(Map<String,Object> analyzerMap : dataTypeAnalyzerList){
				
				long analyzerId = GetterUtil.getLong(analyzerMap.get("analyzerId"), 0);
				if(analyzerId != 0){
					ScienceApp analyzerApp =  ScienceAppLocalServiceUtil.getScienceApp(analyzerId);
					
					if(analyzerApp.getStatus() == 1901004){
						analyzerMap.put("editorType", analyzerApp.getEditorType());
						analyzerMap.put("name", analyzerApp.getName());
						analyzerMap.put("title", analyzerApp.getTitle(themeDisplay.getLocale()));
						analyzerMap.put("exeFileName", analyzerApp.getExeFileName());
					}
				}
			}
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String analyzerStr = jsonSerializer.serialize(dataTypeAnalyzerList);
			JSONArray analyzerJson = JSONFactoryUtil.createJSONArray(analyzerStr);
			
			jsonObj.put("analyzer", analyzerJson);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * file remove
	 * @param request
	 * @param response
	 */
	@ResourceMapping(value = "deleteSingleEdisonFile")
	public void deleteSingleEdisonFile(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			long fileEntryId = Long.parseLong(CustomUtil.strNull(params.get("fileEntryId")));

			String resultMsg = "";

			if(EdisonFileUtil.deleteSingleEdisonFile(fileEntryId)){
				resultMsg = "SUCCESS";
			}else{
				resultMsg = "DELETE_FAIL";
			}

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			jsonObj.put("resultMsg", resultMsg);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}

	@ResourceMapping(value = "deleteDataEntryByCollection")
	public void deleteDataEntryByCollection(ResourceRequest request, ResourceResponse response){

		try{
  		Map params = RequestUtil.getParameterMap(request);
  		long dataEntryId = GetterUtil.getLong(params.get("dataEntryId"));
  		String message = "SUCCESS";
  	try{
  			DataEntryLocalServiceUtil.removeDataEntryByEntryId(dataEntryId);
  		}catch (PortalException e1){
  			e1.printStackTrace();
  			message = "FAIL";
  		}catch (SystemException e1){
  			e1.printStackTrace();
  			message = "FAIL";
  		}
		
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			jsonObj.put("resultMsg", message);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * file download
	 * @param request
	 * @param response
	 */
	@ResourceMapping(value = "edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response){
		try{
			Map paramsMap = RequestUtil.getParameterMap(request);
			long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
			EdisonFileUtil.edisonFileDownload(response, fileEntryId);
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}
	
}
