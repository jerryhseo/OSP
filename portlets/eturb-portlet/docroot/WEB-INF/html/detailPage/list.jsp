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

.<portlet:namespace/>subvisualwrap{ width:100%; height:244px; overflow:hidden; background-image:url(${imagePath}); background-repeat:repeat-x; background-position:center; clear:both;}

.<portlet:namespace/>subvisualcenter{width:1220px; margin:0 auto; height:244px; position:relative; background-image:url(${imagePath}); background-repeat:no-repeat; background-position:center bottom;}

.<portlet:namespace/>subvisualtit{text-align:center; font-size:56px; color:#ecf1f9; font-weight:600; position:absolute; top:80px; width:100% }

</style>
<!-- 페이지 타이틀 & 네비게이션 -->
<div class="<portlet:namespace/>subvisualwrap">
    <div class="<portlet:namespace/>subvisualcenter">
        <div class="<portlet:namespace/>subvisualtit">
            ${subMenuName}
        </div>
    </div>
</div>

<div class="h20"></div>

