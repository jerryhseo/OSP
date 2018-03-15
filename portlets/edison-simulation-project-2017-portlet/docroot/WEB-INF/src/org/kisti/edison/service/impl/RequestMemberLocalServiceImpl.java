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

package org.kisti.edison.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.RequestMember;
import org.kisti.edison.service.RequestMemberLocalServiceUtil;
import org.kisti.edison.service.base.RequestMemberLocalServiceBaseImpl;
import org.kisti.edison.service.persistence.RequestMemberPK;
import org.kisti.edison.util.EdisonExpndoUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.service.TeamLocalServiceUtil;

/**
 * The implementation of the request member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.service.RequestMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author edison
 * @see org.kisti.edison.service.base.RequestMemberLocalServiceBaseImpl
 * @see org.kisti.edison.service.RequestMemberLocalServiceUtil
 */
public class RequestMemberLocalServiceImpl
	extends RequestMemberLocalServiceBaseImpl {
	
	/**
	 * 시뮬레이션 프로젝트 멤버 가입 신청
	 * @param user - 신청 유저 정보
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param requestState - 신청 상태
	 * @return RequestMember - 생성한 신청정보
	 * @throws SystemException
	 */
	public RequestMember insertCustomSimulationProjectMemberRequest(User user, long simulationProjectId, String requestState) throws SystemException{
		
		try{
			long requestSeq = CounterLocalServiceUtil.increment(RequestMember.class.getName());			
			
			RequestMemberPK requestMemberPK = new RequestMemberPK(requestSeq, simulationProjectId);
			RequestMember requestMember = RequestMemberLocalServiceUtil.createRequestMember(requestMemberPK);
			requestMember.setUserId(user.getUserId());
			requestMember.setRequestState(requestState);
			requestMember.setRequestDate(new Date());
			
			requestMember = RequestMemberLocalServiceUtil.updateRequestMember(requestMember);
			
			return requestMember;
		}catch(Exception e){
			throw new SystemException(e);
		} 
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 신청 정보 수정
	 * @param requestSeq - 신청 seq 번호
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param requestState - 신청 상태
	 * @return RequestMember - 수정한 신청정보
	 * @throws SystemException
	 */
	public RequestMember updateCustomSimulationProjectMemberRequest(long requestSeq, long simulationProjectId, long userId, String requestState) throws SystemException{
		
		try{
			RequestMemberPK requestMemberPK = new RequestMemberPK(requestSeq, simulationProjectId);
			RequestMember requestMember = RequestMemberLocalServiceUtil.getRequestMember(requestMemberPK);
			requestMember.setRequestState(requestState);
			requestMember.setProcessDate(new Date());
			
			/* Team Role 관리체계 제거 : 2017.12.05 */
			if("2003001".equals(requestState)){
				long[] userIds = new long[]{userId};
				requestMember.setProcessDate(null);
			}else if("2003002".equals(requestState)){
				requestMember.setProcessDate(new Date());
			}
			
			requestMember = RequestMemberLocalServiceUtil.updateRequestMember(requestMember);
			
			return requestMember;
			
		}catch(Exception e){
			throw new SystemException(e);
		} 
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 신청 정보 삭제
	 * @param requestSeq - 신청 seq
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @throws SystemException
	 */
	public void deleteCustomSimulationProjectMemberRequest(long requestSeq, long simulationProjectId) throws SystemException{
		try{
			RequestMemberPK requestMemberPK = new RequestMemberPK(requestSeq, simulationProjectId);
			RequestMemberLocalServiceUtil.deleteRequestMember(requestMemberPK);
		}catch(Exception e){
			throw new SystemException(e);
		} 
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 신청 정보 리스트
	 * @param begin
	 * @param end
	 * @param searchText - 검색어
	 * @param searchRequestState - 절차
	 * @return List<Map<String, Object>> - 시뮬레이션 프로젝트 멤버 신청 정보 리스트 
	 * @throws SystemException
	 *  
	 */
	public List<Map<String, Object>> getCustomRequestMemberList(long simulationProjectId, int begin, int end, String searchText, String searchRequestState, Locale locale, long columnId) throws SystemException {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		try{
			searchParam.put("begin", begin);
			searchParam.put("end", end);
			searchParam.put("simulationProjectId", simulationProjectId);
			searchParam.put("columnId", columnId);
			if(!"".equals(searchText)){
				searchParam.put("searchText", searchText);
			}
			if(!"".equals(searchRequestState)){
				searchParam.put("searchRequestState", searchRequestState);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			RequestMember requestMember = null;
			Object[] objs = null;
			Map<String, Object> map = null;
			
			List<Object[]> tempList = requestMemberFinder.getCustomRequestMembertList(searchParam);
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			
			
			if(tempList != null){
		        if(tempList.size() > 0){
		        	for(int i=0; i < tempList.size();i++){
		        		objs = tempList.get(i);
		        		requestMember = (RequestMember)objs[0];
		        		
		        		map = new HashMap();
		        		map.put("requestSeq",				requestMember.getRequestSeq());
		        		map.put("simulationProjectId",		requestMember.getSimulationProjectId());
		        		map.put("userId",					requestMember.getUserId());
		        		map.put("requestDate",				requestMember.getRequestDate() != null ?
		        				sdf.format(requestMember.getRequestDate()) : "" );
		        		map.put("processDate",				requestMember.getProcessDate() != null ? 
		        				sdf.format(requestMember.getProcessDate()) : "");
		        		map.put("requestState",				requestMember.getRequestState());
		        		map.put("requestStateNm",			EdisonExpndoUtil.getCommonCdSearchFieldValue(requestMember.getRequestState(), EdisonExpando.CDNM , locale));
		        		map.put("firstName",				(String) objs[1]);
		        		map.put("screenName",	    		(String) objs[2]);
		        		map.put("universityFieldNm",	    EdisonExpndoUtil.getCommonCdSearchFieldValue((String) objs[3], EdisonExpando.CDNM , locale));
		        		resultList.add(map);
		        	}
		        }
	        }
			
			return resultList;
		}catch(Exception e){
			throw new SystemException(e);
		}	
		
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 신청 정보 카운트
	 * @param searchText - 검색어
	 * @param searchRequestState - 절차
	 * @return int - 시뮬레이션 프로젝트 멤버 신청 정보 카운트 
	 * @throws SystemException 
	 */
	public int getCustomRequestMemberCount(long simulationProjectId, String searchText, String searchRequestState, long columnId) throws SystemException{
		Map<String,Object> searchParam = new HashMap<String,Object>();
		try{
			searchParam.put("searchText", searchText);
			searchParam.put("searchRequestState", searchRequestState);
			searchParam.put("simulationProjectId", simulationProjectId);
			searchParam.put("columnId", columnId);
			
			int resultCount = 0;
	        resultCount = requestMemberFinder.getCustomRequestMemberCount(searchParam);
			 
			return resultCount;
		}catch(Exception e){
			throw new SystemException(e);
		}		
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 체크
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param userId - 유저 아이디
	 * @param portalGroupId - 포탈 그룹 아이디
	 * @return 프로젝트 소속 여부
	 * @throws SystemException
	 */
	public boolean isSimulationProjectMember(long simulationProjectId, long userId, long portalGroupId) throws SystemException{
		
		boolean isMember = false;
		
		List<RequestMember> requestMemberList = requestMemberPersistence.findBySimulationProjectIdAndUseId(userId, simulationProjectId);
		if(requestMemberList != null){
			for(RequestMember requestMember : requestMemberList){
				// 가입 승인된 requestMember
				if("2003002".equals(requestMember.getRequestState()) && requestMember.getProcessDate() != null){
					isMember = true;
					break;
				}
			}
		}
		
		return isMember;
	}
	
	/**
	 * 시뮬레이션 프로젝트 멤버 신청중인지 체크
	 * @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	 * @param userId - 유저 아이디
	 * @return 프로젝트 멤버 신청 여부
	 * @throws SystemException
	 */
	public boolean isSimulationProjectMemberRequest(long simulationProjectId, long userId) throws SystemException{
		boolean isMemberRequest = false;
		
		List<RequestMember> requestMemberList = requestMemberPersistence.findBySimulationProjectIdAndUseId(userId, simulationProjectId);
		if(requestMemberList != null){
			for(RequestMember requestMember : requestMemberList){
				if("2003001".equals(requestMember.getRequestState())){
					isMemberRequest = true;
					break;
				}
			}
		}
		return isMemberRequest;
	}
}