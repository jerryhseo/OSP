package org.kisti.edison.asset.portlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.asset.service.AssetEntryCustomService;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class RelateAssetController{

	private static Log _log = LogFactoryUtil.getLog(RelateAssetController.class);

	@RequestMapping // default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){

		String jspPage = "asset/assetPageList";
		String siteYn = request.getPreferences().getValue("siteYn", "");
		try{
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			model.addAttribute("COMMUNITY", AssetCustomModelConstants.COMMUNITY);
			model.addAttribute("SCIENCE_APP", AssetCustomModelConstants.SCIENCE_APP);
			model.addAttribute("CONTENT", AssetCustomModelConstants.SCIENCE_INFORMATION);
			model.addAttribute("OPEN_DATA", AssetCustomModelConstants.OPEN_DATA);
			model.addAttribute("PROJECT", AssetCustomModelConstants.SIMULATION_PROJECT);

			// 유저에 따라 관리버튼 제공 여부
			Boolean isMgrBtn = false;
			long sourceEntryId = 0;
			AssetEntry customModelAssetEntry = null;

			// redirectURL encode
			String redirectURL = ParamUtil.getString(request, "redirectURL", "");
			String redirectName = ParamUtil.getString(request, "redirectName");
			
			if(!siteYn.equals("Y")){ // entryId 받음
				sourceEntryId = ParamUtil.getLong(request, "entryId", 0);
				if(sourceEntryId != 0){
					customModelAssetEntry = AssetEntryLocalServiceUtil.getAssetEntry(sourceEntryId);
				}

				isMgrBtn = ParamUtil.getBoolean(request, "isMgrBtn", false);
				// 페이지에 따른 디자인 변경
				Boolean isVirTitle = ParamUtil.getBoolean(request, "isVirTitle", false);
				model.addAttribute("isVirTitle", isVirTitle);

				Boolean isAppstore = ParamUtil.getBoolean(request, "isAppstore", false);
				model.addAttribute("isAppstore", isAppstore);
			}else{ // 사이트의 entryId
				jspPage = "asset/assetSiteList";

				long groupId = PortalUtil.getScopeGroupId(request);
				long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
				
				if(themeDisplay.isSignedIn()){
	  			if(parentGroupId == 0){// 포탈
	  				// admin 만 모든 목록
	  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser())){
	  					isMgrBtn = true;
	  				}
	  			}else{
	  				// admin, site admin 모든 목록
	  				if(EdisonUserUtil.isPowerUserThan(themeDisplay.getUser()) || EdisonUserUtil.isSiteRole(themeDisplay
	  					.getUser(), themeDisplay.getScopeGroupId(), EdisonRoleConstants.SITE_ADMINISTRATOR)){
	  					isMgrBtn = true;
	  				}
	  			}
				}

				customModelAssetEntry = AssetEntryLocalServiceUtil.getEntry(Group.class.getName(), groupId);
				sourceEntryId = customModelAssetEntry.getEntryId();
				
				//사이트의 URL 생성해서 redirectURL로 보냄.
				redirectURL = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent();
				redirectName = themeDisplay.getScopeGroupName();
			}

			model.addAttribute("sourceEntryId", sourceEntryId);
			model.addAttribute("customModelAssetEntry", customModelAssetEntry);
			model.addAttribute("isMgrBtn", isMgrBtn);

			model.addAttribute("redirectName", redirectName);
			model.addAttribute("redirectURL", redirectURL);
			return jspPage;

		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@RenderMapping(params = "myaction=assetLinkViewDialogue")
	public String assetLinkViewDialogue(RenderRequest request, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		// tab 생성
		try{
			// String tabNames = "SCIENCE APP,CONTENT,INSULT";//,SCIENCE
			// DATA,SIMULATION PROJECT";
			String tabsValues = AssetCustomModelConstants.SCIENCE_APP + ","
				+ AssetCustomModelConstants.SCIENCE_INFORMATION + "," 
				+ AssetCustomModelConstants.OPEN_DATA + ","
				+ AssetCustomModelConstants.SIMULATION_PROJECT;

			String tabNames = LanguageUtil.get(themeDisplay.getLocale(), AssetCustomModelConstants.SCIENCE_APP)
				+ "," + LanguageUtil.get(themeDisplay.getLocale(), AssetCustomModelConstants.SCIENCE_INFORMATION)
				+ "," + LanguageUtil.get(themeDisplay.getLocale(), AssetCustomModelConstants.OPEN_DATA) + ","
				+ LanguageUtil.get(themeDisplay.getLocale(), AssetCustomModelConstants.SIMULATION_PROJECT);

			long sourceEntryId = ParamUtil.getLong(request, "sourceEntryId");
			String sourceClassName = ParamUtil.getString(request, "sourceClassName");
			long sourceClassPK = ParamUtil.getLong(request, "sourceClassPK");

			model.addAttribute("sourceEntryId", sourceEntryId);
			model.addAttribute("sourceClassName", sourceClassName);
			model.addAttribute("sourceClassPK", sourceClassPK);

			model.addAttribute("tabNames", tabNames);
			model.addAttribute("tabsValues", tabsValues);

			model.addAttribute("SCIENCE_APP", AssetCustomModelConstants.SCIENCE_APP);
			model.addAttribute("CONTENT", AssetCustomModelConstants.SCIENCE_INFORMATION);
			model.addAttribute("OPEN_DATA", AssetCustomModelConstants.OPEN_DATA);
			model.addAttribute("PROJECT", AssetCustomModelConstants.SIMULATION_PROJECT);

		}catch (Exception e){
			e.printStackTrace();
		}
		return "asset/assetLinkView";
	}

	@ResourceMapping(value = "retrieveListLinkedAssetEntry")
	public void retrieveListLinkedAssetEntry(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			// 해당 모델의 리스트 작성
			String className = ParamUtil.getString(request, "classModel", "");
			long sourceEntryId = ParamUtil.getLong(request, "sourceEntryId", 0);
			Boolean pagingYn = ParamUtil.getBoolean(request, "pagingYn", false);

			// link 항목 중의 classModel의 entry 조회
			Map<String, Object> entryParams = new HashMap<String, Object>();
			entryParams.put("languageId", themeDisplay.getLanguageId());
			entryParams.put("className", className);
			entryParams.put("sourceEntryId", sourceEntryId);
			entryParams.put("pagingYn", pagingYn);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

			List<Map<String, Object>> assetEntryLinkList = new ArrayList<Map<String, Object>>();

			int start = 0, listSize = 0;
			if(sourceEntryId != 0){

				int totalCnt = AssetEntryCustomService.retrieveCountLinkedAssetEntry(themeDisplay.getLocale(),
					className, sourceEntryId);
				
				if(pagingYn){
					int currentPage = ParamUtil.get(request, "currentPage", 1);
					int blockSize = 10;
					listSize = ParamUtil.get(request, "searchLine", 5);
					start = ((currentPage - 1) * listSize);

					entryParams.put("start", start);
					entryParams.put("end", listSize);

					String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
					String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace
						+ "getSelectedModelDataListPaging", totalCnt, currentPage, listSize, blockSize);

					jsonObj.put("paging", paging);
				}
				
				if(listSize == 0){
					listSize = totalCnt;
				}

				assetEntryLinkList = AssetEntryCustomService.retrieveListLinkedAssetEntry(className, themeDisplay
					.getLocale(), sourceEntryId, pagingYn, start, listSize);

			}
			String linkJson = jsonSerializer.serialize(assetEntryLinkList);

			jsonObj.put("dataLinkList", JSONFactoryUtil.createJSONArray(linkJson));

			if(className.equals(AssetCustomModelConstants.COMMUNITY)){
				String communityUrl = themeDisplay.getPortalURL() + themeDisplay.getPathFriendlyURLPublic();

				jsonObj.put("communityUrl", communityUrl);
			}
			out.write(jsonObj.toString());

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@ResourceMapping(value = "searchListCustomModel")
	public void searchListCustomModel(ResourceRequest request, ResourceResponse response){

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{
			int currentPage = ParamUtil.get(request, "currentPage", 1);
			int listSize = ParamUtil.get(request, "searchLine", 5);
			int blockSize = 10;
			int start = ((currentPage - 1) * listSize);

			// 해당 모델의 리스트 작성
			String className = ParamUtil.getString(request, "classModel");
			String searchText = ParamUtil.getString(request, "searchText", "");
			long sourceEntryId = ParamUtil.getLong(request, "sourceEntryId");

			List<Map<String, Object>> assetEntryList = AssetEntryCustomService.retrieveListCustomModel(className,
				themeDisplay.getLocale(), searchText, start, listSize);

			int totalCnt = AssetEntryCustomService.retrieveCountCustomModel(themeDisplay.getLocale(), className,
				searchText);

			String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
			String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "searchListEntry",
				totalCnt, currentPage, listSize, blockSize);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
			String entryJson = jsonSerializer.serialize(assetEntryList);

			jsonObj.put("dataList", JSONFactoryUtil.createJSONArray(entryJson));
			jsonObj.put("paging", paging);

			out.write(jsonObj.toString());

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@ResourceMapping(value = "updateRelateAssetStatus")
	public void updateRelateAssetStatus(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try{

			Boolean checkYn = ParamUtil.getBoolean(request, "checkYn");

			String classModel = ParamUtil.getString(request, "classModel");
			long sourceEntryId = ParamUtil.getLong(request, "sourceEntryId");
			long targetModelSeq = ParamUtil.getLong(request, "targetModelSeq");

			// targetModelSeq의 entryId와 sourceEntryId가 같은지 검사
			AssetEntry targetEntry = AssetEntryLocalServiceUtil.getEntry(classModel, targetModelSeq);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			if(targetEntry != null){
				if(sourceEntryId == targetEntry.getEntryId()){
					jsonObj.put("status", "fail");
				}else{
					AssetEntryCustomService.updateAssetEntryLink(themeDisplay.getUserId(), checkYn, sourceEntryId,
						targetEntry.getEntryId());
					jsonObj.put("status", "success");
				}
			}
			out.write(jsonObj.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
