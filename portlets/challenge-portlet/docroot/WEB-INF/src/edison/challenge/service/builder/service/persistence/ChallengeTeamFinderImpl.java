package edison.challenge.service.builder.service.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.kisti.edison.bestsimulation.model.Simulation;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.GBatisUtil;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
//import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import edison.challenge.service.builder.model.ChallengeTeam;

public class ChallengeTeamFinderImpl extends BasePersistenceImpl<ChallengeTeam> implements ChallengeTeamFinder{
	public List<String> getChallengeTeamAppList(String teamID, String startDate, String endDate){
		Map<String, Object> searchParam = new HashMap<String, Object>();
		searchParam.put("startDate", startDate);
		searchParam.put("endDate", endDate);
		searchParam.put("teamID", teamID);
		StringBuilder sqlSB = new StringBuilder();
		
		SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			String sql = CustomSQLUtil.get("edison.challenge.service.builder.service.persistence.getTeamAppList");
			//System.out.println("\n\n\n\n\n\n\n\n\n\n");
			//System.out.println("test1 ; " + sql);
			sqlSB.append(sql);
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSB.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			//System.out.println("\n\n\n\n\ntest class Name : "+Simulation.class.toString());      //org.kisti.edison.science.model.impl.
			//query.addEntity("EDSIM_Simulation", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.bestsimulation.model.Simulation"));
			//query.addEntity("EDSIM_SimulationJob", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.bestsimulation.model.SimulationJob"));
			//query.addEntity("EDAPP_ScienceApp", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.science.model.ScienceApp"));
			//query.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
			//System.out.println(" gBatisQuery : "+gBatisQuery);
			//System.out.println("\n\n");
			
			//System.out.println("test execute sql Test : "+ query.list().toString());
			return (List<String>)query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sessionFactory.closeSession(session);
		}
		return null;
	}
	public String getCPUUseage(String teamID, String startDate, String endDate){
		String result = "";
		//edison.challenge.service.builder.service.persistence.getCPUUseage
		Map<String, Object> searchParam = new HashMap<String, Object>();
		searchParam.put("startDate", startDate);
		searchParam.put("endDate", endDate);
		searchParam.put("teamID", teamID);
		StringBuilder sqlSB = new StringBuilder();
		
		SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
		
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			String sql = CustomSQLUtil.get("edison.challenge.service.builder.service.persistence.getCPUUseage");
			//System.out.println("\n\n\n\n\n\n\n\n\n\n");
			//System.out.println("test1 ; " + sql);
			sqlSB.append(sql);
			String gBatisQuery = GBatisUtil.getGBatis(searchParam, sqlSB.toString());
			SQLQuery query = session.createSQLQuery(gBatisQuery);
			//System.out.println("\n\n\n\n\ntest class Name : "+Simulation.class.toString());      //org.kisti.edison.science.model.impl.
			//query.addEntity("EDSIM_Simulation", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.bestsimulation.model.Simulation"));
			//query.addEntity("EDSIM_SimulationJob", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.bestsimulation.model.SimulationJob"));
			//query.addEntity("EDAPP_ScienceApp", PortalClassLoaderUtil.getClassLoader().loadClass("org.kisti.edison.science.model.ScienceApp"));
			//query.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
			//System.out.println(" gBatisQuery : "+gBatisQuery);
			//System.out.println("\n\n");
			
			//System.out.println("test execute sql Test : "+ String.valueOf(query.uniqueResult()));
			result = String.valueOf(query.uniqueResult());
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sessionFactory.closeSession(session);
		}
		return "0";
	}
}
