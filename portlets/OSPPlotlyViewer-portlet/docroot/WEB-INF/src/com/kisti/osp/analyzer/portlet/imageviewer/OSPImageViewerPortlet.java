package com.kisti.osp.analyzer.portlet.imageviewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class OSPImageViewerPortlet
 */
public class OSPImageViewerPortlet extends MVCPortlet {
    private static Log _log = LogFactoryUtil.getLog(OSPImageViewerPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{

        boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
        String inputData = ParamUtil.getString(renderRequest, "inputData");
        String connector = ParamUtil.getString(renderRequest, "connector", "connector");
        String mode = ParamUtil.getString(renderRequest, "mode", "VIEW");

        renderRequest.setAttribute("eventEnable", eventEnable);
        renderRequest.setAttribute("inputData", inputData);
        renderRequest.setAttribute("connector", connector);
        renderRequest.setAttribute("mode",mode);

        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws IOException, PortletException{
        String fileName = ParamUtil.getString(resourceRequest, "fileName");
        String parentPath = ParamUtil.getString(resourceRequest, "parentPath");
        Path filePath = Paths.get(parentPath).resolve(fileName);

        String command = ParamUtil.getString(resourceRequest, "command");
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
