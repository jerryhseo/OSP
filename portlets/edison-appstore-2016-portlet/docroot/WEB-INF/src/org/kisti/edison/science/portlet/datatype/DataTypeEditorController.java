
package org.kisti.edison.science.portlet.datatype;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.DataTypeException;
import com.kisti.osp.icecap.NoSuchDataTypeStructureException;
import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class DataTypeEditorController{
	
	private static Log log = LogFactory.getLog(DataTypeEditorController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws SystemException, IOException, PortalException, SQLException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		model.addAttribute("portletWindowState", request.getWindowState().toString());
		
		//APP관리에서 넘어온 경우 Parameter SETTING
		String searchByPrePage = GetterUtil.getString(params.get("searchByPrePage"));
		if(StringUtil.equalsIgnoreCase(searchByPrePage, "APP")){
			model.addAttribute("searchByPrePage",searchByPrePage);
			model.addAttribute("portType", GetterUtil.getString(params.get("portType")));
			model.addAttribute("portName", GetterUtil.getString(params.get("portName")));
		}
		
		model.addAttribute("portletWindowState", request.getWindowState().toString());
		
		String redirectURL = "";
		String redirectName = "";
		if(!request.getWindowState().equals(LiferayWindowState.POP_UP)){
            redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
			redirectName = GetterUtil.getString(params.get("redirectName"),"My EDISON");
		}else{
			redirectURL = GetterUtil.getString(params.get("redirectURL"),EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
			redirectName = GetterUtil.getString(params.get("redirectName"),"My EDISON");
		}
		
		
		model.addAttribute("redirectURL", redirectURL);
		model.addAttribute("redirectName", redirectName);
		model.addAttribute("searchName", CustomUtil.strNull(params.get("searchName")));
		
		
		//DATA SEARCH
		int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
		int linePerPage = 15;
		int pagePerBlock = 5;
		int begin = (curPage - 1) * linePerPage;
		int end = begin+linePerPage;
		
		String searchName = CustomUtil.strNull(params.get("searchName"));
		
		long ownerId = 0;
		if(!request.getWindowState().equals(LiferayWindowState.POP_UP)&&!EdisonUserUtil.isRegularRole(themeDisplay.getUser(), RoleConstants.ADMINISTRATOR)){
			ownerId = themeDisplay.getUserId();
		}
		
		int totalCnt = DataTypeLocalServiceUtil.retrieveDataTypeCount(searchName, null, ownerId);
		List<Map<String, Object>> listDataType = DataTypeLocalServiceUtil.retrieveDataTypeObjects(searchName,null,ownerId,begin,end);
		String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"searchDataTypeList", totalCnt, curPage, linePerPage, pagePerBlock);
		
		model.addAttribute("listDataType", listDataType);
		model.addAttribute("pagingStr", pagingStr);
		
		return "datatype/list";
	}
	
	/*
	 * modifyMode = modifyEditorAnalyzer -> DataType Editor&Analyzer 수정
	 * modifyMode = modifyContent -> DataTypeSampleFile 및 InputDeck 내용 수정
	 */
	@RenderMapping(params={"myRender=dataTypeModifyRender"})
	public String dataTypeRender(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		String returnJSP = GetterUtil.getString(params.get("modifyMode"),"modifyEditorAnalyzer");
		
		String redirectURL = GetterUtil.getString(params.get("redirectURL"),EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent()));
		String redirectName = GetterUtil.getString(params.get("redirectName"),"My EDISON");
		long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"),0);
		String noMode = dataTypeId==0?Constants.ADD:Constants.UPDATE;
		String mode = GetterUtil.getString(params.get("mode")).equals("")?noMode:params.get("mode").toString();
		boolean editDataName = false;
		
		//APP관리에서 넘어온 경우 Parameter SETTING
		String searchByPrePage = GetterUtil.getString(params.get("searchByPrePage"));
		if(StringUtil.equalsIgnoreCase(searchByPrePage, "APP")){
			model.addAttribute("searchByPrePage",searchByPrePage);
			model.addAttribute("portType", GetterUtil.getString(params.get("portType")));
			model.addAttribute("portName", GetterUtil.getString(params.get("portName")));
		}
		
		model.addAttribute("searchName", GetterUtil.getString(params.get("searchName")));
		
		try{
			if(returnJSP.equals("modifyEditorAnalyzer")){
				long groupId = themeDisplay.getScopeGroupId();
				Locale locale = themeDisplay.getLocale();
				//EDISON EDITOR 목록 조회
                String[] editorAppTypes = {ScienceAppConstants.APP_TYPE_EDITOR};
                //String[] editorTypes = {ScienceAppConstants.EDITOR_TYPE_FILE,
                    ///ScienceAppConstants.EDITOR_TYPE_INPUT_DECK, ScienceAppConstants.EDITOR_TYPE_TEXT};
				List<Map<String, Object>> editorList = ScienceAppLocalServiceUtil.retrieveListScienceApp(
				    groupId, locale, 0, editorAppTypes, null, null, "1901004", 0, 0,true);
				
				//EDISON ANALYZER 목록 조회
				String[] analyzerAppTypes = {ScienceAppConstants.APP_TYPE_ANALYZER};
				List<Map<String, Object>> analyzerList = ScienceAppLocalServiceUtil.retrieveListScienceApp(
				    groupId, locale, 0, analyzerAppTypes, null, null, "1901004", 0, 0,true);
				
				model.addAttribute("editorList", editorList);
				model.addAttribute("analyzerList", analyzerList);
			}
			
			if(mode.equals(Constants.ADD)){
				editDataName = true;
			}else{
				Map<String, Object> dataTypeMap = DataTypeLocalServiceUtil.retrieveDataTypeObject(dataTypeId);
				
				if(!returnJSP.equals("modifyEditorAnalyzer")){
					String description = LocalizationUtil.getLocalization(GetterUtil.getString(dataTypeMap.get("description")), themeDisplay.getLanguageId());
					dataTypeMap.put("description", description);
				}
				
				//DataTypeEditr,Analyze,Structure exist check
				Map<String, Object> viewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);
				
				
				//DataTypeEditor
				if(GetterUtil.getInteger(viewMap.get("numEditors"),0)!=0){
					List<Map<String, Object>> dataTypeEditorMap = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataTypeId);
					String editorStr = "";
					String editorName = "";
					boolean inputdeckExist = false;
					for(Map<String,Object> editorMap : dataTypeEditorMap){
						long editorId = GetterUtil.getLong(editorMap.get("editorId"));
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(editorId);
						editorStr = editorStr.equals("")?String.valueOf(scienceApp.getScienceAppId()):editorStr+","+String.valueOf(scienceApp.getScienceAppId());
						editorName = editorName.equals("")?String.valueOf(scienceApp.getName()):editorName+","+String.valueOf(scienceApp.getName());
						
						if(!inputdeckExist){
							if(scienceApp.getEditorType().equals(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK)){
								inputdeckExist = true;
							}
						}
						if(GetterUtil.getBoolean(editorMap.get("isDefault"))){
							model.addAttribute("defaultEditor",scienceApp.getScienceAppId());
							model.addAttribute("defaultEditorName",scienceApp.getName());
						}
					}
					model.addAttribute("editorStr", editorStr);
					model.addAttribute("editorName", editorName);
					model.addAttribute("inputdeckExist", inputdeckExist);
					
				}
				
				//DataTypeAnalyzer
				if(GetterUtil.getInteger(viewMap.get("numAnalyzers"),0)!=0){
					List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataTypeId);
					String analyzerStr = "";
					String analyzerName = "";
					for(Map<String,Object> analyzerMap : dataTypeAnalyzerMap){
						long analyzerId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(analyzerId);
						analyzerStr = analyzerStr.equals("")?String.valueOf(scienceApp.getScienceAppId()):analyzerStr+","+String.valueOf(scienceApp.getScienceAppId());
						analyzerName = analyzerName.equals("")?String.valueOf(scienceApp.getName()):analyzerName+","+String.valueOf(scienceApp.getName());
						
						if(GetterUtil.getBoolean(analyzerMap.get("isDefault"))){
							model.addAttribute("defaultAnalyzer",scienceApp.getScienceAppId());
							model.addAttribute("defaultAnalyzerName",scienceApp.getName());
						}
					}
					model.addAttribute("analyzerStr", analyzerStr);
					model.addAttribute("analyzerName", analyzerName);
				}
				
				if(returnJSP.equals("modifyContent")){
					//SAMPLE FILE
					long sampleFilePath = GetterUtil.getLong(dataTypeMap.get("samplePath"));
					if(sampleFilePath!=0){
						try{
							DLFileEntry sampleFile =  DLFileEntryLocalServiceUtil.getDLFileEntry(sampleFilePath);
							dataTypeMap.put("sampleRepositoryId", sampleFile.getRepositoryId());
							dataTypeMap.put("sampleUuid", 		sampleFile.getUuid());
							dataTypeMap.put("sampleTitle", 		sampleFile.getTitle());
						}catch (Exception e) {
							if(e instanceof NoSuchFileEntryException){
								
							}else{
								throw e;
							}
						}
					}
					//DataTypeStructure
					if(GetterUtil.getBoolean(viewMap.get("isStructurePresent"),false)){
						Map<String,Object> structureMap = DataTypeStructureLocalServiceUtil.retrieveDataTypeStructurePK(dataTypeId);
						dataTypeMap.put("structure", structureMap.get("structure"));
					}
				}
				
				model.addAttribute("dataTypeMap", dataTypeMap);
			}
			
			model.addAttribute("mode", mode);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		
		model.addAttribute("editDataName", editDataName);
		model.addAttribute("redirectURL", redirectURL);
		model.addAttribute("redirectName", redirectName);
		model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));
		
		return "datatype/"+returnJSP;
	}
	
	@ActionMapping(value="dataTypeAction")
	public void dataTypeAction(ActionRequest request, ActionResponse response, Model model){
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String mode = CustomUtil.strNull(params.get("mode"));
		long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"),0);
		
		try{
			if(mode.equals(Constants.DELETE)){
				DataTypeLocalServiceUtil.removeDataTypeObject(dataTypeId);
			}else if(mode.equals(Constants.COPY)){
				ServiceContext sc = ServiceContextFactory.getInstance(DataType.class.getName(), request);
				Map<String,Object> copyedDataType = DataTypeLocalServiceUtil.copyDataType(dataTypeId,themeDisplay.getUserId(),sc);
				dataTypeId = GetterUtil.getLong(copyedDataType.get("typeId"));
			}else{
				ServiceContext sc = ServiceContextFactory.getInstance(DataType.class.getName(), request);
				List<Map<String,Object>> editorList = returnParameterFormList(params.get("editorCheckbox"), GetterUtil.getString(params.get("defaultEditorSelect")),true);
				
				boolean structureExist = false;
				for(int i=0;i<editorList.size();i++){
					Map<String, Object> editorContents = (Map<String, Object>)editorList.get(i);
					Long editorId = GetterUtil.getLong(editorContents.get("editorId"),0);
					if(!structureExist){
						if(ScienceAppLocalServiceUtil.getScienceApp(editorId).getEditorType().equals(ScienceAppConstants.EDITOR_TYPE_INPUT_DECK)){
							structureExist = true;
						}
					}
				}
				
				
				
				List<Map<String,Object>> analyzerList = returnParameterFormList(params.get("analyzerCheckbox"), GetterUtil.getString(params.get("defaultAnalyzerSelect")),false);
				
				String dtName = CustomUtil.strNull(params.get("dtName"));
				String dtVersion = CustomUtil.strNull(params.get("dtVersion"),"1.0.0");
				
				if(mode.equals(Constants.ADD)){
					if(DataTypeLocalServiceUtil.checkDataTypeObjectValiation(dtName, dtVersion)){
						throw new DataTypeException("DUPLICATED");
					}
				}
				
				String modifyMode = CustomUtil.strNull(params.get("modifyMode"),"modifyEditorAnalyzer");
				if(modifyMode.equals("modifyEditorAnalyzer")){
					//다름 PORTLE 에서 넘어 와서 저장을 할 경우 redirectURL에 PARAMETER를 추가 한다.
//					String searchByPrePage = GetterUtil.getString(params.get("searchByPrePage"));
					
//					if(StringUtil.equalsIgnoreCase(searchByPrePage, "APP")){
//						String redirectURL = CustomUtil.strNull(params.get("redirectURL"));
//						if(!redirectURL.equals("")){
//							String deCodeURL = HttpUtil.decodeURL(redirectURL);
//							deCodeURL = HttpUtil.addParameter(deCodeURL, "_scienceappmanager_WAR_edisonappstore2016portlet_openDataType", true);
//							deCodeURL = HttpUtil.addParameter(deCodeURL, "_scienceappmanager_WAR_edisonappstore2016portlet_openDataTypeSearchName", dtName);
//						}
//					}else if(StringUtil.equalsIgnoreCase(searchByPrePage, "APP")){
//						
//					}
					
					dataTypeId = GetterUtil.getLong(params.get("dataTypeId"));
					Map<Locale,String> description = CustomUtil.getLocalizationNotSetLocaleMap(params, "description");
					dataTypeId = DataTypeLocalServiceUtil.modifyDataTypeObjectForEditorAnalyzer(dtName,dtVersion,description,editorList,analyzerList, sc);
					
					if(!structureExist){
						try {
							DataTypeStructureLocalServiceUtil.deleteDataTypeStructure(dataTypeId);
						} catch (NoSuchDataTypeStructureException e) {
							
						}
					}
					
					response.setRenderParameter("modifyMode", "modifyContent");
				}else{
					String structure = GetterUtil.getString(params.get("structure"));
					DataTypeStructure dataTypeStructure = null;
					try{
						dataTypeStructure = DataTypeStructureLocalServiceUtil.getDataTypeStructure(dataTypeId);
					}catch (NoSuchDataTypeStructureException e) {
						dataTypeStructure = DataTypeStructureLocalServiceUtil.createDataTypeStructure(dataTypeId);
					}
					dataTypeStructure.setStructure(structure);
					
					System.out.println(dataTypeStructure);
					DataTypeStructureLocalServiceUtil.updateDataTypeStructure(dataTypeStructure);
//					DataTypeStructureLocalServiceUtil.createDataTypeStructureObject(dataTypeId, structure);
					response.setRenderParameter("modifyMode", "modifyContent");
				}
			}
			
			
			if(!mode.equals(Constants.DELETE)){
				response.setWindowState(LiferayWindowState.MAXIMIZED);
				
				if(mode.equals(Constants.ADD)||mode.equals(Constants.COPY)){
					SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
				}else if(mode.equals(Constants.UPDATE)){
					SessionMessages.add(request, EdisonMessageConstants.UPDATE_SUCCESS);
				}
				
				RequestUtil.copyRequestParameters(request, response, new String[] {"redirectURL","redirectName","searchByPrePage","portType","portName","searchName"});
				response.setRenderParameter("dataTypeId", String.valueOf(dataTypeId));
			}else{
//				response.setPortletMode(PortletMode.VIEW);
				SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
				response.setRenderParameter("searchName", CustomUtil.strNull(params.get("searchName")));
			}
		}catch(Exception e){
			PortalUtil.copyRequestParameters(request, response);
			if(e instanceof DataTypeException){
				SessionErrors.add(request, e.getClass(), e);
			}else{
				e.printStackTrace();
				//Session Error Message
				if(mode.equals(Constants.ADD)||mode.equals(Constants.COPY)){
					SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
				}else if(mode.equals(Constants.UPDATE)){
					SessionErrors.add(request, EdisonMessageConstants.UPDATE_ERROR);
				}else if(mode.equals(Constants.DELETE)){
					SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
				}
			}
		}finally {
			if(!mode.equals(Constants.DELETE)){
				response.setRenderParameter("myRender", "dataTypeModifyRender");
			}
		}
	}
	
	/**
	 * Editor,Analyzer 파라미터 Format 생성
	 * @param value
	 * @param defaultId
	 * @return
	 */
	protected List<Map<String,Object>> returnParameterFormList(Object value,String defaultId,boolean editor){
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		String key = editor?"editorId":"analyzerId";
		if(value!=null){
			if(value instanceof String){
				String val = GetterUtil.getString(value);
				Map<String,Object> returnMap = new HashMap<String,Object>();
				returnMap.put(key, val);
				if(val.equals(defaultId)){
					returnMap.put("isDefault", true);
				}
				returnList.add(returnMap);
			}else{
				String[] values = GetterUtil.getStringValues(value);
				
				for(String val:values){
					Map<String,Object> returnMap = new HashMap<String,Object>();
					returnMap.put(key, val);
					if(val.equals(defaultId)){
						returnMap.put("isDefault", true);
					}
					
					returnList.add(returnMap);
				}
			}
		}
		return returnList;
	}
	
	
	@ResourceMapping(value="addDataTypeSampeFile")
	public void addDataTypeSampeFile(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"),0);
		
		try{
			ServiceContext sc = ServiceContextFactory.getInstance(PortType.class.getName(), request);
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(sc.getLiferayPortletRequest());
			
			Map<String,Object> sampleFileMap = DataTypeLocalServiceUtil.addSampleFilePath(dataTypeId,sc,upload);
			
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
	
	
	@ResourceMapping(value="dataTypeSearch")
	public void dataTypeSearch(ResourceRequest request, ResourceResponse response){
		try {
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
			int linePerPage = 10;
			int pagePerBlock = 5;
			int begin = (curPage - 1) * linePerPage;
			int end = begin+linePerPage;
			
			String searchName = CustomUtil.strNull(params.get("searchName"));
			
			long ownerId = 0;
			if(!request.getWindowState().equals(LiferayWindowState.POP_UP)&&!EdisonUserUtil.isRegularRole(themeDisplay.getUser(), RoleConstants.ADMINISTRATOR)){
				ownerId = themeDisplay.getUserId();
			}
			
			int totalCnt = DataTypeLocalServiceUtil.retrieveDataTypeCount(searchName, null, ownerId);
			List<Map<String, Object>> listDataType = DataTypeLocalServiceUtil.retrieveDataTypeObjects(searchName,null,ownerId,begin,end);
			
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"searchDataTypeList", totalCnt, curPage, linePerPage, pagePerBlock);
			
			response.setContentType("application/json; charset=UTF-8");
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String optionJson = jsonSerializer.serialize(listDataType);
			JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			
			JSONObject returnObject = JSONFactoryUtil.createJSONObject();
			returnObject.put("dataList", optionArr);
			returnObject.put("pagingStr", pagingStr);
			
			PrintWriter out = response.getWriter();
			out.write(returnObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="dataTypeView")
	public void dataTypeView(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			
			long dataTypeId = GetterUtil.getLong(params.get("typeId"));
			Map<String, Object> dataTypeMap = DataTypeLocalServiceUtil.retrieveDataTypeObject(dataTypeId);
			String description = LocalizationUtil.getLocalization(GetterUtil.getString(dataTypeMap.get("description")), themeDisplay.getLanguageId());
			jsonMap.put("description", description);
			
			
			//SAMPLE FILE
			long sampleFilePath = GetterUtil.getLong(dataTypeMap.get("samplePath"),0);
			jsonMap.put("sampleFileId", sampleFilePath);
			if(sampleFilePath!=0){
				try{
					DLFileEntry sampleFile =  DLFileEntryLocalServiceUtil.getDLFileEntry(sampleFilePath);
					jsonMap.put("sampleRepositoryId", sampleFile.getRepositoryId());
					jsonMap.put("sampleUuid", 		sampleFile.getUuid());
					jsonMap.put("sampleTitle", 		sampleFile.getTitle());
				}catch (Exception e) {
					if(e instanceof NoSuchFileEntryException){
						
					}else{
						throw e;
					}
				}
			}
			
			//DataTypeEditr,Analyze,Structure exist check
			Map<String, Object> viewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);
			//DataTypeEditor
			if(GetterUtil.getInteger(viewMap.get("numEditors"),0)!=0){
				List<Map<String, Object>> dataTypeEditorMap = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataTypeId);
				String editorStr = "";
				for(Map<String,Object> editorMap : dataTypeEditorMap){
					long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
					ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
					editorStr = editorStr.equals("")?scienceApp.getName():editorStr+","+scienceApp.getName();
				}
				jsonMap.put("editor", 		editorStr);
			}
			
			//DataTypeAnalyzer
			if(GetterUtil.getInteger(viewMap.get("numAnalyzers"),0)!=0){
				List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataTypeId);
				String analyzerStr = "";
				for(Map<String,Object> analyzerMap : dataTypeAnalyzerMap){
					long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
					ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
					analyzerStr = analyzerStr.equals("")?scienceApp.getName():analyzerStr+","+scienceApp.getName();
				}
				jsonMap.put("analyzer", 		analyzerStr);
			}
			
			//DataTypeStructure
			boolean isStructurePresent = GetterUtil.getBoolean(viewMap.get("isStructurePresent"),false);
			if(isStructurePresent){
				Map<String,Object> structureMap = DataTypeStructureLocalServiceUtil.retrieveDataTypeStructurePK(dataTypeId);
				jsonMap.put("structure", 		structureMap.get("structure"));
			}
			
			jsonMap.put("isStructurePresent", 		isStructurePresent);
			
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			jsonObj.putAll(jsonMap);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	/**
	 * DataType 수정 및 삭제 가능 여부 체크
	 * Default Check -> ScienceApp에서 In,out Port 에서 사용 여부 체크
	 * MODE = Constants.DELETE -> DataCollection에서 사용 여부 체크
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping(value="dataTypeModifyValidation")
	public void dataTypeModifyValidation(ResourceRequest request, ResourceResponse response) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		try {
			boolean validationCheck = true;
			if (!EdisonUserUtil.isRegularRole(themeDisplay.getUser(), RoleConstants.ADMINISTRATOR)){
				long typeId = GetterUtil.getLong(params.get("typeId"));
				String mode = GetterUtil.getString(params.get("mode"));
				
				long inputCnt = ScienceAppInputPortsLocalServiceUtil.getInputPortsCountByPotyTypeId(typeId);
				
				if(inputCnt!=0){validationCheck = false;}
				if(validationCheck){
					long outputCnt = ScienceAppOutputPortsLocalServiceUtil.getOutPortsCountByPotyTypeId(typeId);
					if(outputCnt!=0){validationCheck = false;}
				}
				
				if(mode.equals(Constants.DELETE)&&validationCheck){
					DynamicQuery query = DynamicQueryFactoryUtil.forClass(DataCollection.class);
					query.add(RestrictionsFactoryUtil.eq("typeId", typeId));
					long dataCollectionCnt = DataCollectionLocalServiceUtil.dynamicQueryCount(query);
					if(dataCollectionCnt!=0){validationCheck=false;}
				}
			}
			net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			jsonObj.put("result", validationCheck);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	/**
	 * Data name,version 중복 체크
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping(value="checkeDataName")
	public void checkePortName(ResourceRequest request, ResourceResponse response) throws IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String name = CustomUtil.strNull(params.get("name"));
		String version = CustomUtil.strNull(params.get("version"));
		boolean result = false;
		try {
			if(!DataTypeLocalServiceUtil.checkDataTypeObjectValiation(name, version)){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
		
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put("result", result);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		
	}
	
	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}
	
	@ResourceMapping(value="deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long fileEntryId = GetterUtil.getLong(params.get("fileEntryId"),0);
		long dataTypeId = GetterUtil.getLong(params.get("dataTypeId"),0);
		
		DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileEntryId);
		DataType dataType = DataTypeLocalServiceUtil.getDataType(dataTypeId);
		dataType.setSamplePath("0");
		dataType.setModifiedDate(new Date());
		DataTypeLocalServiceUtil.updateDataType(dataType);
	}
	
	protected static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
}

