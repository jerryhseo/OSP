package com.kaist.wignernwfet.portlet.wwfeditor;

import java.io.IOException;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class WWFEditorPortlet
 */
public class WWFEditorPortlet extends MVCPortlet {
 
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
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
    	
    	String parentPath = ParamUtil.getString(resourceRequest, "parentPath", "");
    	String fileName = ParamUtil.getString(resourceRequest, "fileName", "");
        String filePath = Paths.get(parentPath, fileName).toString();

        String command = ParamUtil.getString(resourceRequest, "command");
        String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType", OSPRepositoryTypes.USER_JOBS.toString());
        
        System.out.println("command: "+command);
        System.out.println("filePath: "+filePath);

        if(command.equalsIgnoreCase("READ_FILE")){
        	try{
            	OSPFileUtil.readFileContent(resourceRequest, resourceResponse, filePath.toString(), repositoryType);
            }catch (PortalException | SystemException e){
                throw new PortletException();
            }
        }
    }
}
