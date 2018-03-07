package kr.re.edison.hook.saml;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class SAMLAction extends BaseStrutsAction {
	private static Log _log = LogFactoryUtil.getLog(SAMLAction.class);

	@SuppressWarnings("deprecation")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();

		// if this module disabled, redirect signin page.
		boolean enabled = Util.getProperty(companyId, Constants.ENABLED);
		if (enabled == false) {
			String redirect = PortalUtil.getPortalURL(request) + themeDisplay.getURLSignIn();
			response.sendRedirect(redirect);
			return null;
		}

		boolean httpHeaderEnabled = Util.getProperty(companyId, Constants.HTTP_HEADER_ENABLED);

		// parse from http header
		String firstName = getHeader(Util.getPropertyString(companyId, Constants.SAML_FIRSTNAME), request, httpHeaderEnabled);
		String email = getHeader(Util.getPropertyString(companyId, Constants.SAML_EMAIL), request, httpHeaderEnabled);
		String eppn = getHeader(Util.getPropertyString(companyId, Constants.SAML_EPPN), request, httpHeaderEnabled);

		_log.info("eppn: " + eppn);

		if (Validator.isNull(eppn) || Validator.isNull(email) || Validator.isNull(firstName)) {
			return null;
		}

		if (email.contains(";")) {
			String[] emails = email.split(";");
			email = emails[0];
		}

		session.setAttribute(Constants.SAML_FIRSTNAME, firstName);
		session.setAttribute(Constants.SAML_EMAIL, email);
		session.setAttribute(Constants.SAML_EPPN, eppn);

		// Search User from Custom Field
		String eppnCustomFieldName = Util.getPropertyString(companyId, Constants.EXPANDO_EPPN);

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
		} catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		// create Custom Field if not exists
		try {
			UnicodeProperties properties = new UnicodeProperties();
			properties.setProperty("hidden", "false");
			properties.setProperty("visible-with-update-permission", "true");

			ExpandoColumn eppnExpandoColumn = ExpandoColumnLocalServiceUtil.addColumn(expandoTable.getTableId(), eppnCustomFieldName, ExpandoColumnConstants.STRING);
			ExpandoColumnLocalServiceUtil.updateTypeSettings(eppnExpandoColumn.getColumnId(), properties.toString());
		} catch (Exception e) {
		}

		// get User List
		ExpandoColumn eppnExpandoColumn = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), eppnCustomFieldName);
		List<ExpandoValue> expandoValues = ExpandoValueLocalServiceUtil.getColumnValues(eppnExpandoColumn.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, eppnCustomFieldName, eppn, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		int usersCount = expandoValues.size();

		if (usersCount == 1) {
			ExpandoValue expandoValue = expandoValues.get(0);
			long userId = expandoValue.getClassPK();
			User user = UserLocalServiceUtil.getUser(userId);
			session.setAttribute(Constants.SAML_EXIST_USER_TAG, user.getUserId());
			String redirect = themeDisplay.getURLSignIn();
			response.sendRedirect(redirect);
			return null;
		}

		if (themeDisplay.getUser().getEmailAddress().equals("default@liferay.com")) {
			String createAccountURL = Util.getPropertyString(companyId, Constants.CREATE_ACCOUNT_URL);
			if (Validator.isNull(createAccountURL) || createAccountURL == "") {
				createAccountURL = PortalUtil.getCreateAccountURL(request, themeDisplay);
			}

			session.setAttribute("saml:firstName", firstName);
			session.setAttribute("saml:email", email);
			session.setAttribute("saml:eppn", eppn);
			session.setAttribute("saml:usersCount", usersCount);

			createAccountURL = HttpUtil.setParameter(createAccountURL, "firstName", firstName);
			createAccountURL = HttpUtil.setParameter(createAccountURL, "email", email);
			createAccountURL = HttpUtil.setParameter(createAccountURL, "eppn", eppn);
			createAccountURL = HttpUtil.setParameter(createAccountURL, "usersCount", usersCount);
			response.sendRedirect(createAccountURL);

			return null;
		}

		ExpandoValueLocalServiceUtil.addValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, eppnCustomFieldName, themeDisplay.getUser().getUserId(), eppn);
		response.sendRedirect(themeDisplay.getURLHome());

		return null;
	}

	protected String getHeader(String headerName, HttpServletRequest request, boolean headersEnabled) {
		if (Validator.isNull(headerName))
			return null;

		String headerValue;

		if (headersEnabled) {
			headerValue = request.getHeader(headerName);
		} else {
			headerValue = (String) request.getAttribute(headerName);
		}

		return headerValue;
	}
}
