package org.kisti.edison.util;

import com.liferay.portal.kernel.util.HttpUtil;

public final class EdisonHttpUtil {
	
	public static String removeAndencodeURL(String currentURL){
		String returnURL = currentURL;
		
		returnURL = HttpUtil.removeParameter(returnURL, "p_p_lifecycle");
		returnURL = HttpUtil.removeParameter(returnURL, "p_p_state");
		returnURL = HttpUtil.removeParameter(returnURL, "p_p_mode");
		returnURL = HttpUtil.removeParameter(returnURL, "p_p_col_id");
		returnURL = HttpUtil.removeParameter(returnURL, "p_p_col_count");
		
		return HttpUtil.encodeURL(returnURL);
	}
}
