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
	String outputDir = (String)renderRequest.getAttribute("reqOutputDir");
System.out.println(outputDir);
	if(outputDir != null){
			Vector<String> fileNamesVec = ProvDataRetriever.getAllFileList(outputDir);	
System.out.println("num files: " + fileNamesVec.size());
			if(fileNamesVec.size()>0){
%>
				<table>
					<tr>
						<th>No.</th>
						<th>FileName</th>
					</tr>
<%
				for (int i=1;i<=fileNamesVec.size();i++)
				{
						String fileName = fileNamesVec.get(i-1);
%>
						<tr>
							<td><%= i %></td>
							<td><%= fileName %></td>
						</tr>
<%
				}
%>
				</table>
<%
			}
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>