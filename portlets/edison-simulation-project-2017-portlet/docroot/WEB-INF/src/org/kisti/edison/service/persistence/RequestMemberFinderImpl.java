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

import java.util.List;
import java.util.Map;

import org.kisti.edison.model.RequestMember;
import org.kisti.edison.model.impl.RequestMemberImpl;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * The persistence implementation for the request member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see RequestMemberPersistence
 * @see RequestMemberUtil
 * @generated
 */
public class RequestMemberFinderImpl extends BasePersistenceImpl<RequestMember>
	implements RequestMemberFinder {

	public List<Object[]> getCustomRequestMembertList(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
        
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.simulationProject.service.persistence.RequestMemberFinder.getCustomRequestMembertList");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDSIMPRO_RequestMember", RequestMemberImpl.class);
			query.addScalar("firstName", Type.STRING);
			query.addScalar("screenName", Type.STRING);
			query.addScalar("universityField", Type.STRING);
			
			return (List<Object[]>) query.list();
			
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public int getCustomRequestMemberCount(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.simulationProject.service.persistence.RequestMemberFinder.getCustomRequestMemberCount");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("totalCount", Type.INTEGER);
            
            return (Integer) query.uniqueResult();
			
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
}