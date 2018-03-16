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
<%@ page import="com.kisti.osp.icesheet.portlet.ProvData" %>
<%@ page import="com.kisti.osp.icesheet.portlet.Constants" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/icesheetmanager/view.jsp" />
</portlet:renderURL>

<%
	//String reqSimUuidVal = (String)renderRequest.getAttribute("reqSimUuid");
	/* String subjectName = (String)renderRequest.getAttribute("reqAppName");
	String subjectVersion = (String)renderRequest.getAttribute("reqAppVersion");
	Long subjectId = null;
	
	String outputDirPath = null;
	String jobData = (String)renderRequest.getAttribute("reqJobData");
	if(subjectName != null && subjectVersion != null && jobData != null){
		try{
			subjectId = ProvDataCollector.getScienceAppId(subjectName, subjectVersion);
			outputDirPath = ProvDataMatcher.lookUpExistingProvenanceMain(subjectId, jobData);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	} */
	
	Vector<ProvData> resVec = (Vector<ProvData>)renderRequest.getAttribute("reqResVec");
	if(resVec == null){
		System.out.println("return vector is null");
	}else{
		System.out.println("# of elements: " + resVec.size());
		
	}
%>
	<table>
		<tr>
			<th>No.</th>
			<th>Simulation Title</th>
			<th>Simulation Create Date</th>
			<th>Output Directory</th>
		</tr>
	<%
		if(resVec != null){
			// Iterate until we consume all tokens
			for(int i=0;i<resVec.size();i++){
				String simUuid = (resVec.get(i)).simulationUuid;
				String simTitle = (resVec.get(i)).simulationTitle;
				String simCreateDt = (resVec.get(i)).simulationCreateDt;
				String outputDirPath = (resVec.get(i)).outputDirPath;
	%>
				<tr>
					<td><%= (i+1) %></td>
					<td><%= simTitle %></td>
					<td><%= simCreateDt %></td>
					<td><%= outputDirPath %></td>
				</tr>
	<%
			}
		}
	%>
	</table>
<p><a href="<%= viewURL %>">Back</a></p>