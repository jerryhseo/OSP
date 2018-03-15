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

<portlet:actionURL var="storeSingleProvRecActionURL" name="storeSingleProvRecAction">
</portlet:actionURL> 

<%
	String jobData = "{\"-param\":{\"port_data\":\"v0 = 14.5 ; thetaV0 = 90 ; thetaE0 = 0 ; thetaJ0 = 160 ;\",\"editor_type\":\"Inputdeck\"}}";
%>
This is the <b>Single Provenance Insertion</b> portlet in View mode.

<form action='<%= storeSingleProvRecActionURL %>' method="post">
	<font>Provide simulation uuid:</font>
	<input type="text" value="0073da90-b1d0-4849-95fb-2e05074ef837" name="<portlet:namespace />reqSimUuid"> 
	<br/>
	<font>Provide user name:</font>
	<input type="text" value="c02891710052" name="<portlet:namespace />reqUserName"> 
	<br/>
	<font>Provide science app id:</font>
	<input type="text" value="27901" name="<portlet:namespace />reqAppId"> 
	<br/>
	
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
	
	<font>Provide simulation title:</font>
	<input type="text" value="dh2" name="<portlet:namespace />reqSimTitle"> 
	<br/>
	
	<font>Provide simulation create date:</font>
		<input type="text" value="2017-04-28 19:14:23" name="<portlet:namespace />reqSimCreateDt"> 
	<br/>
	
	<font>Provide job uuid:</font>
	<input type="text" value="dde41f63-decd-4813-9cbf-314c1ca6e009" name="<portlet:namespace />reqJobUuid"> 
	<br/>
	
	<font>Provide job input file dir:</font>
	<input type="text" name="<portlet:namespace />reqJobInputFileDir"> 
	<br/>
	
	<font>Provide job start timestamp:</font>
		<input type="text" value="2017-04-28 19:14:19" name="<portlet:namespace />reqJobStartDt"> 
	<br/>
	
	<font>Provide job end timestamp</font>
		<input type="text" value="2017-04-28 19:53:31" name="<portlet:namespace />reqJobEndDt"> 
	<br/>
	
	<font>Provide job status:</font>
		<select name="<portlet:namespace />reqJobStatus">
		 	<option value="1701011" selected><%= Constants.JOB_SUCCESS %></option>
	 	 	<option value="1701012"><%= Constants.JOB_FAILURE %></option>
	 	</select>
	<br/>
	
	<font>Provide job data:</font>
		<textarea rows="10" cols="1000" name="<portlet:namespace />reqJobData"><%= jobData %></textarea>
	<br/>
	
	<input type="submit" value="Store"> 
	<br/>
	<input type="reset"  value="Reset">
	<br/>
</form> 

<p><a href="<%= viewURL %>">Back</a></p>