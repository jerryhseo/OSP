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
	List<String> resInputDataList = (List<String>)renderRequest.getAttribute("foundInputDataList");
	Long scienceAppId = (Long)renderRequest.getAttribute("reqSciAppId");
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(resInputDataList != null){
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
				<td><b>Matching ScienceAppID</b>: <%= scienceAppId %></td>
			</tr>
			<tr>
				<th>InputData Number</th>
				<th>Contents</th>
			</tr>
		<%
			// Iterate until we consume all tokens
			for(int i=0;i<resInputDataList.size();i++){
				String inputData = resInputDataList.get(i);
		%>
				<tr>
					<td><%= (i+1) %>:</td>
					<td><%= inputData %></td>
				</tr>
			<%
			}
			%>
		</table>
<%
	}
%>
<p><a href="<%= viewURL %>">Back</a></p>