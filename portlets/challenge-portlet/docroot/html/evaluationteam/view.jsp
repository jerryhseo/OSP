<%@ include file="/html/init.jsp" %>




<%

	List<ChildChallenge> runningList = null; 
	runningList= ChildChallengeLocalServiceUtil.getRunningChild();
	int runningChildCount = 0;
	runningChildCount = ChildChallengeLocalServiceUtil.getRunningChildCount();

%>


<blockquote>
<h3><b><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-list-status") %></b></h3>
</blockquote>

<liferay-portlet:renderURL varImpl="interatorTeamviewURL">
	<portlet:param name="mvcPath" value="/html/evaluationteam/view.jsp" />
	<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container delta="5" iteratorURL="<%=interatorTeamviewURL %>" deltaConfigurable="true" emptyResultsMessage="There-are-no-challenge-to-Apply">
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
				
				String challengeDate = dateFormat.format(startDate) + " - " + dateFormat.format(endDate);
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-day") %>' value="<%=challengeDate %>" />
			<%
				int number = ChallengeTeamLocalServiceUtil.getChallengeTeamByChild(currentChildChallenge.getPrimaryKey()).size();
				String currentNum = String.valueOf(number);
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-evaluation-teamnumber") %>' value="<%=currentNum %>" />
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="teamList">
						<portlet:param name="mvcPath" value="/html/evaluationteam/teamList.jsp" />
						<portlet:param name="childChallengeID" value="<%=String.valueOf(currentChildChallenge.getPrimaryKey())%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>
					<liferay-ui:icon image="add" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-list-team") %>' url="<%=teamList.toString() %>"/>
				</liferay-ui:icon-menu>
			
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>