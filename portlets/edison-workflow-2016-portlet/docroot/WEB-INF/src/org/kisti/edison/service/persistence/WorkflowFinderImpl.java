package org.kisti.edison.service.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.model.impl.WorkflowImpl;
<<<<<<< HEAD
=======
import org.kisti.edison.model.impl.WorkflowInstanceImpl;
import org.kisti.edison.util.EdisonExpndoUtil;
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class WorkflowFinderImpl extends BasePersistenceImpl<Workflow>
    implements WorkflowFinder{
  public static final String GET_WORKFLOWS_HEADER =
      WorkflowFinder.class.getName() +
            ".retrieveWorkflows.header";
  public static final String GET_WORKFLOWS_WHERE =
      WorkflowFinder.class.getName() +
            ".retrieveWorkflows.where";
  public static final String GET_WORKFLOW_INSTANCES_COUNT_HEADER =
      WorkflowFinder.class.getName() +
      ".countWorkflowInstances.header";
  public static final String GET_WORKFLOW_INSTANCES_HEADER =
      WorkflowFinder.class.getName() +
      ".retrieveWorkflowInstances.header";
  public static final String GET_WORKFLOW_INSTANCES_WHERE =
      WorkflowFinder.class.getName() +
      ".retrieveWorkflowInstances.where";
  
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> retrieveWorkflows(Map<String,Object> searchParam, Locale locale) throws SystemException{
    StringBuilder sqlSb = new StringBuilder();
    Session session = null;
    try{
      String sqlQuerySelect = CustomSQLUtil.get(GET_WORKFLOWS_HEADER);
      String sqlQuery = CustomSQLUtil.get(GET_WORKFLOWS_WHERE);
      sqlSb.append(sqlQuerySelect);
      sqlSb.append(sqlQuery);
      
      session = openSession();
      String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
      SQLQuery query = session.createSQLQuery(gBatisQuery);
      query.addEntity("EDWF_Workflow", WorkflowImpl.class);
      query.addScalar("parentTitle", Type.STRING);
      query.addScalar("parentIsPublic", Type.BOOLEAN);
      query.addScalar("parentUserId", Type.BOOLEAN);
      query.addScalar("screenName", Type.STRING);
      query.addScalar("scienceAppId", Type.INTEGER);
      query.addScalar("name", Type.STRING);
      query.addScalar("version", Type.STRING);
      query.addScalar("status", Type.INTEGER);
      
      
      List<Object[]> rows = (List<Object[]>) query.list();
      List<Map<String, Object>> workflows = new ArrayList<Map<String, Object>>();
      
      for(Object[] columns : rows){
        Workflow workflow =  (Workflow)columns[0];
        Map<String, Object> workflowMap = workflow.getModelAttributes();
        workflowMap.put("title", workflow.getTitle(locale));
        workflowMap.put("titleMap", workflow.getTitle());
        workflowMap.put("description", workflow.getDescription(locale));
        workflowMap.put("descriptionMap", workflow.getDescription());
        workflowMap.put("parentTitle", LocalizationUtil
            .getLocalization(GetterUtil.getString(columns[1]), LocaleUtil.toLanguageId(locale)));
        workflowMap.put("parentIsPublic", columns[2]);
        
        if(workflow.getUserId() == GetterUtil.getLong(columns[3])){
          workflowMap.put("parentIsMine", true);
        }else{
          workflowMap.put("parentIsMine", false);
        }
        workflowMap.put("screenName", columns[4]);
        workflowMap.put("scienceAppId", columns[5]);
        workflowMap.put("appName", columns[6]);
        workflowMap.put("appVesion", columns[7]);
        long status = GetterUtil.getLong(columns[8], 0);
        if(status==0){
        	workflowMap.put("status", "");
        	workflowMap.put("statusNm", "");
        }else{
        	workflowMap.put("status", status);
        	workflowMap.put("statusNm", EdisonExpndoUtil.getCommonCdSearchFieldValue(status, EdisonExpando.CDNM, locale));
        }
        workflows.add(workflowMap);
      }
      
      return workflows;
    }catch (Exception e) {
      e.printStackTrace();
      throw new SystemException(e);
    } finally {
      closeSession(session);
    }
  }
  
  public long countWorkflowSimulationJobs(Map<String,Object> searchParam) throws SystemException{
    StringBuilder sqlSb = new StringBuilder();
    Session session = null;
    try{
      String sqlQuerySelect = CustomSQLUtil.get(GET_WORKFLOW_INSTANCES_COUNT_HEADER);
      String sqlQuery = CustomSQLUtil.get(GET_WORKFLOW_INSTANCES_WHERE);
      sqlSb.append(sqlQuerySelect);
      sqlSb.append(sqlQuery);
      
      session = openSession();
      String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
      SQLQuery query = session.createSQLQuery(gBatisQuery);
      query.addScalar("totalCnt", Type.LONG);
      return (Long) query.uniqueResult();
    }catch (Exception e) {
      e.printStackTrace();
      throw new SystemException(e);
    } finally {
      closeSession(session);
    }
  }
  
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> retrieveWorkflowSimulationJobs(Map<String,Object> searchParam, Locale locale) throws SystemException{
    StringBuilder sqlSb = new StringBuilder();
    Session session = null;
    try{
      String sqlQuerySelect = CustomSQLUtil.get(GET_WORKFLOW_INSTANCES_HEADER);
      String sqlQuery = CustomSQLUtil.get(GET_WORKFLOW_INSTANCES_WHERE);
      sqlSb.append(sqlQuerySelect);
      sqlSb.append(sqlQuery);

      session = openSession();
      String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSb.toString());
      SQLQuery query = session.createSQLQuery(gBatisQuery);
      query.addScalar("workflowTitle", Type.STRING);
      query.addScalar("screenName", Type.STRING);
      
      List<Object[]> rows = (List<Object[]>) query.list();
      List<Map<String, Object>> workflowInstances = new ArrayList<Map<String, Object>>();
      for(Object[] columns : rows){
        WorkflowSimulationJob workflowInstance = (WorkflowSimulationJob)columns[0];
        Map<String, Object> workflowInstanceMap = workflowInstance.getModelAttributes();
        if(workflowInstance.getStatus() != null){
          workflowInstanceMap.put("status", StringUtil.upperCaseFirstLetter(workflowInstance.getStatus().toLowerCase()));
        }
        String workflowTitle = LocalizationUtil.getLocalization(
            GetterUtil.getString(columns[1]),
            LocaleUtil.toLanguageId(locale));
        workflowInstanceMap.put("workflowTitle", workflowTitle);
        workflowInstanceMap.put("screenName", columns[2]);
        workflowInstances.add(workflowInstanceMap);
      }
      return workflowInstances;
    }catch (Exception e) {
      e.printStackTrace();
      throw new SystemException(e);
    } finally {
      closeSession(session);
    }
  }
  
  public List<Object[]> getWorkflowMonitoringList(Map<String, Object> searchParams) throws SystemException{
		StringBuilder sqlSb = new StringBuilder();
		Session session = null;
		try{
			String sqlQuery = CustomSQLUtil.get("org.kisti.edison.service.persistence.WorkflowMonitoringFinder.getListWorkflowMonitoring");
			sqlSb.append(sqlQuery);
			
			session = openSession();
			String gBatisQuery = GBatisUtil.getGBatis(searchParams, sqlSb.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("scienceAppId", 			Type.INTEGER);
			query.addScalar("scienceAppName", 			Type.STRING);
			query.addScalar("scienceAppVersion",		Type.STRING);
			query.addScalar("scienceAppTitle", 			Type.STRING);
			
			query.addScalar("simulationId", 			Type.INTEGER);
			query.addScalar("simulationUserId", 		Type.INTEGER);
			query.addScalar("simulationCreateDate", 	Type.STRING);
			query.addScalar("simulationModifiedDate", 	Type.STRING);
			query.addScalar("classId", 					Type.INTEGER);
			query.addScalar("customId", 				Type.INTEGER);
			query.addScalar("simulationTitle", 			Type.STRING);
			query.addScalar("workflowId", 				Type.INTEGER);
			
			query.addScalar("simulationJobId", 			Type.INTEGER);
			query.addScalar("simulationJobUserId", 		Type.INTEGER);
			query.addScalar("simulationJobCreateDate", 	Type.STRING);
			query.addScalar("simulationJobModifiedDate",Type.STRING);
			query.addScalar("simulationJobTitle",		Type.STRING);
			query.addScalar("status", 					Type.STRING);
			query.addScalar("statusResponse", 			Type.STRING);
			query.addScalar("startTime", 				Type.STRING);
			query.addScalar("endTime", 					Type.STRING);
			query.addScalar("workflowUUID", 			Type.STRING);
			query.addScalar("reuseWorkflowUUID", 		Type.STRING);
			query.addScalar("screenLogic", 				Type.STRING);
			
			query.addScalar("userScreenName", 			Type.STRING);
			
			query.addScalar("executeDate", 				Type.STRING);
			query.addScalar("jobCnt", 					Type.STRING);
			
			return (List<Object[]>) query.list();
		}catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
}
