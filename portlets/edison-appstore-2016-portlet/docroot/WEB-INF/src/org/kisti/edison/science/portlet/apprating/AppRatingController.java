package org.kisti.edison.science.portlet.apprating;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.RequiredLibConfirm;
import org.kisti.edison.science.service.RequiredLibConfirmLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonUserUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class AppRatingController {
	
	private static Log log = LogFactory.getLog(AppRatingController.class);
	
	@RequestMapping//default
	public void view(RenderRequest request, ModelMap model) {
		
		try {
			long statsId = 1069104;
			RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(statsId);
			
			long classNameId = ratingsStats.getClassNameId();
			long classPk = ratingsStats.getClassPK(); 
			String className = ratingsStats.getClassName();
			
			int totalEntries = ratingsStats.getTotalEntries();
			double totalScore = ratingsStats.getTotalScore();
			double averageScore = ratingsStats.getAverageScore();
			
			System.out.println("statsId : " + statsId);
			System.out.println("classNameId : " + classNameId);
			System.out.println("className : " + className);
			System.out.println("classPk : " + classPk);
			System.out.println("totalEntries : " + totalEntries);
			System.out.println("totalScore : " + totalScore);
			System.out.println("averageScore : " + averageScore);
			
			List<RatingsEntry> ratingsEntryList = RatingsEntryLocalServiceUtil.getEntries(className, classPk);
			
			for(RatingsEntry ratingsEntry : ratingsEntryList){
				System.out.println("ratingsEntry userName : " + ratingsEntry.getUserName());
				System.out.println("ratingsEntry classPk : " + ratingsEntry.getClassPK());
				System.out.println("ratingsEntry score : " + ratingsEntry.getScore());
			}
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
	}
	
	@ResourceMapping(value="getScienceAppRating")
	public void getScienceAppRating(ResourceRequest request, ResourceResponse response) throws SystemException, IOException, PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		Locale locale = themeDisplay.getLocale();
		
		JSONObject obj = new JSONObject();
		
		obj.put("", "");
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
	}
}
