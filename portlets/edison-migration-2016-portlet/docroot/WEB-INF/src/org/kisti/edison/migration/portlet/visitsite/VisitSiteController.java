package org.kisti.edison.migration.portlet.visitsite;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class VisitSiteController {
	private static Log log = LogFactoryUtil.getLog(VisitSiteController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) throws SystemException, PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Role tempUserRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), EdisonRoleConstants.TEMP_USER);
		List<User> tempUserList = UserLocalServiceUtil.getRoleUsers(tempUserRole.getRoleId());
		
		
		model.addAttribute("tempUserCnt", tempUserList.size());
		return "migration";
	}
	
	
	@ActionMapping(params="myaction=userMigration")
	public void userMigration(ActionRequest request, ActionResponse response, ModelMap model) throws PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Role tempUserRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), EdisonRoleConstants.TEMP_USER);
		List<User> tempUserList = UserLocalServiceUtil.getRoleUsers(tempUserRole.getRoleId());
		
		for(User user : tempUserList){
			long virtualLabId = GetterUtil.getLong(user.getExpandoBridge().getAttribute(EdisonExpando.USER_LAB_ID),0);
			if(virtualLabId!=0){
//				long groupId = VirtualLabLocalServiceUtil.getVirtualLab(virtualLabId).getGroupId();
//				Group group = GroupLocalServiceUtil.getGroup(groupId);
//				user.getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
//				user.getExpandoBridge().setAttribute(EdisonExpando.TEMP_USER_JOIN_FROM_GROUP,String.valueOf(group.getGroupId()));
//				log.debug("USER_ID==>"+user.getUserId()+
//						",PRE_USER_VISIT_SITE==>"+user.getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE)+
//						",NEW_USER_VISIT_SITE==>"+group.getName()
//				        +",TEMP_USER_JOIN_FROM_GROUP==>"+String.valueOf(group.getGroupId()));
			}
		}
	}
}
