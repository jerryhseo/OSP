/**
 * Copyright (c) 2016-EDISON, KISTI. All rights reserved.
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.base.ScienceAppOutputPortsLocalServiceBaseImpl;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * The implementation of the science app output ports local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.science.service.ScienceAppOutputPortsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppOutputPortsLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppOutputPortsLocalServiceUtil
 */
public class ScienceAppOutputPortsLocalServiceImpl
	extends ScienceAppOutputPortsLocalServiceBaseImpl {
	public ScienceAppOutputPorts create(long scienceAppId, String outputPorts) throws SystemException{
		ScienceAppOutputPorts ports = null;
		try {
			ports = super.getScienceAppOutputPorts(scienceAppId);
		} catch (PortalException e) {
			ports = super.createScienceAppOutputPorts(scienceAppId);
		} catch (SystemException e) {
			throw e;
		}

		ports.setOutputPorts(outputPorts);
		super.addScienceAppOutputPorts(ports);
		
		return ports;

	}
	
	public String getOutputPortsJsonString(long scienceAppId) throws SystemException{
		ScienceAppOutputPorts ports = super.fetchScienceAppOutputPorts(scienceAppId);
		if(ports ==null){return "";}else{return ports.getOutputPorts();}
	}
	
	public void removeAllOutputPorts() throws SystemException{
		super.scienceAppOutputPortsPersistence.removeAll();
	}
	
	/**********************************   ADD GPLUS SERVICE 	 ******************************/
	public long getScienceAppOutputPortsesCount(long scienceAppId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppOutputPorts.class)
							.add(PropertyFactoryUtil.forName("scienceAppId").eq(scienceAppId));
				
		return scienceAppOutputPortsPersistence.countWithDynamicQuery(query);
	}
	
	public long getOutPortsCountByPotyTypeId(long portTypeId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppOutputPorts.class)
				 .add(RestrictionsFactoryUtil.like("outputPorts", "%" + portTypeId + "%"));

		List<ScienceAppOutputPorts> resultList = scienceAppOutputPortsPersistence.findWithDynamicQuery(query);
		
		long resultEqualCnt = 0;
		portfor:for(ScienceAppOutputPorts scienceAppOutputPort : resultList){
		net.sf.json.JSONObject outputPortJson = JSONObject. fromObject(JSONSerializer.toJSON(scienceAppOutputPort.getOutputPorts()));
		Set<String> set = outputPortJson.keySet();
			for (String names : set) {
				JSONObject jsonPort = outputPortJson.getJSONObject(names);
				long port_type_id = GetterUtil.get(jsonPort.get("port-type-id"), 0L);
				if(port_type_id == portTypeId){resultEqualCnt++;continue portfor;}
			}
		}
		//return scienceAppInputPortsPersistence.countWithDynamicQuery(query);
		return resultEqualCnt;
	}
	
	public List<Map<String,Object>> portAppList(long scienceAppId, Locale locale) throws SystemException, PortalException{
		List<Map<String,Object>> portAppList = new ArrayList<Map<String, Object>>();
		ScienceAppOutputPorts ports = super.fetchScienceAppOutputPorts(scienceAppId);
		try{
			if(ports != null) {
				net.sf.json.JSONObject outputPortJson = JSONObject. fromObject(JSONSerializer.toJSON(ports.getOutputPorts()));
				Set<String> set = outputPortJson.keySet();
				for (String names : set) {
					JSONObject jsonPort = outputPortJson.getJSONObject(names);
					String portName = jsonPort.getString("name_");
					String portDefaultAnalyzer = jsonPort.getString("defaultAnalyzer_");
					
					JSONObject dataTypeJson = jsonPort.getJSONObject("dataType_");
					String dataTypeName = dataTypeJson.getString("name");
					String dataTypeVersion = dataTypeJson.getString("version");
					
					Map<String,Object> portMap = new HashMap<String,Object>();
					portMap.put("portName", portName);
					portMap.put("portDefaultNameSpace", portDefaultAnalyzer);
					portMap.put("portType", "OUTPUT");
					
					List<Map<String,Object>> appList = new ArrayList<Map<String, Object>>();
					
					DataType dataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
					List<Map<String, Object>> searchList = new ArrayList<Map<String, Object>>();
					searchList.addAll(DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(dataType.getTypeId()));
					searchList.addAll(DataTypeAnalyzerLocalServiceUtil.retrieveDataTypeAnalyzerList(dataType.getTypeId()));
					
					for(Map<String,Object> searchMap : searchList){
						Map<String,Object> appMap = new HashMap<String,Object>();
						long searchScienceAppId = GetterUtil.getLong(searchMap.get("editorId"),0)==0?GetterUtil.getLong(searchMap.get("analyzerId"),0):GetterUtil.getLong(searchMap.get("editorId"),0);
						
						if(searchScienceAppId!=0){
							ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(searchScienceAppId);
							if(scienceApp.getStatus()==1901004){
								appMap.put("name", scienceApp.getName());
								appMap.put("exeFileName", scienceApp.getExeFileName());
								appMap.put("title", scienceApp.getTitle(locale));
								appMap.put("type", scienceApp.getAppType());
								boolean isDefault = scienceApp.getExeFileName().equals(portDefaultAnalyzer)?true:false;
								appMap.put("isDefault", isDefault);
								appList.add(appMap);
							}
						}
					}
					
					portMap.put("appList", appList);
					portAppList.add(portMap);
					
				}
			}
		}catch (JSONException e) {
			
		}finally {
			return portAppList;
		}
	}
}