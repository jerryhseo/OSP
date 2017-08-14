package org.kisti.edison.science.portlet.jmolviewer;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.IcebreakerUtil;
import org.kisti.edison.util.RequestUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class JMolViewerPortlet extends MVCPortlet 
{
	@SuppressWarnings("rawtypes")
	@Override
	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException 
	{
//		String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
//		System.err.println(portletId);
		try 
		{
			Map param = RequestUtil.getParameterMap(request);
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			
			//long groupId = Long.parseLong(CustomUtil.strNull(param.get("groupId"), String.valueOf(PortalUtil.getScopeGroupId(request))));
			long groupId = 20181;
			Group thisGroup = GroupLocalServiceUtil.getGroup(groupId);

			String fileListType = CustomUtil.strNull(param.get("fileListType"));
			String fileListValue = CustomUtil.strNull(param.get("fileListValue"));
			String jobUuid = CustomUtil.strNull(param.get("jobUuid"));
			String icebreakerUrl = CustomUtil.strNull(thisGroup.getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL));

			//JSP 에서는 화면에 icebreakerUrl을 PUBLIC URL 로 전송
			//String publicIceBreakerUrl = (String) GroupLocalServiceUtil.getGroup(groupId).getExpandoBridge().getAttribute(EdisonExpando.SITE_ICEBREAKER_URL_PUBLIC);

			//Test용
			jobUuid = "f5b64fea-b506-4097-8988-200ea2eda37c";
			
			IcebreakerVcToken icebreakerVcToken = IcebreakerUtil.getIceBreakerToken(user, groupId, thisGroup, themeDisplay);

			String dirPath = "result";
			if(fileListType.equals("FOLDER"))
				dirPath = fileListValue;
			else if(fileListType.equals("TEMP"))
				dirPath = "";
			
			String result = IcebreakerUtil.getSimulationResult(icebreakerUrl, icebreakerVcToken.getVcToken(), jobUuid, dirPath);
			if (!CustomUtil.strNull(result).equals("")) 
			{
				JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(result));
				JSONArray jsonArray = jsonObj.getJSONArray("files");
				
				request.setAttribute("icebreakerUrl",icebreakerUrl);
				request.setAttribute("resultDataList", IcebreakerUtil.sortJsonArray(jsonArray, fileListType,fileListValue,"name").toString());
			}
			
			request.setAttribute("groupId",groupId);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		super.doView(request, response);
	}
}
