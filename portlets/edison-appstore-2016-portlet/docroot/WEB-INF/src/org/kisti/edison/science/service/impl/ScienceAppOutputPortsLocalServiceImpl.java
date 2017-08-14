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

import java.util.List;
import java.util.Set;

import org.kisti.edison.science.model.ScienceAppOutputPorts;
import org.kisti.edison.science.service.base.ScienceAppOutputPortsLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;

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
}