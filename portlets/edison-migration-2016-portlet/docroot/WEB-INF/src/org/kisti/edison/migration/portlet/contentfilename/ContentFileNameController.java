package org.kisti.edison.migration.portlet.contentfilename;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;

import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class ContentFileNameController {
	
	private static Log log = LogFactoryUtil.getLog(ContentFileNameController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model){
		return "contentfilename/migration";
	}

	@ResourceMapping(value="updateContentFileName")
	public void contentMigration(ResourceRequest request, ResourceRequest response,
			@RequestParam(value = "contentDiv", required = true) Long contentDiv
			) throws SystemException{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
			Path baseContentPath = Paths.get(PropsUtil.get(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR),"content");
			
			List<Content> dataList = ContentLocalServiceUtil.findByContentDiv(contentDiv);
			
			int successUpdate = 0;
			int notUpdate = 0;
			int multiFileCnt = 0;
			int fileNotExistCnt = 0;
			int folderNotExistCnt = 0;
			
			List<Long> multiFileList = new ArrayList<Long>();
			List<Long> fileNotExistList = new ArrayList<Long>();
			List<Long> folderNotExistList = new ArrayList<Long>();
			for(Content content:dataList){
				String contentFileNm = content.getContentFileNm(themeDisplay.getLocale());
				if(contentDiv==2001002){
					String[] avaLanuage = content.getAvailableLanguageIds();
					
					for(String lanuage : avaLanuage){
						Path contentFolder = Paths.get(baseContentPath.toString(),String.valueOf(content.getContentSeq()),lanuage);
						
						File dir = contentFolder.toFile();
						if(dir.exists()){
							File[] fileList = dir.listFiles();
							String fileExt = "."+contentFileNm.substring(contentFileNm.lastIndexOf(".")+1);
							
							if(fileList.length==1){
								File contentFile = fileList[0];
								if(!contentFile.getName().equals(content.getContentSeq()+fileExt)){
									File renameFile = contentFolder.resolve(content.getContentSeq()+fileExt).toFile();
									System.out.print("orign file name ->>"+contentFile.getName());
									contentFile.renameTo(renameFile);
									System.out.println("     Update file name ->>"+renameFile.getName());
									successUpdate++;
								}else{
									notUpdate++;
								}
							}else if(fileList.length==0){
								fileNotExistList.add(content.getContentSeq());
								fileNotExistCnt++;
							}else{
								multiFileList.add(content.getContentSeq());
								multiFileCnt++;
							}
						}else{
							folderNotExistList.add(content.getContentSeq());
							folderNotExistCnt++;
						}
					}
				}else{
					Path contentFolder = Paths.get(baseContentPath.toString(),String.valueOf(content.getContentSeq()));
					
					File dir = contentFolder.toFile();
					if(dir.exists()){
						File[] fileList = dir.listFiles();
						String fileExt = "."+contentFileNm.substring(contentFileNm.lastIndexOf(".")+1);
						
						if(fileList.length==1){
							File contentFile = fileList[0];
							if(!contentFile.getName().equals(content.getContentSeq()+fileExt)){
								File renameFile = contentFolder.resolve(content.getContentSeq()+fileExt).toFile();
								System.out.print("orign file name ->>"+contentFile.getName());
								contentFile.renameTo(renameFile);
								System.out.println("     Update file name ->>"+renameFile.getName());
								successUpdate++;
							}else{
								notUpdate++;
							}
						}else if(fileList.length==0){
							fileNotExistList.add(content.getContentSeq());
							fileNotExistCnt++;
						}else{
							multiFileList.add(content.getContentSeq());
							multiFileCnt++;
						}
					}else{
						folderNotExistList.add(content.getContentSeq());
						folderNotExistCnt++;
					}
				}
				
			}
		System.out.println("=======================RESULT==========================");
		System.out.println("TOTAL : "+ dataList.size());
		System.out.println("SUCCESS : "+ successUpdate);
		System.out.println("notUpdate : "+ notUpdate);
		System.out.println("multiFileCnt : "+ multiFileCnt);
		System.out.println("fileNotExistCnt : "+ fileNotExistCnt);
		System.out.println("folderNotExistCnt : "+ folderNotExistCnt);
		log(multiFileList,"MULTI FILE");
		log(fileNotExistList,"FILE NOT EXIST");
		log(folderNotExistList,"FOLDER NOT EXIST");
		System.out.println("=======================================================");
	}
	
	public void log(List<Long> data,String type){
		if(data.size()!=0){
			System.out.println("================="+type+"  ERROR LIST==========================");
			for(Long seq : data){
				System.out.println(seq);
			}
			System.out.println("=======================================================");
		}
	}
	

}
