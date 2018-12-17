package org.kisti.edison.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.kisti.edison.service.CitationsLocalServiceUtil;
import org.kisti.edison.service.ContentsLocalServiceUtil;
import org.kisti.edison.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.service.SiteUserLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class OverviewMonthlySchedule implements MessageListener {
	
	private static Log log = LogFactoryUtil.getLog(OverviewMonthlySchedule.class);
	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
		
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("Start Overview Monthly Scheduler.!!! ---> test schedule time : " + dateForm.format(new Date()));
		
		// ScienceApp Table
		boolean updateScienceAppStatistics = ScienceAppLocalServiceUtil.updateScienceAppStatistics();
		if(!updateScienceAppStatistics){
			log.warn("Failed ScienceApp Statistics Scheduler.!!!");
		}
		
		// Contents Table
		boolean updateContentsStatistics = ContentsLocalServiceUtil.updateContentsStatistics();
		if(!updateContentsStatistics){
			log.warn("Failed Contents Statistics Scheduler.!!!");
		}
		
		// Citations Table
		boolean updateCitationsStatistics = CitationsLocalServiceUtil.updateCitationsStatistics();
		if(!updateCitationsStatistics){
			log.warn("Failed Citations Statistics Scheduler.!!!");
		}
		
		log.info("End Overview Monthly Scheduler.!!! ---> test schedule time : " + dateForm.format(new Date()));
	}

}
