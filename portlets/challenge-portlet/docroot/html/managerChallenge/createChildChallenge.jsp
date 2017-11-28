<%@ include file="/html/init.jsp" %>

<portlet:defineObjects />

<link rel="stylesheet" href="css/bootstrap.min.css"  media="screen">
<link rel="stylesheet" href="css/datepicker.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="assets/css/bootstrap-datetimepicker.min.css">




<!-- Current Date value-->
<%
	out.println("test challenge category id : "+ParamUtil.getLong(request, "chid")+"<br><br>");
	int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int year = Calendar.getInstance().get(Calendar.YEAR);
	
	long chaID = ParamUtil.getLong(request, "chid");
%>		


<div class="container">

	<portlet:actionURL name="createChildChallengeInformation" var="createChildChallengeInformation" windowState="exclusive" >
		<portlet:param name="mvcPath" value="/html/managerChallenge/challenge.jsp" />
		<portlet:param name="challengeID" value="<%=String.valueOf(chaID) %>" />
		<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
	</portlet:actionURL>
	<form class="form-horizontal" id="#<portlet:namespace/>childChallengeForm" action="<%=createChildChallengeInformation%>" method="POST">
		<div class="control-group">
			<label class="control-label" for="inputNum"><liferay-ui:message key="challenge-number"></liferay-ui:message></label>
			<div class="controls">
				<div class="input-append">
					<input type="text" id="<portlet:namespace/>inputNum" name="<portlet:namespace/>inputNum" value="1" readonly/>
					<button type="button" class="btn" id="numUp" onClick="javascript:this.form.<portlet:namespace/>inputNum.value++;"><i class="icon-chevron-up" ></i></button>
					<button type="button" class="btn" id="numDown" onClick="javascript:this.form.<portlet:namespace/>inputNum.value--;"><i class="icon-chevron-down" ></i></button>
				</div>	
			</div>
		</div>
		
				
		<!-- presentation day selection -->
		<div class="control-group">
			<label class="control-label" for="inputPresentationDay"><liferay-ui:message key="challenge-presentationDate"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-date name="inputPresentationDay" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>" >
				</liferay-ui:input-date>
			</div>
		</div>
		
		<!-- paper submission day selection -->		
		<div class="control-group">
			<label class="control-label" for="inputPaperDay"><liferay-ui:message key="challenge-paperDate"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-date name="inputPaperDay" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
				<liferay-ui:input-date name="inputPaperDayEnd" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
			</div>
		</div>
		
		<!-- evaluation day selection -->
		<div class="control-group">
			<label class="control-label" for="inputEvaluationDay"><liferay-ui:message key="challenge-evaluationDate"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-date name="inputEvaluationDay" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
				<liferay-ui:input-date name="inputEvaluationDayEnd" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
			</div>
		</div>
		
		
		
		<!-- Challenge day selection -->
		<div class="control-group">
			<label class="control-label" for="inputChallengeDay"><liferay-ui:message key="challenge-challengeDate"></liferay-ui:message></label>
			<div class="controls">
				<liferay-ui:input-date name="inputChallengeDay" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
				<liferay-ui:input-date name="inputChallengeDayEnd" dayValue="<%=day%>" monthValue="<%=month%>" yearValue="<%=year%>"></liferay-ui:input-date>
			</div>
		</div>
		
		<!-- Current Challenge Status -->
		<div class="control-group">
			<label class="control-label" for="inputChallengeStatus"><liferay-ui:message key="challenge-status"></liferay-ui:message></label>
			<div class="controls">
				<select name="<portlet:namespace/>inputChallengeStatus">
					<option value="Ready"><liferay-ui:message key="challenge-status-ready"></liferay-ui:message></option>
					<option value="Running"><liferay-ui:message key="challenge-status-running"></liferay-ui:message></option>
					<option value="End"><liferay-ui:message key="challenge-status-end"></liferay-ui:message></option>
				</select>
			</div>
		</div>
		
		<div class="separator"></div>
				
		<!-- submission group -->
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit">Create</button>
				<portlet:renderURL var="homeURL">
					<portlet:param name="mvcPath" value="/html/managerChallenge/challenge.jsp" />
					<portlet:param name="challengeID" value="<%=String.valueOf(chaID) %>" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<button class="btn" type="button" name="homeButton" onclick="javascript: location.href='<%=homeURL.toString() %>';" >Home</button>
			</div>
		</div>
	</form>
</div>


</body>
</html>