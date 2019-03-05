package org.kisti.edison.bestsimulation.portlet.workbench.resultfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class ResultFileViewer {
	protected static Log  _log = LogFactoryUtil.getLog(ResultFileViewer.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException, MalformedURLException, IOException, ParseException{
		String jobUuid = ParamUtil.getString(request, "jobUuid", "");
		model.addAttribute("jobUuid", jobUuid);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);
		model.addAttribute("icebreakerUrl", icebreakerUrl);
		
		
		User user = themeDisplay.getUser();
		// 임시 : Result.zip File ID
		IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
		String resultFileStr = SimulationLocalServiceUtil.retrieveRemoteDir(icebreakerUrl, vcToken.getVcToken(), jobUuid, "/");
		String zipFileId = "";
		if(!CustomUtil.strNull(resultFileStr).equals("")){
			JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultFileStr));
			JSONArray jsonArray = jsonObj.getJSONArray("files");
			
			root1: for(int i = 0; i < jsonArray.size(); i++){
				JSONObject comandObj = (JSONObject) jsonArray.get(i);
				if(comandObj.getString("name").equals("result.zip")){
					zipFileId =  comandObj.getString("id");
					break root1;
				}
			}
		}
		
		model.addAttribute("zipFileId", zipFileId);
		
		return "view";
	}
	
	
	@ResourceMapping(value="jobResultFile")
	public void searchFileList(
			@RequestParam("jobUuid") String jobUuid, 
			ResourceRequest request, ResourceResponse response) throws IOException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		User user = themeDisplay.getUser();
		
		Map param = RequestUtil.getParameterMap(request);
		
		try{
			String searchFolderPath = CustomUtil.strNull(param.get("searchFolderPath"),"result");
			IcebreakerVcToken vcToken = SimulationLocalServiceUtil.getOrCreateToken(groupId, user);
			String icebreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL);

			String result = SimulationLocalServiceUtil.retrieveAllRemoteDir(icebreakerUrl, vcToken.getVcToken(), jobUuid,searchFolderPath);
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			
			if(!CustomUtil.strNull(result).equals("")){
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(result)).getJSONObject("files");
				Map<String, Object> resultMap = null;
				
				JSONArray folderArray = jsonObj.getJSONObject("folders").getJSONArray("files");
				for(int i = 0; i < folderArray.size(); i++){
					resultMap = new HashMap<String, Object>();
					JSONObject comandObj = (JSONObject) folderArray.get(i);
					
					
					resultMap.put("id", comandObj.getString("id"));
					resultMap.put("name", comandObj.getString("name"));
					resultMap.put("type", "directory");
//					resultMap.put("extension", this.getFileExtension(comandObj.getString("type")));
					resultMap.put("path", comandObj.getString("name"));
					resultList.add(resultMap);
				}
				
				JSONArray filesArray = jsonObj.getJSONObject("files").getJSONArray("files");
				for(int i = 0; i < filesArray.size(); i++){
					resultMap = new HashMap<String, Object>();
					JSONObject comandObj = (JSONObject) filesArray.get(i);
					
					resultMap.put("id", comandObj.getString("id"));
					resultMap.put("name", comandObj.getString("name"));
					resultMap.put("type", "file");
					resultMap.put("extension", this.getFileExtension(comandObj.getString("name")));
					resultMap.put("path", comandObj.getString("parentPath"));
					resultList.add(resultMap);
				}
				Collections.sort(resultList, sortComp);
			}
			
			JsonObject obj = new JsonObject();
			obj.add("resultList", new Gson().toJsonTree(resultList, new TypeToken<List<Map<String, Object>>>() {}.getType()));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			e.printStackTrace();
		}
	}
	
	private Comparator<Map<String, Object>> sortComp = new Comparator<Map<String, Object>>(){
	    @Override
	    public int compare(Map<String, Object> o1, Map<String, Object> o2){
	      String s1 = o1.get("name").toString();
	      String s2 = o2.get("name").toString();

	      int n1 = s1.length(), n2 = s2.length();
	      for(int i1 = 0, i2 = 0; i1 < n1 && i2 < n2; i1++, i2++){
	        char c1 = s1.charAt(i1);
	        char c2 = s2.charAt(i2);
	        if(c1 != c2){
	          c1 = Character.toUpperCase(c1);
	          c2 = Character.toUpperCase(c2);
	          if(c1 != c2){
	            c1 = Character.toLowerCase(c1);
	            c2 = Character.toLowerCase(c2);
	            if(c1 != c2){
	              return c1 - c2;
	            }
	          }
	        }
	      }
	      return n1 - n2;
	    }
	  };
	  
	  
	private String getFileExtension(String fileName) {
		if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0)) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return "";
	}
	
	
	private static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
	
}
