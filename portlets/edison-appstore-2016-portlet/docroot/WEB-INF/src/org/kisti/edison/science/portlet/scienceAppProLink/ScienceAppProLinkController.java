package org.kisti.edison.science.portlet.scienceAppProLink;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.science.customService.AssetEntryCustomService;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class ScienceAppProLinkController {

	private static Log log = LogFactoryUtil.getLog(ScienceAppProLinkController.class);
	
	/**
	 * 추천 프로젝트 기본 보기
	 * @param sourceEntryId 사이언스앱 엔트리 아이디
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model) throws PortalException, SystemException {
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		long sourceEntryId = 0;
		
		AssetEntry customModelAssetEntry = null;
		sourceEntryId = ParamUtil.getLong(request, "entryId", 0);
		if(sourceEntryId != 0){
			customModelAssetEntry = AssetEntryLocalServiceUtil.getAssetEntry(sourceEntryId);
		}
		
		Boolean isMgrBtn = false;
        isMgrBtn = ParamUtil.getBoolean(request, "isMgrBtn", false);
		
        //링크된 프로젝트 리스트를 가지고 와서 프로젝트 정보를 가지고 와야함 (사진 정보 까지)
		List<Map<String, Object>> selectSimulationProjectList = new ArrayList<Map<String, Object>>();
		selectSimulationProjectList = AssetEntryCustomService.retrieveCustomListLinkedAssetEntry(LinktCustomModelConstants.SIMULATION_PROJECT, themeDisplay.getLocale(), sourceEntryId);
		
		model.addAttribute("selectSimulationProjectList", selectSimulationProjectList);
		model.addAttribute("sourceEntryId", sourceEntryId);
		model.addAttribute("customModelAssetEntry", customModelAssetEntry);
		model.addAttribute("isMgrBtn", isMgrBtn);
		
		String redirectURL = ParamUtil.getString(request, "redirectURL", "");
		String redirectName = ParamUtil.getString(request, "redirectName", "");
        model.addAttribute("redirectName", redirectName);
		model.addAttribute("redirectURL", redirectURL);
		
		return "scienceAppProLink/list"; 
	}

	@RenderMapping(params = "myRender=scienceAppProLinkViewDialogue")
	public String assetLinkViewDialogue(RenderRequest request, ModelMap model){
		Map params = RequestUtil.getParameterMap(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		// tab 생성
		try{
			long sourceEntryId = ParamUtil.getLong(request, "sourceEntryId");
			String sourceClassName = ParamUtil.getString(request, "sourceClassName");
			long sourceClassPK = ParamUtil.getLong(request, "sourceClassPK");
			
			String tabsValues = LinktCustomModelConstants.SIMULATION_PROJECT;
			String tabNames = LanguageUtil.get(themeDisplay.getLocale(), LinktCustomModelConstants.SIMULATION_PROJECT);
			
			model.addAttribute("sourceEntryId", sourceEntryId);
			model.addAttribute("sourceClassName", sourceClassName);
			model.addAttribute("sourceClassPK", sourceClassPK);
			model.addAttribute("tabNames", tabNames);
			model.addAttribute("tabsValues", tabsValues);

			model.addAttribute("PROJECT", LinktCustomModelConstants.SIMULATION_PROJECT);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "scienceAppProLink/scienceAppProLinkView";
	}
	
	@ResourceMapping(value = "retrieveListLinkedAssetEntry")
	public void getListLinkedProject(ResourceRequest request, ResourceResponse response){
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
				if(pagingYn){
					int currentPage = ParamUtil.get(request, "currentPage", 1);
					int blockSize = 10;
					listSize = ParamUtil.get(request, "searchLine", 3);
					start = ((currentPage - 1) * listSize);

					entryParams.put("start", start);
					entryParams.put("end", listSize);

					int totalCnt = AssetEntryCustomService.retrieveCountLinkedAssetEntry(themeDisplay.getLocale(),
						className, sourceEntryId);

					String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
					String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace
						+ "getSelectedModelDataListPaging", totalCnt, currentPage, listSize, blockSize);

					jsonObj.put("paging", paging);
				}

				assetEntryLinkList = AssetEntryCustomService.retrieveListLinkedAssetEntry(className, themeDisplay
					.getLocale(), sourceEntryId, pagingYn, start, listSize);

			}
			
			String linkJson = jsonSerializer.serialize(assetEntryLinkList);
			
			Map<String, Object> portletInfo = AssetEntryCustomService.getMapClassModelPortletInfo(className,
					themeDisplay.getScopeGroupId());
				jsonObj.put("portletInfo", jsonSerializer.serialize(portletInfo));

			jsonObj.put("dataLinkList", JSONFactoryUtil.createJSONArray(linkJson));
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
				AssetEntryCustomService.updateAssetEntryLink(themeDisplay.getUserId(), checkYn, sourceEntryId,
					targetEntry.getEntryId());
			}
			out.write(jsonObj.toString());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
