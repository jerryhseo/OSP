<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.service.UserServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>

<%@ page import="kisti.edison.challenge.model.Challenge" %>
<%@ page import="kisti.edison.challenge.model.ChildChallenge" %>
<%@ page import="kisti.edison.challenge.model.ChallengeTeam" %>
<%@ page import="kisti.edison.challenge.model.ChallengeTeamMember" %>
<%@ page import="kisti.edison.challenge.model.ChallengeEvaluationManagement" %>
<%@ page import="kisti.edison.challenge.model.ChallengeEvaluationScore" %>
<%@ page import="kisti.edison.challenge.util.WebKeys" %>

<%@ page import="kisti.edison.challenge.service.ChildChallengeLocalServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeLocalServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChildChallengeServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeEvaluationManagementServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil" %>

<%@ page import="kisti.edison.challenge.service.ChallengeTeamServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil" %>
<%@ page import="kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil" %>

<%@ page import="kisti.edison.challenge.service.permission.ChallengeModelPermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChallengePermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChildChallengePermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChallengeTeamPermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChallengeTeamMemberPermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission" %>
<%@ page import="kisti.edison.challenge.service.permission.ChallengeEvaluationScorePermission" %>

<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portlet.asset.model.AssetTag" %>
<%@ page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.search.Indexer" %>
<%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %>
<%@ page import="com.liferay.portal.kernel.search.Hits" %>
<%@ page import="com.liferay.portal.kernel.search.Document" %>
<%@ page import="com.liferay.portal.kernel.search.Field" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.log.Log" %>
<%@ page import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.Role" %>
<%@ page import="com.liferay.portal.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.RoleServiceUtil" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactory" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>

<%@ page import="javax.portlet.PortletURL" %>

<!-- JQuery -->
<script src="<%=request.getContextPath()%>/js/jquery/jquery-2.2.3.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js" ></script>
<link type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui.css" rel="stylesheet" />
<!-- edison utils -->
<%@ page import="com.liferay.portal.service.CompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoTable" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoValue" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ page import="org.kisti.edison.util.CustomUtil" %>
<%@ page import="org.kisti.edison.model.EdisonExpando" %>


<portlet:defineObjects />
<theme:defineObjects />