<%@include file="/html/init.jsp"%>


<%
	Challenge challenge = (Challenge) renderRequest.getAttribute(WebKeys.CHALLENGE);
	long childChallengeId = ParamUtil.getLong(renderRequest, "childChallengeId");
	ChildChallenge childChallenge = null;
	String challengeFieldName = challenge.getField(themeDisplay.getLocale());
	
	if (childChallengeId > 0) {
		childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(childChallengeId);
		Challenge challenge2 = ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId());
		
		challengeFieldName = challenge2.getField(themeDisplay.getLocale());
		
	}else{
		
	}
	Calendar calendar = Calendar.getInstance();
	int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int year = Calendar.getInstance().get(Calendar.YEAR);
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/challengeadmin/view.jsp"></portlet:param>
	<portlet:param name="challengeFieldName" value="<%= challengeFieldName %>"/>
</portlet:renderURL>

<portlet:actionURL name="addChildChallenge" var="addChildChallengeURL">
	<portlet:param name="challengeId" value="<%= String.valueOf(challenge.getChallengeId()) %>"/>
	<portlet:param name="childChallengeId" value="<%= String.valueOf(childChallengeId) %>"/>
	<portlet:param name="challengeFieldName" value="<%= challengeFieldName %>"/>
</portlet:actionURL>

<aui:form class="form-horizontal" name="#<portlet:namespace/>childChallengeForm" action="<%=addChildChallengeURL%>" method="POST">
	<aui:model-context bean="<%= childChallenge %>" model="<%= ChildChallenge.class %>" />
	<div class="control-group">
		<label class="control-label" for="inputNum"><%=challengeFieldName %></label>
		<br><br>
		<label class="control-label" for="inputNum"><liferay-ui:message key="challenge-number"></liferay-ui:message></label>
		<div class="controls">
			<div class="input-append">
				<input type="text" id="<portlet:namespace/>inputNum" name="<portlet:namespace/>inputNum" value='<%=(childChallenge!=null)? childChallenge.getNumber() : 1 %>' readonly/>
				<button type="button" class="btn" id="numUp" onClick="javascript:this.form.<portlet:namespace/>inputNum.value++;"><i class="icon-chevron-up" ></i></button>
				<button type="button" class="btn" id="numDown" onClick="javascript:this.form.<portlet:namespace/>inputNum.value--;"><i class="icon-chevron-down" ></i></button>
			</div>	
		</div>
	</div>
		
				
	<!-- presentation day selection -->
	<div class="control-group">
		<label class="control-label" for="inputPresentationDay"><liferay-ui:message key="challenge-presentationDate"></liferay-ui:message></label>
		<div class="controls">
			<%if(childChallenge != null){
				calendar.setTime(childChallenge.getPresentationDay());
			}
			%>
			<liferay-ui:input-date name="inputPresentationDay" 
				dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" 
				monthValue="<%=calendar.get(Calendar.MONTH)%>" 
				yearValue="<%=calendar.get(Calendar.YEAR)%>" >
			</liferay-ui:input-date>
		</div>
	</div>
	
	<!-- paper submission day selection -->		
	<div class="control-group">
		<label class="control-label" for="inputPaperDay"><liferay-ui:message key="challenge-paperDate"></liferay-ui:message></label>
		<div class="controls">
			<%	
				if(childChallenge != null){
					calendar.setTime(childChallenge.getPaperStartDay());
				}
			%>
			<liferay-ui:input-date name="inputPaperDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
			<%
				if(childChallenge != null){
					calendar.setTime(childChallenge.getPaperEndDay());
				}
			%>
			<liferay-ui:input-date name="inputPaperDayEnd" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
		</div>
	</div>
		
	<!-- evaluation day selection -->
	<div class="control-group">
		<label class="control-label" for="inputEvaluationDay"><liferay-ui:message key="challenge-evaluationDate"></liferay-ui:message></label>
		<div class="controls">
			<%if(childChallenge != null){
				calendar.setTime(childChallenge.getEvaluationDay());
			}
			%>
			<liferay-ui:input-date name="inputEvaluationDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
		</div>
	</div>
	
		
		
	<!-- Challenge day selection -->
	<div class="control-group">
		<label class="control-label" for="inputChallengeDay"><liferay-ui:message key="challenge-challengeDate"></liferay-ui:message></label>
		<div class="controls">
			<%if(childChallenge != null){
				calendar.setTime(childChallenge.getChallengeStartDay());
			}
			%>
			<liferay-ui:input-date name="inputChallengeDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
			<%if(childChallenge != null){
				calendar.setTime(childChallenge.getChallengeEndDay());
			}
			%>
			<liferay-ui:input-date name="inputChallengeDayEnd" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
		</div>
	</div>
		
	<!-- Current Challenge Status -->
	<div class="control-group">
		<label class="control-label" for="inputChallengeStatus"><liferay-ui:message key="challenge-status"></liferay-ui:message></label>
		<div class="controls">
			<select name="<portlet:namespace/>inputChallengeStatus" id="<portlet:namespace/>inputChallengeStatus">
				<option value="Ready"><liferay-ui:message key="challenge-status-ready"></liferay-ui:message></option>
				<option value="Running"><liferay-ui:message key="challenge-status-running"></liferay-ui:message></option>
				<option value="End"><liferay-ui:message key="challenge-status-end"></liferay-ui:message></option>
			</select>
		</div>
	</div>
	<script>
		$('#<portlet:namespace/>inputChallengeStatus').val('<%=(childChallenge!=null)?childChallenge.getChallengeStatus(): 1 %>');
	</script>
				
	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryCategorizationPanel" persistState="<%= true %>" title="categorization">
	<aui:fieldset>
		<aui:input name="categories" type="assetCategories" />
		
		<aui:input name="tags" type="assetTags" />
	</aui:fieldset>
	</liferay-ui:panel>
	
	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="<%= ChildChallenge.class.getName() %>" classPK="<%= childChallengeId %>"/>
		</aui:fieldset>
	</liferay-ui:panel>
	
	<aui:button-row>
		<aui:button type="submit" id="save"></aui:button>
		
		<aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
	</aui:button-row>
</aui:form>


