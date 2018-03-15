<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ include file="/common/init.jsp"%>
<%
    //Tab Setting
    String tabNames = (String) request.getAttribute("tabNames");
    String listTabValue = CustomUtil.strNull(request.getAttribute("listTabValue"));
%>
<link type="text/css" rel="stylesheet" href="${contextPath}/css/jstreestyle.css"/>
<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>

<liferay-portlet:renderURL var="tabSearchURL" portletMode='view' />

<div class="h20"></div>
<div class="contabmenu">
  <edison-ui:tabs names="<%=tabNames%>" url="<%=tabSearchURL%>" tabsValues="monitoring,myfile" value="<%=listTabValue%>"
    param="tabValue" minwidth="230" />
</div>

<liferay-util:include page='<%="/WEB-INF/html/myedison/" + listTabValue + ".jsp"%>'
  servletContext="<%=this.getServletContext()%>">
</liferay-util:include>