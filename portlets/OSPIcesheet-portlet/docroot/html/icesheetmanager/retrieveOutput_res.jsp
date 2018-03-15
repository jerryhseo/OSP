<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.kisti.osp.icesheet.portlet.IceSheetManagerPortlet" %>
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataCollector" %>
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataMatcher" %>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<%
	//String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	String subjectName = (String)renderRequest.getAttribute("reqAppName");
	String subjectVersion = (String)renderRequest.getAttribute("reqAppVersion");
	String outputDirPath = (String)renderRequest.getAttribute("reqOutputDirPath");
	//Long subjectId = null;
	/* String outputDirPath = null;
	String jobData = (String)renderRequest.getAttribute("reqJobData");
	if(subjectName != null && subjectVersion != null && jobData != null){
		try{
			subjectId = ProvDataCollector.getScienceAppId(subjectName, subjectVersion);
			outputDirPath = ProvDataMatcher.lookUpExistingProvenanceMain(subjectId, jobData);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	} */
%>
	<table>
		<tr>
			<th>Science App Name</th>
			<th>Science App Version</th>
			<th>Output Directory Path</th>
		</tr>
		<tr>
			<td><%= subjectName %></td>
			<td><%= subjectVersion %></td>
			<td><%= outputDirPath %></td>
		</tr>
	</table>
<p><a href="<%= viewURL %>">Back</a></p>