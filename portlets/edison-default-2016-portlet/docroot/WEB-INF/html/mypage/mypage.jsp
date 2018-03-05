<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>

<%@ include file="/common/init.jsp" %>
<liferay-portlet:renderURL var="renderUrl" portletMode='view'>
</liferay-portlet:renderURL>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css" media="screen"/>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/mypage.css" media="screen"/>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
	String clickTab = GetterUtil.getString(request.getAttribute("clickTab"));
	String redirectURL = GetterUtil.get(request.getAttribute("redirectURL"), "");
	String jspFile = "";
	String queryString = "";
	if(clickTab.equals("favoriteApp")){
		jspFile = "edisonfavoriteApp_WAR_edisonappstore2016portlet";
	}else if(clickTab.equals("siteJoin")){
		jspFile = "edisonsitejoin_WAR_edisondefault2016portlet_INSTANCE_URMq65jsLOe1";
	}else if(clickTab.equals("myCourse")){
		jspFile = "edisonvirtuallabregistrationlist_WAR_edisonvirtuallab2016portlet";
	}else if(clickTab.equals("myClass")){
		jspFile = "edisonvirtuallabclassregistrationlist_WAR_edisonvirtuallab2016portlet";
	}else if(clickTab.equals("myWorkspace")){
		jspFile = "edisonworkspace_WAR_edisonappstore2016portlet";
	}else if(clickTab.equals("myFile")){
		jspFile = "edisonmyfile_WAR_edisonsimulationportlet";
	}else if(clickTab.equals("myApp")){
		jspFile = "scienceappmanager_WAR_edisonappstore2016portlet";
	}
	else if(clickTab.equals("myContent")){
		jspFile = "edisoncontent_WAR_edisoncontent2016portlet";
	}else if(clickTab.equals("myProject")){
		jspFile = "edisonsimulationproject_WAR_edisonsimulationproject2017portlet";
	}else if(clickTab.equals("myDataType")){
		jspFile = "edisondatatypeeditor_WAR_edisonappstore2016portlet";
	}else if(clickTab.equals("myHistory")){
		jspFile = "edisonprofessormanagement_WAR_edisonvirtuallab2016portlet";
	}else if(clickTab.equals("eturbMyFile")){
		jspFile = "simulationmonitoringtreeview_WAR_eturbportlet";
	}
%>
<div>
	<div class="mpleft" >
		${tabStr}
	</div>
	<div class="mprightcont">
		<liferay-portlet:runtime portletName="<%=jspFile %>" />
	</div>
</div>
<script type="text/javascript">
function tabAction(tabValue){
	var searchParameter = "";
	searchParameter += "&<portlet:namespace/>clickTab="+tabValue;
	location.href="<%=renderUrl%>"+searchParameter;
}
	
</script>
