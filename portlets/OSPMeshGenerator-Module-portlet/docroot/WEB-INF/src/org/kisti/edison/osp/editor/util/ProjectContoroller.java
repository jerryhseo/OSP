package org.kisti.edison.osp.editor.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.osp.NoSuchProjectException;
import org.kisti.edison.osp.model.Project;
import org.kisti.edison.osp.service.ProjectLocalServiceUtil;
import org.kisti.edison.osp.service.persistence.ProjectPK;
import org.kisti.edison.osp.util.IBUserTokenUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class ProjectContoroller {
	
	private static Log log = LogFactory.getLog(ProjectContoroller.class);
	
	@ResourceMapping(value="getProject")
	public void getProject(ResourceRequest request, ResourceResponse response,
			@RequestParam("simulationUuid") String simulationUuid,
			@RequestParam("jobSeqNo") long jobSeqNo) throws IOException, PortalException, SystemException, ParseException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		Map params = RequestUtil.getParameterMap(request);
		
		String portletNamespace = themeDisplay.getPortletDisplay().getNamespace();
		ProjectPK projectPK = new ProjectPK(simulationUuid, portletNamespace, jobSeqNo);
		
		JSONObject obj = new JSONObject();
		Project project = null;
		try{
			project = ProjectLocalServiceUtil.getProject(projectPK);
			obj.putAll(project.getModelAttributes());
		}catch (Exception e) {
			if(e instanceof NoSuchProjectException){
				String projectStructure = CustomUtil.strNull(params.get("projectStructure"));
				String analyzerStructure = CustomUtil.strNull(params.get("analyzerStructure"));
				
				Project newProject = ProjectLocalServiceUtil.createProject(projectPK);
				newProject.setProjectStructure(projectStructure);
				newProject.setAnalyzerStructure(analyzerStructure);
				newProject.setUserId(themeDisplay.getUserId());
				newProject.setCreateDate(new Date());
				
				long projectId = CounterLocalServiceUtil.increment(Project.class.getName());
				newProject.setProjectId(projectId);
				project = ProjectLocalServiceUtil.addProject(newProject);
				obj.putAll(project.getModelAttributes());
			}else{
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
			}
		}finally {
			/*Project의 UserId를 통하여 VCToken 생성*/
			long userId = project.getUserId();
			User user = UserLocalServiceUtil.getUser(userId);
			
			/*vcToken*/
			if(!user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId))){
				obj.put("vcToken",IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
			}else{
				String userVcToken = GetterUtil.getString(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId)),"");
				
				if(userVcToken.equals("")){
					obj.put("vcToken",IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
				}else{
					int userVcExpired = GetterUtil.getInteger(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED+ String.valueOf(groupId)),0);
					if(userVcExpired <= Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))){
						obj.put("vcToken",IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
					}else{
						obj.put("vcToken",userVcToken);
					}
				}
			}
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
		}
	}
	
	@ResourceMapping(value="updateProject")
	public void updateProject(
			@RequestParam("simulationUuid") String simulationUuid, 
			@RequestParam("jobSeqNo") long jobSeqNo,
			ResourceRequest request, ResourceResponse response) throws IOException{
		
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String projectStructure = GetterUtil.getString(params.get("projectStructure"),"");
		String analyzerStructure = GetterUtil.getString(params.get("analyzerStructure"),"");
		String portletNamespace = themeDisplay.getPortletDisplay().getNamespace();
		try{
			ProjectPK projectPK = new ProjectPK(simulationUuid, portletNamespace, jobSeqNo);
			Project project = ProjectLocalServiceUtil.getProject(projectPK);
			
			if(!projectStructure.equals("")){
				project.setProjectStructure(projectStructure);
			}
			
			if(!analyzerStructure.equals("")){
				project.setAnalyzerStructure(analyzerStructure);
			}
			
			ProjectLocalServiceUtil.updateProject(project);
		}catch (Exception e) {
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="deleteProject")
	public void deleteProject(
			@RequestParam("simulationUuid") String simulationUuid, 
			@RequestParam("jobSeqNo") long jobSeqNo,
			@RequestParam("removeType") String removeType,
			ResourceRequest request, ResourceResponse response) throws IOException{
		
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String portletNamespace = themeDisplay.getPortletDisplay().getNamespace();
		try{
			if(removeType.equals("SIMULATION")){
				ProjectLocalServiceUtil.removeProjectBySimulationUuid(simulationUuid);
			}else if(removeType.equals("JOB")){
				ProjectLocalServiceUtil.removeProject(simulationUuid, portletNamespace, jobSeqNo);
			}
		}catch (Exception e) {
//			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
			e.printStackTrace();
		}
	}
	
	protected static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
