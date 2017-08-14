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

package org.kisti.edison.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kisti.edison.model.SimProScienceAppLink;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * The persistence implementation for the sim pro science app link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimProScienceAppLinkPersistence
 * @see SimProScienceAppLinkUtil
 * @generated
 */
public class SimProScienceAppLinkFinderImpl extends BasePersistenceImpl<SimProScienceAppLink>
	implements SimProScienceAppLinkFinder {

	public List<Object[]> getScienceAppList(String searchField, String languageId, int begin, int end) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.simulationProject.service.persistence.SimProScienceAppLinkFinder.getScienceAppList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("languageId", languageId);
			params.put("begin", begin);
	        params.put("end", end);
			
			if(searchField != null && !searchField.equals("")) {
				params.put("searchField", searchField);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("title", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("screenName", Type.STRING);
			
			return (List<Object[]>) query.list();
		}  catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;
	}
	
	public int getScienceAppListCount(String searchField, String languageId) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.simulationProject.service.persistence.SimProScienceAppLinkFinder.getScienceAppCount");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("languageId", languageId);
			
			if(searchField != null && !searchField.equals("")) {
				params.put("searchField", searchField);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("totalCnt", Type.INTEGER);
            
            return (Integer) query.uniqueResult();
		}  catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return 0;
	}
	
	public List<Object[]> getSelectScienceAppList(String scienceAppIds, String languageId) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.simulationProject.service.persistence.SimProScienceAppLinkFinder.getSelectScienceAppList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("languageId", languageId);
			
			if(!"0".equals(scienceAppIds)){
				params.put("scienceAppIds", scienceAppIds);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("iconId", Type.LONG);
			query.addScalar("screenName", Type.STRING);
			
			return (List<Object[]>) query.list();
		}  catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;
	}
	
  public int searchTextEntryScienceAppCount(Map<String, Object> params){
    Session session = openSession();
    int cnt = 0;
    try{
      String sqlQuerySelect = CustomSQLUtil.get(
        "org.kisti.edison.simulationProject.service.persistence.SimProScienceAppLinkFinder.getAssetEntryModelCountHeader");
      String sqlQueryWhere = CustomSQLUtil.get(
        "org.kisti.edison.simulationProject.service.persistence.SimProScienceAppLinkFinder.getAssetEntryModelWhere");
      StringBuffer sql = new StringBuffer();
      sql.append(sqlQuerySelect);
      sql.append(sqlQueryWhere);

      String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
      SQLQuery query = session.createSQLQuery(gBatisQuery);
      query.addScalar("totalCnt", Type.INTEGER);

      cnt = (Integer) query.uniqueResult();
    }catch (Exception e){
      e.printStackTrace();
    }finally{
      closeSession(session);
    }
    return cnt;
  }
	
}