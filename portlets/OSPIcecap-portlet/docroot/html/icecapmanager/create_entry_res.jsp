<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.kisti.osp.icecap.portlet.IceCapManagerPortlet" %>
<%@ page import="com.kisti.osp.icecap.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/view.jsp" />
</portlet:renderURL>

<%
	String dataCollectionIdVal = (String)renderRequest.getAttribute("reqDataCollectionIdVal");
	String dataScienceAppIdVal = (String)renderRequest.getAttribute("reqSciAppIdVal");
	String dataInputDataVal = (String)renderRequest.getAttribute("reqInputDataVal");
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(dataCollectionIdVal != null){
		/* StringTokenizer st = new StringTokenizer(clListStr, "\n");
		String libName = "";
		String libPath = "";
		String sysArch = "";
		int LIB_NAME_IDX = 1;
		int LIB_SYS_ARCH_IDX = 3;
		int LIB_PATH_IDX = 5; */ 
%>
		<table>
			<tr>
				<th>Requested Data Collection Id</th>
				<th>Requested ScienceApp Id</th>
				<th>Requested Input Data</th>
			</tr>
		<%
			// Iterate until we consume all tokens
			/* for(int i=0;i<clList.size();i++){
				String libName = (clList.get(i)).getLibName();
				String sysArch = (clList.get(i)).getSysArch();
				String libPath = (clList.get(i)).getLibPath(); */
		%>
				<tr>
					<td><%= dataCollectionIdVal %></td>
					<td><%= dataScienceAppIdVal %></td>
					<td><%= dataInputDataVal %></td>
				</tr>
			<%
			//}
			%>
		</table>
<%
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>