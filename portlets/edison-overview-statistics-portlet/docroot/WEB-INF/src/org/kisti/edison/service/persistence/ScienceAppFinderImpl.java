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

import org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePersistence;
import org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecuteUtil;
import org.kisti.edison.model.ScienceApp;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * The persistence implementation for the science app execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppExecutePersistence
 * @see ScienceAppExecuteUtil
 * @generated
 */
public class ScienceAppFinderImpl extends BasePersistenceImpl<ScienceApp> implements ScienceAppFinder{
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppExecuteUtil} to access the science app execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	
	public int getScienceAppTotalCount() throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;
		
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.overviewStatistics.getScienceAppTotalCount");
			
			sqlSb.append(sqlSelect);
			session = openSession();
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("count", Type.INTEGER);
			
			cnt = (Integer) query.uniqueResult();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		
		return cnt;
	}
	
	public List<Object[]> getScienceAppCountByDate() throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;
		
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.overviewStatistics.getScienceAppCountByDate");
			
			sqlSb.append(sqlSelect);
			session = openSession();
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("createDate", Type.STRING);
			query.addScalar("count", Type.INTEGER);
			query.addScalar("cumulative_count", Type.INTEGER);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public List<Object[]> getScienceAppCountBySite() throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;
		
		try{
			
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.overviewStatistics.getScienceAppCountBySite");
			
			sqlSb.append(sqlSelect);
			session = openSession();
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("siteName", Type.STRING);
			query.addScalar("count", Type.INTEGER);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public int updateScienceAppStatistics() throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		try{
			String sqlSelect = sqlSelect = CustomSQLUtil.get("org.kisti.edison.overview.schedule.updateScienceApp");
			
			sqlSb.append(sqlSelect);
			session = openSession();
			
			Map<String, Object> searchParam = new HashMap<String, Object>();
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			return query.executeUpdate();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
}