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

package org.kisti.edison.virtuallaboratory.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.base.ProfessorLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the professor local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.virtuallaboratory.service.ProfessorLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.base.ProfessorLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil
 */
public class ProfessorLocalServiceImpl extends ProfessorLocalServiceBaseImpl {
	
	public Professor addProfessor(PortletRequest request, Map<String, Object> params, Locale locale, long userId) 
		throws SystemException, PortalException,
		SQLException, IOException{
		List<Professor> professorList = professorPersistence.findByUserId(userId);
		Professor professor = null;
		if(professorList.size() > 0){
			professor = professorPersistence.findByPrimaryKey(professorList.get(0).getProfessorSeq());
			professor.setHomepage(String.valueOf(params.get("professorHomepage")));
			professor.setPhone(String.valueOf(params.get("professorPhone")));
			professor.setRecordMap(CustomUtil.getLocalizationMap(params,"professorRecord"));
			professor.setPaperMap(CustomUtil.getLocalizationMap(params,"professorPaper"));
			professor.setIntroduceMap(CustomUtil.getLocalizationMap(params,"professorIntroduce"));
			
			professor = professorPersistence.update(professor);
		}else{
			long professorSeq = CounterLocalServiceUtil.increment(Professor.class.getName());
			professor = professorPersistence.create(professorSeq);
			professor.setUserId(userId);
			professor.setHomepage(String.valueOf(params.get("professorHomepage")));
			professor.setPhone(String.valueOf(params.get("professorPhone")));
			professor.setRecordMap(CustomUtil.getLocalizationMap(params,"professorRecord"));
			professor.setPaperMap(CustomUtil.getLocalizationMap(params,"professorPaper"));
			professor.setIntroduceMap(CustomUtil.getLocalizationMap(params,"professorIntroduce"));

			professor = professorPersistence.update(professor);
		}
		return professor;
	}
	
	
	public Map<String, Object> getProfessor(long userId, Locale locale) throws SystemException {
		List<Professor> professor = professorPersistence.findByUserId(userId);
		
		Map <String, Object> resultRow = null;
		//해당 하는 User가 professor Table에 등록이 안되어 있을 경우 해당 유저를 등록
		if(professor.size()==0){
			Professor newProfessor = ProfessorLocalServiceUtil.createProfessor(CounterLocalServiceUtil.increment(Professor.class.getName()));
			newProfessor.setUserId(userId);
			ProfessorLocalServiceUtil.addProfessor(newProfessor);
			resultRow = new HashMap<String, Object>();
			resultRow.put("professorSeq", newProfessor.getProfessorSeq());
			resultRow.put("userId", newProfessor.getUserId());
			resultRow.put("professorHomepage", newProfessor.getHomepage());
			resultRow.put("professorPhone", newProfessor.getPhone());
			resultRow.put("professorRecord", newProfessor.getRecord(locale, true));
			resultRow.put("professorPaper", newProfessor.getPaper(locale, true));
			resultRow.put("professorIntroduce", newProfessor.getIntroduce(locale, true));
			
		}else{
			for(int i=0; i< professor.size(); i++){
				Professor resultArray = professor.get(i);
				resultRow = new HashMap<String, Object>();
				
				if (professor != null) {
					resultRow.put("professorSeq", resultArray.getProfessorSeq());
					resultRow.put("userId", resultArray.getUserId());
					resultRow.put("professorHomepage", resultArray.getHomepage());
					resultRow.put("professorPhone", resultArray.getPhone());
					resultRow.put("professorRecord", resultArray.getRecord(locale, true));
					resultRow.put("professorPaper", resultArray.getPaper(locale, true));
					resultRow.put("professorIntroduce", resultArray.getIntroduce(locale, true));
				}
			}
		}
		return resultRow;
	}
	
	public List<Map<String, Object>> getProfessorList(Map<String, Object> params, Locale locale) throws SystemException {
		List<Object[]> professor =  professorFinder.getProfessorList(params ,locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		for(int i = 0; i < professor.size(); i++) {
			Object[] resultArray = professor.get(i);
			resultRow = new HashMap<String, Object>();
			
			User user;
			try {
				user = UserLocalServiceUtil.getUser(Long.parseLong(CustomUtil.strNull(resultArray[6])));
				String universityField = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
				String major = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR);
				
				if (resultArray != null) {
					resultRow.put("screenName", CustomUtil.strNull(resultArray[0]));
					resultRow.put("emailAddress", CustomUtil.strNull(resultArray[1]));
					resultRow.put("firstName", CustomUtil.strNull(resultArray[2]));
					resultRow.put("middleName", CustomUtil.strNull(resultArray[3]));
					resultRow.put("lastName", CustomUtil.strNull(resultArray[4]));
					resultRow.put("professorSeq", CustomUtil.strNull(resultArray[5]));
					resultRow.put("userId", CustomUtil.strNull(resultArray[6]));
					resultRow.put("record", CustomUtil.strNull(resultArray[7]));
					resultRow.put("paper", CustomUtil.strNull(resultArray[8]));
					resultRow.put("homepage", CustomUtil.strNull(resultArray[9]));
					resultRow.put("introduce", CustomUtil.strNull(resultArray[10]));
					resultRow.put("phone", CustomUtil.strNull(resultArray[11]));
					resultRow.put("universityFieldNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(universityField, EdisonExpando.CDNM , locale));
					resultRow.put("major", major);
					
				}
				returnList.add(resultRow);
			
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnList;
	}
	
	public int getCountProfessor(Map<String, Object> params, Locale locale) {
		return professorFinder.getCountProfessor(params, locale);
	}
	
	public boolean existProfessorByUserId(long userId) throws SystemException{
		boolean returnStatus = true;
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Professor.class);
		query.add(RestrictionsFactoryUtil.eq("userId", userId));
		long cnt = ProfessorLocalServiceUtil.dynamicQueryCount(query);
		
		if(cnt==0){
			returnStatus = false;
		}
		return returnStatus;
	}
}