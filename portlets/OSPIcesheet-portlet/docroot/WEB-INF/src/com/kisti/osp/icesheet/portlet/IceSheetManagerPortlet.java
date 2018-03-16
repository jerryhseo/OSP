package com.kisti.osp.icesheet.portlet;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

//class Job{
//	String uuid = "";
//	String jobData = "";
//	String jobSubmitTime = ""; // for queuing time check
//	String jobStartTime = ""; 
//	String jobEndTime = "";
//	String  = true;
//}

/**
 * Portlet implementation class IceSheetManagerPortlet
 */
public class IceSheetManagerPortlet extends MVCPortlet {
	private static final Log _log = LogFactoryUtil.getLog(IceSheetManagerPortlet.class.getName());

	/** 
	 * @param renderRequest
	 * @param renderResponse
	 * @throws IOException
	 * @throws PortletException
	 * 
	 * Also see @see com.liferay.util.bridges.mvc.MVCPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse).
	 */
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
			String renderPage = ParamUtil.getString(renderRequest, "renderPage");

			String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
			_log.info("renderPage: "+renderPage);
			_log.info("mvcPath: "+mvcPath);

		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void doConfig(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doConfig(renderRequest, renderResponse);
	}
	
//	We create a PROV document per simulation
	/****
	 * We create a PROV document per simulation
	 * @param scienceAppName
	 * @param simulationUuid
	 * @param jobUuidArray
	 */
	public void createPROVDoc(
//			Map<String, String> userInfoMap, /* Map<userName, Affiliation> */
//			Map<String, String> scienceAppMap/* Map<ScienceAppId, ScienceAppName>*/,
//			Map<String, String> simulationInfoMap/* Map<simulationUuid, jobData> */,
//			Map<String, Object> jobInfoMap /* Map<jobUuid, JSON>*/
			String simulationUuid /* a given simulation uuid */
			) throws IOException, PortletException, SystemException{
		
		/***
		 * Let's directly have access to our portal database.
		 */
		String query = "";
		
	}
	
	
	/****
	 * Create a PROV doc
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void createPROVAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		
		String appName = ParamUtil.getString(actionRequest, "reqAppName");
System.out.println("requested science app name: " + appName);

		String appVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
System.out.println("requested science app version: " + appVersion);

		String startYear = ParamUtil.getString(actionRequest, "reqStartYear");
		String startMon = ParamUtil.getString(actionRequest, "reqStartMon");
		String startDay = ParamUtil.getString(actionRequest, "reqStartDay");
		
		String endYear = ParamUtil.getString(actionRequest, "reqEndYear");
		String endMon = ParamUtil.getString(actionRequest, "reqEndMon");
		String endDay = ParamUtil.getString(actionRequest, "reqEndDay");
		
		/****
		 * Simulation submit start date
		 */
		String startDateTime = startYear+Constants.DATE_STR_CONNECTOR+startMon+Constants.DATE_STR_CONNECTOR+startDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
		/****
		 * Simulation submit end date
		 */
		String endDateTime = endYear+Constants.DATE_STR_CONNECTOR+endMon+Constants.DATE_STR_CONNECTOR+endDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
		
		
		actionRequest.setAttribute("reqAppName", appName);
		actionRequest.setAttribute("reqAppVersion", appVersion);
		actionRequest.setAttribute("reqStartDateTime", startDateTime);
		actionRequest.setAttribute("reqEndDateTime", endDateTime);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.PROV_CREATE_APP_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
		
//		String entity  = (ParamUtil.getString(actionRequest, "entity"));
		
//		String reqDataTypeId = (ParamUtil.getString(actionRequest, "reqDataTypeIdVal"));
//		System.out.println("action request - received typeid: " + reqDataTypeId);
//				String reqDataCollectionName = ParamUtil.getString(actionRequest, "reqDataCollectionNameVal");
//		System.out.println("action request - received name: " + reqDataCollectionName);
//				String reqDataCollectionVersion = ParamUtil.getString(actionRequest, "reqDataCollectionVersionVal");
//		System.out.println("action request - received version: " + reqDataCollectionVersion);
//				String reqDataCollectionTitle = ParamUtil.getString(actionRequest, "reqDataCollectionTitleVal");
//		System.out.println("action request - received title: " + reqDataCollectionTitle);
//				
//				//get status
//				String reqDataCollectionStatus = ParamUtil.getString(actionRequest, "reqDataCollectionStatusVal");
//				//get description
//				String reqDataCollectionDescription = ParamUtil.getString(actionRequest, "reqDataCollectionDescriptionVal");
//				//get access method
//				String reqDataCollectionAccessMethod = ParamUtil.getString(actionRequest, "reqDataCollectionAccessMethodVal");
//				//get target language
//				String reqDataCollectionTargetLanguage = ParamUtil.getString(actionRequest, "reqTargetLangVal");
//		System.out.println("action request - received target language: " + reqDataCollectionTargetLanguage);
//		
		
		
//		UploadPortletRequest uploadPortletRequest =
//				PortalUtil.getUploadPortletRequest(actionRequest);
//		String reqDataTypeName = ParamUtil.getString(uploadPortletRequest, "reqDataTypeNameVal");
//System.out.println("action request - received name: " + reqDataTypeName);
//		String reqDataTypeVersion = ParamUtil.getString(uploadPortletRequest, "reqDataTypeVersionVal");
//System.out.println("action request - received version: " + reqDataTypeVersion);
//		String reqDataTypeDescription = ParamUtil.getString(uploadPortletRequest, "reqDataTypeDescriptionVal");
	
		// get sample file path
//		String sampleFilePath = "";
//		File uploadedSampleFile = uploadPortletRequest.getFile("reqDataTypeFileSamplePath");
////		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
////		String name = ParamUtil.getString(uploadPortletRequest,"reqDataTypeFileSamplePath");
////		String name = ParamUtil.getString(uploadRequest,"name");
//		if(uploadedSampleFile == null){
//			System.out.println("File does not exist");
//			sampleFilePath = null;
//		}else{
//			System.out.println("File exists!");
////			sampleFilePath = IceSheetManagerServiceUtil.saveDataTypeSampleFile(reqDataTypeName, 
////					reqDataTypeVersion, 
////																			 uploadedSampleFile.getName(), 
////																			 uploadedSampleFile.getAbsolutePath());
//		}
		// get status
//		String reqDataTypeStatus = ParamUtil.getString(uploadPortletRequest, "reqDataTypeStatusVal");
//		// get type structure
//		String reqDataTypeStructure = ParamUtil.getString(uploadPortletRequest, "reqDataTypeStructureVal");
//		// get user id
//		String reqDataTypeUserId = ParamUtil.getString(uploadPortletRequest, "reqDataTypeUserIdVal");
//		// get company id
//		String reqDataTypeCompanyId = ParamUtil.getString(uploadPortletRequest, "reqDataTypeCompanyIdVal");
//		// get create date
//		String reqDataTypeCreateDate = ParamUtil.getString(uploadPortletRequest, "reqCreateTypeVal");
//		// get modified date
//		String reqDataTypeModifiedDate = ParamUtil.getString(uploadPortletRequest, "reqModifiedTypeVal");

		// get owner id
//		Long reqDataTypeOwnerId = ParamUtil.getLong(uploadPortletRequest, "reqDataTypeOwnerIdVal");
//		// get favorite
//		boolean reqDataTypeFavorite = ParamUtil.getBoolean(uploadPortletRequest, "reqDataTypeFavoriteVal");
		
		// create service context 
//		ServiceContext sc = null;
//		try {
//			sc = ServiceContextFactory.getInstance(DataType.class.getName(), actionRequest);
//		} catch (PortalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		boolean success = false;
//		try{
//			// create a data type object
//			success = DataTypeLocalServiceUtil.createDataTypeObject(reqDataTypeName, 
//													 reqDataTypeVersion,
//													 reqDataTypeDescription,
//													 sampleFilePath,
//													 reqDataTypeStatus,
//													 reqDataTypeStructure,
////													 reqDataTypeUserId,
////													 reqDataTypeCompanyId,
////													 reqDataTypeCreateDate,
////													 reqDataTypeModifiedDate,
//													 reqDataTypeOwnerId,
//													 reqDataTypeFavorite,
//													 sc
//													 );
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		if(!success){
//			System.err.println("failed to create a DataType object");
//		}
		
//		boolean success = false;
//		DataType dtObj = DataTypeLocalServiceUtil.findDataTypeObject(reqDataTypeName, reqDataTypeVersion);
//		if(dtObj == null) throw new SystemException();
//		long typeId = dtObj.getTypeId();
//		
//		try{
//			// create a data type object
//			Map<String, Object> mapObj = DataTypeLocalServiceUtil.modifyDataTypeObject(typeId,
//					reqDataTypeName, 
//					reqDataTypeVersion, 
//					reqDataTypeDescription, 
//					sampleFilePath, 
//					Integer.parseInt(reqDataTypeStatus), 
//					reqDataTypeStructure, 
//					reqDataTypeOwnerId, 
//					new Boolean(reqDataTypeFavorite),
//					sc);
//			if(mapObj == null) success= false;
//			else success = true;
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		if(!success){
//			System.err.println("failed to create a DataType object");
//		}
		
//		DataType dtObj = DataTypeLocalServiceUtil.findDataTypeObject(reqDataTypeName, reqDataTypeVersion);
//		if(dtObj == null) throw new SystemException();
//		long typeId = dtObj.getTypeId();
//		try {
//			DataTypeLocalServiceUtil.removeDataTypeObject(typeId);
//		} catch (NoSuchDataTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		/**
//		 * A sample file path
//		 */
//		String sampleFileName = "";
//		String sampleFilePath = "";
//		File uploadedSampleFile = uploadRequest.getFile("reqDataTypeSamplePathVal");
//		if (uploadedSampleFile == null) {
//			System.out.println("File does not exist");
//			sampleFileName = null;
//		}else{
//			System.out.println("src file size bytes:" + uploadedSampleFile.length());
//			System.out.println("src file name:" + uploadedSampleFile.getName());
//			System.out.println("src file path:" + uploadedSampleFile.getAbsolutePath());
//			sampleFileName = uploadedSampleFile.getName();
//			sampleFilePath = uploadedSampleFile.getAbsolutePath();
////			res = ScienceAppManagerServiceUtil.installLibFile(libFileName, uploadedlibFile);
//		}
		
//		List<DataType> clList = DataTypeServiceUtil.retrieveDataType(reqDataTypeName+reqDataVersionName);
//		actionRequest.setAttribute("foundLibList", clList);
//		//String paramCommonLibListStr = toPlainString(clList);
		
		// get a simulation data type object to check if that's created well
//		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(reqDataTypeName, reqDataTypeVersion);
//		if(dt != null) {
//			actionRequest.setAttribute("reqDataTypeNameVal", dt.getName());
//			actionRequest.setAttribute("reqDataTypeVersionVal", dt.getVersion());
//			actionRequest.setAttribute("reqDataTypeFileSamplePathVal", dt.getSamplePath());
//		}else{
//			actionRequest.setAttribute("reqDataTypeNameVal", "no name");
//			actionRequest.setAttribute("reqDataTypeVersionVal", "no version");
//			actionRequest.setAttribute("reqDataTypeFileSamplePathVal", "no path");
//		}
		
//		actionRequest.setAttribute("reqSimUuid", simulationUuid);
//		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.PROV_CREATE_RES;
//		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Search a PROV doc
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void searchPROVAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		
		String appName = ParamUtil.getString(actionRequest, "reqAppName");
System.out.println("requested science app name: " + appName);

		String appVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
System.out.println("requested science app version: " + appVersion);

		String startYear = ParamUtil.getString(actionRequest, "reqStartYear");
		String startMon = ParamUtil.getString(actionRequest, "reqStartMon");
		String startDay = ParamUtil.getString(actionRequest, "reqStartDay");
		
		String endYear = ParamUtil.getString(actionRequest, "reqEndYear");
		String endMon = ParamUtil.getString(actionRequest, "reqEndMon");
		String endDay = ParamUtil.getString(actionRequest, "reqEndDay");
		
		/****
		 * Simulation submit start date
		 */
		String startDateTime = startYear+Constants.DATE_STR_CONNECTOR+startMon+Constants.DATE_STR_CONNECTOR+startDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
		/****
		 * Simulation submit end date
		 */
		String endDateTime = endYear+Constants.DATE_STR_CONNECTOR+endMon+Constants.DATE_STR_CONNECTOR+endDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
		
		
		actionRequest.setAttribute("reqAppName", appName);
		actionRequest.setAttribute("reqAppVersion", appVersion);
		actionRequest.setAttribute("reqStartDateTime", startDateTime);
		actionRequest.setAttribute("reqEndDateTime", endDateTime);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.PROV_SEARCH_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Browse all simulation records
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void browseSimulationsAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
		String appName = ParamUtil.getString(actionRequest, "reqAppName");
		String appVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
		String searchStartDate = ParamUtil.getString(actionRequest, "reqSearchStartDate");
		String topK = ParamUtil.getString(actionRequest, "reqTopK");
		
		Long subjectId = null;
		// Get all simulation records
		// job elapsed time, job queuing time, simulation title, 
		Vector<ProvData> resVec = null;
		try {
System.out.println(appName+"|"+appVersion+"|"+searchStartDate+"|"+topK);
			resVec = ProvDataRetriever.retrieveProvenance(subjectId, appName, appVersion, searchStartDate, topK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionRequest.setAttribute("reqResVec", (Object)resVec);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.BROWSE_SIMS_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Store all provenance records 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void storeProvRecAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		
		String appName = ParamUtil.getString(actionRequest, "reqAppName");
//System.out.println("requested science app name: " + appName);
		String appVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
//System.out.println("requested science app version: " + appVersion);

//		String simUuid = ParamUtil.getString(actionRequest, "reqSimUuid");
////System.out.println("requested sim Uuid: " + simUuid);
//		String jobUuid = ParamUtil.getString(actionRequest, "reqJobUuid");
////System.out.println("requested job Uuid: " + jobUuid);
//		String jobData = ParamUtil.getString(actionRequest, "reqJobData");
////System.out.println("requested job Data: " + jobData);
//		String jobElapsedTime = ParamUtil.getString(actionRequest, "reqJobElapsedTime");
////System.out.println("requested job Elapsed Time: " + jobElapsedTime);
//		String reqJobStatus = ParamUtil.getString(actionRequest, "reqJobStatus");
//System.out.println("requested job Status: " + reqJobStatus);
//		String startYear = ParamUtil.getString(actionRequest, "reqStartYear");
//		String startMon = ParamUtil.getString(actionRequest, "reqStartMon");
//		String startDay = ParamUtil.getString(actionRequest, "reqStartDay");
//		
//		String endYear = ParamUtil.getString(actionRequest, "reqEndYear");
//		String endMon = ParamUtil.getString(actionRequest, "reqEndMon");
//		String endDay = ParamUtil.getString(actionRequest, "reqEndDay");
//		/****
//		 * Simulation submit start date
//		 */
//		String startDateTime = startYear+Constants.DATE_STR_CONNECTOR+startMon+Constants.DATE_STR_CONNECTOR+startDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
//		/****
//		 * Simulation submit end date
//		 */
//		String endDateTime = endYear+Constants.DATE_STR_CONNECTOR+endMon+Constants.DATE_STR_CONNECTOR+endDay+" "+Constants.DATE_DEFAULT_TIMESTAMP;
		
		
		actionRequest.setAttribute("reqAppName", appName);
		actionRequest.setAttribute("reqAppVersion", appVersion);
		
//		actionRequest.setAttribute("reqSimUuid", simUuid);
//		actionRequest.setAttribute("reqJobUuid", jobUuid);
//		actionRequest.setAttribute("reqJobData", jobData);
//		actionRequest.setAttribute("reqJobElapsedTime", jobElapsedTime);
//		actionRequest.setAttribute("reqJobStatus", reqJobStatus);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.STORE_ALL_PROV_REC_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Store a single provenance record
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void storeSingleProvRecAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
				
		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuid");
		String userName = ParamUtil.getString(actionRequest, "reqUserName");
		Long subjectId = ParamUtil.getLong(actionRequest, "reqAppId");
		String scienceAppName = ParamUtil.getString(actionRequest, "reqAppName");
		String scienceAppVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
		String simTitle = ParamUtil.getString(actionRequest, "reqSimTitle");
		String simCreateDt = ParamUtil.getString(actionRequest, "reqSimCreateDt");
		String jobUuid = ParamUtil.getString(actionRequest, "reqJobUuid");
		String jobInputFileDir = ParamUtil.getString(actionRequest, "reqJobInputFileDir");
		String jobData = ParamUtil.getString(actionRequest, "reqJobData");
		String jobStartDt = ParamUtil.getString(actionRequest, "reqJobStartDt");
		String jobEndDt = ParamUtil.getString(actionRequest, "reqJobEndDt");
		String statusCode = ParamUtil.getString(actionRequest, "reqJobStatus");
		
		actionRequest.setAttribute("reqSimUuid", simulationUuid);
		actionRequest.setAttribute("reqUserName", userName);
		actionRequest.setAttribute("reqAppId", subjectId);
		actionRequest.setAttribute("reqAppName", scienceAppName);
		actionRequest.setAttribute("reqAppVersion", scienceAppVersion);
		actionRequest.setAttribute("reqSimTitle", simTitle);
		actionRequest.setAttribute("reqSimCreateDt", simCreateDt);
		actionRequest.setAttribute("reqJobUuid", jobUuid);
		actionRequest.setAttribute("jobInputFileDir", jobInputFileDir);
		actionRequest.setAttribute("reqJobData", jobData);
		actionRequest.setAttribute("reqJobStartDt", jobStartDt);
		actionRequest.setAttribute("reqJobEndDt", jobEndDt);
		actionRequest.setAttribute("reqJobStatus", statusCode);
		
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.STORE_SINGLE_PROV_REC_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	
	
	/****
	 * Retrieve the output directory matching a given science app id and job 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void retrieveOutputAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		String appName = ParamUtil.getString(actionRequest, "reqAppName");
//System.out.println("requested science app name: " + appName);
		String appVersion = ParamUtil.getString(actionRequest, "reqAppVersion");
//System.out.println("requested science app version: " + appVersion);
		String jobData = ParamUtil.getString(actionRequest, "reqJobData");
		
		Long subjectId = null;
		String outputDirPath = null;
		if(appName != null && appVersion != null && jobData != null){
			try{
				subjectId = ProvDataCollector.getScienceAppId(appName, appVersion);
				// open IceSheet DB
				if(IceSheetDBConnector.open(false)){
					System.out.println("IceSheet Database successfully opened!");
				}
				outputDirPath = ProvDataMatcher.lookUpExistingProvenanceMain(subjectId, jobData);
				IceSheetDBConnector.close();
				System.out.println("IceSheet Database successfully closed!");	
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}	
		
		actionRequest.setAttribute("reqAppName", appName);
		actionRequest.setAttribute("reqAppVersion", appVersion);
		actionRequest.setAttribute("reqOutputDirPath", outputDirPath);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.RETRIEVE_OUTPUT_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Browse files under a given directory
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void browseFilesAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		String outputDirPath = ParamUtil.getString(actionRequest, "reqOutputDir");
		
		actionRequest.setAttribute("reqOutputDir", outputDirPath);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.BROWSE_FILES_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Read file
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void readFileAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
//		String simulationUuid = ParamUtil.getString(actionRequest, "reqSimUuidVal");
//System.out.println("requested simulation uuid: " + simulationUuid);
		String outputFilePath = ParamUtil.getString(actionRequest, "reqOutputFilePath");
		
		actionRequest.setAttribute("reqOutputFilePath", outputFilePath);
		String jspFileName = Constants.ICESHEET_MAIN_AXIS+Constants.READ_OUTPUT_FILE_RES;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
}
