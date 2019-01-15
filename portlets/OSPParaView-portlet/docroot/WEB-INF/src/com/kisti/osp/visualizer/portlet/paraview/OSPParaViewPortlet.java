package com.kisti.osp.visualizer.portlet.paraview;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class OSPParaViewPortlet
 */
public class OSPParaViewPortlet extends MVCPortlet {
	private static final String  launcherURL = "https://www.edison.re.kr/paraview";

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
	        throws IOException, PortletException {

		renderRequest.setAttribute("launcherURL", launcherURL);
		super.doView(renderRequest, renderResponse);
	}
	
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws IOException, PortletException{

		OSPFileLocalServiceUtil.processOSPResourceAction(resourceRequest, resourceResponse);
    }
}
