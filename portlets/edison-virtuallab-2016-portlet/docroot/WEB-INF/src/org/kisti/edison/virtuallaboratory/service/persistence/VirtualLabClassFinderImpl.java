package org.kisti.edison.virtuallaboratory.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.GBatisUtil;
import org.kisti.edison.virtuallaboratory.model.VirtualLabClass;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassImpl;
import org.kisti.edison.virtuallaboratory.model.impl.VirtualLabImpl;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class VirtualLabClassFinderImpl extends BasePersistenceImpl<VirtualLabClass> implements VirtualLabClassFinder{
	public static final String sqlPath = "org.kisti.edison.service.persistence.virtualLabClass.";

	public Object[] getVirtualClassInfo(long classId) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getVirtualClassInfo");
			
			sqlSb.append(sqlQuery);
			
			session = openSession();
			
			Map params = new HashMap();
			
			params.put("classId", classId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			
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

	public List<Object[]> getVirtualClassList(Map params) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getVirtualClassList");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addScalar("userCount", Type.INTEGER);
			
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
	
	public int getVirtualClassListCount(Map params) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getVirtualClassListCount");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("TotalCnt", Type.INTEGER);
			
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
	
	public List<Long> getVirtualClassBoardSeqList(long groupId, long classId) {
		Session session=openSession();
		
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.virtualLabClass.getVirtualClassBoardSeqList");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuery); 
			
			Map params = new HashMap();
			
			params.put("groupId", groupId);
			params.put("virtualClassCustomId", "class_" + classId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("boardSeq", Type.LONG);
			
			return (List<Long>) query.list();
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
	
	public List<Object[]> getListVirtualLabClassManagement(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getListVirtualLabClassManagement");
			String sqlLimit = CustomSQLUtil.get(sqlPath + "limit");
			
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlLimit);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			if(statusSort == null || statusSort.equals("0")) {
				params.remove("statusSort");
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addScalar("classCount", Type.INTEGER);
			query.addScalar("tempUserCount", Type.INTEGER);
			query.addScalar("userCount", Type.INTEGER);
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
	
	public List<Object[]> getListVirtualLabClass(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getListVirtualLabClass");
			String sqlLimit = CustomSQLUtil.get(sqlPath + "limit");
			
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlLimit);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			params.put("languageId", locale.toString());
			if(statusSort == null || statusSort.equals("0")) {
				params.remove("statusSort");
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addScalar("classCount", Type.INTEGER);
			query.addScalar("tempUserCount", Type.INTEGER);
			query.addScalar("userCount", Type.INTEGER);
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
	
	public List<Object[]> getVirtualLabClassList(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		try {
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getVirtualLabClassList");
			String sqlLimit = CustomSQLUtil.get(sqlPath + "limit");
			
			sqlSb.append(sqlQuery);
			sqlSb.append(sqlLimit);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addEntity("EDVIR_VirtualLab", VirtualLabImpl.class);
			query.addEntity("EDVIR_VirtualLabClass", VirtualLabClassImpl.class);
			query.addScalar("userCount", Type.INTEGER);
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
	
	public int getCountVirtualLabManagementClass(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		try {
			String sqlCountSelect = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getListVirtualLabClassManagement");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassEnd");
			
			sqlSb.append(sqlCountSelect);
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
	
	public int getCountVirtualLabClass(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		String statusSort = CustomUtil.strNull(params.get("statusSort"));
		
		params.remove("begin");
		
		try {
			String sqlCountSelect = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getListVirtualLabClass");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassEnd");
			
			sqlSb.append(sqlCountSelect);
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
	
	
	public int getCountVirtualLabClassList(Map<String, Object> params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		
		params.remove("begin");
		
		try {
			String sqlCountSelect = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassStart");
			String sqlQuery = CustomSQLUtil.get(sqlPath + "getVirtualLabClassList");
			String sqlCountEnd = CustomSQLUtil.get(sqlPath + "getCountVirtualLabClassEnd");
			
			sqlSb.append(sqlCountSelect);
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
