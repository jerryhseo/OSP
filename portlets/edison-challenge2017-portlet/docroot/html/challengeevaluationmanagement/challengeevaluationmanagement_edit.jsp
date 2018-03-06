<%@include file="/html/init.jsp"%>


<c:choose>
<c:when test="<%=!themeDisplay.isSignedIn()%>">
	<script>
		window.location.href = "${themeDisplay.getURLSignIn()}";
		console.log("sign in hrif");
	</script>
</c:when>
<c:otherwise>

<%
	long editId= 0;
	User currentUser = PortalUtil.getUser(request);
	String challengeFieldName = ParamUtil.getString(request, "challengeFieldName");
	long challengeEvaluationManagementId = ParamUtil.getLong(request, "challengeEvaluationManagementId");
	long childChallengeId = ParamUtil.getLong(request, "childChallengeId");
	
	ChallengeEvaluationManagement challengeEvaluationManagement= null;
	ChildChallenge childChallenge = null;
	Challenge challenge = null;
	
	if(challengeEvaluationManagementId > 0)
		challengeEvaluationManagement = ChallengeEvaluationManagementLocalServiceUtil.getChallengeEvaluationManagement(challengeEvaluationManagementId);
	if(childChallengeId > 0){
		childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId);
		challenge = ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId());
		
		renderRequest.setAttribute(WebKeys.CHALLENGE, challenge);
	}
	List<ChildChallenge> childChallenges = null;
	if(challenge != null){
		childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, challenge.getChallengeId(), "Running");
		challengeFieldName = challenge.getField(themeDisplay.getLocale());
	}
	if(challenge != null && childChallenges != null){
%>


<aui:nav cssClass="nav-tabs">

	<%
		List<Challenge> challenges = null;
		Role role = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "Administrator");
		long groupId = themeDisplay.getScopeGroupId();
		long companyId = themeDisplay.getCompanyId();
		
		if(UserLocalServiceUtil.hasRoleUser(role.getRoleId(), currentUser.getUserId())){
			challenges = ChallengeServiceUtil.getChallenges(groupId);
		}else{
			challenges = ChallengeLocalServiceUtil.getChallengesByManagerRole(groupId, currentUser.getUserId(), companyId);
		}
		
		for (int i = 0; i < challenges.size(); i++) {
			Challenge curChallenge = (Challenge) challenges.get(i);
			String cssClass = StringPool.BLANK;
			if (curChallenge.getChallengeId() == challenge.getChallengeId()) {
				cssClass = "active";
				challengeFieldName = curChallenge.getField(themeDisplay.getLocale());
			}
				
		if (ChallengePermission.contains(
			permissionChecker, curChallenge.getChallengeId(), "VIEW")) {
					
	%>
	
	

		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/html/challengeevaluationmanagement/view.jsp" />
			<portlet:param name="challengeFieldName" value="<%=curChallenge.getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curChallenge.getField(themeDisplay.getLocale()))%>" />
	<% 
			}
		} 
	%>

</aui:nav>

<div id="<portlet:namespace/>listSection" style="display:none">
<c:if test='<%= childChallenges.size()>0&&ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())>0 %>'>
<liferay-ui:search-container id="assessmentList">
<liferay-ui:search-container-results
	results="<%=ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationManagementes(scopeGroupId, childChallenges.get(0).getChildChallengeId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())%>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeEvaluationManagement" modelVar="challengeEvaluationManagementList">
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-evauation-assessment" ) %>' 
			value='<%=challengeEvaluationManagementList.getAssessmentItem(themeDisplay.getLocale()) %>'/>

		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-evaluation-distribution") %>'
			value='<%=String.valueOf(challengeEvaluationManagementList.getDistribution()) %>' />
		<liferay-ui:search-container-column-text>
			<liferay-ui:icon-menu>
				<c:if test="<%= ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.UPDATE)%>">
					<portlet:renderURL var="editChallengeEvaluationManagement">
						<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagementList.getChallengeEvaluationManagementId()) %>" />
						<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeEvaluationManagementList.getChildChallengeId()) %>" />
						<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
						<portlet:param name="mvcPath" value="/html/challengeevaluationmanagement/challengeevaluationmanagement_edit.jsp"/>
					</portlet:renderURL>
					<liferay-ui:icon image="edit" url="<%=editChallengeEvaluationManagement %>" />
				</c:if>
				
				<c:if test="<%=ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.PERMISSIONS)%>">
					<liferay-security:permissionsURL
						modelResource="<%= ChallengeEvaluationManagement.class.getName() %>"
						modelResourceDescription="<%= challengeEvaluationManagementList.getAssessmentItem(themeDisplay.getLocale()) %>"
						resourcePrimKey="<%= String.valueOf(challengeEvaluationManagementList.getChallengeEvaluationManagementId()) %>"
						var="permissionsURL" />
					<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
				</c:if>
	
				<c:if test="<%=ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.DELETE)%>">
					<portlet:actionURL name="DeleteChallengeEvaluationManagement" var="deleteURL">
						<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagementList.getChallengeEvaluationManagementId()) %>" />
						<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeEvaluationManagementList.getChildChallengeId()) %>" />
						<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
					</portlet:actionURL>
					<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
				</c:if>
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
</c:if>
</div>



<div id="<portlet:namespace/>challengeEvaluationItemEdit" >
<portlet:actionURL name="addChallengeEvaluationManagement" var="addChallengeEvaluationManagement">
	<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagement.getChallengeEvaluationManagementId()) %>" />
	<portlet:param name="childChallengeId" value="<%= String.valueOf(childChallenges.get(0).getChildChallengeId()) %>" />
	<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
	<portlet:param name="mvcPath" value="/html/challengeevaluationmanagement/view.jsp"/>
</portlet:actionURL>
<form action="<%=addChallengeEvaluationManagement %>" method="post" id="<portlet:namespace/>submitform">
<label><%=LanguageUtil.get(pageContext, "challenge-evauation-assessment") %></label>
<liferay-ui:input-localized type="textarea" style="display:inline;" name="inputAssessment" xml='<%=challengeEvaluationManagement.getAssessmentItem() %>'></liferay-ui:input-localized>
<label><%=LanguageUtil.get(pageContext, "challenge-evaluation-distribution") %></label>
<input style="display:inline" type="text" id="<portlet:namespace/>inputDistribution" name="<portlet:namespace/>inputDistribution" value="<%=challengeEvaluationManagement.getDistribution() %>" />

<br>
<input id="<portlet:namespace/>addChallengeEvaluationItemButton" onclick='javascript:hideEdit();' class="btn btn-primary" type="submit" value='<%=LanguageUtil.get(pageContext, "challenge-edit") %>'/>
<portlet:renderURL var="editChallengeEvaluationManagement">
	<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagement.getChallengeEvaluationManagementId()) %>" />
	<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeEvaluationManagement.getChildChallengeId()) %>" />
	<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
	<portlet:param name="mvcPath" value="/html/challengeevaluationmanagement/view.jsp"/>
</portlet:renderURL>
<input type="button" onclick="javascript: location.href='<%=editChallengeEvaluationManagement%>'" id="<portlet:namespace/>cancelEvaluationItemButton" class="btn" value='<%=LanguageUtil.get(pageContext, "challenge-cancel") %>'/>
</form>
</div>



<%}%>



</c:otherwise>
</c:choose>



<script>

function addEvaluation(){
	$("#<portlet:namespace/>addChallengeEvaluationItemButton2").css("display", "none");
	$("#<portlet:namespace/>challengeEvaluationItemEdit").css("display", "block");
	$("#<portlet:namespace/>assessmentList").css("display", "none");
}

function hideEdit(){
	$("#<portlet:namespace/>addChallengeEvaluationItemButton2").css("display", "block");
	$("#<portlet:namespace/>challengeEvaluationItemEdit").css("display", "none");
	$("#<portlet:namespace/>assessmentList").css("display", "block");
}

function updateEvaluation(url){
	console.log(url);
	console.log();
	
	$("<portlet:namespace/>submitform").action = url;
	
	$("#<portlet:namespace/>assessmentList").css("display", "none");
	$("#<portlet:namespace/>challengeEvaluationItemEdit").css("display", "block");
}
</script>