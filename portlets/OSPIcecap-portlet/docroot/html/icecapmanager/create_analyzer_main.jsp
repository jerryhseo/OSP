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

<portlet:renderURL var="findDataTypeURL">
    <portlet:param name="jspPage" value="/html/icecapmanager/search_data_type.jsp" />
</portlet:renderURL>

<portlet:actionURL var="addDataTypeAnalyzerActionURL" name="addDataTypeAnalyzerAction">
</portlet:actionURL> 

This is the <b>Add Analyzer</b> Main portlet in View mode.

<%
	Long dataTypeIdVal = (Long)renderRequest.getAttribute("reqDataTypeIdVal");
	
	//List<DataType> dtList = (List<DataType>)renderRequest.getAttribute("foundDTList");
	//if(dtList != null){
	if(dataTypeIdVal == null){
%>
		Cannot find a data type matching requested data type name and version.
		Please type again.
<%
	}
	else{
		// got typeId
		/* SimpleDateFormat logsdf = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = logsdf.format(new Date(System
				.currentTimeMillis()));
		String modifiedDate = createDate; */
%>
		<%-- <form action='<%= manageSimDataCollectionActionURL %>' method="post" enctype="multipart/form-data">
		 --%>
		 <form action='<%= addDataTypeAnalyzerActionURL %>' method="post">
		 	<input type="hidden" name="<portlet:namespace />reqDataTypeIdVal" value="<%= dataTypeIdVal%>">
			<%-- <font>*  Enter simulation data analyzer name:</font>
				<input type="text" name="<portlet:namespace />reqDataCollectionNameVal" value="yksuh" size=10 maxlength=20>
			
			<br/> --%>
			<font>*  Enter data type analyzer ID:</font>
				<input type="text" name="<portlet:namespace />reqDataTypeAnalyzerIdVal" value="77777" size=10 maxlength=10>
			<br/>
			<font>*  Is this default?</font>
				<select name="<portlet:namespace />reqIsDefaultVal">
				  <option selected value="1">yes</option>
			  	  <option value="0">no</option>
				</select>
			<br/>
			<input type="submit" value="Create"> <input type="submit" value="Add"> 
			<br/>
			<input type="reset"  value="Reset">
		</form>
<%
	}
%>

<p><a href="<%= findDataTypeURL %>">Back</a></p>