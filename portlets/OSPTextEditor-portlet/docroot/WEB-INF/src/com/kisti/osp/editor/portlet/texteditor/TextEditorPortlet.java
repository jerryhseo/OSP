package com.kisti.osp.editor.portlet.texteditor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class TextEditorPortlet
 */
public class TextEditorPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(TextEditorPortlet.class);

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

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {

		Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
		String fileName = ParamUtil.getString(resourceRequest, "fileName");
		String target = parentPath.resolve(fileName).toString();
		
		String command = ParamUtil.getString(resourceRequest, "command");
		String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_HOME.toString());
		
		if( command.equalsIgnoreCase("READ_FILE")){
			try {
				OSPFileUtil.readFileContent(resourceRequest, resourceResponse, target, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("readFileContent(): "+target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("SAVE_AS")){
			String context = ParamUtil.getString(resourceRequest, "context");
			try {
				OSPFileUtil.saveFileContent(resourceRequest, target, context, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("saveFileContent(): "+target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("CHECK_VALID_FILE")){
			try {
				OSPFileUtil.checkValidFile(resourceRequest, resourceResponse, target, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("checkValidFile(): "+target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("CHECK_DUPLICATED") ){
			try {
				OSPFileUtil.duplicated(resourceRequest, resourceResponse, target, repositoryType);
			} catch (PortalException | SystemException e) {
				_log.error("duplicated(): "+target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("READ_DLENTRY") ){
			long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
			
			try {
				OSPFileUtil.readDLAppEntry(resourceResponse, entryId);
			} catch (SystemException e) {
				_log.error("Read dlentry: "+entryId);
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
