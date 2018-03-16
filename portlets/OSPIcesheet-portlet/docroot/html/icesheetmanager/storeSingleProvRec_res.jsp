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
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataRetriever" %>
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataLoader" %>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<%
	String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	String userName = (String)renderRequest.getAttribute("reqUserName");
	Long subjectId = (Long)renderRequest.getAttribute("reqAppId");
	String scienceAppName = (String)renderRequest.getAttribute("reqAppName");
	String scienceAppVersion = (String)renderRequest.getAttribute("reqAppVersion");
	String simTitle = (String)renderRequest.getAttribute("reqSimTitle");
	String simCreateDt = (String)renderRequest.getAttribute("reqSimCreateDt");
	String simUuid = (String)renderRequest.getAttribute("reqSimUuid");
	String jobUuid = (String)renderRequest.getAttribute("reqJobUuid");
	String jobInputFileDir = (String)renderRequest.getAttribute("reqJobInputFileDir");
	String jobData = (String)renderRequest.getAttribute("reqJobData");
	String jobStartDt = (String)renderRequest.getAttribute("reqJobStartDt");
	String jobEndDt = (String)renderRequest.getAttribute("reqJobEndDt");
	String statusCode = (String)renderRequest.getAttribute("reqJobStatus");
	
	if (scienceAppName != null && scienceAppVersion != null && simUuid != null){
		try{
			String path = ProvDataLoader.loadSimulationProvenance(reqSimUuidVal, userName, 
					subjectId, scienceAppName, scienceAppVersion, simTitle, 
					simCreateDt, jobUuid, jobInputFileDir, jobData, statusCode, 
					jobStartDt, jobEndDt);
			
			String loadMsg =path;
%>		
				<table>
				<tr>
					<th>Science App Name</th>
					<th>Science App Version</th>
					<th>Output path</th>
				</tr>
				<tr>
					<td><%= scienceAppName %></td>
					<td><%= scienceAppVersion %></td>
					<td><%= loadMsg %></td>
				</tr>
			</table>
<%
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>