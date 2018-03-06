<%@include file="/html/init.jsp"%>

<%
	String mvcPath = ParamUtil.getString(request, "mvcPath");

	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

	ChallengeTeam challengeTeam = (ChallengeTeam)row.getObject(); 
	
	Challenge challenge = ChallengeLocalServiceUtil.getChallenge(
			ChildChallengeLocalServiceUtil.getChildChallenge(challengeTeam.getChildChallengeId()).getChallengeId());
	String challengeFieldName = challenge.getField(themeDisplay.getLocale());
	
	User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));
%>

<liferay-ui:icon-menu>
	<c:if test="<%= ChallengeTeamPermission.contains(permissionChecker, challengeTeam.getChallengeTeamId(), ActionKeys.UPDATE)%>">
		<portlet:renderURL var="editURL">
			<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
			<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
			<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
			<portlet:param name="mvcPath" value="/html/challengeteam/challengeteam_edit.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString() %>" />
	</c:if>

	<c:if test="<%=ChallengeTeamPermission.contains(permissionChecker, challengeTeam.getChallengeTeamId(), ActionKeys.PERMISSIONS)%>">

		<liferay-security:permissionsURL
			modelResource="<%= ChallengeTeam.class.getName() %>"
			modelResourceDescription="<%= challengeTeam.getPaperName(themeDisplay.getLocale()) %>"
			resourcePrimKey="<%= String.valueOf(challengeTeam.getChallengeTeamId()) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

	</c:if>

	<c:if test="<%=ChallengeTeamPermission.contains(permissionChecker, challengeTeam.getChallengeTeamId(), ActionKeys.DELETE)%>">

		<portlet:actionURL name="deleteChallengeTeam" var="deleteURL">
			<portlet:param name="challengeTeamId" value="<%= String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
			<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeTeam.getChildChallengeId()) %>" />
			<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

	</c:if>
</liferay-ui:icon-menu>