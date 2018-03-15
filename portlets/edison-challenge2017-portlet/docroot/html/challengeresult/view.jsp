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
	Challenge challenge = (Challenge) renderRequest
			.getAttribute(WebKeys.CHALLENGE);

	User currentUser = PortalUtil.getUser(request);
	String challengeFieldName = "";
	String curChallengeFieldName = "";
	List<ChildChallenge> childChallenges = null;
	if(challenge != null){
		childChallenges = ChildChallengeLocalServiceUtil.getChildChallenges(scopeGroupId, challenge.getChallengeId(), "Running");
		challengeFieldName = challenge.getField(themeDisplay.getLocale());
		curChallengeFieldName = challenge.getField(themeDisplay.getLocale());
		//System.out.println("test current challenge : "+challengeFieldName);
		//System.out.println("childChallenges : " + childChallenges.size());
		//System.out.println("childChallenge team list num : "+ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(scopeGroupId, childChallenges.get(0).getChildChallengeId()).size());
	}
%>

<c:if test="<%=challenge != null && childChallenges != null %>">

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
				//renderRequest.setAttribute(WebKeys.CHALLENGE, curChallenge);
			}
				

					
	%>
	
	

		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/html/challengeresult/view.jsp" />
			<portlet:param name="challengeFieldName" value="<%=curChallenge.getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curChallenge.getField(themeDisplay.getLocale()))%>" />
	<% 
			
		}
	%>

</aui:nav>


<portlet:renderURL var="viewPageURL2">
	<portlet:param name="mvcPath" value="/html/challengeresult/view.jsp" />
	<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>" />
</portlet:renderURL>
<c:if test='<%= childChallenges.size()>0 %>'>

<%
	//out.println(challengeFieldName);
	//out.println("<br>");
	//out.println(curChallengeFieldName);
	
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("challengeFieldName", curChallengeFieldName);
	
%>

<liferay-ui:search-container delta="50" iteratorURL="<%=iteratorURL%>">
<liferay-ui:search-container-results
	results="<%=ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(scopeGroupId, childChallenges.get(0).getChildChallengeId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(scopeGroupId, childChallenges.get(0).getChildChallengeId()).size()%>" />

	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeTeam" modelVar="challengeTeam">
			
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-team-name" ) %>' 
			value='<%=challengeTeam.getTeamName() %>' cssClass="liferaywith9"/>

		<liferay-ui:search-container-column-text cssClass="liferaywith4" name='<%=LanguageUtil.get(pageContext,"challenge-scienceappcount") %>'
			value='<%=String.valueOf(ChallengeTeamLocalServiceUtil.getTeamSimulationNumber(themeDisplay.getCompanyId(), challengeTeam.getChallengeTeamId())) %>'/>
			
		<liferay-ui:search-container-column-text cssClass="liferaywith4" name='<%=LanguageUtil.get(pageContext,"challenge-cputime") %>'
			value='<%=String.valueOf(ChallengeTeamLocalServiceUtil.getCPUUseage(themeDisplay.getCompanyId(), challengeTeam.getChallengeTeamId())) %>'/>
			
		<liferay-ui:search-container-column-text cssClass="liferaywith4" name='<%=LanguageUtil.get(pageContext,"challenge-evaluator-num") %>'
			value='<%=String.valueOf(ChallengeEvaluationScoreLocalServiceUtil.countChallengeEvaluationScore(scopeGroupId, challengeTeam.getChallengeTeamId())) %>'/>
			
		<liferay-ui:search-container-column-text cssClass="liferaywith4" name='Total Score'
			value='<%=String.valueOf(ChallengeEvaluationScoreLocalServiceUtil.getChallengeTeamTotalScore(scopeGroupId, challengeTeam.getChallengeTeamId())) %>'/>
		<liferay-ui:search-container-column-text cssClass="liferaywith4">
			<portlet:renderURL var="evaluationResultURL">
				<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
				<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
				<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
				<portlet:param name="mvcPath" value="/html/challengeresult/challengeevaluationresult.jsp" />
			</portlet:renderURL>
			<liferay-ui:icon image="view" url="<%=evaluationResultURL.toString() %>"/>
		</liferay-ui:search-container-column-text>
		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<portlet:resourceURL var="getEvaluationResult">
	<portlet:param name="type" value="getResult"/>
	<portlet:param name="challengeFieldName" value="challengeFieldName"/>
	<portlet:param name="childChallengeId" value="<%=String.valueOf(childChallenges.get(0).getChildChallengeId())  %>" />
</portlet:resourceURL> 
<input type="button" class="btn btn-success" onclick="javascript: location.href='<%=getEvaluationResult%>';" value='Download'/>

</c:if>



</c:if>


