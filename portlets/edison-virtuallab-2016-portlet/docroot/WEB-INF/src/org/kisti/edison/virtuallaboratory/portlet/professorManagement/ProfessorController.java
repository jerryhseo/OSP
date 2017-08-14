package org.kisti.edison.virtuallaboratory.portlet.professorManagement;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.model.Professor;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class ProfessorController {
	private static Log log = LogFactoryUtil.getLog(ProfessorController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException {
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map params = RequestUtil.getParameterMap(upload);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		User user = PortalUtil.getUser(request);
		long userId = PortalUtil.getUserId(request);
		long virtualLabId = ParamUtil.get(request, "virtualLabId", 0);
		
		if(virtualLabId != 0){
			String admin = ParamUtil.get(request, "admin", "N");
			Map<String, Object> labInfo = VirtualLabLocalServiceUtil.getVirtualLabInfomation(virtualLabId, locale);
			Professor professor = ProfessorLocalServiceUtil.getProfessor(Long.parseLong(String.valueOf(labInfo.get("virtualLabProfessorSeq"))));
			User userInfomation = UserLocalServiceUtil.getUser(professor.getUserId());
			
			model.addAttribute("professor" , professor);
			model.addAttribute("userInfomation", userInfomation);
			
			model.addAttribute("introduce", StringUtil.split(professor.getIntroduce(locale), StringPool.NEW_LINE));
			model.addAttribute("paper", StringUtil.split(professor.getPaper(locale), StringPool.NEW_LINE));
			model.addAttribute("record", StringUtil.split(professor.getRecord(locale), StringPool.NEW_LINE));
			model.addAttribute("admin", admin);
			model.addAttribute("portraitURL", userInfomation.getPortraitURL(themeDisplay));
			
			return "professorManagement/professorView";
		}else if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) && virtualLabId == 0){
			return "professorManagement/professorManagementList";
		}else{
			String admin = "";
			User userInfomation = UserLocalServiceUtil.getUser(userId);
			Map<String, Object> professor = ProfessorLocalServiceUtil.getProfessor(userId, locale);
			professor.put("portraitURL", GetterUtil.getString(userInfomation.getPortraitURL(themeDisplay)));
			
			model.addAttribute("admin", admin);		
			model.addAttribute("userInfomation", userInfomation);
			model.addAttribute("professor", professor);
			
			return "professorManagement/professorManagement";
		}
	}
	
	//교수 이력정보 등록
	@ActionMapping(params = "myaction=addProfessor")
	public void addProfessor(ActionRequest request, ActionResponse response, ModelMap model) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();

			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map params = RequestUtil.getParameterMap(upload);
			long userId = Long.parseLong(String.valueOf(params.get("userId")));
			ProfessorLocalServiceUtil.addProfessor(request, params, locale, userId);
			response.setRenderParameter("myRender", "professorManagement");
			response.setRenderParameter("userId", String.valueOf(userId) );
			SessionMessages.add(request, EdisonMessageConstants.INSERT_SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.INSERT_ERROR);
		}
	}
	
	//교수 정보 조회(관리자)
	@ResourceMapping(value="professorList")
	public void getProfessorList(ResourceRequest request, ResourceResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		
		List<Map<String, Object>> professorList = null;
		int professorCount = 0;
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 10);
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		String portletNameSpace = response.getNamespace();
		
		String CurrentUrl = themeDisplay.getURLCurrent();
		String redirectURL= EdisonHttpUtil.removeAndencodeURL(CurrentUrl);
		
		UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
		Map<String, Object> params = RequestUtil.getParameterMap(upload);
		
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		
		Role professorRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),EdisonRoleConstants.PROFESSOR);
		params.put("roleId", professorRole.getRoleId());
		
		professorList = ProfessorLocalServiceUtil.getProfessorList(params, locale);
		professorCount = ProfessorLocalServiceUtil.getCountProfessor(params, locale);
		
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", professorCount, curPage, linePerPage, pagePerBlock);
		
		JSONObject obj = new JSONObject();
		obj.put("professorList", professorList);
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("professorCount", professorCount);
		obj.put("redirectURL", redirectURL);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	//교수정보 관리
	@RenderMapping(params = "myRender=professorManagement")
	public String professorManagement(RenderRequest request, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		User user = PortalUtil.getUser(request);
		long userId = Long.parseLong(String.valueOf(ParamUtil.get(request, "userId","")));
		String admin = "";
		if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR)){
			admin = "admin";
		}
		String redirectURL = HttpUtil.decodeURL(String.valueOf(ParamUtil.get(request, "redirectURL","")));
		
		User userInfomation = UserLocalServiceUtil.getUser(userId);
		Map<String, Object> professor = ProfessorLocalServiceUtil.getProfessor(userId, locale);
		professor.put("portraitURL", userInfomation.getPortraitURL(themeDisplay));
		
		model.addAttribute("myHistoryUrl", redirectURL);
		model.addAttribute("admin", admin);			
		model.addAttribute("userInfomation", userInfomation);
		model.addAttribute("professor", professor);
		
		return "professorManagement/professorManagement";
	}
	
	@ResourceMapping(value="syncProfessorList")
	public void syncProfessorList(ResourceRequest request, ResourceResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long roleId = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(),EdisonRoleConstants.PROFESSOR).getRoleId();
		List<User> userList = UserLocalServiceUtil.getRoleUsers(roleId);
		
		for(User user : userList){
			if(!ProfessorLocalServiceUtil.existProfessorByUserId(user.getUserId())){
				Professor professor = ProfessorLocalServiceUtil.createProfessor(CounterLocalServiceUtil.increment(Professor.class.getName()));
				professor.setUserId(user.getUserId());
				ProfessorLocalServiceUtil.addProfessor(professor);
			}
		}
	}
}
