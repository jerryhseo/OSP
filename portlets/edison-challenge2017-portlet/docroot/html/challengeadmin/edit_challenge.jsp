<%@include file = "/html/init.jsp" %>

<%
	Challenge challenge = null;

	long challengeId = ParamUtil.getLong(request, "challengeId");

	if (challengeId > 0) {
		challenge = ChallengeLocalServiceUtil.getChallenge(challengeId);
	}
	
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= challenge == null ? "addChallenge" : "updateChallenge" %>' var="editChallengeURL" />


<aui:form action="<%= editChallengeURL %>" name="<portlet:namespace />fm">
	<aui:model-context bean="<%= challenge %>" model="<%= Challenge.class %>" />

	<div>
		<input type="hidden" name="<portlet:namespace />challengeId" value='<%= challenge == null ? "" : challenge.getChallengeId() %>'></input>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputName"><%=LanguageUtil.get(pageContext, "challenge-name") %></label>
		<div class="controls">
			<liferay-ui:input-localized name="inputName" xml='<%=challenge == null ? "" : challenge.getName() %>'></liferay-ui:input-localized>
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="inputField"><%=LanguageUtil.get(pageContext, "challenge-field") %></label>
		<div class="controls">
			<liferay-ui:input-localized name="inputField" xml='<%=challenge == null ? "" : challenge.getField() %>'></liferay-ui:input-localized>
		</div>
	</div>
		
	<div class="control-group">
		<label class="control-label" for="inputDescription"><%=LanguageUtil.get(pageContext, "challenge-description") %></label>
		<div class="controls">
			<liferay-ui:input-localized name="inputDescirption" xml='<%=challenge == null ? "" : challenge.getDescription() %>' type="textarea"></liferay-ui:input-localized>
		</div>
	</div>
        
		<liferay-ui:asset-categories-error />
		<liferay-ui:asset-tags-error />
	    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="challengeCategorizationPanel" persistState="<%= true %>" title="categorization">
			<aui:fieldset>
				<aui:input name="categories" type="assetCategories" />

				<aui:input name="tags" type="assetTags" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="challengeAssetLinksPanel" persistState="<%= true %>" title="related-assets">
			<aui:fieldset>
				<liferay-ui:input-asset-links
					className="<%= Challenge.class.getName() %>"
					classPK="<%= challengeId %>"
				/>
			</aui:fieldset>
		</liferay-ui:panel>

		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit" ><%=challenge == null ? LanguageUtil.get(pageContext, "challenge-create") : LanguageUtil.get(pageContext, "challenge-update") %></button>
				<button class="btn" type="button" name="cancelButton" onclick="javascript: location.href='<%=viewURL.toString() %>';" ><%=LanguageUtil.get(pageContext, "challenge-cancel") %></button>
			</div>
		</div>

</aui:form>
