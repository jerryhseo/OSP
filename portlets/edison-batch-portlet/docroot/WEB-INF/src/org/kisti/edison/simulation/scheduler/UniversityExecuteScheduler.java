package org.kisti.edison.simulation.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.simulation.service.BatchMonitoringLocalServiceUtil;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

public class UniversityExecuteScheduler implements MessageListener{
	@Override 
	public void receive(Message message) throws MessageListenerException { 
//		System.out.println("test UniversityExecuteScheduler");
		
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
		
		ExpandoTable table;
		long columnId = 0;
		try {
			table = ExpandoTableLocalServiceUtil.getTable(PortalUtil.getDefaultCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			columnId = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), EdisonExpando.USER_UNIVERSITY).getColumnId();
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/*UniversityExecuteInsertDataList Insert*/
		try {
			UniversityExecuteLocalServiceUtil.insertCustomUniversityExecute(columnId, startDt, endDt);
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
			BatchMonitoringLocalServiceUtil.insertCustomBatchMonitoring("UniversityExecute", "N", status,  batMessage, insertId, exeDate);
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
