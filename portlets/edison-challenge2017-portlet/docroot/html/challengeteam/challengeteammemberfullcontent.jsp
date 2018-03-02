<%@include file="/html/init.jsp"%>

<%
	ChallengeTeamMember challengeTeamMember = 
		(ChallengeTeamMember)request.getAttribute("challengeTeamMember");
	challengeTeamMember = challengeTeamMember.toEscapedModel();	
	
	//themeDisplay.getLocale()
%>

