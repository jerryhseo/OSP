<%@include file="/html/init.jsp"%>

<%
	Challenge challenge = (Challenge)request.getAttribute("challenge_Challenge");

	challenge = challenge.toEscapedModel();
		
%>

<dl>
	<dt>Name</dt>
	<dd><%= challenge.getName(themeDisplay.getLocale()) %></dd>
	
	<dt>Field</dt>
	<dd><%= challenge.getField(themeDisplay.getLocale())  %></dd>
	
	<dt>Description</dt>
	<dd><%= challenge.getDescription(themeDisplay.getLocale())  %></dd>
</dl>

<br>
content view 