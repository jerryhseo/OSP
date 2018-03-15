package com.kaist.wignernwfet.portlet.csvplotter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class CSVPlotterPortlet
 */
public class CSVPlotterPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
        String inputData = ParamUtil.getString(renderRequest, "inputData", "");
        String connector = ParamUtil.getString(renderRequest, "connector", "broadcast");
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
        String parentPath = ParamUtil.getString(resourceRequest, "parentPath");
        String fileName = ParamUtil.getString(resourceRequest, "fileName");

        String command = ParamUtil.getString(resourceRequest, "command");
        String action = ParamUtil.getString(resourceRequest, "action", "output");
        boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;
        
        System.out.println("command: "+command);
        System.out.println("action: "+action);

        if(command.equalsIgnoreCase("READ_FILE")){
            Path filePath = Paths.get(parentPath).resolve(fileName);
            System.out.println("filePath: "+filePath.toString());
            try{
                FileManagementLocalServiceUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(), isJobResult);
            }catch (PortalException | SystemException e){
                throw new PortletException();
            }
        }
        else if(command.equalsIgnoreCase("READ_FIRST_FILE")){
            try{
                FileManagementLocalServiceUtil.readFirstFileContent(resourceRequest, resourceResponse, parentPath,
                    fileName, isJobResult);
            }catch (PortalException | SystemException e){
                System.out.println("READ_FIRST_FILE: " + parentPath + ", "+fileName);
                throw new PortletException();
            }
        }
        else if(command.equalsIgnoreCase("GET_FIRST_FILE_NAME")){
            try{
                FileManagementLocalServiceUtil.getFirstFileName(resourceRequest, resourceResponse, parentPath,
                    fileName, isJobResult);
            }catch (PortalException | SystemException e){
                System.out.println("GET_FIRST_FILE_NAME: " + parentPath + ", "+fileName);
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("DOWNLOAD_FILE")){
            try{
                FileManagementLocalServiceUtil.download(resourceRequest, resourceResponse, parentPath,
                    new String[]{fileName}, isJobResult);
            }catch (PortalException | SystemException e){
            	System.out.println("checkValidFile(): " + Paths.get(parentPath).resolve(fileName).toString());
                throw new PortletException();
            }
        }
    }
}
