package org.kisti.edison.bestsimulation.portlet.simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.kisti.edison.bestsimulation.model.SimulationJob;
import org.kisti.edison.bestsimulation.model.SimulationJobData;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationShareLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.science.model.PortType;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.model.ScienceAppDescription;
import org.kisti.edison.science.service.PortTypeEditorLinkLocalServiceUtil;
import org.kisti.edison.science.service.PortTypeLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppDescriptionLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppInputPortsLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.NoSuchCategoryPropertyException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class SimulationController {

	private static Log log = LogFactoryUtil.getLog(SimulationController.class);

	private final String ICEBREAKER_TEMP_PATH = PropsUtil.get(PropsKeys.LIFERAY_HOME)+"/ICEBREAKER_TEMP";

	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		Map params = RequestUtil.getParameterMap(request);
		String visitSite ="";
		//PropsUtil.get(PropsKeys.LIFERAY_HOME)

		try{

			Group thisGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getSiteGroupId());

			boolean isPortalMain = true;
			/**####################################################################################
			 * ### 앱스토어, 모니터링 직접 실행 Start
			 */
			long directGroupId = ParamUtil.getLong(request, "directGroupId",0);
			//앱스토어에서 실행요청을 하는 경우 directGroupId, directSolverId가 있음.
			long directScienceAppId = ParamUtil.getLong(request, "directScienceAppId",0);
			if(directScienceAppId > 0){
				//모니터링에서 실행요청을 하는 경우 directSimulationUuid, directJobSeqNo가 추가됨
				String directJobUuid = ParamUtil.getString(request, "directJobUuid", "");
				if(directJobUuid.equals("")) {
					model.addAttribute("simulationJobData", "{}");
				} else {
					SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(directJobUuid);

					if(simulationJobData != null) {
						model.addAttribute("simulationJobData", simulationJobData.getJobData());
					} else {
						model.addAttribute("simulationJobData", "{}");
					}
				}

				model.addAttribute("directGroupId", directGroupId);
				model.addAttribute("directScienceAppId", directScienceAppId);

				Map scienceAppMap = ScienceAppLocalServiceUtil.getScienceAppReturnObject(directScienceAppId, themeDisplay.getLocale());

				model.addAttribute("directScienceApp_name", CustomUtil.strNull(scienceAppMap.get("name")));
				model.addAttribute("directScienceApp_title", CustomUtil.strNull(scienceAppMap.get("title")));
			} else {
				model.addAttribute("simulationJobData", "{}");
			}
			/**
			 * ### 앱스토어, 모니터링 직접 실행 End
			 ####################################################################################*/

			long parentGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getScopeGroupId(request)).getParentGroupId();

			String tabViewYn = request.getPreferences().getValue("tabViewYn", "N");
			String tabUseStr = request.getPreferences().getValue("tabUseList", "");

			if(tabViewYn.equals("Y")){
				
				Group defaultGroup = GroupLocalServiceUtil.getGroup(companyId, "CFD");
				
				//User Expando 값 가지고 오기
				if(themeDisplay.isSignedIn()){
					visitSite =  themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_VISIT_SITE).toString();
				}else{
					model.addAttribute("visitSite", CustomUtil.strNull(params.get("thisGroupId"), Long.toString(defaultGroup.getGroupId()) ));
				}
				
				String groupName = "";
				int groupCnt = 0;
				String groupId = "";

				Map<String,Long> GroupMap = new HashMap<String,Long>();
				if(!tabUseStr.equals("")){
					String []tabUseArray = tabUseStr.split(",");
					for(int i=0;i<tabUseArray.length;i++){

						Long tabUserGroupId = Long.parseLong(CustomUtil.strNull(tabUseArray[i]));
						Group group = GroupLocalServiceUtil.getGroup(tabUserGroupId);
						GroupMap.put(group.getName(), group.getGroupId());

						if(groupCnt==0){
							groupName += group.getName();
							groupId += group.getGroupId();
							groupCnt++;
						}else{
							groupName += ","+group.getName();
							groupId += ","+group.getGroupId();
						}
						
						if(!visitSite.equals("")&&visitSite.equals(group.getName())){
							model.addAttribute("visitSite", Long.toString(group.getGroupId()));
						}
					}
				}
				model.addAttribute("tabNames", groupName);
				model.addAttribute("tabsValues", groupId);

				net.sf.json.JSONObject json = new net.sf.json.JSONObject();
				json.putAll(GroupMap);
				model.addAttribute("groupMap", json.toString());
				model.addAttribute("parentGroupId", parentGroupId);

			}else{
				//상세 사이트 일경우 Tab Not View
				model.addAttribute("visitSite", PortalUtil.getScopeGroupId(request));
				model.addAttribute("parentGroupId", parentGroupId);
			}

			if(thisGroup.getParentGroupId() != 0){//Portal
				isPortalMain = false;
			} else {
				isPortalMain = true;
			}

			visitSite = Long.toString(PortalUtil.getScopeGroupId(request));

			model.addAttribute("isPortalMain", isPortalMain);
			long popupPlid = PortalUtil.getPlidFromPortletId(Long.parseLong(visitSite), "edisonmyfile_WAR_edisonsimulationportlet");

			//Preprocessor File Popup
			PortletURL preprocessorPopupRenderURL = PortletURLFactoryUtil.create(request,"edisonmyfile_WAR_edisonsimulationportlet", popupPlid, PortletRequest.RENDER_PHASE);
			preprocessorPopupRenderURL.setWindowState(LiferayWindowState.POP_UP);
			preprocessorPopupRenderURL.setParameter("tabs1", "1917001");

			long monitoringPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false, "edisonmonitoring_WAR_edisonsimulationportlet");

			model.addAttribute("tabViewYn",tabViewYn);
			model.addAttribute("tabUseStr",tabUseStr);
			model.addAttribute("monitoringPlid", monitoringPlid);
			model.addAttribute("preprocessorPopupRenderURL", preprocessorPopupRenderURL);
			model.addAttribute("preprocessorPortletNamespace", PortalUtil.getPortletNamespace("edisonmyfile_WAR_edisonsimulation2016portlet"));
			
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();

			//Session Error Message
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		return "simulation";
	}
	
	@ResourceMapping(value ="simulationSharing" )
	public void simulationSharing(ResourceRequest request, ResourceResponse response) throws IOException, NumberFormatException, PortalException, SystemException, ParseException{
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		
		int customId = Integer.parseInt(CustomUtil.strNull(params.get("customId"), "0"));
		int classId = Integer.parseInt(CustomUtil.strNull(params.get("classId"), "0"));
		
		String simulationUuid = CustomUtil.strNull(params.get("simulationUuid"));
		String simulationJobUuid = CustomUtil.strNull(params.get("simulationJobUuid"));
		int simulationJobSeqNo = Integer.parseInt(CustomUtil.strNull(params.get("simulationJobSeqNo"), "0"));
		
		// 공유 기능
		boolean shareResult =  SimulationShareLocalServiceUtil.sharingSimulationJob(classId, customId, simulationJobSeqNo, simulationJobUuid, simulationUuid);
		
		JSONObject obj = new JSONObject();
		// success 결과 전송
		obj.put("shareResult", shareResult);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value ="getScienceApp" )
	public void getScienceApp(ResourceRequest request, ResourceResponse response) throws IOException, NumberFormatException, PortalException, SystemException, ParseException{
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		long scienceAppId = ParamUtil.get(request, "scienceAppId", 0L);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);

		Map<String, Object> scienceApp = ScienceAppLocalServiceUtil.getScienceAppReturnObject(scienceAppId, themeDisplay.getLocale());

		JSONObject obj = new JSONObject();

		obj.put("scienceApp", scienceApp);
		obj.put("language", themeDisplay.getLanguageId());

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}

	@SuppressWarnings("serial")
    @ResourceMapping(value ="searchList" )
	public void searchList(ResourceRequest request, ResourceResponse response) throws IOException, NumberFormatException, PortalException, SystemException{
		Map<String, Object> params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		long companyGroupId = themeDisplay.getCompany().getGroupId();

		int curPage = Integer.parseInt(CustomUtil.strNull(params.get("curPage"), "1"));
		int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("linePerPage"), "6"));
		final String searchText = CustomUtil.strNull(params.get("searchText"));

		int pagePerBlock = 5;

		String[] appTypes = {ScienceAppConstants.APP_TYPE_SOLVER};
		
		int totalCnt = ScienceAppLocalServiceUtil.countListScienceApp(
			companyGroupId, themeDisplay.getLocale(), 0, appTypes, null, 
			new HashMap<String, Object>(){{
				put("searchType", "SWNM");
				put("searchText", searchText);
			}}, "1901004", true);
		
		List<Map<String, Object>> scienceAppList =
			ScienceAppLocalServiceUtil.retrieveListScienceApp(
				companyGroupId, themeDisplay.getLocale(), 0, appTypes, null,
				new HashMap<String, Object>(){{
					put("searchType", "SWNM");
					put("searchText", searchText);
				}}, "1901004", (curPage - 1) * linePerPage, linePerPage, true);

		String pagingStr = PagingUtil.getPaging(
			request.getContextPath(), "dataSearchList", totalCnt, curPage, linePerPage, pagePerBlock);

		JSONObject obj = new JSONObject();

		obj.put("totalCnt", totalCnt);
		obj.put("scienceAppList", scienceAppList);
		obj.put("pagingStr", pagingStr);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}

	@ResourceMapping(value="edisonFileDownload")
	public void edisonFileDownload(ResourceRequest request, ResourceResponse response) throws SystemException, JSONException, IOException, PortalException, ParseException, PortletModeException{

		Map paramsMap = RequestUtil.getParameterMap(request);
		long fileEntryId = Long.parseLong(CustomUtil.strNull(paramsMap.get("fileEntryId")));

		EdisonFileUtil.edisonFileDownload(response, fileEntryId);
	}

	//탭 이벤트 저장
	@ResourceMapping(value ="cickTab")
	public void fileList(ResourceRequest request, ResourceResponse response){
		try{
			Map param = RequestUtil.getParameterMap(request);
			long groupId = ParamUtil.getLong(request, "groupId",0);
			if(groupId!=0){
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				themeDisplay.getUser().getExpandoBridge().setAttribute(EdisonExpando.USER_VISIT_SITE,group.getName());
			}
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
	}

	@ResourceMapping(value="getScienceAppDetailView")
	public void getScienceAppDetailView(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, JSONException, MalformedURLException, IOException{
			Map params = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long scienceAppId = GetterUtil.get(params.get("scienceAppId"), 0L);

			String scienceApp_description = "";
			String resultMsg = "FAILED";

			ScienceApp scienceApp = null;
			try{
				scienceApp = ScienceAppLocalServiceUtil.fetchScienceApp(scienceAppId);
				long descriptionId = GetterUtil.getLong(scienceApp.getDescriptionId(), 0L);
				if(descriptionId > 0) {
					ScienceAppDescription description = ScienceAppDescriptionLocalServiceUtil.getScienceAppDescription(descriptionId);
					scienceApp_description = description.getContentMap().get(themeDisplay.getLocale());
				}

				resultMsg = "SUCCESS";
			}catch(Exception e){
				e.printStackTrace();
				resultMsg = "FAILED";
			}

			JSONObject obj = new JSONObject();

			obj.put("description", scienceApp_description);
			obj.put("scienceAppMap", scienceApp.getModelAttributes());
			obj.put("resultMsg", resultMsg);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
	}

	@ResourceMapping(value="getClickScienceApp")
	public void getClickScienceApp(ResourceRequest request, ResourceResponse response) throws PortalException, SystemException, JSONException, MalformedURLException, IOException{
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long scienceAppId = GetterUtil.get(params.get("scienceAppId"), 0L);
		long plid = themeDisplay.getPlid();
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		String resultMsg = "SUCCESS";
		Map<String, Object> scienceAppMap = null;
		String inputPorts = "";
		List<List<Map<String, Object>>> portMapResultList = new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> portMapList = null;

		try{
			ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
			scienceAppMap = scienceApp.getModelAttributes();
			scienceAppMap.put("currentTitle", scienceApp.getTitle(themeDisplay.getLocale()));
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			scienceAppMap.put("createDt", transFormat.format(scienceApp.getCreateDate()));
			scienceAppMap.put("statusDt", transFormat.format(scienceApp.getStatusDate()));
			scienceAppMap.put("developers", scienceApp.getDevelopers(themeDisplay.getLocale()));
			User user = UserLocalServiceUtil.getUser(scienceApp.getUserId());
			String universityId = user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY).toString();
			scienceAppMap.put("affiliation", EdisonExpndoUtil.getCommonCdSearchFieldValue(universityId, EdisonExpando.CDNM, themeDisplay.getLocale()));

			if(scienceApp.getIconId()!=0){
				scienceAppMap.put("iconId", scienceApp.getIconId());
				DLFileEntry iconDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(scienceApp.getIconId());
				scienceAppMap.put("iconRepositoryId", iconDl.getRepositoryId());
				scienceAppMap.put("iconUuid", iconDl.getUuid());
				scienceAppMap.put("iconTitle", iconDl.getTitle());
			}


			inputPorts = ScienceAppInputPortsLocalServiceUtil.getInputPortsJsonString(scienceAppId);
			if(inputPorts != null && !inputPorts.equals("")) {
				JSONObject inputPortJson = JSONObject.fromObject(JSONSerializer.toJSON(inputPorts));
				Set<String> set = inputPortJson.keySet();
				for (String names : set) {
					JSONObject jsonPort = inputPortJson.getJSONObject(names);

					long port_type_id = GetterUtil.get(jsonPort.get("port-type-id"), 0L);
					List<Map<String,Object>> portTypeEditorLinkList = PortTypeEditorLinkLocalServiceUtil.findByPortTypeId(port_type_id, themeDisplay.getLocale());
					PortType portType = PortTypeLocalServiceUtil.fetchPortType(port_type_id);
					long sampleFilePath = 0;
					if(portType != null) {
						sampleFilePath = GetterUtil.getLong(portType.getSampleFilePath(), 0);
					}
					if(portTypeEditorLinkList != null && portTypeEditorLinkList.size() > 0) {
						portMapList = new ArrayList<Map<String, Object>>();
						for (Map<String, Object> editorDataMap : portTypeEditorLinkList) {
							Map<String,Object> portMap = new HashMap<String, Object>();
							String portName = GetterUtil.get(jsonPort.get("name"), names);
							boolean mandatory = GetterUtil.get(jsonPort.get("mandatory"), false);
							portMap.put("portName", portName);
							portMap.put("sampleFilePath", sampleFilePath);
							portMap.put("mandatory", mandatory);
							portMap.put("port_type_id", port_type_id);
							portMap.put("scienceAppId", CustomUtil.strNull(editorDataMap.get("scienceAppId")));
							portMap.put("name", CustomUtil.strNull(editorDataMap.get("name")));
							portMap.put("title", CustomUtil.strNull(editorDataMap.get("title")));
							portMap.put("token", AuthTokenUtil.getToken(httpRequest,plid, CustomUtil.strNull(editorDataMap.get("exeFileName"))));
							portMap.put("editorType", CustomUtil.strNull(editorDataMap.get("editorType")));
							portMap.put("exeFileName", CustomUtil.strNull(editorDataMap.get("exeFileName")));
							portMap.put("status", CustomUtil.strNull(editorDataMap.get("status")));
							portMap.put("plid", plid);
							portMapList.add(portMap);
						}
						portMapResultList.add(portMapList);
					} else {
						resultMsg = "FAILED";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMsg = "FAILED";
		}

		JSONObject obj = new JSONObject();

		obj.put("inputPortJson", inputPorts);
		obj.put("portMapList", portMapResultList);
		obj.put("scienceAppMap", scienceAppMap);
		obj.put("resultMsg", resultMsg);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}

	@ResourceMapping(value ="deleteJob" )
	public void deleteJob(ResourceRequest request, ResourceResponse response) throws Exception {

		Map params = RequestUtil.getParameterMap(request);
		PrintWriter writer = response.getWriter();
		User user  = (User) PortalUtil.getHttpServletRequest(request).getAttribute(WebKeys.USER);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);

		long thisGroupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"), "0"));
		String simulationUuid = CustomUtil.strNull(params.get("simulationUuid"));
		long jobSeqNo = Long.parseLong(CustomUtil.strNull(params.get("jobSeqNo"), "0"));

		try{
			SimulationJobLocalServiceUtil.deleteSimulationParameter(thisGroupId, simulationUuid, jobSeqNo);
			SimulationJobLocalServiceUtil.deleteSimulationJob(thisGroupId, simulationUuid, 0L, jobSeqNo);
		}catch (Exception e) {
			System.out.println(e.toString());
			throw e;
		}finally{

		}

		JSONObject obj = new JSONObject();
		obj.put("msg", "SUCCESS");

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.close();

	}

	@ResourceMapping(value ="submitJob" )
	public void submitJob(ResourceRequest request, ResourceResponse response) throws Exception {

		Map params = RequestUtil.getParameterMap(request);
		User user  = PortalUtil.getUser(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long thisGroupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"), "0"));
		Group thisGroup = GroupLocalServiceUtil.getGroup(thisGroupId);

		String resultMessage = "";
		String submitDt = "";
		String status = "";
		long jobStatus = 0;

		String simulationUuid = CustomUtil.strNull(params.get("simulationUuid"), "0");
		String cluster = CustomUtil.strNull(params.get("cluster"), "0"); //dev, prod : LDAP

		String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));

		String icebreakerZone = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_ZONE));
		IcebreakerVcToken tokenEntity = SimulationLocalServiceUtil.getOrCreateToken(thisGroupId, user);

		/*
		 * params 에서 simulation_title과 simulation_description 항목의 HTML 태그를 삭제
		 */
		params.put("simulation_title",  CustomUtil.strNull(params.get("simulation_title")).replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
		params.put("simulation_description",  CustomUtil.strNull(params.get("simulation_description")).replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));

		//================================ 시뮬레이션 생성==================================
		/*  시뮬레이션 생성(ICEBREAKER)에 필요한 데이터
		 * 	scienceAppId	// 웹에서 받아온 사이언스앱 아이디
			simulation_title	// 웹에서 받아온 시뮬레이션 제목
			simulation_description	// 웹에서 받아온 시뮬레이션 설명
		 */
		 // 리턴값
		 //	uuid	//	IceBreaker에서 받은 UUID
		 //	cluster //  IceBreaker에서 받은 cluster
		/*  시뮬레이션 생성(DATABASE)에 필요한 데이터
		 * 	groupId	// 현재 분야
			simulationUuid	// IceBreaker에서 받아온 시뮬레이션 UUID
			cluster			// IceBreaker에서 받아온 클러스터명
			userId	// 현재 등록자 userId
			simulation_title	// 웹에서 받아온 시뮬레이션 제목
			simulation_description	// 웹에서 받아온 시뮬레이션 설명
			scienceAppId	// 웹에서 받아온 사이언스앱 아이디
			scienceApp_name	// 웹에서 받아온 사이언스앱 이름
		 */
		//==================================================================================
		String uuid = "";
		String jobUuid = "";

		//------- InputDeck 정보 저장용 ------------
		boolean isInputDeck = false;
		String inputDeckName = "";
		String testYn = ParamUtil.get(request, "testYn", "N");
		params.put("testYn", testYn);

		Map<String, Object> resultMap = null;

		// 시뮬레이션 uuid가 없을때만 실행

		if(tokenEntity.getVcToken() != null && !tokenEntity.getVcToken().equals("")){
			if(simulationUuid != null && simulationUuid.equals("0")) {
				params.put("Token", tokenEntity.getVcToken());
//				resultMap = SimulationLocalServiceUtil.createSimulation(
//																		icebreakerUrl,
//																		icebreakerZone,
//																		tokenEntity.getVcToken(),
//																		CustomUtil.strNull(params.get("scienceAppId")),
//																		CustomUtil.strNull(params.get("simulation_title")),
//																		CustomUtil.strNull(params.get("simulation_description"))
//																		);

				if(resultMap != null){
					uuid = CustomUtil.strNull(resultMap.get("uuid"));
					cluster = CustomUtil.strNull(resultMap.get("cluster"));

					if(!uuid.equals("")){
						params.put("groupId",thisGroupId);
						params.put("simulationUuid", uuid);
						params.put("cluster",cluster);
						params.put("user_screenName",user.getScreenName());
						params.put("userId",user.getUserId());

						try{
//							Simulation simulationObj =  SimulationLocalServiceUtil.addSimulation(params);
//							if(simulationObj != null) {
//								simulationUuid = simulationObj.getSimulationUuid();
//							} else {
//								resultMessage = "Add simulation error in Portal [simulationCreate-01]";
//							}
						}catch (Exception e) {
							log.debug(e.toString());
							simulationUuid = "";
							resultMessage = "Add simulation error in Portal [simulationCreate-01]";
						}
					}else{
						resultMessage = "Get simulation uuid error in computational resources [simulationCreate-02]";
					}
				}else{
					resultMessage = "Create simulation error in computational resources [simulationCreate-03]";
				}
			} else {
				uuid = simulationUuid;
			}
			// 여기까지 완료 되었다면 시뮬레이션이 생성된것!!!

			// ============================= 작업 생성 시작 ======================

			params.put("jobPhase", "1301001");

			// ============================= 작업 실행 라이브러리 받아오기 =================
			StringBuffer code_library = new StringBuffer();	// 실행 라이브러리

			code_library.append("<dependencies>");
			code_library.append("</dependencies>");

//				================================= 작업 실행 라이브러리 받아오기 끝 =================

//				================================= 실행 정보 받아오기 (MPI)===============================
			String code_mpi_type = CustomUtil.strNull(params.get("code_mpi_type"));
			String code_mpi_module = "";
			String code_mpi_number = "1";											// Sequential 이면 1
			if(code_mpi_type.equals(ScienceAppConstants.APP_RUNTYPE_PARALLEL)) {	// Parallel 이면
				code_mpi_module = CustomUtil.strNull(params.get("code_mpi_module"));
				code_mpi_number = CustomUtil.strNull(params.get("code_mpi_number"));
			}

			StringBuffer execution_mpi_attributes = new StringBuffer();
			execution_mpi_attributes.append(" <attributes>");
			execution_mpi_attributes.append(" 		<item key=\"np\" value=\""+code_mpi_number+"\"/> ");
			execution_mpi_attributes.append(" </attributes>");
//				================================= 실행 정보 받아오기 끝 =================================

//				================================= 포트 가져오기 =============================

			//디렉토리 생성
			File icebreakerTempFolder = new File(ICEBREAKER_TEMP_PATH);
			//해당 디렉토리의 존재여부를 확인
			if(!icebreakerTempFolder.exists()){
				icebreakerTempFolder.mkdirs();//없다면 생성
			}

			StringBuffer fileItemsStr = new StringBuffer(); // 파일 데이터
			Map<String,String> executionMap = new HashMap<String,String>(); // 실행 데이터맵
			fileItemsStr.append("	<files>");

			String executionStr = "";

			// 명령어 정렬
			String executionStrOrder = "";
			String inputPortJson = GetterUtil.get(params.get("inputPortJson"), "");
			JSONObject inputPortJsonObj = null;

			if(inputPortJson != null && !inputPortJson.equals("")) {
				inputPortJsonObj = JSONObject.fromObject(JSONSerializer.toJSON(inputPortJson));
				Set<String> set = inputPortJsonObj.keySet();
				for (String portNameStr : set) {
					String portData = GetterUtil.getString(params.get(portNameStr + "_DATA"), "");

					//GerrerUtil 에서 String trim 을 통하여 맨 마지막 줄의 \n 을 제거 하기 때문에 붙여줌.
					String portFile = GetterUtil.getString(params.get(portNameStr + "_FILE"), "")+"\n";
					String portDataType = GetterUtil.getString(params.get(portNameStr + "_TYPE"), "");
					if(!portData.equals("")) {
						if(portDataType.equals("String")) {
							executionMap.put(StringUtil.trim(portNameStr), portNameStr + " " + portData + " ");
						} else if (portDataType.equals("Inputdeck")) {
							isInputDeck = true;
							inputDeckName = portNameStr;

							String folderName = "/" + user.getScreenName()+Calendar.getInstance().getTimeInMillis()+"ID";
							File inputdeckTempFolder = new File(ICEBREAKER_TEMP_PATH + folderName);
							//해당 디렉토리의 존재여부를 확인
							if(!inputdeckTempFolder.exists()){
								inputdeckTempFolder.mkdirs();//없다면 생성
							}

//							String fileName = "i.Ipd";
							String fileName = inputDeckName.replaceAll("-", "")+UUID.randomUUID().toString().substring(0, 7);
							File inputDeckTempFile =  EdisonFileUtil.createCustomFile(ICEBREAKER_TEMP_PATH + folderName, fileName, portFile);

							log.debug("##############################################################################################################");
							log.debug("ICEBREAKER_TEMP_PATH : "+ICEBREAKER_TEMP_PATH);
							log.debug("fileName : "+fileName);
							log.debug("fileBufferStr : "+portFile);
							log.debug("##############################################################################################################");

							//api를 통한input.cmd 파일업로드
							resultMap = SimulationLocalServiceUtil.uploadFile(
																				cluster,
																				icebreakerUrl,
																				tokenEntity.getVcToken(),
																				inputDeckTempFile
																			);

							log.debug("############################################RETURN_INPUT_DECK_FILE###########################################");
							log.debug("INPUTDECK_FILE_ID : "+CustomUtil.strNull(resultMap.get("fileId")));
							log.debug("INPUTDECK_FILE_PATH : "+CustomUtil.strNull(resultMap.get("filePath")));
							log.debug("INPUTDECK_FILE_SIZE : "+CustomUtil.strNull(resultMap.get("fileSize")));
							log.debug("##############################################################################################################");

							if(inputDeckTempFile.exists()){
								inputDeckTempFile.delete();//Temp File Delete
								inputdeckTempFolder.delete();//Temp Folder Delete
							}

							if(!CustomUtil.strNull(resultMap.get("fileId")).equals("")){
								executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $inputdeck ");
								fileItemsStr.append("		<item key=\"inputdeck\" value=\""+CustomUtil.strNull(resultMap.get("fileId"))+"\"/>");
							}
						} else if(portDataType.equals("File") || portDataType.equals("Text")) { // String과 Inputdeck을 제외한 경우는 execution과 file에 넣음
							if(!portData.equals("")) {
								JSONObject jsonData = JSONObject.fromObject(JSONSerializer.toJSON(portData));
								String jsonFileId = jsonData.getString("fileId");
								if(jsonFileId.equals("SAMPLE")) {	// 샘플파일 업로드
									JSONObject portJSON = (JSONObject) inputPortJsonObj.get(portNameStr);
									long portTypeId = GetterUtil.get(portJSON.get("port-type-id"), 0L);
									PortType portType = PortTypeLocalServiceUtil.getPortType(portTypeId);
									long fileEntryId = GetterUtil.get(portType.getSampleFilePath(), 0L);

									if(fileEntryId > 0) {
										DLFileEntry dlFileEntry = DLFileEntryServiceUtil.getFileEntry(fileEntryId);
										//File file = new File(dlFileEntry.getTitle());
										//유니크한 파일 명으로 변경
										File file = new File(ICEBREAKER_TEMP_PATH+File.separator+UUID.randomUUID().toString().substring(0, 7)+"_"+dlFileEntry.getTitle());

										InputStream inputStream = dlFileEntry.getContentStream();
										try {

											OutputStream outStream = new FileOutputStream(file);
	//										// 읽어들일 버퍼크기를 메모리에 생성
											byte[] buf = new byte[1024];
											int len = 0;
	//										// 끝까지 읽어들이면서 File 객체에 내용들을 쓴다
											while ((len = inputStream.read(buf)) > 0){
												outStream.write(buf, 0, len);
											}
	//										// Stream 객체를 모두 닫는다.
											outStream.close();
											inputStream.close();

											//icebreaker fileupload
											IcebreakerVcToken icebreakerVcToken = SimulationLocalServiceUtil.getOrCreateToken(thisGroupId, user);
											Map returnFileMap = SimulationLocalServiceUtil.uploadFile(cluster,icebreakerUrl, icebreakerVcToken.getVcToken(), file);

											String fileId = CustomUtil.strNull(returnFileMap.get("fileId"));

											executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $cmd"+ portNameStr +" ");
											fileItemsStr.append("	<item key=\"cmd"+portNameStr+"\" value=\""+fileId+"\"/>");
										} catch (FileNotFoundException e) {
											throw new SystemException(e);
										} catch (IOException e) {
											throw new PortalException(e);
										} catch (ParseException e) {
											throw new PortalException(e);
										} catch (InterruptedException e) {
											throw new PortalException(e);
										} catch (JSONException e) {
											throw new PortalException(e);
										}finally{
											if(file.exists()){file.delete();}
										}
									}
								} else {
									executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $cmd"+ portNameStr +" ");
									fileItemsStr.append("	<item key=\"cmd"+portNameStr+"\" value=\""+jsonFileId+"\"/>");
								}
							}
						} else { // String과 Inputdeck을 제외한 경우는 execution과 file에 넣음
							executionMap.put(StringUtil.trim(portNameStr), portNameStr+" $cmd"+ portNameStr +" ");
							fileItemsStr.append("	<item key=\"cmd"+portNameStr+"\" value=\""+portData+"\"/>");
						}
						executionStrOrder += executionMap.get(StringUtil.trim(portNameStr));
					}
				}
			}

			//로컬에 파일 input.cmd 파일 임시생성
			fileItemsStr.append("	</files>");

			String exec_path = PrefsPropsUtil.getString(companyId, EdisonPropsUtil.SCIENCEAPP_BASE_PATH) + ScienceAppLocalServiceUtil.getScienceAppBinPath(GetterUtil.get(params.get("scienceAppId"), 0L));

			//resultMap 파일업로드 정보를 담고있는 맵에 JOB 추가 파라미터 입력
			resultMap.put("code_library", code_library.toString() );
			resultMap.put("filesItem", fileItemsStr.toString() );
			resultMap.put("executionStr", executionStrOrder);
			resultMap.put("executionType", code_mpi_type );
			resultMap.put("code_mpi_module", code_mpi_module);
			resultMap.put("execution_mpi_attributes",execution_mpi_attributes.toString() );
			resultMap.put("simulationUuid", CustomUtil.strNull(uuid));
			resultMap.put("scienceAppId", CustomUtil.strNull(params.get("scienceAppId")));
			resultMap.put("scienceAppName", CustomUtil.strNull(params.get("scienceApp_name")));
			resultMap.put("cyberLabId", " ");
			resultMap.put("classId", " ");
			resultMap.put("title", CustomUtil.strNull(params.get("simulation_title")));
			resultMap.put("description", CustomUtil.strNull(params.get("simulation_description")));
			resultMap.put("exec_path", exec_path);
			resultMap.put("cluster", CustomUtil.strNull(cluster));

			if(user != null){
				resultMap.put("Token", tokenEntity.getVcToken());
			}

			//Job submit
			long jobSeqNo = ParamUtil.get(request, "jobSeqNo", 1);

			//Create syncCallBackURL
			String syncCallBackURL ="";
			if(themeDisplay.isSecure()){
				syncCallBackURL += "https://";
			}else{
				syncCallBackURL += "http://";
			}

			String serverName = themeDisplay.getServerName();
			String virtualHostName = themeDisplay.getCompany().getVirtualHostname();

			if(serverName.equals(virtualHostName)){
				syncCallBackURL += virtualHostName;
			}else{
				syncCallBackURL += serverName+":"+themeDisplay.getServerPort();
			}
			//slee callbackURL
			//syncCallBackURL ="http://61.85.104.240:8090";

			try{
				SimulationJobPK simulationJobPK = new SimulationJobPK();
				simulationJobPK.setJobSeqNo(jobSeqNo);
				simulationJobPK.setGroupId(thisGroupId);
				simulationJobPK.setSimulationUuid(simulationUuid);

				SimulationJob simulationJob = SimulationJobLocalServiceUtil.createSimulationJob(simulationJobPK);

//				simulationJob.setTestYn(testYn);
				simulationJob.setJobStatus(1701005);
				simulationJob.setJobTitle("#"+new DecimalFormat("000").format(jobSeqNo)+" "+CustomUtil.strNull(params.get("simulation_title")));
				simulationJob.setJobExecPath(exec_path);
//				simulationJob.setJobPostProcessor("");
				simulationJob.setJobUniversityField(GetterUtil.get(user.getExpandoBridge().getAttribute(EdisonExpando.USER_UNIVERSITY), 0L));
				long jobPhase = 1301003;
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				simulationJob.setJobPhase(jobPhase);//JOB_01_003 실행

				if(user.getExpandoBridge().hasAttribute(EdisonExpando.USER_LAB_ID)){
//					simulationJob.setJobVirtualLabId(Long.parseLong(CustomUtil.strNull(themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_LAB_ID), "0")));
				}else{
//					simulationJob.setJobVirtualLabId(0L);
				}
				if(user.getExpandoBridge().hasAttribute(EdisonExpando.USER_CLASS_ID)){
//					simulationJob.setJobClassId(Long.parseLong(CustomUtil.strNull(themeDisplay.getUser().getExpandoBridge().getAttribute(EdisonExpando.USER_CLASS_ID), "0")));
				}else{
//					simulationJob.setJobClassId(0L);
				}

				simulationJob.setJobInputDeckYn(isInputDeck);
				simulationJob.setJobInputDeckName(inputDeckName);

				log.debug("==================================================================================================================");
				log.debug("Portal jobUuid Update Start, Info[(simulationUuid)/(jobUuid)/(JobPhase)]");
				log.debug("Info : (" + uuid + ") / (" + jobUuid + ") / (" + String.valueOf(jobStatus) + ")");
				log.debug("Update Time : "+new Date());
				log.debug("==================================================================================================================");

				SimulationJobLocalServiceUtil.addSimulationJob(simulationJob);

				syncCallBackURL +="/api/jsonws/edison-simulation-portlet.simulationjob/update-simulation-job";
				syncCallBackURL = HttpUtil.addParameter(syncCallBackURL, "gid", String.valueOf(thisGroupId));
				syncCallBackURL = HttpUtil.addParameter(syncCallBackURL, "simulationUuid", uuid);
				syncCallBackURL = HttpUtil.addParameter(syncCallBackURL, "jobSeqNo", jobSeqNo);
				syncCallBackURL = syncCallBackURL.replaceAll("&", "%26");
				resultMap.put("syncCallBackURL", syncCallBackURL);

				Map resultJobMap = SimulationLocalServiceUtil.executeJob(icebreakerUrl, resultMap);

				if(resultJobMap != null) {
					jobUuid = CustomUtil.strNull(resultJobMap.get("uuid"));
					Date submittedTime = transFormat.parse(CustomUtil.strNull(resultJobMap.get("submittedTime")));

					SimulationJob submitJob = SimulationJobLocalServiceUtil.getSimulationJob(simulationJobPK);

					submitDt = transFormat.format(submittedTime);
					submitJob.setJobSubmitDt(submittedTime);
					submitJob.setJobUuid(jobUuid);
					jobStatus = submitJob.getJobStatus();
					SimulationJobLocalServiceUtil.updateSimulationJob(submitJob);
					status = EdisonExpndoUtil.getCommonCdSearchFieldValue(jobStatus, EdisonExpando.CDNM, themeDisplay.getLocale());
					// ==================================== ReRun을 위한 데이터 백업 =======================================
					SimulationJobData simulationJobData = SimulationJobDataLocalServiceUtil.createSimulationJobData(jobUuid);

					JSONObject jobDataJSON = new JSONObject();

					jobDataJSON.put("code_mpi_number", code_mpi_number);
					jobDataJSON.put("simulation_title", CustomUtil.strNull(params.get("simulation_title")));
					jobDataJSON.put("simulation_description", CustomUtil.strNull(params.get("simulation_description")));

					if(inputPortJsonObj != null) {
						Iterator<String> itr = inputPortJsonObj.keys();
						while (itr.hasNext()) {
							String portNameStr = itr.next();
							String portData = CustomUtil.strNull(params.get(portNameStr + "_DATA"), "");
							String portType = CustomUtil.strNull(params.get(portNameStr + "_TYPE"), "");
							jobDataJSON.put(portNameStr + "_type", portType);
							if(portType.equals("Inputdeck") && !portData.equals("")) {
								JSONArray jobJSON = JSONArray.fromObject(portData);
								jobDataJSON.put(portNameStr, jobJSON);
							} else {
								jobDataJSON.put(portNameStr, portData);
							}
						}
					}
					simulationJobData.setJobData(jobDataJSON.toString());

					SimulationJobDataLocalServiceUtil.updateSimulationJobData(simulationJobData);
					// =====================================================================================================
					resultMessage = "SUCCESS";
				} else {
					resultMessage = "PORTAL JOB UPDATE FAILED";
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.debug("JOB SUBMIT Error : " + e.toString());
				resultMessage = "PORTAL JOB UPDATE FAILED";
			}
		}

		JSONObject obj = new JSONObject();
		obj.put("message",	resultMessage);
		obj.put("jobSeqNo", ParamUtil.get(request, "jobSeqNo", 1));
		obj.put("jobUuid", jobUuid);
		obj.put("simulationUuid", uuid);
		obj.put("status", status);
		obj.put("submitDt", submitDt);
		obj.put("cluster", cluster);
		obj.put("jobStatus", jobStatus);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}

	@ResourceMapping(value ="createPortDataSession" )
	public void setPortDataSession(ResourceRequest request, ResourceResponse response) throws IOException, NumberFormatException, PortalException, SystemException, ParseException{

		String dialogId = ParamUtil.get(request, "dialogId", "");
		String portData = ParamUtil.get(request, "portData", "");

		//portlet session set parameter
		request.getPortletSession().setAttribute(dialogId, portData,PortletSession.APPLICATION_SCOPE);
	}

	@ResourceMapping(value="searchSimulationJobData")
	public void searchSimulationJobData(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, JSONException{
		Map params = RequestUtil.getParameterMap(request);
		String jobUuid = CustomUtil.strNull(params.get("jobUuid"));
		String jobData = SimulationJobDataLocalServiceUtil.fetchSimulationJobData(jobUuid).getJobData();

		com.liferay.portal.kernel.json.JSONObject data = JSONFactoryUtil.createJSONObject(jobData);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(data.toString());
	}

	// ADD
	@ResourceMapping(value ="solverTypeList" )
	public void solverTypeList(ResourceRequest request, ResourceResponse response) throws IOException{
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long globalGroupId = themeDisplay.getCompany().getGroupId();
			long companyId = themeDisplay.getCompanyId();
			
			List<Long> categoryIds = new ArrayList<Long>();
			
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, EdisonAssetCategory.GLOBAL_DOMAIN);
			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
			
			List<Map> solverTypeList =  new ArrayList<Map>();
			
			for(AssetCategory aCategory : aCategoryList){
				Map categoryMap = new HashMap();
				if(aCategory.getVocabularyId()== assetVocabulary.getVocabularyId() && aCategory.getParentCategoryId() != 0){
					categoryMap.put("categoryId", aCategory.getCategoryId());
					categoryMap.put("title", aCategory.getTitle(themeDisplay.getLanguageId()));
					categoryMap.put("companyId", aCategory.getCompanyId());
					categoryMap.put("parentCategoryId", aCategory.getParentCategoryId());
					categoryMap.put("userId", aCategory.getUserId());
					categoryMap.put("userName", aCategory.getUserName());

					try{
						AssetCategoryProperty categoryProperty = AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(aCategory.getCategoryId(), "IMAGE");
						categoryMap.put("image", categoryProperty);
					}catch(NoSuchCategoryPropertyException e){
						
					}
					
					solverTypeList.add(categoryMap);
				}
			}
			
			for(int i=0; i<aCategoryList.size(); i++){
				categoryIds.add(i, aCategoryList.get(i).getCategoryId());
			}
			
			int count = SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectCount("", categoryIds, themeDisplay.getLocale());
			List<Map<String, Object>> projectList = SimulationProjectLocalServiceUtil
					.getCustomIntegratedSearchSimulationProjectList(0, 5,"", themeDisplay.getLocale(), categoryIds);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			com.liferay.portal.kernel.json.JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(solverTypeList);
			String projectListJson = jsonSerializer.serialize(projectList);
			com.liferay.portal.kernel.json.JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			com.liferay.portal.kernel.json.JSONArray projectArr = JSONFactoryUtil.createJSONArray(projectListJson);
			jsonObj.put("solverTypeList", optionArr);
			jsonObj.put("projectList", projectArr);
			jsonObj.put("projectListCnt", count);
			writer.write(jsonObj.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@ResourceMapping(value ="projectSearch" ) //하위사이트 groupId로 각 리스트 가져오기
	public void projectSearch(ResourceRequest request, ResourceResponse response, @RequestParam(value="categoryId") String categoryIdParam) throws IOException{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (WebKeys.THEME_DISPLAY);
		List solverIconList = new ArrayList();
		
		try {
			Map<String, Object> params = RequestUtil.getParameterMap(request);
			
			params.put("languageId", themeDisplay.getLocale().toString());
			
			long groupId = Long.parseLong(CustomUtil.strNull(params.get("groupId"),String.valueOf(PortalUtil.getScopeGroupId(request))));
			long userId = PortalUtil.getUserId(request);
			long globalGroupId = themeDisplay.getCompany().getGroupId();
			
			List<Long> categoryIds = new ArrayList<Long>();
			long categoryId = Long.parseLong(CustomUtil.strNull(params.get("categoryId"),"0"));
			if(categoryId == 0){
				AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, EdisonAssetCategory.GLOBAL_DOMAIN);
				AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
				List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry.getEntryId());
				for(int i=0; i<aCategoryList.size(); i++){
					categoryIds.add(aCategoryList.get(i).getCategoryId());
				}
			} else {
				categoryIds.add(categoryId);
			}
			
			String searchText = CustomUtil.strNull(params.get("searchText"),"");
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();

			int curPage = Integer.parseInt(CustomUtil.strNull(params.get("p_curPage"), "1"));
			int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("linePerPage"), "10"));
			int pagePerBlock = 5;
			
			params.put("groupId", groupId);
			params.put("status", "1901004");
			
			long companyGroupId = themeDisplay.getCompany().getGroupId();
			
			String categoryValue = CustomUtil.strNull(params.get("categoryId"), "");
			
			if(!categoryValue.equals("")){
				categoryId = Long.valueOf(categoryValue);
			}
			
			int begin = (curPage - 1) * linePerPage;
			int end = linePerPage;
			
			int solverCount = SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectCount(searchText, categoryIds, themeDisplay.getLocale());
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"dataSearchList", solverCount, curPage, linePerPage, pagePerBlock);
			
			List<Map<String, Object>> projects = SimulationProjectLocalServiceUtil
					.getCustomIntegratedSearchSimulationProjectList(begin, end, searchText, themeDisplay.getLocale(), categoryIds);
			
			int totalCnt = solverCount;
			
			writer = response.getWriter();
			
			com.liferay.portal.kernel.json.JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			com.liferay.portal.kernel.json.JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			
			String optionJson = jsonSerializer.serialize(projects);
			com.liferay.portal.kernel.json.JSONArray optionArr = JSONFactoryUtil.createJSONArray(optionJson);
			jsonObj.put("dataList", optionArr);
			jsonObj.put("pageList", pagingStr);
			jsonObj.put("select_line", linePerPage);
			jsonObj.put("totalCnt", totalCnt);
			writer.write(jsonObj.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
