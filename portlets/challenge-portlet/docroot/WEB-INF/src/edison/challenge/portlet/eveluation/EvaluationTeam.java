package edison.challenge.portlet.eveluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.IOUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import edison.challenge.service.builder.model.ChallengeTeam;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;

/**
 * Portlet implementation class EvaluationTeam
 */
public class EvaluationTeam extends MVCPortlet {
	private final static int ONE_GB = 1073741824;
	private final static String basefilePath = File.separator+"EDISON/PORTAL/dlstore/CHALLENGE/";
	private final static String paperFileName = "papersubmission";
	private final static String presenatationFileName = "presentationsubmission";
	private final static String paperFileNamePDF = "papersubmissionPDF";
	
	private static final long managercompanyID = 20154;
	//private String paperFileName;
	private String createPath;
	
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		// TODO Auto-generated method stub
		
		String type = ParamUtil.getString(resourceRequest, "type");
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
		
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
	public void teamFileDownLoad(long chTeamID, String inpuName, String fileName, ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, IOException{
		ChallengeTeam myTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(chTeamID);
		String filePath = myTeam.getFilepath()+inpuName+"/"+fileName;
		
		File downFile = new File(filePath);
		
		System.out.println("test file download 3 : "+downFile.getName()+"\n\n\n\n\n\n\n");
		System.out.println("test file download 3 : "+downFile.getAbsolutePath()+"\n\n\n\n\n\n\n");
		//OutputStream in;
		
		try {
			response.setContentType(Files.probeContentType(downFile.toPath()));
			OutputStream out = response.getPortletOutputStream();
			InputStream in = new FileInputStream(downFile);
			
			IOUtils.copy(in, out);
			out.flush();
			
			//in = new FileInputStream(downFile);
			//in = response.getPortletOutputStream();
			//HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(response);
			//HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		
			//ServletResponseUtil.sendFile(httpReq, httpRes,  downFile.getName(), Files.readAllBytes(downFile.toPath()), Files.probeContentType(downFile.toPath()));
			//in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return;
		}
		
	}
	
	
	

}
