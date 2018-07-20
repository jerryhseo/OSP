package org.kisti.edison.iitp.util.icebreaker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.HttpFileUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.SystemException;

public class IBFiltUtil {
	private static final String IB_FILE_CLUSTER = "EDISON-CFD";
	private static final String FILE_MOVE_PATH = "/EDISON/LDAP/DATA/";
	
	public static String ibFileUpload(String icebreakerUrl,String vcToken, File file,String userScreenName) throws SystemException{
		HttpURLConnection conn = null;
		String path = "";
		try{
			URL url = new URL(icebreakerUrl+"/api/file/upload?cluster="+IB_FILE_CLUSTER);
			
			HttpFileUtil httpFileUtil = new HttpFileUtil(url);
			httpFileUtil.Token = vcToken;
			httpFileUtil.addFile("file", file);
			
			String resultJson = httpFileUtil.sendMultipartPost();
			
			if(!"".equals(CustomUtil.strNull(resultJson))){
				JsonObject json = new JsonParser().parse(resultJson).getAsJsonObject();
				String fileId = json.get("id").toString();
				String fileName = json.get("name").toString();
				
				createFolder(icebreakerUrl, vcToken, "RF-DESIGNER");
				moveFile(icebreakerUrl, vcToken, userScreenName, fileId, "RF-DESIGNER/"+file.getName());
				path = "/RF-DESIGNER/"+file.getName();
				
			}
			return path;
		}catch (Exception e) {
			throw new SystemException(e);
		}
	}
	
	
	private static void createFolder(String icebreakerUrl, String vcToken,String newFolderName) throws IOException, SystemException{
		HttpURLConnection conn = null;
		OutputStream os = null;
		try{
			URL url = new URL(icebreakerUrl+"/api/folder/create?name="+newFolderName+"&cluster="+IB_FILE_CLUSTER);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + vcToken);
			
			StringBuffer bodyStr = new StringBuffer();
			bodyStr.append("<folder id='HOME'>");
			bodyStr.append("<name>"+newFolderName+"</name>");
			bodyStr.append("<size>80</size>");
			bodyStr.append("<type>text/plain</type>");
			bodyStr.append("</folder>");
			
			os = conn.getOutputStream();
			os.write(bodyStr.toString().getBytes());
			os.flush();
			
		}catch (Exception e) {
			throw new SystemException(e);
		}finally{
			if(conn!=null){conn.disconnect();}
			if(os!=null){os.close();}
		}
	}
	
	private static void moveFile(String icebreakerUrl, String vcToken,String userScreenName, String fileId, String path) throws IOException, SystemException{
		HttpURLConnection conn = null;
		OutputStream os = null;
		
		try{
			URL url = new URL(icebreakerUrl+"/api/file/move");
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + vcToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            
            StringBuffer bodyStr = new StringBuffer();
            String finallyPath = FILE_MOVE_PATH + userScreenName + "/repository/"+path;
            bodyStr.append("{");
            bodyStr.append("   \"fileId\" : \"" + fileId + "\", ");
            bodyStr.append("   \"destPath\" : \""+finallyPath+"\" ");
            bodyStr.append("}");
            
            os = conn.getOutputStream();
            os.write(bodyStr.toString().getBytes());
            os.flush();
		}catch (Exception e) {
			throw new SystemException(e);
		}finally{
			if(conn!=null){conn.disconnect();}
			if(os!=null){os.close();}
		}
	}
}
