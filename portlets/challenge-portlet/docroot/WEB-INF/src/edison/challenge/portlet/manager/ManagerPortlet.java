package edison.challenge.portlet.manager;



import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import edison.challenge.service.builder.model.Agency;
import edison.challenge.service.builder.model.Award;
import edison.challenge.service.builder.model.Challenge;
import edison.challenge.service.builder.model.ChildChallenge;
import edison.challenge.service.builder.service.AgencyLocalServiceUtil;
import edison.challenge.service.builder.service.AwardLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.impl.ChallengeLocalServiceImpl;

/**
 * Portlet implementation class ManagerPortlet
 */
public class ManagerPortlet extends MVCPortlet {
	
private Map<Locale, String> reName;
private Map<Locale, String> reDescription;


	public void createChallenge(ActionRequest actionRequest, ActionResponse actionResponse){
		reName = LocalizationUtil.getLocalizationMap(actionRequest, "inputName");
		reDescription = LocalizationUtil.getLocalizationMap(actionRequest, "inputDescription");
		
		try {
			Challenge ch = ChallengeLocalServiceUtil.createChallenge(CounterLocalServiceUtil.increment());
			ch.setNameMap(reName);
			ch.setDescriptionMap(reDescription);
			ChallengeLocalServiceUtil.updateChallenge(ch);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reName = null;
		reDescription = null;
		
	}
	
	public void deleteChallenge(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long chid = ParamUtil.getLong(actionRequest, "challengeID");
		ChallengeLocalServiceUtil.deleteChallengeAllCoverage(chid);
		ChallengeLocalServiceUtil.deleteChallenge(chid);
	}
	
	public void updateChallenge(ActionRequest actionRequest, ActionResponse actionResponse){
		
		reName = LocalizationUtil.getLocalizationMap(actionRequest, "inputName");
		reDescription = LocalizationUtil.getLocalizationMap(actionRequest, "inputDescription");
		
		long chID = ParamUtil.getLong(actionRequest, "challengePK");
		
		try {
			Challenge ch;
			ch = ChallengeLocalServiceUtil.getChallenge(chID);
			ch.setNameMap(reName);
			ch.setDescriptionMap(reDescription);
			ChallengeLocalServiceUtil.updateChallenge(ch);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//actionResponse.setRenderParameter("mvcPath", "/html/managerChallenge/view.jsp");
		reName = null;
		reDescription = null;
	}
	
	public void createChildChallengeInformation(ActionRequest actionRequest, ActionResponse actionResponse){
		try {
			
			ChildChallenge cc = ChildChallengeLocalServiceUtil.createChildChallenge(CounterLocalServiceUtil.increment());
			cc.setNumber(ParamUtil.getInteger(actionRequest, "inputNum"));
			cc.setPresentationDay(new Date(ParamUtil.getString(actionRequest, "inputPresentationDay")));
			cc.setPaperStartDay(new Date(ParamUtil.getString(actionRequest, "inputPaperDay")));
			cc.setPaperEndDay(new Date(ParamUtil.getString(actionRequest, "inputPaperDayEnd")));
			cc.setEvaluationStartDay(new Date(ParamUtil.getString(actionRequest, "inputEvaluationDay")));
			cc.setEvaluationEndDay(new Date(ParamUtil.getString(actionRequest, "inputEvaluationDayEnd")));
			//Date endDate = cc.getChallengeEndDay();
			cc.setChallengeStartDay(new Date(ParamUtil.getString(actionRequest, "inputChallengeDay")));
			
			cc.setChallengeEndDay(new Date(ParamUtil.getString(actionRequest, "inputChallengeDayEnd")));
			cc.setStatus(ParamUtil.getString(actionRequest, "inputChallengeStatus"));
			cc.setChallengeid(ParamUtil.getLong(actionRequest, "challengeID"));
			ChildChallengeLocalServiceUtil.updateChildChallenge(cc);
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteChildChallenge(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long childChallengeID = ParamUtil.getLong(actionRequest, "ccID");
		ChildChallenge cch = ChildChallengeLocalServiceUtil.deleteChildChallenge(childChallengeID);
		ChildChallengeLocalServiceUtil.deleteChildChallengeAllCoverage(childChallengeID);
		
		//ChildChallengeLocalServiceUtil.deleteChildChallenge(cch);
	}
	
	public void updateChildChallenge(ActionRequest actionRequest, ActionResponse actionResponse){
		try {
			long childChallengeID = ParamUtil.getLong(actionRequest, "childChallengeID");
			long challengeID = ParamUtil.getLong(actionRequest, "challengeID");
			ChildChallenge cc = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeID);
			cc.setNumber(ParamUtil.getInteger(actionRequest, "inputNum"));
			cc.setPresentationDay(new Date(ParamUtil.getString(actionRequest, "inputPresentationDay")));
			cc.setPaperStartDay(new Date(ParamUtil.getString(actionRequest, "inputPaperDay")));
			cc.setPaperEndDay(new Date(ParamUtil.getString(actionRequest, "inputPaperDayEnd")));
			cc.setEvaluationStartDay(new Date(ParamUtil.getString(actionRequest, "inputEvaluationDay")));
			cc.setEvaluationEndDay(new Date(ParamUtil.getString(actionRequest, "inputEvaluationDayEnd")));
			cc.setChallengeStartDay(new Date(ParamUtil.getString(actionRequest, "inputChallengeDay")));
			cc.setChallengeEndDay(new Date(ParamUtil.getString(actionRequest, "inputChallengeDayEnd")));
			
			cc.setChallengeid(ParamUtil.getLong(actionRequest, "challengeID"));
			cc.setStatus(ParamUtil.getString(actionRequest, "inputChallengeStatus"));
			ChildChallengeLocalServiceUtil.updateChildChallenge(cc);
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAward(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		long awardID = ParamUtil.getLong(actionRequest, "awardID");
		Award award = AwardLocalServiceUtil.deleteAward(awardID);
		AwardLocalServiceUtil.deleteAward(award);
	}
	
	public void deleteAgency(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException, PortalException{
		long agencyID = ParamUtil.getLong(actionRequest, "agencyID");
		Agency agency = AgencyLocalServiceUtil.deleteAgency(agencyID);
		AgencyLocalServiceUtil.deleteAgency(agency);
	}
	
	public void createAward(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException{
		long childChallengeID = ParamUtil.getLong(actionRequest, "ccid");
		Award award = AwardLocalServiceUtil.createAward(CounterLocalServiceUtil.increment());
		
		
		award.setAwardGradeNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputGradeName"));
		award.setAwardNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputAwardName"));
		award.setPrizeMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputPrize"));
		award.setNumber(ParamUtil.getString(actionRequest, "inputAwardNumber"));
		award.setChildid(childChallengeID);
		
		AwardLocalServiceUtil.updateAward(award);
	}
	
	public void createAgency(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException{
		long childChallengeID = ParamUtil.getLong(actionRequest, "ccid");
		Agency agency = AgencyLocalServiceUtil.createAgency(CounterLocalServiceUtil.increment());
		
		agency.setChildid(childChallengeID);
		agency.setNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputName"));
		agency.setLevel(ParamUtil.getString(actionRequest, "inputAgencyLevel"));
		agency.setUrl(ParamUtil.getString(actionRequest, "inputURL"));
		
		System.out.println("--------------Agency Test--------------");
		System.out.println("Child Challenge ID : " + ParamUtil.getLong(actionRequest, "ccid"));
		System.out.println("Name : " + LocalizationUtil.getLocalizationMap(actionRequest, "inputName").get("en_US"));
		System.out.println("Agency Level : "+ParamUtil.getString(actionRequest,  "inputAgencyLevel"));
		System.out.println("URL : " + ParamUtil.getString(actionRequest,  "inputURL"));
		System.out.println("--------------Agency Test--------------\n\n");
		
		
		//Liferay.Util.getOpener().<portlet:namespace/>closePopupwithAgency('<portlet:namespace/>agencyDialog');
		AgencyLocalServiceUtil.updateAgency(agency);
	}
	
}
