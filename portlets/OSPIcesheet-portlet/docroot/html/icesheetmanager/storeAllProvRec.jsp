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

<portlet:actionURL var="storeProvRecActionURL" name="storeProvRecAction">
</portlet:actionURL> 

This is the <b>Store All Provenances</b> portlet in View mode.
<%
	/* SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
	String createDate = logsdf.format(new Date(System
			.currentTimeMillis()));
	String modifiedDate = createDate; */
%>

<form action='<%= storeProvRecActionURL %>' method="post">
	<!-- <font>*  Enter simulation uuid:</font>
		<input type="text" name="<portlet:namespace />reqSimUuidVal" value="729664e6-fa61-4eb0-9902-4ec3ecd8af3f" size=30 maxlength=50>
	-->
	<font>Choose a Science App:</font>
	<select name="<portlet:namespace />reqAppName">
	 	<option value="WaveSimulation">WaveSimulation(Nano)</option>
 	 	<option value="rollerCoaster">rollerCoaster(Nano)</option>
 	 	<option value="gravityslingshot">gravityslingshot(Nano)</option>
 	 	<option value="pianostring" selected>pianostring(Nano)</option>
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
	
	<!-- <font>Provide simulation uuid:</font>
	<input type="text" name="<portlet:namespace />reqSimUuid"> 
	<br/>
	
	<font>Provide job uuid:</font>
	<input type="text" name="<portlet:namespace />reqJobUuid"> 
	<br/>
	
	<font>Provide job elapsed time (sec):</font>
		<input type="text" value="1" name="<portlet:namespace />reqJobElapsedTime"> 
	<br/>
	
	<font>Provide job status:</font>
		<select name="<portlet:namespace />reqJobStatus">
		 	<option value="0" selected><%= Constants.JOB_SUCCESS %></option>
	 	 	<option value="1"><%= Constants.JOB_FAILURE %></option>
	 	</select>
	<br/>
	
	<font>Provide job data:</font>
	<textarea rows="10" cols="1000" name="<portlet:namespace />reqJobData"></textarea>
	<br/>
	-->
	<input type="submit" value="Store"> 
	<br/>
	<input type="reset"  value="Reset">
</form> 

<p><a href="<%= viewURL %>">Back</a></p>