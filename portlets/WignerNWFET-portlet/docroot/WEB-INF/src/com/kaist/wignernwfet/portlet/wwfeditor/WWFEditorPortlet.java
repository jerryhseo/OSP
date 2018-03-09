package com.kaist.wignernwfet.portlet.wwfeditor;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.osp.service.FileManagementLocalServiceUtil;
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
        String filePath = ParamUtil.getString(resourceRequest, "filePath");

        String command = ParamUtil.getString(resourceRequest, "command");
        String action = ParamUtil.getString(resourceRequest, "action", "output");
        boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;
        
        System.out.println("command: "+command);
        System.out.println("action: "+action);
        System.out.println("filePath: "+filePath);

        if(command.equalsIgnoreCase("READ_FILE")){
            try{
                FileManagementLocalServiceUtil.readFileContent(resourceRequest, resourceResponse, filePath, isJobResult);
            }catch (PortalException | SystemException e){
                throw new PortletException();
            }
        }
    }
}
