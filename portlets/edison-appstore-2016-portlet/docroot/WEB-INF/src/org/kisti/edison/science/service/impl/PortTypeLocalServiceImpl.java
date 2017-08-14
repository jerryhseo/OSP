/**
 * Copyright (c) 2015-present KISTI. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.science.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.PortTypeAnalyzerLink;
import org.kisti.edison.science.model.PortTypeEditorLink;
import org.kisti.edison.science.model.PortTypeInputdeckForm;
import org.kisti.edison.science.service.PortTypeAnalyzerLinkLocalServiceUtil;
import org.kisti.edison.science.service.PortTypeEditorLinkLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil;
import org.kisti.edison.science.service.base.PortTypeLocalServiceBaseImpl;
import org.kisti.edison.science.service.persistence.PortTypeInputdeckFormUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeAnalyzer;
import com.kisti.osp.icecap.model.DataTypeEditor;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceWrapper;
import com.kisti.osp.icecap.service.DataTypeEditorServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK;
import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerUtil;
import com.kisti.osp.icecap.service.persistence.DataTypeEditorPK;
import com.kisti.osp.icecap.service.persistence.DataTypeEditorUtil;
import com.kisti.osp.icecap.service.persistence.DataTypePersistence;
import com.kisti.osp.icecap.service.persistence.DataTypeStructureUtil;
import com.kisti.osp.icecap.service.persistence.DataTypeUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

// TODO: Auto-generated Javadoc
/**
 * The implementation of the port type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.science.platform.service.PortTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 *@author EDISON
 * @see org.kisti.edison.science.service.base.PortTypeLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.PortTypeLocalServiceUtil
 */
public class PortTypeLocalServiceImpl extends PortTypeLocalServiceBaseImpl {
	
	private static Log log = LogFactory.getLog(PortTypeLocalServiceImpl.class);

	/**
	 * Create a port type of a science app. If provided new port type name already exist, returns null instance.
	 * Created new port type is not saved in database physically.
	 * 
	 * @author EDISON
	 * @param String portTypeName
	 * @param ServiceContext sc
	 * @throws SystemException
	 * @return PortType instance
	 */
	public PortType createPortType(String portTypeName, ServiceContext sc ) throws SystemException{
		if(this.existPortType(portTypeName))	return null;
		
		long portTypeId = super.counterLocalService.increment(PortType.class.getName());
		PortType portType = super.portTypePersistence.create(portTypeId);

		portType.setName(portTypeName);
		portType.setCreateDate(sc.getCreateDate());
		portType.setUserId(sc.getUserId());
		
		return portType;
	}
	
	/**
	 * @param portTypeName
	 * @return
	 * @throws SystemException
	 *
	 * @see com.kisti.science.platform.service.PortTypeLocalService#existPortType(java.lang.String)
	 */
	public boolean existPortType(String portTypeName) throws SystemException{
		int count = super.portTypePersistence.countByName(portTypeName);
		if(count > 0)	return true;
		else		return false;
	}
	
	public void setPortTypeInputdeckForm(long portTypeId, String inputdeckForm) throws PortalException, SystemException{
		super.portTypeInputdeckFormLocalService.create(portTypeId, inputdeckForm);
	}
	
	public PortType deletePortType(long portTypeId) throws SystemException, PortalException{
		PortType portType = super.fetchPortType(portTypeId);
		return this.deletePortType(portType);
	}
	
	public PortType deletePortType(PortType portType) throws SystemException, PortalException{
		this.cleanIntegratedData(portType.getPortTypeId());
		return super.deletePortType(portType);
	}

	
	/**
	 * For reserving integration
	 *
	 * @param portTypeId the port type id
	 * @throws SystemException the system exception
	 * @throws PortalException 
	 */
	protected void cleanIntegratedData(long portTypeId) throws SystemException, PortalException{
		super.portTypeInputdeckFormLocalService.deletePortTypeInputdeckForm(portTypeId);
	}
	
	/**********************************   ADD GPLUS SERVICE   
	 * @throws SystemException ******************************/
	public Map<String,Integer> migrationPortType(long companyId) throws SystemException{
		List<PortType> portTypeListAll = portTypePersistence.findAll();
		int errorPortTypeCnt = 0;
		int mirPortTypeCnt = 0;
		int deletePortTypeCnt = 0;
		
		List<Long> errorPortTypeList = new ArrayList<Long>();
		
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		long portTypeId = 0;
			try {
				for(PortType portType : portTypeListAll){
					portTypeId = portType.getPortTypeId();
					System.out.println(portType.getPortTypeId());
					
					boolean usePortTypeFormApp = false;
					//APP 사용 여부 확인
					long inputCnt = ScienceAppInputPortsLocalServiceUtil.getInputPortsCountByPotyTypeId(portType.getPortTypeId());
					if(inputCnt!=0){usePortTypeFormApp=true;}
					
					if(!usePortTypeFormApp){
						long outputCnt = ScienceAppOutputPortsLocalServiceUtil.getOutPortsCountByPotyTypeId(portType.getPortTypeId());
						if(outputCnt!=0){usePortTypeFormApp=true;}
					}
				
					if(usePortTypeFormApp){
						//마이그레이션 대상
						
						//add DataType
						DataType dataType = DataTypeLocalServiceUtil.createDataType(portType.getPortTypeId());
						dataType.setCompanyId(portType.getCompanyId());
						dataType.setUserId(portType.getUserId());;
						dataType.setCreateDate(portType.getCreateDate());
						dataType.setName(portType.getName());
						dataType.setVersion("1.0.0");
						dataType.setIsFavorite(false);
						dataType.setOwnerId(portType.getUserId());
						dataType.setModifiedDate(new Date());
						
						if(GetterUtil.getString(portType.getSampleFilePath(),"0")!="0"){
							try{
								long groupId = DLFileEntryLocalServiceUtil.getDLFileEntry(GetterUtil.getLong(portType.getSampleFilePath())).getGroupId();
								dataType.setGroupId(groupId);
								dataType.setSamplePath(portType.getSampleFilePath());
							}catch(NoSuchFileEntryException e){
								dataType.setSamplePath("0");
							}
							
							
							
						}
						
						DataTypeLocalServiceUtil.updateDataType(dataType);
						
						//add DataTypeEditor
						List<PortTypeEditorLink> portTypeEditorList = portTypeEditorLinkPersistence.findByPortTypeId(portType.getPortTypeId());
						for(PortTypeEditorLink portTypeEditorLink : portTypeEditorList){
							boolean isDefaule = false;
							if(portTypeEditorLink.getPortTypeEditorLinkId()==portType.getDefaultEditorId()){
								isDefaule = true;
							}
							
							DataTypeEditorLocalServiceUtil.createDataTypeEditorObject(portType.getPortTypeId(), isDefaule, portTypeEditorLink.getEditorId());
						}
						
						//add DataTypeAnalyzer
						List<PortTypeAnalyzerLink> portTypeAnalyzerList = portTypeAnalyzerLinkPersistence.findByPortTypeId(companyId, portType.getPortTypeId());
						for(PortTypeAnalyzerLink portTypeAnalyzerLink : portTypeAnalyzerList){
							boolean isDefaule = false;
							if(portTypeAnalyzerLink.getPortTypeAnalyzerLinkId()==portType.getDefaultAnalyzerId()){
								isDefaule = true;
							}
							DataTypeAnalyzerLocalServiceUtil.createDataTypeAnalyzerObject(portType.getPortTypeId(), isDefaule, portTypeAnalyzerLink.getAnalyzerId());
						}
						
						//add DataTypeStructure
						PortTypeInputdeckForm portTypeInputdeckForm = portTypeInputdeckFormPersistence.fetchByPrimaryKey(portType.getPortTypeId());
						DynamicQuery query = DynamicQueryFactoryUtil.forClass(PortTypeInputdeckForm.class)
								.add(PropertyFactoryUtil.forName("portTypeId").eq(portType.getPortTypeId()));
						if(PortTypeInputdeckFormUtil.countWithDynamicQuery(query)>0){
							DataTypeStructure dataTypeStructure = DataTypeStructureLocalServiceUtil.createDataTypeStructure(portType.getPortTypeId()); 
							dataTypeStructure.setStructure(portTypeInputdeckForm.getInputdeckForm());
							DataTypeStructureLocalServiceUtil.updateDataTypeStructure(dataTypeStructure);
						}
						
						mirPortTypeCnt++;
					}else{
						//삭제 대상
						//EDAPP_PortTypeEditorLink
						List<PortTypeEditorLink> portTypeEditorList = portTypeEditorLinkPersistence.findByPortTypeId(portType.getPortTypeId());
						for(PortTypeEditorLink portTypeEditorLink : portTypeEditorList){
							PortTypeEditorLinkLocalServiceUtil.deletePortTypeEditorLink(portTypeEditorLink.getPortTypeEditorLinkId());
						}
						
						//EDAPP_PortTypeAnalyzerLink
						List<PortTypeAnalyzerLink> portTypeAnalyzerList = portTypeAnalyzerLinkPersistence.findByPortTypeId(companyId, portType.getPortTypeId());
						for(PortTypeAnalyzerLink portTypeAnalyzerLink : portTypeAnalyzerList){
							PortTypeAnalyzerLinkLocalServiceUtil.deletePortTypeAnalyzerLink(portTypeAnalyzerLink.getPortTypeAnalyzerLinkId());
						}
						
						//EDAPP_PortTypeInputdeckForm
						DynamicQuery query = DynamicQueryFactoryUtil.forClass(PortTypeInputdeckForm.class)
								.add(PropertyFactoryUtil.forName("portTypeId").eq(portType.getPortTypeId()));
						if(PortTypeInputdeckFormUtil.countWithDynamicQuery(query)>0){
							portTypeInputdeckFormPersistence.remove(portType.getPortTypeId());
						}
						
						
						//EDAPP_PortType
						portTypePersistence.remove(portType.getPortTypeId());
						if(!CustomUtil.strNull(portType.getSampleFilePath()).equals("")){
							//SAMPLE FILE DELETE
							EdisonFileUtil.deleteSingleEdisonFile(Long.parseLong(portType.getSampleFilePath()));
						}
						
						deletePortTypeCnt++;
					}
				}
			} catch (Exception e) {
				errorPortTypeCnt++;
				errorPortTypeList.add(portTypeId);
				e.printStackTrace();
			} finally{
				resultMap.put("errorPortTypeCnt", errorPortTypeCnt);
				resultMap.put("mirPortTypeCnt", mirPortTypeCnt);
				resultMap.put("deletePortTypeCnt", deletePortTypeCnt);
				if(!errorPortTypeList.isEmpty()){
					System.out.println("==============================ERROR LIST====================================");
					for(long errorPortTypeId : errorPortTypeList){
						System.out.println(errorPortTypeId);
					}
					System.out.println("============================================================================");
				}
			}
		return resultMap;
		
	}
	
	public int countPortType(long companyGroupId,Locale locale, Map<String,Object> searchParam) throws PortalException, SystemException{
		Map<String,Object> search = settingPortTypeParameter(companyGroupId, locale, searchParam, 0, 0);
		return portTypeFinder.countPortType(search);
	}
	
	public List<Map<String, Object>> retrieveListPortType(long companyGroupId, Locale locale, Map<String,Object> searchParam,int begin, int end) throws PortalException, SystemException{
		Map<String,Object> search = settingPortTypeParameter(companyGroupId, locale, searchParam, begin, end);
		List<Object[]> portTypeList = portTypeFinder.retrieveListPortType(search);
				
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < portTypeList.size(); i++) {
			Object[] resultArray = portTypeList.get(i);
			PortType portType = (PortType) resultArray[0];
			int editorCnt = (Integer) resultArray[1];
			int analyzerCnt = (Integer) resultArray[2];
			int inputdeckCnt = (Integer) resultArray[3];
			
			Map<String, Object> resultRow = portType.getModelAttributes();
			if(!portType.getSampleFilePath().equals("0")){
				try{
					DLFileEntry sampleFile =  DLFileEntryLocalServiceUtil.getDLFileEntry(Long.valueOf(portType.getSampleFilePath()));
					resultRow.put("sampleRepositoryId", sampleFile.getRepositoryId());
					resultRow.put("sampleUuid", 		sampleFile.getUuid());
					resultRow.put("sampleTitle", 		sampleFile.getTitle());
				}catch(NoSuchFileEntryException e){
					log.error("error_portTypeId = " + portType.getPortTypeId());
					e.printStackTrace();
				}
			}
			
			resultRow.put("editorCnt", editorCnt);
			resultRow.put("analyzerCnt", analyzerCnt);
			resultRow.put("inputdeckCnt", inputdeckCnt);
			returnList.add(resultRow);
		}
		return returnList;
	}
	
	public void createPortType(ServiceContext sc, Map params) throws SystemException{
		long defaultEditorId = GetterUtil.getLong(params.get("defaultEditor"),0);
		long defaultAnalyzerId = GetterUtil.getLong(params.get("defaultAnalyzer"),0);
		String name = GetterUtil.getString(params.get("portTypeName"), "");
		String sampleFilePath = GetterUtil.getString(params.get("sampleFilePath"), "0");
		sampleFilePath = sampleFilePath.equals("")?"0":sampleFilePath;
		
		String targetLanguage = GetterUtil.getString(params.get("availableLanguages"), "").replace("{", "").replace("}", "");
		
		String portTypeEditorStr = GetterUtil.getString(params.get("editor"),"");
		String portTypeAnalyzerStr = GetterUtil.getString(params.get("analyzer"),"");
		
		
		//create PortTypeId
		long portTypeId = super.counterLocalService.increment(PortType.class.getName());
		
		
		//insert PortTypeEditorLink
		if(!portTypeEditorStr.equals("")){
			String[] portTypeEditorArray = portTypeEditorStr.split(",");
			for(String portTypeEditorId : portTypeEditorArray){
				long editorId = GetterUtil.getLong(portTypeEditorId);
				long portTypeEditorLinkId = super.counterLocalService.increment(PortTypeEditorLink.class.getName());
				PortTypeEditorLink portTypeEditorLink = portTypeEditorLinkLocalService.createPortTypeEditorLink(portTypeEditorLinkId);
				portTypeEditorLink.setCompanyId(sc.getCompanyId());
				portTypeEditorLink.setPortTypeId(portTypeId);
				portTypeEditorLink.setEditorId(editorId);
				portTypeEditorLinkLocalService.updatePortTypeEditorLink(portTypeEditorLink);
			}
		}
		
		//insert PortTypeAnalyzerLink
		if(!portTypeAnalyzerStr.equals("")){
			String[] portTypeAnalyzerArray = portTypeAnalyzerStr.split(",");
			for(String portTypeAnalyzerId : portTypeAnalyzerArray){
				long analyzerId = GetterUtil.getLong(portTypeAnalyzerId);
				long portTypeAnaluzerLinkId = super.counterLocalService.increment(PortTypeAnalyzerLink.class.getName());
				PortTypeAnalyzerLink portTypeAnalyzerLink =  portTypeAnalyzerLinkLocalService.createPortTypeAnalyzerLink(portTypeAnaluzerLinkId);
				portTypeAnalyzerLink.setCompanyId(sc.getCompanyId());
				portTypeAnalyzerLink.setPortTypeId(portTypeId);
				portTypeAnalyzerLink.setAnalyzerId(analyzerId);
				portTypeAnalyzerLinkLocalService.updatePortTypeAnalyzerLink(portTypeAnalyzerLink);
			}
		}
		
		//insert default PortTypeEditorLink
		long defaultPortTypeEditorLinkId = 0;
		if(defaultEditorId!=0){
			defaultPortTypeEditorLinkId = super.counterLocalService.increment(PortTypeEditorLink.class.getName());
			PortTypeEditorLink portTypeEditorLink = portTypeEditorLinkLocalService.createPortTypeEditorLink(defaultPortTypeEditorLinkId);
			portTypeEditorLink.setCompanyId(sc.getCompanyId());
			portTypeEditorLink.setPortTypeId(portTypeId);
			portTypeEditorLink.setEditorId(defaultEditorId);
			portTypeEditorLinkLocalService.updatePortTypeEditorLink(portTypeEditorLink);
		}
		//insert default PortTypeAnalyzerLink
		long defaultPortTypeAnaluzerLinkId = 0;
		if(defaultAnalyzerId!=0){
			defaultPortTypeAnaluzerLinkId = super.counterLocalService.increment(PortTypeAnalyzerLink.class.getName());
			PortTypeAnalyzerLink portTypeAnalyzerLink =  portTypeAnalyzerLinkLocalService.createPortTypeAnalyzerLink(defaultPortTypeAnaluzerLinkId);
			portTypeAnalyzerLink.setCompanyId(sc.getCompanyId());
			portTypeAnalyzerLink.setPortTypeId(portTypeId);
			portTypeAnalyzerLink.setAnalyzerId(defaultAnalyzerId);
			portTypeAnalyzerLinkLocalService.updatePortTypeAnalyzerLink(portTypeAnalyzerLink);
		}
		
		boolean inputDeckExist = GetterUtil.getBoolean(params.get("inputDeckExist"),false);
		if(inputDeckExist){
			//insert PortTypeInputdeckForm
			String inputdeckForm = GetterUtil.get(params.get("inputdeckFormJSON"), "");
			portTypeInputdeckFormLocalService.create(portTypeId, inputdeckForm);
		}
		
		//insert PortType
		PortType portType = portTypeLocalService.createPortType(portTypeId);
		portType.setCompanyId(sc.getCompanyId());
		portType.setCreateDate(new Date());
		portType.setDefaultEditorId(defaultPortTypeEditorLinkId);
		//defaultAnalyzerId
		portType.setDefaultAnalyzerId(defaultPortTypeAnaluzerLinkId);
		portType.setName(name);
		portType.setSampleFilePath(sampleFilePath);
		portType.setTargetLanguage(targetLanguage);
		portType.setUserId(sc.getUserId());
		portTypeLocalService.updatePortType(portType);
	}
	
	protected Map<String,Object> settingPortTypeParameter(long companyGroupId, Locale locale, Map<String,Object> searchParam, int begin, int end) throws PortalException, SystemException{
		String targetLanguage = LocaleUtil.toLanguageId(locale);
		searchParam.put("targetLanguage", targetLanguage);
		searchParam.put("companyId", GroupLocalServiceUtil.getGroup(companyGroupId).getCompanyId());
		
		//페이징
		if(end!=0){
			searchParam.put("begin", begin);
			searchParam.put("end", end);
		}
		
		return searchParam;
	}
	
	
}