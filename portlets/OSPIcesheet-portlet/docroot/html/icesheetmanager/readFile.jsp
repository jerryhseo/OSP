<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="readFileActionURL" name="readFileAction">
</portlet:actionURL> 

This is the <b>File Reader</b> portlet in View mode.
	<form action='<%= readFileActionURL %>' method="post">
		<font>Type output file path:</font>
		<input size="50" type="text" value="/usr/liferay/liferay-portal-6.2/tomcat-7.0.62/NOTICE" name="<portlet:namespace />reqOutputFilePath"> 
		<br/>
		<input type="submit" value="Browse"> 
		<!-- 
		<br/>
		<input type="reset"  value="Reset">
		-->
	</form> 

<p><a href="<%= viewURL %>">Back</a></p>