<%@ include file="/html/init.jsp" %>


<%
	String fullUserName = "";
	if(!UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser())).getFullName().equals(null))
		fullUserName = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser())).getFullName();
%>

<blockquote>
<h3><b>Challenge List for Manager</b></h3>
<small><cite><%= fullUserName %></cite></small>
</blockquote>

<!-- data of ChallengeInformation  -->



<liferay-ui:search-container  var="searchContainer" delta="5"	deltaConfigurable="true" emptyResultsMessage="There-are-no-challenge-Information">
	<liferay-ui:search-container-results 		
		total="<%= ChallengeLocalServiceUtil.getChallengesCount() %>" 
		results="<%= ChallengeLocalServiceUtil.getChallenges(searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.Challenge" 
			keyProperty="challengeid" 
			modelVar="currentChallenge" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-name")%>' value="<%=currentChallenge.getName(themeDisplay.getLocale()) %>"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-description")%>' value="<%=currentChallenge.getDescription(request.getLocale()) %>" />
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value="/html/managerChallenge/challenge.jsp" />
						<portlet:param name="challengeID" value="<%=String.valueOf(currentChallenge.getPrimaryKey())%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>
					<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-edit") %>' url="<%=editURL.toString() %>" />
				
					<portlet:actionURL name="deleteChallenge" var="deleteChallenge" windowState="normal">
						<portlet:param name="challengeID" value="<%=String.valueOf(currentChallenge.getPrimaryKey())%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>' url="<%=deleteChallenge.toString() %>" />
					
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>

	<portlet:renderURL var="newChallengeURL">
		<portlet:param name="mvcPath" value="/html/managerChallenge/createChallenge.jsp" />
	</portlet:renderURL>
	<button class="btn btn-primary btn-large" onclick="javascript: location.href='<%=newChallengeURL.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>

