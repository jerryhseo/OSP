<%@include file="/html/init.jsp"%>

<aui:button-row cssClass="guestbook-buttons">

<c:if test='<%= ChallengeModelPermission.contains(permissionChecker, scopeGroupId, "ADD_CHALLENGE")%>'>
	<portlet:renderURL var="addChallengeURL">
		<portlet:param name="mvcPath" value='<%=themeDisplay.getPathContext()+"/html/challengeadmin/edit_challenge.jsp" %>' />
	</portlet:renderURL>
	<button class="btn btn-primary btn-large" onclick="javascript: location.href='<%=addChallengeURL.toString() %>';"><%=LanguageUtil.get(pageContext, "challenge-create") %></button>
</c:if>

</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="There-are-no-challenge">
	<liferay-ui:search-container-results
		results="<%= ChallengeLocalServiceUtil.getChallenges(scopeGroupId,
				searchContainer.getStart(),
				searchContainer.getEnd()) %>"
		total="<%= ChallengeLocalServiceUtil.getChallengesCount(scopeGroupId) %>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.Challenge" modelVar="challenge">
		
		<portlet:renderURL var="viewChallenge">
			<portlet:param name="mvcPath" value="/html/challengeadmin/view_challenge.jsp" />
			<portlet:param name="challengeId" value="<%= String.valueOf(challenge.getChallengeId()) %>" />
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-name")%>' value="<%=challenge.getName(themeDisplay.getLocale())%>" href="<%= viewChallenge %>" />
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-field")%>' value="<%=challenge.getField(themeDisplay.getLocale())%>"/>
		<liferay-ui:search-container-column-text name="status" >
            <aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>"
                status="<%= challenge.getStatus() %>" />
        </liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
	            path="/html/challengeadmin/challenge_actions.jsp"
	            align="right" />
	
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<br><br>

<hr></hr>

<br><br>
<!-- challenge complex...... -->


<liferay-ui:error key="challenge-cannot-be-displayed" message="challenge-cannot-be-displayed" />

<%
	Challenge challenge = null;
	challenge = (Challenge) renderRequest
			.getAttribute(WebKeys.CHALLENGE);
	if(challenge != null){
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view_search.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
    <liferay-portlet:renderURLParams varImpl="searchURL" />

    <div class="search-form">
		<span class="aui-search-bar">
		    <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" />
		    <aui:button type="submit" value="search" />
		</span>
    </div>
</aui:form>

<aui:nav cssClass="nav-tabs">

	<%
     List<Challenge> challenges = ChallengeLocalServiceUtil
                .getChallenges(scopeGroupId, WorkflowConstants.STATUS_APPROVED);
     
			for (int i = 0; i < challenges.size(); i++) {
				Challenge curChallenge = (Challenge) challenges.get(i);

				String cssClass = StringPool.BLANK;

				if (curChallenge.getChallengeId() == challenge.getChallengeId()) {
					cssClass = "active";
				}
				
				if (ChallengePermission.contains(
						permissionChecker, curChallenge.getChallengeId(), "VIEW")) {
					
	%>
	
	

		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp" />
			<portlet:param name="challengeFieldName" value="<%=curChallenge.getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curChallenge.getField(themeDisplay.getLocale()))%>" />
	<% 
			}
		} 
	%>

</aui:nav>

<aui:button-row cssClass="guestbook-buttons">



<c:if test='<%= ChallengePermission.contains(permissionChecker, challenge.getChallengeId(), "ADD_CHILD_CHALLENGE") && !challenge.getField(themeDisplay.getLocale()).equals("Main") %>'>
	<portlet:renderURL var="addChildChallengeURL">
		<portlet:param name="mvcPath" value="/html/challengeadmin/edit_childchallenge.jsp" />
		<portlet:param name="challengeFieldName" value="<%=challenge.getField(themeDisplay.getLocale()).toString()%>" />
	</portlet:renderURL>
	<button class="btn btn-primary" onclick="javascript: location.href='<%=addChildChallengeURL.toString() %>';"><%=LanguageUtil.get(pageContext, "challenge-create") %></button>
</c:if>

</aui:button-row>

<liferay-ui:search-container>
<liferay-ui:search-container-results
    results="<%=ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId,
                	challenge.getChallengeId(), WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(),
                	searchContainer.getEnd())%>"
    total="<%=ChildChallengeLocalServiceUtil.getChildChallengesCount(scopeGroupId,
    		challenge.getChallengeId(), WorkflowConstants.STATUS_APPROVED)%>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChildChallenge" modelVar="childChallenge">
		
		<portlet:renderURL var="viewChildChallenge">
			<portlet:param name="mvcPath" value="/html/challengeadmin/view_childchallenge.jsp" />
			<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallenge.getChallengeId()) %>" />
			<portlet:param name="number" value="<%=String.valueOf(childChallenge.getNumber()) %>" />
			<portlet:param name="challengeFieldName" value="<%=ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId()).getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text property="number" href="<%=viewChildChallenge%>"/>

		<liferay-ui:search-container-column-text property="challengeEndDay" />
		
		<liferay-ui:search-container-column-jsp
            path="/html/challengeadmin/childchallenge_actions.jsp"
            align="right" />

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
<% 
}
%>