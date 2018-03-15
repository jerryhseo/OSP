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

package org.kisti.edison.bestsimulation.service.persistence;

import java.util.List;
import java.util.Map;

import org.kisti.edison.bestsimulation.model.ScienceAppExecute;
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
public class ScienceAppExecuteFinderImpl extends BasePersistenceImpl<ScienceAppExecute>
	implements ScienceAppExecuteFinder {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScienceAppExecuteUtil} to access the science app execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	
	/**
	 * 
	 * @param searchParam > parentCategoryId, columnId, startDt, endDt
	 * @return
	 * @throws SystemException
	 */	
	public List<Object[]> getStatisticsSwExeTableOrganigation(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsSwExeTableOrganigation");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("scienceApp_name", Type.STRING);
			query.addScalar("user_count", Type.INTEGER);
			query.addScalar("averageRuntime", Type.INTEGER);
			query.addScalar("exe_count", Type.INTEGER);
			
			query.addScalar("scienceApp_affiliation_name", Type.STRING);
			query.addScalar("mgtName", Type.STRING);
			query.addScalar("mgtDate", Type.STRING);
			query.addScalar("scienceApp_version", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	/**
	 * 
	 * @param searchParam > parentCategoryId, columnId, startDt, endDt
	 * @return
	 * @throws SystemException
	 */	
	public List<Object[]> getStatisticsSwNoneExeTableOrganigation(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsSwNoneExeTableOrganigation");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("scienceApp_name", Type.STRING);
			query.addScalar("user_count", Type.INTEGER);
			query.addScalar("averageRuntime", Type.INTEGER);
			query.addScalar("exe_count", Type.INTEGER);
			
			query.addScalar("scienceApp_affiliation_name", Type.STRING);
			query.addScalar("mgtName", Type.STRING);
			query.addScalar("mgtDate", Type.STRING);
			query.addScalar("scienceApp_version", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public List<Object[]> getStatisticsSwExeBarChartDate(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsSwExeBarChartDate");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("month", Type.STRING);
			query.addScalar("monthCnt", Type.INTEGER);
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public int insertCustomScienceAppExecute(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecuteFinder.InsertCustomScienceAppExecute");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			return query.executeUpdate();
			
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public int deleteCustomScienceAppExecute(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecuteFinder.DeleteCustomScienceAppExecute");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
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