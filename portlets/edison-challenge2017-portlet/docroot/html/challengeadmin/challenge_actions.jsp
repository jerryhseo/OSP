<%@include file="/html/init.jsp"%>

<%
	String mvcPath = ParamUtil.getString(request, "mvcPath");

	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

	Challenge challenge = (Challenge) row.getObject();
%>

<liferay-ui:icon-menu>

	<c:if
		test="<%=ChallengePermission.contains(permissionChecker,
							challenge.getChallengeId(), ActionKeys.UPDATE)%>">
		<portlet:renderURL var="editURL">
			<portlet:param name="challengeId"
				value="<%=String.valueOf(challenge.getChallengeId()) %>" />
			<portlet:param name="mvcPath"
				value="/html/challengeadmin/edit_challenge.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString() %>" />
	</c:if>

	<c:if
		test="<%=ChallengePermission.contains(permissionChecker,
							challenge.getChallengeId(), ActionKeys.PERMISSIONS)%>">
		<liferay-security:permissionsURL
			modelResource="<%= Challenge.class.getName() %>"
			modelResourceDescription="<%= challenge.getName(themeDisplay.getLocale()) %>"
			resourcePrimKey="<%= String.valueOf(challenge.getChallengeId()) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if
		test="<%=ChallengePermission.contains(permissionChecker,
							challenge.getChallengeId(), ActionKeys.DELETE)%>">
		<portlet:actionURL name="deleteChallenge" var="deleteURL">
			<portlet:param name="challengeId"
				value="<%= String.valueOf(challenge.getChallengeId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>