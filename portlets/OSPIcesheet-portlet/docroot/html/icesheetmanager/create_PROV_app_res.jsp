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
	int simCounts = 0;
	String reqAppName = (String)renderRequest.getAttribute("reqAppName");
	String reqAppVersion = (String)renderRequest.getAttribute("reqAppVersion");
	String reqStartDateTime = (String)renderRequest.getAttribute("reqStartDateTime");
	String reqEndDateTime = (String)renderRequest.getAttribute("reqEndDateTime");

	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(reqAppName != null 
	&& reqAppVersion != null
	&& reqStartDateTime != null
	&& reqEndDateTime != null
	){
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
				<th>Requested App and Simulation Info.</th>
			</tr>
		<%
			// Iterate until we consume all tokens
			/* for(int i=0;i<clList.size();i++){
				String libName = (clList.get(i)).getLibName();
				String sysArch = (clList.get(i)).getSysArch();
				String libPath = (clList.get(i)).getLibPath(); */
		%>
				<tr>
					<td>AppName</td>
					<td>AppVersion</td>
					<td>SimulationStartDate</td>
					<td>SimulationEndDate</td>
				</tr> 
				<tr>
					<td><%= reqAppName %></td>
					<td><%= reqAppVersion %></td>
					<td><%= reqStartDateTime %></td>
					<td><%= reqEndDateTime %></td>
				</tr>
			<%
			//}
			%>
			<%
				HashMap<String, Vector<Object>> provDocVecMap = ProvDataCollector.collectProvenanceDataByApp(reqAppName,reqAppVersion,reqStartDateTime,reqEndDateTime);
				simCounts = provDocVecMap.size();
				String provDoc = PROVModeller.constructPROVDoc(
						  ProvDataCollector.getUserInfoRecords(),
						  ProvDataCollector.getSimulationRecords(), 
						  ProvDataCollector.getSimulationJobRecords(), 
						  ProvDataCollector.getSimulationJobDataRecords());
				
				//PROVDocWriter.writetoFile(provDoc);
				//ProvDataCollector.flushProvenanceData();
				
				/* String solvName = simUuidQueryVal;
				ProvDataCollector.collectJobData(solvName);*/
			}
		%>
			<tr>
					<td><b>Total Simulation Counts</b>: <%= simCounts %></td>
				</tr> 
		</table>
<p><a href="<%= viewURL %>">Back</a></p>