package kisti.edison.challenge.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
import com.liferay.util.bridges.mvc.MVCPortlet;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeEvaluationPortlet
 */
public class ChallengeEvaluationPortlet extends MVCPortlet {
 
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
}
