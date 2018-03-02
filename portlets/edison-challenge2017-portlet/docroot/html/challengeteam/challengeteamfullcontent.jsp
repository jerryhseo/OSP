<%@include file="/html/init.jsp"%>

<%
	ChallengeTeam challengeTeam = (ChallengeTeam)request.getAttribute("challengeTeam");
	challengeTeam = challengeTeam.toEscapedModel();	
%>


<dl>
	<dt>Name</dt>
	<dd><%=challengeTeam.getTeamName()%></dd>
	
	<dt>Paper Name</dt>
	<dd><%=challengeTeam.getPaperName(themeDisplay.getLocale())  %></dd>
	
	<dt>Paper Abstract</dt>
	<dd><%=challengeTeam.getPaperAbstract(themeDisplay.getLocale())  %></dd>
</dl>