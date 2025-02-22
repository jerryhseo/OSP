package org.kisti.edison.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class VirtualLabClassStatisticsSchedule implements MessageListener {
	
	private static Log log = LogFactoryUtil.getLog(VirtualLabClassStatisticsSchedule.class);
	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
		
		try {
			Map params = new HashMap();
			SimulationJobLocalServiceUtil.executeSchedulerOfClassStatistics(params);
		} catch (SystemException e) {
			e.printStackTrace();
			log.error("Failed VirtualLabClass Statistics Scheduler....!!");
		}
		
	}

}
