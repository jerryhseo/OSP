package com.kisti.osp.editor.portlet.fileexplorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kisti.osp.service.FileManagementLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class FileExplorerPortlet
 * 
 * Author: Jerry H. Seo
 * 
 * Important terms
 */
public class FileExplorerPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(FileExplorerPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		String inputData = ParamUtil.getString(renderRequest, "inputData", "");
		
		boolean eventEnable = ParamUtil.getBoolean(renderRequest, "eventEnable", true);
		String connector = ParamUtil.getString(renderRequest, "connector", "BROADCAST");
		
		String mode = ParamUtil.getString(renderRequest, "mode", "EDIT");
		String action = ParamUtil.getString(renderRequest, "action", "input");
		
		renderRequest.setAttribute("inputData", inputData);
		renderRequest.setAttribute("eventEnable", eventEnable);
		renderRequest.setAttribute("connector", connector);
		renderRequest.setAttribute("mode", mode);
		renderRequest.setAttribute("action", action);
		 
		super.doView(renderRequest, renderResponse);
	}
	
	public void serveResource( ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(resourceRequest);
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(resourceResponse);

		String command = ParamUtil.getString(resourceRequest, "command");
		String action = ParamUtil.getString(resourceRequest, "action", "input");
		boolean isJobResult = action.equalsIgnoreCase("input") ? false : true;

		if( command.equalsIgnoreCase("GET_FILE_INFO") ){
			String pathType = ParamUtil.getString(resourceRequest, "pathType", "file");
			Path basePath = Paths.get(ParamUtil.getString(resourceRequest, "basePath"));
			Path parentPath = Paths.get(ParamUtil.getString(resourceRequest, "parentPath"));
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			
			Path targetPath = basePath.resolve(parentPath).resolve(fileName); 

			_log.debug("basePath: "+basePath.toString());
			_log.debug("Parent Folder: "+parentPath.toString());
			_log.debug("File Name: "+fileName);

			JSONArray fileInfos = null;
			JSONObject resultJSON = JSONFactoryUtil.createJSONObject();
			if( pathType.equalsIgnoreCase("file") ){
				JSONObject fileInfo = null;
				try {
					fileInfo = FileManagementLocalServiceUtil.getFileInformation( resourceRequest, targetPath.toString(), isJobResult );
					resultJSON.put("parentPath", targetPath.getParent().toString());
				} catch (PortalException | SystemException e) {
					_log.debug("[ERROR]FileManagementLocalServiceUtil.getFileInformation("+targetPath.toString()+")");
					throw new PortletException();
				}
				fileInfos = JSONFactoryUtil.createJSONArray();
				fileInfos.put(fileInfo);
			}
			else if( pathType.equalsIgnoreCase("ext") ){
				try {
					fileInfos  = FileManagementLocalServiceUtil.getFolderInformation( resourceRequest, targetPath.getParent().toString(), fileName, isJobResult );
					resultJSON.put("parentPath", targetPath.getParent().toString());
				} catch (PortalException | SystemException e) {
					_log.debug("[ERROR]FileManagementLocalServiceUtil.getFolderInformation("+targetPath.toString()+")");
					throw new PortletException();
				}
			}
			else if( pathType.equalsIgnoreCase("folder") ){
				try {
					fileInfos = FileManagementLocalServiceUtil.getFolderInformation( resourceRequest, targetPath.toString(), "", isJobResult );
					resultJSON.put("parentPath", targetPath.toString());
				} catch (PortalException | SystemException e) {
					_log.debug("[ERROR]FileManagementLocalServiceUtil.getFolderInformation("+targetPath.toString()+ ")");
					throw new PortletException();
				}
			}
			else
				throw new IOException("Unknown path type: " + pathType);
			
			resultJSON.put( "fileInfos", fileInfos );
			resultJSON.put("fileName", fileName);
			resultJSON.put("fileInfos", fileInfos);
			
			_log.debug(resultJSON.toString());

			ServletResponseUtil.write(httpResponse, resultJSON.toString());
		}
		else if( command.equalsIgnoreCase("GET_COPIED_TEMP_FILE_PATH") ){
			/*
			String result = "";
			try {
				result = FileManagementLocalServiceUtil.getCopiedTemporaryFilePath( 
						resourceRequest, fullFilePath.toString(), "", ".tmp" );
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletResponseUtil.write(httpResponse, result);
			*/
		}
		else if( command.equalsIgnoreCase("GET_LINKED_TEMP_FILE_PATH")){
			/*
			String result = "";
			try {
				result = FileManagementLocalServiceUtil.getLinkedTemporaryFilePath( 
						resourceRequest, fullFilePath.toString(), "", ".tmp", true );
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletResponseUtil.write(httpResponse, result);
			*/
		}
		else if( command.equalsIgnoreCase("GET_COPIED_TEMP_HTML_PATH")){
			/*
			String result = "";
			try {
				result = FileManagementLocalServiceUtil.getCopiedTemporaryFilePath( 
						resourceRequest, parentPath.toString(), "ospjm", ".tmp" );
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletResponseUtil.write(httpResponse, Paths.get(result).resolve(fileName).toString() );
			*/
		}
		else if( command.equalsIgnoreCase("GET_LINKED_TEMP_HTML_PATH")){
			/*
			String result = "";
			try {
				result = FileManagementLocalServiceUtil.getLinkedTemporaryFilePath( 
						resourceRequest, fullFilePath.toString(), "", ".tmp", true );
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletResponseUtil.write(httpResponse, Paths.get(result).resolve(fileName).toString() );
			*/
		}
		else if( command.equalsIgnoreCase("GET_FILE") ){
			/*
			if( Files.notExists(fullFilePath) )
				throw new FileNotFoundException(fileName);
			
			ServletResponseUtil.write(httpResponse, fullFilePath.toFile());
			*/
		}
		else if( command.equalsIgnoreCase("CHECK_DUPLICATED") ){
			String target = ParamUtil.getString(resourceRequest, "target");

			try {
				FileManagementLocalServiceUtil.duplicated( resourceRequest, resourceResponse, target, isJobResult);
			} catch (PortalException | SystemException e) {
				_log.error("duplicated: "+ target);
				throw new PortletException();
			}
		}
		else if( command.equalsIgnoreCase("CHECK_PATH_TYPE") ){
			/*
			ServletResponseUtil.write(httpResponse, String.valueOf(!Files.isDirectory(fullFilePath)) );
			*/
		}
		else if( command.equalsIgnoreCase("CREATE") ){
			
		}
		else if( command.equalsIgnoreCase("COPY") ){
			
		}
		else if( command.equalsIgnoreCase("DELETE") ){
			
		}
		else if( command.equalsIgnoreCase("MOVE") ){
			
		}
		else if( command.equalsIgnoreCase("SAVE") ){
			
		}
		else if( command.equalsIgnoreCase("UPLOAD")){
			String targetFolder = ParamUtil.getString(resourceRequest, "targetFolder");
			String fileName = ParamUtil.getString(resourceRequest, "fileName");
			String uploadFileParam = "uploadFile";
			
			_log.debug("UPLOAD targetFolder: "+ targetFolder);
			_log.debug("UPLOAD fileName: "+ fileName);
			Path target = Paths.get(targetFolder).resolve(fileName);

			try {
				FileManagementLocalServiceUtil.upload(resourceRequest, target.toString(), uploadFileParam, isJobResult);
			} catch (PortalException | SystemException e) {
				_log.debug("Upload Failed: "+target);
				throw new IOException();
			}
		}
		else if( command.equalsIgnoreCase("DOWNLOAD")){
			String folderPath = ParamUtil.getString(resourceRequest, "folderPath");
			String fileNames = ParamUtil.getString(resourceRequest, "fileNames");
			
			_log.debug("FolderPath: "+folderPath);
			_log.debug("FileNames: "+fileNames);
			
			JSONArray files = null;
			String[] sources = null;
			try {
				files = JSONFactoryUtil.createJSONArray(fileNames);
				sources = new String[files.length()];
				for (int i=0; i<files.length(); i++ ) {
					sources[i] = files.getString(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				throw new IOException();
			}
			
			try {
				FileManagementLocalServiceUtil.download( resourceRequest, resourceResponse, folderPath, sources, isJobResult);
			} catch (PortalException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("{ERROR] Unsupported command: FileExplorer");
		}

		super.serveResource(resourceRequest, resourceResponse);
	}
}
