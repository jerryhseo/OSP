package org.kisti.edison.virtuallaboratory.portlet.virtualLabRequestList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class VirtualLabRequestListController {
    private static Log _log = LogFactoryUtil.getLog(VirtualLabRequestListController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, ModelMap model) {
		return "virtualLabRequestList/virtualLabRequestList";
	}
	
	@ResourceMapping(value="virtualLabRequestList")
	public void virtualLabRequestList(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		String searchField = ParamUtil.get(request, "search_parameter", "");
		int curPage = ParamUtil.get(request, "cur_page", 1);
		int linePerPage = ParamUtil.get(request, "select_line", 5);
		int pagePerBlock = 10;
		int begin = linePerPage * (curPage - 1);
		
		String portletNameSpace = response.getNamespace();
		
		long groupId = themeDisplay.getScopeGroupId();
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchField", searchField);
		params.put("begin", String.valueOf(begin));
		params.put("end", String.valueOf(linePerPage));
		params.put("statusSort", 1401001); // 생성 요청 코드
		
		if(parentGroupId != 0){//분야사이트
			params.put("groupId", groupId);
		}
		
		List<Map<String, Object>> virtualLabRequestList = VirtualLabLocalServiceUtil.getListVirtualLab(params, locale);
		int virtualLabCount = VirtualLabLocalServiceUtil.getCountVirtualLab(params, locale);
		
		JSONObject obj = new JSONObject();
		
		String pageList = PagingUtil.getPaging(request.getContextPath(), portletNameSpace+"dataSearchList", virtualLabCount, curPage, linePerPage, pagePerBlock);
		
		obj.put("selectLine", linePerPage);
		obj.put("curPage", curPage);
		obj.put("pageList", pageList);
		obj.put("virtualLabRequestList", virtualLabRequestList);
		obj.put("virtualLabCount", virtualLabCount);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
	
	@ResourceMapping(value="getSitePagePlid")
	public void getSitePagePlid(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
	    if(_log.isDebugEnabled()){
            _log.debug("getSitePagePlid parameters");
            for(String key : request.getParameterMap().keySet()){
                String[] values = (String[])request.getParameterMap().get(key);
                for(String value : values){
                    _log.debug(key + ": " + value);
                }
            }
        }
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = ParamUtil.get(request, "groupId", 0);
		User user = PortalUtil.getUser(request);
		
		long requestPlid = 0;
		if(EdisonUserUtil.isRegularRole(user, RoleConstants.ADMINISTRATOR) ||
			EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_ADMINISTRATOR) ||
			EdisonUserUtil.isSiteRole(user, groupId, RoleConstants.SITE_OWNER)) {
			requestPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "edisonvirtuallabrequestmanagement_WAR_edisonvirtuallab2016portlet");
		}
		
		JSONObject obj = new JSONObject();
		obj.put("requestPlid", requestPlid);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
}
