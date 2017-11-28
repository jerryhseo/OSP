<%@ include file="/html/init.jsp" %>
<c:choose>
	<c:when test="<%=!themeDisplay.isSignedIn()%>">
		<script>
			window.location.href = "${themeDisplay.getURLSignIn()}";
		</script>
	</c:when>
	<c:otherwise>

<%

	List<ChildChallenge> runningList = null; 
	runningList= ChildChallengeLocalServiceUtil.getRunningChild();
	int runningChildCount = 0;
	runningChildCount = ChildChallengeLocalServiceUtil.getRunningChildCount();

	User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));
%>

<blockquote>
<h3><b><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-running-list") %></b></h3>
</blockquote>


<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="There-are-no-challenge-to-Apply">
	<liferay-ui:search-container-results 		
		total="<%= runningChildCount %>" 
		results="<%= ListUtil.subList(runningList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChildChallenge" 
			modelVar="currentChildChallenge" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-name") %>' 
			value='<%=ChallengeLocalServiceUtil.getChallenge(currentChildChallenge.getChallengeid()).getDescription(themeDisplay.getLocale())+"  " + String.valueOf(currentChildChallenge.getNumber())+ "th <br>"  + ChallengeLocalServiceUtil.getChallenge(currentChildChallenge.getChallengeid()).getName(themeDisplay.getLocale()) %>'/>
			<%
			
				//Calendar startDate = currentChildChallenge.getChallengeStartDay().;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
				Date startDate = currentChildChallenge.getChallengeStartDay();
				Date endDate = currentChildChallenge.getChallengeEndDay();
				
				Calendar currentTime = Calendar.getInstance();
				Date currentDate = new Date(currentTime.getTimeInMillis());
				
				
				String challengeDate = dateFormat.format(startDate) + " - " + dateFormat.format(endDate);
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-day") %>' value="<%=challengeDate %>" />
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="applyChallenge">
						<portlet:param name="mvcPath" value="/html/teaminformation/teamForm.jsp" />
						<portlet:param name="childChallengeID" value="<%=String.valueOf(currentChildChallenge.getPrimaryKey())%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>
					<liferay-ui:icon image="add" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-apply") %>' url='<%=ChildChallengeLocalServiceUtil.isMemeberofTeam(currentUser.getPrimaryKey(), currentChildChallenge.getPrimaryKey())?"":applyChallenge.toString() %>'/>
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>

</c:otherwise>
</c:choose>