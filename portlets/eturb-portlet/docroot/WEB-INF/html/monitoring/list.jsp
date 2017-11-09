<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ include file="/common/init.jsp"%>

<%
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String listTabValue = CustomUtil.strNull(request.getAttribute("listTabValue"));
%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css" media="screen"/>
<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstreestyle.css" media="screen"/>

<liferay-portlet:renderURL var="tabSearchURL" portletMode='view'/>

<style type="text/css">

</style>
<div class="h20"></div>
<div class="contabmenu">
	<edison-ui:tabs names="<%=tabNames%>" url="<%=tabSearchURL%>" tabsValues="myfile,monitoring" value="<%=listTabValue%>" param="tabValue" minwidth="230"/>
</div>

<liferay-util:include page='<%= "/WEB-INF/html/monitoring/" + listTabValue + ".jsp" %>' servletContext="<%=this.getServletContext() %>">

</liferay-util:include>