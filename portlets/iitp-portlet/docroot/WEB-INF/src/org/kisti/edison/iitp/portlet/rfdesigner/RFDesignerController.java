package org.kisti.edison.iitp.portlet.rfdesigner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.iitp.util.icebreaker.IBFiltUtil;
import org.kisti.edison.iitp.util.icebreaker.IBUserTokenUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class RFDesignerController{
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		
	return "view";
	}
	
	@ResourceMapping(value ="graphFileAction")
	public void graphFileAction(
			ResourceRequest request, ResourceResponse response) throws IOException{
		Map param = RequestUtil.getParameterMap(request);
		String content = CustomUtil.strNull(param.get("content"));
		String fileActionType = CustomUtil.strNull(param.get("fileActionType"));
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		File file = null;
		try{
			file = createFile(content);
			
			if(fileActionType.equals("download")){
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				JsonObject data = new JsonObject();
				data.addProperty("fileName", file.getName());
				out.write(data.toString());
			}else if(fileActionType.equals("upload")){
				User user = PortalUtil.getUser(request);
				Group group = themeDisplay.getScopeGroup();
				String filePath = ibFileUpload(file, group, user);
				
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				JsonObject data = new JsonObject();
				data.addProperty("filePath", filePath);
				out.write(data.toString());
			}
			
			/*
			StringBuffer contents = createStringBuffer(content);
			
			BufferedInputStream input = null;
			BufferedOutputStream output = null;
			try{
				response.setContentType("application/force-download");
				response.setProperty("Content-Disposition", "attachment; filename=RF_Designer_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".txt");
				
				
				input = new BufferedInputStream(fromStringBuffer(contents));
				output = new BufferedOutputStream(response.getPortletOutputStream());
				
				byte[] buffer = new byte[8192];
				for (int length = 0; (length = input.read(buffer)) > 0;) {
					output.write(buffer, 0, length);
				}
				output.flush();
				
			}catch (Exception e) {
				if (output != null) try { output.close(); } catch (IOException ignore) {}
				if (input != null) try { input.close(); } catch (IOException ignore) {}
			}
			*/
		}catch (Exception e) {
			e.printStackTrace();
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-job-submit-error"));
		}finally {
			if(fileActionType.equals("upload")&&file!=null){
				FileUtil.delete(file);
			}
		}
	}
	
	private static InputStream fromStringBuffer(StringBuffer buf) {
		return new ByteArrayInputStream(buf.toString().getBytes());
	}
	
	@ResourceMapping(value ="edisonFileDownload")
	public void fileDownload(ResourceRequest request, ResourceResponse response) throws InterruptedException{
		Map param = RequestUtil.getParameterMap(request);
		String fileName = CustomUtil.strNull(param.get("fileName"));
		File file = new File(SystemProperties.get(SystemProperties.TMP_DIR)+File.separator+fileName);
		
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try{
			response.setContentType("application/force-download");
			response.setProperty("Content-Disposition", "attachment; filename=RF_Designer_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".dat");
			
			
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getPortletOutputStream());
			
			byte[] buffer = new byte[8192];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
			output.flush();
			
		}catch (Exception e) {
			
		}finally {
			if (output != null) try { output.close(); } catch (IOException ignore) {}
			if (input != null) try { input.close(); } catch (IOException ignore) {}
			FileUtil.delete(file);
		}
	}
	
	private static StringBuffer createStringBuffer(String fileContent){
		StringBuffer row = new StringBuffer();
		
		JsonArray contnets = new JsonParser().parse(fileContent).getAsJsonArray();
		boolean subLineExist = contnets.size() >1?true:false;
		int contentsSize = contnets.size();
		
		JsonObject data =  contnets.get(0).getAsJsonObject();
		JsonArray xaxisData = data.getAsJsonArray("x");
		
		List<Map<String,Object>> fileContentList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<xaxisData.size();i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("x", xaxisData.get(i));
			map.put("y1", data.getAsJsonArray("y").get(i));
			
			if(subLineExist){
				for(int j=1;j<contentsSize;j++){
					int num = j+1;
					JsonArray yaxisData = contnets.get(j).getAsJsonObject().getAsJsonArray("y");
					map.put("y"+num, yaxisData.get(i));
				}
			}
			
			fileContentList.add(map);
		}
		
		
		for(Map<String,Object> rowMap:fileContentList){
			row.append(CustomUtil.strNull(rowMap.get("x")));
			row.append("  ");
			
			for(int i=1;i<=rowMap.keySet().size();i++){
				row.append(CustomUtil.strNull(rowMap.get("y"+i))+"  ");
			}
			
			row.append(System.getProperty("line.separator"));
		}
		
		return row;
	}
	
	private static File createFile(String fileContent) throws SystemException, IOException {
		File tempFile = FileUtil.createTempFile("dat");
		BufferedWriter out = null;
		try{
			JsonArray contnets = new JsonParser().parse(fileContent).getAsJsonArray();
			boolean subLineExist = contnets.size() >1?true:false;
			int contentsSize = contnets.size();
			
			JsonObject data =  contnets.get(0).getAsJsonObject();
			JsonArray xaxisData = data.getAsJsonArray("x");
			
			List<Map<String,Object>> fileContentList = new ArrayList<Map<String,Object>>();
			for(int i=0;i<xaxisData.size();i++){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("x", xaxisData.get(i));
				map.put("y1", data.getAsJsonArray("y").get(i));
				
				if(subLineExist){
					for(int j=1;j<contentsSize;j++){
						int num = j+1;
						JsonArray yaxisData = contnets.get(j).getAsJsonObject().getAsJsonArray("y");
						map.put("y"+num, yaxisData.get(i));
					}
				}
				
				fileContentList.add(map);
			}
			
			out = new BufferedWriter(new FileWriter(tempFile,true));
			
			
			for(Map<String,Object> rowMap:fileContentList){
				StringBuffer row = new StringBuffer();
				row.append(CustomUtil.strNull(rowMap.get("x")));
				for(int i=1;i<=rowMap.keySet().size();i++){
					row.append(","+CustomUtil.strNull(rowMap.get("y"+i)));
				}
				
//				row.append(System.getProperty("line.separator"));
				
				out.write(row.toString());
				out.newLine();
			}
		}catch (Exception e) {
			if(tempFile.isFile()){FileUtil.delete(tempFile);}
			throw new SystemException(e);
		}finally {
			out.close();
		}
		return tempFile;
	}
	
	private static String ibFileUpload(File file, Group group, User user) throws MalformedURLException, PortalException, SystemException, IOException, ParseException{
		String userScreenName = user.getScreenName();
		String vcToken = IBUserTokenUtil.getOrCreateToken(group.getGroupId(), user).getVcToken();
        String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
        
		return IBFiltUtil.ibFileUpload(icebreakerUrl, vcToken, file, userScreenName);
	}
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
}

