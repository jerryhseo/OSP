package kisti.edison.challenge.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.json.JSONFactoryUtil;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamService;
import kisti.edison.challenge.service.ChallengeTeamServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeTeamPortlet
 */
public class ChallengeTeamPortlet extends MVCPortlet {
	
	public void addChallengeTeam(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException{
		
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		
		System.out.println("\n\n\n\ntest add team");
		
		ChallengeTeam challengeTeam = (ChallengeTeam)actionRequest.getAttribute(WebKeys.CHALLENGE_TEAM);
		String teamName = ParamUtil.getString(actionRequest, "inputTeamName");
		System.out.println("input team name : "+teamName.toString());
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		
		long childChallengeId;
		childChallengeId = ParamUtil.getLong(actionRequest, "inputSubject");
		/*
		if(challengeTeamId >0){
			childChallengeId = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId).getChildChallengeId();
		}
		else{
			childChallengeId = ParamUtil.getLong(actionRequest, "inputSubject");
		}
		*/
		
		System.out.println("input team challengeTeamId : "+challengeTeamId);
		Map<Locale, String> subject = ChallengeLocalServiceUtil.getChallenge(ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId).getChallengeId()).getFieldMap();
		System.out.println("input team subject : "+subject.toString());
		
		Map<Locale, String> paperName = LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperName");
		System.out.println("input team papername : "+paperName.toString());
		Map<Locale, String> paperAbstract = LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperAbstract");
		System.out.println("input team paper abstract : "+paperAbstract.toString());
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String grade = ParamUtil.getString(actionRequest, "inputGrade");
		
		String phone = ParamUtil.getString(actionRequest, "phone1");
		if(ParamUtil.getString(actionRequest, "phone2").equals("") ||ParamUtil.getString(actionRequest, "phone3").equals("")){
			phone = "";
		}else{
			phone += "-";
			phone += ParamUtil.getString(actionRequest, "phone2");
			phone += "-";
			phone += ParamUtil.getString(actionRequest, "phone3");
		}
		
		//site join
		User currentUser = UserLocalServiceUtil.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();
				
		if(!GroupUtil.containsUser(groupId, currentUser.getUserId())){
			System.out.println("User add to site memeber : "+currentUser.getScreenName());
			System.out.println("User add to site memeber : groupId : "+groupId);
			System.out.println("Group Name : "+GroupLocalServiceUtil.getGroup(groupId).getName());
					
			EdisonUserUtil.addSiteRole(currentUser, groupId, EdisonRoleConstants.SITE_MEMBER);
			System.out.println("After add group : "+GroupUtil.containsUser(groupId, currentUser.getUserId()));
		}
				
		
		System.out.println("\nAdd challenge team information.\n");
		System.out.println("\nPhone Number test1 : "+phone);
		
		
			if(challengeTeamId > 0){
				try{
					//ChallengeTeam oldTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
					phone = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamLeaderMember(challengeTeamId, groupId).getPhone();
					
					
					ChallengeTeam setChallengeTeam = ChallengeTeamServiceUtil
							.updateChallengeTeam(serviceContext.getUserId(), childChallengeId, challengeTeamId, 
									teamName, subject, paperName, paperAbstract, uploadRequest, 
									grade, phone, serviceContext);
					actionRequest.setAttribute(WebKeys.CHALLENGE_TEAM, setChallengeTeam);	
					SessionMessages.add(actionRequest, "ChallengeTeam Added.");
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/editmyteam.jsp");
					}catch(Exception e){
						SessionErrors.add(actionRequest, e.getClass().getName());
						
						PortalUtil.copyRequestParameters(actionRequest, actionResponse);
						actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/editmyteam.jsp");
					}
			}else{
				try{
				ChallengeTeam setChallengeTeam = ChallengeTeamServiceUtil.addChallengeTeam(serviceContext.getUserId(), 
						teamName, subject, paperName, paperAbstract, uploadRequest, 
						grade, phone, childChallengeId, serviceContext);
				actionRequest.setAttribute(WebKeys.CHALLENGE_TEAM, setChallengeTeam);	
				SessionMessages.add(actionRequest, "ChallengeTeam Added.");
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/view.jsp");
				}catch(Exception e){
					SessionErrors.add(actionRequest, e.getClass().getName());
					
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					actionResponse.setRenderParameter("mcvPath", "/html/challengeparticipation/view.jsp");
				}
			}
	}
	
	public void deleteChallengeTeam(ActionRequest actionRequest, ActionResponse actionResponse){
		long childChallengeId = ParamUtil.getLong(actionRequest, "childChallengeId");
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
		
		try{
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(ChallengeTeam.class.getName(), actionRequest);
			
			actionResponse.setRenderParameter("childChallengeId", Long.toString(childChallengeId));
			
			ChallengeTeamServiceUtil.deleteChallengeTeam(challengeTeamId, serviceContext);
			actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
		}catch(Exception e){
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
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
		
		ChallengeTeamMemberServiceUtil.addChallengeTeamMember(serviceContext.getUserId(), challengeTeamId, 
				searchUserId, grade, phone, isLeader, serviceContext);
		
	}
	
	public void deleteChallengeTeamMember(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeTeam.class.getName(), actionRequest);
		long challengeTeamMemberId = ParamUtil.getLong(actionRequest, "challengeTeamMemberId");
		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();
		
		ChallengeTeamMember challengeTeamMember = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMember(challengeTeamMemberId);
		
		if(challengeTeamMember.isLeader()){
			ChallengeTeamLocalServiceUtil.deleteChallengeTeam(challengeTeamMember.getChallengeTeamId(), serviceContext);
			actionResponse.setRenderParameter("mcvPath", themeDisplay.getURLHome());
		}else{
			ChallengeTeamMemberLocalServiceUtil.deleteChallengeTeamMember(challengeTeamMember.getChallengeTeamMemberId());
		}		
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws PortletException, IOException{
		try{
			Challenge challenge = null;
			
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(ChildChallenge.class.getName(), renderRequest);
			
			//Test Role Check
			User currentUser = UserServiceUtil.getUserById(serviceContext.getUserId());
		
			//UserGroupRoleLocalServiceUtil.hasUserGroupRole(currentUser.getUserId(), serviceContext.getScopeGroupId(), "Challenge_Centermanager_NANO", true);
			
			String challengeFieldName = "";
			long groupId = serviceContext.getScopeGroupId();
			Role role = RoleLocalServiceUtil.getRole(serviceContext.getCompanyId(), "Administrator");
			List<Challenge> challenges = null;
			if(UserLocalServiceUtil.hasRoleUser(role.getRoleId(), currentUser.getUserId())){
				challenges = ChallengeServiceUtil.getChallenges(groupId);
			}else{
				challenges = ChallengeLocalServiceUtil.getChallengesByManagerRole(groupId, currentUser.getUserId(), serviceContext.getCompanyId());
			}
			
			if(challenges.size() == 0){
				System.out.println("\n\nChallenge Team Portlet \n : Challenge is not exist..");
				
				renderRequest.setAttribute(WebKeys.CHALLENGE, challenge);
			}
			
			challenge = (Challenge)renderRequest.getAttribute(WebKeys.CHALLENGE);
			
			if(challenge == null){
				challengeFieldName = ParamUtil.getString(renderRequest,  "challengeFieldName");
				//System.out.println("Test000000 in ChallengeTeam portlet.");
				if(challengeFieldName.equalsIgnoreCase("")){
					//System.out.println("Test11111 in ChallengeTeam portlet. ::"+challengeFieldName+"::");
					challenge = challenges.get(0);
				}else{
					//System.out.println("Test22222 in ChallengeTeam portlet.");
					OrderByComparatorFactory orderByComparatorFactory = 
							OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory
							.create("challenge", "field", true);
					
					challenge = ChallengeServiceUtil.getChallengeGroupAndField(
							serviceContext.getScopeGroupId(), 
							challengeFieldName, orderByComparator);
					
				}
				//System.out.println("Test333333 in ChallengeTeam portlet.");
			}
			
			renderRequest.setAttribute(WebKeys.CHALLENGE, challenge);
		}catch(Exception e){
			//System.out.println("\n\n\n\ntest challenge ChallengeTeam 2.\n\n\n");
			//throw new PortletException(e);
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		String type = ParamUtil.getString(resourceRequest, "type");
		long challengeTeamId = ParamUtil.getLong(resourceRequest, "challengeTeamId");
		ChallengeTeam challengeTeam;
		try {
			if(challengeTeamId > 0){
					challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
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
			}else if(type.equals("downloadTeamList")){
				downloadTeamInformation(resourceRequest, resourceResponse);
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
		
		
		
		//super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void downloadTeamInformation(ResourceRequest resourceRequest, ResourceResponse resourceResponse){
		long childChallengeId = ParamUtil.getLong(resourceRequest, "childChallengeId");
		
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//PageContext pageContext = resourceRequest.getAttribute(WebKeys.PAGE_)
		
		long groupId = themeDisplay.getScopeGroupId();
		
		if(childChallengeId>0){
			long challengeId;
			System.out.println("test1 download team list");
			
			try {
				challengeId = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId).getChallengeId();
				
				String challengeFieldName = ChallengeLocalServiceUtil.getChallenge(challengeId).getField(new Locale("en", "US"));
				//System.out.println("FieldName : "+challengeFieldName);
				String fileName = challengeFieldName+"_TeamList.xls";
				
				//System.out.println("test2 download team list : " + fileName);
				List<ChallengeTeam> challengeTeamList =
				ChallengeTeamLocalServiceUtil.getChallengeTeams(groupId, childChallengeId);
				//System.out.println("test3 download team list : " +challengeTeamList.size() );
				
				Workbook workBook = new HSSFWorkbook();
				Sheet sheet = workBook.createSheet("new sheet");
				
				//Create top row for names
				//No. | teamName | paperName | Phone | teamMemberName | Major | University | grade | email
				Row row = sheet.createRow(0);
				row.createCell(0).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-info-num"));
				row.createCell(1).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-team-name"));
				row.createCell(2).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-papername"));
				row.createCell(3).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-team-phone"));
				row.createCell(4).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-team-teammember"));
				row.createCell(5).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-major"));
				row.createCell(6).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-university"));
				row.createCell(7).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-grade"));
				row.createCell(8).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-email"));
			
				//set row for each challengeTeam
				int rowNum = 1;
				int teamIndex=0;
				while( teamIndex < challengeTeamList.size()){
					System.out.println("test4 download team list : "+ rowNum + " | " + teamIndex);
					
					ChallengeTeam team = challengeTeamList.get(teamIndex);
					
					List<ChallengeTeamMember> memberList = ChallengeTeamMemberLocalServiceUtil.getChallengeTeamMembers(groupId, team.getChallengeTeamId());
					for(ChallengeTeamMember member : memberList){
						//No. | teamName | paperName | Phone | teamMemberName | Major | University | grade | email |
						
						String grade = "";
						
						int memberGrade = Integer.parseInt(member.getGrade());
						switch(memberGrade){
							case 1: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-1");break;
							case 2: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-2");break;
							case 3: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-3");break;
							case 4: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-4");break;
							case 5: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-5");break;
							case 6: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-6");break;
							case 7: grade=LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-grade-7");break;
							default : grade="";break;
						}
						
						if(member.isLeader()){
							Row leaderRow = sheet.createRow(rowNum);
							leaderRow.createCell(0).setCellValue(teamIndex+1);
							leaderRow.createCell(1).setCellValue(team.getTeamName());
							leaderRow.createCell(2).setCellValue(team.getPaperName(themeDisplay.getLocale()));
							leaderRow.createCell(3).setCellValue(member.getPhone());
							leaderRow.createCell(4).setCellValue(member.getApplyUserName());
							leaderRow.createCell(5).setCellValue(member.getMajor(themeDisplay.getLocale()));
							leaderRow.createCell(6).setCellValue(member.getInstitute(themeDisplay.getLocale()));
							leaderRow.createCell(7).setCellValue(grade);
							leaderRow.createCell(8).setCellValue(member.getEmail());
						}else{
							Row leaderRow = sheet.createRow(rowNum);
							leaderRow.createCell(0).setCellValue("");
							leaderRow.createCell(1).setCellValue("");
							leaderRow.createCell(2).setCellValue("");
							leaderRow.createCell(3).setCellValue(member.getPhone());
							leaderRow.createCell(4).setCellValue(member.getApplyUserName());
							leaderRow.createCell(5).setCellValue(member.getMajor(themeDisplay.getLocale()));
							leaderRow.createCell(6).setCellValue(member.getInstitute(themeDisplay.getLocale()));
							leaderRow.createCell(7).setCellValue(grade);
							leaderRow.createCell(8).setCellValue(member.getEmail());
						}
						rowNum++;
					}
					teamIndex++;
				}
				//System.out.println("test export excel");
				
				
				try{
					OutputStream outStrm = null;
					
					HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(resourceRequest);
					HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(resourceResponse);
					resourceResponse.setContentType("application/vnd.ms-excel");
					resourceResponse.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
					resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
					outStrm = resourceResponse.getPortletOutputStream();
					workBook.write(outStrm);
					
				}catch(Exception e){
					e.printStackTrace();
					return;
				}
				
				
				
				
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
