package org.kisti.edison.bestsimulation.portlet.dataentry;

import java.io.PrintWriter;
import java.net.SocketException;
import java.text.SimpleDateFormat;
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

import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
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
import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleThreadLocal;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class DataEntryController{

	private static Log log = LogFactoryUtil.getLog(DataEntryController.class);

	@RequestMapping // default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			Map param = RequestUtil.getParameterMap(request);

			long groupId = ParamUtil.getLong(request, "groupId", PortalUtil.getScopeGroupId(request));
			long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
			
			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int searchLine = ParamUtil.get(request, "searchLine", 10);
			int blockSize = 10;
			int start = ((currentPage - 1) * searchLine);

			String searchText = CustomUtil.strNull(param.get("searchText"));
			
			boolean isAdmin = false;
			long userId = themeDisplay.getUserId();
			long companyGroupId = themeDisplay.getCompanyGroupId();
			
			if(parentGroupId == 0){// 포탈
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
					isAdmin = true;
					userId = 0;
				}
			}else{
				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
					isAdmin = true;
					userId = 0;
				}
			}
			int totalCount = DataEntryLocalServiceUtil.retrieveCountDataCollection(companyGroupId, groupId, searchText, userId);
			List<Map<String, Object>> dataEntryList = DataEntryLocalServiceUtil.retrieveListDataEntry(companyGroupId, groupId, searchText, userId, start, searchLine, themeDisplay.getLocale());
			
			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "dataEntryList",
				totalCount, currentPage, searchLine, blockSize);
			
			model.addAttribute("searchLine", searchLine); 
			model.addAttribute("isAdmin", isAdmin); 
			model.addAttribute("dataEntryList", dataEntryList); 
			model.addAttribute("seq", totalCount - ((currentPage - 1) * searchLine));
			model.addAttribute("paging", paging);
			
			if(!searchText.equals("")){
				model.addAttribute("searchText", searchText);
			}
			
			model.addAttribute("redirectName",  "My Edison");
			String redirectURL = EdisonHttpUtil.removeAndencodeURL(themeDisplay.getURLCurrent());
			model.addAttribute("redirectURL", ParamUtil.get(request, "redirectURL", redirectURL));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dataentry/dataEntryList";
	}
	
	
	@RenderMapping(params = "myRender=manageViewDataEntry")
	public String manageViewDataEntry(RenderRequest request, RenderResponse response, ModelMap model){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			Map param = RequestUtil.getParameterMap(request);
			String mode = CustomUtil.strNull(param.get("mode"), Constants.ADD);
			
			model.addAttribute("mode", mode);
			
			
			int defaultRowNum = 0;
			if(mode.equals(Constants.UPDATE)){
				defaultRowNum = 3;
				long dataEntryId = GetterUtil.getLong(param.get("dataEntryId"), 0);

				DataEntry dataEntry = null;
				if(dataEntryId != 0){
					dataEntry = DataEntryLocalServiceUtil.getDataEntry(dataEntryId);
				}

				model.addAttribute("dataEntry", dataEntry);
				
				if(dataEntry.getFileEntryId() != 0){
					model.addAttribute("dataEntryFile", DLFileEntryLocalServiceUtil.getFileEntry(dataEntry.getFileEntryId()));
				}

				Map<String, Object> collectionInfoMap = new HashMap<String, Object>();
				//콜렉션 조회
				DataCollection collection = DataCollectionLocalServiceUtil.getDataCollection(dataEntry.getCollectionId());
				
				long dataTypeId = collection.getTypeId();
				DataType dataType = DataTypeLocalServiceUtil.getDataType(dataTypeId);
				
				//DataTypeEditor
				Map<String,Object> dataTypeEditorMap = new HashMap<String,Object>();
				if(dataType != null){
					
					//DataTypeEditr,Analyze,Structure exist check
  				Map<String, Object> datTypeViewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);
  			
  				if(GetterUtil.getInteger(datTypeViewMap.get("numEditors"),0)!=0){
  					List<Map<String, Object>> dataTypeEditorMapList = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataTypeId);
  					String editorStr = "";
  					for(Map<String,Object> editorMap : dataTypeEditorMapList){
  						long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
  						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
  						editorStr = editorStr.equals("")?scienceApp.getName():editorStr+","+scienceApp.getName();
  					}
  					dataTypeEditorMap.put("editor", 		editorStr);
  					defaultRowNum++;
  				}
  				
  				//DataTypeAnalyzer
  				if(GetterUtil.getInteger(datTypeViewMap.get("numAnalyzers"),0)!=0){
  					List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataTypeId);
  					String analyzerStr = "";
  					for(Map<String,Object> analyzerMap : dataTypeAnalyzerMap){
  						long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
  						ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
  						analyzerStr = analyzerStr.equals("")?scienceApp.getName():analyzerStr+","+scienceApp.getName();
  					}
  					dataTypeEditorMap.put("analyzer", 		analyzerStr);
  					defaultRowNum++;
  				}
				}
				collectionInfoMap.put("collection", collection);
				collectionInfoMap.put("dataType", dataType);
				collectionInfoMap.put("dataTypeEditorMap", dataTypeEditorMap);
				
				model.addAttribute("defaultRowNum", defaultRowNum);
				model.addAttribute("collectionInfoMap", collectionInfoMap);
				
				//viewCnt update
				DataEntryLocalServiceUtil.incrementViewCnt(dataEntryId);
			}else{
				//monitoring fileId, fileNm
				String monitoringResultFileId = ParamUtil.getString(request, "monitoringResultFileId");
				String monitoringResultFileNm = ParamUtil.getString(request, "monitoringResultFileNm");
				String simulationSubjectId = ParamUtil.getString(request, "simulationSubjectId");
				String simulationUuid = ParamUtil.getString(request, "simulationUuid");
				String jobUuid = ParamUtil.getString(request, "jobUuid");
				String jobSeqNo = ParamUtil.getString(request, "jobSeqNo");
				long groupId = ParamUtil.getLong(request, "groupId");
				
				model.addAttribute("groupId", groupId);
				model.addAttribute("monitoringResultFileId", monitoringResultFileId);
				model.addAttribute("monitoringResultFileNm", monitoringResultFileNm);
				model.addAttribute("simulationSubjectId", simulationSubjectId);
				model.addAttribute("simulationUuid", simulationUuid);
				model.addAttribute("jobUuid", jobUuid);
				model.addAttribute("jobSeqNo", jobSeqNo);
				
				if(!jobUuid.equals("")){
					//jobUuid = "5273a6b1-e73e-4503-8d3b-9ff2850d54ec";
					SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
					Map<String, Object> checkAlreadyExistJobData = DataEntryLocalServiceUtil.checkExistSubjectIdAndJobData(Long.parseLong(simulationSubjectId), simulationJobData.getJobData());
					model.addAttribute("alreadyExistJobDataMap", checkAlreadyExistJobData);
				}
			}
			
			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			model.addAttribute("redirectOrignURL", HttpUtil.decodeURL(redirectURL));

		}catch (Exception e) {
			e.printStackTrace();
		}
		return "dataentry/dataEntryMgt";
	}
	
	
	
	@ResourceMapping(value = "retrieveMapCollection")
	public void retrieveMapCollection(ResourceRequest request, ResourceResponse response){
		try{
			Map params = RequestUtil.getParameterMap(request);
			
			long collectionId = GetterUtil.getLong(params.get("collectionId"), 0);
			
			Map<String, Object> dataCollectionMap = new HashMap<String, Object>();
			String resultMsg = "FAIL";
			
			int defaultRowNum = 3;
			if(collectionId != 0){
				DataCollection collection = DataCollectionLocalServiceUtil.getDataCollection(collectionId);
				DataType dataTypeObj = null;
				//DataTypeEditor
				Map<String,Object> dataTypeEditorMap = new HashMap<String,Object>();
				
				if(collection != null){
					long dataTypeId = collection.getTypeId();
					if(dataTypeId != 0){
						dataTypeObj = DataTypeLocalServiceUtil.getDataType(dataTypeId);
						
						//DataTypeEditr,Analyze,Structure exist check
						Map<String, Object> datTypeViewMap = DataTypeLocalServiceUtil.retrieveViewCount(dataTypeId);
					
						if(GetterUtil.getInteger(datTypeViewMap.get("numEditors"),0)!=0){
							List<Map<String, Object>> dataTypeEditorMapList = DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataTypeId);
							String editorStr = "";
							for(Map<String,Object> editorMap : dataTypeEditorMapList){
								long scienceAppId = GetterUtil.getLong(editorMap.get("editorId"));
								ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
								editorStr = editorStr.equals("")?scienceApp.getName():editorStr+","+scienceApp.getName();
							}
							dataTypeEditorMap.put("editor", 		editorStr);
							defaultRowNum++;
						}
						
						//DataTypeAnalyzer
						if(GetterUtil.getInteger(datTypeViewMap.get("numAnalyzers"),0)!=0){
							List<Map<String, Object>> dataTypeAnalyzerMap = DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataTypeId);
							String analyzerStr = "";
							for(Map<String,Object> analyzerMap : dataTypeAnalyzerMap){
								long scienceAppId = GetterUtil.getLong(analyzerMap.get("analyzerId"));
								ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
								analyzerStr = analyzerStr.equals("")?scienceApp.getName():analyzerStr+","+scienceApp.getName();
							}
							dataTypeEditorMap.put("analyzer", 		analyzerStr);
							defaultRowNum++;
						}
						
						/*//DataTypeStructure
						if(GetterUtil.getBoolean(datTypeViewMap.get("isStructurePresent"),false)){
							Map<String,Object> structureMap = DataTypeStructureLocalServiceUtil.retrieveDataTypeStructurePK(dataTypeId);
						}*/
						resultMsg = "SUCCESS";
					}

					dataCollectionMap = collection.getModelAttributes();

					if(dataTypeObj != null){
						Map<String, Object> dataTypeMap = new HashMap<String, Object>();
						dataTypeMap = dataTypeObj.getModelAttributes();
						
						dataCollectionMap.put("dataTypeEditorMap", dataTypeEditorMap);
						dataCollectionMap.put("dataType", dataTypeMap);
					}
				}
			}
			
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String dataTypeJsonStr = jsonSerializer.serialize(dataCollectionMap);
			JSONObject dataTypeJsonOjbect= JSONFactoryUtil.createJSONObject(dataTypeJsonStr);
			
			jsonObj.put("defaultRowNum", defaultRowNum);
			jsonObj.put("dataCollection", dataTypeJsonOjbect);
			jsonObj.put("resultMsg", resultMsg);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@ActionMapping(params = "myAction=manageDataEntry")
	public void manageDataEntry(ActionRequest request, ActionResponse response){
		try{
			
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			Map param = RequestUtil.getParameterMap(request);
			String mode = CustomUtil.strNull(param.get("mode"), Constants.ADD);
			
			//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(param.get("currentPage"));
			String searchLine = CustomUtil.strNull(param.get("searchLine"));
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");
			
			long collectionId = GetterUtil.getLong(param.get("collectionId"), 0);
			long dataEntryId = GetterUtil.getLong(param.get("dataEntryId"), 0);

			Map<Locale, String> deComment = CustomUtil.getLocalizationMap(param, "comment");
			
			DataCollection collection = DataCollectionLocalServiceUtil.getDataCollection(collectionId);
			DLFolder collectionDLFolder =  DLFolderLocalServiceUtil.getFolder(collection.getFolderId());
			String path = collectionDLFolder.getPath();
			
			ServiceContext sc = ServiceContextFactory.getInstance(DataEntry.class.getName(), request);
			sc.setScopeGroupId(collection.getGroupId());

			
			DataEntry dataEntry = null;
			if(dataEntryId == 0){
				String jobData = "";
				Long simulationSubjectId = GetterUtil.getLong(param.get("simulationSubjectId"), 0);
				String simulationUuid = GetterUtil.getString(param.get("simulationUuid"), "");
				
				String productionTime = "0";
				long jobGroupId = GetterUtil.getLong(param.get("groupId"), 0);
				long jobSeq = GetterUtil.getLong(param.get("jobSeq"), 0);
				String jobUuid = GetterUtil.getString(param.get("jobUuid"), "");
				
				if(!jobUuid.equals("")){
					//jobUuid = "5273a6b1-e73e-4503-8d3b-9ff2850d54ec";
					SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.getSimulationJobData(jobUuid);
					jobData  = simulationJobData.getJobData();
					
					Map<String, Object> simulationJobMap = SimulationJobLocalServiceUtil.getMonitoringJob(jobGroupId,
						simulationUuid, jobSeq);
					
					
					String jobExecuteDt = GetterUtil.get(simulationJobMap.get("executeDt"), "");
					if(!jobExecuteDt.equals("")){
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
						Date executeDate = simpleDateFormat.parse(jobExecuteDt);
						productionTime = String.valueOf(executeDate.getTime()/1000); 
					}
					
				}
				
				String monitoringResultFileNm = CustomUtil.strNull(param.get("monitoringResultFileNm"), "");
				String monitoringResultFileId = CustomUtil.strNull(param.get("monitoringResultFileId"), "");
				
				
				dataEntry = DataEntryLocalServiceUtil.createDataEntryObject(collectionId,
					simulationSubjectId, jobData, path, deComment, productionTime, collection.getFolderId(),
					monitoringResultFileId, monitoringResultFileNm, sc);
			}else{
				dataEntry = DataEntryLocalServiceUtil.updateDataEntryObject(dataEntryId, 
					collectionId, deComment, collection.getFolderId(), sc);
			}
			
			//DataType dataType = DataTypeLocalServiceUtil.getDataType(collection.getTypeId());
			
			response.setPortletMode(PortletMode.VIEW);
			response.setRenderParameter("myRender", "manageViewDataEntry");
			response.setRenderParameter("mode", Constants.UPDATE);
			response.setRenderParameter("p_p_state", "maximized");
			response.setRenderParameter("dataEntryId", String.valueOf(dataEntry.getEntryId()));
			response.setRenderParameter("collectionId", String.valueOf(collectionId));
			response.setRenderParameter("redirectName", redirectName);
			response.setRenderParameter("redirectURL", redirectURL);
			response.setRenderParameter("currentPage", currentPage);
			response.setRenderParameter("searchLine", searchLine);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ActionMapping(params = "myAction=deleteDataEntry")
	public void deleteDataEntry(ActionRequest request, ActionResponse response){
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			Map param = RequestUtil.getParameterMap(request);
			
		//searchText, serarchLine, currentPage
			String currentPage = CustomUtil.strNull(param.get("currentPage"));
			String searchLine = CustomUtil.strNull(param.get("searchLine"));
			
			long dataEntryId = GetterUtil.getLong(param.get("dataEntryId"), 0);
			
			if(dataEntryId != 0){ 
				DataEntryLocalServiceUtil.removeDataEntryByEntryId(dataEntryId);
			}
			
			response.setRenderParameter("currentPage", currentPage);
			response.setRenderParameter("searchLine", searchLine);
			response.setRenderParameter("clickTab", "myScienceData");
			
			SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
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
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
			long dataEntryid = GetterUtil.get(paramsMap.get("dataEntryId"), 0);
			
			if(dataEntryid != 0){
  			DataEntryLocalServiceUtil.incrementDownloadCnt(dataEntryid);
  			
  			long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));
  			EdisonFileUtil.edisonFileDownload(response, fileEntryId);
			}
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}

	}
}

