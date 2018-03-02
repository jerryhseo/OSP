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
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeEvaluationManagementServiceUtil;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeEvaluationManagementPortlet
 */
public class ChallengeEvaluationManagementPortlet extends MVCPortlet {

	public void addChallengeEvaluationManagement(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long challengeEvaluationManagementId = ParamUtil.getLong(actionRequest, "challengeEvaluationManagementId");
		long childChallengeId = ParamUtil.getLong(actionRequest, "childChallengeId");
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeEvaluationManagement.class.getName(), actionRequest);
		
		
		Map<Locale, String> assessment = LocalizationUtil.getLocalizationMap(actionRequest, "inputAssessment");
		double distribution = ParamUtil.getDouble(actionRequest, "inputDistribution");
		String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
		
		ChallengeEvaluationManagement challengeEvaluationManagement = null;
		System.out.println("\n\n\n\n\n\n====================");
		System.out.println("Test Evaluation Add 1");
		System.out.println("Distribution : " + distribution);
		System.out.println("assessment : " + assessment);
		System.out.println("challengeFieldName : " + challengeFieldName);
		
		
		if(challengeEvaluationManagementId > 0){
			try{
				System.out.println("Update Evaluation Item");
				challengeEvaluationManagement = ChallengeEvaluationManagementServiceUtil
						.updateChallengeEvaluationManagement(serviceContext.getUserId(), childChallengeId, challengeEvaluationManagementId, assessment, distribution, serviceContext);
				
				SessionMessages.add(actionRequest, "Challenge Evaluation Assessment Item Added.");
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluationmanagement/view.jsp");
			}catch(Exception e){
				SessionErrors.add(actionRequest, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluationmanagement/view.jsp");
			}
		}else{
			try{
				System.out.println("Create New Evaluation Item");
				challengeEvaluationManagement = ChallengeEvaluationManagementServiceUtil
						.addChallengeEvalutionManagement(serviceContext.getUserId(), childChallengeId, assessment, distribution, serviceContext);
				//SessionMessages.add(actionRequest, "Challenge Evaluation Assessment Item Added.");
				System.out.println("end of create.");
				System.out.println(challengeEvaluationManagement.toString());
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluationmanagement/view.jsp");
			}catch(Exception e){
				SessionErrors.add(actionRequest, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mcvPath", "/html/challengeevaluationmanagement/view.jsp");
			}
		}
	}
	
	public void DeleteChallengeEvaluationManagement(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long challengeEvaluationManagementId = ParamUtil.getLong(actionRequest, "challengeEvaluationManagementId");
		long childChallengeId = ParamUtil.getLong(actionRequest, "childChallengeId");
		String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
		
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(ChallengeEvaluationManagement.class.getName(), actionRequest);
		
		ChallengeEvaluationManagement challengeEvaluationManagement = null;
		challengeEvaluationManagement = ChallengeEvaluationManagementServiceUtil
					.deleteChallengeEvaluationManagement(challengeEvaluationManagementId, serviceContext);
		actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
		actionResponse.setRenderParameter("childChallengeId", String.valueOf(childChallengeId));
		
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
}
