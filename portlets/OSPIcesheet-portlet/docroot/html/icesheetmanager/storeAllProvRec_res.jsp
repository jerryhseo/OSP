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
	//String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	String scienceAppName = (String)renderRequest.getAttribute("reqAppName");
	String scienceAppVersion = (String)renderRequest.getAttribute("reqAppVersion");
	
	/* String simUuid = (String)renderRequest.getAttribute("reqSimUuid");
	String jobUuid = (String)renderRequest.getAttribute("reqJobUuid");
	String jobData = (String)renderRequest.getAttribute("reqJobData");
	
	String elapsedTime = (String)renderRequest.getAttribute("reqJobElapsedTime");
System.out.println("elapsed time: " + elapsedTime);
	String statusCode = (String)renderRequest.getAttribute("reqJobStatus"); */
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(scienceAppName != null && scienceAppVersion != null){
		try{
			HashMap<String, Long> resMap = ProvDataLoader.storeAllProvenances(scienceAppName, 
											   scienceAppVersion);	
%>
			
<%
			long totalCnt = 0;
			long dupCnt = 0;
			for (Map.Entry<String,Long> jobEntry : resMap.entrySet())
			{
				String keyName = jobEntry.getKey();
				if(keyName.contains(Constants.TOTAL_LOAD_CNT_STR))
					totalCnt = jobEntry.getValue();
				else
					dupCnt = jobEntry.getValue();
			}
%>		
				<table>
				<tr>
					<th>Science App Name</th>
					<th>Science App Version</th>
					<th><%= Constants.TOTAL_LOAD_CNT_STR %></th>
					<th><%= Constants.TOTAL_DUP_CNT_STR %></th>
				</tr>
				<tr>
					<td><%= scienceAppName %></td>
					<td><%= scienceAppVersion %></td>
					<td><%= totalCnt %></td>
					<td><%= dupCnt %></td>
				</tr>
			</table>
<%
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>