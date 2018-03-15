package kr.re.edison.hook.saml;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class Util {
	public static boolean getProperty(long companyId, String key) throws Exception {
		return GetterUtil.get(PrefsPropsUtil.getString(companyId, key), GetterUtil.getBoolean(PropsUtil.get(key)));
	}

	public static String getPropertyString(long companyId, String key) throws Exception {
		return GetterUtil.getString(PrefsPropsUtil.getString(companyId, key), PropsUtil.get(key));
	}
}
