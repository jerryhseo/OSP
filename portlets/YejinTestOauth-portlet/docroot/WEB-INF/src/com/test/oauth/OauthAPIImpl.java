package com.test.oauth;

import org.scribe.builder.api.DefaultApi10a;

import com.liferay.portal.kernel.util.Validator;

public class OauthAPIImpl extends DefaultApi10a{

	@Override
	protected String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		if (Validator.isNull(_accessTokenEndpoint)) {
            _accessTokenEndpoint = OAuthUtil.buildURL(
                "oauth-portal-host", 80, "http",
                PortletPropsValues.OSB_LCS_PORTLET_OAUTH_ACCESS_TOKEN_URI);
        }
		return _accessTokenEndpoint;
	}

	@Override
	protected String getRequestTokenEndpoint() {
		// TODO Auto-generated method stub
		if (Validator.isNull(_requestTokenEndpoint)) {
            _requestTokenEndpoint = OAuthUtil.buildURL(
                "oauth-portal-host", 80, "http",
                PortletPropsValues.OSB_LCS_PORTLET_OAUTH_REQUEST_TOKEN_URI);
        }

        return _requestTokenEndpoint;
	}

	private String _accessTokenEndpoint;
    private String _requestTokenEndpoint;
}
