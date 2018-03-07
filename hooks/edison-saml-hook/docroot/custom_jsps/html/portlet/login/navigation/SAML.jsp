<%@ include file="/html/portlet/login/init.jsp"%>

<%
	final String SHIBBOLETH_ENABLED = "edison.hook.saml.enabled";
	boolean shibbolethEnabled = GetterUtil.getBoolean(PrefsPropsUtil.getString(PortalUtil.getCompanyId(renderRequest), SHIBBOLETH_ENABLED));
%>

<c:if test="<%=shibbolethEnabled%>">

	<liferay-ui:icon src=""
		url="/c/portal/login/shibboleth" message="KAFE" />

</c:if>
