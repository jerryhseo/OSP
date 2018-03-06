<%@include file="/html/init.jsp"%>

<%
	ChildChallenge childChallenge = (ChildChallenge)request.getAttribute("challenge_ChildChallenge");

	childChallenge = childChallenge.toEscapedModel();
	String fieldName = ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId()).getField(themeDisplay.getLocale());
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
	
%>

<dl>
	<dt>Name</dt>
	<dd><%= String.valueOf(childChallenge.getNumber()) +"th "+ fieldName %></dd>
	
	<dt>Date</dt>
	<dd><%= dateFormat.format(childChallenge.getChallengeStartDay())  %></dd>
	<dd><%= dateFormat.format(childChallenge.getChallengeEndDay()) %></dd>
</dl>

<br>
content view 