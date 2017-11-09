<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ include file="/common/init.jsp"%>

<%
	//Tab Setting
	String tabNames = (String)request.getAttribute("tabNames");
	String listTabValue = CustomUtil.strNull(request.getAttribute("listTabValue"));
%>

<!-- nav style -->
 
<liferay-portlet:renderURL var="tabSearchURL" portletMode='view'/>

<style type="text/css">

</style>
<!-- 페이지 타이틀 & 네비게이션 -->
<div class="virtitlebox">
    <%-- <img src="${contextPath}/images/title_virtual.png" width="20" height="20" /> 
	<div class="virtitle">MyFile</div> --%>
</div>

<div class="h20"></div>

<liferay-util:include page='<%= "/WEB-INF/html/myfile/" + listTabValue + ".jsp" %>' servletContext="<%=this.getServletContext() %>">
</liferay-util:include>
