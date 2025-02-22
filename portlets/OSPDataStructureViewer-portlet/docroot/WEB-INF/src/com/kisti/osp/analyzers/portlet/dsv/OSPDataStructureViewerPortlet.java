package com.kisti.osp.analyzers.portlet.dsv;

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
 * Portlet implementation class OSPDataStructureViewerPortlet
 */
public class OSPDataStructureViewerPortlet extends MVCPortlet {
    private static Log _log = LogFactoryUtil.getLog(OSPDataStructureViewerPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{

        boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
        String inputData = ParamUtil.getString(renderRequest, "inputData");
        String connector = ParamUtil.getString(renderRequest, "connector", "connector");
        String mode = ParamUtil.getString(renderRequest, "mode", "VIEW");

        renderRequest.setAttribute("eventEnable", eventEnable);
        renderRequest.setAttribute("inputData", inputData);
        renderRequest.setAttribute("connector", connector);
        renderRequest.setAttribute("mode", mode);

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
        String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_JOBS.toString());

        if(command.equalsIgnoreCase("READ_FILE")){
            try{
                OSPFileUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("READ_IMAGE")){
            try{
            	OSPFileUtil.getFile(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("READ_FIRST_FILE")){
            try{
            	OSPFileUtil.readFirstFileContent(resourceRequest, resourceResponse, parentPath, fileName, repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("GET_FIRST_FILE_NAME")){
            try{
            	OSPFileUtil.getFirstFileName(resourceRequest, resourceResponse, parentPath, fileName, repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("getFirstFileName(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("SAVE_AS")){
            String context = ParamUtil.getString(resourceRequest, "context");
            try{
            	OSPFileUtil.saveFileContent(resourceRequest, filePath.toString(), context, repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("saveFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("CHECK_VALID_FILE")){
            try{
            	OSPFileUtil.checkValidFile(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("checkValidFile(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("CHECK_DUPLICATED")){
            try{
            	OSPFileUtil.duplicated(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("duplicated(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("READ_SAMPLE")){
            long entryId = ParamUtil.getLong(resourceRequest, "dlEntryId");
            try{
            	OSPFileUtil.readDLAppEntry(resourceResponse, entryId);
            }catch (SystemException e){
                _log.error("Read sample: " + entryId);
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("DOWNLOAD_FILE")){
            try{
            	OSPFileUtil.downloadFile(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
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
