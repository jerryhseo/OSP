package org.kisti.edison.science.portlet.texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.IcebreakerUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.util.TokenProviderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class TextEditorPortlet
{
//	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	@SuppressWarnings("rawtypes")
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model)
	{
//		String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
//		System.err.println(portletId);
		try 
		{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();

			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			//long groupId = 20181;
			//System.err.println( "GROUPID : " +groupId);
			
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			IcebreakerVcToken icebreakerVcToken = IcebreakerUtil.getIceBreakerToken(user, groupId, thisGroup, themeDisplay);

			if(!"".equals(icebreakerUrl)){
				model.addAttribute("icebreakerUrl", icebreakerUrl);
			}
			model.addAttribute("icebreakerVcToken", icebreakerVcToken);
			model.addAttribute("groupId", groupId);
			model.addAttribute("cluster", CustomUtil.strNull(param.get("cluster")));
			model.addAttribute("workflowType", CustomUtil.strNull(param.get("workflowType")));
		} catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "texteditor/view"; 
	}
	/**
	 * 최상위 폴더
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResourceMapping(value ="getRepositoryFolder")
	public void getRepositoryFolder(ResourceRequest request, ResourceResponse response){
		try {
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
	
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String responseValue = "";
			
			if(!"".equals(icebreakerUrl)){
				URL url = new URL(icebreakerUrl+"/api/folder/list");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
				
				String  output = "";		
				if (conn.getResponseCode() == 200) {

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseValue += output;
						}
					}
				}
				
				conn.disconnect();
			}
			
			List resultList = new ArrayList();
			if (!CustomUtil.strNull(responseValue).equals("")) {
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				HashMap resultMap = null;
				
				//childCnt = folder + file
				int childCnt = 0;
				for (int i = 0; i < jsonArray.size(); i++) {
					resultMap = new HashMap();
					JSONObject comandObj = (JSONObject) jsonArray.get(i);
					
					resultMap.put("fileName",comandObj.getString("name"));
					resultMap.put("path",comandObj.getString("path"));
					resultMap.put("fileId", comandObj.getString("id"));
					resultMap.put("fileType", "folder");
					
					String folderId = comandObj.getString("id");
					
					if(!folderId.equals("")){
						URL url = new URL(icebreakerUrl+"/api/folder/"+folderId+"/list");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
						conn.setDoOutput(true);
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
	
						String childResponseValue = "";
						String  output = "";		
						
						if (conn.getResponseCode() == 200) {
	
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
							while ((output = br.readLine()) != null) {
								if(!CustomUtil.strNull(output).equals("null")){
									childResponseValue += output;
								}
							}
							
							if (!CustomUtil.strNull(childResponseValue).equals("")) {
								JSONObject childJsonObj = JSONObject.fromObject(JSONSerializer.toJSON(childResponseValue));
								
								childCnt = Integer.parseInt( childJsonObj.get("count").toString() );
							}
						}
						conn.disconnect();
						
						//File 정보 : tree의 카운팅을 위한 기능일뿐; 추후 api 수정하여 필요없는 호출을 줄일 필요가 있다.
						url = new URL(icebreakerUrl+"/api/file/"+folderId+"/list");
						HttpURLConnection fileConn = (HttpURLConnection) url.openConnection();
						fileConn.setDoOutput(true);
						fileConn.setRequestMethod("GET");
						fileConn.setRequestProperty("Accept", "application/json");
						fileConn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
						
						childResponseValue = "";
						output = "";		
						
						if (fileConn.getResponseCode() == 200) {
	
							BufferedReader br = new BufferedReader(new InputStreamReader((fileConn.getInputStream())));
							while ((output = br.readLine()) != null) {
								if(!CustomUtil.strNull(output).equals("null")){
									childResponseValue += output;
								}
							}
							
							if (!CustomUtil.strNull(childResponseValue).equals("")) {
								JSONObject childJsonObj = JSONObject.fromObject(JSONSerializer.toJSON(childResponseValue));
								childCnt += Integer.parseInt( childJsonObj.get("count").toString() );
							}
						}
						fileConn.disconnect();
						resultMap.put("childCnt", childCnt);
					}

					resultList.add(resultMap);
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("dataList", resultList);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 선택한 폴더의 하위폴더 목록
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResourceMapping(value ="getChildFolder")
	public void getChildFolder(ResourceRequest request, ResourceResponse response){
		try {
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String responseValue = "";
			
			int responseCode = 0;
			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
			if(!"".equals(icebreakerUrl)){
				URL url = new URL(icebreakerUrl+"/api/folder/"+selectFolderId+"/list");

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
				
				responseCode = conn.getResponseCode();
				String  output = "";		
				if (responseCode == 200) {
					
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseValue += output;
						}
					}
				}
				conn.disconnect();
			}
			
			List resultList = new ArrayList();
			if (!CustomUtil.strNull(responseValue).equals("")) {
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				HashMap resultMap = null;
				
				for (int i = 0; i < jsonArray.size(); i++) {
					resultMap = new HashMap();
					JSONObject comandObj = (JSONObject) jsonArray.get(i);
					
					resultMap.put("parentFolderId", selectFolderId);
					resultMap.put("fileName",comandObj.getString("name"));
					resultMap.put("path",comandObj.getString("path"));
					resultMap.put("fileId", comandObj.getString("id"));
					resultMap.put("fileType", "folder");
					
					String folderId = comandObj.getString("id");
					
					if(!folderId.equals("")){
						URL url = new URL(icebreakerUrl+"/api/folder/"+folderId+"/list");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
						conn.setDoOutput(true);
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
	
						String childResponseValue = "";
						String  output = "";		
						if (conn.getResponseCode() == 200) {
	
							BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
							while ((output = br.readLine()) != null) {
								if(!CustomUtil.strNull(output).equals("null")){
									childResponseValue += output;
								}
							}
							
							if (!CustomUtil.strNull(childResponseValue).equals("")) {
								JSONObject childJsonObj = JSONObject.fromObject(JSONSerializer.toJSON(childResponseValue));
								
								resultMap.put("childCnt", childJsonObj.get("count"));
							}
						}
						conn.disconnect();
					}
					resultList.add(resultMap);
				}
			}
			
			/*JSONObject jsonObj = new JSONObject(resultFileStr);
			org.json.JSONArray jsonArray = jsonObj.getJSONArray("files");*/
			JSONObject json = new JSONObject();
			json.put("responseCode", responseCode);
			json.put("dataList", resultList);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 선택한 폴더의 하위파일목록
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResourceMapping(value ="getChildFile")
	public void getChildFile(ResourceRequest request, ResourceResponse response){
		try {
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String responseValue = "";
			
			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
			if(!"".equals(icebreakerUrl)){
				
				String urlStr = "";
				if(!selectFolderId.equals("HOME")){
					urlStr = icebreakerUrl+"/api/file/"+selectFolderId+"/list";
				}else{
					urlStr = icebreakerUrl+"/api/file/list";
				}
				
				URL url = new URL(urlStr);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
				
				String  output = "";		
				if (conn.getResponseCode() == 200) {
					
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseValue += output;
						}
					}
				}
				conn.disconnect();
			}
			
			List resultList = new ArrayList();
			if (!CustomUtil.strNull(responseValue).equals("")) {
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				HashMap resultMap = null;
				
				// 파일명 필터링, text로 읽기가 가능한 파일만 사용(예-이미지, 압축파일, jmol 등)
				Pattern p = Pattern.compile("\\.(jpg|jpeg|png|gif|bmp|tif|tiff|zip)$", Pattern.CASE_INSENSITIVE);
				Matcher m;
				
				for (int i = 0; i < jsonArray.size(); i++) {
					resultMap = new HashMap();
					JSONObject comandObj = (JSONObject) jsonArray.get(i);
					String fileName = comandObj.getString("name");
					m = p.matcher( fileName );
					if(!m.find())
					{
						resultMap.put("parentFolderId", selectFolderId);
						resultMap.put("fileName", fileName);
						resultMap.put("path",comandObj.getString("path"));
						resultMap.put("fileId", comandObj.getString("id"));
						resultMap.put("parentPath",comandObj.getString("parentPath"));
						resultMap.put("lastModified", comandObj.getString("lastModified"));
						resultMap.put("fileSize", CustomUtil.fileVolumeCalc(String.valueOf(comandObj.getDouble("size"))));
						resultMap.put("fileType", "file");
						
						resultList.add(resultMap);
					}
				}
			}
			
			/*JSONObject jsonObj = new JSONObject(resultFileStr);
			org.json.JSONArray jsonArray = jsonObj.getJSONArray("files");*/
			JSONObject json = new JSONObject();
			json.put("dataList", resultList);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 파일업로드 POPUP OPEN
	 * @param request
	 * @param model
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RenderMapping(params = "myaction=fileUploadPopUp")
	@SuppressWarnings("rawtypes")
	public String fileUploadPopUp(RenderRequest request,RenderResponse response, ModelMap model)
	{
		try 
		{
			Map param = RequestUtil.getParameterMap(request);
			
			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			
			String destFolerId = CustomUtil.strNull(param.get("destFolerId"));
			String destFolerParents = CustomUtil.strNull(param.get("destFolerParents"));
			String isPopUp = CustomUtil.strNull(param.get("isPopUp"), "false");
			
			String returnId = CustomUtil.strNull(param.get("returnId"));
			String returnFileName = CustomUtil.strNull(param.get("returnFileName"));
			String cluster = CustomUtil.strNull(param.get("cluster"));
			String workflowType = CustomUtil.strNull(param.get("workflowType"));
			String dialogId = CustomUtil.strNull(param.get("dialogId"));
			
			model.addAttribute("groupId", groupId);
			model.addAttribute("vcToken", icebreakerToken);
			model.addAttribute("destFolerId", destFolerId);
			model.addAttribute("destFolerParents", destFolerParents);
			model.addAttribute("isPopUp", isPopUp);
			model.addAttribute("returnId", returnId);
			model.addAttribute("returnFileName", returnFileName);
			model.addAttribute("cluster", cluster);
			model.addAttribute("workflowType", workflowType);
			model.addAttribute("dialogId", dialogId);
			return "texteditor/fileUpload";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * IB 파일업로드
	 * @param request
	 * @param response
	 * @throws SystemException
	 * @throws PortalException
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@ResourceMapping(value ="savePortletSessionValue")
	private void savePortletSessionValue( ResourceRequest request, ResourceResponse response )
	{
		PortletSession portletSession = request.getPortletSession();
		Map param = RequestUtil.getParameterMap(request);
		String portName = CustomUtil.strNull(param.get("portName"));
		String taskId = CustomUtil.strNull(param.get("taskId"));
		String value = CustomUtil.strNull(param.get("value"));
		
		com.liferay.portal.kernel.json.JSONObject sendEvent = JSONFactoryUtil.createJSONObject();
		sendEvent.put("taskId", taskId);
		sendEvent.put("portName", portName);
		sendEvent.put("value", value);
		portletSession.setAttribute(portName,sendEvent,PortletSession.APPLICATION_SCOPE);
	}
	
	@SuppressWarnings("rawtypes")
	@ResourceMapping(value ="getPortletSessionValue")
	private void getPortletSessionValue( ResourceRequest request, ResourceResponse response ) throws IOException
	{
		PortletSession portletSession = request.getPortletSession();
		Map param = RequestUtil.getParameterMap(request);
		String portName = CustomUtil.strNull(param.get("portName"));
		
		String result = "";
		com.liferay.portal.kernel.json.JSONObject receivedEvent = (com.liferay.portal.kernel.json.JSONObject)portletSession.getAttribute(portName,PortletSession.APPLICATION_SCOPE);
		if(receivedEvent != null)
		{
			result = receivedEvent.toString();
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(result);
	}
	
	@SuppressWarnings("rawtypes")
	@ResourceMapping(value ="getTextFile")
	public void getTextFile(ResourceRequest request, ResourceResponse response)
	{
		File tempFile = null;
		try
		{
			Map param = RequestUtil.getParameterMap(request);
			String downloadUrl = CustomUtil.strNull(param.get("downloadUrl"));
			
			URL remoteFileURL = new URL(downloadUrl);
			URLConnection uCon =  remoteFileURL.openConnection();
			InputStream is = uCon.getInputStream();
			
			
			try {
					tempFile = FileUtil.createTempFile(is);
				} catch (IllegalArgumentException e) { 
					//빈 값의 temp 파일 생성 메소드 
					tempFile = FileUtil.createTempFile();
					//temp 파일 물리적 생성
					FileUtil.write(tempFile, "");
				}
			
			
			FileReader reader = new FileReader(tempFile);
			BufferedReader bufferReader = new BufferedReader(reader);
			String read="";
			StringBuffer readBuffer = new StringBuffer();
			int lineIndex=0;
			int areaIndex=0;
			int diviceIndex=1000;
			
			com.liferay.portal.kernel.json.JSONObject fileAreaJson = JSONFactoryUtil.createJSONObject();
			
			while((read=bufferReader.readLine())!=null)
			{
				readBuffer.append(read+"\r\n");
				if(lineIndex==diviceIndex)
				{
					fileAreaJson.put(String.valueOf(areaIndex),readBuffer.toString());
					readBuffer = new StringBuffer();
					areaIndex++;
					diviceIndex+=500;
				}
				lineIndex++;
			}
			
			if(areaIndex==0)
				fileAreaJson.put(String.valueOf(areaIndex),readBuffer.toString());				
			
			FileUtil.delete(tempFile);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(fileAreaJson.toString());
			
			reader.close();
			bufferReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			FileUtil.delete(tempFile);
		}
	}
	
	@ResourceMapping(value ="readLocalTextFile")
	public void readLocalTextFile(ResourceRequest request, ResourceResponse response) throws IOException
	{
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		
		try 
		{
			InputStream uploadInputStream = upload.getFileAsStream("localTextFile",false);
				
			InputStream fileObj = uploadInputStream;
			File tempFile = null;

			try {
				tempFile = FileUtil.createTempFile(fileObj);
			} catch (IllegalArgumentException e) { 
				//빈 값의 temp 파일 생성 메소드 
				tempFile = FileUtil.createTempFile();
				//temp 파일 물리적 생성
				FileUtil.write(tempFile, "");
			}

			FileReader reader = new FileReader(tempFile);
			
			BufferedReader bufferReader = new BufferedReader(reader);
			String read="";
			StringBuffer readBuffer = new StringBuffer();
			int lineIndex=0;
			int areaIndex=0;
			int diviceIndex=1000;
			
			com.liferay.portal.kernel.json.JSONObject fileAreaJson = JSONFactoryUtil.createJSONObject();
			
			while((read=bufferReader.readLine())!=null)
			{
				readBuffer.append(read+"\r\n");
				if(lineIndex==diviceIndex)
				{
					fileAreaJson.put(String.valueOf(areaIndex),readBuffer.toString());
					readBuffer = new StringBuffer();
					areaIndex++;
					diviceIndex+=500;
				}
				lineIndex++;
			}
			
			if(areaIndex==0)
				fileAreaJson.put(String.valueOf(areaIndex),readBuffer.toString());				
			
			FileUtil.delete(tempFile);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(fileAreaJson.toString());
			
			reader.close();
			bufferReader.close();
			
			if(tempFile.exists())
			{
				tempFile.delete();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@ResourceMapping(value ="saveTextFile")
	public void saveFileText(ResourceRequest request, ResourceResponse response)
	{
		Map param = RequestUtil.getParameterMap(request);
		String fileName = CustomUtil.strNull(param.get("fileName"));
		String fileContent = CustomUtil.strNull(param.get("fileContent"));
		String fileFolderId = CustomUtil.strNull(param.get("fileFolderId"));
		String vcToken = CustomUtil.strNull(param.get("vcToken"));
		String responseValue = "";
		
		try 
		{
			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			
			if(!"".equals(icebreakerUrl))
			{
				URL writeUrl = new URL(icebreakerUrl+"/api/file/write?name="+fileName+"&cluster=EDISON-CFD");

				HttpURLConnection conn = (HttpURLConnection) writeUrl.openConnection();
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + vcToken);
				conn.setRequestProperty("Content-Type", "text/plain"); 
				conn.setRequestProperty("charset", "utf-8");
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(fileContent);
				writer.flush();
				writer.close();
				
				String  output = "";
				if (conn.getResponseCode() == 201) {

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) {
						if(!CustomUtil.strNull(output).equals("null")){
							responseValue += output;
						}
					}
				}
				conn.disconnect();
				
				String fildId = "";
				if(!CustomUtil.strNull(responseValue).equals(""))
				{
					JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
					fildId = jsonObj.get("id").toString();
					//Home 경로가 아닐 경우 move 시킨다.
					if(!fileFolderId.equals("HOME"))
					{
						URL fileMoveUrl = new URL(icebreakerUrl+"/api/file/move/"+fildId+"/"+fileFolderId);
						conn = (HttpURLConnection) fileMoveUrl.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("PUT");
						conn.setRequestProperty("Accept", "application/json");
						conn.setRequestProperty("Authorization", "Basic " + vcToken);
						conn.disconnect();
					}
				}
				
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write(fildId);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
}
