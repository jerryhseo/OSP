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
	Challenge challenge = (Challenge) renderRequest.getAttribute(WebKeys.CHALLENGE);
	
	User currentUser = PortalUtil.getUser(request);
	String challengeFieldName = "";
	String curChallengeFieldName = "";
	List<ChildChallenge> childChallenges = null;
	if(challenge != null){
		childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, challenge.getChallengeId(), "Running");
		challengeFieldName = challenge.getField(themeDisplay.getLocale());
		curChallengeFieldName = challenge.getField(themeDisplay.getLocale());
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
			challenges = ChallengeLocalServiceUtil.getChallengesByEvaluationRole(groupId, currentUser.getUserId(), companyId);
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
			<portlet:param name="mvcPath" value="/html/challengeteam/view.jsp" />
			<portlet:param name="challengeFieldName" value="<%=curChallenge.getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curChallenge.getField(themeDisplay.getLocale()))%>" />
	<% 
		//curChallengeFieldName = curChallenge.getField(themeDisplay.getLocale());
			}
		} 
	%>

</aui:nav>


<c:if test='<%= childChallenges.size()>0 %>'>
<%
	//out.println(challengeFieldName);
	//out.println("<br>");
	//out.println(curChallengeFieldName);
	
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("challengeFieldName", curChallengeFieldName);
	
	String orderByType = ParamUtil.getString(request, "orderByType");
	String sortByCol = ParamUtil.getString(request, "orderByCol");
	
	if(orderByType.equals("desc")){
		orderByType = "asc";
	}else{
		orderByType = "desc";
	}
	 
	if(Validator.isNull(orderByType)){
		orderByType = "desc";
	}
%>


<liferay-ui:search-container delta="50" iteratorURL="<%=iteratorURL%>" orderByType="<%=orderByType %>">
<liferay-ui:search-container-results results="<%=ChallengeTeamLocalServiceUtil.getChallengeTeams(scopeGroupId, childChallenges.get(0).getChildChallengeId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeTeamLocalServiceUtil.getChallengeTeamsCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())%>" />
	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeTeam" modelVar="challengeTeam">
		
		<portlet:renderURL var="viewChallengeTeam">
			<portlet:param name="mvcPath" value="/html/challengeteam/view_challengeteam.jsp" />
			<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
			<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
		</portlet:renderURL>
		
		
		<liferay-ui:search-container-column-text orderable="true" name='<%=LanguageUtil.get(pageContext, "challenge-team-name" ) %>' 
			value='<%=challengeTeam.getTeamName() %>' href="<%=viewChallengeTeam%>" cssClass="liferaywith4"/>

		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-user-papername") %>'
			value='<%=challengeTeam.getPaperName(themeDisplay.getLocale()) %>' href="<%=viewChallengeTeam%>" cssClass="liferaywith2"/>
			
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-team-phone") %>'
			value='<%=ChallengeTeamMemberLocalServiceUtil.getChallengeTeamLeaderMember(challengeTeam.getChallengeTeamId(), themeDisplay.getScopeGroupId()).getPhone() %>' href="<%=viewChallengeTeam%>" cssClass="liferaywith4"/>
		
		<c:if test="<%=themeDisplay.isSignedIn() %>">
		<liferay-ui:search-container-column-jsp
            path="/html/challengeteam/challengeteam_action.jsp"
            align="right" />
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<portlet:resourceURL var="getTeamInformationList">
	<portlet:param name="type" value="downloadTeamList"/>
	<portlet:param name="challengeFieldName" value="challengeFieldName"/>
	<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallenges.get(0).getChildChallengeId())  %>" />
</portlet:resourceURL> 
<input type="button" class="btn btn-success" onclick="javascript: location.href='<%=getTeamInformationList%>';" value='Download'/>

</c:if>



<%}%>
