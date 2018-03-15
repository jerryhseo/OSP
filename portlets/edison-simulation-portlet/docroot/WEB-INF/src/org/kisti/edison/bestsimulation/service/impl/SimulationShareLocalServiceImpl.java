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
import java.util.List;

import org.kisti.edison.bestsimulation.NoSuchSimulationJobException;
import org.kisti.edison.bestsimulation.NoSuchSimulationShareException;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationShare;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.base.SimulationShareLocalServiceBaseImpl;
import org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
	
	
	
	public List<SimulationShare> findListByJobUuid(String jobUuid) throws SystemException{
		return simulationSharePersistence.findByjobUuid(jobUuid);
	}
	
	public boolean isExitByJobUUid(String jobUuid) throws SystemException{
		DynamicQuery simulationShareJob = DynamicQueryFactoryUtil.forClass(SimulationShare.class, "simulationShare",PortletClassLoaderUtil.getClassLoader());
		simulationShareJob.add(RestrictionsFactoryUtil.eq("primaryKey.jobUuid",jobUuid));
		long cnt = simulationShareLocalService.dynamicQueryCount(simulationShareJob);
		return cnt==0?false:true;
	}
	
	public void removeBySimulationUuid(String simulationUuid) throws SystemException{
		DynamicQuery simulationShareJob = DynamicQueryFactoryUtil.forClass(SimulationShare.class, "simulationShare",PortletClassLoaderUtil.getClassLoader());
		simulationShareJob.add(RestrictionsFactoryUtil.eq("primaryKey.simulationUuid",simulationUuid));
		List<SimulationShare> shareList = simulationShareLocalService.dynamicQuery(simulationShareJob);
		for(SimulationShare simulationShare : shareList){
			try {
				simulationSharePersistence.remove(simulationShare.getPrimaryKey());
			} catch (NoSuchSimulationShareException e) {
				
			}
		}
	}
	
	public void removeByJobUuid(String jobUuid) throws SystemException{
		List<SimulationShare> shareList = this.findListByJobUuid(jobUuid);
		for(SimulationShare simulationShare : shareList){
			SimulationSharePK simulationSharePK = new SimulationSharePK(simulationShare.getShareSeqno(),
					simulationShare.getJobSeqNo(), jobUuid, simulationShare.getSimulationUuid());
			try {
				simulationSharePersistence.remove(simulationSharePK);
			} catch (NoSuchSimulationShareException e) {
				
			}
		}
	}
	
	public SimulationShare createByJobUuid(String jobUuid, long classId, long customId) throws SystemException{
		try{
			SimulationJob simulationJob =SimulationJobLocalServiceUtil.getJob(jobUuid);
			
			int jobSeqNo = (int) simulationJob.getJobSeqNo();
			String simulationUuid = simulationJob.getSimulationUuid();
			
			long shareSeqNo = simulationShareFinder.getMaxShareSeqNoSimulationShare(jobSeqNo, jobUuid, simulationUuid);
			
			SimulationSharePK simulationSharePK = new SimulationSharePK();
			simulationSharePK.setShareSeqno(shareSeqNo);
			simulationSharePK.setJobSeqNo(jobSeqNo);
			simulationSharePK.setJobUuid(jobUuid);
			simulationSharePK.setSimulationUuid(simulationUuid);
			
			SimulationShare simulationShare = SimulationShareLocalServiceUtil.createSimulationShare(simulationSharePK);
			simulationShare.setClassId(classId);
			simulationShare.setCustomId(customId);
			simulationShare.setSimulationShareDt(new Date());
			
			return SimulationShareLocalServiceUtil.updateSimulationShare(simulationShare);
		}catch (NoSuchSimulationJobException e) {
			return null;
		}
	}
	
	/**
	 * JobUUid를 통한 공유 항목 삭제 후 재정의
	 * @param jobUuid
	 * @param classId
	 * @param customIds - 다수 일경우 delimeter는 ,
	 * @throws SystemException
	 */
	public void removeAndCreateByJobUUids(String jobUuid,long classId, String customIds) throws SystemException{
		this.removeByJobUuid(jobUuid);
		
		String[] customIdArray = StringUtil.split(customIds,",");
		for(String customIdStr : customIdArray){
			long customId = Long.parseLong(customIdStr);
			
			if(classId!=0&&customId!=0){
				this.createByJobUuid(jobUuid, classId, customId);
			}
		}
	}
	
}