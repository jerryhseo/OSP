package edison.challenge.portlet.team;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.RequestUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.ServletRequestUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.service.ChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalService;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;


/**
 * Portlet implementation class TeamInformation
 */
public class TeamInformation extends MVCPortlet {
	private final static String paperFileName = "papersubmission";
	private final static String presenatationFileName = "presentationsubmission";
	private final static String paperPDFFileName = "papersubmissionPDF";
	
	
	public TeamInformation(){
		//basefilePath = "D:"+basefilePath;
	}
	
	public void createTeam(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception{
		System.out.println("Create Team information");
		Map params = RequestUtil.getParameterMap(actionRequest);
		
		long userID = ParamUtil.getLong(actionRequest, "userInfo");
		long childID = ParamUtil.getLong(actionRequest, "childChallengeID");
		//User applyUser = UserServiceUtil.getUserById(userID);
		//LocalizationUtil.getLocalizationMap();
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		
		ChallengeTeam teamInformation = ChallengeTeamLocalServiceUtil.createChallengeTeam(CounterLocalServiceUtil.increment());
		ChallengeTeamLocalServiceUtil.updateChallengeTeam(teamInformation);
		Date today = new Date();
		//Set Team Name
		teamInformation.setTeamNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputTeamName"));
		teamInformation.setSubjectMap(ChallengeLocalServiceUtil.getChallenge(ChildChallengeLocalServiceUtil.getChildChallenge(childID).getChallengeid()).getNameMap());
		teamInformation.setPaperstatus(ChallengeTeamLocalServiceUtil.fileUpload(childID, paperFileName, actionRequest, teamInformation.getPrimaryKey()));
		teamInformation.setPresentationstatus(ChallengeTeamLocalServiceUtil.fileUpload(childID, presenatationFileName, actionRequest, teamInformation.getPrimaryKey()));
		teamInformation.setPaperPDFstatus(ChallengeTeamLocalServiceUtil.fileUpload(childID, paperPDFFileName, actionRequest, teamInformation.getPrimaryKey()));
		teamInformation.setRegisterDay(today);
		teamInformation.setRegisterid(ParamUtil.getString(actionRequest, "userInfo"));
		teamInformation.setPaperNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperName"));
		teamInformation.setPaperAbstractMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperAbstract"));
		
		if(teamInformation.getPaperstatus()){
			teamInformation.setPaperSubmissionDay(today);
			teamInformation.setPaperModifyDay(today);
			teamInformation.setPaperFileName(uploadRequest.getFileName("papersubmission"));
		}
		if(teamInformation.getPresentationstatus()){
			teamInformation.setPresentationSubmissionDay(today);
			teamInformation.setPresentationModifyDay(today);
			teamInformation.setPresentationFileName(uploadRequest.getFileName("presentationsubmission"));
		}
		if(teamInformation.getPaperPDFstatus()){
			teamInformation.setPaperPDFModifyDay(today);
			teamInformation.setPaperPDFSubmissionDay(today);
			teamInformation.setPaperPDFFileName(uploadRequest.getFileName("papersubmissionPDF"));
		}
	
		teamInformation.setFilepath(ChallengeTeamLocalServiceUtil.getCreateFilePath());
		teamInformation.setChildid(childID);
		
		//team member create
		//teamInformation.setPaperAbstractMap(abstractpaperMap);
		ChallengeTeamLocalServiceUtil.updateChallengeTeam(teamInformation);
		createChallengeTeamMemberByTeam(actionRequest, actionResponse, true, teamInformation.getPrimaryKey());
	}
	
	
	//Create Member
	public void createChallengeTeamMemberByTeam(ActionRequest actionRequest, ActionResponse actionResponse, boolean isLeader, long chteamID) throws SystemException, PortalException{
		ChallengeMemberLocalServiceUtil.addChallengeTeamMember(actionRequest, actionResponse, isLeader, chteamID);
	}
}
