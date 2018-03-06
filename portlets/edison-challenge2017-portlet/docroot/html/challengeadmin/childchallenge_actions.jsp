<%@include file="/html/init.jsp"%>


<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ChildChallenge childChallenge = (ChildChallenge)row.getObject(); 
%>

<liferay-ui:icon-menu>

	<c:if test="<%= ChildChallengePermission.contains(permissionChecker, childChallenge.getChildChallengeId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallenge.getChildChallengeId()) %>" />
			<portlet:param name="mvcPath" value="/html/challengeadmin/edit_childchallenge.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString() %>" />
	</c:if>

	<c:if test="<%=ChildChallengePermission.contains(permissionChecker, childChallenge.getChildChallengeId(), ActionKeys.PERMISSIONS) %>">

		<liferay-security:permissionsURL
			modelResource="<%= ChildChallenge.class.getName() %>"
			modelResourceDescription="<%= childChallenge.getChallengeStatus() %>"
			resourcePrimKey="<%= String.valueOf(childChallenge.getChildChallengeId()) %>"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

	</c:if>

	<c:if
		test="<%=ChildChallengePermission.contains(permissionChecker, childChallenge.getChildChallengeId(), ActionKeys.DELETE) %>">

		<portlet:actionURL name="deleteChildChallenge" var="deleteURL">
			<portlet:param name="childChallengeId" value="<%= String.valueOf(childChallenge.getChildChallengeId()) %>" />
			<portlet:param name="challengeId" value="<%= String.valueOf(childChallenge.getChallengeId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

	</c:if>

</liferay-ui:icon-menu>