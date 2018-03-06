<%@include file="/html/init.jsp"%>

<%
	ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(ParamUtil.getLong(request, "challengeTeamId"));
	ChildChallenge childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(ParamUtil.getLong(request, "childChallengeId"));
	Challenge challenge = ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId());
%>

<dl>
	<dt><%=LanguageUtil.get(pageContext, "challenge-user-field") %></dt>
	<dd><%= challenge.getField(themeDisplay.getLocale()) %></dd>
</dl>



<dl>
	<dt><%=LanguageUtil.get(pageContext, "challenge-user-teamname") %></dt>
	<dd><%= challengeTeam.getTeamName() %></dd>
</dl>


<dl>
	<dt><%=LanguageUtil.get(pageContext, "challenge-user-papername") %></dt>
	<dd><%= challengeTeam.getPaperName(themeDisplay.getLocale()) %></dd>
</dl>

<c:if test="<%= ChallengeTeamPermission.contains(permissionChecker, challengeTeam.getChallengeTeamId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
			<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
			<portlet:param name="challengeFieldName" value="<%=String.valueOf(challenge.getField(themeDisplay.getLocale())) %>" />
			<portlet:param name="mvcPath" value="/html/challengeteam/challengeteam_edit.jsp" />
		</portlet:renderURL>

		<button class="btn" onclick="javascript: location.href='<%=editURL.toString() %>';">Edit</button>
	</c:if>