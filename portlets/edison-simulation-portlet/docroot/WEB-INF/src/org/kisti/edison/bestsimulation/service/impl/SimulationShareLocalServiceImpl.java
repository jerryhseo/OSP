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

import java.util.Date;

import org.kisti.edison.bestsimulation.model.SimulationShare;
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.base.SimulationShareLocalServiceBaseImpl;
import org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * The implementation of the simulation share local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.SimulationShareLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.base.SimulationShareLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil
 */
public class SimulationShareLocalServiceImpl
	extends SimulationShareLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil} to access the simulation share local service.
	 */
	
	private static Log log = LogFactoryUtil.getLog(SimulationJobLocalServiceImpl.class);
	
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	public boolean sharingSimulationJob(int classId, int customId, int jobSeqNo, String jobUuid, String simulationUuid){
		try {
			
			long shareSeqNo = simulationShareFinder.getMaxShareSeqNoSimulationShare(jobSeqNo, jobUuid, simulationUuid);
			
			SimulationSharePK simulationSharePK = new SimulationSharePK();
			simulationSharePK.setShareSeqno(shareSeqNo);
			simulationSharePK.setJobSeqNo(jobSeqNo);
			simulationSharePK.setJobUuid(jobUuid);
			simulationSharePK.setSimulationUuid(simulationUuid);
			
			SimulationShare simulationShare =  simulationSharePersistence.create(simulationSharePK);
			simulationShare.setClassId(classId);
			simulationShare.setCustomId(customId);
			simulationShare.setSimulationShareDt(new Date());
			
			SimulationShareLocalServiceUtil.addSimulationShare(simulationShare);
			
			return true;
			
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteShareSimulationJob(SimulationShare simulationShare){
		
		try {
			
			SimulationShareLocalServiceUtil.deleteSimulationShare(simulationShare);
			
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public SimulationShare getSimulationShare(int shareSeqNo, int jobSeqNo, String jobUuid, String simulationUuid){
		
		try {
			
			SimulationSharePK simulationSharePK = new SimulationSharePK();
			simulationSharePK.setShareSeqno(shareSeqNo);
			simulationSharePK.setJobSeqNo(jobSeqNo);
			simulationSharePK.setJobUuid(jobUuid);
			simulationSharePK.setSimulationUuid(simulationUuid);
			
			return SimulationShareLocalServiceUtil.fetchSimulationShare(simulationSharePK);
			
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}