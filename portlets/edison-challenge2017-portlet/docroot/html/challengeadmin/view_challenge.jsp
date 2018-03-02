<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%= viewURL %>" title="challenge" />

<%
	long challengeId = ParamUtil.getLong(renderRequest, "challengeId");
	Challenge challenge = ChallengeLocalServiceUtil.getChallenge(challengeId);
	challenge = challenge.toEscapedModel();
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Challenge.class.getName(), challenge.getChallengeId());

	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, challenge.getField(themeDisplay.getLocale()),
			currentURL);

	PortalUtil.setPageSubtitle(challenge.getName(themeDisplay.getLocale()), request);
	PortalUtil.setPageDescription(challenge.getDescription(themeDisplay.getLocale()), request);

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Challenge.class.getName(), challenge.getChallengeId());
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
%>

<dl>
	<dt>Challenge Filed</dt>
	<dd><%= challenge.getField(themeDisplay.getLocale()) %></dd>
</dl>

<c:if test="<%= themeDisplay.isSignedIn() %>">
	<liferay-ui:panel-container extended="<%= false %>"
		id="challengeCollaborationPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
	        id="challengeCollaborationPanel" persistState="<%= true %>"
	        title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
			<liferay-ui:ratings className="<%= Challenge.class.getName() %>"
				classPK="<%= challenge.getChallengeId() %>" type="stars" />
			
			<br />

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
	
			<liferay-ui:discussion className="<%= Challenge.class.getName() %>"
	            classPK="<%= challenge.getChallengeId() %>"
	            formAction="<%= discussionURL %>" formName="fm2"
	            ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
	            subject="<%= challenge.getField(themeDisplay.getLocale()) %>"
	            userId="<%= challenge.getUserId() %>" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
	className="<%= Challenge.class.getName() %>"
	classPK="<%= challenge.getChallengeId() %>" />
	
<br>
challenge view 