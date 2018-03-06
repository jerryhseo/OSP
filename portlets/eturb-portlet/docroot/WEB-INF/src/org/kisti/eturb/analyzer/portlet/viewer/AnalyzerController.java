package org.kisti.eturb.analyzer.portlet.viewer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils; 
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.RequestUtil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class AnalyzerController {
	private static Log log = LogFactory.getLog(AnalyzerController.class);
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws IOException, PortalException, SystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Group group = themeDisplay.getScopeGroup();
		String icebreakerUrl = CustomUtil.strNull(group.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
		
		model.addAttribute("icebreakerUrl", icebreakerUrl);
		
		long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "eturbanalyzer_WAR_eturbportlet");
		model.addAttribute("eturbanalyzerPlid", plid);
		
		return "viewer";
	}
	
	/**
	 * search file and send to analyzer
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@ResourceMapping(value ="handleIceBraker")
	public void handleIceBraker(ResourceRequest request, ResourceResponse response) throws IOException{
		OutputStream outs = null;
		PrintWriter uploadOut = null;
		
		try {
			System.out.println("[handleIceBraker] start handleIceBraker..");
			//this.printDetailValues("parameter", request, request.getParameterNames());
			//this.printDetailValues("attributes", request, request.getAttributeNames());
			
			Map param = RequestUtil.getParameterMap(request);
			
			//System.out.println("test reqType : " + ParamUtil.getString(request, "reqType"));
			
			byte[] fileContent = null;
			String icebreakerUrl = null;
			String icebreakerToken = null;
			String fileId = CustomUtil.strNull(param.get("fileId"));
			String reqType = CustomUtil.strNull(param.get("reqType"));
			
			if ("local".equals(reqType)) {
				fileContent = this.execLocalFileDownload(fileId);
			} else if ("edison".equals(reqType)) {
				icebreakerUrl = CustomUtil.strNull(param.get("icebreakerUrl"));
				icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
	
				/*
				System.out.println("[start] >>>>>>>>>>>>>>>>>>>> read from IB");
				fileContent = this.callFileAppiProxy(fileId);
				if (fileContent != null) System.out.println("file Length : " + fileContent.length);
				System.out.println("[end] >>>>>>>>>>>>>>>>>>>> read from IB");
				*/
				fileContent = this.execIBFileDownload(fileId, icebreakerUrl, icebreakerToken);
			} else if (reqType.equals("UPLOAD_IB")) {	
					// get parameter from local
					String pFileName = CustomUtil.strNull(param.get("fname"));
					if (pFileName == null || pFileName.length() <= 0 || pFileName.indexOf(".") <= -1) {
						throw new Exception("[ReceiverPortletAction] file name is empty!!");
					}
					pFileName = pFileName.substring(pFileName.lastIndexOf("=") + 1, pFileName.length());
					
					String fileExt = pFileName.substring(pFileName.lastIndexOf(".") + 1, pFileName.length());
					String sourceFileName = pFileName.substring(0, pFileName.lastIndexOf("."));
					String savedFileName = sourceFileName + "_" + getCurrentTime() + "." + fileExt;
							
					String receiveData = CustomUtil.strNull(param.get("data"));
					if (receiveData == null || receiveData.length() <= 0) {
						throw new Exception("[ReceiverPortletAction] image data is empty!!");
					}
					
					String vcToken = CustomUtil.strNull(param.get("vcToken"));
					if (vcToken == null || vcToken == "") {
						throw new Exception("[ReceiverPortletAction] vcTokenis empty!!");
					}
					
					String ibUrl = CustomUtil.strNull(param.get("ibUrl"));
					if (ibUrl == null || ibUrl == "") {
						throw new Exception("[ReceiverPortletAction] ibUrl empty!!");
					}
					ibUrl += savedFileName;
					
					System.out.println("vcToken : " + vcToken);
					System.out.println("ibUrl : " + ibUrl);
					System.out.println("savedFileName : " + savedFileName);
					System.out.println("receiveData length : " + receiveData.length());
					
					receiveData = receiveData.substring(receiveData.indexOf(",") + 1, receiveData.length());
					byte[] dataArr = Base64.decodeBase64(receiveData);
					
					// send request to IB File Api
					CloseableHttpClient httpclient = null;
			        try {
			        	httpclient = HttpClients.createDefault();
			        	HttpPost httppost = new HttpPost(ibUrl);
			            httppost.setHeader("Accept", "application/xml");
			            httppost.setHeader("Authorization", "Basic " + vcToken);

			            ContentBody cd = new InputStreamBody(new ByteArrayInputStream(dataArr), savedFileName);
			            HttpEntity reqEntity = (HttpEntity) MultipartEntityBuilder.create()
			                        						.addPart("file", cd)
			                        						.build();
			            
			            httppost.setEntity(reqEntity); 
			            System.out.println("reqEntity : " + reqEntity.toString());
			            HttpResponse uploadResponse = httpclient.execute(httppost); 
			            System.out.println( uploadResponse ) ; 
			            HttpEntity resEntity = uploadResponse.getEntity(); 
			            System.out.println( resEntity ) ; 
			            System.out.println( EntityUtils.toString(resEntity) ); 
			            EntityUtils.consume(resEntity); 
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        } finally {
			        	httpclient.close();
			        }
			        
			        return;	
			} 

			if (fileContent == null) {
				throw new Exception("Fail to call file api - NULL value return...");
			}
			
			outs = response.getPortletOutputStream();
			if (outs != null) {
				//response.setProperty("x-content-length", Integer.toString(fileContent.length));
				
				outs.write(fileContent);
				outs.flush();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outs != null) { outs.close(); }
			if (uploadOut != null) { uploadOut.close(); }
		}
	}

	private String getCurrentTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    Date now = new Date();
	    return sdfDate.format(now);
	}
	
	/**
	 * LOCAL ?�스?�용
	 * 
	 * @param pfileEntryId
	 * @return
	 * @throws IOException
	 */
	private byte[] execLocalFileDownload(String pfileEntryId) throws IOException {
		FileInputStream ins = null;
		InputStream blobIns = null;
		byte[] fileContent = null;
		
		try {
			if (pfileEntryId != null) {
				long fileEntryId = Long.parseLong(pfileEntryId);
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
				fileContent = new byte[(int) fileEntry.getSize()];
				blobIns = fileEntry.getContentStream();
	
				OutputBlob outputBlob = new OutputBlob(blobIns, fileEntry.getSize());
				ins = (FileInputStream) outputBlob.getBinaryStream();
				ins.read(fileContent);
			} else {
				throw new Exception("[callLocalFileApi] the fileEntryId is NULL.....");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (blobIns != null) blobIns.close();
			if (ins != null) ins.close();
		}
		return fileContent;
	}
	
	private byte[] callFileAppiProxy(String fileId) throws IOException {
		byte[] fileContent = null;
		
		String urlStr = "http://118.128.153.167:9090/fileDownload?fileId=" + fileId;
        
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        //conn.setDoOutput(true);
        conn.setRequestMethod("GET");
		
        System.out.println("[callFileAppiProxy] >>>> conn.getResponseCode() : " + conn.getResponseCode());
        System.out.println("content length : " + conn.getContentLength());
        
        if (conn.getResponseCode() == 200) {
        	fileContent = IOUtils.toByteArray(conn.getInputStream());
        } else {
        	System.out.println("[error] response message : " + conn.getResponseMessage());
        	System.out.println("[content] " + conn.getContent());
        	
        	int i = 0;
        	char c;
        	InputStream es = conn.getErrorStream();
        	 // reads till the end of the stream
            while((i = es.read())!=-1) {
               // converts integer to character
               c = (char)i;
               // prints character
               System.out.print(c);
            }
        }
        conn.disconnect();
        
		return fileContent;
	}
	
	private byte[] execIBFileDownload(String fileId,String icebreakerUrl,String icebreakerToken) throws IOException{
		byte[] fileContent = null;
		
		try{
			if(!"".equals(fileId) && !"".equals(icebreakerUrl) && !"".equals(icebreakerToken)){
                String urlStr = icebreakerUrl + "/api/file/download?id=" + fileId;
                
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
                
                System.out.println("[callIBFileApi] >>>> conn.getResponseCode() : " + conn.getResponseCode());
                if (conn.getResponseCode() == 200) {
                	fileContent = IOUtils.toByteArray(conn.getInputStream());
                } 
                conn.disconnect();
            } else {
            	throw new Exception("fail to call the IB File Api..- empty parameter");
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileContent;
	}
	
	private JSONObject apiGetFileInfo(String fileId, String icebreakerUrl, String icebreakerToken) throws IOException  {
		String responseValue = "",output = "";        
        
		HttpURLConnection conn = null;
        BufferedReader br = null;
        JSONObject jsonObj = null;
        
        try{
        	String urlStr = icebreakerUrl + "api/file/" + fileId;
             
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);
            
            
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                while ((output = br.readLine()) != null) {
                    if(!CustomUtil.strNull(output).equals("null")){
                        responseValue += output;
                    }
                }

                jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
            }else{
                System.out.println("Failed FileUtil [ apiHomeFolderList ] : ETC : etc error - HTTP error code : " + conn.getResponseCode());
            }
            return jsonObj;

        }catch (Exception e) {
            e.printStackTrace(); 
        }finally {
        	if(br != null){br.close();}
            if(conn!=null){conn.disconnect();}
        }
        return jsonObj;
    }
	
	
	private void printDetailValues(String label, ResourceRequest request, Enumeration<String> names) {
    	System.out.println("++++++++++++ " + label + " ++++++++++++");
    	
        String name = "", value = "";
        while(names.hasMoreElements()) {
        	name = names.nextElement();
        	
        	if (label.indexOf("parameter") >= 0) {
        		value = request.getParameter(name).toString();
        	} else {
        		value = request.getAttribute(name).toString();
        	}
        	
        	System.out.println("name : " + name + ", value : " + value);
        }
    }
}