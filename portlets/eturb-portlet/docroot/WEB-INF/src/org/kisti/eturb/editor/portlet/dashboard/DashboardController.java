
package org.kisti.eturb.editor.portlet.dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.eturb.dbservice.model.Project;
import org.kisti.eturb.dbservice.service.ProjectLocalServiceUtil;
import org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil;
import org.kisti.eturb.dbservice.service.persistence.ProjectPK;
import org.kisti.eturb.editor.portlet.dashboard.helper.EturbAppHelper;
import org.kisti.eturb.util.icebreaker.IBUserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class DashboardController{
	
	@Autowired
	private EturbAppHelper eturbAppHelper;
    
    private static Log log = LogFactory.getLog(DashboardController.class);
    private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
    
    
    @RequestMapping//default
    public String view(RenderRequest request, RenderResponse response, ModelMap model){
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Map params = RequestUtil.getParameterMap(request);
        try{
            User user = PortalUtil.getUser(request);
            long groupId = PortalUtil.getScopeGroupId(request);
            
            
            /*Program Use Site*/
            String useSite =  GetterUtil.getString(request.getPreferences().getValue("site", "ETURB"),"ETURB");
            model.addAttribute("site", useSite);
            
            List<String[]> dataStructurAppList = new ArrayList<String[]>();;
            if(useSite.equals("ETURB")){
            	dataStructurAppList.add(new String[]{"KFOIL_AirFoil_Para_parin", "1.0.0", "parametric"});
            }else{
            	dataStructurAppList.add(new String[]{"KFOIL_AirFoil_Para_parin_kflow", "1.0.0", "parametric"});
            	dataStructurAppList.add(new String[]{"kflow_mesher", "1.0.0", "meshparametric"});
            }
            
            for(String[] appList : dataStructurAppList){
            	String appName = appList[0];
            	String appVersion = appList[1];
            	String parameterName = appList[2];
            	
            	ScienceApp scienceAppParam = ScienceAppLocalServiceUtil.getScienceApp(appName, appVersion);
            	String paramInputPorts = ScienceAppLocalServiceUtil.getScienceAppInputPorts(scienceAppParam.getScienceAppId());
            	
            	long paramTypeId = 0;
            	JSONObject paramJSON = (JSONObject) JSONSerializer.toJSON(paramInputPorts);
            	Map paramJsonMap = paramJSON;
            	Iterator paramItr = paramJsonMap.keySet().iterator();
            	while(paramItr.hasNext()) {
            		String key = (String) paramItr.next();
            		Map paramMap = (Map)paramJsonMap.get(key);
            		Map dataTypeMap = (Map) paramMap.get("dataType_");
            		String dataTypeName = (String) dataTypeMap.get("name");
            		String dataTypeVersion = (String) dataTypeMap.get("version");
            		DataType paramDataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
            		paramTypeId = paramDataType.getTypeId();
            		break;
            	}
            	DataTypeStructure paramStructure = DataTypeStructureLocalServiceUtil.getDataTypeStructure(paramTypeId);
            	model.addAttribute(parameterName, paramStructure.getStructure());
            	
            }
            
            
            
            /*vcToken*/
            if(!user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId))){
                model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
            }else{
                String userVcToken = GetterUtil.getString(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN+ String.valueOf(groupId)),"");
                
                if(userVcToken.equals("")){
                    model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
                }else{
                    int userVcExpired = GetterUtil.getInteger(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED+ String.valueOf(groupId)),0);
                    if(userVcExpired <= Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))){
                        model.addAttribute("vcToken", IBUserTokenUtil.getOrCreateToken(groupId, user).getVcToken());
                    }else{
                        model.addAttribute("vcToken", userVcToken);
                    }
                }
            }
            
            /*Project*/
            long projectId = GetterUtil.getLong(params.get("projectId"),0);
            if(projectId!=0){
                ProjectPK projectPK = new ProjectPK(projectId, user.getUserId(), groupId);
                Project project = ProjectLocalServiceUtil.getProject(projectPK);
                model.addAttribute("project", project);
                model.addAttribute("openProjectModal", false);
                model.addAttribute("projectLoad", true);
            }else{
                model.addAttribute("projectLoad", false);
                model.addAttribute("openProjectModal", true);
            }
            
            long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "SimulationWorkbench_WAR_OSPWorkbenchportlet");
			model.addAttribute("workBenchPlid", plid);
			model.addAttribute("bcUse", GetterUtil.getBoolean(request.getPreferences().getValue("bcUse", "false"),false));
			
        }catch(Exception e){
            e.printStackTrace();
            SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
        }
        
        return "view";
    }
    
    @ActionMapping(value="projectAction")
    public void projectAction(ActionRequest request, ActionResponse response, Model model){
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        
        
        String mode = CustomUtil.strNull(params.get("actionMode"));
        long userId = PortalUtil.getUserId(request);
        long groupId = themeDisplay.getScopeGroupId();
        
        try{
            if(mode.equals(Constants.DELETE)){
                long projectId = GetterUtil.getLong(params.get("projectId"),0);
                ProjectLocalServiceUtil.removeProject(projectId, userId, groupId);
                returnView(request, response, 0, mode);
            }else if(mode.equals(Constants.ADD)){
                String name = CustomUtil.strNull(params.get("projectName"));
                String projectStructure = CustomUtil.strNull(params.get("projectStructure"));
                String analyzerStructure = CustomUtil.strNull(params.get("analyzerStructure"));
                
                Project project = ProjectLocalServiceUtil.modifyProject(0, userId, groupId, name, projectStructure, analyzerStructure, mode);
                returnView(request, response, project.getProjectId(), mode);
            }
        }catch(Exception e){
            e.printStackTrace();
            PortalUtil.copyRequestParameters(request, response);
            
            if(mode.equals(Constants.ADD)){
                SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
            }else if(mode.equals(Constants.DELETE)){
                SessionErrors.add(request, EdisonMessageConstants.DELETE_ERROR);
            }
        }
    }
    
    protected static void returnView(ActionRequest request, ActionResponse response, long projectId,String mode) throws PortletModeException {
        if(mode.equals(Constants.ADD)){
            SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
        }else{
            SessionMessages.add(request, EdisonMessageConstants.DELETE_SUCCESS);
        }
        
//      RequestUtil.copyRequestParameters(request, response, new String[] {"searchValue", "searchOption","searchStatus", "p_curPage", "clickTab", "scienceAppId", "isPort","redirectURL","redirectName"});
        response.setPortletMode(PortletMode.VIEW);
        response.setRenderParameter("projectId",  CustomUtil.strNull(projectId));
        
    }
    
    /*project  method - START*/
    @ResourceMapping(value="getProject")
    public void getProject(ResourceRequest request, ResourceResponse response) throws IOException{
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        try{
            long projectId = GetterUtil.getLong(params.get("projectId"));
            long userId = PortalUtil.getUserId(request);
            long groupId = themeDisplay.getScopeGroupId();
            
            ProjectPK projectPK = new ProjectPK(projectId, userId, groupId);
            Project project = ProjectLocalServiceUtil.getProject(projectPK);
            
            JSONObject obj = new JSONObject();
            obj.putAll(project.getModelAttributes());
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(obj.toString());
        }catch (Exception e) {
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
        }
    }
    
    @ResourceMapping(value="getProjectList")
    public void getProjectList(ResourceRequest request, ResourceResponse response) throws IOException{
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        try{
            long projectId = GetterUtil.getLong(params.get("projectId"),0);
            long userId = PortalUtil.getUserId(request);
            long groupId = themeDisplay.getScopeGroupId();
            
            int curPage = ParamUtil.get(request, "p_curPage", 1);
            int linePerPage = 10;
            
            int pagePerBlock = 10;
            int start = linePerPage * (curPage - 1);
            int totalCnt = ProjectLocalServiceUtil.countProjectByUserId(userId,groupId);
            List<Map<String,Object>> resultList = ProjectLocalServiceUtil.retrieveListProjectByUserId(userId, groupId, start, curPage*linePerPage);
            
            String portletNameSpace = response.getNamespace();
            String pageStr = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"searchProjectList", totalCnt, curPage, linePerPage, pagePerBlock);
            
            JSONObject obj = new JSONObject();
            obj.put("resultList", resultList);
            obj.put("pageStr", pageStr);
            obj.put("seq", totalCnt - ((curPage - 1)*linePerPage));
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(obj.toString());
        }catch(Exception e){
            e.printStackTrace();
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-search-error"));
        }
    }
    
    @ResourceMapping(value="updateProject")
    public void modifyProject(ResourceRequest request, ResourceResponse response) throws IOException{
        Map params = RequestUtil.getParameterMap(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        try{
            long projectId = GetterUtil.getLong(params.get("projectId"));
            long userId = PortalUtil.getUserId(request);
            long groupId = themeDisplay.getScopeGroupId();
            String projectStructure = GetterUtil.getString(params.get("projectStructure"));
            String analyzerStructure = GetterUtil.getString(params.get("analyzerStructure"));
            
            Project project = ProjectLocalServiceUtil.modifyProject(projectId, userId, groupId, "", projectStructure, analyzerStructure,Constants.UPDATE);
            JSONObject obj = new JSONObject();
            obj.putAll(project.getModelAttributes());
            
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(obj.toString());
        }catch(Exception e){
            e.printStackTrace();
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
        }
    }
    /*project  method - END*/
    
    /*simulation  method - START*/
    @ResourceMapping(value = "removeSimulationWithPath")
    public void removeSimulationWithPath(
    		@RequestParam("projectId") String projectId, 
            @RequestParam("executeId") String executeId, 
    		ResourceRequest request, ResourceResponse response
    		) throws IOException{
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	
    	try{
    		String userScreenName = themeDisplay.getUser().getScreenName();
    		SimulationLocalServiceUtil.removeSimulationWithPath(GetterUtil.getLong(projectId), executeId, userScreenName, Paths.get(eturbAppHelper.EXECUTE_BASE_PATH).toString());
    	}catch(Exception e){
            e.printStackTrace();
            handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-data-update-error"));
        }
    }
    		
    /*simulation  method - END*/
    
    protected static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(message);
        response.flushBuffer();
    }
}
