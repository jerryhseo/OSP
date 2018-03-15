package kisti.edison.challenge.portlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChildChallengeServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeAdminPortlet
 */
public class ChallengeAdminPortlet extends MVCPortlet {
 
	public void addChildChallenge(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException{
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(ChildChallenge.class.getName(), actionRequest);
			
			System.out.println("challenge admin test add1");
			
			long challengeId = ParamUtil.getLong(actionRequest, "challengeId");
			long childChallengeId = ParamUtil.getLong(actionRequest, "childChallengeId");
			String challengeFieldName = ParamUtil.getString(actionRequest, "challengeFieldName");
			
			
			int number = ParamUtil.getInteger(actionRequest, "inputNum");
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date presentationDay = new Date(ParamUtil.getString(actionRequest, "inputPresentationDay"));
			Date paperStartDay = new Date(ParamUtil.getString(actionRequest, "inputPaperDay"));
			Date paperEndDay = new Date(ParamUtil.getString(actionRequest, "inputPaperDayEnd"));
			Date evaluationDay =  new Date(ParamUtil.getString(actionRequest, "inputEvaluationDay"));
			Date challengeStartDay = new Date(ParamUtil.getString(actionRequest, "inputChallengeDay"));
			Date challengeEndDay = new Date(ParamUtil.getString(actionRequest, "inputChallengeDayEnd"));
			String challengeStatus = ParamUtil.getString(actionRequest, "inputChallengeStatus");
		
			
			/*
			System.out.println("\n\n\n\n\n\n\n\n===============================");
			System.out.println("Challenge Id : "+challengeId);
			System.out.println("challengeFieldName : "+challengeFieldName);
			System.out.println("childChallengeId : "+childChallengeId);
			System.out.println("challengeStartDay : "+challengeStartDay);
			System.out.println("challengeEndDay : "+challengeEndDay);
			System.out.println("challengeStatus : "+challengeStatus);
			System.out.println("============================================\n\n\n\n\n\n\n\n");
			*/
			
			if(childChallengeId > 0){
				System.out.println("\n\n\n\n\n============== update child challenge ============");
				try {
					ChildChallengeServiceUtil.updateChildChallenge(serviceContext.getUserId(), 
							challengeId, childChallengeId, number, presentationDay, 
							paperStartDay, paperEndDay, evaluationDay, challengeStartDay, 
							challengeEndDay, challengeStatus, serviceContext);
					SessionMessages.add(actionRequest, "Child Challenge updated\n\n\n\n\n");
					System.out.println("Challenge Field Name : " + challengeFieldName);
					actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
					
				} catch (Exception e) {
					SessionErrors.add(actionRequest, e.getClass().getName());
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					
					System.out.println("\n\n\n\n\n Copy Parameter");
					System.out.println(actionRequest.getParameterMap());
					System.out.println("\n===================================\n");
					System.out.println(actionResponse.getRenderParameterMap());
					System.out.println("\n\n\n\n\n");
					
					actionResponse.setRenderParameter("mvcPath", 
							"/html/challengeadmin/edit_childchallenge.jsp");
				}
			} else {
				System.out.println("\n\n\n\n\n============== create new child challenge ============\n\n\n\n\n");
				try{
					ChildChallengeServiceUtil.addChildChallenge(serviceContext.getUserId(),
							challengeId, number, presentationDay, paperStartDay, 
							paperEndDay, evaluationDay, challengeStartDay, challengeEndDay, 
							challengeStatus, serviceContext);
					SessionMessages.add(actionRequest, "Child Challenge added");
					
					actionResponse.setRenderParameter("challengeFieldName", challengeFieldName);
				} catch (Exception e) {
					SessionErrors.add(actionRequest, e.getClass().getName());
					
					PortalUtil.copyRequestParameters(actionRequest, actionResponse);
					
					System.out.println("\n\n\n\n\n Copy Parameter");
					System.out.println(actionRequest.getParameterMap());
					System.out.println("error\n\n");
					System.out.println(e);
					System.out.println("\n===================================\n");
					System.out.println(actionResponse.getRenderParameterMap());
					System.out.println("\n\n\n\n\n");
					
					actionResponse.setRenderParameter("mvcPath", 
							"/html/challengeadmin/edit_childchallenge.jsp");
				}
			}
		}
		
	public void deleteChildChallenge(ActionRequest actionRequest, ActionResponse actionResponse){
			long childChallengeId = ParamUtil.getLong(actionRequest, "childChallengeId");
			long challengeId = ParamUtil.getLong(actionRequest, "challengeId");
			
			try{
				ServiceContext serviceContext = ServiceContextFactory
						.getInstance(ChildChallenge.class.getName(), actionRequest);
				
				actionResponse.setRenderParameter("challengeId", Long.toString(challengeId));
				
				ChildChallengeServiceUtil.deleteChildChallenge(childChallengeId, serviceContext);
			} catch (Exception e) {
				SessionErrors.add(actionRequest, e.getClass().getName());
			}
		}

	public void addChallenge(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Challenge.class.getName(), actionRequest);
		
		Map<Locale, String> name = LocalizationUtil.getLocalizationMap(actionRequest, "inputName");
		Map<Locale, String> field = LocalizationUtil.getLocalizationMap(actionRequest, "inputField");
		Map<Locale, String> description = LocalizationUtil.getLocalizationMap(actionRequest, "inputDescirption");
		
		try{
			ChallengeServiceUtil.addChallenge(serviceContext.getUserId(), 
					name, field, description, serviceContext);
			SessionMessages.add(actionRequest, "Challenge Added.");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getMessage());
			
			actionResponse.setRenderParameter("mvcPath", 
					"/html/challengeadmin/edit_challenge.jsp");
		}
	}
	
	public void updateChallenge(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(Challenge.class.getName(), actionRequest);
		
		Map<Locale, String> name = LocalizationUtil.getLocalizationMap(actionRequest, "inputName");
		Map<Locale, String> field = LocalizationUtil.getLocalizationMap(actionRequest, "inputField");
		Map<Locale, String> description = LocalizationUtil.getLocalizationMap(actionRequest, "inputDescirption");
		long challengeId = ParamUtil.getLong(actionRequest, "challengeId");
		
		//System.out.println("\n\n\n\n\n\n\n");
		//System.out.println("test challenge update in admin view");
		//System.out.println(name.toString());
		//System.out.println(field.toString());
		//System.out.println(description.toString());
		//System.out.println(challengeId);
		//System.out.println("\n\n\n\n\n\n\n");
		
		try{
			Challenge challenge = ChallengeServiceUtil.updateChallenge(serviceContext.getUserId(), 
					challengeId, name, field, description, serviceContext);
			System.out.println(challenge.toString());
			SessionMessages.add(actionRequest, "Challenge updated successfully.");
		}catch(PortalException e){
			SessionMessages.add(actionRequest, e.getClass().getName());
		}
	}
	
	public void deleteChallenge(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws PortalException, SystemException{
		ServiceContext serviceContext = ServiceContextFactory
				.getInstance(Challenge.class.getName(), actionRequest);
		
		long challengeId = ParamUtil.getLong(actionRequest, "challengeId");
		
		try {
			ChallengeServiceUtil.deleteChallenge(challengeId, serviceContext);
			SessionMessages.add(actionRequest, "Challenge Deleted successfully.");
		}catch(PortalException e){
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		try{
			Challenge challenge = null;
			
			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(Challenge.class.getName(), renderRequest);
			
			String challengeFieldName = "";
			
			long groupId = serviceContext.getScopeGroupId();
			
			List<Challenge> challenges = ChallengeServiceUtil.getChallenges(groupId);
			
			if(challenges.size() == 0 ){
				//System.out.println("\n\n\n\n test render method : ");
				//Map<Locale, String> name = new HashMap<Locale, String>();
				
				//name.put(new Locale("en", "US"), "Main");
				//name.put(new Locale("ko","KR"), "메인");
				
				//challenge = ChallengeServiceUtil
				//		.addChallenge(serviceContext.getUserId(), 
				//				name, name, name, serviceContext);
				System.out.println("\n\ntest  challenge admin1");
				System.out.println(challenge.toString());
				renderRequest.setAttribute(WebKeys.CHALLENGE, challenge);
			}
			
			challenge = (Challenge) renderRequest.getAttribute(WebKeys.CHALLENGE);
			
			if(challenge == null){
				challengeFieldName = ParamUtil.getString(renderRequest, "challengeFieldName");
				
				if(challengeFieldName.equalsIgnoreCase("")){
					challenge = challenges.get(0);
				}else{
					OrderByComparatorFactory orderByComparatorFactory = 
							OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory
							.create("challenge", "field", true);
					
					challenge = ChallengeServiceUtil.getChallengeGroupAndField(
							serviceContext.getScopeGroupId(), 
							challengeFieldName, orderByComparator);
				}
			}
			renderRequest.setAttribute(WebKeys.CHALLENGE, challenge);
		} catch (Exception e){
			System.out.println("\n\n\n\ntest challenge admin 2.\n\n\n");
			//throw new PortletException(e);
		}
		
		super.render(renderRequest, renderResponse);
	}

}
