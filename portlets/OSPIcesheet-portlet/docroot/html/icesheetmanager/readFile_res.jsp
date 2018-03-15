<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.kisti.osp.icesheet.portlet.IceSheetManagerPortlet" %>
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataRetriever" %>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<%
	//String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	String outputFilePath = (String)renderRequest.getAttribute("reqOutputFilePath");
System.out.println(outputFilePath);
	if(outputFilePath != null){
			String contents = ProvDataRetriever.readOutputFile(outputFilePath);	
			if(contents != null){
%>
			<table>
				<tr>
					<th>FileName</th>
					<th>Contents</th>
				</tr>
					<tr>
						<td><%= outputFilePath %></td>
						<td><%= contents %></td>
					</tr>
<%
			}
%>
			</table>
<%
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>