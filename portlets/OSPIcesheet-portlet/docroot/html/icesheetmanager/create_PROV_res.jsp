<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.kisti.osp.icesheet.portlet.IceSheetManagerPortlet" %>
<%@ page import="com.kisti.osp.icesheet.portlet.ProvDataCollector" %>
<%@ page import="com.kisti.osp.icesheet.portlet.PROVModeller" %>
<%@ page import="com.kisti.osp.icesheet.portlet.PROVDocWriter" %>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<%
	String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(reqSimUuidVal != null){
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
				<th>Requested Simulation Uuid</th>
			</tr>
		<%
			// Iterate until we consume all tokens
			/* for(int i=0;i<clList.size();i++){
				String libName = (clList.get(i)).getLibName();
				String sysArch = (clList.get(i)).getSysArch();
				String libPath = (clList.get(i)).getLibPath(); */
		%>
				<!-- <tr>
					<td>TypeName</td>
					<td>TypeVersion</td>
					<td>TypeSamplePath</td>
				</tr> -->
				<tr>
					<td><%= reqSimUuidVal %></td>
				</tr>
			<%
			//}
			%>
		</table>
	<%
		ProvDataCollector.collectProvenanceData(reqSimUuidVal);
		String provDoc = PROVModeller.constructPROVDoc(
				  ProvDataCollector.getUserInfoRecords(),
				  ProvDataCollector.getSimulationRecords(), 
				  ProvDataCollector.getSimulationJobRecords(), 
				  ProvDataCollector.getSimulationJobDataRecords());
		PROVDocWriter.writetoFile(provDoc);
		//ProvDataCollector.flushProvenanceData();
		
		/* String solvName = simUuidQueryVal;
		ProvDataCollector.collectJobData(solvName);*/
	}
	%>
<p><a href="<%= viewURL %>">Back</a></p>