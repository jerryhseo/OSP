<%@include file="/html/init.jsp"%>


<c:choose>
<c:when test="<%=!themeDisplay.isSignedIn()%>">
	<script>
		window.location.href = "${themeDisplay.getURLSignIn()}";
		console.log("sign in hrif");
	</script>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose>
<%
	Challenge challenge = (Challenge) renderRequest
			.getAttribute(WebKeys.CHALLENGE);

	long editId= 0;
	User currentUser = PortalUtil.getUser(request);
	String challengeFieldName = "";
	String curChallengeFieldName = "";
	List<ChildChallenge> childChallenges = null;
	if(challenge != null){
		childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, challenge.getChallengeId(), "Running");
		challengeFieldName = challenge.getField(themeDisplay.getLocale());
		curChallengeFieldName = challenge.getField(themeDisplay.getLocale());
		//out.println(childChallenges.toString());
	}
	if(challenge != null && childChallenges != null){
		//out.println(childChallenges.toString());
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
				curChallengeFieldName = curChallenge.getField(themeDisplay.getLocale());
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





<c:if test='<%= childChallenges!=null &&childChallenges.size()>0&&ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())>0 %>'>
<%
	//out.println(challengeFieldName);
	//out.println("<br>");
	//out.println(curChallengeFieldName);
	
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("challengeFieldName", curChallengeFieldName);
	
%>
<liferay-ui:search-container delta="50" iteratorURL="<%=iteratorURL%>">
<liferay-ui:search-container-results
	results="<%=ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationManagementes(scopeGroupId, childChallenges.get(0).getChildChallengeId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeEvaluationManagementServiceUtil.getChallengeEvaluationCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())%>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeEvaluationManagement" modelVar="challengeEvaluationManagement">
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-evauation-assessment" ) %>' 
			value='<%=challengeEvaluationManagement.getAssessmentItem(themeDisplay.getLocale()) %>' cssClass="liferaywith5"/>

		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-evaluation-distribution") %>'
			value='<%=String.valueOf(challengeEvaluationManagement.getDistribution()) %>' />
		<liferay-ui:search-container-column-text>
			<liferay-ui:icon-menu>
				<c:if test="<%= ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.UPDATE)%>">
					<portlet:renderURL var="editChallengeEvaluationManagement">
						<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagement.getChallengeEvaluationManagementId()) %>" />
						<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeEvaluationManagement.getChildChallengeId()) %>" />
						<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
						<portlet:param name="mvcPath" value="/html/challengeevaluationmanagement/challengeevaluationmanagement_edit.jsp"/>
					</portlet:renderURL>
					<liferay-ui:icon image="edit" url="<%=editChallengeEvaluationManagement %>" />
				</c:if>
				
				<c:if test="<%=ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.PERMISSIONS)%>">
					<liferay-security:permissionsURL
						modelResource="<%= ChallengeEvaluationManagement.class.getName() %>"
						modelResourceDescription="<%= challengeEvaluationManagement.getAssessmentItem(themeDisplay.getLocale()) %>"
						resourcePrimKey="<%= String.valueOf(challengeEvaluationManagement.getChallengeEvaluationManagementId()) %>"
						var="permissionsURL" />
					<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
				</c:if>
	
				<c:if test="<%=ChallengeEvaluationManagementPermission.contains(permissionChecker, challengeEvaluationManagement.getChallengeEvaluationManagementId(), ActionKeys.DELETE)%>">
					<portlet:actionURL name="DeleteChallengeEvaluationManagement" var="deleteURL">
						<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(challengeEvaluationManagement.getChallengeEvaluationManagementId()) %>" />
						<portlet:param name="childChallengeId" value="<%= String.valueOf(challengeEvaluationManagement.getChildChallengeId()) %>" />
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



<div id="<portlet:namespace/>challengeEvaluationItemEdit" style="display:none;">
<portlet:actionURL name="addChallengeEvaluationManagement" var="addChallengeEvaluationManagement">
	<portlet:param name="challengeEvaluationManagementId" value="<%= String.valueOf(0) %>" />
	<portlet:param name="childChallengeId" value="<%=childChallenges.size() > 0 ? String.valueOf(childChallenges.get(0).getChildChallengeId()):String.valueOf(0) %>" />
	<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
</portlet:actionURL>
<form action="<%=addChallengeEvaluationManagement %>" method="post" id="<portlet:namespace/>submitform">
<label><%=LanguageUtil.get(pageContext, "challenge-evauation-assessment") %></label>
<liferay-ui:input-localized type="textarea" style="display:inline;" name="inputAssessment" xml=''></liferay-ui:input-localized>
<label><%=LanguageUtil.get(pageContext, "challenge-evaluation-distribution") %></label>
<input style="display:inline" type="text" id="<portlet:namespace/>inputDistribution" name="<portlet:namespace/>inputDistribution" />

<br>
<input id="<portlet:namespace/>addChallengeEvaluationItemButton" onclick='javascript:hideEdit();' class="btn btn-primary" type="submit" value='<%=LanguageUtil.get(pageContext, "challenge-evaluation-add") %>'/>
<input type="button" onclick='javascript:hideEdit();' id="<portlet:namespace/>cancelEvaluationItemButton" class="btn" value='<%=LanguageUtil.get(pageContext, "challenge-cancel") %>'/>
</form>
</div>


<c:if test="<%=childChallenges.size() > 0 %>">
<input type="button" id="<portlet:namespace/>addChallengeEvaluationItemButton2" class="btn btn-primary" onclick='javascript:addEvaluation();' value="<%=LanguageUtil.get(pageContext, "challenge-evaluation-add") %>"/>
</c:if>




<%}%>

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
</script>