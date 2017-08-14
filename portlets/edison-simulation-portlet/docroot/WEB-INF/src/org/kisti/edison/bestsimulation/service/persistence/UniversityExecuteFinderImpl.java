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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException;
import org.kisti.edison.bestsimulation.model.UniversityExecute;
import org.kisti.edison.bestsimulation.model.impl.UniversityExecuteImpl;
import org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl;
import org.kisti.edison.util.GBatisUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The persistence implementation for the university execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see UniversityExecutePersistence
 * @see UniversityExecuteUtil
 * @generated
 */
public class UniversityExecuteFinderImpl extends BasePersistenceImpl<UniversityExecute>
	implements UniversityExecuteFinder {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UniversityExecuteUtil} to access the university execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	
	//시뮬레이션 실행현황 표 데이터
	public List<Object[]> getStatisticsExecTableOrganigation(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsExecTableOrganigation");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("affiliation", Type.STRING);
			query.addScalar("userCnt", Type.INTEGER);
			query.addScalar("averageRuntime", Type.BIG_INTEGER);
			query.addScalar("jobCnt", Type.INTEGER);
			query.addScalar("cputime", Type.BIG_INTEGER);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}

	//시뮬레이션 실행현황 바 챠트 - 월별
	public List<Object[]> getStatisticsExecBarChartDate(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsExecBarChartDate");
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
	
	public int insertCustomUniversityExecute(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.bestsimulation.service.persistence.UniversityExecuteFinder.InsertCustomUniversityExecute");
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
	
	public int deleteCustomUniversityExecute(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.bestsimulation.service.persistence.UniversityExecuteFinder.DeleteCustomUniversityExecute");
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