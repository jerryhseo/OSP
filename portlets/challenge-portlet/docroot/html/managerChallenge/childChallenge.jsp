<%@page import="javax.portlet.filter.ActionResponseWrapper"%>
<%@page import="com.liferay.portlet.PortletURLUtil"%>
<%@ include file="/html/init.jsp" %>

<%
	Challenge ch = ChallengeLocalServiceUtil.getChallenge(ParamUtil.getLong(request, "cid"));
	ChildChallenge cch = ChildChallengeLocalServiceUtil.getChildChallenge(ParamUtil.getLong(request, "ccid"));

	String fullUserName = "";
	if(!UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser())).getFullName().equals(null))
		fullUserName = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser())).getFullName();
%>


<blockquote>
<h3><b><%=LanguageUtil.get(themeDisplay.getLocale(), "challenge") %></b> <%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-update") %> </h3><br>
<small><cite><%=fullUserName %></cite></small>
</blockquote>

<%
	String selectedTab = ParamUtil.getString(request, "seletTab");
	if(selectedTab.equals(null))
		selectedTab = "Award";
	//actionRequest.setAttribute("tabViewofChild", selectedTab); 
	//actionResponse.setRenderParameter("tabViewofChild", selectedTab); 
	
%>

<liferay-portlet:renderURL var="portletURL">
 <liferay-portlet:param name="tabViewofChild" value="<%=selectedTab %>"></liferay-portlet:param>
</liferay-portlet:renderURL>

<liferay-ui:tabs names="Challenge, Award, Agency" refresh="<%=false %>" tabsValues="Challenge, Award, Agency"
	param="tabViewofChild" value="<%=selectedTab %>" url="<%=portletURL %>">
	<liferay-ui:section>
		<%@ include file="/html/managerChallenge/challengeInformation/challengeView.jsp" %>
	</liferay-ui:section>
	
	<liferay-ui:section>
		<%@ include file="/html/managerChallenge/challengeInformation/award.jsp" %>
	</liferay-ui:section>
	
	<liferay-ui:section>
		<%@ include file="/html/managerChallenge/challengeInformation/agency.jsp" %>
	</liferay-ui:section>

</liferay-ui:tabs>
