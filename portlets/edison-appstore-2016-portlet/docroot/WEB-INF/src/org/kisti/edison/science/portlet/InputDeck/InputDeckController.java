package org.kisti.edison.science.portlet.InputDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("VIEW")
public class InputDeckController {
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response,ModelMap model) throws PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		Locale[] locales = LanguageUtil.getAvailableLocales();
		List<String> localeList = new ArrayList<String>();
		String localesStr = "";
		for(Locale aLocale : locales){
			if(localesStr.equals("")){
				localesStr = LocaleUtil.toLanguageId(aLocale);
			}else{
				localesStr += ","+LocaleUtil.toLanguageId(aLocale);
			}
//			localeList.add(LocaleUtil.toLanguageId(aLocale));
		}
		model.addAttribute("siteDefaultLanguageId", LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()));
		model.addAttribute("defaultLanguageId", LocaleUtil.toLanguageId(themeDisplay.getLocale()));
//		model.addAttribute("availableLanguageIds", localeList);
		model.addAttribute("availableLanguageIds", localesStr);
		
		return "inputdeck/edit";
	}
}
