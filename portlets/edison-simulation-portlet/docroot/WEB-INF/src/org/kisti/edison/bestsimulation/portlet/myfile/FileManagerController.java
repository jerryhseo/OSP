package org.kisti.edison.bestsimulation.portlet.myfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.HttpFileUtil;
import org.kisti.edison.util.MyFileIcebreakerTokenUtil;
import org.kisti.edison.util.MyFileIcebreakerUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.util.TokenProviderUtil;
import org.kisti.edison.util.VCRegisterUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping({ "VIEW" })
public class FileManagerController {
	private static Log log = LogFactoryUtil.getLog(FileManagerController.class);
	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";

	public FileManagerController() {
	}

	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model) {
		
		try {
			Map param = RequestUtil.getParameterMap(request);

			long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			IcebreakerVcToken icebreakerVcToken = new IcebreakerVcToken();

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String userScreenName = "";
			String userPassword = "";

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			String universityField = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY);
			String virtualLabId = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_LAB_ID);
			String classId = (String)user.getExpandoBridge().getAttribute(EdisonExpando.USER_CLASS_ID);
			String majorField = "";
			
			// popUpState
			model.addAttribute("portletWindowState", request.getWindowState().toString());
			
			// extension value Param
			String fileExt = CustomUtil.strNull(param.get("fileExt"),"");
			model.addAttribute("fileExt", fileExt);
			
			// fileSearchType
			String fileSearchType = CustomUtil.strNull(param.get("fileSearchType"), "");
			model.addAttribute("fileSearchType", fileSearchType);
			
			// fileIdFilter
			String fileIdFilter = CustomUtil.strNull(param.get("fileIdFilter"), "");
			model.addAttribute("fileIdFilter", fileIdFilter);

			if (thisGroup.getParentGroupId() == 0L) {
				String visitSite = themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
				List<Group> groupList = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), thisGroup.getGroupId(), true);

				for (Group group : groupList) {
					if (visitSite.equals(group.getName())) {
						groupId = group.getGroupId();
						thisGroup = GroupLocalServiceUtil.getGroup(groupId);
						break;
					}
				}
			}

			if(EdisonUserUtil.isRegularRole(user, EdisonRoleConstants.ADMINISTRATOR) || EdisonUserUtil.isSiteRole(user, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
				userScreenName = (String)thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_ID);
				userPassword = (String)thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ADMIN_PWD);
			}else{
				userScreenName = String.valueOf(user.getScreenName());
				userPassword = user.getPassword();
			}
			
			log.info("vcRegister --> " + VCRegisterUtil.isVcInfo(groupId, userScreenName));

			if (VCRegisterUtil.isVcInfo(groupId, userScreenName) == 200) {
				if(user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))){
					icebreakerVcToken.setVcToken(CustomUtil.strNull(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId))));				
					icebreakerVcToken.setVcTokenExpired(CustomUtil.strNull(user.getExpandoBridge().getAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId)), "0"));

					if (Integer.parseInt(icebreakerVcToken.getVcTokenExpired()) <= Integer.parseInt(CustomUtil.dateToStringFormat(new Date(), "yyyyMMdd"))) {
						icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, userScreenName, userPassword);

						if (icebreakerVcToken.getResultCode() != 200) {
							int updateResult = VCRegisterUtil.VCUpdate(groupId, userScreenName, userPassword,user.getEmailAddress());
							if (updateResult == 200) {
								icebreakerVcToken = TokenProviderUtil.getVcToken(groupId, userScreenName, userPassword);
							}
						}

						if (icebreakerVcToken.getResultCode() == 200) {
							icebreakerVcToken.setVcToken(icebreakerVcToken.getVcToken());
							icebreakerVcToken.setVcTokenExpired(icebreakerVcToken.getVcTokenExpired());

							user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(groupId), icebreakerVcToken.getVcToken());
							user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(groupId), icebreakerVcToken.getVcTokenExpired());
						} else {
							log.debug("SimulationLocalServiceImpl : Icebreaker getOrCreateToken Error !!");
						}
					}
				} else {
					log.debug("GROUP_ID--->" + groupId);
					icebreakerVcToken = MyFileIcebreakerTokenUtil.createExpandoUserVctoken(user, groupId,
							userScreenName, userPassword);
				}
			} else {
				int resultRegist = VCRegisterUtil.VCRegist(groupId, userScreenName, userPassword,user.getEmailAddress(), user.getFirstName(), universityField, virtualLabId, classId,majorField);

				if (resultRegist == 201) {
					icebreakerVcToken = MyFileIcebreakerTokenUtil.createExpandoUserVctoken(user, groupId,userScreenName, userPassword);
				}
			}

			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			String icebreakerPublicUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC));

			if (!"".equals(icebreakerUrl)) {
				model.addAttribute("icebreakerUrl", icebreakerUrl);
			}
			model.addAttribute("icebreakerPublicUrl", icebreakerPublicUrl);
			model.addAttribute("icebreakerVcToken", icebreakerVcToken);
			model.addAttribute("groupId", Long.valueOf(groupId));

			Map<String, Object> uploadMap = request.getPortletSession().getAttributeMap();
			if (uploadMap != null) {
				model.addAttribute("mode", CustomUtil.strNull(request.getPortletSession().getAttribute("mode")));
				model.addAttribute("uploaddestFolderId", CustomUtil.strNull(request.getPortletSession().getAttribute("uploaddestFolderId")));
				model.addAttribute("destFolderParents", CustomUtil.strNull(request.getPortletSession().getAttribute("destFolderParents")));

				request.getPortletSession().removeAttribute("mode");
				request.getPortletSession().removeAttribute("uploaddestFolderId");
				request.getPortletSession().removeAttribute("destFolderParents");
			}

			model.addAttribute("returnId", CustomUtil.strNull(param.get("returnId")));
			model.addAttribute("returnFileName", CustomUtil.strNull(param.get("returnFileName")));
			model.addAttribute("cluster", CustomUtil.strNull(param.get("cluster")));
			model.addAttribute("workflowType", CustomUtil.strNull(param.get("workflowType")));
		} catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}

		return "myfile/fileManager";
	}

	@ResourceMapping("getRootData")
	public void getRootData(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);

			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			
			/* 팝업 출력 시 필터 */
			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"),"");
			String fileExt = CustomUtil.strNull(param.get("fileExt"),"");
			String fileIdFilter = CustomUtil.strNull(param.get("fileIdFilter"),"");
			
			Map<String, Object> getFolderAndFile = MyFileIcebreakerUtil.apiHomeFolderAndFileList(icebreakerUrl, icebreakerToken, fileExt, fileIdFilter);
			JSONArray folderJsonArr = (JSONArray) getFolderAndFile.get("jsonFolderArray");
			JSONArray fileJsonArr = (JSONArray) getFolderAndFile.get("jsonFileArray");
			int folderCount = (int) getFolderAndFile.get("folderCount");
			int fileCount = (int) getFolderAndFile.get("fileCount");
			
			JSONArray rootDataJsonArr = concatJsonArray(folderJsonArr, fileJsonArr);

			JSONObject json = new JSONObject();

			json.put("dataArray", rootDataJsonArr);
			json.put("folderCount", folderCount);
			json.put("fileCount", fileCount);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("getSelectedFolder")
	public void getChildFolder(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String responseValue = "";

			int responseCode = 0;
			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
			
			/* 팝업 출력 시 필터 */
			String fileExt = CustomUtil.strNull(param.get("fileExt"),"");
			String fileIdFilter = CustomUtil.strNull(param.get("fileIdFilter"),"");

			Map<String, Object> getFolderAndFile = new HashMap<String, Object>();
			if(selectFolderId.toUpperCase().equals("HOME")){
				getFolderAndFile = MyFileIcebreakerUtil.apiHomeFolderAndFileList(icebreakerUrl, icebreakerToken, fileExt, fileIdFilter);
			} else {
				getFolderAndFile = MyFileIcebreakerUtil.apiFolderAndFileList(icebreakerUrl, icebreakerToken, selectFolderId, fileExt, fileIdFilter);
			}
				
			JSONArray folderJsonArr = (JSONArray) getFolderAndFile.get("jsonFolderArray");
			JSONArray fileJsonArr = (JSONArray) getFolderAndFile.get("jsonFileArray");
			int folderCount = (int) getFolderAndFile.get("folderCount");
			int fileCount = (int) getFolderAndFile.get("fileCount");

			JSONArray dataJsonArr = concatJsonArray(folderJsonArr, fileJsonArr);

			JSONObject json = new JSONObject();
			json.put("responseCode", Integer.valueOf(responseCode));
			json.put("dataArray", dataJsonArr);
			json.put("folderCount", folderCount);
			json.put("fileCount", fileCount);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONArray concatJsonArray(JSONArray folderArr, JSONArray fileArr) {
		JSONArray resultArray = new JSONArray();

		for (int i = 0; i < folderArr.size(); i++) {
			resultArray.element(folderArr.get(i));
		}
		for (int i = 0; i < fileArr.size(); i++) {
			resultArray.element(fileArr.get(i));
		}
		return resultArray;
	}

	public String getFileExtension(String fileName) {
		if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0)) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return "";
	}

	@ResourceMapping("getChildFile")
	public void getChildFile(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String responseValue = "";

			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));
			if (!"".equals(icebreakerUrl)) {
				String urlStr = "";
				if (!selectFolderId.equals("HOME")) {
					urlStr = icebreakerUrl + "/api/file/" + selectFolderId + "/list";
				} else {
					urlStr = icebreakerUrl + "/api/file/list";
				}

				URL url = new URL(urlStr);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + icebreakerToken);

				String output = "";
				if (conn.getResponseCode() == 200) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					while ((output = br.readLine()) != null) {
						if (!CustomUtil.strNull(output).equals("null")) {
							responseValue = responseValue + output;
						}
					}
				}
				conn.disconnect();
			}

			List resultList = new ArrayList();
			if (!CustomUtil.strNull(responseValue).equals("")) {
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(responseValue));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				HashMap resultMap = null;

				for (int i = 0; i < jsonArray.size(); i++) {
					resultMap = new HashMap();
					JSONObject comandObj = (JSONObject) jsonArray.get(i);

					resultMap.put("parentFolderId", selectFolderId);
					resultMap.put("fileName", comandObj.getString("name"));
					resultMap.put("path", comandObj.getString("path"));
					resultMap.put("fileId", comandObj.getString("id"));
					resultMap.put("parentPath", comandObj.getString("parentPath"));
					resultMap.put("lastModified", comandObj.getString("lastModified"));
					resultMap.put("fileSize", CustomUtil.fileVolumeCalc(String.valueOf(comandObj.getDouble("size"))));

					resultList.add(resultMap);
				}
			}

			JSONObject json = new JSONObject();
			json.put("dataList", resultList);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("createNewFolder")
	public void createFolder(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));

			int responseStatus = MyFileIcebreakerUtil.createFolder(icebreakerUrl, icebreakerToken, param);

			log.info("responseStatus : " + responseStatus);
			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(responseStatus));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("renameFolder")
	public void renameFolder(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));

			int responseStatus = MyFileIcebreakerUtil.renameFolder(icebreakerUrl, icebreakerToken, param);
			
			log.info("responseStatus : " + responseStatus);

			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(responseStatus));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("deleteFolder")
	public void deleteFolder(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));

			String selectFolderId = CustomUtil.strNull(param.get("selectFolderId"));

			int responseStatus = MyFileIcebreakerUtil.deleteFolder(icebreakerUrl, icebreakerToken, param);
			log.info("responseStatus : " + responseStatus);

			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(responseStatus));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("moveNode")
	public void moveNode(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);

			User user = PortalUtil.getUser(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = MyFileIcebreakerTokenUtil.getOrCreateToken(groupId, user).getVcToken();

			int responseStatus = 0;

			String userScreenName = "";
			if (EdisonUserUtil.isRegularRole(user, "Administrator")) {
				userScreenName = (String) thisGroup.getExpandoBridge().getAttribute("icebreakerAdminId");
			} else {
				userScreenName = String.valueOf(user.getScreenName());
			}

			responseStatus = MyFileIcebreakerUtil.moveNode(icebreakerUrl, icebreakerToken, param, userScreenName);

			log.info("responseStatus : " + responseStatus);
			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(responseStatus));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResourceMapping("deleteFile")
	public void deleteFile(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));
			String deleteFileId = CustomUtil.strNull(param.get("deletefileId"));

			int deleteResponseCode = MyFileIcebreakerUtil.deleteFile(icebreakerUrl, icebreakerToken, param);

			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(deleteResponseCode));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RenderMapping(params = { "myaction=fileUploadPopUp" })
	public String fileUploadPopUp(RenderRequest request, RenderResponse response, ModelMap model) {
		try {
			Map param = RequestUtil.getParameterMap(request);

			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			String icebreakerToken = CustomUtil.strNull(param.get("vcToken"));

			String destFolderId = CustomUtil.strNull(param.get("destFolderId"));
			String destFolderParents = CustomUtil.strNull(param.get("destFolderParents"));
			String isPopUp = CustomUtil.strNull(param.get("isPopUp"), "false");

			String returnId = CustomUtil.strNull(param.get("returnId"));
			String returnFileName = CustomUtil.strNull(param.get("returnFileName"));
			String cluster = CustomUtil.strNull(param.get("cluster"));
			String workflowType = CustomUtil.strNull(param.get("workflowType"));

			model.addAttribute("groupId", Long.valueOf(groupId));
			model.addAttribute("vcToken", icebreakerToken);
			model.addAttribute("destFolderId", destFolderId);
			model.addAttribute("destFolderParents", destFolderParents);
			model.addAttribute("isPopUp", isPopUp);
			model.addAttribute("returnId", returnId);
			model.addAttribute("returnFileName", returnFileName);
			model.addAttribute("cluster", cluster);
			model.addAttribute("workflowType", workflowType);

			return "myfile/fileUpload";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@ActionMapping(params = { "myaction=fileIBUpload" })
	public void fileIBUpload(ActionRequest request, ActionResponse response) {

		try {
			UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
			Map param = RequestUtil.getParameterMap(upload);
			User user = PortalUtil.getUser(request);
			
			File[] uploadFiles = upload.getFiles("addFile");
			String[] fileNames = upload.getFileNames("addFile");
			
			String vcToken = CustomUtil.strNull(param.get("vcToken"));
			String destFolderId = CustomUtil.strNull(param.get("destFolderId"));
			String destFolderParents = CustomUtil.strNull(param.get("destFolderParents"));
			String destFolderPath = CustomUtil.strNull(param.get("destFolderPath"));
			
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			if (!"".equals(icebreakerUrl)) {
				URL url = new URL(icebreakerUrl + "/api/file/upload?cluster=EDISON-CFD");
				InputStream[] uploadInputStream = upload.getFilesAsStream("addFile", false);

				int responseStatus = 0;

				for (int i = 0; i < uploadFiles.length; i++) {
					HttpFileUtil httpFileUtil = new HttpFileUtil(url);
					httpFileUtil.Token = vcToken;

					File tempFile = null;
					byte[] bytes = FileUtil.getBytes(uploadFiles[i]);
					if (ArrayUtil.isNotEmpty(bytes)) {
						tempFile = FileUtil.createTempFile(bytes);
					} else {
						InputStream fileObj = uploadInputStream[i];
						try {
							tempFile = FileUtil.createTempFile(fileObj);
						} catch (IllegalArgumentException e) {
							tempFile = FileUtil.createTempFile();

							FileUtil.write(tempFile, "");
						}
					}
					
					StringBuffer bodyStr = new StringBuffer();
					String uploadPath = "/EDISON/./LDAP/DATA/"+user.getScreenName()+"/repository/" + destFolderPath + fileNames[i];
					bodyStr.append("{");
					bodyStr.append("\"destPath\" : \""+uploadPath+"\"");
					bodyStr.append("}");
					
					FileOutputStream output = new FileOutputStream(ICEBREAKER_TEMP_PATH + File.separator + fileNames[i]);
					output.write(bodyStr.toString().getBytes());
					FileInputStream inputStream = new FileInputStream(tempFile);

					byte[] buffer = new byte[2048];
					int readcount = 0;
					while ((readcount = inputStream.read(buffer)) != -1) {
						output.write(buffer, 0, readcount);
					}
					inputStream.close();
					output.close();

					if (tempFile.exists()) {
						tempFile.delete();
					}

					File uploadfile = new File(ICEBREAKER_TEMP_PATH + File.separator + fileNames[i]);

					httpFileUtil.addFile("file", uploadfile);

					String resultJson = httpFileUtil.sendMultipartPost();
					if (!"".equals(CustomUtil.strNull(resultJson))) {
						/*JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(resultJson));
						String fileId = json.getString("id");

						if ((!"".equals(fileId)) && (!destFolderId.equals("HOME"))) {
							URL fileMoveUrl = new URL(icebreakerUrl + "/api/file/move/" + fileId + "/" + destFolderId);

							HttpURLConnection conn = (HttpURLConnection) fileMoveUrl.openConnection();

							conn.setDoOutput(true);
							conn.setRequestMethod("PUT");
							conn.setRequestProperty("Accept", "application/json");
							conn.setRequestProperty("Authorization", "Basic " + vcToken);

							responseStatus = conn.getResponseCode();
							log.info("responseStatus : " + responseStatus);
							conn.disconnect();

							if (responseStatus == 200) {
								request.getPortletSession().setAttribute("mode", "fileUpload");
								request.getPortletSession().setAttribute("uploaddestFolderId", destFolderId);
								request.getPortletSession().setAttribute("destFolderParents", destFolderParents);

								SessionMessages.add(request, "edion-insert-success");
							}
						} else if (destFolderId.equals("HOME")) {
							SessionMessages.add(request, "edion-insert-success");
						} else {
							SessionErrors.add(request, "edion-insert-error");
						}*/
					} else {
						SessionErrors.add(request, "edion-insert-error");
					}

					if (uploadfile.exists()) {
						uploadfile.delete();
					}
				}
			}
		} catch (Exception e) {
			SessionErrors.add(request, "edion-insert-error");
			e.printStackTrace();
		}
	}

	@ResourceMapping("copyFile")
	public void copyFile(ResourceRequest request, ResourceResponse response) {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
			Map param = RequestUtil.getParameterMap(request);

			User user = PortalUtil.getUser(request);
			long groupId = Long.parseLong(
					CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));

			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute("icebreakerUrl"));

			String icebreakerToken = MyFileIcebreakerTokenUtil.getOrCreateToken(groupId, user).getVcToken();

			int responseStatus = 0;

			String userScreenName = "";
			if (EdisonUserUtil.isRegularRole(user, "Administrator")) {
				userScreenName = (String) thisGroup.getExpandoBridge().getAttribute("icebreakerAdminId");
			} else {
				userScreenName = String.valueOf(user.getScreenName());
			}

			responseStatus = MyFileIcebreakerUtil.copyFile(icebreakerUrl, icebreakerToken, param, userScreenName);

			log.info("responseStatus : " + responseStatus);
			JSONObject json = new JSONObject();
			json.put("status", Integer.valueOf(responseStatus));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping("getItemInfo")
	public void getItemInfo(ResourceRequest request, ResourceResponse response) {
		try {
			Map requestParamMap = RequestUtil.getParameterMap(request);
			String icebreakerUrl = CustomUtil.strNull(requestParamMap.get("icebreakerUrl"));
			String icebreakerToken = CustomUtil.strNull(requestParamMap.get("vcToken"));
			
			HashMap<String, Object> itemInfoMap = MyFileIcebreakerUtil.getItemInfo(icebreakerUrl, icebreakerToken, requestParamMap);
			
			JSONObject json = new JSONObject();
			json.put("itemInfoMap", itemInfoMap);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
