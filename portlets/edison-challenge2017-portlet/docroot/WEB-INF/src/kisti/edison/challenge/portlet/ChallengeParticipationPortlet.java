package kisti.edison.challenge.portlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeParticipation
 */
public class ChallengeParticipationPortlet extends MVCPortlet {
	//DLFolderConstants.DEFAULT_PARENT_FOLDER_ID

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws PortletException, IOException{
		ServiceContext serviceContext;
		ChallengeTeam challengeTeam = null;
		try {
			serviceContext = ServiceContextFactory
						.getInstance(ChallengeTeam.class.getName(), renderRequest);
			if(serviceContext.isSignedIn()){
				User currentUser = UserServiceUtil.getUserById(serviceContext.getUserId());

				challengeTeam = ChallengeTeamLocalServiceUtil.getUserCurrentTeam(serviceContext.getScopeGroupId(), currentUser.getUserId());
				//System.out.println("\n\n\n\n");
				//System.out.println("challengeTeam exist?");
				if(challengeTeam == null){
					//System.out.println("challenge team is null");
				}
				else{
					//System.out.println(challengeTeam.toString());
				}
				//System.out.println("\n\n\n\n");
				renderRequest.setAttribute(WebKeys.CHALLENGE_TEAM, challengeTeam);
				
			}
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("[RENDER] test1");
		challengeTeam = (ChallengeTeam)renderRequest.getAttribute(WebKeys.CHALLENGE_TEAM);
		
		//System.out.println("[RENDER] test2");
		if(challengeTeam == null){
			renderRequest.setAttribute(WebKeys.CHALLENGE_TEAM, null);
		}else{
			renderRequest.setAttribute(WebKeys.CHALLENGE_TEAM, challengeTeam);
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	public void addChallengeTeam(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException{
		
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		
		//site join
		User currentUser = UserLocalServiceUtil.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();
		
		if(!GroupUtil.containsUser(groupId, currentUser.getUserId())){
			System.out.println("User add to site memeber : "+currentUser.getScreenName());
			System.out.println("User add to site memeber : groupId : "+groupId);
			System.out.println("Group Name : "+GroupLocalServiceUtil.getGroup(groupId).getName());
			
			//UserLocalServiceUtil.addGroupUser(groupId, currentUser);
			//List<Role> siteRoleList = RoleLocalServiceUtil.getGroupRoles(groupId);
			//UserGroupLocalServiceUtil.addGroupUserGroup
			
			//GroupUtil.addUser(groupId, currentUser.getUserId());
			//RoleLocalServiceUtil.getRole
			//UserGroupLocalServiceUtil.addGroupUserG
			EdisonUserUtil.addSiteRole(currentUser, groupId, EdisonRoleConstants.SITE_MEMBER);
			System.out.println("After add group : "+GroupUtil.containsUser(groupId, currentUser.getUserId()));
			//ResourcePermissionLocalServiceUtil.getResourcePermissions
			//UserUtil.update(currentUser);
		}
		
		System.out.println("\n\n\n\ntest add team");
		
		ChallengeTeam challengeTeam = (ChallengeTeam)actionRequest.getAttribute(WebKeys.CHALLENGE_TEAM);
		String teamName = ParamUtil.getString(actionRequest, "inputTeamName");
		//System.out.println("input team name : "+teamName.toString());
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		long childChallengeId;
		childChallengeId = ParamUtil.getLong(actionRequest, "inputSubject");
		
		//boolean agreement = ParamUtil.getBoolean(actionRequest, "<portlet:namespace/>agreement2");
		//System.out.println("input team challengeTeamId : "+challengeTeamId);
		Map<Locale, String> subject = ChallengeLocalServiceUtil.getChallenge(ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId).getChallengeId()).getFieldMap();
		//System.out.println("input team subject : "+subject.toString());
		
		Map<Locale, String> paperName = LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperName");
		//System.out.println("input team papername : "+paperName.toString());
		Map<Locale, String> paperAbstract = LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperAbstract");
		//System.out.println("input team paper abstract : "+paperAbstract.toString());
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String grade = ParamUtil.getString(actionRequest, "inputGrade");
		
		String phone = ParamUtil.getString(actionRequest, "phone1");
		if(phone.equals("")){
			phone="010-0000-0000";
		}else{
			phone += "-";
			phone += ParamUtil.getString(actionRequest, "phone2");
			phone += "-";
			phone += ParamUtil.getString(actionRequest, "phone3");
		}
		System.out.println("Phone : "+phone);
		
		
		
		if(challengeTeamId > 0){
			try{
				System.out.println("Update current challenge team.");
				ChallengeTeam setChallengeTeam = ChallengeTeamServiceUtil
						.updateChallengeTeam(serviceContext.getUserId(), childChallengeId, challengeTeamId, 
								teamName, subject, paperName, paperAbstract, uploadRequest, 
								grade, phone, serviceContext);
				actionRequest.setAttribute(WebKeys.CHALLENGE_TEAM, setChallengeTeam);	
				SessionMessages.add(actionRequest, "ChallengeTeam Update.");
				System.out.println("challenge Team update ");
				//System.out.println(challengeTeam.toString()+"\n\n\n\n\n\n");
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/editmyteam.jsp");
				}catch(Exception e){
					SessionErrors.add(actionRequest, e.getClass().getName());
						
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/editmyteam.jsp");
				}
		}else{
			try{
			System.out.println("\nAdd challenge team information.\n");
			ChallengeTeam setChallengeTeam = ChallengeTeamServiceUtil.addChallengeTeam(serviceContext.getUserId(), 
					teamName, subject, paperName, paperAbstract, uploadRequest, 
					grade, phone, childChallengeId, serviceContext);
			actionRequest.setAttribute(WebKeys.CHALLENGE_TEAM, setChallengeTeam);	
			
			SessionMessages.add(actionRequest, "ChallengeTeam Added.");
			///System.out.println(challengeTeam.toString()+"\n\n\n\n\n\n");
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/view.jsp");
			}catch(Exception e){
				SessionErrors.add(actionRequest, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/view.jsp");
			}
		
		}
	}
	
	public void withdrawChallengeTeam(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		long challengeTeamMemberId = ParamUtil.getLong(actionRequest, "challengeTeamMemberId");
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		ChallengeTeamMember member = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMember(challengeTeamMemberId);
		
		System.out.println("Team Withdraw Test1 : ");
		System.out.println(challengeTeam.toString());
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();
		
		User currentUser = UserServiceUtil.getUserById(serviceContext.getUserId());
		
		System.out.println("Team Withdraw Test2 : ");
		
		boolean leaderMemberDelete = false;
		List<ChallengeTeamMember> memberList = ChallengeTeamMemberLocalServiceUtil
				.getChallengeTeamMembers(serviceContext.getScopeGroupId(), challengeTeam.getChallengeTeamId());
		
		System.out.println(memberList.toString());
		System.out.println("\n\n");
		
		if(memberList.size()==1){
			System.out.println("Team Withdraw Test3 : ");
			ChallengeTeamLocalServiceUtil.deleteChallengeTeam(challengeTeam.getChallengeTeamId(), serviceContext);
			actionResponse.setRenderParameter("mcvPath", themeDisplay.getURLHome());
		}else{
			if(member.isLeader()){
				ChallengeTeamLocalServiceUtil.deleteChallengeTeam(challengeTeam.getChallengeTeamId(), serviceContext);
				actionResponse.setRenderParameter("mcvPath", themeDisplay.getURLHome());
			}else{
				ChallengeTeamMemberLocalServiceUtil.deleteChallengeTeamMember(member.getChallengeTeamMemberId());
			}
		}
	}
	
	public void deleteChallengeTeamMember(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		long challengeTeamMemberId = ParamUtil.getLong(actionRequest, "challengeTeamMemberId");
		ChallengeTeamMemberServiceUtil.deleteChallengeTeamMember(challengeTeamMemberId, serviceContext);
	}
	
	public void addChallengeTeamMember(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		String searchScreenName = ParamUtil.getString(actionRequest, "searchUserScreenName");
		long searchUserId = ParamUtil.getLong(actionRequest, "searchUserId");
		String grade = ParamUtil.getString(actionRequest, "inputUserGrade");
		String phone = "";
		boolean isLeader = false;
		long groupId = serviceContext.getScopeGroupId();
		
		if(!GroupUtil.containsUser(groupId, searchUserId)){
			EdisonUserUtil.addSiteRole(UserLocalServiceUtil.getUser(searchUserId), groupId, EdisonRoleConstants.SITE_MEMBER);
		}
		
		if(ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMember(serviceContext.getScopeGroupId(), challengeTeamId, searchUserId)==null){
			ChallengeTeamMemberServiceUtil.addChallengeTeamMember(serviceContext.getUserId(), challengeTeamId, 
					searchUserId, grade, phone, isLeader, serviceContext);
			SessionMessages.add(actionRequest, "Participation new team member.");
		}else{
			SessionMessages.add(actionRequest, "Searched member is already your team member.");
		}
		PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/editmyteam.jsp");
	}
	
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		String type = ParamUtil.getString(resourceRequest, "type");
		long challengeTeamId = ParamUtil.getLong(resourceRequest, "challengeTeamId");
		ChallengeTeam challengeTeam=null;
		System.out.println("Test serve resource url");
		System.out.println("TYPE : "+type);
		try {
			challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
			resourceRequest.setAttribute(WebKeys.CHALLENGE_TEAM, challengeTeam);
			if(type.equals("paperFile")){
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"papersubmission", challengeTeam.getPaperFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("paperFilePDF")){
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"papersubmissionPDF", challengeTeam.getPaperPDFFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("presentationFile")){
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"presentationsubmission", challengeTeam.getPresentationFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("getuserInformation")){
				System.out.println("tset user information");
				ChallengeTeamLocalServiceUtil.getUserInfo(challengeTeamId, resourceRequest, resourceResponse);
			}
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			//System.out.println("\n\n\n\ntest challenge ChallengeTeam 2.\n\n\n");
			//e.printStackTrace();
		} catch(SystemException e){
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
