<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="searchSimulationDataActionURL" name="searchSimulationDataAction">
</portlet:actionURL> 

This is the <b>Search Simulation Data</b> Main portlet in View mode.

<form action='<%= searchSimulationDataActionURL %>' method="post">
	 	<!--  <input type="hidden" name="<portlet:namespace />reqDataTypeIdVal" value="">-->
		<font>*  Select a Science App:</font>
			<select name="<portlet:namespace />reqScienceAppIdVal">
			  <option selected value="10001">2D_Comp</option>
			  <option value="10002">2D_In_Comp</option>
			  <option value="10003">gamess</option>
			  <option value="10004">KFLOW</option>
		  	  <option value="10005">KFLOW_2</option> 
			</select>
		<br/>
		
		<font>*  Type search keywords:</font>
			<input type="text" name="<portlet:namespace />reqSearchTermVal" value="RE 10" size=10 maxlength=20>
		
		<br/>
		
		<input type="submit" value="search"> <input type="reset"  value="Reset">
	</form>
	
<p><a href="<%= viewURL %>">Back</a></p>