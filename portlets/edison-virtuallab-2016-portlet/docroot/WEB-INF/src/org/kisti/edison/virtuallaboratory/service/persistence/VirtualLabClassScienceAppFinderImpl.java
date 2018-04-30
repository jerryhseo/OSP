package org.kisti.edison.virtuallaboratory.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.util.GBatisUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClassScienceApp;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class VirtualLabClassScienceAppFinderImpl extends BasePersistenceImpl<VirtualLabClassScienceApp> implements VirtualLabClassScienceAppFinder {
	public List<Object[]> getVirtualLabScienceAppList(long columnId, long virtualLabId, long classId,long scienceAppId, Locale locale) {
		Session session=openSession();
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.VirtualLabClassScienceApp.getVirtualLabScienceAppList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("columnId", columnId);
			params.put("virtualLabId", virtualLabId);
			params.put("languageId", locale.toString());
			if(classId != 0){
				params.put("classId", classId);
			}
			if(scienceAppId != 0){
				params.put("scienceAppId", scienceAppId);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("title", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("userId", Type.LONG);
			query.addScalar("universityId", Type.STRING);
			query.addScalar("scienceAppSeqNo", Type.LONG);
			query.addScalar("virtualLabId", Type.LONG);
			query.addScalar("manualId", Type.STRING);
			query.addScalar("appType", Type.STRING);
			query.addScalar("openLevel", Type.STRING);
			query.addScalar("groupId", Type.LONG);
			if(classId != 0){
				query.addScalar("scienceAppCheck", Type.INTEGER);
			}
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
	
	public List<Object[]> getVirtualLabClassScienceAppList(long columnId, long virtualLabId ,long classId, Locale locale) {
		Session session=openSession();
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.VirtualLabClassScienceApp.getVirtualLabClassScienceAppList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("columnId", columnId);
			params.put("virtualLabId", virtualLabId);
			params.put("languageId", locale.toString());
			params.put("classId", classId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("title", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("userId", Type.LONG);
			query.addScalar("universityId", Type.STRING);
			query.addScalar("scienceAppSeqNo", Type.LONG);
			query.addScalar("scienceAppClassSeqNo", Type.LONG);
			query.addScalar("classId", Type.LONG);
			query.addScalar("manualId", Type.STRING);
			query.addScalar("appType", Type.STRING);
			query.addScalar("openLevel", Type.STRING);
			query.addScalar("groupId", Type.LONG);
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
	
	public List<Object[]> getScienceAppList(long virtualLabId, String searchField) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.VirtualLabClassScienceApp.getScienceAppList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("virtualLabId", virtualLabId);
			if(searchField != null && !searchField.equals("")) {
				params.put("searchField", searchField);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("name", Type.STRING);
			query.addScalar("version", Type.STRING);
			query.addScalar("screenName", Type.STRING);
			query.addScalar("virtuallabScienceAppCheck", Type.INTEGER);
			
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
	
	
	public List<Object[]> getVirtualLabClassesInfo(long virtualLabId, long scienceAppId) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.VirtualLabClassScienceApp.getVirtualLabClassesInfo");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("virtualLabId", virtualLabId);
			if(scienceAppId != 0){
				params.put("scienceAppId", scienceAppId);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppClassSeqNo", Type.LONG);
			query.addScalar("scienceAppSeqNo", Type.LONG);
			query.addScalar("classId", Type.LONG);
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("virtualLabId", Type.LONG);
			
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
	
	
	public List<Object[]> getVirtualLabInfo(long virtualLabId, long scienceAppId) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.VirtualLabClassScienceApp.getVirtualLabInfo");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("virtualLabId", virtualLabId);
			if(scienceAppId != 0){
				params.put("scienceAppId", scienceAppId);
			}
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppSeqNo", Type.LONG);
			query.addScalar("scienceAppId", Type.LONG);
			query.addScalar("virtualLabId", Type.LONG);
			
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
}
