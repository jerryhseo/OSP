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

<portlet:actionURL var="browseSimulationsActionURL" name="browseSimulationsAction">
</portlet:actionURL> 

This is the <b>Simulation Browse</b> portlet in View mode.
	<form action='<%= browseSimulationsActionURL %>' method="post">
		<font>Choose a Science App:</font>
			<select name="<portlet:namespace />reqAppName">
			 	<option value="WaveSimulation" selected>WaveSimulation(Nano)</option>
		 	 	<option value="rollerCoaster">rollerCoaster(Nano)</option>
		 	 	<option value="gravityslingshot">gravityslingshot(Nano)</option>
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
		<font>Search before:</font>
			<select name="<portlet:namespace />reqSearchStartDate">
				<option value=""></option>
		 	 	<option value="7">1 week</option>
		 	 	<option value="14">2 weeks</option>
		 	 	<option value="30">1 month</option>
		 	 	<option value="60" selected>2 months</option>
		 	 	<option value="90">3 months</option>
		 	 	<option value="180">6 months</option>
		 	 	<option value="all">all</option>
			</select>
		<br/>
		<font>How many before the most recent?</font>
			<select name="<portlet:namespace />reqTopK">
				<option value="5">5</option>
		 	 	<option value="10" selected>10</option>
		 	 	<option value="20">20</option>
		 	 	<option value="30">30</option>
		 	 	<option value="all">all</option>
			</select>
		<br/>
		<input type="submit" value="Browse"> 
		<input type="reset"  value="Reset">
		<br/>
	</form> 
<p><a href="<%= viewURL %>">Back</a></p>