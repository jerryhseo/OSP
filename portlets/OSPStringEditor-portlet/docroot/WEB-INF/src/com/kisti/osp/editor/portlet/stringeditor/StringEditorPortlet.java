package com.kisti.osp.editor.portlet.stringeditor;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class StringEditorPortlet
 */
public class StringEditorPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String inputData = ParamUtil.getString(renderRequest, "inputData");
		boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
		String connector = ParamUtil.getString(renderRequest, "connector", "broadcast");
		String mode = ParamUtil.getString(renderRequest, "mode", "VIEW");

		renderRequest.setAttribute("inputData", inputData);
		renderRequest.setAttribute("eventEnable", eventEnable);
		renderRequest.setAttribute("connector", connector);
		renderRequest.setAttribute("mode", mode);
		
		super.doView(renderRequest, renderResponse);
	}
}
