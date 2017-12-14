package org.kisti.edison.bestsimulation.service.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kisti.edison.bestsimulation.model.SimulationShare;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class SimulationShareFinderImpl extends BasePersistenceImpl<SimulationShare> implements SimulationShareFinder {

	public long getMaxShareSeqNoSimulationShare(int jobSeqNo, String jobUuid, String simulationUuid) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {

			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getMaxShareSeqNoSimulationShare");
	
			sql.append(sqlSelect);
			
			Map params = new HashMap();
			
			params.put("jobSeqNo", jobSeqNo);
			params.put("jobUuid", jobUuid);
			params.put("simulationUuid", simulationUuid);
			
			session=openSession();
			String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
			System.out.println("gBatisQuery : " + gBatisQuery);
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			
			query.addScalar("maxShareSeqNo", Type.LONG);
			
			return (Long) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Object[]> getSharedSimulationJobCustomId(int jobSeqNo, String jobUuid, String simulationUuid) {
		StringBuffer sql = new StringBuffer();
		Session session = null;
		try {
			String sqlSelect = CustomSQLUtil.get("org.kisti.edison.service.persistence.SimulationJob.getListSimulationJob");
			
            sql.append(sqlSelect); 
            
            session=openSession();
            
            Map params = new HashMap();
            
            params.put("jobSeqNo", jobSeqNo);
    		params.put("jobUuid", jobUuid);
    		params.put("simulationUuid", simulationUuid);
    		
            String gBatisQuery = GBatisUtil.getGBatis(params, sql.toString());
            SQLQuery query = session.createSQLQuery(gBatisQuery);
            
            query.addScalar("customId", Type.STRING);
            
            return (List<Object[]>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getSimulationShareCount(int jobSeqNo, String jobUuid, String simulationUuid) {
		return 0;
	}

}
