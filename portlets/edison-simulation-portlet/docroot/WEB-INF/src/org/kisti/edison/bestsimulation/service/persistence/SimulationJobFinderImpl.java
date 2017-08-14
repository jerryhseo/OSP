package org.kisti.edison.bestsimulation.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.impl.SimulationImpl;
import org.kisti.edison.bestsimulation.model.impl.SimulationJobImpl;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class SimulationJobFinderImpl extends BasePersistenceImpl<SimulationJob> implements SimulationJobFinder{
	public static final String MONITORING_SQLPATH = "org.kisti.edison.bestsimulation.service.persistence.Monitoring.";
	
	
	public long getMaxJobSeqNoSimulationJob(String simulationUuid) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getMaxJobSeqNoSimulationJob");

            sql.append(sqlSelect);

            Map params = new HashMap();

            params.put("simulationUuid", simulationUuid);
            
            session=openSession();
            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
            
            query.addScalar("maxJobSeqNo", Type.LONG);
                 
            return (Long) query.uniqueResult();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public int deleteSimulationJob(long groupId, String simulationUuid, long jobPhase, long jobSeqNo) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlDelete = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.deleteSimulationJob");

            sql.append(sqlDelete);

            Map params = new HashMap();
            
            params.put("groupId", groupId);
            params.put("simulationUuid", simulationUuid);
            params.put("jobPhase", jobPhase);
            params.put("jobSeqNo", jobSeqNo);
            
            session=openSession();

            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
       
            return query.executeUpdate();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteSimulationCommandOption(long groupId, String simulationUuid, long optionSeq) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlDelete = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationCommandOption.deleteSimulationCommandOption");

            sql.append(sqlDelete);

            Map params = new HashMap();
            
            params.put("groupId", groupId);
            params.put("simulationUuid", simulationUuid);
            params.put("optionSeq", optionSeq);
            
            session=openSession();

            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
       
            return query.executeUpdate();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteSimulationParameter(long groupId, String simulationUuid, long jobSeqNo) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlDelete = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationParameter.deleteSimulationParameter");

            sql.append(sqlDelete);

            Map params = new HashMap();
            
            params.put("groupId", groupId);
            params.put("simulationUuid", simulationUuid);
            params.put("jobSeqNo", jobSeqNo);
            
            session=openSession();

            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
       
            return query.executeUpdate();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public List<SimulationJob> getListSimulationJob(long groupId, String simulationUuid, long jobPhase, long jobSeqNo) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getListSimulationJob");
			
            sql.append(sqlSelect); 
            
            session=openSession();
            
            Map params = new HashMap();
            
            params.put("groupId", groupId);
            params.put("simulationUuid", simulationUuid);
            params.put("jobPhase", jobPhase);
            if(jobSeqNo > 0) {
            	params.put("jobSeqNo", jobSeqNo);
            }
            
            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            
            SQLQuery query = session.createSQLQuery(gBatisQuery);
            
            query.addEntity("EDSIM_SimulationJob", SimulationJobImpl.class);
            
            return (List<SimulationJob>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//모니터링 리스트 조회
	public List<Object[]> getMonitoringList(long groupId, long userId,String searchValue, long jobStatus, long classId, long customId, int begin, int end) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(MONITORING_SQLPATH + "getListMonitoringHeader");
			String sqlQuery = CustomSQLUtil.get(MONITORING_SQLPATH + "getMonitoringBody");
			sqlSb.append(sqlQuerySelect);
			sqlSb.append(sqlQuery);
			
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			if(userId > 0) {
				params.put("userId", userId);
			}
			params.put("searchValue", searchValue);
			if(jobStatus > 0) {
				params.put("jobStatus", jobStatus);
			}
			if(classId > 0){
				params.put("classId", classId);
			}
			if(customId > 0){
				params.put("customId", customId);
			}
			params.put("begin", begin);
			params.put("end", end);
//			params.put("jobSeqNo", 1);
			params.put("groupList", "Y");
			
			
			//관리자 및 가상실험실에서 조회 할 경우 userId값이 0.
			//검색 값이 있을 경우에는 admin 조회로 변경
			if(userId==0 &&!searchValue.equals("")){
				params.put("searchValue", "");
				params.put("searchValueAndUserId", searchValue);
			}
			
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDSIM_Simulation", SimulationImpl.class);
			query.addEntity("EDSIM_SimulationJob", SimulationJobImpl.class);
			query.addScalar("stayDt", Type.STRING);
			query.addScalar("executeDt", Type.STRING);
			query.addScalar("jobCnt", Type.STRING);
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}

	//모니터링 카운트 조회
	public int getMonitoringCount(long groupId, long userId,String searchValue, long jobStatus, long classId, long customId)throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(MONITORING_SQLPATH + "getCountMonitoringList");
//			String sqlQuery = CustomSQLUtil.get(MONITORING_SQLPATH + "getMonitoringBody");
			sqlSb.append(sqlQuerySelect);
//			sqlSb.append(sqlQuery);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			if(userId > 0) {
				params.put("userId", userId);
			}
			params.put("searchValue", searchValue);
			if(jobStatus > 0) {
				params.put("jobStatus", jobStatus);
			}
			if(classId > 0){
				params.put("classId", classId);
			}
			if(customId > 0){
				params.put("customId", customId);
			}
//			params.put("jobSeqNo", 1);
			params.put("groupList", "Y");

			//관리자 및 가상실험실에서 조회 할 경우 userId값이 0.
			//검색 값이 있을 경우에는 admin 조회로 변경
			if(userId==0 &&!searchValue.equals("")){
				params.put("searchValue", "");
				params.put("searchValueAndUserId", searchValue);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);
			
			cnt = (Integer) query.uniqueResult();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return cnt;
	}
	
	//모니터링 시뮬레이션 JOB 조회
	public List<Object[]> getMonitoringJobList(long groupId,String simulationUuid,long jobSeqNo) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(MONITORING_SQLPATH + "getListMonitoringHeader");
			String sqlQuery = CustomSQLUtil.get(MONITORING_SQLPATH + "getMonitoringBody");
			sqlSb.append(sqlQuerySelect);
			sqlSb.append(sqlQuery);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("simulationUuid", simulationUuid);
			if(jobSeqNo > 0) {
				params.put("jobSeqNo", jobSeqNo);
			}
			params.put("jobListSearch", "Y");
			
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDSIM_Simulation", SimulationImpl.class);
			query.addEntity("EDSIM_SimulationJob", SimulationJobImpl.class);
			query.addScalar("stayDt", Type.STRING);
			query.addScalar("executeDt", Type.STRING);
			query.addScalar("jobCnt", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	//시뮬레이션 프로젝트 모니터링 JOB 조회
	public List<Object[]> getSimulationMonitoringJobList(long classId, long customId, int begin, int end) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(MONITORING_SQLPATH + "getListMonitoringHeader");
			String sqlQuery = CustomSQLUtil.get(MONITORING_SQLPATH + "getSimProMonitoringBody");
			sqlSb.append(sqlQuerySelect);
			sqlSb.append(sqlQuery);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();

			if(classId > 0){
				params.put("classId", classId);
			}
			if(customId > 0){
				params.put("customId", customId);
			}
			
			params.put("begin", begin);
			params.put("end", end);
			params.put("jobListSearch", "Y");
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addEntity("EDSIM_Simulation", SimulationImpl.class);
			query.addEntity("EDSIM_SimulationJob", SimulationJobImpl.class);
			query.addScalar("stayDt", Type.STRING);
			query.addScalar("executeDt", Type.STRING);
			query.addScalar("jobCnt", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	//시뮬레이션 프로젝트 모니터링 JOB 카운트
	public int getSimulationMonitoringJobCount(long classId, long customId)throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		int cnt = 0;
		try{
			String sqlQuerySelect = CustomSQLUtil.get(MONITORING_SQLPATH + "getCountMonitoringHeader");
			String sqlQuery = CustomSQLUtil.get(MONITORING_SQLPATH + "getSimProMonitoringBody");
			sqlSb.append(sqlQuerySelect);
			sqlSb.append(sqlQuery);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();

			if(classId > 0){
				params.put("classId", classId);
			}
			if(customId > 0){
				params.put("customId", customId);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("totalCnt", Type.INTEGER);
			
			cnt = (Integer) query.uniqueResult();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
		return cnt;
	}
	

	
	//시뮬레이션 JobSeqNo - Update
	public int updateSimulationJobSetJobSeqNo(long V_jobSeqNo,long jobSeqNo, String simulationUuid, long groupId) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String updateSql = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.updateJobSeqNoSimulationJob");
			sqlSb.append(updateSql);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("V_jobSeqNo", V_jobSeqNo);
			params.put("jobSeqNo", jobSeqNo);
			params.put("simulationUuid", simulationUuid);
			params.put("groupId", groupId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			return query.executeUpdate();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	//SimulationJob > jobStatus Update
	public Long getJobSeqNoSimulationJob(long groupId, String simulationUuid, String jobUuid) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String updateSql = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getJobSeqNoSimulationJob");
			sqlSb.append(updateSql);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("simulationUuid", simulationUuid);
			params.put("jobUuid", jobUuid);

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("jobSeqNo", Type.LONG);
			
			Object result = query.uniqueResult();
			
			if(result == null){
				return 0L;
			}else{
				return (Long) query.uniqueResult();
			}
			
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}


	public long getStatusSimulationJobStatus(long groupId,  String simulationUuid, String jobUuid){
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String updateSql = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getStatusSimulationJobStatus");
			sqlSb.append(updateSql);
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("simulationUuid", simulationUuid);
			params.put("jobUuid", jobUuid);

			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("jobStatus", Type.LONG);
			
			Object result = query.uniqueResult();
			
			if(result == null){
				return 0L;
			}else{
				return (Long) query.uniqueResult();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return 0;
	}
	

	public long getMaxStatusSeqSimulationJobStatus(long groupId, String simulationUuid, String jobUuid) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getMaxStatusSeqSimulationJobStatus");

            sql.append(sqlSelect);

            Map params = new HashMap();

            params.put("groupId", groupId);
            params.put("simulationUuid", simulationUuid);
			params.put("jobUuid", jobUuid);
            
            session=openSession();
            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
            
            query.addScalar("maxStatusSeq", Type.LONG);
                 
            return (Long) query.uniqueResult();
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<SimulationJob> getMigrationSimulationJobStatus(long groupId, long jobStatus, boolean dateUpdate) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getListSimulationJob");
			
            sql.append(sqlSelect); 
            
            session=openSession();
            
            Map params = new HashMap();
            
            if(groupId > 0) {
            	params.put("groupId", groupId);
            }
            if(jobStatus > 0) {
            	params.put("jobStatus", jobStatus);
            }
            
            if(dateUpdate){
            	params.put("dateUpdate", dateUpdate);
            }
            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
            
            query.addEntity("EDSIM_SimulationJob", SimulationJobImpl.class);
            
            return (List<SimulationJob>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	/**
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ##### 통계 Service #########################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 */

	//기관 정보에 따른 실험실 목록
	public List<Object[]> getListVirtualLabSearchUni(long groupId, String languageId, long jobUniversityField) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getListVirtualLabSearchUni");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("languageId", languageId);
			if (jobUniversityField > 0) {
				params.put("jobUniversityField", jobUniversityField);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("virtualLabId", Type.BIG_INTEGER);
			query.addScalar("virtualLabTitle", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	
	//실험실정보에 따른 수업목록
	public List<Object[]> getListVirtualClassSearchLab(long groupId, String languageId, long jobVirtualLabId) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getListVirtualClassSearchLab");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("languageId", languageId);
			params.put("jobVirtualLabId", jobVirtualLabId);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("classId", Type.BIG_INTEGER);
			query.addScalar("classTitle", Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	/**
	 * ##### 2. Sw START ###################################################################################################################################################
	 */
	public List<Object[]> getStatisticsSwTableOrganigation(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsSwTableOrganigation");

			sqlSb.append(sqlSelect);			
			
			session = openSession();

			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("universityId", Type.BIG_INTEGER);
			query.addScalar("cnt", Type.INTEGER);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public List<Object[]> getStatisticsSwBarChartDate(Map<String,Object> searchParam) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsSwBarChartDate");
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
	/**
	 * ##### 2. Sw END ###################################################################################################################################################
	 */	
	
	/**
	 * ##### 5. User START ###################################################################################################################################################
	 */	
	public List<Object[]> getStatisticsUserTableOrganigation(long groupId, long columnId, String startDt, String endDt) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsUserTableOrganigation");

			sqlSb.append(sqlSelect);			
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("columnId", columnId);
			params.put("startDt", startDt);
			params.put("endDt", endDt);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("affiliation", Type.STRING);
			query.addScalar("userCnt", Type.INTEGER);
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}


	public List<Object[]> getStatisticsUserBarChartDate(long groupId, long columnId, String startDt, String endDt) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.statistics.getStatisticsUserBarChartDate");
			sqlSb.append(sqlSelect);			
			
			session = openSession();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("groupId", groupId);
			params.put("columnId", columnId);
			params.put("startDt", startDt);
			params.put("endDt", endDt);
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("month", Type.STRING);
			query.addScalar("userCnt", Type.INTEGER);
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	/**
	 * ##### 5. User END ###################################################################################################################################################
	 */
	

	public List<Object[]> getVirtualClassStatisticsList(Map params, Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.statistics.getVirtualClassStatisticsList");
			
			sqlSb.append(sqlQuery);
			
			String sql = sqlSb.toString();
			
			session = openSession();
			params.put("languageId", locale.toString());
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("groupId", Type.INTEGER);//0
			query.addScalar("university", Type.STRING);//0
			query.addScalar("virtualLabId", Type.LONG);//1
			query.addScalar("virtualLabTitle", Type.STRING);//1
			query.addScalar("classTitle", Type.STRING);//1
			query.addScalar("virtualLabPersonName", Type.STRING);//2
			query.addScalar("classId", Type.STRING);//4
			query.addScalar("executeCount", Type.INTEGER);//5
			query.addScalar("executeStudentcount", Type.INTEGER);//6
			query.addScalar("scienceAppId", Type.STRING);//7
			query.addScalar("avgerageRuntime", Type.INTEGER);//8
			
			return (List<Object[]>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession(session);
		}
		return null;
	}
	
	
	public int getCountVirtualClassStatistics(Map params , Locale locale) {
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try {
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.statistics.getCountVirtualClassStatistics");
			
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
	
	
	/**
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ##### EDISON 프로젝트 앱, 시뮬레이션 통계 Service #########################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 * ###########################################################################################################################################################################
	 */
	public List<Object[]> getMajorAchievementsListForProjectStatistics(String projectYn, String key){
		Session session=openSession();
		try{
			String select = CustomSQLUtil.get("org.kisti.edison.project.statistics.getMajorAchievementsSelectHeader");
			
			StringBuffer sql = new StringBuffer();
			sql.append(select);
			
			Map param = new HashMap();

			param.put("projectYn", projectYn);
			param.put("propertyKey", key);
			
			String gBatisQuery = GBatisUtil.getGBatis(param, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("categoryId", Type.LONG); 
			query.addScalar("name", Type.STRING); 
			query.addScalar("key_", Type.STRING); 
			query.addScalar("value", Type.STRING); 
			query.addScalar("CONCNT", Type.INTEGER); 
			query.addScalar("APPCNT", Type.INTEGER); 
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	
	
	
	public List<Object[]> getScienceAppCenterListForProjectStatistics(String propertyKey){
		Session session=openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get("org.kisti.edison.project.statistics.getRegistSwContentHeader");
			String sqlQueryJoin = CustomSQLUtil.get("org.kisti.edison.project.statistics.getRegistSwBody");
			String sqlQueryGroupBy = CustomSQLUtil.get("org.kisti.edison.project.statistics.getRegistSwContentFrom");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			sql.append(sqlQueryJoin);
			sql.append(sqlQueryGroupBy);
			

			Map param = new HashMap();
			param.put("propertyKey", propertyKey);
			
			String gBatisQuery = GBatisUtil.getGBatis(param, sql.toString());
			
			SQLQuery query = session.createSQLQuery(gBatisQuery);

			query.addScalar("categoryId", Type.LONG); 
			query.addScalar("name", Type.STRING); 
			query.addScalar("key_", Type.STRING); 
			query.addScalar("value", Type.STRING); 
			query.addScalar("APPCNT", Type.INTEGER); 
			
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
	
	
	public List<Object[]> getAppDetailListForProjectStatistics(long jobPhase, long columnId, long categoryId, String languageId){
		Session session=openSession();
		try{
			String sqlQuerySelect = CustomSQLUtil.get("org.kisti.edison.project.statistics.getAppDetail");
			
			StringBuffer sql = new StringBuffer();
			sql.append(sqlQuerySelect);
			
			Map params = new HashMap();
			
			params.put("jobPhase", jobPhase);
			params.put("columnId", columnId);
			params.put("languageId", languageId);
			
			if(categoryId != 0){
				params.put("categoryId", categoryId);
			}
			
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			query.addScalar("scienceAppId", Type.LONG); 
			query.addScalar("groupId", Type.LONG); 
			query.addScalar("categoryId", Type.LONG); 
			query.addScalar("name", Type.STRING); 
			query.addScalar("key_", Type.STRING); 
			query.addScalar("value", Type.STRING); 
			query.addScalar("title", Type.STRING); 
			query.addScalar("data_", Type.LONG); 
			query.addScalar("authorId", Type.LONG); 
			query.addScalar("firstName", Type.STRING); 
			query.addScalar("screenName", Type.STRING); 
			query.addScalar("version", Type.STRING); 
			query.addScalar("runtime", Type.LONG); 
			query.addScalar("executeCount", Type.INTEGER); 
			query.addScalar("averageRuntime", Type.LONG); 
			query.addScalar("userCount", Type.INTEGER); 
			query.addScalar("createDate", Type.STRING); 
			
			return (List<Object[]>) query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return null;
	}
}