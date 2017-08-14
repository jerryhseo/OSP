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

package org.kisti.edison.bestsimulation.service.impl;

import org.kisti.edison.bestsimulation.NoSuchSimulationJobDataException;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.base.SimulationJobDataLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * The implementation of the simulation job data local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.SimulationJobDataLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author mhkang
 * @see org.kisti.edison.bestsimulation.service.base.SimulationJobDataLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil
 */
public class SimulationJobDataLocalServiceImpl
	extends SimulationJobDataLocalServiceBaseImpl {
	
	public SimulationJobData modifySimulationJobData(String jobUuid,String jobData) throws SystemException{
		SimulationJobData simulationJobData = null;
		try {
			simulationJobData = simulationJobDataPersistence.findByPrimaryKey(jobUuid);
		} catch (NoSuchSimulationJobDataException e) {
			simulationJobData = simulationJobDataPersistence.create(jobUuid);
		} catch (SystemException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		
		if(!GetterUtil.getString(jobData,"").equals("")){
			simulationJobData.setJobData(jobData);
			simulationJobDataPersistence.update(simulationJobData);
		}
		
		return simulationJobData;
	}
	
	/***
	 *  Added by Jerry H. Seo, from July 5, 2017
	 * @throws SystemException 
	 * @throws NoSuchSimulationJobDataException 
	 */
	
	public SimulationJobData replaceJobData( String prevUuid, String newUuid, String jobData ) throws SystemException{
		try {
			super.simulationJobDataPersistence.remove(prevUuid);
		} catch (NoSuchSimulationJobDataException e) {
			System.out.println("No job data: "+prevUuid);
		}
		SimulationJobData newJobData = super.simulationJobDataPersistence.create(newUuid);
		newJobData.setJobData(jobData);
		
		return super.addSimulationJobData(newJobData);
	}
}	