package kisti.edison.challenge.portlet;


import java.io.IOException;

import java.io.OutputStream;

import java.util.List;
import java.util.Locale;

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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;

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
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.util.WebKeys;

/**
 * Portlet implementation class ChallengeResult
 */
public class ChallengeResult extends MVCPortlet {
 
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
		long childChallengeId = ParamUtil.getLong(resourceRequest, "childChallengeId");
		
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//PageContext pageContext = resourceRequest.getAttribute(WebKeys.PAGE_)
		
		long groupId = themeDisplay.getScopeGroupId();
		System.out.println("Test serve resource url");
		System.out.println("TYPE : "+type);
		
		
		try {
			if(type.equals("paperFile")){
				ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"papersubmission", challengeTeam.getPaperFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("paperFilePDF")){
				ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"papersubmissionPDF", challengeTeam.getPaperPDFFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("presentationFile")){
				ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
				ChallengeTeamLocalServiceUtil.fileDownload(challengeTeamId, 
						"presentationsubmission", challengeTeam.getPresentationFileName(), resourceRequest, resourceResponse);
			}else if(type.equals("getResult")){
				if(childChallengeId>0){
					long challengeId;
					try {
						challengeId = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId).getChallengeId();
						
						String challengeFieldName = ChallengeLocalServiceUtil.getChallenge(challengeId).getField(new Locale("en", "US"));
						System.out.println("FieldName : "+challengeFieldName);
						String fileName = challengeFieldName+"_Result.xls";
						
						
						List<ChallengeTeam> challengeTeamList =
						ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(groupId, childChallengeId);
						
						
						Workbook workBook = new HSSFWorkbook();
						Sheet sheet = workBook.createSheet("new sheet");
						
						//Create top row for names
						Row row = sheet.createRow(0);
						
						row.createCell(0).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-team-name"));
						row.createCell(1).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-user-papername"));
						row.createCell(2).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-scienceapp"));
						row.createCell(3).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-scienceappcount"));
						row.createCell(4).setCellValue(LanguageUtil.get(getPortletConfig(), themeDisplay.getLocale(), "challenge-cputime"));
						row.createCell(5).setCellValue("Total Score");
						
						//set row for each challengeTeam
						for(int i=0; i<challengeTeamList.size(); i++){
							Row teamRow = sheet.createRow((i+1));
							ChallengeTeam team = challengeTeamList.get(i);
							teamRow.createCell(0).setCellValue(team.getTeamName());
							teamRow.createCell(1).setCellValue(team.getPaperName(themeDisplay.getLocale()));
							teamRow.createCell(2).setCellValue(ChallengeTeamLocalServiceUtil.getTeamAppListString(themeDisplay.getCompanyId(), team.getChallengeTeamId()));
							teamRow.createCell(3).setCellValue(ChallengeTeamLocalServiceUtil.getTeamSimulationNumber(themeDisplay.getCompanyId(), team.getChallengeTeamId()));
							teamRow.createCell(4).setCellValue(ChallengeTeamLocalServiceUtil.getCPUUseage(themeDisplay.getCompanyId(), team.getChallengeTeamId()));
							teamRow.createCell(5).setCellValue(ChallengeEvaluationScoreLocalServiceUtil.getChallengeTeamTotalScore(groupId, team.getChallengeTeamId()));
						}
						System.out.println("test export excel");
						
						
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
		}catch (PortalException e) {
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
