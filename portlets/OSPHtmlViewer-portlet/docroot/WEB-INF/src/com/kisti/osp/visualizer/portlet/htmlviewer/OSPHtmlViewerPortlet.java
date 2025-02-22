package com.kisti.osp.visualizer.portlet.htmlviewer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class OSPHtmlViewerPortlet
 */
public class OSPHtmlViewerPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(OSPHtmlViewerPortlet.class);
	
    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{
        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws IOException, PortletException{
    	
    	OSPFileLocalServiceUtil.processOSPResourceAction(resourceRequest, resourceResponse);

/*        String fileName = ParamUtil.getString(resourceRequest, "fileName");
        String parentPath = ParamUtil.getString(resourceRequest, "parentPath");
        String command = ParamUtil.getString(resourceRequest, "command");
        String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_JOBS.toString());
        
        Path filePath = Paths.get(parentPath).resolve(fileName);
        
        if(command.equalsIgnoreCase("READ_FILE")){
            try{
            	OSPFileUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                _log.error("readFileContent(): " + filePath.toString());
                throw new PortletException();
            }
        }else if(command.equalsIgnoreCase("GET_COPIED_TEMP_FILE_PATH")){
            String result;
            try{
            	result = OSPFileUtil.getCopiedTemporaryFilePath(resourceRequest, parentPath.toString(), "", "", repositoryType);
            	result = Paths.get(result, fileName).toString();
            	System.out.println("getCopiedTemporaryFilePath: "+result);
            }catch (PortalException | SystemException e){
                _log.error("FileManagementLocalServiceUtil.getCopiedTemporaryFilePath()");
                throw new PortletException();
            }
            
            HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
            ServletResponseUtil.write(httpResponse, result);
        }else if(command.equalsIgnoreCase("GET_LINKED_TEMP_FILE_PATH")){
            String result;
            try{ 
            	result = OSPFileUtil.getLinkedTemporaryFilePath(resourceRequest, parentPath.toString(), "", "", repositoryType);
            	System.out.println("getCopiedTemporaryFilePath: "+result);
            }catch (PortalException | SystemException e){
                _log.error("FileManagementLocalServiceUtil.getCopiedTemporaryFilePath()");
                throw new PortletException();
            }
            
            HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);
            ServletResponseUtil.write(httpResponse, result);
        }
        else{
            _log.info("There are no command option.");
        }
*/        
    }
}
