<%@ include file="/html/init.jsp" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>



<%
	//out.println(ParamUtil.getLong(renderRequest, "cchID"));
	//out.println(renderRequest.getAttribute("cchID"));
	
	long cchID = 0;
	cchID = ParamUtil.getLong(renderRequest, "cchID");
	if(cchID == 0)
		cchID = Long.parseLong((String)renderRequest.getAttribute("cchID"));
	User currentUser = UserServiceUtil.getUserById(Long.parseLong(renderRequest.getRemoteUser()));
	renderRequest.setAttribute("cchID", String.valueOf(cchID));
	
	
%>

<c:choose>
<c:when test="<%=cchID == -1 %>">

<%
List<ChildChallenge> childList = ChildChallengeLocalServiceUtil.getRunningChild();

int[] admteamNum = new int[childList.size()];
List<ChallengeTeam> admteamList[] = new List[childList.size()];
List<PortletURL> managerURL= new ArrayList<PortletURL>();
String[] currentParamList = new String[childList.size()];
int i=0;

List<String> tabNameList = new ArrayList<String>();
String tabNames = "";



String curtabName = "";
curtabName = ParamUtil.getString(request, "curtabName");
request.setAttribute("curtabName", curtabName);
if(curtabName.equals("") || curtabName.equals(null)){
	curtabName = ChallengeLocalServiceUtil.getChallenge(childList.get(3).getPrimaryKey()).getDescription(themeDisplay.getLocale());
	request.setAttribute("curtabName", curtabName);
}
System.out.println("current tab name : " + curtabName +"\n"+tabNames + "\n\n\n\n");
%>


<%
for(int index=0; index<childList.size(); index++){
	tabNameList.add(index, ChallengeLocalServiceUtil.getChallenge(childList.get(index).getPrimaryKey()).getDescription(themeDisplay.getLocale()));
	tabNames += tabNameList.get(index);
	//out.println("<li><a href='#tabs-"+tabNameList.get(index)+"'>"+tabNameList.get(index)+"</a></li>");
}

%>

<!-- 


 -->


<%
for(ChildChallenge child : childList){
	admteamList[i]  = ChallengeTeamLocalServiceUtil.getChallengeTeamByChild(child.getPrimaryKey());	
	admteamNum[i] = admteamList[i].size();
	managerURL.add(i, renderResponse.createRenderURL());
	managerURL.get(i).setParameter("mvcPath", "/html/teammanagement/view.jsp");
	managerURL.get(i).setParameter("cchID", String.valueOf(cchID));
	managerURL.get(i).setParameter("redirect", PortalUtil.getCurrentURL(renderRequest));
	managerURL.get(i).setParameter("curtabName", ChallengeLocalServiceUtil.getChallenge(child.getPrimaryKey()).getDescription(themeDisplay.getLocale()));
	currentParamList[i] = ("storedCurrent"+i);
	
%>

<blockquote>
<h3><b><%=ChallengeLocalServiceUtil.getChallenge(child.getPrimaryKey()).getDescription(themeDisplay.getLocale()) %> <%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-manage") %></b></h3>
</blockquote>

<liferay-ui:search-container curParam="<%=currentParamList[i] %>" delta="5" iteratorURL="<%=managerURL.get(i) %>" deltaConfigurable="true">
	<liferay-ui:search-container-results 		
		total="<%= admteamNum[i] %>" 
		results="<%= ListUtil.subList(admteamList[i], searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChallengeTeam" 
			modelVar="currentTeam" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-name") %>' value='<%=currentTeam.getTeamName(themeDisplay.getLocale())%>'/>
			<%
				//team member list 
				List<ChallengeMember> chmemberList = ChallengeMemberLocalServiceUtil.getChallengeMemberByTeam(currentTeam.getPrimaryKey());
				
				String userSNList = "";
				String userMailList = "";
				String userUniversityList = "";
				for(ChallengeMember member : chmemberList){
					userSNList += member.getScreenName();
					userSNList += "<br>";
					userMailList += member.getEmail();
					userMailList += "<br>";
					userUniversityList += member.getFalculty(themeDisplay.getLocale());
					userUniversityList += "<br>";
				}
						
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teammember") %>' value='<%=userSNList%>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-email") %>' value='<%=userMailList%>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-univ") %>' value='<%=userUniversityList%>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-user-papername") %>' value='<%=currentTeam.getPaperName(themeDisplay.getLocale())%>'/>
			<liferay-ui:search-container-column-text name='App Name' value='<%=ChallengeTeamLocalServiceUtil.getTeamAppList(currentUser.getCompanyId(), currentTeam.getPrimaryKey())%>'/>
			<liferay-ui:search-container-column-text name='CPU' value='<%=ChallengeTeamLocalServiceUtil.getTeamCPUUseage(currentUser.getCompanyId(), currentTeam.getPrimaryKey())%>'/>
			
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-manage") %>'>			
			<liferay-ui:icon-menu>
				<portlet:renderURL var="myChallengeTeam">
					<portlet:param name="mvcPath" value="/html/teammanagement/teammanagement.jsp" />
					<portlet:param name="chTeamID" value="<%=String.valueOf(currentTeam.getPrimaryKey())%>" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team") %>' url='<%=myChallengeTeam.toString() %>'/>
			</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
			
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>
<%
	i++;
%>

<%
}
%>

</c:when>

<c:otherwise>
<%
	if(cchID == 0)
		cchID = Long.parseLong((String)renderRequest.getAttribute("cchID"));
	List<ChallengeTeam> teamList = ChallengeTeamLocalServiceUtil.getChallengeTeamByChild(cchID);
	long chID = ChildChallengeLocalServiceUtil.getChildChallenge(cchID).getChallengeid();
	
	int teamNum = teamList.size();
%>
<blockquote>
<h3><b><%=ChallengeLocalServiceUtil.getChallenge(chID).getDescription(themeDisplay.getLocale()) %> <%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-manage") %></b></h3>
</blockquote>

<liferay-portlet:renderURL varImpl="interatorTeamListURL">
	<portlet:param name="mvcPath" value="/html/teammanagement/view.jsp" />
	<portlet:param name="cchID" value="<%=String.valueOf(cchID) %>" />
	<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container delta="10" iteratorURL="<%=interatorTeamListURL %>" deltaConfigurable="true" emptyResultsMessage="There-are-no-Team-to-Manage">
	<liferay-ui:search-container-results 		
		total="<%= teamNum %>" 
		results="<%= ListUtil.subList(teamList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
		<liferay-ui:search-container-row
			className="edison.challenge.service.builder.model.ChallengeTeam" 
			modelVar="currentTeam" 
		>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-name") %>' value='<%=currentTeam.getTeamName(themeDisplay.getLocale())%>'/>
			<%
				//team member list 
				List<ChallengeMember> chmemberList = ChallengeMemberLocalServiceUtil.getChallengeMemberByTeam(currentTeam.getPrimaryKey());
				
				String userSNList = "";
				String userMailList = "";
				String userUniversityList = "";
				for(ChallengeMember member : chmemberList){
					userSNList += member.getScreenName();
					userSNList += "<br>";
					userMailList += member.getEmail();
					userMailList += "<br>";
					userUniversityList += member.getFalculty(themeDisplay.getLocale());
					userUniversityList += "<br>";
				}
						
			%>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-teammember") %>' value='<%=userSNList%>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-email") %>' value='<%=userMailList%>'/>
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-univ") %>' value='<%=userUniversityList%>'/>
			<liferay-ui:search-container-column-text name='App Name' value='<%=ChallengeTeamLocalServiceUtil.getTeamAppList(currentUser.getCompanyId(), currentTeam.getPrimaryKey())%>'/>
			<liferay-ui:search-container-column-text name='CPU' value='<%=ChallengeTeamLocalServiceUtil.getTeamCPUUseage(currentUser.getCompanyId(), currentTeam.getPrimaryKey())%>'/>
			
			
			<liferay-ui:search-container-column-text name='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team-manage") %>'>			
			<liferay-ui:icon-menu>
				<portlet:renderURL var="myChallengeTeam">
					<portlet:param name="mvcPath" value="/html/teammanagement/teammanagement.jsp" />
					<portlet:param name="chTeamID" value="<%=String.valueOf(currentTeam.getPrimaryKey())%>" />
					<portlet:param name="redirect" value="<%=PortalUtil.getCurrentURL(renderRequest) %>" />
				</portlet:renderURL>
				<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(themeDisplay.getLocale(), "challenge-team") %>' url='<%=myChallengeTeam.toString() %>'/>
			</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
			
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
</liferay-ui:search-container>
</c:otherwise>
</c:choose>





<script type="text/javascript">
<!--

//-->

$("#tabs").tabs({
    activate: function (event, ui) {
        var active = $('#tabs').tabs('option', 'active');
        $("#tabid").html('the tab id is ' + $("#tabs ul>li a").eq(active).attr("href"));

    }
}

);
</script>