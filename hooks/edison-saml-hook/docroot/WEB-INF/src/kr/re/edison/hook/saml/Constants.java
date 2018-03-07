package kr.re.edison.hook.saml;

public class Constants {
	private static final String PREFIX = "edison.hook.saml.";

	public static final String ENABLED = PREFIX + "enabled";
	public static final String HTTP_HEADER_ENABLED = PREFIX + "enabled.http.header";

	public static final String LOGOUT_URL = PREFIX + "logout.url";
	public static final String EXPANDO_EPPN = PREFIX + "expando.eppn";

	public static final String CREATE_ACCOUNT_URL = PREFIX + "create_account_url";

	public static final String SAML_FIRSTNAME = PREFIX + "value.firstname";
	public static final String SAML_EPPN = PREFIX + "value.eppn";
	public static final String SAML_EMAIL = PREFIX + "value.email";
	public static final String SAML_EXIST_USER_TAG = PREFIX + "value.exist_user";
}
