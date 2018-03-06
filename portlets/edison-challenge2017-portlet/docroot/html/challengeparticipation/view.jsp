<%@include file="/html/init.jsp"%>
<c:choose>
<c:when test="<%=!themeDisplay.isSignedIn()%>">
	<script>
		window.location.href = "${themeDisplay.getURLSignIn()}";
	</script>
</c:when>
</c:choose>


<liferay-ui:error key="challenge-cannot-be-displayed" message="challenge-cannot-be-displayed" />
<%
User currentUser = PortalUtil.getUser(request);
ChallengeTeam challengeTeam = null;
//challengeTeam = ChallengeTeamLocalServiceUtil.getUserCurrentTeam(scopeGroupId, currentUser.getUserId());

challengeTeam = (ChallengeTeam)request.getAttribute(WebKeys.CHALLENGE_TEAM);

%>


<c:if test="<%=challengeTeam!=null %>">
<portlet:renderURL var="editmyTeam">
	<portlet:param name="mvcPath" value="/html/challengeparticipation/myteamview.jsp"/>
</portlet:renderURL>
<script>
window.location.href='<%=editmyTeam%>';
</script>
</c:if>

<c:if test="<%=challengeTeam==null %>">
<portlet:renderURL var="paticipationchallenge">
	<portlet:param name="mvcPath" value="/html/challengeparticipation/paticipationchallenge.jsp"/>
</portlet:renderURL>
<script>
window.location.href='<%=paticipationchallenge%>';
</script>
</c:if>