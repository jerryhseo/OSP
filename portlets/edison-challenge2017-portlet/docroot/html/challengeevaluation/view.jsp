<%@include file="/html/init.jsp"%>
<div style="word-break: break-all;">
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
			<portlet:param name="mvcPath" value="/html/challengeevaluation/view.jsp" />
			<portlet:param name="challengeFieldName" value="<%=curChallenge.getField(themeDisplay.getLocale())%>" />
		</portlet:renderURL>

		<aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
			label="<%=HtmlUtil.escape(curChallenge.getField(themeDisplay.getLocale()))%>" />
	<% 
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
	
%>
<liferay-ui:search-container delta="50" iteratorURL="<%=iteratorURL%>">
<liferay-ui:search-container-results
	results="<%=ChallengeTeamLocalServiceUtil.getChallengeTeams(scopeGroupId, childChallenges.get(0).getChildChallengeId(), searchContainer.getStart(), searchContainer.getEnd())%>"
    total="<%=ChallengeTeamLocalServiceUtil.getChallengeTeamsCount(scopeGroupId, childChallenges.get(0).getChildChallengeId())%>" />

	
	<liferay-ui:search-container-row
		className="kisti.edison.challenge.model.ChallengeTeam" modelVar="challengeTeam">
		
		<%
				List<ChallengeEvaluationScore> scoreList = new ArrayList<ChallengeEvaluationScore>(); 
				scoreList = ChallengeEvaluationScoreLocalServiceUtil
				.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(scopeGroupId, 
						challengeTeam.getChallengeTeamId(), currentUser.getUserId());
		%>
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-team-name" ) %>' 
			value='<%=challengeTeam.getTeamName() %>' href="#" cssClass="liferaywith1"/>

		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-user-papername") %>'
			value='<%=challengeTeam.getPaperName(themeDisplay.getLocale()) %>' href="#" cssClass="liferaywith8"/>
			
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-cputime") %>'
			value='<%=String.valueOf(ChallengeTeamLocalServiceUtil.getCPUUseage(themeDisplay.getCompanyId(), challengeTeam.getChallengeTeamId()))%>' href="#" cssClass="liferaywith3"/>
		
		<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(pageContext, "challenge-evaluation") %>' cssClass="liferaywith3">
			
			
			<liferay-ui:icon-menu>
				<portlet:renderURL var="evaluationURL">
					<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
					<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
					<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
					<portlet:param name="mvcPath" value="/html/challengeevaluation/challengeevaluation.jsp" />
				</portlet:renderURL>
				<liferay-ui:icon image="edit" url="<%=evaluationURL.toString() %>"
					message='<%=LanguageUtil.get(pageContext, "challenge-evaluation") %>'/>
				
				<portlet:actionURL name="deleteChallengeEvaluationScore" var="deleteURL">
					<portlet:param name="childChallengeId" value="<%=String.valueOf(challengeTeam.getChildChallengeId()) %>" />
					<portlet:param name="challengeTeamId" value="<%=String.valueOf(challengeTeam.getChallengeTeamId()) %>" />
					<portlet:param name="challengeFieldName" value="<%=challengeFieldName %>"/>
				</portlet:actionURL>
				<liferay-ui:icon image="delete" url="<%=deleteURL.toString() %>"
					message='<%=LanguageUtil.get(pageContext, "challenge-delete") %>'/>
			</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text cssClass="liferaywith6">
			
			<c:if test="<%=scoreList.size()>0%>">
				  <liferay-ui:icon image="check"/>
			</c:if>
		</liferay-ui:search-container-column-text>
		<%
			double userScore=0.0;
			List<ChallengeEvaluationScore> scoreLsit = ChallengeEvaluationScoreLocalServiceUtil.getChallengeEvaluationScores(scopeGroupId, challengeTeam.getChallengeTeamId());
			for(ChallengeEvaluationScore score : scoreList){
				if(score.getUserId()==currentUser.getUserId()){
					userScore += score.getScore();
				}
			}
		%>
		<liferay-ui:search-container-column-text name='Score'
			value='<%=String.valueOf(userScore) %>' cssClass="liferaywith3" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
</c:if>
<%}%>
</div>