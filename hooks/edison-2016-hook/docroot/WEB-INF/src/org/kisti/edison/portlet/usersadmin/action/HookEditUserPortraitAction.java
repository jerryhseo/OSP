package org.kisti.edison.portlet.usersadmin.action;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.util.PortalUtil;

public class HookEditUserPortraitAction extends BaseStrutsPortletAction{
	
	private static Log _log = LogFactoryUtil.getLog(HookEditUserPortraitAction.class);
	
	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {
		
		_log.debug("******************************************PROCESSACTION*****************************************");
		debugParameters(actionRequest.getParameterMap(),actionRequest.getWindowID());
		
		
		UploadPortletRequest uploadPortletRequest =PortalUtil.getUploadPortletRequest(actionRequest);
		File file = uploadPortletRequest.getFile("fileName");
		_log.debug("FILE--->"+file.getName());
		_log.debug("******************************************UPLOADPORTLETREQUEST*****************************************");
		debugParameters(uploadPortletRequest.getParameterMap(),actionRequest.getWindowID());
		originalStrutsPortletAction.processAction(portletConfig, actionRequest, actionResponse);
	}
	
	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {
		_log.debug("******************************************RENDER*****************************************");
		
		
		debugParameters(renderRequest.getParameterMap(),renderRequest.getWindowID());
		return originalStrutsPortletAction.render(portletConfig, renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
			throws Exception {
		
		_log.debug("******************************************SERVERESOURCE*****************************************");
		debugParameters(resourceRequest.getParameterMap(),resourceRequest.getWindowID());
		
		originalStrutsPortletAction.serveResource(portletConfig, resourceRequest, resourceResponse);
	}
			
	
	
	private static void debugParameters(Map param, String serverName) {
		Iterator iter = param.entrySet().iterator();
		Map.Entry entry = null;
		String paramName = null;
		String[] paramValues = null;

		_log.debug("**************************************************************************************************************");
		_log.debug("***** "+serverName+".do DEBUGGING PARAMETERS *****************************************************************");
		_log.debug("**************************************************************************************************************");
		while (iter.hasNext()) {
			entry = (Map.Entry) iter.next();
			paramName = (String) entry.getKey();			
			
			if(entry.getValue() instanceof String){
				paramValues = new String[]{CustomUtil.strNull(entry.getValue())};
			}else{
				paramValues = (String[]) entry.getValue();	
			}
			
			if(paramValues == null){
				_log.debug(paramName + " : [file type]");
			}else{
				for (int i = 0; i < paramValues.length; i++) {
					_log.debug(paramName + " : [" + paramValues[i] + "]");
				}
			}
		}
		_log.debug("**************************************************************************************************************");
	}
}
