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

package org.kisti.edison.simulation.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kisti.edison.simulation.model.BatchMonitoring;
import org.kisti.edison.simulation.service.base.BatchMonitoringLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the batch monitoring local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.BatchMonitoringLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.bestsimulation.service.base.BatchMonitoringLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.BatchMonitoringLocalServiceUtil
 */
public class BatchMonitoringLocalServiceImpl
	extends BatchMonitoringLocalServiceBaseImpl {
	
	/**
	 * 배치 결과 메세지 
	 * @param exeDate - 배치 실행 시간
	 * @param startDt -시작 날짜
	 * @param endDt - 종료 날짜
	 * @param batchSuccess - 배치 성공 여부
	 * @return
	 */
	public String getBatchResultMassege(Date exeDate, String startDt, String endDt, boolean batchSuccess){
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String batMessage = "";
		if(batchSuccess){
			batMessage += "Batch Excute Success<br>Batch Excute Time : "+ sdf2.format(exeDate) + "<br>StartDt : "+ startDt + " / EndDt : " + endDt;
		}else{
			batMessage += "Batch Excute Fail<br>Batch Excute Time : "+ sdf2.format(exeDate) + "<br>StartDt : "+ startDt + " / EndDt : " + endDt;
		}
		
		return batMessage;
	}
	
	/**
	 * 배치 실행 이력 저장
	 * @param batDiv - 배치 분류 
	 * @param manualYN - 수동실행 여부
	 * @param status - 실행 상태 (SUCCESS, FAIL)
	 * @param message - 실행 정보
	 * @param insertId - 배치실행 아이디 (스케쥴러로 실행시는 아이디 없음)
	 * @param exeDate - 실행 시간
	 */
	public BatchMonitoring insertCustomBatchMonitoring(String batDiv, String manualYN, String status, String message, Long insertId, Date exeDate) throws SystemException, NumberFormatException, NoSuchModelException {
		
		BatchMonitoring batchMonitoring = null;
		long batSeqNo = CounterLocalServiceUtil.increment(BatchMonitoring.class.getName());
		
		batchMonitoring = batchMonitoringPersistence.create(batSeqNo);
		
		batchMonitoring.setBatDiv(CustomUtil.strNull(batDiv,""));
		batchMonitoring.setManualYN(CustomUtil.strNull(manualYN,""));
		batchMonitoring.setStatus(CustomUtil.strNull(status,""));
		batchMonitoring.setMessage(CustomUtil.strNull(message,""));
		batchMonitoring.setInsertId(insertId);
		batchMonitoring.setExeDate(exeDate);
		
		batchMonitoring = batchMonitoringPersistence.update(batchMonitoring);
		
		return batchMonitoring;
	}
	
	/**
	 * 배치 실행 이력 리스트
	 * @param begin
	 * @param end
	 * @return 실행 이력 
	 */
	public List<Map<String, Object>> getCustomBatchMonitoringList(int begin, int end) throws SystemException, PortalException, ParseException{		
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("begin", begin);
		searchParam.put("end", end);
		
		List<Object[]> tempList = batchMonitoringFinder.getCustomBatchMonitoringList(searchParam);
		
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("batSeqNo",		objs[0]);
					map.put("batDiv",		objs[1]);
					map.put("manualYN",	    objs[2]);
					map.put("status",		objs[3]);
					map.put("message",		objs[4]);
					map.put("insertName",	objs[5]);
					map.put("exeDate",		objs[6]);
					
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	
	/**
	 * 배치 실행 이력 카운트
	 * @return 실행 이력 카운트 
	 */
	public int getCustomBatchMonitoringCount() throws SystemException, PortalException, ParseException{		
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		
		int totalCount = batchMonitoringFinder.getCustomBatchMonitoringCount(searchParam);
		
		return totalCount;
	}
}