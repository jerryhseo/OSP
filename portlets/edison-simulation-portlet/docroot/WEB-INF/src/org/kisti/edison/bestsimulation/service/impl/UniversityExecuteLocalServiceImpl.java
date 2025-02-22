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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.bestsimulation.service.base.UniversityExecuteLocalServiceBaseImpl;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * The implementation of the university execute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.bestsimulation.service.UniversityExecuteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.bestsimulation.service.base.UniversityExecuteLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil
 */
public class UniversityExecuteLocalServiceImpl
	extends UniversityExecuteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil} to access the university execute local service.
	 */
	//시뮬레이션 실행현황 표 데이터
	public List<Map<String, Object>> getStatisticsExecTableOrganigation(Locale locale, String startDt, String endDt) throws SystemException, PortalException, ParseException{		
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("startDt", startDt);
		searchParam.put("endDt", endDt);
		
		List<Object[]> tempList = universityExecuteFinder.getStatisticsExecTableOrganigation(searchParam);
		
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();

					String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(objs[0]), EdisonExpando.CDNM, locale);
					
					map.put("affiliation",		affiliation);
					map.put("userCnt",			objs[1]);
					map.put("averageRuntime",	objs[2]);
					map.put("jobCnt",			objs[3]);
					map.put("cputime",			objs[4]);
					
					resultList.add(map);
				}
				
				Comparator<HashMap> affiliation = new Comparator<HashMap>() {
					@Override
					public int compare(HashMap o1, HashMap o2) {
						String s1 = o1.get("affiliation").toString();
						String s2 = o2.get("affiliation").toString();
						
						int n1=s1.length(), n2=s2.length();
						for (int i1=0, i2=0; i1<n1 && i2<n2; i1++, i2++) {
							char c1 = s1.charAt(i1);
							char c2 = s2.charAt(i2);
							if (c1 != c2) {
								c1 = Character.toUpperCase(c1);
								c2 = Character.toUpperCase(c2);
								if (c1 != c2) {
									c1 = Character.toLowerCase(c1);
									c2 = Character.toLowerCase(c2);
									if (c1 != c2) {
										return c1 - c2;
									}
								}
							}
						}
						return n1 - n2;
					}
				};
				
				Collections.sort(resultList, affiliation);
			}
		}		
		return resultList;
	}	
	
	//시뮬레이션 실행현황 바 챠트 - 월별
	public List<Map<String, Object>> getStatisticsExecBarChartDate(String startDt, String endDt) throws SystemException, PortalException, ParseException{
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("startDt", startDt);
		searchParam.put("endDt", endDt);
		
		List<Object[]> tempList = universityExecuteFinder.getStatisticsExecBarChartDate(searchParam);
		List resultList = new ArrayList();
		Object[] objs = null;
		Map map = null;
		
		if(tempList != null){
			if(tempList.size() > 0){
				for(int i=0;i < tempList.size();i++){
					objs = (Object[])tempList.get(i);
					map = new HashMap();
					map.put("month",		objs[0]);
					map.put("monthCnt",		objs[1]);
					resultList.add(map);
				}
			}
		}		
		return resultList;
	}
	
	/*UniversityExecute Insert*/
	public int insertCustomUniversityExecute(long columnId, String startDt, String endDt) throws SystemException, NumberFormatException, PortalException {
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		
		searchParam.put("tableId", ExpandoTableLocalServiceUtil.getTable(PortalUtil.getDefaultCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME));
		searchParam.put("columnId", columnId);
		searchParam.put("startDt", CustomUtil.strNull(startDt,""));
		searchParam.put("endDt", CustomUtil.strNull(endDt,""));
		
		int result = 0;
		try{
			result = universityExecuteFinder.insertCustomUniversityExecute(searchParam);
		}catch (Exception e) {
			result = universityExecuteFinder.deleteCustomUniversityExecute(searchParam);
			result = universityExecuteFinder.insertCustomUniversityExecute(searchParam);
		}
		
		return result;
	}
}