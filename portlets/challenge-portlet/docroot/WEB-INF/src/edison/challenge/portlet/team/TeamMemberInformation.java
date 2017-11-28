package edison.challenge.portlet.team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.RequestUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import edison.challenge.service.builder.model.ChallengeMember;
import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;


import net.sf.json.JSONObject;
/**
 * Portlet implementation class TeamMemberInformation
 */
public class TeamMemberInformation extends MVCPortlet {
	private final static int ONE_GB = 1073741824;
	private final static String basefilePath = File.separator+"EDISON/PORTAL/dlstore/CHALLENGE/";
	private final static String paperFileName = "papersubmission";
	private final static String presenatationFileName = "presentationsubmission";
	private final static String paperFileNamePDF = "papersubmissionPDF";
	
	private static final long managercompanyID = 20154;
	//private String paperFileName;
	private String createPath;
	
	public void updateTeam(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		Map params = RequestUtil.getParameterMap(actionRequest);
		long chTeamID = ParamUtil.getLong(actionRequest, "chTeamID");
		ChallengeTeam updateTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
		updateTeam.setTeamNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputTeamName"));
		updateTeam.setPaperNameMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputPaperName"));
		long childID = updateTeam.getChildid();
		
		
		
		updateTeam.setPaperAbstractMap(LocalizationUtil.getLocalizationMap(actionRequest, "inputpaperAbstract"));
		
		//upload file in team update menu
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		Date today = new Date();
		if(ChallengeTeamLocalServiceUtil.fileUpload(childID, paperFileName, actionRequest, chTeamID)){
			updateTeam.setPaperstatus(true);
			updateTeam.setPaperModifyDay(today);
			updateTeam.setPaperFileName(uploadRequest.getFileName(paperFileName));
		}
		if(ChallengeTeamLocalServiceUtil.fileUpload(childID, presenatationFileName, actionRequest, chTeamID)){
			updateTeam.setPresentationstatus(true);
			updateTeam.setPresentationModifyDay(today);
			updateTeam.setPresentationFileName(uploadRequest.getFileName(presenatationFileName));
		}
		if(ChallengeTeamLocalServiceUtil.fileUpload(childID, paperFileNamePDF, actionRequest, chTeamID)){
			updateTeam.setPaperPDFstatus(true);
			updateTeam.setPaperPDFModifyDay(today);
			updateTeam.setPaperPDFSubmissionDay(today);
			updateTeam.setPaperPDFFileName(uploadRequest.getFileName(paperFileNamePDF));
		}
		
		ChallengeTeamLocalServiceUtil.updateChallengeTeam(updateTeam);
		
		
		ChallengeMember leaderMember = ChallengeMemberLocalServiceUtil.getChallengeMember(ChallengeMemberLocalServiceUtil.getTeamLeaderToMemberID(updateTeam.getPrimaryKey()));
		leaderMember.setGrade(ParamUtil.getString(actionRequest, "inputUserGrade"));
		ChallengeMemberLocalServiceUtil.updateChallengeMember(leaderMember);
		ChallengeTeamLocalServiceUtil.updateChallengeTeam(updateTeam);		
	}
	
	
	public void addChallengeMember(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException, PortalException{
		long teamID = ParamUtil.getLong(actionRequest, "teamID");
		
		ChallengeMemberLocalServiceUtil.addChallengeTeamMember(actionRequest, actionResponse, false, teamID);
		//createChallengeTeamMemberByTeamLeader(actionRequest, actionResponse, false, teamID);
	}
	
	public void deleteMember(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		ChallengeMemberLocalServiceUtil.deleteTeamMemberByService(actionRequest, actionResponse);
	}
	
	public void getUserInfo(ResourceRequest request, ResourceResponse response) throws Exception {
		Map params = RequestUtil.getParameterMap(request);
		long groupID = PortalUtil.getScopeGroupId(request);
		
		String userScreenName = "";
		userScreenName = CustomUtil.strNull(params.get("searchName"),"0");
		
		long groupId = PortalUtil.getScopeGroupId(request);
		long companyId = PortalUtil.getCompanyId(request);
		
		//User userTest = UserServiceUtil.getUserById(Long.parseLong(userScreenName));
		//System.out.println("\n\n\nuser get information test ... ");
		//System.out.println("user screen name : "+userTest.getScreenName());
		//System.out.println("user email : " + userTest.getEmailAddress());
		
		User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, userScreenName);
		
		PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
		PermissionThreadLocal.setPermissionChecker(checker);
		
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		Map<String, String> memberUserInfo = null;
		if (user == null) {
			obj.put("result", "none");
			System.out.println("test json 3");
		} else {
			if (EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)
				|| EdisonUserUtil.isSiteRole(user, groupID, RoleConstants.SITE_ADMINISTRATOR)
				|| EdisonUserUtil.isSiteRole(user, groupID, RoleConstants.SITE_OWNER)) {
				obj.put("result", "admin");
				System.out.println("test json 4");
			}else {
				
				
				String userUniversityCode = user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
				//long companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
				ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(companyId,ExpandoTable.class.getName(),EdisonExpando.TALBE_NAME);
				ExpandoValue universityValue = ExpandoValueLocalServiceUtil.getValue(companyId, table.getClassNameId(), EdisonExpando.TALBE_NAME, EdisonExpando.CDNM, Long.valueOf(userUniversityCode));
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
				
				memberUserInfo = new HashMap<String, String>();
				memberUserInfo.put("userUniversity", universityValue.getString(themeDisplay.getLocale()));
				memberUserInfo.put("userScreenName", user.getScreenName());
				memberUserInfo.put("userScreenNameView", user.getScreenName());
				String userFullName = user.getFullName();
				String strtemp = userFullName.substring(userFullName.length()-2, userFullName.length()-1);
				userFullName = userFullName.replace(strtemp, "*");
				
				memberUserInfo.put("userFullName", userFullName);
				
				
				obj.put("memberUserInfo", memberUserInfo);
				obj.put("result", "user");
				
				System.out.println("test json 5");
			}
		}
		try{
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		// TODO Auto-generated method stub
		String type = ParamUtil.getString(resourceRequest, "type");
		
		
		//teamFileDownLoad(long chTeamID, String inpuName, String fileName, ResourceRequest request, ResourceResponse response)
		if(type.equals("paperFile")){
			try {
				//
				long chTeamID = ParamUtil.getLong(resourceRequest, "chTeamID");
				ChallengeTeam chTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
				if(chTeam.isPaperstatus())
					this.teamFileDownLoad(chTeamID, paperFileName, chTeam.getPaperFileName(), resourceRequest, resourceResponse);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("presentationFile")){
			try {
				long chTeamID = ParamUtil.getLong(resourceRequest, "chTeamID");
				ChallengeTeam chTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);				
				if(chTeam.isPresentationstatus())
					this.teamFileDownLoad(chTeamID, presenatationFileName, chTeam.getPresentationFileName(), resourceRequest, resourceResponse);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("paperFilePDF")){
			try {
				long chTeamID = ParamUtil.getLong(resourceRequest, "chTeamID");
				ChallengeTeam chTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);				
				if(chTeam.isPaperPDFstatus())
					this.teamFileDownLoad(chTeamID, paperFileNamePDF, chTeam.getPaperPDFFileName(), resourceRequest, resourceResponse);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type.equals("getuser")){
			try {
				this.getUserInfo(resourceRequest, resourceResponse);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	public void teamFileDownLoad(long chTeamID, String inpuName, String fileName, ResourceRequest request, ResourceResponse response) throws PortalException, SystemException{
		ChallengeTeam myTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
		String filePath = myTeam.getFilepath()+inpuName+"/"+fileName;
		
		File downFile = new File(filePath);
		
		System.out.println("test file download 3 : "+downFile.getName()+"\n\n\n\n\n\n\n");
		InputStream in;
		try {
			in = new FileInputStream(downFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(response);
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		try {
			ServletResponseUtil.sendFile(httpReq, httpRes,  downFile.getName(), Files.readAllBytes(downFile.toPath()), Files.probeContentType(downFile.toPath()));
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}
	
	public void deleteTeam(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException{
		ChallengeTeamLocalServiceUtil.deleteTeamandMember(actionRequest, actionResponse);		
	}
}
