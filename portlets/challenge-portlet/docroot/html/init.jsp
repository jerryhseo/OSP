<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.template.TemplateHandler" %>
<%@ page import="com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.service.UserServiceUtil" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Locale" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>



<!-- Service Classes -->
<%@ page import="edison.challenge.service.builder.model.Challenge" %>
<%@ page import="edison.challenge.service.builder.service.ChallengeLocalServiceUtil" %>
<%@ page import="edison.challenge.service.builder.model.ChildChallenge" %>
<%@ page import="edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil" %>
<%@ page import="edison.challenge.service.builder.model.Award" %>
<%@ page import="edison.challenge.service.builder.service.AwardLocalServiceUtil" %>
<%@ page import="edison.challenge.service.builder.model.Agency" %>
<%@ page import="edison.challenge.service.builder.service.AgencyLocalServiceUtil" %>
<%@ page import="edison.challenge.service.builder.model.ChallengeTeam" %>
<%@ page import="edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil" %>
<%@ page import="edison.challenge.service.builder.model.ChallengeMember" %>
<%@ page import="edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil" %>


<!-- script, jquery -->
<script src="https://code.jquery.com/jquery-2.2.3.min.js" ></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" ></script>
<link type="text/css" href="https://code.jquery.com/ui/1.11.4/themes/south-street/jquery-ui.css" rel="stylesheet" />

<!-- edison utils -->
<%@ page import="org.kisti.edison.model.EdisonExpando" %>
<%@ page import="com.liferay.portal.service.CompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoTable" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoValue" %>
<%@ page import="org.kisti.edison.util.CustomUtil" %>

<!-- json utils -->
<%@ page import="net.sf.json.JSONObject"%>

<theme:defineObjects />
<portlet:defineObjects />

