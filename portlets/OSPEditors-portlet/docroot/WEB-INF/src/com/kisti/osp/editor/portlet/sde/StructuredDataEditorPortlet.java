package com.kisti.osp.editor.portlet.sde;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class StructuredDataEditorPortlet
 */
public class StructuredDataEditorPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(StructuredDataEditorPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String inputData = ParamUtil.getString(renderRequest, "inputData", "");
		boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
		String connector = ParamUtil.getString(renderRequest, "connector", "broadcast");
		String action = ParamUtil.getString(renderRequest, "action", "input");


		renderRequest.setAttribute("inputData", inputData);
		renderRequest.setAttribute("eventEnable", eventEnable);
		renderRequest.setAttribute("connector", connector);
		renderRequest.setAttribute("action", action);
		
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		String command = ParamUtil.getString(resourceRequest, "command");
		String action = ParamUtil.getString(resourceRequest, "action", "input");
		boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;
		
		if( command.equalsIgnoreCase("READ_FILE")){
			Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			Path filePath = parentPath.resolve(fileName);
			
			try {
				FileManagementLocalServiceUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(), isJobResult);
			} catch (PortalException | SystemException e) {
				_log.error("Read file: "+filePath.toString());
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("SAVE_AS")){
			Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			String content = ParamUtil.getString(resourceRequest, "content");
			Path filePath = parentPath.resolve(fileName);
			
			try {
				FileManagementLocalServiceUtil.saveFileContent(resourceRequest, filePath.toString(), content, isJobResult);
			} catch (PortalException | SystemException e) {
				_log.error("Save file: "+filePath.toString());
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("READ_SAMPLE") ){
			long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
			
			try {
				FileManagementLocalServiceUtil.readDLAppEntry(resourceResponse, entryId);
			} catch (SystemException e) {
				_log.error("Read sample: "+entryId);
				throw new PortletException();
			}
		}
		else{
			_log.error("Un-known command: "+command);
			throw new PortletException();
		}
		
		
		super.serveResource(resourceRequest, resourceResponse);
	}

}
