<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>

<liferay-portlet:resourceURL var="IBhandlerURL" id="handleIceBraker" copyCurrentRenderParameters="false" escapeXml="false"/>

<%
String Local_MAC_URL = "http://118.128.153.167:9090/fileDownload";
String frameURL = "/eturb-portlet/public/viewer.html";

HttpServletRequest r = PortalUtil.getHttpServletRequest(renderRequest);
String paramUrl = PortalUtil.getOriginalServletRequest(r).getParameter("url");
if (paramUrl == null || paramUrl == "") paramUrl = "_.prj";
String url = frameURL + "?serverUrl=" + IBhandlerURL + "&url=" + paramUrl;
String portletId= portletDisplay.getId(); 
%>
<style type="text/css">
div#<portlet:namespace/>viewerDiv{
	height: inherit;
	border: 1px solid #d3d3d3;
}
iframe#<portlet:namespace/>viewerFrame{
	width: 100%;
	height: inherit;
	border: 0;
}
</style>

<div id="<portlet:namespace/>viewerDiv">
	<iframe id="<portlet:namespace/>viewerFrame" src="<%= url %>" allowfullscreen></iframe>
</div>

<script type="text/javascript">
	
</script>
