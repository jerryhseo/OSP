<%@ include file="/html/init.jsp" %>


<blockquote>
<h3><b>Challenge information </b> Challenge</h3><br>
</blockquote>



<%
	long ccID = ParamUtil.getLong(request, "ccid");
	long cID = ParamUtil.getLong(request, "cid");
	
	//out.println("test challenge id : "+ cID);
	//out.println("test child challenge id : "+ ccID);
	
	ChildChallenge cchup = ChildChallengeLocalServiceUtil.getChildChallenge(ccID);
	
	String getStatusStr = cchup.getStatus();
	if(getStatusStr.equals(null))
		getStatusStr = "Ready";
	
	
	Calendar calendar = Calendar.getInstance();
%>


<div class="container">

	<portlet:actionURL name="updateChildChallenge" var="updateChildChallenge" windowState="exclusive" >
		<portlet:param name="childChallengeID" value="<%=String.valueOf(ccID) %>" />
		<portlet:param name="challengeID" value="<%=String.valueOf(cID) %>" />
		<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:actionURL>
	<form class="form-horizontal" id="#<portlet:namespace/>childChallengeUpdateForm" action="<%=updateChildChallenge%>" method="POST">
		<div class="control-group">
			<label class="control-label" for="inputNum"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-number") %></label>
			<div class="controls">
				<div class="input-append">
					<input type="text" id="<portlet:namespace/>inputNum" name="<portlet:namespace/>inputNum" value="<%=cchup.getNumber() %>" readonly/>
					<button type="button" class="btn" id="numUp" onClick="javascript:this.form.<portlet:namespace/>inputNum.value++;"><i class="icon-chevron-up" ></i></button>
					<button type="button" class="btn" id="numDown" onClick="javascript:this.form.<portlet:namespace/>inputNum.value--;"><i class="icon-chevron-down" ></i></button>
				</div>	
			</div>
		</div>
		
				
		<!-- presentation day selection -->
		<div class="control-group">
			<label class="control-label" for="inputPresentationDay"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-presentationDate") %></label>
			<div class="controls">
				<% 
					calendar.setTime(cchup.getPresentationDay());
				%>
				<liferay-ui:input-date name="inputPresentationDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>" >
				</liferay-ui:input-date>
			</div>
		</div>
		
		<!-- paper submission day selection -->		
		<div class="control-group">
			<label class="control-label" for="inputPaperDay"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-paperDate") %></label>
			<div class="controls">
				<% 
					calendar.setTime(cchup.getPaperStartDay());
				%>
				<liferay-ui:input-date name="inputPaperDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
				<% 
					calendar.setTime(cchup.getPaperEndDay());
				%>
				<liferay-ui:input-date name="inputPaperDayEnd" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
			</div>
		</div>
		
		<!-- evaluation day selection -->
		<div class="control-group">
			<label class="control-label" for="inputEvaluationDay"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-evaluationDate") %></label>
			<div class="controls">
				<% 
					calendar.setTime(cchup.getEvaluationStartDay());
				%>
				<liferay-ui:input-date name="inputEvaluationDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
				<% 
					calendar.setTime(cchup.getEvaluationEndDay());
				%>
				<liferay-ui:input-date name="inputEvaluationDayEnd" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
			</div>
		</div>
		
		
		
		<!-- Challenge day selection -->
		<div class="control-group">
			<label class="control-label" for="inputChallengeDay"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-challengeDate") %></label>
			<div class="controls">
				<% 
					calendar.setTime(cchup.getChallengeStartDay());
				%>
				<liferay-ui:input-date name="inputChallengeDay" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
				<% 
					calendar.setTime(cchup.getChallengeEndDay());
				%>
				<liferay-ui:input-date name="inputChallengeDayEnd" dayValue="<%=calendar.get(Calendar.DAY_OF_MONTH)%>" monthValue="<%=calendar.get(Calendar.MONTH)%>" yearValue="<%=calendar.get(Calendar.YEAR)%>"></liferay-ui:input-date>
			</div>
		</div>
		
		<!-- Current Challenge Status -->
		<div class="control-group">
			<label class="control-label" for="inputChallengeStatus"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-status") %></label>
			<div class="controls">
				<select name="<portlet:namespace/>inputChallengeStatus" id="<portlet:namespace/>inputChallengeStatus">
					<option value="Ready"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-status-ready") %></option>
					<option value="Running"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-status-running") %></option>
					<option value="End"><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-status-end") %></option>
				</select>
			</div>
		</div>
		<script>
			$('#<portlet:namespace/>inputChallengeStatus').val("<%=ChildChallengeLocalServiceUtil.getChildChallenge(ccID).getStatus()%>");
		</script>
		<div class="separator"></div>
				
		<!-- submission group -->
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-update") %></button>
				<portlet:renderURL var="homeURL">
					<portlet:param name="mvcPath" value="/html/managerChallenge/challenge.jsp" />
					<portlet:param name="challengeID" value="<%=String.valueOf(cID) %>" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<button class="btn" type="button" name="homeButton" onclick="javascript: location.href='<%=homeURL.toString() %>';" ><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-home") %></button>
			</div>
		</div>
	</form>
</div>
