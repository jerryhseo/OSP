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
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<portlet:actionURL var="retrieveOutputActionURL" name="retrieveOutputAction">
</portlet:actionURL> 

<%
	String jobData = "{\"-param\":{\"port_data\":\"v0 = 14.5 ; thetaV0 = 90 ; thetaE0 = 0 ; thetaJ0 = 160 ;\",\"editor_type\":\"Inputdeck\"}}";
%>

This is the <b>Output Retrieval</b> portlet in View mode.

<form action='<%= retrieveOutputActionURL %>' method="post">
	<font>Choose a Science App:</font>
		<select name="<portlet:namespace />reqAppName">
		 	<option value="WaveSimulation">WaveSimulation(Nano)</option>
	 	 	<option value="rollerCoaster">rollerCoaster(Nano)</option>
	 	 	<option value="gravityslingshot" selected>gravityslingshot(Nano)</option>
	 	 	<option value="pianostring">pianostring(Nano)</option>
	 	 	<option value="PhaseDiagramSW">PhaseDiagramSW(Nano)</option>
	 	 	<option value="roundSTMtip">roundSTMtip(Nano)</option>
	 	 	<option value="acuteSTMtip">acuteSTMtip(Nano)</option>
		 	<option value="Bowling">Bowling(Nano)</option>
 		</select>
 		<br/>
	<font>Choose a Science Version:</font>
		<select name="<portlet:namespace />reqAppVersion">
			<option value="1.0.0" selected>1.0.0</option>
		</select>
	<br/>
 		
	<font>Provide job data:</font>
		<textarea rows="20" cols="1000" name="<portlet:namespace />reqJobData"><%= jobData %></textarea>
	<br/>
	<input type="submit" value="Search"> 
	<!-- 
	<br/>
	<input type="reset"  value="Reset">
	-->
</form> 

<p><a href="<%= viewURL %>">Back</a></p>