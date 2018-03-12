package org.kisti.edison.search.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.science.service.constants.ScienceAppConstants;
import org.kisti.edison.search.service.model.Search;
import org.kisti.edison.search.service.model.SearchCondition;
import org.kisti.edison.search.service.model.impl.SearchConditionModelImpl;
import org.kisti.edison.search.service.service.SearchConditionLocalServiceUtil;
import org.kisti.edison.search.service.service.SearchLocalServiceUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonHttpUtil;
import org.kisti.edison.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class SearchController{
    private static Log log = LogFactory.getLog(SearchController.class);

    @RequestMapping
    public String view(
        RenderRequest request, RenderResponse response, ModelMap model,
        @ModelAttribute SearchConditionModelImpl searchConditionModel)
        throws PortalException, SystemException{
        log.debug("view rendering");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyGroupId = PortalUtil.getCompany(request).getGroupId();
        long groupId = PortalUtil.getScopeGroupId(request);
        Locale locale = themeDisplay.getLocale();
        PortletPreferences prefs = request.getPreferences();
        List<Map<String, Object>> lv1Categories = SearchLocalServiceUtil.assetCategoryToJstreeModel(
            SearchLocalServiceUtil.getLv1Categories(companyGroupId, groupId, locale), locale);
        for(Map<String, Object> assetCategory : lv1Categories){
	        String imageName = prefs.getValue(String.valueOf(assetCategory.get("categoryId")), "");
	        if(StringUtils.hasText(imageName)){
	            assetCategory.put("image", imageName);
	        }
        }
        if(themeDisplay.getURLCurrent().indexOf("?") > 0){
        model.addAttribute("redirectURL",
            themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?")));
        }else{
        model.addAttribute("redirectURL", themeDisplay.getURLCurrent());
        }
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        log.debug("searchCondition : " + searchCondition.toString());
        
        model.addAttribute("lv1Categories", lv1Categories);
        model.addAttribute("categoriesJsonString",
            SearchLocalServiceUtil.getCategoriesJsonString(companyGroupId, groupId, locale));
        return "search/view";
    }
    
    @ResourceMapping(value = "getEncodedUrl")
    @ResponseBody
    public void getEncodedUrl(
        @RequestParam( value = "currentUrl", required = true) String currentUrl, 
        ResourceRequest request, ResourceResponse response, ModelMap model) throws IOException, PortalException, SystemException{
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        currentUrl += SearchConditionLocalServiceUtil.createSearchParameters(
            searchCondition, response.getNamespace(), PortalUtil.getPortletId(request));
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(EdisonHttpUtil.removeAndencodeURL(currentUrl));
        writer.flush();
        writer.close();
    }
    
    @ResourceMapping(value = "categorySearch")
    public String categorySearchView(
        @RequestParam( value = "categoryId", required = false) String categoryId, 
        ResourceRequest request, ResourceResponse response, ModelMap model){
        response.setContentType("text/html; charset=UTF-8");
        log.debug("categoryId : " + categoryId);
        String key1 = "areaScienceApp";
        log.debug(key1 + " : " + GetterUtil.getString(request.getParameter(key1), "emtpy"));
        model.addAttribute("categoryId", categoryId);
        return "search/category";
    }
    
    @ResourceMapping(value = "totalSearch")
    public String totalSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("view rendering");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = PortalUtil.getScopeGroupId(request);
        
        long appstorePlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisonscienceAppstore_WAR_edisonappstore2016portlet");
        long contentsPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisoncontent_WAR_edisoncontent2016portlet");
        long simulationProjectPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "edisonsimulationproject_WAR_edisonsimulationproject2017portlet");
        long openDataPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "datasearch_WAR_SDR_baseportlet");/*"edisondatacollection_WAR_edisonsimulationportlet"*/
        long workBenchPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "SimulationWorkbench_WAR_OSPWorkbenchportlet");
        
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        try{
            model.addAttribute("searchResults", SearchLocalServiceUtil.totalSearch(request, response, searchCondition));
        }catch (Exception e){
            throw new SystemException(e);
        }
        model.addAttribute("isSignedIn", themeDisplay.isSignedIn());
        model.addAttribute("workBenchPlid", workBenchPlid);
        model.addAttribute("appstorePlid", appstorePlid);
        model.addAttribute("contentsPlid", contentsPlid);
        model.addAttribute("simulationProjectPlid", simulationProjectPlid);
        model.addAttribute("openDataPlid", openDataPlid);
        model.addAttribute("groupId", groupId);
        model.addAttribute("downloadOnly", ScienceAppConstants.OPENLEVEL_DWN);
        return "search/total";
    }
    
    @ResourceMapping(value = "appSearch")
    public String appSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("app type search view");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long workBenchPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), false,
            "SimulationWorkbench_WAR_OSPWorkbenchportlet");
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        log.debug("Paging");
        log.debug(request.getParameter("currentPage"));
        log.debug(searchCondition.getModelAttributes().toString());
        Search searchResults = SearchLocalServiceUtil.appSearch(searchCondition);
        String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
        String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "loadApps",
            searchResults.getAppCount(), searchCondition.getCurrentPage(), searchCondition.getListSize(),
            searchCondition.getBlockSize());
        model.addAttribute("isSignedIn", themeDisplay.isSignedIn());
        model.addAttribute("workBenchPlid", workBenchPlid);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("downloadOnly", ScienceAppConstants.OPENLEVEL_DWN);
        model.addAttribute("paging", paging);
        return "search/type";
    }

    @ResourceMapping(value = "contentSearch")
    public String contentSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("content type search view");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        log.debug("Paging");
        log.debug(request.getParameter("currentPage"));
        log.debug(searchCondition.getModelAttributes().toString());
        Search searchResults = SearchLocalServiceUtil.contentSearch(searchCondition);
        String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
        String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "loadContents",
            searchResults.getContentCount(), searchCondition.getCurrentPage(), searchCondition.getListSize(),
            searchCondition.getBlockSize());
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("paging", paging);
        return "search/type";
    }

    @ResourceMapping(value = "projectSearch")
    public String projectSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("project type search view");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        log.debug("Paging");
        log.debug(request.getParameter("currentPage"));
        log.debug(searchCondition.getModelAttributes().toString());
        Search searchResults = SearchLocalServiceUtil.projectSearch(searchCondition);
        String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
        String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "loadProjects",
            searchResults.getProjectCount(), searchCondition.getCurrentPage(), searchCondition.getListSize(),
            searchCondition.getBlockSize());
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("paging", paging);
        return "search/type";
    }

    @ResourceMapping(value = "dataSearch")
    public String dataSearchView(
        ResourceRequest request, ResourceResponse response, ModelMap model)
        throws PortalException, SystemException{
        log.debug("data type search view");
        response.setContentType("text/html; charset=UTF-8");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        SearchCondition searchCondition = SearchConditionLocalServiceUtil
            .createSearchCondition(request);
        log.debug("Paging");
        log.debug(request.getParameter("currentPage"));
        log.debug(searchCondition.getModelAttributes().toString());
        //Search searchResults = SearchLocalServiceUtil.dataSearch(searchCondition);
        Search searchResults = SearchLocalServiceUtil.dataSearch(request, response, searchCondition);
        String portletNameSpace = themeDisplay.getPortletDisplay().getNamespace();
        String paging = PagingUtil.getPaging(request.getContextPath(), portletNameSpace + "loadDatas",
            searchResults.getDataCount(), searchCondition.getCurrentPage(), searchCondition.getListSize(),
            searchCondition.getBlockSize());
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("paging", paging);
        return "search/type";
    }
  
    @ResourceMapping(value = "downloadManual")
    public void downloadManual(
        @RequestParam(value="manualId") String manualId,
        ResourceRequest request, ResourceResponse response)
        throws PortalException, SystemException, IOException{
        long fileEntryId = Long.parseLong(manualId);
        EdisonFileUtil.edisonFileDownload(response, fileEntryId);
    }
  
}
