package com.kisti.osp.analyzer.portlet.paraview;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.constants.OSPRepositoryTypes;
import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class OSPParaViewPortlet
 */
public class OSPParaViewPortlet extends MVCPortlet {
	private static final String  launcherURL = "https://www.edison.re.kr/paraview";
	private static Log _log = LogFactoryUtil.getLog(OSPParaViewPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
	        throws IOException, PortletException {

		renderRequest.setAttribute("launcherURL", launcherURL);
		super.doView(renderRequest, renderResponse);
	}
	
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
        throws IOException, PortletException{

        String targetPath = ParamUtil.getString(resourceRequest, "targetPath");
        String command = ParamUtil.getString(resourceRequest, "command");
        String repositoryType = ParamUtil.getString(resourceRequest, "repositoryType" );
        _log.info("command: "+command);
        _log.info("repositoryType: "+repositoryType);
        _log.info("targetPath: "+targetPath);
        
        if(command.equalsIgnoreCase("GET_ABSOLUTE_PATH")){
        	try {
				OSPFileUtil.getFileURL(resourceRequest, resourceResponse, targetPath, repositoryType);
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else{
            _log.info("There are no command option.");
        }
        
    }
}
