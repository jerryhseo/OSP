package com.kisti.osp.workbench.agent.ib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.TokenProviderUtil;
import org.kisti.edison.util.VCRegisterUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;

public class IBAgent {
	public static String URL = "";
	public static String ZONE = "";
	private String vcToken = "";
	private Group group = null;
	private User user = null;
	
	
	public IBAgent(Group group, User user) {
		super();
		this.group = group;
		this.user = user;

		if( URL.isEmpty() )
			URL = GetterUtil.getString(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL),"");
		if( ZONE.isEmpty() )
			ZONE = GetterUtil.getString(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ZONE));
		
		try {
			this.vcToken = this.getVCToken();
		} catch (SystemException | PortalException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HttpURLConnection connect( String apiUrl, String method, String accept, String type ) throws IOException{
		URL url = new URL(apiUrl);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String contentType = type;
		connection.setDoOutput(true);
		connection.setRequestMethod(method);
		connection.setRequestProperty("Accept", accept);
		connection.setRequestProperty("Content-Type", contentType+";charset=utf-8");

		connection.setRequestProperty("Authorization", "Basic "+this.vcToken);
		return connection;
	}
	
	private String getVCToken() throws SystemException, MalformedURLException, PortalException, IOException, ParseException{
		IcebreakerVcToken icebreakerVcToken = new IcebreakerVcToken();

		long groupId = group.getGroupId();
		String ibUserId = "";
		String ibUserPassword = "";
		String universityField = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
		String virtualLabId = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_LAB_ID);
		String classId = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_CLASS_ID);
		String majorField = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_MAJOR);
		
		if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
			ibUserId = (String)group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
			ibUserPassword = (String)group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_PWD);
		}else{
			ibUserId = String.valueOf(user.getScreenName());
			ibUserPassword = user.getPassword();
		}
		
		if(VCRegisterUtil.isVcInfo(groupId, ibUserId) == 200){
			
			if(user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))){
			
				icebreakerVcToken.setVcToken(CustomUtil.strNull(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))));				
				icebreakerVcToken.setVcTokenExpired(CustomUtil.strNull(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId)), "0"));
	
				if(Integer.parseInt(icebreakerVcToken.getVcTokenExpired()) <= Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))){
					//시간이 지난 토큰인 경우 새로운 토큰 발행 및 커스텀 필드 저장				
					icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, ibUserId, ibUserPassword);
		
					//Icebreaker에 회원정보는 있으나 로그인 되지 않는 경우 비밀번호 변경으로 인한것으로 판단하여 비밀번호 update후에 다시 로그인하여 토큰을 요청 합니다. 
					if(icebreakerVcToken.getResultCode() != 200){
						int updateResult = VCRegisterUtil.VCUpdate(groupId, ibUserId, ibUserPassword, user.getEmailAddress());
						if(updateResult==200){
							icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, ibUserId, ibUserPassword);
						}
					}
					
					if(icebreakerVcToken.getResultCode() == 200){
						icebreakerVcToken.setVcToken(icebreakerVcToken.getVcToken());					
						icebreakerVcToken.setVcTokenExpired(icebreakerVcToken.getVcTokenExpired());
						
						user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId), icebreakerVcToken.getVcToken());
						user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId), icebreakerVcToken.getVcTokenExpired());
					}else{
						System.out.println("SimulationLocalServiceImpl : Icebreaker getOrCreateToken Error !!");
					}
				}
			}else{
				//icebreaker 계정은 있으나 포털에 expando가 없는 경우 expando 추가 생성
				icebreakerVcToken = createExpandoUserVctoken(ibUserId, ibUserPassword);
			}
		}else{
			int resultRegist = VCRegisterUtil.VCRegist(groupId, ibUserId, ibUserPassword, user.getEmailAddress(), user.getFirstName(), universityField, virtualLabId, classId, majorField);
			if (resultRegist == 201) {
				//icebreaker 가입이 성공한 경우 신규 토큰 발행 및 커스텀 필드 추가
				icebreakerVcToken = createExpandoUserVctoken(ibUserId, ibUserPassword);
			}
		}
	
		return icebreakerVcToken.getVcToken();
	}
	
	private IcebreakerVcToken createExpandoUserVctoken(String userScreenName, String userPassword) throws SystemException, MalformedURLException, PortalException, IOException, ParseException {
		System.out.println("User Screen Name: "+userScreenName);
		System.out.println("User Password: "+userPassword);
		long thisGroupId = group.getGroupId();

		IcebreakerVcToken icebreakerVcToken = new IcebreakerVcToken();
		
		//icebreaker 가입이 성공한 경우 신규 토큰 발행 및 커스텀 필드 추가
		icebreakerVcToken = TokenProviderUtil.getVcToken(thisGroupId, userScreenName, userPassword);										
		icebreakerVcToken.setVcToken(icebreakerVcToken.getVcToken());					
		icebreakerVcToken.setVcTokenExpired(icebreakerVcToken.getVcTokenExpired());
		
		if(!user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId))){		

			user.getExpandoBridge().addAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId), false);
			user.getExpandoBridge().addAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId), false);
			
			ExpandoColumn toKenColumn = ExpandoColumnLocalServiceUtil.getColumn(
																			user.getExpandoBridge().getCompanyId(), 
																			user.getExpandoBridge().getClassName(), 
																			ExpandoTableConstants.DEFAULT_TABLE_NAME, 
																			EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId)
																			);
			setExpandoPermissions(user.getExpandoBridge().getCompanyId(), toKenColumn); 
		
			ExpandoColumn expiredColumn = ExpandoColumnLocalServiceUtil.getColumn(
																			user.getExpandoBridge().getCompanyId(), 
																			user.getExpandoBridge().getClassName(), 
																			ExpandoTableConstants.DEFAULT_TABLE_NAME, 
																			EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId)
																			);
			setExpandoPermissions(user.getExpandoBridge().getCompanyId(), expiredColumn);
		}
		
		user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId), icebreakerVcToken.getVcToken());
		user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId), icebreakerVcToken.getVcTokenExpired());
		return icebreakerVcToken;
	}

	private void setExpandoPermissions(long companyId, ExpandoColumn column) throws SystemException {
		try {
	 
			Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
			Role adminRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ADMINISTRATOR);
	 
	          
			if (userRole != null && adminRole != null) {
				// define actions 
				String[] actionIds = new String[] { ActionKeys.VIEW, ActionKeys.UPDATE };
				
				Map<Long, String[]> map = new HashMap<>();
				map.put(userRole.getRoleId(), actionIds);
				map.put(adminRole.getRoleId(), actionIds);
	                
				// set the permission
				ResourcePermissionLocalServiceUtil.setResourcePermissions(
	                															companyId, 
	                															ExpandoColumn.class.getName(), 
	                															ResourceConstants.SCOPE_INDIVIDUAL, 
	                															String.valueOf(column.getColumnId()), 
	                															map
	                															);
	                	                
	            }
	      } catch (PortalException pe) {
	      }
	}
	
	public String getSimulationUuid() throws IOException, SystemException{
//		String apiUrl = URL+_simulationAPI+"/create?zone="+ZONE;
		String apiUrl = URL+_simulationAPI+"/create";
		
		HttpURLConnection connection = connect( apiUrl, "POST", "application/json", "application/json" );
		JSONObject jsonResult = null;
		
		JSONObject payload = JSONFactoryUtil.createJSONObject();
		payload.put("title", "simulation");
		payload.put("description", "uuid");
		payload.put("solverId", "0");
		
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(payload.toString().getBytes());
		outputStream.flush();
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String line = "";
		StringBuffer responseBuffer = new StringBuffer();
		
		if( connection.getResponseCode() == 201){
			while( (line = reader.readLine()) !=null ){
				responseBuffer.append(line);
			}
			reader.close();
			
			System.out.println("Create Simulation response: "+responseBuffer.toString());
			try {
				jsonResult = JSONFactoryUtil.createJSONObject(responseBuffer.toString());
			} catch (JSONException e) {
				System.out.println("WRONG JSON from IB response: "+responseBuffer.toString());
			}
			
		}
		else if (connection.getResponseCode() == 400) {
			connection.disconnect();
			throw new SystemException("Failed IcebreakerService [ createSimulation ] : BAD REQUEST : wrong body content - HTTP error code : " + connection.getResponseCode());
		}
		else{			
			connection.disconnect();
			throw new SystemException("Failed IcebreakerService [ createSimulation ] : ETC : etc error - HTTP error code : " + connection.getResponseCode());
		}
		
		connection.disconnect();
		
		return jsonResult.getString("uuid");
	}
	
	public String uploadFile( String fileName, String content, String cluster ) throws IOException, JSONException, SystemException{
		String fileId = "";
		String apiUrl = URL+"/api/file/write";
		//String apiUrl = URL+"/api/file/upload";
		apiUrl = HttpUtil.addParameter(apiUrl, "cluster", cluster);
		apiUrl = HttpUtil.addParameter(apiUrl, "name", fileName);
		System.out.println("Upload URL: "+apiUrl);
		
		HttpURLConnection connection = this.connect( apiUrl, "POST", "application/json", "plain/text" );
		
		OutputStream outStream = connection.getOutputStream();
		System.out.println("IB.Uploading **************************************");
		System.out.println("Content Size: "+content.length());
		System.out.println("************************************** IB.Uploading");
		
		outStream.write(content.getBytes(), 0, content.length());
		outStream.flush();
		outStream.close();
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String line = "";
		StringBuffer responseBuffer = new StringBuffer();
		
		if( connection.getResponseCode() == 201){
			while( (line = reader.readLine()) !=null ){
				responseBuffer.append(line);
			}
			reader.close();
			
			JSONObject response = JSONFactoryUtil.createJSONObject(responseBuffer.toString() );
			System.out.println("File Upload response: "+responseBuffer.toString());
			fileId = response.getString("id");
		}
		else if (connection.getResponseCode() == 400) {
			connection.disconnect();
			throw new SystemException("Failed IcebreakerService [ encode file id ] : BAD REQUEST : wrong body content - HTTP error code : " + connection.getResponseCode());
		}
		
		connection.disconnect();
		return fileId;
	}
	
	public String getFileId( String path, boolean isJobResult ) throws IOException, SystemException, JSONException{
		String fileId = "";
		
		String userScreenName;
		if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR)){
			userScreenName = (String)group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
		}else{
			userScreenName = String.valueOf(user.getScreenName());
		}
		
		Path filePath = Paths.get(userScreenName);
		if( isJobResult ){
			filePath = filePath.resolve("jobs").resolve(path);
		}
		else{
			filePath = filePath.resolve("repository").resolve(path);
		}
			
		
		String apiUrl = URL+"/api/file/encode?path="+filePath.toString();
		
		HttpURLConnection connection = connect( apiUrl, "POST", "application/json", "application/json" );
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
		String line = "";
		StringBuffer responseBuffer = new StringBuffer();
		
		if( connection.getResponseCode() == 201){
			while( (line = reader.readLine()) !=null ){
				responseBuffer.append(line);
			}
			reader.close();
			
			JSONObject response = JSONFactoryUtil.createJSONObject(responseBuffer.toString() );
			fileId = response.getString("id");
			System.out.println("File ID response: "+responseBuffer.toString());
		}
		else if (connection.getResponseCode() == 400) {
			connection.disconnect();
			throw new SystemException("Failed IcebreakerService [ encode file id ] : BAD REQUEST : wrong body content - HTTP error code : " + connection.getResponseCode());
		}
		
		connection.disconnect();
		return fileId;
	}
	
	public JSONObject submit(
			String simulationUuid,
			String runType, 
			String category, 
			String title, 
			String description, 
			String scienceAppId, 
			String solverName,
			String cyberLabId,
			String classId,
			String executable,
			String[] dependencies,
			Map<String, String> files,
			String cluster,
			Map<String, String> mpiAttributes,
			String callbackUrl ) throws IOException{
		String apiUrl = URL+_simulationAPI+"/"+simulationUuid+"/job/submit";
		System.out.println("IB URL: "+apiUrl);
		apiUrl = HttpUtil.addParameter(apiUrl, "url", callbackUrl);
		
		/*
		Map<String, String> commandOptions = new HashMap<>();
		Set<String> optionNames = files.keySet();
		for(String optionName : optionNames ){
			String fileId = "";
			try {
				fileId = this.getFileId(files.get(optionName));
			} catch (JSONException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			commandOptions.put(optionName, fileId);
		}
		*/
		
		IBJob job = new IBJob(
				runType, 
				title, 
				executable, 
				dependencies, 
				cluster, 
				files, 
				category, 
				mpiAttributes, 
				simulationUuid, 
				description, 
				scienceAppId, 
				solverName, 
				cyberLabId, 
				classId
		);
		
		HttpURLConnection connection = connect(apiUrl, "POST", "application/xml", "application/xml");
		
		String payload = job.toXML();
		System.out.println("Payload: "+payload);
		
		JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
		
		OutputStream outStream = connection.getOutputStream();
		outStream.write(payload.getBytes());
		outStream.flush();
		outStream.close();
		
		if (connection.getResponseCode() == 201) {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream() ));
			String line;
			StringBuffer responseBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseBuffer.append(line);
			}
			reader.close();
			
		    DocumentBuilderFactory factory  =  DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    
		    Document document = null;
			try {
				document = builder.parse(new InputSource(new StringReader(responseBuffer.toString())));
			} catch (SAXException e) {
				e.printStackTrace();
			}

			String uuid = document.getElementsByTagName("uuid").item(0).getChildNodes().item(0).getNodeValue();
				    
			String status = document.getElementsByTagName("status").item(0).getChildNodes().item(0).getNodeValue();
			status = CustomUtil.getStatusConvertIceToPortal(status);  
				    
			String submittedTime = document.getElementsByTagName("submittedTime").item(0).getChildNodes().item(0).getNodeValue();
			submittedTime = submittedTime.substring(0, 19);  //2012-07-12T20:50:37					    
			submittedTime = submittedTime.replace("T", " "); //2012-07-12 20:50:37
			
			jsonResult.put("uuid", uuid);
			jsonResult.put("status", status);
			
			SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			Date startDate = null;
			try {
				startDate = fomatter.parse(submittedTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonResult.put("submitTime", startDate.getTime());
		}else if (connection.getResponseCode() == 400) {
			_log.error("Failed IcebreakerService [ executeJob ] : BAD REQUEST : wrong body content - HTTP error code : " + connection.getResponseCode());
			jsonResult.put("error", connection.getResponseCode());
		}else{			
			_log.error("Failed IcebreakerService [ executeJob ] : ETC : etc error - HTTP error code : " + connection.getResponseCode());
			jsonResult.put("error", connection.getResponseCode());
		}
		
		connection.disconnect();
		
		return jsonResult;
	}
	
	private static Log _log = LogFactoryUtil.getLog(IBAgent.class);
	private static final String _simulationAPI = "/api/simulation";
}
