package kr.re.edison.hook.saml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.UserLocalServiceUtil;

public class SAMLAutoLogin implements AutoLogin {

	private static Log _log = LogFactoryUtil.getLog(SAMLAutoLogin.class);

	@Override
	public String[] handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws AutoLoginException {
		if (Validator.isNull(request.getAttribute(AutoLogin.AUTO_LOGIN_REDIRECT))) {
			throw new AutoLoginException(e);
		}
		_log.error(e, e);
		return null;
	}

	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
		String[] credentials = null;

		try {
			HttpSession session = request.getSession();

			Long userId = (Long) session.getAttribute(Constants.SAML_EXIST_USER_TAG);

			if (userId == null) {
				return credentials;
			}

			session.removeAttribute(Constants.SAML_EXIST_USER_TAG);

			User user = UserLocalServiceUtil.getUserById(userId);

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.TRUE.toString();
		} catch (Exception e) {
		}

		return credentials;
	}
}
