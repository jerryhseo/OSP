package org.kisti.edison.bestsimulation.portlet.workbench.jobcontroller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class JobController {
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Group sdrGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), "SDR");
		model.addAttribute("sdrGroupId", sdrGroup.getGroupId());
		return "view";
	}
	
	@ResourceMapping(value="downManualFile")
	public void searchSimulationJobInfo(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "fileEntryId", required = true) long fileEntryId
			){
		try {
			fileDownload(response, fileEntryId);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fileDownload(ResourceResponse response, long fileEntryId) throws Exception{
		DLFileEntry dfe = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
		String fileName = dfe.getTitle();
		
		InputStream inputStream = null;
		inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dfe.getFileEntryId(), dfe.getVersion());
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		response.setContentType("application/octet-stream");
		
//		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setProperty("Content-Disposition", "attachment;filename="+fileName);
		
		int readBytes = 0;
		byte data[] = new byte[8192];
		OutputStream out = response.getPortletOutputStream();
		
		while ((readBytes = bis.read(data)) != -1) {
			out.write(data, 0, readBytes);
		}
		out.flush(); 
		out.close();
		bis.close();
	}
	
	
	@ResourceMapping(value = "transferJobData")
	public void transferJobDataToSDR(ResourceRequest request, ResourceResponse response){
		final int REPO_ID = 1;
	}
}
