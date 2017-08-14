/*
* Copyright (c) 2017, KISTI Board of Regents
* 
* See LICENSE at https://github.com/sp-edison/edison-2016.
* See README at https://github.com/sp-edison/edison-2016.
* See EDISON source code at https://github.com/sp-edison/edison-2016.
* See EDISON source code at https://github.com/sp-edison/edison-2016.
* This is an EDISON Provenance Data Manager's Logger. 
* 
* Author:
* Young-Kyoon Suh (yksuh@kisti.re.kr)
*/
package com.kisti.osp.icecap.portlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;

import com.kisti.osp.icecap.NoSuchDataTypeException;
import com.kisti.osp.icecap.model.DataCollection;
import com.kisti.osp.icecap.model.DataEntry;
import com.kisti.osp.icecap.model.DataEntryProvenance;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.portlet.Constants;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataCollectionServiceUtil;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryProvenanceLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/****
 * 
 */
/**
 * Portlet implementation class IceCapManagerPortlet
 */
public class IceCapManagerPortlet extends MVCPortlet {
	private static final Log _log = LogFactoryUtil.getLog(IceCapManagerPortlet.class.getName());
 
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
	
//	/****
//	 * Retrieve libraries matching library name keyword
//	 * @param actionRequest
//	 * @param actionResponse
//	 * @throws IOException
//	 * @throws PortletException
//	 */
//	public void addDataTypeSamplePathAction(
//            ActionRequest actionRequest, ActionResponse actionResponse)
//        throws IOException, PortletException {
//		UploadPortletRequest uploadPortletRequest =
//				PortalUtil.getUploadPortletRequest(actionRequest);
//		String sampleFileName = "";
//		String sampleFilePath = "";
//		File uploadedSampleFile = uploadPortletRequest.getFile("reqDataTypeFileSamplePath");
//		if(uploadedSampleFile == null){
//			System.out.println("File does not exist");
//			sampleFileName = null;
//			sampleFilePath = null;
//		}else{
//			System.out.println("File exists!");
//			sampleFileName = name;
//			System.out.println("file name: " + name);
//			sampleFilePath = uploadedSampleFile.getAbsolutePath();
//		}
//		/**
//		 * A sample file path
//		 */
//		actionRequest.setAttribute("reqDataTypeSampleFilePathVal", sampleFilePath);
//		String jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.TYPE_CREATION_RESULT_JSP;
//		actionResponse.setRenderParameter("jspPage", jspFileName);
//    } 
	
	/****
	 * Retrieve libraries matching library name keyword
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException 
	 */
	public void manageSimDataTypeAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		UploadPortletRequest uploadPortletRequest =
				PortalUtil.getUploadPortletRequest(actionRequest);
		
		String reqDataTypeName = ParamUtil.getString(uploadPortletRequest, "reqDataTypeNameVal");
System.out.println("action request - received name: " + reqDataTypeName);
		String reqDataTypeVersion = ParamUtil.getString(uploadPortletRequest, "reqDataTypeVersionVal");
System.out.println("action request - received version: " + reqDataTypeVersion);
		Map params = RequestUtil.getParameterMap(actionRequest);
		Map<Locale, String> reqDataTypeDescription = CustomUtil.getLocalizationNotSetLocaleMap(params, "reqDataTypeDescriptionVal");
	
		// get sample file path
		String sampleFilePath = "";
		File uploadedSampleFile = uploadPortletRequest.getFile("reqDataTypeFileSamplePath");
//		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
//		String name = ParamUtil.getString(uploadPortletRequest,"reqDataTypeFileSamplePath");
//		String name = ParamUtil.getString(uploadRequest,"name");
		if(uploadedSampleFile == null){
			System.out.println("File does not exist");
			sampleFilePath = null;
		}else{
			System.out.println("File exists!");
			sampleFilePath = IceCapManagerServiceUtil.saveDataTypeSampleFile(reqDataTypeName, 
					reqDataTypeVersion, 
																			 uploadedSampleFile.getName(), 
																			 uploadedSampleFile.getAbsolutePath());
		}
		// get status
		String reqDataTypeStatus = ParamUtil.getString(uploadPortletRequest, "reqDataTypeStatusVal");
		// get type structure
		String reqDataTypeStructure = ParamUtil.getString(uploadPortletRequest, "reqDataTypeStructureVal");
//		// get user id
//		String reqDataTypeUserId = ParamUtil.getString(uploadPortletRequest, "reqDataTypeUserIdVal");
//		// get company id
//		String reqDataTypeCompanyId = ParamUtil.getString(uploadPortletRequest, "reqDataTypeCompanyIdVal");
//		// get create date
//		String reqDataTypeCreateDate = ParamUtil.getString(uploadPortletRequest, "reqCreateTypeVal");
//		// get modified date
//		String reqDataTypeModifiedDate = ParamUtil.getString(uploadPortletRequest, "reqModifiedTypeVal");

		// get owner id
		Long reqDataTypeOwnerId = ParamUtil.getLong(uploadPortletRequest, "reqDataTypeOwnerIdVal");
		// get favorite
		boolean reqDataTypeFavorite = ParamUtil.getBoolean(uploadPortletRequest, "reqDataTypeFavoriteVal");
		
		// create service context 
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(DataType.class.getName(), actionRequest);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean success = false;
		try{
			// create a data type object
			success = DataTypeLocalServiceUtil.createDataTypeObject(reqDataTypeName, 
													 reqDataTypeVersion,
													 reqDataTypeDescription,
													 sampleFilePath,
													 reqDataTypeStatus,
													 reqDataTypeStructure,
//													 reqDataTypeUserId,
//													 reqDataTypeCompanyId,
//													 reqDataTypeCreateDate,
//													 reqDataTypeModifiedDate,
													 reqDataTypeOwnerId,
													 reqDataTypeFavorite,
													 sc
													 );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(!success){
			System.err.println("failed to create a DataType object");
		}
		
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
		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(reqDataTypeName, reqDataTypeVersion);
		if(dt != null) {
			actionRequest.setAttribute("reqDataTypeNameVal", dt.getName());
			actionRequest.setAttribute("reqDataTypeVersionVal", dt.getVersion());
			actionRequest.setAttribute("reqDataTypeFileSamplePathVal", dt.getSamplePath());
		}else{
			actionRequest.setAttribute("reqDataTypeNameVal", "no name");
			actionRequest.setAttribute("reqDataTypeVersionVal", "no version");
			actionRequest.setAttribute("reqDataTypeFileSamplePathVal", "no path");
		}
		
//		List<DataType> dtList = null;
//		try {
//			dtList = DataTypeLocalServiceUtil.findDataTypeObjectByOwner(Long.parseLong("0"), Long.parseLong("13907"));
//		} catch (NoSuchDataTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(dtList != null){
//			System.out.println("size: " + dtList.size());
//		}
		
		
		String jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.TYPE_CREATION_RESULT_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
//		super.processAction(actionRequest, actionResponse);
    } 
	
	/****
	 * Retrieve libraries matching library name keyword
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void manageSimDataCollectionAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		
		String reqDataTypeId = (ParamUtil.getString(actionRequest, "reqDataTypeIdVal"));
System.out.println("action request - received typeid: " + reqDataTypeId);
		String reqDataCollectionName = ParamUtil.getString(actionRequest, "reqDataCollectionNameVal");
System.out.println("action request - received name: " + reqDataCollectionName);
		String reqDataCollectionVersion = ParamUtil.getString(actionRequest, "reqDataCollectionVersionVal");
System.out.println("action request - received version: " + reqDataCollectionVersion);
		String reqDataCollectionTitle = ParamUtil.getString(actionRequest, "reqDataCollectionTitleVal");
System.out.println("action request - received title: " + reqDataCollectionTitle);
		
		//get status
		String reqDataCollectionStatus = ParamUtil.getString(actionRequest, "reqDataCollectionStatusVal");
		//get description
		String reqDataCollectionDescription = ParamUtil.getString(actionRequest, "reqDataCollectionDescriptionVal");
		//get access method
		String reqDataCollectionAccessMethod = ParamUtil.getString(actionRequest, "reqDataCollectionAccessMethodVal");
		//get target language
		String reqDataCollectionTargetLanguage = ParamUtil.getString(actionRequest, "reqTargetLangVal");
System.out.println("action request - received target language: " + reqDataCollectionTargetLanguage);

		//create service context 
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(DataType.class.getName(), actionRequest);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean success = false;
		try{
			long[] categoryIds = {-1,0};
			// create a data type object
//			success = DataCollectionLocalServiceUtil.createDataCollectionObject(Long.parseLong(reqDataTypeId),
//																	reqDataCollectionName, 
//																	 reqDataCollectionVersion,
//																	 /*reqDataCollectionTitle*/null,
//																	 /*reqDataCollectionDescription*/null,
//																	 reqDataCollectionAccessMethod,
//																	 reqDataCollectionStatus,
//																	 reqDataCollectionTargetLanguage,
//																	 null,// null
//																	 categoryIds,// null
//																	 -1,//null
//																	 sc
//																	 );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(!success){
			System.err.println("failed to create a DataType object");
		}

//		List<CommonLibrary> clList = CommonLibraryServiceUtil.retrieveLibrary(reqLibName);
//		actionRequest.setAttribute("foundLibList", clList);
//		//String paramCommonLibListStr = toPlainString(clList);
		actionRequest.setAttribute("reqDataCollectionNameVal", reqDataCollectionName);
		actionRequest.setAttribute("reqDataCollectionVersionVal", reqDataCollectionVersion);
		actionRequest.setAttribute("reqDataCollectionTitleVal", reqDataCollectionTitle);
		
		String jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.COLLECTION_CREATION_RESULT_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
//		super.processAction(actionRequest, actionResponse);
    } 
	
	/****
	 * Search a matching simulation data 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException
	 */
	public void searchSimulationDataAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		
		String reqSciApp = ParamUtil.getString(actionRequest, "reqScienceAppIdVal");
System.out.println("action request - received science app Id: " + reqSciApp);
		String reqTerm = ParamUtil.getString(actionRequest, "reqSearchTermVal");
System.out.println("action request - received keywords: " + reqTerm);

		Long reqSciAppId = null;
		if(reqSciApp != null){
			reqSciAppId = Long.parseLong(reqSciApp);
		}else{
			throw new SystemException("no science app id given");
		}
		 
		List<DataEntry> dataEntryList = DataEntryLocalServiceUtil.findDataEntryPerApp(reqSciAppId);
		if(dataEntryList == null)
			throw new SystemException("no data entry list returned");
		
		List<String> resMatchingInputDataList = new ArrayList<String>();
		for(int i=0;i<dataEntryList.size();i++){
			DataEntry dataEntry = dataEntryList.get(i);
//			String inputDataStr = dataEntry.getJobData();
			
			DataEntryProvenance dataEntryProvenance = null;
			try {
				dataEntryProvenance = DataEntryProvenanceLocalServiceUtil.getDataEntryProvenance(dataEntry.getEntryId());
			} catch (PortalException e) {
				throw new SystemException("no dataEntryProvenance returned");
			}
			String inputDataStr = dataEntryProvenance.getInputData();
			
			if(inputDataStr == null) continue;
			if(inputDataStr.contains(reqTerm)){
				resMatchingInputDataList.add(inputDataStr);
			}
		}
		
		String jspFileName = "";
//		if(reqActionType == Constants.COLLECTION_CREATION){
//			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.COLLECTION_MAIN_JSP;
//		}else if(reqActionType == Constants.TYPE_ANAYLYZER_ADDITION){
//			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ANALYZER_MAIN_JSP;
//		}else if(reqActionType == Constants.TYPE_EDITOR_ADDITION){
//			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.EDITOR_MAIN_JSP;
//		}
//		else if(reqActionType == Constants.TYPE_STRUCTURE_ADDITION){
//			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.EDITOR_MAIN_JSP;
//		}else if(reqActionType == Constants.TYPE_STRUCTURE_ADDITION){
			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.SEARCH_SIM_DATA_RES_JSP;
//		}
		actionRequest.setAttribute("reqSciAppId", reqSciAppId);
		actionRequest.setAttribute("foundInputDataList", resMatchingInputDataList);
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	
	/****
	 * Find a matching data type object
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException
	 */
	public void findSimDataTypeAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException {
		String reqDTName = ParamUtil.getString(actionRequest, "reqDataTypeNameVal");
System.out.println("action request - received type name: " + reqDTName);
		String reqDTVersion = ParamUtil.getString(actionRequest, "reqDataTypeVersionVal");
System.out.println("action request - received type version: " + reqDTVersion);
		Long reqActionType = ParamUtil.getLong(actionRequest, "reqAssociatedActionVal");
System.out.println("action request - received action type: " + reqActionType);

		Long dataTypeId = null;
		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(reqDTName, reqDTVersion);
		if(dt != null){
			dataTypeId = new Long(dt.getTypeId());
		}
		actionRequest.setAttribute("reqDataTypeIdVal", dataTypeId);
		
		String jspFileName = "";
		if(reqActionType == Constants.COLLECTION_CREATION){
			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.COLLECTION_MAIN_JSP;
		}else if(reqActionType == Constants.TYPE_ANAYLYZER_ADDITION){
			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ANALYZER_MAIN_JSP;
		}else if(reqActionType == Constants.TYPE_EDITOR_ADDITION){
			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.EDITOR_MAIN_JSP;
		}else if(reqActionType == Constants.ENTRY_CREATION){
			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ENTRY_JSP;
		}
//		else if(reqActionType == Constants.TYPE_STRUCTURE_ADDITION){
//			jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.EDITOR_MAIN_JSP;
//		}
		
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/*****
	 * Find data collection id 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public void findSimDataCollectionAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException, NoSuchDataTypeException {
//		String reqDTName = ParamUtil.getString(actionRequest, "reqDataTypeNameVal");
//System.out.println("action request - received type name: " + reqDTName);
//		String reqDTVersion = ParamUtil.getString(actionRequest, "reqDataTypeVersionVal");
//System.out.println("action request - received type version: " + reqDTVersion);
//
//		Long dataTypeId = null;
//		DataType dt = DataTypeServiceUtil;
//		if(dt != null){
//			dataTypeId = new Long(dt.getTypeId());
//		}
		String reqDataTypeId = ParamUtil.getString(actionRequest, "reqDataTypeIdVal");
		if(reqDataTypeId == null) throw new SystemException("no data type id received");
			
		Long dataTypeId = Long.parseLong(reqDataTypeId);
System.out.println("action request - received type id: " + reqDataTypeId);
		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeId);
		if(dt == null){
			throw new SystemException("no matching data type");
		}
		else{
			System.out.println("dataTypeID: " + reqDataTypeId);
		}
				
		String reqDCName = ParamUtil.getString(actionRequest, "reqDataCollectionNameVal");
System.out.println("action request - received collection name: " + reqDCName);
		String reqDCVersion = ParamUtil.getString(actionRequest, "reqDataCollectionVersionVal");
System.out.println("action request - received collection version: " + reqDCVersion);

		Long dataCollectionId = null;
//		DataCollection dc = DataCollectionLocalServiceUtil.findDataCollectionObject(dataTypeId, reqDCName, reqDCVersion);
//		if(dc != null){
//			dataCollectionId = new Long(dc.getCollectionId());
//		}
		Map<String, Object> dc = DataCollectionLocalServiceUtil.findDataCollectionObject(dataTypeId, reqDCName, reqDCVersion);
		if(dc != null){
			dataCollectionId = (Long)dc.get("collectionId");
		}
System.out.println("dataCollectionID: " + dataCollectionId);
		actionRequest.setAttribute("reqDataCollectionIdVal", dataCollectionId);
		String jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ENTRY_MAIN_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/*****
	 * Manage simulation data entry 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void manageSimDataEntryAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		
/*****************************************************************************************/
		/**
		 * Primary key 
		 */
		// collection id
		String reqCollectionId = ParamUtil.getString(actionRequest, "reqDataCollectionIdVal");
System.out.println("action request - received collection id: " + reqCollectionId);
		/**
		 * Unique property 
		 */
		// science app id
		String reqSciAppId = ParamUtil.getString(actionRequest, "reqScienceAppIdVal");
System.out.println("action request - received science app id: " + reqSciAppId);
		// input data
		String reqInputData = ParamUtil.getString(actionRequest, "reqInputDataVal");
System.out.println("action request - received inputData: " + reqInputData);
/*****************************************************************************************/

		// file path
		String reqPath = ParamUtil.getString(actionRequest, "reqPathVal");
System.out.println("action request - received path: " + reqPath);
		String reqProductionTime = ParamUtil.getString(actionRequest, "reqProductionTimeVal");
System.out.println("action request - received production time: " + reqProductionTime);
		String reqComments = ParamUtil.getString(actionRequest, "reqCommentsVal");
System.out.println("action request - received comments: " + reqComments);

		//create service context 
		ServiceContext sc = null;
		try {
			sc = ServiceContextFactory.getInstance(DataEntry.class.getName(), actionRequest);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		boolean success = false;
//		DataEntry de = null;
//		try{
//			// create a data type object
//			de = DataEntryLocalServiceUtil.createDataEntryObject(Long.parseLong(reqCollectionId),
//																Long.parseLong(reqSciAppId), 
//																 reqInputData,
//																 reqPath,
////																 reqComments,
//																 null,
//																 reqProductionTime,
//																 null,
//																 "",
//																 "",
//																 sc
//																 );
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		if(de == null){
//			System.err.println("failed to create a DataEntry object");
//		}
	

//		DataEntry de = DataEntryServiceUtil.findDataEntryObject(Long.parseLong(reqCollectionId),
//																reqSciAppId, 
//																reqInputData);
//		if(de != null){
//			dataEntryId = new Long(de.dataEntryId());
//		}
//		actionRequest.setAttribute("reqEntryIdVal", dataEntryId);
		actionRequest.setAttribute("reqDataCollectionIdVal", reqCollectionId);
		actionRequest.setAttribute("reqSciAppIdVal", reqSciAppId);
		actionRequest.setAttribute("reqInputDataVal", reqInputData);
		
		String jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ENTRY_CREATION_RESULT_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
//		super.processAction(actionRequest, actionResponse);
    } 
	
	/****
	 * Add Data Type Analyzer
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public void addDataTypeAnalyzerAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException, NoSuchDataTypeException {
		String reqDataTypeId = ParamUtil.getString(actionRequest, "reqDataTypeIdVal");
		if(reqDataTypeId == null) throw new SystemException("no data type id received");
			
		Long dataTypeId = Long.parseLong(reqDataTypeId);
System.out.println("action request - received type id: " + reqDataTypeId);
		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeId);
		if(dt == null){
			throw new SystemException("no matching data type");
		}
		else{
			System.out.println("dataTypeID: " + reqDataTypeId);
		}
//		String reqDTName = ParamUtil.getString(actionRequest, "reqDataTypeNameVal");
//System.out.println("action request - received type name: " + reqDTName);
//		String reqDTVersion = ParamUtil.getString(actionRequest, "reqDataTypeVersionVal");
//System.out.println("action request - received type version: " + reqDTVersion);
		String reqAnalyzerId = ParamUtil.getString(actionRequest, "reqDataTypeAnalyzerIdVal");
		if(reqAnalyzerId == null) throw new PortletException("no analyzer id given");
		
System.out.println("action request - received analyzer Id: " + reqAnalyzerId);

//		// Find a matching data type object
//		DataType existingDataTypeObj = DataTypeServiceUtil.findDataTypeObject(dataTypeId);
////		if(dt != null){
////			dataTypeId = new Long(dt.getTypeId());
////		}
//		if(existingDataTypeObj == null) {
//			actionRequest.setAttribute("reqErrMsg", "Cannot find a matching data type object");
//		}
		boolean isDefaultVal = ParamUtil.getBoolean(actionRequest, "reqIsDefaultVal");
		boolean success = DataTypeAnalyzerLocalServiceUtil.createDataTypeAnalyzerObject(dataTypeId, isDefaultVal, new Long(reqAnalyzerId));
		if(success){
			System.out.println("Analyzer creation successful!");
			actionRequest.setAttribute("reqDataTypeAnalyzerIdVal", reqAnalyzerId);
		}else{
			System.err.println("Analyzer creation failed.");
			actionRequest.setAttribute("reqDataTypeAnalyzerIdVal", null);
		}
		
		String jspFileName = "";
		jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.ANALYZER_CREATION_RESULT_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Add Data Type Editor
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws SystemException
	 * @throws NoSuchDataTypeException 
	 */
	public void addDataTypeEditorAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException, SystemException, NoSuchDataTypeException {
		String reqDataTypeId = ParamUtil.getString(actionRequest, "reqDataTypeIdVal");
		if(reqDataTypeId == null) throw new SystemException("no data type id received");
			
		Long dataTypeId = Long.parseLong(reqDataTypeId);
System.out.println("action request - received type id: " + reqDataTypeId);
		DataType dt = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeId);
		if(dt == null){
			throw new SystemException("no matching data type");
		}
		else{
			System.out.println("dataTypeID: " + reqDataTypeId);
		}
//		String reqDTName = ParamUtil.getString(actionRequest, "reqDataTypeNameVal");
//System.out.println("action request - received type name: " + reqDTName);
//		String reqDTVersion = ParamUtil.getString(actionRequest, "reqDataTypeVersionVal");
//System.out.println("action request - received type version: " + reqDTVersion);
		String reqEditorId = ParamUtil.getString(actionRequest, "reqDataTypeEditorIdVal");
System.out.println("action request - received editor Id: " + reqEditorId);
		boolean isDefaultVal = ParamUtil.getBoolean(actionRequest, "reqIsDefaultVal");
	
//		// Find a matching data type object
//		DataType existingDataTypeObj = DataTypeServiceUtil.findDataTypeObject(dataTypeId);
////		if(dt != null){
////			dataTypeId = new Long(dt.getTypeId());
////		}
//		actionRequest.setAttribute("reqDataTypeIdVal", dataTypeId);
//		if(existingDataTypeObj == null) {
//			actionRequest.setAttribute("reqErrMsg", "Cannot find a matching data type object");
//		}
		
		boolean success = DataTypeEditorLocalServiceUtil.createDataTypeEditorObject(dataTypeId, isDefaultVal, Long.parseLong(reqEditorId));
		if(success){
			System.out.println("Editor creation successful!");
			actionRequest.setAttribute("reqDataTypeEditorIdVal", reqEditorId);
		}else{
			System.err.println("Editor creation failed.");
			actionRequest.setAttribute("reqDataTypeEditorIdVal", null);
		}


		String jspFileName = "";
		jspFileName = Constants.ICECAP_MAIN_AXIS+Constants.EDITOR_CREATION_RESULT_JSP;
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
}
