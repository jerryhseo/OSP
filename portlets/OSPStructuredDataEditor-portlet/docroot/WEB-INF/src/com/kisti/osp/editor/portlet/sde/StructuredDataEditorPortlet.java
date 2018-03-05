package com.kisti.osp.editor.portlet.sde;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.util.OSPFileUtil;
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
		String mode = ParamUtil.getString(renderRequest, "mode", "input");


		renderRequest.setAttribute("inputData", inputData);
		renderRequest.setAttribute("eventEnable", eventEnable);
		renderRequest.setAttribute("connector", connector);
		renderRequest.setAttribute("mode", mode);
		
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		String command = ParamUtil.getString(resourceRequest, "command");
		String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_HOME.toString());
		
		if( command.equalsIgnoreCase("READ_FILE")){
			Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			String targetPath = parentPath.resolve(fileName).toString();
			
			try {
				OSPFileUtil.readFileContent(resourceRequest, resourceResponse, targetPath, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("Read file: "+e.getMessage());
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("SAVE_AS")){
			Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			String content = ParamUtil.getString(resourceRequest, "content");
			String targetPath = parentPath.resolve(fileName).toString();
			
			try {
				OSPFileUtil.saveFileContent(resourceRequest, targetPath, content, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("Save file: "+e.getMessage());
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("READ_DLENTRY") ){
			long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
			
			try {
				OSPFileUtil.readDLAppEntry(resourceResponse, entryId);
			} catch (SystemException e) {
				_log.error("Read dlentry: "+e.getMessage());
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
