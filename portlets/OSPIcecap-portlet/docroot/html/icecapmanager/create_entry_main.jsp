<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.LocalizationUtil"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<portlet:defineObjects />

<portlet:renderURL var="entryURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/create_entry.jsp" />
</portlet:renderURL>

<portlet:actionURL var="manageSimDataEntryActionURL" name="manageSimDataEntryAction">
</portlet:actionURL> 

This is the <b>Data Entry </b> Main portlet in View mode.

<%
	Long dataCollectionIdVal = (Long)renderRequest.getAttribute("reqDataCollectionIdVal");
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(dataCollectionIdVal == null){
%>
		Cannot find a DataCollection object matching requested data type name and version.
		Please check typed information again.
<%
	}
	else{
		// got typeId
		SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = logsdf.format(new Date(System
				.currentTimeMillis()));
		String modifiedDate = createDate;
%>
		<%-- <form action='<%= manageSimDataCollectionActionURL %>' method="post" enctype="multipart/form-data">
		 --%>
		 <form action='<%= manageSimDataEntryActionURL %>' method="post">
		 	<input type="hidden" name="<portlet:namespace />reqDataCollectionIdVal" value="<%= dataCollectionIdVal%>">
			<font>*  Enter science app Id:</font>
				<input type="text" name="<portlet:namespace />reqScienceAppIdVal" value="5901" size=5 maxlength=10>
			
			<br/>
			<font>*  Enter input data:</font>
				<textarea name="<portlet:namespace />reqInputDataVal" rows="10" cols="50">Provide your input data.</textarea>
			<br/>
			
			<font>*  Enter comments:</font>
				<textarea name="<portlet:namespace />reqCommentsVal" rows="10" cols="50">KFLOW_EDISON_5</textarea>
			<br/>
			
			<font>*  Enter input file path:</font>
				<input type="text" name="<portlet:namespace />reqPathVal" value="/EDISON/DATA_TYPE/input.path" size=100 maxlength=200>
			<br/>
			<font>*  Enter production time:</font>
				<input type="text" name="<portlet:namespace />reqProductionTimeVal" value="100" size=10>
			<br/>
			
			<input type="submit" value="Create"> <input type="submit" value="Add"> 
			<br/>
			<input type="reset"  value="Reset">
		</form>
<%
	}
%>

<p><a href="<%= entryURL %>">Back</a></p>