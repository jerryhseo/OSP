package org.kisti.edison.virtuallaboratory.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.GBatisUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLab;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassImpl;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabImpl;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabUserImpl;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class VirtualLabFinderImpl extends BasePersistenceImpl<VirtualLab> implements VirtualLabFinder{
	public static final String sqlPath2 = "org.kisti.edison.virtuallaboratory.service.persistence.virtualLab.";
	
	public List<Object[]> getVirtualLabList(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabList");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			session = openSession();
			params.put("languageId", locale.toString());
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addScalar("classCount", Type.INTEGER);
			
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
	
	public List<Object[]> getVirtualLabTabList(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		String statusSort = CustomUtil.strNull(params.get("statusSort"));

		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabTabList");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			session = openSession();
			params.put("languageId", locale.toString());
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addScalar("virCount", Type.INTEGER);
			
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
	
	
	public List<Object[]> getVirtualLabAuthList(long groupId, long userId) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabAuthList");
			
			sqlSb.append(sqlQuery);
			
			session = openSession();
			
			Map params = new HashMap();
			
			params.put("groupId", groupId);
			params.put("userId", userId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addScalar("classCount", Type.INTEGER);
			
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
	
	public List<Object[]> getVirtualLabClassRegisterList(long groupId, long userId, long begin, long end) {
		StringBuilder sqlSb = new StringBuilder();
		
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabClassRegisterList");
			
			sqlSb.append(sqlQuery);
			
			session = openSession();
			
			Map params = new HashMap();
			
			if(groupId > 0) {
				params.put("groupId", groupId);
			}
			params.put("userId", userId);
			params.put("begin", String.valueOf(begin));
			params.put("end", String.valueOf(end));
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addEntity("EDVIR_VirtualLabUser", VirtualLabUserImpl.class);
			
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
	
	public Object[] getVirtualLabClassRegisterInfo(long classId, long userId, long groupId) {
		StringBuilder sqlSb = new StringBuilder();
		
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabClassRegisterInfo");
			
			sqlSb.append(sqlQuery);
			
			session = openSession();
			
			Map params = new HashMap();
			
			params.put("groupId", groupId);
			params.put("userId", userId);
			params.put("classId", classId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addEntity("EDVIR_VirtualLabUser", VirtualLabUserImpl.class);
			
			return (Object[]) query.uniqueResult();
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
	
	public List<Object[]> getListVirtualLab(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getListVirtualLab");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			if(statusSort == null || statusSort.equals("0")) {
				params.remove("statusSort");
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addScalar("classCount", Type.INTEGER);
			
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
	
	
	public List<Object[]> getListVirtualLabManagement(Map<String, Object> params, Locale locale) { /*안쓰는 메서드임*/
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getListVirtualLabManagement");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			if(statusSort == null || statusSort.equals("0")) {
				params.remove("statusSort");
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("virtualLabId", Type.LONG);
			query.addScalar("userId", Type.LONG);
			query.addScalar("groupId", Type.LONG);
			query.addScalar("virtualLabTitleSearchField", Type.STRING);
			query.addScalar("virtualLabPersonNameSearchField", Type.STRING);
			query.addScalar("virtualLabConfirmDescription", Type.STRING);
			query.addScalar("virtualLabStatus", Type.LONG);
			query.addScalar("virtualLabRequestDt", Type.DATE);
			query.addScalar("virtualLabUniversityField", Type.LONG);
			query.addScalar("virtualLabConfirmDt", Type.DATE);
			query.addScalar("virtualLabUseYn", Type.STRING);
			query.addScalar("virtualLabDescription", Type.STRING);
			query.addScalar("requestCheck", Type.LONG);
			query.addScalar("virtualLabTitle", Type.STRING);
			query.addScalar("virtualLabPersonName", Type.STRING);
			
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
	
	public List<Object[]> getVirtualLabGroup() {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabGroup");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis( null, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("groupId", Type.LONG);
			query.addScalar("name", Type.STRING);
			
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

	public int getCountVirtualLab(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		params.remove("begin");
		
		try {
			String sqlCountStart = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getListVirtualLab");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabEnd");
			
			sqlSb.append(sqlCountStart);
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlCountEnd);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			if(statusSort == null || statusSort.equals("0")) {
				params.remove("statusSort");
			}
			
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
	
	public int getCountVirtualLabClassRegisterList(long groupId, long userId, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		
		try {
			String sqlCountStart = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabCountClassRegisterList");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabEnd");
			
			sqlSb.append(sqlCountStart);
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlCountEnd);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			Map params = new HashMap();
			
			params.put("groupId", groupId);
			params.put("userId", userId);
			
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
	
	public int getCountVirtualLabList(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		params.remove("begin");
		try {
			String sqlCountStart = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath2 + "getVirtualLabList");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath2 + "getCountVirtualLabEnd");
			
			sqlSb.append(sqlCountStart);
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlCountEnd);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
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
