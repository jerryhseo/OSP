<%@include file = "/html/init.jsp" %>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp"></portlet:param>
</portlet:renderURL>



<%
	//out.println("test call asset definition");
	String number = ParamUtil.getString(renderRequest, "number");
	
	String challengeFieldName=ParamUtil.getString(renderRequest, "challengeFieldName");
	
	String title = challengeFieldName +" "+ number;
%>
<liferay-ui:header backURL="<%= viewURL %>" title="<%=title%>" />
<%
	OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
	OrderByComparator orderByComparator = orderByComparatorFactory.create("challenge", "field", true);
	Challenge challenge = ChallengeLocalServiceUtil.getChallengeByGroupAndField(scopeGroupId, challengeFieldName, orderByComparator);
	//Challenge challenge = ChallengeLocalServiceUtil.getChallengeByGroupAndField(scopeGroupId, challengeFieldName);
	
	List<ChildChallenge> childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, challenge.getChallengeId());
	
	
	for (ChildChallenge childChallenge : childChallenges) {
		childChallenge = childChallenge.toEscapedModel();

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				ChildChallenge.class.getName(), childChallenge.getChildChallengeId());

		String currentURL = PortalUtil.getCurrentURL(request);

		PortalUtil.addPortletBreadcrumbEntry(request,
			String.valueOf(childChallenge.getNumber()), currentURL);

		PortalUtil.setPageSubtitle(String.valueOf(childChallenge.getNumber()), request);
		PortalUtil.setPageDescription(childChallenge.getChallengeStatus(), request);

		List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
				ChildChallenge.class.getName(), childChallenge.getChildChallengeId());
		PortalUtil.setPageKeywords(
				ListUtil.toString(assetTags, "name"), request);
		if(childChallenge.getNumber() == Integer.parseInt(number)){
			
		
%>

<dl>
	<dt>Challenge Name</dt>
	<dd><%= ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId()).getField(themeDisplay.getLocale()) %></dd>
	<dt>Date</dt>
	<dd><%= childChallenge.getChallengeStartDay() %> ~ <%= childChallenge.getChallengeEndDay() %></dd>
	<dt>Number</dt>
	<dd><%= childChallenge.getNumber() %></dd>
	<dt>Status</dt>
	<dd><%= childChallenge.getChallengeStatus() %></dd>
</dl>

<c:if test="<%= themeDisplay.isSignedIn() %>">
	<liferay-ui:panel-container extended="<%= false %>"
		id="entryCollaborationPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
	        id="entryCollaborationPanel" persistState="<%= true %>"
	        title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
			<liferay-ui:ratings className="<%= ChildChallenge.class.getName() %>"
				classPK="<%= childChallenge.getChildChallengeId() %>" type="stars" />
			
			<br/>

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
	
			<liferay-ui:discussion className="<%= ChildChallenge.class.getName() %>"
	            classPK="<%= childChallenge.getChildChallengeId() %>"
	            formAction="<%= discussionURL %>" formName="fm2"
	            ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
	            subject="<%= childChallenge.getChallengeStatus() %>"
	            userId="<%= childChallenge.getUserId() %>" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
	className="<%= ChildChallenge.class.getName() %>"
	classPK="<%= childChallenge.getChildChallengeId() %>" />
<%
		}
	}
%>


<br>
challenge view 