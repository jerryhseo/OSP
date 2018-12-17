package org.kisti.edison.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.kisti.edison.service.ExecuteUserLocalServiceUtil;
import org.kisti.edison.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.service.SiteUserLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class OverviewDailySchedule implements MessageListener {
	
	private static Log log = LogFactoryUtil.getLog(OverviewDailySchedule.class);
	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("Start Overview Daily Scheduler.!!! ---> test schedule time : " + dateForm.format(new Date()));
		
		// SiteUser Table
		boolean updateSiteUserStatistics = SiteUserLocalServiceUtil.updateSiteUserStatistics();
		if(!updateSiteUserStatistics){
			log.warn("Failed Site User Statistics Scheduler.!!!");
		}
		
		// ExecuteUser Table
		boolean updateExecuteUserStatistics = ExecuteUserLocalServiceUtil.updateExecuteUserStatistics();
		if(!updateExecuteUserStatistics){
			log.warn("Failed Execute User Statistics Scheduler.!!!");
		}
		
		// SimulationJob Table
		boolean updateSimulationJobStatistics = SimulationJobLocalServiceUtil.updateSimulationJobStatistics();
		if(!updateSimulationJobStatistics){
			log.warn("Failed SimulationJob Statistics Scheduler.!!!");
		}
		
		log.info("End Overview Daily Scheduler.!!! ---> test schedule time : " + dateForm.format(new Date()));
	}

}
