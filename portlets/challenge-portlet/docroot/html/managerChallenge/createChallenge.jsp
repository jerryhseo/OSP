<%@ include file="/html/init.jsp" %>


<h3><b>Create New Challenge by Manager</b></h3><br>

<div class="container">

	<portlet:actionURL name="createChallenge" var="createChallenge" windowState="normal" />
	<form class="form-horizontal" id="#<portlet:namespace/>challengeForm" action="<%=createChallenge%>" method="POST">
		<div class="control-group">
			<label class="control-label" for="inputName"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-name") %></label>
			<div class="controls">
				<liferay-ui:input-localized name="inputName" xml=""></liferay-ui:input-localized>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="inputDescription"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-description") %></label>
			<div class="controls">
				<liferay-ui:input-localized name="inputDescription" xml="" type="textarea"></liferay-ui:input-localized>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-create") %></button>
				<portlet:renderURL var="homeURL">
					<portlet:param name="mvcPath" value="/html/managerChallenge/view.jsp" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<button class="btn" type="button" name="homeButton" onclick="javascript: location.href='<%=homeURL.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-home") %></button>
			</div>
		</div>
	</form>
</div>

