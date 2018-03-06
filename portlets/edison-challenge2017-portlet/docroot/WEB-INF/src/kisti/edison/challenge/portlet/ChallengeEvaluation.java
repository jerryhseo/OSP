package kisti.edison.challenge.portlet;

import java.io.IOException;
import java.util.ArrayList;
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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeEvaluation
 */
public class ChallengeEvaluation extends MVCPortlet {

	public void addChallengeEvaluationScore(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		long childChallengeId = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId).getChildChallengeId();
		String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
		
		
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeEvaluationScore.class.getName(), actionRequest);
		
		//Get ChallengeEvaluation list for this running child challenge
		List<ChallengeEvaluationManagement> evaluationManagementList = ChallengeEvaluationManagementLocalServiceUtil
				.getChallengeEvaluationManagementes(serviceContext.getScopeGroupId(), childChallengeId);
		//Get current user has a score about this team
		List<ChallengeEvaluationScore> scoreList = null;
		
		System.out.println("\n\n\n\n\n\nSearch Challenge Score : ");
		
		scoreList =	ChallengeEvaluationScoreLocalServiceUtil
				.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(serviceContext.getScopeGroupId(), 
						challengeTeamId, serviceContext.getUserId());
		//System.out.println(scoreList.toString());
		System.out.println("End Search Score .");
		if(scoreList.size()>0 ){
			//Update input evaluation score
			System.out.println("Update current Score");
			try{
				for(ChallengeEvaluationScore challengeEvaluationScore : scoreList){
					System.out.println("Update current Score Test 1 : "+challengeEvaluationScore.getChallengeEvaluationManagementId());
					long ChallengEvaluationManagementId = challengeEvaluationScore.getChallengeEvaluationManagementId();
					double score = ParamUtil.getDouble(actionRequest, String.valueOf(ChallengEvaluationManagementId));
					System.out.println("Update current Score Test 2 : "+score);
					challengeEvaluationScore = ChallengeEvaluationScoreServiceUtil.updateChallengeEvaluationScore(serviceContext.getUserId(), 
							challengeEvaluationScore.getChallengeEvaluationScoreId(), score, serviceContext);
					System.out.println("Update current Score Test 3 : "+challengeEvaluationScore);
					SessionMessages.add(actionRequest, "Challenge Evaluation Score Updated.");
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
					actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluation/view.jsp");
				}
			}catch(Exception e){
				SessionErrors.add(actionRequest, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluation/view.jsp");
			}
		}else{
			//Create all evaluation score
			try{
				System.out.println("Create New Evaluation Score");
				
				for(ChallengeEvaluationManagement managementItem :evaluationManagementList){
					double score = ParamUtil.getDouble(actionRequest, String.valueOf(managementItem.getChallengeEvaluationManagementId()));
					ChallengeEvaluationScore tempScore = ChallengeEvaluationScoreServiceUtil.addChallengeEvaluationScore(serviceContext.getUserId(), challengeTeamId, 
							managementItem.getChallengeEvaluationManagementId(), score, serviceContext);
					System.out.println("team : " + ChallengeTeamLocalServiceUtil.getChallengeTeam(tempScore.getChallengeTeamId()).getTeamName());
					System.out.println("Score Item ID : "+managementItem.getChallengeEvaluationManagementId());
					System.out.println("create score : " + tempScore.getScore());
				}

						
				SessionMessages.add(actionRequest, "Challenge Evaluation Score Added.");
				System.out.println("end of create.");
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluation/view.jsp");
			}catch(Exception e){
				SessionErrors.add(actionRequest, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluation/view.jsp");
			}
		}
		
		
		
	}
	
	public void deleteChallengeEvaluationScore(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long challengeEvaluationScoreId = ParamUtil.getLong(actionRequest, "challengeEvaluationScoreId");
		long challengeTeamId = ParamUtil.getLong(actionRequest, "challengeTeamId");
		String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
		
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeEvaluationScore.class.getName(), actionRequest);
		
		
		List<ChallengeEvaluationScore> scoreList = new ArrayList<ChallengeEvaluationScore>(); 
		scoreList = ChallengeEvaluationScoreLocalServiceUtil
		.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(serviceContext.getScopeGroupId(), 
				challengeTeamId, serviceContext.getUserId());
		if(scoreList.size() > 0){
			for(ChallengeEvaluationScore score : scoreList){
				ChallengeEvaluationScore challengeEvaluationScore = ChallengeEvaluationScoreServiceUtil
						.deleteChallengeEvaluationScore(score.getChallengeEvaluationScoreId(), serviceContext);
			}
		}
		
		
		actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
		PortalUtil.copyRequestParameters(actionRequest, actionResponse);
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
				challenges = ChallengeLocalServiceUtil.getChallengesByEvaluationRole(groupId, currentUser.getUserId(), serviceContext.getCompanyId());
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

}
