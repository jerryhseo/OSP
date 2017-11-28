<%@ include file="/html/init.jsp" %>
<%@ page import="org.kisti.edison.model.EdisonExpando" %>
<%@ page import="org.kisti.edison.util.EdisonExpndoUtil" %>
<%@ page import="org.kisti.edison.util.EdisonUserUtil" %>

<%
	User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));	

	List<ChallengeTeam> challengeTeamList = ChallengeMemberLocalServiceUtil.getChallengeTeamListByMember(currentUser.getScreenName());
	
	int challengeTeamCount = 0;
	challengeTeamCount = challengeTeamList.size();
	String currentUserSN = currentUser.getScreenName();
	boolean curretIsLeader = false;
	ChallengeMember leaderMember;
	
	//long currentTeamMemberID = ChallengeMemberLocalServiceUtil.getChallengeMember(chMemberid);
%>

<blockquote>
<h3><b><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-myteamlist") %></b></h3>
</blockquote>


<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="There-are-no-MyTeam-to-Apply">
	<liferay-ui:search-container-results 		
		total="<%= challengeTeamCount %>"
		results="<%= ListUtil.subList(challengeTeamList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChallengeTeam" 
			modelVar="currentChallengeTeam" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-name") %>' value='<%=currentChallengeTeam.getTeamName(themeDisplay.getLocale()) %>'/>
			<%
				long challengeid;
				ChildChallenge child = ChildChallengeLocalServiceUtil.getChildChallenge(currentChallengeTeam.getChildid());
				challengeid = child.getChallengeid();
				
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-field") %>' value='<%=ChallengeLocalServiceUtil.getChallenge(challengeid).getName(themeDisplay.getLocale()) +  ChallengeLocalServiceUtil.getChallenge(challengeid).getDescription(themeDisplay.getLocale()) %>'/>
			
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="myChallengeTeam">
						<portlet:param name="mvcPath" value="/html/teammemberinformation/teamInformation.jsp" />
						<portlet:param name="chTeamID" value="<%=String.valueOf(currentChallengeTeam.getPrimaryKey())%>" />
						<portlet:param name="currentUserSN" value="<%=currentUser.getScreenName()%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>
					<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team") %>' url='<%=myChallengeTeam.toString() %>'/>
					
						<%
							leaderMember = ChallengeMemberLocalServiceUtil.getLeaderMemeber(currentChallengeTeam.getPrimaryKey());
							if(leaderMember.getScreenName()!=null){
								if(leaderMember.getScreenName().equals(currentUserSN)){
									curretIsLeader = true;
								}
							}
						%>
						<c:choose>
						<c:when test="<%=curretIsLeader %>">
							<portlet:actionURL name="deleteTeam" var="deleteTeam" windowState="normal">
								<portlet:param name="teamID" value="<%=String.valueOf(currentChallengeTeam.getPrimaryKey())%>" />
								<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
							</portlet:actionURL>
							<liferay-ui:icon-delete image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-delete") %>' url="<%=deleteTeam.toString() %>" />
						</c:when>
						<c:otherwise></c:otherwise>
						</c:choose>	
						<%
							curretIsLeader = false;
						%>
					</liferay-ui:icon-menu>
					
					
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>