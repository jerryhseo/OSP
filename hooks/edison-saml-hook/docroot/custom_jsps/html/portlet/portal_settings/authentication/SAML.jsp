<%@ include file="/html/portlet/portal_settings/init.jsp"%>
<%
	final String PREFIX = "edison.hook.saml.";

	final String ENABLED = PREFIX + "enabled";
	final String HTTP_HEADER_ENABLED = PREFIX + "enabled.http.header";
	final String LOGOUT_URL = PREFIX + "logout.url";
	final String EXPANDO_EPPN = PREFIX + "expando.eppn";
	final String CREATE_ACCOUNT_URL = PREFIX + "create_account_url";
	final String SAML_FIRSTNAME = PREFIX + "value.firstname";
	final String SAML_EPPN = PREFIX + "value.eppn";
	final String SAML_EMAIL = PREFIX + "value.email";

	String enabled = PrefsPropsUtil.getString(company.getCompanyId(), ENABLED, "true");

	String enabledHttpHeader = PrefsPropsUtil.getString(company.getCompanyId(), HTTP_HEADER_ENABLED, "false");
	String logoutURL = PrefsPropsUtil.getString(company.getCompanyId(), LOGOUT_URL, "/Shibboleth.sso/Logout?return=/");
	String createAccountURL = PrefsPropsUtil.getString(company.getCompanyId(), CREATE_ACCOUNT_URL, "");
	String expando_eppn = PrefsPropsUtil.getString(company.getCompanyId(), EXPANDO_EPPN, "eppn");

	String firstName = PrefsPropsUtil.getString(company.getCompanyId(), SAML_FIRSTNAME, "displayName");
	String eppn = PrefsPropsUtil.getString(company.getCompanyId(), SAML_EPPN, "eppn");
	String email = PrefsPropsUtil.getString(company.getCompanyId(), SAML_EMAIL, "mail");
%>

<aui:fieldset>

	<aui:input label="enabled" name='<%="settings--" + ENABLED + "--"%>'
		type="checkbox" value="<%=enabled%>" />

	<h3>SAML Configuration</h3>
	<hr />

	<aui:input label="enabled HTTP Header"
		name='<%="settings--" + HTTP_HEADER_ENABLED + "--"%>' type="checkbox"
		value="<%=enabledHttpHeader%>" />

	<aui:input label="Logout URL"
		name='<%="settings--" + LOGOUT_URL + "--"%>' type="text"
		value="<%=logoutURL%>" />

	<aui:input label="EPPN Custom Field Name"
		name='<%="settings--" + EXPANDO_EPPN + "--"%>' type="text"
		value="<%=expando_eppn%>" />

	<aui:input label="Create Account URL"
		name='<%="settings--" + CREATE_ACCOUNT_URL + "--"%>' type="text"
		value="<%=createAccountURL%>" />

	<aui:input label="FirstName"
		name='<%="settings--" + SAML_FIRSTNAME + "--"%>' type="text"
		value="<%=firstName%>" />

	<aui:input label="EPPN" name='<%="settings--" + SAML_EPPN + "--"%>'
		type="text" value="<%=eppn%>" />

	<aui:input label="email" name='<%="settings--" + SAML_EMAIL + "--"%>'
		type="text" value="<%=email%>" />

</aui:fieldset>

