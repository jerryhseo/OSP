package org.kisti.edison.simulation.portlet.monitoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowStateException;

import org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.simulation.service.BatchMonitoringLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class EdisonBatchMonitoringController {
	
	protected static Log  log = LogFactoryUtil.getLog(EdisonBatchMonitoringController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, WindowStateException {
		Map params = RequestUtil.getParameterMap(request);
		
		String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
		
		model.addAttribute("currentPage", CustomUtil.strNull(params.get("currentPage"), "1"));
		model.addAttribute("startDt", CustomUtil.strNull(params.get("startDt"), toDay));
		model.addAttribute("endDt", CustomUtil.strNull(params.get("endDt"), toDay));
		
		return "batchMonitoring/list"; 
	}
	
	@RenderMapping(params = "myRender=batchMonitoringRender")
	public String batchMonitoringRender(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, WindowStateException {
		Map params = RequestUtil.getParameterMap(request);
		
		String toDay = CustomUtil.dateToStringFormat(new Date(), "yyyy-MM-dd");
		
		model.addAttribute("currentPage", CustomUtil.strNull(params.get("currentPage"), "1"));
		model.addAttribute("startDt", CustomUtil.strNull(params.get("startDt"), toDay));
		model.addAttribute("endDt", CustomUtil.strNull(params.get("endDt"), toDay));
		
		return "batchMonitoring/list"; 
	}
	
	@ResourceMapping(value="getBatchMonitoringList")
	public void getStatisticsExec(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, ParseException, IOException{
		Map params = RequestUtil.getParameterMap(request);

		int currentPage = Integer.parseInt(CustomUtil.strNull(params.get("currentPage"), "1"));
		int listSize = Integer.parseInt(CustomUtil.strNull(params.get("listSize"), "10"));
		int blockSize = Integer.parseInt(((PortletRequest) request).getPreferences().getValue("blockSize", "10"));
		int begin = ((currentPage - 1) * listSize);
		int end = listSize;
		
		List batchMonitoringList  = BatchMonitoringLocalServiceUtil.getCustomBatchMonitoringList(begin, end);
		int totalCount = BatchMonitoringLocalServiceUtil.getCustomBatchMonitoringCount();
		String paging = PagingUtil.getPaging(request.getContextPath(), CustomUtil.strNull(params.get("methodName")), totalCount, currentPage, listSize, blockSize);
		
		JSONObject obj = new JSONObject();
		obj.put("batchMonitoringList", batchMonitoringList);
		obj.put("seq", totalCount - ((currentPage - 1)*listSize));
		obj.put("paging", paging);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ActionMapping(params="myAction=scienceAppExecuteBatch")
	public void scienceAppExecuteBatchAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
			Map params = RequestUtil.getParameterMap(upload);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			boolean batError = false;
			String status = "SUCCESS";
			String batMessage = "";
			long insertId = themeDisplay.getUserId();
			Date exeDate = new Date();
	        
			String startDt = CustomUtil.strNull(params.get("startDt"));
			String endDt = CustomUtil.strNull(params.get("endDt"));
			
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
				BatchMonitoringLocalServiceUtil.insertCustomBatchMonitoring("ScienceAppExecute", "Y", status,  batMessage, insertId, exeDate);
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
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myRender", "batchMonitoringRender");			
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage"), "1"));
			portletURL.setParameter("startDt", startDt);
			portletURL.setParameter("endDt", endDt);
			
			SessionMessages.add(request, EdisonMessageConstants.BATCH_EXECUTE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.BATCH_EXECUTE_ERROR);
		}
	}
	
	@ActionMapping(params="myAction=universityExecuteBatch")
	public void scienceAppExecuteAction(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			UploadPortletRequest upload = com.liferay.portal.util.PortalUtil.getUploadPortletRequest(request);			
			Map params = RequestUtil.getParameterMap(upload);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			
			boolean batError = false;
			String status = "SUCCESS";
			String batMessage = "";
			long insertId = themeDisplay.getUserId();
			Date exeDate = new Date();
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String startDt = CustomUtil.strNull(params.get("startDt"));
			String endDt = CustomUtil.strNull(params.get("endDt"));
			
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
			
			
			/*universityExecuteInsertDataList Insert*/
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
				BatchMonitoringLocalServiceUtil.insertCustomBatchMonitoring("UniversityExecute", "Y", status,  batMessage, insertId, exeDate);
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
			
			PortletURL portletURL = PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setParameter("myRender", "batchMonitoringRender");			
			portletURL.setParameter("currentPage", CustomUtil.strNull(params.get("currentPage"), "1"));
			portletURL.setParameter("startDt", startDt);
			portletURL.setParameter("endDt", endDt);
			
			SessionMessages.add(request, EdisonMessageConstants.BATCH_EXECUTE_SUCCESS);
			
			response.sendRedirect(portletURL.toString());
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.BATCH_EXECUTE_ERROR);
		}
	}
	
}
