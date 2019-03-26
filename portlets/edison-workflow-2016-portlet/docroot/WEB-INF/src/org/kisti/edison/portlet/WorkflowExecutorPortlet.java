package org.kisti.edison.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.model.Workflow;
import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.service.WorkflowLocalServiceUtil;
import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.util.TokenProviderUtil;
import org.kisti.edison.util.VCRegisterUtil;
import org.kisti.edison.wfapi.custom.IBUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

@Controller
@RequestMapping("VIEW")
public class WorkflowExecutorPortlet extends MVCPortlet{

    private static Log log = LogFactory.getLog(WorkflowExecutorPortlet.class);

    @RequestMapping
    public String view(RenderRequest request, RenderResponse response, ModelMap model)
        throws SystemException, IOException, PortalException, SQLException, ParseException{
        try{
            
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long appstorePlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
                "edisonscienceAppstore_WAR_edisonappstore2016portlet");
            Locale[] availableLanguage = LanguageUtil.getAvailableLocales(); // 서비스언어
            User currentUser = themeDisplay.getUser();
            ScienceApp textEditor = ScienceAppLocalServiceUtil.getTextEditorScienceApp();
            ScienceApp fileEditor = ScienceAppLocalServiceUtil.getFileEditorScienceApp();
            ScienceApp structuredEditor = ScienceAppLocalServiceUtil.getStructuredEditorScienceApp();
            String workflowId = ParamUtil.get(request, "workflowId", "0");
            String title = CustomUtil.strNull("title", null);
//            if(StringUtils.hasText(workflowId)){
//                int simulationCount = WorkflowSimulationLocalServiceUtil
//                .getCountWorkflowSimulations(Long.valueOf(workflowId), title, currentUser.getUserId());
//            }
            
            // Workflow Edit Permission Check
            boolean isAdmin = false;
            int wfScienceAppCnt = ScienceAppLocalServiceUtil.countScienceAppByWorkflowId(Long.parseLong(workflowId));
            if(wfScienceAppCnt <= 0){
            	isAdmin = true;
            } else {
            	ScienceApp wfScienceApp = ScienceAppLocalServiceUtil.getScienceAppByWorkflowId(Long.parseLong(workflowId));
            	
            	// Not Open Workflow-ScienceApp
            	if(wfScienceApp.getStatus() != 1901004){
            		boolean powerUser = EdisonUserUtil.isRegularRole(currentUser, RoleConstants.POWER_USER);
            		boolean admin = EdisonUserUtil.isRegularRole(currentUser, RoleConstants.ADMINISTRATOR);
            		boolean siteAdmin = EdisonUserUtil.isSiteRole(currentUser, themeDisplay.getScopeGroupId(), RoleConstants.SITE_ADMINISTRATOR);
            		if(powerUser || admin || siteAdmin || wfScienceApp.getAuthorId() == currentUser.getUserId()){
            			isAdmin = true;
            		}
            	}
            }
            
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("textEditor", textEditor);
            model.addAttribute("fileEditor", fileEditor);
            model.addAttribute("structuredEditor", structuredEditor);
            model.addAttribute("ableLang", availableLanguage);
            model.addAttribute("defaultLang", themeDisplay.getLanguageId());
            model.addAttribute("appstorePlid", appstorePlid);
            model.addAttribute("username", currentUser.getScreenName());
            model.addAttribute("companyGroupId", themeDisplay.getCompanyGroupId());
            model.addAttribute("groupId", PortalUtil.getScopeGroupId(request));
            model.addAttribute("isAdmin", isAdmin);

        }catch (Exception e){
            log.error(e);
            e.printStackTrace();
            SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
        }
        return "executor/view";
    }
    
    @SuppressWarnings("unchecked")
    @ResourceMapping(value = "getIcebreakerAccessToken")
    public void getIcebreakerAccessToken(ResourceRequest request, ResourceResponse response)
        throws IOException, NumberFormatException, PortalException, SystemException, ParseException{
        Map<String, Object> param = RequestUtil.getParameterMap(request);
        long groupId = Long
            .parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
        
        String icebreakerUrl = CustomUtil
        		.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
        
        IcebreakerVcToken icebreakerVcToken = getOrCreateToken(user, groupId);

        JSONObject obj = JSONFactoryUtil.createJSONObject();
        obj.put("icebreakerUrl", icebreakerUrl);
        obj.put("icebreakerVcToken", icebreakerVcToken.getVcToken());
        obj.put("groupId", groupId);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(obj.toString());
    }

    private IcebreakerVcToken getOrCreateToken(User user, long groupId) throws PortalException, SystemException,
        NumberFormatException, MalformedURLException, IOException, ParseException{

        IcebreakerVcToken icebreakerVcToken = new IcebreakerVcToken();

        Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
        String userScreenName = "";
        String userPassword = "";

        String universityField = (String) user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
        String virtualLabId = (String) user.getExpandoBridge().getAttribute(EdisonExpando.USER_LAB_ID);
        String classId = (String) user.getExpandoBridge().getAttribute(EdisonExpando.USER_CLASS_ID);
        String majorField = (String) user.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR);

        if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR)
            || EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
            userScreenName = (String) thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
            userPassword = (String) thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_PWD);
        }else{
            userScreenName = String.valueOf(user.getScreenName());
            userPassword = user.getPassword();
        }

        if(VCRegisterUtil.isVcInfo(groupId, userScreenName) == 200){
            if(user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))){

                icebreakerVcToken.setVcToken(CustomUtil.strNull(
                    user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))));
                icebreakerVcToken.setVcTokenExpired(CustomUtil.strNull(
                    user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId)),
                    "0"));

                if(Integer.parseInt(icebreakerVcToken.getVcTokenExpired()) <= Integer
                    .parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))){
                    // 시간이 지난 토큰인 경우 새로운 토큰 발행 및 커스텀 필드 저장
                    icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, userScreenName, userPassword);

                    // Icebreaker에 회원정보는 있으나 로그인 되지 않는 경우 비밀번호 변경으로 인한것으로 판단하여
                    // 비밀번호
                    // update후에 다시 로그인하여 토큰을 요청 합니다.
                    if(icebreakerVcToken.getResultCode() != 200){
                        int updateResult = VCRegisterUtil.VCUpdate(groupId, userScreenName, userPassword,
                            user.getEmailAddress());
                        if(updateResult == 200){
                            icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, userScreenName, userPassword);
                        }
                    }

                    if(icebreakerVcToken.getResultCode() == 200){
                        icebreakerVcToken.setVcToken(icebreakerVcToken.getVcToken());
                        icebreakerVcToken.setVcTokenExpired(icebreakerVcToken.getVcTokenExpired());

                        user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId),
                            icebreakerVcToken.getVcToken());
                        user.getExpandoBridge().setAttribute(
                            EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId),
                            icebreakerVcToken.getVcTokenExpired());
                    }else{
                        log.debug("Icebreaker getOrCreateToken Error !!");
                    }
                }else{
                    // icebreaker 계정은 있으나 포털에 expando가 없는 경우 expando 추가 생성
                    icebreakerVcToken = IBUtil.createExpandoUserVctoken(user, groupId, userScreenName,
                        userPassword);
                }
            }
        }else{ // Icebreaker 계정이 없는경우
            // icebreaker 계정도 없는 경우 생성
            int resultRegist = VCRegisterUtil.VCRegist(groupId, userScreenName, userPassword, user.getEmailAddress(),
                user.getFirstName(), universityField, virtualLabId, classId, majorField);
            if(resultRegist == 201){
                // icebreaker 가입이 성공한 경우 신규 토큰 발행 및 커스텀 필드 추가
                icebreakerVcToken = IBUtil.createExpandoUserVctoken(user, groupId, userScreenName,
                    userPassword);
            }
        }
        return icebreakerVcToken;
    }
    
    @ResourceMapping(value = "copyParentNodeFiles")
    public void copyParentNodeFiles(ResourceRequest request, ResourceResponse response) throws IOException{
    	
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			Map params = RequestUtil.getParameterMap(request);
			String copyFileObj = CustomUtil.strNull(params.get("copyFileObj"), "");
			
			JSONObject copyFileobj = JSONFactoryUtil.createJSONObject(copyFileObj);
			Long simulationJobId = copyFileobj.getLong("simulationJobId");
			WorkflowSimulationJob job = WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(simulationJobId);
			
			String fileName = copyFileobj.getString("fileName");
			String srcPportName = CustomUtil.strNull(copyFileobj.getString("sourcePortName"));
			String simulationUuid = CustomUtil.strNull(copyFileobj.getString("simulationUuid"));
			String simulationJobUuid = CustomUtil.strNull(copyFileobj.getString("simulationJobUuid"));
			JSONObject outputData = copyFileobj.getJSONArray("jobData").getJSONObject(0);
			
			User sourceUser = UserLocalServiceUtil.getUser(job.getUserId());
			String source = simulationUuid + "/" + simulationJobUuid + "/" + srcPportName + "/" +fileName;
			String srcRepositoryType = CustomUtil.strNull(copyFileobj.getString("repositoryType_"));
			
			User targetUser = themeDisplay.getUser();
			Date thisDate = new Date();
			String target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(thisDate) + "/" + fileName;
			String targetRepositoryType = CustomUtil.strNull(copyFileobj.getString("targetRepositoryType"));
			
			// copyFile
			JSONObject copyResultObj = OSPFileLocalServiceUtil.setJobDataWithFileFormOutputData(sourceUser.getScreenName(), simulationUuid, simulationJobUuid, outputData, targetUser.getScreenName());
			
			JSONObject returnObj = JSONFactoryUtil.createJSONObject();
			returnObj.put("jobData", copyResultObj);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(returnObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof WorkflowException){
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-workflow-not-run-workbench-message"));
			}else{
				e.printStackTrace();
				handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), LanguageUtil.get(themeDisplay.getLocale(), "edison-workflow-not-run-workbench-message"));
			}
		}
	}
    
    public static void handleRuntimeException(Exception ex, HttpServletResponse response,String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
