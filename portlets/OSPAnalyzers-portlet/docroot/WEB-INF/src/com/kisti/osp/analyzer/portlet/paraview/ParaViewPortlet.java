package com.kisti.osp.analyzer.portlet.paraview;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class ParaViewPortlet
 */
public class ParaViewPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {	
		String launcherURL = ParamUtil.getString(renderRequest, "paraviewLauncherURL");
		launcherURL = "https://www.edison.re.kr/paraview";
		try 
		{
			//String portType = ParamUtil.getString(renderRequest, "portType");
			String portType = "folder";
			String dataDirectory = ParamUtil.getString(renderRequest, "parentPath");
			dataDirectory = "/EDISON/TEST/DATA/test/jobs/a65aff7d-643e-4e5b-8a9d-42b79c974553/20d9391d-1f9b-4353-bc26-ae137fba5b70.job/result/NACA0012_familyII_3";

			String fileToLoad = ParamUtil.getString(renderRequest, "fileToLoad");
			fileToLoad = "pre_rec_d08.vtk";
			PortletSession portletSession = renderRequest.getPortletSession();
			if( portType.equalsIgnoreCase("file") || portType.isEmpty() ){
				System.out.println("ParaViewPortlet-fileToLoad: "+fileToLoad);
//				String[] folderAndFilename = FileManagementLocalServiceUtil.splitFileName(fullPath);
				portletSession.setAttribute("fileToLoad", fileToLoad, PortletSession.APPLICATION_SCOPE);
			}

			//String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
		
			if( launcherURL == null || launcherURL.isEmpty()){
				System.out.println("There is no transfered data in ParaView Portlet.....");
			} 
			System.out.println("ParaViewPortlet-launcherURL: "+launcherURL);
			System.out.println("ParaViewPortlet-dataDirectory: "+dataDirectory);
	
			portletSession.setAttribute("launcherURL", launcherURL, PortletSession.APPLICATION_SCOPE);
			portletSession.setAttribute("dataDirectory", dataDirectory, PortletSession.APPLICATION_SCOPE);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// Trigger the view rendering
		super.doView(renderRequest, renderResponse);
	}

}
