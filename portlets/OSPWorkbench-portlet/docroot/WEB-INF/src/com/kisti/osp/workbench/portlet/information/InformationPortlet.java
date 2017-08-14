package com.kisti.osp.workbench.portlet.information;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class InformationPortlet
 */
public class InformationPortlet extends MVCPortlet {
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException{
		String type = ParamUtil.getString(resourceRequest, "type");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			if(type.equals("manualfileDownload")){
				long fileEntryId = ParamUtil.getLong(resourceRequest, "fileEntryId");
				fileDownload(resourceResponse, fileEntryId);
			}else if(type.equals("searchApp")){
				long scienceAppId = ParamUtil.getLong(resourceRequest, "scienceAppId");
				JSONObject jsonObj = searchApp(themeDisplay.getLocale(),scienceAppId);
				resourceResponse.setContentType("application/json; charset=UTF-8");
				PrintWriter out = resourceResponse.getWriter();
				out.write(jsonObj.toString());
			}
			
			super.serveResource(resourceRequest, resourceResponse);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(resourceResponse), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
		}
	}
	
	private JSONObject searchApp(Locale locale, long scienceAppId) throws Exception{
		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		Map<String,Object> scienceAppData = ScienceAppLocalServiceUtil.getScienceAppReturnObject(scienceAppId, locale);
		scienceAppData.put("manualId", GetterUtil.getLong(scienceAppData.get("manualId_"+LocaleUtil.toLanguageId(locale)),0));
		String optionJson = jsonSerializer.serialize(scienceAppData);
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject(optionJson);
		return jsonObj;
		
	}
	
	private void fileDownload(ResourceResponse response, long fileEntryId) throws Exception{
		DLFileEntry dfe = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
		String fileName = dfe.getTitle();
		
		InputStream inputStream = null;
		inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dfe.getUserId(), dfe.getFileEntryId(), dfe.getVersion());
		
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		response.setContentType("application/octet-stream");
		
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
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
	
	private static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
