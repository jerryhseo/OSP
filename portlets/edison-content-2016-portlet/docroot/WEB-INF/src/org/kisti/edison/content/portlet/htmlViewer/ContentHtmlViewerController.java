package org.kisti.edison.content.portlet.htmlViewer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PortalUtil;


@Controller
@RequestMapping("VIEW")
public class ContentHtmlViewerController {
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model)
		throws IOException, PortletException {
		
		String contentSeq = ParamUtil.getString(request, "contentSeq");
		String contentIndexFile = ParamUtil.getString(request, "contentIndexFile");
		String shipmentForm = ParamUtil.getString(request, "shipmentForm");
		String lookUpPath = ParamUtil.getString(request, "lookUpPath");
		
		String filePath = "";
		shipmentForm = "file";
		//filePath ="C:/liferay-portal-6.2-ce-ga6/content/22701/22701/lecture_02-10";
		
		String contentBasicPath = PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/" +"content";
		filePath = contentBasicPath + "/" + contentSeq + "/" + contentSeq + "/" + contentIndexFile;
//		//String shipmentForm = "extention";
//		String shipmentForm = "file";
//		//String shipmentForm = "folder";
//		//String filePath = HtmlUtil.escapeJS("data/pdbs/1crn.pdb");
//		String filePath = HtmlUtil.escapeJS("data/Map/Map.html");
//		//String filePath = HtmlUtil.escapeJS("data"+File.separator+"caffeine.mol");
//		lookUpPath = HtmlUtil.escapeJS("/EDISON/LDAP/DATA/edisonadm/jobs/simulation_uuid/job_uuid.job");
		
		model.addAttribute("shipmentForm", shipmentForm);
		model.addAttribute("filePath", filePath);
		model.addAttribute("lookUpPath", lookUpPath);
		
		return "osphtmlviewer/htmlviewer_view";
	}

	@ResourceMapping(value ="serveResource")
	public void serveResource( ResourceRequest request,ResourceResponse response) throws IOException, PortletException {
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		String method = ParamUtil.getString(request, "method");
		Path filePath = Paths.get(ParamUtil.getString(request, "filePath").replace("*", ""));
		Path lookUpPath = Paths.get(ParamUtil.getString(request, "lookUpPath"));

		Path contextPath = Paths.get( request.getContextPath() );
		Path realContextPath = Paths.get(request.getPortletSession().getPortletContext().getRealPath(""));
//		System.out.println("realContextpath: "+realContextPath);
		
		if( method.equalsIgnoreCase("getInitialHtmlTempPath") ){
			//String tempFilePath = FileManagementLocalServiceUtil.getCopiedInitialHtmlPath(realContextPath, contextPath, lookUpPath, filePath);
			Path realTargetPath = lookUpPath.resolve(filePath);
			Path targetPath = realTargetPath.getParent();
			
			
//			Path tempFilePath = ContentHtmlViewerUtil.getTemporarySymbolicLink(realContextPath, contextPath, lookUpPath, targetPath, "osphtm", "");
/*			Path tempFilePath = FileManagementLocalServiceUtil.getTemporarySymbolicLink(realContextPath, contextPath, lookUpPath, targetPath, "osphtm", "");
			
			Path targetFileName = filePath.getFileName();
			JSONObject resultJSON = JSONFactoryUtil.createJSONObject();
			
			resultJSON.put("initialHtmlPath", tempFilePath.resolve(targetFileName).toString());
			
			ServletResponseUtil.write(httpResponse, resultJSON.toString());*/
//			System.out.println(resultJSON.toString());
			
			/*realContextpath: C:\liferay-portal-6.2-ce-ga6\tomcat-7.0.62\webapps\edison-content-2016-portlet
        Symbolic Link: From C:\liferay-portal-6.2-ce-ga6\content\22901\22901 To C:\liferay-portal-6.2-ce-ga6\tomcat-7.0.62\webapps\edison-content-2016-portlet\temp\osphtm2dfb916b-f16b-45ad-8455-0f36372e2318
        Extracted ContextPath: \edison-content-2016-portlet
        Complete symbolic link: \edison-content-2016-portlet\temp\osphtm2dfb916b-f16b-45ad-8455-0f36372e2318
        {"initialHtmlPath":"\\edison-content-2016-portlet\\temp\\osphtm2dfb916b-f16b-45ad-8455-0f36372e2318\\lecture_01"}*/
			 
		}
	}
}
