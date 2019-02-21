package com.kisti.osp.visualizer.portlet.sde;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class StructuredDataEditorPortlet
 */
public class StructuredDataEditorPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(StructuredDataEditorPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		String command = ParamUtil.getString(resourceRequest, "command", "");
		if( command.equalsIgnoreCase("READ_DATATYPE_STRUCTURE") ) {
			String dataTypeName = ParamUtil.getString(resourceRequest, "dataTypeName", "");
			String dataTypeVersion = ParamUtil.getString(resourceRequest, "dataTypeVersion", "");
			
			JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
			try {
				String dataTypeStructure = DataTypeLocalServiceUtil.getDataTypeStructure(dataTypeName, dataTypeVersion);
				jsonResult.put("dataTypeName", dataTypeName);
				jsonResult.put("dataTypeVersion", dataTypeVersion);
				jsonResult.put("structuredData", dataTypeStructure);
			} catch (SystemException e) {
				System.out.println("Data type may not exist: "+ dataTypeName + ", "+dataTypeVersion );
				jsonResult.put("error", "Data type may not exist: "+ dataTypeName + ", "+dataTypeVersion);
			} finally {
				HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
			    ServletResponseUtil.write(httpResponse, jsonResult.toString());
			}
		}
		else {
			OSPFileLocalServiceUtil.processOSPResourceAction(resourceRequest, resourceResponse);
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
}
