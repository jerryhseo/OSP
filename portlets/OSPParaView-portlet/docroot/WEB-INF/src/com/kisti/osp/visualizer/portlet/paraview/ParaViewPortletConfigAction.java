package com.kisti.osp.visualizer.portlet.paraview;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

public class ParaViewPortletConfigAction extends DefaultConfigurationAction {
	@Override
	public void processAction(
	    PortletConfig portletConfig, ActionRequest actionRequest,
	    ActionResponse actionResponse) throws Exception {  

	    super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
