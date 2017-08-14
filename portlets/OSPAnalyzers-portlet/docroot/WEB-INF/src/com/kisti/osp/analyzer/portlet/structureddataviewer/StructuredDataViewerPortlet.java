package com.kisti.osp.analyzer.portlet.structureddataviewer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class StructuredDataViewerPortlet
 */
public class StructuredDataViewerPortlet extends MVCPortlet {
 
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		String method = ParamUtil.getString(resourceRequest, "method");
		Path filePath = Paths.get(ParamUtil.getString(resourceRequest, "fileToLoad"));

		if( method.equalsIgnoreCase("getFileContent") ){
			ServletResponseUtil.write(httpResponse, filePath.toFile());
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
}
