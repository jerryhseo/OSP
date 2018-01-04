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

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppInputPorts;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.base.ScienceAppInputPortsLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * The implementation of the science app input ports local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.science.service.ScienceAppInputPortsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.science.service.base.ScienceAppInputPortsLocalServiceBaseImpl
 * @see org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil
 */
public class ScienceAppInputPortsLocalServiceImpl
	extends ScienceAppInputPortsLocalServiceBaseImpl {
	public ScienceAppInputPorts create(long scienceAppId, String inputPorts) throws SystemException{
		ScienceAppInputPorts ports = null;
		try {
			ports = super.getScienceAppInputPorts(scienceAppId);
		} catch (PortalException e) {
			ports = super.createScienceAppInputPorts(scienceAppId);
		} catch (SystemException e) {
			throw e;
		}

		ports.setInputPorts(inputPorts);
		super.addScienceAppInputPorts(ports);
		
		return ports;
	}
	
	public String getInputPortsJsonString(long scienceAppId) throws SystemException{
		String result = "";
		ScienceAppInputPorts ports = super.fetchScienceAppInputPorts(scienceAppId);
		if(ports != null) {
			result = ports.getInputPorts();
		}
		return result;
	}
	
	
	public String getInputPortsJsonArray(long scienceAppId) throws SystemException{
		String result = "";
		ScienceAppInputPorts ports = super.fetchScienceAppInputPorts(scienceAppId);
		if(ports != null) {
			net.sf.json.JSONObject inputPortJson = JSONObject. fromObject(JSONSerializer.toJSON(ports.getInputPorts()));
			JSONArray inputPortArray = new JSONArray();
			Set<String> set = inputPortJson.keySet();
			for (String names : set) {
				JSONObject jsonPort = inputPortJson.getJSONObject(names);
				inputPortArray.add(jsonPort);
			}
			result = inputPortArray.toString();
		}
		return result;
	}
	
	public void removeAllInputPorts() throws SystemException{
		super.scienceAppInputPortsPersistence.removeAll();
	}
	
	/**********************************   ADD GPLUS SERVICE 	 ******************************/
	public long getScienceAppInputPortsesCount(long scienceAppId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppInputPorts.class)
							.add(PropertyFactoryUtil.forName("scienceAppId").eq(scienceAppId));
				
		return scienceAppInputPortsPersistence.countWithDynamicQuery(query);
	}
	
	
	public long getInputPortsCountByPotyTypeId(long portTypeId) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ScienceAppInputPorts.class)
							 .add(RestrictionsFactoryUtil.like("inputPorts", "%" + portTypeId + "%"));
		
		List<ScienceAppInputPorts> resultList = scienceAppInputPortsPersistence.findWithDynamicQuery(query);
		
		long resultEqualCnt = 0;
		portfor:for(ScienceAppInputPorts scienceAppInputPort : resultList){
			net.sf.json.JSONObject inputPortJson = JSONObject. fromObject(JSONSerializer.toJSON(scienceAppInputPort.getInputPorts()));
			Set<String> set = inputPortJson.keySet();
			for (String names : set) {
				JSONObject jsonPort = inputPortJson.getJSONObject(names);
				long port_type_id = GetterUtil.get(jsonPort.get("port-type-id"), 0L);
				if(port_type_id == portTypeId){resultEqualCnt++;continue portfor;}
			}
		}
//		return scienceAppInputPortsPersistence.countWithDynamicQuery(query);
		return resultEqualCnt;
	}
	
	public Map<String,Object> addSampeFile(long scienceAppId,String portName,ServiceContext sc,UploadPortletRequest upload) throws SystemException, PortalException{
		ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		
		ScienceAppInputPorts scienceAppInputPorts = scienceAppInputPortsPersistence.findByPrimaryKey(scienceAppId);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		JSONObject jsonObject =null;
		if(!GetterUtil.getString(scienceAppInputPorts.getInputPortsSampleFile()).equals("")){
			jsonObject= (JSONObject) JSONSerializer.toJSON(scienceAppInputPorts.getInputPortsSampleFile());
			
			//기존 파일 삭제
			if(!CustomUtil.strNull(jsonObject.get(portName)).equals("")){
				long prePortSampleFileId = GetterUtil.getLong(jsonObject.get(portName));
				try{
					DLFileEntryLocalServiceUtil.deleteDLFileEntry(prePortSampleFileId);
				}catch(NoSuchFileEntryException e){
					
				}
			}
		}else{
			jsonObject = new JSONObject();
		}
		
		try {
			List<FileEntry> sampleFileList = EdisonFileUtil.insertEdisonFile(sc.getLiferayPortletRequest(), upload, sc.getUserId(),
					scienceApp.getGroupId(), "", String.valueOf(scienceAppId), "sampleFile",
					EdisonFileConstants.SCIENCE_APPS);
			
			FileEntry sampeFileEntry = sampleFileList.get(0);
			returnMap.put("fileEntryId", sampeFileEntry.getFileEntryId());
			returnMap.put("title", sampeFileEntry.getTitle());
			
			jsonObject.put(portName, sampeFileEntry.getFileEntryId());
			
			scienceAppInputPorts.setInputPortsSampleFile(jsonObject.toString());
			
			scienceAppInputPortsPersistence.update(scienceAppInputPorts);
		} catch (SQLException e) {
			e.printStackTrace();
			new PortalException(e);
		} catch (IOException e) {
			e.printStackTrace();
			new PortalException(e);
		}
		return returnMap;
	}
}