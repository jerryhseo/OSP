package org.kisti.edison.simulation.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil;
import org.kisti.edison.simulation.service.BatchMonitoringLocalServiceUtil;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class ScienceAppExecuteScheduler implements MessageListener{
	@Override 
	public void receive(Message message) throws MessageListenerException { 
//		System.out.println("test ScienceAppExecuteScheduler");
		
		boolean batError = false;
		String status = "SUCCESS";
		String batMessage = "";
		long insertId = 0;
		Date exeDate = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date yesterday = new Date();
        yesterday.setTime ( yesterday.getTime ( ) - ( (long) 1000 * 60 * 60 * 24 ) );
        
		String startDt = sdf.format(yesterday);
		String endDt = startDt;
		
		/*scienceAppExecuteInsertDataList Insert*/
		try {
			ScienceAppExecuteLocalServiceUtil.insertCustomScienceAppExecute(startDt, endDt);
		} catch (NoSuchModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			batError = true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			batError = true;
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			batError = true;
		}
		
		if(batError){
			batMessage += BatchMonitoringLocalServiceUtil.getBatchResultMassege(exeDate, startDt, endDt, false);
			status = "FAIL";
		}else{
			batMessage += BatchMonitoringLocalServiceUtil.getBatchResultMassege(exeDate, startDt, endDt, true);
		}
		
		try {
			BatchMonitoringLocalServiceUtil.insertCustomBatchMonitoring("ScienceAppExecute", "N", status,  batMessage, insertId, exeDate);
		} catch (NoSuchModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
