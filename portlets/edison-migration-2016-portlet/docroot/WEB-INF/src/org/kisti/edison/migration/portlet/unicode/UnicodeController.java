package org.kisti.edison.migration.portlet.unicode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.kisti.edison.migration.util.TxtFileFilter;
import org.kisti.edison.science.NoSuchPortTypeInputdeckFormException;
import org.kisti.edison.science.model.PortTypeInputdeckForm;
import org.kisti.edison.science.service.PortTypeInputdeckFormLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class UnicodeController {
	
	private static Log log = LogFactoryUtil.getLog(UnicodeController.class);
	String txtFilePath = PropsUtil.get(PropsKeys.LIFERAY_HOME) + File.separator+"migration"+File.separator+"unicode";
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		
		File dirFile = new File(txtFilePath);
		// 파일 경로가 없을 경우 생성
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		
		
		if(dirFile.isDirectory()){
			TxtFileFilter txtFileFilter = new TxtFileFilter();
			File[] fileList = dirFile.listFiles(txtFileFilter);
			model.addAttribute("fileSize", fileList.length);
		}else{
			model.addAttribute("error", "no directory");
		}
		
		return "migration";
	}
	
	@ActionMapping(params="myaction=unicodeMigration")
	public void unicodeMigration(ActionRequest request, ActionResponse response, ModelMap model){
		TxtFileFilter txtFileFilter = new TxtFileFilter();
		File dirFile = new File(txtFilePath);
		File[] fileList = dirFile.listFiles(txtFileFilter);
		long portTypeId = 0;
		
		for(File file:fileList){
			FileInputStream fs  = null;
			InputStreamReader reader = null;
			BufferedReader bufferedReader = null;
			
			String temp = "";
			String content = "";
			
			try{
				fs = new FileInputStream(file);
				reader = new InputStreamReader(fs,"UTF-8");
				bufferedReader = new BufferedReader(reader);
				
				while( (temp = bufferedReader.readLine()) != null) {
					content += temp;
				}
				log.debug("파일명:"+portTypeId);
				
				portTypeId = Long.parseLong(file.getName().substring(0,file.getName().lastIndexOf("."))); 
				
				JSONObject jsonObject = JSONObject.fromObject(content);
				
				PortTypeInputdeckForm portTypeInputdeckForm =  PortTypeInputdeckFormLocalServiceUtil.getPortTypeInputdeckForm(portTypeId);
				portTypeInputdeckForm.setInputdeckForm(jsonObject.toString());
				System.out.println("JSON==>"+jsonObject.toString());
				PortTypeInputdeckFormLocalServiceUtil.updatePortTypeInputdeckForm(portTypeInputdeckForm);
				
				fs.close();
				reader.close();
				bufferedReader.close();
				file. delete();
			}catch (Exception e) {
				if(e instanceof NoSuchPortTypeInputdeckFormException){
					log.error("NoSuchPortTypeId:"+portTypeId);
				}else{
					e.printStackTrace();
				}
				
			}finally{
				if(fs != null){try{fs.close();}catch(Exception e){e.printStackTrace();}}
				if(reader != null){try{reader.close();}catch(Exception e){e.printStackTrace();}}
				if(bufferedReader != null){try{bufferedReader.close();}catch(Exception e){e.printStackTrace();}}
				System.out.println("======================================================================================================================================================================================");
			}
		}
	}
}
