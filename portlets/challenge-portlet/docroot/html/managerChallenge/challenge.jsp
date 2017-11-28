<%@ include file="/html/init.jsp" %>



<%
	long challengeID = ParamUtil.getLong(request, "challengeID");
	Challenge ch = ChallengeLocalServiceUtil.getChallenge(challengeID);
%>


<blockquote>
<h3><b><%=ch.getName(themeDisplay.getLocale()) %> </b> Challenge</h3><br>
<small><cite>manager by <%=UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser())).getFullName() %></cite></small>
</blockquote>


<!-- Challenge Category Filed -->

<div class="container">

	<portlet:actionURL name="updateChallenge" var="updateChallenge" windowState="normal" >
		<portlet:param name="challengePK" value="<%=String.valueOf(challengeID)%>" />
		<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:actionURL>
	<form class="form-horizontal" id="#<portlet:namespace/>challengeForm2" action="<%=updateChallenge%>" method="POST">
		<div class="control-group">
			<label class="control-label" for="inputName"><liferay-ui:message key="challenge-name"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-localized xml="<%=ch.getName() %>" name="inputName" ></liferay-ui:input-localized>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="inputDescription"><liferay-ui:message key="challenge-description"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-localized xml="<%=ch.getDescription() %>" name="inputDescription" type="textarea" ></liferay-ui:input-localized>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-update")%></button>
				<portlet:renderURL var="homeURL">
					<portlet:param name="mvcPath" value="/html/managerChallenge/view.jsp" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<button class="btn" type="button" name="homeButton" onclick="javascript: location.href='<%=homeURL.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-home") %></button>
			</div>
		</div>
	</form>
</div>


<div class="separator"></div>

<!-- data of ChallengeInformation  -->
<%
	List<ChildChallenge> childChallengeFullList = ChildChallengeLocalServiceUtil.getChildByChallenge(challengeID);
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<liferay-portlet:renderURL varImpl="interatorChildURL">
	<portlet:param name="mvcPath" value="/html/managerChallenge/challenge.jsp" />
	<portlet:param name="challengeID" value="<%=String.valueOf(challengeID) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container iteratorURL="<%=interatorChildURL %>" delta="5" deltaConfigurable="true" emptyResultsMessage="There-are-no-Child-challenge-Information">
	<liferay-ui:search-container-results		
		total="<%=childChallengeFullList.size() %>"
		results="<%= ListUtil.subList(childChallengeFullList, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChildChallenge" 
			modelVar="currentChildChallenge" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-number") %>' property="number"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-startday") %>' property="challengeStartDay"/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-endday") %>' property="challengeEndDay"/>
			<liferay-ui:search-container-column-text>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value="/html/managerChallenge/childChallenge.jsp" />
						<portlet:param name="ccid" value="<%=String.valueOf(currentChildChallenge.getPrimaryKey())%>" />
						<portlet:param name="cid" value="<%=String.valueOf(challengeID)%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:renderURL>
					<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-edit") %>' url="<%=editURL.toString() %>" />

					<portlet:actionURL name="deleteChildChallenge" var="deleteChildChallenge" windowState="normal">
						<portlet:param name="ccID" value="<%=String.valueOf(currentChildChallenge.getPrimaryKey())%>" />
						<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete image="delete" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-delete") %>' url="<%=deleteChildChallenge.toString() %>" />
					
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>




<!-- Create New Child Challenge -->

<portlet:renderURL var="newChildChallengeURL">
	<portlet:param name="mvcPath" value="/html/managerChallenge/createChildChallenge.jsp" />
	<portlet:param name="chid" value="<%=String.valueOf(challengeID) %>" />
</portlet:renderURL>
<button class="btn btn-primary" onclick="javascript: location.href='<%=newChildChallengeURL.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>


