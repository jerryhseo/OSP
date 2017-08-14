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

import org.kisti.edison.science.NoSuchScienceAppLogPortsException;
import org.kisti.edison.science.model.ScienceAppLogPorts;
import org.kisti.edison.science.service.base.ScienceAppLogPortsLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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
}