package com.kisti.osp.analyzer.portlet.proteinviewer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
 * Portlet implementation class OSPProteinViewerPortlet
 */
public class OSPProteinViewerPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(OSPProteinViewerPortlet.class);
	
	 
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
        String inputData = ParamUtil.getString(renderRequest, "inputData");
        String connector = ParamUtil.getString(renderRequest, "connector", "connector");
        String action = ParamUtil.getString(renderRequest, "action", "output");

        renderRequest.setAttribute("eventEnable", eventEnable);
        renderRequest.setAttribute("inputData", inputData);
        renderRequest.setAttribute("connector", connector);
        renderRequest.setAttribute("action", action);
		
		super.doView(renderRequest, renderResponse);
	}
	
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws IOException, PortletException{
        String fileName = ParamUtil.getString(resourceRequest, "fileName");
        String parentPath = ParamUtil.getString(resourceRequest, "parentPath");
        Path filePath = Paths.get(parentPath).resolve(fileName);

        String command = ParamUtil.getString(resourceRequest, "command");
        _log.info(command);
        String action = ParamUtil.getString(resourceRequest, "action", "output");
        boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;

        if(command.equalsIgnoreCase("READ_FILE")){
            try{
                FileManagementLocalServiceUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(),
                    isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("GET_FILE")){
            try{
                FileManagementLocalServiceUtil.getFile(
                    resourceRequest, resourceResponse, filePath.toString(), isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("READ_FIRST_FILE")){
            try{
                FileManagementLocalServiceUtil.readFirstFileContent(resourceRequest, resourceResponse, parentPath,
                    fileName, isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("GET_FIRST_FILE_NAME")){
            try{
                FileManagementLocalServiceUtil.getFirstFileName(resourceRequest, resourceResponse, parentPath, fileName,
                    isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("getFirstFileName(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("SAVE_AS")){
            String context = ParamUtil.getString(resourceRequest, "context");
            try{
                FileManagementLocalServiceUtil.saveFileContent(resourceRequest, filePath.toString(), context,
                    isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("saveFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("CHECK_VALID_FILE")){
            try{
                FileManagementLocalServiceUtil.checkValidFile(resourceRequest, resourceResponse, filePath.toString(),
                    isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("checkValidFile(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("CHECK_DUPLICATED")){
            try{
                FileManagementLocalServiceUtil.duplicated(resourceRequest, resourceResponse, filePath.toString(),
                    isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("duplicated(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("READ_SAMPLE")){
            long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
            try{
                FileManagementLocalServiceUtil.readDLAppEntry(resourceResponse, entryId);
            }catch (SystemException e){
                _log.error("Read sample: " + entryId);
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("DOWNLOAD_FILE")){
            try{
                FileManagementLocalServiceUtil.download(resourceRequest, resourceResponse, parentPath,
                    new String[]{fileName}, isJobResult);
            }catch (PortalException | SystemException e){
                _log.error("checkValidFile(): " + filePath.toString());
                throw new PortletException();
            }
        }else{
            _log.error("Un-known command: " + command);
            throw new PortletException();
        }
    }
}
