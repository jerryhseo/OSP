/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import org.kisti.edison.science.NoSuchScienceAppLogPortsException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.base.ScienceAppLogPortsLocalServiceBaseImpl;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * The implementation of the science app log ports local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.science.service.ScienceAppLogPortsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppLogPortsLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppLogPortsLocalServiceUtil
 */
public class ScienceAppLogPortsLocalServiceImpl
	extends ScienceAppLogPortsLocalServiceBaseImpl {
	
	public ScienceAppLogPorts create(long scienceAppId, String logPorts) throws PortalException, SystemException{
		ScienceAppLogPorts ports= null;
		try{
			ports = super.getScienceAppLogPorts(scienceAppId);
		}catch(NoSuchScienceAppLogPortsException e){
			ports = super.createScienceAppLogPorts(scienceAppId);
		}
		
		ports.setLogPorts(logPorts);
		super.addScienceAppLogPorts(ports);
		
		return ports;
	}
	
	public String getLogPortsJsonString(long scienceAppId) throws SystemException{
		ScienceAppLogPorts ports = super.fetchScienceAppLogPorts(scienceAppId);
		if( ports == null )	return "";
		return ports.getLogPorts();
	}
	
	public void removeAllLogPorts() throws SystemException{
		super.scienceAppLogPortsPersistence.removeAll();
	}
	
	public long getScienceAppLogPortsesCount(long scienceAppId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppLogPorts.class)
							.add(PropertyFactoryUtil.forName("scienceAppId").eq(scienceAppId));
				
		return scienceAppLogPortsPersistence.countWithDynamicQuery(query);
	}
	
	public List<Map<String,Object>> portAppList(long scienceAppId, Locale locale) throws SystemException, PortalException{
		List<Map<String,Object>> portAppList = new ArrayList<Map<String, Object>>();
		ScienceAppLogPorts ports = super.fetchScienceAppLogPorts(scienceAppId);
		if(ports != null) {
			net.sf.json.JSONObject logPortJson = JSONObject. fromObject(JSONSerializer.toJSON(ports.getLogPorts()));
			Set<String> set = logPortJson.keySet();
			for (String names : set) {
				JSONObject jsonPort = logPortJson.getJSONObject(names);
				String portName = jsonPort.getString("name_");
				String portDefaultAnalyzer = jsonPort.getString("defaultAnalyzer_");
				
				JSONObject dataTypeJson = jsonPort.getJSONObject("dataType_");
				String dataTypeName = dataTypeJson.getString("name");
				String dataTypeVersion = dataTypeJson.getString("version");
				
				Map<String,Object> portMap = new HashMap<String,Object>();
				portMap.put("portName", portName);
				portMap.put("portDefaultNameSpace", portDefaultAnalyzer);
				portMap.put("portType", "LOG");
				
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
		return portAppList;
	}
}

