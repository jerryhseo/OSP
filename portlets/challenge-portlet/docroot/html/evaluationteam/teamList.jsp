<%@ include file="/html/init.jsp" %>


<%
	long childID = ParamUtil.getLong(request, "childChallengeID");
	List<ChallengeTeam> teamList = ChallengeTeamLocalServiceUtil.getChallengeTeamByChild(childID);
	int teamNum = teamList.size();
	long chID = ChildChallengeLocalServiceUtil.getChildChallenge(childID).getChallengeid();
	
	String challengeDes = ChallengeLocalServiceUtil.getChallenge(chID).getDescription(themeDisplay.getLocale());
	
%>

<blockquote>
<h3><b><%=challengeDes %> <%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-list-status") %></b></h3>
</blockquote>


<liferay-portlet:renderURL varImpl="interatorTeamListURL">
	<portlet:param name="mvcPath" value="/html/evaluationteam/teamList.jsp" />
	<portlet:param name="childChallengeID" value="<%=String.valueOf(childID) %>" />
	<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent() %>" />
</liferay-portlet:renderURL>


<liferay-ui:search-container delta="10" iteratorURL="<%=interatorTeamListURL %>" deltaConfigurable="true" emptyResultsMessage="There-are-no-Team-to-Apply">
	<liferay-ui:search-container-results 		
		total="<%= teamNum %>" 
		results="<%= ListUtil.subList(teamList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChallengeTeam" 
			modelVar="currentTeam" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-name") %>' value='<%=currentTeam.getTeamName(themeDisplay.getLocale())%>'/>
			
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papername") %>' value='<%=currentTeam.getPaperName(themeDisplay.getLocale())%>'/>
			
			<%
				long leaderID = ChallengeMemberLocalServiceUtil.getTeamLeaderToMemberID(currentTeam.getPrimaryKey());
				ChallengeMember leaderMember = ChallengeMemberLocalServiceUtil.getChallengeMember(leaderID);
			%>
			
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-member-falculty") %>' value='<%=leaderMember.getFalculty(themeDisplay.getLocale())%>'/>			
			
			
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>

<portlet:renderURL var="returnChallengeList">
	<portlet:param name="mvcPath" value="/html/evaluationteam/view.jsp" />
</portlet:renderURL>
<button class="btn btn-primary" onclick="javascript: location.href='<%=returnChallengeList.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-home") %></button>


