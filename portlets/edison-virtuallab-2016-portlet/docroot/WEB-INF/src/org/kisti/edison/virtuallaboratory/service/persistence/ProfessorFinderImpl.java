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

package org.kisti.edison.virtuallaboratory.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.GBatisUtil;
import org.kisti.edison.virtuallaboratory.model.Professor;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * The persistence implementation for the professor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ProfessorPersistence
 * @see ProfessorUtil
 * @generated
 */
public class ProfessorFinderImpl extends BasePersistenceImpl<Professor>
	implements ProfessorFinder {
	
	public List<Object[]> getProfessorList(Map<String, Object> params,Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.professor.getProfessorList");
			sqlSb.append(sqlQuery);
			String sql = sqlSb.toString();
			session = openSession();
			params.put("languageId", locale.toString());
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("screenName", Type.STRING);
			query.addScalar("emailAddress", Type.STRING);
			query.addScalar("firstName", Type.STRING);
			query.addScalar("middleName", Type.STRING);
			query.addScalar("lastName", Type.STRING);
			query.addScalar("professorSeq", Type.LONG);
			query.addScalar("userId", Type.LONG);
			query.addScalar("record", Type.STRING);
			query.addScalar("paper", Type.STRING);
			query.addScalar("homepage", Type.STRING);
			query.addScalar("introduce", Type.STRING);
			query.addScalar("phone", Type.STRING);
			
			return (List<Object[]>) query.list();
		} catch (Exception e) {
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
	
	
	
	
	public int getCountProfessor(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		params.remove("begin");
		try {
			String sqlCountStart = CustomSQLUtil.get("org.kisti.edison.service.persistence.professor.getCountProfessorStart");
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.professor.getProfessorList");
			String sqlCountEnd = CustomSQLUtil.get("org.kisti.edison.service.persistence.professor.getCountProfessorEnd");
			
			sqlSb.append(sqlCountStart);
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlCountEnd);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("totalCount", Type.INTEGER);
			
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
}